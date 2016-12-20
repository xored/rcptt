/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2009, 2016 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.testrail.ui.preferences;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.rcptt.internal.testrail.TestRailPlugin;
import org.eclipse.rcptt.testrail.ui.internal.preferences.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class TestRailPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private Button testRailCheckBox;
	private Text testRailAddress;
	private Text testRailUsername;
	private Text testRailPassword;

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	public boolean performOk() {
		if (testRailCheckBox.getSelection())
			TestRailPlugin.setTestRailState(1);
		else
			TestRailPlugin.setTestRailState(0);

		TestRailPlugin.setTestRailAddress(testRailAddress.getText());
		TestRailPlugin.setTestRailUsername(testRailUsername.getText());
		TestRailPlugin.setTestRailPassword(testRailPassword.getText());
		return super.performOk();
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		testRailCheckBox = createCheckBoxButton(composite, Messages.TestRailPreferencePage_EnableIntegration,
				TestRailPlugin.getTestRailState());
		testRailAddress = createText(composite, Messages.TestRailPreferencePage_Address,
				TestRailPlugin.getTestRailAddress());
		testRailUsername = createText(composite, Messages.TestRailPreferencePage_Username,
				TestRailPlugin.getTestRailUsername());
		testRailPassword = createText(composite, Messages.TestRailPreferencePage_Password,
				TestRailPlugin.getTestRailPassword());

		testRailAddress.setEnabled(TestRailPlugin.getTestRailState());
		testRailUsername.setEnabled(TestRailPlugin.getTestRailState());
		testRailPassword.setEnabled(TestRailPlugin.getTestRailState());

		return null;
	}

	private Text createText(Composite parent, String labelText, String initialValue) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(labelText);
		Text text = new Text(parent, SWT.BORDER);
		text.setText(initialValue);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});

		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return text;
	}

	private Button createCheckBoxButton(Composite parent, String labelText, boolean state) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(labelText);
		Button button = new Button(parent, SWT.CHECK);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean state = testRailCheckBox.getSelection();
				testRailAddress.setEnabled(state);
				testRailUsername.setEnabled(state);
				testRailPassword.setEnabled(state);
			}
		});

		button.setSelection(state);
		return button;
	}

	private void validate() {
		String message = doValidate();
		setErrorMessage(message);
		setValid(message == null);
	}

	private String doValidate() {
		if (!isValidURL(testRailAddress.getText())) {
			return Messages.TestRailPreferencePage_IncorrectAddressMsg;
		}
		if (!testRailAddress.getText().endsWith("/")) {
			return Messages.TestRailPreferencePage_AddressEndsWithSlash;
		}
		return null;
	}

	private boolean isValidURL(String urlString) {
		try {
			URL url = new URL(urlString);
			return !url.getHost().equals("");
		} catch (MalformedURLException e) {
			// ignore
		}
		return false;
	}

}
