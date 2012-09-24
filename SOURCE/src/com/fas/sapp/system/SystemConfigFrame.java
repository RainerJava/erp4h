/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：SystemConfigFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/03/01   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.sapp.system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.DBColorContants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.ExitWindow;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.panel.BaseTabPanel;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.NumberText;
import com.fas.service.common.color.ColorService;
import com.fas.service.common.color.ColorServiceImpl;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.systemconfig.SystemConfigService;
import com.fas.service.systemconfig.SystemConfigServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.color.DClrCtlVo;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.config.SystemConfigVo;

/**
 * @author PC13
 * 
 */
public class SystemConfigFrame extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(SystemConfigFrame.class);
	/** */
	public final static int I_KIHON_SETTEI_TAB = 0;
	/** */
	public final static int I_KENGEN_SETTEI_TAB = 1;
	/** */
	public final static int I_SHOHIZEI_SETTEI_TAB = 2;
	/** */
	public final static int I_SHOW_SETTEI_TAB = 3;

	/** */
	protected ActionButton btnF1;
	/** */
	protected ActionButton btnF4;
	/** */
	protected ActionButton btnF8;
	/** */
	protected ActionButton btnF12;
	/** */
	private BaseLabel lblHelpInfor;
	/** */
	private BaseTabPanel tabPnl;
	/** */
	private int iTab = 0;

	/** */
	private BaseInputText txtJishaMei;
	/** */
	private InspectRadioButton rdJidouSaiban;
	/** */
	private InspectRadioButton rdTedouSaiban;
	/** */
	private InspectRadioButton rdJidouSaibanJuchu;
	/** */
	private InspectRadioButton rdTedouSaibanJuchu;
	/** */
	private NumberText txtKikanFrom;
	/** */
	private NumberText txtKikanTo;
	/** */
	private NumberText txtShouhin;
	/** */
	private NumberText txtTorihikiSaki;
	/** */
	private NumberText txtShiten;
	/** */
	private BaseInputText txtKeishou1;
	/** */
	private BaseInputText txtKeishou2;
	/** */
	private BaseInputText txtKeishou3;
	/** */
	private BaseInputText txtKeishou4;
	/** */
	private BaseInputText txtKeishou5;

	/** */
	private BaseCheckBox chkYuzaKengen;
	/** */
	private BaseCheckBox chkLoginInfor;
	/** */
	private BaseCheckBox chkPassword;
	/** */
	private InspectRadioButton rdYuzaTani;
	/** */
	private InspectRadioButton rdTantousahTani;

	/** */
	private ColorCombobox cbHyoujiSettei;
	/** */
	private NumberText txtR;
	/** */
	private NumberText txtG;
	/** */
	private NumberText txtB;
	/** */
	private BasePanel pnlColor;

	/** */
	private MClrCtlVo clrVo = null;

	/** */
	private ColorService colorService;
	/** */
	private SystemConfigService configService;

	/** */
	private boolean isHenkouShita = false;
	private Timer timer;
	private PropertyChangeListener pro;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public SystemConfigFrame() {
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param parentFrame
	 * @param modal
	 */
	public SystemConfigFrame(BaseFrame parentFrame, boolean modal) {
		super(parentFrame, modal);
		init();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	private void init() {

		setTitle("システム設定");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new ExitWindow(btnF12));

		setResizable(false);
		// setIconImage(ImageConstants.TITLE.getImage());
		pro = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (("focusOwner".equals(prop))) {
					if (((e.getNewValue()) instanceof InspectRadioButton) 
							|| ((e.getNewValue()) instanceof BaseComboBox) 
							|| ((e.getNewValue()) instanceof BaseInputText) 
							|| ((e.getNewValue()) instanceof BaseCheckBox) 
							|| ((e.getNewValue()) instanceof JButton) 
							|| ((e.getNewValue()) instanceof NumberText)) {
						JComponent comp = (JComponent) e.getNewValue();
						String name = StringUtils.emptyIfNull(comp.getToolTipText());
						setHelpInfor(name);
					}
				}
			}
		};
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(pro);

		// MouseAdapter mouseAdapter = new MouseAdapter() {
		// public void mouseEntered(MouseEvent e) {
		// getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// }
		//
		// public void mouseExited(MouseEvent e) {
		// getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// }
		// };
		//
		// setMouseCurrsor(btnF1, mouseAdapter);
		// setMouseCurrsor(btnF4, mouseAdapter);
		// setMouseCurrsor(btnF8, mouseAdapter);
		// setMouseCurrsor(btnF12, mouseAdapter);
		isHenkouShita = false;
		btnF8.setFocusable(false);
		btnF12.setFocusable(false);

		if (!"ADMIN".equalsIgnoreCase(ApplicationConstants.getLoginUser().getUserUser())) {
			iTab = 3;
		} else {
			iTab = 0;
		}
	}

	// /**
	// * <DL>
	// * <DT>メソッド記述：</DT>
	// * <DD></DD>
	// * <BR>
	// *
	// * </DL>
	// * @param btn
	// * @param mouseAd
	// */
	// private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
	// if (btn != null) {
	// btn.addMouseListener(mouseAd);
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.InspectDialog#getBodyPanel()
	 */
	@Override
	protected BasePanel getBodyPanel() {

		mainPnl = new BasePanel();
		mainPnl.setLayout(null);
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		int xPos = 9;
		int yPos = 4;

		tabPnl = new BaseTabPanel();
		tabPnl.setLocation(xPos, yPos);
		tabPnl.setSize(new Dimension(X_BODY_LENGTH - 2 * xPos, Y_BODY_LENGTH - 2 * yPos - 40));
		tabPnl.addTab("　基本設定　", createKihonConfigPanel("基本設定"));
		tabPnl.addTab("　権限設定　", createKengenConfigPanel("権限設定"));
		tabPnl.addTab("　消費税率　", createShouhizeiConfigPanel("消費税率"));
		tabPnl.addTab("　表示設定　", createHyoujiConfigPanel("表示設定"));

		if (!"ADMIN".equalsIgnoreCase(ApplicationConstants.getLoginUser().getUserUser())) {
			tabPnl.setSelectedIndex(3);
			tabPnl.setEnabledAt(0, false);
			tabPnl.setEnabledAt(1, false);
		} else {
			tabPnl.setSelectedIndex(0);
		}
		tabPnl.setEnabledAt(2, false);

		tabPnl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				iTab = tabPnl.getSelectedIndex();
			}
		});

		int X_BTN_WIDTH = 71;
		final int Y_BTN_HEGITH = 36;

		xPos = 220;
		yPos += Y_BODY_LENGTH - yPos - 40 + FaceContants.VERTICAL_SPACE;
		F8Action f8Action = new F8Action("f8Action");
		btnF8 = new ActionButton();
		btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF8.setText("<html><center><font size=-1>OK</font></center><center><font size=-1>F8(A)</font></center>");
		btnF8.addActionListener(f8Action);
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
		btnF8.getActionMap().put("f8Action", f8Action);
		btnF8.setToolTipText("[F8]：入力情報を登録します。");
		mainPnl.add(btnF8);

		xPos += X_BTN_WIDTH + 40;
		btnF12 = new ActionButton();
		btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF12.setText("<html><center><font size=-1>終了</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.setToolTipText("[F12]：処理を終了します。");
		AbstractAction f12Action = new AbstractAction("f12Action") {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
				stopTimerRemoveListener(getDialog());
			}
		};
		btnF12.addActionListener(f12Action);
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), f12Action.getValue(Action.NAME));
		btnF12.getActionMap().put("f12Action", f12Action);
		mainPnl.add(btnF12);

		mainPnl.add(tabPnl);

		return mainPnl;
	}

	/**
	 * get dialog to stop timer, remove propertyChangeListener
	 * 
	 * @return
	 */
	public JDialog getDialog() {
		return this;
	}

	@Override
	protected BasePanel getFooter() {
		// パネルの生成
		footerMainPnl = new BasePanel();
		footerMainPnl.setLayout(null);
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH, Y_FOOTER_LENGTH));
		int xPos = 1;
		int yPos = 1;
		final int I_LBL_HEIGHT = 22;

		BasePanel statusBar = new BasePanel();
		statusBar.setSize(new Dimension(X_FOOTER_LENGTH - 4, Y_FOOTER_LENGTH - 4));
		statusBar.setLocation(2, 3);
		statusBar.setLayout(null);
		statusBar.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor.setSize(new Dimension(X_FOOTER_LENGTH - 359, I_LBL_HEIGHT));
		lblHelpInfor.setLocation(xPos, yPos);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);

		xPos = X_FOOTER_LENGTH - 359 + 2;
		final BaseLabel lblDate = new BaseLabel();
		lblDate.setSize(new Dimension(160, I_LBL_HEIGHT));
		lblDate.setLocation(xPos, yPos);
		lblDate.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblDate.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblDate.setText(getSystemDateTime());
		lblDate.setBorder(FaceContants.LABEL_BORDER);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDate.setText(getSystemDateTime());
				repaint();
			}
		});
		timer.start();
		statusBar.add(lblDate);

		xPos = xPos + 160 + 2;
		BaseLabel lblUser = new BaseLabel();
		lblUser.setSize(new Dimension(85, I_LBL_HEIGHT));
		lblUser.setLocation(xPos, yPos);
		lblUser.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblUser.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblUser.setBorder(FaceContants.LABEL_BORDER);
		lblUser.setText(getUserName());
		statusBar.add(lblUser);

		xPos = xPos + 85 + 2;
		BaseLabel lblPc = new BaseLabel();
		lblPc.setSize(new Dimension(90, I_LBL_HEIGHT));
		lblPc.setLocation(xPos, yPos);
		lblPc.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblPc.setText(getPCName());
		lblPc.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblPc.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblPc);

		xPos = xPos + 90 + 2;
		BaseLabel lblSpace = new BaseLabel();
		lblSpace.setSize(new Dimension(10, I_LBL_HEIGHT));
		lblSpace.setLocation(xPos, yPos);
		lblSpace.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblSpace.setText("");
		lblSpace.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblSpace.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblSpace);

		footerMainPnl.add(statusBar);

		return footerMainPnl;
	}

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
	protected String getHelpInfor() {
		return "";
	}

	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected BasePanel getHeader() {
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.gui.AbstractFrame#setFrameSize()
	 */
	protected void setFrameSize() {
		colorService = new ColorServiceImpl();
		configService = new SystemConfigServiceImpl();
		X_FRAME_LENGTH = 650;
		Y_FRAME_LENGTH = 400;
		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH - 30;
		X_HEADER_LENGTH = X_FRAME_LENGTH;
		Y_HEADER_LENGTH = 0;
		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 30;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the isHenkouShita
	 */
	public boolean isHenkouShita() {
		return isHenkouShita;
	}

	/**
	 * @author PC13
	 * 
	 */
	class F8Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F8Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF8();
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
	protected void doF8() {

		SystemConfigVo configVo = getSystemConfigVoValue();
		if (configVo != null) {
			try {
				configService.updateSystemConfig(configVo);
				isHenkouShita = true;
				dispose();
			} catch (BizException e) {
				logger.error(e.getMessage());
				MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			}
		}
		return;
	}

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
	private SystemConfigVo getSystemConfigVoValue() {

		SystemConfigVo configVo = null;

		if (iTab == I_KIHON_SETTEI_TAB) {
			if (validateKihon()) {
				configVo = new SystemConfigVo();
				configVo.setShaMei(txtJishaMei.getText().trim());
				if (rdJidouSaiban.isSelected()) {
					configVo.setJidouSaiban("0");
				} else {
					configVo.setJidouSaiban("1");
				}

				if (rdJidouSaibanJuchu.isSelected()) {
					configVo.setJidouSaibanJuchuu("0");
				} else {
					configVo.setJidouSaibanJuchuu("1");
				}
				configVo.setKikanFrom(txtKikanFrom.getText().trim());
				configVo.setKikanTo(txtKikanTo.getText().trim());
				configVo.setHinmei(txtShouhin.getText().trim());
				configVo.setTokuisaki(txtTorihikiSaki.getText().trim());
				configVo.setShiten(txtShiten.getText().trim());
				configVo.setKeishou1(txtKeishou1.getText().trim());
				configVo.setKeishou2(txtKeishou2.getText().trim());
				configVo.setKeishou3(txtKeishou3.getText().trim());
				configVo.setKeishou4(txtKeishou4.getText().trim());
				configVo.setKeishou5(txtKeishou5.getText().trim());
				configVo.setIAction(iTab);
			}
		} else if (iTab == I_KENGEN_SETTEI_TAB) {

			if (validateKengen()) {

				configVo = new SystemConfigVo();

				if (chkYuzaKengen.isSelected()) {
					configVo.setYuzaKengen("0");
				} else {
					configVo.setYuzaKengen("1");
				}
				if (chkLoginInfor.isSelected()) {
					configVo.setLoguonJouhou("0");
				} else {
					configVo.setLoguonJouhou("1");
				}
				if (chkPassword.isSelected()) {
					configVo.setPassword("0");
				} else {
					configVo.setPassword("1");
				}

				if (rdYuzaTani.isSelected()) {
					configVo.setYuzaSentaku("0");
				} else {
					configVo.setYuzaSentaku("1");
				}

				configVo.setIAction(iTab);
			}
		} else {
			if (validateHyouji()) {
				configVo = new SystemConfigVo();
				ComboObjectVo obj = cbHyoujiSettei.getSelectObjValue();
				if (obj != null) {
					if (clrVo != null) {
						clrVo.setClrRed(NumberUtils.getIntFromString(txtR.getText()));
						clrVo.setClrGreen(NumberUtils.getIntFromString(txtG.getText()));
						clrVo.setClrBlue(NumberUtils.getIntFromString(txtB.getText()));
						configVo.setClrVo(clrVo);
					} else {
						configVo.setClrVo(null);
					}
					configVo.setIAction(iTab);
				}
			}
		}
		return configVo;
	}

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
	private boolean validateKihon() {

		boolean isValid = true;
		JComponent comp = null;

		if (isValid && !StringUtils.checkLenString(txtJishaMei.getText(), 50)) {
			isValid = false;
			comp = txtJishaMei;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !DateUtils.isValidFormat(txtKikanFrom.getText(), "yyyyMMdd")) {
			isValid = false;
			comp = txtKikanFrom;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0101"));
		}

		if (isValid && !DateUtils.isValidFormat(txtKikanTo.getText(), "yyyyMMdd")) {
			isValid = false;
			comp = txtKikanTo;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0101"));
		}

		if (isValid && StringUtils.compareS(txtKikanFrom.getText(), txtKikanTo.getText()) >= 0) {
			isValid = false;
			comp = txtKikanFrom;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0015"));
		}

		if (isValid && !StringUtils.checkLenString(txtKeishou1.getText(), 6)) {
			isValid = false;
			comp = txtKeishou1;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !StringUtils.checkLenString(txtKeishou2.getText(), 6)) {
			isValid = false;
			comp = txtKeishou2;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !StringUtils.checkLenString(txtKeishou3.getText(), 6)) {
			isValid = false;
			comp = txtKeishou3;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !StringUtils.checkLenString(txtKeishou4.getText(), 6)) {
			isValid = false;
			comp = txtKeishou4;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !StringUtils.checkLenString(txtKeishou5.getText(), 6)) {
			isValid = false;
			comp = txtKeishou5;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (comp != null) {
			setFocusComponent(comp);
		}

		return isValid;
	}

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
	private boolean validateKengen() {

		return true;
	}

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
	private boolean validateHyouji() {

		boolean isValid = true;
		JComponent comp = null;

		if (isValid && (cbHyoujiSettei.getSelectedIndex() <= 0)) {
			isValid = false;
			comp = cbHyoujiSettei;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
		}

		if (isValid && !StringUtils.isInteger(txtR.getText())) {
			isValid = false;
			comp = txtR;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0116"));
		}

		if (isValid) {
			if (!StringUtils.isColorInt(txtR.getText())) {
				isValid = false;
				comp = txtR;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0018"));
			}
		}

		if (isValid && !StringUtils.isInteger(txtG.getText())) {
			isValid = false;
			comp = txtG;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0116"));
		}

		if (isValid) {
			if (!StringUtils.isColorInt(txtG.getText())) {
				isValid = false;
				comp = txtG;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0018"));
			}
		}

		if (isValid && !StringUtils.isInteger(txtB.getText())) {
			isValid = false;
			comp = txtB;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0116"));
		}

		if (isValid) {
			if (!StringUtils.isColorInt(txtB.getText())) {
				isValid = false;
				comp = txtB;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0018"));
			}
		}

		if (comp != null) {
			setFocusComponent(comp);
		}

		return isValid;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param s
	 * @return
	 */
	private BasePanel createKihonConfigPanel(String s) {

		int xPos = 9;
		int yPos = 10;
		int LBL_WIDTH = 120;
		int VERTICAL_SPACE = FaceContants.VERTICAL_SPACE + 8;
		int LBL_SUB_WIDTH = 50;

		BasePanel pnl = new BasePanel();
		pnl.setLayout(null);

		EditLabel lblShamei = new EditLabel("自 社 名");
		lblShamei.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblShamei.setLocation(xPos, yPos);
		pnl.add(lblShamei);

		xPos += LBL_WIDTH + 10;
		txtJishaMei = new BaseInputText("", 20, 50);
		txtJishaMei.setSize(350, FaceContants.TEXT_HEIGHT);
		txtJishaMei.setLocation(xPos, yPos);
		txtJishaMei.setText(MCtlConstants.getValue("SYSTEM" + "CMP_NAME"));
		txtJishaMei.setToolTipText("自社名を入力して下さい。");
		pnl.add(txtJishaMei);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblJidouSaiban = new EditLabel("自動採番");
		lblJidouSaiban.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblJidouSaiban.setLocation(xPos, yPos);
		pnl.add(lblJidouSaiban);

		xPos += LBL_WIDTH + 10;
		rdJidouSaiban = new InspectRadioButton("自動採番");
		rdJidouSaiban.setSize(100, FaceContants.TEXT_HEIGHT);
		rdJidouSaiban.setLocation(xPos, yPos);
		rdJidouSaiban.setToolTipText("自動採番を入力して下さい。");
		pnl.add(rdJidouSaiban);

		xPos += 100;
		rdTedouSaiban = new InspectRadioButton("手動採番");
		rdTedouSaiban.setSize(100, FaceContants.TEXT_HEIGHT);
		rdTedouSaiban.setLocation(xPos, yPos);
		rdTedouSaiban.setToolTipText("自動採番を入力して下さい。");
		pnl.add(rdTedouSaiban);

		ButtonGroup btnJidouSaiban = new ButtonGroup();
		btnJidouSaiban.add(rdTedouSaiban);
		btnJidouSaiban.add(rdJidouSaiban);

		if (MCtlConstants.checkValue("SYSTEM" + "SLP_AUTO", "0")) {
			rdJidouSaiban.setSelected(true);
		} else {
			rdTedouSaiban.setSelected(true);
		}

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblJidouSaibanJuchuu = new EditLabel("自動採番（受注）");
		lblJidouSaibanJuchuu.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblJidouSaibanJuchuu.setLocation(xPos, yPos);
		pnl.add(lblJidouSaibanJuchuu);

		xPos += LBL_WIDTH + 10;
		rdJidouSaibanJuchu = new InspectRadioButton("自動採番");
		rdJidouSaibanJuchu.setSize(100, FaceContants.TEXT_HEIGHT);
		rdJidouSaibanJuchu.setLocation(xPos, yPos);
		rdJidouSaibanJuchu.setToolTipText("自動採番（受注）を入力して下さい。");
		pnl.add(rdJidouSaibanJuchu);

		xPos += 100;
		rdTedouSaibanJuchu = new InspectRadioButton("手動採番");
		rdTedouSaibanJuchu.setSize(100, FaceContants.TEXT_HEIGHT);
		rdTedouSaibanJuchu.setLocation(xPos, yPos);
		rdTedouSaibanJuchu.setToolTipText("自動採番（受注）を入力して下さい。");
		pnl.add(rdTedouSaibanJuchu);

		ButtonGroup btnJidouSaibanJuchu = new ButtonGroup();
		btnJidouSaibanJuchu.add(rdJidouSaibanJuchu);
		btnJidouSaibanJuchu.add(rdTedouSaibanJuchu);
		if (MCtlConstants.checkValue("SYSTEM" + "SLP_ORDR", "0")) {
			rdJidouSaibanJuchu.setSelected(true);
		} else {
			rdTedouSaibanJuchu.setSelected(true);
		}
		rdJidouSaibanJuchu.setEnabled(false);
		rdTedouSaibanJuchu.setEnabled(false);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblDetaNyuryokuKikan = new EditLabel("データ入力期間");
		lblDetaNyuryokuKikan.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblDetaNyuryokuKikan.setLocation(xPos, yPos);
		pnl.add(lblDetaNyuryokuKikan);
		String strKikan = StringUtils.emptyIfNull(MCtlConstants.getValue("SYSTEM" + "DATE_ENT")).trim();

		xPos += LBL_WIDTH + 10;
		txtKikanFrom = new NumberText("", 20, 8);
		txtKikanFrom.setSize(80, FaceContants.TEXT_HEIGHT);
		txtKikanFrom.setLocation(xPos, yPos);
		txtKikanFrom.setToolTipText("データ入力期間を入力して下さい。");
		pnl.add(txtKikanFrom);

		xPos += 80;
		BaseLabel lblHyphan = new BaseLabel("～");
		lblHyphan.setSize(30, FaceContants.TEXT_HEIGHT);
		lblHyphan.setLocation(xPos, yPos);
		lblHyphan.setBorder(null);
		lblHyphan.setBackground(pnl.getBackground());
		lblHyphan.setHorizontalAlignment(SwingConstants.CENTER);
		pnl.add(lblHyphan);

		xPos += 30;
		txtKikanTo = new NumberText("", 20, 8);
		txtKikanTo.setSize(80, FaceContants.TEXT_HEIGHT);
		txtKikanTo.setLocation(xPos, yPos);
		txtKikanTo.setToolTipText("データ入力期間を入力して下さい。");
		pnl.add(txtKikanTo);

		if (strKikan.length() != 16) {
			txtKikanFrom.setText("");
			txtKikanTo.setText("");
		} else {
			txtKikanFrom.setText(StringUtils.subString(strKikan, 8));
			txtKikanTo.setText(StringUtils.subString(strKikan, 8, 16));
		}

		xPos += 80;
		BaseLabel lblHelp = new BaseLabel("（西暦：年月日）");
		lblHelp.setSize(150, FaceContants.TEXT_HEIGHT);
		lblHelp.setLocation(xPos, yPos);
		lblHelp.setBorder(null);
		lblHelp.setBackground(pnl.getBackground());
		pnl.add(lblHelp);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblCdKetaSuu = new EditLabel("コード桁数");
		lblCdKetaSuu.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblCdKetaSuu.setLocation(xPos, yPos);
		pnl.add(lblCdKetaSuu);

		xPos += LBL_WIDTH + 10;
		EditLabel lblShouhin = new EditLabel("品名");
		lblShouhin.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblShouhin.setLocation(xPos, yPos);
		pnl.add(lblShouhin);
		xPos += LBL_SUB_WIDTH;
		txtShouhin = new NumberText("", 20, 2);
		txtShouhin.setSize(30, FaceContants.TEXT_HEIGHT);
		txtShouhin.setLocation(xPos, yPos);
		txtShouhin.setText(MCtlConstants.getValue("SYSTEM" + "BM_PROD"));
		txtShouhin.setToolTipText("品名を入力して下さい。");
		pnl.add(txtShouhin);
		xPos += 30;
		BaseLabel lblShouhinKeta = new BaseLabel("桁");
		lblShouhinKeta.setSize(20, FaceContants.LABLE_HEIGHT);
		lblShouhinKeta.setBackground(pnl.getBackground());
		lblShouhinKeta.setBorder(null);
		lblShouhinKeta.setLocation(xPos, yPos);
		pnl.add(lblShouhinKeta);

		xPos += 20 + 10;
		EditLabel lblTorihikiSaki = new EditLabel("得意先");
		lblTorihikiSaki.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblTorihikiSaki.setLocation(xPos, yPos);
		pnl.add(lblTorihikiSaki);
		xPos += LBL_SUB_WIDTH;
		txtTorihikiSaki = new NumberText("", 20, 2);
		txtTorihikiSaki.setSize(30, FaceContants.TEXT_HEIGHT);
		txtTorihikiSaki.setLocation(xPos, yPos);
		txtTorihikiSaki.setText(MCtlConstants.getValue("SYSTEM" + "BM_CUST"));
		txtTorihikiSaki.setToolTipText("得意先を入力して下さい。");
		pnl.add(txtTorihikiSaki);
		xPos += 30;
		BaseLabel lblTorihikiSakiKeta = new BaseLabel("桁");
		lblTorihikiSakiKeta.setSize(20, FaceContants.LABLE_HEIGHT);
		lblTorihikiSakiKeta.setBackground(pnl.getBackground());
		lblTorihikiSakiKeta.setBorder(null);
		lblTorihikiSakiKeta.setLocation(xPos, yPos);
		pnl.add(lblTorihikiSakiKeta);

		xPos += 20 + 10;
		EditLabel lblShiten = new EditLabel("支店");
		lblShiten.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblShiten.setLocation(xPos, yPos);
		pnl.add(lblShiten);
		xPos += LBL_SUB_WIDTH;
		txtShiten = new NumberText("", 20, 2);
		txtShiten.setSize(30, FaceContants.TEXT_HEIGHT);
		txtShiten.setLocation(xPos, yPos);
		txtShiten.setEnabled(false);
		txtShiten.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
		txtShiten.setToolTipText("支店を入力して下さい。");
		pnl.add(txtShiten);
		xPos += 30;
		BaseLabel lblShitenKeta = new BaseLabel("桁");
		lblShitenKeta.setSize(20, FaceContants.LABLE_HEIGHT);
		lblShitenKeta.setBackground(pnl.getBackground());
		lblShitenKeta.setBorder(null);
		lblShitenKeta.setLocation(xPos, yPos);
		pnl.add(lblShitenKeta);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblKeishou = new EditLabel("敬　称");
		lblKeishou.setSize(LBL_WIDTH, 2 * FaceContants.LABLE_HEIGHT + 3);
		lblKeishou.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		lblKeishou.setLocation(xPos, yPos);
		pnl.add(lblKeishou);

		xPos += LBL_WIDTH + 10;
		EditLabel lblKeishou1 = new EditLabel("敬称１");
		lblKeishou1.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblKeishou1.setLocation(xPos, yPos);
		pnl.add(lblKeishou1);
		xPos += LBL_SUB_WIDTH;
		txtKeishou1 = new BaseInputText("", 20, 6);
		txtKeishou1.setSize(40, FaceContants.TEXT_HEIGHT);
		txtKeishou1.setLocation(xPos, yPos);
		txtKeishou1.setText(NameConstants.getName("TTL" + "1"));
		txtKeishou1.setToolTipText("敬称１を入力して下さい。");
		pnl.add(txtKeishou1);

		xPos += 40 + 20;
		EditLabel lblKeishou2 = new EditLabel("敬称２");
		lblKeishou2.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblKeishou2.setLocation(xPos, yPos);
		pnl.add(lblKeishou2);
		xPos += LBL_SUB_WIDTH;
		txtKeishou2 = new BaseInputText("", 20, 6);
		txtKeishou2.setSize(40, FaceContants.TEXT_HEIGHT);
		txtKeishou2.setLocation(xPos, yPos);
		txtKeishou2.setText(NameConstants.getName("TTL" + "2"));
		txtKeishou2.setToolTipText("敬称２を入力して下さい。");
		pnl.add(txtKeishou2);

		xPos += 40 + 20;
		EditLabel lblKeishou3 = new EditLabel("敬称３");
		lblKeishou3.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblKeishou3.setLocation(xPos, yPos);
		pnl.add(lblKeishou3);
		xPos += LBL_SUB_WIDTH;
		txtKeishou3 = new BaseInputText("", 20, 6);
		txtKeishou3.setSize(40, FaceContants.TEXT_HEIGHT);
		txtKeishou3.setLocation(xPos, yPos);
		txtKeishou3.setText(NameConstants.getName("TTL" + "3"));
		txtKeishou3.setToolTipText("敬称３を入力して下さい。");
		pnl.add(txtKeishou3);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + 3;
		xPos += LBL_WIDTH + 10;
		EditLabel lblKeishou4 = new EditLabel("敬称４");
		lblKeishou4.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblKeishou4.setLocation(xPos, yPos);
		pnl.add(lblKeishou4);
		xPos += LBL_SUB_WIDTH;
		txtKeishou4 = new BaseInputText("", 20, 6);
		txtKeishou4.setSize(40, FaceContants.TEXT_HEIGHT);
		txtKeishou4.setLocation(xPos, yPos);
		txtKeishou4.setText(NameConstants.getName("TTL" + "4"));
		txtKeishou4.setToolTipText("敬称４を入力して下さい。");
		pnl.add(txtKeishou4);

		xPos += 40 + 20;
		EditLabel lblKeishou5 = new EditLabel("敬称５");
		lblKeishou5.setSize(LBL_SUB_WIDTH, FaceContants.LABLE_HEIGHT);
		lblKeishou5.setLocation(xPos, yPos);
		pnl.add(lblKeishou5);
		xPos += LBL_SUB_WIDTH;
		txtKeishou5 = new BaseInputText("", 20, 6);
		txtKeishou5.setSize(40, FaceContants.TEXT_HEIGHT);
		txtKeishou5.setLocation(xPos, yPos);
		txtKeishou5.setText(NameConstants.getName("TTL" + "5"));
		txtKeishou5.setToolTipText("敬称５を入力して下さい。");
		pnl.add(txtKeishou5);

		return pnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param s
	 * @return
	 */
	private BasePanel createShouhizeiConfigPanel(String s) {

		BasePanel pnl = new BasePanel();
		pnl.setLayout(null);

		return pnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param s
	 * @return
	 */
	private BasePanel createKengenConfigPanel(String s) {

		int xPos = 9;
		int yPos = 10;
		int LBL_WIDTH = 120;
		int VERTICAL_SPACE = FaceContants.VERTICAL_SPACE + 20;
		BasePanel pnl = new BasePanel();
		pnl.setLayout(null);

		EditLabel lblYuzaKengen = new EditLabel("ユーザー権限");
		lblYuzaKengen.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblYuzaKengen.setLocation(xPos, yPos);
		pnl.add(lblYuzaKengen);

		xPos += LBL_WIDTH + 10;
		chkYuzaKengen = new BaseCheckBox("ユーザー毎に権限を設定する。");
		chkYuzaKengen.setSize(350, FaceContants.TEXT_HEIGHT);
		chkYuzaKengen.setLocation(xPos, yPos);
		chkYuzaKengen.setToolTipText("ユーザー権限を選択して下さい。");
		pnl.add(chkYuzaKengen);

		ActionListener userKengen = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chkYuzaKengen.isSelected()) {
					chkLoginInfor.setEnabled(true);
					chkPassword.setEnabled(true);
					rdYuzaTani.setEnabled(true);
					rdTantousahTani.setEnabled(true);
				} else {
					chkLoginInfor.setEnabled(false);
					chkPassword.setEnabled(false);
					rdYuzaTani.setEnabled(false);
					rdTantousahTani.setEnabled(false);
				}
			}

		};
		chkYuzaKengen.addActionListener(userKengen);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblLoginJouhou = new EditLabel("ログオン情報");
		lblLoginJouhou.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblLoginJouhou.setLocation(xPos, yPos);
		pnl.add(lblLoginJouhou);

		xPos += LBL_WIDTH + 10;
		chkLoginInfor = new BaseCheckBox("毎回ユーザーの初期表示をする。");
		chkLoginInfor.setSize(350, FaceContants.TEXT_HEIGHT);
		chkLoginInfor.setLocation(xPos, yPos);
		chkLoginInfor.setToolTipText("ログオン情報を選択して下さい。");
		pnl.add(chkLoginInfor);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblPassword = new EditLabel("パスワード");
		lblPassword.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblPassword.setLocation(xPos, yPos);
		pnl.add(lblPassword);

		xPos += LBL_WIDTH + 10;
		chkPassword = new BaseCheckBox("パスワード未入力の許可。");
		chkPassword.setSize(350, FaceContants.TEXT_HEIGHT);
		chkPassword.setLocation(xPos, yPos);
		chkPassword.setToolTipText("パスワードを選択して下さい。");
		pnl.add(chkPassword);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblYuzaSentaku = new EditLabel("ユーザー選択");
		lblYuzaSentaku.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblYuzaSentaku.setLocation(xPos, yPos);
		pnl.add(lblYuzaSentaku);

		xPos += LBL_WIDTH + 10;
		rdYuzaTani = new InspectRadioButton("ユーザー単位");
		rdYuzaTani.setSize(130, FaceContants.TEXT_HEIGHT);
		rdYuzaTani.setLocation(xPos, yPos);
		rdYuzaTani.setToolTipText("ユーザー選択を選択して下さい。");
		pnl.add(rdYuzaTani);

		xPos += 130;
		rdTantousahTani = new InspectRadioButton("担当者単位");
		rdTantousahTani.setSize(130, FaceContants.TEXT_HEIGHT);
		rdTantousahTani.setLocation(xPos, yPos);
		rdTantousahTani.setToolTipText("ユーザー選択を選択して下さい。");
		pnl.add(rdTantousahTani);

		ButtonGroup btnYuzaSentaku = new ButtonGroup();
		btnYuzaSentaku.add(rdYuzaTani);
		btnYuzaSentaku.add(rdTantousahTani);

		if (MCtlConstants.checkValue("SYSTEM" + "USR_PRVL", "0")) {
			chkYuzaKengen.setSelected(true);
		} else {
			chkYuzaKengen.setSelected(false);
			chkLoginInfor.setEnabled(false);
			chkPassword.setEnabled(false);
			rdYuzaTani.setEnabled(false);
			rdTantousahTani.setEnabled(false);
		}

		if (MCtlConstants.checkValue("SYSTEM" + "USR_RWRT", "0")) {
			chkLoginInfor.setSelected(true);
		} else {
			chkLoginInfor.setSelected(false);
		}

		if (MCtlConstants.checkValue("SYSTEM" + "PWD_ENPT", "0")) {
			chkPassword.setSelected(true);
		} else {
			chkPassword.setSelected(false);
		}

		if (MCtlConstants.checkValue("SYSTEM" + "USR_TYPE", "0")) {
			rdYuzaTani.setSelected(true);
		} else {
			rdTantousahTani.setSelected(true);
		}

		return pnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param s
	 * @return
	 */
	private BasePanel createHyoujiConfigPanel(String s) {

		int xPos = 9;
		int yPos = 10;
		int LBL_WIDTH = 120;
		int VERTICAL_SPACE = FaceContants.VERTICAL_SPACE + 20;
		int X_BTN_WIDTH = 71;
		final int Y_BTN_HEGITH = 36;

		BasePanel pnl = new BasePanel();
		pnl.setLayout(null);

		EditLabel lblHyoujiSetteiMei = new EditLabel("表示設定名");
		lblHyoujiSetteiMei.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblHyoujiSetteiMei.setLocation(xPos, yPos);
		pnl.add(lblHyoujiSetteiMei);

		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		try {
			lstData = comService.getLstColor(ApplicationConstants.getLoginUser().getUserId());
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstData = new ArrayList<ComboObjectVo>();
		}
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData, 1);
		xPos += LBL_WIDTH + 10;
		cbHyoujiSettei = new ColorCombobox(aModel);
		cbHyoujiSettei.setSize(300, FaceContants.TEXT_HEIGHT);
		cbHyoujiSettei.setLocation(xPos, yPos);
		cbHyoujiSettei.setPopupWidth(300);
		cbHyoujiSettei.setToolTipText("表示設定名を選択して下さい。");
		pnl.add(cbHyoujiSettei);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblRed = new EditLabel("R");
		lblRed.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblRed.setLocation(xPos, yPos);
		pnl.add(lblRed);

		FocusAdapter focus = new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (cbHyoujiSettei.getSelectedIndex() > 0) {
					if (StringUtils.isColorInt(txtR.getText()) && StringUtils.isColorInt(txtR.getText()) && StringUtils.isColorInt(txtR.getText())) {
						pnlColor.setBackground(new Color(NumberUtils.getIntFromString(txtR.getText()), NumberUtils.getIntFromString(txtG.getText()),
								NumberUtils.getIntFromString(txtB.getText())));
						pnlColor.repaint();
					}
				}
			}
		};

		xPos += LBL_WIDTH + 10;
		txtR = new NumberText("", 20, 3);
		txtR.setSize(80, FaceContants.TEXT_HEIGHT);
		txtR.setLocation(xPos, yPos);
		txtR.addFocusListener(focus);
		txtR.setToolTipText("赤（Ｒ）を入力して下さい。");
		pnl.add(txtR);

		pnlColor = new BasePanel();
		pnlColor.setSize(180, 2 * FaceContants.VERTICAL_SPACE + 3 * FaceContants.TEXT_HEIGHT);
		pnlColor.setLocation(xPos + 100, yPos);
		pnlColor.setBorder(FaceContants.LINE_BORDER);
		pnl.add(pnlColor);

		VERTICAL_SPACE = FaceContants.VERTICAL_SPACE;
		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblGreen = new EditLabel("G");
		lblGreen.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblGreen.setLocation(xPos, yPos);
		pnl.add(lblGreen);

		xPos += LBL_WIDTH + 10;
		txtG = new NumberText("", 20, 3);
		txtG.setSize(80, FaceContants.TEXT_HEIGHT);
		txtG.setLocation(xPos, yPos);
		txtG.addFocusListener(focus);
		txtG.setToolTipText("緑（Ｇ）を入力して下さい。");
		pnl.add(txtG);

		xPos = 9;
		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE;
		EditLabel lblBlue = new EditLabel("B");
		lblBlue.setSize(LBL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblBlue.setLocation(xPos, yPos);
		pnl.add(lblBlue);
		xPos += LBL_WIDTH + 10;
		txtB = new NumberText("", 20, 3);
		txtB.setSize(80, FaceContants.TEXT_HEIGHT);
		txtB.setLocation(xPos, yPos);
		txtB.addFocusListener(focus);
		txtB.setToolTipText("青（Ｂ）を入力して下さい。");
		pnl.add(txtB);

		yPos += FaceContants.LABLE_HEIGHT + VERTICAL_SPACE + 10;
		F1Action f1Action = new F1Action("f1Action");
		xPos = 9;
		btnF1 = new ActionButton();
		btnF1.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF1.setText("<html><center><font size=-1>初期化</font></center><center><font size=-1>F1(E)</font></center>");
		btnF1.addActionListener(f1Action);
		btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), f1Action.getValue(Action.NAME));
		btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt E"), f1Action.getValue(Action.NAME));
		btnF1.setToolTipText("[F1]：選択された表示設定のでフォルトカラーを表示します。");
		btnF1.getActionMap().put("f1Action", f1Action);
		pnl.add(btnF1);

		F4Action f4Action = new F4Action("f4Action");
		xPos += X_BTN_WIDTH + 20;
		btnF4 = new ActionButton();
		btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF4.setText("<html><center><font size=-1>パレット</font></center><center><font size=-1>F4(S)</font></center>");
		btnF4.addActionListener(f4Action);
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), f4Action.getValue(Action.NAME));
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), f4Action.getValue(Action.NAME));
		btnF4.getActionMap().put("f4Action", f4Action);
		btnF4.setToolTipText("[F4]：カラーパレットを表示します。");
		pnl.add(btnF4);

		return pnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strInfor
	 */
	protected void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}

	/**
	 * @author PC13
	 * 
	 */
	class F4Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F4Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			btnF4.setEnabled(true);
			doF4();
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
	protected void doF4() {
		Color newColor = JColorChooser.showDialog(this, StringUtils.objectToStr(cbHyoujiSettei.getSelectedItem()), pnlColor.getBackground());
		if (newColor != null) {
			pnlColor.setBackground(newColor);
			pnlColor.repaint();
			txtR.setText(newColor.getRed() + "");
			txtG.setText(newColor.getGreen() + "");
			txtB.setText(newColor.getBlue() + "");
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class F1Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F1Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF1();
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
	protected void doF1() {
		ComboObjectVo cbObj = cbHyoujiSettei.getSelectObjValue();
		if (cbObj != null && cbHyoujiSettei.getSelectedIndex() > 0) {
			DClrCtlVo dColor = null;
			try {
				dColor = colorService.getDClrCtlVo(cbObj.getValue1().trim());
			} catch (BizException e) {
				logger.error(e.getMessage());
				MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			}

			if (dColor != null) {
				txtR.setText(dColor.getClrRed() + "");
				txtG.setText(dColor.getClrGreen() + "");
				txtB.setText(dColor.getClrBlue() + "");
				Color color = new Color(dColor.getClrRed(), dColor.getClrGreen(), dColor.getClrBlue());
				pnlColor.setBackground(color);
				pnlColor.repaint();
			}
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class ColorCombobox extends BaseComboBox {

		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public ColorCombobox(ComboBoxModel aModel) {
			super(aModel);
			init();
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		private void init() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComboBox#setSelectedIndex(int)
		 */
		public void setSelectedIndex(int anIndex) {
			super.setSelectedIndex(anIndex);
			ComboObjectVo obj = getSelectObjValue();
			if (obj != null && anIndex > 0) {
				clrVo = DBColorContants.getMClrCtlVo(ApplicationConstants.getLoginUser().getUserId() + obj.getValue1().trim());
				if (clrVo != null) {
					txtR.setText(clrVo.getClrRed() + "");
					txtG.setText(clrVo.getClrGreen() + "");
					txtB.setText(clrVo.getClrBlue() + "");
					Color color = new Color(clrVo.getClrRed(), clrVo.getClrGreen(), clrVo.getClrBlue());
					pnlColor.setBackground(color);
					pnlColor.repaint();
				}
			} else {
				clrVo = null;
				if (anIndex <= 0) {
					txtR.setText("");
					txtG.setText("");
					txtB.setText("");
					pnlColor.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
					pnlColor.repaint();
				}
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
		public void resetValue() {
			setSelectedIndex(0);
		}

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
		public ComboObjectVo getSelectObjValue() {
			int iValue = getSelectedIndex();
			if (dataModel instanceof ArrayListComboBoxModel) {
				ArrayListComboBoxModel aDataModel = (ArrayListComboBoxModel) dataModel;
				return (ComboObjectVo) aDataModel.getObjAt(iValue);
			} else {
				return null;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.pipe.jface.combo.InspectComboBox#getSelectedIndex()
		 */
		public int getSelectedIndex() {

			Object sObject = dataModel.getSelectedItem();
			int i;
			Object obj;
			String cStr = "";

			if (dataModel instanceof ArrayListComboBoxModel) {
				cStr = ((ArrayListComboBoxModel) dataModel).getSelectedItemKey();
			} else {
				if (sObject instanceof String) {
					cStr = (String) sObject;
				}
			}

			for (i = 0; i < dataModel.getSize(); i++) {
				obj = dataModel.getElementAt(i);
				String strI = StringUtils.objectToStr(obj);
				if (StringUtils.isEquals(cStr, strI)) {
					return i;
				}
			}
			return -1;
		}
	}

	public void stopTimerRemoveListener(JDialog dialog) {
		if (timer.isRunning()) {
			timer.stop();
			timer = null;
		}
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.removePropertyChangeListener(pro);
		dialog = null;
	}
}
