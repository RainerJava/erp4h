package com.fas.sapp.master.maintenanceregister;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.jface.bussiness.BaseMasterLayoutFrameForOrderProcessing;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.RequiredLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableModel;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.jface.text.BaseDatePicker;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputSearchText;
import com.fas.jface.text.IntegerNumberText;
import com.fas.sapp.search.SearchPC01Frame;
import com.fas.sapp.search.SearchPG01Frame;
import com.fas.sapp.search.SearchPG02Frame;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.master.pc01.Pc01Service;
import com.fas.service.master.pc01.Pc01ServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.memp.MEmpVo;
import com.fas.vo.pc01.Pc01Vo;
import com.fas.vo.search.SearchVo;

public class MaintenanceRegisterFrame extends BaseMasterLayoutFrameForOrderProcessing {

	private CdInputSearchText txtCertificationNumber;
	
	private BaseInputText txtSpecificationNumber;
	
	private BaseComboBox cbbAffilicationGroup;
	
	private CdInputSearchText txtBranchAffilication;
	
	private BaseComboBox cbbBusinessCategory;
	
	//Novelty: tinh moi me
	private BaseComboBox cbbNovelty01;
	private BaseComboBox cbbNovelty02;
	private BaseComboBox cbbNovelty03;
	private BaseComboBox cbbNovelty04;
	
	//SmallSize: kich thuoc nho
	private BaseComboBox cbbSmallSize01;
	private BaseComboBox cbbSmallSize02;
	private BaseComboBox cbbSmallSize03;
	private BaseComboBox cbbSmallSize04;
	
	//Usually: tinh thong dung
	private BaseComboBox cbbUsually01;
	private BaseComboBox cbbUsually02;	
	private BaseComboBox cbbUsually03;
	private BaseComboBox cbbUsually04;

	//Light: do nang nhe
	private BaseComboBox cbbLight01;
	private BaseComboBox cbbLight02;
	private BaseComboBox cbbLight03;
	private BaseComboBox cbbLight04;
	
	//AbolitionDay: ngay huy bo
	private BaseComboBox cbbAbolitionDay01;
	private BaseComboBox cbbAbolitionDay02;
	private BaseComboBox cbbAbolitionDay03;
	private BaseComboBox cbbAbolitionDay04;
	
	//BusinessMan
	private BaseInputText txtBManName;	
	private BaseInputText txtBManPost;	
	private BaseInputText txtBManAddress;	
	private BaseInputText txtBManRepreame;	
	private CdInputSearchText txtBManAddressCode;	
	private BaseLabel lblBManAddressName;	
	private BaseInputText txtBManTel;	
	private BaseInputText txtBManFax;
	
	//BusinessField
	private BaseInputText txtBFieldName;	
	private BaseInputText txtBFieldPost;	
	private BaseInputText txtBFieldAddress;	
	private BaseInputText txtBFieldKana;	
	private CdInputSearchText txtBFieldAddressCode;	
	private BaseLabel lblBFieldAddressName;	
	private BaseInputText txtBFieldTel;	
	private BaseInputText txtBFieldFax;
	
	
	//Tab1
	private BaseInputText txtTab1CarPlantEmployee;
	private BaseInputText txtTab1TestPlantEmployee;
	private BaseInputText txtTab1SheetMetalPlantEmployee;
	private BaseInputText txtTab1PaintPlantEmployee;
	private BaseInputText txtTab1OtherPlantsEmployee;// Nhan vien cua cac nha may khac
	private BaseInputText txtTab1Otherwise;
	private BaseLabel lblTab1TotalEmployee;
	
	private BaseInputText txtTab1MechanicLevel2;
	private BaseInputText txtTab1MechanicLevel3;
	private BaseLabel lblTab1MeterMechanic;// May do co khi
	
	
	private BaseCheckBox chkTab1Motive;
	
	private BaseCheckBox chkTab1Power;
	
	private BaseCheckBox chkTab1Travel;
	
	private BaseCheckBox chkTab1Control;
	
	private BaseCheckBox chkTab1Braking;
	
	private BaseCheckBox chkTab1Buffered;
	
	private BaseCheckBox chkTab1Consolidation;
	
	private BaseComboBox cbbTab1NoLimit01;
	private BaseComboBox cbbTab1NoLimit02;
	private BaseComboBox cbbTab1NoLimit03;
	private BaseComboBox cbbTab1NoLimit04;
	private BaseComboBox cbbTab1NoLimit05;
	private BaseComboBox cbbTab1NoLimit06;
	private BaseComboBox cbbTab1NoLimit07;
	private BaseComboBox cbbTab1NoLimit08;
	private BaseComboBox cbbTab1NoLimit09;
	private BaseComboBox cbbTab1NoLimit10;
	private BaseComboBox cbbTab1NoLimit11;
	
	//Tab 2
	private IntegerNumberText txtTab2Area01Line1;	
	private IntegerNumberText txtTab2Frontage01Line1;
	private IntegerNumberText txtTab2Frontage02Line1;
	private IntegerNumberText txtTab2Frontage03Line1;
	private IntegerNumberText txtTab2Frontage04Line1;	
	private IntegerNumberText txtTab2Depth01Line1;
	private IntegerNumberText txtTab2Depth02Line1;
	private IntegerNumberText txtTab2Depth03Line1;
	private IntegerNumberText txtTab2Depth04Line1;
	
	private IntegerNumberText txtTab2Area01Line2;	
	private IntegerNumberText txtTab2Frontage01Line2;
	private IntegerNumberText txtTab2Frontage02Line2;
	private IntegerNumberText txtTab2Frontage03Line2;
	private IntegerNumberText txtTab2Frontage04Line2;	
	private IntegerNumberText txtTab2Depth01Line2;
	private IntegerNumberText txtTab2Depth02Line2;
	private IntegerNumberText txtTab2Depth03Line2;
	private IntegerNumberText txtTab2Depth04Line2;
	
	private IntegerNumberText txtTab2Area01Line3;	
	private IntegerNumberText txtTab2Frontage01Line3;
	private IntegerNumberText txtTab2Frontage02Line3;
	private IntegerNumberText txtTab2Frontage03Line3;
	private IntegerNumberText txtTab2Frontage04Line3;	
	private IntegerNumberText txtTab2Depth01Line3;
	private IntegerNumberText txtTab2Depth02Line3;
	private IntegerNumberText txtTab2Depth03Line3;
	private IntegerNumberText txtTab2Depth04Line3;
	
	private IntegerNumberText txtTab2Area01Line4;	
	private IntegerNumberText txtTab2Frontage01Line4;
	private IntegerNumberText txtTab2Frontage02Line4;
	private IntegerNumberText txtTab2Frontage03Line4;
	private IntegerNumberText txtTab2Frontage04Line4;	
	private IntegerNumberText txtTab2Depth01Line4;
	private IntegerNumberText txtTab2Depth02Line4;
	private IntegerNumberText txtTab2Depth03Line4;
	private IntegerNumberText txtTab2Depth04Line4;
	
	private IntegerNumberText txtTab2Area01Line5;
	
	private IntegerNumberText txtTab2Area01Line6;
	
	private IntegerNumberText txtTab2Area01Line7;
	
	private BaseComboBox cbbTab2StateOfFloor;
	
	//Tab3
	/** */
	protected InspectTable m_tableTab3;
	/** */
	protected InspectTableModel m_dataTab3;
	
	/** テーブルヘッダーサイズ */
	private static String colHeadNmTab3[] = { "行", "＊", "申請届出日", "申請届出内容" };
	/** 名称一覧 ヘッダーサイズ */
	private int[] colHeadwidthTab3 = { 30, 20, 170, 340 };
	
	private BaseInputText txtTab3Line;
	private BaseCheckBox chkTab3Editable;
	private BaseDatePicker dtpTab3ApplicationDay;
	private BaseComboBox cbbTab3ApplicationContent;
	
	//Tab4
	/** */
	protected InspectTable m_tableTab4;
	/** */
	protected InspectTableModel m_dataTab4;
	
	/** テーブルヘッダーサイズ */
	private static String colHeadNmTab4[] = { "行", "整備主任者名", "証書番号", "選任日", "解任日", "仮"};
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private int[] colHeadwidthTab4 = { 50, 120, 120, 120, 120, 15 };
	
