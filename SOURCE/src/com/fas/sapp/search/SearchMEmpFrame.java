/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchTantousyaFrame.java
*
*     記述				：
*     
*     作成日			：2010/02/17   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.sapp.search;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.StringUtils;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.sapp.base.SearchMasterFrame;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;

/**
 * @author PC14
 *
 */
public class SearchMEmpFrame extends SearchMasterFrame {

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

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchMEmpFrame() {
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
	public SearchMEmpFrame(BaseFrame frame) {
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
	public SearchMEmpFrame(BaseFrame frame, String title) {
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
	public SearchMEmpFrame(BaseFrame frame, boolean modal) {
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
	public SearchMEmpFrame(BaseFrame frame,SortObjVo sortVo) {
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
			dspVo = searchService.getMSchDspVo("1");
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
		
		int xPos = 30;
		int yPos = 16;

		lblInput2 = new BaseLabel();
		lblInput2.setBorder(null);
		lblInput2.setText(dspVo.getTtlName1());
		lblInput2.setBackground(pnlFilter.getBackground());
		lblInput2.setSize(100, FaceContants.TEXT_HEIGHT);
		lblInput2.setLocation(xPos, yPos);
		pnlFilter.add(lblInput2);
		
		xPos += 100;
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(new ArrayList<ComboObjectVo>());
		comInput2 = new BaseComboBox(aModel);
		comInput2.setSize(170, FaceContants.TEXT_HEIGHT);
		comInput2.setLocation(xPos, yPos);
		comInput2.setEnabled(false);
		pnlFilter.add(comInput2);
		
		xPos += 170;
		lblInputInfor2 = new BaseLabel();
		lblInputInfor2.setSize(200, FaceContants.TEXT_HEIGHT);
		lblInputInfor2.setLocation(xPos, yPos);
		lblInputInfor2.setBorder(FaceContants.LABEL_BORDER);
		pnlFilter.add(lblInputInfor2);
		
		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE + 7;
		xPos = 30;
		lblInput1 = new BaseLabel();
		lblInput1.setBorder(null);
		lblInput1.setText(dspVo.getTtlName2());
		lblInput1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput1.setBackground(pnlFilter.getBackground());
		lblInput1.setSize(100, FaceContants.TEXT_HEIGHT);
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
		lblInputInfor1.setSize(200, FaceContants.TEXT_HEIGHT);
		lblInputInfor1.setLocation(xPos, yPos);
		lblInputInfor1.setBorder(FaceContants.LABEL_BORDER);
		pnlFilter.add(lblInputInfor1);		

	}

	@Override
	protected void doRd1() {
		rdSchItem1.setSelected(true);
		lblInput1.setText(dspVo.getSchName1());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtInput1);
		searchItem = "1";
	}

	@Override
	protected void doRd2() {
		rdSchItem2.setSelected(true);
		lblInput1.setText(dspVo.getSchName2());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_HIRAGANA);
		setFocusComponent(txtInput1);
		searchItem = "2";
	}

	@Override
	protected void doRd3() {
		rdSchItem3.setSelected(true);
		lblInput1.setText(dspVo.getSchName3());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_HALFKANA);
		setFocusComponent(txtInput1);
		searchItem = "3";
	}

	@Override
	protected void doRd4() {
		rdSchItem4.setSelected(true);
		lblInput1.setText(dspVo.getSchName4());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtInput1);
		searchItem = "4";
	}

	@Override
	protected void doRd5() {
		rdSchItem5.setSelected(true);
		lblInput1.setText(dspVo.getSchName5());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtInput1);
		searchItem = "5";
	}

	@Override
	protected void doRd6() {
		rdSchItem6.setSelected(true);
		lblInput1.setText(dspVo.getSchName6());
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		setFocusComponent(txtInput1);
		searchItem = "6";
	}

	@Override
	protected SearchConditionVo getSearchCondition() {		
		SearchConditionVo searchCondition = new SearchConditionVo();		
		searchCondition.setTxtValue1(txtInput1.getText());
		searchCondition.setIndexValue(searchIndex);
		searchCondition.setItemValue(searchItem);		
		if (chkIsUsed.isSelected()) {
			searchCondition.setUsed(true);
		} else {
			searchCondition.setUsed(false);
		}		
		return searchCondition;
	}
	
	@Override
	protected void doRequestComp() {
		if (comInput2.isEnabled()) {
			comInput2.requestFocus();
		} else {
			txtInput1.requestFocus();
		}
	}

	@Override
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
		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_EMP");
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
		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_EMP");
		if (ctlVo != null) {
			String strControl = StringUtils.subString(ctlVo.getCData(), 0, 1);
			if ("1".equalsIgnoreCase(strControl)) {
				return 1;
			} else if ("2".equalsIgnoreCase(strControl)) {
				return 2;
			} else if ("3".equalsIgnoreCase(strControl)) {
				return 3;
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
	 *
	 * </DL>
	 * @param args
	 */
	public static void main(String args[]) {
		//Use for testing
		SearchMEmpFrame templte = new SearchMEmpFrame();
		templte.pack();
		templte.setVisible(true);
	}	
}
