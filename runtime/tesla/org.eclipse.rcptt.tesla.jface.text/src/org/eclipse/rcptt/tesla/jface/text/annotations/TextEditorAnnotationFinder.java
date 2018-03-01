package org.eclipse.rcptt.tesla.jface.text.annotations;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.rcptt.tesla.core.ui.Marker;
import org.eclipse.rcptt.tesla.jface.text.JFaceTextActivator;

public class TextEditorAnnotationFinder {

	private static ITextEditorAnnotationFinder finder = null;

	private static ITextEditorAnnotationFinder getFinder() {
		if (finder == null) {
			final String extensionPointId = JFaceTextActivator.PLUGIN_ID + ".annotationFinder";
			IConfigurationElement[] elements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(extensionPointId);
			for (IConfigurationElement element : elements) {
				try {
					ITextEditorAnnotationFinder extension = (ITextEditorAnnotationFinder) element
							.createExecutableExtension("class");
					if (extension.isSupported()) {
						finder = extension;
					}
				} catch (CoreException e) {
					throw new RuntimeException("Failed to create " + element.getName(), e);
				}
			}
			if (finder == null) {
				finder = new DefaultFinder();
			}
		}
		return finder;
	}

	public static Map<String, EList<Marker>> findAnnotations(TextViewer viewer) {
		return getFinder().findAnnotations(viewer);
	}

	private static class DefaultFinder implements ITextEditorAnnotationFinder {

		@Override
		public boolean isSupported() {
			return true;
		}

		@Override
		public Map<String, EList<Marker>> findAnnotations(TextViewer viewer) {
			return new HashMap<String, EList<Marker>>();
		}

	}

}
