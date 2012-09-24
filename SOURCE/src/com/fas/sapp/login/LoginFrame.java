/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：LoginFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2009/10/06   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.sapp.login;

import interfaces.INotify;
import interfaces.IUserModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.swingx.border.DropShadowBorder;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.constants.screen.ImageConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.BaseDataManager;
import com.fas.jface.MessageBox;
import com.fas.jface.button.Action;
import com.fas.jface.button.ActionButton;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.PasswordInputText;
import com.fas.sapp.system.menu.MainMenuFrame;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.init.InitService;
import com.fas.service.init.InitServiceImpl;
import com.fas.service.system.user.UserService;
import com.fas.service.system.user.UserServiceImpl;
import com.fas.vo.MEmp;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.user.LoginUser;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD> <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC13</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class LoginFrame extends InspectFrame {

	/** */
	static Logger logger = Logger.getLogger(LoginFrame.class);
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private BasePanel mainPnl;
	/** パスワードラベル */
	private BaseLabel lblPass;
	/** パスワードテキストボックス */
	private PasswordInputText psTxtInput;
	/** ログインボーターン */
	private ActionButton btnLogin;
	/** */
	private BaseLabel lblUser;
	/** */
	private BaseInputText txtUser;
	/** */
	private BaseComboBox cbUser;
	/** */
	private BaseInputText txtNinshou;
	/** */
	private String typeLogin = "";
	/** */
	private BaseLabel lblCreateDate;
	/** */
	private UserService userSerivce = new UserServiceImpl();
	/** */
	private boolean isLoginFail = false;
	/** Constant */
	static private Color LOGIN_COLOR = Color.WHITE;
	static private String LOGIN_BY_TEXT = "0";
	static private String LOGIN_BY_COMBOBOX = "1";
	/** */
	private boolean isGroup = false;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 */
	public LoginFrame() {

		super();
		init();
		try {
			loginController = new LoginController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LoginFrame(String aa) {
		this();
		txtUser.setText(aa);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 */
	private void init() {
		File sysLogFile = new File("Config\\sysLog.txt");
		boolean systemLogin = false;
		if (sysLogFile.exists()) {
			Long lastModified = sysLogFile.lastModified();

			Calendar cal = Calendar.getInstance();

			Long currTime = cal.getTime().getTime();

			if (currTime - lastModified > 10000) {
				systemLogin = true;
			}
		} else {
			systemLogin = true;
		}
		if (systemLogin) {

			Timer timer = new Timer();
			timer.schedule(new RemindTask(), 0, 10 * 1000);

			this.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(WindowEvent winEvt) {
					File f = new File("Config\\sysLog.txt");
					if (f.exists()) {
						f.delete();
					}
					System.exit(0);
				}
			});
			mainPnl.setBorder(new DropShadowBorder(Color.BLACK, 5, 0.3f, 5,
					true, true, true, true));
			setFrameBackgroupColor(LOGIN_COLOR);
			setTitle("ログイン");
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			typeLogin = MCtlConstants.getValue("SYSTEM" + "USR_LGID");

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (LOGIN_BY_COMBOBOX.equalsIgnoreCase(typeLogin)) {
						if (cbUser.isEnabled()) {
							cbUser.requestFocus();
						} else {
							psTxtInput.requestFocus();
						}
					} else if (LOGIN_BY_TEXT.equalsIgnoreCase(typeLogin)) {
						if (txtUser.isEnabled()) {
							txtUser.requestFocus();
						} else {
							psTxtInput.requestFocus();
						}
					}
				}
			});

			btnLogin.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					if (isLoginFail == true) {
						if (LOGIN_BY_COMBOBOX.equalsIgnoreCase(typeLogin)) {
							if (cbUser.isEnabled()) {
								cbUser.requestFocus();
							} else {
								psTxtInput.requestFocus();
							}
						} else if (LOGIN_BY_TEXT.equalsIgnoreCase(typeLogin)) {
							if (txtUser.isEnabled()) {
								txtUser.requestFocus();
							} else {
								psTxtInput.requestFocus();
							}
						}
						isLoginFail = false;
					}
				}
			});

			if (MCtlConstants.checkValue("SYSTEM" + "USR_TYPE", "0")) {
				isGroup = true;
			} else {
				isGroup = false;
			}

			try {

				File file = new File("pipe.jar");
				if (file.exists()) {
					Long lastModified = file.lastModified();
					Date date = new Date(lastModified);

					lblCreateDate.setText(date.toLocaleString());
				}

			} catch (Exception ex) {

			}
		} else {
			System.exit(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getFrameBackColor()
	 */
	protected Color getFrameBackColor() {
		return ColorConstants.FRAME_DEFAULT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getFrameForeColor()
	 */
	protected Color getFrameForeColor() {
		return ColorConstants.FRAME_DEFAULT_FORE_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getFrameName()
	 */
	protected String getFrameName() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getFrameID()
	 */
	protected String getFrameID() {
		return "";
	}

	/**
	 * @author PC13
	 */
	class ImagePanel extends BasePanel {

		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private Image img;

		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null), img
					.getHeight(null));
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
		}

		public void paintComponent(Graphics g) {
			// g.drawImage(img, 0, 0, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.LincFrame#getBodyPanel()
	 */
	protected BasePanel getBodyPanel() {
		typeLogin = MCtlConstants.getValue("SYSTEM" + "USR_LGID");
		// パネルの生成
		mainPnl = new BasePanel();
		mainPnl.setLayout(null);
		mainPnl.setSize(X_BODY_LENGTH, Y_BODY_LENGTH);
		mainPnl.setLocation(FaceContants.FRAME_LEFT, FaceContants.FRAME_TOP);
		mainPnl.setBackground(LOGIN_COLOR);
		mainPnl.setBorder(null);

		ImagePanel pnlCenter = new ImagePanel(
				ImageConstants.LOGIN_BACK_GROUND_IMAGE.getImage());
		pnlCenter.setLayout(null);
		pnlCenter.setSize(X_BODY_LENGTH + 2, Y_BODY_LENGTH + 2);
		pnlCenter.setLocation(0, 0);
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.setBorder(null);

		Font companyFont = new Font("ＭＳ ゴシック", Font.BOLD, 18);

		BaseLabel lblCompnay = new BaseLabel(MCtlConstants
				.getValueByCKey("CMP_NAME"));
		lblCompnay.setBounds(new Rectangle(80, 42, 430, 25));
		lblCompnay.setBorder(new EmptyBorder(new Insets(0, 0, 0, 10)));
		lblCompnay.setFont(companyFont);
		lblCompnay.setOpaque(false);
		pnlCenter.add(lblCompnay);

		BaseLabel lblProName = new BaseLabel(MCtlConstants
				.getValueByCKey("CMP_ZSYS"));
		lblProName.setBounds(new Rectangle(80, 65, 430, 25));
		lblProName.setBorder(new EmptyBorder(new Insets(0, 0, 0, 10)));
		lblProName.setFont(companyFont);
		lblProName.setOpaque(false);
		pnlCenter.add(lblProName);

		BaseLabel lblUpBar = new BaseLabel(" ");
		lblUpBar.setBounds(new Rectangle(80, 100, 430, 15));
		lblUpBar.setBorder(null);
		lblUpBar.setBackground(Color.BLUE);
		pnlCenter.add(lblUpBar);

		BaseLabel lblNinshou = new BaseLabel("認証方式");
		lblNinshou.setBounds(new Rectangle(80, 123, 100, 23));
		lblNinshou.setBorder(null);
		lblNinshou.setOpaque(false);
		pnlCenter.add(lblNinshou);

		txtNinshou = new BaseInputText();
		txtNinshou.setBounds(new Rectangle(330, 123, 160, 23));
		if (MCtlConstants.checkValue("SYSTEM" + "USR_TYPE", "0")) {
			txtNinshou.setText("グループ認証");
		} else {
			txtNinshou.setText("個人認証");
		}
		txtNinshou.setEditable(false);
		txtNinshou.setBackground(Color.WHITE);
		txtNinshou.setForeground(Color.BLUE);
		txtNinshou.setFocusable(false);
		pnlCenter.add(txtNinshou);

		BaseLabel lblLogin = new BaseLabel("Login");
		lblLogin.setBounds(new Rectangle(80, 153, 100, 23));
		lblLogin.setBorder(null);
		lblLogin.setOpaque(false);
		pnlCenter.add(lblLogin);

		lblUser = new BaseLabel();
		lblUser.setText("ユーザーID");
		lblUser.setBounds(new Rectangle(250, 153, 80, 23));
		lblUser.setBorder(null);
		lblUser.setOpaque(false);
		pnlCenter.add(lblUser);

		if ("1".equalsIgnoreCase(typeLogin)) {
			List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
			ComboService combSer = new ComboServiceImpl();
			try {
				lstData = combSer.getLstLoginUser();
			} catch (BizException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cbUser = new BaseComboBox(ApplicationUtils.createComboDataModel(
					lstData, 24, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
			txtUser = new BaseInputText();
			cbUser.setPopupWidth(160);
			cbUser.setBorder(FaceContants.DOT_LINE_BORDER);
			cbUser.setFont(FontConstants.COMBOBOX_FONT);
			cbUser.setToolTipText("ユーザＩＤを選択して下さい。");
			cbUser.setBounds(new Rectangle(330, 153, 160, 23));
			// cbUser.setEditable(true);
			String strLastLogin = "";
			try {
				if (userSerivce == null) {
					userSerivce = new UserServiceImpl();
				}
				strLastLogin = StringUtils.trimAll(userSerivce
						.getLastLoginUser(getPcId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// set last login
			boolean iSelect = true;
			int iCount = 0;
			List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbUser
					.getModel()).getAnArrayList();
			if (lstDataCombo != null) {
				if (StringUtils.isValid(strLastLogin)) {
					for (ComboObjectVo obj : lstDataCombo) {
						if (strLastLogin.equalsIgnoreCase(StringUtils
								.trimAll(obj.getValue3()))) {
							cbUser.setSelectedIndex(iCount);
							cbUser.repaint();
							iSelect = false;
							break;
						}
						iCount++;
					}
				}
				if (iSelect) {
					cbUser.requestFocus();
				}
			}
			pnlCenter.add(cbUser);
		} else if ("0".equalsIgnoreCase(typeLogin)) {
			txtUser = new BaseInputText();
			txtUser.setBounds(new Rectangle(330, 153, 160, 23));
			try {
				if (userSerivce == null) {
					userSerivce = new UserServiceImpl();
				}
				txtUser.setText(StringUtils.trimAll(userSerivce
						.getLastLoginUser(getPcId())));
			} catch (Exception e) {
				e.printStackTrace();
			}
			pnlCenter.add(txtUser);
		}
		BaseLabel lblMsg = new BaseLabel("ユーザＩＤと");
		lblMsg.setBounds(new Rectangle(80, 179, 120, 20));
		lblMsg.setBorder(null);
		lblMsg.setOpaque(false);
		pnlCenter.add(lblMsg);

		BaseLabel lblMsg1 = new BaseLabel("パスワードを");
		lblMsg1.setBounds(new Rectangle(80, 197, 120, 20));
		lblMsg1.setBorder(null);
		lblMsg1.setOpaque(false);
		pnlCenter.add(lblMsg1);

		BaseLabel lblMsg2 = new BaseLabel("入力して下さい。");
		lblMsg2.setBounds(new Rectangle(80, 215, 120, 20));
		lblMsg2.setBorder(null);
		lblMsg2.setOpaque(false);
		pnlCenter.add(lblMsg2);

		lblPass = new BaseLabel();
		lblPass.setText("パスワード");
		lblPass.setBounds(new Rectangle(250, 183, 80, 23));
		lblPass.setBorder(null);
		lblPass.setOpaque(false);
		pnlCenter.add(lblPass);

		psTxtInput = new PasswordInputText(16);
		psTxtInput.setBounds(new Rectangle(330, 183, 160, 23));
		pnlCenter.add(psTxtInput);

		btnLogin = new ActionButton();
		btnLogin.setText("** ログイン **");
		btnLogin.setBounds(new Rectangle(330, 213, 160, 23));
		btnLogin.addAction(new LoginAction());
		btnLogin.setBackground(LOGIN_COLOR);

		pnlCenter.add(btnLogin);

		lblCreateDate = new BaseLabel("");
		lblCreateDate.setLocation(450, 310);
		lblCreateDate.setSize(new Dimension(300, FaceContants.LABLE_HEIGHT));
		lblCreateDate.setBorder(null);
		lblCreateDate.setOpaque(false);
		pnlCenter.add(lblCreateDate);

		BaseLabel lblDownBar = new BaseLabel(" ");
		lblDownBar.setBounds(new Rectangle(80, 245, 430, 15));
		lblDownBar.setBorder(null);
		lblDownBar.setBackground(Color.BLUE);
		pnlCenter.add(lblDownBar);
		pnlCenter.setOpaque(false);
		mainPnl.add(pnlCenter);

		return mainPnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 * 
	 * @return
	 */
	private String getPcId() {

		String pcId = "";

		try {
			pcId = ApplicationConstants.PC_NAME;
			if (!StringUtils.isValid(pcId)) {
				pcId = InetAddress.getLocalHost().getHostName();
			}
		} catch (UnknownHostException e) {

		}

		return pcId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
	 */
	protected void setFrameSize() {

		X_FRAME_LENGTH = 590;
		Y_FRAME_LENGTH = 335;
		X_BODY_LENGTH = 590;
		Y_BODY_LENGTH = 335;

		X_HEADER_LENGTH = 0;
		Y_HEADER_LENGTH = 0;
		X_FOOTER_LENGTH = 0;
		Y_FOOTER_LENGTH = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getHeader()
	 */
	protected BasePanel getHeader() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getFooter()
	 */
	protected BasePanel getFooter() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getHeaderSeparator()
	 */
	protected JSeparator getHeaderSeparator() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#getFooterSeparator()
	 */
	protected JSeparator getFooterSeparator() {
		return null;
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @author PC13
	 */
	class LoginAction implements Action {

		public void execute() {

			// ログインを実行する
			doLogin();
		}
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @author PC13
	 */
	class LoginCancelAction implements Action {

		public void execute() {

			// キャンセルする
			doCancel();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 */
	private void doCancel() {
		typeLogin = MCtlConstants.getValue("SYSTEM" + "USR_LGID");
		psTxtInput.setText("");
		if ("1".equalsIgnoreCase(typeLogin)) {
			if (cbUser != null) {
				if (cbUser.isEnabled()) {
					cbUser.setSelectedIndex(0);
				}
			}
		} else if ("0".equalsIgnoreCase(typeLogin)) {
			if (txtUser != null) {
				if (txtUser.isEnabled()) {
					txtUser.setText("");
				}
			}
		}
	}

	private LoginController loginController;

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 */
	@SuppressWarnings("unchecked")
	private void doLogin() {

		typeLogin = MCtlConstants.getValue("SYSTEM" + "USR_LGID");
		String strUser = StringUtils.emptyIfNull(
				(String) cbUser.getSelectedItem()).trim();
		LoginUser userInfor = new LoginUser();
		if ("1".equalsIgnoreCase(typeLogin)) {
			List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbUser
					.getModel()).getAnArrayList();
			if (lstDataCombo != null) {
				if (StringUtils.isValid(strUser)) {
					for (ComboObjectVo obj : lstDataCombo) {
						if (strUser.equalsIgnoreCase(StringUtils.trimAll(obj
								.getCode()))) {
							userInfor.setUserId(StringUtils.trimAll(obj
									.getValue3()));
							break;
						}
					}
				}
			}
		} else if ("0".equalsIgnoreCase(typeLogin)) {
			userInfor.setUserId(txtUser.getText());
		}
		userInfor.setPwd(new String(psTxtInput.getPassword()));
		userInfor.setLoginPC(getPcId());
		userInfor.setGroup(isGroup);

		try {
			loginController.Login(userInfor.getUserId(), userInfor.getPwd());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public LoginUser loginUser;

	public synchronized void afterLogin() {
		// super.addNotify();
		if (loginUser != null) {
			BaseDataManager.setLoginUser(loginUser);
			ApplicationConstants.LOGIN_USER_ID = loginUser.getUserId();
			ApplicationConstants.loginUser = loginUser;

			InitService initService = new InitServiceImpl();
			try {
				initService.initAffterLogin();
			} catch (BizException e) {
				e.printStackTrace();
				MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			}

			ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, "",
					"", LogContants.LOGACT_LI, "");

			JFrame menuFrame = new MainMenuFrame();
			menuFrame.pack();
			GraphicsEnvironment e = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			menuFrame.setMaximizedBounds(e.getMaximumWindowBounds());
			menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			menuFrame.setVisible(true);

			dispose();

		} else {
			ApplicationConstants.LOGIN_USER_ID = "";
			ApplicationConstants.loginUser = null;
			isLoginFail = true;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0013"));
			if ("1".equalsIgnoreCase(typeLogin)) {
				cbUser.requestFocus();
			} else if ("0".equalsIgnoreCase(typeLogin)) {
				txtUser.requestFocus();
			}
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述： if log4j.properties file is not exist, create
	 * log4j.properties</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 * 
	 * @param path
	 * @param fileName
	 * @param project
	 * @return
	 */
	private static boolean createLog4jConfigFile(String path, String fileName,
			String project) {
		File f = new File(fileName);
		if (f.exists())
			return true;

		FileWriter fstream = null;
		BufferedWriter out = null;
		boolean result = true;
		try {
			// Create file
			fstream = new FileWriter(fileName);
			out = new BufferedWriter(fstream);
			out.write("log4j.rootLogger=INFO,STDOUT," + project + "\n");
			out.write("log4j.logger.noModule=FATAL\n");

			out.write("\n");
			out.write("# logging console.\n");
			out.write("# debug < info < warn < error < fatal\n");
			out
					.write("# Print only messages of level ERROR or above in the package noModule.\n");
			out.write("log4j.logger.noModule=FATAL\n");

			out.write("\n");
			out.write("# set PROJECT package\n");
			out.write("log4j.logger.com." + project + "=debug\n");
			out.write("log4j.logger.com." + project + ".common=debug\n");

			out.write("\n");
			out
					.write("log4j.appender.STDOUT=org.apache.log4j.ConsoleAppender\n");
			out
					.write("log4j.appender.STDOUT.layout=org.apache.log4j.PatternLayout\n");
			out
					.write("log4j.appender.STDOUT.layout.ConversionPattern=%d{yyyy/MM/dd HH:mm:ss} %-5p [%t] %c  -%m%n\n");
			out.write("log4j.appender.STDOUT.threshold=DEBUG\n");

			out.write("\n");
			out.write("log4j.appender." + project
					+ "=org.apache.log4j.RollingFileAppender\n");
			out.write("log4j.appender." + project + ".file=" + project
					+ ".log\n");
			out.write("log4j.appender." + project + ".MaxFileSize=10MB\n");
			out.write("log4j.appender." + project + ".MaxBackupIndex=3\n");
			out.write("log4j.appender." + project
					+ ".layout=org.apache.log4j.PatternLayout\n");
			out.write("log4j.appender." + project + ".threshold=DEBUG\n");
			out
					.write("log4j.appender."
							+ project
							+ ".layout.ConversionPattern=%d{yyyy/MM/dd HH:mm:ss} %-5p [%t] %c  -%m%n\n");
			out.write("\n");
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			result = false;
		} finally {
			// Close the output stream
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	class RemindTask extends TimerTask {

		public void run() {
			try {
				File f;
				f = new File("Config\\sysLog.txt");
				if (f.exists())
					f.delete();
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * </DL>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "Database_pipe.properties";
		String log4jConfigFileName = "log4j.properties";
		File rootDir = new File(".");
		FileInputStream inputStream = null;
		try {
			if (args.length > 0) {
				ApplicationConstants.PC_NAME = args[0];
			}
			inputStream = new FileInputStream(filePath);
			ResourceBundle bundle = new PropertyResourceBundle(inputStream);
			String DB_URL = StringUtils.trimString(bundle.getString("DB_URL"));
			String DB_USER = StringUtils
					.trimString(bundle.getString("DB_USER"));
			String DB_PASSWORD = StringUtils.trimString(bundle
					.getString("DB_PASSWORD"));
			String PATH_ACROBAT_EXE = StringUtils.trimString(bundle
					.getString("PATH_ACROBAT_EXE"));

			String ROOT_FOLDER = rootDir.getCanonicalPath();

			if (createLog4jConfigFile(ROOT_FOLDER, log4jConfigFileName, "pipe")) {
				PropertyConfigurator.configure(ROOT_FOLDER
						+ ApplicationConstants.FILE_SEPERATE
						+ log4jConfigFileName);
			} else {
				PropertyConfigurator.configure(ROOT_FOLDER
						+ ApplicationConstants.FILE_SEPERATE + "Config"
						+ ApplicationConstants.FILE_SEPERATE
						+ "log4j.properties");
			}

			if (StringUtils.isValid(DB_URL) && StringUtils.isValid(DB_USER)) {

				ApplicationConstants.DB_URL = DB_URL;
				ApplicationConstants.DB_USER = DB_USER;
				ApplicationConstants.DB_PASSWORD = DB_PASSWORD;
				ApplicationConstants.PATH_ACROBAT_EXE = PATH_ACROBAT_EXE;
				ApplicationConstants.XML_SQL_PATH = "file:\\" + ROOT_FOLDER
						+ ApplicationConstants.FILE_SEPERATE + "Sql"
						+ ApplicationConstants.FILE_SEPERATE;
				ApplicationConstants.XML_HOLIDAY_FILE = "file:\\" + ROOT_FOLDER
						+ ApplicationConstants.FILE_SEPERATE + "Config"
						+ ApplicationConstants.FILE_SEPERATE + "Holiday.xml";

				initData();
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.pack();
				loginFrame.setVisible(true);
			} else {
				MessageBox.error(MessageConstants.SYS_ERR_MSG_DATABASE_INFO);
			}
		} catch (Exception e) {
			MessageBox.error(MessageConstants.SYS_ERR_MSG);
			logger.error(e.getMessage());
		} finally {
			try {
				inputStream.close();
				inputStream = null;
				rootDir = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class LoginController extends UnicastRemoteObject implements INotify {
	private IUserModel controller;
	private LoginFrame view;

	protected LoginController(LoginFrame v) throws RemoteException,
			MalformedURLException, NotBoundException {
		super();
		Remote remoteObject = Naming.lookup("rmi://localhost:1099/user");
		controller = (IUserModel) remoteObject;
		view = v;
	}

	public IUserModel getController() {
		return controller;
	}

	public void setController(IUserModel controller) {
		this.controller = controller;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void doLoginCallback() throws RemoteException {
		MEmp emp = controller.loginCallback();
		if (emp == null)
			return;
		LoginUser lu = new LoginUser();
		lu.setUserId(StringUtils.emptyIfNull(emp.getEmpUser()).trim());
		lu.setEmpCode(StringUtils.emptyIfNull(emp.getEmpCode()).trim());
		lu.setEmpKana(StringUtils.emptyIfNull(emp.getEmpKana()).trim());
		lu.setEmpName(StringUtils.emptyIfNull(emp.getEmpName()).trim());
		lu.setUserName(StringUtils.emptyIfNull(emp.getEmpName()).trim());
		lu.setPwd(StringUtils.emptyIfNull(emp.getPwd()).trim());
		lu.setUserUser(StringUtils.emptyIfNull(emp.getUserUser()).trim());
		lu.setPcid(StringUtils.emptyIfNull(emp.getPcid()).trim());
		lu.setGroup(false);
		view.loginUser = lu;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.afterLogin();
			}
		});
	}

	@Override
	public synchronized void done(String code) throws RemoteException {
		// TODO Auto-generated method stub
		if (code.equals("login")) {
			System.out.println("do login callback");
			doLoginCallback();
		} else
			System.out.println(controller.searchCallback().size());
	}

	public void Login(String UserName, String Password) throws RemoteException {
		controller.login(UserName, Password, this);
	}

	public void Search(String pattern) throws RemoteException {
		controller.search(pattern, this);
	}
}
