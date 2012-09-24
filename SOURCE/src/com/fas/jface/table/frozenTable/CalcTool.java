/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：EditClsName.java
*
*     記述			：
*     
*     作成日			：Jun 18, 2010   
*
*     作成者			：Admin
*
*     備考			：
*
**************************************************************************************/

package com.fas.jface.table.frozenTable;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.RequiredLabel;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.DoubleText;
import com.fas.jface.text.PasswordInputText;
import com.fas.jface.text.TimeInputText;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.vo.base.ComboObjectVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: QuangPV</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class CalcTool  extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private DoubleText txtA;
	/** */
	private BaseLabel lblA;
	/** */
	private DoubleText txtB;
	/** */
	private BaseLabel lblB;
	/** */
	private DoubleText txtC;
	/** */
	private BaseLabel lblC;
	/** */
	private BaseLabel lblUnit1;
	/** */
	private BaseLabel lblUnit2;
	/** */
	private BaseLabel lblDiv1;
	/** */
	private BaseLabel lblDiv2;
	/** */
	private BaseLabel lblQly;
	/** */
	private BaseLabel lblSyoSuuTen;
	/** */
	private BaseLabel lblHelpInfor;
	/**	 */
	private EditLabel lblValue;
	/** */
	private BaseLabel lblUnit;
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
	private BasePanel pnlHeader;
	/** */
	private BasePanel pnlButton;
	
	private BaseComboBox cbbCaclType;
	private BaseComboBox cbbFraction;
	/** */
	private BaseCheckBox chkCalcType; 
	
	static Logger logger = Logger.getLogger(CalcTool.class);
	public CalcTool()
	{
		 super();
		 init();
	}
	
	public CalcTool(BaseFrame owner, boolean modal) {
	    super(owner, modal);
	    init();
	}
	
	@Override
	protected BasePanel getHeader() {
		 headerMainPnl = new BasePanel();		 
		 headerMainPnl.setPreferredSize(new Dimension (X_HEADER_LENGTH, Y_HEADER_LENGTH - 70));
		 headerMainPnl.setLocation(0, 0);
	     
	     pnlHeader = new BasePanel();
	     pnlHeader.setSize(X_HEADER_LENGTH - 15, Y_HEADER_LENGTH -120);
	     pnlHeader.setLocation(10, 10);
	     pnlHeader.setBorder(FaceContants.TITLE_BORDER);		 	     
	     initHeaderPnl(pnlHeader);
	     
	     headerMainPnl.add(pnlHeader);
	     
	     return headerMainPnl;
	}
	
	@Override
	protected BasePanel getBodyPanel() {
		 mainPnl = new BasePanel();
//		 mainPnl.setPreferredSize(new Dimension (X_BODY_LENGTH + 5, Y_BODY_LENGTH + 5));
		 mainPnl.setPreferredSize(new Dimension (X_BODY_LENGTH , Y_BODY_LENGTH));
		 mainPnl.setLocation(0, 0);
		 
		 pnlButton = new BasePanel();
		 pnlButton.setSize(575, 54 );
		 pnlButton.setLocation(10, 0);
		 pnlButton.setBorder(FaceContants.TITLE_BORDER);
		 initMainPnl(pnlButton);
		 
		 mainPnl.add(pnlButton);
		 
		 return mainPnl;
	}

	@Override
	protected BasePanel getFooter() {
		// パネルの生成
		footerMainPnl = new BasePanel();
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH + 10,
				Y_FOOTER_LENGTH + 5 ));
		
		
		final int I_LBL_HEIGHT = 22;

		BasePanel statusBar = new BasePanel();
		statusBar.setSize(new Dimension(X_FOOTER_LENGTH ,
				Y_FOOTER_LENGTH));
		statusBar.setLocation(0, 0);
		statusBar.setLayout(null);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor.setSize(new Dimension(X_FOOTER_LENGTH - 3, I_LBL_HEIGHT));
		lblHelpInfor.setLocation(0, 4);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);

		footerMainPnl.add(statusBar);

		return footerMainPnl;
	}

	protected String getHelpInfor() {
		return "";
	}

	protected void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}
	
	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}


	

	private void initHeaderPnl(final BasePanel mainPnl1) {

    	int xPos = 15;
    	int yPos = 10;
		int I_LBL_LENGTH = 110;
		int TXT_TEXT_FIELD_LENGTH_60 = 50;		
		int TXT_TEXT_FIELD_LENGTH_100 = 100;
		int TXT_TEXT_FIELD_LENGTH_180 = 180;
		int TXT_TEXT_FIELD_LENGTH_300 = 300;

		ComboService comService = new ComboServiceImpl();
		
		RequiredLabel lblAsteriskCalcType = new RequiredLabel("*");
		lblAsteriskCalcType.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		mainPnl1.add(lblAsteriskCalcType);
		EditLabel lblCalcType = new EditLabel("計算式");
		lblCalcType.setLocation(xPos, yPos);
		lblCalcType.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		mainPnl1.add(lblCalcType);
		
		List<ComboObjectVo> lstDataCalcType  = null;
		try {
			 lstDataCalcType = comService.getLstCalcType();
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstDataCalcType = new ArrayList<ComboObjectVo>();
		}
		
			
		ComboObjectVo dataVoCaclType = new ComboObjectVo();
		dataVoCaclType.setCode("");
		dataVoCaclType.setValue1("");
		lstDataCalcType.add(0, dataVoCaclType);

		cbbCaclType = new BaseComboBox(ApplicationUtils.createComboDataModel(lstDataCalcType, 4, 30,ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE));//		
		cbbCaclType.setToolTipText( StringUtils.trimAllVN(  StringUtils.trimAllVN( lblCalcType.getText()  ) + "を選択して下さい。"));
		cbbCaclType.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbCaclType.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300, FaceContants.LABLE_HEIGHT));
		cbbCaclType.setPopupWidth(TXT_TEXT_FIELD_LENGTH_300);	
		
		setDefaultSelected(cbbCaclType);
		
		mainPnl1.add(cbbCaclType);
		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;

		EditLabel lblUseFlg = new EditLabel("SUS区分");
		lblUseFlg.setLocation(xPos, yPos);
		lblUseFlg
				.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		mainPnl1.add(lblUseFlg);

		chkCalcType = new BaseCheckBox(NameConstants.getName("SUS" + "0"));
		chkCalcType.setToolTipText( StringUtils.trimAllVN( lblUseFlg.getText()  ) + "を選択して下さい。");
		chkCalcType.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkCalcType.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_180,
				FaceContants.LABLE_HEIGHT));
		chkCalcType.setChkLabel(NameConstants.getName("SUS" + "1"));// Get
		// value in Table M_NAME
		chkCalcType.setUnChkLabel(NameConstants.getName("SUS" + "0"));
		chkCalcType.setSelected(false);
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (StringUtils.isValid(chkCalcType.getChkLabel())
						&& StringUtils.isValid(chkCalcType.getUnChkLabel())) {
					AbstractButton abstractButton = (AbstractButton) actionEvent
							.getSource();
					boolean selected = abstractButton.getModel().isSelected();
					String newLabel = (selected ? chkCalcType.getChkLabel() : chkCalcType.getUnChkLabel());
					abstractButton.setText(newLabel);
					if(selected){
						lblUnit2.setText(MCtlConstants.getValueByCKey("UNIT_WT1"));
					}else{
						lblUnit2.setText(MCtlConstants.getValueByCKey("UNIT_WT0"));
					}
				}
			}
		};

		chkCalcType.addActionListener(actionListener);
		mainPnl1.add(chkCalcType);

		//1. LBL TXT LCLS MaxLength 8 Width 180
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskLclsName = new RequiredLabel("*");
		lblAsteriskLclsName.setBounds(new Rectangle(xPos - 10, yPos, 10, FaceContants.LABLE_HEIGHT));
		mainPnl1.add(lblAsteriskLclsName);
		
		lblValue = new EditLabel("値");
		lblValue.setLocation(xPos, yPos);
		lblValue.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblValue.setHorizontalAlignment(EditLabel.LEFT);
		mainPnl1.add(lblValue);
		
		txtA = new DoubleText("0",5,2);
		txtA.setToolTipText(StringUtils.trimAllVN("値を入力して下さい。"));
		txtA.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtA.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_100, FaceContants.LABLE_HEIGHT));		
		mainPnl1.add(txtA);		
		
		lblA = new BaseLabel("φ　X");
		lblA.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100, yPos);
		lblA.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_60, FaceContants.LABLE_HEIGHT));
		lblA.setBorder(null);
		mainPnl1.add(lblA);	
		
		txtB = new DoubleText("0",5,2);
		txtB.setToolTipText(StringUtils.trimAllVN("値を入力して下さい。"));
		txtB.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100 + TXT_TEXT_FIELD_LENGTH_60, yPos);
		txtB.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_100, FaceContants.LABLE_HEIGHT));		
		mainPnl1.add(txtB);		
		
		lblB = new BaseLabel("φ　X");
		lblB.setLocation(xPos + I_LBL_LENGTH + 2*TXT_TEXT_FIELD_LENGTH_100 + TXT_TEXT_FIELD_LENGTH_60, yPos);
		lblB.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_60, FaceContants.LABLE_HEIGHT));
		lblB.setBorder(null);
		mainPnl1.add(lblB);	
		
		txtC = new DoubleText("0",5,2);
		txtC.setToolTipText(StringUtils.trimAllVN("値を入力して下さい。"));
		txtC.setLocation(xPos + I_LBL_LENGTH + 2*TXT_TEXT_FIELD_LENGTH_100 + 2*TXT_TEXT_FIELD_LENGTH_60, yPos);
		txtC.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_100, FaceContants.LABLE_HEIGHT));		
		mainPnl1.add(txtC);		
		
		lblC = new BaseLabel("t");
		lblC.setLocation(xPos + I_LBL_LENGTH + 3*TXT_TEXT_FIELD_LENGTH_100 + 2*TXT_TEXT_FIELD_LENGTH_60, yPos);
		lblC.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_60 - 15, FaceContants.LABLE_HEIGHT));
		lblC.setBorder(null);
		mainPnl1.add(lblC);	
		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		
		lblUnit = new BaseLabel("単位重量");
		lblUnit.setLocation(xPos, yPos);
		lblUnit.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblUnit.setHorizontalAlignment(EditLabel.LEFT);
		lblUnit.setBorder(FaceContants.LINE_BORDER);
		lblUnit.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		mainPnl1.add(lblUnit);
		
		lblUnit1 = new BaseLabel("  　X");
		lblUnit1.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100, yPos);
		lblUnit1.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_60, FaceContants.LABLE_HEIGHT));
		lblUnit1.setBorder(null);
		mainPnl1.add(lblUnit1);	
		
		lblUnit2 = new BaseLabel();
		lblUnit2.setText(MCtlConstants.getValueByCKey("UNIT_WT0"));
		lblUnit2.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100 + TXT_TEXT_FIELD_LENGTH_60, yPos);
		lblUnit2.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_100, FaceContants.LABLE_HEIGHT));	
		lblUnit2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnit2.setBorder(FaceContants.LABEL_BORDER);
		mainPnl1.add(lblUnit2);	
		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		
		BaseLabel lblDiv = new BaseLabel("除算値");
		lblDiv.setLocation(xPos, yPos);
		lblDiv.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblDiv.setHorizontalAlignment(EditLabel.LEFT);
		lblDiv.setBorder(FaceContants.LINE_BORDER);
		lblDiv.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		mainPnl1.add(lblDiv);
		
		lblDiv1 = new BaseLabel("  　÷");       
		lblDiv1.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100 - 4, yPos);
		lblDiv1.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_60, FaceContants.LABLE_HEIGHT));
		lblDiv1.setBorder(null);
		mainPnl1.add(lblDiv1);	
		lblDiv2 = new BaseLabel( MCtlConstants.getValueByCKey("CAL_DIV"));
		lblDiv2.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100 + TXT_TEXT_FIELD_LENGTH_60, yPos);
		lblDiv2.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_100, FaceContants.LABLE_HEIGHT));	
		lblDiv2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiv2.setBorder(FaceContants.LABEL_BORDER);
		mainPnl1.add(lblDiv2);	
		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