	private BaseInputText txtTab4Line;
	private CdInputSearchText txtTab4SeniorDevelopment;
	private BaseLabel lblTab4SeniorDevelopment;
	private BaseLabel lblTab4CertificateNumber;	
	private BaseDatePicker dtpTab4ElectionDay;
	private BaseDatePicker dtpTab4DismissalDay;
	private BaseCheckBox chkTab4Provisional;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//********************* Constructors **************************//
	public MaintenanceRegisterFrame(BaseFrame frame, String title) {
		super(frame, title);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD>Init Function</DD> <BR>
	 * 
	 * @param frame
	 *            </DL>
	 */
	public MaintenanceRegisterFrame(BaseFrame frame) {
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
	public MaintenanceRegisterFrame() {
		super();
		init();
	}
	
	//********************* Business Method **************************//
	private void init(){
		getBodyLeft();
		
		btnF1.setEnabled(true);
		btnF1.setText("<html><center><font size=-1>←Tab切替</font></center><center><font size=-1>F1(E)</font></center>");
		btnF1.setToolTipText("[F1]：Tab切替を変わります。");
		
		btnF2.setEnabled(true);
		btnF2.setText("<html><center><font size=-1>Tab切替→</font></center><center><font size=-1>F2(F)</font></center>");
		btnF2.setToolTipText("[F2]：Tab切替を変わります。");
		
		btnF3.setEnabled(false);
		btnF3.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF3.setToolTipText("");
		
		btnF4.setEnabled(true);
		btnF4.setText("<html><center><font size=-1>コード検索</font></center><center><font size=-1>F4(S)</font></center>");
		btnF4.setToolTipText("[F4]：コードを検索します。");
		
		btnF5.setEnabled(false);
		btnF5.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF5.setToolTipText("");
		
		btnF6.setEnabled(true);
		btnF6.setText("<html><center><font size=-1>削　除</font></center><center><font size=-1>F6(D)</font></center>");
		btnF6.setToolTipText("[F6]：データを削除します。");
		
		btnF7.setEnabled(false);
		btnF7.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF7.setToolTipText("");
		
		btnF8.setEnabled(true);
		btnF8.setText("<html><center><font size=-1>登　録</font></center><center><font size=-1>F8(A)</font></center>");
		btnF8.setToolTipText("[F8]：データを登録します。");
		
		btnF9.setEnabled(true);
		btnF9.setText("<html><center><font size=-1>終　了</font></center><center><font size=-1>F9(R)</font></center>");
		btnF9.setToolTipText("[F9]：終了します。");
		
		btnF10.setEnabled(false);
		btnF10.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF10.setToolTipText("");
		
		btnF11.setEnabled(true);
		btnF11.setText("<html><center><font size=-1>中　止</font></center><center><font size=-1>F11(C)</font></center>");
		btnF11.setToolTipText("");
		
		btnF12.setEnabled(false);
		btnF12.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF12.setToolTipText("");
		
	}
	
	/**
	 * Ham goi form tim kiem
	 * tim kiem ma so xac nhan
	 * 
	 */
	private void doSearchPC01()
	{
		SearchPC01Frame search = new SearchPC01Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtCertificationNumber.setText(searchData.getProPer1());
			loadData();
		}
	}
	
	//Search chi nhanh
	private void doSearchPG01()
	{
		SearchPG01Frame search = new SearchPG01Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtBranchAffilication.setText(searchData.getProPer1());
		}
	}
	
