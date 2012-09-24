/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchTempalte.java
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
package com.fas.sapp.template;

import com.fas.common.exception.BizException;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.sapp.base.SearchMasterFrame;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;

/**
 * @author PC13
 *
 */
public class SearchTempalte extends SearchMasterFrame {

	/** */
	private static final long serialVersionUID = 1L;

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
	public SearchTempalte(BaseFrame frame, boolean modal) {
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
	public SearchTempalte() {
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
	 * @param frame
	 */
	public SearchTempalte(BaseFrame frame) {
		super(frame, true);
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
		return "";
	}

	public static void main(String args[]) {

		SearchTempalte templte = new SearchTempalte();
		templte.pack();
		templte.setVisible(true);
	}

	@Override
	protected String getSubName() {
		return dspVo.getDspName().trim();
	}

	@Override
	protected void doF8() {
		
	}

	@Override
	protected String getKenSuu() {
		// TODO Auto-generated method stub
		return "1";
	}

	@Override
	protected boolean isShortBtn() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected MSchDspVo getSchDspVo() {

		MSchDspVo dspVo = null;
		
		try {
			dspVo = searchService.getMSchDspVo("7");
		} catch (BizException e) {
		}
		
		return dspVo;
	}

	@Override
	protected void doF7() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doRd1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doRd2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doRd3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doRd4() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doRd5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doRd6() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected SearchConditionVo getSearchCondition() {
		// TODO Auto-generated method stub
		return new SearchConditionVo();
	}

	@Override
	protected void doRequestComp() {
	}
	
    /* (non-Javadoc)
     * @see com.pipe.sapp.base.SearchMasterFrame#getCKey()
     */
    protected String getCKey() {
    	return dspVo.getSchCtl().trim();
    }
}
