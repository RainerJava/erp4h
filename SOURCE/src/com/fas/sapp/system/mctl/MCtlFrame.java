/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：ControlMaster.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：Admin
*
*     備考			：
*
**************************************************************************************/

package com.fas.sapp.system.mctl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import jxl.write.WriteException;

import org.apache.log4j.Logger;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.MessageBox;
import com.fas.jface.bussiness.BaseMasterFrame;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.ViewLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.system.mctl.MCtlService;
import com.fas.service.system.mctl.MCtlServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.menuexe.MenuExeVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Nguyen Viet Hung</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class MCtlFrame extends BaseMasterFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** Log */
	static Logger logger = Logger.getLogger(MCtlFrame.class);
	
	/** DEFINE CONTROL*/
	/** */
	private ControlCombobox cbbControl;
	/** */
	private BaseLabel lblPermission;
	/** Define Table */
	private InspectTable m_table;
	/** */
	private InspectMCtlTableModel m_model;
	/** */
	private BaseScrollPane psTable;
	/** Define List object*/
	protected List<MCtlVo> lstData;

	/** テーブルヘッダーサイズ */
	private static String colHeadNm[] = { "名称", "値" };
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private int[] colHeadwidth = { 500, 150 };
	/** Row per page*/
	private int ROW_PER_PAGE;
	/** Database service */
	private MCtlService mCtlService;	
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#getHelpInfor()
	 */
	@Override
	protected String getHelpInfor() {
		return "";
	}

	/* (non-Javadoc)
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
	*   <DT>コンストラクター記述：</DT>
	* 		<DD>Init Function</DD>
	* <BR>
	* @param frame
	* @param title
	* </DL>
	*/
	public MCtlFrame(BaseFrame frame, String title) {
		super(frame, title);
		init();
	}
	
	/**
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD>Init Function</DD>
	* <BR>
	* @param frame
	* </DL>
	*/
	public MCtlFrame(BaseFrame frame) {
		super(frame);
		init();
	}
	
	/**
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	public MCtlFrame() {
		super();
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.gui.AbstractFrame#initKodomo()
	 */
	protected void initKodomo() {
		//Create Service in ServiceImpl
		mCtlService = new MCtlServiceImpl();
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void init() 
	{
		setResizable(false);
	
		//CUR_MODE = EditConstants.VIEW_MODE;
		setDispTabFocus();
		//txtEmpCode.setBtnSearch(btnF4);
		// 0:初期表示しない 1:初期表示する
		//1.4
		if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INTD_CTL").trim())) 
		{
			//Refesh data
			doF10();
		}

		//set string empty for F2 F4 F6 button
		btnF2.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");		
		btnF3.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");		
		btnF4.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");		
		btnF8.setText("<html><center><font size=-1>変更</font></center><center><font size=-1>F8(A)</font></center>");
		btnF2.setToolTipText("");
		btnF3.setToolTipText("");
		btnF4.setToolTipText("");
		btnF8.setToolTipText("[F8]：コントロール情報変更画面を表示します。");		
		//btnF12.setConfirmRerquired(false);
		//Set En-Disable all button
		setStatus();
	}
		
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	protected void setStatus() 
	{		
		cbbControl.setEnabled(true);
				
		if (isRequireResetTabDispFocus) {
			setDispTabFocus();
			setDefaultFirstFocus(cbbControl);
		}

		btnF1.setEnabled(true);
		btnF2.setEnabled(false);
		btnF3.setEnabled(false);
		btnF4.setEnabled(false);
		btnF10.setEnabled(true);
		btnF11.setEnabled(true);		
		
		btnFirst.setEnabled(true);
		btnPre.setEnabled(true);
		btnNext.setEnabled(true);
		btnLast.setEnabled(true);
		
		m_table.setEnabled(true);
		//this.getrInputPnl().setAlpha(0.5f);	
		
		isRequireResetTabDispFocus = true;
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				setPermission();
			}
		});
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	public void setPermission() {
//		//Check permission to enable F8
//		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu)) 
//		{			
//			btnF8.setEnabled(true);
//			btnF8.setAffterDoDisable(true);
//		}
//		else//Not permission edit
//		{
//			btnF8.setEnabled(false);
//		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD>
	* 			Set Tab for all Control
	* 		</DD>
	* <BR>
	* </DL>
	*/
	protected void setDispTabFocus() {
		
		List<Object> focusList = new ArrayList<Object>();
		focusList.add(cbbControl);
		focusList.add(m_table);

		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}

	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 	<DD></DD>
	 * <BR>
	 *   <DT>変更歴史：</DT>
	 * 	<DD>著作者: Nguyen Viet Hung</DD><BR>
	 * 	<DD></DD>
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
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void resetData() 
	{
		//Base form status
		cbbControl.setSelectedIndex(0);
		setStatus();
		if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INTD_CTL").trim())) 
		{
			//Refesh data
			doF10();
		}
	}
		
	private List getTableData() 
	{	
		if (mCtlService == null)
		{
			mCtlService = new MCtlServiceImpl();
		}
		//Can Xu Li
		try {			
			//Get list data from Service
			//Condition: USERID=SYSTEM, C_KEY from cbb,
			//MTN_FLG=0
	        //Function load table data
			String strUserid = mCtlService.getByKey("SYSTEM", "Z_SYSUSR").getCData();
	        String strCKey = StringUtils.objectToStr(cbbControl.getSelectedKey());
	        int iMtnFlg = 0;
	        if(cbbControl.getSelectedIndex() <= 0) //Load all data to table
	        {
	        	lstData = mCtlService.getListVo(strUserid, iMtnFlg);
	        }
	        else
	        {
	        	lstData = mCtlService.getListVo(strUserid, strCKey, iMtnFlg);
	        }
		} catch (BizException e1) {
			e1.printStackTrace();
		}
		//Return list Obj Vo, not return amounyous list
		return lstData;
	}
	
	private BaseScrollPane getTable() {
		BaseScrollPane tablePnl;
		colHeadwidth = new int[] { 500, 150 };
		colHeadwidth[0] = 500;
		colHeadwidth[1] = rInputPnl.getSize().width - colHeadwidth[0] - 25;
		
		m_model = new InspectMCtlTableModel(colHeadNm);
		m_table = new InspectTable(m_model) {
			private static final long serialVersionUID = 1L;

			private int[] align = {BaseLabel.LEFT, BaseLabel.LEFT};  
			            
			private InspectTableRenderer renderer = new InspectTableRenderer(align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row, int column) {
				return null;
			}
		};

		// set data for spread
		//m_model.setData(getTableData());
		m_model.setData(new ArrayList<MCtlVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_table.getColumnModel();
		for (int i = 0; i < colHeadwidth.length; i++) {
			defModel.getColumn(i).setMinWidth(colHeadwidth[i]);
			defModel.getColumn(i).setWidth(colHeadwidth[i]);
			defModel.getColumn(i).setMaxWidth(colHeadwidth[i] + 200);
		}
		
		// add action for table: UP, DOWN,...
		initTableAction(m_table);
		//Set tab for table to control		
	    AbstractAction tabKey = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					if (cbbControl.isEnabled()) {
						cbbControl.requestFocus();
					} else if (cbbControl.isEnabled()) {
						cbbControl.requestFocus();
					}
				} else {
					m_table.requestFocus();
				}
		    }
		};
	    
		F8Action f8Action = new F8Action("f8Action");
		m_table.getInputMap().put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		m_table.getActionMap().put("f8Action", f8Action);
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabKey");
	    m_table.getActionMap().put("tabKey", tabKey);	
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "shiftTab");
	    m_table.getActionMap().put("shiftTab", tabKey);
		// EVENT
		// mouse click
		m_table.addMouseListener(new TableMouseClick());
		//Not allow change index of Columns
		m_table.getTableHeader().setReorderingAllowed(false);

