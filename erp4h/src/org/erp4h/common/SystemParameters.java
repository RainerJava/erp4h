package org.erp4h.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.erp4h.system.dto.UserDTO;

public class SystemParameters {
	public static String APPLICATION_NAME="erp4h";
	public static String PHAN_HE;
	public static UserDTO CURRENT_USER;
	public static ArrayList<Integer> CURRENT_GROUP_RIGHT;
	public static ArrayList<Integer> CURRENT_USER_RIGHT;

	public static Timestamp CURRENT_SERVER_TIME;
	public static Date CURRENT_PC_TIME = new Date();
	
	public static Date CURENT_WORKING_DATE;

	public static Locale VN_LOCALE = new Locale("vi", "VN");

	public static void main(String[] args) {
		System.out.println(CURRENT_PC_TIME);
	}
}
