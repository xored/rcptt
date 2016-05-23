package org.eclipse.rcptt.runtime.ui;

import org.eclipse.rap.rwt.internal.lifecycle.PhaseEvent;
import org.eclipse.rap.rwt.internal.lifecycle.PhaseId;
import org.eclipse.rap.rwt.internal.lifecycle.PhaseListener;

public class RAPPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -4142549161824231880L;

	public RAPPhaseListener() {
		Q7ServerStarter.INSTANCE.start();
	}

	public void beforePhase(PhaseEvent event) {
	}

	public void afterPhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.PREPARE_UI_ROOT;
	}

}

