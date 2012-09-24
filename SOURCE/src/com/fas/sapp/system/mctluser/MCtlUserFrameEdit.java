/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名		：エスイーシー化成
 * 
 *     ファイル名		：ControlEdit.java
 *
 *     記述			：
 *     
 *     作成日			：Apr 10, 2010   
 *
 *     作成者			：Admin
 *
 *     備考			：
 *
 **************************************************************************************/

package com.fas.sapp.system.mctluser;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.ViewLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.textarea.BaseTextArea;
import com.fas.service.system.mctl.MCtlService;
import com.fas.service.system.mctl.MCtlServiceImpl;
import com.fas.vo.mctl.MCtlVo;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: Admin</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class MCtlUserFrameEdit extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private String strUserid;
	/** */
	private String strCKey;
	/** */
	private boolean bPermission;
	/** */
	private BaseInputText txtCKey;
	/** */
	private BaseInputText txtCName;
	/** */
	private JFormattedTextField txtCData;
	/** */
	private BaseInputText txtCBm;
	/** */
	private BaseInputText txtCAttr;
	/** */
	private BaseTextArea txtCHelp;
	/** */
	private BaseLabel lblPermission;
	/** */
	private EditLabel lblCData;
	/** */
	protected ActionButton btnF1;
	/** */
	protected ActionButton btnF2;
	/** */
	protected ActionButton btnF3;
	/** */
	protected ActionButton btnF4;
	/** */
	protected ActionButton btnF5;
	/** */
	protected ActionButton btnF6;
	/** */
	protected ActionButton btnF7;
	/** */
	protected ActionButton btnF8;
	/** */
	protected ActionButton btnF9;
	/** */
	protected ActionButton btnF10;
	/** */
	protected ActionButton btnF11;
	/** */
	protected ActionButton btnF12;
	/** */
	private static int itxtCDataX;
	private static int itxtCDataY;
	/** */
	private BasePanel pnlMain;
	/** Database service */
	private MCtlService ctlService;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	public MCtlUserFrameEdit() {

		super();
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public MCtlUserFrameEdit(BaseFrame owner, boolean modal) {
		super(owner, modal);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param owner
	 * @param modal
	 * @param strUserid
	 * @param strCKey
	 *            </DL>
	 */
	public MCtlUserFrameEdit(BaseFrame owner, boolean modal, String strUserid, String strCKey, boolean bPermission) {
		super(owner, modal);
		this.strUserid = strUserid;
		this.strCKey = strCKey;
		this.bPermission = bPermission;
		init();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	protected void init() {
		setTitle("コントロール情報");
		setStatus();
		doF11();

		// MouseAdapter mouseAdapter = new MouseAdapter() {
		// public void mouseEntered(MouseEvent e) {
		// getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// }
		//
		// public void mouseExited(MouseEvent e) {
		// getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// }
		// };
		// setMouseCurrsor(btnF8, mouseAdapter);
		// setMouseCurrsor(btnF11, mouseAdapter);
		// setMouseCurrsor(btnF12, mouseAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.InspectDialog#setContentPane(java.awt.Container)
	 */
	public void setContentPane(Container contentPane) {
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * get Value UPD_FLG from M_CTL if == 1 not EditAble show label if == 0 EditAble</DD>
	 * <BR>
	 * </DL>
	 */
	protected void setStatus() {
		txtCKey.setEditable(false);
		txtCName.setEditable(false);
		txtCBm.setEditable(false);
		txtCAttr.setEditable(false);
		txtCHelp.setEditable(false);
		try {
			// Load Data and Set Status;
			int iMtnFlg = 0;
			MCtlVo dataVo = ctlService.getByKey(strUserid, strCKey, iMtnFlg);

			if (dataVo != null) {
				// Check CData Type and set Type to Text box
				// txtCData = new BaseInputText();

				int iCBm = NumberUtils.getIntFromString(dataVo.getCBm());
				int iCDecbm = NumberUtils.getIntFromString(dataVo.getCDecbm());
				if (dataVo.getCAttr().equals("数字"))// isNumber
				{
					if (iCDecbm == 0) // is Integer
					{
						java.util.regex.Pattern noVowels = java.util.regex.Pattern.compile("[0-9-]*", java.util.regex.Pattern.CASE_INSENSITIVE);
						RegexPatternFormatter noVowelFormatter = new RegexPatternFormatter(noVowels);
						noVowelFormatter.setAllowsInvalid(false); // don't allow user to type
						// vowels
						txtCData = new JFormattedTextField(noVowelFormatter);
						txtCData.addKeyListener(new txtCDataKeyListener());
						txtCData.addFocusListener(new txtCDataFocusListener());
					} else// is Double
					{
						java.util.regex.Pattern noVowels = java.util.regex.Pattern.compile("[0-9.-]*", java.util.regex.Pattern.CASE_INSENSITIVE);
						RegexPatternFormatter noVowelFormatter = new RegexPatternFormatter(noVowels);
						noVowelFormatter.setAllowsInvalid(false); // don't allow user to type
						// vowels
						txtCData = new JFormattedTextField(noVowelFormatter);
						txtCData.addKeyListener(new txtCDataKeyListener());
						txtCData.addFocusListener(new txtCDataFocusListener());
					}
				} else // is Nvarchar
				{
					txtCData = new JFormattedTextField();
					txtCData.addKeyListener(new txtCDataKeyListener());
					txtCData.addFocusListener(new txtCDataFocusListener());
				}

				int TXT_TEXT_FIELD_LENGTH_180 = 180;
				int TXT_TEXT_FIELD_LENGTH_300 = 300;
				int TXT_TEXT_FIELD_LENGTH_560 = 560;
				int iCBmAndCDecbm = iCBm + iCDecbm;
				int iWIDTH = TXT_TEXT_FIELD_LENGTH_180;
				if (iCBmAndCDecbm <= 15)
					iWIDTH = TXT_TEXT_FIELD_LENGTH_180;
				if (15 < iCBmAndCDecbm && iCBmAndCDecbm <= 25)
					iWIDTH = TXT_TEXT_FIELD_LENGTH_300;
				if (25 < iCBmAndCDecbm)
					iWIDTH = TXT_TEXT_FIELD_LENGTH_560;
				txtCData.setSize(new Dimension(iWIDTH, FaceContants.LABLE_HEIGHT));

				// txtCData.setToolTipText(lblCData.getText() + "を入力して下さい。");
				txtCData.setLocation(itxtCDataX, itxtCDataY);
				pnlMain.add(txtCData);
				pnlMain.repaint();
			}
		} catch (BizException e1) {
			e1.printStackTrace();
		}

		// Paint length for txtCKey
		// Get dataVo and Set size by max length CBM and CDEBM
		try {
			// Load Data and Set Status;
			int iMtnFlg = 0;
			MCtlVo dataVo = ctlService.getByKey(strUserid, strCKey, iMtnFlg);

			if (dataVo != null) {
				// Check CData Type and set Type to Text box
				if (dataVo.getCAttr().equals("数字"))// isNumber
				{
					if (dataVo.getCDecbm() == "0") // is Integer
					{
						txtCData.setHorizontalAlignment(BaseInputText.RIGHT);
					} else// is Double
					{
						txtCData.setHorizontalAlignment(BaseInputText.RIGHT);
					}
				} else // is Nvarchar
				{
					txtCData.setHorizontalAlignment(BaseInputText.LEFT);
				}
				// SetLength For txtCData
				// int TXT_TEXT_FIELD_LENGTH_180 = 180;
				// int TXT_TEXT_FIELD_LENGTH_300 = 300;
				// int TXT_TEXT_FIELD_LENGTH_560 = 560;
				// int iCBm = NumberUtils.getIntFromString(dataVo.getCBm());
				// int iCDecbm = NumberUtils.getIntFromString(dataVo.getCDecbm());
				// int iCBmAndCDecbm = iCBm + iCDecbm;
				// int iWIDTH = TXT_TEXT_FIELD_LENGTH_180;
				// if( iCBmAndCDecbm <= 15)
				// iWIDTH = TXT_TEXT_FIELD_LENGTH_180;
				// if( 15< iCBmAndCDecbm && iCBmAndCDecbm <= 25)
				// iWIDTH = TXT_TEXT_FIELD_LENGTH_300;
				// if( 25 < iCBmAndCDecbm )
				// iWIDTH = TXT_TEXT_FIELD_LENGTH_560;
				// txtCData.setSize(new Dimension(iWIDTH , FaceContants.LABLE_HEIGHT));
				// if(iCDecbm == 0)
				// txtCData.setMaxLength(iCBm);
				// else
				// txtCData.setMaxLength(iCBmAndCDecbm +1);

				int iUdpFlg = NumberUtils.getIntFromString(dataVo.getUpdFlg());
				if (iUdpFlg == 0)// EditAble
				{
					// Not show lable UpdFlg
					lblPermission.setText("");
					txtCData.setEditable(true);
					btnF8.setEnabled(true);
				} else// Not EditAble
				{
					// Show label UpdFlg
					lblPermission.setText("更新不可");
					txtCData.setEditable(false);
					btnF8.setEnabled(false);
				}
			}
		} catch (BizException e1) {
			e1.printStackTrace();
		}

		if (bPermission == false) {
			// Show label UpdFlg
			lblPermission.setText("更新不可");
			txtCData.setEditable(false);
			btnF8.setEnabled(false);
		}
	}

	private void initMainPnl(BasePanel mainPnl) {

		// Create control here
		int X_BTN_WIDTH = 71;
		int X_BTN_SPACE = 4;
		// int yPos = (Y_FOOTER_LENGTH - Y_BTN_HEIGHT)/2;
		int yPos = 5;
		int xPos = (X_FOOTER_LENGTH - (X_BTN_WIDTH * 9 + X_BTN_SPACE * 8)) / 2;
		int I_LBL_LENGTH = 110;
		int TXT_TEXT_FIELD_LENGTH_180 = 180;
		int TXT_TEXT_FIELD_LENGTH_560 = 560;

		// 1. LBL TXT CKEY MaxLength 8 Width 180

		ViewLabel lblCKey = new ViewLabel("KEY:");
		lblCKey.setLocation(xPos, yPos);
		lblCKey.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblCKey.setHorizontalAlignment(EditLabel.RIGHT);
		mainPnl.add(lblCKey);

		txtCKey = new CdInputText("", 0, BaseInputText.IM_OFF, 8);
		// txtCKey.setToolTipText(lblCKey.getText() + "を入力して下さい。");
		txtCKey.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtCKey.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180, FaceContants.LABLE_HEIGHT));
		mainPnl.add(txtCKey);

		// LBL UDPFLG
		lblPermission = new BaseLabel("");
		lblPermission.setLocation(xPos + TXT_TEXT_FIELD_LENGTH_560, yPos);
		lblPermission.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblPermission.setForeground(Color.RED);
		lblPermission.setHorizontalAlignment(BaseLabel.RIGHT);
		lblPermission.setBorder(null);
		mainPnl.add(lblPermission);

		// 2. LBL TXT CNAME MaxLength 50 Width 500
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		ViewLabel lblCName = new ViewLabel("NAME:");
		lblCName.setLocation(xPos, yPos);
		lblCName.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblCName.setHorizontalAlignment(EditLabel.RIGHT);
		mainPnl.add(lblCName);

		txtCName = new BaseInputText("", 0, BaseInputText.IM_OFF, 50);
		// txtCName.setToolTipText(lblCName.getText() + "を入力して下さい。");
		txtCName.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtCName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_560, FaceContants.LABLE_HEIGHT));
		mainPnl.add(txtCName);

		// 3. LBL TXT CDATA MaxLength 50 Width 500
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblCData = new EditLabel("DATA:");
		lblCData.setLocation(xPos, yPos);
		lblCData.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblCData.setHorizontalAlignment(EditLabel.RIGHT);
		mainPnl.add(lblCData);

		itxtCDataX = xPos + I_LBL_LENGTH;
		itxtCDataY = yPos;
		// txtCData = new BaseInputText("", 0, BaseInputText.IM_OFF, 50);
		// txtCData.setToolTipText(lblCData.getText() + "を入力して下さい。");
		// txtCData.setLocation(xPos + I_LBL_LENGTH, yPos);
		// txtCData.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_560, FaceContants.LABLE_HEIGHT));
		// mainPnl.add(txtCData);

		// txtCData = new BaseInputText("", 0, BaseInputText.IM_OFF, 50);
		// txtCData.setToolTipText("");
		// txtCData.setLocation(xPos + I_LBL_LENGTH, yPos);
		// txtCData.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_560, FaceContants.LABLE_HEIGHT));
		// mainPnl.add(txtCData);

		// 4. LBL TXT CBM MaxLength 3 Width 120
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		ViewLabel lblCBm = new ViewLabel("入力桁数:");
		lblCBm.setLocation(xPos, yPos);
		lblCBm.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblCBm.setHorizontalAlignment(EditLabel.RIGHT);
		mainPnl.add(lblCBm);

		txtCBm = new BaseInputText("", 0, BaseInputText.IM_OFF, 3);
		// txtCBm.setToolTipText(lblCBm.getText() + "を入力して下さい。");
		txtCBm.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtCBm.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180, FaceContants.LABLE_HEIGHT));
		mainPnl.add(txtCBm);

		// 5. LBL TXT CATTR MaxLength 10 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		ViewLabel lblCAttr = new ViewLabel("入力属性:");
		lblCAttr.setLocation(xPos, yPos);
		lblCAttr.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblCAttr.setHorizontalAlignment(EditLabel.RIGHT);
		mainPnl.add(lblCAttr);

		txtCAttr = new BaseInputText("", 0, BaseInputText.IM_OFF, 10);
		// txtCAttr.setToolTipText(lblCAttr.getText() + "を入力して下さい。");
		txtCAttr.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtCAttr.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180, FaceContants.LABLE_HEIGHT));
		mainPnl.add(txtCAttr);

		// 6. LBL TXT HELP MaxLength 200 Width 500 Height *2
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		ViewLabel lblCHelp = new ViewLabel("HELP:");
		lblCHelp.setLocation(xPos, yPos);
		lblCHelp.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT * 2));
		lblCHelp.setHorizontalAlignment(EditLabel.RIGHT);
		mainPnl.add(lblCHelp);

		txtCHelp = new BaseTextArea(3, 1);
		txtCHelp.setLineWrap(true);
		txtCHelp.setBorder(new TitledBorder(""));
		// txtCHelp.setToolTipText(lblCHelp.getText() + "を入力して下さい。");
		txtCHelp.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtCHelp.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_560, FaceContants.LABLE_HEIGHT * 2));
		mainPnl.add(txtCHelp);

	}

	private void initFooterMainPnl(BasePanel footerMainPnl) {
		int X_BTN_WIDTH = 71;
		int Y_BTN_HEIGHT = 36;
		int X_BTN_SPACE = 4;
		int yPos = (Y_FOOTER_LENGTH - Y_BTN_HEIGHT) / 2;
		int xPos = (X_FOOTER_LENGTH - (X_BTN_WIDTH * 9 + X_BTN_SPACE * 8)) / 2;
		// int BTN_PANEL_HEIGHT = 45;
		// int BTN_PANEL_WIDTH = X_BODY_LENGTH;

		F1Action f1Action = new F1Action("f1Action");
		btnF1 = new ActionButton();
		btnF1.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF1.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF1.addActionListener(f1Action);
		btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), f1Action.getValue(Action.NAME));
		btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt E"), f1Action.getValue(Action.NAME));
		btnF1.getActionMap().put("f1Action", f1Action);
		btnF1.setEnabled(false);
		footerMainPnl.add(btnF1);

		F2Action f2Action = new F2Action("f2Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF2 = new ActionButton();
		btnF2.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF2.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF2.addActionListener(f2Action);
		btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt F"), f2Action.getValue(Action.NAME));
		btnF2.getActionMap().put("f2Action", f2Action);
		btnF2.setEnabled(false);
		footerMainPnl.add(btnF2);

		F4Action f4Action = new F4Action("f4Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF4 = new ActionButton();
		btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF4.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF4.addActionListener(f4Action);
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), f4Action.getValue(Action.NAME));
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), f4Action.getValue(Action.NAME));
		btnF4.getActionMap().put("f4Action", f4Action);
		btnF4.setEnabled(false);
		footerMainPnl.add(btnF4);

		F6Action f6Action = new F6Action("f6Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF6 = new ActionButton();
		btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF6.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF6.addActionListener(f6Action);
		btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), f6Action.getValue(Action.NAME));
		btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f6Action.getValue(Action.NAME));
		btnF6.getActionMap().put("f6Action", f6Action);
		btnF6.setEnabled(false);
		footerMainPnl.add(btnF6);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		F8Action f8Action = new F8Action("f8Action");
		btnF8 = new ActionButton();
		btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF8.setText("<html><center><font size=-1>登録</font></center><center><font size=-1>F8(A)</font></center>");
		btnF8.addActionListener(f8Action);
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
		btnF8.getActionMap().put("f8Action", f8Action);
		btnF8.setToolTipText("[F8]：データを登録します。");
		footerMainPnl.add(btnF8);

		F9Action f9Action = new F9Action("f9Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF9 = new ActionButton();
		btnF9.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF9.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF9.addActionListener(f9Action);
		btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), f9Action.getValue(Action.NAME));
		btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f9Action.getValue(Action.NAME));
		btnF9.getActionMap().put("f9Action", f9Action);
		btnF9.setEnabled(false);
		footerMainPnl.add(btnF9);

		F10Action f10Action = new F10Action("f10Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF10 = new ActionButton();
		btnF10.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF10.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF10.addActionListener(f10Action);
		btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), f10Action.getValue(Action.NAME));
		btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f10Action.getValue(Action.NAME));
		btnF10.getActionMap().put("f10Action", f10Action);
		btnF10.setEnabled(false);
		footerMainPnl.add(btnF10);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF11 = new ActionButton();
		F11Action f11Action = new F11Action("f11Action");
		btnF11.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF11.setText("<html><center><font size=-1>中止</font></center><font size=-1>F11(C)</font>");
		btnF11.addActionListener(f11Action);
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), f11Action.getValue(Action.NAME));
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt C"), f11Action.getValue(Action.NAME));
		btnF11.getActionMap().put("f11Action", f11Action);
		btnF11.setToolTipText("[F11]：処理を中止します。");
		footerMainPnl.add(btnF11);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF12 = new ActionButton();
		F12Action f12Action = new F12Action("f12Action");
		btnF12.addActionListener(f12Action);
		btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), f12Action.getValue(Action.NAME));
		btnF12.setToolTipText("[F12]：処理を終了します。");
		btnF12.getActionMap().put("f12Action", f12Action);
		footerMainPnl.add(btnF12);

		// BTN_PANEL_WIDTH = xPos + X_BTN_WIDTH + 3 * X_BTN_SPACE;
		// footerMainPnl.setSize(new Dimension(BTN_PANEL_WIDTH, BTN_PANEL_HEIGHT));
		// footerMainPnl.setBorder(BorderFactory.createTitledBorder(""));

	}

	@Override
	protected BasePanel getHeader() {
		return null;
	}

	@Override
	protected BasePanel getBodyPanel() {
		mainPnl = new BasePanel();
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		mainPnl.setLocation(0, 0);

		pnlMain = new BasePanel();
		pnlMain.setSize(X_BODY_LENGTH - 2, Y_BODY_LENGTH - 2);
		pnlMain.setLocation(1, 1);
		pnlMain.setBorder(FaceContants.TITLE_BORDER);
		initMainPnl(pnlMain);

		mainPnl.add(pnlMain);

		return mainPnl;
	}

	@Override
	protected BasePanel getFooter() {
		footerMainPnl = new BasePanel();
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH, Y_FOOTER_LENGTH));
		footerMainPnl.setLocation(0, Y_BODY_LENGTH);

		BasePanel pnlButton = new BasePanel();
		pnlButton.setSize(X_FOOTER_LENGTH - 2, Y_FOOTER_LENGTH - 2);
		pnlButton.setLocation(1, 1);
		pnlButton.setBorder(FaceContants.TITLE_BORDER);
		initFooterMainPnl(pnlButton);

		footerMainPnl.add(pnlButton);

		return footerMainPnl;
	}

	@Override
	protected JSeparator getFooterSeparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setFrameSize() {

		ctlService = new MCtlServiceImpl();

		X_FRAME_LENGTH = 680;
		Y_FRAME_LENGTH = 230;

		X_HEADER_LENGTH = 0;
		Y_HEADER_LENGTH = 0;

		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = 185;

		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 45;
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
	 * <DD>最初</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF1() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F2Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F2Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF2();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>上へ</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF2() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F3Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F3Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF3();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>下へ</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF3() {

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
	 * <DD>最後</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF4() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F5Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F5Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF5();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>追加</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF5() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F6Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F6Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF6();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>削除</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF6() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F7Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F7Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF7();
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
	protected void doF7() {

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
		try {
			if (validateData() == true) {
				if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0002")) == MessageBox.YES) {
					// Update Check Exist in DB
					int iMtnFlg = 0;
					MCtlVo objCtl = ctlService.getByKey(strUserid, strCKey, iMtnFlg);
					if (objCtl == null) {
						MessageBox.message(this, MessageConstants.getMstMsgVo("E0003"));
						return;
					}
					// Update data
					// Save Data
					MCtlVo dataVo = convertTxt2Vo();
					ctlService.updateVo(dataVo);

					// Reload data Control for system
					MCtlService ctlService = new MCtlServiceImpl();
					MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());

					doF12();
				} else {
					// Set Default First Focus
					if (txtCData.isEnabled() == true) {
						setDefaultFirstFocus(txtCData);
					}
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class F9Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F9Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF9();
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
	protected void doF9() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F10Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F10Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF10();
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
	protected void doF10() {

	}

	/**
	 * @author PC13
	 * 
	 */
	class F11Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F11Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF11();
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
	protected void doF11() {
		try {
			// Load Data and Set Status;
			int iMtnFlg = 0;
			MCtlVo dataVo = ctlService.getByKey(strUserid, strCKey, iMtnFlg);
			if (dataVo != null) {
				// Load Data to txt
				convertVo2Txt(dataVo);
			}
		} catch (BizException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class F12Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F12Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF12();
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
	protected void doF12() {
		// if (isHenkouChuu) {
		// if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
		// dispose();
		// }
		// } else {
		// dispose();
		// }
		dispose();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * Convert from control to dataVo</DD> <BR>
	 * 
	 * @return </DL>
	 */
	private MCtlVo convertTxt2Vo() {
		MCtlVo dataVo = new MCtlVo();
		dataVo.setUserid(strUserid);
		dataVo.setCKey(txtCKey.getText().trim());
		// dataVo.setCName(txtCName.getText().trim());
		dataVo.setCData(txtCData.getText().trim());
		// dataVo.setCBm( txtCBm.getText().trim() );
		// dataVo.setCAttr(txtCAttr.getText().trim());
		// dataVo.setCHelp(txtCHelp.getText().trim());

		return dataVo;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * Convert from dataVo to control</DD> <BR>
	 * 
	 * @param dataVo
	 *            </DL>
	 */
	private void convertVo2Txt(MCtlVo dataVo) {
		txtCKey.setText(dataVo.getCKey());
		txtCName.setText(dataVo.getCName());
		txtCData.setText(dataVo.getCData());
		// isNvarchar
		if (!dataVo.getCAttr().equals("数字")) {
			txtCBm.setText(dataVo.getCBm() + "桁");
		}
		// isInteger
		if (dataVo.getCAttr().equals("数字") && NumberUtils.getIntFromString(dataVo.getCDecbm()) <= 0) {
			txtCBm.setText("整数" + dataVo.getCBm() + "桁");
		}
		// isDouble
		if (dataVo.getCAttr().equals("数字") && NumberUtils.getIntFromString(dataVo.getCDecbm()) > 0) {
			txtCBm.setText("整数" + dataVo.getCBm() + "桁" + "　少数" + dataVo.getCDecbm() + "桁");
		}
		txtCAttr.setText(dataVo.getCAttr());
		txtCHelp.setText(dataVo.getCHelp());
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * Validate data</DD> <BR>
	 * 
	 * @return </DL>
	 */
	private boolean validateData() {
		boolean isValid = true;
		JComponent comp = null;
		MCtlVo dataVo = new MCtlVo();
		try {
			int iMtnFlg = 0;
			dataVo = ctlService.getByKey(strUserid, strCKey, iMtnFlg);
		} catch (BizException e1) {
			e1.printStackTrace();
			isValid = false;
		}
		// Valid maxlength from C_BM txtCData E0129
		String strCAttr = txtCAttr.getText().trim();
		if (strCAttr.equals("文字"))// Is varchar
		{
			int iMaxLength = NumberUtils.getIntFromString(dataVo.getCBm());
			if (isValid && !StringUtils.checkLenString(txtCData.getText(), iMaxLength)) {
				isValid = false;
				comp = txtCData;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblCData.getText()));
			}
		}
		if (strCAttr.equals("数字"))// Is number
		{
			// Check is number == true
			if (isValid && !StringUtils.isDouble(txtCData.getText())) {
				isValid = false;
				comp = txtCData;
				// MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
			}

			int iMaxLengthInteger = NumberUtils.getIntFromString(dataVo.getCBm());
			int iMaxLengthFloat = NumberUtils.getIntFromString(dataVo.getCDecbm());
			if (iMaxLengthFloat == 0) // Is interger
			{
				String strNumberInteger = txtCData.getText();
				if (strNumberInteger.indexOf('-') != -1)// Exist - character
				{
					strNumberInteger = strNumberInteger.substring(0, strNumberInteger.length() - 1);
				}
				// Check length Integer
				if (isValid && !StringUtils.checkLenString(strNumberInteger, iMaxLengthInteger)) {
					isValid = false;
					comp = txtCData;
					MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblCData.getText()));
				}
			}
			if (iMaxLengthFloat > 0) // Is Float
			{
				int iIndexDot = txtCData.getText().indexOf('.');
				String strNumberInteger = "";
				String strNumberFloat = "";
				if (iIndexDot <= 0) {
					strNumberInteger = txtCData.getText();
					strNumberFloat = "";
				} else if (iIndexDot == txtCData.getText().length() - 1) {
					txtCData.setText(txtCData.getText().substring(0, txtCData.getText().length() - 1));
					strNumberInteger = txtCData.getText();
					strNumberFloat = "";
				} else {
					strNumberInteger = txtCData.getText().substring(0, iIndexDot);
					strNumberFloat = txtCData.getText().substring(iIndexDot + 1, txtCData.getText().length());
				}

				if (strNumberInteger.indexOf('-') != -1)// Exist - character
				{
					strNumberInteger = strNumberInteger.substring(0, strNumberInteger.length() - 1);
					strNumberFloat = "";
				}
				// Check length Integer
				if (isValid && !StringUtils.checkLenString(strNumberInteger, iMaxLengthInteger)) {
					isValid = false;
					comp = txtCData;
					MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblCData.getText()));
				}
				// Check length Float
				if (isValid && !StringUtils.checkLenString(strNumberFloat, iMaxLengthFloat)) {
					isValid = false;
					comp = txtCData;
					MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblCData.getText()));
				}
			}

			// Set Default First Focus
			if (comp != null) {
				setDefaultFirstFocus(comp);
			}
		}
		return isValid;
	}

	// private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
	// if (btn != null) {
	// btn.addMouseListener(mouseAd);
	// }
	// }

	protected void setDefaultFirstFocus(final JComponent com) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (com.isEnabled()) {
					com.requestFocusInWindow();
				}
			}
		});
	}

	public class txtCDataFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e) {
			if (txtCData.isEditable() == true)
				txtCData.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
			else
				txtCData.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
		 */
		public void focusGained(FocusEvent e) {
			if (txtCData.isEditable() == true) {
				txtCData.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
				txtCData.selectAll();
			} else {
				txtCData.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
			}
		}
	}

	private static boolean bAllowChange = true;
	private static String strOldTxt = "";

	public class txtCDataKeyListener extends KeyAdapter {

		/** Handle the key-pressed event from the text field. */
		public void keyPressed(KeyEvent ke) {

			bAllowChange = true;
			// strOldTxt = txtCData.getText();
			char i = ke.getKeyChar();
			String str = Character.toString(i);
			int iIndexOfKey = txtCData.getText().lastIndexOf(str);
			String strCData = txtCData.getText();

			switch (i) {
			case '.':
				if (iIndexOfKey == 0 || iIndexOfKey == strCData.length()) {
					strOldTxt = strCData;
					bAllowChange = false;
					// ke.consume();
					// txtCData.setText( txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey+1,
					// txtCData.getText().length()));

				} else {
					char[] arrC = strCData.toCharArray();
					for (int a = 0; a < arrC.length; a++) {
						if (arrC[a] == i) {
							strOldTxt = strCData;
							bAllowChange = false;
							// ke.consume();
							// txtCData.setText( txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey+1,
							// txtCData.getText().length()));
							break;
						}
					}
				}
				break;
			default:
				break;
			}

		}

		/** Handle the key-released event from the text field. */
		public void keyReleased(KeyEvent ke) {
			char i = ke.getKeyChar();
			String str = Character.toString(i);
			int iIndexOfKey = txtCData.getText().lastIndexOf(str);

			if (bAllowChange == false) {
				txtCData.setText(strOldTxt);
				// ke.consume();
				return;
			}

			String strCData = txtCData.getText();

			switch (i) {
			case '-':
				if (iIndexOfKey != 0 || strCData.split(str).length > 2) {

					txtCData.setText(txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey + 1, txtCData.getText().length()));
					// ke.consume();
				}
				break;
			case '.':
				if (iIndexOfKey == 0) {
					txtCData.setText(txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey + 1, txtCData.getText().length()));
					// ke.consume();
				}
				break;
			default:
				break;
			}

		}

		/** Handle the key typed event from the text field. */
		public void keyTyped(KeyEvent ke) {
			// char i = ke.getKeyChar();
			// String str = Character.toString(i);
			//
			// String strCData = txtCData.getText();
			// int iIndexOfKey = ke.get
			// //Check Range Data input
			// if( !str.equals("0") && !str.equals("1") && !str.equals("2") && !str.equals("3") && !str.equals("4") &&
			// !str.equals("5") && !str.equals("6") && !str.equals("7") && !str.equals("8") && !str.equals("9") &&
			// !str.equals("-") && !str.equals("."))
			// {
			// txtCData.setText( txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey+1, txtCData.getText().length()));
			// }
			//
			// //Check if input '-' character
			// if( str.equals("-") && txtCData.getText() != null && ( iIndexOfKey != 1 || txtCData.getText().indexOf("-") != 0 ))
			// {
			// txtCData.setText( txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey+1, txtCData.getText().length()));
			// }
			// //Check if input '.' character
			// if( str.equals(".") && (txtCData.getText().indexOf(".") != -1
			// || iIndexOfKey == 0
			// || iIndexOfKey == txtCData.getText().length()) )
			// {
			// txtCData.setText( txtCData.getText().substring(0, iIndexOfKey) + txtCData.getText().substring(iIndexOfKey+1, txtCData.getText().length()));
			// }
			// }

			//
			// switch (i) {
			// case '-':
			// if(iIndexOfKey != 1 || strCData.split(str).length > 1)
			// {
			// ke.consume();
			// }
			// break;
			// case '.':
			//
			// break;
			// default:
			// break;
			// }
			//
		}

	}

	class RegexPatternFormatter extends DefaultFormatter {

		/** */
		private static final long serialVersionUID = 1L;
		protected java.util.regex.Matcher matcher;

		public RegexPatternFormatter(java.util.regex.Pattern regex) {
			setOverwriteMode(false);
			matcher = regex.matcher(""); // create a Matcher for the regular
			// expression
		}

		public Object stringToValue(String string) throws java.text.ParseException {
			if (string == null)
				return null;
			matcher.reset(string); // set 'string' as the matcher's input

			if (!matcher.matches()) // Does 'string' match the regular expression?
				throw new java.text.ParseException("does not match regex", 0);

			// If we get this far, then it did match.
			return super.stringToValue(string); // will honor the 'valueClass'

		}
	}
}
