/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.recording.aspects.swt.rap;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptExecutor;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.ui.RWTUtils;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;

public final class SWTEventManager {
	public static final int EVENT_TAB_RESTORE = 0;
	public static final int EVENT_TAB_MINIMIZE = 1;
	public static final int EVENT_TAB_MAXIMIZE = 2;
	public static final int EVENT_TAB_SHOW_LIST = 3;

	private SWTEventManager() {
	}

	private static Set<IAbstractSWTEventListener> listeners = new HashSet<IAbstractSWTEventListener>();
	private static boolean freezeState;
	private static Shell[] ignoreFreeze;
	private static List<WeakReference<CCombo>> comboList = new ArrayList<WeakReference<CCombo>>();

	private static boolean shouldProceed = false;

	public static void addListener(IAbstractSWTEventListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public static void removeListener(IAbstractSWTEventListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	private static <T> Set<T> getListeners(Class<T> clazz$) {
		Set<T> result = new HashSet<T>();
		synchronized (listeners) {
			for (IAbstractSWTEventListener eventListener : listeners) {
				if (clazz$.isInstance(eventListener)) {
					result.add(clazz$.cast(eventListener));
				}
			}
		}
		return result;
	}

	public static boolean needProceedEvents() {
		Set<IExtendedSWTEventListener> set = getListeners(IExtendedSWTEventListener.class);
		for (IExtendedSWTEventListener l : set) {
			if (l.needProceedEvent()) {
				return true;
			}
		}
		return false;
	}

	public static void setCurrentEvent(Event event) {
		for (IExtendedSWTEventListener listener : getListeners(IExtendedSWTEventListener.class)) {
			listener.setCurrentEvent(event);
		}
	}

	public static boolean skipEvent(Widget widget, int type, Event event, boolean send) {
		for (ISkipAwareEventListener listener : getListeners(ISkipAwareEventListener.class)) {
			if (listener.isSkipEvent(widget, type, event, send)) {
				return true;
			}
		}
		return false;
	}

	public static void recordEvent(Widget widget, int type, Event event) {
		Set<IBasicSWTEventListener> listeners = getListeners(IBasicSWTEventListener.class);

		IBasicSWTEventListener exclusive = null;
		for (IBasicSWTEventListener listener : listeners) {
			if (listener.isExclusiveEventHandle(widget, type, event)) {
				if (exclusive == null)
					exclusive = listener;
				else {
					throw new RuntimeException("Recording processors collision: " //$NON-NLS-1$
							+ listener.getClass().getName() + " and " //$NON-NLS-1$
							+ exclusive.getClass().getName() + " both want to handle event exclusive."); //$NON-NLS-1$
				}
			}
		}

		if (exclusive != null)
			exclusive.recordEvent(widget, type, event);
		else
			for (IBasicSWTEventListener listener : listeners)
				listener.recordEvent(widget, type, event);
	}

	public static void recordDragEvent(Event event) {
		for (IExtendedSWTEventListener listener : getListeners(IExtendedSWTEventListener.class)) {
			listener.recordDragEvent(event);
		}
	}

	public static void recordSWTDialog(Dialog dialog, Object result) {
		for (IExtendedSWTEventListener listener : getListeners(IExtendedSWTEventListener.class)) {
			listener.recordSWTDialog(dialog, result);
		}
	}

	public static void recordRapDownload(String handler, String url, String content) {
		for (IExtendedSWTEventListener listener : getListeners(IExtendedSWTEventListener.class)) {
			listener.recordRapDownloadHandler(handler, url, content);
		}
	}

	public static void setFreeze(final boolean value) {
		freezeState = value;

		RWTUtils.findDisplay().asyncExec(new Runnable() {
			public void run() {
				RWT.getClient().getService(JavaScriptExecutor.class)
						.execute("rwt.client.rcptt.Assertion.getInstance().setActive(" + (value ? "true" : "false") + ");");
			}
		});
	}

	public static boolean isFreeze(Widget widget, int type, Event event) {
		if (freezeState) {
			// if ((widget instanceof Menu || widget instanceof MenuItem)
			// && (/* type == SWT.Show || */type == SWT.Hide
			// || type == SWT.MeasureItem || type == SWT.Arm)) {
			// return false;
			// }
			if (ignoreFreeze != null) {
				if (widget instanceof Control) {
					Shell shell = ((Control) widget).getShell();
					for (int i = 0; i < ignoreFreeze.length; i++) {
						if (ignoreFreeze[i].equals(shell)) {
							return false;
						}
					}
				} else if (widget instanceof ToolItem) {
					Shell shell = ((ToolItem) widget).getParent().getShell();
					for (int i = 0; i < ignoreFreeze.length; i++) {
						if (ignoreFreeze[i].equals(shell)) {
							return false;
						}
					}
				} else if (widget instanceof ScrollBar) {
					Shell shell = ((ScrollBar) widget).getParent().getShell();
					for (int i = 0; i < ignoreFreeze.length; i++) {
						if (ignoreFreeze[i].equals(shell)) {
							return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	public static void setIgnoreFreeze(Shell[] ignored) {
		ignoreFreeze = ignored;
	}

	public static boolean handleEventInFreeze(Widget widget, int type, Event event) {
		boolean finalResult = false;
		for (IAssertSWTEventListener listener : getListeners(IAssertSWTEventListener.class)) {
			finalResult = finalResult || listener.handleEventInFreeze(widget, type, event);
		}
		return finalResult;
	}

	public static boolean highlightWidget(Widget widget) {
		boolean finalResult = false;
		for (IAssertSWTEventListener listener : getListeners(IAssertSWTEventListener.class)) {
			finalResult = finalResult || listener.highlightWidget(widget);
		}
		return finalResult;
	}

	public static boolean updateAssertionPanelWindow(Widget widget) {
		boolean finalResult = false;
		for (IAssertSWTEventListener listener : getListeners(IAssertSWTEventListener.class)) {
			finalResult = finalResult || listener.updateAssertionPanelWindow(widget);
		}
		return finalResult;
	}

	public static SWTUIElement getSWTUIElement(Element element) {
		for (IAssertSWTEventListener listener : getListeners(IAssertSWTEventListener.class)) {
			SWTUIElement result = listener.getSWTUIElement(element);
			if (null != result) {
				return result;
			}
		}
		return null;
	}

	public static Element getElement(SWTUIElement swtUIElement) {
		for (IAssertSWTEventListener listener : getListeners(IAssertSWTEventListener.class)) {
			Element result = listener.getElement(swtUIElement);
			if (null != result) {
				return result;
			}
		}
		return null;
	}

	public static void handleUpdateHover(Control widget) {
		for (IAssertSWTEventListener listener : getListeners(IAssertSWTEventListener.class)) {
			 listener.handleUpdateHover(widget);
		}
	}

	public static void recordCombo(CCombo combo) {
		comboList.add(new WeakReference<CCombo>(combo));
		// Remove disposed ones
		if (comboList.size() > 1) {
			cleanCombos();
		}
	}

	public static CCombo[] getCombo() {
		cleanCombos();
		List<CCombo> result = new ArrayList<CCombo>();
		for (WeakReference<CCombo> cc : comboList) {
			if (cc != null) {
				CCombo ccg = cc.get();
				if (ccg != null && !ccg.isDisposed()) {
					result.add(ccg);
				}
			}
		}
		return result.toArray(new CCombo[result.size()]);
	}

	private static void cleanCombos() {
		List<WeakReference<CCombo>> toRemove = new ArrayList<WeakReference<CCombo>>();
		for (WeakReference<CCombo> cc : comboList) {
			CCombo ccg = cc.get();
			if (ccg == null || ccg.isDisposed()) {
				toRemove.add(cc);
			}
		}
		comboList.removeAll(toRemove);
	}

	// public static void recordStyledTextActionBefore(StyledText text, int
	// action) {
	// for (IExtendedSWTEventListener listener :
	// getListeners(IExtendedSWTEventListener.class)) {
	// listener.recordStyledTextActionBefore(text, action);
	// }
	// }
	//
	// public static void recordStyledTextActionAfter(StyledText text, int
	// after) {
	// for (IExtendedSWTEventListener listener :
	// getListeners(IExtendedSWTEventListener.class)) {
	// listener.recordStyledTextActionAfter(text, after);
	// }
	// }
	//
	// public static void recordStyledTextOffset(StyledText text) {
	// for (IExtendedSWTEventListener listener :
	// getListeners(IExtendedSWTEventListener.class)) {
	// listener.recordStyledTextOffset(text);
	// }
	// }

	public static void recordTabFolderEvent(Control tabControl, int eventId) {
		for (IExtendedSWTEventListener listener : getListeners(IExtendedSWTEventListener.class)) {
			listener.recordTabFolderEvent(tabControl, eventId);
		}
	}

	public static boolean getFreeze() {
		return freezeState;
	}

	public static boolean isShouldProceed(Widget widget, int type) {
		return shouldProceed;
	}

	public static void setShouldProceed(boolean shouldProceess) {
		SWTEventManager.shouldProceed = shouldProceess;
	}

	// menuSources stuff

	private static Map<Widget, Object> menuSources = Collections.synchronizedMap(new WeakHashMap<Widget, Object>());

	public static void setMenuSource(Menu menu, Object source) {
		if (menu != null && source != null) {
			menuSources.put(menu, source);
		}
	}

	public static Object getMenuSource(Widget menu) {
		return menuSources.get(menu);
	}

	public static void cleanMenuSources() {
		Set<Widget> keysToRemove = new HashSet<Widget>();
		// Clear out menu sourced with disposed parts
		for (Map.Entry<Widget, Object> entry : menuSources.entrySet()) {
			if (entry.getValue() instanceof Widget) {
				if (entry.getKey().isDisposed() || (((Widget) entry.getValue()).isDisposed())) {
					keysToRemove.add(entry.getKey());
				}
			}
		}
		for (Widget w : keysToRemove) {
			menuSources.remove(w);
		}
	}

	//
}
