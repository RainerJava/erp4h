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


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.MouseInputAdapter;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.HeaderLabel;
import com.fas.jface.label.ViewLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.panel.HaBoxLayoutPanel;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputSearchText;
import com.fas.sapp.base.SearchTransactionFrame;
import com.fas.sapp.base.SearchTransactionFrameGridBagLayout;
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
public class SearchPE01FrameGridBagLayout extends SearchTransactionFrameGridBagLayout {

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
	
	private static final String colMasterHead[] = { "コード","名　　所","組合員","支　　部","税　　抜","税　　込"};
	//Chu y: phan set Width cho TextBox phai dua vao set Cols de tao width, 1 col = 8.5 pixel, vay 10 cols se la 85 pixcel
	private static final int[] colHeadwidth = {60  , 150      , 85    , 85  , 85  , 85};
	private static final int[] headerAligns = {BaseLabel.CENTER, BaseLabel.LEFT, BaseLabel.LEFT, 
			BaseLabel.RIGHT, BaseLabel.RIGHT, BaseLabel.RIGHT, BaseLabel.LEFT};
	private int preSelectedRow = 0;
	private int curSelectedRow = 0;
	
	//INDEX LIST VIEW 
	private static final int I_PRODUCT_CODE = 0; //Ma San Pham
	private static final int I_PRODUCT_NAME = 1; //Ten San Pham
	private static final int I_MEMBER = 2; //Thanh Vien
	private static final int I_BRANCH = 3; //Chi Nhanh
	private static final int I_TAX_WITHOUT_INCLUDED_ = 4; //Chua tinh Thue
	private static final int I_TAX_INCLUDED = 5; //Sau Khi Tinh Thue	
	public static int TABLE_ROWS = 2;
	public static int TABLE_COLUMNS = 6;
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchPE01FrameGridBagLayout() {
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
	public SearchPE01FrameGridBagLayout(BaseFrame frame) {
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
	public SearchPE01FrameGridBagLayout(BaseFrame frame, String title) {
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
	public SearchPE01FrameGridBagLayout(BaseFrame frame, boolean modal) {
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
	public SearchPE01FrameGridBagLayout(BaseFrame frame,SortObjVo sortVo) {
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
		TABLE_ROWS = 5;
		m_data = new SearchTableDataModel();
		
	}

	/* Dinh nghia ve giao dien cho form Search
	 * @return
	 * @description
	 */
	@Override
	protected TrSchdspVo getSchDspVo() {

		TrSchdspVo dspVo = null;
		dspVo = new TrSchdspVo();			
		dspVo.setDspCode(StringUtils.convertIntegerToStr(SearchTransactionFrame.I_PE01_SEARCH));			
		dspVo.setDspName("商品検索");			
		dspVo.setSchCtl("SCH_PE01");
		dspVo.setTblName("PE01");
		
		// SQL_ID: SQLID get from XML
		dspVo.setSqlId("PE01001");			
		// TTL_NAME1: Line 1 Label 1 
		dspVo.setTtlName1("商品分類");			
		// TTL_NAME2: Line 2 Label 1 
		dspVo.setTtlName2("コード");

		//Show Cmb and Txt
		dspVo.setKbnGrp("1");
		// CMB_ITEM1: Line 1 Cmb 2 
		dspVo.setCmbItem1("0");
		// TXT_ITEM1: Line 2 Txt 2 
		dspVo.setTxtItem1("0");
		
		//SPD_COL: this Column is Visible, Invisible. If 1 Invisible, If 0 Visible			
		dspVo.setSpdCol1("0");
		dspVo.setSpdCol2("0");
		dspVo.setSpdCol3("0");
		dspVo.setSpdCol4("0");
		dspVo.setSpdCol5("0");
		dspVo.setSpdCol6("0");
		dspVo.setSpdCol7("1");
		dspVo.setSpdCol8("1");
		dspVo.setSpdCol9("1");
		dspVo.setSpdCol10("1");
		dspVo.setSpdCol11("1");
		dspVo.setSpdCol12("1");
		
		//SPD_COL: this Column HeaderTitle
		dspVo.setSpdColnm1("コード");
		dspVo.setSpdColnm2("名　称");
		dspVo.setSpdColnm3("組合員");
		dspVo.setSpdColnm4("支　部");
		dspVo.setSpdColnm5("税　抜");
		dspVo.setSpdColnm6("税　込");
		dspVo.setSpdColnm7("");
		dspVo.setSpdColnm8("");
		dspVo.setSpdColnm9("");
		dspVo.setSpdColnm10("");
		dspVo.setSpdColnm11("");
		dspVo.setSpdColnm12("");
		
		//SPD_COLWD: this Column Width
		dspVo.setSpdColwd1(100);
		dspVo.setSpdColwd2(400);
		dspVo.setSpdColwd3(100);
		dspVo.setSpdColwd4(100);
		dspVo.setSpdColwd5(100);
		dspVo.setSpdColwd6(100);
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
		dspVo.setSchItem3("0");
		dspVo.setSchItem4("1");
		dspVo.setSchItem5("1");
		dspVo.setSchItem6("1");
		
		//SCH_NAME: this Item Title
		dspVo.setSchName1("コード");
		dspVo.setSchName2("名　称");
		dspVo.setSchName3("カ　ナ");
		dspVo.setSchName4("");
		dspVo.setSchName5("");
		dspVo.setSchName6("");
		
		//CHK_USE: CheckUse Visible, Invisible
		dspVo.setChkUse("1");
		//KANA_ITEM: ListKanaItem Visible, Invisible
		dspVo.setKanaItem("1");
		//HELP_HTML: Help file
		dspVo.setHelpHtml("商品検索.html");
		dspVo.setUseFlg("0");
		
		return dspVo;
	}
	
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
//		lblLine1Label1.setBorder(null);
//		lblLine1Label1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblLine1Label1.setBackground(pnlFilter.getBackground());
		lblLine1Label1.setSize(100, FaceContants.TEXT_HEIGHT);
		lblLine1Label1.setLocation(xPos, yPos);
		pnlFilter.add(lblLine1Label1);
		searchItem = "1";		
				
		xPos += 100;
		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		lstData = comService.getLstFromName("PCLS", true);
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
	
	protected void doSearch() {
		int result = m_data.retrieveData();		
		if (result == 0) {
			btnF7.setEnabled(false);
		} else {
//			m_table.setRowSelectionInterval(0, 0);
//			m_table.changeSelection(0, 0, false, false);
			btnF7.setEnabled(true);
		}
		TABLE_ROWS = m_data.getRowCount();
//		TABLE_ROWS = 3;
		initTableComp();
//		leftTablePane.setPaneRows(new BasePanel[TABLE_ROWS]);
//		leftTablePane.RemovePaneRow();
		leftTablePane.removeAll();
		leftTablePane.setPaneRows(createTableRowsPanel());
		
		leftTablePane.getPaneRows();
		for(int i=0; i<TABLE_ROWS; i++)
		{
			//Gan gia tri vao GridBagTable
			for(int j=0; j <TABLE_COLUMNS; j++ )
			{
				Component comp = arrCellComponent[i][j];
				((BaseLabel)comp).setText( String.valueOf(m_data.getValueAt(i, j)) );
			}
		}
		
		NumberFormat formaterInt = new DecimalFormat("###,###,##0");
		lblNumber.setText(formaterInt.format( NumberUtils.getIntFromString(getNumber())) + "件");
		if (result == 0) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
		}		
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

	/**
	 * 
	 * @description
	 */
	private void createTable()
	{
	}
	
	/**
	 * initial the left table
	 */
	protected BaseScrollPane getTablePane() {
		initTableComp();
		leftTablePane = new HaBoxLayoutPanel(TABLE_ROWS);
		leftTablePane.setPaneHeader(createTableHeaderPane());		
		leftTablePane.setPaneRows(createTableRowsPanel());
		// add scroll pane
		BaseScrollPane scrollLeftTbl = new BaseScrollPane();
		scrollLeftTbl.getViewport().removeAll();
		scrollLeftTbl.setColumnHeaderView(leftTablePane.getPaneHeader());
		scrollLeftTbl.getViewport().add(leftTablePane, BorderLayout.CENTER);
		scrollLeftTbl.getVerticalScrollBar().setUnitIncrement(10);
		scrollLeftTbl.setAutoscrolls(true);
		
		scrollLeftTbl.setBorder(null);
		scrollLeftTbl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollLeftTbl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLeftTbl.revalidate();
		
		return scrollLeftTbl;
	}
	
	protected BaseScrollPane getTablePane(SortObjVo sortObj) {
		initTableComp();
		BaseScrollPane scrollLeftTbl = new BaseScrollPane();
		return scrollLeftTbl;
	}
	
	//Ham tao Header, Sort
	private BasePanel createTableHeaderPane() {
		int iCol = 0;
		// add header
		BasePanel paneHeader;
		paneHeader = new BasePanel();
		paneHeader.setLayout(new GridBagLayout());
		//Line 1
		//1. Dummy
		HeaderLabel lblHeaderX00Y00 = new HeaderLabel("");
		lblHeaderX00Y00.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));		
		paneHeader.add(lblHeaderX00Y00, 
				setBagConstraints(iCol++, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//2. Dummy
		//Cot co dan
		HeaderLabel lblHeaderX01Y00 = new HeaderLabel("");
		lblHeaderX01Y00.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));	
		lblHeaderX01Y00.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_PRODUCT_NAME, isAsc);
				isAsc = !isAsc;
				
			}
		});
		paneHeader.add(lblHeaderX01Y00, 
				setBagConstraints(iCol++, 0, 1, 1, 0.5, 0.5,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//3. Group Cols 3,4
		HeaderLabel lblHeaderX02Y00 = new HeaderLabel("売上単価");
		lblHeaderX02Y00.setPreferredSize(new Dimension(colHeadwidth[2] + colHeadwidth[3], FaceContants.LABLE_HEIGHT));		
		paneHeader.add(lblHeaderX02Y00, 
				setBagConstraints(2, 0, 2, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//4. Group Cols 5,6
		HeaderLabel lblHeaderX03Y00 = new HeaderLabel("当月最終仕入れ単価");
		lblHeaderX03Y00.setPreferredSize(new Dimension(colHeadwidth[4] + colHeadwidth[5], FaceContants.LABLE_HEIGHT));		
		paneHeader.add(lblHeaderX03Y00, 
				setBagConstraints(4, 0, 2, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
	
		//Line 2
		iCol = 0;
		//1. ProductCode
		HeaderLabel lblHeaderX00Y01 = new HeaderLabel(colMasterHead[iCol]);
		lblHeaderX00Y01.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));
		lblHeaderX00Y01.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_PRODUCT_CODE, isAsc);
				isAsc = !isAsc;
			}
		});
		paneHeader.add(lblHeaderX00Y01, 
				setBagConstraints(iCol++, 1, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//2. ProductName	
		//Cot co dan
		HeaderLabel lblHeaderX01Y01 = new HeaderLabel(colMasterHead[iCol]);
		lblHeaderX01Y01.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));
		lblHeaderX01Y01.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_PRODUCT_NAME, isAsc);
				isAsc = !isAsc;
			}
		});
		paneHeader.add(lblHeaderX01Y01, 
				setBagConstraints(iCol++, 1, 1, 1, 0.5, 0.5,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//3. Member
		HeaderLabel lblHeaderX02Y01 = new HeaderLabel(colMasterHead[iCol]);
		lblHeaderX02Y01.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));
		lblHeaderX02Y01.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_MEMBER, isAsc);
				isAsc = !isAsc;
			}
		});
		paneHeader.add(lblHeaderX02Y01, 
				setBagConstraints(iCol++, 1, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//4. Branch
		HeaderLabel lblHeaderX03Y01 = new HeaderLabel(colMasterHead[iCol]);
		lblHeaderX03Y01.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));
		lblHeaderX03Y01.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_BRANCH, isAsc);
				isAsc = !isAsc;
			}
		});
		paneHeader.add(lblHeaderX03Y01, 
				setBagConstraints(iCol++, 1, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//5. TaxWithoutIncluded
		HeaderLabel lblHeaderX04Y01 = new HeaderLabel(colMasterHead[iCol]);
		lblHeaderX04Y01.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));
		lblHeaderX04Y01.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_TAX_WITHOUT_INCLUDED_, isAsc);
				isAsc = !isAsc;
			}
		});
		paneHeader.add(lblHeaderX04Y01, 
				setBagConstraints(iCol++, 1, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		//6. TaxIncluded
		HeaderLabel lblHeaderX05Y01 = new HeaderLabel(colMasterHead[iCol]);
		lblHeaderX05Y01.setPreferredSize(new Dimension(colHeadwidth[iCol], FaceContants.LABLE_HEIGHT));
		lblHeaderX05Y01.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			boolean isAsc = true;
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				sortCol(I_TAX_INCLUDED, isAsc);
				isAsc = !isAsc;
			}
		});
		paneHeader.add(lblHeaderX05Y01, 
				setBagConstraints(iCol++, 1, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		paneHeader.setBorder(BorderFactory.createRaisedBevelBorder());
		paneHeader.setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
		paneHeader.setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);

		return paneHeader;
	}
	
	public void sortCol (int iCol, boolean isAsc)
	{
		for(int i=0 ; i < TABLE_ROWS - 1; i++)
		{
			for(int j=i+1; j < TABLE_ROWS; j++)
			{						
				//1. ProductCode
				Component comp1 = arrCellComponent[i][iCol];
				String strComp1 = StringUtils.emptyIfNull(((CdInputSearchText) comp1).getText());		
				Component comp2 = arrCellComponent[j][iCol];
				String strComp2 = StringUtils.emptyIfNull(((CdInputSearchText) comp2).getText());
				if(isAsc)
				{
					if(strComp1.compareTo(strComp2) < 0)
					{							
						swapData(i, j);
					}
				}
				else
				{
					if(strComp1.compareTo(strComp2) > 0)
					{							
						swapData(i, j);
					}
				}
			}
		}
	}

	protected void swapData(int iRow1, int iRow2)
	{
		Component comp1;
		Component comp2;
		
		String strComp1;
		String strComp2;
		String strTemp = "";
		
		//1. ProductCode
		comp1 = arrCellComponent[iRow1][I_PRODUCT_CODE];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_PRODUCT_CODE];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//2. ProductName
		comp1 = arrCellComponent[iRow1][I_PRODUCT_NAME];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_PRODUCT_NAME];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//3. Member
		comp1 = arrCellComponent[iRow1][I_MEMBER];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_MEMBER];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//4. Branch
		comp1 = arrCellComponent[iRow1][I_BRANCH];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_BRANCH];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//5. TaxWithoutIncluded
		comp1 = arrCellComponent[iRow1][I_TAX_WITHOUT_INCLUDED_];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_TAX_WITHOUT_INCLUDED_];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//6. TaxIncluded
		comp1 = arrCellComponent[iRow1][I_TAX_INCLUDED];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_TAX_INCLUDED];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
	}
		
	private void initTableComp() {
		arrCellComponent = new JComponent[TABLE_ROWS][TABLE_COLUMNS];
		for (int row = 0; row < TABLE_ROWS; row++) {
			createRowComponent(row);
		}		
	}
	
	//Ham tao phan Grid
	private void createRowComponent(final int row) {
		int col = 0;
		/*
		 *  first row in panel
		 */
		//1. BaseLabel ProductCode 		
		BaseLabel lblProductCode = new BaseLabel();
		lblProductCode.setPreferredSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblProductCode.setMinimumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblProductCode.setMaximumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblProductCode.setHorizontalAlignment(headerAligns[col]);
		arrCellComponent[row][col++] = lblProductCode;

		//2. BaseLabel ProductName 
		BaseLabel lblProductName = new BaseLabel();
		lblProductName.setPreferredSize(new Dimension(colHeadwidth[col], FaceContants.LABLE_HEIGHT));
		lblProductName.setMinimumSize(new Dimension(colHeadwidth[col], FaceContants.LABLE_HEIGHT));
		lblProductName.setMaximumSize(new Dimension(colHeadwidth[col], FaceContants.LABLE_HEIGHT));
		lblProductName.setHorizontalAlignment(headerAligns[col]);
		arrCellComponent[row][col++] = lblProductName;
		
		//3. BaseLabel Member		
		BaseLabel lblMember = new BaseLabel();
		lblMember.setPreferredSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblMember.setMinimumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblMember.setMaximumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblMember.setHorizontalAlignment(headerAligns[col]);
		arrCellComponent[row][col++] = lblMember;
		
		//4. BaseLabel Branch		
		BaseLabel lblBranch = new BaseLabel();
		lblBranch.setPreferredSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblBranch.setMinimumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblBranch.setMaximumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblBranch.setHorizontalAlignment(headerAligns[col]);
		arrCellComponent[row][col++] = lblBranch;
		
		//5. BaseLabel TaxWithoutIncluded		
		BaseLabel lblTaxWithoutIncluded = new BaseLabel();
		lblTaxWithoutIncluded.setPreferredSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblTaxWithoutIncluded.setMinimumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblTaxWithoutIncluded.setMaximumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblTaxWithoutIncluded.setHorizontalAlignment(headerAligns[col]);
		arrCellComponent[row][col++] = lblTaxWithoutIncluded;
		
		//6. BaseLabel lblTaxIncluded		
		BaseLabel lblTaxIncluded = new BaseLabel();
		lblTaxIncluded.setPreferredSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblTaxIncluded.setMinimumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblTaxIncluded.setMaximumSize(new Dimension(colHeadwidth[col] + 1, FaceContants.LABLE_HEIGHT));
		lblTaxIncluded.setHorizontalAlignment(headerAligns[col]);
		arrCellComponent[row][col++] = lblTaxIncluded;
		
		for (Component comp : arrCellComponent[row]) {
			if (comp instanceof JLabel)
				continue;

			if (row % 2 == 0) {
				comp.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
				comp.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
			} else {
				comp.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
				comp.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
			}
		}
	}
	
	//Ve giao dien cho Grid
	private BasePanel[] createTableRowsPanel(){
		GridBagConstraints gridBagConstr = new GridBagConstraints();
		Insets insert = new Insets(1, 1, 1, 1);
		final BasePanel[] paneArrRows  = new BasePanel[TABLE_ROWS];
		
		for (int row = 0; row < TABLE_ROWS; row++) {

			//get row panel
			BasePanel paneRow = new BasePanel();
			// draw a row panel
			paneRow.setLayout(new GridBagLayout());			
		
			for (int column = 0; column < TABLE_COLUMNS; column++) {				
				final Component cell = arrCellComponent[row][column];
				if(column == 1)// Cot cuoi co dan
				{
					gridBagConstr = setBagConstraints(column, row, 1, 1, 0.5, 0.5, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insert, 0, 0);
				}
				else
				{
					gridBagConstr = setBagConstraints(column, row, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, insert, 0, 0);
				}
				// add mouseMoved event
				cell.addMouseMotionListener(new MouseInputAdapter (){
					public void mouseMoved(MouseEvent e) {
						for (int x = 0; x < TABLE_ROWS; x++){
							if(curSelectedRow != x){
								paneArrRows[x].setBorder(null);
								 if (x % 2 == 0) {
										paneArrRows[x].setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
										paneArrRows[x].setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
								} else {
										paneArrRows[x].setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
										paneArrRows[x].setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
								}									
								
								paneArrRows[x].setAlpha(0.5f);	 
							}
						 }
						
						 for (int x = 0; x < TABLE_ROWS; x++)
							if(curSelectedRow != x)
								for (int y = 0; y < TABLE_COLUMNS; y++) {
									if (cell.hashCode() == arrCellComponent[x][y].hashCode()) {
										// set current focus panel border
										paneArrRows[x].setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR));
										paneArrRows[x].setBackground(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR);
										paneArrRows[x].setForeground(ColorConstants.TABLE_ROLLOVER_FOREGROUND_COLOR);
										paneArrRows[x].setAlpha(1f);
									}
								}
					}
				});
				
				cell.addFocusListener(new FocusListener(){
					public void focusGained(FocusEvent e) {
						for (int x = 0; x < TABLE_ROWS; x++)
							for (int y = 0; y < TABLE_COLUMNS; y++) {
								if (cell.hashCode() == arrCellComponent[x][y].hashCode()) {
									// set current focus panel border
									curSelectedRow = x;
									paneArrRows[x].setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR));
									paneArrRows[x].setBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
									paneArrRows[x].setForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
									
									paneArrRows[x].setAlpha(1f);
								}
							}

						if (preSelectedRow != curSelectedRow) {
							preSelectedRow = curSelectedRow;
							Rectangle r = new Rectangle(0, 0, paneArrRows[curSelectedRow].getWidth(), paneArrRows[curSelectedRow].getHeight());
							paneArrRows[curSelectedRow].scrollRectToVisible(r);
						}
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
					 */
					public void focusLost(FocusEvent e) {
						paneArrRows[preSelectedRow].setBorder(null);
						if (preSelectedRow % 2 == 0) {
							paneArrRows[preSelectedRow].setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
							paneArrRows[preSelectedRow].setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
						} else {
							paneArrRows[preSelectedRow].setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
							paneArrRows[preSelectedRow].setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
						}
						
						if (preSelectedRow >= TABLE_ROWS){
							paneArrRows[preSelectedRow].setAlpha(0.5f);
						} else {
							paneArrRows[preSelectedRow].setAlpha(1f);
						}

						// reset background color for component
						for (Component comp : arrCellComponent[preSelectedRow]) {

							if (comp instanceof JLabel)
								continue;

							if (preSelectedRow % 2 == 0) {
								comp.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
								comp.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
							} else {
								comp.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
								comp.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
							}
						}
					}
				});
				
				paneRow.add(cell, gridBagConstr);	
			}
			if (row % 2 == 0) {
				paneRow.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
				paneRow.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
			} else {
				paneRow.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
				paneRow.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
			}
			
			paneArrRows[row] = paneRow;
		}
		
		return paneArrRows;
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
		SearchPE01FrameGridBagLayout templte = new SearchPE01FrameGridBagLayout();
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
		X_FRAME_LENGTH = 1100;
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
