package org.waehner.oc.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;

public class ResourceReader {

	/**
	 * Return resource for the current locale
	 * @param key Key to get the resource for
	 * @param parameters Optional: Parameters to add to resource
	 * @return the resolved string
	 */
	public static String getString(String key, Object... parameters) {
		ResourceBundle bundle = ResourceBundle.getBundle("org.waehner.oc.i18n.NextcloudDavBackup", Locale.getDefault());
		
		if(ArrayUtils.isNotEmpty(parameters)) {
			return MessageFormat.format(bundle.getString(key), parameters);
		} else {
			return bundle.getString(key);
		}
	}
}
