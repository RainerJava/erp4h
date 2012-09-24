package com.fas.sapp.master.orderprocessing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.bussiness.BaseMasterFrameLayoutNEW;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.ViewLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.jface.table.TableHeaderRenderer;
import com.fas.jface.text.BaseDatePicker;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputSearchText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.text.ImeText;
import com.fas.jface.text.IntegerNumberText;
import com.fas.sapp.search.SearchPC01Frame;
import com.fas.sapp.search.SearchPE01Frame;
import com.fas.sapp.search.SearchPE03Frame;
import com.fas.sapp.search.SearchPG01Frame;
import com.fas.sapp.search.SearchPG02Frame;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.search.SearchVo;


/**
 * @author THANGNM
 * 
 */
public class OrderProcessing extends BaseMasterFrameLayoutNEW {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5896382197319798467L;
	
	//*************************************** Components  *********************************//
	
	private BaseComboBox cbbMode = null;
	private BaseInputText txtOrder = null;	
	private BaseComboBox cbbProcess = null;
	private BaseComboBox cbbMoney = null;
	private BaseLabel lblPermission = null;
	private BaseDatePicker dtpSellDate = null;
	private CdInputSearchText txtSeller = null;
	
	private BaseLabel lblSellerName = null;
	private BaseLabel lblTotal = null;
	private BaseLabel lblBalanceAvailable = null;
	private BaseLabel lblTotalValue = null;
	
	private BasePanel pnlBalance ;
	
	private BasePanel pnlThird;
	
	private int iRightSpace = 20;

	/* Define table */
	private InspectTable table;
	//private InspectTableModel defaultTableModel;
	private DefaultTableModel defaultTableModel;
	private BaseScrollPane psTable;
	private static final String colMasterHead[] = { "行№","商品番号","商品名","数量","単価","金額","摘要"};
	private static final int[] colMasterHeadwidth = {40  , 80      , 285    , 100  , 100  , 100 , 150};
	private final int[] headerAligns = {BaseLabel.CENTER, BaseLabel.LEFT, BaseLabel.LEFT, 
			BaseLabel.RIGHT, BaseLabel.RIGHT, BaseLabel.RIGHT, BaseLabel.LEFT};
	
	//INDEX LIST VIEW 
	private static final int I_NO = 0; //So dong
	private static final int I_PRODUCT_CODE = 1; //Ma hang
	private static final int I_PRODUCT_NAME = 2; //Ten hang
	private static final int I_QUANTITY = 3; //so luong
	private static final int I_ORDER_PRICE = 4; //don gia
	private static final int I_PRICE = 5; //gia
	private static final int I_SUMMARY= 6; //tom tat
	
	private int iconTextGap = 4;
	
	private JPopupMenu popup = null;
	
	private int order = 1;	
	/** */
	private int iCount = 0;

	//*************************************** Constructors  *********************************//
	public OrderProcessing(){
		super();
		init();
	}
	
	public OrderProcessing(BaseFrame frame){
		super(frame);
		init();
	}
	
	public OrderProcessing(BaseFrame frame, String title){
		super();
		init();
	}

	//*************************************** Business Methods *********************************//

	/**
	 * This method is used to initilize component
	 */
	private void init() {
		//getHeader();
		getBodyLeft();
		setFrameSize();
	}
	
	private int row ;
	//Table events
	private void tableMouseClicked(MouseEvent evt){
		row = table.getSelectedRow();
	}
	
	/**
	 * This method is used to create table
	 * @return BaseScrollPane
	 */
	private BaseScrollPane getMasterTable() {
		BaseScrollPane tablePnl;	
		defaultTableModel = new DefaultTableModel(null,colMasterHead);
		table = new InspectTable(defaultTableModel) {
			/** */
			private static final long serialVersionUID = 1L;
			private InspectTableRenderer renderer = new InspectTableRenderer(headerAligns);
			private HaCellIntegerTextBoxEditor txtEditor = new HaCellIntegerTextBoxEditor(new IntegerNumberText(), table);
			private HaCellCdInputSearchTextBoxEditor txtCdSearchEditor = new HaCellCdInputSearchTextBoxEditor(new CdInputSearchText("", 0, CdInputText.IM_OFF, 4),table);		
			private HaCellBaseInputTextEditor txtText = new HaCellBaseInputTextEditor(new BaseInputText(),table);
			private HaCellBaseInputTextEditorNotEditable lblText = new HaCellBaseInputTextEditorNotEditable(new BaseInputText(),table);
			private CDInputTextSearchRender txtCdInputTextSearchRender = new CDInputTextSearchRender();
			public TableCellRenderer getCellRenderer(int row, int col) {
//				if (col == I_PRODUCT_CODE){//Is CdInputSearchText
//					return txtCdInputTextSearchRender;
//				}
				return renderer;
			}		
			public TableCellEditor getCellEditor(int row, int column) {
				if(column == I_PRODUCT_CODE){
					return txtCdSearchEditor;
				}else if (column == I_PRODUCT_NAME) {
					return lblText;				
				}else if(column == I_QUANTITY) {
					txtEditor.setMinValue(-99999);
					txtEditor.setMaxValue( 99999);
					txtEditor.addCellEditorListener(new CellEditorListener() {
						@Override
						public void editingStopped(ChangeEvent e) {
							recalculateTotal();
						}		
						@Override
						public void editingCanceled(ChangeEvent arg0) {
						}
					});	
					return txtEditor;
				}else if(column == I_ORDER_PRICE) {
					txtEditor.setMinValue(-999999);
					txtEditor.setMaxValue( 999999);
					// Ham tinh lai gia tri Total
					txtEditor.addCellEditorListener(new CellEditorListener() {
						@Override
						public void editingStopped(ChangeEvent e) {
							recalculateTotal();
						}	
						@Override
						public void editingCanceled(ChangeEvent arg0) {
						}
					});	
					return txtEditor;
				}
				else if (column == I_PRICE) {
					return lblText;				
				}				
				if (column == I_SUMMARY) {
					txtText.getTxtText().setIMType(ImeText.IM_HIRAGANA);
					return txtText;
				}
				return null;
			}
			
			public boolean isCellEditable(int row, int column) {
				return (column == I_PRODUCT_CODE ||	column == I_QUANTITY || column == I_PRODUCT_NAME ||
						column == I_ORDER_PRICE  || column == I_PRICE || column == I_SUMMARY );			
			}
		};
			
		//initData For table
		initDataForTable();
		//Events of table
		table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	tableMouseClicked(evt);
            }
        });
		
		defaultTableModel.fireTableDataChanged();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) table.getColumnModel();
		
		for (int i = 0; i < table.getColumnCount(); i++){
			if(i == I_PRODUCT_CODE || i == I_QUANTITY
			|| i == I_ORDER_PRICE  || i == I_SUMMARY )
			{
				// COLUMN setting
				TableHeaderRenderer cellRenderer = new TableHeaderRenderer();
				cellRenderer.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
				cellRenderer.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
				table.getColumn(i).setHeaderRenderer(cellRenderer);
			}
		}

		//Chi cho mot cot tu thay doi do rong
		for (int i = 0; i < colMasterHead.length; i++) {
			defModel.getColumn(i).setPreferredWidth(colMasterHeadwidth[i]);
			if (i != 2) {
				defModel.getColumn(i).setMinWidth(colMasterHeadwidth[i]);
				defModel.getColumn(i).setMaxWidth(colMasterHeadwidth[i]);
			}
		}
		
