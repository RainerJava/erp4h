												/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：NameFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/02/15
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.sapp.system.mname;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.text.NumberText;
import com.fas.service.system.mname.MNameService;
import com.fas.service.system.mname.MNameServiceImpl;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.mname.MNameVo;
import com.fas.vo.mnamecls.MNameclsVo;

/**
 * @author PC12
 * 
 */
public class MNameFrame extends BaseMasterLayoutFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private CdInputText txtNameclsCode;
	/** */
	private BaseLabel lblNameclsName;
	/** */
	private CdInputText txtNameCode;
	/** */
	private BaseInputText txtNameName;
	/** */
	private BaseInputText txtNameRName;
	/** */
	private BaseInputText txtNamePName;
	/** */
	private BaseInputText txtNameTName;
	/** */
	private EditLabel lblNameMei;
	/** */
	private EditLabel lblNameRName;
	/** */
	private EditLabel lblNamePName;
	/** */
	private EditLabel lblNameTName;
	/** */
	private InspectRadioButton rdoNameTypeM; 
	private InspectRadioButton rdoNameTypeF; 
	/** */
	protected BaseCheckBox chkDefaultType;
	/** */
	private InspectRadioButton rdoDspFlg0; 
	/** */
	private InspectRadioButton rdoDspFlg1; 
	/** */
	private NumberText txtDsporderNo;
	/** */
	private BaseLabel lblStatus;
	/** */
	private BaseLabel lblNumber;
	/** */
	private BaseLabel lblRegisterUser;
	/** */
	private BaseLabel lblRegisterDate;
	/** */
	private BaseLabel lblEditUser;
	/** */
	private BaseLabel lblEditDate;
	/** */
	private BasePanel infoPnl ;
	/** */
	private BaseLabel lblPermission;
	/** */
	private BaseScrollPane psTable;
	/** */
	private int iCount = 0;
	/** Spread */
	protected InspectTable m_master_table;
	/** */
	protected InspectTableModel m_master_model;
	/** */
	protected InspectTable m_detail_table;
	/** */
	protected InspectTableModel m_detail_model;
	/** */
	protected List<MNameclsVo> lstMaterData;
	/** */
	protected List<MNameVo> lstDetailData;
	/** */
	protected MNameVo beforeMNameVo;
	/** */
	protected MNameVo affterMNameVo;
	/** */
	protected BasePanel tblPnl;
	/** */
	//protected MNameVo dataVoDel ;

	/** テーブルヘッダーサイズ */
	private static final String colMasterHeadNm[] = { "Code", "種別名" };
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private static final int[] colMasterHeadwidth = { 0, 145 };

	/** テーブルヘッダーサイズ */
	private static final String colDetailHeadNm[] = { "CD", "名称", "*"};
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private static final int[] colDetailHeadwidth = { 50, 200, 35 };

	/** */
	static Logger logger = Logger.getLogger(MNameFrame.class);
	/** */
	private int ROW_PER_PAGE;
	/** */
	private MNameService mNameService;

	@Override
	protected String getHelpInfor() {
		return "";
	}

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
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public MNameFrame(BaseFrame frame, String title) {
		super(frame, title);
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
	public MNameFrame(BaseFrame frame) {
		super(frame);
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
	public MNameFrame() {
		super();
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
		mNameService = new MNameServiceImpl();
		X_FRAME_LENGTH = 850;
		getHeader();
		getBodyLeft();
		getBodyCenter();
		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(CUR_MODE);
		setDispTabFocus();
		doFirst();
		resetDetailData();
		btnF4.setText("");
		btnF4.setEnabled(false);	
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD>
	* 			SetScreenMode check user's permission
	* 			if user have not permission Insert, Update, Delete
	* 			F2(Copy), F6(Delete), F8(Save) not Enable
	* 		</DD>
	* <BR>
	* @param mode
	* </DL>
	*/
	private void setScreenMode(String mode) 
	{
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode) ) {
			lblStatus.setText(EditConstants.LBL_EDIT_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
			
			//Check permission to enable F2 F6 F8
			if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu)) {
				btnF2.setEnabled(true);
				btnF2.setAffterDoDisable(true);				
				btnF6.setEnabled(true);
				btnF6.setAffterDoDisable(true);
				btnF8.setEnabled(true);
				btnF8.setAffterDoDisable(true);
			}
			
			btnF10.setEnabled(false);
			btnF12.setConfirmRerquired(true);
			m_master_table.setEnabled(false);
			m_detail_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlCenter.setAlpha(0.5f);
			tblPnl.setAlpha(0.5f);
			
		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			
			btnF2.setEnabled(false);			
			btnF6.setEnabled(false);
			//Check permission to enable F8
			if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu)) {
				btnF8.setEnabled(true);
			}			
			btnF10.setEnabled(false);
			btnF12.setConfirmRerquired(true);
			m_master_table.setEnabled(false);
			m_detail_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlCenter.setAlpha(0.5f);
			tblPnl.setAlpha(0.5f);
		} 
		else if (EditConstants.COPY_MODE.equalsIgnoreCase(mode)) 
		{
			lblStatus.setText(EditConstants.LBL_COPY_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF2.setEnabled(false);			
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);
			btnF10.setEnabled(false);
			btnF12.setConfirmRerquired(true);
			m_master_table.setEnabled(false);
			m_detail_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlCenter.setAlpha(0.5f);
			tblPnl.setAlpha(0.5f);
		} else {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF2.setEnabled(false);			
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);
			btnF10.setEnabled(true);
			btnF12.setConfirmRerquired(false);
			m_master_table.setEnabled(true);
			m_detail_table.setEnabled(true);
			btnFirst.setEnabled(true);
			btnNext.setEnabled(true);
			btnPre.setEnabled(true);
			btnLast.setEnabled(true);
			pnlCenter.setAlpha(1f);
			tblPnl.setAlpha(1f);
		}		
	}
	
	@Override
	protected void doF1() {
		//If not exist element in DB show error E0001
		try {
			if(mNameService.countNameclsVo() <= 0)
			{
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
					mNameService.exportEXCEL(strFilePath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} catch (BizException e) {
			logger.error(e.getMessage());
			MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0123"));
		}
	}

	@Override
	protected void doF2() {
		if (btnF2.isEnabled()) {
			CUR_MODE = EditConstants.COPY_MODE;
			txtNameCode.setText("");
			setStatus(CUR_MODE);
			txtNameCode.requestFocus();
		}
	}

	@Override
	protected void doF3() {
	}

	@Override
	protected void doF4() {
	}

	@Override
	protected void doF5() {
	}

	@Override
	protected void doF6() {
		btnF6.setAffterDoDisable(false);
		try {
			if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
				if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0003")) == MessageBox.YES) {
					MNameVo dataVo = convertTxt2Vo();
					mNameService.deleteVo(dataVo);
					ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(), LogContants.LOGACT_DE, "名称コード: " + StringUtils.trimString(txtNameCode.getText()));
					//resetData();
					doF10();
					//dataVoDel = NameConstants.getMNameVo(txtNameclsCode.getText() + txtNameCode.getText().trim());
					masterRowChanged();
					txtNameCode.requestFocus();
				}
			}
		} catch (Exception e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void doF7() {
	}

	@Override
	protected void doF8() {
		btnF8.setAffterDoDisable(false);
		try {
			if (validateData()) {
				if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE) || EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
					if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0002")) == MessageBox.YES) {
						MNameVo dataVo = convertTxt2Vo();
						if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
							mNameService.updateVo(dataVo);
							ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(), LogContants.LOGACT_UP, "名称コード: " + StringUtils.trimString(txtNameCode.getText()));
						} else {
							mNameService.insertVo(dataVo);
							ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(), LogContants.LOGACT_AD, "名称コード: " + StringUtils.trimString(txtNameCode.getText()));
						}
						doF10();
						lstDetailData = mNameService.getAll();
						m_master_table.changeSelection(0, 0, false, false);
						masterRowChanged();
						txtNameCode.requestFocus();
						NameConstants.setMapNameVo(mNameService.getMapNameVo());
					}
				}
			}else{
				//if(comp != null)comp.requestFocus();
				btnF8.setAffterDoDisable(false);
			}
		} catch (Exception e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void doF9() {
	}

	@Override
	protected void doF10() {
		setTitleInfor();
		m_master_model.setData(getMasterData());
		m_master_model.fireTableDataChanged();
		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(CUR_MODE);
		m_detail_table.repaint();
		m_detail_table.requestFocus();		
		resetDetailData();
		txtNameCode.requestFocus();
	}

	@Override
	protected void doF11() {
		affterMNameVo = convertTxt2Vo();
		if (!EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			if(compMNameVo(beforeMNameVo,affterMNameVo)){
				resetDetailData();
			}else{
				if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
					resetDetailData();
				}
			}
		} else {				
			resetDetailData();
		}
		txtNameCode.requestFocus();
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
		JComponent comp = null;
		boolean isValid = true;
		
		if (!StringUtils.isValid(txtNameclsCode.getText().trim())) {
			isValid = false;
			comp = txtNameclsCode;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("名称種別コード"));
		}

		if (isValid && !StringUtils.isValid(txtNameCode.getText().trim())) {
			isValid = false;
			comp = txtNameCode;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("名称コード"));
		}

		if (isValid && !StringUtils.checkLenString(txtNameCode.getText().trim(), 5)) {
			isValid = false;
			comp = txtNameCode;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor("名称コード"));
		}
		if (isValid && !StringUtils.isValid(txtNameName.getText().trim())) {
			isValid = false;
			comp = txtNameName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(lblNameMei.getText()));
		}

		if (isValid && !StringUtils.checkLenString(txtNameName.getText().trim(), 40)) {
			isValid = false;
			comp = txtNameName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblNameMei.getText()));
		}
		
		if (isValid && !StringUtils.isValid(txtNameRName.getText().trim())) {
			isValid = false;
			comp = txtNameRName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(lblNameRName.getText()));
		}
		if (isValid &&!StringUtils.checkLenString(txtNameRName.getText().trim(), 20)) {
			isValid = false;
			comp = txtNameRName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblNameRName.getText()));
		}
		
		if (isValid && !StringUtils.isValid(txtNamePName.getText().trim())) {
			isValid = false;
			comp = txtNamePName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(lblNamePName.getText()));
		}
		if (isValid && !StringUtils.checkLenString(txtNamePName.getText().trim(), 10)) {
			isValid = false;
			comp = txtNamePName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblNamePName.getText()));
		}
		
		if (isValid && !StringUtils.isValid(txtNameTName.getText().trim())) {
			isValid = false;
			comp = txtNameTName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor(lblNameTName.getText()));
		}
		if (isValid && !StringUtils.checkLenString(txtNameTName.getText().trim(), 4)) {
			isValid = false;
			comp = txtNameTName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129").setExtraInfor(lblNameTName.getText()));
		}
		
		if (comp != null) {
			setDefaultFirstFocus(comp);
		}

		return isValid;
	}

	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };
		fBtn[3] = false;
		fBtn[5] = false;
		fBtn[7] = false;
		fBtn[9] = false;
		return fBtn;
	}

	@Override
	protected void doFirst() {
		if (m_master_table.getRowCount() > 0) {
			int selectIndex = 0;
			m_master_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	@Override
	protected void doLast() {
		if (m_master_table.getRowCount() > 0) {
			int selectIndex = m_master_table.getRowCount();
			m_master_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	@Override
	protected void doNext() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight() / FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_master_table.getRowCount() > 0) {
			int selectIndex = m_master_table.getSelectedRow();
			selectIndex = selectIndex + ROW_PER_PAGE;
			
			if (selectIndex > m_master_table.getRowCount())
				selectIndex = m_master_table.getRowCount();
			
			m_master_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	@Override
	protected void doPre() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight() / FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_master_table.getRowCount() > 0) {

			int selectIndex = m_master_table.getSelectedRow();
			selectIndex = selectIndex - ROW_PER_PAGE;

			if (selectIndex < 0)
				selectIndex = 0;

			m_master_table.changeSelection(selectIndex, 0, false, false);
		}
	}
	@Override
	protected BasePanel getHeader() {
	

		int X_WIDTH = 100;
		int xPos = 4;
		int yPos = 6;

		lblStatus = new BaseLabel("登　録");
		lblStatus.setBounds(new Rectangle(xPos, yPos, X_WIDTH + 20, FaceContants.LABLE_HEIGHT));
		lblStatus.setBorder(FaceContants.LINE_BORDER);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		headerPnl.add(lblStatus);

		xPos += X_WIDTH + 20;
		BaseLabel lblReg = new BaseLabel("登録件数");
		lblReg.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblReg.setBorder(FaceContants.LINE_BORDER);
		lblReg.setHorizontalAlignment(SwingConstants.CENTER);
		lblReg.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		headerPnl.add(lblReg);

		xPos += X_WIDTH;
		lblNumber = new BaseLabel("");
		lblNumber.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblNumber.setBorder(FaceContants.LABEL_BORDER);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		headerPnl.add(lblNumber);

		xPos += X_WIDTH + 20;
		lblPermission = new BaseLabel();
		lblPermission.setForeground(Color.RED);
		lblPermission.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.LEFT);

		headerPnl.add(lblPermission);
		headerPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));
		headerPnl.setLayout( new BorderLayout());
		mainPanel.add(headerPnl,BorderLayout.PAGE_START);
		
		setTitleInfor();
		return headerPnl;
	}

	private void setTitleInfor() {

		try {
			lblNumber.setText("");
			lblNumber.setText(mNameService.countNameclsVo() + "件");
		} catch (BizException e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	protected BasePanel getBodyLeft(){
		int topY = 5;
		int xPos = 15;
		int I_LBL_LENGTH = 120;
		int I_LBL_LENGTH1 = 290;
		int TXT_TEXT_FIELD_LENGTH = 108;
		int TXT_TEXT_FIELD_LENGTH_80 = 80;
		int TXT_TEXT_FIELD_LENGTH_120 = 120;
		int TXT_TEXT_FIELD_LENGTH_180 = 180;
		//BasePanel com = new BasePanel();
		RequiredLabel lblAsteriskNameclsCd = new RequiredLabel("*");
		lblAsteriskNameclsCd.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameclsCd);

		EditLabel lblNameclsCd = new EditLabel("名称種別コード");
		lblNameclsCd.setLocation(xPos, topY);
		lblNameclsCd.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameclsCd);
		txtNameclsCode = new CdInputText("", 0, CdInputText.IM_OFF, 8);
		txtNameclsCode.setToolTipText("名称種別コードを入力して下さい。");
		txtNameclsCode.setLocation(xPos + I_LBL_LENGTH, topY);
		txtNameclsCode.setSize(new Dimension(100, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtNameclsCode);
		
		xPos += 100 + I_LBL_LENGTH;
		lblNameclsName = new BaseLabel();
		lblNameclsName.setLocation(xPos, topY);
		lblNameclsName.setSize(new Dimension(190, FaceContants.LABLE_HEIGHT - 1));
		pnlLeft.add(lblNameclsName);

		xPos = 15;
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		
		RequiredLabel lblAsteriskNameCd = new RequiredLabel("*");
		lblAsteriskNameCd.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameCd);
		
		EditLabel lblNameCd = new EditLabel("名称コード");
		lblNameCd.setLocation(xPos, topY);
		lblNameCd.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameCd);
		txtNameCode = new CdInputText("", 0, CdInputText.IM_OFF, 5);
		txtNameCode.setToolTipText("名称コードを入力して下さい。");
		txtNameCode.setLocation(xPos + I_LBL_LENGTH, topY);
		txtNameCode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_80, FaceContants.LABLE_HEIGHT));
		
		AbstractAction enter = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				try {
					if (txtNameCode.isEditable() && StringUtils.isValid(txtNameCode.getText()) && StringUtils.isInteger(txtNameCode.getText())) {

						MNameVo dataVo = mNameService.getByKey(txtNameclsCode.getText() , txtNameCode.getText().trim());
						//NameConstants.setMapMNameVo(mNameService.getMapMNameVo());
						
						if (dataVo != null) {
							if (EditConstants.COPY_MODE.equalsIgnoreCase(CUR_MODE)) {
								MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0006"));
								setDefaultFirstFocus(txtNameCode);
							} else if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
								convertDetailVo2Txt(dataVo);
								CUR_MODE = EditConstants.EDIT_MODE;
								setStatus(CUR_MODE);
								beforeMNameVo = convertTxt2Vo();
							}
						} else {
							CUR_MODE = EditConstants.NEW_MODE;
							setStatus(EditConstants.NEW_MODE);
						}
					}
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		};
		txtNameCode.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		txtNameCode.getActionMap().put("enter", enter);
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (txtNameCode.isFocusOwner()) {
					m_master_table.requestFocus();
				} 
			}
		};
		AbstractAction tabKeyShift = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (txtNameCode.isFocusOwner()) {
					m_detail_table.requestFocus();
				} 
			}
		};
		txtNameCode.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		txtNameCode.getActionMap().put("tabKey", tabKey);
		txtNameCode.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		txtNameCode.getActionMap().put("shiftTab", tabKeyShift);
	
		pnlLeft.add(txtNameCode);
		
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskNameM = new RequiredLabel("*");
		lblAsteriskNameM.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameM);

		lblNameMei = new EditLabel(NameConstants.getName("NAMETEXT"+"1"));
		lblNameMei.setLocation(xPos, topY);
		lblNameMei.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameMei);

		txtNameName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 40);
		txtNameName.setToolTipText(NameConstants.getRName("NAMETEXT"+"1")+"を入力して下さい。");
		txtNameName.setLocation(xPos + I_LBL_LENGTH, topY);
		txtNameName.setSize(new Dimension(I_LBL_LENGTH1, FaceContants.LABLE_HEIGHT));
		txtNameName.addFocusListener(new java.awt.event.FocusAdapter() 
		{
            public void focusLost(java.awt.event.FocusEvent evt) 
            {
            	if(txtNameRName.getText().equals("")) {
            		int iMaxLen = txtNameRName.getMaxLength();
            		txtNameRName.setText(StringUtils.subStringUseEncode(txtNameName.getText().trim(), 0, iMaxLen));
            	}
            	copyName();
            }
        });
		pnlLeft.add(txtNameName);

		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskNameR = new RequiredLabel("*");
		lblAsteriskNameR.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameR);
		
		lblNameRName = new EditLabel(NameConstants.getName("NAMETEXT"+"2"));
		lblNameRName.setLocation(xPos, topY);
		lblNameRName.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameRName);

		txtNameRName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 20);
		txtNameRName.setToolTipText(NameConstants.getRName("NAMETEXT"+"2")+ "を入力して下さい。");
		txtNameRName.setLocation(xPos + I_LBL_LENGTH, topY);
		txtNameRName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtNameRName);
		
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskNameP = new RequiredLabel("*");
		lblAsteriskNameP.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameP);
		
		lblNamePName = new EditLabel(NameConstants.getName("NAMETEXT"+"3"));
		lblNamePName.setLocation(xPos, topY);
		lblNamePName.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNamePName);

		txtNamePName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 10);
		txtNamePName.setToolTipText(NameConstants.getRName("NAMETEXT"+"3") + "を入力して下さい。");
		txtNamePName.setLocation(xPos + I_LBL_LENGTH, topY);
		txtNamePName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtNamePName);
		
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskNameT = new RequiredLabel("*");
		lblAsteriskNameT.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameT);
		
		lblNameTName = new EditLabel(NameConstants.getName("NAMETEXT"+"4"));
		lblNameTName.setLocation(xPos, topY);
		lblNameTName.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameTName);

		txtNameTName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 4);
		txtNameTName.setToolTipText(NameConstants.getRName("NAMETEXT"+"4")+ "を入力して下さい。");
		txtNameTName.setLocation(xPos + I_LBL_LENGTH, topY);
		txtNameTName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_120, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtNameTName);
		
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskNameType = new RequiredLabel("*");
		lblAsteriskNameType.setBounds(new Rectangle(xPos - 10, topY, 10, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskNameType);
		
		EditLabel lblNameType = new EditLabel("名称分類");
		lblNameType.setLocation(xPos, topY);
		lblNameType.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameType);

		ButtonGroup groupTemp = new ButtonGroup();
		rdoNameTypeM = new InspectRadioButton("名称", false);
		rdoNameTypeM.setBounds(new Rectangle(xPos + I_LBL_LENGTH, topY, 100, FaceContants.TEXT_HEIGHT));
		rdoNameTypeM.setToolTipText("名称分類を選択して下さい。");
		groupTemp.add(rdoNameTypeM);
		
		rdoNameTypeF = new InspectRadioButton("フラグ", false);
		rdoNameTypeF.setBounds(new Rectangle(xPos + 300, topY, 100, FaceContants.TEXT_HEIGHT));
		rdoNameTypeF.setToolTipText("名称分類を選択して下さい。");
		groupTemp.add(rdoNameTypeF);
		pnlLeft.add(rdoNameTypeM);
		pnlLeft.add(rdoNameTypeF);		

		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblDefaultType = new EditLabel("デフォルト区分");
		lblDefaultType.setLocation(xPos, topY);
		lblDefaultType.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblDefaultType);

		chkDefaultType = new BaseCheckBox("デフォルトにする");
		chkDefaultType.setToolTipText("デフォルトを選択して下さい。");
		chkDefaultType.setLocation(xPos + I_LBL_LENGTH, topY);
		chkDefaultType.setSize(new Dimension(200, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(chkDefaultType);
		
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblNameDspF = new EditLabel("表示区分");
		lblNameDspF.setLocation(xPos, topY);
		lblNameDspF.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblNameDspF);

		ButtonGroup groupDsp = new ButtonGroup();
		rdoDspFlg0 = new InspectRadioButton("表示する", false);
		rdoDspFlg0.setToolTipText("表示区分を選択して下さい。");
		rdoDspFlg0.setBounds(new Rectangle(xPos + I_LBL_LENGTH, topY, 100, FaceContants.TEXT_HEIGHT));
		groupDsp.add(rdoDspFlg0);
		
		rdoDspFlg1 = new InspectRadioButton("表示しない", false);
		rdoDspFlg1.setBounds(new Rectangle(xPos + 300, topY, 100, FaceContants.TEXT_HEIGHT));
		rdoDspFlg1.setToolTipText("表示区分を選択して下さい。");
		groupDsp.add(rdoDspFlg1);
		pnlLeft.add(rdoDspFlg0);
		pnlLeft.add(rdoDspFlg1);
		
		topY += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblDspOrderNo = new EditLabel("表示順序");
		lblDspOrderNo.setLocation(xPos, topY);
		lblDspOrderNo.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblDspOrderNo);
		txtDsporderNo = new NumberText("", 0, 3);
		txtDsporderNo.setToolTipText("表示順序を入力して下さい。");
		txtDsporderNo.setLocation(xPos + I_LBL_LENGTH, topY);
		txtDsporderNo.setSize(new Dimension(I_LBL_LENGTH1, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(txtDsporderNo);
		
		
		I_LBL_LENGTH = 63;
		TXT_TEXT_FIELD_LENGTH = 76;
		String nameOS =  System.getProperty("os.name");
		if(nameOS.equalsIgnoreCase("Windows 7")){
			topY =  Y_FRAME_LENGTH - 155 - FaceContants.LABLE_HEIGHT - 3*FaceContants.VERTICAL_SPACE;
		}else if(nameOS.equalsIgnoreCase("Windows XP")){
			topY =  Y_FRAME_LENGTH - 145 - FaceContants.LABLE_HEIGHT - 3*FaceContants.VERTICAL_SPACE;
		}else{
			topY =  Y_FRAME_LENGTH - 138 - FaceContants.LABLE_HEIGHT - 3*FaceContants.VERTICAL_SPACE;
		}
		
		infoPnl = new BasePanel();
		int xPos1 = 0;
		int yPos1 = 0;
		ViewLabel lblRegisterInfo = new ViewLabel("登録情報");
		lblRegisterInfo.setBounds(new Rectangle(xPos1, yPos1, I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblRegisterInfo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPnl.add(lblRegisterInfo);

		xPos1 += I_LBL_LENGTH;
		lblRegisterUser = new BaseLabel();
		lblRegisterUser.setVerticalAlignment(BaseLabel.CENTER);
		lblRegisterUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH - 5, FaceContants.LABLE_HEIGHT));
		lblRegisterUser.setLocation(xPos1, yPos1);
		lblRegisterUser.setText("");
		lblRegisterUser.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblRegisterUser.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblRegisterUser);

		xPos1 += TXT_TEXT_FIELD_LENGTH - 5;
		lblRegisterDate = new BaseLabel();
		lblRegisterDate.setVerticalAlignment(BaseLabel.CENTER);
		lblRegisterDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH + 5, FaceContants.LABLE_HEIGHT));
		lblRegisterDate.setLocation(xPos1, yPos1);
		lblRegisterDate.setText("");
		lblRegisterDate.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblRegisterDate.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblRegisterDate);

		xPos1 += TXT_TEXT_FIELD_LENGTH + 5;
		ViewLabel lblEditInfo = new ViewLabel("更新情報");
		lblEditInfo.setBounds(new Rectangle(xPos1, yPos1, I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblEditInfo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPnl.add(lblEditInfo);

		xPos1 += I_LBL_LENGTH;
		lblEditUser = new BaseLabel();
		lblEditUser.setVerticalAlignment(BaseLabel.CENTER);
		lblEditUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH - 5, FaceContants.LABLE_HEIGHT));
		lblEditUser.setLocation(xPos1, yPos1);
		lblEditUser.setText("");
		lblEditUser.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblEditUser.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblEditUser);

		xPos1 += TXT_TEXT_FIELD_LENGTH - 5;
		lblEditDate = new BaseLabel();
		lblEditDate.setVerticalAlignment(BaseLabel.CENTER);
		lblEditDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH + 5, FaceContants.LABLE_HEIGHT));
		lblEditDate.setLocation(xPos1, yPos1);
		lblEditDate.setText("");
		lblEditDate.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		lblEditDate.setBorder(FaceContants.LABEL_BORDER);
		infoPnl.add(lblEditDate);
		infoPnl.setLocation(xPos, topY);
		infoPnl.setSize(430, FaceContants.LABLE_HEIGHT );
		
		pnlLeft.add(infoPnl);
		
		topY = 0;
		// 
		tblPnl = new BasePanel();
		tblPnl.setSize(180,  Y_FRAME_LENGTH - Y_HEADER_LENGTH - Y_FOOTER_LENGTH - 70 -3*FaceContants.VERTICAL_SPACE);
		tblPnl.setLocation(450, topY);
		//
		
		psTable = getMasterTable();
		psTable.setSize(180,  Y_FRAME_LENGTH - Y_HEADER_LENGTH - Y_FOOTER_LENGTH - 70 -3*FaceContants.VERTICAL_SPACE);
		psTable.getViewport().add(m_master_table);
		psTable.setLocation(0, 0);
		tblPnl.add(psTable);
		pnlLeft.add(tblPnl);
		pnlLeft.setPreferredSize(new Dimension(640, 100));
		pnlLeft.setLayout( new BorderLayout());
		// add table
		mainPanel.add(pnlLeft,BorderLayout.LINE_START);
		
		return pnlLeft;

	}
	@Override
	protected BasePanel getBodyCenter() {
		// TODO Auto-generated method stub
		BaseScrollPane psTableDetail = getDetailTable();
		pnlCenter.setLayout(new GridLayout(1,1));
		pnlCenter.add(psTableDetail,createBothConstraints(0, 1, 1, 1, 1, 1));
		mainPanel.add(pnlCenter,BorderLayout.CENTER);
		// event
		pnlCenter.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e)
			{  
				if( iCount!=0){
					int topY =  pnlCenter.getHeight()- FaceContants.LABLE_HEIGHT - 2*FaceContants.VERTICAL_SPACE;
					infoPnl.setLocation(15, topY);
					psTable.setSize(180,  pnlCenter.getHeight() -2*FaceContants.VERTICAL_SPACE);
					tblPnl.setSize(180,  pnlCenter.getHeight() -2*FaceContants.VERTICAL_SPACE);
					psTable.revalidate();
				}
				iCount++;
			}
		   });		
		return pnlCenter;
	}
	/**
	 * 画面TABキー移動設定
	 */
	protected void setDispTabFocus() {

		List<Object> focusList = new ArrayList<Object>();

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
			// フォーカスの設定
			focusList.add(txtNameName);
			focusList.add(txtNameRName);
			focusList.add(txtNamePName);
			focusList.add(txtNameTName);
			focusList.add(rdoNameTypeM);
			focusList.add(rdoNameTypeF);
			focusList.add(chkDefaultType);
			focusList.add(rdoDspFlg0);
			focusList.add(rdoDspFlg1);
			focusList.add(txtDsporderNo);
			focusList.add(m_master_table);
			focusList.add(m_detail_table);
		} else if (EditConstants.COPY_MODE.equalsIgnoreCase(CUR_MODE)) {
			// フォーカスの設定
			focusList.add(txtNameCode);
			focusList.add(m_detail_table);
		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			// フォーカスの設定
			focusList.add(txtNameName);
			focusList.add(txtNameRName);
			focusList.add(txtNamePName);
			focusList.add(txtNameTName);
			focusList.add(rdoNameTypeM);
			focusList.add(rdoNameTypeF);
			focusList.add(chkDefaultType);
			focusList.add(rdoDspFlg0);
			focusList.add(rdoDspFlg1);
			focusList.add(txtDsporderNo);
		} else {
			// フォーカスの設定
			focusList.add(txtNameCode);
			focusList.add(m_master_table);
			focusList.add(m_detail_table);
		}
		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}
	/**
	 * getTable for TableHeader
	 */
	private BaseScrollPane getMasterTable() {

		BaseScrollPane tablePnl;
		
		m_master_model = new InspectTableModel(colMasterHeadNm);

		m_master_table = new InspectTable(m_master_model) {
			/** */
			private static final long serialVersionUID = 1L;
			private int[] align = { 
									 BaseLabel.RIGHT 
									, BaseLabel.LEADING 
			};
			private InspectTableRenderer renderer = new InspectTableRenderer(align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row, int column) {
				return null;
			}
		};

		m_master_model.setData(getMasterData());
		m_master_model.fireTableDataChanged();
		m_master_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_master_table.getColumnModel();

		for (int i = 0; i < colMasterHeadNm.length; i++) {
			defModel.getColumn(i).setMinWidth(colMasterHeadwidth[i]);
			defModel.getColumn(i).setWidth(colMasterHeadwidth[i]);
			defModel.getColumn(i).setMaxWidth(colMasterHeadwidth[i] + 200);
		}
		m_master_table.getTableHeader().setReorderingAllowed(false);
		// invisible Column Code
		defModel.getColumn(0).setPreferredWidth(0);
		defModel.getColumn(0).setMaxWidth(0);

		// EVENT
		//initTableAction(m_master_table);
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_master_table.isFocusOwner()) {
					m_detail_table.requestFocus();
				} else {
					m_master_table.requestFocus();
				}
			}
		};
		
		AbstractAction tabKeyShift = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_master_table.isFocusOwner()) {
					if (txtNameCode.isEditable()) {
						txtNameCode.requestFocus();
					} else if (txtNameName.isEditable()) {
						txtNameName.requestFocus();
					}
				} 
			}
		};
		
		m_master_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		m_master_table.getActionMap().put("tabKey", tabKey);
		m_master_table.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_master_table.getActionMap().put("shiftTab", tabKeyShift);
		m_master_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),	"enterKey");
		m_master_table.getActionMap().put("enterKey", tabKey);			
		// mouse click
		m_master_table.addMouseListener(new MasterTableMouseClick());
		// change selected row
		ListSelectionModel rowSM = m_master_table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				masterRowChanged();
			}
		});

		tablePnl = new BaseScrollPane(m_master_table);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePnl.repaint();
		return tablePnl;
	}

	/**
	 * @author PC12
	 * 
	 */
	private class MasterTableMouseClick implements MouseListener {

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if (m_master_table.getRowCount() < 1)
				return;

			int row = m_master_table.getSelectedRow();

			if (e.getClickCount() == 1) {

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
	 * テーブルへデータをセット
	 * 
	 * convert List of Object to List of List
	 * 
	 * @return
	 */
	private List getMasterData() {

		try {
			lstMaterData = mNameService.getListNameclsVo();
			lstDetailData = mNameService.getAll();
		} catch (BizException e1) {
			e1.printStackTrace();
		}

		List retList = new ArrayList();

		for (int i = 0; i < lstMaterData.size(); i++) {
			List<String> list = new ArrayList<String>();
			list.add(lstMaterData.get(i).getNameclsCode()); // Code
			list.add(lstMaterData.get(i).getNameclsName()); // Name
			retList.add(list);
		}

		return retList;
	}

	/**
	 * テーブルへデータをセット
	 * 
	 * convert List of Object to List of List
	 * 
	 * @return
	 */
	private List getDetailData(String nameclsCode) {

		List retList = new ArrayList();

		for (int i = 0; i < lstDetailData.size(); i++) {
			List<String> list = new ArrayList<String>();

			if (nameclsCode.equalsIgnoreCase(lstDetailData.get(i).getNameclsCode())) {
				list.add(lstDetailData.get(i).getNameCode()); 		// Code
				list.add(lstDetailData.get(i).getNameName()); 		// Name
				list.add(lstDetailData.get(i).getDefaultType()); 	// Default
				retList.add(list);
			}
		}

		return retList;
	}
	/**
	 * getTable for TableHeader
	 */
	private BaseScrollPane getDetailTable() {

		BaseScrollPane tablePnl;

		m_detail_model = new InspectTableModel(colDetailHeadNm);
		m_detail_table = new InspectTable(m_detail_model) {
			private static final long serialVersionUID = 1L;
			private int[] align = { 
									 BaseLabel.LEFT
									, BaseLabel.LEADING 
									,BaseLabel.LEFT
			};
			private InspectTableRenderer renderer = new InspectTableRenderer(align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row, int column) {
				return null;
			}
		};

		//init data for detail table
		m_detail_model.setData(getDetailData(m_master_table.getValueAt(0, 0).toString()));
		m_detail_model.fireTableDataChanged();
		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_detail_table.getColumnModel();

		for (int i = 0; i < colDetailHeadNm.length; i++) {
			defModel.getColumn(i).setWidth(colDetailHeadwidth[i]);
			if (i != 1) {
				defModel.getColumn(i).setMinWidth(colDetailHeadwidth[i]);
				defModel.getColumn(i).setMaxWidth(colDetailHeadwidth[i]);
			}
		}
		m_detail_table.getTableHeader().setReorderingAllowed(false);	
		// invisible Column Code
		defModel.getColumn(colDetailHeadNm.length - 1).setPreferredWidth(0);

		// EVENT
		//initTableAction(m_detail_table);
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_detail_table.isFocusOwner()) {
					if (txtNameCode.isEditable()) {
						txtNameCode.requestFocus();
					} else if (txtNameName.isEditable()) {
						txtNameName.requestFocus();
					}
				} 
			}
		};
		AbstractAction tabKeyShift = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_detail_table.isFocusOwner()) {
					m_master_table.requestFocus();
				} 
			}
		};
		m_detail_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		m_detail_table.getActionMap().put("tabKey", tabKey);
		m_detail_table.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_detail_table.getActionMap().put("shiftTab", tabKeyShift);
		
		
		
		m_detail_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
		"enterKey");
		
		m_detail_table.getActionMap().put("enterKey", tabKey);
	
		// mouse click
		m_detail_table.addMouseListener(new DetailTableMouseClick());
