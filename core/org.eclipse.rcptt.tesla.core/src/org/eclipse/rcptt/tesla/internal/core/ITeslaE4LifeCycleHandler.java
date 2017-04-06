/*******************************************************************************
 * Copyright (c) 2017 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.tesla.internal.core;

/**
 * Interface to handle Tesla startup and shutdown in E4 applications
 */
public interface ITeslaE4LifeCycleHandler {
	/**
	 * Entry point for Tesla startup code
	 */
	public void startup();

	/**
	 * Entry point for Tesla shutdown code
	 */
	public void shutdown();
}
