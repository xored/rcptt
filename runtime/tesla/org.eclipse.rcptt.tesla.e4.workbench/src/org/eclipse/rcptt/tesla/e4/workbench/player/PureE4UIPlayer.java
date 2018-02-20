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
package org.eclipse.rcptt.tesla.e4.workbench.player;

import static org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils.safeMatches;

import java.util.Collection;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.rcptt.runtime.e4.ui.PureE4ModelProcessor;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerSelectionFilter;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

public class PureE4UIPlayer {

	private SWTUIPlayer swtPlayer;

	public PureE4UIPlayer(SWTUIPlayer swtPlayer) {
		this.swtPlayer = swtPlayer;
	}

	public SWTUIElement select(PlayerSelectionFilter filter) {
		switch (filter.kind.kind) {
		case EclipseWindow:
			return selectEclipseWindow(swtPlayer, filter.index);
		case Part:
			return selectPart(swtPlayer, filter);
		}
		return null;
	}

	public SWTUIElement selectEclipseWindow(SWTUIPlayer player, Integer index) {
		// TODO (e4 support): review, test
		// Display display = E4ModelProcessor.getDisplay();
		// if (display == Display.getCurrent()) {
		// return player.wrap(E4ModelProcessor.getDisplay().getActiveShell());
		// }
		// List<MWindow> windows = E4ModelProcessor.getApplication().getChildren();
		// if (!windows.isEmpty()) {
		// return player.wrap((Shell) windows.get(0).getWidget());
		// }
		final List<MWindow> windows = PureE4ModelProcessor.getApplication().getChildren();
		if (!windows.isEmpty()) {
			if (index == null) {
				return player.wrap((Shell) windows.get(0).getWidget());
			} else if (index.intValue() < windows.size()) {
				return player.wrap((Shell) windows.get(index.intValue()).getWidget());
			}
		}
		return null;
	}
	
	public SWTUIElement selectPart(SWTUIPlayer player, PlayerSelectionFilter f) {
		final Collection<MPart> parts = PureE4ModelProcessor.getPartService().getParts();

		int currIdx = 0;
		for (MPart part : parts) {
			if (matches(part.getLabel(), f.pattern)) {
				if (matches(currIdx++, f.index)) {
					PureE4ModelProcessor.getModelService().bringToTop(part);
					return player.wrap((Widget) part.getWidget());
				}
			}
		}
		return null;
	}

	private static boolean matches(String value, String pattern) {
		return pattern == null || (value != null && (value.equals(pattern) || safeMatches(value, pattern)));
	}

	private static boolean matches(Integer value, Integer pattern) {
		return pattern == null || (value != null && value.equals(pattern));
	}

	public SWTUIPlayer getSWTPlayer() {
		return swtPlayer;
	}

}
