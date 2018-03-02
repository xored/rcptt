package org.eclipse.rcptt.runtime;

import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.core.Q7Features;
import org.eclipse.rcptt.tesla.core.TeslaFeatures;
import org.eclipse.rcptt.tesla.core.TeslaVariables;
import org.eclipse.rcptt.tesla.swt.events.TeslaEventManager;

public class RcpttFeatureUtils {

	public static void setFeatures(EList<String> features) {
		checkStatusDialogFeature(features);

		Q7Features.getInstance().setValues(features);
		TeslaFeatures.getInstance().setValues(features);

		TeslaVariables.updateFromFeatureList(features);
	}

	private static void checkStatusDialogFeature(EList<String> features) {
		for (String feature : features) {
			int pos = feature.indexOf('=');
			String key = feature.substring(0, pos);
			String value = feature.substring(pos + 1);
			if (key.equals(TeslaFeatures.STATUS_DIALOG_ALLOWED)) {
				TeslaEventManager.getManager().setStatusDialogModeAllowed(
						Boolean.valueOf(value));
			}
		}
	}

}