//		m_detail_table.addFocusListener(new FocusAdapter() {
//		    @Override
//		    public void focusGained(FocusEvent fe) {
//		    	TableHeaderRenderer cellRenderer = new TableHeaderRenderer();
//				cellRenderer.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
//				cellRenderer.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
//				cellRenderer.setHorizontalAlignment(BaseLabel.LEFT);
//				m_detail_table.getColumn(0).setHeaderRenderer(cellRenderer);
//				m_detail_table.getColumn(1).setHeaderRenderer(cellRenderer);
//				m_detail_table.getColumn(2).setHeaderRenderer(cellRenderer);
//		    }
//		    @Override
//		    public void focusLost(FocusEvent fe) {
//		    	TableHeaderRenderer cellRenderer = new TableHeaderRenderer();
//				cellRenderer.setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
//				cellRenderer.setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
//				cellRenderer.setHorizontalAlignment(BaseLabel.LEFT);
//				m_detail_table.getColumn(0).setHeaderRenderer(cellRenderer);
//				m_detail_table.getColumn(1).setHeaderRenderer(cellRenderer);
//				m_detail_table.getColumn(2).setHeaderRenderer(cellRenderer);
//		    }
//		});
		// change selected row
		ListSelectionModel rowSM = m_detail_table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				detailRowChanged();
			}
		});

		tablePnl = new BaseScrollPane(m_detail_table);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePnl.repaint();
		m_detail_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return tablePnl;
	}

	/**
	 * @author PC12
	 * 
	 */
	private class DetailTableMouseClick implements MouseListener {
		
		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if (m_detail_table.getRowCount() < 0)
				return;
			
			if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
				return;
			}
			int row = m_detail_table.getSelectedRow();
			if (e.getClickCount() == 1) {
				detailRowChanged();
			} else {
				if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
					CUR_MODE = EditConstants.EDIT_MODE;
					setStatus(CUR_MODE);
					txtNameName.requestFocus();
					beforeMNameVo = convertTxt2Vo();
				}
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
	 * 
	 */
	protected void masterRowChanged() {
		
		if (m_master_table.getRowCount() < 1)
			return;

		int selectIndex = m_master_table.getSelectedRow();
		
		if (selectIndex < 0) selectIndex = 0;
		
		String nameclsCode = "";
		String nameclsName = "";

		for (int i = 0; i < m_master_table.getColumnCount(); i++) {
			if (m_master_table.getColumnName(i).equals(colMasterHeadNm[0])){
				nameclsCode = m_master_table.getValueAt(selectIndex, i).toString();
				
				//set data for detail table
				List detailData = new ArrayList();
				detailData = getDetailData(nameclsCode);
				
				if (detailData.size() > 0){
					m_detail_table.resetSortOrder();
					m_detail_model.setData(detailData);
					m_detail_model.fireTableDataChanged();
					m_detail_table.changeSelection(0, 0, false, false);
					//m_detail_table.addRowSelectionInterval(0, 0);
				}else{
					m_detail_model.setData(null);
					m_detail_model.fireTableDataChanged();
				}
				
			}
			else if(m_master_table.getColumnName(i).equals(colMasterHeadNm[1]))
				nameclsName = m_master_table.getValueAt(selectIndex, i).toString();
		}
		
		txtNameclsCode.setText(nameclsCode);
		lblNameclsName.setText(nameclsName);
		resetDetailData();
	}
	
	/**
	 * 
	 */
	protected void detailRowChanged() {
		
		if (m_master_table.getRowCount() < 1 || m_detail_table.getRowCount() < 1 )
			return;

		int selectMasterIndex = m_master_table.getSelectedRow();
		int selectDetailIndex = m_detail_table.getSelectedRow();
		
		if (selectMasterIndex < 0) selectMasterIndex = 0;
		if (selectDetailIndex < 0) selectDetailIndex = 0;
		
		String nameclsCode = "";
		String nameclsName = "";

		String nameCode = "";

		for (int i = 0; i < m_master_table.getColumnCount(); i++) {
			if (m_master_table.getColumnName(i).equals(colMasterHeadNm[0])) 
				 nameclsCode = m_master_table.getValueAt(selectMasterIndex, i).toString();
			else if(m_master_table.getColumnName(i).equals(colMasterHeadNm[1]))
				nameclsName = m_master_table.getValueAt(selectMasterIndex, i).toString();
		}
		
		for (int i = 0; i < m_detail_table.getColumnCount(); i++) {
			if (m_detail_table.getColumnName(i).equals(colDetailHeadNm[0])) {
				nameCode = m_detail_table.getValueAt(selectDetailIndex, i).toString();
				break;
			}
		}
		
		MNameVo detailVo = new MNameVo();
		
		for (int i = 0; i < lstDetailData.size(); i++) {
			if (nameclsCode.equalsIgnoreCase(lstDetailData.get(i).getNameclsCode())
					&& nameCode.equalsIgnoreCase(lstDetailData.get(i).getNameCode()))
			{
				detailVo = lstDetailData.get(i);
				break;
			}
		}
		
		txtNameclsCode.setText(nameclsCode);
		lblNameclsName.setText(nameclsName);

		convertDetailVo2Txt(detailVo);
	}
	
	/**
	 * @return
	 */
	private void convertDetailVo2Txt(MNameVo detailVo) {
		txtNameCode.setText(detailVo.getNameCode());
		txtNameName.setText(detailVo.getNameName());
		txtNameRName.setText(detailVo.getNameRname());
		txtNamePName.setText(detailVo.getNamePname());
		txtNameTName.setText(detailVo.getNameTname());
		
		if ("M".equalsIgnoreCase(detailVo.getNametype()))
			rdoNameTypeM.setSelected(true);
		else
			rdoNameTypeF.setSelected(true);
		
		if("*".equalsIgnoreCase(detailVo.getDefaultType()))
			chkDefaultType.setSelected(true);
		else
			chkDefaultType.setSelected(false);
		
		if ("1".equalsIgnoreCase(detailVo.getDspFlg()))
			rdoDspFlg1.setSelected(true);
		else
			rdoDspFlg0.setSelected(true);
		
		txtDsporderNo.setText("" + detailVo.getDsporderNo());
			
		lblRegisterUser.setText(detailVo.getAddUser());
		lblRegisterDate.setText(DateUtils.getDateWithSplit(detailVo.getAddDate()));
		lblEditUser.setText(detailVo.getLastupUser());
		lblEditDate.setText(DateUtils.getDateWithSplit(detailVo.getLastupDate()));
	}
	
	/**
	 * @return MNameVo
	 */
	private MNameVo convertTxt2Vo() {

		MNameVo dataVo = new MNameVo();

		dataVo.setNameclsCode(txtNameclsCode.getText().trim());
		dataVo.setNameCode(txtNameCode.getText().trim());
		dataVo.setNameName(txtNameName.getText().trim());
		dataVo.setNameRname(txtNameRName.getText().trim());
		dataVo.setNamePname(txtNamePName.getText().trim());
		dataVo.setNameTname(txtNameTName.getText().trim());
		if (rdoNameTypeM.isSelected()){
			dataVo.setNametype("M");
		}else
			dataVo.setNametype("F");
		
		if (chkDefaultType.isSelected())
			dataVo.setDefaultType("1");
		else
			dataVo.setDefaultType("0");
		
		if (rdoDspFlg1.isSelected())
			dataVo.setDspFlg("1");
		else
			dataVo.setDspFlg("0");
		
		if(StringUtils.isInteger(txtDsporderNo.getText()))
			dataVo.setDsporderNo(Integer.parseInt(txtDsporderNo.getText().trim()));

		return dataVo;
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
	private void setStatus(String mode) {
		
		txtNameclsCode.setEditable(false);
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode) || EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			txtNameCode.setEditable(false);
			txtNameName.setEditable(true);
			txtNameRName.setEditable(true);
			chkDefaultType.setEnabled(true);
			rdoNameTypeM.setEnabled(true);
			rdoNameTypeF.setEnabled(true);
			chkDefaultType.setEnabled(true);
			rdoDspFlg0.setEnabled(true);
			rdoDspFlg1.setEnabled(true);
			txtDsporderNo.setEditable(true);
			
			m_master_table.setEnabled(false);
			m_detail_table.setEnabled(false);
			
//			btnF2.setEnabled(true);
//			btnF6.setEnabled(true);
//			btnF8.setEnabled(true);
			
			setScreenMode(CUR_MODE);
			setDispTabFocus();
			txtNameName.requestFocus();
		} else {
			txtNameCode.setEditable(true);
			txtNameName.setEditable(false);
			txtNameRName.setEditable(false);
			chkDefaultType.setEnabled(false);
			rdoNameTypeM.setEnabled(false);
			rdoNameTypeF.setEnabled(false);
			chkDefaultType.setEnabled(false);
			rdoDspFlg0.setEnabled(false);
			rdoDspFlg1.setEnabled(false);
			txtDsporderNo.setEditable(false);
			
			m_master_table.setEnabled(true);
			m_detail_table.setEnabled(true);
			
//			btnF2.setEnabled(false);
//			btnF6.setEnabled(false);
//			btnF8.setEnabled(false);
			setScreenMode(CUR_MODE);
			setDispTabFocus();
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
	@SuppressWarnings("unused")
	private void resetData() {
		m_master_table.clearSelection();
		m_detail_table.clearSelection();
		txtNameclsCode.setText("");
		lblNameclsName.setText("");
		txtNameCode.setText("");
		resetDetailData();
	}
	/* (non-Javadoc)
	 * @see com.pipe.jface.bussiness.BaseMasterLayoutFrame#isChange()
	 */
	public boolean isChange() {
		
		affterMNameVo = convertTxt2Vo();
		
		if(!compMNameVo(beforeMNameVo, affterMNameVo)){
			return true;
		} else {
			return false;
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
	private void resetDetailData() {
		//m_detail_table.clearSelection();
		txtNameName.setText("");
		txtNameCode.setText("");
		txtNameRName.setText("");
		txtNamePName.setText("");
		txtNameTName.setText("");
		chkDefaultType.setSelected(false);
		rdoDspFlg0.setSelected(true);
		rdoNameTypeM.setSelected(true);
		txtDsporderNo.setText("");

		lblRegisterUser.setText("");
		lblRegisterDate.setText("");
		lblEditUser.setText("");
		lblEditDate.setText("");		
		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(EditConstants.VIEW_MODE);
		//setDefaultFirstFocus(txtNameclsCode);
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
	
	protected void copyName() {
		
		String name = txtNameName.getText().trim();
		if (txtNameRName.getText().equalsIgnoreCase("")) {
			int maxlength = 20;
			txtNameRName.setText(StringUtils.subStringUseEncode(name, 0, maxlength));	
		}
		if (txtNamePName.getText().equalsIgnoreCase("")) {
			int maxlength = 10;
			txtNamePName.setText(StringUtils.subStringUseEncode(name, 0, maxlength));		
		}
		if (txtNameTName.getText().equalsIgnoreCase("")) {
			int maxlength = 4;
			txtNameTName.setText(StringUtils.subStringUseEncode(name, 0, maxlength));		
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
	@SuppressWarnings("unused")
	private boolean compMNameVo(MNameVo beMNameVo, MNameVo affMNameVo) {
		boolean flg = true;
		if(!beMNameVo.getNameCode().equalsIgnoreCase(affMNameVo.getNameCode()))return false;
		if(!beMNameVo.getNameName().equalsIgnoreCase(affMNameVo.getNameName()))return false;
		if(!beMNameVo.getNameRname().equalsIgnoreCase(affMNameVo.getNameRname()))return false;
		if(!beMNameVo.getNamePname().equalsIgnoreCase(affMNameVo.getNamePname()))return false;
		if(!beMNameVo.getNameTname().equalsIgnoreCase(affMNameVo.getNameTname()))return false;
		
		if(!beMNameVo.getNametype().equalsIgnoreCase(affMNameVo.getNametype()))return false;
		if(!beMNameVo.getDefaultType().equalsIgnoreCase(affMNameVo.getDefaultType()))return false;
		if(!beMNameVo.getDspFlg().equalsIgnoreCase(affMNameVo.getDspFlg()))return false;
		if(! (beMNameVo.getDsporderNo() == affMNameVo.getDsporderNo()))return false;
		
		return flg;
	}
	
	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doDoubleClick(int row) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void doSingleClick(int row) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void setFrameSize() {
		X_FRAME_LENGTH = 860;
		Y_FRAME_LENGTH = 600;
		this.setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH - 70;
		X_HEADER_LENGTH = X_FRAME_LENGTH;
		Y_HEADER_LENGTH = 40;
		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 30;
	}

}