//		// change selected row
//		ListSelectionModel rowSM = m_table.getSelectionModel();
//		rowSM.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				convertTableRow2Txt();
//			}
//		});
		
		//Set Pointer to first row
		int iSelectedIndex = 0;
		m_table.changeSelection(iSelectedIndex, 0, false, false);
		
		//Repaint table
		tablePnl = new BaseScrollPane(m_table);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePnl.repaint();
		return tablePnl;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#initHeader(com.pipe.jface.BasePanel)
	 */
	@Override
	protected void initHeader(BasePanel pnl) 
	{	
		int xPos = 5;
		int yPos = 5;
		
		int I_LBL_LENGTH = 110;
		int TXT_TEXT_FIELD_LENGTH_500 = 500;
		
		//1. LBL CMB C_NAME MaxLength 10 Width 180	
		
		ViewLabel lblControl = new ViewLabel("名称");
		lblControl.setLocation(xPos, yPos);
		lblControl.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnl.add(lblControl);
		
		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		try {
			String strUserid = mCtlService.getByKey("SYSTEM", "Z_SYSUSR").getCData();
			int iMtnFlg = 0;
			lstData = comService.getCmbMCtlByUseridAndMtnFlg(strUserid, iMtnFlg);
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstData = new ArrayList<ComboObjectVo>();
		}
		xPos += I_LBL_LENGTH;
		ArrayListComboBoxModel aModel = ApplicationUtils.createComboDataModel(lstData, 12, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		//cbbControl = new InspectComboBox(aModel);
		cbbControl = new ControlCombobox(aModel);
		cbbControl.setPopupWidth(TXT_TEXT_FIELD_LENGTH_500);
		//cbbControl.setPopupHeight(FaceContants.LABLE_HEIGHT * 50);
		cbbControl.setToolTipText("名称を選択して下さい。");
		cbbControl.setLocation(xPos , yPos);
		cbbControl.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_500, FaceContants.LABLE_HEIGHT));
		pnl.add(cbbControl);

		xPos = xPos + TXT_TEXT_FIELD_LENGTH_500 + 5;
		lblPermission = new BaseLabel();
		lblPermission.setForeground(Color.RED);
		lblPermission.setBounds(new Rectangle(xPos, yPos, I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.RIGHT);
		pnl.add(lblPermission);
	}
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#initLeftBody(com.pipe.jface.BasePanel)
	 */
	@Override
	protected void initLeftBody(BasePanel com) 
	{
	}
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#initRightBody(com.pipe.jface.BasePanel)
	 */
	@Override
	protected void initRightBody(BasePanel pnl) 
	{
		rInputPnl.setLocation(5, 5);// Set Location for RightPanel, xPos = Header 1st xPost		
		psTable = getTable();

		psTable.setSize(pnl.getWidth() - 5, pnl.getHeight() - 5);
		psTable.getViewport().add(m_table);
		psTable.setLocation(0, 0);

		pnl.add(psTable);

		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnl.getHeight() / FaceContants.TABLE_ROW_HEIGHT) - 1;
		//Set PopupHeight
