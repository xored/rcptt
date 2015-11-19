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
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchWindow;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public class E4ModelProcessor {
 
	private static E4ModelProcessor INSTANCE = null;

	@Inject
	MApplication application;

	Display display;

	@Execute
	public void execute(Display display) {
		this.display = display;
		INSTANCE = this;
	}

	public static GenericElementKind getPartKind(Object w) {
		if (w instanceof Widget) {
			Object part_ = ((Widget) w).getData("modelElement");
			if (part_ instanceof MPart) {
				// TODO: Find a better way to recognize editors.
				MPart part = (MPart) part_;
				
				Field[] fields = null;
				boolean isPartRendered = part.getObject() != null;
				if (!isPartRendered) {
					String bundleName = part.getContributorURI().replace("platform:/plugin/", "");
					Bundle bundle = Activator.getDefault().getBundleForName(bundleName);

					int lastSlashIndex = part.getContributionURI().lastIndexOf("/");
					String clazzName = part.getContributionURI().substring(++lastSlashIndex);
					try {
						Class<?> clazz = bundle.loadClass(clazzName);
						fields = clazz.getDeclaredFields();
					} catch (ClassNotFoundException e) {
						// ignore, its aren't going to happen
						// because part is created from targetClass (origin class of the 
						//part)
					}
				} else {
					fields = part.getObject().getClass().getDeclaredFields();
				}
				for (Field field : fields) {
					boolean isEditor = MDirtyable.class.equals(field.getType());
					if (isEditor)
						return new GenericElementKind(ElementKind.Editor);
				}
			
				return new GenericElementKind(ElementKind.View);
				
				
			}
			
		}
		return GenericElementKind.Unknown;
		
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
		return INSTANCE.application.getContext().get(EModelService.class);
	}
	
	public static ESelectionService getSelectionService() {
		return INSTANCE.application.getContext().get(ESelectionService.class);
	}
	
	public static EPartService getPartService() {
		return INSTANCE.application.getContext().get(EPartService.class);
	}

}
