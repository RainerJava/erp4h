/***************************************************************     
 *
 *     プロジェクト名		：
 * 
 *     ファイル名			：EmpMasterFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/04/02 14h11  
 *
 *     作成者			：HUNGNV
 *
 *     備考				：
 *
 ****************************************************************/
package com.fas.sapp.system.memp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.EditConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.MessageBox;
import com.fas.jface.bussiness.BaseMasterLayoutFrame;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.RequiredLabel;
import com.fas.jface.label.ViewLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableModel;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.BaseKanaInputText;
import com.fas.jface.text.CdInputSearchText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.text.IntegerNumberText;
import com.fas.jface.text.LatinInputText;
import com.fas.jface.text.PasswordInputText;
import com.fas.jface.utils.SwingUtils;
import com.fas.sapp.search.SearchMEmpFrame;
import com.fas.service.common.color.ColorService;
import com.fas.service.common.color.ColorServiceImpl;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.system.flog.FLogService;
import com.fas.service.system.flog.FLogServiceImpl;
import com.fas.service.system.mctl.MCtlService;
import com.fas.service.system.mctl.MCtlServiceImpl;
import com.fas.service.system.memp.MEmpService;
import com.fas.service.system.memp.MEmpServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.memp.MEmpVo;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.search.SearchVo;

/**
 * @author HUNGNV
 * 
 */

