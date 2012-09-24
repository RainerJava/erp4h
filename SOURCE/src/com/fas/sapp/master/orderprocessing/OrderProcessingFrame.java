package com.fas.sapp.master.orderprocessing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicComboPopup;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.bussiness.BaseMasterLayoutFrameForOrderProcessing;
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
import com.fas.jface.text.BaseDatePicker;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputSearchText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.text.IntegerNumberText;
import com.fas.sapp.search.SearchPE01Frame;
import com.fas.sapp.search.SearchPE03Frame;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.search.SearchVo;


/**
 * @author THANGNM
 * 
 */
public class OrderProcessingFrame extends BaseMasterLayoutFrameForOrderProcessing {

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
	private ViewLabel lblTotal = null;	
	private BaseLabel lblTotalValue = null;
	
	private BasePanel pnlBalance ;	
	private BasePanel pnlThird;
	
	private int iRightSpace = 20;
	
	private Color clrDefaultCbbSelectionBackground;
	private Color clrDefaultCbbSelectionForeground;
	
	// for GridBag table
	/** */
	public static final int TABLE_ROWS = 10;
	public static final int TABLE_COLUMNS = 7;

	private HaBoxLayoutPanel leftTablePane;
	private JComponent[][] arrCellComponent;
	private BaseScrollPane leftScroll;
	
	private static final String colMasterHead[] = { "行№","商品番号","商品名","数量","単価","金額","摘要"};
	//Chu y: phan set Width cho TextBox phai dua vao set Cols de tao width, 1 col = 8.5 pixel, vay 10 cols se la 85 pixcel
	private static final int[] colHeadwidth = {42  , 85      , 255    , 85  , 85  , 85 , 170};
	private final int[] headerAligns = {BaseLabel.CENTER, BaseLabel.LEFT, BaseLabel.LEFT, 
			BaseLabel.RIGHT, BaseLabel.RIGHT, BaseLabel.RIGHT, BaseLabel.LEFT};
	private int preSelectedRow = 0;
	private int curSelectedRow = 0;
	
	//INDEX LIST VIEW 
	private static final int I_ROW_NO = 0; //So dong
	private static final int I_PRODUCT_CODE = 1; //Ma hang
	private static final int I_PRODUCT_NAME = 2; //Ten hang
	private static final int I_QUANTITY = 3; //so luong
	private static final int I_UNIT_PRICE = 4; //don gia
	private static final int I_CALC_PRICE = 5; //gia
	private static final int I_SUMMARY= 6; //tom tat
	
	private int iconTextGap = 4;
	
	private JPopupMenu popup = null;
	
	private int order = 1;	
	/** */
	private int iCount = 0;

	public OrderProcessingFrame(){
		super();
		init();
	}
	
	public OrderProcessingFrame(BaseFrame frame){
		super(frame);
		init();
	}
	
	public OrderProcessingFrame(BaseFrame frame, String title){
		super();
		init();
	}

	/**
	 * This method is used to initilize component
	 */
	private void init() {
		initTableComp();
		getHeader();
		getBodyLeft();
		buildMiddle();
		initPopup();
		setFrameSize();
		setTitle("売上伝票処理");
		setResizable(true);
		setDispTabFocus();
		btnF4.setEnabled(false);
	}

