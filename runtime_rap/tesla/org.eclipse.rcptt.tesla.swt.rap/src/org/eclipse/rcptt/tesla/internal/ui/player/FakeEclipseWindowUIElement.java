package org.eclipse.rcptt.tesla.internal.ui.player;

import org.eclipse.rcptt.tesla.core.protocol.ElementKind;
import org.eclipse.rcptt.tesla.core.protocol.GenericElementKind;

public class FakeEclipseWindowUIElement extends SWTUIElement {
	public FakeEclipseWindowUIElement(SWTUIPlayer p) {
		super(null, p);
	}

	@Override
	public GenericElementKind getKind() {
		return new GenericElementKind(ElementKind.EclipseWindow);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FakeEclipseWindowUIElement other = (FakeEclipseWindowUIElement) obj;
		return true;
	}
}
