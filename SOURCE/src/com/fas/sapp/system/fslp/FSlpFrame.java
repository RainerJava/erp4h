/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：SlpMasterFrame.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.sapp.system.fslp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.EditConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.MessageBox;
import com.fas.jface.bussiness.BaseMasterLayoutFrame;
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
import com.fas.jface.text.NumberText;
import com.fas.jface.text.NumberTextWithZero;
import com.fas.jface.utils.SwingUtils;
import com.fas.service.system.fslp.FSlpService;
import com.fas.service.system.fslp.FSlpServiceImpl;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.slp.SlpVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Bui Ngoc Viet</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class FSlpFrame  extends BaseMasterLayoutFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** log */
	static Logger logger = Logger.getLogger(FSlpFrame.class);
	/** テーブルヘッダーサイズ */
	private static String colHeadNm[] = { "種類 ", " 種類名", "最新値"," " };

	/** */
	private NumberTextWithZero txtSlpType;
	/** */
	private NumberTextWithZero txtSlpDate;
	/** */
	private BaseInputText txtSlpName;
	/** */
	private NumberText txtSlpNew;
	/** */
	private NumberText txtSlpMin;
	/** */
	private NumberText txtSlpMax;
	/** */
	private BaseLabel lblStatus;
	/** */
	private BaseLabel lblRegisterUser;
	/** */
	private BaseLabel lblRegisterDate;
	/** */
	private BaseLabel lblEditUser;
	/** */
	private BaseLabel lblEditDate;
	/** Table */
	protected InspectTable m_table;
	/** */
	protected InspectTableModel m_model;
	/** */
	protected List<SlpVo> lstData;
	/** */
	private BaseScrollPane psTable;
	/** */
	private BasePanel infoPnl;
	/** */
	private int iCount = 0;
	/** */
	private SlpVo oldVo;
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private int[] colHeadwidth = { 80, 180, 70,0 };
	/** */
	private int ROW_PER_PAGE;
	/** database service */
	private FSlpService slpService;
	/** */
	private BaseLabel lblPermission;
	/** log service */
	//private LogService logService = new LogServiceImpl();
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
	 * <DD></DD> <BR>
	 * 
	 * @param frame
	 * @param title
	 *            </DL>
	 */
	public FSlpFrame(BaseFrame frame, String title) {
		super(frame, title);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param frame
	 *            </DL>
	 */
	public FSlpFrame(BaseFrame frame) {
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
	public FSlpFrame() {
		super();
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.gui.AbstractFrame#initKodomo()
	 */
	protected void initKodomo() {
		slpService = new FSlpServiceImpl();
		//logService = new LogServiceImpl();		
		// comService = new ComboServiceImpl();
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
		CUR_MODE = EditConstants.VIEW_MODE;
		getHeader();
		getBodyLeft();
		getBodyCenter();
		buildMiddle();
    	setFrameSize();
		setDispTabFocus();
		setStatus(CUR_MODE);
		btnF10.setText("");
		btnF1.setText("");
		btnF2.setText("");
		btnF4.setText("");
		btnF6.setText("");
		
		if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INTD_CST").trim())) {
			doF10();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param mode
	 *            </DL>
	 */
	private void setScreenMode(String mode) {
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_EDIT_MODE);
			lblStatus
					.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
			if (PermissionPolicy.checkMnExePermission(
					PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
							.getLoginUser().getUserUser(), exeMenu)) {
				btnF8.setEnabled(true);
				btnF8.setAffterDoDisable(true);
				btnF12.setConfirmRerquired(true);

				m_table.setEnabled(false);				
				btnFirst.setEnabled(false);
				btnNext.setEnabled(false);
				btnPre.setEnabled(false);
				btnLast.setEnabled(false);
				pnlCenter.setAlpha(0.5f);
			}
		}
//		else if (EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
//			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
//			lblStatus
//					.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
//			btnF2.setEnabled(false);
//			btnF6.setEnabled(false);
//			if (PermissionPolicy.checkMnExePermission(
//					PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
//							.getLoginUser().getUserUser(), exeMenu)) {
//				btnF8.setEnabled(true);
//			}
//			btnF10.setEnabled(false);
//
//			btnF12.setConfirmRerquired(true);
//
//			m_table.setEnabled(false);
//			btnFirst.setEnabled(false);
//			btnNext.setEnabled(false);
//			btnPre.setEnabled(false);
//			btnLast.setEnabled(false);
//			this.getrInputPnl().setAlpha(1f);
//		} else if (EditConstants.COPY_MODE.equalsIgnoreCase(mode)) {
//			lblStatus.setText(EditConstants.LBL_COPY_MODE);
//			lblStatus
//					.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
//			btnF2.setEnabled(false);
//			btnF6.setEnabled(false);
//			btnF8.setEnabled(false);
//			btnF10.setEnabled(false);
//
//			btnF12.setConfirmRerquired(true);
//
//			m_table.setEnabled(false);
//			btnFirst.setEnabled(false);
//			btnNext.setEnabled(false);
//			btnPre.setEnabled(false);
//			btnLast.setEnabled(false);
//			this.getrInputPnl().setAlpha(1f);
//		} 
		else {
			lblStatus.setText(EditConstants.LBL_EDIT_MODE);
			lblStatus
					.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
			btnF1.setEnabled(false);
			btnF2.setEnabled(false);
			btnF4.setEnabled(false);
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);
			btnF10.setEnabled(false);

			btnF12.setConfirmRerquired(false);
			btnF8.setAffterDoDisable(true);

			m_table.setEnabled(true);
			btnFirst.setEnabled(true);
			btnNext.setEnabled(true);
			btnPre.setEnabled(true);
			btnLast.setEnabled(true);
			pnlCenter.setAlpha(1f);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF1()
	 */
	@Override
	protected void doF1() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF2()
	 */
	@Override
	protected void doF2() {
		if (btnF2.isEnabled()) {
			CUR_MODE = EditConstants.COPY_MODE;
			txtSlpType.setText("");
			txtSlpDate.setText("");
			setStatus(CUR_MODE);
			invokeSetStatusLater();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF3()
	 */
	@Override
	protected void doF3() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF4()
	 */
	@Override
	protected void doF4() {
//		SearchTokuiSakiFrame search = new SearchTokuiSakiFrame(getFrame());
//
//		search.pack();
//		search.setModal(true);
//		search.setVisible(true);
//		SearchVo searchData = search.getReturnData();
//		if (searchData != null) {
//			txtCustCode.setText(searchData.getProPer1());
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF5()
	 */
	@Override
	protected void doF5() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF6()
	 */
	@Override
	protected void doF6() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF7()
	 */
	@Override
	protected void doF7() {
	}


	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF8()
	 */
	// Not insert
	@Override
	protected void doF8() {
		try {
			if (validateData()) {
				if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)){
					if (MessageBox.message(this, MessageConstants
							.getMstMsgVo("Q0002")) == MessageBox.YES) {
						SlpVo dataVo = convertTxt2Vo();
						if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
							SlpVo temp = slpService.getSlpVo(dataVo.getSlpType(),dataVo.getSlpDate());
							if (temp == null) {
								MessageBox.message(this, MessageConstants
										.getMstMsgVo("E0003"));
							} 
							else{
								ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(), LogContants.LOGACT_UP, "");
								slpService.updateSlpVo(dataVo);								
							}							
						}
						resetData();
						doF10();
					}
				}
			}
		}
		catch (Exception e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
		invokeSetStatusLater();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF9()
	 */
	@Override
	protected void doF9() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF10()
	 */
	@Override
	protected void doF10() {
		//setTitleInfor();
		m_model.setData(getTableData());
		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(CUR_MODE);
		m_table.repaint();
		psTable.getViewport().removeAll();
		psTable.getViewport().add(m_table);
		m_table.requestFocus();

		m_table.changeSelection(0, 0, false, false);
		resetData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF11()
	 */
	@Override
	protected void doF11() {
		if (!EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {			
			SlpVo dataVo = convertTxt2Vo();
			if(!SlpVo.equal(dataVo, oldVo)){
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
	private boolean validateData() {

		boolean isValid = true;
		JComponent comp = null;

		if (!StringUtils.isValid(txtSlpType.getText())) {
			isValid = false;
			comp = txtSlpType;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
		}

		else if (!StringUtils.checkLenString(txtSlpType.getText(), 3)) {
			isValid = false;
			comp = txtSlpType;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		else if (!StringUtils.isValid(txtSlpDate.getText())) {
			isValid = false;
			comp = txtSlpDate;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
		}

		else if (!StringUtils.checkLenString(txtSlpDate.getText(), 4)) {
			isValid = false;
			comp = txtSlpDate;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}
		
		else if (isValid && !StringUtils.checkLenString(txtSlpName.getText(), 30)) {
			isValid = false;
			comp = txtSlpName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}
		else if (isValid && !StringUtils.checkLenString(txtSlpNew.getText(), 6)) {
			isValid = false;
			comp = txtSlpNew;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}
		else if (isValid && !StringUtils.checkLenString(txtSlpMin.getText(), 6)) {
			isValid = false;
			comp = txtSlpMin;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}
		else if (isValid && !StringUtils.checkLenString(txtSlpMax.getText(), 6)) {
			isValid = false;
			comp = txtSlpMax;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}
		if (comp != null) {
			setDefaultFirstFocus(comp);
		}

		return isValid;
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
		if (m_table.getRowCount() > 0) {
			int selectIndex = 0;
			m_table.changeSelection(selectIndex, 0, false, false);
			doSingleClick(selectIndex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doLast()
	 */
	@Override
	protected void doLast() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getRowCount();
			m_table.changeSelection(selectIndex - 1, 0, false, false);
			doSingleClick(selectIndex - 1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doNext()
	 */
	@Override
	protected void doNext() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex + ROW_PER_PAGE;

			if (selectIndex > m_table.getRowCount())
				selectIndex = m_table.getRowCount();

			m_table.changeSelection(selectIndex - 1, 0, false, false);
			doSingleClick(selectIndex - 1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doPre()
	 */
	@Override
	protected void doPre() {
		if (m_table.getRowCount() > 0) {

			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex - ROW_PER_PAGE;

			if (selectIndex < 0)
				selectIndex = 0;

			m_table.changeSelection(selectIndex, 0, false, false);
			doSingleClick(selectIndex);
		}
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initHeader(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getHeader() {

		int X_WIDTH = 100;
		int xPos = 4;
		int yPos = 6;

		lblStatus = new BaseLabel("登　録");
		lblStatus.setBounds(new Rectangle(xPos, yPos, X_WIDTH + 20,
				FaceContants.LABLE_HEIGHT));
		lblStatus.setBorder(FaceContants.LINE_BORDER);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus
				.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		headerPnl.add(lblStatus);
		xPos = xPos + X_WIDTH+20 + 5;
		lblPermission = new BaseLabel();
		lblPermission.setForeground(Color.RED);
		lblPermission.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.RIGHT);
		headerPnl.add(lblPermission);
//
//		xPos += X_WIDTH + 20;
//		BaseLabel lblReg = new BaseLabel("登録件数");
//		lblReg.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
//				FaceContants.LABLE_HEIGHT));
//		lblReg.setBorder(FaceContants.LINE_BORDER);
//		lblReg.setHorizontalAlignment(SwingConstants.CENTER);
//		lblReg.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
//		pnl.add(lblReg);
//
//		xPos += X_WIDTH;
//		lblNumber = new BaseLabel("4件");
//		lblNumber.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
//				FaceContants.LABLE_HEIGHT));
//		lblNumber.setBorder(FaceContants.LABEL_BORDER);
//		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
//		pnl.add(lblNumber);
//
//		xPos += X_WIDTH;
//		BaseLabel lblLatestCd = new BaseLabel("最終コード");
//		lblLatestCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
//				FaceContants.LABLE_HEIGHT));
//		lblLatestCd.setBorder(FaceContants.LINE_BORDER);
//		lblLatestCd.setHorizontalAlignment(SwingConstants.CENTER);
//		lblLatestCd.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
//		pnl.add(lblLatestCd);
//
//		xPos += X_WIDTH;
//		lblLstCd = new BaseLabel("999");
//		lblLstCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
//				FaceContants.LABLE_HEIGHT));
//		lblLstCd.setBorder(FaceContants.LABEL_BORDER);
//		lblLstCd.setHorizontalAlignment(SwingConstants.RIGHT);
//		pnl.add(lblLstCd);
		//setTitleInfor();
		headerPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));
		headerPnl.setLayout(new BorderLayout());
		mainPanel.add(headerPnl, BorderLayout.PAGE_START);
		
		return headerPnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
//	private void setTitleInfor() {
//
//		try {
//			lblNumber.setText("");
//			lblLstCd.setText("");
//			lblNumber.setText(custService.getCountVo() + "件");
//			lblLstCd.setText(custService.getMaxCustVo().getCustCode());
//		} catch (BizException e) {
//			logger.error(e.getMessage());
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initLeftBody(com.pipe.jface.BasePanel
	 * )
	 */
	@Override
	protected BasePanel getBodyLeft() {

		int yPos = 10;
		int xPos = 15;
		int I_LBL_LENGTH = 110;
		int TXT_TEXT_FIELD_LENGTH = 108;
		int I_LBL_HEIGHT = FaceContants.LABLE_HEIGHT;

		// Required Edit Label
		RequiredLabel rlblType = new RequiredLabel("*");
		rlblType.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		pnlLeft.add(rlblType);
		EditLabel lblType = new EditLabel("伝票種類");
		lblType.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		pnlLeft.add(lblType);
		// End Required Edit Label

		txtSlpType = new NumberTextWithZero("", 0,  3);
		txtSlpType.setToolTipText("伝票種類を入力して下さい。");
		txtSlpType.setBounds(xPos + I_LBL_LENGTH, yPos, 50, I_LBL_HEIGHT);
		pnlLeft.add(txtSlpType);

		
		// com.add(txtCustCode);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// Required Edit Label
		RequiredLabel rlblSlpDate = new RequiredLabel("*");
		rlblSlpDate.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		pnlLeft.add(rlblSlpDate);
		EditLabel lblSlpDate = new EditLabel("年月");
		lblSlpDate.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		pnlLeft.add(lblSlpDate);
		// End Required Edit Label
		txtSlpDate = new NumberTextWithZero("", 0,4);
		txtSlpDate.setToolTipText("年月を入力して下さい。");
		txtSlpDate.setBounds(xPos + I_LBL_LENGTH, yPos, 50, I_LBL_HEIGHT);
		AbstractAction enter = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				try {
					if (txtSlpType.isEditable()
							&& StringUtils.isValid(txtSlpType.getText()) && txtSlpDate.isEditable()
							&& StringUtils.isValid(txtSlpDate.getText())) {

						SlpVo dataVo = slpService
								.getSlpVo((txtSlpType.getText().trim()),txtSlpDate.getText().trim());

						if (dataVo != null) {
							if (EditConstants.COPY_MODE
									.equalsIgnoreCase(CUR_MODE)) {
								MessageBox.message(getFrame(), MessageConstants
										.getMstMsgVo("E0006"));
								setDefaultFirstFocus(txtSlpType);
							} else if (EditConstants.VIEW_MODE
									.equalsIgnoreCase(CUR_MODE)) {
								convertVo2Txt(dataVo);
								CUR_MODE = EditConstants.EDIT_MODE;
								setStatus(CUR_MODE);
							}
						} else {
							MessageBox.message(getContentPane(), MessageConstants
									.getMstMsgVo("E0003"));
							setDefaultFirstFocus(txtSlpType);
						}
					} else {
						MessageBox.message(getContentPane(), MessageConstants
								.getMstMsgVo("E0204"));
						if(!StringUtils.isValid(txtSlpType.getText()))
							setDefaultFirstFocus(txtSlpType);
						else if(!StringUtils.isValid(txtSlpDate.getText()))
							setDefaultFirstFocus(txtSlpDate);
					}
					oldVo = convertTxt2Vo();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		};
		txtSlpDate.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		txtSlpDate.getActionMap().put("enter", enter);
		pnlLeft.add(txtSlpDate);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblSlpName = new EditLabel("伝票種類名");
		lblSlpName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		pnlLeft.add(lblSlpName);
		// End Required Edit Label

		txtSlpName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 30);
		txtSlpName.setToolTipText("伝票種類名を入力して下さい。");
		txtSlpName.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		pnlLeft.add(txtSlpName);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblSlpNew = new EditLabel("最新値");
		lblSlpNew.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		pnlLeft.add(lblSlpNew);

		txtSlpNew = new NumberText("", 0, 6);
		txtSlpNew.setToolTipText("最新値を入力して下さい。");
		txtSlpNew.setBounds(xPos + I_LBL_LENGTH, yPos, 60, I_LBL_HEIGHT);
		pnlLeft.add(txtSlpNew);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblSlpMin = new EditLabel("最小値");
		lblSlpMin.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		pnlLeft.add(lblSlpMin);

		txtSlpMin = new NumberText("", 0, 6);
		txtSlpMin.setToolTipText("最小値を入力して下さい。");
		txtSlpMin.setBounds(xPos + I_LBL_LENGTH, yPos, 60, I_LBL_HEIGHT);
		pnlLeft.add(txtSlpMin);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblSlpMax = new EditLabel("最大値");
		lblSlpMax.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		pnlLeft.add(lblSlpMax);
		txtSlpMax = new NumberText("", 0, 6);
		txtSlpMax.setToolTipText("最大値を入力して下さい。");
		txtSlpMax.setBounds(xPos + I_LBL_LENGTH, yPos, 60, I_LBL_HEIGHT);
		pnlLeft.add(txtSlpMax);
		
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
		String nameOS =  System.getProperty("os.name");
		if(nameOS.equalsIgnoreCase("Windows 7")){
			infoPnl.setLocation(xPos, 2*yPos + 71);
		}else if(nameOS.equalsIgnoreCase("Windows XP")){
			infoPnl.setLocation(xPos, 2*yPos + 81);
		}else{
			infoPnl.setLocation(xPos, 2*yPos + 88);
		}
		
		infoPnl.setSize(430, FaceContants.LABLE_HEIGHT);
		pnlLeft.add(infoPnl);
		pnlLeft.setPreferredSize(new Dimension(455, 100));
		
		return pnlLeft;
	}

	/**
	 * 画面TABキー移動設定
	 */
	protected void setDispTabFocus() {

		List<Object> focusList = new ArrayList<Object>();

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(txtSlpName);
			focusList.add(txtSlpNew);
			focusList.add(txtSlpMin);
			focusList.add(txtSlpMax);
		} else if (EditConstants.COPY_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(txtSlpType);
			focusList.add(txtSlpDate);
		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(txtSlpName);
			focusList.add(txtSlpNew);
			focusList.add(txtSlpMin);
			focusList.add(txtSlpMax);
		} else {
			focusList.add(txtSlpType);
			focusList.add(txtSlpDate);
		}
		focusList.add(m_table);

		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}
	/* (non-Javadoc)
	 * @see com.pipe.jface.bussiness.BaseMasterLayoutFrame#isChange()
	 */
	public boolean isChange() {
		SlpVo dataVo = convertTxt2Vo();
		
		if(!SlpVo.equal(dataVo, oldVo)){
			return true;
		} else {
			return false;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initRightBody(com.pipe.jface.BasePanel
	 * )
	 */
//	@Override
//	protected void initRightBody(BasePanel pnl) {
//
//		psTable = getTable();
//
//		psTable.setSize(pnl.getWidth() - 5, pnl.getHeight() - 5);
//		psTable.getViewport().add(m_table);
//		psTable.setLocation(0, 0);
//
//		pnl.add(psTable);
//
//		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnl.getHeight()
//				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
//	}

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
	 * getTable
	 */
	private BaseScrollPane getTable() {

		BaseScrollPane tablePnl;
		m_model = new InspectTableModel(colHeadNm);

		m_table = new InspectTable(m_model) {
			private static final long serialVersionUID = 1L;
			private int[] align = { BaseLabel.LEFT, BaseLabel.LEFT,
					BaseLabel.LEFT };
			private InspectTableRenderer renderer = new InspectTableRenderer(align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row,
					int column) {
				return null;
			}
		};

//		// set data for spread
//		m_model.setData(new ArrayList<MCustVo>());

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
		// invisible Column
		for (int i = 3; i < colHeadwidth.length; i++) {
			defModel.getColumn(i).setPreferredWidth(0);
			defModel.getColumn(i).setMaxWidth(0);
		}

		// EVENT
		// add action for table: UP, DOWN,...
		initTableAction(m_table);
	    AbstractAction tabKey = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					if (txtSlpType.isEditable()) {
						txtSlpType.requestFocus();
					} else if (txtSlpName.isEditable()) {
						txtSlpName.requestFocus();
					}
				} else {
					m_table.requestFocus();
				}
		    }
		};
	    
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabKey");
	    m_table.getActionMap().put("tabKey", tabKey);	
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "shiftTab");
	    m_table.getActionMap().put("shiftTab", tabKey);
	    m_table.getTableHeader().setReorderingAllowed(false); 
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),	"enterKey");
		m_table.getActionMap().put("enterKey", tabKey);		    

		tablePnl = new BaseScrollPane(m_table);
		tablePnl.setBorder(FaceContants.PANEL_BORDER);
		tablePnl
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		m_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return tablePnl;
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
	/**
	 * @return MMemoVo
	 */
	private SlpVo convertTxt2Vo() {

		SlpVo dataVo = new SlpVo();

		dataVo.setSlpType(txtSlpType.getText().trim());
		dataVo.setSlpDate(txtSlpDate.getText().trim());
		dataVo.setSlpName(txtSlpName.getText().trim());
		dataVo.setSlpnew(NumberUtils.getIntFromString(txtSlpNew.getText().trim()));
		dataVo.setSlpmin(NumberUtils.getIntFromString(txtSlpMin.getText().trim()));
		dataVo.setSlpmax(NumberUtils.getIntFromString(txtSlpMax.getText().trim()));
		return dataVo;
		
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param dataVo
	 *            </DL>
	 */
	private void convertVo2Txt(SlpVo dataVo) {
		
		txtSlpType.setText(dataVo.getSlpType());
		txtSlpDate.setText(dataVo.getSlpDate());
		txtSlpName.setText(String.valueOf(dataVo.getSlpName()));
		txtSlpNew.setText(String.valueOf(dataVo.getSlpnew()));
		txtSlpMin.setText(String.valueOf(dataVo.getSlpmin()));
		txtSlpMax.setText(String.valueOf(dataVo.getSlpmax()));		
		lblRegisterUser.setText(dataVo.getAddUser());
		lblRegisterDate
				.setText(DateUtils.getDateWithSplit(dataVo.getAddDate()));
		lblEditUser.setText(dataVo.getLastupUser());
		lblEditDate.setText(DateUtils.getDateWithSplit(dataVo.getLastupDate()));
	}


	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	private List getTableData() {

		// get data for table
		if (slpService == null)
			slpService = new FSlpServiceImpl();

		try {

			
			lstData = slpService.getLstSlpVo();
		} catch (BizException e1) {
			e1.printStackTrace();
		}
		List retList = new ArrayList();

		for (int i = 0; i < lstData.size(); i++) {
			List<String> list = new ArrayList<String>();
			list.add(lstData.get(i).getSlpType());
			list.add(lstData.get(i).getSlpName());
			list.add(String.valueOf(lstData.get(i).getSlpnew()));
			
			list.add(lstData.get(i).getSlpDate()); //invi

//			// list.add(lstData.get(i).getCustCode()); // HD_TYPE
//			// list.add(lstData.get(i).getCust1Name()); // TRD_CODE
//			// list.add(lstData.get(i).getUseFlg()); // OLD_CODE
//			//
//			list.add(lstData.get(i).getAddUser()); // ADD_USER
//			list.add(lstData.get(i).getAddPc()); // ADD_PC
//			list.add(DateUtils.getDateWithSplit(lstData.get(i).getAddDate()));
//			// ADD_DATE
//			list.add(DateUtils.getTimeWithSplit(lstData.get(i).getAddTime()));
//			// ADD_TIME
//			list.add(lstData.get(i).getLastupUser()); // LASTUP_USER
//			list.add(lstData.get(i).getLastupPc()); // LASTUP_PC
//			list
//					.add(DateUtils.getDateWithSplit(lstData.get(i)
//							.getLastupDate()));
//			// LASTUP_DATE
//			list
//					.add(DateUtils.getTimeWithSplit(lstData.get(i)
//							.getLastupTime()));
//			// LASTUP_TIME

			retList.add(list);
		}

		return retList;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param mode
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#setStatus(java.lang.String)
	 */
	protected void setStatus(String mode) {

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)
				|| EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			txtSlpType.setEditable(false);
			txtSlpDate.setEditable(false);
			txtSlpName.setEditable(true);
			txtSlpNew.setEditable(true);
			txtSlpMin.setEditable(true);
			txtSlpMax.setEditable(true);			

			if (isRequireResetTabDispFocus) {
				setDispTabFocus();
				setDefaultFirstFocus(txtSlpName);
			}
			setScreenMode(CUR_MODE);
		} else {
			txtSlpType.setEditable(true);
			txtSlpDate.setEditable(true);
			txtSlpName.setEditable(false);
			txtSlpNew.setEditable(false);
			txtSlpMin.setEditable(false);
			txtSlpMax.setEditable(false);	

			if (isRequireResetTabDispFocus) {
				setDispTabFocus();
				setDefaultFirstFocus(txtSlpType);
			}

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
	private void resetData() {
		txtSlpType.setText("");
		txtSlpDate.setText("");
		txtSlpName.setText("");
		txtSlpNew.setText("");
		txtSlpMin.setText("");
		txtSlpMax.setText("");
		lblRegisterUser.setText("");
		lblRegisterDate.setText("");
		lblEditUser.setText("");
		lblEditDate.setText("");

		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(EditConstants.VIEW_MODE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doDoubleClick(int)
	 */
	@Override
	protected void doDoubleClick(int row) {
		if(row < 0)
			return;
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
	@Override
	protected void doSingleClick(int row) {
		if(row < 0)
			return;
		// 修正時は、何にもしません。
		if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE) == false) {
			return;
		}

		String slpType = "";
		String slpDate = "";

		if (lstData != null && lstData.size() > 0 && row >= 0) {
			for (int i = 0; i < m_table.getColumnCount(); i++) {
				if (m_table.getColumnName(i).equals(colHeadNm[0])) {
					slpType = m_table.getValueAt(row, i).toString();
					break;
				}
			}
			for (int i = 0; i < m_table.getColumnCount(); i++) {
				if (m_table.getColumnName(i).equals(colHeadNm[3])) {
					slpDate = m_table.getValueAt(row, i).toString();
					break;
				}
			}
		}

		SlpVo selectedVo = new SlpVo();

		for (int i = 0; i < lstData.size(); i++) {
			if (slpType.equalsIgnoreCase(lstData.get(i).getSlpType()) && slpDate.equalsIgnoreCase(lstData.get(i).getSlpDate())) {
				selectedVo = lstData.get(i);
				break;
			}
		}
		oldVo = convertTxt2Vo();
		convertVo2Txt(selectedVo);
		SwingUtils.scrollRowColumn(m_table, row, 0);
	}
	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu) == false) 
			{
				//Not permission show red text and disable btn
				lblPermission.setText(NameConstants.getName("UPDFLG"+"1"));
				lblPermission.setBorder(null);
			}
		
	}

	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}	
	
	/**
	 * 
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>build layout for middle area (containing left and center panel)</DD>
	 * <BR>
	 * </DL>
	 */
	protected void buildMiddle() {
		BasePanel middle = new BasePanel();
		GroupLayout layout = new GroupLayout(middle);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(5)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
					.addComponent(pnlLeft))
				.addGap(7)
				.addComponent(pnlCenter)
				.addGap(5));
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(pnlLeft)
					.addComponent(pnlCenter)
				);

		middle.setLayout(layout);
		mainPanel.add(middle, BorderLayout.CENTER);
	}
	
	@Override
	protected void setFrameSize() {
		X_FRAME_LENGTH = 800;
		Y_FRAME_LENGTH = 600;
		setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		setPreferredSize(new Dimension(X_FRAME_LENGTH , Y_FRAME_LENGTH));
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		setLocation((maximumWindowBounds.width - X_FRAME_LENGTH) / 2, (maximumWindowBounds.height - Y_FRAME_LENGTH) / 2);
	}
}