	@Override
	protected BasePanel getHeader() {	
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
		
		int iPnlFirstWidth = X_FRAME_LENGTH - iGap5 * 2 - 50;
		
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
		BaseLabel lblOrder = new BaseLabel("伝票番号");
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
		Color colors[] = { Color.black, Color.blue, Color.cyan, Color.darkGray,
			        Color.gray, Color.green, Color.lightGray, Color.magenta,
			        Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
		cbbMode = new BaseComboBox(aModel);
		cbbMode.setToolTipText(StringUtils.trimAllVN( lblMode.getText() + "を選択して下さい。"));
		cbbMode.setLocation(xPos, yPos);
		cbbMode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		cbbMode.setPopupWidth(TXT_TEXT_FIELD_LENGTH_107);
		cbbMode.setSelectedIndex(0);		
		
		cbbMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbbMode.getSelectedIndex() == 0)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 1)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 2)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setBackground(ColorConstants.LABEL_DELETE_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 3)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setBackground(ColorConstants.LABEL_COPY_MODE_BACKGROUND_COLOR);
				}
			}
		});
		cbbMode.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(cbbMode.getSelectedIndex() == 0)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setSelectionBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 1)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setSelectionBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 2)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setSelectionBackground(ColorConstants.LABEL_DELETE_MODE_BACKGROUND_COLOR);
					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_DELETE_MODE_BACKGROUND_COLOR);
				}
				else if(cbbMode.getSelectedIndex() == 3)
				{
					Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
					BasicComboPopup popup = (BasicComboPopup)child;
					JList list = popup.getList();
					list.setSelectionBackground(ColorConstants.LABEL_COPY_MODE_BACKGROUND_COLOR);
					list.setSelectionForeground(Color.BLACK);
					list.setBackground(ColorConstants.LABEL_COPY_MODE_BACKGROUND_COLOR);
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				Object child = cbbMode.getAccessibleContext().getAccessibleChild(0);
				BasicComboPopup popup = (BasicComboPopup)child;
				JList list = popup.getList();
				list.setSelectionBackground(clrDefaultCbbSelectionBackground);
				list.setSelectionForeground(clrDefaultCbbSelectionForeground);
			}
		});
		pnlFirst.add(cbbMode);
		//2.2 TextBox order
		xPos += TXT_TEXT_FIELD_LENGTH_107 + iGap20;	
		txtOrder = new BaseInputText();
		txtOrder.setLocation(xPos, yPos);
		txtOrder.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_107, FaceContants.LABLE_HEIGHT));
		txtOrder.setEditable(false);
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
		txtSeller.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				btnF4.setEnabled(false);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				btnF4.setEnabled(true);
			}
		});
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
		
		xPos = iPnlFirstWidth - iRightSpace - TXT_TEXT_FIELD_LENGTH_180;
		System.out.println(xPos + " gia tri " + iPnlFirstWidth);
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
		lblPoint.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
		lblPoint.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBalance.add(lblPoint,BorderLayout.LINE_START);
		//Line lbl Balance
		xPos += lblPoint.getWidth();
		ViewLabel lblBalance = new ViewLabel("残　　高");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setLocation(xPos, yPos);
		lblBalance.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
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
		lblUsable.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
		lblUsable.setSize(TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT);
		pnlBalance.add(lblUsable);		
		//Line lbl UsableValue
		yPos += FaceContants.LABLE_HEIGHT;
		BaseLabel lblUsableValue = new BaseLabel("");
		lblUsableValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsableValue.setLocation(xPos, yPos);
		lblUsableValue.setSize(TXT_TEXT_FIELD_LENGTH_140, FaceContants.LABLE_HEIGHT);
		pnlBalance.add(lblUsableValue);
		
		
		pnlFirst.setSize(iPnlFirstWidth, FaceContants.LABLE_HEIGHT*2 + iGap5*2);
		pnlSecond.setSize(iPnlFirstWidth, FaceContants.LABLE_HEIGHT*4 + iGap5*2);
		
		GroupLayout layout = new GroupLayout(headerPnl);
		
		int iGap = 7;
		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup()
			    				.addComponent(pnlFirst, GroupLayout.PREFERRED_SIZE, iPnlFirstWidth, Short.MAX_VALUE)
			    				.addGap(iGap)
			    				.addComponent(pnlSecond, GroupLayout.PREFERRED_SIZE, iPnlFirstWidth, Short.MAX_VALUE)
			    		  )			    				
			    );

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)//||
				.addGroup(layout.createSequentialGroup()				    
				    		.addComponent(pnlFirst , GroupLayout.PREFERRED_SIZE, FaceContants.LABLE_HEIGHT*2 + iGap5*2, GroupLayout.PREFERRED_SIZE)
				    		.addGap(iGap)
				    		.addComponent(pnlSecond, GroupLayout.PREFERRED_SIZE, FaceContants.LABLE_HEIGHT*4 + iGap5*2, GroupLayout.PREFERRED_SIZE)				    	 
				)
		);