	//Search dia chi
	private void doSearchPG02ForBMan()
	{
		SearchPG02Frame search = new SearchPG02Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtBManAddressCode.setText(searchData.getProPer1());
			lblBManAddressName.setText(searchData.getProPer2());
		}
	}
	
	private void doSearchPG02ForBField()
	{
		SearchPG02Frame search = new SearchPG02Frame(getFrame());	
		search.setModal(true);
		
		search.pack();
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtBFieldAddressCode.setText(searchData.getProPer1());
			lblBFieldAddressName.setText(searchData.getProPer2());
		}
	}
	
	private void loadData()
	{
		try {
			//Get data tu bang PC01 dua len tren Form
			Pc01Service pc01Service = new Pc01ServiceImpl();
			Pc01Vo dataVo = pc01Service.getByKey(NumberUtils.getIntFromString(txtCertificationNumber.getText()));
			if(dataVo != null)
			{
				//Cer Number
				//Spec Number				
				//Affi Group
				
				cbbAffilicationGroup.getModel().setSelectedItem(dataVo.getC0103());
				cbbAffilicationGroup.repaint();
				//Branch Affi
				//?
				//Business Category				
				cbbBusinessCategory.getModel().setSelectedItem(dataVo.getC0105());
				cbbBusinessCategory.repaint();
				//Ninshou Bi, Haishi Bi
				
				//Dang lam do
				//Ji gyouu sha
				txtBManName.setText(dataVo.getC0180());
				txtBManRepreame.setText(dataVo.getC0181());
				txtBManPost.setText(dataVo.getC0182());
				txtBManAddressCode.setText(dataVo.getC0181());
				txtBManName.setText(dataVo.getC0180());
				txtBManName.setText(dataVo.getC0180());
				txtBManName.setText(dataVo.getC0180());
				txtBManName.setText(dataVo.getC0180());
				
				
				
				
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * This method is used to initialize first panel 
	 */
	private BasePanel initPnlFirst(){
		BasePanel pnlFirst = new BasePanel();
		pnlFirst.setBorder(FaceContants.TITLE_BORDER);
		
		int xPos = 15;
		int yPos = 10;
		int X_WIDTH_90 = 90;
		int X_WIDTH_82 = 82;
		int X_WIDTH_10 = 10;
		int X_WIDTH_104 = 104;
		int X_WIDTH_100 = 100;
		int X_WIDTH_58 = 58;
		int X_WIDTH_129 = 129;
		int X_WIDTH_72 = 72;
		int X_WIDTH_COMBO_39 = 39;
		int Y_HEIGHT_44 = 44;

		//Line1
		BaseLabel lbl1Line1 = new BaseLabel();
		lbl1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line1.setText("登　録");
		lbl1Line1.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_90, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl1Line1);
		
		xPos+=X_WIDTH_90 + 20;
		EditLabel lbl2Line1 = new EditLabel();
		lbl2Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line1.setText("認証番号");
		lbl2Line1.setBounds(xPos, yPos, X_WIDTH_82, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl2Line1);
		
		xPos+=X_WIDTH_82;
		txtCertificationNumber = new CdInputSearchText();
		txtCertificationNumber.setBounds(xPos, yPos, X_WIDTH_72, FaceContants.LABLE_HEIGHT);
		txtCertificationNumber.getFindButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearchPC01();
			}
		});
		pnlFirst.add(txtCertificationNumber);
		
		xPos+=X_WIDTH_72 + 10;
		EditLabel lbl3Line1 = new EditLabel();
		lbl3Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3Line1.setText("指定番号");
		lbl3Line1.setBounds(xPos, yPos, X_WIDTH_104, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl3Line1);
		
		xPos+=X_WIDTH_104;
		txtSpecificationNumber = new BaseInputText();
		txtSpecificationNumber.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(txtSpecificationNumber);
		
		BaseLabel lblPermission = new BaseLabel();
		lblPermission.setText("更新不可");
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermission.setBounds(862, yPos, 60, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lblPermission);
		
		//Line2
		xPos=15;
		int yPos86 = 86;
		//required1
		RequiredLabel requiredLabel = new RequiredLabel();
		requiredLabel.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel.setText("*");
		requiredLabel.setBounds(5, yPos86, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(requiredLabel);
	
		yPos+=FaceContants.LABLE_HEIGHT +10;	//start YPos of  Line2
		EditLabel lbl1Line2 = new EditLabel();		
		lbl1Line2.setText("<html>所属<br>部会</html>");
		lbl1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line2.setBounds(xPos, yPos, X_WIDTH_58, Y_HEIGHT_44);
		pnlFirst.add(lbl1Line2);
		
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		ComboService comService = new ComboServiceImpl();
		lstData = comService.getLstFromName("AREA", true);
		ArrayListComboBoxModel aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbAffilicationGroup = new BaseComboBox(aModel);
		cbbAffilicationGroup.setBounds(xPos, yPos86, X_WIDTH_58, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbAffilicationGroup);
		
		//Requried2
		xPos+=X_WIDTH_58;
		RequiredLabel requiredLabel2 = new RequiredLabel();
		requiredLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel2.setText("*");
		requiredLabel2.setBounds(xPos, yPos86, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(requiredLabel2);
		
		xPos+=X_WIDTH_10;
		EditLabel lbl2Line2 = new EditLabel();
		lbl2Line2.setText("所属支部");
		lbl2Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line2.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl2Line2.setBounds(xPos, yPos, X_WIDTH_129, 44);
		pnlFirst.add(lbl2Line2);
		
		txtBranchAffilication = new CdInputSearchText();
		txtBranchAffilication.setBounds(xPos, yPos86, X_WIDTH_58, FaceContants.LABLE_HEIGHT);
		txtBranchAffilication.getFindButton().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearchPG01();	
			}
		});
		
		pnlFirst.add(txtBranchAffilication);
		
		int xPosLine2Temp = xPos + X_WIDTH_58;
		BaseLabel lbl1Line4 = new BaseLabel();
		lbl1Line4.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line4.setBounds(xPosLine2Temp, yPos86, X_WIDTH_72-1, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl1Line4);
		
		xPos+=X_WIDTH_129;
		//required3
		RequiredLabel requiredLabel3 = new RequiredLabel();
		requiredLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel3.setText("*");
		requiredLabel3.setBounds(xPos, yPos86, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(requiredLabel3);
		
		xPos+=X_WIDTH_10;
		EditLabel lbl3Line2 = new EditLabel();
		lbl3Line2.setText("業態");
		//lbl3Line2.setText("<html><br>業態</html>");
		lbl3Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3Line2.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl3Line2.setBounds(xPos, yPos, X_WIDTH_58, 44);
		pnlFirst.add(lbl3Line2);
			
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("BSNS", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbBusinessCategory = new BaseComboBox(aModel);
		cbbBusinessCategory.setBounds(xPos, yPos86, X_WIDTH_58, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbBusinessCategory);
		
		//yPosLine3
		int yPosLine3 = yPos +FaceContants.LABLE_HEIGHT;
		xPos+=X_WIDTH_58;
		//required4
		RequiredLabel requiredLabel4 = new RequiredLabel();
		requiredLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel4.setText("*");
		requiredLabel4.setBounds(xPos, yPosLine3,X_WIDTH_10,FaceContants.LABLE_HEIGHT);
		pnlFirst.add(requiredLabel4);
		
		//required5
		RequiredLabel requiredLabel5 = new RequiredLabel();
		requiredLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel5.setText("*");
		requiredLabel5.setBounds(xPos, yPos86, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(requiredLabel5);
		
		//Line2
		xPos+=X_WIDTH_10;
		int X_WIDTH_lbl4Line2 = (X_WIDTH_72 * 2) + X_WIDTH_10 + (X_WIDTH_COMBO_39 * 8);
		EditLabel lbl4Line2 = new EditLabel();
		lbl4Line2.setText("認証日");
		lbl4Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4Line2.setBounds(xPos, yPos, X_WIDTH_lbl4Line2, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl4Line2);
		
		//su dung cho cac component last
		//int xPosLBL5Line2 = xPos + X_WIDTH_lbl4Line2;
		//Line3
		EditLabel lbl1Line3 = new EditLabel();
		lbl1Line3.setText("新規");
		lbl1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line3.setBounds(xPos, yPosLine3, X_WIDTH_72, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl1Line3);
		
		int xPosTempLine34 = xPos + X_WIDTH_72;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("NERA", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbNovelty01 = new BaseComboBox(aModel);
		cbbNovelty01.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbNovelty01);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("YEAR", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbNovelty02 = new BaseComboBox(aModel);
		cbbNovelty02.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbNovelty02);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("MONTH", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbNovelty03 = new BaseComboBox(aModel);
		cbbNovelty03.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbNovelty03);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("DAY", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbNovelty04 = new BaseComboBox(aModel);
		cbbNovelty04.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbNovelty04);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		RequiredLabel requiredLabel6 = new RequiredLabel();
		requiredLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel6.setText("*");
		requiredLabel6.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(requiredLabel6);
		
		xPosTempLine34+=X_WIDTH_10;
		EditLabel lbl2Line3 = new EditLabel();
		lbl2Line3.setText("普通");
		lbl2Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line3.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_72, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl2Line3);
				
		xPosTempLine34+=X_WIDTH_72;
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		lstData = comService.getLstFromName("NERA", true);
		cbbUsually01 = new BaseComboBox(aModel);
		cbbUsually01.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbUsually01);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("YEAR", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbUsually02 = new BaseComboBox(aModel);
		cbbUsually02.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbUsually02);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("MONTH", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbUsually03 = new BaseComboBox(aModel);
		cbbUsually03.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbUsually03);
		
		xPosTempLine34+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("DAY", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbUsually04 = new BaseComboBox(aModel);
		cbbUsually04.setBounds(xPosTempLine34, yPosLine3, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbUsually04);
		
		//Line4
		EditLabel lbl2Line4 = new EditLabel();
		lbl2Line4.setText("小型");
		lbl2Line4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line4.setBounds(xPos, yPos86, X_WIDTH_72, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl2Line4);
		
		xPos+=X_WIDTH_72;		
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("NERA", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbSmallSize01 = new BaseComboBox(aModel);
		cbbSmallSize01.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbSmallSize01);
		
		xPos+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("YEAR", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbSmallSize02 = new BaseComboBox(aModel);
		cbbSmallSize02.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbSmallSize02);
		
		xPos+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("MONTH", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbSmallSize03 = new BaseComboBox(aModel);
		cbbSmallSize03.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbSmallSize03);
		
		xPos+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("DAY", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);	
		cbbSmallSize04 = new BaseComboBox(aModel);
		cbbSmallSize04.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbSmallSize04);
		
		xPos+=X_WIDTH_10+X_WIDTH_COMBO_39;
		EditLabel lbl3Line4 = new EditLabel();
		lbl3Line4.setText("　軽　");
		lbl3Line4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3Line4.setBounds(xPos, yPos86, X_WIDTH_72, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(lbl3Line4);
		
		xPos+=X_WIDTH_72;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("NERA", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbLight01 = new BaseComboBox(aModel);
		cbbLight01.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbLight01);
		
		xPos+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("YEAR", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbLight02 = new BaseComboBox(aModel);
		cbbLight02.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbLight02);
		
		xPos+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("MONTH", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbLight03 = new BaseComboBox(aModel);
		cbbLight03.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbLight03);
		
		xPos+=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("DAY", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbLight04 = new BaseComboBox(aModel);
		cbbLight04.setBounds(xPos, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbLight04);
		
		int xPosForLastComps = xPosTempLine34 + X_WIDTH_COMBO_39 + 10;
		EditLabel lbl5Line2 = new EditLabel();
		lbl5Line2.setText("廃止日");
		lbl5Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5Line2.setVerticalAlignment(SwingConstants.BOTTOM);	
		lbl5Line2.setBounds(xPosForLastComps, yPos,X_WIDTH_COMBO_39 * 4 , 44);
		pnlFirst.add(lbl5Line2);
		
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("NERA", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbAbolitionDay01 = new BaseComboBox(aModel);
		cbbAbolitionDay01.setBounds(xPosForLastComps, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbAbolitionDay01);
		
		xPosForLastComps +=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("YEAR", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbAbolitionDay02 = new BaseComboBox(aModel);
		cbbAbolitionDay02.setBounds(xPosForLastComps, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbAbolitionDay02);
		
		xPosForLastComps +=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("MONTH", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbAbolitionDay03 = new BaseComboBox(aModel);
		cbbAbolitionDay03.setBounds(xPosForLastComps, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbAbolitionDay03);
		
		xPosForLastComps +=X_WIDTH_COMBO_39;
		lstData = new ArrayList<ComboObjectVo>();
		lstData = comService.getLstFromName("DAY", true);
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbAbolitionDay04 = new BaseComboBox(aModel);
		cbbAbolitionDay04.setBounds(xPosForLastComps, yPos86, X_WIDTH_COMBO_39, FaceContants.LABLE_HEIGHT);
		pnlFirst.add(cbbAbolitionDay04);
			
		return pnlFirst;	
	}
	/*
	 * This method is used to initialize second panel
	 */
	private BasePanel initPnlSecond(){
		BasePanel pnlSecond = new BasePanel();	
		
		//BorderFactory.createTitledBorder("事業者")
		pnlSecond.setBorder( new TitledBorder("事業者"));
		
		int xPos = 15;
		int yPos = 20;
		int xPosTemp = 0;
		int xPos1 = 0;
		
		//Line1
		int X_WIDTH_89 = 89;
		int X_WIDTH_570 = 570;
		int X_WIDTH_140 = 140;
		int X_WIDTH_10 = 10;
		int X_WIDTH_20 = 20;
		EditLabel lbl1Line1 = new EditLabel();
		lbl1Line1.setText("名　称");
		lbl1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl1Line1);
		
		xPos+=X_WIDTH_89;
		txtBManName = new BaseInputText();
		txtBManName.setBounds(xPos, yPos, X_WIDTH_570, FaceContants.LABLE_HEIGHT); // Chua tinh do dai cua width 542
		pnlSecond.add(txtBManName);
		
		xPos+=X_WIDTH_570; 
		xPosTemp = xPos;//Luu gia tri tam thoi o cuoi txt1Line1
		xPos1=xPos + 20;
		
		EditLabel lbl2Line1 = new EditLabel();
		lbl2Line1.setText("代表者名");
		lbl2Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line1.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl2Line1);
		
		xPos1+=X_WIDTH_89;
		txtBManRepreame = new BaseInputText();
		txtBManRepreame.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(txtBManRepreame);
		
		//reset xPos1
		xPos1 = xPos + 20;
		//Line2
		xPos=15;
		yPos+=FaceContants.LABLE_HEIGHT;
		
		RequiredLabel requiredLabel_7 = new RequiredLabel();
		requiredLabel_7.setText("*");
		requiredLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_7.setBounds(5, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(requiredLabel_7);
		
		EditLabel lbl1Line2 = new EditLabel();
		lbl1Line2.setText("郵便番号");
		lbl1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line2.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl1Line2);
		
		xPos+=X_WIDTH_89;
		BaseLabel lbl2Line2 = new BaseLabel();
		lbl2Line2.setText("〒");
		lbl2Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line2.setBounds(xPos, yPos, X_WIDTH_20, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl2Line2);
		
		xPos+=X_WIDTH_20;
		txtBManPost = new BaseInputText();
		txtBManPost.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(txtBManPost);
		
		
		RequiredLabel requiredLabel_8 = new RequiredLabel();
		requiredLabel_8.setText("*");
		requiredLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_8.setBounds(xPosTemp+10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(requiredLabel_8);
		
		EditLabel lbl3Line2 = new EditLabel();
		lbl3Line2.setText("住所コード");
		lbl3Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3Line2.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl3Line2);
		
		xPos1+=X_WIDTH_89;
		txtBManAddressCode = new CdInputSearchText();
		txtBManAddressCode.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		txtBManAddressCode.getFindButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearchPG02ForBMan();
			}
		});
		pnlSecond.add(txtBManAddressCode);
			
		xPos1-=X_WIDTH_89;
		//Line3
		xPos=15;
		yPos+=FaceContants.LABLE_HEIGHT;
		EditLabel lbl1Line3 = new EditLabel();
		lbl1Line3.setText("住　所");
		lbl1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line3.setBounds(xPos, yPos, X_WIDTH_89, 44);
		pnlSecond.add(lbl1Line3);
		
		xPos+=X_WIDTH_89;
		int xPosTemForLine4 = xPos; //Su dung cho txt1 line4
		lblBManAddressName = new BaseLabel();
		lblBManAddressName.setBounds(xPos, yPos,X_WIDTH_570, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lblBManAddressName);
		
		RequiredLabel requiredLabel_9 = new RequiredLabel();
		requiredLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_9.setText("*");
		requiredLabel_9.setBounds(xPosTemp + 10,yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(requiredLabel_9);

		EditLabel lbl2Line3 = new EditLabel();
		lbl2Line3.setText("TEL");
		lbl2Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line3.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl2Line3);
		
		xPos1+=X_WIDTH_89;
		txtBManTel = new BaseInputText();
		txtBManTel.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(txtBManTel);
		
		xPos1-=X_WIDTH_89;
		//Line4 
		yPos+=FaceContants.LABLE_HEIGHT;
		txtBManAddress = new BaseInputText();
		txtBManAddress.setBounds(xPosTemForLine4, yPos, X_WIDTH_570, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(txtBManAddress);
		
		EditLabel lbl2Line4 = new EditLabel();
		lbl2Line4.setText("FAX");
		lbl2Line4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line4.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(lbl2Line4);
		
		xPos1+=X_WIDTH_89;
		txtBManFax = new BaseInputText();
		txtBManFax.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		pnlSecond.add(txtBManFax);
		
		return pnlSecond;	
	}
	/*
	 * This method is used to initialize third panel
	 */
	private BasePanel initPnlThird(){
		BasePanel pnlThird = new BasePanel();	
		pnlThird.setBorder(BorderFactory.createTitledBorder("事業場"));
		
		int xPos = 15;
		int yPos = 20;
		int xPosTemp = 0;
		int xPos1 = 0;		
		
		//Line1
		int X_WIDTH_89 = 89;
		int X_WIDTH_570 = 570;
		int X_WIDTH_140 = 140;
		int X_WIDTH_10 = 10;
		int X_WIDTH_20 = 20;
		int X_WIDTH_231 = 231;
		
		EditLabel lbl1Line1 = new EditLabel();
		lbl1Line1.setText("名　称");
		lbl1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl1Line1);
		
		xPos+=X_WIDTH_89;
		int X_WITDTH_TXT1Line1 = X_WIDTH_570-231;
		txtBFieldName = new BaseInputText();
		txtBFieldName.setBounds(xPos, yPos,X_WITDTH_TXT1Line1, FaceContants.LABLE_HEIGHT); // Chua tinh do dai cua width 542
		pnlThird.add(txtBFieldName);
		
		//xPos+= X_WIDTH_570-231;
		BaseInputText txt2Line2 = new BaseInputText("text 2");
		txt2Line2.setBounds(xPos + X_WITDTH_TXT1Line1, yPos,X_WIDTH_231 , FaceContants.LABLE_HEIGHT);
		pnlThird.add(txt2Line2);
		
		xPos+=X_WIDTH_570; 
		xPosTemp = xPos;//Luu gia tri tam thoi o cuoi txt1Line1
		xPos1=xPos + 20;

		//Line2
		xPos=15;
		yPos+=FaceContants.LABLE_HEIGHT;
		
		RequiredLabel requiredLabel_7 = new RequiredLabel();
		requiredLabel_7.setText("*");
		requiredLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_7.setBounds(5, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlThird.add(requiredLabel_7);
		
		EditLabel lbl1Line2 = new EditLabel();
		lbl1Line2.setText("郵便番号");
		lbl1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line2.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl1Line2);
		
		xPos+=X_WIDTH_89;
		BaseLabel lbl2Line2 = new BaseLabel();
		lbl2Line2.setText("〒");
		lbl2Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line2.setBounds(xPos, yPos, X_WIDTH_20, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl2Line2);
		
		xPos+=X_WIDTH_20;
		txtBFieldPost = new BaseInputText();
		txtBFieldPost.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(txtBFieldPost);
		
		int xPosTempForLine2 = 344;
		RequiredLabel requiredLabel_12 = new RequiredLabel();
		requiredLabel_12.setText("*");
		requiredLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_12.setBounds(xPosTempForLine2, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlThird.add(requiredLabel_12);
		
		xPosTempForLine2+=X_WIDTH_10;
		EditLabel lbl3Line2 = new EditLabel();
		lbl3Line2.setText("名称カナ");
		lbl3Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3Line2.setBounds(xPosTempForLine2, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl3Line2);
		
		xPosTempForLine2+=X_WIDTH_89;
		txtBFieldKana = new BaseInputText();
		txtBFieldKana.setBounds(xPosTempForLine2, yPos,X_WIDTH_231 , FaceContants.LABLE_HEIGHT);
		pnlThird.add(txtBFieldKana);
		
		RequiredLabel requiredLabel_8 = new RequiredLabel();
		requiredLabel_8.setText("*");
		requiredLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_8.setBounds(xPosTemp+10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlThird.add(requiredLabel_8);
		
		EditLabel lbl4Line2 = new EditLabel();
		lbl4Line2.setText("住所コード");
		lbl4Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4Line2.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl4Line2);
		
		xPos1+=X_WIDTH_89;
		txtBFieldAddressCode = new CdInputSearchText();
		txtBFieldAddressCode.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		txtBFieldAddressCode.getFindButton().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearchPG02ForBField();
			}
		});
		pnlThird.add(txtBFieldAddressCode);
			
		xPos1-=X_WIDTH_89;
		//Line3
		xPos=15;
		yPos+=FaceContants.LABLE_HEIGHT;
		EditLabel lbl1Line3 = new EditLabel();
		lbl1Line3.setText("住　所");
		lbl1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line3.setBounds(xPos, yPos, X_WIDTH_89, 44);
		pnlThird.add(lbl1Line3);
		
		xPos+=X_WIDTH_89;
		int xPosTemForLine4 = xPos; //Su dung cho txt1 line4
		lblBFieldAddressName = new BaseLabel();
		lblBFieldAddressName.setBounds(xPos, yPos,X_WIDTH_570, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lblBFieldAddressName);
		
		RequiredLabel requiredLabel_9 = new RequiredLabel();
		requiredLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_9.setText("*");
		requiredLabel_9.setBounds(xPosTemp + 10,yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlThird.add(requiredLabel_9);

		EditLabel lbl2Line3 = new EditLabel();
		lbl2Line3.setText("TEL");
		lbl2Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line3.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl2Line3);
		
		xPos1+=X_WIDTH_89;
		txtBFieldTel = new BaseInputText();
		txtBFieldTel.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		pnlThird.add(txtBFieldTel);
		
		xPos1-=X_WIDTH_89;
		//Line4 
		yPos+=FaceContants.LABLE_HEIGHT;
		txtBFieldAddress = new BaseInputText();
		txtBFieldAddress.setBounds(xPosTemForLine4, yPos, X_WIDTH_570, FaceContants.LABLE_HEIGHT);
		pnlThird.add(txtBFieldAddress);
		
		RequiredLabel requiredLabel_11 = new RequiredLabel();
		requiredLabel_11.setText("*");
		requiredLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		requiredLabel_11.setBounds(xPosTemp+10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlThird.add(requiredLabel_11);
		
		EditLabel lbl2Line4 = new EditLabel();
		lbl2Line4.setText("FAX");
		lbl2Line4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line4.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlThird.add(lbl2Line4);
		
		xPos1+=X_WIDTH_89;
		txtBFieldFax = new BaseInputText();
		txtBFieldFax.setBounds(xPos1, yPos, X_WIDTH_140, FaceContants.LABLE_HEIGHT);
		pnlThird.add(txtBFieldFax);
		
		return pnlThird;	
	}
	/*
	 * This method is used to initialize four panel
	 */
	private BasePanel initPnlFourth(){
		BasePanel pnlFourth = new BasePanel();
//		pnlFourth.setBorder(FaceContants.TITLE_BORDER);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(FaceContants.TITLE_BORDER);
		
		JComponent panel1 = getTabPanel1();
		tabbedPane.addTab("認証状況１", null, panel1, "認証状況１");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = getTabPanel2();
		tabbedPane.addTab("認証状況２", null, panel2, "認証状況２");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = getTabPanel3();
		tabbedPane.addTab("申請届出状況", null, panel3, "申請届出状況");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = getTabPanel4();
		tabbedPane.addTab("整備主任者変更状況", null, panel4, "整備主任者変更状況");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		tabbedPane.setBounds(0, 0, 934, 396);		
		pnlFourth.add(tabbedPane);	
		
		return pnlFourth;	
	}
	
	private BasePanel getTabPanel1()
	{
		BasePanel pnlTab1 = new BasePanel();
		pnlTab1.setBorder(FaceContants.TITLE_BORDER);
		
		int xPos = 7;
		int yPos = 15;
		int X_WIDTH_89 = 89;
		int X_WIDTH_85 = 85;
		
		//Line1 
		EditLabel lbl1Line1 = new EditLabel();
		lbl1Line1.setText("自動車工");
		lbl1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl1Line1);
		
		xPos+=X_WIDTH_89;
		EditLabel lbl2Line1 = new EditLabel();
		lbl2Line1.setText("検査工");
		lbl2Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl2Line1);
		
		xPos+=X_WIDTH_89;
		EditLabel lbl3Line1 = new EditLabel();
		lbl3Line1.setText("板金工");
		lbl3Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl3Line1);
		
		xPos+=X_WIDTH_89;
		EditLabel lbl4Line1 = new EditLabel();
		lbl4Line1.setText("その他");
		lbl4Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl4Line1);
		
		xPos+=X_WIDTH_89;
		EditLabel lbl5Line1 = new EditLabel();
		lbl5Line1.setText("他工");
		lbl5Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl5Line1);
		
		xPos+=X_WIDTH_89;
		EditLabel lbl6Line1 = new EditLabel();
		lbl6Line1.setText("塗装工");
		lbl6Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl6Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl6Line1);
		
		xPos+=X_WIDTH_89;
		BaseLabel lbl7Line1 = new BaseLabel();
		lbl7Line1.setText("従業員計");
		lbl7Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl7Line1.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl7Line1);
		
		int xPos1 = 658;
		EditLabel lbl8Line1 = new EditLabel();
		lbl8Line1.setText("整備士２級");
		lbl8Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl8Line1.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl8Line1);
		
		xPos1+=X_WIDTH_89;
		EditLabel lbl9Line1 = new EditLabel();
		lbl9Line1.setText("整備士３級");
		lbl9Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl9Line1.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl9Line1);
		
		xPos1+=X_WIDTH_89;
		BaseLabel lbl10Line1 = new BaseLabel();
		lbl10Line1.setText("整備士計");
		lbl10Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl10Line1.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lbl10Line1);
		
		//reset xPos1 , xPos
		xPos = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		//Line2
		txtTab1CarPlantEmployee = new BaseInputText();
		txtTab1CarPlantEmployee.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1CarPlantEmployee);
		
		xPos+=X_WIDTH_89;
		txtTab1TestPlantEmployee = new BaseInputText();
		txtTab1TestPlantEmployee.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1TestPlantEmployee);
		
		xPos+=X_WIDTH_89;
		txtTab1SheetMetalPlantEmployee = new BaseInputText();
		txtTab1SheetMetalPlantEmployee.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1SheetMetalPlantEmployee);
		
		xPos+=X_WIDTH_89;
		txtTab1PaintPlantEmployee = new BaseInputText();
		txtTab1PaintPlantEmployee.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1PaintPlantEmployee);
		
		xPos+=X_WIDTH_89;
		txtTab1OtherPlantsEmployee = new BaseInputText();
		txtTab1OtherPlantsEmployee.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1OtherPlantsEmployee);
		
		xPos+=X_WIDTH_89;
		txtTab1Otherwise = new BaseInputText();
		txtTab1Otherwise.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1Otherwise);
		
		xPos+=X_WIDTH_89;
		lblTab1TotalEmployee = new BaseLabel();
		lblTab1TotalEmployee.setBounds(xPos, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTab1TotalEmployee);
		
		txtTab1MechanicLevel2 = new BaseInputText();
		txtTab1MechanicLevel2.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1MechanicLevel2);
		
		xPos1+=X_WIDTH_89;
		txtTab1MechanicLevel3 = new BaseInputText();
		txtTab1MechanicLevel3.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1MechanicLevel3);
		
		xPos1+=X_WIDTH_89;
		lblTab1MeterMechanic = new BaseLabel();
		txtTab1MechanicLevel3.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(txtTab1MechanicLevel3);
		
		//Phan them moi 17/09
		//Line 00 Header
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT*2;
		// ***
		xPos1+=X_WIDTH_89;
		EditLabel lblNoLimit = new EditLabel();
		lblNoLimit.setText("限定なし");
		lblNoLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoLimit.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblNoLimit);
		
		xPos1+=X_WIDTH_85;
		chkTab1Motive = new BaseCheckBox();
		chkTab1Motive.setText("原動");
		chkTab1Motive.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Motive.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Motive.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Motive.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Motive.setBorderPainted(true);
		chkTab1Motive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Motive.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Motive.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Motive.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Motive);
		
		xPos1+=X_WIDTH_85;
		chkTab1Power = new BaseCheckBox();
		chkTab1Power.setText("動力");
		chkTab1Power.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Power.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Power.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Power.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Power.setBorderPainted(true);
		chkTab1Power.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Power.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Power.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Power.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Power);
		
		xPos1+=X_WIDTH_85;
		chkTab1Travel = new BaseCheckBox();
		chkTab1Travel.setText("走行");
		chkTab1Travel.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Travel.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Travel.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Travel.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Travel.setBorderPainted(true);
		chkTab1Travel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Travel.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Travel.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Travel.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Travel);
		
		xPos1+=X_WIDTH_85;
		chkTab1Control = new BaseCheckBox();
		chkTab1Control.setText("操縦");
		chkTab1Control.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Control.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Control.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Control.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Control.setBorderPainted(true);
		chkTab1Control.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Control.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Control.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Control.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Control);
				
		xPos1+=X_WIDTH_85;
		chkTab1Braking = new BaseCheckBox();
		chkTab1Braking.setText("制動");
		chkTab1Braking.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Braking.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Braking.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Braking.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Braking.setBorderPainted(true);
		chkTab1Braking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Braking.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Braking.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Braking.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Braking);
		
		xPos1+=X_WIDTH_85;
		chkTab1Buffered = new BaseCheckBox();
		chkTab1Buffered.setText("緩衝");
		chkTab1Buffered.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Buffered.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Buffered.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Buffered.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Buffered.setBorderPainted(true);
		chkTab1Buffered.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Buffered.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Buffered.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Buffered.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Buffered);
		
		xPos1+=X_WIDTH_85;
		chkTab1Consolidation = new BaseCheckBox();
		chkTab1Consolidation.setText("連結");
		chkTab1Consolidation.setHorizontalAlignment(SwingConstants.CENTER);
		chkTab1Consolidation.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		chkTab1Consolidation.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		chkTab1Consolidation.setBorder(FaceContants.TITLE_BORDER);
		chkTab1Consolidation.setBorderPainted(true);
		chkTab1Consolidation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Do something
			}
		});
		
		chkTab1Consolidation.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				chkTab1Consolidation.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				chkTab1Consolidation.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
			}
		});
		pnlTab1.add(chkTab1Consolidation);
		
		List<ComboObjectVo> lstData;
		ArrayListComboBoxModel aModel;
		
		//*******************
		//Line 01
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader01 = new EditLabel();
		lblHeader01.setText("普大");
		lblHeader01.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader01.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader01);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit01 = new BaseComboBox(aModel);
		cbbTab1NoLimit01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit01);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive01 = new BaseLabel();
		lblMotive01.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive01);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower01 = new BaseLabel();
		lblPower01.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower01);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel01 = new BaseLabel();
		lblTravel01.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel01);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl01 = new BaseLabel();
		lblControl01.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl01);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking01 = new BaseLabel();
		lblBraking01.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking01);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered01 = new BaseLabel();
		lblBuffered01.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered01);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation01 = new BaseLabel();
		lblConsolidation01.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation01.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation01);
		
		//*******************
		//Line 02
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader02 = new EditLabel();
		lblHeader02.setText("普中");
		lblHeader02.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader02.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader02);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit02 = new BaseComboBox(aModel);
		cbbTab1NoLimit02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit02);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive02 = new BaseLabel();
		lblMotive02.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive02);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower02 = new BaseLabel();
		lblPower02.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower02);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel02 = new BaseLabel();
		lblTravel02.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel02);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl02 = new BaseLabel();
		lblControl02.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl02);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking02 = new BaseLabel();
		lblBraking02.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking02);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered02 = new BaseLabel();
		lblBuffered02.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered02);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation02 = new BaseLabel();
		lblConsolidation02.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation02.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation02);
		
		//*******************
		//Line 03
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader03 = new EditLabel();
		lblHeader03.setText("普小");
		lblHeader03.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader03.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader03);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit03 = new BaseComboBox(aModel);
		cbbTab1NoLimit03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit03);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive03 = new BaseLabel();
		lblMotive03.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive03);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower03 = new BaseLabel();
		lblPower03.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower03);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel03 = new BaseLabel();
		lblTravel03.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel03);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl03 = new BaseLabel();
		lblControl03.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl03);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking03 = new BaseLabel();
		lblBraking03.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking03);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered03 = new BaseLabel();
		lblBuffered03.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered03);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation03 = new BaseLabel();
		lblConsolidation03.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation03.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation03);
		
		//*******************
		//Line 04
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader04 = new EditLabel();
		lblHeader04.setText("普乗");
		lblHeader04.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader04.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader04);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit04 = new BaseComboBox(aModel);
		cbbTab1NoLimit04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit04);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive04 = new BaseLabel();
		lblMotive04.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive04);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower04 = new BaseLabel();
		lblPower04.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower04);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel04 = new BaseLabel();
		lblTravel04.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel04);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl04 = new BaseLabel();
		lblControl04.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl04);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking04 = new BaseLabel();
		lblBraking04.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking04);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered04 = new BaseLabel();
		lblBuffered04.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered04);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation04 = new BaseLabel();
		lblConsolidation04.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation04.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation04);
		
		//*******************
		//Line 05
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader05 = new EditLabel();
		lblHeader05.setText("大特");
		lblHeader05.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader05.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader05);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit05 = new BaseComboBox(aModel);
		cbbTab1NoLimit05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit05);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive05 = new BaseLabel();
		lblMotive05.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive05);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower05 = new BaseLabel();
		lblPower05.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower05);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel05 = new BaseLabel();
		lblTravel05.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel05);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl05 = new BaseLabel();
		lblControl05.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl05);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking05 = new BaseLabel();
		lblBraking05.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking05);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered05 = new BaseLabel();
		lblBuffered05.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered05);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation05 = new BaseLabel();
		lblConsolidation05.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation05.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation05);
		
		//*******************
		//Line 06
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader06 = new EditLabel();
		lblHeader06.setText("小四");
		lblHeader06.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader06.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader06);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit06 = new BaseComboBox(aModel);
		cbbTab1NoLimit06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit06);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive06 = new BaseLabel();
		lblMotive06.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive06);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower06 = new BaseLabel();
		lblPower06.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower06);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel06 = new BaseLabel();
		lblTravel06.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel06);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl06 = new BaseLabel();
		lblControl06.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl06);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking06 = new BaseLabel();
		lblBraking06.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking06);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered06 = new BaseLabel();
		lblBuffered06.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered06);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation06 = new BaseLabel();
		lblConsolidation06.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation06.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation06);
		
		//*******************
		//Line 07
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader07 = new EditLabel();
		lblHeader07.setText("小三");
		lblHeader07.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader07.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader07);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit07 = new BaseComboBox(aModel);
		cbbTab1NoLimit07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit07);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive07 = new BaseLabel();
		lblMotive07.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive07);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower07 = new BaseLabel();
		lblPower07.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower07);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel07 = new BaseLabel();
		lblTravel07.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel07);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl07 = new BaseLabel();
		lblControl07.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl07);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking07 = new BaseLabel();
		lblBraking07.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking07);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered07 = new BaseLabel();
		lblBuffered07.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered07);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation07 = new BaseLabel();
		lblConsolidation07.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation07.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation07);
		
		//*******************
		//Line 08
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader08 = new EditLabel();
		lblHeader08.setText("小二");
		lblHeader08.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader08.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader08);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit08 = new BaseComboBox(aModel);
		cbbTab1NoLimit08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit08);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive08 = new BaseLabel();
		lblMotive08.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive08);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower08 = new BaseLabel();
		lblPower08.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower08);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel08 = new BaseLabel();
		lblTravel08.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel08);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl08 = new BaseLabel();
		lblControl08.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl08);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking08 = new BaseLabel();
		lblBraking08.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking08);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered08 = new BaseLabel();
		lblBuffered08.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered08);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation08 = new BaseLabel();
		lblConsolidation08.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation08.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation08);
		
		//*******************
		//Line 09
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader09 = new EditLabel();
		lblHeader09.setText("軽");
		lblHeader09.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader09.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader09);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit09 = new BaseComboBox(aModel);
		cbbTab1NoLimit09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit09);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblMotive09 = new BaseLabel();
		lblMotive09.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotive09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblMotive09);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblPower09 = new BaseLabel();
		lblPower09.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblPower09);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblTravel09 = new BaseLabel();
		lblTravel09.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravel09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblTravel09);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblControl09 = new BaseLabel();
		lblControl09.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblControl09);
				
		xPos1+=X_WIDTH_85;
		BaseLabel lblBraking09 = new BaseLabel();
		lblBraking09.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraking09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBraking09);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblBuffered09 = new BaseLabel();
		lblBuffered09.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuffered09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBuffered09);
		
		xPos1+=X_WIDTH_85;
		BaseLabel lblConsolidation09 = new BaseLabel();
		lblConsolidation09.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsolidation09.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblConsolidation09);
		
		//*******************
		//Line 10
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader10 = new EditLabel();
		lblHeader10.setText("普通");
		lblHeader10.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader10.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader10);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit10 = new BaseComboBox(aModel);
		cbbTab1NoLimit10.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit10);
		
		//*******************
		//Line 11
		xPos1 = 7;
		yPos+=FaceContants.LABLE_HEIGHT;
		// ***
		//Label row
		EditLabel lblHeader11 = new EditLabel();
		lblHeader11.setText("限定");
		lblHeader11.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader11.setBounds(xPos1, yPos, X_WIDTH_89, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblHeader11);
		
		//Combobox
		xPos1+=X_WIDTH_89;		
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit11 = new BaseComboBox(aModel);
		cbbTab1NoLimit11.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit11);
		
		
		//Label row
		xPos1+=(int)(X_WIDTH_85*4.5)+1;
		EditLabel lblBusinessScope = new EditLabel();
		lblBusinessScope.setText("業務範囲");
		lblBusinessScope.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusinessScope.setBounds(xPos1, yPos, X_WIDTH_85, FaceContants.LABLE_HEIGHT);
		pnlTab1.add(lblBusinessScope);
		//Combobox
		xPos1+=X_WIDTH_85;
		lstData = new ArrayList<ComboObjectVo>();
		aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		cbbTab1NoLimit11 = new BaseComboBox(aModel);
		cbbTab1NoLimit11.setBounds(xPos1, yPos, (int)(X_WIDTH_85*2.5), FaceContants.LABLE_HEIGHT);
		pnlTab1.add(cbbTab1NoLimit11);
		
		return pnlTab1;
	}
	
	private BasePanel getTabPanel2()
	{
		BasePanel pnlTab2 = new BasePanel();
		pnlTab2.setBorder(FaceContants.TITLE_BORDER);
		
		int xPos = 7;
		int yPos = 15;
		int iGap5 = 5;
		int iGap20 = 20;
		int X_WIDTH_70 = 70;
		int X_WIDTH_100 = 100;
		int X_WIDTH_120 = 120;
		int X_WIDTH_400 = 295;
		
		//Line0
		xPos += 120;
		BaseLabel lbl1Line0 = new BaseLabel();
		lbl1Line0.setText("面積㎡");
		lbl1Line0.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line0.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line0);
		
		xPos+= X_WIDTH_100 + iGap20;
		BaseLabel lbl2Line0 = new BaseLabel();
		lbl2Line0.setText("間口ｍ");
		lbl2Line0.setHorizontalAlignment(SwingConstants.LEFT);
		lbl2Line0.setBounds(xPos, yPos, X_WIDTH_400, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl2Line0);
		
		xPos+= X_WIDTH_400 + iGap20;
		BaseLabel lbl3Line0 = new BaseLabel();
		lbl3Line0.setText("奥行");
		lbl3Line0.setHorizontalAlignment(SwingConstants.LEFT);
		lbl3Line0.setBounds(xPos, yPos, X_WIDTH_400, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl3Line0);
		
		//Line1		
		yPos += FaceContants.LABLE_HEIGHT;
		xPos = 7;
		EditLabel lbl1Line1 = new EditLabel();
		lbl1Line1.setText("車両整備");
		lbl1Line1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line1);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line1 = new IntegerNumberText();
		txtTab2Area01Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line1.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line1);
		
		xPos+= X_WIDTH_100 + iGap20;
		txtTab2Depth01Line1 = new IntegerNumberText();
		txtTab2Depth01Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth01Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth01Line1);
	
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth02Line1 = new IntegerNumberText();
		txtTab2Depth02Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth02Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth02Line1);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth03Line1 = new IntegerNumberText();
		txtTab2Depth03Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth03Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth03Line1);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth04Line1 = new IntegerNumberText();
		txtTab2Depth04Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth04Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth04Line1);
		
		//Frontage
		xPos+= X_WIDTH_70 + iGap20;
		txtTab2Frontage01Line1 = new IntegerNumberText();
		txtTab2Frontage01Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage01Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage01Line1);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage02Line1 = new IntegerNumberText();
		txtTab2Frontage02Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage02Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage02Line1);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage03Line1 = new IntegerNumberText();
		txtTab2Frontage03Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage03Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage03Line1);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage04Line1 = new IntegerNumberText();
		txtTab2Frontage04Line1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage04Line1.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage04Line1);
		
		//Line2
		yPos += FaceContants.LABLE_HEIGHT;
		xPos = 7;
		EditLabel lbl1Line2 = new EditLabel();
		lbl1Line2.setText("部品整備");
		lbl1Line2.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line2.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line2);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line2 = new IntegerNumberText();
		txtTab2Area01Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line2.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line2);
		
		xPos+= X_WIDTH_100 + iGap20;
		txtTab2Depth01Line2 = new IntegerNumberText();
		txtTab2Depth01Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth01Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth01Line2);
	
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth02Line2 = new IntegerNumberText();
		txtTab2Depth02Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth02Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth02Line2);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth03Line2 = new IntegerNumberText();
		txtTab2Depth03Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth03Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth03Line2);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth04Line2 = new IntegerNumberText();
		txtTab2Depth04Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth04Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth04Line2);
		
		
		//Frontage
		xPos+= X_WIDTH_70 + iGap20;
		txtTab2Frontage01Line2 = new IntegerNumberText();
		txtTab2Frontage01Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage01Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage01Line2);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage02Line2 = new IntegerNumberText();
		txtTab2Frontage02Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage02Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage02Line2);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage03Line2 = new IntegerNumberText();
		txtTab2Frontage03Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage03Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage03Line2);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage04Line2 = new IntegerNumberText();
		txtTab2Frontage04Line2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage04Line2.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage04Line2);
		
		//Line3
		yPos += FaceContants.LABLE_HEIGHT;
		xPos = 7;
		EditLabel lbl1Line3 = new EditLabel();
		lbl1Line3.setText("点　　検");
		lbl1Line3.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line3.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line3);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line3 = new IntegerNumberText();
		txtTab2Area01Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line3.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line3);
		
		xPos+= X_WIDTH_100 + iGap20;
		txtTab2Depth01Line3 = new IntegerNumberText();
		txtTab2Depth01Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth01Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);		
		pnlTab2.add(txtTab2Depth01Line3);
	
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth02Line3 = new IntegerNumberText();
		txtTab2Depth02Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth02Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth02Line3);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth03Line3 = new IntegerNumberText();
		txtTab2Depth03Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth03Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth03Line3);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth04Line3 = new IntegerNumberText();
		txtTab2Depth04Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth04Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth04Line3);
		
		
		//Frontage
		xPos+= X_WIDTH_70 + iGap20;
		txtTab2Frontage01Line3 = new IntegerNumberText();
		txtTab2Frontage01Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage01Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage01Line3);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage02Line3 = new IntegerNumberText();
		txtTab2Frontage02Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage02Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage02Line3);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage03Line3 = new IntegerNumberText();
		txtTab2Frontage03Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage03Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage03Line3);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage04Line3 = new IntegerNumberText();
		txtTab2Frontage04Line3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage04Line3.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage04Line3);
		
		
		//Line4
		yPos += FaceContants.LABLE_HEIGHT;
		xPos = 7;
		EditLabel lbl1Line4 = new EditLabel();
		lbl1Line4.setText("車両置場");
		lbl1Line4.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line4.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line4);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line4 = new IntegerNumberText();
		txtTab2Area01Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line4.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line4);
		
		xPos+= X_WIDTH_100 + iGap20;
		txtTab2Depth01Line4 = new IntegerNumberText();
		txtTab2Depth01Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth01Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth01Line4);
	
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth02Line4 = new IntegerNumberText();
		txtTab2Depth02Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth02Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth02Line4);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth03Line4 = new IntegerNumberText();
		txtTab2Depth03Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth03Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth03Line4);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Depth04Line4 = new IntegerNumberText();
		txtTab2Depth04Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Depth04Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Depth04Line4);

		//Frontage
		xPos+= X_WIDTH_70 + iGap20;
		txtTab2Frontage01Line4 = new IntegerNumberText();
		txtTab2Frontage01Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage01Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage01Line4);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage02Line4 = new IntegerNumberText();
		txtTab2Frontage02Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage02Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage02Line4);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage03Line4 = new IntegerNumberText();
		txtTab2Frontage03Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage03Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage03Line4);
		
		xPos+= X_WIDTH_70 + iGap5;
		txtTab2Frontage04Line4 = new IntegerNumberText();
		txtTab2Frontage04Line4.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Frontage04Line4.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Frontage04Line4);
		
		//Line5
		yPos += FaceContants.LABLE_HEIGHT;
		xPos = 7;
		EditLabel lbl1Line5 = new EditLabel();
		lbl1Line5.setText("その他");
		lbl1Line5.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line5.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line5);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line5 = new IntegerNumberText();
		txtTab2Area01Line5.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line5.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line5);
		
		//Line6
		yPos += FaceContants.LABLE_HEIGHT;
		xPos = 7;
		EditLabel lbl1Line6 = new EditLabel();
		lbl1Line6.setText("総敷地");
		lbl1Line6.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line6.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line6);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line6 = new IntegerNumberText();
		txtTab2Area01Line6.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line6.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line6);
				
		//Line7
		yPos += FaceContants.LABLE_HEIGHT * 2;
		xPos = 7;
		EditLabel lbl1Line7 = new EditLabel();
		lbl1Line7.setText("総敷地");
		lbl1Line7.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1Line7.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl1Line7);
		
		xPos+= X_WIDTH_120;
		txtTab2Area01Line7 = new IntegerNumberText();
		txtTab2Area01Line7.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab2Area01Line7.setBounds(xPos, yPos, X_WIDTH_100, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(txtTab2Area01Line7);
		
		xPos+= X_WIDTH_100 + iGap20;
		EditLabel lbl2Line7 = new EditLabel();
		lbl2Line7.setText("床面の状態");
		lbl2Line7.setHorizontalAlignment(SwingConstants.LEFT);
		lbl2Line7.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(lbl2Line7);
	
		xPos+= X_WIDTH_100;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		ArrayListComboBoxModel aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE);
		cbbTab2StateOfFloor = new BaseComboBox(aModel);
		cbbTab2StateOfFloor.setBounds(xPos, yPos, X_WIDTH_100 * 2, FaceContants.LABLE_HEIGHT);
		pnlTab2.add(cbbTab2StateOfFloor);
		
		return pnlTab2;
	}
	
	private BasePanel getTabPanel3()
	{
		int xPos = 15;
		int yPos = 15;
		int X_WIDTH_10 = 10;
		int X_WIDTH_30 = 30;
		int X_WIDTH_70 = 70;
		int X_WIDTH_120 = 120;
		int X_WIDTH_150 = 180;
		int X_WIDTH_550 = 550;
		
		
		BasePanel pnlTab3 = new BasePanel();		
		getTableTab3();
		BaseScrollPane psTable = new BaseScrollPane();
		psTable.setSize(X_WIDTH_550, 300);
		psTable.getViewport().add(m_tableTab3);
		psTable.setLocation(xPos, yPos);
		pnlTab3.add(psTable);				
		pnlTab3.setBorder(FaceContants.TITLE_BORDER);
		
		//Line0
		xPos += X_WIDTH_550 + 20;
		
		RequiredLabel lblRequire1Line0 = new RequiredLabel();
		lblRequire1Line0.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line0.setText("*");
		lblRequire1Line0.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lblRequire1Line0);
		
		EditLabel lbl1Line0 = new EditLabel();
		lbl1Line0.setText("行");
		lbl1Line0.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line0.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lbl1Line0);
		
		xPos+= X_WIDTH_120;
		txtTab3Line = new BaseInputText();
		txtTab3Line.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab3Line.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(txtTab3Line);
		
		//Line1
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;			
		
		EditLabel lbl1Line1 = new EditLabel();
		lbl1Line1.setText("更　　新");
		lbl1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lbl1Line1);
		
		xPos+= X_WIDTH_120;
		chkTab3Editable = new BaseCheckBox();
		chkTab3Editable.setHorizontalAlignment(SwingConstants.RIGHT);
		chkTab3Editable.setBounds(xPos, yPos, X_WIDTH_30, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(chkTab3Editable);
		
		//Line2
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;
		
		RequiredLabel lblRequire1Line2 = new RequiredLabel();
		lblRequire1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line2.setText("*");
		lblRequire1Line2.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lblRequire1Line2);
				
		EditLabel lbl1Line2 = new EditLabel();
		lbl1Line2.setText("申請届出日");
		lbl1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line2.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lbl1Line2);
		
		xPos+= X_WIDTH_120;
		dtpTab3ApplicationDay = new BaseDatePicker();
		dtpTab3ApplicationDay.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(dtpTab3ApplicationDay);
		
		//Line3
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;
		
		RequiredLabel lblRequire1Line3 = new RequiredLabel();
		lblRequire1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line3.setText("*");
		lblRequire1Line3.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lblRequire1Line3);
		
		EditLabel lbl1Line3 = new EditLabel();
		lbl1Line3.setText("申請届出内容");
		lbl1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line3.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(lbl1Line3);
		
		xPos+= X_WIDTH_120;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();	
		ArrayListComboBoxModel aModel = ApplicationUtils.createComboDataModel(lstData, 3, 100, ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE);
		cbbTab3ApplicationContent = new BaseComboBox(aModel);
		cbbTab3ApplicationContent.setBounds(xPos, yPos, X_WIDTH_150, FaceContants.LABLE_HEIGHT);
		pnlTab3.add(cbbTab3ApplicationContent);
		
		return pnlTab3;
	}
	
	private void getTableTab3()
	{
		BaseScrollPane tablePnl;
		m_dataTab3 = new InspectTableModel(colHeadNmTab3);
		m_tableTab3 = new InspectTable(m_dataTab3) {
			private static final long serialVersionUID = 1L;

			private int[] align = { BaseLabel.CENTER, BaseLabel.CENTER, BaseLabel.LEFT,	BaseLabel.LEFT };

			private InspectTableRenderer renderer = new InspectTableRenderer(
					align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row,
					int column) {
				return null;
			}
		};
		// set data for spread
		m_dataTab3.setData(new ArrayList<MEmpVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_tableTab3.getColumnModel();
		int length = colHeadwidthTab3.length;
		for (int i = 0; i < length; i++) {
			defModel.getColumn(i).setPreferredWidth(colHeadwidthTab3[i]);
			if (i != 3) {
				defModel.getColumn(i).setMinWidth(colHeadwidthTab3[i]);
				defModel.getColumn(i).setMaxWidth(colHeadwidthTab3[i]);
			}
		}
		m_tableTab3.getTableHeader().setReorderingAllowed(false);
//		// add action for table: UP, DOWN,...
//		initTableAction(m_tableTab3);
//		attachF8(m_tableTab3, false);
		// Set tab for table to control
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
//				if (m_tableTab3.isFocusOwner()) {
//					if (txtEmpCode.isEditable()) {
//						txtEmpCode.requestFocus();
//					} else if (txtEmpCode.isEditable()) {
//						txtEmpCode.requestFocus();
//					}
//				} else {
//					m_tableTab3.requestFocus();
//				}
			}
		};

		m_tableTab3.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		m_tableTab3.getActionMap().put("tabKey", tabKey);
		m_tableTab3.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_tableTab3.getActionMap().put("shiftTab", tabKey);
		m_tableTab3.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),	"enterKey");
		m_tableTab3.getActionMap().put("enterKey", tabKey);		
		// EVENT
