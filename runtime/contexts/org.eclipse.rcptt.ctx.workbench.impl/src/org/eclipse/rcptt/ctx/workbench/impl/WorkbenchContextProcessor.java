/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.core.IContextProcessor;
import org.eclipse.rcptt.core.scenario.Context;
import org.eclipse.rcptt.core.scenario.ScenarioFactory;
import org.eclipse.rcptt.core.scenario.WorkbenchContext;
import org.eclipse.rcptt.tesla.ecl.impl.UIRunnable;
import org.eclipse.rcptt.tesla.swt.workbench.EclipseWorkbenchProvider;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class WorkbenchContextProcessor implements IContextProcessor {


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
	

	public void apply(final Context context) throws CoreException {
		EclipseWorkbenchProvider.getProvider().applyContext(context);
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

	public Context create(EObject param) throws CoreException {
		return UIRunnable.exec(new UIRunnable<WorkbenchContext>() {
			@Override
			public WorkbenchContext run() throws CoreException {
				return doCreate();
			}
		});
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
