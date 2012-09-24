package com.fas.sapp.master.productregister;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.jface.bussiness.BaseMasterLayoutFrame;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.RequiredLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableModel;
import com.fas.jface.text.BaseInputText;
import com.fas.sapp.system.mname.InspectTableRenderer;

public class ProductRegisterFrame extends BaseMasterLayoutFrame{

	private static final long serialVersionUID = 1L;
	
	
	/** テーブルヘッダーサイズ */
	private static final String colHeader[] = { "商品番号", "商品名" };
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private static final int[] colHeaderWidth = { 100, 200 };
	
	
	public ProductRegisterFrame(BaseFrame frame, String title) {
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
	public ProductRegisterFrame(BaseFrame frame) {
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
	public ProductRegisterFrame(){
		super();
		init();
	}
	
	private void init(){
		setResizable(false);
		getHeader();
		getBodyLeft();
		getBodyCenter();
		//setSize(600, 400);
		
	}
	
	@Override
	protected String getSubName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getHelpInfor() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
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
		boolean[] fbtn = {true,true,true,false,true,false,true,false,true,true,true,true,false,true,true};
		fbtn[3] = false;
		fbtn[5] = false;
		fbtn[7] = false;
		fbtn[9] = false;
		return fbtn;
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

	@Override
	protected BasePanel getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasePanel getBodyLeft() {
		// TODO Auto-generated method stub		
		int X_WIDTH = 100;
		int xPos = 20, yPos = 10;
		int LINE_SPACE = 10;
		int COMBOBOX_LENGHT_120 = 120;
		
		BasePanel panel_1 = new BasePanel();
		panel_1.setLayout(null);
		panel_1.setBorder(FaceContants.PANEL_BORDER);
		panel_1.setBounds(20,10,850-2*20,670-3*Y_FOOTER_LENGTH-3*FaceContants.VERTICAL_SPACE);
		
		// header 
		//
		BaseLabel lbStatus = new BaseLabel("登　　録");
		lbStatus.setFont(new java.awt.Font("Serif", 1, 13));
		lbStatus.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		lbStatus.setBorder(FaceContants.LINE_BORDER);
		lbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lbStatus.setBackground(ColorConstants.LABEL_NEW_MODE_BACKGROUND_COLOR);
		panel_1.add(lbStatus);
		
		xPos+=X_WIDTH;
		BaseLabel lb2 = new BaseLabel("登録件数");
		lb2.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		lb2.setBorder(FaceContants.LINE_BORDER);
		lb2.setHorizontalAlignment(SwingConstants.LEADING);
		//lb2.setBackground(ColorConstants.LABEL_COPY_MODE_BACKGROUND_COLOR);
		panel_1.add(lb2);
		
		xPos+=X_WIDTH;
		BaseLabel lb3 = new BaseLabel("");
		lb3.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		lb3.setBorder(FaceContants.LINE_BORDER);
		lb3.setHorizontalAlignment(SwingConstants.TRAILING);
		//lb3.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		panel_1.add(lb3);
		
		xPos+=X_WIDTH;
		BaseLabel lb4 = new BaseLabel("最終コード");
		lb4.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		lb4.setBorder(FaceContants.LINE_BORDER);
		lb4.setHorizontalAlignment(SwingConstants.LEADING);
		//lb4.setBackground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
		panel_1.add(lb4);
		
		xPos+=X_WIDTH;
		BaseLabel lb5 = new BaseLabel("");
		lb5.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		lb5.setBorder(FaceContants.LINE_BORDER);
		lb5.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lb5);
		
		xPos+=X_WIDTH;
		BaseLabel lb6 = new BaseLabel("更新不可");
		lb6.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		lb6.setBorder(FaceContants.border);
		lb6.setForeground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
		lb6.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lb6);
		
		xPos =10; yPos = 20+FaceContants.LABLE_HEIGHT; 
		
		BasePanel left = new BasePanel();
		left.setLayout(null);
		left.setBounds(xPos-5,yPos,450,panel_1.getSize().height-30-FaceContants.LABLE_HEIGHT);
		left.setBorder(FaceContants.PANEL_BORDER);
		panel_1.add(left);

		//
		//panel left
		//
		xPos = 15; yPos = 5;
		//line 1
		RequiredLabel rlLine1 = new RequiredLabel("*");
		rlLine1.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine1);
		