//		// mouse click
//		m_tableTab3.addMouseListener(new TableMouseClick());

		// Not allow change index of Columns
		m_tableTab3.getTableHeader().setReorderingAllowed(false);
//		// change selected row
//		ListSelectionModel rowSM = m_tableTab3.getSelectionModel();
//		rowSM.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				convertTableRow2Txt();
//			}
//		});

		tablePnl = new BaseScrollPane(m_tableTab3);
		tablePnl.setBorder(FaceContants.PANEL_BORDER);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		m_tableTab3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
		
	protected void doRequestCompTab3() {
		txtTab3Line.requestFocus();
	}
	
	
	private BasePanel getTabPanel4()
	{
		BasePanel pnlTab4 = new BasePanel();
		int xPos = 15;
		int yPos = 15;
		int X_WIDTH_10 = 10;
		int X_WIDTH_30 = 30;
		int X_WIDTH_70 = 70;
		int X_WIDTH_120 = 120;
		int X_WIDTH_150 = 180;
		int X_WIDTH_550 = 550;		
			
		getTableTab4();
		BaseScrollPane psTable = new BaseScrollPane();
		psTable.setSize(X_WIDTH_550, 300);
		psTable.getViewport().add(m_tableTab4);
		psTable.setLocation(xPos, yPos);
		pnlTab4.add(psTable);				
		pnlTab4.setBorder(FaceContants.TITLE_BORDER);
		
		//Line0
		xPos += X_WIDTH_550 + 20;
		
		RequiredLabel lblRequire1Line0 = new RequiredLabel();
		lblRequire1Line0.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line0.setText("*");
		lblRequire1Line0.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lblRequire1Line0);
		
		EditLabel lbl1Line0 = new EditLabel();
		lbl1Line0.setText("行");
		lbl1Line0.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line0.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lbl1Line0);
		
		xPos+= X_WIDTH_120;
		txtTab4Line = new BaseInputText();
		txtTab4Line.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTab4Line.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(txtTab4Line);
		
		//Line1
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;			
		
		RequiredLabel lblRequire1Line1 = new RequiredLabel();
		lblRequire1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line1.setText("*");
		lblRequire1Line1.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lblRequire1Line1);
		
		EditLabel lbl1Line1 = new EditLabel();
		lbl1Line1.setText("整備主任者");
		lbl1Line1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line1.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lbl1Line1);
		
		xPos+= X_WIDTH_120;
		txtTab4SeniorDevelopment = new CdInputSearchText();
		txtTab4SeniorDevelopment.setBounds(xPos, yPos, X_WIDTH_70, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(txtTab4SeniorDevelopment);
		
		xPos+= X_WIDTH_70;
		lblTab4SeniorDevelopment = new BaseLabel();
		lblTab4SeniorDevelopment.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lblTab4SeniorDevelopment);
		
		//Line2
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;
		
		RequiredLabel lblRequire1Line2 = new RequiredLabel();
		lblRequire1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line2.setText("*");
		lblRequire1Line2.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lblRequire1Line2);
				
		BaseLabel lbl1Line2 = new BaseLabel();
		lbl1Line2.setText("証書番号");
		lbl1Line2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line2.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lbl1Line2);
		
		xPos+= X_WIDTH_120;
		lblTab4CertificateNumber = new BaseLabel();
		lblTab4CertificateNumber.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lblTab4CertificateNumber);
		
		//Line3
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;
		
		RequiredLabel lblRequire1Line3 = new RequiredLabel();
		lblRequire1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire1Line3.setText("*");
		lblRequire1Line3.setBounds(xPos - 10, yPos, X_WIDTH_10, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lblRequire1Line3);
		
		EditLabel lbl1Line3 = new EditLabel();
		lbl1Line3.setText("選　任　日");
		lbl1Line3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line3.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lbl1Line3);
		
		xPos+= X_WIDTH_120;
		dtpTab4ElectionDay = new BaseDatePicker();
		dtpTab4ElectionDay.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(dtpTab4ElectionDay);
		
		//Line4
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;
			
		EditLabel lbl1Line4 = new EditLabel();
		lbl1Line4.setText("選　任　日");
		lbl1Line4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line4.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lbl1Line4);
		
		xPos+= X_WIDTH_120;
		dtpTab4DismissalDay = new BaseDatePicker();
		dtpTab4DismissalDay.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(dtpTab4DismissalDay);
		
		//Line5
		xPos = 15;
		xPos += X_WIDTH_550 + 20;
		yPos += FaceContants.LABLE_HEIGHT*2;			
		
		EditLabel lbl1Line5 = new EditLabel();
		lbl1Line5.setText("仮");
		lbl1Line5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Line5.setBounds(xPos, yPos, X_WIDTH_120, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(lbl1Line5);
		
		xPos+= X_WIDTH_120;
		chkTab4Provisional = new BaseCheckBox();
		chkTab4Provisional.setHorizontalAlignment(SwingConstants.RIGHT);
		chkTab4Provisional.setBounds(xPos, yPos, X_WIDTH_30, FaceContants.LABLE_HEIGHT);
		pnlTab4.add(chkTab4Provisional);
		
		return pnlTab4;
	}
		
	private void getTableTab4()
	{
		BaseScrollPane tablePnl;
		m_dataTab4 = new InspectTableModel(colHeadNmTab4);
		m_tableTab4 = new InspectTable(m_dataTab4) {
			private static final long serialVersionUID = 1L;

			private int[] align = { BaseLabel.CENTER, BaseLabel.LEFT, BaseLabel.LEFT, BaseLabel.LEFT, BaseLabel.LEFT, BaseLabel.CENTER };

			private InspectTableRenderer renderer = new InspectTableRenderer(
					align);

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			public Component prepareEditor(TableCellEditor editor, int row,
					int column) {
				return null;
			}
		};
		// set data for spread
		m_dataTab4.setData(new ArrayList<MEmpVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_tableTab4.getColumnModel();
		int length = colHeadwidthTab4.length;
		for (int i = 0; i < length; i++) {
			defModel.getColumn(i).setPreferredWidth(colHeadwidthTab4[i]);
			if (i != 0 && i != 5) {
				defModel.getColumn(i).setMinWidth(colHeadwidthTab4[i]);
				defModel.getColumn(i).setMaxWidth(colHeadwidthTab4[i]);
			}
		}
		m_tableTab4.getTableHeader().setReorderingAllowed(false);
//		// add action for table: UP, DOWN,...
//		initTableAction(m_tableTab4);
//		attachF8(m_tableTab4, false);
		// Set tab for table to control
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
//				if (m_tableTab4.isFocusOwner()) {
//					if (txtEmpCode.isEditable()) {
//						txtEmpCode.requestFocus();
//					} else if (txtEmpCode.isEditable()) {
//						txtEmpCode.requestFocus();
//					}
//				} else {
//					m_tableTab4.requestFocus();
//				}
			}
		};

		m_tableTab4.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		m_tableTab4.getActionMap().put("tabKey", tabKey);
		m_tableTab4.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_tableTab4.getActionMap().put("shiftTab", tabKey);
		m_tableTab4.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),	"enterKey");
		m_tableTab4.getActionMap().put("enterKey", tabKey);		
		// EVENT
