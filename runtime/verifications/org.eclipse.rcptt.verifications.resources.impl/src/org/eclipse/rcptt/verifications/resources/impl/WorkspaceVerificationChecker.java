package org.eclipse.rcptt.verifications.resources.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.rcptt.core.Q7Features;
import org.eclipse.rcptt.resources.WSUtils;
import org.eclipse.rcptt.util.resources.ResourcesUtil;
import org.eclipse.rcptt.verifications.resources.internal.impl.Activator;
import org.eclipse.rcptt.workspace.WSFile;
import org.eclipse.rcptt.workspace.WSFolder;
import org.eclipse.rcptt.workspace.WSProject;
import org.eclipse.rcptt.workspace.WSRoot;
import org.eclipse.rcptt.workspace.WorkspaceVerification;

public class WorkspaceVerificationChecker {

	private WSRoot root;
	private String location;
	private boolean allowUncapturedFiles;
	private List<Pattern> ignoredLinePatterns;

	public WorkspaceVerificationChecker(WorkspaceVerification verification) throws CoreException {
		this.root = verification.getContent();
		this.location = verification.getLocation();
		this.allowUncapturedFiles = verification.isAllowUncapturedFiles();

		if (verification.getIgnoredLines() != null) {
			this.ignoredLinePatterns = new ArrayList<Pattern>();
			for (String skippedline : verification.getIgnoredLines().split("\n")) {
				try {
					this.ignoredLinePatterns.add(Pattern.compile(skippedline));
				} catch (PatternSyntaxException e) {
					throw error(String.format("Invalid '%s' regex", skippedline), e);
				}
			}
		}
	}

	public void verifyWorkspace() throws CoreException {
		for (final WSProject project : root.getProjects()) {
			final IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
			final IProject rProject = ResourcesUtil.getCaseInsensitiveChild(wsRoot,
					project.getName(), IProject.class);
			verifyProject(location, project, rProject);
		}
	}

	private void verifyProject(final String location, final WSProject project,
			final IProject rProject) throws CoreException {
		if (rProject == null) {
			throw error(String.format("Project '%s' is not found", project.getName()));
		}
		if (!rProject.exists()) {
			throw error(String.format("Project '%s' does not exist", project.getName()));
		}
		if (!rProject.isOpen()) {
			throw error(String.format("Project '%s' is not open", project.getName()));
		}
		verifyContainer(location, project, rProject);
	}
	private void verifyContainer(final String location, final WSFolder folder,
			final IContainer rFolder) throws CoreException {
		if (!rFolder.exists()) {
			throw error(String.format("Folder '%s' does not exist", folder.getName()));
		}
		if (!allowUncapturedFiles) {
			for (IResource rResource : rFolder.members()) {
				if (!(rResource instanceof IFile)) {
					continue;
				}
				boolean found = false;
				String rFileName = ((IFile) rResource).getName();
				for (WSFile file : folder.getFiles()) {
					if (file.getName().equals(rFileName)) {
						found = true;
						break;
					}
				}
				if (!found) {
					throw error(String.format("Folder '%s' contains unexpected '%s' file",
							folder.getName(), rFileName));
				}
			}
		}
		verifyContent(location, folder, rFolder);
	}

	private void verifyContent(final String location, final WSFolder folder,
			final IContainer rFolder) throws CoreException {
		for (final WSFolder child : folder.getFolders()) {
			final IFolder rChild = rFolder.getFolder(new Path(child.getName()));
			verifyContainer(location, child, rChild);
		}
		for (final WSFile child : folder.getFiles()) {
			final IFile rChild = rFolder.getFile(new Path(child.getName()));
			verifyFile(location, child, rChild);
		}
	}

	private void verifyFile(final String location, final WSFile file,
			final IFile rFile) throws CoreException {
		if (!rFile.exists()) {
			throw error(String.format("File '%s' does not exist", file.getName()));
		}
		try {
			InputStream stream = null;
			if (file.getContent() == null) {
				stream = WSUtils.getFileStream(location, file, null);
			} else {
				if (Q7Features.getInstance().isTrue(
						Q7Features.Q7_CONTEXTS_RESOURCES_ZIPPED_TRANSFER)) {

					ZipInputStream zin = new ZipInputStream(
							new ByteArrayInputStream(file.getContent()));
					zin.getNextEntry();
					stream = zin;
				} else {
					stream = new ByteArrayInputStream(file.getContent());
				}
			}
			InputStream rStream = rFile.getContents();
			try {
				if (isTextFile(file.getName()) && isTextFile(rFile.getName())) {
					final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
					final BufferedReader rReader = new BufferedReader(new InputStreamReader(rStream));
					verifyLineByLine(reader, rReader, 1);
				} else {
					final InputStreamReader reader = new InputStreamReader(stream);
					final InputStreamReader rReader = new InputStreamReader(rStream);
					verifyCharByChar(reader, rReader, 1);
				}
			} catch (CoreException e) {
				throw error(
						String.format("Error while verifying '%s' file:\n%s", file.getName(), e.getMessage()));
			} finally {
				WSUtils.safeClose(rStream);
			}
		} catch (final IOException e) {
			final Status status = new Status(IStatus.ERROR,
					Activator.PLUGIN_ID, e.getMessage(), e);
			throw new CoreException(status);
		}
	}

	private void verifyLineByLine(final BufferedReader reader, final BufferedReader rReader,
			int lineNumber) throws IOException, CoreException {
		String line = null;
		String rLine = null;
		if (reader.ready()) {
			line = reader.readLine();
		}
		if (rReader.ready()) {
			rLine = rReader.readLine();
		}
		if (line == null && rLine == null) {
			return;
		}
		if (line != null && !line.equals(rLine) || rLine != null && !rLine.equals(line)) {
			if (!isSkippedLine(line) || !isSkippedLine(rLine)) {
				throw error(String.format("Text on %d line do not match. Expected '%s',\nbut was '%s'.",
						lineNumber, line, rLine));
			}
		}
		verifyLineByLine(reader, rReader, ++lineNumber);
	}

	private void verifyCharByChar(final InputStreamReader reader, final InputStreamReader rReader, int byteNumber)
			throws IOException, CoreException {
		final int value = reader.read();
		final int rValue = rReader.read();
		if (value == -1 && rValue == -1) {
			return;
		}
		if (value != rValue) {
			String symbol = value == -1 ? null
					: String.valueOf((char) value).replaceAll("\\n", "\\\\n").replaceAll("\\r", "\\\\r");
			String rSymbol = rValue == -1 ? null
					: String.valueOf((char) rValue).replaceAll("\\n", "\\\\n").replaceAll("\\r", "\\\\r");

			throw error(String.format("Symbols on %d position do not match. Expected '%s', but was '%s'.",
					byteNumber, symbol, rSymbol));
		}
		verifyCharByChar(reader, rReader, ++byteNumber);
	}

	private final IContentType TEXT = Platform.getContentTypeManager().getContentType(IContentTypeManager.CT_TEXT);

	private boolean isTextFile(final String fileName) throws IOException {
		final IContentType type = Platform.getContentTypeManager().findContentTypeFor(fileName);
		return type == null ? false : type.isKindOf(TEXT);
	}

	private boolean isSkippedLine(final String line) throws IOException {
		if (line == null) {
			return false;
		}
		if (ignoredLinePatterns == null) {
			return false;
		}
		for (Pattern ignoredLine : ignoredLinePatterns) {
			Matcher matcher = ignoredLine.matcher(line);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}

	private CoreException error(String message) {
		return error(message, null);
	}

	private CoreException error(String message, Throwable exception) {
		return new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, message, exception));
	}

}