//		for (int i = 0; i < colMasterHead.length; i++) {
//			defModel.getColumn(i).setMinWidth(colMasterHeadwidth[i]);
//			defModel.getColumn(i).setWidth(colMasterHeadwidth[i]);
//			defModel.getColumn(i).setMaxWidth(colMasterHeadwidth[i] + 200);
//		}
		
		table.getTableHeader().setReorderingAllowed(false);
		// invisible Column Code
		defModel.getColumn(0).setPreferredWidth(0);
		defModel.getColumn(0).setMaxWidth(0);

		//Popup menu
		initPopup();
		
		initTableEvent();
		
		tablePnl = new BaseScrollPane(table);
//		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePnl.setAutoscrolls(false);
		tablePnl.repaint();
		return tablePnl; 
	}
	
	private void recalculateTotal()	{
		//Xu li lay ra nhung gia tri khac tu gia tri ProdCode 
		int selectedRow = table.getSelectedRow();
		int selectedCol = table.getSelectedColumn();
		
		int iQuantity = 0;
		int iUnitPrice = 0;
		if( !(StringUtils.isValid( StringUtils.emptyIfNull((String)table.getValueAt(selectedRow, I_QUANTITY))) && StringUtils.isValid( StringUtils.emptyIfNull((String)table.getValueAt(selectedRow, I_ORDER_PRICE)) ))  )
			return;
		iQuantity = NumberUtils.getIntFromString( StringUtils.emptyIfNull((String)table.getValueAt(selectedRow, I_QUANTITY)) );
		iUnitPrice = NumberUtils.getIntFromString( StringUtils.emptyIfNull((String)table.getValueAt(selectedRow, I_ORDER_PRICE)) );
		int iPrice = iQuantity * iUnitPrice;
		table.setValueAt(StringUtils.convertIntegerToStr(iPrice), selectedRow, I_PRICE);
		
		//Calc TotalPrice		
		int iTotalValue = 0;
		for(int i=0 ; i< table.getRowCount(); i++)
		{
			iTotalValue += NumberUtils.getIntFromString( StringUtils.emptyIfNull((String)table.getValueAt(i, I_PRICE)));
		}
		lblTotalValue.setText(StringUtils.convertIntegerToStr(iTotalValue));
	}
	
	/**
	 * This method is used to create popup menu.
	 */
	private void initPopup() {
		
		popup = new JPopupMenu();
		
		//insert row
		JMenuItem newItem = new JMenuItem("行挿入");
		newItem.setHorizontalAlignment(JMenuItem.LEFT);
		popup.add(newItem);
		newItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {		
				int rowCount = defaultTableModel.getRowCount();
				if (rowCount > 0) {
					order = rowCount + 1;
					System.out.println("ORDER : " + order);
					defaultTableModel.addRow(new Object[]{order,"","","","","",""});
					order++;
				} else {
					System.out.println("ORDER : " + order);
					defaultTableModel.addRow(new Object[]{order});
					order++;
				}
			}
		});
		newItem.setIcon(new ImageIcon(this.getClass().getResource("/image/add.png")));
		newItem.setIconTextGap(iconTextGap);
		
		//copy row
		JMenuItem copyItem = new JMenuItem("行複写");
		copyItem.setHorizontalAlignment(JMenuItem.LEFT);
		popup.add(copyItem);
		copyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {			      
				int rowCount = defaultTableModel.getRowCount();
				if (rowCount > 0) {
                	 order = rowCount + 1;
                	 //get all table data
                    Vector data = defaultTableModel.getDataVector();
                    Vector rowCopy = (Vector) data.elementAt(row);
                    //copy row data
                    rowCopy = (Vector) rowCopy.clone();
                    //set new order for row is copied
                    rowCopy.setElementAt(order, 0);
                    defaultTableModel.addRow(rowCopy);
                    order++;
                } 
			}
		});
		copyItem.setIcon(new ImageIcon(this.getClass().getResource("/image/copy.png")));
		copyItem.setIconTextGap(iconTextGap);
		
		//delete row
		JMenuItem deleteItem = new JMenuItem("行削除");
		deleteItem.setHorizontalAlignment(JMenuItem.LEFT);
		popup.add(deleteItem);
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				if(defaultTableModel.getRowCount() > 0 ) {
					defaultTableModel.removeRow(row);
					resetOrder();
				}
			}
		});
		deleteItem.setIcon(new ImageIcon(this.getClass().getResource("/image/del.png")));
		deleteItem.setIconTextGap(iconTextGap);
		
		table.setComponentPopupMenu(popup);		
	}
	
	/**
	 * This method is used to update order column
	 */
	public void resetOrder() {
	        int k = 1;
	        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
	            defaultTableModel.setValueAt(k++, i, 0);
	        }
	        order = defaultTableModel.getRowCount() + 1;
	 }
	
	protected void focusCellEditor(int row, int column) {
		if (table.getRowCount() > 0) {
			if(column == I_PRODUCT_CODE || column == I_QUANTITY || column == I_ORDER_PRICE || column == I_SUMMARY){			
				table.changeSelection(row, column, false, false);			
				boolean success = table.editCellAt(row, column);
				if (success) {
					if(column == I_PRODUCT_CODE){
						HaCellCdInputSearchTextBoxEditor editor = (HaCellCdInputSearchTextBoxEditor)table.getCellEditor();
						editor.requestFocus();
					} else if(column == I_QUANTITY || column == I_ORDER_PRICE){
						HaCellIntegerTextBoxEditor editor = (HaCellIntegerTextBoxEditor)table.getCellEditor();
						editor.requestFocus();
					} else if(column == I_SUMMARY){
						HaCellBaseInputTextEditor editor = (HaCellBaseInputTextEditor)table.getCellEditor();
						editor.requestFocus();
					}
			    }
			}
		}
	}
	
	private void initDataForTable(){
		ProductData dataVo = null;			
		Vector v = null;
	
		for(int i = 1 ; i <= 10;i++){
			v = new Vector();
			dataVo = new ProductData(String.valueOf(i),"","","","","","");
			v.add(dataVo.getNumber());
			v.add(dataVo.getProductCode());
			v.add(dataVo.getProductName());
			v.add(dataVo.getQuantity());
			v.add(dataVo.getOrderPrice());
			v.add(dataVo.getPrice());
			v.add(dataVo.getSummary());
			defaultTableModel.addRow(v);
		}
	}
	//*************************************** Override Methods *********************************//
	
	@Override
	protected String getSubName() {
		// TODO Auto-generated method stub
		if (exeMenu != null) {
			return StringUtils.emptyIfNull(exeMenu.getMenuexeName());
		} else {
			return "";
		}
	}

	@Override
	protected String getHelpInfor() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected void doF1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF4() {
//		doSearchPE03();
//		doSearchPG01();
//		doSearchPG02();
		doSearchPC01();
	}

	@Override
	protected void doF5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF6() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF7() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF8() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF9() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void doF10() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doF11() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean[] enableBtn() {
		// TODO Auto-generated method stub
		boolean[] fBtn = { true, true, true, true, true, true, true, true, true, true, true, true };
		//fBtn[2] = false; // invisiable 	
		return fBtn;
	}
		
	@Override
	protected void doFirst() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPre() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void doNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doLast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doSingleClick(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doDoubleClick(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected BasePanel getBodyCenter() {
		// TODO Auto-generated method stub
		return null;
	}

// <editor-fold defaultstate="collapsed" desc="Your Fold Comment">
	@Override
	protected BasePanel getBodyLeft() {
	
		setTitle("売上伝票処理");
		setResizable(true);
		
		int xPos = 0;
		int yPos = 0;
		int iGap5 = 5;
		int iGap10 = 10;
		int iGap20 = 20;
		int iGap45 = 45;

		int LABEL_HEIGHT = FaceContants.LABLE_HEIGHT;
		int TXT_TEXT_FIELD_LENGTH_107 = 107;
		int TXT_TEXT_FIELD_LENGTH_130 = 130;
		int TXT_TEXT_FIELD_LENGTH_140 = 140;
		int TXT_TEXT_FIELD_LENGTH_180 = 180;
		
//		int iPanelWidth = X_FRAME_LENGTH - iRightSpace; // KQ 870
		
		//I. First Panel
		BasePanel pnlFirst = new BasePanel();
		pnlFirst.setBorder(FaceContants.TITLE_BORDER); //iPanelWidth
		
		//1.1 Label mode
		xPos = iGap5;
		yPos = iGap5;
		EditLabel lblMode = new EditLabel("処理モード");	
		lblMode.setLocation(xPos, yPos);
		lblMode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		lblMode.setHorizontalAlignment(SwingConstants.CENTER);
		pnlFirst.add(lblMode);
		//1.2 Label order
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap20;	
		ViewLabel lblOrder = new ViewLabel("伝票番号");
		lblOrder.setLocation(xPos, yPos);
		lblOrder.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		pnlFirst.add(lblOrder,BorderLayout.PAGE_START);
		//1.3 Label process		
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap45;	
		EditLabel lblProcess = new EditLabel("処　理");
		lblProcess.setLocation(xPos, yPos);
		lblProcess.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		lblProcess.setHorizontalAlignment(SwingConstants.CENTER);
		pnlFirst.add(lblProcess,BorderLayout.PAGE_START);
		//2.1 ComboBox mode
		xPos = iGap5;
		yPos += FaceContants.LABLE_HEIGHT;
		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		lstData = comService.getLstFromName("MODE", false);
		ArrayListComboBoxModel aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbMode = new BaseComboBox(aModel);
		cbbMode.setToolTipText(StringUtils.trimAllVN( lblMode.getText() + "を選択して下さい。"));
		cbbMode.setLocation(xPos, yPos);
		cbbMode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		cbbMode.setPopupWidth(TXT_TEXT_FIELD_LENGTH_107);
		cbbMode.setSelectedIndex(0);
		Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup)child;
		JList list = popup.getList();
//		list.setSelectionBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
//		list.setSelectionForeground(Color.BLACK);
		list.setBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
		cbbMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbbMode.getSelectedIndex() == 0)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
//					list.setSelectionBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
//					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 1)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
//					list.setSelectionBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
//					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 2)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
//					list.setSelectionBackground(ColorConstants.LABEL_DELETE_MODE_BACKGROUND_COLOR);
//					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_DELETE_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 3)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
//					list.setSelectionBackground(ColorConstants.LABEL_COPY_MODE_BACKGROUND_COLOR);
//					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_COPY_MODE_BACKGROUND_COLOR);
				}
			}
		});
		pnlFirst.add(cbbMode);
		//2.2 TextBox order
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap20;	
		txtOrder = new BaseInputText();
		txtOrder.setLocation(xPos, yPos);
		txtOrder.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		pnlFirst.add(txtOrder);		
		//2.3 ComboBox process
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap45;
		lstData = null;
		lstData = comService.getLstFromName("PROC", false);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE);
		cbbProcess = new BaseComboBox(aModel);
		cbbProcess.setToolTipText(StringUtils.trimAllVN( lblMode.getText() + "を選択して下さい。"));
		cbbProcess.setLocation(xPos, yPos);
		cbbProcess.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		cbbProcess.setPopupWidth(TXT_TEXT_FIELD_LENGTH_107);
		pnlFirst.add(cbbProcess);
		
		//Label Permission
		lblPermission = new BaseLabel("");
		lblPermission.setBounds( 755, yPos, 100, LABEL_HEIGHT); //700
		lblPermission.setForeground(Color.RED);
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.RIGHT);
		pnlFirst.add(lblPermission);		
		
		//*****************************
		//II. Second Panel
		BasePanel pnlSecond = new BasePanel();
		pnlSecond.setBorder(FaceContants.TITLE_BORDER);
		
		//1.1 Label money
		xPos = iGap5;
		yPos = iGap5;
		EditLabel lblMoney = new EditLabel("現金・掛区分");	
		lblMoney.setLocation(xPos, yPos);
		lblMoney.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSecond.add(lblMoney);
		//1.2 Label dtpSellDate
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap20;	
		EditLabel lblSellDate = new EditLabel("売上日");
		lblSellDate.setLocation(xPos, yPos);
		lblSellDate.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		pnlSecond.add(lblSellDate,BorderLayout.PAGE_START);
		lblSellDate.setHorizontalAlignment(SwingConstants.CENTER);
		//1.3 Label seller		
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap45;	
		EditLabel lblSeller = new EditLabel("売上先");
		lblSeller.setLocation(xPos, yPos);
		lblSeller.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107 + TXT_TEXT_FIELD_LENGTH_107 + TXT_TEXT_FIELD_LENGTH_107 + 50, FaceContants.LABLE_HEIGHT));
		pnlSecond.add(lblSeller,BorderLayout.PAGE_START);
		lblSeller.setHorizontalAlignment(SwingConstants.CENTER);
		
		//2.1 ComboBox Money
		xPos = iGap5;
		yPos += FaceContants.LABLE_HEIGHT;
		lstData = null;
		comService = new ComboServiceImpl();
		lstData = comService.getLstFromName("CC", false);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbMoney = new BaseComboBox(aModel);
		cbbMoney.setToolTipText(StringUtils.trimAllVN( lblMode.getText() + "を選択して下さい。"));
		cbbMoney.setLocation(xPos, yPos);
		cbbMoney.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		cbbMoney.setPopupWidth(TXT_TEXT_FIELD_LENGTH_107);
		pnlSecond.add(cbbMoney);
		//2.2 DateTimePicker dtpSellDate
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap20;	
		dtpSellDate = new BaseDatePicker();
		dtpSellDate.setToolTipText( StringUtils.trimAllVN( lblSellDate.getText()  ) + "を入力して下さい。");
		dtpSellDate.setBounds(xPos, yPos, TXT_TEXT_FIELD_LENGTH_130, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(dtpSellDate);
		//2.3 TextBox seller
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap45;
		txtSeller = new CdInputSearchText("", 0, CdInputText.IM_OFF, 4);
		txtSeller.setBounds(new Rectangle(xPos, yPos, TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		txtSeller.getFindButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doSearchPE03();				
			}
		});
		pnlSecond.add(txtSeller);
		//2.4 Label sellerName	
		xPos += TXT_TEXT_FIELD_LENGTH_107;	
		lblSellerName = new BaseLabel("");
		lblSellerName.setLocation(xPos, yPos);
		lblSellerName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107 + TXT_TEXT_FIELD_LENGTH_107 + 50 , FaceContants.LABLE_HEIGHT));
		pnlSecond.add(lblSellerName,BorderLayout.PAGE_START);
		
		xPos = (X_FRAME_LENGTH - iGap5) - iRightSpace - TXT_TEXT_FIELD_LENGTH_180;
		System.out.println(xPos + " gia tri " + (X_FRAME_LENGTH - iGap5));
		yPos = iGap5;
		//2.5 Balance
		pnlBalance = new BasePanel();	
		pnlBalance.setLocation(xPos, yPos);
		pnlBalance.setSize(TXT_TEXT_FIELD_LENGTH_180, FaceContants.LABLE_HEIGHT*4);
		pnlSecond.add(pnlBalance);
		//POINT
		xPos = 0;
		yPos = 0;
		BaseLabel lblPoint = new BaseLabel("<html>ポ<br>イ<br>ン<br>ト<br></html>"); 
		lblPoint.setLocation(xPos, yPos);
		lblPoint.setSize(TXT_TEXT_FIELD_LENGTH_180 - TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT*4);
		lblPoint.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBalance.add(lblPoint,BorderLayout.LINE_START);
		//Line lbl Balance
		xPos += lblPoint.getWidth();
		ViewLabel lblBalance = new ViewLabel("残　　高");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setLocation(xPos, yPos);
		lblBalance.setSize(TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT);
		pnlBalance.add(lblBalance);		
		//Line lbl BalanceValue
		yPos += FaceContants.LABLE_HEIGHT;
		BaseLabel lblBalanceValue = new BaseLabel("");
		lblBalanceValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalanceValue.setLocation(xPos, yPos);
		lblBalanceValue.setSize(TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT);
		pnlBalance.add(lblBalanceValue);		
		//Line lbl Usable
		yPos += FaceContants.LABLE_HEIGHT;
		ViewLabel lblUsable = new ViewLabel("利用可能");
		lblUsable.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsable.setLocation(xPos, yPos);
		lblUsable.setSize(TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT);
		pnlBalance.add(lblUsable);		
		//Line lbl UsableValue
		yPos += FaceContants.LABLE_HEIGHT;
		BaseLabel lblUsableValue = new BaseLabel("");
		lblUsableValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsableValue.setLocation(xPos, yPos);
		lblUsableValue.setSize(TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT);
		pnlBalance.add(lblUsableValue);
		
		//*****************************
		//III. Third Panel Table
		xPos = iGap5;
		yPos = iGap5;
		pnlThird = new BasePanel();
		pnlThird.setBorder(FaceContants.TITLE_BORDER);
		
		psTable = getMasterTable();
		psTable.setLocation(xPos, yPos);		
		psTable.setSize((X_FRAME_LENGTH - iGap5) - iRightSpace - iGap5, FaceContants.LABLE_HEIGHT*11 + 4);
		psTable.getViewport().add(table);
