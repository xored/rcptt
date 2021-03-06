/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.canvas;

import org.eclipse.rcptt.tesla.core.context.ContextManagement.Context;
import org.eclipse.rcptt.tesla.core.info.AdvancedInformation;
import org.eclipse.rcptt.tesla.core.info.Q7WaitInfoRoot;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.IElementProcessorMapper;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.SelectCommand;
import org.eclipse.rcptt.tesla.core.protocol.SelectResponse;
import org.eclipse.rcptt.tesla.core.protocol.diagram.MouseCommand;
import org.eclipse.rcptt.tesla.core.protocol.raw.Command;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.Response;
import org.eclipse.rcptt.tesla.internal.core.AbstractTeslaClient;
import org.eclipse.rcptt.tesla.internal.core.processing.ElementGenerator;
import org.eclipse.rcptt.tesla.internal.core.processing.ITeslaCommandProcessor;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerWrapUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTEvents;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.internal.ui.processors.SWTUIProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;

public class CanvasProcessor implements ITeslaCommandProcessor {

	private AbstractTeslaClient client;

	public CanvasProcessor() {
	}

	@Override
	public int getPriority() {
		return 275;
	}

	@Override
	public String getFeatureID() {
		return "canvas";
	}

	@Override
	public boolean isSelectorSupported(String kind) {
		return false;
	}

	@Override
	public SelectResponse select(SelectCommand cmd, ElementGenerator generator,
			IElementProcessorMapper mapper) {
		return null;
	}

	@Override
	public boolean isCommandSupported(Command cmd) {
		if (cmd instanceof MouseCommand) {
			return true;
		}
		return false;
	}

	@Override
	public Response executeCommand(Command command,
			IElementProcessorMapper mapper) {
		if (command instanceof MouseCommand) {
			return handleMouseCommand((MouseCommand) command);
		}
		return null;
	}

	private Response handleMouseCommand(final MouseCommand command) {
		final SWTUIElement element = getSWTProcessor().getMapper().get(
				command.getElement());
		final Canvas canvas = (Canvas) PlayerWrapUtils.unwrapWidget(element);
		final org.eclipse.swt.graphics.Rectangle oldBounds = canvas.getBounds();

		getPlayer().exec("Set bounds and location", new Runnable() {
			@Override
			public void run() {
				if (command.getBoundsHeight() != 0
						&& command.getBoundsWidth() != 0) {
					canvas.setBounds(0, 0, command.getBoundsWidth(),
							command.getBoundsHeight());
				}
			}
		});
		getPlayer().exec("Canvas executable", new Runnable() {
			@Override
			public void run() {
				SWTEvents events = getPlayer().getEvents();
				final Event e = events.createEvent(element);
				e.x = command.getX();
				e.y = command.getY();
				getPlayer().addMouseWidgetInfo(canvas, e.x, e.y);
				e.button = command.getButton();
				e.stateMask = command.getStateMask();
				switch (command.getKind()) {
				case DOWN:
					e.type = SWT.MouseDown;
					events.sendEvent(element, e);
					break;
				case ENTER:
					e.type = SWT.MouseEnter;
					events.sendEvent(element, e);
					break;
				case EXIT:
					e.type = SWT.MouseExit;
					events.sendEvent(element, e);
					break;
				case HOVER:
					e.type = SWT.MouseHover;
					events.sendEvent(element, e);
					break;
				case UP:
					e.type = SWT.MouseUp;
					events.sendEvent(element, e);
					break;
				case MOVE:
					e.type = SWT.MouseMove;
					events.sendEvent(element, e);
					break;
				case DOUBLE_CLICK:
					e.type = SWT.MouseDoubleClick;
					e.count = 2;
					events.sendEvent(element, e);
					break;
				}
				if (!canvas.isDisposed()) {
					canvas.setBounds(oldBounds);
				}
			}
		});
		return ProtocolFactory.eINSTANCE.createBooleanResponse();
	}

	private SWTUIPlayer getPlayer() {
		return getSWTProcessor().getPlayer();
	}

	private SWTUIProcessor getSWTProcessor() {
		return client.getProcessor(SWTUIProcessor.class);
	}

	@Override
	public PreExecuteStatus preExecute(Command command,
			PreExecuteStatus previousStatus, Q7WaitInfoRoot info) {
		return null;
	}

	@Override
	public void initialize(AbstractTeslaClient client, String id) {
		this.client = client;
	}

	@Override
	public void postSelect(Element element, IElementProcessorMapper mapper) {
		// Map text elements to support this processor operations
		if (element.getKind().equals(ElementKind.Canvas.name())
				// For compatibility alow also windows
				|| element.getKind().equals(ElementKind.Window.name())
				|| element.getKind().equals(ElementKind.Link.name())
				|| element.getKind().equals(ElementKind.Text.name())
				|| element.getKind().equals(ElementKind.Expandable.name())) {
			mapper.map(element, this);
		}
	}

	@Override
	public boolean isInactivityRequired() {
		return false;
	}

	@Override
	public boolean canProceed(Context context, Q7WaitInfoRoot info) {
		return true;
	}

	@Override
	public void clean() {
	}

	@Override
	public void terminate() {
		client = null;
	}

	@Override
	public void checkHang() {
	}

	@Override
	public void collectInformation(AdvancedInformation information,
			Command lastCommand) {
	}

	@Override
	public void notifyUI() {
		// TODO Auto-generated method stub

	}
}
