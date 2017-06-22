/*******************************************************************************
 * Copyright (c) 2009, 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ctx.workbench.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.rcptt.core.IContextProcessor;
import org.eclipse.rcptt.core.scenario.Context;
import org.eclipse.rcptt.core.scenario.ScenarioFactory;
import org.eclipse.rcptt.core.scenario.WorkbenchContext;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.tesla.core.TeslaLimits;
import org.eclipse.rcptt.tesla.ecl.impl.UIRunnable;
import org.eclipse.rcptt.tesla.ecl.impl.Utils;
import org.eclipse.rcptt.tesla.internal.ui.player.UIJobCollector;
import org.eclipse.rcptt.tesla.swt.workbench.EclipseWorkbenchProvider;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.IIntroPart;
import org.osgi.framework.Bundle;

public class WorkbenchContextProcessor implements IContextProcessor {

	@Override
	public boolean isApplied(final Context context) {
		return UIRunnable.safeExec(new UIRunnable<Boolean>() {
			@Override
			public Boolean run() throws CoreException {
				IWorkbenchWindow window = getWindow();
				if (window != null) {
					IWorkbenchPage page = window.getActivePage();
					if (page != null) {
						IPerspectiveDescriptor descriptor = page.getPerspective();
						if (descriptor != null) {
							return descriptor.getId().equals(getPerspectiveId(context));
						}
					}
				}
				return false;
			}
		});
	}

	public boolean isCreateAllowed() {
		return true;
	}

	private UIRunnable<IStatus> closeModalDialogs = new UIRunnable<IStatus>() {
		@Override
		public IStatus run() throws CoreException {
			try {
				return Utils.closeDialogs();
			} catch (Throwable e) {
				return RcpttPlugin.createStatus(e);
			}
		}
	};
	private Runnable closeModalDialogsAsync = new Runnable() {
		public void run() {
			Utils.closeDialogs();
		}
	};
	private UIRunnable<Object> closeIntro = new UIRunnable<Object>() {
		@Override
		public Object run() throws CoreException {
			IIntroManager manager = PlatformUI.getWorkbench().getIntroManager();
			if (manager != null) {
				IIntroPart intro = manager.getIntro();
				if (intro != null) {
					manager.closeIntro(intro);
				}
			}
			return null;
		}
	};
	private UIRunnable<Object> clearClipboard = new UIRunnable<Object>() {
		@Override
		public Object run() throws CoreException {
			Clipboard clipboard = new Clipboard(EclipseWorkbenchProvider.getProvider().getDisplay());
			// First put something into clipboard, to force our
			// clipboard became owner of system clipboard
			clipboard.setContents(new Object[] { " " }, new Transfer[] { TextTransfer.getInstance() });

			// clipboard.clearContents(DND.CLIPBOARD);
			// clipboard.clearContents(DND.SELECTION_CLIPBOARD);
			clipboard.clearContents();
			return null;
		}
	};

	public void apply(final Context context) throws CoreException {
		final WorkbenchContext ctx = (WorkbenchContext) context;
		final UIJobCollector collector = new UIJobCollector();
		Job.getJobManager().addJobChangeListener(collector);
		try {
			EclipseWorkbenchProvider.getProvider().getDisplay().asyncExec(new Runnable() {
				public void run() {
					collector.enable();
				}
			});
			if (ctx.isNoModalDialogs()) {
				EclipseWorkbenchProvider.getProvider().getDisplay().asyncExec(closeModalDialogsAsync);
				EclipseWorkbenchProvider.getProvider().getDisplay().asyncExec(closeModalDialogsAsync);
				IStatus status = UIRunnable.exec(closeModalDialogs);
				if (!status.isOK())
					throw new CoreException(status);
			}
			UIRunnable.exec(closeIntro);
			final IWorkbenchPage page = UIRunnable.exec(activatePerspective(ctx));
			if (ctx.isCloseEditors()) {
				if (page != null) {
					UIRunnable.exec(closeAllEditors(page));
				}
			}
			if (ctx.isClearClipboard()) {
				UIRunnable.exec(clearClipboard);
			}
			String perspectiveId = getPerspectiveId(ctx);
			if (page != null && perspectiveId != null && perspectiveId.length() > 0 && ctx.isResetPerspective()) {
				// Close all perspectives

				// Wait until some jobs to finish, before trying to close
				// perspective
				// collector.addAllJobs(10 * 1000);
				setPageInput(page, getDefaultPageInput());
				UIRunnable.exec(closeAllPerspectives(page));
				UIRunnable.exec(cleanOtherPerspectives(page));
			}

			UIRunnable.exec(setPerspective(ctx, page));

			openParts(ctx);

			updateSelection(ctx);
			UIRunnable.exec(new UIRunnable<Object>() {
				@Override
				public Object run() throws CoreException {
					collector.setNeedDisable();
					return null;
				}
			});
			collector.join(TeslaLimits.getContextJoinTimeout());
		} catch (Exception e) {
			CoreException ee = new CoreException(RcpttPlugin
					.createStatus("Failed to execute context: " + ctx.getName() + " Cause: " + e.getMessage(), e));
			RcpttPlugin.log(e);
			throw ee;
		} finally {
			Job.getJobManager().removeJobChangeListener(collector);
		}
	}

	private void updateSelection(WorkbenchContext ctx) {
		IWorkbenchWindow[] windows = EclipseWorkbenchProvider.getProvider().getWorkbenchWindows();
		for (IWorkbenchWindow win : windows) {
			IWorkbenchPage[] pages = win.getPages();
			for (IWorkbenchPage page0 : pages) {
				IViewReference[] references = page0.getViewReferences();
				for (IViewReference ref : references) {
					if (!ctx.getViews().contains(ref.getId())) {
						continue;
					}
					IWorkbenchPart part = ref.getPart(false);
					if (part != null) {
						final ISelectionProvider provider = part.getSite().getSelectionProvider();
						if (provider != null) {
							try {
								UIRunnable.exec(setSelection(provider));
							} catch (Throwable e) {
								RcpttPlugin.log(e);
							}
						}
					}
				}
			}
		}
	}

	private UIRunnable<Object> setSelection(final ISelectionProvider provider) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				provider.setSelection(new StructuredSelection());
				return null;
			}
		};
	}

	private UIRunnable<Object> setPerspective(final WorkbenchContext ctx, final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				showPerspective(ctx);
				if (page != null && page.getSortedPerspectives().length == 0) {
					String defaultPerspectiveId = PlatformUI.getWorkbench().getPerspectiveRegistry()
							.getDefaultPerspective();
					IPerspectiveDescriptor perspectiveDesc = PlatformUI.getWorkbench().getPerspectiveRegistry()
							.findPerspectiveWithId(defaultPerspectiveId);
					page.setPerspective(perspectiveDesc);
				}
				return null;
			}
		};
	}

	private UIRunnable<Object> cleanOtherPerspectives(final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@SuppressWarnings("restriction")
			@Override
			public Object run() throws CoreException {
				// Clean other perspectives
				for (final IPerspectiveDescriptor persp : page.getWorkbenchWindow().getWorkbench()
						.getPerspectiveRegistry().getPerspectives()) {
					// if (descriptors.contains(persp)) {
					Bundle bundle = Platform.getBundle("org.eclipse.osgi");
					bundle.getVersion();

					IPreferenceStore store = org.eclipse.ui.internal.WorkbenchPlugin.getDefault().getPreferenceStore();

					store.setToDefault(persp.getId() + "_persp");
				}
				return null;
			}
		};
	}

	private UIRunnable<Object> closeAllPerspectives(final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				try {
					page.closeAllPerspectives(false, false);
				} catch (Throwable e) {
					RcpttPlugin.log(e);
				}
				return null;
			}
		};
	}

	private UIRunnable<Object> closeAllEditors(final IWorkbenchPage page) {
		return new UIRunnable<Object>() {
			@Override
			public Object run() throws CoreException {
				page.closeAllEditors(false);
				return null;
			}
		};
	}

	private UIRunnable<IWorkbenchPage> activatePerspective(final WorkbenchContext ctx) {
		return new UIRunnable<IWorkbenchPage>() {
			@Override
			public IWorkbenchPage run() throws CoreException {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage lpage = window.getActivePage();
				if (lpage == null) {
					lpage = showPerspective(ctx);
				}
				return lpage;
			}
		};
	}

	private Object getDefaultPageInput() {
		try {
			return ResourcesSupport.getWorkspaceRoot();
		} catch (Throwable e) {
		}
		return null;
	}

	@SuppressWarnings("restriction")
	protected void setPageInput(IWorkbenchPage page, Object value) {
		try {
			Field field = org.eclipse.ui.internal.WorkbenchPage.class.getDeclaredField("input");
			field.setAccessible(true);
			field.set(page, value);
		} catch (SecurityException e) {
			Activator.log(e);
		} catch (NoSuchFieldException e) {
			Activator.log(e);
		} catch (IllegalArgumentException e) {
			Activator.log(e);
		} catch (IllegalAccessException e) {
			Activator.log(e);
		}
	}

	@Override
	public Context create(EObject param) throws CoreException {
		return UIRunnable.exec(new UIRunnable<WorkbenchContext>() {
			@Override
			public WorkbenchContext run() throws CoreException {
				return doCreate();
			}
		});
	}

	private IWorkbenchPage showPerspective(WorkbenchContext context) throws CoreException {
		// open perspective
		IWorkbench workbench = PlatformUI.getWorkbench();
		String perspective = getPerspectiveId(context);
		IWorkbenchWindow window = getWindow();
		if (perspective != null && perspective.length() > 0) {
			return workbench.showPerspective(perspective, window);
		}
		if (window == null) {
			window = workbench.getWorkbenchWindows()[0];
		}
		IWorkbenchPage activePage = window.getActivePage();
		if (activePage == null) {
			IWorkbenchPage[] pages = window.getPages();
			for (IWorkbenchPage page : pages) {
				activePage = page;
			}
		}
		return activePage;
	}

	private void openParts(WorkbenchContext context) throws CoreException {
		final IWorkbench workbench = PlatformUI.getWorkbench();

		final IWorkbenchPage page = UIRunnable.exec(new UIRunnable<IWorkbenchPage>() {
			@Override
			public IWorkbenchPage run() throws CoreException {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window == null) {
					window = workbench.getWorkbenchWindows()[0];
				}
				IWorkbenchPage activePage = window.getActivePage();
				if (activePage == null) {
					IWorkbenchPage[] pages = window.getPages();
					if (pages.length > 0) {
						activePage = pages[0];
					}
				}
				return activePage;
			}
		});
		if (page != null) {
			// show views
			for (final String viewId : context.getViews()) {
				IViewReference[] references = page.getViewReferences();
				boolean found = false;
				for (IViewReference iViewReference : references) {
					if (viewId.equals(iViewReference.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					UIRunnable.exec(new UIRunnable<Object>() {
						@Override
						public Object run() throws CoreException {
							page.showView(viewId);
							return null;
						}
					});
				}
			}
			// close opened editors
			if (context.isCloseEditors()) {
				UIRunnable.exec(new UIRunnable<Object>() {
					@Override
					public Object run() throws CoreException {
						page.closeAllEditors(false);
						return null;
					}
				});
			}
			// open editors
			try {
				ResourcesSupport.openEditors(page, context);
			} catch (CoreException e) {
				throw e;
			} catch (Throwable e) {

			}
		}
	}

	private WorkbenchContext doCreate() throws CoreException {
		IWorkbenchWindow window = getWindow();
		IWorkbenchPage page = window.getActivePage();
		WorkbenchContext context = ScenarioFactory.eINSTANCE.createWorkbenchContext();

		IPerspectiveDescriptor descriptor = page.getPerspective();
		if (descriptor != null) {
			context.setPerspectiveId(descriptor.getId());
		}

		IViewReference[] views = page.getViewReferences();
		List<IViewReference> viewReference = sortViewsByActive(page, views);
		for (IViewReference view : viewReference) {
			context.getViews().add(view.getId());
		}

		ResourcesSupport.fillEditors(page, context);
		return context;
	}

	private List<IViewReference> sortViewsByActive(IWorkbenchPage page, IViewReference[] viewReferences) {
		List<IViewReference> references = new ArrayList<IViewReference>();
		List<IViewReference> referencesToSort = new ArrayList<IViewReference>();
		for (IViewReference reference : viewReferences) {
			if (reference.getView(false) == null) {
				references.add(reference);
			} else {
				referencesToSort.add(reference);
			}
		}

		sortViewsByActive(page, referencesToSort);
		references.addAll(referencesToSort);

		return references;
	}

	private void sortViewsByActive(IWorkbenchPage page, List<IViewReference> viewReferences) {
		List<IViewReference> sorted = new ArrayList<IViewReference>();
		while (!viewReferences.isEmpty()) {
			IViewReference viewReference = viewReferences.get(0);
			IViewPart viewPart = viewReference.getView(false);
			IViewPart[] stack = page.getViewStack(viewPart);
			if (stack == null) {
				sorted.add(viewReference);
			} else {
				for (int i = stack.length - 1; i >= 0; i--) {
					IViewPart stackPart = stack[i];
					IViewReference target = null;
					for (IViewReference r : viewReferences) {
						if (r.getView(false).equals(stackPart)) {
							target = r;
							break;
						}
					}
					if (target != null) {
						viewReferences.remove(target);
						sorted.add(target);
					}
				}
			}
		}
		viewReferences.addAll(sorted);
	}

	private IWorkbenchWindow getWindow() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	private String getPerspectiveId(Context context) {
		WorkbenchContext pContext = (WorkbenchContext) context;
		return pContext.getPerspectiveId();
	}
}
