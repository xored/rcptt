/*******************************************************************************
 * Copyright (c) 2009, 2015 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.sherlock.core.reporting.internal;


import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.sherlock.core.INodeBuilder;
import org.eclipse.rcptt.sherlock.core.SherlockCore;
import org.eclipse.rcptt.sherlock.core.model.sherlock.EclipseStatus;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Event;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.ReportFactory;
import org.eclipse.rcptt.sherlock.core.reporting.AbstractEventProvider;
import org.eclipse.rcptt.sherlock.core.reporting.IEventProvider;
import org.eclipse.rcptt.sherlock.core.reporting.IReportBuilder;

public class EventLogEventProvider extends AbstractEventProvider implements
		IEventProvider, ILogListener {

	public static IStatus handledStatus;

	public EventLogEventProvider() {
	}

	public void storeSnapshot(INodeBuilder builder, String id) {
	}

	@Override
	protected void doneBuilders() {
		SherlockCore.removeLogListener(this);
	}

	public void logging(IStatus status, String plugin) {
		if (!status.equals(handledStatus)) {
			IReportBuilder[] builders = getListeners();
			for (IReportBuilder builder : builders) {
				Event event = ReportFactory.eINSTANCE.createEvent();
				EclipseStatus data = SherlockCore.convert(status);
				event.setData(data);
				builder.getCurrent().createEvent(event);
			}
		}
	}

	@Override
	protected void initializeBuilder(IReportBuilder builder) {
		SherlockCore.addLogListener(this, null, null);
	}
}
