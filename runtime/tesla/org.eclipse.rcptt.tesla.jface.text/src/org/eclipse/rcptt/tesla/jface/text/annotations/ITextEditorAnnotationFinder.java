package org.eclipse.rcptt.tesla.jface.text.annotations;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.rcptt.tesla.core.ui.Marker;

public interface ITextEditorAnnotationFinder {

	public boolean isSupported();

	public Map<String, EList<Marker>> findAnnotations(TextViewer viewer);

}
