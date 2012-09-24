/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchShiIreSakiFrame.java
*
*     記述				：
*     
*     作成日			：2010/02/16   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.sapp.search;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.sapp.base.SearchMasterFrame;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;

/**
 * @author PC13
 *
 */
public class SearchMCustFrame extends SearchMasterFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private BaseLabel lblInput1;
	/** */
	private BaseLabel lblInputInfor1;
	/** */
	private BaseInputText txtInput1;
	/** */
	private BaseComboBox comInput2;
	/** */
	private BaseLabel lblInputInfor2;
	/** */
	private BaseLabel lblInput2;
	/** */
	private InspectRadioButton rdKubun1;
	/** */
	private InspectRadioButton rdKubun2;
	/** */
	private InspectRadioButton rdKubun3;
	/** */
	private BasePanel pnlKubun;
	/** */
	private ComboService comboService;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchMCustFrame() {
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
	public SearchMCustFrame(BaseFrame frame) {
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
	public SearchMCustFrame(BaseFrame frame, String title) {
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
	public SearchMCustFrame(BaseFrame frame, boolean modal) {
        super(frame, modal);
        init();
	}

	/**
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param frame
	* @param sortVo
	* </DL>
	*/
	public SearchMCustFrame(BaseFrame frame, SortObjVo sortVo) {
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
		setFocusComponent(comInput2);
		setFocusComponent(txtInput1);
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
	protected void doF8() {
		searchIndex = "";
		resetData();
		doSearch();
	}

	@Override
	protected String getKenSuu() {
		if (m_table != null) {
			return "" + m_table.getRowCount();
		} else {
			return "0";
		}
	}

	@Override
	protected boolean isShortBtn() {
		return true;
	}

	@Override
	protected MSchDspVo getSchDspVo() {

		MSchDspVo dspVo = null;
		
		try {
			dspVo = searchService.getMSchDspVo("29");
		} catch (BizException e) {
		}
		
		return dspVo;
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
	protected void initPnlCondition(BasePanel pnlFilter) {
		
		int xPos = 15;
		int yPos = 16;

		lblInput2 = new BaseLabel();
		lblInput2.setBorder(null);
		lblInput2.setText(dspVo.getTtlName1());
		lblInput2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInput2.setBackground(pnlFilter.getBackground());
		lblInput2.setSize(92, FaceContants.TEXT_HEIGHT);
		lblInput2.setLocation(xPos, yPos);
		pnlFilter.add(lblInput2);
		
		pnlKubun = new BasePanel();
		pnlKubun.setBorder(FaceContants.TITLE_BORDER);
		pnlKubun.setLocation(xPos + 100, yPos - 2);
		pnlKubun.setSize(335, 28);
		
		int xRPos = 2;
		int yRPos = 2;		
		rdKubun1 = new InspectRadioButton();
		rdKubun1.setText("得意先区分１");
		rdKubun1.setSelected(true);
		rdKubun1.setSize(110, FaceContants.TEXT_HEIGHT);
		rdKubun1.setLocation(xRPos, yRPos);
		rdKubun1.setFont(FontConstants.SEARCH_COMMON_FONT);
		pnlKubun.add(rdKubun1);

		xRPos += 110;
		rdKubun2 = new InspectRadioButton();
		rdKubun2.setText("得意先区分２");
		rdKubun2.setSize(110, FaceContants.TEXT_HEIGHT);
		rdKubun2.setLocation(xRPos, yRPos);
		rdKubun2.setFont(FontConstants.SEARCH_COMMON_FONT);
		pnlKubun.add(rdKubun2);
		
		xRPos += 110;
		rdKubun3 = new InspectRadioButton();
		rdKubun3.setText("得意先区分３");
		rdKubun3.setSize(110, FaceContants.TEXT_HEIGHT);
		rdKubun3.setLocation(xRPos, yRPos);
		rdKubun3.setFont(FontConstants.SEARCH_COMMON_FONT);
		pnlKubun.add(rdKubun3);
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rdKubun1);
		btnGrp.add(rdKubun2);
		btnGrp.add(rdKubun3);
		
		
		xPos += 100;		
		comboService = new ComboServiceImpl();
		List<ComboObjectVo> lsData = null;
		try {
			 lsData = comboService.getLstMNameCustType();
		} catch (BizException e) {
			e.printStackTrace();
		}
		
		comInput2 = new BaseComboBox(ApplicationUtils.createComboDataModel(lsData, 4, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
		comInput2.setSize(170, FaceContants.TEXT_HEIGHT);
		comInput2.setPopupWidth(280);
		comInput2.setBorder(FaceContants.DOT_LINE_BORDER);
		comInput2.setFont(FontConstants.COMBOBOX_FONT);
		comInput2.setLocation(xPos, yPos);
		
		xPos += 170;
		lblInputInfor2 = new BaseLabel();
		lblInputInfor2.setSize(210, FaceContants.TEXT_HEIGHT);
		lblInputInfor2.setLocation(xPos, yPos);
		lblInputInfor2.setBorder(FaceContants.LABEL_BORDER);
		//comInput2.setLblSectName(lblInputInfor2);
		if ("4".equalsIgnoreCase(schCtlVo.getCData().trim())) {
			pnlFilter.add(pnlKubun);
		} else {
			pnlFilter.add(comInput2);
			pnlFilter.add(lblInputInfor2);
		}
		comInput2.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				comInput2ItemStateChanged(evt);
			}
		});
		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE + 7;
		xPos = 15;
		lblInput1 = new BaseLabel();
		lblInput1.setBorder(null);
		lblInput1.setText(dspVo.getTtlName2());
		lblInput1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInput1.setBackground(pnlFilter.getBackground());
		lblInput1.setSize(92, FaceContants.TEXT_HEIGHT);
		lblInput1.setLocation(xPos, yPos);
		pnlFilter.add(lblInput1);
		searchItem = "1";
		
		xPos += 100;
		txtInput1 = new BaseInputText();
		txtInput1.setSize(170, FaceContants.TEXT_HEIGHT);
		txtInput1.setLocation(xPos, yPos);
		pnlFilter.add(txtInput1);
		
		xPos += 170;
		lblInputInfor1 = new BaseLabel();
		lblInputInfor1.setSize(210, FaceContants.TEXT_HEIGHT);
		lblInputInfor1.setLocation(xPos, yPos);
		lblInputInfor1.setBorder(FaceContants.LABEL_BORDER);
		pnlFilter.add(lblInputInfor1);		

	}
	private void comInput2ItemStateChanged(java.awt.event.ItemEvent evt) {
		String s = StringUtils.objectToStr(comInput2.getSelectedValue1());
		lblInputInfor2.setText(s);		
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	private void changeCoditionItem() {
		if (rdSchItem4.isSelected()) {
			pnlCondition.add(pnlKubun);
			pnlCondition.remove(comInput2);
			pnlCondition.remove(lblInputInfor2);
			lblInput2.setText("");
		} else {
			pnlCondition.add(comInput2);
			pnlCondition.add(lblInputInfor2);
			pnlCondition.remove(pnlKubun);
			lblInput2.setText(dspVo.getTtlName1());
		}
		pnlCondition.repaint();
	}

	@Override
	protected void doRd1() {
		rdSchItem1.setSelected(true);
		changeCoditionItem();
		lblInput1.setText(dspVo.getSchName1());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		doRequestComp();
		searchItem = "1";
	}

	@Override
	protected void doRd2() {
		rdSchItem2.setSelected(true);
		changeCoditionItem();
		lblInput1.setText(dspVo.getSchName2());	
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_HIRAGANA);
		doRequestComp();
		searchItem = "2";
	}

	@Override
	protected void doRd3() {
		rdSchItem3.setSelected(true);
		changeCoditionItem();
		lblInput1.setText(dspVo.getSchName3());	
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_HALFKANA);
		doRequestComp();
		searchItem = "3";
	}

	@Override
	protected void doRd4() {
		rdSchItem4.setSelected(true);
		changeCoditionItem();
		lblInput1.setText(dspVo.getSchName4());	
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		doRequestComp();
		searchItem = "4";
	}

	@Override
	protected void doRd5() {
		rdSchItem5.setSelected(true);
		changeCoditionItem();
		lblInput1.setText(dspVo.getSchName5());	
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		doRequestComp();
		searchItem = "5";
	}

	@Override
	protected void doRd6() {
		rdSchItem6.setSelected(true);
		changeCoditionItem();
		txtInput1.setText("");
		lblInput1.setText(dspVo.getSchName6());	
		txtInput1.setIMType(BaseInputText.IM_OFF);
		doRequestComp();
		searchItem = "6";		
	}

	@Override
	protected SearchConditionVo getSearchCondition() {
		
		SearchConditionVo searchCondition = new SearchConditionVo();
		
		searchCondition.setTxtValue1(txtInput1.getText());
		searchCondition.setIndexValue(searchIndex);
		searchCondition.setItemValue(searchItem);

		//区分が選択された
		if ("4".equalsIgnoreCase(searchItem)) {
			if (rdKubun1.isSelected()) {
				searchCondition.setTxtValue2("1");
			} else if (rdKubun2.isSelected()) {
				searchCondition.setTxtValue2("2");
			} else {
				searchCondition.setTxtValue2("3");
			}
		} else {
			searchCondition.setTxtValue2(StringUtils.emptyIfNull((String) comInput2.getSelectedItem()).trim());
		}
		
		if (chkIsUsed.isSelected()) {
			searchCondition.setUsed(true);
		} else {
			searchCondition.setUsed(false);
		}
		
		return searchCondition;
	}
	
	@Override
	protected void doRequestComp() {
		setFocusComponent(txtInput1);
	}

    /* (non-Javadoc)
     * @see com.pipe.sapp.base.SearchMasterFrame#getCKey()
     */
    protected String getCKey() {
    	return dspVo.getSchCtl().trim();
    }

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	protected boolean isDisplayWhenInit() {

		if (initSortObj != null && StringUtils.isValid(initSortObj.getStrCmbKey())) {
			comInput2.setSelectedItem(initSortObj.getStrCmbKey());
			comInput2.repaint();
		}
		
		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_CUST");
		if (ctlVo != null) {
			if ("1".equalsIgnoreCase(StringUtils.subStringUseEncode(ctlVo.getCData(), 1, 1))) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.pipe.sapp.base.SearchMasterFrame#getDefaultSearchItem()
	 */
	protected int getDefaultSearchItem() {
		
		isRadioAndColumnTheSame[2] = false;
		
		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_CUST");
		if (ctlVo != null) {
			String strControl = StringUtils.subString(ctlVo.getCData(), 0, 1);
			if ("1".equalsIgnoreCase(strControl)) {
				return 1;
			} else if ("2".equalsIgnoreCase(strControl)) {
				return 2;
			} else if ("3".equalsIgnoreCase(strControl)) {
				return 3;
			} else if ("4".equalsIgnoreCase(strControl)) {
				return 4;
			} else if ("5".equalsIgnoreCase(strControl)) {
				return 5;
			} 
		}
		return 1;
	}

	
	/* (non-Javadoc)
	 * @see com.pipe.sapp.base.SearchMasterFrame#doF9()
	 */
	protected void doF9() {
		super.doF9();
		if (txtInput1.isEnabled()) {
			setFocusComponent(txtInput1);
		} else {
			if (comInput2.isEnabled()) {
				setFocusComponent(comInput2);
			}
		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strKey
	* </DL>
	*/
	public void setDefaultComboValue(String strKey) {
		if (comInput2 != null) {
			comInput2.setSelectedItem(strKey);
			comInput2.repaint();
		}
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
		SearchMCustFrame templte = new SearchMCustFrame();
		templte.pack();
		templte.setVisible(true);
	}
}
