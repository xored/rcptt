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

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;
import org.eclipse.rcptt.tesla.internal.ui.player.PlayerTextUtils;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIElement;
import org.eclipse.rcptt.tesla.internal.ui.player.SWTUIPlayer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

public class PureE4UIElement extends SWTUIElement {
	public MPart modelElement;

	public PureE4UIElement(MPart modelElement, SWTUIPlayer p) {
		super(null, p);
		this.modelElement = modelElement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((modelElement == null) ? 0 : modelElement.hashCode());
		return result;
	}

	@Override
	public GenericElementKind getKind() {
		return SWTUIPlayer.getKind(modelElement);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PureE4UIElement other = (PureE4UIElement) obj;
		if (modelElement == null) {
			if (other.modelElement != null)
				return false;
		} else if (!modelElement.equals(other.modelElement))
			return false;
		return true;
	}

	public boolean match(Object c) {
		return modelElement != null && modelElement.equals(c);
	}

	public String toString() {
		return modelElement == null ? "Empty"
				: modelElement.getClass()
				.getSimpleName() + ":" + PlayerTextUtils.getText(this);
	}

	public String getClassName() {
		return modelElement == null ? "null"
				: modelElement.getClass()
				.getSimpleName();
	}

	public MPart getModelElement() {
		return modelElement;
	}

	@Override
	public Object unwrap() {
		return modelElement;
	}

	@Override
	public Control unwrapWidget() {
		if (modelElement == null) {
			return null;
		}
		// TODO (e4 support): verify
		assert modelElement.getWidget() instanceof Control;
		return (Control) modelElement.getWidget();
	}

	@Override
	public Rectangle getBounds() {
		if (modelElement != null) {
			// TODO (e4 support): verify
			Control control = unwrapWidget();
			if (control != null && !control.isDisposed()) {
				Rectangle bounds = control.getBounds();
				if (control.getParent() != null) {
					Point point = control.toDisplay(bounds.x, bounds.y);
					bounds.x = point.x;
					bounds.y = point.y;
				}

				return bounds;
			}
		}
		return super.getBounds();
	}

}