//		for(int i=12; i>=0;i=i-4)
//		{
//			if( getPopupHeightForRowCount( ROW_PER_PAGE + i ) < (pnl.getHeight() + headerMainPnl.getHeight())  )
//			{
//			
//			}
//		}
		cbbControl.setPopupHeight(ROW_PER_PAGE + 4);
	}
	
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF1()
	 */
	@Override
	protected void doF1() {
		
		//If not exist element in DB show error E0001
		try {
			String strUserid = MCtlConstants.getValueByCKey("Z_SYSUSR");
			int iMtnFlg = 0;
			
			if(mCtlService.getListVo(strUserid, iMtnFlg).size() <= 0)
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
					mCtlService.exportEXCEL(strFilePath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (BizException e) {
			logger.error(e.getMessage());
			MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0123"));
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF2()
	 */
	@Override
	protected void doF2() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF3()
	 */
	@Override
	protected void doF3() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF4()
	 */
	@Override
	protected void doF4() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF5()
	 */
	@Override
	protected void doF5() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF6()
	 */
	@Override
	protected void doF6() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF7()
	 */
	@Override
	protected void doF7() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF8()
	 */
	@Override
	protected void doF8() {
		btnF8.setAffterDoDisable(false);
//		//Check permission to do F8
//		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu)) 
//		{
			try
			{
				//Call edit dialog
				String strUserid = mCtlService.getByKey("SYSTEM", "Z_SYSUSR").getCData();
				String strCKey = "";
				
				int iSelectedRow = m_table.getSelectedRow();
				iSelectedRow = m_table.convertRowIndexToModel(m_table.getSelectedRow());
				if (iSelectedRow >= 0 && m_table.getRowCount() > 0) 
				{
					//Change Type to Vo
					MCtlVo dataVo = (MCtlVo) ((InspectMCtlTableModel) m_table.getModel()).getSelectedObject(iSelectedRow);
					if (dataVo != null) {
						strCKey = dataVo.getCKey();
						boolean bPermission = PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu);
						MCtlFrameEdit a =  new MCtlFrameEdit(this.getFrame(), true, strUserid, strCKey, bPermission);
						a.pack();
						//a.doF11();
						a.setVisible(true);
						doF10();
					}
				}
			}
			catch(BizException ex)
			{}
//		}
	}
	
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 	<DD></DD>
	 * <BR>
	 *   <DT>変更歴史：</DT>
	 * 	<DD>著作者: Admin</DD><BR>
	 * 	<DD></DD>
	 * </DL>
	 */
	class F8Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F8Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF8();	
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF9()
	 */
	@Override	
	protected void doF9() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF10()
	 */
	@Override
	protected void doF10() 
	{
		//Get data to table from Service
		m_model.setData(getTableData());
				
		//Repanit table (paint Scroll Vertical)
		m_table.repaint();
		psTable.getViewport().removeAll();
		psTable.getViewport().add(m_table);
		m_table.requestFocus();
		
		//Set Pointer to first row
		int iSelectedIndex = 0;
		m_table.changeSelection(iSelectedIndex, 0, false, false);
		
		//setStatus();
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doF11()
	 */
	@Override
	protected void doF11() 
	{
		resetData();		
	}

	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doFirst()
	 */
	@Override
	protected void doFirst() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = 0;
			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doLast()
	 */
	@Override
	protected void doLast() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getRowCount();
			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	/* (non-Javadoc)
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
		}
	}

	/* (non-Javadoc)
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
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#enableBtn()
	 */
	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };		
		fBtn[5] = false;		
		fBtn[6] = false;		
		fBtn[7] = false;
		fBtn[9] = false;
		return fBtn;
	}

	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#getRBodyWidth()
	 */
	@Override
	protected int getRBodyWidth() {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doDoubleClick(int)
	 */
	@Override
	protected void doDoubleClick(int row) 
	{
		//If have not element
		if(m_table.getModel().getRowCount() == 0)
		{
			return;
		}
		doF8();
	}

	/* (non-Javadoc)
	 * @see com.pipe.jface.BaseMasterFrame#doSingleClick(int)
	 */
	@Override
	protected void doSingleClick(int row)
	{		
	}
		
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 	<DD></DD>
	 * <BR>
	 *   <DT>変更歴史：</DT>
	 * 	<DD>著作者: Admin</DD><BR>
	 * 	<DD>
	 * 		Class CTL COMBOBOX inherit from InspectComboBox
	 * 		process event selected index change
	 * 	</DD>
	 * </DL>
	 */
	class ControlCombobox extends BaseComboBox 
	{
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		protected int popupHeight;
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public ControlCombobox(ComboBoxModel aModel) {
		    super(aModel);
		    init();
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		private void init() {
		}
	
	    /* (non-Javadoc)
	     * @see javax.swing.JComboBox#setSelectedIndex(int)
	     */
	    public void setSelectedIndex(int anIndex) {
	        super.setSelectedIndex(anIndex);
	        
	        if(anIndex > 0)
	        {
	        	getTable();
		        //Get data to table from Service
		        m_model.setData(getTableData());
		        
		        //Repanit table (paint Scroll Vertical)
				m_table.repaint();
				psTable.getViewport().removeAll();
				psTable.getViewport().add(m_table);
				m_table.requestFocus();
				
				//Set Pointer to first row
				int iSelectedIndex = 0;
				m_table.changeSelection(iSelectedIndex, 0, false, false);
	        }
	        else//Index == 0, check INTD_CTL
	        {
	        	if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INTD_CTL").trim())) 
	    		{
	        		getTable();
	        		 //Get data to table from Service
			        m_model.setData(getTableData());
			        
			        //Repanit table (paint Scroll Vertical)
					m_table.repaint();
					psTable.getViewport().removeAll();
					psTable.getViewport().add(m_table);
					m_table.requestFocus();
					
					//Set Pointer to first row
					int iSelectedIndex = 0;
					m_table.changeSelection(iSelectedIndex, 0, false, false);
	    		}
	        	else //Clear data from Table
	        	{
	        		getTable();
	        		 //Get data to table from Service
			        m_model.setData(null);
			        
			        //Repanit table (paint Scroll Vertical)
					m_table.repaint();
					psTable.getViewport().removeAll();
					psTable.getViewport().add(m_table);
					m_table.requestFocus();
	        	}
	        }
	    }
	    
	    /**
	     * <DL>
	     *   <DT>メソッド記述：</DT>
	     * 		<DD></DD>
	     * <BR>
	     *
	     * </DL>
	     */
	    public void resetValue() {
	    	setSelectedIndex(0);
	    }
	    
	    /**
	     * <DL>
	     *   <DT>メソッド記述：</DT>
	     * 		<DD></DD>
	     * <BR>
	     *
	     * </DL>
	     * @return
	     */
	    public Object getSelectObjValue() {
	    	int iValue = getSelectedIndex();
	    	if (dataModel instanceof ArrayListComboBoxModel) 
	    	{
	    		ArrayListComboBoxModel aDataModel = (ArrayListComboBoxModel) dataModel;
	    		return aDataModel.getObjAt(iValue);
	    	} 
	    	else 
	    	{
	    		return null;
	    	}	
	    }
	
	    public int getMaximumRowCount() {
	    	return popupHeight;
	    }
	    
	    public void setPopupHeight (int iHeight)
	    {
	    	popupHeight = iHeight;
	    	return;
	    }
//	    
//	    //Create Width and Height for PopupComboBox
//	    //Override function get Width and Height for popup
//	    public Dimension getPopupSize() {		    
//		    return new Dimension(popupWidth, popupHeight);
//		}
	}	
		
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 	<DD></DD>
	 * <BR>
	 *   <DT>変更歴史：</DT>
	 * 	<DD>著作者: </DD><BR>
	 * 	<DD></DD>
	 * </DL>
	 */
	class InspectMCtlTableModel extends DefaultTableModel {
		
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private String[] headerName;
	    /** */
	    private List data;
	    
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * </DL>
	    */
	    public InspectMCtlTableModel(String[]  headerName){
	        if(headerName==null){
	            throw new IllegalArgumentException();
	        }
	        this.headerName=headerName;        
	    }
	    
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * @param data
	    * </DL>
	    */
	    public InspectMCtlTableModel(String[] headerName,List data) {
	        if(headerName==null){
	            throw new IllegalArgumentException();
	        }
	        this.headerName=headerName;
	        this.data=data;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getRowCount()
	     */
	    public int getRowCount() {
	        if(data==null){
	            return 0;
	        }
	        return data.size();
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#setValueAt(java.lang.Object, int, int)
	     */
	    public void setValueAt(Object value,int row,int column){
	        data.set(row, value);
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getColumnCount()
	     */
	    public int getColumnCount() {
	        return headerName.length;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	     */
	    public String getColumnName(int column) {
	        return (String) headerName[column];
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	if (data != null && rowIndex < data.size()) {
		        MCtlVo dataVo = (MCtlVo) data.get(rowIndex);
		        if (dataVo != null) {
					    switch (columnIndex) {
				      	case 0: return dataVo.getCName();
				      	case 1: return dataVo.getCData();
				      	default: return "";
				    }
		        } else {
		        	return "";
		        }
	    	} else {
	    		return "";
	    	}
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @return
	    * </DL>
	    */
	    public List getData() {
	        return data;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param data
	    * </DL>
	    */
	    public void setData(List data) {
	        this.data = data;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @return
	    * </DL>
	    */
	    public String[] getHeaderName() {
	        return headerName;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * </DL>
	    */
	    public void setHeaderName(String[] headerName) {
	        this.headerName = headerName;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	     */
	    public Class getColumnClass(int columnIndex) {
	    	return getValueAt(0,columnIndex).getClass();
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param iSelectedRow
	    * @return
	    * </DL>
	    */
	    public Object getSelectedObject(int iSelectedRow) 
	    {
//			 String strCName = m_table.getValueAt(iSelectedRow, 0).toString().trim();
//			 String strCValue = m_table.getValueAt(iSelectedRow, 1).toString().trim();
//	    	 
//	    	if (0 <= iSelectedRow && iSelectedRow < data.size()) 
//	    	{	
//    			for (int i = 0; i < data.size(); i++) 
//    			{
//    				MCtlVo dataVo = (MCtlVo) data.get(i); 
//    				if( dataVo.getCName().trim().equals(strCName) && dataVo.getCData().trim().equals(strCValue) )
//    					return dataVo;
//				}
//	    	}
	    	if (0 <= iSelectedRow && iSelectedRow < data.size()) {
	    		return data.get(iSelectedRow);
	    	}
	    	
	    	return null;
	    }	
	}


	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu) == false) 
			{
				//Not permission show red text and disable btn
				lblPermission.setText("更新不可");
				lblPermission.setBorder(null);
			}
		
	}

}