public class MEmpFrame extends BaseMasterLayoutFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** Log */
	static Logger logger = Logger.getLogger(MEmpFrame.class);

	/** DEFINE CONTROL */
	/** */
	private CdInputSearchText txtEmpCode;
	/** */
	private BaseKanaInputText txtEmpName;
	/** */
	private BaseInputText txtEmpKana;
	/** */
	private BaseInputText txtEmpTname;
	/** */
	private BaseComboBox cbbGroup;
	/** */
	private CdInputText txtEmpUser;
	/** */
	private PasswordInputText pwdPwd;
	/** */
	private IntegerNumberText txtDisplayOrder;
	/** */
	private BaseCheckBox chkLogviewFlg;
	/** */
	private BaseCheckBox chkExcelout;
	/** */
	private LatinInputText txtTelno;
	/** */
	private LatinInputText txtMailadr;
	/** */
	private LatinInputText txtMailadrm;
	/** */
	private BaseInputText txtPcid;
	/** */
	private InspectRadioButton rdInoutFlgType0;
	/** */
	private InspectRadioButton rdInoutFlgType1;
	/** */
	private CdInputText txtOldCode;
	/** */
	private BaseCheckBox chkUseFlg;
	/** */

	private EditLabel lblEmpCode;
	/** */
	private EditLabel lblEmpName;
	/** */
	private EditLabel lblEmpKana;
	/** */
	private EditLabel lblEmpTname;
	/** */
	private EditLabel lblGroup;
	/** */
	private EditLabel lblEmpUser;
	/** */
	private EditLabel lblPwd;
	/** */
	private EditLabel lblDisplayOrder;
	/** */
	private EditLabel lblLogviewFlg;
	/** */
	private EditLabel lblExcelout;
	/** */
	private EditLabel lblTelno;
	/** */
	private EditLabel lblMailadr;
	/** */
	private EditLabel lblMailadrm;
	/** */
	private EditLabel lblPcid;
	/** */
	private EditLabel lblInoutFlg;
	/** */
	private EditLabel lblOldCode;
	/** */
	private EditLabel lblUseFlg;
	/** */

	private BaseLabel lblStatus;
	/** */
	private BaseLabel lblNumber;
	/** */
	private BaseLabel lblLstCd;
	/** */
	private BaseLabel lblPermission;
	/** */
	private BaseLabel lblRegisterUser;
	/** */
	private BaseLabel lblRegisterDate;
	/** */
	private BaseLabel lblEditUser;
	/** */
	private BaseLabel lblEditDate;
	/** Define Table */
	private InspectTable m_table;
	/** */
	private InspectTableModel m_model;
	/** */
	private BaseScrollPane psTable;
	/** */
	private BasePanel infoPnl;
	/** */
	private int iCount = 0;
	
	private MEmpVo oldVo;
	/** Define List object */
	protected List<MEmpVo> lstData;

	/** テーブルヘッダーサイズ */
	private static String colHeadNm[] = { "コード", "担当者名", "可否" };
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private int[] colHeadwidth = { 80, 200, 60 };
	/** Row per page */
	private int ROW_PER_PAGE;
	/** Database service */
	private MEmpService mEmpService;
	private MCtlService mCtlService;
	private ColorService colorService;
	private FLogService fLogService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#getHelpInfor()
	 */
	@Override
	protected String getHelpInfor() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#getSubName()
	 */
	@Override
	protected String getSubName() {
		if (exeMenu != null) {
			return StringUtils.emptyIfNull(exeMenu.getMenuexeName());
		} else {
			return "";
		}
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD>Init Function</DD> <BR>
	 * 
	 * @param frame
	 * @param title
	 *            </DL>
	 */
	public MEmpFrame(BaseFrame frame, String title) {
		super(frame, title);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD>Init Function</DD> <BR>
	 * 
	 * @param frame
	 *            </DL>
	 */
	public MEmpFrame(BaseFrame frame) {
		super(frame);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	public MEmpFrame() {
		super();
		init();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	private void init() {
		mEmpService = new MEmpServiceImpl();
		fLogService = new FLogServiceImpl();
		getHeader();
		getBodyLeft();
		getBodyCenter();
		buildMiddle();
    	setFrameSize();
		CUR_MODE = EditConstants.VIEW_MODE;
		setDispTabFocus();
		setStatus(CUR_MODE);
		txtEmpCode.setBtnSearch(btnF4);
		// 0:初期表示しない 1:初期表示する
		if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INTD_EMP").trim())) {
			doF10();
		}
		btnF4.addFocusListener(new FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent arg0) {
				btnF4.setEnabled(false);
			}
		});
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	protected void setStatus(String mode) {
		if (EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			txtEmpCode.setEditable(false);
			txtEmpName.setEditable(true);
			txtEmpKana.setEditable(true);
			txtEmpTname.setEditable(true);
			cbbGroup.setEnabled(true);
			txtEmpUser.setEditable(true);
			pwdPwd.setEditable(true);
			txtDisplayOrder.setEditable(true);
			chkLogviewFlg.setEnabled(true);
			chkExcelout.setEnabled(true);
			txtTelno.setEditable(true);
			txtMailadr.setEditable(true);
			txtMailadrm.setEditable(true);
			txtPcid.setEditable(true);
			rdInoutFlgType0.setEnabled(true);
			rdInoutFlgType1.setEnabled(true);
			txtOldCode.setEditable(true);
			chkUseFlg.setEnabled(true);

			setDispTabFocus();
			setDefaultFirstFocus(txtEmpName);
			
			setScreenMode(CUR_MODE);
		} else if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)) {
			txtEmpCode.setEditable(false);
			txtEmpName.setEditable(true);
			txtEmpKana.setEditable(true);
			txtEmpTname.setEditable(true);
			cbbGroup.setEnabled(true);
			txtEmpUser.setEditable(true);
			pwdPwd.setEditable(true);
			txtDisplayOrder.setEditable(true);
			chkLogviewFlg.setEnabled(true);
			chkExcelout.setEnabled(true);
			txtTelno.setEditable(true);
			txtMailadr.setEditable(true);
			txtMailadrm.setEditable(true);
			txtPcid.setEditable(true);
			rdInoutFlgType0.setEnabled(true);
			rdInoutFlgType1.setEnabled(true);
			txtOldCode.setEditable(true);
			chkUseFlg.setEnabled(true);

			setDispTabFocus();
			setDefaultFirstFocus(txtEmpName);

			setScreenMode(CUR_MODE);
		} else {// VIEWMODE
			txtEmpCode.setEditable(true);
			txtEmpName.setEditable(false);
			txtEmpKana.setEditable(false);
			txtEmpTname.setEditable(false);
			cbbGroup.setEnabled(false);
			txtEmpUser.setEditable(false);
			pwdPwd.setEditable(false);
			txtDisplayOrder.setEditable(false);
			chkLogviewFlg.setEnabled(false);
			chkExcelout.setEnabled(false);
			txtTelno.setEditable(false);
			txtMailadr.setEditable(false);
			txtMailadrm.setEditable(false);
			txtPcid.setEditable(false);
			rdInoutFlgType0.setEnabled(false);
			rdInoutFlgType1.setEnabled(false);
			txtOldCode.setEditable(false);
			chkUseFlg.setEnabled(false);

			setDispTabFocus();
			setDefaultFirstFocus(txtEmpCode);

			setScreenMode(CUR_MODE);
		}

		isRequireResetTabDispFocus = true;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	protected void setDispTabFocus() {

		List<Object> focusList = new ArrayList<Object>();

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)
				|| EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(txtEmpName);
			focusList.add(txtEmpKana);
			focusList.add(txtEmpTname);
			focusList.add(cbbGroup);
			focusList.add(txtEmpUser);
			focusList.add(pwdPwd);
			focusList.add(txtDisplayOrder);
			focusList.add(chkLogviewFlg);
			focusList.add(chkExcelout);
			focusList.add(txtTelno);
			focusList.add(txtMailadr);
			focusList.add(txtMailadrm);
			focusList.add(txtPcid);
			focusList.add(rdInoutFlgType0);
			focusList.add(rdInoutFlgType1);
			focusList.add(txtOldCode);
			focusList.add(chkUseFlg);
		} else {
			// COPY_MODE
			focusList.add(txtEmpCode);
		}
		focusList.add(m_table);

		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * SetScreenMode check user's permission if user have not permission Insert,
	 * Update, Delete F2(Copy), F6(Delete), F8(Save) not Enable</DD> <BR>
	 * 
	 * @param mode
	 *            </DL>
	 */
	private void setScreenMode(String mode) {
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_EDIT_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);

			// Check permission to enable F2 F6 F8
			if (PermissionPolicy.checkMnExePermission(
					PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
							.getLoginUser().getUserUser(), exeMenu)) {
				btnF2.setEnabled(true);
				btnF2.setAffterDoDisable(true);
				btnF6.setEnabled(true);
				btnF6.setAffterDoDisable(true);
				btnF8.setEnabled(true);
				btnF8.setAffterDoDisable(true);
			}

			btnF10.setEnabled(false);
			btnF12.setConfirmRerquired(true);
			m_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlCenter.setAlpha(0.5f);

		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus
					.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);

			btnF2.setEnabled(false);
			btnF6.setEnabled(false);
			// Check permission to enable F8
			if (PermissionPolicy.checkMnExePermission(
					PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
							.getLoginUser().getUserUser(), exeMenu)) {
				btnF8.setEnabled(true);
			}

			btnF10.setEnabled(false);
			btnF12.setConfirmRerquired(true);

			m_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlCenter.setAlpha(0.5f);
			// //Reset txt not reset EmpCode
			// resetDataNotResetCode();
		} else if (EditConstants.COPY_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_COPY_MODE);
			// Background when copy is green == background register
			// lblStatus.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
			lblStatus
					.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);

			btnF2.setEnabled(false);
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);

			btnF10.setEnabled(false);
			btnF12.setConfirmRerquired(true);

			m_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlCenter.setAlpha(0.5f);
		} else {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF2.setEnabled(false);
			btnF6.setEnabled(false);
			btnF6.setAffterDoDisable(true);
			btnF8.setEnabled(false);

			btnF10.setEnabled(true);

			btnF12.setConfirmRerquired(false);

			m_table.setEnabled(true);
			btnFirst.setEnabled(true);
			btnNext.setEnabled(true);
			btnPre.setEnabled(true);
			btnLast.setEnabled(true);
			pnlCenter.setAlpha(1f);
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>Get Number of Element and MaxCode from DB</DD>
	 * <BR>
	 * </DL>
	 */
	private void setTitleInfor() {
		try {
			lblNumber.setText("");
			lblLstCd.setText("");
			lblNumber.setText(mEmpService.getVoCount() + "件");
			lblLstCd.setText(mEmpService.getLatestCode());
		} catch (BizException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	private boolean validateData() {

		boolean isValid = true;
		JComponent comp = null;

		// Valid * 6 txtEmpCode
		if (!StringUtils.isValid(txtEmpCode.getText())) {
			isValid = false;
			comp = txtEmpCode;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(StringUtils.trimAllVN( lblEmpCode.getText())));
		}

		String strLenEmpCode = MCtlConstants.getValueByCKey("BM_EMP");
		int iLenEmpCode = StringUtils.isValid(strLenEmpCode) ? NumberUtils.getIntFromString(strLenEmpCode) : 6;

		if (isValid && !StringUtils.checkLenString(txtEmpCode.getText(), iLenEmpCode)) {
			isValid = false;
			comp = txtEmpCode;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(StringUtils.trimAllVN( lblEmpCode.getText())));
		}

		// Valid * 24 txtEmpName
		if (isValid && !StringUtils.isValid(txtEmpName.getText())) {
			isValid = false;
			comp = txtEmpName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(StringUtils.trimAllVN( lblEmpName.getText())));
		}

		if (isValid && !StringUtils.checkLenString(txtEmpName.getText(), 24)) {
			isValid = false;
			comp = txtEmpName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(StringUtils.trimAllVN( lblEmpName.getText())));
		}

		// Valid * 20 txtEmpKana
		if (isValid && !StringUtils.isValid(txtEmpKana.getText())) {
			isValid = false;
			comp = txtEmpKana;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(StringUtils.trimAllVN( lblEmpKana.getText())));
		}

		if (isValid && !StringUtils.checkLenString(txtEmpKana.getText(), 20)) {
			isValid = false;
			comp = txtEmpKana;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblEmpKana.getText())));
		}

		// Valid * 12 txtEmpTname
		if (isValid && !StringUtils.isValid(txtEmpTname.getText())) {
			isValid = false;
			comp = txtEmpTname;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor(StringUtils.trimAllVN( lblEmpTname.getText())));
		}

		if (isValid && !StringUtils.checkLenString(txtEmpTname.getText(), 12)) {
			isValid = false;
			comp = txtEmpTname;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblEmpTname.getText())));
		}

		// Valid * 10 cbbGroup
		if (isValid && cbbGroup.getSelectedIndex() <= 0) {
			isValid = false;
			comp = cbbGroup;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor(StringUtils.trimAllVN( lblGroup.getText())));
		}

		// Valid * 10 txtEmpUser isExist
		if (isValid && !StringUtils.isValid(txtEmpUser.getText())) {
			isValid = false;
			comp = txtEmpUser;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor(StringUtils.trimAllVN( lblEmpUser.getText())));
		}

		if (isValid && !StringUtils.checkLenString(txtEmpUser.getText(), 10)) {
			isValid = false;
			comp = txtEmpUser;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblEmpUser.getText())));
		}

		int iCountUser = 0;
		try {
			iCountUser = mEmpService.getVoCountByUserEmpUser(txtEmpUser.getText());
			// If is Edit, not check user of current ID
			if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE))
			{
				MEmpVo dataVo = mEmpService.getByKey(txtEmpCode.getText());
				if(dataVo == null){
					iCountUser = -1;
				}else if (dataVo.getEmpUser().toLowerCase().equals(txtEmpUser.getText().toLowerCase())) {
					iCountUser = 0;
				}
			}
		} catch (BizException ex) {
			ex.printStackTrace();
		}
		
		if (isValid && iCountUser == -1) {
			isValid = false;
			comp = txtEmpUser;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0003").setExtraInfor(StringUtils.trimAllVN( lblEmpUser.getText())));
		}
		
		if (isValid && iCountUser > 0) {
			isValid = false;
			comp = txtEmpUser;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0012").setExtraInfor(StringUtils.trimAllVN( lblEmpUser.getText())));
		}

		// Valid * 16 pwdPwd
		if (isValid && !StringUtils.isValid(pwdPwd.getText())) {
			isValid = false;
			comp = pwdPwd;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor(StringUtils.trimAllVN( lblPwd.getText())));
		}

		if (isValid && !StringUtils.checkLenString(pwdPwd.getText(), 16)) {
			isValid = false;
			comp = pwdPwd;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblPwd.getText())));
		}

		// Valid * 6 displayOrderNo
		if (isValid && !StringUtils.isValid(txtDisplayOrder.getText())) {
			isValid = false;
			comp = txtDisplayOrder;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor(StringUtils.trimAllVN( lblDisplayOrder.getText())));
		}
		
		if(isValid){
			int dspOrder = txtDisplayOrder.getValue();
			if (dspOrder < 0 || dspOrder > 999999) {
				isValid = false;
				comp = txtDisplayOrder;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(StringUtils.trimAllVN(lblDisplayOrder.getText())));
			}
		}	

		// Valid 17 txtTelno
		if (isValid && !StringUtils.checkLenString(txtTelno.getText(), 17)) {
			isValid = false;
			comp = txtTelno;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblTelno.getText())));
		}

		// Valid 50 txtMailadr
		if (isValid && !StringUtils.checkLenString(txtMailadr.getText(), 50)) {
			isValid = false;
			comp = txtMailadr;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblMailadr.getText())));
		}
		
		// Valid Mail Syntax txtMailadr
		if (isValid && StringUtils.isValid(txtMailadr.getText())) 
		{
			if( StringUtils.isEmail(txtMailadr.getText()) == false)
			{
				isValid = false;
				comp = txtMailadr;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0227").setExtraInfor(StringUtils.trimAllVN( lblMailadr.getText())));
			}
		}

		// Valid 50 txtMailadrm
		if (isValid && !StringUtils.checkLenString(txtMailadrm.getText(), 50)) {
			isValid = false;
			comp = txtMailadrm;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblMailadrm.getText())));
		}
		
		// Valid Mail Syntax txtMailadr
		if (isValid && StringUtils.isValid(txtMailadrm.getText())) 
		{
			if( StringUtils.isEmail(txtMailadrm.getText()) == false)
			{
				isValid = false;
				comp = txtMailadrm;
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0227").setExtraInfor(StringUtils.trimAllVN( lblMailadrm.getText())));
			}
		}

		// Valid 30 txtPcid
		if (isValid && !StringUtils.checkLenString(txtPcid.getText(), 30)) {
			isValid = false;
			comp = txtPcid;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblPcid.getText())));
		}

		// Valid 20 txtOldCode
		if (isValid && !StringUtils.checkLenString(txtOldCode.getText(), 20)) {
			isValid = false;
			comp = txtOldCode;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor(StringUtils.trimAllVN( lblOldCode.getText())));
		}
		// Set Default First Focus
		if (comp != null) {
			setDefaultFirstFocus(comp);
		}

		return isValid;
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * <DT>変更歴史：</DT>
	 * <DD>著作者: Bui Ngoc Viet</DD><BR>
	 * <DD></DD>
	 * </DL>
	 */
	private class TableMouseClick implements MouseListener {

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			int selectedRow = m_table.getSelectedRow();

			if (m_table.getRowCount() < 1)
				return;

			if (e.getClickCount() == 1) {
				doSingleClick(selectedRow);
			} else {
				doDoubleClick(selectedRow);
			}
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}
	}

	/**
	 * Lay du lieu tu Vo, dua vao cac control tren man hinh
	 *
	 *
	 *
	 */
	private void convertVo2Txt(MEmpVo dataVo) {
		
		if(dataVo != null){
			oldVo = dataVo;
			NumberFormat nf = NumberFormat.getInstance();
	
			txtEmpCode.setText(dataVo.getEmpCode());
			txtEmpName.setText(dataVo.getEmpName());
			txtEmpKana.setText(dataVo.getEmpKana());
			txtEmpTname.setText(dataVo.getEmpTname());
			txtDisplayOrder.setText(nf.format(dataVo.getDsporderNo()));
	
			cbbGroup.getModel().setSelectedItem(dataVo.getUserUser());
			cbbGroup.repaint();
	
			txtEmpUser.setText(dataVo.getEmpUser());
			pwdPwd.setText(dataVo.getPwd());
			if ("1".equalsIgnoreCase(dataVo.getLogviewFlg())) {
				chkLogviewFlg.setSelected(true);
				chkLogviewFlg.setText(NameConstants.getName("LOGVIEW" + "1"));
			} else {
				chkLogviewFlg.setSelected(false);
				chkLogviewFlg.setText(NameConstants.getName("LOGVIEW" + "0"));
			}
	
			if ("1".equalsIgnoreCase(dataVo.getExcelout())) {
				chkExcelout.setSelected(true);
				chkExcelout.setText(NameConstants.getName("EXCELOUT" + "1"));
			} else {
				chkExcelout.setSelected(false);
				chkExcelout.setText(NameConstants.getName("EXCELOUT" + "0"));
			}
	
			txtTelno.setText(dataVo.getTelno());
			txtMailadr.setText(dataVo.getMailadr());
			txtMailadrm.setText(dataVo.getMailadrm());
			txtPcid.setText(dataVo.getPcid());
	
			if ("1".equalsIgnoreCase(dataVo.getInoutFlg())) {
				rdInoutFlgType1.setSelected(true);
			} else {
				rdInoutFlgType0.setSelected(true);
			}
	
			txtOldCode.setText(dataVo.getOldCode());
	
			if ("1".equalsIgnoreCase(dataVo.getUseFlg())) {
				chkUseFlg.setSelected(true);
				chkUseFlg.setText(NameConstants.getName("USEFLG" + "1"));
			} else {
				chkUseFlg.setSelected(false);
				chkUseFlg.setText(NameConstants.getName("USEFLG" + "0"));
			}
	
			lblRegisterUser.setText(dataVo.getAddUser());
			lblRegisterDate.setText(DateUtils.getDateWithSplit(dataVo.getAddDate()));
			lblEditUser.setText(dataVo.getLastupUser());
			lblEditDate.setText(DateUtils.getDateWithSplit(dataVo.getLastupDate()));
		}
	}

	/**
	 * Lay du lieu tu cac control tren man hinh, chuyen thanh Vo va save vao CSDL
	 * 
	 * 
	 * 
	 */
	private MEmpVo convertTxt2Vo() {

		MEmpVo dataVo = new MEmpVo();

		dataVo.setEmpCode(txtEmpCode.getText().trim());
		dataVo.setEmpName(txtEmpName.getText().trim());
		dataVo.setEmpKana(txtEmpKana.getText().trim());
		// Search index get first character from EMP_KANA
		dataVo.setSearchidx(StringUtils.subString(txtEmpKana.getText().trim(), 1));
		dataVo.setEmpTname(txtEmpTname.getText().trim());
		dataVo.setUserUser(StringUtils.objectToStr(cbbGroup.getSelectedKey()));
		dataVo.setEmpUser(txtEmpUser.getText().trim());
		dataVo.setPwd(pwdPwd.getText().trim());
		dataVo.setDsporderNo(txtDisplayOrder.getValue());

		if (chkLogviewFlg.isSelected() == false) {
			dataVo.setLogviewFlg("0");
		} else {
			dataVo.setLogviewFlg("1");
		}

		if (chkExcelout.isSelected() == false) {
			dataVo.setExcelout("0");
		} else {
			dataVo.setExcelout("1");
		}

		dataVo.setTelno(txtTelno.getText().trim());
		dataVo.setMailadr(txtMailadr.getText().trim());
		dataVo.setMailadrm(txtMailadrm.getText().trim());
		dataVo.setPcid(txtPcid.getText().trim());

		if (rdInoutFlgType1.isSelected() == true) {
			dataVo.setInoutFlg("1");
		} else {
			dataVo.setInoutFlg("0");
		}

		dataVo.setOldCode(txtOldCode.getText().trim());
		dataVo.setUsingFlg("0");//Default is 0
		if (chkUseFlg.isSelected() == false) {
			dataVo.setUseFlg("0");
		} else {
			dataVo.setUseFlg("1");
		}

		return dataVo;
	}

	/**
	 * Lay du lieu tu Row duoc chon lay ra Vo, dua vao
	 * 
	 * 
	 */
	private void convertTableRow2Txt() {

		int selectIndex = m_table.getSelectedRow();
		String prodCode = "";

		if (lstData != null && lstData.size() > 0 && selectIndex >= 0) {
			for (int i = 0; i < m_table.getColumnCount(); i++) {
				if (m_table.getColumnName(i).equals(colHeadNm[0])) {
					prodCode = m_table.getValueAt(selectIndex, i).toString();
					break;
				}
			}
		}

		MEmpVo selectedVo = new MEmpVo();

		for (int i = 0; i < lstData.size(); i++) {
			if (prodCode.equalsIgnoreCase(lstData.get(i).getEmpCode())) {
				selectedVo = lstData.get(i);
				break;
			}
		}

		convertVo2Txt(selectedVo);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * Reset data on Control to Empty or Default value 1. txt to String.Empty 2.
	 * cbb to SelectedInde(0) 3. pwd to String.Empty 4. chk to Selected(false)
	 * 5. rd to Selected 0</DD>
	 * <BR>
	 * </DL>
	 */
	private void resetData() {
		txtEmpCode.setText("");
		txtEmpName.setText("");
		txtEmpKana.setText("");
		txtEmpTname.setText("");
		cbbGroup.setSelectedIndex(0);
		txtEmpUser.setText("");
		pwdPwd.setText("");
		txtDisplayOrder.setText("");
		chkLogviewFlg.setSelected(false);
		chkLogviewFlg.setText(NameConstants.getName("LOGVIEW" + "0"));
		chkExcelout.setSelected(false);
		chkExcelout.setText(NameConstants.getName("EXCELOUT" + "0"));
		txtTelno.setText("");
		txtMailadr.setText("");
		txtMailadrm.setText("");
		txtPcid.setText("");
		rdInoutFlgType0.setSelected(true);
		txtOldCode.setText("");
		chkUseFlg.setSelected(false);
		chkUseFlg.setText(NameConstants.getName("USEFLG" + "0"));

		lblRegisterUser.setText("");
		lblRegisterDate.setText("");
		lblEditUser.setText("");
		lblEditDate.setText("");

		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(EditConstants.VIEW_MODE);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	private void resetDataNotResetCode() {
		txtEmpName.setText("");
		txtEmpName.setTxtHiragana("");
		txtEmpKana.setText("");
		txtEmpTname.setText("");
		cbbGroup.setSelectedIndex(0);
		txtEmpUser.setText("");
		pwdPwd.setText("");
		txtDisplayOrder.setText("");
		chkLogviewFlg.setSelected(false);
		chkLogviewFlg.setText(NameConstants.getName("LOGVIEW" + "0"));
		chkExcelout.setSelected(false);
		chkExcelout.setText(NameConstants.getName("EXCELOUT" + "0"));
		txtTelno.setText("");
		txtMailadr.setText("");
		txtMailadrm.setText("");
		txtPcid.setText("");
		rdInoutFlgType0.setSelected(true);
		txtOldCode.setText("");
		chkUseFlg.setSelected(false);
		chkUseFlg.setText(NameConstants.getName("USEFLG" + "0"));

		lblRegisterUser.setText("");
		lblRegisterDate.setText("");
		lblEditUser.setText("");
		lblEditDate.setText("");
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * Get data from Service to bind to table 1. Get value SCH_EMP from table
	 * M_CTL ( the 1st character) 2. Get data from Service to lstData (full
	 * Columns) 3. Take some columns to bind to list of Table in form</DD> <BR>
	 * 
	 * @return </DL>
	 */
	private List getTableData() {

		// get data for table
		if (mEmpService == null)
			mEmpService = new MEmpServiceImpl();
		try {
			// lstData = mEmpService.getLstMemoVo();
			// Get value SCH_EMP from table M_CTL to order lstData
			// Get the first Value to Order. Example get 30 use [3] to Order
			// SCH_EMP == 1 sort by EMP_CODE
			// SCH_EMP == 2 sort by EMP_NAME
			// SCH_EMP == 3 sort by EMP_KANA
			int iOrderBy = 0;
			String strSCH_EMP = MCtlConstants.getValueByCKey("SCH_EMP").trim();
			if (strSCH_EMP.length() == 2) {
				strSCH_EMP = strSCH_EMP.substring(0, 1);
				iOrderBy = NumberUtils.getIntFromString(strSCH_EMP);
			}
			// Get list data from Service
			lstData = mEmpService.getAll(iOrderBy);
		} catch (BizException e1) {
			e1.printStackTrace();
		}

		List retList = new ArrayList();

		for (int i = 0; i < lstData.size(); i++) {
			// Value to get: EMP_CODE, EMP_KANA, USE_FLG,
			// ADD_USER, ADD_PC, ADD_DATE, ADD_TIME,
			// LASTUP_USER, LASTUP_PC, LASTUP_DATE, LASTUP_TIME
			List<String> list = new ArrayList<String>();
			list.add(lstData.get(i).getEmpCode()); // EMP_CODE
			list.add(lstData.get(i).getEmpName()); // EMP_NAME

			if ("1".equalsIgnoreCase(lstData.get(i).getUseFlg()))// USE_FLG
			{
				list.add("レ");
			} else {
				list.add("");
			}

			list.add(lstData.get(i).getAddUser()); // ADD_USER
			list.add(lstData.get(i).getAddPc()); // ADD_PC
			list.add(DateUtils.getDateWithSplit(lstData.get(i).getAddDate())); // ADD_DATE
			list.add(DateUtils.getTimeWithSplit(lstData.get(i).getAddTime())); // ADD_TIME
			list.add(lstData.get(i).getLastupUser()); // LASTUP_USER
			list.add(lstData.get(i).getLastupPc()); // LASTUP_PC
			list.add(DateUtils.getDateWithSplit(lstData.get(i).getLastupDate())); // LASTUP_DATE
			list.add(DateUtils.getTimeWithSplit(lstData.get(i).getLastupTime())); // LASTUP_TIME

			retList.add(list);
		}

		return retList;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	private BaseScrollPane getTable() {
		BaseScrollPane tablePnl;
		m_model = new InspectTableModel(colHeadNm);
		m_table = new InspectTable(m_model) {
			private static final long serialVersionUID = 1L;

			private int[] align = { BaseLabel.LEFT, BaseLabel.LEFT,
					BaseLabel.CENTER };

			private InspectTableRenderer renderer = new InspectTableRenderer(
					align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row,
					int column) {
				return null;
			}
		};
		// set data for spread
		m_model.setData(new ArrayList<MEmpVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_table.getColumnModel();
		int length = colHeadwidth.length;
		for (int i = 0; i < length; i++) {
			defModel.getColumn(i).setPreferredWidth(colHeadwidth[i]);
			if (i != 1) {
				defModel.getColumn(i).setMinWidth(colHeadwidth[i]);
				defModel.getColumn(i).setMaxWidth(colHeadwidth[i]);
			}
		}
		m_table.getTableHeader().setReorderingAllowed(false);
		// add action for table: UP, DOWN,...
		initTableAction(m_table);
		attachF8(m_table, false);
		// Set tab for table to control
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					if (txtEmpCode.isEditable()) {
						txtEmpCode.requestFocus();
					} else if (txtEmpCode.isEditable()) {
						txtEmpCode.requestFocus();
					}
				} else {
					m_table.requestFocus();
				}
			}
		};

		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		m_table.getActionMap().put("tabKey", tabKey);
		m_table.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_table.getActionMap().put("shiftTab", tabKey);
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),	"enterKey");
		m_table.getActionMap().put("enterKey", tabKey);		
		// EVENT
		// mouse click
		m_table.addMouseListener(new TableMouseClick());

		// Not allow change index of Columns
		m_table.getTableHeader().setReorderingAllowed(false);
		// change selected row
		ListSelectionModel rowSM = m_table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				convertTableRow2Txt();
			}
		});

		tablePnl = new BaseScrollPane(m_table);
		tablePnl.setBorder(FaceContants.PANEL_BORDER);
		tablePnl
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		m_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return tablePnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#initHeader(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getHeader() {
		int X_WIDTH = 100;
		int xPos = 5;
		int yPos = 6;

		// lbl Status : MODE VIEW, EDIT, NEW
		lblStatus = new BaseLabel("登　録");
		lblStatus.setBounds(new Rectangle(xPos, yPos, X_WIDTH + 20, FaceContants.LABLE_HEIGHT));
		lblStatus.setBorder(FaceContants.LINE_BORDER);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus
				.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		headerPnl.add(lblStatus);

		xPos += X_WIDTH + 20;
		BaseLabel lblReg = new BaseLabel("登録件数");
		lblReg.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblReg.setBorder(FaceContants.LINE_BORDER);
		lblReg.setHorizontalAlignment(SwingConstants.CENTER);
		lblReg.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		headerPnl.add(lblReg);

		xPos += X_WIDTH;
		lblNumber = new BaseLabel("");
		lblNumber.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblNumber.setBorder(FaceContants.LABEL_BORDER);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		headerPnl.add(lblNumber);

		xPos += X_WIDTH;
		BaseLabel lblLatestCd = new BaseLabel("最終コード");
		lblLatestCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblLatestCd.setBorder(FaceContants.LINE_BORDER);
		lblLatestCd.setHorizontalAlignment(SwingConstants.CENTER);
		lblLatestCd.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		headerPnl.add(lblLatestCd);

		xPos += X_WIDTH;
		lblLstCd = new BaseLabel("");
		lblLstCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblLstCd.setBorder(FaceContants.LABEL_BORDER);
		lblLstCd.setHorizontalAlignment(SwingConstants.RIGHT);
		headerPnl.add(lblLstCd);

		xPos = xPos + X_WIDTH + 5;
		lblPermission = new BaseLabel();
		lblPermission.setForeground(Color.RED);
		lblPermission.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.RIGHT);
		headerPnl.add(lblPermission);

		headerPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));
		headerPnl.setLayout(new BorderLayout());
		mainPanel.add(headerPnl, BorderLayout.PAGE_START);
		
		return headerPnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initLeftBody(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getBodyLeft() {
		int yPos = 10;
		int xPos = 15;
		int I_LBL_LENGTH = 110;
		int TXT_TEXT_FIELD_LENGTH = 108;
		int TXT_TEXT_FIELD_LENGTH_80 = 80;
		int TXT_TEXT_FIELD_LENGTH_120 = 120;
		int TXT_TEXT_FIELD_LENGTH_180 = 180;
		int TXT_TEXT_FIELD_LENGTH_300 = 300;

		// 1. * LBL TXT EMPCODE MaxLength 6 Width 120
		RequiredLabel lblAsteriskEmpCode = new RequiredLabel("*");
		lblAsteriskEmpCode.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskEmpCode);

		lblEmpCode = new EditLabel("担当者コード");
		lblEmpCode.setLocation(xPos, yPos);
		lblEmpCode.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblEmpCode);

		String strLenEmpCode = MCtlConstants.getValueByCKey("BM_EMP");
		int iLenEmpCode = StringUtils.isValid(strLenEmpCode) ? NumberUtils
				.getIntFromString(strLenEmpCode) : 6;

		txtEmpCode = new CdInputSearchText("", 0, CdInputText.IM_OFF, iLenEmpCode);
		txtEmpCode.setToolTipText(StringUtils.trimAllVN( lblEmpCode.getText() + "を入力して下さい。"));
		txtEmpCode.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtEmpCode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_80 + 20, FaceContants.LABLE_HEIGHT));

		AbstractAction enter = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				try {
					if (txtEmpCode.isEditable() && StringUtils.isValid(txtEmpCode.getText())) {
						// if fill 0 show error E0204
						if (txtEmpCode.getText().equals("0"))
						{
							MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0204"));
							setDefaultFirstFocus(txtEmpCode);
							return;
						}

						String strLenEmpCode = MCtlConstants.getValueByCKey("BM_EMP");
						int iLenEmpCode = StringUtils.isValid(strLenEmpCode) ? NumberUtils.getIntFromString(strLenEmpCode) : 6;

						if (StringUtils.trimAll(txtEmpCode.getText()).length() != iLenEmpCode) {
							MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0004").setExtraInfor(StringUtils.trimAllVN( lblEmpCode.getText())));
							setDefaultFirstFocus(txtEmpCode);
							return;
						}

						MEmpVo dataVo = mEmpService.getByKey(txtEmpCode.getText().trim());

						if (dataVo != null)// Not exist in DB
						{
							if (EditConstants.COPY_MODE.equalsIgnoreCase(CUR_MODE)) {
								MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0006").setExtraInfor(StringUtils.trimAllVN( lblEmpCode.getText())));
								setDefaultFirstFocus(txtEmpCode);
							} else if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
								convertVo2Txt(dataVo); // if is Copy not reload
								// Data
								CUR_MODE = EditConstants.EDIT_MODE;
								setStatus(CUR_MODE);
							}
						} else {
							// Exist in DB but not is Copy
							if (EditConstants.COPY_MODE.equalsIgnoreCase(CUR_MODE) == false) {
								// Reset txt not reset EmpCode
								resetDataNotResetCode();
							}
							CUR_MODE = EditConstants.NEW_MODE;
							setStatus(EditConstants.NEW_MODE);
						}
					} else// Not fill text to EmpCode, do nothing
					{
						setDefaultFirstFocus(txtEmpCode);
					}
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		};
		txtEmpCode.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		txtEmpCode.getActionMap().put("enter", enter);
		txtEmpCode.getFindButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doF4();				
			}
		});
		pnlLeft.add(txtEmpCode);

		// 2. * LBL TXT EMPNAME MaxLength 24 Width 300
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskEmpName = new RequiredLabel("*");
		lblAsteriskEmpName.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskEmpName);

		lblEmpName = new EditLabel("担当者名");
		lblEmpName.setLocation(xPos, yPos);
		lblEmpName.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblEmpName);

		txtEmpName = new BaseKanaInputText("", 0, BaseInputText.IM_HIRAGANA, 24);
		txtEmpName.setToolTipText(StringUtils.trimAllVN( lblEmpName.getText() + "を入力して下さい。"));
		txtEmpName.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtEmpName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300,
				FaceContants.LABLE_HEIGHT));
		txtEmpName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				copyName();
			}
		});

		pnlLeft.add(txtEmpName);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskEmpKana = new RequiredLabel("*");
		lblAsteriskEmpKana.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskEmpKana);

		lblEmpKana = new EditLabel("担当者名(カナ)");
		lblEmpKana.setLocation(xPos, yPos);
		lblEmpKana.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblEmpKana);

		txtEmpKana = new BaseInputText("", 0, BaseInputText.IM_HALFKANA, 20);
		txtEmpKana.setToolTipText(StringUtils.trimAllVN( lblEmpKana.getText() + "を入力して下さい。"));
		txtEmpKana.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtEmpKana.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtEmpKana);

		// 4. * LBL TXT EMPTNAME MaxLength 12 Width 120
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskEmpTname = new RequiredLabel("*");
		lblAsteriskEmpTname.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskEmpTname);

		lblEmpTname = new EditLabel("担当者名(短縮)");
		lblEmpTname.setLocation(xPos, yPos);
		lblEmpTname.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblEmpTname);

		txtEmpTname = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 12);
		txtEmpTname.setToolTipText(StringUtils.trimAllVN( lblEmpTname.getText() + "を入力して下さい。"));
		txtEmpTname.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtEmpTname.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_120,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtEmpTname);

		// 5. * LBL CMB GROUP MaxLength 10 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskGroup = new RequiredLabel("*");
		lblAsteriskGroup.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskGroup);

		lblGroup = new EditLabel("グループ");
		lblGroup.setLocation(xPos, yPos);
		lblGroup
				.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblGroup);

		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		try {
			lstData = comService.getLstUSERGRP();
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstData = new ArrayList<ComboObjectVo>();
		}
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData);
		cbbGroup = new BaseComboBox(aModel);
		cbbGroup.setToolTipText(StringUtils.trimAllVN( lblGroup.getText() + "を選択して下さい。"));
		cbbGroup.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbGroup.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		cbbGroup.setPopupWidth(TXT_TEXT_FIELD_LENGTH_180);
		pnlLeft.add(cbbGroup);

		// 6. * LBL TXT EMPUSER MaxLength 10 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskEmpUser = new RequiredLabel("*");
		lblAsteriskEmpUser.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskEmpUser);

		lblEmpUser = new EditLabel("ユーザー名");
		lblEmpUser.setLocation(xPos, yPos);
		lblEmpUser.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblEmpUser);

		txtEmpUser = new CdInputText("", 0, CdInputText.IM_OFF, 10);
		txtEmpUser.setToolTipText(StringUtils.trimAllVN( lblEmpUser.getText() + "を入力して下さい。"));
		txtEmpUser.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtEmpUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtEmpUser);

		// 7. * LBL PWD PWD MaxLength 16 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskPwd = new RequiredLabel("*");
		lblAsteriskPwd.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskPwd);

		lblPwd = new EditLabel("パスワード");
		lblPwd.setLocation(xPos, yPos);
		lblPwd.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblPwd);

		pwdPwd = new PasswordInputText(16);
		pwdPwd.setToolTipText(StringUtils.trimAllVN( lblPwd.getText() + "を入力して下さい。"));
		pwdPwd.setLocation(xPos + I_LBL_LENGTH, yPos);
		pwdPwd.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(pwdPwd);

		// add display order
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		RequiredLabel lblAsteriskOrder = new RequiredLabel("*");
		lblAsteriskOrder.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskOrder);

		lblDisplayOrder = new EditLabel("表示順");
		lblDisplayOrder.setLocation(xPos, yPos);
		lblDisplayOrder.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblDisplayOrder);

		txtDisplayOrder = new IntegerNumberText();
		txtDisplayOrder.setReformat(true);
		txtDisplayOrder.setMinValue(0);
		txtDisplayOrder.setMaxValue(999999);		
		txtDisplayOrder.setToolTipText(StringUtils.trimAllVN( lblDisplayOrder.getText() + "を入力して下さい。"));
		txtDisplayOrder.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtDisplayOrder.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_80,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtDisplayOrder);

		// 8. * LBL CHK LOGVIEW_FLG MaxLength 1 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		lblLogviewFlg = new EditLabel("ログ表示");
		lblLogviewFlg.setLocation(xPos, yPos);
		lblLogviewFlg.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblLogviewFlg);

		chkLogviewFlg = new BaseCheckBox(NameConstants.getName("LOGVIEW" + "0"));
		chkLogviewFlg.setToolTipText(StringUtils.trimAllVN( lblLogviewFlg.getText() + "を選択して下さい。"));
		chkLogviewFlg.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkLogviewFlg.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		chkLogviewFlg.setChkLabel(NameConstants.getName("LOGVIEW" + "1"));// Get
		// value in Table M_NAME
		chkLogviewFlg.setUnChkLabel(NameConstants.getName("LOGVIEW" + "0"));
		chkLogviewFlg.setSelected(false);
		pnlLeft.add(chkLogviewFlg);

		// 9. LBL CHK EXCELOUT MaxLength 1 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblExcelout = new EditLabel("Excel出力区分");
		lblExcelout.setLocation(xPos, yPos);
		lblExcelout.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblExcelout);

		chkExcelout = new BaseCheckBox(NameConstants.getName("EXCELOUT" + "0"));
		chkExcelout.setToolTipText(StringUtils.trimAllVN( lblExcelout.getText() + "を選択して下さい。"));
		chkExcelout.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkExcelout.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		chkExcelout.setChkLabel(NameConstants.getName("EXCELOUT" + "1"));// Get
		// value　in　Table　M_NAME
		chkExcelout.setUnChkLabel(NameConstants.getName("EXCELOUT" + "0"));
		chkExcelout.setSelected(false);
		pnlLeft.add(chkExcelout);

		// 10. LBL TXT TELNO MaxLength 17 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblTelno = new EditLabel("電話番号");
		lblTelno.setLocation(xPos, yPos);
		lblTelno
				.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblTelno);

		txtTelno = new LatinInputText("", 0, 17);
		txtTelno.setToolTipText(StringUtils.trimAllVN( lblTelno.getText() + "を入力して下さい。"));
		txtTelno.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtTelno.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtTelno);

		// 11. LBL TXT MAILADR MaxLength 50 Width 300
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblMailadr = new EditLabel("Mailアドレス");
		lblMailadr.setLocation(xPos, yPos);
		lblMailadr.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblMailadr);

		txtMailadr = new LatinInputText("", 0, 50);
		txtMailadr.setToolTipText(StringUtils.trimAllVN( lblMailadr.getText() + "を入力して下さい。"));
		txtMailadr.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtMailadr.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtMailadr);

		// 11. LBL TXT MAILADRM MaxLength 50 Width 300
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblMailadrm = new EditLabel("携帯アドレス");
		lblMailadrm.setLocation(xPos, yPos);
		lblMailadrm.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblMailadrm);

		txtMailadrm = new LatinInputText("", 0, 50);
		txtMailadrm.setToolTipText(StringUtils.trimAllVN( lblMailadrm.getText() + "を入力して下さい。"));
		txtMailadrm.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtMailadrm.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtMailadrm);

		// 12. LBL TXT PCNAME MaxLength 30 Width 300
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblPcid = new EditLabel("PC名称");
		lblPcid.setLocation(xPos, yPos);
		lblPcid.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblPcid);

		txtPcid = new BaseInputText("", 0, CdInputText.IM_OFF, 30);
		txtPcid.setToolTipText(StringUtils.trimAllVN( lblPcid.getText() + "を入力して下さい。"));
		txtPcid.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtPcid.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtPcid);

		// 13. LBL RDO INOUT_FLG MaxLength 1 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblInoutFlg = new EditLabel("内外フラグ");
		lblInoutFlg.setLocation(xPos, yPos);
		lblInoutFlg.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblInoutFlg);

		ButtonGroup groupTemp = new ButtonGroup();
		rdInoutFlgType0 = new InspectRadioButton(NameConstants.getName("INOUT"
				+ "0"), false);
		rdInoutFlgType0.setBounds(new Rectangle(xPos + I_LBL_LENGTH, yPos, 100,
				FaceContants.TEXT_HEIGHT));
		rdInoutFlgType0.setToolTipText(StringUtils.trimAllVN( lblInoutFlg.getText() + "を選択して下さい。"));
		groupTemp.add(rdInoutFlgType0);
		rdInoutFlgType1 = new InspectRadioButton(NameConstants.getName("INOUT"
				+ "1"), true);
		rdInoutFlgType1.setBounds(new Rectangle(xPos + 300, yPos, 100,
				FaceContants.TEXT_HEIGHT));
		rdInoutFlgType1.setToolTipText(StringUtils.trimAllVN( lblInoutFlg.getText() + "を選択して下さい。"));
		groupTemp.add(rdInoutFlgType1);
		rdInoutFlgType0.setSelected(true);
		pnlLeft.add(rdInoutFlgType0);
		pnlLeft.add(rdInoutFlgType1);

		// 旧コード
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblOldCode = new EditLabel("旧コード");
		lblOldCode.setLocation(xPos, yPos);
		lblOldCode.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblOldCode);

		txtOldCode = new CdInputText("", 0, 20);
		txtOldCode.setToolTipText(StringUtils.trimAllVN( lblOldCode.getText() + "を入力して下さい。"));
		txtOldCode.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtOldCode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtOldCode);

		// 14. LBL CHK USE_FLG MaxLength 1 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		lblUseFlg = new EditLabel("使用可否");
		lblUseFlg.setLocation(xPos, yPos);
		lblUseFlg
				.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblUseFlg);

		chkUseFlg = new BaseCheckBox(NameConstants.getName("USEFLG" + "0"));
		chkUseFlg.setToolTipText(StringUtils.trimAllVN( lblUseFlg.getText() + "を選択して下さい。"));
		chkUseFlg.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkUseFlg.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		chkUseFlg.setChkLabel(NameConstants.getName("USEFLG" + "1"));// Get
		// value in Table M_NAME
		chkUseFlg.setUnChkLabel(NameConstants.getName("USEFLG" + "0"));
		chkUseFlg.setSelected(false);
		pnlLeft.add(chkUseFlg);

		// REGISTER USER, DATE AND LAST MODIFIER USER, DATE
		I_LBL_LENGTH = 63;
		TXT_TEXT_FIELD_LENGTH = 76;
		// yPos = Y_FRAME_LENGTH - 130 - 2 * FaceContants.LABLE_HEIGHT;
		yPos += FaceContants.LABLE_HEIGHT + 2 * FaceContants.VERTICAL_SPACE;

		infoPnl = new BasePanel();
		int xPos1 = 0;
		int yPos1 = 0;
		ViewLabel lblRegisterInfo = new ViewLabel("登録情報");
		lblRegisterInfo.setBounds(new Rectangle(xPos1, yPos1, I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		lblRegisterInfo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPnl.add(lblRegisterInfo);

		xPos1 += I_LBL_LENGTH;
		lblRegisterUser = new BaseLabel();
		lblRegisterUser.setVerticalAlignment(BaseLabel.CENTER);
		lblRegisterUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH - 5,
				FaceContants.LABLE_HEIGHT));
		lblRegisterUser.setLocation(xPos1, yPos1);
		lblRegisterUser.setText("");
		lblRegisterUser.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblRegisterUser.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblRegisterUser);

		xPos1 += TXT_TEXT_FIELD_LENGTH - 5;
		lblRegisterDate = new BaseLabel();
		lblRegisterDate.setVerticalAlignment(BaseLabel.CENTER);
		lblRegisterDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH + 5,
				FaceContants.LABLE_HEIGHT));
		lblRegisterDate.setLocation(xPos1, yPos1);
		lblRegisterDate.setText("");
		lblRegisterDate.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblRegisterDate.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblRegisterDate);

		xPos1 += TXT_TEXT_FIELD_LENGTH + 5;
		ViewLabel lblEditInfo = new ViewLabel("更新情報");
		lblEditInfo.setBounds(new Rectangle(xPos1, yPos1, I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		lblEditInfo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPnl.add(lblEditInfo);

		xPos1 += I_LBL_LENGTH;
		lblEditUser = new BaseLabel();
		lblEditUser.setVerticalAlignment(BaseLabel.CENTER);
		lblEditUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH - 5,
				FaceContants.LABLE_HEIGHT));
		lblEditUser.setLocation(xPos1, yPos1);
		lblEditUser.setText("");
		lblEditUser.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblEditUser.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblEditUser);

		xPos1 += TXT_TEXT_FIELD_LENGTH - 5;
		lblEditDate = new BaseLabel();
		lblEditDate.setVerticalAlignment(BaseLabel.CENTER);
		lblEditDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH + 5,
				FaceContants.LABLE_HEIGHT));
		lblEditDate.setLocation(xPos1, yPos1);
		lblEditDate.setText("");
		lblEditDate.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblEditDate.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblEditDate);
		infoPnl.setLocation(xPos, yPos);
		infoPnl.setSize(430, FaceContants.LABLE_HEIGHT);
		pnlLeft.add(infoPnl);
		pnlLeft.setPreferredSize(new Dimension(455, 100));
		Y_BODY_LENGTH = yPos + FaceContants.LABLE_HEIGHT + 2 * FaceContants.VERTICAL_SPACE;
		
		return pnlLeft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initRightBody(com.pipe.jface.BasePanel)
	 */
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initRightBody(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getBodyCenter() {
		psTable = getTable();
		pnlCenter.setLayout(new GridLayout(1, 1));
		pnlCenter.add(psTable, createBothConstraints(0, 1, 1, 1, 1, 1));
		pnlCenter.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				if (iCount != 0) {
					int topY = pnlCenter.getHeight()
							- FaceContants.LABLE_HEIGHT - 2
							* FaceContants.VERTICAL_SPACE;
					infoPnl.setLocation(15, topY);
				}
				iCount++;
			}
		});
		return pnlCenter;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	void copyName() {
		if (txtEmpTname.getText().equals("")) {
			int iMaxLen = txtEmpTname.getMaxLength();
			txtEmpTname.setText(StringUtils.subStringUseEncode(txtEmpName
					.getText().trim(), 0, iMaxLen));
		}

		if (txtEmpKana.getText().equals("")) {
			int iMaxLen = txtEmpKana.getMaxLength();
			txtEmpKana.setText(StringUtils.subStringUseEncode(txtEmpName
					.getTxtHiragana().trim(), 0, iMaxLen));
		}
	}

	/**
	 * F1: Ham xuat du lieu tren man hinh ra file Excel
	 * 
	 * 
	 */
	@Override
	protected void doF1() {
		// If not exist element in DB show error E0001
		try {
			if (mEmpService.getVoCount() <= 0) {
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0001"));
				return;
			}
			try {
				String strPath = MOutCtlContants.getValue(ApplicationConstants.getLoginUser().getUserId(), MOutCtlContants.I_PATH1);
				String[] fileName = new String[] { "xls" };
				JFileChooser fileChoser = new JFileChooser();
				fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName, "ファイル (*.xls)"));
				if (StringUtils.isValid(strPath)) {
					fileChoser.setCurrentDirectory(FileUtils.getFileObj(strPath));
				} else {
					fileChoser.setCurrentDirectory(FileUtils.getFileObj(fileChoser.getFileSystemView().getDefaultDirectory().getAbsolutePath()));
				}
				int rVal = fileChoser.showSaveDialog(getFrame());
				if (rVal == JFileChooser.APPROVE_OPTION) {
					String strFilePath = fileChoser.getCurrentDirectory().toString()+ "\\"	+ fileChoser.getSelectedFile().getName().replaceAll(".xls", "") + ".xls";
					mEmpService.exportEXCEL(strFilePath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (BizException e) {
			logger.error(e.getMessage());
			MessageBox.message(getFrame(), MessageConstants
					.getMstMsgVo("E0123"));
		}
	}

	/**
	 * F2: Ham copy du lieu tu mot ban ghi co truoc
	 * 
	 * 
	 */
	@Override
	protected void doF2() {
		if (btnF2.isEnabled()) {
			CUR_MODE = EditConstants.COPY_MODE;
			txtEmpCode.setText("");
			setStatus(CUR_MODE);
		}
		// invokeSetStatusLater();
	}

	/**
	 * F3: Ham chua su dung, nut bi Disable
	 * 
	 * 
	 */
	@Override
	protected void doF3() {
	}

	/**
	 * F4: Ham goi form tim kiem
	 * 
	 * 
	 */
	@Override
	protected void doF4() {

		SortObjVo sortObj = new SortObjVo();
		sortObj.setM_sortCol(NumberUtils.getIntFromString(MCtlConstants
				.getValueByCKey("SCH_EMP").substring(0, 1)));

		SearchMEmpFrame search = new SearchMEmpFrame(getFrame(),
				sortObj);	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtEmpCode.setText(searchData.getProPer1());
			try {
				MEmpVo dataVo = mEmpService.getByKey(txtEmpCode.getText().trim());
				convertVo2Txt(dataVo); // if is Copy not reload Data
				//oldVo = dataVo;
				
				CUR_MODE = EditConstants.EDIT_MODE;
				setStatus(CUR_MODE);
				btnF4.setAffterDoDisable(true);
			} catch (BizException ex) {
				MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
				logger.error(ex.getMessage());
			}
		}
	}

	/**
	 * F5: Ham chua su dung, nut bi Disable
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF5()
	 */
	@Override
	protected void doF5() {
	}

	/**
	 * F6: Ham delete du lieu
	 * 
	 * 
	 */
	@Override
	protected void doF6() {
		btnF6.setAffterDoDisable(false);
		try {
			if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
				// Check this data is still used
				int iCountTrInspd = 0;
				if (iCountTrInspd > 0) {
					MessageBox.message(this, MessageConstants.getMstMsgVo("E0203"));
					return;
				}
				int iCountFLog = fLogService.countVoByUserUser(txtEmpUser.getText());
				if (iCountFLog > 0) {
					MessageBox.message(this, MessageConstants.getMstMsgVo("E0203"));
					return;
				}
				if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0003")) == MessageBox.YES) {
					MEmpVo dataVo = convertTxt2Vo();
					// Write log delete to DB
					ApplicationUtils.logData(
							ApplicationConstants.LOGIN_USER_ID, 
							exeMenu.getMenugrpCode(),
							exeMenu.getMenuexeCode(), 
							LogContants.LOGACT_DE,
							lblEmpCode.getText() + ":" + txtEmpCode.getText());

					// Delete Check Exist in DB
					MEmpVo objEmp = mEmpService.getByKey(dataVo.getEmpCode());
					if (objEmp == null) {
						MessageBox.message(this, MessageConstants.getMstMsgVo("E0003"));
						return;
					}

					// Delete data in M_CTL for delete Data
					mCtlService = new MCtlServiceImpl();
					try {
						String strUserid = txtEmpUser.getText();
						int iMtnFlg = 0;
						mCtlService.deleteDataByUserid(strUserid, iMtnFlg);
					} catch (BizException e) {
						logger.error(e.getMessage());
					}

					// Delete data in M_CLRCTL for delete Data
					colorService = new ColorServiceImpl();
					try {
						colorService.deleteClrCtlByUserID(txtEmpUser.getText());
					} catch (BizException e) {
						logger.error(e.getMessage());
					}

					mEmpService.deleteVo(dataVo);

					resetData();
					doF10();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
	}

	/**
	 * F7: Ham chua su dung, nut bi Disable
	 * 
	 * 
	 */
	@Override
	protected void doF7() {
	}

	/**
	 * F8: Ham save du lieu danh cho ca Insert, Copy va Update
	 * 
	 * 
	 */
	@Override
	protected void doF8() {
		copyName();
		try {
			if (validateData()) {
				if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)
						|| EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {

					if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0002")) == MessageBox.YES) {
						MEmpVo dataVo = convertTxt2Vo();
						if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
							// Update Check Exist in DB
							MEmpVo objEmp = mEmpService.getByKey(dataVo.getEmpCode());
							if (objEmp == null) {
								MessageBox.message(this, MessageConstants.getMstMsgVo("E0003"));
								return;
							}
							// Write log update to DB
							ApplicationUtils.logData(
									ApplicationConstants.LOGIN_USER_ID, 
									exeMenu.getMenugrpCode(), 
									exeMenu.getMenuexeCode(),
									LogContants.LOGACT_UP, 
									lblEmpCode.getText() + ":" + txtEmpCode.getText());
							// Update data
							mEmpService.updateVo(dataVo);
							// Create data in M_CTL for new User
							mCtlService = new MCtlServiceImpl();
							try {
								String strFromUserid = mCtlService.getByKey("SYSTEM", "Z_SYSUSR").getCData();
								String strToUserid = txtEmpUser.getText();
								int iMtnFlg = 0;
								mCtlService.fillDataFromUseridToUserid(
										strFromUserid, strToUserid, iMtnFlg);
							} catch (BizException e) {
								logger.error(e.getMessage());
							}
						} else {
							// Insert Check Not Exist in DB
							MEmpVo objEmp = mEmpService.getByKey(dataVo.getEmpCode());
							if (objEmp != null) {
								MessageBox.message(this, MessageConstants
										.getMstMsgVo("E0001"));
								return;
							}
							// Write log insert to DB
							ApplicationUtils.logData(
									ApplicationConstants.LOGIN_USER_ID, 
									exeMenu.getMenugrpCode(), 
									exeMenu.getMenuexeCode(),
									LogContants.LOGACT_AD, 
									lblEmpCode.getText() + ":" + txtEmpCode.getText());
							// Insert data
							mEmpService.insertVo(dataVo);
							// Create data in M_CTL for new User
							mCtlService = new MCtlServiceImpl();
							try {
								String strFromUserid = mCtlService.getByKey("SYSTEM", "Z_SYSUSR").getCData();
								String strToUserid = txtEmpUser.getText();
								int iMtnFlg = 0;
								mCtlService.fillDataFromUseridToUserid(
										strFromUserid, strToUserid, iMtnFlg);
							} catch (BizException e) {
								logger.error(e.getMessage());
							}
						}
						resetData();
						doF10();
					} else {
						btnF8.setAffterDoDisable(false);
					}
				}
			} else {
				btnF8.setAffterDoDisable(false);
			}
		} catch (Exception e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
		// invokeSetStatusLater();
	}

	/**
	 * F9: Ham chua su dung, nut bi Disable
	 * 
	 * 
	 */
	@Override
	protected void doF9() {
	}

	/**
	 * F10: Load lai du lieu tu DB len Grid, dua man hinh lai trang thai nhu luc mo form
	 * 
	 * 
	 */
	@Override
	protected void doF10() {
		// Get number element and max code from database
		setTitleInfor();
		getTable();
		// Get data to table from Service
		m_model.setData(getTableData());

		// Repaint table (paint Scroll Vertical)
		m_table.repaint();
		psTable.getViewport().removeAll();
		psTable.getViewport().add(m_table);
		m_table.requestFocus();
		// Set Pointer to first row
		int iSelectedIndex = 0;
		m_table.changeSelection(iSelectedIndex, 0, false, false);
		// Don't show data in left body
		resetData();
	}

	/**
	 * F11: Khong load lai du lieu tu DB len Grid, dua man hinh lai trang thai nhu luc mo form
	 * 
	 * 
	 */
	@Override
	protected void doF11() {
		if (!EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {			
			MEmpVo dataVo = convertTxt2Vo();
			if(!MEmpVo.equal(dataVo, oldVo)){
				if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
					m_table.requestFocus();
					resetData();
				}
			}
			else {
				m_table.requestFocus();
				resetData();
			}
		} else {
			m_table.requestFocus();
			resetData();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#enableBtn()
	 */
	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = { true, true, true, true, true, true, true, true,
				true, true, true, true, true, true, true };
		fBtn[3] = false;
		fBtn[5] = false;
		fBtn[7] = false;
		fBtn[9] = false;
		return fBtn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doFirst()
	 */
	@Override
	protected void doFirst() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {
			int selectIndex = 0;
			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doLast()
	 */
	@Override
	protected void doLast() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getRowCount();
			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doNext()
	 */
	@Override
	protected void doNext() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex + ROW_PER_PAGE;

			if (selectIndex > m_table.getRowCount())
				selectIndex = m_table.getRowCount();

			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doPre()
	 */
	@Override
	protected void doPre() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {

			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex - ROW_PER_PAGE;

			if (selectIndex < 0)
				selectIndex = 0;

			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.bussiness.BaseMasterLayoutFrame#isChange()
	 */
	public boolean isChange() {
		
		MEmpVo dataVo = convertTxt2Vo();
		
		if(!MEmpVo.equal(dataVo, oldVo)){
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doDoubleClick(int)
	 */
	protected void doDoubleClick(int row) {
		// If have not element
		if (row < 0) {
			return;
		}
		if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			doSingleClick(row);
			// 修正モードを変わります。
			CUR_MODE = EditConstants.EDIT_MODE;
			setStatus(CUR_MODE);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doSingleClick(int)
	 */
	protected void doSingleClick(int row) {
		// If have not element
		if (row < 0) {
			return;
		}
		// 修正時は、何にもしません。
		if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE) == false) {
			return;
		}

		String memoCode = "";

		if (lstData != null && lstData.size() > 0 && row >= 0) {
			for (int i = 0; i < m_table.getColumnCount(); i++) {
				if (m_table.getColumnName(i).equals(colHeadNm[0])) {
					memoCode = m_table.getValueAt(row, i).toString();
					break;
				}
			}
		}

		MEmpVo selectedVo = new MEmpVo();

		for (int i = 0; i < lstData.size(); i++) {
			if (memoCode.equalsIgnoreCase(lstData.get(i).getEmpCode())) {
				selectedVo = lstData.get(i);
				break;
			}
		}

		//oldVo = selectedVo;
		convertVo2Txt(selectedVo);
		SwingUtils.scrollRowColumn(m_table, row, 0);
	}

	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu) == false) {
			// Not permission show red text and disable btn
			lblPermission.setText(NameConstants.getName("UPDFLG"+"1"));
			lblPermission.setBorder(null);
		}

	}
	
	@Override
	protected void setFrameSize() {
		X_FRAME_LENGTH = 900;
		Y_FRAME_LENGTH = Y_BODY_LENGTH + BTN_PANEL_HEIGHT + Y_FOOTER_LENGTH + Y_HEADER_LENGTH + getHeightBorder();
		setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		setPreferredSize(new Dimension(X_FRAME_LENGTH , Y_FRAME_LENGTH));
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		setLocation((maximumWindowBounds.width - X_FRAME_LENGTH) / 2, (maximumWindowBounds.height - Y_FRAME_LENGTH) / 2);
	}
}