//		// mouse click
//		m_tableTab4.addMouseListener(new TableMouseClick());

		// Not allow change index of Columns
		m_tableTab4.getTableHeader().setReorderingAllowed(false);
//		// change selected row
//		ListSelectionModel rowSM = m_tableTab4.getSelectionModel();
//		rowSM.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				convertTableRow2Txt();
//			}
//		});

		tablePnl = new BaseScrollPane(m_tableTab4);
		tablePnl.setBorder(FaceContants.PANEL_BORDER);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		m_tableTab4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
		
	protected void doRequestCompTab4() {
		txtTab4Line.requestFocus();
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
		boolean[] fBtn = { true, true, true, true, true, true, true, true, true, true, true, true };
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

	@Override
	protected BasePanel getHeader() {
		return null;
	}

	@Override
	protected BasePanel getBodyLeft() {
		
		BasePanel pnlFirst = initPnlFirst();
		BasePanel pnlSecond = initPnlSecond();
		BasePanel pnlThird = initPnlThird();
		BasePanel pnlFourth = initPnlFourth();
		
		GroupLayout layout = new GroupLayout(pnlLeft);
		
//		layout.setHorizontalGroup(
//				layout.createParallelGroup(Alignment.TRAILING)
//					.addGroup(layout.createSequentialGroup()
//						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
//							.addComponent(pnlFourth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
//							.addComponent(pnlThird, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
//							.addComponent(pnlFirst, GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
//							.addComponent(pnlSecond, GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE))
//						.addGap(20))
//			);
//			layout.setVerticalGroup(
//				layout.createParallelGroup(Alignment.LEADING)
//					.addGroup(layout.createSequentialGroup()
//						.addComponent(pnlFirst, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
//						.addPreferredGap(ComponentPlacement.RELATED)
//						.addComponent(pnlSecond, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
//						.addPreferredGap(ComponentPlacement.RELATED)
//						.addComponent(pnlThird, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
//						.addPreferredGap(ComponentPlacement.RELATED)
//						.addComponent(pnlFourth, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
//						.addContainerGap(48, Short.MAX_VALUE))
//			);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(pnlFirst, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
					.addComponent(pnlSecond, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)						
					.addComponent(pnlThird, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
					.addComponent(pnlFourth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
				.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(pnlFirst, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.DEFAULT_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnlSecond, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.DEFAULT_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnlThird, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.DEFAULT_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnlFourth, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.DEFAULT_SIZE)
			.addContainerGap())
		);

		pnlLeft.setLayout(layout);
//		leftPnl.setPreferredSize(new Dimension(970,120));
		mainPanel.add(pnlLeft,BorderLayout.CENTER);
		return pnlLeft;
	}

	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setFrameSize() {
		X_FRAME_LENGTH = 970;
		Y_FRAME_LENGTH = 900;
		setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		setPreferredSize(new Dimension(X_FRAME_LENGTH , Y_FRAME_LENGTH));
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		setLocation((maximumWindowBounds.width - X_FRAME_LENGTH) / 2, (maximumWindowBounds.height - Y_FRAME_LENGTH) / 2);
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
		//Using for testing
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab("Tab 1", null, panel1,
		                  "Does nothing");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanel("Panel #2");
		tabbedPane.addTab("Tab 2", null, panel2,
		                  "Does twice as much nothing");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Tab 3", null, panel3,
		                  "Still does nothing");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeTextPanel(
		        "Panel #4 (has a preferred size of 410 x 50).");
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Tab 4", null, panel4,
		                      "Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		
		//Make sure we have nice window decorations.
	    JFrame.setDefaultLookAndFeelDecorated(true);

	    //Create and set up the window.
	    JFrame frame = new JFrame("TabbedPaneDemo");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Create and set up the content pane.
	    frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}
	
	protected static JComponent makeTextPanel(String text) {
	    JPanel panel = new JPanel(false);
	    JLabel filler = new JLabel(text);
	    filler.setHorizontalAlignment(JLabel.CENTER);
	    panel.setLayout(new GridLayout(1, 1));
	    panel.add(filler);
	    return panel;
	  }

}
