/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： SearchProd.java
*
*	記述			：
*
*	作成日		：  2011/09/06  02:34:11 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.sapp.search;


import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.table.ColumnData;
import com.fas.jface.table.GenericCellRenderer;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.jface.table.SearchTableTransaction;
import com.fas.jface.text.BaseInputText;
import com.fas.sapp.base.SearchTransactionFrame;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.search.SearchConditionVo;
import com.fas.vo.search.TrSchdspVo;

/**
 * @author Administrator
 * @date 2011/09/06
 * @description
 */
public class SearchPG01Frame extends SearchTransactionFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private EditLabel lblLine1Label1;
	/** */
	private BaseComboBox cbbLine1Combo2;
	/** */
	private EditLabel lblLine2Label1;
	/** */
	private BaseInputText txtLine2Text2;	
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchPG01Frame() {
        super();
        init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchPG01Frame(BaseFrame frame) {
		super(frame, true);
        init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 * @param title
	 */
	public SearchPG01Frame(BaseFrame frame, String title) {
        super(frame, true);
        setTitle(title);
        init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 * @param modal
	 */
	public SearchPG01Frame(BaseFrame frame, boolean modal) {
        super(frame, modal);
        init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchPG01Frame(BaseFrame frame,SortObjVo sortVo) {
		super(frame,sortVo, true);
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

	/* 
	 * @return
	 * @description
	 */
	@Override
	protected TrSchdspVo getSchDspVo() {

		TrSchdspVo dspVo = null;
		dspVo = new TrSchdspVo();			
		dspVo.setDspCode(StringUtils.convertIntegerToStr(SearchTransactionFrame.I_PG01_SEARCH));			
		dspVo.setDspName("支部検索");			
		dspVo.setSchCtl("SCH_PG01");
		dspVo.setTblName("PG01");
		
		// SQL_ID: SQLID get from XML
		dspVo.setSqlId("PG01001");			
		// TTL_NAME1: Line 1 Label 1 
		dspVo.setTtlName1("地　図");			
		// TTL_NAME2: Line 2 Label 1 
		dspVo.setTtlName2("コード");

		//Show Cmb and Txt
		dspVo.setKbnGrp("1");
		// CMB_ITEM1: Line 1 Cmb 2 : 0 la show, 1 la an
		dspVo.setCmbItem1("0");
		// TXT_ITEM1: Line 2 Txt 2 : 0 la show, 1 la an
		dspVo.setTxtItem1("0");
		
		//SPD_COL: this Column is Visible, Invisible. If 1 Invisible, If 0 Visible			
		dspVo.setSpdCol1("0");
		dspVo.setSpdCol2("0");
		dspVo.setSpdCol3("0");
		dspVo.setSpdCol4("1");
		dspVo.setSpdCol5("1");
		dspVo.setSpdCol6("1");
		dspVo.setSpdCol7("1");
		dspVo.setSpdCol8("1");
		dspVo.setSpdCol9("1");
		dspVo.setSpdCol10("1");
		dspVo.setSpdCol11("1");
		dspVo.setSpdCol12("1");
		
		//SPD_COL: this Column HeaderTitle
		dspVo.setSpdColnm1("コード");
		dspVo.setSpdColnm2("地図名");
		dspVo.setSpdColnm3("名　称");
		dspVo.setSpdColnm4("");
		dspVo.setSpdColnm5("");
		dspVo.setSpdColnm6("");
		dspVo.setSpdColnm7("");
		dspVo.setSpdColnm8("");
		dspVo.setSpdColnm9("");
		dspVo.setSpdColnm10("");
		dspVo.setSpdColnm11("");
		dspVo.setSpdColnm12("");
		
		//SPD_COLWD: this Column Width
		dspVo.setSpdColwd1(100);
		dspVo.setSpdColwd2(100);
		dspVo.setSpdColwd3(400);
		dspVo.setSpdColwd4(0);
		dspVo.setSpdColwd5(0);
		dspVo.setSpdColwd6(0);
		dspVo.setSpdColwd7(0);
		dspVo.setSpdColwd8(0);
		dspVo.setSpdColwd9(0);
		dspVo.setSpdColwd10(0);
		dspVo.setSpdColwd11(0);
		dspVo.setSpdColwd12(0);
		
		dspVo.setSpdColuse(1);
		dspVo.setSpdColwdc(1);
		dspVo.setSpdColhid(3);
		
		//SCH_ITEM: this Item Visible, Invisible. If 1 Invisible, If 0 Visible
		dspVo.setSchItem1("0");
		dspVo.setSchItem2("0");
		dspVo.setSchItem3("1");
		dspVo.setSchItem4("1");
		dspVo.setSchItem5("1");
		dspVo.setSchItem6("1");
		
		//SCH_NAME: this Item Title
		dspVo.setSchName1("コード");
		dspVo.setSchName2("名　称");
		dspVo.setSchName3("");
		dspVo.setSchName4("");
		dspVo.setSchName5("");
		dspVo.setSchName6("");
		
		//CHK_USE: CheckUse Visible, Invisible
		dspVo.setChkUse("1");
		//KANA_ITEM: ListKanaItem Visible, Invisible
		dspVo.setKanaItem("1");
		//HELP_HTML: Help file
		dspVo.setHelpHtml("支部検索.html");
		dspVo.setUseFlg("0");
		
		return dspVo;
	}
	
	//Set value to ComboBox
	/* 
	 * @param pnlFilter
	 * @description
	 */
	@Override
	protected void initPnlCondition(BasePanel pnlFilter) {
		
		int xPos = 30;
		int yPos = 16;
		
		lblLine1Label1 = new EditLabel();
		lblLine1Label1.setText(dspVo.getTtlName1());
		lblLine1Label1.setSize(100, FaceContants.TEXT_HEIGHT);
		lblLine1Label1.setLocation(xPos, yPos);
		pnlFilter.add(lblLine1Label1);
		searchItem = "1";		
				
		xPos += 100;
		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		lstData = comService.getLstG0102OfPG01(true);
		ArrayListComboBoxModel aModel = ApplicationUtils.createComboDataModel(lstData, 12, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbLine1Combo2 = new BaseComboBox(aModel);
		cbbLine1Combo2.setSize(190, FaceContants.TEXT_HEIGHT);
		cbbLine1Combo2.setLocation(xPos, yPos);
		pnlFilter.add(cbbLine1Combo2);
				
		xPos = 30;
		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE + 7;	
		lblLine2Label1 = new EditLabel();
		lblLine2Label1.setText(dspVo.getTtlName2());
		lblLine2Label1.setSize(100, FaceContants.TEXT_HEIGHT);
		lblLine2Label1.setLocation(xPos, yPos);
		pnlFilter.add(lblLine2Label1);
		
		xPos += 100;
		txtLine2Text2 = new BaseInputText();
		txtLine2Text2.setSize(170, FaceContants.TEXT_HEIGHT);
		txtLine2Text2.setLocation(xPos, yPos);
		pnlFilter.add(txtLine2Text2);
	}
		
	/* 
	 * 
	 * @description
	 */
	@Override
	protected void doF8() {
		searchIndex = "";
		resetData();
		doSearch();
	}
	
	/* 
	 * 
	 * @description
	 */
	protected void doF9() {
		super.doF9();
		if (txtLine2Text2.isEnabled()) {
			setFocusComponent(txtLine2Text2);
		} else {
			if (cbbLine1Combo2.isEnabled()) {
				setFocusComponent(cbbLine1Combo2);
			}
		}
	}
	
	@Override
	protected void doBtnA() {
		searchIndex = "ア";
		resetData();
		doSearch();
		btnA.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnHa() {
		searchIndex = "ハ";
		resetData();
		doSearch();
		btnHa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}
	
	@Override
	protected void doBtnKa() {
		searchIndex = "カ";
		resetData();
		doSearch();
		btnKa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnMa() {
		searchIndex = "マ";
		resetData();
		doSearch();
		btnMa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnNa() {
		searchIndex = "ナ";
		resetData();
		doSearch();
		btnNa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnRa() {
		searchIndex = "ラ";
		resetData();
		doSearch();
		btnRa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnSa() {
		searchIndex = "サ";
		resetData();
		doSearch();
		btnSa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnTa() {
		searchIndex = "タ";
		resetData();
		doSearch();
		btnTa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnWa() {
		searchIndex = "ワ";
		resetData();
		doSearch();
		btnWa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnYa() {
		searchIndex = "ヤ";
		resetData();
		doSearch();
		btnYa.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doBtnEiSuu() {
		searchIndex = "e";
		resetData();
		doSearch();
		btnEisuu.setBackground(ColorConstants.BTN_SEARCH_FILTER_COLOR);
	}

	@Override
	protected void doRd1() {
		rdSchItem1.setSelected(true);
		lblLine2Label1.setText(dspVo.getSchName1());		
		txtLine2Text2.setText("");
		txtLine2Text2.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtLine2Text2);
		searchItem = "1";
	}

	@Override
	protected void doRd2() {
		rdSchItem2.setSelected(true);
		lblLine2Label1.setText(dspVo.getSchName2());
		txtLine2Text2.setText("");
		txtLine2Text2.setIMType(BaseInputText.IM_HIRAGANA);
		setFocusComponent(txtLine2Text2);
		searchItem = "2";
	}

	@Override
	protected void doRd3() {
		rdSchItem3.setSelected(true);
		lblLine2Label1.setText(dspVo.getSchName3());
		txtLine2Text2.setText("");
		txtLine2Text2.setIMType(BaseInputText.IM_HALFKANA);
		setFocusComponent(txtLine2Text2);
		searchItem = "3";
	}

	@Override
	protected void doRd4() {
		rdSchItem4.setSelected(true);
		lblLine2Label1.setText(dspVo.getSchName4());
		txtLine2Text2.setText("");
		txtLine2Text2.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtLine2Text2);
		searchItem = "4";
	}

	@Override
	protected void doRd5() {
		rdSchItem5.setSelected(true);
		lblLine2Label1.setText(dspVo.getSchName5());
		txtLine2Text2.setText("");
		txtLine2Text2.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtLine2Text2);
		searchItem = "5";
	}

	@Override
	protected void doRd6() {
		rdSchItem6.setSelected(true);
		lblLine2Label1.setText(dspVo.getSchName6());
		txtLine2Text2.setText("");
		txtLine2Text2.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtLine2Text2);
		searchItem = "6";
	}

	@Override
	protected SearchConditionVo getSearchCondition() {
		SearchConditionVo searchCondition = new SearchConditionVo();
		
		searchCondition.setTxtValue1(cbbLine1Combo2.getSelectedKey());
		searchCondition.setTxtValue2(txtLine2Text2.getText());
		searchCondition.setIndexValue(searchIndex);
		searchCondition.setItemValue(searchItem);

		if (chkIsUsed != null && chkIsUsed.isSelected()) {
			searchCondition.setUsed(true);
		} else {
			searchCondition.setUsed(false);
		}		
		return searchCondition;
	}
	
	@Override
	protected void doRequestComp() {
		if (cbbLine1Combo2.isEnabled()) {
			cbbLine1Combo2.requestFocus();
		} else {
			txtLine2Text2.requestFocus();
		}
	}

	/** Ham Hien thi du lieu khi init hay khong
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	protected boolean isDisplayWhenInit() {
//		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_EMP");
//		if (ctlVo != null) {
//			if ("1".equalsIgnoreCase(StringUtils.subStringUseEncode(ctlVo.getCData(), 1, 1))) {
//				return false;
//			}
//		}
		return true;
	}
	
	/* Ham set Default Search item ben phai
	 * @see com.pipe.sapp.base.SearchMasterFrame#getDefaultSearchItem()
	 */
	protected int getDefaultSearchItem() {
//		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_EMP");
//		if (ctlVo != null) {
//			String strControl = StringUtils.subString(ctlVo.getCData(), 0, 1);
//			if ("1".equalsIgnoreCase(strControl)) {
//				return 1;
//			} else if ("2".equalsIgnoreCase(strControl)) {
//				return 2;
//			} else if ("3".equalsIgnoreCase(strControl)) {
//				return 3;
//			} 
//		}
		return 1;
	}

	@Override
	protected String getHelpInfor() {
		return "検索条件を入力して下さい。";
	}

	@Override
	protected String getSubName() {
		if (dspVo != null) {
			return dspVo.getDspName().trim();			
		} else {
			return "";
		}

	}
	
	@Override
	protected String getNumber() {
		if (m_table != null) {
			return "" + m_table.getRowCount();
		} else {
			return "0";
		}
	}

	@Override
	protected String getCKey() {
    	return dspVo.getSchCtl().trim();
	}
	
	//Set Filter A, Ka, Ma, Sa v...v... and ChkUseFlg Panel Disable Visible
	@Override
	protected boolean isShortBtn() {
		return false;
	}

	/* 
	 * @return
	 * @description
	 */
	@Override
	protected SearchTableTransaction getMTable() {
		isInit = true;
		m_data = new SearchTableDataModel();
		createTable();	
		return m_table;
	}
	
	/* 
	 * @param sortObj
	 * @return
	 * @description
	 */
	@Override
	protected SearchTableTransaction getMTable(SortObjVo sortObj) {
		m_data = new SearchTableDataModel(sortObj);		
		createTable();		
		return m_table;
	}
	
	/**
	 * 
	 * @description
	 */
	private void createTable()
	{
		m_table = new SearchTableTransaction();
		m_table.setModel(m_data);

		Rd2Action rd2Action = new Rd2Action("rd2Action");
		m_table.getInputMap().put(KeyStroke.getKeyStroke("F2"),	rd2Action.getValue(Action.NAME));
		m_table.getActionMap().put("rd2Action", rd2Action);

		ColumnData mCols[] = m_data.getColumnData();
		for (int k = 0; k < mCols.length; k++) {
			GenericCellRenderer renderer = new GenericCellRenderer();
			TableColumn column = new TableColumn(k, mCols[k].m_width, renderer,	null);
			m_table.addColumn(column);
		}
		
		//b: SET TABLE HEADER ALIGN
		int[] align = { BaseLabel.LEFT, BaseLabel.LEFT, BaseLabel.LEFT};
		InspectTableRenderer renderer = new InspectTableRenderer(align);
		m_table.setSearchRenderer(renderer);
		//e: SET TABLE ALIGN

		//b: SET TABLE HEADER SORTABLE
		m_table.getTableHeader().addMouseListener(m_data.setMouseListener(m_table));
//		m_table.getTableHeader().setPreferredSize(new Dimension(m_table.getTableHeader().getWidth(),FaceContants.TABLE_HEADER_HEIGHT));
		//e: SET TABLE HEADER SORTABLE
				
		NumberFormat formaterInt = new DecimalFormat("###,###,##0");
		lblNumber.setText(formaterInt.format( NumberUtils.getIntFromString(getNumber())) + "件");
		m_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = m_table.getSelectedRow();
				if (e.getClickCount() < 2) {
					doSingleClick(row);
				} else {
					doDoubleClick(row);
				}
			}
		});

		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					doRequestComp();
				}
			}
		};

		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),"tabKey");
		m_table.getActionMap().put("tabKey", tabKey);
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_table.getActionMap().put("shiftTab", tabKey);

		AbstractAction enterKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					int row = m_table.getSelectedRow();
					if (row >= 0) {
						doDoubleClick(row);
					}
				}
			}
		};
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),	"enterKey");
		m_table.getActionMap().put("enterKey", enterKey);
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,InputEvent.SHIFT_DOWN_MASK), "shiftEnter");
		m_table.getActionMap().put("shiftEnter", enterKey);	
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param args
	 */
	public static void main(String args[]) {
		//Use for testing
		SearchPG01Frame templte = new SearchPG01Frame();
		templte.pack();
		templte.setVisible(true);
	}

	protected void setFrameSize() {
		dspVo = getSchDspVo();
		schCtlVo = MCtlConstants.getMCtlVoByCKey(getCKey());
		if (schCtlVo == null) {
			schCtlVo = new MCtlVo();
			schCtlVo.setCData("10");
		}		
		X_FRAME_LENGTH = 800;
		Y_FRAME_LENGTH = 640;
		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH - 28;
		X_HEADER_LENGTH = 0;
		Y_HEADER_LENGTH = 0;
		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 28;	
		
		LEFT_PNL_WIDTH = X_FRAME_LENGTH - 200;
		RIGHT_PNL_WIDTH = X_BODY_LENGTH - LEFT_PNL_WIDTH - 25;
		
		Y_CONDITION_PANEL_HEIGHT = 90;
		Y_TABLE_PANEL_HEIGHT = 460;
		Y_FILTER_PANEL_HEIGHT = 200;
	}
}
