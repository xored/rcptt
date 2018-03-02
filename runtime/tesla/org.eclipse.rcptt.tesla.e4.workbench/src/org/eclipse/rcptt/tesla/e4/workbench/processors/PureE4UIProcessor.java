/*******************************************************************************
 * Copyright (c) 2009, 2018 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.e4.workbench.processors;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rcptt.runtime.e4.ui.PureE4ModelProcessor;
import org.eclipse.rcptt.tesla.core.context.ContextManagement.Context;
import org.eclipse.rcptt.tesla.core.info.AdvancedInformation;
import org.eclipse.rcptt.tesla.core.info.Q7WaitInfoRoot;
import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.core.protocol.IElementProcessorMapper;
import org.eclipse.rcptt.tesla.core.protocol.ProtocolFactory;
import org.eclipse.rcptt.tesla.core.protocol.SelectCommand;
import org.eclipse.rcptt.tesla.core.protocol.SelectData;
import org.eclipse.rcptt.tesla.core.protocol.SelectResponse;
import org.eclipse.rcptt.tesla.core.protocol.raw.Command;
import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.Response;
import org.eclipse.rcptt.tesla.core.protocol.raw.ResponseStatus;
import org.eclipse.rcptt.tesla.core.ui.Part;
import org.eclipse.rcptt.tesla.core.ui.UiFactory;
import org.eclipse.rcptt.tesla.core.ui.Widget;
import org.eclipse.rcptt.tesla.e4.workbench.player.PureE4UIElement;
import org.eclipse.rcptt.tesla.e4.workbench.player.PureE4UIPlayer;
import org.eclipse.rcptt.tesla.e4.workbench.player.PureE4UIPlayerExtension;
import org.eclipse.rcptt.tesla.internal.core.AbstractTeslaClient;
import org.eclipse.rcptt.tesla.internal.core.processing.ElementGenerator;
import org.eclipse.rcptt.tesla.internal.core.processing.ITeslaCommandProcessor;
import org.eclipse.rcptt.tesla.internal.ui.SWTElementMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTModelMapperExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.ISWTUIPlayerExtension;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTModelMapper;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.rcptt.tesla.internal.ui.processors.SWTUIProcessor;
import org.eclipse.rcptt.tesla.swt.TeslaSWTMessages;
import org.eclipse.swt.widgets.Display;

public class PureE4UIProcessor implements ITeslaCommandProcessor, ISWTModelMapperExtension {

	private static final ElementKind[] allSelectors = {
			ElementKind.EclipseWindow,
			ElementKind.Part
	};

	private static final EClass[] supportedCommands = {
	};

	private AbstractTeslaClient client;
	private String id;
	private Display display;

	private ISWTUIPlayerExtension extension;
	private SWTUIProcessor swtProcessor;
	private SWTUIPlayer swtPlayer;
	private PureE4UIPlayer workbenchPlayer;

	@Override
	public int getPriority() {
		return 55;
	}

	@Override
	public String getFeatureID() {
		return "org.eclipse.rcptt.tesla.e4.workbench";
	}

	@Override
	public void initialize(AbstractTeslaClient client) {
		this.client = client;
		this.id = client.getID();

		this.display = PureE4ModelProcessor.getDisplay();

		this.extension = new PureE4UIPlayerExtension();
		SWTUIPlayer.addExtension(extension);
	}

	@Override
	public void terminate() {
		this.client = null;
		this.id = null;

		this.display = null;

		SWTUIPlayer.removeExtension(extension);
		this.extension = null;

		this.swtProcessor = null;
		this.swtPlayer = null;
		this.workbenchPlayer = null;
	}

	@Override
	public boolean canProceed(Context context, Q7WaitInfoRoot info) {
		return true;
	}

	@Override
	public PreExecuteStatus preExecute(Command command, PreExecuteStatus previousStatus, Q7WaitInfoRoot info) {
		// TODO (e4 support): implement
		return null;
	}

	@Override
	public boolean isSelectorSupported(final String kind) {
		for (final ElementKind e : allSelectors) {
			if (e.name().equals(kind)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isCommandSupported(Command cmd) {
		final EClass cl0 = cmd.eClass();
		for (final EClass cl : supportedCommands) {
			if (cl0.equals(cl)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Response executeCommand(Command command, IElementProcessorMapper mapper) {
		return null;
	}

	@Override
	public SelectResponse select(SelectCommand command, ElementGenerator generator, IElementProcessorMapper mapper) {
		// TODO (e4 support): should we create a new collector instance or use an existing one?
		getSWTPlayer().getCollector().enable();

		final SelectData data = command.getData();
		final SWTUIElement result = getWorkbenchPlayer().select(
				new PlayerSelectionFilter(null, GenericElementKind
						.fromString(data.getKind()), data.getPattern(),
						null, data.getIndex(), null, null,
						data.getClassPattern())); // fill non-used params as null
		if (result != null) {
			final SelectResponse response = ProtocolFactory.eINSTANCE.createSelectResponse();
			response.getElements().add(getMapper().get(result));
			return response;
		}

		// return fail status only if it does not overwrite swt processor select result
		if (result == null && !getSWTProcessor().isSelectorSupported(data.getKind())) {
			final SelectResponse response = ProtocolFactory.eINSTANCE.createSelectResponse();
			response.setMessage(NLS.bind(
					TeslaSWTMessages.SWTUIProcessor_CannotFindControl,
					data.getKind(),
					(data.getPattern() != null ? data.getPattern() : data.getPath())));
			response.setStatus(ResponseStatus.FAILED);
			return response;
		}
		return null;
	}

	@Override
	public void collectInformation(AdvancedInformation information, Command lastCommand) {
		// TODO (e4 support): implement
	}

	private SWTUIProcessor getSWTProcessor() {
		if (swtProcessor == null) {
			swtProcessor = client.getProcessor(SWTUIProcessor.class);
		}
		return swtProcessor;
	}

	private SWTUIPlayer getSWTPlayer() {
		if (swtPlayer == null) {
			swtPlayer = getSWTProcessor().getPlayer();
		}
		return swtPlayer;
	}

	private PureE4UIPlayer getWorkbenchPlayer() {
		if (workbenchPlayer == null) {
			workbenchPlayer = new PureE4UIPlayer(getSWTPlayer());
		}
		return workbenchPlayer;
	}

	@Override
	public Widget mapExtraValues(SWTUIElement element, Widget result) {
		switch (element.getKind().kind) {
		case Part:
			return fillPart(element);
		}
		return result;
	}

	private static Widget fillPart(SWTUIElement element) {
		Part part = UiFactory.eINSTANCE.createPart();
		if (element instanceof PureE4UIElement) {
			MPart mPart = ((PureE4UIElement) element).getModelElement();
			part.setTitle(PlayerTextUtils.unifyMultilines(mPart.getLabel()));
			SWTModelMapper.fillControl(part, ((PureE4UIElement) element).unwrapWidget());
		}
		return part;
	}

	public SWTElementMapper getMapper() {
		return SWTElementMapper.getMapper(id);
	}

	@Override
	public void postSelect(Element element, IElementProcessorMapper mapper) {
	}

	@Override
	public boolean isInactivityRequired() {
		return false;
	}

	@Override
	public void clean() {
	}

	@Override
	public void checkHang() {
	}

	@Override
	public void notifyUI() {
	}

}
