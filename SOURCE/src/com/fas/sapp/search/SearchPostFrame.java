/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：エスイーシー化成
 * 
 *     ファイル名		：SearchPostFrame.java
 *
 *     記述				：
 *     
 *     作成日			：May 13, 2010   
 *
 *     作成者			：Bui Ngoc Viet
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.sapp.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.jdesktop.swingx.JXBusyLabel;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.sapp.base.SearchMasterFrame;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: Bui Ngoc Viet</DD><BR>
 * <DD></DD>
 * </DL>
 */

public class SearchPostFrame extends SearchMasterFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private BaseLabel lblInput1;
	/** */

	private BaseInputText txtInput1;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param frame
	 * @param title
	 */
	public SearchPostFrame(BaseFrame frame, String title) {
		super(frame, true);
		setTitle(title);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param frame
	 * @param modal
	 */
	public SearchPostFrame(BaseFrame frame, boolean modal) {
		super(frame, modal);
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
	public SearchPostFrame(BaseFrame frame) {
		super(frame, true);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param frame
	 * @param sortVo
	 *            </DL>
	 */
	public SearchPostFrame(BaseFrame frame, SortObjVo sortVo) {
		super(frame, sortVo, true);
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
	public SearchPostFrame() {
		super();
		init();
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
		doRd1();
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

	protected void doF8() {
		if(StringUtils.isValid(txtInput1.getText().trim()) == false)
		{
			MessageBox.message(this, MessageConstants
					.getMstMsgVo("E0005"));
			setFocusComponent(txtInput1);
			return;
		}
		searchIndex = "";
		try {

			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						SplashScreen sp = new SplashScreen();
						sp.setVisible(true);
						sp.setAlwaysOnTop(true);
						Thread.sleep(1000);
						resetData();
						doSearch();
						sp.setVisible(false);
						sp.dispose();
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
				}
			});
			t.start();
		} catch (Exception e) {
		}
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
		return false;
	}

	@Override
	protected MSchDspVo getSchDspVo() {

		MSchDspVo dspVo = null;

		try {
			dspVo = searchService.getMSchDspVo("" + I_M_POST_SEARCH);
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

		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE + 7;
		xPos = 30;
		lblInput1 = new BaseLabel();
		lblInput1.setBorder(null);
		lblInput1.setText(dspVo.getTtlName2() + "  ");
		lblInput1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput1.setBackground(pnlFilter.getBackground());
		lblInput1.setSize(95, FaceContants.TEXT_HEIGHT);
		lblInput1.setLocation(xPos, yPos);
		pnlFilter.add(lblInput1);
		searchItem = "1";

		xPos += 100;
		txtInput1 = new BaseInputText();
		txtInput1.setSize(170, FaceContants.TEXT_HEIGHT);
		txtInput1.setLocation(xPos, yPos);
		pnlFilter.add(txtInput1);
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
		setFocusComponent(txtInput1);
		txtInput1.setText("");
		txtInput1.setIMType(BaseInputText.IM_OFF);
		searchItem = "6";
	}

	@Override
	protected SearchConditionVo getSearchCondition() {

		SearchConditionVo searchCondition = new SearchConditionVo();

		searchCondition.setTxtValue1(txtInput1.getText());
		searchCondition.setIndexValue(searchIndex);
		searchCondition.setItemValue(searchItem);
		searchCondition.setUsed(false);
//		if (chkIsUsed.isSelected()) {
//			searchCondition.setShiyouFuka(true);
//		} else {
//			searchCondition.setShiyouFuka(false);
//		}

		return searchCondition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.sapp.base.SearchMasterFrame#getCKey()
	 */
	protected String getCKey() {
		return dspVo.getSchCtl().trim();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	protected boolean isDisplayWhenInit() {
		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_POST");
		if (ctlVo != null) {
			if ("*".equalsIgnoreCase(StringUtils.subStringUseEncode(ctlVo
					.getCData(), 1, 1))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.sapp.base.SearchMasterFrame#getDefaultSearchItem()
	 */
	protected int getDefaultSearchItem() {
		MCtlVo ctlVo = MCtlConstants.getMCtlVoByCKey("SCH_POST");
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

	@Override
	protected void doRequestComp() {

		txtInput1.requestFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.sapp.base.SearchMasterFrame#doF9()
	 */
	protected void doF9() {
		super.doF9();
		setFocusComponent(txtInput1);
	}
	
	 /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public class SplashScreen extends JWindow {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SplashScreen() {
			JPanel content = (JPanel) getContentPane();
			content.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
			content.setBackground(Color.blue);
			int width = 250;
			int height = 90;
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width - width) / 2;
			int y = (screen.height - height) / 2;
			setBounds(x, y, width, height);
			JXBusyLabel sms = new JXBusyLabel();
			sms.setBusy(true);
			sms.setText("<html><center><font size=5 COLOR=white>只今処理中です。</font></center><center><font size=5 COLOR=white>しばらくお待ちください。</font></center>");
			sms.setHorizontalAlignment(SwingConstants.CENTER);
			content.add(sms,
							BorderLayout.CENTER);
		
		}
	}

}
