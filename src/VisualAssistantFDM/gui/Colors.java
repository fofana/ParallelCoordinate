package VisualAssistantFDM.gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Colors {
	private static final String BUNDLE_NAME = "VisualAssistantFDM.gui.colors";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
	.getBundle(BUNDLE_NAME);

	private Colors() {
	}
	/**
	 *  Fonction permettant d'obtenir la couleur correspondante à une classe
	 * @param key
	 * @return la couleur cocorrespondante 
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key.toUpperCase());
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
		
}