		BaseLabel blLine1 = new BaseLabel("商品番号");
		blLine1.setBorder(FaceContants.TITLE_BORDER);
		blLine1.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine1.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine1);
		
		BaseInputText bitLine1 = new BaseInputText();
		bitLine1.setBorder(FaceContants.LABEL_BORDER);
		bitLine1.setHorizontalAlignment(SwingConstants.TRAILING);
		bitLine1.setBounds(xPos+X_WIDTH, yPos, X_WIDTH-30, FaceContants.LABLE_HEIGHT);
		left.add(bitLine1);
		
		//line2
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;
		
		RequiredLabel rlLine2 = new RequiredLabel("*");
		rlLine2.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine2);
		
		BaseLabel blLine2 = new BaseLabel("分　類");
		blLine2.setBorder(FaceContants.TITLE_BORDER);
		blLine2.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine2.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine2);
		
		JComboBox cbbLine2 = new JComboBox();
		cbbLine2.setBorder(FaceContants.TITLE_BORDER);
		cbbLine2.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120+20, FaceContants.LABLE_HEIGHT);
		left.add(cbbLine2);
		
		//line3
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;
		
		RequiredLabel rlLine3 = new RequiredLabel("*");
		rlLine3.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine3);
		
		BaseLabel blLine3 = new BaseLabel("商品名");
		blLine3.setBorder(FaceContants.TITLE_BORDER);
		blLine3.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine3.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine3);
		
		BaseInputText bitLine3 = new BaseInputText();
		bitLine3.setBorder(FaceContants.LABEL_BORDER);
		bitLine3.setBounds(xPos+X_WIDTH, yPos, 290, FaceContants.LABLE_HEIGHT);
		left.add(bitLine3);
				
		//line4
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;
		
		RequiredLabel rlLine4 = new RequiredLabel("*");
		rlLine4.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine4);
		
		BaseLabel blLine4 = new BaseLabel("ふりがな");
		blLine4.setBorder(FaceContants.TITLE_BORDER);
		blLine4.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine4.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine4);
		
		BaseInputText bitLine4 = new BaseInputText();
		bitLine4.setBorder(FaceContants.LABEL_BORDER);
		bitLine4.setBounds(xPos+X_WIDTH, yPos, 290, FaceContants.LABLE_HEIGHT);
		left.add(bitLine4);
		
		//line 5
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;		
		
		BaseLabel blLine5 = new BaseLabel("整理番号");
		blLine5.setBorder(FaceContants.TITLE_BORDER);
		blLine5.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine5.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine5);
		
		BaseInputText bitLine5 = new BaseInputText();
		bitLine5.setHorizontalAlignment(SwingConstants.TRAILING);
		bitLine5.setBorder(FaceContants.LABEL_BORDER);
		bitLine5.setBounds(xPos+X_WIDTH, yPos, X_WIDTH-30, FaceContants.LABLE_HEIGHT);
		left.add(bitLine5);
		
		//line 6
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;	
		
		RequiredLabel rlLine6 = new RequiredLabel("*");
		rlLine6.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine6);
		
		BaseLabel blLine6 = new BaseLabel("ポイント");
		blLine6.setBorder(FaceContants.TITLE_BORDER);
		blLine6.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine6.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine6);
		
		BaseInputText bitLine6 = new BaseInputText();
		bitLine6.setHorizontalAlignment(SwingConstants.TRAILING);
		bitLine6.setBorder(FaceContants.LABEL_BORDER);
		bitLine6.setBounds(xPos+X_WIDTH, yPos, X_WIDTH-30, FaceContants.LABLE_HEIGHT);
		left.add(bitLine6);
		
		BaseLabel blLine6_1 = new BaseLabel("倍");
		blLine6_1.setBorder(FaceContants.border);
		blLine6_1.setBounds(xPos+X_WIDTH+X_WIDTH-30, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		left.add(blLine6_1);
		
		//line 7
		
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;		
		
		BaseLabel blLine7 = new BaseLabel("当月最終仕入単価");
		blLine7.setFont(new java.awt.Font("Serif", 0, 11));
		blLine7.setBorder(FaceContants.TITLE_BORDER);
		blLine7.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine7.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine7);
		
		BaseLabel blLine7_1 = new BaseLabel("999999.99");
		blLine7_1.setBorder(FaceContants.TITLE_BORDER);
		blLine7_1.setHorizontalAlignment(SwingConstants.TRAILING);
		blLine7_1.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(blLine7_1);
		
		//line 8
		
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;		
		
		BaseLabel blLine8 = new BaseLabel("翌月最終仕入単価");
		blLine8.setFont(new java.awt.Font("Serif", 0, 11));
		blLine8.setBorder(FaceContants.TITLE_BORDER);
		blLine8.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine8.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine8);
		
		BaseLabel blLine8_1 = new BaseLabel("999999.99");
		blLine8_1.setBorder(FaceContants.TITLE_BORDER);
		blLine8_1.setHorizontalAlignment(SwingConstants.TRAILING);
		blLine8_1.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(blLine8_1);
		
		//line 9
		
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;
		
		RequiredLabel rlLine9 = new RequiredLabel("*");
		rlLine9.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine9);
		
		BaseLabel blLine9 = new BaseLabel("当月消費税");
		blLine9.setBorder(FaceContants.TITLE_BORDER);
		blLine9.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine9.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine9);
		
		JComboBox cbbLine9 = new JComboBox();
		cbbLine9.setBorder(FaceContants.TITLE_BORDER);
		cbbLine9.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(cbbLine9);
		
		// line 10
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;
		
		RequiredLabel rlLine10 = new RequiredLabel("*");
		rlLine10.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine10);
		
		BaseLabel blLine10 = new BaseLabel("翌月消費税");
		blLine10.setBorder(FaceContants.TITLE_BORDER);
		blLine10.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine10.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine10);
		
		JComboBox cbbLine10 = new JComboBox();
		cbbLine10.setBorder(FaceContants.TITLE_BORDER);
		cbbLine10.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(cbbLine10);
		
		//line 11
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;
		
		RequiredLabel rlLine11 = new RequiredLabel("*");
		rlLine11.setBounds(xPos-10, yPos, 10, FaceContants.LABLE_HEIGHT);
		left.add(rlLine11);
		
		BaseLabel blLine11 = new BaseLabel("取扱状況");
		blLine11.setBorder(FaceContants.TITLE_BORDER);
		blLine11.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine11.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine11);
		
		JComboBox cbbLine11 = new JComboBox();
		cbbLine11.setBorder(FaceContants.TITLE_BORDER);
		cbbLine11.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(cbbLine11);
		
		//line 12
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;		
		
		BaseLabel blLine12 = new BaseLabel("組合員販売単価");
		blLine12.setFont(new java.awt.Font("Serif", 0, 12));
		blLine12.setBorder(FaceContants.TITLE_BORDER);
		blLine12.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine12.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine12);
		
		BaseInputText bitLine12 = new BaseInputText();
		bitLine12.setHorizontalAlignment(SwingConstants.TRAILING);
		bitLine12.setBorder(FaceContants.TITLE_BORDER);
		bitLine12.setBounds(xPos+X_WIDTH, yPos,COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(bitLine12);
		
		//line 13
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;		
		
		BaseLabel blLine13 = new BaseLabel("支部販売単価");
		blLine13.setBorder(FaceContants.TITLE_BORDER);
		blLine13.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine13.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine13);
		
		BaseInputText bitLine13 = new BaseInputText();
		bitLine13.setHorizontalAlignment(SwingConstants.TRAILING);
		bitLine13.setBorder(FaceContants.TITLE_BORDER);
		bitLine13.setBounds(xPos+X_WIDTH, yPos, COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(bitLine13);
		
		//line 14
		xPos = 15; yPos+= FaceContants.LABLE_HEIGHT + LINE_SPACE;		
		
		BaseLabel blLine14 = new BaseLabel("一般販売単価");
		blLine14.setBorder(FaceContants.TITLE_BORDER);
		blLine14.setBounds(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT);
		blLine14.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		left.add(blLine14);
		
		BaseInputText bitLine14 = new BaseInputText();
		bitLine14.setHorizontalAlignment(SwingConstants.TRAILING);
		bitLine14.setBorder(FaceContants.TITLE_BORDER);
		bitLine14.setBounds(xPos+X_WIDTH, yPos,COMBOBOX_LENGHT_120, FaceContants.LABLE_HEIGHT);
		left.add(bitLine14);
		
		//table
		int topY = 45;
		// 
		BasePanel tblPnl = new BasePanel();
		tblPnl.setSize(panel_1.getSize().width-20-left.getSize().width,panel_1.getSize().height-65);
		tblPnl.setLocation(left.getSize().width+10, topY);
		
		InspectTableModel m_master_model = new InspectTableModel(colHeader);

		InspectTable m_master_table = new InspectTable(m_master_model) {
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
		
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_master_table.getColumnModel();

		for (int i = 0; i < colHeader.length; i++) {
			defModel.getColumn(i).setMinWidth(colHeaderWidth[i]);
			defModel.getColumn(i).setWidth(colHeaderWidth[i]);
			defModel.getColumn(i).setMaxWidth(colHeaderWidth[i] + 200);
		}
		m_master_table.getTableHeader().setReorderingAllowed(false);
		// invisible Column Code
		defModel.getColumn(0).setPreferredWidth(0);
		defModel.getColumn(0).setMaxWidth(0);
		
		BaseScrollPane tablePnl = new BaseScrollPane(m_master_table);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePnl.repaint();
		
		tablePnl.setSize(panel_1.getSize().width-20-left.getSize().width,panel_1.getSize().height-65);
		tablePnl.getViewport().add(m_master_table);
		tablePnl.setLocation(0, 0);
		tblPnl.add(tablePnl);
		
		panel_1.add(tblPnl);
		
		
		
		pnlLeft.add(panel_1);
		pnlLeft.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		pnlLeft.setLayout(new BorderLayout());
		mainPanel.add(pnlLeft,BorderLayout.LINE_START);
		return pnlLeft;
	}

	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void setFrameSize()
	{
		X_FRAME_LENGTH = 850; Y_FRAME_LENGTH = 700;
	}
	
}