//		pnlThird.setLayout(new GridLayout(1, 1));
		pnlThird.add(psTable);	
		pnlThird.setBorder(FaceContants.TITLE_BORDER);
				
		//Label total
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) table.getColumnModel();
		
		int iCol2Width =  psTable.getWidth() - (colMasterHeadwidth[0] + colMasterHeadwidth[1] + colMasterHeadwidth[3] + 
				colMasterHeadwidth[4] + colMasterHeadwidth[5] + colMasterHeadwidth[6]);	

		lblTotal = new BaseLabel("合計");	
//		lblTotal.setLocation(defModel.getColumn(0).getPreferredWidth() + defModel.getColumn(1).getPreferredWidth() + defModel.getColumn(2).getPreferredWidth() + defModel.getColumn(3).getPreferredWidth() -2, psTable.getHeight() + iGap5);
		lblTotal.setLocation(colMasterHeadwidth[0] + colMasterHeadwidth[1] + iCol2Width + colMasterHeadwidth[3] + 5 - 2, psTable.getHeight() + 5);
		lblTotal.setSize(defModel.getColumn(4).getPreferredWidth()+1, FaceContants.LABLE_HEIGHT);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		pnlThird.add(lblTotal);
		
		//Label totalValue
		lblTotalValue = new BaseLabel("0");
