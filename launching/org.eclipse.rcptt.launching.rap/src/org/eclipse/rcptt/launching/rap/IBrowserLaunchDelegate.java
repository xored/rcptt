package org.eclipse.rcptt.launching.rap;

import java.net.URL;

import org.eclipse.core.runtime.CoreException;

public interface IBrowserLaunchDelegate {
	void launch(URL uri, RAPLaunchConfig config) throws CoreException;
}
