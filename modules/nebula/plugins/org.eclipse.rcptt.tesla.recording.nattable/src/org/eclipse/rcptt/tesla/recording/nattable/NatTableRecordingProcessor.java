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
package org.eclipse.rcptt.tesla.recording.nattable;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.rcptt.tesla.core.protocol.ViewerUIElement;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.SetMode;
import org.eclipse.rcptt.tesla.internal.ui.player.FindResult;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTModelMapperExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTModelMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.nattable.NatTableHelper;
import org.eclipse.rcptt.tesla.nattable.model.NatTableCellPosition;
import org.eclipse.rcptt.tesla.nattable.processors.NatTableProcessor;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableCellMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.recording.aspects.IBasicSWTEventListener;
import org.eclipse.rcptt.tesla.recording.aspects.SWTEventManager;
import org.eclipse.rcptt.tesla.recording.core.IRecordingHelper;
import org.eclipse.rcptt.tesla.recording.core.IRecordingProcessor;
import org.eclipse.rcptt.tesla.recording.core.TeslaRecorder;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTRecordingHelper;
import org.eclipse.rcptt.tesla.recording.core.swt.SWTWidgetLocator;
import org.eclipse.rcptt.util.swt.Events;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public class NatTableRecordingProcessor implements IRecordingProcessor, IBasicSWTEventListener,
		ISWTModelMapperExtension {

	private static Set<Integer> INTERESTING_EVENTS = ImmutableSet.of(SWT.MouseDoubleClick, SWT.MouseDown, SWT.MouseUp);

	private Map<Integer, Event> mouseDownEvents = Maps.newHashMap();
	private SWTWidgetLocator locator;

	private SWTWidgetLocator getLocator() {
		if (locator == null) {
			locator = SWTRecordingHelper.getHelper().getLocator();
		}
		return locator;
	}

	public NatTableRecordingProcessor() {
		super();
		SWTEventManager.addListener(this);
	}

	@Override
	public void recordEvent(Widget widget, int type, Event event) {
		if (!(widget instanceof NatTable) || !INTERESTING_EVENTS.contains(type)) {
			// ignoring not interesting events,
			// it is essential to filter some event types like focusing
			// to correctly determine menu source
			return;
		}
		FindResult result = getLocator().findElement(widget, true, false, false);
		NatTable natTable = (NatTable) widget;
		switch (type) {
		case SWT.MouseDown:
			if (NatTableHelper.isNatTableCell(natTable, event.x, event.y)) {
				// store where mouse click was started
				mouseDownEvents.put(event.button, event);
			}

			break;

		case SWT.MouseUp:
			// handle events only if event started on NatTable widget
			if (mouseDownEvents.containsKey(event.button)) {
				Event mouseDownEvent = mouseDownEvents.get(event.button);
				NatTableCellPosition clickEndPosition = NatTableHelper.getCellPosition(natTable, event.x, event.y);
				if (clickEndPosition != null) {
					NatTableCellPosition clickStartPosition = NatTableHelper.getCellPosition(natTable,
							mouseDownEvent.x, mouseDownEvent.y);
					if (clickStartPosition.equals(clickEndPosition)) {
						if (event.button == Events.DEFAULT_BUTTON
								&& NatTableHelper.isEditable(natTable, clickEndPosition)) {
							recordActivateCellEditor(natTable, result, clickEndPosition);
						} else {
							recordMouseEvent(natTable, result, clickEndPosition, NatTableMouseEventKind.CLICK,
									event.button, event.stateMask);
						}
					} else {
						recordMouseEvent(natTable, result, clickStartPosition, NatTableMouseEventKind.DOWN,
								event.button, event.stateMask);
						recordMouseEvent(natTable, result, clickEndPosition, NatTableMouseEventKind.UP, event.button,
								event.stateMask);
					}

				}
				mouseDownEvents.remove(event.button);
			}
			break;
		// TODO: Handle double click?
		}
	}

	@Override
	public boolean isExclusiveEventHandle(Widget widget, int type, Event event) {
		return (widget instanceof NatTable) && INTERESTING_EVENTS.contains(type);
	}

	private void recordMouseEvent(NatTable natTable, FindResult result, NatTableCellPosition position,
			NatTableMouseEventKind kind, int button, int stateMask) {
		NatTableCellMouseEvent command = NattableFactory.eINSTANCE.createNatTableCellMouseEvent();
		command.setKind(kind);
		command.setButton(button);
		command.setStateMask(stateMask);
		command.setRow(position.getRow());
		command.setColumn(position.getCol());
		ILayer layer = natTable.getLayer().getUnderlyingLayerByPosition(position.getCol(), position.getRow());
		command.setColumnHeader(layer instanceof ColumnHeaderLayer);
		command.setRowHeader(layer instanceof RowHeaderLayer);
		command.setElement((result.element != null) ? (Element) EcoreUtil.copy(result.element) : null);
		locator.getRecorder().safeExecuteCommand(command);
	}

	private void recordActivateCellEditor(NatTable natTable, FindResult result, NatTableCellPosition position) {
		ViewerUIElement element = new ViewerUIElement(result.element, locator.getRecorder());
		String path = position.getPath();
		element.setSelection(path);
		element.activateCellEditor(path);
	}


	// IRecordingProcessor implementation

	@Override
	public void initialize(TeslaRecorder teslaRecorder) {
	}

	@Override
	public void clear() {
		mouseDownEvents.clear();
		NatTableRecordingHelper.getHelper().clear();
		SWTModelMapper.initializeExtensions(getLocator().getRecorder().getProcessors(ISWTModelMapperExtension.class));
		getLocator().cleanMenuSources();
		locator = null;
	}

	@Override
	public void setFreeze(boolean value, SetMode command) {
	}

	@Override
	public void resetAssertSelection() {
	}

	@Override
	public IRecordingHelper<?> getHelper() {
		return NatTableRecordingHelper.getHelper();
	}

	@Override
	public int getInitLevel() {
		return 20;
	}

	@Override
	public org.eclipse.rcptt.tesla.core.ui.Widget mapExtraValues(SWTUIElement element,
			org.eclipse.rcptt.tesla.core.ui.Widget result) {
		return NatTableProcessor.mapWidget(element, result);
	}

}
