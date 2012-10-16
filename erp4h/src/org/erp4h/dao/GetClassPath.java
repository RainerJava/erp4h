package org.erp4h.dao;

import java.util.Properties;

public class GetClassPath {
	static Properties prop = System.getProperties();

	public static void main(String[] args) {
		prop.setProperty("java.class.path", getClassPath());
		System.out.println("java.class.path now = " + getClassPath());
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(System.getProperty("user.dir"));

	}

	static String getClassPath() {
		return prop.getProperty("java.class.path", null);
	}
}