//		lblTotalValue.setLocation(defModel.getColumn(0).getWidth() + defModel.getColumn(1).getPreferredWidth()  + defModel.getColumn(2).getPreferredWidth() + defModel.getColumn(3).getPreferredWidth() + defModel.getColumn(4).getPreferredWidth() -2, psTable.getHeight() +  iGap5);
		lblTotalValue.setLocation(colMasterHeadwidth[0] + colMasterHeadwidth[1] + iCol2Width + colMasterHeadwidth[3] + colMasterHeadwidth[4] + 5 - 2, psTable.getHeight() + 5);
		lblTotalValue.setSize(defModel.getColumn(5).getPreferredWidth()+1, FaceContants.LABLE_HEIGHT);
		lblTotalValue.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlThird.add(lblTotalValue);
		
		pnlFirst.setSize((X_FRAME_LENGTH - iGap5), FaceContants.LABLE_HEIGHT*2 + iGap5*2);
		pnlSecond.setSize((X_FRAME_LENGTH - iGap5), FaceContants.LABLE_HEIGHT*4 + iGap5*2);
		pnlThird.setSize((X_FRAME_LENGTH - iGap5), FaceContants.LABLE_HEIGHT*11 + 4 + lblTotalValue.getHeight() + 16);

		//GroupLayout