//		headerPnl.setLayout(gl_middlePnl);
		headerPnl.setLayout(layout);		
		headerPnl.setPreferredSize(new Dimension(X_FRAME_LENGTH, pnlFirst.getHeight() + pnlSecond.getHeight() + iGap5*4));
		headerPnl.setBorder(FaceContants.TITLE_BORDER);
		mainPanel.add(headerPnl,BorderLayout.PAGE_START);
		
		headerPnl.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				if (iCount != 0) {					
					int xPos = headerPnl.getWidth() - iRightSpace - pnlBalance.getWidth() ;
//					System.out.println(headerPnl.getWidth() + " balance " + pnlBalance.getWidth() );
					int yPos = 5;
					//2.5 Balance
					pnlBalance.setLocation(xPos, yPos);										
				}
				iCount++;
			}
		});
		return headerPnl;
	}
	
	@Override
	protected BasePanel getBodyLeft() {
		leftScroll = getTablePane();
		pnlLeft.setLayout(new GridLayout(1, 1));
		pnlLeft.add(leftScroll, createBothConstraints(1, 1, 1, 1, 1, 1));
		return pnlLeft;
	}
	
	/**
	 * initial the left table
	 */
	private BaseScrollPane getTablePane() {
		leftTablePane = new HaBoxLayoutPanel(TABLE_ROWS);
		leftTablePane.setPaneHeader(createTableHeaderPane());		
		leftTablePane.setPaneRows(createTableRowsPanel());
		// add table data
		loadTableData();		
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

	//Ham tao Header, Sort
	private BasePanel createTableHeaderPane() {
		// add header
		BasePanel paneHeader;
		paneHeader = new BasePanel();
		paneHeader.setLayout(new GridBagLayout());
		
		HeaderLabel lblHeaderX00Y00 = new HeaderLabel(colMasterHead[0]);
		lblHeaderX00Y00.setPreferredSize(new Dimension(colHeadwidth[0], FaceContants.LABLE_HEIGHT));
		lblHeaderX00Y00.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		lblHeaderX00Y00.setForeground(ColorConstants.LABEL_CD_FORE_COLOR);		
		paneHeader.add(lblHeaderX00Y00, 
				setBagConstraints(0, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		final HeaderLabel lblHeaderX01Y00 = new HeaderLabel(colMasterHead[1]);
		lblHeaderX01Y00.setPreferredSize(new Dimension(colHeadwidth[1], FaceContants.LABLE_HEIGHT));
		lblHeaderX01Y00.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		lblHeaderX01Y00.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
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
				sortCol(I_PRODUCT_CODE, isAsc);
				isAsc = !isAsc;
//				lblHeaderX01Y00.setText(lblHeaderX01Y00.getText() + (isAsc ? " △" : " ▽"));
			}
		});
		paneHeader.add(lblHeaderX01Y00, 
				setBagConstraints(1, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		final HeaderLabel lblHeaderX02Y00 = new HeaderLabel(colMasterHead[2]);
		lblHeaderX02Y00.setPreferredSize(new Dimension(colHeadwidth[2], FaceContants.LABLE_HEIGHT));
		lblHeaderX02Y00.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		lblHeaderX02Y00.setForeground(ColorConstants.LABEL_CD_FORE_COLOR);
		lblHeaderX02Y00.addMouseListener(new MouseListener() {
			
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
//				lblHeaderX02Y00.setText(lblHeaderX02Y00.getText() + (isAsc ? " △" : " ▽"));
			}
		});
		paneHeader.add(lblHeaderX02Y00, 
				setBagConstraints(2, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		final HeaderLabel lblHeaderX03Y00 = new HeaderLabel(colMasterHead[3]);
		lblHeaderX03Y00.setPreferredSize(new Dimension(colHeadwidth[3], FaceContants.LABLE_HEIGHT));
		lblHeaderX03Y00.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		lblHeaderX03Y00.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
		lblHeaderX03Y00.addMouseListener(new MouseListener() {
			
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
				sortCol(I_QUANTITY, isAsc);
				isAsc = !isAsc;
//				lblHeaderX03Y00.setText(lblHeaderX03Y00.getText() + (isAsc ? " △" : " ▽"));
			}
		});
		paneHeader.add(lblHeaderX03Y00, 
				setBagConstraints(3, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		final HeaderLabel lblHeaderX04Y00 = new HeaderLabel(colMasterHead[4]);
		lblHeaderX04Y00.setPreferredSize(new Dimension(colHeadwidth[4], FaceContants.LABLE_HEIGHT));
		lblHeaderX04Y00.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		lblHeaderX04Y00.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
		lblHeaderX04Y00.addMouseListener(new MouseListener() {
			
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
				sortCol(I_UNIT_PRICE, isAsc);
				isAsc = !isAsc;
//				lblHeaderX04Y00.setText(lblHeaderX04Y00.getText() + (isAsc ? " △" : " ▽"));
			}
		});
		paneHeader.add(lblHeaderX04Y00, 
				setBagConstraints(4, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		final HeaderLabel lblHeaderX05Y00 = new HeaderLabel(colMasterHead[5]);
		lblHeaderX05Y00.setPreferredSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		lblHeaderX05Y00.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		lblHeaderX05Y00.setForeground(ColorConstants.LABEL_CD_FORE_COLOR);
		lblHeaderX05Y00.addMouseListener(new MouseListener() {
			
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
				sortCol(I_CALC_PRICE, isAsc);
				isAsc = !isAsc;
//				lblHeaderX05Y00.setText(lblHeaderX05Y00.getText() + (isAsc ? " △" : " ▽"));
			}
		});
		paneHeader.add(lblHeaderX05Y00, 
				setBagConstraints(5, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
		// cot cuoi cung co dan
		final HeaderLabel lblHeaderX06Y00 = new HeaderLabel(colMasterHead[6]);
		lblHeaderX06Y00.setPreferredSize(new Dimension(colHeadwidth[6], FaceContants.LABLE_HEIGHT));
		lblHeaderX06Y00.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		lblHeaderX06Y00.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
		lblHeaderX06Y00.addMouseListener(new MouseListener() {
			
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
				sortCol(I_SUMMARY, isAsc);
				isAsc = !isAsc;	
				lblHeaderX06Y00.setText(lblHeaderX06Y00.getText() + (isAsc ? " △" : " ▽"));
			}
		});
		paneHeader.add(lblHeaderX06Y00, 
				setBagConstraints(6, 0, 1, 1, 0.5, 0.5,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0) );
		
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
	
	private void initTableComp() {
		arrCellComponent = new JComponent[TABLE_ROWS][TABLE_COLUMNS];
		for (int row = 0; row < TABLE_ROWS; row++) {
			createRowComponent(row);
		}		
	}
	
	//Ham tao phan Grid
	private void createRowComponent(final int iRow) {
		int column;
		/*
		 *  first iRow in panel
		 */
		//1. ViewLabel RowNo 
		column = 0;
		ViewLabel lblRowNo = new ViewLabel(StringUtils.padLeft(("" + (iRow + 1)), '0', 3));
		lblRowNo.setPreferredSize(new Dimension(colHeadwidth[0], FaceContants.LABLE_HEIGHT));
		lblRowNo.setMinimumSize(new Dimension(colHeadwidth[0], FaceContants.LABLE_HEIGHT));
		lblRowNo.setMaximumSize(new Dimension(colHeadwidth[0], FaceContants.LABLE_HEIGHT));
		lblRowNo.setHorizontalAlignment(headerAligns[0]);
		lblRowNo.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
		arrCellComponent[iRow][column++] = lblRowNo;

		//2. CdInputSearchText ProductCode 
//		final CdInputSearchText txtProductCode = new CdInputSearchText();
		final CdInputSearchText txtProductCode = new CdInputSearchText("", 10, 100);
		final ViewLabel lblProductName = new ViewLabel();
		txtProductCode.setPreferredSize(new Dimension(colHeadwidth[1], FaceContants.TEXT_HEIGHT));
		txtProductCode.setMinimumSize(new Dimension(colHeadwidth[1], FaceContants.TEXT_HEIGHT));
		txtProductCode.setMaximumSize(new Dimension(colHeadwidth[1], FaceContants.TEXT_HEIGHT));		
		txtProductCode.setHorizontalAlignment(headerAligns[1]);		
		txtProductCode.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				btnF4.setEnabled(false);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				btnF4.setEnabled(true);
			}
		});
		txtProductCode.getFindButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doSearchPE01(iRow);
			}
		});
		arrCellComponent[iRow][column++] = txtProductCode;
		
		//3. ViewLabel ProductName		
		lblProductName.setBorder(BorderFactory.createEtchedBorder());
		lblProductName.setPreferredSize(new Dimension(colHeadwidth[2], FaceContants.LABLE_HEIGHT));
		lblProductName.setMinimumSize(new Dimension(colHeadwidth[2], FaceContants.LABLE_HEIGHT));
		lblProductName.setMaximumSize(new Dimension(colHeadwidth[2], FaceContants.LABLE_HEIGHT));
		lblProductName.setHorizontalAlignment(headerAligns[2]);
		lblProductName.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
		arrCellComponent[iRow][column++] = lblProductName;
		
		//4. IntegerNumberText Quantity
		final IntegerNumberText txtQuantity = new IntegerNumberText();
		txtQuantity.setColumns(10);		
		txtQuantity.setPreferredSize(new Dimension(colHeadwidth[3], FaceContants.TEXT_HEIGHT));
		txtQuantity.setMinimumSize(new Dimension(colHeadwidth[3], FaceContants.TEXT_HEIGHT));
		txtQuantity.setMaximumSize(new Dimension(colHeadwidth[3], FaceContants.TEXT_HEIGHT));
		txtQuantity.setHorizontalAlignment(headerAligns[3]);
		txtQuantity.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
		txtQuantity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Goi ham recalculateData
				recalculateTotal(iRow);
			}
		});
		arrCellComponent[iRow][column++] = txtQuantity;

		//5. IntegerNumberText UnitPrice		
		final IntegerNumberText txtUnitPrice = new IntegerNumberText();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setPreferredSize(new Dimension(colHeadwidth[4], FaceContants.TEXT_HEIGHT));
		txtUnitPrice.setMinimumSize(new Dimension(colHeadwidth[4], FaceContants.TEXT_HEIGHT));
		txtUnitPrice.setMaximumSize(new Dimension(colHeadwidth[4], FaceContants.TEXT_HEIGHT));
		txtUnitPrice.setHorizontalAlignment(headerAligns[4]);
		txtUnitPrice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Goi ham recalculateData
				recalculateTotal(iRow);
			}
		});
		arrCellComponent[iRow][column++] = txtUnitPrice;

		//6. ViewLabel CalcPrice
		ViewLabel lblCalcPrice = new ViewLabel();
		lblCalcPrice.setHorizontalAlignment(JLabel.RIGHT);
		lblCalcPrice.setBorder(BorderFactory.createEtchedBorder());
		lblCalcPrice.setPreferredSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		lblCalcPrice.setMinimumSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		lblCalcPrice.setMaximumSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		lblCalcPrice.setHorizontalAlignment(headerAligns[5]);
		lblCalcPrice.setBackground(ColorConstants.LABEL_VIEW_BACKGROUND_COLOR);
		arrCellComponent[iRow][column++] = lblCalcPrice;
		
		//7. BaseTextArea Summary
		BaseInputText txtSummary = new BaseInputText();
		txtSummary.setPreferredSize(new Dimension(colHeadwidth[6], FaceContants.TEXT_HEIGHT));
