package org.eclipse.rcptt.runtime.ui.model.e4x;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.internal.workbench.Activator;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public class E4ModelProcessor {
 
	private static E4ModelProcessor INSTANCE = null;

	@Inject
	private MApplication application;

	private Display display;
	private EPartService partService;
	private EModelService modelService;

	@Execute
	public void execute(Display display,
			EModelService modelService,
			EPartService partService) {
		this.display = display;
		this.partService = partService;
		this.modelService = modelService;
		INSTANCE = this;
	}

	/**
	 * Tries to guess whether the given part is used as a view or an editor, and returns the corresponding
	 * {@link GenericElementKind}.
	 */
	public static GenericElementKind getPartKind(MPart part) {
		// TODO: Find a better way to recognize editors! Currently, this considers a part to be an editor if the
		// implementing model has a field of type MDirtyable, otherwise it considers it to be a view.
		Object object = part.getObject();
		Class<?> clazz;
		if (object != null) {
			clazz = object.getClass();
		} else {
			// The part has not been rendered yet. Load class manually.
			String bundleName = part.getContributorURI().replace("platform:/plugin/", "");
			Bundle bundle = Activator.getDefault().getBundleForName(bundleName);
			int lastSlashIndex = part.getContributionURI().lastIndexOf("/");
			String clazzName = part.getContributionURI().substring(++lastSlashIndex);
			try {
				clazz = bundle.loadClass(clazzName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Class extracted from contributor URI not found.", e);
			}
		}
		for (Field field : clazz.getDeclaredFields()) {
			if (MDirtyable.class.equals(field.getType()))
				return new GenericElementKind(ElementKind.Editor);
		}
		return new GenericElementKind(ElementKind.View);
	}
	
	public static boolean isEditor(MPart part) {
		return ElementKind.Editor.equals(getPartKind(part).kind);
	}

	public static Display getDisplay() {
		return INSTANCE.display;
	}
	
	public static IWorkbenchWindow[] getWorkbenchWindows() {
		List<IWorkbenchWindow> windows = new ArrayList<IWorkbenchWindow>();
		List<MWindow> children = INSTANCE.application.getChildren();
		for (MWindow window : children) {
			IEclipseContext context = window.getContext();
			if (context != null) {
				IWorkbenchWindow wwindow = context.get(IWorkbenchWindow.class);
				if (wwindow != null) {
					windows.add(wwindow);
				}
			}
		}
		return windows.toArray(new IWorkbenchWindow[windows.size()]);
	}

	public static MApplication getApplication() {
		return INSTANCE.application;
	}
	
	public static EModelService getModelService() {
		return INSTANCE.modelService;
	}
	
	public static ESelectionService getSelectionService() {
		return INSTANCE.application.getContext().get(ESelectionService.class);
	}
	
	public static EPartService getPartService() {
		return INSTANCE.partService;
	}

	public static MPart getActivePart() {
		return INSTANCE.application.getContext().getActiveLeaf().get(MPart.class);
	}

	public static Shell getActiveShell() {
		return INSTANCE.application.getContext().getActiveLeaf().get(Shell.class);
	}

}