//		GroupLayout gl_middlePnl = new GroupLayout(pnlLeft);
//		gl_middlePnl.setHorizontalGroup(
//			gl_middlePnl.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_middlePnl.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(gl_middlePnl.createParallelGroup(Alignment.LEADING)
//						.addComponent(pnlFirst)
//						.addComponent(pnlSecond)						
//						.addComponent(pnlThird))
//					.addContainerGap())
//		);
//		gl_middlePnl.setVerticalGroup(
//			gl_middlePnl.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_middlePnl.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(pnlFirst)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(pnlSecond)
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(pnlThird)
//				.addContainerGap())
//		);
		
		GroupLayout layout = new GroupLayout(pnlLeft);
		
		int iGap = 7;
		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup()
			    				.addComponent(pnlFirst, GroupLayout.DEFAULT_SIZE, (X_FRAME_LENGTH - iGap5), Short.MAX_VALUE)
			    				.addGap(iGap)
			    				.addComponent(pnlSecond, GroupLayout.DEFAULT_SIZE, (X_FRAME_LENGTH - iGap5), Short.MAX_VALUE)
			    				.addGap(iGap)
			    				.addComponent(pnlThird))
			    );

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)//||
				.addGroup(layout.createSequentialGroup()				    
				    		.addComponent(pnlFirst , GroupLayout.PREFERRED_SIZE, FaceContants.LABLE_HEIGHT*2 + iGap5*2, GroupLayout.PREFERRED_SIZE)
				    		.addGap(iGap)
				    		.addComponent(pnlSecond, GroupLayout.PREFERRED_SIZE, FaceContants.LABLE_HEIGHT*4 + iGap5*2, GroupLayout.PREFERRED_SIZE)
				    		.addGap(iGap)
				    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)
				    				.addComponent(pnlThird))
				)
		);
//		pnlLeft.setLayout(gl_middlePnl);
		pnlLeft.setLayout(layout);		
		pnlLeft.setPreferredSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH - iGap5));
		pnlLeft.setBorder(FaceContants.TITLE_BORDER);
		mainPanel.add(pnlLeft,BorderLayout.CENTER);

		pnlLeft.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				if (iCount != 0) {
					int topY = pnlLeft.getHeight()
							- FaceContants.LABLE_HEIGHT - 2
							* FaceContants.VERTICAL_SPACE;
					//infoPnl.setLocation(15, topY);
					
					psTable.setSize(pnlLeft.getWidth() - iRightSpace - 5, FaceContants.LABLE_HEIGHT*11 + 4);


					int xPos = pnlLeft.getWidth() - iRightSpace - pnlBalance.getWidth() ;
//					System.out.println(pnlLeft.getWidth() + " balance " + pnlBalance.getWidth() );
					int yPos = 5;
					//2.5 Balance
					pnlBalance.setLocation(xPos, yPos);
					int iCol2Width =  pnlLeft.getWidth() - (colMasterHeadwidth[0] + colMasterHeadwidth[1] + colMasterHeadwidth[3] + 
							colMasterHeadwidth[4] + colMasterHeadwidth[5] + colMasterHeadwidth[6]);	
					
					DefaultTableColumnModel defModel = (DefaultTableColumnModel) table.getColumnModel();
					defModel.getColumn(2).setPreferredWidth(iCol2Width);
					defModel.getColumn(2).setMinWidth(iCol2Width);
					defModel.getColumn(2).setMaxWidth(iCol2Width);
					
					lblTotal.setLocation(colMasterHeadwidth[0] + colMasterHeadwidth[1] + iCol2Width + colMasterHeadwidth[3] + 5, psTable.getHeight() + 5);
					lblTotalValue.setLocation(colMasterHeadwidth[0] + colMasterHeadwidth[1] + iCol2Width + colMasterHeadwidth[3] + colMasterHeadwidth[4] + 5, psTable.getHeight() + 5);
					
					//pnlThird.setSize(pnlThird.getWidth(), pnlLeft.getHeight() - 300);
				}
				iCount++;
			}
		});
		return pnlLeft;
	}
