package org.eclipse.rcptt.tesla.workbench;

import java.lang.reflect.Field;

import org.eclipse.rcptt.tesla.internal.workbench.TeslaWorkbenchPlugin;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.PageBook;

public class WorkbenchAccess {

	public static Control getBookPage(PageBook book) {
		Class<? extends PageBook> class1 = book.getClass();
		try {
			Field field = class1.getDeclaredField("currentPage");
			field.setAccessible(true);
			return (Control) field.get(book);
		} catch (Throwable e) {
			TeslaWorkbenchPlugin.log(e);
		}
		return null;
	}

}
