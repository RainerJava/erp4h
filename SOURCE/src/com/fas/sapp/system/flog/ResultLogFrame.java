package com.fas.sapp.system.flog;

import java.awt.Component;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import jxl.write.WriteException;

import org.apache.log4j.Logger;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspecTableComlumnModel;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableModel;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.service.system.flog.FLogService;
import com.fas.service.system.flog.FLogServiceImpl;
import com.fas.vo.flog.FLogConditionVo;
import com.fas.vo.flog.FLogVo;

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
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: Bui Ngoc Viet</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class ResultLogFrame extends FLogFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** log */
	static Logger logger = Logger.getLogger(ResultLogFrame.class);
	/** テーブルヘッダーサイズ */
	private static final String colHeadNm[] = { "日付", "時刻", "担当者", "機能名", "操作",
			"メモ", "PC名" };
	private static final int[] colHeadwidth = { 100, 80, 100, 350, 80, 200, 100 };

	/** Table */
	protected InspectTable m_table;
	/** */
	protected InspectTableModel m_model;
	/** */
	protected List<FLogVo> lstData;
	/** */
	private BaseScrollPane psTable;
	/**
	 * 名称一覧 ヘッダーサイズ
	 */

	/** */
	private int ROW_PER_PAGE;
	/** database service */
	
	private InspecTableComlumnModel columnModel;
	private FLogService fLogService;
	private FLogConditionVo conditionVo;
	private BaseLabel lblName;
	private BaseLabel lblCount;

	/** log service */
	// private LogService fLogService = new LogServiceImpl();
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
	public ResultLogFrame(BaseFrame frame, String title) {
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
	public ResultLogFrame(BaseFrame frame) {
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
	public ResultLogFrame() {
		super();
		init();
	}

	public ResultLogFrame(FLogConditionVo conditionVo) {
		super();
		this.conditionVo = conditionVo;
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.gui.AbstractFrame#initKodomo()
	 */
	protected void initKodomo() {
		
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
		initRightBody(rInputPnl);
		initHeader(headerMainPnl);
		btnF2.setEnabled(false);
		btnF3.setEnabled(false);
		btnF4.setEnabled(false);
		btnF5.setEnabled(false);
		btnF7.setEnabled(false);
		btnF8.setEnabled(false);
		btnF9.setEnabled(false);
		btnF10.setEnabled(false);

		btnF2
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF3
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF4
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF5
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF6
				.setText("<html><center><font size=-1>表示選択</font></center><center><font size=-1>F6(D)</font></center>");
		btnF6.setToolTipText("[F6]：表示選択を設定します。");
		btnF7
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF8
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF9
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF10
				.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF1()
	 */
	@Override
	protected void doF1() {

		if(lstData.isEmpty())
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
				fLogService.exportEXCEL(strFilePath, conditionVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF2()
	 */
	@Override
	protected void doF2() {

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
	void setColumnShow(boolean[] isShow) {
		for (int i = 0; i < isShow.length; i++) {
			TableColumn column = columnModel.getColumnByModelIndex(i);
			columnModel.setColumnVisible(column, isShow[i]);
		}
	}

	@Override
	protected void doF6() {
		boolean isShow[] = new boolean[colHeadNm.length];

		for (int i = 0; i < m_model.getColumnCount(); i++) {
			isShow[i] = columnModel.isColumnVisible(columnModel
					.getColumnByModelIndex(i));
		}
		HidenColumnFrame search = new HidenColumnFrame(getFrame(),true,colHeadNm, isShow);
		
		search.pack();
		search.setModal(true);
		search.setVisible(true);

		setColumnShow(search.getColumnShow());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF7()
	 */
	@Override
	protected void doF7() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF8()
	 */
	@Override
	protected void doF8() {

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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF11()
	 */
	@Override
	protected void doF11() {
		this.setVisible(false);
		backToBeforInterFace("800"+"390"+"1");
		this.getParentFrame().setVisible(true);
		this.dispose();
		stopTimerRemoveListener(this);
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#enableBtn()
	 */
	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = { true, true, true, true, true, true, true, true,
				true, true, true, true, true, true, true };
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
	 * @see com.pipe.jface.BaseMasterFrame#getRBodyWidth()
	 */
	@Override
	protected int getRBodyWidth() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initHeader(com.pipe.jface.BasePanel)
	 */
	@Override
	protected void initHeader(BasePanel pnl) {
		if (conditionVo == null)
			return;
		int X_WIDTH = 100;
		int xPos = 4;
		int yPos = 6;

		BaseLabel lblReg = new BaseLabel("担当者");
		lblReg.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblReg.setBorder(FaceContants.LINE_BORDER);
		lblReg.setHorizontalAlignment(SwingConstants.CENTER);
		lblReg.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		pnl.add(lblReg);

		xPos += X_WIDTH;
		lblName = new BaseLabel("4件");
		lblName.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblName.setBorder(FaceContants.LABEL_BORDER);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		pnl.add(lblName);

		xPos += X_WIDTH;
		BaseLabel lblLatestCd = new BaseLabel("件数");
		lblLatestCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblLatestCd.setBorder(FaceContants.LINE_BORDER);
		lblLatestCd.setHorizontalAlignment(SwingConstants.CENTER);
		lblLatestCd.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		pnl.add(lblLatestCd);

		xPos += X_WIDTH;
		lblCount = new BaseLabel("999");
		lblCount.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblCount.setBorder(FaceContants.LABEL_BORDER);
		lblCount.setHorizontalAlignment(SwingConstants.RIGHT);
		pnl.add(lblCount);

		setTitleInfor();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	private void setTitleInfor() {
		this.setTitle("操作ログ確認");
		if (conditionVo.getName().trim().length() == 0)
			lblName.setText("全て");
		else
			lblName.setText(conditionVo.getName());
		lblCount.setText(StringUtils.convertIntegerToStr(m_table.getRowCount())
				+ "件");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initLeftBody(com.pipe.jface.BasePanel
	 * )
	 */
	@Override
	protected void initLeftBody(BasePanel com) {
		//
		// int yPos = 10;
		// int xPos = 15;
		// int I_LBL_LENGTH = 110;
		// int TXT_TEXT_FIELD_LENGTH = 108;
		// int I_LBL_HEIGHT = FaceContants.LABLE_HEIGHT;
		//
		// // Required Edit Label
		// RequiredLabel rlblCode = new RequiredLabel("*");
		// rlblCode.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		// com.add(rlblCode);
		// EditLabel lblCode = new EditLabel("得意先コード");
		// lblCode.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblCode);
		// // End Required Edit Label
		//
		// txtCustCode = new CdInputText("", 0, CdInputText.IM_OFF, NumberUtils
		// .getIntFromString(MCtlConstants.getValue("SYSTEM" + "BM_CUST")));
		// txtCustCode.setToolTipText("得意先コードを入力して下さい。");
		// txtCustCode.setBounds(xPos + I_LBL_LENGTH, yPos, 80, I_LBL_HEIGHT);
		// com.add(txtCustCode);
		//
		// AbstractAction enter = new AbstractAction() {
		// /** */
		// private static final long serialVersionUID = 1L;
		//
		// public void actionPerformed(ActionEvent e) {
		//
		// try {
		// if (txtCustCode.isEditable()
		// && StringUtils.isValid(txtCustCode.getText())) {
		// if (StringUtils.trimAll(txtCustCode.getText())
		// .equalsIgnoreCase("0")) {
		// MessageBox.message(getContentPane(),
		// MessageConstants.getMstMsgVo("E0204"));
		// setDefaultFirstFocus(txtCustCode);
		// } else if ((StringUtils.trimAll(txtCustCode.getText())
		// .length() != NumberUtils
		// .getIntFromString(MCtlConstants
		// .getValue("SYSTEM" + "BM_CUST")))) {
		// MessageBox.message(getContentPane(),
		// MessageConstants.getMstMsgVo("E0004"));
		// setDefaultFirstFocus(txtCustCode);
		// } else {
		// MCustVo dataVo = custService
		// .getCustVoByCode((txtCustCode.getText()
		// .trim()));
		//
		// if (dataVo != null) {
		// if (EditConstants.COPY_MODE
		// .equalsIgnoreCase(CUR_MODE)) {
		// MessageBox.message(getFrame(),
		// MessageConstants
		// .getMstMsgVo("E0006"));
		// setDefaultFirstFocus(txtCustCode);
		// } else if (EditConstants.VIEW_MODE
		// .equalsIgnoreCase(CUR_MODE)) {
		// convertVo2Txt(dataVo);
		// CUR_MODE = EditConstants.EDIT_MODE;
		// setStatus(CUR_MODE);
		// }
		// } else {
		// if (!EditConstants.COPY_MODE
		// .equalsIgnoreCase(CUR_MODE))
		// cleanDataForNew();
		// CUR_MODE = EditConstants.NEW_MODE;
		// setStatus(EditConstants.NEW_MODE);
		// }
		// }
		// } else {
		// // MessageBox.message(getContentPane(), MessageConstants
		// // .getMstMsgVo("E0204"));
		// setDefaultFirstFocus(txtCustCode);
		// }
		// } catch (Exception ex) {
		// logger.error(ex.getMessage());
		// }
		// }
		// };
		// txtCustCode.getInputMap().put(
		// KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		// txtCustCode.getActionMap().put("enter", enter);
		// // com.add(txtCustCode);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// // Required Edit Label
		// RequiredLabel rlblCustName = new RequiredLabel("*");
		// rlblCustName.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		// com.add(rlblCustName);
		// EditLabel lblCustName = new EditLabel("得意先名");
		// lblCustName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblCustName);
		// // End Required Edit Label
		// txtCustName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA,
		// 30);
		// txtCustName.setToolTipText("得意先名を入力して下さい。");
		// txtCustName.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		// txtCustName.addFocusListener(new java.awt.event.FocusAdapter() {
		// public void focusLost(java.awt.event.FocusEvent evt) {
		// copyName();
		// }
		// });
		// com.add(txtCustName);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// // Required Edit Label
		// EditLabel lblCust2Name = new EditLabel("部 署 名 ");
		// lblCust2Name.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblCust2Name);
		// // End Required Edit Label
		//
		// txtCust2Name = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA,
		// 24);
		// txtCust2Name.setToolTipText("部 署 名を入力して下さい。");
		// txtCust2Name.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		// com.add(txtCust2Name);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// // Required Edit Label
		// RequiredLabel rlblKName = new RequiredLabel("*");
		// rlblKName.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		// com.add(rlblKName);
		// EditLabel lblKName = new EditLabel("得意先名(カナ)");
		// lblKName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblKName);
		// // End Required Edit Label
		// txtKName = new BaseInputText("", 0, BaseInputText.IM_HALFKANA, 20);
		// txtKName.setToolTipText("得意先名(カナ)を入力して下さい。");
		// txtKName.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		// com.add(txtKName);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// RequiredLabel rlblCustRName = new RequiredLabel("*");
		// rlblCustRName.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		// com.add(rlblCustRName);
		// EditLabel lblRName = new EditLabel("得意先名(略称)");
		// lblRName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblRName);
		//
		// txtRName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 20);
		// txtRName.setToolTipText("得意先名(略称)を入力して下さい。");
		// txtRName.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		// com.add(txtRName);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// RequiredLabel rlblCustTName = new RequiredLabel("*");
		// rlblCustTName.setBounds(xPos - 10, yPos, 10, I_LBL_HEIGHT);
		// com.add(rlblCustTName);
		// EditLabel lblTName = new EditLabel("得意先名(短縮)");
		// lblTName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblTName);
		// txtTName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 10);
		// txtTName.setToolTipText("得意先名(短縮)を入力して下さい。");
		// txtTName.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		// com.add(txtTName);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblType = new EditLabel("得意先区分");
		// lblType.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT * 2);
		// com.add(lblType);
		//
		// chkType1 = new InspectCheckBox(NameConstants.getName("CUSTTYPE" +
		// "1"));
		// chkType1.setToolTipText("得意先区分を入力して下さい。");
		// chkType1.setLocation(xPos + I_LBL_LENGTH, yPos);
		// chkType1.setBounds(new Rectangle(xPos + I_LBL_LENGTH, yPos, 150,
		// FaceContants.LABLE_HEIGHT));
		// com.add(chkType1);
		//
		// chkType2 = new InspectCheckBox(NameConstants.getName("CUSTTYPE" +
		// "2"));
		// chkType2.setToolTipText("得意先区分を入力して下さい。");
		// chkType2.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		// chkType2.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 150, yPos,
		// 150,
		// FaceContants.LABLE_HEIGHT));
		// com.add(chkType2);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// chkType3 = new InspectCheckBox(NameConstants.getName("CUSTTYPE" +
		// "3"));
		// chkType3.setToolTipText("得意先区分を入力して下さい。");
		// chkType3.setLocation(xPos + I_LBL_LENGTH, yPos);
		// chkType3.setBounds(new Rectangle(xPos + I_LBL_LENGTH, yPos, 150,
		// FaceContants.LABLE_HEIGHT));
		// com.add(chkType3);
		//
		// chkType4 = new InspectCheckBox(NameConstants.getName("CUSTTYPE" +
		// "4"));
		// chkType4.setToolTipText("得意先区分を入力して下さい。");
		// chkType4.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		// chkType4.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 150, yPos,
		// 150,
		// FaceContants.LABLE_HEIGHT));
		// com.add(chkType4);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblEmp = new EditLabel("担当者名");
		// lblEmp.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblEmp);
		//
		// txtEmployment = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA,
		// 10);
		// txtEmployment.setToolTipText("担当者名を入力して下さい。");
		// txtEmployment.setBounds(xPos + I_LBL_LENGTH, yPos, 200,
		// I_LBL_HEIGHT);
		// com.add(txtEmployment);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblLabel = new EditLabel("敬　　称");
		// lblLabel.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblLabel);
		//
		// cbbLabel = new InspectComboBox(ApplicationUtils.createComboDataModel(
		// "TTL", 4, 20, ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE));
		// cbbLabel.setToolTipText("敬 称 を入力して下さい。");
		// cbbLabel.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		// com.add(cbbLabel);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblPostCode = new EditLabel("郵便番号");
		// lblPostCode.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblPostCode);
		// txtPostCode = new BaseInputText("", 0, BaseInputText.IM_OFF, 8);
		// txtPostCode.setToolTipText("郵便番号 を入力して下さい。");
		// txtPostCode.setBounds(xPos + I_LBL_LENGTH, yPos, 160, I_LBL_HEIGHT);
		// com.add(txtPostCode);
		// btnSearchPostCode = new ActionButton("〒");
		// btnSearchPostCode.setBounds(xPos + I_LBL_LENGTH + 160, yPos, 40,
		// I_LBL_HEIGHT);
		// com.add(btnSearchPostCode);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblAddress1 = new EditLabel("住 所 1");
		// lblAddress1.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblAddress1);
		// txtAddress1 = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA,
		// 30);
		// txtAddress1.setToolTipText("住 所 1 を入力して下さい。");
		// txtAddress1.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		// com.add(txtAddress1);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblAddress2 = new EditLabel("住 所 2");
		// lblAddress2.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblAddress2);
		// txtAddress2 = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA,
		// 30);
		// txtAddress2.setToolTipText("住 所 2 を入力して下さい。");
		// txtAddress2.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		// com.add(txtAddress2);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblTel = new EditLabel("電話番号");
		// lblTel.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblTel);
		// txtTel = new BaseInputText("", 0, BaseInputText.IM_OFF, 17);
		// txtTel.setToolTipText("電話番号 を入力して下さい。");
		// txtTel.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		// com.add(txtTel);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblFAX = new EditLabel("FAX 番号");
		// lblFAX.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblFAX);
		// txtFAX = new BaseInputText("", 0, BaseInputText.IM_OFF, 17);
		// txtFAX.setToolTipText("FAX 番号 を入力して下さい。");
		// txtFAX.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		// com.add(txtFAX);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblMail = new EditLabel("メールアドレス");
		// lblMail.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblMail);
		// txtMail = new BaseInputText("", 0, BaseInputText.IM_OFF, 50);
		// txtMail.setToolTipText("メールアドレス を入力して下さい。");
		// txtMail.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		// com.add(txtMail);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblURL = new EditLabel("URL");
		// lblURL.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblURL);
		// txtURL = new BaseInputText("", 0, BaseInputText.IM_OFF, 50);
		// txtURL.setToolTipText("URL を入力して下さい。");
		// txtURL.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		// com.add(txtURL);
		//
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// EditLabel lblCustEnable = new EditLabel("使用可否");
		// lblCustEnable.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		// com.add(lblCustEnable);
		// chkCustEnable = new InspectCheckBox(NameConstants.getName("USEFLG"
		// + "0"));
		// chkCustEnable.setToolTipText("使用可否 を入力して下さい。");
		// chkCustEnable.setBounds(xPos + I_LBL_LENGTH, yPos, 150,
		// I_LBL_HEIGHT);
		// chkCustEnable.setChkLabel(NameConstants.getName("USEFLG" + "1"));
		// chkCustEnable.setUnChkLabel(NameConstants.getName("USEFLG" + "0"));
		// com.add(chkCustEnable);
		//
		// I_LBL_LENGTH = 63;
		// TXT_TEXT_FIELD_LENGTH = 76;
		// yPos = com.getHeight() - FaceContants.LABLE_HEIGHT - 2
		// * FaceContants.VERTICAL_SPACE;
		// ViewLabel lblRegisterInfo = new ViewLabel("登録情報");
		// lblRegisterInfo.setBounds(new Rectangle(xPos, yPos, I_LBL_LENGTH,
		// FaceContants.LABLE_HEIGHT));
		// lblRegisterInfo.setHorizontalAlignment(SwingConstants.CENTER);
		// com.add(lblRegisterInfo);
		//
		// xPos += I_LBL_LENGTH;
		// lblRegisterUser = new BaseLabel();
		// lblRegisterUser.setVerticalAlignment(BaseLabel.CENTER);
		// lblRegisterUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH - 5,
		// FaceContants.LABLE_HEIGHT));
		// lblRegisterUser.setLocation(xPos, yPos);
		// lblRegisterUser.setText("");
		// lblRegisterUser.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		// lblRegisterUser.setBorder(FaceContants.LABEL_BORDER);
		// com.add(lblRegisterUser);
		//
		// xPos += TXT_TEXT_FIELD_LENGTH - 5;
		// lblRegisterDate = new BaseLabel();
		// lblRegisterDate.setVerticalAlignment(BaseLabel.CENTER);
		// lblRegisterDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH + 5,
		// FaceContants.LABLE_HEIGHT));
		// lblRegisterDate.setLocation(xPos, yPos);
		// lblRegisterDate.setText("");
		// lblRegisterDate.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		// lblRegisterDate.setBorder(FaceContants.LABEL_BORDER);
		// com.add(lblRegisterDate);
		//
		// xPos += TXT_TEXT_FIELD_LENGTH + 5;
		// ViewLabel lblEditInfo = new ViewLabel("更新情報");
		// lblEditInfo.setBounds(new Rectangle(xPos, yPos, I_LBL_LENGTH,
		// FaceContants.LABLE_HEIGHT));
		// lblEditInfo.setHorizontalAlignment(SwingConstants.CENTER);
		// com.add(lblEditInfo);
		//
		// xPos += I_LBL_LENGTH;
		// lblEditUser = new BaseLabel();
		// lblEditUser.setVerticalAlignment(BaseLabel.CENTER);
		// lblEditUser.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH - 5,
		// FaceContants.LABLE_HEIGHT));
		// lblEditUser.setLocation(xPos, yPos);
		// lblEditUser.setText("");
		// lblEditUser.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		// lblEditUser.setBorder(FaceContants.LABEL_BORDER);
		// com.add(lblEditUser);
		//
		// xPos += TXT_TEXT_FIELD_LENGTH - 5;
		// lblEditDate = new BaseLabel();
		// lblEditDate.setVerticalAlignment(BaseLabel.CENTER);
		// lblEditDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH + 5,
		// FaceContants.LABLE_HEIGHT));
		// lblEditDate.setLocation(xPos, yPos);
		// lblEditDate.setText("");
		// lblEditDate.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		// lblEditDate.setBorder(FaceContants.LABEL_BORDER);
		// com.add(lblEditDate);
	}

	/**
	 * 画面TABキー移動設定
	 */
	protected void setDispTabFocus() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initRightBody(com.pipe.jface.BasePanel
	 * )
	 */
	@Override
	protected void initRightBody(BasePanel pnl) {
		if (conditionVo == null)
			return;
		psTable = getTable();

		psTable.setSize(pnl.getWidth() - 5, pnl.getHeight() - 5);
		psTable.getViewport().add(m_table);
		psTable.setLocation(0, 0);

		pnl.add(psTable);

		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnl.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
	}

	/**
	 * getTable
	 */
	private BaseScrollPane getTable() {
		BaseScrollPane tablePnl;
		colHeadwidth[3] = rInputPnl.getSize().width - colHeadwidth[0] - colHeadwidth[1] - colHeadwidth[2]- colHeadwidth[4]-colHeadwidth[5]-colHeadwidth[6]-25;
		m_model = new InspectTableModel(colHeadNm);

		m_table = new InspectTable(m_model) {
			private static final long serialVersionUID = 1L;

			private InspectTableRenderer renderer = new InspectTableRenderer();

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row,
					int column) {
				return null;
			}
		};
		columnModel = new InspecTableComlumnModel();
		m_table.setColumnModel(columnModel);
		m_table.createDefaultColumnsFromModel();

		// set data for spread
		m_model.setData(getTableData(conditionVo));

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_table
				.getColumnModel();

		for (int i = 0; i < colHeadwidth.length; i++) {
			defModel.getColumn(i).setMinWidth(colHeadwidth[i]);
			defModel.getColumn(i).setWidth(colHeadwidth[i]);
			defModel.getColumn(i).setMaxWidth(colHeadwidth[i] + 200);
		}

		// // invisible Column 0
		// for (int i = 0; i < 1; i++) {
		// defModel.getColumn(i).setPreferredWidth(0);
		// defModel.getColumn(i).setMaxWidth(0);
		// }

		// EVENT
		// mouse click
		// m_table.addMouseListener(new TableMouseClick());

		// change selected row
		ListSelectionModel rowSM = m_table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		m_table.getTableHeader().setReorderingAllowed(false); 
		tablePnl = new BaseScrollPane(m_table);
		tablePnl
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePnl.repaint();
		return tablePnl;
	}
	private String getTimeWithSplit(int HHmmss) {
		String result = "" + HHmmss;
		result = StringUtils.padLeft(result, '0', 6);
		
		result = result.substring(0, 2) + ":" + result.substring(2, 4) + ":" + result.substring(4, 6);
		
		return result;
	}
	private List getTableData(FLogConditionVo conditionVo) {
		// get data for table

		if (fLogService == null)
			fLogService = new FLogServiceImpl();

		try {

			lstData = fLogService.getLstLogInfor(conditionVo);

		} catch (BizException e1) {
			e1.printStackTrace();
		}
		List retList = new ArrayList();

		for (int i = 0; i < lstData.size(); i++) {
			List<String> list = new ArrayList<String>();

			list.add(DateUtils.getDateWithSplitYobi(lstData.get(i).getActDate()));
			list.add(getTimeWithSplit(lstData.get(i).getActTime()));
			list.add(lstData.get(i).getUserUser());
			list.add(lstData.get(i).getMenuexeCode());
			list.add(lstData.get(i).getActionType());
			list.add(lstData.get(i).getText());
			list.add(lstData.get(i).getPcid());
			retList.add(list);
		}
		// splash.setVisible(false);
		// splash.dispose();
		;
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doDoubleClick(int)
	 */
	@Override
	protected void doDoubleClick(int row) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doSingleClick(int)
	 */
	@Override
	protected void doSingleClick(int row) {

	}

	@Override
	protected void doF12() {
		this.setVisible(false);
		backToMenu("800"+"390", "800"+"390"+"1") ;
		this.getParentFrame().dispose();
		this.dispose();	
		stopTimerRemoveListener(this);
	}

}