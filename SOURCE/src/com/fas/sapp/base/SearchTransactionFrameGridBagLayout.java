/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：SearchMasterFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/02/12   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.sapp.base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.button.ActionButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.panel.HaBoxLayoutPanel;
import com.fas.jface.table.ColumnData;
import com.fas.jface.table.SearchTableTransaction;
import com.fas.jface.utils.SwingUtils;
import com.fas.service.search.SearchService;
import com.fas.service.search.SearchServiceImpl;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.search.SearchConditionVo;
import com.fas.vo.search.SearchVo;
import com.fas.vo.search.TrSchdspVo;

/**
 * @author PC13
 * 
 */
public abstract class SearchTransactionFrameGridBagLayout extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(SearchTransactionFrame.class);
	//Dinh nghia cac chuoi Select
	/** */
	public static final int I_PE01_SEARCH = 5;
	/** */
	public static final int I_PE03_SEARCH = 6;
	
	/** */
	protected int LEFT_PNL_WIDTH = 0;
	protected int RIGHT_PNL_WIDTH = 0;
	
	protected int Y_CONDITION_PANEL_HEIGHT = 0;
	protected int Y_TABLE_PANEL_HEIGHT = 0;
	protected int Y_FILTER_PANEL_HEIGHT = 0;
	

	// for GridBag table
	/** */
	protected HaBoxLayoutPanel leftTablePane;
	protected JComponent[][] arrCellComponent;
	protected BaseScrollPane leftScroll;
	
	/** */
	protected JDialog dlg;	
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
	protected ActionButton btnPre;
	/** */
	protected ActionButton btnNext;
	/** */
	protected ActionButton btnLast;
	/** */
	protected ActionButton btnFirst;
	/** */
	protected ActionButton btnA;
	/** */
	protected ActionButton btnKa;
	/** */
	protected ActionButton btnSa;
	/** */
	protected ActionButton btnTa;
	/** */
	protected ActionButton btnNa;
	/** */
	protected ActionButton btnHa;
	/** */
	protected ActionButton btnMa;
	/** */
	protected ActionButton btnYa;
	/** */
	protected ActionButton btnRa;
	/** */
	protected ActionButton btnWa;
	/** */
	protected ActionButton btnEisuu;
	/** */
	private BaseLabel lblHelpInfor;
	/** */
	protected BaseCheckBox chkIsUsed;
	/** */
	protected SearchTableTransaction m_table;
	/** */
	protected SearchTableDataModel m_data;
	/** */
	private boolean isFirst = true;
	/** */
	private int ROW_PER_PAGE;
	/** */
	private BasePanel pnlTable;
	/** */
	protected InspectRadioButton rdSchItem1;
	/** */
	protected InspectRadioButton rdSchItem2;
	/** */
	protected InspectRadioButton rdSchItem3;
	/** */
	protected InspectRadioButton rdSchItem4;
	/** */
	protected InspectRadioButton rdSchItem5;
	/** */
	protected InspectRadioButton rdSchItem6;
	/** */
	protected TrSchdspVo dspVo;
	/** */
	protected BasePanel pnlCondition;
	/** */
	protected SearchService searchService = new SearchServiceImpl();
	/** */
	protected SearchVo returnData;
	/** */
	protected BaseLabel lblNumber;
	/** */
	protected String searchIndex = "";
	/** */
	protected String searchItem;
	/** */
	protected MCtlVo schCtlVo;
	/** */
	protected boolean isInit = true;
	/** */
	protected boolean[] isRadioAndColumnTheSame;
	/** */
	protected SortObjVo initSortObj;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public SearchTransactionFrameGridBagLayout() {
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
	public SearchTransactionFrameGridBagLayout(BaseFrame owner, boolean modal) {
		super(owner, modal);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param owner
	 * @param sortObj
	 * @param modal
	 *            </DL>
	 */
	public SearchTransactionFrameGridBagLayout(BaseFrame owner, SortObjVo sortObj, boolean modal) {
		super(owner, modal);

		init(sortObj);
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

		setTitle(getSubName());
		//setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		leftScroll = getTablePane();
		pnlTable.setLayout(new GridLayout(1, 1));
		pnlTable.add(leftScroll, createBothConstraints(1, 1, 1, 1, 1, 1));
		

		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlTable.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
	}

	/** init set value and design Table
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param sortObj
	 *            </DL>
	 */
	private void init(SortObjVo sortObj) {

		setTitle(getSubName());
		//setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initSortObj = sortObj;
		
		leftScroll = getTablePane(sortObj);
		pnlTable.setLayout(new GridLayout(1, 1));
		pnlTable.add(leftScroll, createBothConstraints(1, 1, 1, 1, 1, 1));

		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlTable.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
	}
	
	protected abstract BaseScrollPane getTablePane();
	

	protected abstract BaseScrollPane getTablePane(SortObjVo sortObj);
	
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doRequestComp();

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
	protected abstract String getSubName();

	//Main Panel add cac panel khac
	@Override
	protected BasePanel getBodyPanel() {

		// パネルの生成
		mainPnl = new BasePanel();
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		mainPnl.setLocation(0, 0);
		int xPos = 5;
		int yPos = 5;
		int Y_SPACE = 6;
		//左長さ、
		int Y_BTN_HEIGHT = 40;

		isRadioAndColumnTheSame = new boolean[6];
		for (int i = 0; i < isRadioAndColumnTheSame.length; i++) {
			isRadioAndColumnTheSame[i] = true;
		}
		
		BasePanel pnlBorder = new BasePanel();		
		pnlBorder.setSize(LEFT_PNL_WIDTH + RIGHT_PNL_WIDTH + 15, Y_BODY_LENGTH - (Y_BTN_HEIGHT + 10));
		pnlBorder.setLocation(xPos, yPos);
		pnlBorder.setBorder(FaceContants.TITLE_BORDER);
		mainPnl.add(pnlBorder);

		//Add lbl1, cbb1, lbl2, txt2 o tren cung
		pnlCondition = new BasePanel();
		pnlCondition.setSize(LEFT_PNL_WIDTH, Y_CONDITION_PANEL_HEIGHT);
		pnlCondition.setLocation(xPos, yPos);
		pnlCondition.setBorder(FaceContants.TITLE_BORDER);
		initPnlCondition(pnlCondition);
		pnlBorder.add(pnlCondition);

		System.out.println("//Add lbl1, cbb1, lbl2, txt2 o tren cung " + pnlCondition.getWidth()+ " " + pnlCondition.getX() );
		
		//Add table
		yPos += pnlCondition.getHeight() + Y_SPACE;
		pnlTable = new BasePanel();
		pnlTable.setSize(LEFT_PNL_WIDTH, Y_TABLE_PANEL_HEIGHT);
		pnlTable.setBorder(FaceContants.BEVEL_BORDER);
		pnlTable.setLocation(xPos, yPos);
		pnlBorder.add(pnlTable);
		
		System.out.println("//pnlTable " + pnlTable.getWidth() + " " + pnlTable.getX() );
		
		//Add Search button
		yPos = 5;
		xPos += LEFT_PNL_WIDTH + 5;
		BasePanel pnlSearchButton = new BasePanel();
		pnlSearchButton.setSize(RIGHT_PNL_WIDTH, Y_CONDITION_PANEL_HEIGHT);
		pnlSearchButton.setLocation(xPos, yPos);
		initSearchButton(pnlSearchButton);
		pnlBorder.add(pnlSearchButton);
		
		System.out.println("//Search button " + pnlSearchButton.getWidth() + " " + pnlSearchButton.getX() );
		
		//Add Filter list
		yPos += pnlSearchButton.getHeight() + Y_SPACE;
		BasePanel pnlSearchItem = new BasePanel();
		pnlSearchItem.setSize(RIGHT_PNL_WIDTH, Y_FILTER_PANEL_HEIGHT);
		pnlSearchItem.setLocation(xPos, yPos);
		pnlSearchItem.setBorder(new TitledBorder("検索項目"));
		initPnlSearchItem(pnlSearchItem);
		pnlBorder.add(pnlSearchItem);
		
		System.out.println("//Filter list " + pnlSearchItem.getWidth() + " " + pnlSearchItem.getX());
		
		//Add list Button F7, F9
		xPos = 5;
		yPos = pnlBorder.getHeight() + Y_SPACE;
		BasePanel pnlButton = new BasePanel();
		pnlButton.setSize(LEFT_PNL_WIDTH + RIGHT_PNL_WIDTH + 15, Y_BODY_LENGTH - yPos);
		pnlButton.setLocation(xPos, yPos);
		pnlButton.setBorder(FaceContants.TITLE_BORDER);
		initBottomButton(pnlButton);
		mainPnl.add(pnlButton);
		
		System.out.println("//list Button F7, F9 " + pnlButton.getWidth() + " " + pnlButton.getX());
		
//		Y_BTN_HEIGHT = 36;		
//		yPos += 198 + 30;

		return mainPnl;
	}

	/** lbl1, cbb1, lbl2, txt2
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlFilter
	 */
	protected abstract void initPnlCondition(BasePanel pnlFilter);
	
	/** Hang nut ben duoi cung
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlButton
	 */
	private void initBottomButton(BasePanel pnlButton) {
		int Y_BTN_HEIGHT = 36;
		int X_BTN_WIDTH = 90;
		int xSubPos = 125;
		int ySubPos = 5;
		int X_BTN_SPACE = 5;
		
		//Khoang cach giua 2 nut
		int X_BTN_F7_F9 = (LEFT_PNL_WIDTH + RIGHT_PNL_WIDTH) - ((X_BTN_WIDTH + xSubPos) * 2);

		F7Action f7Action = new F7Action("f7Action");
		btnF7 = new ActionButton();
		btnF7.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,Y_BTN_HEIGHT));
		btnF7.setText("<html><center><font size=-1>選択</font></center><center><font size=-1>F7(W)</font></center>");
		btnF7.setToolTipText("[F7]：選択を行います。");
		btnF7.addActionListener(f7Action);
		btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), f7Action.getValue(Action.NAME));
		btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt W"),f7Action.getValue(Action.NAME));
		btnF7.getActionMap().put("f7Action", f7Action);
		btnF7.setFocusable(false);
		pnlButton.add(btnF7);

		xSubPos += X_BTN_WIDTH + X_BTN_SPACE + X_BTN_F7_F9;
		F9Action f9Action = new F9Action("f9Action");
		btnF9 = new ActionButton();
		btnF9.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,Y_BTN_HEIGHT));
		btnF9.setText("<html><center><font size=-1>再検索</font></center><center><font size=-1>F9(R)</font></center>");
		btnF9.setToolTipText("[F9]：再検索を行います。");
		btnF9.addActionListener(f9Action);
		btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), f9Action.getValue(Action.NAME));
		btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt R"),f9Action.getValue(Action.NAME));
		btnF9.getActionMap().put("f9Action", f9Action);
		btnF9.setFocusable(false);
		pnlButton.add(btnF9);
	}

	/** Nut search F8 va so su dung
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlSearchButton
	 */
	private void initSearchButton(BasePanel pnlSearchButton){
		
		int xPos = 5;
		int yPos = 5;

		//Panel ben phai, F8 Search Items
		/* 右の側。 */
		xPos = 1;
		yPos = 0;
		
		F8Action f8Action = new F8Action("f8Action");
		btnF8 = new ActionButton();
		btnF8.setBounds(new Rectangle(xPos, yPos, RIGHT_PNL_WIDTH, 40));
		btnF8.setText("<html><center><font size=-1>実行</font></center><center><font size=-1>F8(A)</font></center>");
		btnF8.addActionListener(f8Action);
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"),f8Action.getValue(Action.NAME));
		btnF8.getActionMap().put("f8Action", f8Action);
		btnF8.setToolTipText("[F8]：検索処理を行います。");
		btnF8.setFocusable(false);
		pnlSearchButton.add(btnF8);

		yPos += 40 + 2;
		BaseLabel lblShowKen = new BaseLabel("表示件数");
		lblShowKen.setSize(RIGHT_PNL_WIDTH, FaceContants.LABLE_HEIGHT);
		lblShowKen.setLocation(xPos, yPos);
		lblShowKen.setBorder(null);
		lblShowKen.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSearchButton.add(lblShowKen);

		yPos += FaceContants.LABLE_HEIGHT + 2;
		lblNumber = new BaseLabel("0件");
		lblNumber.setSize(RIGHT_PNL_WIDTH - 10, FaceContants.LABLE_HEIGHT);
		lblNumber.setLocation(xPos + 5, yPos);
		lblNumber.setBorder(FaceContants.LABEL_BORDER);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSearchButton.add(lblNumber);
	}
	
	/** Search Items ben phai
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlSearchItem
	 */
	private void initPnlSearchItem(BasePanel pnlSearchItem) {
		int yPos = 20;
		int xPos = 5;
		int CHK_WIDTH = 85;
		//Radio button in right panel
		
		//1. RdSchItem1
		rdSchItem1 = new InspectRadioButton(dspVo.getSchName1());
		Rd1Action rd1Action = new Rd1Action("rd1Action");
		rdSchItem1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("F1"), rd1Action.getValue(Action.NAME));
		rdSchItem1.getActionMap().put("rd1Action", rd1Action);
		rdSchItem1.setSelected(true);
		rdSchItem1.addActionListener(rd1Action);
		rdSchItem1.setLocation(xPos, yPos);
		rdSchItem1.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
		rdSchItem1.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
		rdSchItem1.setEnabled(true);
		if ("0".equalsIgnoreCase(dspVo.getSchItem1().trim())) {
			pnlSearchItem.add(rdSchItem1);
		}
	
		//2. RdSchItem2
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE + 1;
		rdSchItem2 = new InspectRadioButton(dspVo.getSchName2());
		Rd2Action rd2Action = new Rd2Action("rd2Action");
		rdSchItem2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("F2"), rd2Action.getValue(Action.NAME));
		rdSchItem2.getActionMap().put("rd2Action", rd2Action);
		rdSchItem2.addActionListener(new Rd2Action("Rd2Action"));
		rdSchItem2.setLocation(xPos, yPos);
		rdSchItem2.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
		rdSchItem2.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);		
		rdSchItem2.setEnabled(true);
		if ("0".equalsIgnoreCase(dspVo.getSchItem2().trim())) {
			pnlSearchItem.add(rdSchItem2);
		}
	
		//3. RdSchItem3
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE + 1;
		rdSchItem3 = new InspectRadioButton(dspVo.getSchName3());
		Rd3Action rd3Action = new Rd3Action("rd3Action");
		rdSchItem3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("F3"), rd3Action.getValue(Action.NAME));
		rdSchItem3.getActionMap().put("rd3Action", rd3Action);
		rdSchItem3.setLocation(xPos, yPos);
		rdSchItem3.addActionListener(new Rd3Action("Rd3Action"));
		rdSchItem3.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
		rdSchItem3.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);		
		rdSchItem3.setEnabled(true);
		if ("0".equalsIgnoreCase(dspVo.getSchItem3().trim())) {
			pnlSearchItem.add(rdSchItem3);
		}
	
		//4. RdSchItem4		
		rdSchItem4 = new InspectRadioButton(dspVo.getSchName4());
		rdSchItem4.setEnabled(false);
		if ("0".equalsIgnoreCase(dspVo.getSchItem4().trim())) {
			yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE + 1;
			Rd4Action rd4Action = new Rd4Action("rd4Action");
			rdSchItem4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F4"), rd4Action.getValue(Action.NAME));
			rdSchItem4.getActionMap().put("rd4Action", rd4Action);
			rdSchItem4.setLocation(xPos, yPos);
			rdSchItem4.addActionListener(new Rd4Action("Rd4Action"));
			rdSchItem4.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			rdSchItem4.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);		
			rdSchItem4.setEnabled(true);
			pnlSearchItem.add(rdSchItem4);
		}
	
		//5. RdSchItem5		
		rdSchItem5 = new InspectRadioButton(dspVo.getSchName5());
		rdSchItem5.setEnabled(false);
		if ("0".equalsIgnoreCase(dspVo.getSchItem5().trim())) {
			yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE + 1;
			Rd5Action rd5Action = new Rd5Action("rd5Action");
			rdSchItem5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F5"), rd5Action.getValue(Action.NAME));
			rdSchItem5.getActionMap().put("rd5Action", rd5Action);
			rdSchItem5.setLocation(xPos, yPos);
			rdSchItem5.addActionListener(new Rd5Action("Rd5Action"));
			rdSchItem5.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			rdSchItem5.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);			
			rdSchItem5.setEnabled(true);				
			pnlSearchItem.add(rdSchItem5);
		}
	
		//6. RdSchItem6		
		rdSchItem6 = new InspectRadioButton(dspVo.getSchName6());
		rdSchItem6.setEnabled(false);
		if ("0".equalsIgnoreCase(dspVo.getSchItem6().trim())) {
			yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE + 1;
			Rd6Action rd6Action = new Rd6Action("rd6Action");
			rdSchItem6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F6"), rd6Action.getValue(Action.NAME));
			rdSchItem6.getActionMap().put("rd6Action", rd6Action);
			rdSchItem6.setLocation(xPos, yPos);
			rdSchItem6.addActionListener(new Rd6Action("Rd6Action"));
			rdSchItem6.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			rdSchItem6.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			rdSchItem6.setEnabled(true);
			pnlSearchItem.add(rdSchItem6);
		}

		ButtonGroup rdBtnGrp = new ButtonGroup();
		rdBtnGrp.add(rdSchItem1);
		rdBtnGrp.add(rdSchItem2);
		rdBtnGrp.add(rdSchItem3);
		rdBtnGrp.add(rdSchItem4);
		rdBtnGrp.add(rdSchItem5);
		rdBtnGrp.add(rdSchItem6);

		yPos = 20;
		xPos += CHK_WIDTH - 1;
		CHK_WIDTH = 25;
		if (rdSchItem1.isEnabled()) {
			BaseLabel lblF1 = new BaseLabel("(F1)");
			lblF1.setLocation(xPos, yPos);
			lblF1.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			lblF1.setBorder(null);
			lblF1.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			pnlSearchItem.add(lblF1);
			if (getDefaultSearchItem() == 1) {
				doRd1();
			}
		}

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		if (rdSchItem2.isEnabled()) {
			BaseLabel lblF2 = new BaseLabel("(F2)");
			lblF2.setLocation(xPos, yPos);
			lblF2.setBorder(null);
			lblF2.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			lblF2.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			pnlSearchItem.add(lblF2);
			if (getDefaultSearchItem() == 2) {
				doRd2();
			}
		}

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		if (rdSchItem3.isEnabled()) {
			BaseLabel lblF3 = new BaseLabel("(F3)");
			lblF3.setLocation(xPos, yPos);
			lblF3.setBorder(null);
			lblF3.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			lblF3.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			pnlSearchItem.add(lblF3);
			if (getDefaultSearchItem() == 3) {
				doRd3();
			}
		}

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		if (rdSchItem4.isEnabled()) {
			BaseLabel lblF4 = new BaseLabel("(F4)");
			lblF4.setLocation(xPos, yPos);
			lblF4.setBorder(null);
			lblF4.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			lblF4.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			pnlSearchItem.add(lblF4);
			if (getDefaultSearchItem() == 4) {
				doRd4();
			}
		}

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		if (rdSchItem5.isEnabled()) {
			BaseLabel lblF5 = new BaseLabel("(F5)");
			lblF5.setLocation(xPos, yPos);
			lblF5.setBorder(null);
			lblF5.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			lblF5.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			pnlSearchItem.add(lblF5);
			if (getDefaultSearchItem() == 5) {
				doRd5();
			}
		}

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		if (rdSchItem6.isEnabled()) {
			BaseLabel lblF6 = new BaseLabel("(F6)");
			lblF6.setLocation(xPos, yPos);
			lblF6.setBorder(null);
			lblF6.setSize(CHK_WIDTH, FaceContants.LABLE_HEIGHT);
			lblF6.setFont(FontConstants.SEARCH_RADIO_BTN_FONT);
			pnlSearchItem.add(lblF6);
			if (getDefaultSearchItem() == 6) {
				doRd6();
			}
		}
		
		//***
		
		
		int Y_BTN_HEIGHT = 40;
		int X_BTN_WIDTH = 25;
		
		
		xPos = 5;
		yPos = 5;
		//Add Check Is Use, Add Ha, Ka, Na, Ma filter
		yPos += Y_FILTER_PANEL_HEIGHT + 2;
		if (isShortBtn()) {
			chkIsUsed = new BaseCheckBox("使用不可を表示");
			chkIsUsed.setSize(RIGHT_PNL_WIDTH, FaceContants.TEXT_HEIGHT);
			chkIsUsed.setLocation(xPos, yPos);
			chkIsUsed.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 13));
			chkIsUsed.setChkLabel("使用不可を表示");
			chkIsUsed.setUnChkLabel("使用不可を表示");
			mainPnl.add(chkIsUsed);
		}
		yPos += FaceContants.LABLE_HEIGHT;
		if (isShortBtn()) {
			BaseLabel lblHelpShiyou = new BaseLabel("（赤字で表示）");
			lblHelpShiyou.setSize(RIGHT_PNL_WIDTH, FaceContants.LABLE_HEIGHT);
			lblHelpShiyou.setLocation(xPos, yPos);
			lblHelpShiyou.setForeground(Color.RED);
			lblHelpShiyou.setBorder(null);
			lblHelpShiyou.setHorizontalAlignment(SwingConstants.CENTER);
			lblHelpShiyou.setVerticalAlignment(SwingConstants.TOP);
			mainPnl.add(lblHelpShiyou);
		}
		yPos += FaceContants.LABLE_HEIGHT + 2;

		if (isShortBtn()) {
			BasePanel pnlFilter = new BasePanel();
			pnlFilter.setSize(RIGHT_PNL_WIDTH, Y_FILTER_PANEL_HEIGHT);
			pnlFilter.setLocation(xPos, yPos);
			X_BTN_WIDTH = NumberUtils.getIntFromDouble(RIGHT_PNL_WIDTH / 2);
			Y_BTN_HEIGHT = 32;
			initFilter(pnlFilter, X_BTN_WIDTH, Y_BTN_HEIGHT);
			mainPnl.add(pnlFilter);
		}
	}
	
	/** Hien thi phan Search theo A, Ka, Na, Sa, Ma v..v.. va UseFlg
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlFilter
	 * @param X_BTN_WIDTH
	 * @param Y_BTN_HEIGHT
	 */
	private void initFilter(BasePanel pnlFilter, int X_BTN_WIDTH,
			int Y_BTN_HEIGHT) {

		int xSubPos = 0;
		int ySubPos = 0;
		BtnAAction btnAAction = new BtnAAction("btnAAction");
		btnA = new ActionButton();
		btnA.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH + 1,
				Y_BTN_HEIGHT + 1));
		btnA.setText("<html><center><font size=-1>ア</font></center>");
		btnA.addActionListener(btnAAction);
		btnA.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnA);

		ySubPos += Y_BTN_HEIGHT;
		BtnKaAction btnKaAction = new BtnKaAction("btnKaAction");
		btnKa = new ActionButton();
		btnKa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH + 1,
				Y_BTN_HEIGHT + 1));
		btnKa.setText("<html><center><font size=-1>カ</font></center>");
		btnKa.addActionListener(btnKaAction);
		btnKa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnKa);

		ySubPos += Y_BTN_HEIGHT;
		BtnSaAction btnSaAction = new BtnSaAction("btnSaAction");
		btnSa = new ActionButton();
		btnSa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH + 1,
				Y_BTN_HEIGHT + 1));
		btnSa.setText("<html><center><font size=-1>サ</font></center>");
		btnSa.addActionListener(btnSaAction);
		btnSa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnSa);

		ySubPos += Y_BTN_HEIGHT;
		BtnTaAction btnTaAction = new BtnTaAction("btnTaAction");
		btnTa = new ActionButton();
		btnTa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH + 1,
				Y_BTN_HEIGHT + 1));
		btnTa.setText("<html><center><font size=-1>タ</font></center>");
		btnTa.addActionListener(btnTaAction);
		btnTa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnTa);

		ySubPos += Y_BTN_HEIGHT;
		BtnNaAction btnNaAction = new BtnNaAction("btnNaAction");
		btnNa = new ActionButton();
		btnNa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH + 1,
				Y_BTN_HEIGHT + 1));
		btnNa.setText("<html><center><font size=-1>ナ</font></center>");
		btnNa.addActionListener(btnNaAction);
		btnNa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnNa);

		ySubPos += Y_BTN_HEIGHT;
		BtnEiSuuAction btnEiSuuAction = new BtnEiSuuAction("btnEiSuuAction");
		btnEisuu = new ActionButton();
		btnEisuu.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH * 2,
				Y_BTN_HEIGHT));
		btnEisuu.setText("<html><center><font size=-1>英数</font></center>");
		btnEisuu.addActionListener(btnEiSuuAction);
		btnEisuu.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnEisuu);

		xSubPos = X_BTN_WIDTH;
		ySubPos = 0;
		BtnHaAction btnHaAction = new BtnHaAction("btnHaAction");
		btnHa = new ActionButton();
		btnHa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT + 1));
		btnHa.setText("<html><center><font size=-1>ハ</font></center>");
		btnHa.addActionListener(btnHaAction);
		btnHa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnHa);

		ySubPos += Y_BTN_HEIGHT;
		BtnMaAction btnMaAction = new BtnMaAction("btnMaAction");
		btnMa = new ActionButton();
		btnMa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT + 1));
		btnMa.setText("<html><center><font size=-1>マ</font></center>");
		btnMa.addActionListener(btnMaAction);
		btnMa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnMa);

		ySubPos += Y_BTN_HEIGHT;
		BtnYaAction btnYaAction = new BtnYaAction("btnYaAction");
		btnYa = new ActionButton();
		btnYa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT + 1));
		btnYa.setText("<html><center><font size=-1>ヤ</font></center>");
		btnYa.addActionListener(btnYaAction);
		btnYa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnYa);

		ySubPos += Y_BTN_HEIGHT;
		BtnRaAction btnRaAction = new BtnRaAction("btnRaAction");
		btnRa = new ActionButton();
		btnRa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT + 1));
		btnRa.setText("<html><center><font size=-1>ラ</font></center>");
		btnRa.addActionListener(btnRaAction);
		btnRa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnRa);

		ySubPos += Y_BTN_HEIGHT;
		BtnWaAction btnWaAction = new BtnWaAction("btnWaAction");
		btnWa = new ActionButton();
		btnWa.setBounds(new Rectangle(xSubPos, ySubPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT + 1));
		btnWa.setText("<html><center><font size=-1>ワ</font></center>");
		btnWa.addActionListener(btnWaAction);
		btnWa.setBorder(FaceContants.LINE_BORDER);
		pnlFilter.add(btnWa);
	}
	
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	protected int getDefaultSearchItem() {
		return 1;
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
	protected abstract String getNumber();

	@Override
	protected BasePanel getFooter() {
		// パネルの生成
		footerMainPnl = new BasePanel();
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH,
				Y_FOOTER_LENGTH));
		final int I_LBL_HEIGHT = 22;

		BasePanel statusBar = new BasePanel();
		statusBar.setSize(new Dimension(X_FOOTER_LENGTH - 3,
				Y_FOOTER_LENGTH - 4));
		statusBar.setLocation(2, 3);
		statusBar.setLayout(null);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor.setSize(new Dimension(X_FOOTER_LENGTH - 3, I_LBL_HEIGHT));
		lblHelpInfor.setLocation(0, 2);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);

		footerMainPnl.add(statusBar);

		return footerMainPnl;
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
	protected abstract String getHelpInfor();
	
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
	protected abstract String getCKey();

	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected BasePanel getHeader() {
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
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
	protected abstract boolean isShortBtn();

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
	protected abstract TrSchdspVo getSchDspVo();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param row
	 */
	protected void doDoubleClick(int row) {
		returnData = (SearchVo) m_data.getValueAtRow(row);
		dispose();
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
	public SearchVo getReturnData() {
		return returnData;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param row
	 */
	protected void doSingleClick(int row) {

		if (!isFirst) {
		} else {
			isFirst = false;
		}
		SwingUtils.scrollRowColumn(m_table, row, 0);
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
	protected abstract void doF8();

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
		returnData = (SearchVo) m_data.getValueAtRow(m_table.getSelectedRow());
		dispose();
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
		doSearch();
	}

	/**
	 * @author PC13
	 * 
	 */
	class BtnAAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnAAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnA();
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
	protected abstract void doBtnA();

	/**
	 * @author PC13
	 * 
	 */
	class BtnKaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnKaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnKa();
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
	protected abstract void doBtnKa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnSaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnSaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnSa();
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
	protected abstract void doBtnSa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnTaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnTaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnTa();
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
	protected abstract void doBtnTa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnNaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnNaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnNa();
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
	protected abstract void doBtnNa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnHaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnHaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnHa();
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
	protected abstract void doBtnHa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnMaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnMaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnMa();
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
	protected abstract void doBtnMa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnYaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnYaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnYa();
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
	protected abstract void doBtnYa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnRaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnRaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnRa();
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
	protected abstract void doBtnRa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnWaAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnWaAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnWa();
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
	protected abstract void doBtnWa();

	/**
	 * @author PC13
	 * 
	 */
	class BtnEiSuuAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public BtnEiSuuAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doBtnEiSuu();
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
	protected abstract void doBtnEiSuu();

	/**
	 * @author PC13
	 * 
	 */
	class Rd1Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public Rd1Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			if (m_data.sortObj != null) {
				m_data.sortObj.setM_sortCol(1);
				m_data.sortObj.setInit(true);
			}
			rdSchItem1.requestFocus();
			doRd1();
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
	protected abstract void doRd1();

	/**
	 * @author PC13
	 * 
	 */
	public class Rd2Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public Rd2Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			if (m_data.sortObj != null) {
				m_data.sortObj.setM_sortCol(2);
				m_data.sortObj.setInit(true);
			}
			rdSchItem2.requestFocus();
			doRd2();
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
	protected abstract void doRd2();

	/**
	 * @author PC13
	 * 
	 */
	class Rd3Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public Rd3Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			if (m_data.sortObj != null) {
				m_data.sortObj.setM_sortCol(3);
				m_data.sortObj.setInit(true);
			}
			rdSchItem3.requestFocus();
			doRd3();
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
	protected abstract void doRd3();

	/**
	 * @author PC13
	 * 
	 */
	class Rd4Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public Rd4Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			if (m_data.sortObj != null) {
				m_data.sortObj.setM_sortCol(4);
				m_data.sortObj.setInit(true);
			}
			rdSchItem4.requestFocus();
			doRd4();
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
	protected abstract void doRd4();

	/**
	 * @author PC13
	 * 
	 */
	class Rd5Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public Rd5Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			if (m_data.sortObj != null) {
				m_data.sortObj.setM_sortCol(5);
				m_data.sortObj.setInit(true);
			}
			rdSchItem5.requestFocus();
			doRd5();
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
	protected abstract void doRd5();

	/**
	 * @author PC13
	 * 
	 */
	class Rd6Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public Rd6Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			if (m_data.sortObj != null) {
				m_data.sortObj.setM_sortCol(6);
				m_data.sortObj.setInit(true);
			}
			rdSchItem6.requestFocus();
			doRd6();
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
	protected abstract void doRd6();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	protected boolean isDisplayWhenInit() {
		return true;
	}

	/**
	 * @author PC13
	 * 
	 */
	public class SearchTableDataModel extends AbstractTableModel {

		/** */
		protected ColumnData m_columns[];
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		protected Vector<SearchVo> m_vector;
		/** */
		protected SortObjVo sortObj = new SortObjVo();

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public SearchTableDataModel() {
			m_vector = new Vector<SearchVo>();
			if (sortObj == null)
				sortObj = new SortObjVo();
			setDefaultData();
		}

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD> <BR>
		 * 
		 * @param sortObj
		 *            </DL>
		 */
		public SearchTableDataModel(SortObjVo sortObj) {
			m_vector = new Vector<SearchVo>();
			this.sortObj = sortObj;
			setDefaultData();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		public int getRowCount() {

			return m_vector == null ? 0 : m_vector.size();
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
		public SortObjVo getSortObj() {
			if (sortObj == null)
				sortObj = new SortObjVo();
			return sortObj;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD> <BR>
		 * 
		 * @param sortObj
		 * @return </DL>
		 */
		public SortObjVo getSortObj(SortObjVo sortObj) {
			if (this.sortObj == null)
				this.sortObj = new SortObjVo();
			this.sortObj = sortObj;
			return this.sortObj;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		public int getColumnCount() {
			return m_columns.length;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		public String getColumnName(int column) {

			String headerTitle = m_columns[column].m_title;

			if (sortObj.isInit()) {
				if ((column + 1) == sortObj.getM_sortCol()) {
					if (isRadioAndColumnTheSame[column]) {
						headerTitle += sortObj.isM_sortAsc() ? " △" : " ▽";
					}
				}
			} else {
				if (column == sortObj.getM_sortCol()) {
					headerTitle += sortObj.isM_sortAsc() ? " △" : " ▽";
				}
			}

			return headerTitle;
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
		public ColumnData[] getColumnData() {
			return m_columns;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
		 */
		public boolean isCellEditable(int nRow, int nCol) {
			return false;
		}

		public int retrieveData() {
			try {
				if (searchService == null)
					searchService = new SearchServiceImpl();
				List<SearchVo> lstData = searchService.getLstSearchVo(dspVo, getSearchCondition(), getSortObj());
				m_vector.clear();
				if (lstData != null) {
					for (int i = 0; i < lstData.size(); i++) {
						SearchVo dataVo = lstData.get(i);
						if (dataVo != null) {
							m_vector.add(dataVo);
						}
					}
					return 1;
				} else {
					return 0;
				}
			} catch (BizException e) {
				e.printStackTrace();
			}
			return -1;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public MouseAdapter setMouseListener(JTable m_table) {
			return new ColumnListener(m_table);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int nRow, int nCol) {

			if (nRow < 0 || nRow >= getRowCount())
				return "";

			SearchVo row = (SearchVo) m_vector.elementAt(nRow);

			int iCount = 0;
			String strReturn = "";
			if ("0".equalsIgnoreCase(dspVo.getSpdCol1())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer1();
					iCount++;
				}
			}

			if ("0".equalsIgnoreCase(dspVo.getSpdCol2())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer2();
					iCount++;
				}
			}

			if ("0".equalsIgnoreCase(dspVo.getSpdCol3())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer3();
					iCount++;
				}
			}

			if ("0".equalsIgnoreCase(dspVo.getSpdCol4())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer4();
					iCount++;
				}
			}

			if ("0".equalsIgnoreCase(dspVo.getSpdCol5())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer5();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol6())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer6();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol7())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer7();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol8())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer8();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol9())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer9();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol10())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer10();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol11())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer11();
					iCount++;
				}
			}
			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol12())) {
				if (iCount != nCol) {
					iCount++;
				} else {
					strReturn = row.getProPer12();
					iCount++;
				}
			}

			return strReturn;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param nRow
		 * @return
		 */
		public Object getValueAtRow(int nRow) {

			if (nRow < 0)
				return null;

			SearchVo row = (SearchVo) m_vector.elementAt(nRow);

			return row;
		}

		public void setDefaultData() {

			List<ColumnData> lstHeader = new ArrayList<ColumnData>();
			int iWith = dspVo.getSpdColwd1()  + dspVo.getSpdColwd2()
					  + dspVo.getSpdColwd3()  + dspVo.getSpdColwd4()
					  + dspVo.getSpdColwd5()  + dspVo.getSpdColwd6()
					  + dspVo.getSpdColwd7()  + dspVo.getSpdColwd8()
					  + dspVo.getSpdColwd9()  + dspVo.getSpdColwd10()
					  + dspVo.getSpdColwd11() + dspVo.getSpdColwd12();

			//Set Column Properties
			//1. Table column 1
			if ("0".equalsIgnoreCase(dspVo.getSpdCol1().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm1();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd1();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//2. Table column 2
			if ("0".equalsIgnoreCase(dspVo.getSpdCol2().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm2();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd2();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//3. Table column 3
			if ("0".equalsIgnoreCase(dspVo.getSpdCol3().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm3();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd3();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//4. Table column 4
			if ("0".equalsIgnoreCase(dspVo.getSpdCol4().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm4();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd4();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//5. Table column 5
			if ("0".equalsIgnoreCase(dspVo.getSpdCol5().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm5();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd5();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//6. Table column 6			
			if ("0".equalsIgnoreCase(dspVo.getSpdCol6().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm6();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd6();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//7. Table column 7
			if ("0".equalsIgnoreCase(dspVo.getSpdCol7().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm7();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd7();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//8. Table column 8
			if ("0".equalsIgnoreCase(dspVo.getSpdCol8().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm8();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd8();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//9. Table column 9
			if ("0".equalsIgnoreCase(dspVo.getSpdCol9().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm9();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd9();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//10. Table column 10
			if ("0".equalsIgnoreCase(dspVo.getSpdCol10().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm10();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd10();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//11. Table column 11
			if ("0".equalsIgnoreCase(dspVo.getSpdCol11().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm11();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd11();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}
			//12. Table column 12
			if ("0".equalsIgnoreCase(dspVo.getSpdCol12().trim())) {
				ColumnData colData = new ColumnData();
				colData.m_title = dspVo.getSpdColnm12();
				colData.m_alignment = SwingConstants.LEFT;
				colData.m_width = dspVo.getSpdColwd12();
				if (colData.m_width == 0)
					colData.m_width = pnlTable.getWidth() - iWith;
				lstHeader.add(colData);
			}

			m_columns = new ColumnData[lstHeader.size()];

			for (int i = 0; i < lstHeader.size(); i++) {
				m_columns[i] = lstHeader.get(i);
			}

			if (isInit) {
				if (isDisplayWhenInit()) {
					retrieveData();
				}
				isInit = false;
			}
		}

		/**
		 * @author PC13
		 * 
		 */
		protected class ColumnListener extends MouseAdapter {

			/** */
			protected JTable m_table;

			/**
			 * <DL>
			 * <DT>コンストラクター記述：</DT>
			 * <DD></DD>
			 * <BR>
			 * 
			 * </DL>
			 * 
			 * @param table
			 */
			public ColumnListener(JTable table) {
				m_table = table;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			public void mouseClicked(MouseEvent e) {

				TableColumnModel colModel = m_table.getColumnModel();
				int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
				int modelIndex = colModel.getColumn(columnModelIndex)
						.getModelIndex();

				if (modelIndex < 0)
					return;

				if (m_data.sortObj != null) {
					if (m_data.sortObj.isInit() == true) {
						sortObj.setM_sortCol(modelIndex);
					}
					m_data.sortObj.setInit(false);
				}

				if (sortObj.getM_sortCol() == modelIndex)
					sortObj.setM_sortAsc(!sortObj.isM_sortAsc());
				else
					sortObj.setM_sortCol(modelIndex);

				doSearch();

				return;
			}
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
	protected abstract SearchConditionVo getSearchCondition();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doSearch();


	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void resetData() {

		if (StringUtils.isValid(searchIndex)) {
			if (rdSchItem3.isEnabled()) {
				if (!rdSchItem3.isSelected()) {
					if (m_data.sortObj != null) {
						m_data.sortObj.setM_sortCol(3);
						m_data.sortObj.setInit(true);
					}
					doRd3();
				}
			}
		}
		
		if (btnA != null) {
			btnA.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnKa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnSa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnTa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnNa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnHa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnMa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnYa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnRa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnWa.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
			btnEisuu.setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.gui.AbstractFrame#setFrameSize()
	 */
	protected abstract void setFrameSize();
	

	/**
	 * gridx : Toa do(vi tri) x cua cell
	 * gridy : Toa do(vi tri) y cua cell
	 * gridwidth : Do rong cua cell
	 * gridheight : Chieu cao cua cell
	 * weightx : Do co dan x khi size thay doi. Neu do rong cell fix thi gan bang 0
	 * weightx : Do co dan y khi size thay doi. Neu do rong cell fix thi gan bang 0
	 * anchor :This field is used when the component is smaller than its display area. It determines where, within the display area, to place the component. 
	 * fill : Cell co gian khi man hinh co gian theo
	 * insets : Thay doi thuoc tinh Padding theo thu tu Top, Left, Bottom, Right
	 * ipadx : Chen them khoang trong theo chieu ngang
	 * ipady : Chen them khoang trong theo chieu doc
	 */
	public static GridBagConstraints setBagConstraints(int gridx, int gridy, int gridwidth, 
									int gridheight, double weightx, double weighty, int anchor, int fill, 
									Insets insets, int ipadx, int ipady) {		
		return new GridBagConstraints(gridx,gridy,gridwidth, gridheight, 
										weightx, weighty, anchor, fill, insets, ipadx,ipady);

	}
	
	public static GridBagConstraints createFixedHorzConstraints(int gridx,
			int gridy, int gridwidth, int gridheight) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0,
				0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(1, 12, 1, 2), 0, 0);
	}

	public static GridBagConstraints createHorzConstraints(int gridx,
			int gridy, int gridwidth, int gridheight, double weightx) {

		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(1, 0, 1, 0), 0, 0);
	}

	public static GridBagConstraints createBothConstraints(int gridx,
			int gridy, int gridwidth, int gridheight, double weightx,
			double heightx) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, heightx, GridBagConstraints.LAST_LINE_START,
				GridBagConstraints.BOTH, new Insets(1, 0, 1, 0), 0, 0);
	}

	
}
