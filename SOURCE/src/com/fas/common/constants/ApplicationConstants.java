package com.fas.common.constants;

import java.awt.Insets;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.fas.common.DBConnectionManager;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.sapp.login.LoginFrame;
import com.fas.service.init.InitService;
import com.fas.service.init.InitServiceImpl;
import com.fas.vo.holiday.HolidayVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 * 
 */
public class ApplicationConstants {

	/** */
	static Logger logger = Logger.getLogger(ApplicationConstants.class);

	/** */
	public static final int DB2 = 1;

	/** */
	public static final int MySQL = 2;

	/** */
	public static final int MSSQLSERVER = 3;

	/** */
	public static final int ORACLE = 4;

	/** */
	public static final int POSTGRES = 5;

	/**	 */
	public static String DRIVER = ApplicationConstants.getString("DRIVER");

	/**  */
	public static String DB_URL = "";

	/**  */
	public static String DB_SCHEMA = "";

	/**	 */
	public static String DB_USER = "";

	/**	 */
	public static String DB_PASSWORD = "";

	/**	 */
	public static final int POOLSIZE = ApplicationConstants.getInt("POOLSIZE");

	/**   */
	public static final long TIMEOUT = ApplicationConstants.getInt("TIMEOUT");

	/**   */
	public static int PAGESIZE = ApplicationConstants.getInt("PAGESIZE");

	/**   */
	public static int PAGECOUNT = ApplicationConstants.getInt("PAGECOUNT");

	/**   */
	public static final int DB_TYPE = ApplicationConstants.getInt("DB_TYPE");

	/**   */
	public static final String DB_SQL_CHECK_CONNECTION = ApplicationConstants
			.getString("DB_SQL_CHECK_CONNECTION");

	/**   */
	public static String LOOK_FEEL = ApplicationConstants
			.getString("LOOK_FEEL");

	/**   */
	public static String DEFAULT_THEME = ApplicationConstants
			.getString("DEFAULT_THEME");

	/**   */
	public static String REPORT_FILE_PATH = "com/pipe/report/config/";
	/** */
	public static final String ROOT_FOLDER = ApplicationConstants
			.getString("ROOT_FOLDER");
	/** */
	public static String OUT_REPORT_FILE_PATH = ROOT_FOLDER; // ApplicationConstants.getString("OUT_REPORT_FILE_PATH");
	/** */
	public static String PATH_ACROBAT_EXE = "";

	/** */
	public static String PATH_EXEL_EXE = "";

	/** */
	public static String XML_HOLIDAY_FILE = "";

	/** */
	public static boolean IS_INIT = false;

	/** */
	public static final String ENCODE_MODE = ApplicationConstants
			.getString("ENCODE_MODE");

	/** */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/** */
	public static String FILE_RETURN_CHAR = ApplicationConstants
			.getString("FILE_RETURN_CHAR");

	/** */
	public static String XML_SQL_PATH = "";

	/** */
	public static String SOUND_PATH = "";

	/** */
	public static String FILE_SEPERATE = ApplicationConstants
			.getString("FILE_SEPERATE");

	/** */
	public static String DATABASE_CONFIG_FILE = ApplicationConstants
			.getString("DATABASE_CONFIG_FILE");

	/** */
	public static String LOGIN_USER_ID = "";

	/** */
	public static LoginUser loginUser = null;

	/** */
	public static String MENU_GRP = "";

	/** */
	public static String MENU_EXE = "";
	/** */
	public static String PRINT_MENU_GRP = "";

	/** */
	public static String PRINT_MENU_EXE = "";
	/** */
	public static FileInputStream inStream = null;

	/** */
	public static File LOCK = null;

	/** */
	public static String PC_NAME = "";

	/** */
	public static MediaSizeName mediaSizeName = MediaSizeName.ISO_A4;

	/** */
	public static List<HolidayVo> LST_HOLIDAY_DATE = new ArrayList<HolidayVo>();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	public static LoginUser getLoginUser() {

		if (loginUser != null) {
			return loginUser;
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					for (Window w : JFrame.getFrames()) {
						if (w != null) {
							w.dispose();
						}
					}
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.pack();
					loginFrame.setVisible(true);
				}
			});
			return null;
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			String bundleName = "resources.application";
			ResourceBundle rb = ResourceBundle.getBundle(bundleName);

			return rb.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		try {
			String bundleName = "resources.application";
			ResourceBundle rb = ResourceBundle.getBundle(bundleName);

			return Integer.parseInt(rb.getString(key));
		} catch (MissingResourceException e) {
			return 100;
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @throws BizException
	 */
	public static void initData() throws BizException {

		if (!IS_INIT) {

			try {
				new DBConnectionManager();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}

			InitService initService = new InitServiceImpl();

			try {
				initService.init();
			} catch (BizException e) {
				throw new BizException(e.getMessage());
			}

			try {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
				UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			JFrame getInset = new JFrame();
			getInset.pack();
			Insets inset = getInset.getInsets();
			FaceContants.setFrameInsets(inset.top, inset.left, inset.bottom,
					inset.right);
			inset = null;
			getInset = null;

			IS_INIT = true;
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public static void closeFile() {
		try {
			if (ApplicationConstants.inStream != null) {
				ApplicationConstants.inStream.close();
			}
			if (ApplicationConstants.LOCK != null) {
				ApplicationConstants.LOCK.delete();
			}
		} catch (Exception ex) {
			logger.error("Error while releasing the application LOCK. "
					+ ex.toString());
		}
	}

}