//		RequiredLabel lblAsteriskSyosuuten = new RequiredLabel("*");
//		lblAsteriskSyosuuten.setBounds(new Rectangle(xPos - 10, yPos, 10, FaceContants.LABLE_HEIGHT));
//		mainPnl1.add(lblAsteriskSyosuuten);
		
		EditLabel lblSyouSuuTen = new EditLabel("小数第");
		lblSyouSuuTen.setLocation(xPos, yPos);
		lblSyouSuuTen.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		lblSyouSuuTen.setHorizontalAlignment(EditLabel.LEFT);
		lblSyouSuuTen.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		mainPnl1.add(lblSyouSuuTen);
		
		lblSyoSuuTen = new BaseLabel();	
		lblSyoSuuTen.setLocation(xPos + I_LBL_LENGTH, yPos);
		lblSyoSuuTen.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_100, FaceContants.LABLE_HEIGHT));
		lblSyoSuuTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSyoSuuTen.setBorder(FaceContants.LABEL_BORDER);
		lblSyoSuuTen.setText(MCtlConstants.getValueByCKey("FRC_DEC"));	

		mainPnl1.add(lblSyoSuuTen);		
		
		BaseLabel lblPos = new BaseLabel("位");
		lblPos.setLocation(xPos + I_LBL_LENGTH + TXT_TEXT_FIELD_LENGTH_100, yPos);
		lblPos.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_60, FaceContants.LABLE_HEIGHT));
		lblPos.setBorder(null);
		mainPnl1.add(lblPos);	
		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		
		RequiredLabel lblAsteriskFraction = new RequiredLabel("*");
		lblAsteriskFraction.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		mainPnl1.add(lblAsteriskFraction);
		
		EditLabel lblFraction = new EditLabel("端数処理");
		lblFraction.setLocation(xPos, yPos);
		lblFraction.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		mainPnl1.add(lblFraction);
		
		List<ComboObjectVo> lstDataFraction = null;
		try {
			lstDataFraction = comService.getLstFraction();
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstDataFraction = new ArrayList<ComboObjectVo>();
		}
			
		ComboObjectVo dataVoFraction = new ComboObjectVo();
		dataVoFraction.setCode("");
		dataVoFraction.setValue1("");
		lstDataFraction.add(0, dataVoFraction);

		cbbFraction = new BaseComboBox(ApplicationUtils.createComboDataModel(lstDataFraction, 4, 30,ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE));//		
		cbbFraction.setToolTipText( StringUtils.trimAllVN(  StringUtils.trimAllVN( lblFraction.getText()  ) + "を選択して下さい。"));
		cbbFraction.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbFraction.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300, FaceContants.LABLE_HEIGHT));
		cbbFraction.setPopupWidth(TXT_TEXT_FIELD_LENGTH_300);	
		
		setDefaultSelected(cbbFraction);
		mainPnl1.add(cbbFraction);
		
		yPos += FaceContants.LABLE_HEIGHT + 4*FaceContants.VERTICAL_SPACE;
		
		EditLabel lblSum = new EditLabel("重　量");
		lblSum.setLocation(xPos, yPos);
		lblSum.setSize(new Dimension(I_LBL_LENGTH, 3*FaceContants.LABLE_HEIGHT));
		lblSum.setBorder(FaceContants.LINE_BORDER);
		lblSum.setFont(new Font("ＭＳ Ｐゴシック", Font.PLAIN, 22));
		lblSum.setBackground(new Color(242, 200, 15));
		mainPnl1.add(lblSum);
		
		lblQly = new BaseLabel();
		lblQly.setLocation(xPos + I_LBL_LENGTH , yPos);
		lblQly.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300, 3*FaceContants.LABLE_HEIGHT));	
		lblQly.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQly.setFont(new Font("ＭＳ Ｐゴシック", Font.BOLD, 22));
		lblQly.setBorder(FaceContants.LABEL_BORDER);
		mainPnl1.add(lblQly);
		
		/**/
		if("1".equalsIgnoreCase(cbbCaclType.getSelectedKey())){
			lblA.setText("φ　X");
			lblB.setText("φ　X");
			//txtB.setText("");
			//txtB.setEditable(false);
		}else{
			lblA.setText("  　X");
			lblB.setText("  　X");
			//txtB.setEditable(true);
		}
		//
		cbbCaclType.addItemListener(new ItemListener() {
						@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						//txtA.setText("");
						//txtA.setText("");
						//txtC.setText("");
					   if("1".equalsIgnoreCase(cbbCaclType.getSelectedKey())){
							lblA.setText("φ　X");
							lblB.setText("φ　X");
							//txtB.setText("");
							//txtB.setEditable(false);
						}else{
							lblA.setText("  　X");
							lblB.setText("  　X");
							//txtB.setEditable(true);
						}
					}
				}
		});
	}

	private void initMainPnl(BasePanel footerMainPnl)
	{	
		int X_BTN_WIDTH = 71;
    	int Y_BTN_HEIGHT = 36;
    	int X_BTN_SPACE = 12;    	
    	int xPos = 13;
    	int yPos = 10;

    	F5Action f5Action = new F5Action("f5Action");
    	btnF5 = new ActionButton();
    	btnF5.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF5.setText("<html><center><font size=-1>重量選択</font></center><center><font size=-1>F5(T)</font></center>");
    	btnF5.addActionListener(f5Action);
    	btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), f5Action.getValue(Action.NAME));
    	btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt T"), f5Action.getValue(Action.NAME));
    	btnF5.getActionMap().put("f5Action", f5Action);
    	btnF5.setToolTipText("[F5]：重量選択を選択します。");
    	btnF5.setFocusable(false);
    	footerMainPnl.add(btnF5); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	//F6Action f6Action = new F6Action("f6Action");
    	btnF6 = new ActionButton();
    	btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF6.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
    	//btnF5.addActionListener(f5Action);
    	//btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), f5Action.getValue(Action.NAME));
    	//btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt T"), f5Action.getValue(Action.NAME));
    	//btnF5.getActionMap().put("f5Action", f5Action);
    	//btnF5.setToolTipText("[F5]：重量選択を選択します。");
    	btnF6.setEnabled(false);
    	footerMainPnl.add(btnF6); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	F7Action f7Action = new F7Action("f7Action");
    	btnF7 = new ActionButton();
    	btnF7.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF7.setText("<html><center><font size=-1>重量計算</font></center><center><font size=-1>F7(W)</font></center>");
    	btnF7.addActionListener(f7Action);
    	btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), f7Action.getValue(Action.NAME));
    	btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt W"), f7Action.getValue(Action.NAME));
    	btnF7.getActionMap().put("f7Action", f7Action);
    	btnF7.setToolTipText("[F7]：重量計算を選択します。");
    	btnF7.setFocusable(false);
    	footerMainPnl.add(btnF7); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF8 = new ActionButton();
    	btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF8.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
    	btnF8.setEnabled(false);
    	footerMainPnl.add(btnF8); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF11 = new ActionButton();
		F11Action f11Action = new F11Action("f11Action");
		btnF11.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT));
		btnF11.setText("<html><center><font size=-1>中　止</font></center><font size=-1>F11(C)</font>");
		btnF11.addActionListener(f11Action);
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("F11"),
				f11Action.getValue(Action.NAME));
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("alt C"),
				f11Action.getValue(Action.NAME));
		btnF11.getActionMap().put("f11Action", f11Action);
		btnF11.setToolTipText("[F11]：処理を中止します。");
		btnF11.setFocusable(false);
		footerMainPnl.add(btnF11);
		
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF12 = new ActionButton();
    	F12Action f12Action = new F12Action("f12Action");
    	btnF12.addActionListener(f12Action);
    	btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
    	btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
    	btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), f12Action.getValue(Action.NAME));
    	btnF12.setToolTipText("[F12]：処理を終了します。");
    	btnF12.getActionMap().put("f12Action", f12Action);
    	footerMainPnl.add(btnF12); 
    	    
	}
	
	protected void init() {
		setTitle("重量計算ツール");
		//btnF8.setFocusable(false);
		btnF12.setFocusable(false);
	

		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (("focusOwner".equals(prop))) {
					if (((e.getNewValue()) instanceof InspectRadioButton)
							|| ((e.getNewValue()) instanceof BaseCalendarText)
							|| ((e.getNewValue()) instanceof JCheckBox)
							|| ((e.getNewValue()) instanceof JTextField)
							|| ((e.getNewValue()) instanceof JButton)
							|| ((e.getNewValue()) instanceof BaseComboBox)
							|| ((e.getNewValue()) instanceof TimeInputText)
							|| ((e.getNewValue()) instanceof PasswordInputText)) {
						JComponent comp = (JComponent) e.getNewValue();
						String name = comp.getToolTipText();
						if (name != null) {
							setHelpInfor(name);
						}
					}
				}
			}
		});		
		
		this.setResizable(false);
	}
	/* (non-Javadoc)
	 * @see com.pipe.jface.InspectDialog#setContentPane(java.awt.Container)
	 */
	public String getSum(){
		return lblQly.getText().trim();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.jface.InspectDialog#setContentPane(java.awt.Container)
	 */
	public void setContentPane(Container contentPane) {
	}

	protected void setFrameSize() {
	    	//ctlService = new MCtlServiceImpl();
	    	
	    	X_FRAME_LENGTH = 590;
	    	Y_FRAME_LENGTH = 390;  
	    	
	    	X_HEADER_LENGTH = X_FRAME_LENGTH;
	    	Y_HEADER_LENGTH =400;
	    	
	    	X_BODY_LENGTH = X_FRAME_LENGTH;
	    	Y_BODY_LENGTH = 69;
	    	
	    	X_FOOTER_LENGTH = X_FRAME_LENGTH + 10;
	    	Y_FOOTER_LENGTH = 25;
	    }

	/**
	 * @author PC13
	 *
	 */
	class F1Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F1Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF1();	
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>最初</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF1() {
		
	}
	/**
	 * @author PC13
	 *
	 */
	class F2Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F2Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF2();	
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>上へ</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF2() {
	
	}
	/**
	 * @author PC13
	 *
	 */
	class F3Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F3Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF3();	
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>下へ</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF3() {
	
	}
	/**
	 * @author PC13
	 *
	 */
	class F4Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F4Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			btnF4.setEnabled(true);
			doF4();	
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>最後</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF4() {
		
	}
	/**
	 * @author PC13
	 *
	 */
	class F5Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F5Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF5();	
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>追加</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF5() {
		if(StringUtils.isValid(getSum())){
			String strI = getSum().substring(0, getSum().indexOf("."));
			String strF = getSum().substring(getSum().indexOf(".") + 1, getSum().length());
			int iSosuten = Integer.parseInt(MCtlConstants.getValueByCKey("FRC_DEC")) - 1;
			if(strI.length() > 5 || strF.length() > iSosuten){
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0229"));
				txtA.requestFocus();
			}else{
				this.dispose();	
			}
		}else{
			this.dispose();	
		}
	}
	/**
	 * @author PC13
	 *
	 */
	class F6Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F6Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF6();	
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>削除</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF6() {
		
	}
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
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF7();	
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
	protected void doF7() {
		NumberFormat nf = NumberFormat.getInstance();
		Double dbSum1 = 0.0;
		if(validateData()){
			if("1".equalsIgnoreCase(cbbCaclType.getSelectedKey())){
				double dbSum = (txtA.getValue()*txtA.getValue()*txtC.getValue()*(Double.parseDouble(lblUnit2.getText().trim())))
				/Double.parseDouble(lblDiv2.getText().trim());
				int iSosu = Integer.parseInt(lblSyoSuuTen.getText().trim()) - 1;
				if("1".equalsIgnoreCase(cbbFraction.getSelectedKey())){ // 切り捨て
					BigDecimal bi = new BigDecimal(String.valueOf(dbSum));
					dbSum1 = bi.setScale(iSosu , RoundingMode.FLOOR).doubleValue();
				}else if("2".equalsIgnoreCase(cbbFraction.getSelectedKey())){ // 切り上げ
					BigDecimal bi = new BigDecimal(String.valueOf(dbSum));
					dbSum1 = bi.setScale(iSosu, RoundingMode.CEILING).doubleValue();
				}else if("3".equalsIgnoreCase(cbbFraction.getSelectedKey())){ // 四捨五入
					BigDecimal bi = new BigDecimal(String.valueOf(dbSum));
					dbSum1 = bi.setScale(iSosu, RoundingMode.HALF_UP).doubleValue();
				}
				if(dbSum1!=0){
					lblQly.setText( nf.format(dbSum1));
				}else{
					lblQly.setText("0.0");
				}
			}else{
				double dbSum = (txtA.getValue()*txtB.getValue()*txtC.getValue()*(Double.parseDouble(lblUnit2.getText().trim())))
				/Double.parseDouble(lblDiv2.getText().trim());
				int iSosu = Integer.parseInt(lblSyoSuuTen.getText().trim()) - 1;
				if("1".equalsIgnoreCase(cbbFraction.getSelectedKey())){ // 切り捨て
					BigDecimal bi = new BigDecimal(String.valueOf(dbSum));
					dbSum1 = bi.setScale(iSosu , RoundingMode.FLOOR).doubleValue();
				}else if("2".equalsIgnoreCase(cbbFraction.getSelectedKey())){ // 切り上げ
					BigDecimal bi = new BigDecimal(String.valueOf(dbSum));
					dbSum1 = bi.setScale(iSosu, RoundingMode.CEILING).doubleValue();
				}else if("3".equalsIgnoreCase(cbbFraction.getSelectedKey())){ // 四捨五入
					BigDecimal bi = new BigDecimal(String.valueOf(dbSum));
					dbSum1 = bi.setScale(iSosu, RoundingMode.HALF_UP).doubleValue();
				}
				if(dbSum1!=0){
					lblQly.setText( nf.format(dbSum1));
				}else{
					lblQly.setText("0.0");
				}
			}
		}else{
			btnF7.setAffterDoDisable(false);
		}
		cbbCaclType.requestFocus();
		
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
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF8();	
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
	protected void doF8() {
		
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
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF9();	
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
	protected void doF9() {
		
	}
	/**
	 * @author PC13
	 *
	 */
	class F10Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F10Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF10();	
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
	protected void doF10() {
		
	}
	/**
	 * @author PC13
	 *
	 */
	class F11Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F11Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF11();	
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
	protected void doF11() 
	{	
		chkCalcType.setSelected(false);
		lblUnit2.setText(MCtlConstants.getValueByCKey("UNIT_WT0"));
		txtA.setText("");
		txtB.setText("");
		txtC.setText("");
		lblQly.setText("");
		setDefaultSelected(cbbCaclType);
		setDefaultSelected(cbbFraction);

	}
	/**
	 * @author PC13
	 *
	 */
	class F12Action extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		public F12Action(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF12();	
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
	protected void doF12() {
		dispose();
	}

	boolean validateData()
	{
		boolean isValid = true;
		JComponent comp = null;
		
		if (isValid && !StringUtils.isValid((cbbCaclType).getSelectedKey())){
			isValid = false;
			comp = cbbCaclType;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("計算式"));
		}
		//Valid * 40 txtMclsName
		if (isValid && !StringUtils.isValid(txtA.getText())) {
			isValid = false;
			comp = txtA;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("値"));
		}
		
		if (isValid && !StringUtils.isValid(txtB.getText())) {
			isValid = false;
			comp = txtB;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("値"));
		}
		
		if (isValid && !StringUtils.isValid(txtC.getText())) {
			isValid = false;
			comp = txtC;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("値"));
		}
		
		if (isValid && !StringUtils.isValid(cbbFraction.getSelectedKey())){
			isValid = false;
			comp = cbbFraction;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005").setExtraInfor("端数処理"));
		}

		//Set Default First Focus
		if (comp != null) {
			setDefaultFirstFocus(comp);
		}
		
		return isValid;
	}
	
	/**
	 * @param cbb
	 */
	private void setDefaultSelected(JComboBox cbb){
		boolean findOut = false;
		try {
			ArrayListComboBoxModel model = (ArrayListComboBoxModel) cbb.getModel();
			final String defaultType = "1";
			final int cbbSize = model.getSize();
			ComboObjectVo temp = null;
			for (int i = 0; i < cbbSize; i++) {
				if (model.getObjAt(i) != null && model.getObjAt(i) instanceof ComboObjectVo) {
					temp = (ComboObjectVo) model.getObjAt(i);
					if (defaultType.equals(temp.getValue3())) {
						findOut = true;
						cbb.setSelectedIndex(i);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!findOut) {
			cbb.setSelectedIndex(0);
		}
		cbb.repaint();
	}

    protected void setDefaultFirstFocus(final JComponent com) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			if (com.isEnabled()) {
    				com.requestFocusInWindow();
    			}
    		}
    	});
    }
}