// </editor-fold>

	/**
	 * Ham goi form tim kiem
	 * 
	 * 
	 */
	public void doSearchPE03() {
		SearchPE03Frame search = new SearchPE03Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtSeller.setText(searchData.getProPer1());
			lblSellerName.setText(searchData.getProPer3());
		}
	}
	
	public void doSearchPG01() {
		SearchPG01Frame search = new SearchPG01Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtSeller.setText(searchData.getProPer1());
			lblSellerName.setText(searchData.getProPer3());
		}
	}

	public void doSearchPG02() {
		SearchPG02Frame search = new SearchPG02Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtSeller.setText(searchData.getProPer1());
			lblSellerName.setText(searchData.getProPer3());
		}
	}
	

	public void doSearchPC01() {
		SearchPC01Frame search = new SearchPC01Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtSeller.setText(searchData.getProPer1());
			lblSellerName.setText(searchData.getProPer3());
		}
	}
	
	
	@Override
	protected BasePanel getHeader() {
		return null;
	}
	
	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void setFrameSize() {
		// TODO Auto-generated method stub
		//super.setFrameSize();
		X_FRAME_LENGTH = 906;
		Y_FRAME_LENGTH = 590;
//		Y_FRAME_LENGTH = Y_BODY_LENGTH + BTN_PANEL_HEIGHT + Y_FOOTER_LENGTH + Y_HEADER_LENGTH + getHeightBorder();
		setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		setPreferredSize(new Dimension(X_FRAME_LENGTH , Y_FRAME_LENGTH));
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		setLocation((maximumWindowBounds.width - X_FRAME_LENGTH) / 2, (maximumWindowBounds.height - Y_FRAME_LENGTH) / 2);
	}
	
	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu) == false) {
			// Not permission show red text and disable btn
			lblPermission.setText(NameConstants.getName("UPDFLG"+"1"));
			lblPermission.setBorder(null);
		}
	}
	
	private void initTableEvent(){

		table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "keyTab");
	    table.getActionMap().put("keyTab", new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbbMode.requestFocus();
			}
		});
	    
	    table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "keyShiftTab");
	    table.getActionMap().put("keyShiftTab", new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtSeller.requestFocus();
			}
		});
	       
		
	    // -> RIGHT KEY
		table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightKey");
	    table.getActionMap().put("rightKey", new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextCellEditor();
			}
		});
	    
	    //<- LEFT KEY
	    table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftKey");
	    table.getActionMap().put("leftKey", new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				previousCellEditor();
			}
		});   

	    //^ UP KEY
	    table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upKey");
	    table.getActionMap().put("upKey", new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				upCellEditor();
			}
		});
	    
	    //v DOWN KEY
	    table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downKey");
	    table.getActionMap().put("downKey", new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				downCellEditor();
			}
		});	    
	
	}
	
	protected void nextCellEditor(){
		if (table.getRowCount() > 0) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn(); 
			
			if( col == I_NO){//move -> to PRODUCT_CODE
				focusCellEditor(row, I_PRODUCT_CODE);
			} else if( col == I_PRODUCT_CODE) {//move -> to QUANTITY
				focusCellEditor(row, I_QUANTITY);
			} else if( col == I_PRODUCT_NAME) {//move -> to QUANTITY
				focusCellEditor(row, I_QUANTITY);
			} else if( col == I_QUANTITY) {//move -> to ORDER PRICE
				focusCellEditor(row, I_ORDER_PRICE);
			} else if( col == I_ORDER_PRICE) {//move -> to SUMMARY
				focusCellEditor(row, I_SUMMARY);
			} else if( col == I_PRICE) {//move -> to SUMMARY
				focusCellEditor(row, I_SUMMARY);
			} else if( col == I_SUMMARY) {//move -> to PRODUCT CODE
				focusCellEditor(row, I_PRODUCT_CODE);
			}
		}
	}
	
	protected void previousCellEditor(){
		if (table.getRowCount() > 0) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn(); 
			
			if( col == I_NO){//move -> to SUMMARY
				focusCellEditor(row, I_SUMMARY);
			} else if( col == I_PRODUCT_CODE) {//move -> to SUMMARY
				focusCellEditor(row, I_SUMMARY);
			} else if( col == I_PRODUCT_NAME) {//move -> to PRODUCT CODE
				focusCellEditor(row, I_PRODUCT_CODE);
			} else if( col == I_QUANTITY) {//move -> to PRODUCT CODE
				focusCellEditor(row, I_PRODUCT_CODE);
			} else if( col == I_ORDER_PRICE) {//move -> to QUANTITY
				focusCellEditor(row, I_QUANTITY);
			} else if( col == I_PRICE) {//move -> to ORDER PRICE
				focusCellEditor(row, I_ORDER_PRICE);
			} else if( col == I_SUMMARY) {//move -> to ORDER PRICE
				focusCellEditor(row, I_ORDER_PRICE);
			}
		}
	}
	
	protected void upCellEditor(){
		int selectedRow = (table.getSelectedRow() == 0)? table.getRowCount() - 1 : table.getSelectedRow() - 1;
		int selectedCol = table.getSelectedColumn();
		if(table.getRowCount() > 0) {
			if (selectedCol == I_NO) {// Move to PRODUCT CODE
				focusCellEditor(selectedRow, I_PRODUCT_CODE);
			} else if (selectedCol == I_PRODUCT_NAME) {// Move to QUANTITY
				focusCellEditor(selectedRow, I_QUANTITY);
			} else if (selectedCol == I_PRICE) {// Move to SUMMARY
				focusCellEditor(selectedRow, I_SUMMARY);
			}
			else
			{
				focusCellEditor(selectedRow, selectedCol);
			}
		}
		
	}
	
	protected void downCellEditor(){
		int selectedRow = (table.getSelectedRow() < table.getRowCount() - 1)? table.getSelectedRow() + 1 : 0;
		int selectedCol = table.getSelectedColumn();
		if(table.getRowCount() > 0) {
			if (selectedCol == I_NO) {// Move to PRODUCT CODE
				focusCellEditor(selectedRow, I_PRODUCT_CODE);
			} else if (selectedCol == I_PRODUCT_NAME) {// Move to QUANTITY
				focusCellEditor(selectedRow, I_QUANTITY);
			} else if (selectedCol == I_PRICE) {// Move to SUMMARY
				focusCellEditor(selectedRow, I_SUMMARY);
			}
			else
			{
				focusCellEditor(selectedRow, selectedCol);
			}
		}
	}

	//Renderer de hien thi kinh lup len tren row tren Grid
	public class CDInputTextSearchRender extends DefaultTableCellRenderer implements TableCellEditor {
		
		private static final long serialVersionUID = 1L;
		private CdInputSearchText txtCdInput ;
//		private BaseInputText txtCdInput ;
		
		public CdInputSearchText getTxtCdInput() {
			return txtCdInput;
		}

		public void setTxtCdInput(CdInputSearchText txtCdInput) {
			this.txtCdInput = txtCdInput;
		}
		
//		public BaseInputText getTxtCdInput() {
//			return txtCdInput;
//		}
//
//		public void setTxtCdInput(BaseInputText txtCdInput) {
//			this.txtCdInput = txtCdInput;
//		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			// TODO Auto-generated method stub
			CdInputSearchText render =  new CdInputSearchText("", 0, CdInputText.IM_OFF, 4);
			render.requestFocusInWindow();
//			txtCdInput.setText(value.toString());
			return render;
		}
		
		public CDInputTextSearchRender() {
			super();
//			txtCdInput = new CdInputSearchText();
		}

		/* 
		 * @param arg0
		 * @description
		 */
		@Override
		public void addCellEditorListener(CellEditorListener arg0) {
			// TODO Auto-generated method stub
			
		}

		/* 
		 * 
		 * @description
		 */
		@Override
		public void cancelCellEditing() {
			// TODO Auto-generated method stub
			
		}

		/* 
		 * @return
		 * @description
		 */
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}
		 
		/* 
		 * @param arg0
		 * @description
		 */
		@Override
		public void removeCellEditorListener(CellEditorListener arg0) {
			// TODO Auto-generated method stub
			
		}

		/* 
		 * @param arg0
		 * @return
		 * @description
		 */
		@Override
		public boolean shouldSelectCell(EventObject arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		/* 
		 * @return
		 * @description
		 */
		@Override
		public boolean stopCellEditing() {
			// TODO Auto-generated method stub
			return false;
		}

		/* 
		 * @param table
		 * @param value
		 * @param isSelected
		 * @param row
		 * @param column
		 * @return
		 * @description
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			// TODO Auto-generated method stub
			return null;
		}

		/* 
		 * @param arg0
		 * @return
		 * @description
		 */
		@Override
		public boolean isCellEditable(EventObject arg0) {
			// TODO Auto-generated method stub
			return false;
		}	
		
//		public boolean isCellEditable(EventObject evt) {
//	        if (evt instanceof MouseEvent) {
//	            int clickCount;
//
//	            // For single-click activation
//	            clickCount = 1;
////
////	            // For double-click activation
////	            clickCount = 2;
////
////	            // For triple-click activation
////	            clickCount = 3;
//
//	            return ((MouseEvent)evt).getClickCount() >= clickCount;
//	        }
//	        return true;
//	    }

	}
	
	public class HaCellBaseInputTextEditorNotEditable extends DefaultCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private BaseInputText txtText = null;
		private InspectTable m_table;
		 
		public BaseInputText getTxtText() {
			return txtText;
		}
		public void setTxtText(BaseInputText txtText) {
			this.txtText = txtText;
		}
		
		public HaCellBaseInputTextEditorNotEditable(BaseInputText txtInput, InspectTable table) {
			super(txtInput);
			this.txtText = txtInput;
			this.m_table = table;
			init();			
		}
		
		public void init()
		{
			txtText.setEditable(false);
			txtText.setFocusable(false);
			// -> RIGHT KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightKey");
			txtText.getActionMap().put("rightKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nextCellEditor();
				}
			});
		    
		    //<- LEFT KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftKey");
			txtText.getActionMap().put("leftKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					previousCellEditor();
				}
			});   

		    //^ UP KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upKey");
			txtText.getActionMap().put("upKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					upCellEditor();
				}
			});
		    
		    //v DOWN KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downKey");
			txtText.getActionMap().put("downKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					downCellEditor();
				}
			});
			
		}
	
		public void requestFocus() {
//			txtText.requestFocus();
		}

	}
	
	public class HaCellBaseInputTextEditor extends DefaultCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private BaseInputText txtText = null;
		private InspectTable m_table;
		 
		public BaseInputText getTxtText() {
			return txtText;
		}
		public void setTxtText(BaseInputText txtText) {
			this.txtText = txtText;
		}
		
		public HaCellBaseInputTextEditor(BaseInputText txtInput, InspectTable table) {
			super(txtInput);
			this.txtText = txtInput;
			this.m_table = table;
			init();
		}
		
		public void init()
		{
			// -> RIGHT KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightKey");
			txtText.getActionMap().put("rightKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nextCellEditor();
				}
			});
		    
		    //<- LEFT KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftKey");
			txtText.getActionMap().put("leftKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					previousCellEditor();
				}
			});   

		    //^ UP KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upKey");
			txtText.getActionMap().put("upKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					upCellEditor();
				}
			});
		    
		    //v DOWN KEY
			txtText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downKey");
			txtText.getActionMap().put("downKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					downCellEditor();
				}
			});
			
		}
	
		public void requestFocus() {
			txtText.requestFocus();
		}
		
		public boolean isCellEditable(EventObject evt) {
	        if (evt instanceof MouseEvent) {
	            int clickCount;

	            // For single-click activation
	            clickCount = 1;
//
//	            // For double-click activation
//	            clickCount = 2;
//
//	            // For triple-click activation
//	            clickCount = 3;

	            return ((MouseEvent)evt).getClickCount() >= clickCount;
	        }
	        return true;
	    }
	}
	
	public class HaCellIntegerTextBoxEditor extends DefaultCellEditor implements TableCellEditor {

		/** */
		private static final long serialVersionUID = 1L;

		/** */
		private IntegerNumberText txtInput;
		
		/** */
		private InspectTable m_table;

		private boolean enterShiftTab = false;	
		
		public HaCellIntegerTextBoxEditor(IntegerNumberText txtInput, InspectTable table) {
			super(txtInput);
			this.txtInput = txtInput;
			this.m_table = table;
			init();
		}

		
		private void init() {
			txtInput.setHorizontalAlignment(SwingConstants.RIGHT);
			
		    // -> RIGHT KEY
			txtInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightKey");
			txtInput.getActionMap().put("rightKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nextCellEditor();
				}
			});
		    
		    //<- LEFT KEY
			txtInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftKey");
			txtInput.getActionMap().put("leftKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					previousCellEditor();
				}
			});   

		    //^ UP KEY
			txtInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upKey");
			txtInput.getActionMap().put("upKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					upCellEditor();
				}
			});
		    
		    //v DOWN KEY
			txtInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downKey");
			txtInput.getActionMap().put("downKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					downCellEditor();
				}
			});
			
		}
		
		public void setMinValue (int iMinValue)
		{
			txtInput.setMinValue(iMinValue);
		}
		
		public void setMaxValue (int iMaxValue)
		{
			txtInput.setMaxValue(iMaxValue);
		}
		
		public void setText(String str) {
			txtInput.setText(str);
		}

		public String getText() {
			return txtInput.getText();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			this.txtInput.setText(StringUtils.objectToStr(value));
			return this.txtInput;
		}

		public Object getCellEditorValue() {
			return getText();
		}

		public void editCurrentCell() {
			int selectedRow = m_table.getSelectedRow();
			int selectedCol = m_table.getSelectedColumn();

			if (selectedRow >= 0 && selectedCol >= 0) {
				focusCellEditor(selectedRow, selectedCol);
			}
		}
		
		public void requestFocus() {
			txtInput.requestFocus();
		}

		public boolean isCellEditable(EventObject evt) {
	        if (evt instanceof MouseEvent) {
	            int clickCount;

	            // For single-click activation
	            clickCount = 1;
//
//	            // For double-click activation
//	            clickCount = 2;
//
//	            // For triple-click activation
//	            clickCount = 3;

	            return ((MouseEvent)evt).getClickCount() >= clickCount;
	        }
	        return true;
	    }

	}
	
	public class HaCellCdInputSearchTextBoxEditor extends DefaultCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private CdInputSearchText txtCdInput ;
		private InspectTable m_table;
		
		//Goi ham search PE01
		public HaCellCdInputSearchTextBoxEditor(CdInputSearchText txtInput, InspectTable table) {
			super(txtInput);
			this.txtCdInput = txtInput;
			this.m_table = table;
			init();
		}
		
		public void init()
		{
			txtCdInput.getFindButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SearchPE01Frame search = new SearchPE01Frame(getFrame());	
					search.setModal(true);							
					search.pack();
					search.setVisible(true);
					SearchVo searchData = search.getReturnData();
					if (searchData != null) {
						txtCdInput.setText(searchData.getProPer1());
						//Xu li lay ra nhung gia tri khac tu gia tri ProdCode 
						int selectedRow = table.getSelectedRow();
						int selectedCol = table.getSelectedColumn();
						
						HaCellBaseInputTextEditorNotEditable editor = (HaCellBaseInputTextEditorNotEditable)table.getCellEditor(selectedRow, I_PRODUCT_NAME);
						table.setValueAt(searchData.getProPer2(), selectedRow, I_PRODUCT_NAME);
					}
					searchData = null;
//					doSearchPE01();
				}
			});

			// -> RIGHT KEY
			txtCdInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightKey");
			txtCdInput.getActionMap().put("rightKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nextCellEditor();
				}
			});
		    
		    //<- LEFT KEY
			txtCdInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftKey");
			txtCdInput.getActionMap().put("leftKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					previousCellEditor();
				}
			});   

		    //^ UP KEY
			txtCdInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upKey");
			txtCdInput.getActionMap().put("upKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					upCellEditor();
				}
			});
		    
		    //v DOWN KEY
			txtCdInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downKey");
			txtCdInput.getActionMap().put("downKey", new AbstractAction() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					downCellEditor();
				}
			});
			
		}
				
		public CdInputSearchText getTxtCdInput() {
			return txtCdInput;
		}

		public void setTxtCdInput(CdInputSearchText txtCdInput) {
			this.txtCdInput = txtCdInput;
		}
		
		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			// TODO Auto-generated method stub
		
			isSelected = true;
			isRequestFocus = true;
			txtCdInput.setText( value.toString() );
			return this.txtCdInput;
		}
		
		public void editCurrentCell() {
			int selectedRow = table.getSelectedRow();
			int selectedCol = table.getSelectedColumn();

			if (selectedRow >= 0 && selectedCol >= 0) {
				focusCellEditor(selectedRow, selectedCol);
			}
		}
		
		public void requestFocus() {
			txtCdInput.requestFocus();
		}
	
		public boolean isCellEditable(EventObject evt) {
	        if (evt instanceof MouseEvent) {
	            int clickCount;

	            // For single-click activation
	            clickCount = 1;
//
//	            // For double-click activation
//	            clickCount = 2;
//
//	            // For triple-click activation
//	            clickCount = 3;

	            return ((MouseEvent)evt).getClickCount() >= clickCount;
	        }
	        return true;
	    }
	
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			// TODO Auto-generated method stub
			CdInputSearchText render =  new CdInputSearchText("", 0, CdInputText.IM_OFF, 4);
			render.requestFocusInWindow();
//			txtCdInput.setText(value.toString());
			return render;
		}
	}
}

class ProductData {
	private String number;
	private String productCode; 
	private String productName ;
	private String quantity ;
	private String orderPrice; 
	private String price; 
	private String summary;
	
	public ProductData() {
	} 
	public ProductData(String number, String productCode, String productName,
			String quantity, String orderPrice, String price, String summary) {
		super();
		this.number = number;
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.orderPrice = orderPrice;
		this.price = price;
		this.summary = summary;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}