//		txtSummary.setMinimumSize(new Dimension(colHeadwidth[6], FaceContants.TEXT_HEIGHT));
//		txtSummary.setMaximumSize(new Dimension(colHeadwidth[6], FaceContants.TEXT_HEIGHT));
		txtSummary.setHorizontalAlignment(headerAligns[6]);
		arrCellComponent[iRow][column++] = txtSummary;		

		for (Component comp : arrCellComponent[iRow]) {
			if (comp instanceof JLabel)
				continue;

			if (iRow % 2 == 0) {
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
		Insets insert = new Insets(0, 1, 0, 1);
		final BasePanel[] paneArrRows  = new BasePanel[TABLE_ROWS+1];
		
		for (int row = 0; row < TABLE_ROWS; row++) {

			//get row panel
			BasePanel paneRow = new BasePanel();
			// draw a row panel
			paneRow.setLayout(new GridBagLayout());			
		
			for (int column = 0; column < TABLE_COLUMNS; column++) {				
				final Component cell = arrCellComponent[row][column];
				if(column == 6)// Cot cuoi co dan
				{
					gridBagConstr = setBagConstraints(column, row, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insert, 0, 0);
				}
				else
				{
					gridBagConstr = setBagConstraints(column, row, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
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
						 {
							 if(curSelectedRow != x)
							 {
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
						 }
						 
//						 cell.setBackground(ColorConstants.MENU_BUTTON_FOCUS_BACK_COLOR);
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
		//Label total

		//get row panel
		BasePanel lastPaneRow = new BasePanel();
		// draw a row panel
		lastPaneRow.setLayout(new GridBagLayout());	
		
		BasePanel pnl0 = new BasePanel();
		pnl0.setPreferredSize(new Dimension(colHeadwidth[0], FaceContants.LABLE_HEIGHT));
//		pnl0.setB)((javax.swing.border.Border) Border.ALL);
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(0, TABLE_ROWS + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
		lastPaneRow.add(pnl0, gridBagConstr);
		
		BasePanel pnl1 = new BasePanel();
		pnl1.setPreferredSize(new Dimension(colHeadwidth[1], FaceContants.LABLE_HEIGHT));
//		pnl1.setBorder((jxl.format.Border) Border.ALL);
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(1, TABLE_ROWS + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
		lastPaneRow.add(pnl1, gridBagConstr);
		
		BasePanel pnl2 = new BasePanel();
		pnl2.setPreferredSize(new Dimension(colHeadwidth[2], FaceContants.LABLE_HEIGHT));
//		pnl2.setBorder((javax.swing.border.Border) Border.ALL);
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(2, TABLE_ROWS + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
		lastPaneRow.add(pnl2, gridBagConstr);
		
		BasePanel pnl3 = new BasePanel();
		pnl3.setPreferredSize(new Dimension(colHeadwidth[3] + 2, FaceContants.LABLE_HEIGHT));
//		pnl3.setBorder((javax.swing.border.Border) Border.ALL);
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(3, TABLE_ROWS + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
		lastPaneRow.add(pnl3, gridBagConstr);
		
		
		//6. ViewLabel CalcPrice
		lblTotal = new ViewLabel("合計");
		lblTotal.setHorizontalAlignment(JLabel.LEFT);
		lblTotal.setBorder(BorderFactory.createEtchedBorder());
		lblTotal.setPreferredSize(new Dimension(colHeadwidth[4], FaceContants.LABLE_HEIGHT));
		lblTotal.setMinimumSize(new Dimension(colHeadwidth[4], FaceContants.LABLE_HEIGHT));
		lblTotal.setMaximumSize(new Dimension(colHeadwidth[4], FaceContants.LABLE_HEIGHT));
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(4, TABLE_ROWS + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
		lastPaneRow.add(lblTotal, gridBagConstr);		
		
		//Label totalValue
		lblTotalValue = new BaseLabel("0");
		lblTotalValue.setHorizontalAlignment(JLabel.RIGHT);
		lblTotalValue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblTotalValue.setPreferredSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		lblTotalValue.setMinimumSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		lblTotalValue.setMaximumSize(new Dimension(colHeadwidth[5], FaceContants.LABLE_HEIGHT));
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(5, TABLE_ROWS + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insert, 0, 0);
		lastPaneRow.add(lblTotalValue, gridBagConstr);
		
		BasePanel pnl6 = new BasePanel();
		pnl6.setPreferredSize(new Dimension(colHeadwidth[6], FaceContants.LABLE_HEIGHT));
//		pnl3.setBorder((javax.swing.border.Border) Border.ALL);
		gridBagConstr = new GridBagConstraints();
		gridBagConstr = setBagConstraints(6, TABLE_ROWS + 1, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insert, 0, 0);
		lastPaneRow.add(pnl6, gridBagConstr);
		
		paneArrRows[TABLE_ROWS] = lastPaneRow;
		return paneArrRows;
	}	

	private void recalculateTotal(final int iRow)	{
		//Xu li lay ra nhung gia tri khac tu gia tri ProdCode 
		Component comp;
		comp = arrCellComponent[iRow][I_QUANTITY];
		String strQuantity = ((IntegerNumberText) comp).getText();
		comp = arrCellComponent[iRow][I_UNIT_PRICE];
		String strUnitPrice = ((IntegerNumberText) comp).getText();
		comp = arrCellComponent[iRow][I_CALC_PRICE];
		
		int iQuantity = 0;
		int iUnitPrice = 0;
		if( StringUtils.isValid( StringUtils.emptyIfNull(strQuantity)) == false || StringUtils.isValid( StringUtils.emptyIfNull(strUnitPrice) ) == false  )
		{
			((ViewLabel)comp).setText("");
		}
		else
		{
			iQuantity = NumberUtils.getIntFromString( StringUtils.emptyIfNull(strQuantity) );
			iUnitPrice = NumberUtils.getIntFromString( StringUtils.emptyIfNull(strUnitPrice) );
			int iPrice = iQuantity * iUnitPrice;
			((ViewLabel)comp).setText(StringUtils.convertIntegerToStr(iPrice));
		}
		//Calc TotalPrice		
		int iTotalValue = 0;
		for(int i=0 ; i< TABLE_ROWS; i++)
		{
			comp = arrCellComponent[i][I_CALC_PRICE];
			iTotalValue += NumberUtils.getIntFromString( StringUtils.emptyIfNull(((ViewLabel)comp).getText()));
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
				//Ham insert
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
				//ham copy
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
				//ham delete
			}
		});
		deleteItem.setIcon(new ImageIcon(this.getClass().getResource("/image/del.png")));
		deleteItem.setIconTextGap(iconTextGap);
		
		leftScroll.setComponentPopupMenu(popup);		
	}
		
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
		if(txtSeller.isFocusOwner() == true)
			doSearchPE03();
		else
		{
			doSearchPE01(curSelectedRow);
		}
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

	private void loadTableData(){
//		ProductDataList dataVo = null;			
//		Vector v = null;
//	
//		for(int i = 1 ; i <= iMaxTableRow;i++){
//			v = new Vector();
//			dataVo = new ProductDataList(String.valueOf(i),"","","","","","");
//			v.add(dataVo.getNumber());
//			v.add(dataVo.getProductCode());
//			v.add(dataVo.getProductName());
//			v.add(dataVo.getQuantity());
//			v.add(dataVo.getOrderPrice());
//			v.add(dataVo.getPrice());
//			v.add(dataVo.getSummary());
//			defaultTableModel.addRow(v);
//		}
	}

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

	public void doSearchPE01(int iRow) {
		//Goi form Search 01
		SearchPE01Frame search = new SearchPE01Frame(getFrame());				
		search.setModal(true);							
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			((CdInputSearchText)arrCellComponent[iRow][I_PRODUCT_CODE]).setText (searchData.getProPer1()); 
			((ViewLabel)arrCellComponent[iRow][I_PRODUCT_NAME]).setText (searchData.getProPer2());
//			txtProductCode.setText(searchData.getProPer1());
//			//Xu li lay ra nhung gia tri khac tu gia tri ProdCode 
//			lblProductName.setText(searchData.getProPer2());
		}
	}
	
	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void buildMiddle() {
		BasePanel middle = new BasePanel();
		GroupLayout layout = new GroupLayout(middle);
		layout.setHorizontalGroup(layout.createSequentialGroup().addGap(5).addComponent(pnlLeft).addGap(7).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(pnlCenter)).addGap(5));
		layout.setVerticalGroup(layout.createParallelGroup().addComponent(pnlLeft).addComponent(pnlCenter));

		middle.setLayout(layout);
		mainPanel.add(middle, BorderLayout.CENTER);
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

	protected void setDispTabFocus() {
//		List<Object> focusList = new ArrayList<Object>();
//		focusList.add(cbbMode);
////		focusList.add(txtOrder);
//		focusList.add(cbbProcess);
//		focusList.add(cbbMoney);
//		focusList.add(dtpSellDate);
//		focusList.add(txtSeller);
//		setFocusTraversalPolicy(new FocusPolicy(focusList));
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
		strComp1 = StringUtils.emptyIfNull(((CdInputSearchText) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_PRODUCT_CODE];
		strComp2 = StringUtils.emptyIfNull(((CdInputSearchText) comp2).getText());		
		strTemp = strComp1;
		((CdInputSearchText)comp1).setText(strComp2);
		((CdInputSearchText)comp2).setText(strTemp);
		
		//2. ProductName
		comp1 = arrCellComponent[iRow1][I_PRODUCT_NAME];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_PRODUCT_NAME];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//3. Quantity
		comp1 = arrCellComponent[iRow1][I_QUANTITY];
		strComp1 = StringUtils.emptyIfNull(((IntegerNumberText) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_QUANTITY];
		strComp2 = StringUtils.emptyIfNull(((IntegerNumberText) comp2).getText());		
		strTemp = strComp1;
		((IntegerNumberText)comp1).setText(strComp2);
		((IntegerNumberText)comp2).setText(strTemp);
		
		//4. UnitPrice
		comp1 = arrCellComponent[iRow1][I_UNIT_PRICE];
		strComp1 = StringUtils.emptyIfNull(((IntegerNumberText) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_UNIT_PRICE];
		strComp2 = StringUtils.emptyIfNull(((IntegerNumberText) comp2).getText());		
		strTemp = strComp1;
		((IntegerNumberText)comp1).setText(strComp2);
		((IntegerNumberText)comp2).setText(strTemp);
		
		//5. CalcPrice
		comp1 = arrCellComponent[iRow1][I_CALC_PRICE];
		strComp1 = StringUtils.emptyIfNull(((ViewLabel) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_CALC_PRICE];
		strComp2 = StringUtils.emptyIfNull(((ViewLabel) comp2).getText());		
		strTemp = strComp1;
		((ViewLabel)comp1).setText(strComp2);
		((ViewLabel)comp2).setText(strTemp);
		
		//3. Quantity
		comp1 = arrCellComponent[iRow1][I_QUANTITY];
		strComp1 = StringUtils.emptyIfNull(((BaseInputText) comp1).getText());		
		comp2 = arrCellComponent[iRow2][I_QUANTITY];
		strComp2 = StringUtils.emptyIfNull(((BaseInputText) comp2).getText());		
		strTemp = strComp1;
		((BaseInputText)comp1).setText(strComp2);
		((BaseInputText)comp2).setText(strTemp);
	}
	
}

final class ImageLoader {
	private ImageLoader() {
	}

	public static Image getImage(Class relativeClass, String filename) {
		Image returnValue = null;
		java.io.InputStream is = relativeClass.getResourceAsStream(filename);
		if (is != null) {
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				int ch;
		        while ((ch = bis.read()) != -1) {
		          baos.write(ch);
		        }
		        returnValue = Toolkit.getDefaultToolkit().createImage(
		            baos.toByteArray());
				} catch (IOException exception) {
					System.err.println("Error loading: " + filename);
				}
			}
			return returnValue;
	  }
}
