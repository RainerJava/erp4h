package org.erp4h.com.constants;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationConstants {
	public static final int WIDTH=400;
	public static final int HEIGHT=300;
	
	public static String DRIVER_MYSQL=ApplicationConstants.getString("DRIVER_MYSQL");;
	
	public static String getString(String key) {
		try {
			String bundleName = "org.erp4h.resources.application";
			ResourceBundle rb = ResourceBundle.getBundle(bundleName);

			return rb.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	public static void main(String[] args){
		System.out.println(ApplicationConstants.DRIVER_MYSQL);
	}
}
