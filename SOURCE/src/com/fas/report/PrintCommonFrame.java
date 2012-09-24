/***************************************************************     
 *
 *     プロジェクト名		：
 * 
 *     ファイル名			：PrintCommonFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/07/22
 *
 *     作成者			：QuangPV
 *
 *     備考				：
 *
 ****************************************************************/
package com.fas.report;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.EditConstants;
import com.fas.common.constants.ReportConstants;
import com.fas.common.constants.dbtable.FPrinterConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.label.RequiredLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.HaCellCheckboxEditor;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableModel;
import com.fas.jface.table.TableHeaderRenderer;
import com.fas.jface.text.BaseDatePicker;
import com.fas.jface.text.BaseMonthPicker;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.common.report.PrintCommonService;
import com.fas.service.common.report.PrintCommonServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.menuexe.MenuExeVo;

/**
 * @author QuangPV
 * 
 */

public class PrintCommonFrame extends BasePrintComonFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** Log */
	static Logger logger = Logger.getLogger(PrintCommonFrame.class);
	public static final int UN_SELECTED_DATE = -1;
	
	/** DEFINE CONTROL */
	/** */
	private BaseComboBox cbbTypeReport;	 
	/** */
	private BaseComboBox cbbCustCode;	
	/** */
	private RequiredLabel lblAsteriskShip;
	/** */
	private RequiredLabel lblAsteriskCust;
	/** */
	private RequiredLabel lblAsteriskEmp;
	/** */
	private BaseComboBox cbbShipNo;
	/** */
	private RequiredLabel lblAsteriskSect;
	/** */
	private BaseComboBox cbbSect; 
	/** */
	private EditLabel lblCustCode;
	/** */
	private EditLabel lblShipNo;
	/** */
	private EditLabel lblSect;
	// マーキン入力チェックリスト
	private BaseComboBox cbbEmp;
	// 仕入集計表 - 発注済チェックリスト
	private RequiredLabel lblAsteriskHaChu;
	private EditLabel lblMnDate;
	private BaseDatePicker cbbFromMnDate; 
	private BaseDatePicker cbbToMnDate;
	private EditLabel lblToMnDate;
	
	// 部材集計表(パイプ) - 部材集計表(パイプ以外)
	private EditLabel lblMnDate1;
	private BaseDatePicker cbbFromMnDate1; 
	private BaseDatePicker cbbToMnDate1;
	private EditLabel lblToMnDate1;
	
	// 仕入集計表以外
	private EditLabel lblDlvrDate;
	private EditLabel lblToDlvrDate;
	private EditLabel lblHurryType;
	private EditLabel lblDlvrDate1;
	private EditLabel lblToDlvrDate1;
	private EditLabel lblToObfiDate;
	private EditLabel lblFromObfiDate;
	private BaseComboBox cbbHurry;
	private BaseDatePicker cbbFromDlvrDate; 
	private BaseDatePicker cbbToDlvrDate;
	private BaseDatePicker cbbFromObfiDate;
	private BaseDatePicker cbbToObfiDate;
	private BaseDatePicker cbbFromObfiDate1;
	private BaseDatePicker cbbToObfiDate1;
	private BaseMonthPicker dpClDate;
	private EditLabel lblClDate;
	private RequiredLabel lblAsteriskClDate;
	/** */ 
	private EditLabel lblEmp;
//	/** */
//	private TPipeService pipeService;
//	/** */
//	private MCustService custService;
	/** */
	private SimpleDateFormat sdfPersisting = new SimpleDateFormat("yyyyMMdd");
	/** */
	private SimpleDateFormat sdfShowing = new SimpleDateFormat("yyyy/MM/dd(E)");
	/** Define Table */
	private InspectTable m_table;
	/** */
	private InspectTableModel m_model;
	/** */
	private BaseScrollPane psTable;
	/** */
	private MenuExeVo exeVo ;
	/** Define List object */
//	protected List<PipeHVo> lstData;
	/** Flag */
	protected boolean flgDisable = false;
	protected String oldTypeReport = "";
	/** テーブルヘッダーサイズ */
	private static String colHeadNm[] = {"選択","記号", "納　期", "※","入図日","管理No","管理日"};
	/**
	 * 名称一覧 ヘッダーサイズ
	 */
	private int[] colHeadwidth = { 60,60, 200, 40,0,0,0 };
	/** Row per page */
	private int ROW_PER_PAGE;
	/** Database service */
	private PrintCommonService printService;
	
	/** Parameter for report */
	// 納期
	protected String strDlvrMax = "";
	protected String strDlvrMin = "";
	// 記号
	protected String strSignMax = "";
	protected String strSignMin = "";
	// 入図日
	protected String strObfiMax = "";
	protected String strObfiMin = "";
	
	protected String savePdcls = "";
	private boolean initFlag = true;
	private boolean initFocus = true;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#getHelpInfor()
	 */
	@Override
	protected String getHelpInfor() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#getSubName()
	 */
	@Override
	protected String getSubName() {
		if (exeMenu != null) {
			return StringUtils.emptyIfNull(exeMenu.getMenuexeName());
		} else {
			return "";
		}
	}

	protected void setSubName(String strTitle) {
		this.setTitle(strTitle);
	}
	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD>Init Function</DD> <BR>
	 * 
	 * @param frame
	 * @param title
	 *            </DL>
	 */
	public PrintCommonFrame(BaseFrame frame, String title) {
		super(frame, title);
		initFrame();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD>Init Function</DD> <BR>
	 * 
	 * @param frame
	 *            </DL>
	 */
	public PrintCommonFrame(BaseFrame frame) {
		super(frame);
		initFrame();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	public PrintCommonFrame() {
		super();
		initFrame();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	private void initFrame() {
		
//		pipeService = new TPipeServiceImpl();
		printService = new PrintCommonServiceImpl();
		getHeader();
		getBodyLeft();
		getBodyCenter();
		buildMiddle();
    	setFrameSize();
		CUR_MODE = EditConstants.VIEW_MODE;
		setDispTabFocus();
		//cbbTypeReport.setSelectedIndex(cbbTypeReport.getSelectedIndex());
		setEnable(false);
		btnF8.setEnabled(false);
		setDefaultFirstFocus(cbbCustCode);
	}

	protected void setDispTabFocus() {

		List<Object> focusList = new ArrayList<Object>();		 
		// 仕入集計表 - 発注済チェックリスト 
		if("400200".equalsIgnoreCase(getGrpExeCode())||
				"250200".equalsIgnoreCase(getGrpExeCode())){
			//focusList.add(cbbTypeReport);
			focusList.add(cbbCustCode);
			focusList.add(cbbShipNo);
			focusList.add(cbbSect);
			focusList.add(cbbFromMnDate);
			focusList.add(cbbToMnDate);
			focusList.add(rdoPreview);
			focusList.add(rdoPrint);
			focusList.add(txtNumPage);
		//径別製作数一覧表 
//		}else if("300200".equalsIgnoreCase(getGrpExeCode())){
//			focusList.add(cbbTypeReport);
//			focusList.add(cbbCustCode);
//			focusList.add(txtNumPage);
//			focusList.add(rdoPreview);
//			focusList.add(rdoPrint);
			//focusList.add(cbbShipNo);
		//内作関係ブロック別材料集計表 
		}else if("150240".equalsIgnoreCase(getGrpExeCode())){
			//focusList.add(cbbTypeReport);
			// lblToObfiDate, lblFromObfiDate, cbbFromObfiDate1, cbbToObfiDate1
			focusList.add(cbbCustCode);
			focusList.add(cbbShipNo);
			focusList.add(cbbFromObfiDate1);
			focusList.add(cbbToObfiDate1);
			focusList.add(rdoPreview);
			focusList.add(rdoPrint);
			focusList.add(txtNumPage);
			
		}else{
			//focusList.add(cbbTypeReport);
			focusList.add(cbbCustCode);
			focusList.add(cbbShipNo);
			focusList.add(cbbSect);
			focusList.add(cbbFromDlvrDate);
			focusList.add(cbbToDlvrDate);
			focusList.add(cbbHurry);
			focusList.add(cbbFromObfiDate);
			focusList.add(cbbToObfiDate);
			// マーキン入力チェックリスト 
			if("350200".equalsIgnoreCase(getGrpExeCode())){
				focusList.add(cbbEmp);
			}
			// 部材集計表(パイプ) - 部材集計表(パイプ以外)
			if("150200".equalsIgnoreCase(getGrpExeCode())||
					"150210".equalsIgnoreCase(getGrpExeCode())	){
				focusList.add(cbbFromMnDate1);
				focusList.add(cbbToMnDate1);
			}
			if("300200".equalsIgnoreCase(getGrpExeCode())||
					"300220".equalsIgnoreCase(getGrpExeCode())||
					"300230".equalsIgnoreCase(getGrpExeCode())||
					"300240".equalsIgnoreCase(getGrpExeCode())||
					"300260".equalsIgnoreCase(getGrpExeCode())){
				
				focusList.add(dpClDate);
			}
			focusList.add(rdoPreview);
			focusList.add(rdoPrint);
			focusList.add(txtNumPage);
			focusList.add(m_table);
		}
		
		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}
	
	
	public void Close()
	{
		btnF12.doClick();
	}
	/**
	 * <DL> 
	 * <DT>メソッド記述：</DT>  
	 * <DD></DD> <BR>
	 *  
	 * @return </DL>
	 */
	private boolean validateCombo() {

		boolean isValid = true;

//		String strPdcls = "";
//		try {
//			strPdcls = custService.getPdclsType(cbbCustCode.getSelectedKey()).trim();
//		} catch (BizException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		// 径別製作数一覧表 - マーキン表 -  部材集計表(パイプ) - 部材集計表(パイプ以外)
		if("300200".equalsIgnoreCase(getGrpExeCode())||"350210".equalsIgnoreCase(getGrpExeCode())||
				"150200".equalsIgnoreCase(getGrpExeCode())||"150210".equalsIgnoreCase(getGrpExeCode())){
			// Valid * 10 cbbCustCode
			String strPdcls = "";
//			if(custService==null)custService = new MCustServiceImpl();
//			try {
//				strPdcls = custService.getPdclsType(cbbCustCode.getSelectedKey()).trim();
//			} catch (BizException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			if("0".equalsIgnoreCase(strPdcls)){
				if (isValid && cbbCustCode.getSelectedIndex() <= 0) {
					isValid = false;
				}
				// Valid * 10 cbbSect
				if (isValid && cbbSect.getSelectedIndex() <= 0) {
					isValid = false;
				}
			}else{
				// Valid * 10 cbbCustCode
				if (isValid && cbbCustCode.getSelectedIndex() <= 0) {
					isValid = false;
				}
				
				// Valid * 10 cbbShipNo
				if (isValid && cbbShipNo.getSelectedIndex() <= 0) {
					isValid = false;
				}
				
				// Valid * 10 cbbSect
				if (isValid && cbbSect.getSelectedIndex() <= 0) {
					isValid = false;
				}
			}
			
		// 仕入集計表 - 発注済チェックリスト
		}else if ("400200".equalsIgnoreCase(getGrpExeCode())||
				"250200".equalsIgnoreCase(getGrpExeCode())){
			// Valid * 10 cbbCustCode
			if (isValid && cbbCustCode.getSelectedIndex() <= 0) {
				isValid = false;
			}
			
			// Valid * 10 cbbShipNo
			if (isValid && cbbShipNo.getSelectedIndex() <= 0) {
				isValid = false;
			}
			
			// Valid * 10 cbbSect
			if (isValid && cbbSect.getSelectedIndex() <= 0) {
				isValid = false;
			}
			
			String strDate = cbbFromMnDate.getText();
			if (isValid &&!StringUtils.isValid(strDate)) {
				isValid = false;
			}
			
			strDate = cbbToMnDate.getText();
			if (isValid &&!StringUtils.isValid(strDate)) {
				isValid = false;
			}
		// 内作関係ブロック別材料集計表
		}else if ("150240".equalsIgnoreCase(getGrpExeCode())){
			if (isValid && cbbCustCode.getSelectedIndex() <= 0) {
				isValid = false;
			}
			
			// Valid * 10 cbbShipNo
			if (isValid && cbbShipNo.getSelectedIndex() <= 0) {
				isValid = false;
			}
		}else{
			// Valid * 10 cbbCustCode
			if (isValid && cbbCustCode.getSelectedIndex() <= 0) {
				isValid = false;
			}
			
			// Valid * 10 cbbShipNo
			if (isValid && cbbShipNo.getSelectedIndex() <= 0) {
				isValid = false;
			}
			
			// Valid * 10 cbbSect
			if (isValid && cbbSect.getSelectedIndex() <= 0) {
				isValid = false;
			}
		}
		
		return isValid;
	}
//	
//	/**
//	 * <DL>
//	 * <DT>メソッド記述：</DT>
//	 * <DD></DD> <BR>
//	 * 
//	 * @return </DL>
//	 */
//	@SuppressWarnings("unchecked")
//	private PipeHVo getConditionVo() {
//		PipeHVo pipeVo = new PipeHVo();
//		pipeVo.setCustCode(cbbCustCode.getSelectedKey());
//		pipeVo.setCustName(cbbCustCode.getSelectedValue1());
//		pipeVo.setShipNo(cbbShipNo.getSelectedKey());
//		pipeVo.setDrawType(cbbSect.getSelectedKey());
//		pipeVo.setDrawTypeName(cbbSect.getSelectedValue1());
//		pipeVo.setHurryType(cbbHurry.getSelectedKey());
//		// set for R14
//		// 仕入集計表 - 発注済チェックリスト
//		if("400200".equalsIgnoreCase(getGrpExeCode())||
//				"250200".equalsIgnoreCase(getGrpExeCode())){
//			String strDate = cbbFromMnDate.getText();
//			Date selectedDate = cbbFromMnDate.getDate();
//			if (selectedDate == null || !StringUtils.isValid(strDate)) {
//				pipeVo.setHachuFromDate(UN_SELECTED_DATE);
//			} else {
//				pipeVo.setHachuFromDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//			}
//			strDate = cbbToMnDate.getText();
//			selectedDate = cbbToMnDate.getDate();
//			if (selectedDate == null || !StringUtils.isValid(strDate)) {
//				pipeVo.setHachuToDate(UN_SELECTED_DATE);
//			} else {
//				pipeVo.setHachuToDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//			}
//		// 径別製作数一覧表
////		}else if ("300200".equalsIgnoreCase(getGrpExeCode())){
////			PipeHVo pVo = new PipeHVo();
////			pVo.setCustCode(cbbCustCode.getSelectedKey());
////			return pVo;
//		}else{
//			// delivery date from
//			String strDate = "";
//			Date selectedDate = cbbFromDlvrDate.getDate();
//			strDate = cbbFromDlvrDate.getText();
//			if (selectedDate == null || !StringUtils.isValid(strDate) ) {
//				pipeVo.setDlvrFromDate(UN_SELECTED_DATE);
//			} else {
//				pipeVo.setDlvrFromDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//			}
//
//			// delivery date to
//			strDate = cbbToDlvrDate.getText();
//			selectedDate = cbbToDlvrDate.getDate();
//			if (selectedDate == null || !StringUtils.isValid(strDate)) {
//				pipeVo.setDlvrToDate(UN_SELECTED_DATE);
//			} else {
//				pipeVo.setDlvrToDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//			}
//			
//			
//			// delivery date from
//			strDate = cbbFromObfiDate.getText();
//			selectedDate = cbbFromObfiDate.getDate();
//			if (selectedDate == null || !StringUtils.isValid(strDate)) {
//				pipeVo.setObfiFromDate(UN_SELECTED_DATE);
//			} else {
//				pipeVo.setObfiFromDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//			}
//
//			// delivery date to
//			strDate = cbbToObfiDate.getText();
//			selectedDate = cbbToObfiDate.getDate();
//			if (selectedDate == null || !StringUtils.isValid(strDate)) {
//				pipeVo.setObfiToDate(UN_SELECTED_DATE);
//			} else {
//				pipeVo.setObfiToDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//			}
//			
//			// 内作関係ブロック別材料集計表
//			if("150240".equalsIgnoreCase(getGrpExeCode())){
//				strDate = cbbFromObfiDate1.getText();
//				selectedDate = cbbFromObfiDate1.getDate();
//				if (selectedDate == null || !StringUtils.isValid(strDate)) {
//					pipeVo.setObfiFromDate(UN_SELECTED_DATE);
//				} else {
//					pipeVo.setObfiFromDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//				}
//
//				// delivery date to
//				strDate = cbbToObfiDate1.getText();
//				selectedDate = cbbToObfiDate1.getDate();
//				if (selectedDate == null || !StringUtils.isValid(strDate)) {
//					pipeVo.setObfiToDate(UN_SELECTED_DATE);
//				} else {
//					pipeVo.setObfiToDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//				}
//			}
//			
//			if("150200".equalsIgnoreCase(getGrpExeCode())||
//					"150210".equalsIgnoreCase(getGrpExeCode())){
//				strDate = cbbFromMnDate1.getText();
//				selectedDate = cbbFromMnDate1.getDate();
//				if (selectedDate == null || !StringUtils.isValid(strDate)) {
//					pipeVo.setHachuFromDate(UN_SELECTED_DATE);
//				} else {
//					pipeVo.setHachuFromDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//				}
//				strDate = cbbToMnDate1.getText();
//				selectedDate = cbbToMnDate1.getDate();
//				if (selectedDate == null || !StringUtils.isValid(strDate)) {
//					pipeVo.setHachuToDate(UN_SELECTED_DATE);
//				} else {
//					pipeVo.setHachuToDate(NumberUtils.getIntFromString(sdfPersisting.format(selectedDate)));
//				}
//			}
//
//			// get data display in table
//			List<List<Object>> tableData = m_model.getData();
//			List listSignNo = new ArrayList();
//			List listMnNo = new ArrayList();    
//			List listMnDate = new ArrayList();  
//			for(int i = 0 ; i < tableData.size(); i++ )
//			{
//				boolean bChecked = Boolean.parseBoolean( tableData.get(i).get(0).toString() );
//				if( bChecked  == true )
//				{
//					listSignNo.add(tableData.get(i).get(1));
//					listMnNo.add(tableData.get(i).get(5));
//					listMnDate.add(tableData.get(i).get(6));
//				}
//			}
//			
//			pipeVo.setListSignNo(listSignNo);
//			pipeVo.setListMnNo(listMnNo);
//			pipeVo.setListMnDate(listMnDate);
//		}
//
//		return pipeVo;
//	}
//	
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
	private class TableMouseClick implements MouseListener {

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			int selectedRow = m_table.getSelectedRow();

			if (m_table.getRowCount() < 1)
				return;

			if (e.getClickCount() == 1) {
				doSingleClick(selectedRow);
			} else {
				doDoubleClick(selectedRow);
			}
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>
	 * Get data from Service to bind to table 1. Get value SCH from table
	 * M_CTL ( the 1st character) 2. Get data from Service to lstData (full
	 * Columns) 3. Take some columns to bind to list of Table in form</DD> <BR>
	 * 
	 * @return </DL>
	 */
	@SuppressWarnings("unchecked")
	private List getTableData() {
		// get data for table
		if (printService == null)
			printService = new PrintCommonServiceImpl();
//		try {
//			// Get list data from Service
//			lstData = printService.getDataTablePipeHVo(getConditionVo());
//		} catch (BizException e1) {
//			e1.printStackTrace();
//		}
//		if(lstData == null)return null;
		List retList = new ArrayList();
		// 納期別作業表
//		if("200200".equalsIgnoreCase(getGrpExeCode())){
//			for (int i = 0; i < lstData.size(); i++) {
//				if (!"9".equalsIgnoreCase(StringUtils.emptyIfNull(lstData.get(i).getCompType()).trim())){
//					List list = new ArrayList();
//					list.add(true); 
//					list.add(lstData.get(i).getSignNo()); 
//					try {
//						String strTempDate = lstData.get(i).getDlvrDate()+"";
//						if(strTempDate.length()== 8){
//							list.add(sdfShowing.format(sdfPersisting.parse(strTempDate)));
//						}else{
//							list.add(StringUtils.emptyIfNull(lstData.get(i).getDlvrDateName()));
//						}
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					list.add("");
//					
//					list.add(lstData.get(i).getObfiDate()); 
//		
//					retList.add(list);
//				}
//			}
//			// 重量集計表(全て),重量集計表(処理後),重量集計表(自社),径別製作数一覧表 
//		if("300230".equalsIgnoreCase(getGrpExeCode())
//				||"300240".equalsIgnoreCase(getGrpExeCode())
//				||"300220".equalsIgnoreCase(getGrpExeCode())){
//				//||"300200".equalsIgnoreCase(getGrpExeCode())){
//				//|| "請求明細表(顧客提出)".equalsIgnoreCase((String)cbbTypeReport.getSelectedItem())
//				//|| "請求明細表(自社)".equalsIgnoreCase((String)cbbTypeReport.getSelectedItem())){
//			
//			for (int i = 0; i < lstData.size(); i++) {
//				if (!"9".equalsIgnoreCase(StringUtils.emptyIfNull(lstData.get(i).getCompType()).trim())){
//					List list = new ArrayList();
//					list.add(true); 
//					list.add(lstData.get(i).getSignNo()); 
//					try {
//						String strTempDate = lstData.get(i).getDlvrDate()+"";
//						if(strTempDate.length()== 8){
//							list.add(sdfShowing.format(sdfPersisting.parse(strTempDate)));
//						}else{
//							list.add(StringUtils.emptyIfNull(lstData.get(i).getDlvrDateName()));
//						}
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					//list.add(MCtlConstants.getValueByCKey("COMP_LBL").trim());
//					list.add("");
//					list.add(lstData.get(i).getObfiDate()); 
//					
//					list.add(lstData.get(i).getMnNo());
//					list.add(lstData.get(i).getMnDate());
//		
//					retList.add(list);
//				}
//			}
//		// 他の場合
//		}else{
//			for (int i = 0; i < lstData.size(); i++) {
//				List list = new ArrayList();
//				list.add(true); 
//				list.add(lstData.get(i).getSignNo()); 
//				try {
//					String strTempDate = lstData.get(i).getDlvrDate()+"";
//					if(strTempDate.length()== 8){
//						list.add(sdfShowing.format(sdfPersisting.parse(strTempDate)));
//					}else{
//						list.add(StringUtils.emptyIfNull(lstData.get(i).getDlvrDateName()));
//					}
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				if ("9".equalsIgnoreCase(StringUtils.emptyIfNull(lstData.get(i).getCompType()).trim()))
//				{
//					list.add(MCtlConstants.getValueByCKey("COMP_LBL").trim());
//				} else {
//					list.add("");
//				}
//	
//				list.add(lstData.get(i).getObfiDate()); 
//				
//				retList.add(list);
//				if (!"9".equalsIgnoreCase(StringUtils.emptyIfNull(lstData.get(i).getCompType()).trim())){
//					List list = new ArrayList();
//					list.add(true); 
//					list.add(lstData.get(i).getSignNo()); 
//					try {
//						String strTempDate = lstData.get(i).getDlvrDate()+"";
//						if(strTempDate.length()== 8){
//							list.add(sdfShowing.format(sdfPersisting.parse(strTempDate)));
//						}else{
//							list.add(StringUtils.emptyIfNull(lstData.get(i).getDlvrDateName()));
//						}
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					list.add("");
//					
//					list.add(lstData.get(i).getObfiDate()); 
//					
//					list.add(lstData.get(i).getMnNo());
//					
//					list.add(lstData.get(i).getMnDate());
//					
//					retList.add(list);
//				}
//			}
//		}
		
		return retList;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @return </DL>
	 */
	private BaseScrollPane getTable() {
		BaseScrollPane tablePnl;
		m_model = new InspectTableModel(colHeadNm);
		m_table = new InspectTable(m_model) {
			private static final long serialVersionUID = 1L;

			private HaEditableCellRenderer renderer = new HaEditableCellRenderer();

			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			private HaCellCheckboxEditor chkEditor = new HaCellCheckboxEditor(new BaseCheckBox(), m_table);
			
			
			public TableCellEditor getCellEditor(int row, int column) {
				if (column == 0) {
					return chkEditor;
				} 
				return null;
			}
			
			public boolean isCellEditable(int row, int column) {
				return (column == 0);
			}
		};
		// set data for spread
//		m_model.setData(new ArrayList<PipeHVo>());
		
		// COLUMN setting
		TableHeaderRenderer cellRenderer = new TableHeaderRenderer();
		cellRenderer.setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		cellRenderer.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
		m_table.getColumn(0).setHeaderRenderer(cellRenderer);
		
		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_table.getColumnModel();
		int length = colHeadwidth.length;
		for (int i = 0; i < length; i++) {
			defModel.getColumn(i).setPreferredWidth(colHeadwidth[i]);
			if (i != 2) {
				defModel.getColumn(i).setMinWidth(colHeadwidth[i]);
				defModel.getColumn(i).setMaxWidth(colHeadwidth[i] + 50);
			}
		}
		defModel.getColumn(4).setPreferredWidth(0);
		defModel.getColumn(4).setMaxWidth(0);
		defModel.getColumn(5).setPreferredWidth(0);
		defModel.getColumn(5).setMaxWidth(0);
		defModel.getColumn(6).setPreferredWidth(0);
		defModel.getColumn(6).setMaxWidth(0);
		//attachF8(m_table, true);
		attachF2(m_table);
		attachF3(m_table);
		m_table.getTableHeader().setReorderingAllowed(false);
		// add action for table: UP, DOWN,...
		//initTableAction(m_table);
		 initTableEvent();
		// Set tab for table to control
		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					cbbTypeReport.requestFocus();
				} else {
					m_table.requestFocus();
				}
			}
		};

		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabKey");
		m_table.getActionMap().put("tabKey", tabKey);
		AbstractAction shiftTabKey = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				rdoPrint.requestFocus();
			}
		};
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_table.getActionMap().put("shiftTab", shiftTabKey);
		AbstractAction upArrowKey = new AbstractAction() {

			/** */
			private static final long serialVersionUID = 1500756781871087076L;

			public void actionPerformed(ActionEvent e) {
				int row = m_table.getSelectedRow();
				if (0 == row) {
					row = m_table.getRowCount() - 1;
				} else {
					row = row - 1;
				}
				//int columnIndex = m_table.getSelectedColumn();
				if (row >= 0 && row < m_table.getRowCount()) {
					m_table.changeSelection(row, 0, false, false);
				}
			}
		};
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upArrowKey");
		m_table.getActionMap().put("upArrowKey", upArrowKey);
		AbstractAction downArrowKey = new AbstractAction() {

			/** */
			private static final long serialVersionUID = -790220600770561628L;

			public void actionPerformed(ActionEvent e) {
				int row = m_table.getSelectedRow();
				if (m_table.getRowCount() - 1 == row) {
					row = 0;
				} else {
					row = row + 1;
				}
				//int columnIndex = m_table.getSelectedColumn();
				if (row >= 0 && row < m_table.getRowCount()) {
					m_table.changeSelection(row, 0, false, false);
				}
			}
		};
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downArrowKey");
		m_table.getActionMap().put("downArrowKey", downArrowKey);		
		// EVENT
		// mouse click
		m_table.addMouseListener(new TableMouseClick());

		// Not allow change index of Columns
		m_table.getTableHeader().setReorderingAllowed(false);
		// change selected row
		ListSelectionModel rowSM = m_table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});

		tablePnl = new BaseScrollPane(m_table);
		tablePnl.setBorder(FaceContants.PANEL_BORDER);
		tablePnl
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		m_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return tablePnl;
	}
	
	// EVENT for table
	@SuppressWarnings("serial")
	private void initTableEvent(){    
		
		// DO F8
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "doF8");
		m_table.getActionMap().put("doF8", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doF8();
			}
		});
	    // -> RIGHT KEY
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightKey");
	    m_table.getActionMap().put("rightKey", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (m_table.getRowCount() > 0) {
					int row = m_table.getSelectedRow();
					m_table.changeSelection(row, 0, false, false);
				}
			}
		});	    

	    //<- LEFT KEY
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftKey");
	    m_table.getActionMap().put("leftKey", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (m_table.getRowCount() > 0) {
					int row = m_table.getSelectedRow();
					m_table.changeSelection(row, 0, false, false);
				}
			}
		});
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#initHeader(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getHeader() {
		int X_WIDTH = 110;
		int yPos = 10;
		int xPos = 15;
		int TXT_TEXT_FIELD_LENGTH = 315;
		
		BasePanel selectPanel = new BasePanel();
		selectPanel.setBorder(new TitledBorder("帳票選択"));
		selectPanel.setBounds(new Rectangle(5, 5, 785, 2*FaceContants.LABLE_HEIGHT + 10));
		
		RequiredLabel lblAsteriskStdCode = new RequiredLabel("*");
		lblAsteriskStdCode.setBounds(new Rectangle(xPos - 10, 2*yPos, 10,
				FaceContants.LABLE_HEIGHT));
		selectPanel.add(lblAsteriskStdCode);
		
		EditLabel lblSelectReport = new EditLabel("出力帳票");
		lblSelectReport.setBounds(new Rectangle(xPos, 2*yPos, X_WIDTH , FaceContants.LABLE_HEIGHT));
		selectPanel.add(lblSelectReport);
		
		List<ComboObjectVo> lstDataMenuPrt = null;
		
		ComboService comService = new ComboServiceImpl();
		try {
			lstDataMenuPrt = comService.getMenuPrt();
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstDataMenuPrt = new ArrayList<ComboObjectVo>();
		}
		ComboObjectVo dataVo = new ComboObjectVo();
		dataVo.setCode("");
		dataVo.setValue1("");
		lstDataMenuPrt.add(0, dataVo);
		cbbTypeReport = new BaseComboBox(ApplicationUtils.createComboDataModel(lstDataMenuPrt, 42, 30,ArrayListComboBoxModel.CODE_SHOW_TYPE));
		cbbTypeReport.setToolTipText( "出力帳票を選択して下さい。");
		cbbTypeReport.setLocation(xPos + X_WIDTH, 2*yPos);
		cbbTypeReport.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH, FaceContants.LABLE_HEIGHT));
		cbbTypeReport.setPopupWidth(TXT_TEXT_FIELD_LENGTH);
		final List<ComboObjectVo> lstData = lstDataMenuPrt;
		
//		// set default value for 出力方法
//		cbbTypeReport.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				String strProc = "";
//				String strKey ="";
//				String strMenuPrt =  StringUtils.emptyIfNull((String)cbbTypeReport.getSelectedItem());
//				// マーキン入力チェックリスト
//				if("350200".equalsIgnoreCase(getGrpExeCode())){
//					//pnlLeft.add(lblAsteriskEmp);
//					pnlLeft.add(cbbEmp);
//					pnlLeft.add(lblEmp);
//					pnlLeft.add(lblShipNo);
//					pnlLeft.add(lblSect);
//					pnlLeft.add(lblAsteriskShip);
//					pnlLeft.add(cbbShipNo);
//					pnlLeft.add(lblAsteriskSect);
//					pnlLeft.add(cbbSect);
//					pnlLeft.add(lblDlvrDate);
//					pnlLeft.add(lblToDlvrDate);
//					pnlLeft.add(lblHurryType);
//					pnlLeft.add(lblDlvrDate1);
//					pnlLeft.add(lblToDlvrDate1);
//					pnlLeft.add(cbbHurry);
//					pnlLeft.add(cbbFromDlvrDate);
//					pnlLeft.add(cbbToDlvrDate);
//					pnlLeft.add(cbbFromObfiDate);
//					pnlLeft.add(cbbToObfiDate);
//					pnlLeft.remove(lblMnDate1);
//					pnlLeft.remove(cbbFromMnDate1);
//					pnlLeft.remove(lblToMnDate1);
//					pnlLeft.remove(cbbToMnDate1);
//					
//					pnlLeft.remove(lblToObfiDate);
//					pnlLeft.remove(lblFromObfiDate);
//					pnlLeft.remove(cbbFromObfiDate1);
//					pnlLeft.remove(cbbToObfiDate1);
//					
//					pnlLeft.remove(lblAsteriskClDate);
//					pnlLeft.remove(lblClDate);
//					pnlLeft.remove(dpClDate);
//					
//					pnlCenter.setAlpha(1f);
//					//if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INT_PEMP").trim())) {
//						//cbbEmp.setSelectedItem(ApplicationConstants.getLoginUser().getUserId());
////						String strLogin = ApplicationConstants.getLoginUser().getUserId();
////						int iCount = 0;
////						List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbbEmp.getModel()).getAnArrayList();
////						if (lstDataCombo != null) {
////							if (StringUtils.isValid(strLogin)) {
////								for (ComboObjectVo obj : lstDataCombo) {
////									if (strLogin.equalsIgnoreCase(StringUtils.trimAll(obj.getValue3()))) {
////										cbbEmp.setSelectedIndex(iCount);
////										cbbEmp.repaint();
////										break;
////									}
////									iCount++;
////								}
////							}
////						}
//					//}
//					cbbEmp.setSelectedIndex(0);
//					pnlLeft.repaint();
//				// 部材集計表(パイプ以外) - 部材集計表(パイプ)
//				}else if("150200".equalsIgnoreCase(getGrpExeCode())||
//						"150210".equalsIgnoreCase(getGrpExeCode())){
//					//pnlLeft.remove(lblAsteriskEmp);
//					pnlLeft.remove(cbbEmp);
//					pnlLeft.remove(lblEmp);
//					pnlLeft.remove(lblAsteriskHaChu);
//					pnlLeft.remove(lblMnDate);
//					pnlLeft.remove(cbbFromMnDate);
//					pnlLeft.remove(lblToMnDate);
//					pnlLeft.remove(cbbToMnDate);
//					pnlLeft.add(lblShipNo);
//					pnlLeft.add(lblSect);
//					pnlLeft.add(lblAsteriskShip);
//					pnlLeft.add(cbbShipNo);
//					pnlLeft.add(lblAsteriskSect);
//					pnlLeft.add(cbbSect);
//					pnlLeft.add(lblDlvrDate);
//					pnlLeft.add(lblToDlvrDate);
//					pnlLeft.add(lblHurryType);
//					pnlLeft.add(lblDlvrDate1);
//					pnlLeft.add(lblToDlvrDate1);
//					pnlLeft.add(cbbHurry);
//					pnlLeft.add(cbbFromDlvrDate);
//					pnlLeft.add(cbbToDlvrDate);
//					pnlLeft.add(cbbFromObfiDate);
//					pnlLeft.add(cbbToObfiDate);	
//					pnlLeft.add(lblMnDate1);
//					pnlLeft.add(cbbFromMnDate1);
//					pnlLeft.add(lblToMnDate1);
//					pnlLeft.add(cbbToMnDate1);
//					
//					pnlLeft.remove(lblToObfiDate);
//					pnlLeft.remove(lblFromObfiDate);
//					pnlLeft.remove(cbbFromObfiDate1);
//					pnlLeft.remove(cbbToObfiDate1);
//					
//					pnlLeft.remove(lblAsteriskClDate);
//					pnlLeft.remove(lblClDate);
//					pnlLeft.remove(dpClDate);
//					
//					pnlCenter.setAlpha(1f);
//					pnlLeft.repaint();
//				}else if("400200".equalsIgnoreCase(getGrpExeCode())||
//						"250200".equalsIgnoreCase(getGrpExeCode())){
//				// set for R14
//				// 仕入集計表 - 発注済チェックリスト
//					pnlLeft.remove(lblDlvrDate);
//					pnlLeft.remove(lblToDlvrDate);
//					pnlLeft.remove(lblHurryType);
//					pnlLeft.remove(lblDlvrDate1);
//					pnlLeft.remove(lblToDlvrDate1);
//					pnlLeft.remove(cbbHurry);
//					pnlLeft.remove(cbbFromDlvrDate);
//					pnlLeft.remove(cbbToDlvrDate);
//					pnlLeft.remove(cbbFromObfiDate);
//					pnlLeft.remove(cbbToObfiDate);
//					pnlLeft.add(lblShipNo);
//					pnlLeft.add(lblSect);
//					pnlLeft.add(lblAsteriskShip);
//					pnlLeft.add(cbbShipNo);
//					pnlLeft.add(lblAsteriskSect);
//					pnlLeft.add(cbbSect);
//					pnlLeft.add(lblAsteriskHaChu);
//					pnlLeft.add(lblMnDate);
//					pnlLeft.add(cbbFromMnDate);
//					pnlLeft.add(lblToMnDate);
//					pnlLeft.add(cbbToMnDate);
//					//pnlLeft.remove(lblAsteriskEmp);
//					pnlLeft.remove(cbbEmp);
//					pnlLeft.remove(lblEmp);
//					pnlLeft.remove(lblMnDate1);
//					pnlLeft.remove(cbbFromMnDate1);
//					pnlLeft.remove(lblToMnDate1);
//					pnlLeft.remove(cbbToMnDate1);
//					
//					pnlLeft.remove(lblToObfiDate);
//					pnlLeft.remove(lblFromObfiDate);
//					pnlLeft.remove(cbbFromObfiDate1);
//					pnlLeft.remove(cbbToObfiDate1);
//					
//					pnlLeft.remove(lblAsteriskClDate);
//					pnlLeft.remove(lblClDate);
//					pnlLeft.remove(dpClDate);
//					
//					pnlCenter.setAlpha(0.5f);
//					pnlLeft.repaint();
//					
//				// 内作関係ブロック別材料集計表
//				} else if("150240".equalsIgnoreCase(getGrpExeCode())){
//					pnlLeft.remove(lblSect);
//					pnlLeft.remove(lblAsteriskSect);
//					pnlLeft.remove(cbbSect);
//					pnlLeft.remove(lblDlvrDate);
//					pnlLeft.remove(lblToDlvrDate);
//					pnlLeft.remove(lblHurryType);
//					pnlLeft.remove(lblDlvrDate1);
//					pnlLeft.remove(lblToDlvrDate1);
//					pnlLeft.remove(cbbHurry);
//					pnlLeft.remove(cbbFromDlvrDate);
//					pnlLeft.remove(cbbToDlvrDate);
//					pnlLeft.remove(cbbFromObfiDate);
//					pnlLeft.remove(cbbToObfiDate);
//					pnlLeft.remove(lblAsteriskHaChu);
//					pnlLeft.remove(lblMnDate);
//					pnlLeft.remove(cbbFromMnDate);
//					pnlLeft.remove(lblToMnDate);
//					pnlLeft.remove(cbbToMnDate);
//					pnlLeft.add(lblShipNo);
//					pnlLeft.add(lblAsteriskShip);
//					pnlLeft.add(cbbShipNo);
//					//pnlLeft.remove(lblAsteriskEmp);
//					pnlLeft.remove(cbbEmp);
//					pnlLeft.remove(lblEmp);
//					pnlLeft.remove(lblMnDate1);
//					pnlLeft.remove(cbbFromMnDate1);
//					pnlLeft.remove(lblToMnDate1);
//					pnlLeft.remove(cbbToMnDate1);
//					pnlLeft.remove(lblAsteriskClDate);
//					pnlLeft.remove(lblClDate);
//					pnlLeft.remove(dpClDate);
//					//pnlCenter.setAlpha(0.5f);
//					pnlLeft.add(lblToObfiDate);
//					pnlLeft.add(lblFromObfiDate);
//					pnlLeft.add(cbbFromObfiDate1);
//					pnlLeft.add(cbbToObfiDate1);
//					pnlCenter.setAlpha(1f);
//					pnlLeft.repaint();
//				}else{
//					pnlLeft.remove(lblAsteriskHaChu);
//					pnlLeft.remove(lblMnDate);
//					pnlLeft.remove(cbbFromMnDate);
//					pnlLeft.remove(cbbToMnDate);
//					pnlLeft.remove(lblToMnDate);
//					pnlLeft.add(lblShipNo);
//					pnlLeft.add(lblSect);
//					pnlLeft.add(lblAsteriskShip);
//					pnlLeft.add(cbbShipNo);
//					pnlLeft.add(lblAsteriskSect);
//					pnlLeft.add(cbbSect);
//					pnlLeft.add(lblDlvrDate);
//					pnlLeft.add(lblToDlvrDate);
//					pnlLeft.add(lblHurryType);
//					pnlLeft.add(lblDlvrDate1);
//					pnlLeft.add(lblToDlvrDate1);
//					pnlLeft.add(cbbHurry);
//					pnlLeft.add(cbbFromDlvrDate);
//					pnlLeft.add(cbbToDlvrDate);
//					pnlLeft.add(cbbFromObfiDate);
//					pnlLeft.add(cbbToObfiDate);
//					if("300200".equalsIgnoreCase(getGrpExeCode())||
//							"300220".equalsIgnoreCase(getGrpExeCode())||
//							"300230".equalsIgnoreCase(getGrpExeCode())||
//							"300240".equalsIgnoreCase(getGrpExeCode())||
//							"300260".equalsIgnoreCase(getGrpExeCode())){
//						pnlLeft.add(lblAsteriskClDate);
//						pnlLeft.add(lblClDate);
//						pnlLeft.add(dpClDate);
//						//dpClDate.setDate(new Date());
//					}else{
//						pnlLeft.remove(lblAsteriskClDate);
//						pnlLeft.remove(lblClDate);
//						pnlLeft.remove(dpClDate);
//					}
//					
//					//pnlLeft.remove(lblAsteriskEmp);
//					pnlLeft.remove(cbbEmp);
//					pnlLeft.remove(lblEmp);
//					pnlLeft.remove(lblMnDate);
//					pnlLeft.remove(cbbFromMnDate);
//					pnlLeft.remove(lblToMnDate);
//					pnlLeft.remove(cbbToMnDate);
//					pnlLeft.remove(lblMnDate1);
//					pnlLeft.remove(cbbFromMnDate1);
//					pnlLeft.remove(lblToMnDate1);
//					pnlLeft.remove(cbbToMnDate1);
//					pnlLeft.remove(lblToObfiDate);
//					pnlLeft.remove(lblFromObfiDate);
//					pnlLeft.remove(cbbFromObfiDate1);
//					pnlLeft.remove(cbbToObfiDate1);
//					pnlCenter.setAlpha(1f);
//					pnlLeft.repaint();
//				}
//				// Reset 処理
//				int iCount = 0;
//				//部材集計表(パイプ)
//				if("150200".equalsIgnoreCase(getGrpExeCode())||
//						"150220".equalsIgnoreCase(getGrpExeCode())||
//						"150230".equalsIgnoreCase(getGrpExeCode())||
//						"150210".equalsIgnoreCase(getGrpExeCode())){
//					if("150200".equalsIgnoreCase(oldTypeReport)||
//							"150220".equalsIgnoreCase(oldTypeReport)||
//							"150230".equalsIgnoreCase(oldTypeReport)||
//							"150210".equalsIgnoreCase(oldTypeReport)){
//						iCount = 1;
//					}
//				}else if("300230".equalsIgnoreCase(getGrpExeCode())||
//						"300240".equalsIgnoreCase(getGrpExeCode())||
//						"300220".equalsIgnoreCase(getGrpExeCode())){		
//					if("300230".equalsIgnoreCase(oldTypeReport)||
//							"300240".equalsIgnoreCase(oldTypeReport)||
//							"300220".equalsIgnoreCase(oldTypeReport)){
//						iCount = 1;
//					}
//				}else if("350200".equalsIgnoreCase(getGrpExeCode())||
//						"350210".equalsIgnoreCase(getGrpExeCode())){
//					if("350200".equalsIgnoreCase(oldTypeReport)||
//							"350210".equalsIgnoreCase(oldTypeReport)){
//						iCount = 1;
//					}
//				}else if(getGrpExeCode().equalsIgnoreCase(oldTypeReport)){
//					iCount = 1;
//				}
//				
//				if(iCount == 0){
//					resetData();
//				}
//				
//				//
//				setDispTabFocus();
//				if(strMenuPrt.isEmpty()){
//					btnF8.setEnabled(false);
//					return;
//				}
//				if(lstData == null)return;
//				for(int i =0;i < lstData.size(); i++){
//					if(strMenuPrt.equalsIgnoreCase(lstData.get(i).getCode())){
//						strProc = lstData.get(i).getValue4();
//						strKey = ApplicationConstants.getLoginUser().getUserUser()+
//						                          lstData.get(i).getValue2().trim()+ lstData.get(i).getValue3().trim();
//					}
//				}
//				
//				if(StringUtils.isValid(strProc))strProc = StringUtils.subString(strProc, 1);
//				if("0".equalsIgnoreCase(strProc)){
//					rdoPreview.setSelected(true);
//				}else if("1".equalsIgnoreCase(strProc)){
//					rdoPrint.setSelected(true);
//				}else{
//					rdoPreview.setSelected(false);
//					rdoPrint.setSelected(false);
//				}
//				setSubName(strMenuPrt);
//				
//				// set 権限
//				Exectl2Vo exec2Vo = PermissionPolicy.MAP_EXECTL2VO.get(strKey);
//				if(PermissionPolicy.MNEXE_VIEW_MN_PERMISSION.equalsIgnoreCase(exec2Vo.getControlType().trim())){
//					btnF8.setEnabled(false);
//					flgDisable = false;
//				}else{
//					if (validateCombo()){
//						if(initFlag){
//							btnF8.setEnabled(false);
//							initFlag = false;
//						}
//						else 
//							btnF8.setEnabled(true);
//						
//					}
//						
//					flgDisable = true;
//				}
//				//setDefaultFirstFocus(cbbCustCode);
//				oldTypeReport =  getGrpExeCode();
//				
//				// set disable F10
//				if("250200".equalsIgnoreCase(getGrpExeCode())||
//						"400200".equalsIgnoreCase(getGrpExeCode())){
//					btnF10.setEnabled(false);
//				}else{
//					btnF10.setEnabled(true);
//				}
//				// 径別製作数一覧表 - マーキン表
//				// 部材集計表(パイプ) - 部材集計表(パイプ以外)
//				if("300200".equalsIgnoreCase(getGrpExeCode())||"350210".equalsIgnoreCase(getGrpExeCode())||
//						"150200".equalsIgnoreCase(getGrpExeCode())||"150210".equalsIgnoreCase(getGrpExeCode())){
//					custService = new MCustServiceImpl();
//					String strPdcls = "";
//					try {
//						strPdcls = custService.getPdclsType(cbbCustCode.getSelectedKey()).trim();
//					} catch (BizException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					if("0".equalsIgnoreCase(strPdcls)){
//						savePdcls = "0";
//						//pnlLeft.remove(lblAsteriskCust);
//						pnlLeft.remove(lblAsteriskShip);;
//						lblAsteriskShip.repaint();
//					    return;
//					}else if("1".equalsIgnoreCase(strPdcls)){
//						savePdcls = "1";
//						pnlLeft.add(lblAsteriskShip);
//						lblAsteriskShip.repaint();
//					}
//				}
//			}
//		});
		
		selectPanel.add(cbbTypeReport);
		
		headerPnl.add(selectPanel);
		headerPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH , Y_HEADER_LENGTH + 20));
		headerPnl.setLayout(new BorderLayout());
		mainPanel.add(headerPnl, BorderLayout.PAGE_START);		

		return headerPnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initLeftBody(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getBodyLeft() {
		
		int yPos = 20;
	    int xPos = 15;
		final int I_LBL_LENGTH = 110;	
		final String TOOLTIP_SUBFIX_COMBO = "を選択して下さい。";
		final int TEXT_FIELD_WIDTH = 130;
		final int ADDED_WIDTH_DATE = 15;
		final int FIELDS_SPACE = 25;
		final int COMBO_POPUP_WIDTH = 315;
		
		lblAsteriskCust = new RequiredLabel("*");
		lblAsteriskCust.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskCust);
		lblCustCode = new EditLabel("得意先");		
		lblCustCode.setLocation(xPos, yPos);
		lblCustCode.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblCustCode);

//		List<ComboObjectVo> listCust = null;
//		try {
//			MCustService custService = new MCustServiceImpl();
//			listCust = custService.getCustForCombobox();
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			listCust = new ArrayList<ComboObjectVo>();
//		}
		
		List<ComboObjectVo> listCust = null;			
		ComboService cboService = new ComboServiceImpl();
//			listCust = cboService.getLstMCustName();	
		//MCustService custService = new MCustServiceImpl();
		//lstDataCustCode = custService.getCustForCombobox();
		ComboObjectVo blankCustCode = new ComboObjectVo();
		blankCustCode.setCode("");
		blankCustCode.setValue1("");
		listCust.add(0, blankCustCode);	
		
		cbbCustCode = new BaseComboBox(ApplicationUtils.createComboDataModel(listCust, 9, 30, ArrayListComboBoxModel.VALUE1_SHOW_TYPE));
		cbbCustCode.setToolTipText( StringUtils.trimAllVN( lblCustCode.getText()  ) + "を選択して下さい。");
		cbbCustCode.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbCustCode.setSize(new Dimension(COMBO_POPUP_WIDTH, FaceContants.LABLE_HEIGHT));
		cbbCustCode.setPopupWidth(COMBO_POPUP_WIDTH);
		// default data for mcls combobox
		final List<ComboObjectVo> defaultOptions = new ArrayList<ComboObjectVo>();
		ComboObjectVo mclsOption = new ComboObjectVo();
		mclsOption.setCode("");
		mclsOption.setValue1("");
		defaultOptions.add(0, mclsOption);
		
//		//change ship-no combo-box every customer is changed
//		cbbCustCode.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					if (cbbCustCode.getSelectedIndex() == 0) {
//						cbbShipNo.setModel(ApplicationUtils.createComboDataModel(defaultOptions, 25, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
//					} else {
//						List<ComboObjectVo> options = null;
//						try {
//							options = pipeService.getShipNoByCustomer(cbbCustCode.getSelectedKey());
//							options.add(0, defaultOptions.get(0));
//							cbbShipNo.setModel(ApplicationUtils.createComboDataModel(options, 25, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
//						} catch (BizException ex) {
//							logger.error(ex.getMessage());
//							cbbShipNo.setModel(ApplicationUtils.createComboDataModel(defaultOptions, 25, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
//						}
//					}
//				}
//				
//				// 径別製作数一覧表 - マーキン表 -  部材集計表(パイプ) - 部材集計表(パイプ以外)
//				if("300200".equalsIgnoreCase(getGrpExeCode())||"350210".equalsIgnoreCase(getGrpExeCode())||
//						"150200".equalsIgnoreCase(getGrpExeCode())||"150210".equalsIgnoreCase(getGrpExeCode())){
//					custService = new MCustServiceImpl();
//					String strPdcls = "";
//					try {
//						strPdcls = custService.getPdclsType(cbbCustCode.getSelectedKey()).trim();
//					} catch (BizException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					if("0".equalsIgnoreCase(strPdcls)){
//						savePdcls = "0";
//						//pnlLeft.remove(lblAsteriskCust);
//						pnlLeft.remove(lblAsteriskShip);
//						//pnlLeft.remove(lblAsteriskSect);
//						//lblAsteriskCust.repaint();
//						lblAsteriskShip.repaint();
//						//lblAsteriskSect.repaint();
//						if (cbbCustCode.getSelectedIndex() > 0 && cbbSect.getSelectedIndex() > 0) {
//							btnF8.setEnabled(true);
//							m_model.setData(getTableData());
//							m_model.fireTableDataChanged();
//							m_table.repaint();
//							setEnable(true);
//						}else{
//							btnF8.setEnabled(false);
//							setEnable(false);
//						}
//					    return;
//					}else if("1".equalsIgnoreCase(strPdcls)){
//						savePdcls = "1";
//						//pnlLeft.add(lblAsteriskCust);
//						//pnlLeft.add(lblAsteriskShip);
//						pnlLeft.add(lblAsteriskShip);
//						//lblAsteriskCust.repaint();
//						//lblAsteriskShip.repaint();
//						lblAsteriskShip.repaint();
//					}
//				}
//				
//				if (validateCombo()){
//					if(flgDisable)
//						setEnable(true);
//					
//					if("150240".equalsIgnoreCase(getGrpExeCode())){
//						cbbFromObfiDate1.setEnabled(true) ;
//						cbbToObfiDate1.setEnabled(true) ;
//					}
//					//仕入集計表 - 発注済チェックリスト 
//					if(!"400200".equalsIgnoreCase(getGrpExeCode())&& !"250200".equalsIgnoreCase(getGrpExeCode())){
//						m_model.setData(getTableData());
//						m_model.fireTableDataChanged();
//						m_table.repaint();
//						// 部材集計表(パイプ) - 部材集計表(パイプ以外) 
////						if(lstData.size() > 0){
////							if(lstData.get(0).getMftgDate() !=0){
////								String strTempDateTo = lstData.get(0).getMftgDate()+"";
////								String strTempDateFrom =DateUtils.getCurrentDate()+"";
////								if(strTempDateTo.length()== 8){
////									try {
////										cbbFromMnDate1.setText(sdfShowing.format(sdfPersisting.parse(strTempDateTo)));
////									} catch (ParseException e1) {
////										// TODO Auto-generated catch block
////										e1.printStackTrace();
////									}
////								}
////								
////								try {
////									cbbToMnDate1.setText(sdfShowing.format(sdfPersisting.parse(strTempDateFrom)));
////								} catch (ParseException e1) {
////									// TODO Auto-generated catch block
////									e1.printStackTrace();
////								}
////							}
////						}
//					}
//					
//				}else{
//					m_model.setData(new ArrayList<PipeHVo>());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					setEnable(false);
//					resetDate();
//					
//					if("150240".equalsIgnoreCase(getGrpExeCode())){
//						cbbFromObfiDate1.setEnabled(false) ;
//						cbbToObfiDate1.setEnabled(false) ;
//					}
//				}
//				
////				// マーキン入力チェックリスト
////				if("350200".equalsIgnoreCase(getGrpExeCode())){
////					if (cbbEmp.getSelectedIndex() <= 0) {
////						btnF8.setEnabled(false);
////					}
////				}
//			}
//			
//		});
//		pnlLeft.add(cbbCustCode);	
		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		
		lblAsteriskShip = new RequiredLabel("*");
		lblAsteriskShip.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskShip);
		lblShipNo = new EditLabel("船　番");
		lblShipNo.setLocation(xPos, yPos);
		lblShipNo.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblShipNo);

		cbbShipNo = new BaseComboBox(ApplicationUtils.createComboDataModel(defaultOptions, 25, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
		cbbShipNo.setToolTipText( StringUtils.trimAllVN( lblShipNo.getText()  ) + "を選択して下さい。");
		cbbShipNo.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbShipNo.setSize(new Dimension(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE + 60, FaceContants.LABLE_HEIGHT));
		cbbShipNo.setPopupWidth(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE + 60);
		pnlLeft.add(cbbShipNo);	
		
		// lblToObfiDate, lblFromObfiDate, cbbFromObfiDate1, cbbToObfiDate1
		// 内作関係ブロック別材料集計表
		// date for 入図日 
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		lblFromObfiDate = new EditLabel("入図日");
		lblFromObfiDate.setLocation(xPos, yPos);
		lblFromObfiDate.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(lblDlvrDate1);
		
		xPos += I_LBL_LENGTH;
		cbbFromObfiDate1 = new BaseDatePicker();
		cbbFromObfiDate1.setToolTipText(StringUtils.trimAllVN(lblFromObfiDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbFromObfiDate1.setLocation(xPos, yPos);
		cbbFromObfiDate1.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(cbbFromObfiDate);
		// cbbToDlvrDate 
		xPos += TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE;
		lblToObfiDate = new EditLabel("～");
		lblToObfiDate.setLocation(xPos, yPos);
		lblToObfiDate.setBackground(this.getBackground());
		lblToObfiDate.setBorder(null);
		lblToObfiDate.setHorizontalAlignment(JLabel.CENTER);
		lblToObfiDate.setSize(FIELDS_SPACE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(lblToDlvrDate1);

		xPos += FIELDS_SPACE;
		cbbToObfiDate1 = new BaseDatePicker();
		cbbToObfiDate1.setToolTipText(StringUtils.trimAllVN(lblFromObfiDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbToObfiDate1.setLocation(xPos, yPos);
		cbbToObfiDate1.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(cbbToObfiDate);
		
		// yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		xPos = 15;
		lblAsteriskSect = new RequiredLabel("*");
		lblAsteriskSect.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblAsteriskSect);
		lblSect = new EditLabel("区　分");
		lblSect.setLocation(xPos, yPos);
		lblSect.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		pnlLeft.add(lblSect);
		
		cbbSect = new BaseComboBox(ApplicationUtils.createComboDataModel("DRAW", 4, 20, ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE, true));
		cbbSect.setToolTipText("区分を選択して下さい。");
		cbbSect.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbSect.setSize(new Dimension(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT));
		cbbSect.setPopupWidth(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE);		
		pnlLeft.add(cbbSect);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		
		// set for 仕入集計表
		lblAsteriskHaChu = new RequiredLabel("*");
		lblAsteriskHaChu.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		
		lblMnDate = new EditLabel("発注日");
		lblMnDate.setLocation(xPos, yPos);
		lblMnDate.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		
		xPos += I_LBL_LENGTH;
		cbbFromMnDate = new BaseDatePicker();
		cbbFromMnDate.setToolTipText(StringUtils.trimAllVN(lblMnDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbFromMnDate.setLocation(xPos, yPos);
		cbbFromMnDate.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		cbbFromMnDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(cbbToMnDate.getText().trim()) && !"".equals(cbbFromMnDate.getText().trim())) {
						Date fromDate = cbbFromMnDate.getDate();
						if (fromDate.compareTo(cbbToMnDate.getDate()) > 0) {
							cbbToMnDate.setDate(fromDate);
						}
					}
				} catch (Exception ex) {
				}
				
				if (validateCombo()){
					if(flgDisable)setEnable(true);
				}else{
					setEnable(false);
				}
			}
		});

		xPos += TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE;
		lblToMnDate = new EditLabel("～");
		lblToMnDate.setLocation(xPos, yPos);
		lblToMnDate.setBackground(this.getBackground());
		lblToMnDate.setBorder(null);
		lblToMnDate.setHorizontalAlignment(JLabel.CENTER);
		lblToMnDate.setSize(FIELDS_SPACE, FaceContants.LABLE_HEIGHT);
		
		xPos += FIELDS_SPACE;
		cbbToMnDate = new BaseDatePicker();
		cbbToMnDate.setToolTipText(StringUtils.trimAllVN(lblMnDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbToMnDate.setLocation(xPos, yPos);
		cbbToMnDate.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		cbbToMnDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(cbbToMnDate.getText().trim()) && !"".equals(cbbFromMnDate.getText().trim())) {
						Date toDate = cbbToMnDate.getDate();
						if (toDate.compareTo(cbbFromMnDate.getDate()) < 0) {
							cbbFromMnDate.setDate(toDate);
						}
					}
				} catch (Exception ex) {
				}
				if (validateCombo()){
					if(flgDisable)setEnable(true);
				}else{
					setEnable(false);
				}
			}
		});
		
		// set for 仕入集計表以外
		xPos = 15;
		lblDlvrDate = new EditLabel("納　期");
		lblDlvrDate.setLocation(xPos, yPos);
		lblDlvrDate.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(lblDlvrDate);
		
		xPos += I_LBL_LENGTH;
		cbbFromDlvrDate = new BaseDatePicker();
		cbbFromDlvrDate.setToolTipText(StringUtils.trimAllVN(lblDlvrDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbFromDlvrDate.setLocation(xPos, yPos);
		cbbFromDlvrDate.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(cbbFromDlvrDate);
		// Lost focus 
//		cbbFromDlvrDate.getEditor().addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {}
//			public void focusLost(FocusEvent e) {
//				if (validateCombo()){
//					m_model.setData(getTableData());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					if(flgDisable)setEnable(true);
//				}else{
//					m_model.setData(new ArrayList<PipeHVo>());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					setEnable(false);
//				}
//		}});
		// cbbToDlvrDate
		xPos += TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE;
		lblToDlvrDate = new EditLabel("～");
		lblToDlvrDate.setLocation(xPos, yPos);
		lblToDlvrDate.setBackground(this.getBackground());
		lblToDlvrDate.setBorder(null);
		lblToDlvrDate.setHorizontalAlignment(JLabel.CENTER);
		lblToDlvrDate.setSize(FIELDS_SPACE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(lblToDlvrDate);

		xPos += FIELDS_SPACE;
		cbbToDlvrDate = new BaseDatePicker();
		cbbToDlvrDate.setToolTipText(StringUtils.trimAllVN(lblDlvrDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbToDlvrDate.setLocation(xPos, yPos);
		cbbToDlvrDate.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(cbbToDlvrDate);
		// Lost focus 
//		cbbToDlvrDate.getEditor().addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {}
//			public void focusLost(FocusEvent e) {
//				if (validateCombo()){
//					m_model.setData(getTableData());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					if(flgDisable)setEnable(true);
//				}else{
//					m_model.setData(new ArrayList<PipeHVo>());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					setEnable(false);
//				}
//		}});
		// cbbHurry
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		xPos = 15;
		
		lblHurryType = new EditLabel("至急区分");
		lblHurryType.setLocation(xPos, yPos);
		lblHurryType.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		//pnlLeft.add(lblHurryType);
		List<ComboObjectVo> lstDataHurryType = new ArrayList<ComboObjectVo>();
		ComboService comService = new ComboServiceImpl();
		ComboObjectVo dataVo = new ComboObjectVo();
		dataVo.setCode("");
		dataVo.setValue1("");
		lstDataHurryType.add(0, dataVo);
		
		cbbHurry = new BaseComboBox(ApplicationUtils.createComboDataModel(lstDataHurryType, 4, 30,ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE));
		cbbHurry.setToolTipText( StringUtils.trimAllVN( lblHurryType.getText()  ) + "を選択して下さい。");
		cbbHurry.setLocation(xPos + I_LBL_LENGTH, yPos);
		cbbHurry.setSize(new Dimension(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT));
		cbbHurry.setPopupWidth(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE);
		//change ship-no combo-box every customer is changed
		
		//pnlLeft.add(cbbHurry);

		// date for 入図日 
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		xPos = 15;
		lblDlvrDate1 = new EditLabel("入図日");
		lblDlvrDate1.setLocation(xPos, yPos);
		lblDlvrDate1.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(lblDlvrDate1);
		
		xPos += I_LBL_LENGTH;
		cbbFromObfiDate = new BaseDatePicker();
		cbbFromObfiDate.setToolTipText(StringUtils.trimAllVN(lblDlvrDate1.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbFromObfiDate.setLocation(xPos, yPos);
		cbbFromObfiDate.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(cbbFromObfiDate);
		cbbFromObfiDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(cbbToObfiDate.getText().trim()) && !"".equals(cbbFromObfiDate.getText().trim())) {
						Date fromDate = cbbFromObfiDate.getDate();
						if (fromDate.compareTo(cbbToObfiDate.getDate()) > 0) {
							cbbToObfiDate.setDate(fromDate);
						}
					}
				} catch (Exception ex) {
				}
				if (validateCombo()){
					m_model.setData(getTableData());
					m_model.fireTableDataChanged();
					m_table.repaint();
					if(flgDisable)
						setEnable(true);
				}else{
//					m_model.setData(new ArrayList<PipeHVo>());
					m_model.fireTableDataChanged();
					m_table.repaint();
					setEnable(false);
				}
			}
		});
		// Lost focus 
//		cbbFromObfiDate.getEditor().addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {}
//			public void focusLost(FocusEvent e) {
//				if (validateCombo()){
//					m_model.setData(getTableData());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					if(flgDisable)setEnable(true);
//				}else{
//					m_model.setData(new ArrayList<PipeHVo>());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					setEnable(false);
//				}
//		}});
		// cbbToDlvrDate 
		xPos += TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE;
		lblToDlvrDate1 = new EditLabel("～");
		lblToDlvrDate1.setLocation(xPos, yPos);
		lblToDlvrDate1.setBackground(this.getBackground());
		lblToDlvrDate1.setBorder(null);
		lblToDlvrDate1.setHorizontalAlignment(JLabel.CENTER);
		lblToDlvrDate1.setSize(FIELDS_SPACE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(lblToDlvrDate1);

		xPos += FIELDS_SPACE;
		cbbToObfiDate = new BaseDatePicker();
		cbbToObfiDate.setToolTipText(StringUtils.trimAllVN(lblDlvrDate1.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbToObfiDate.setLocation(xPos, yPos);
		cbbToObfiDate.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		//pnlLeft.add(cbbToObfiDate);
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		xPos = 15;
		lblAsteriskClDate = new RequiredLabel("*");
		lblAsteriskClDate.setBounds(new Rectangle(xPos - 10, yPos, 10,FaceContants.LABLE_HEIGHT));
		//headerPnl.add(lblAsteriskClDate);
		
		lblClDate = new EditLabel("請求年月");
		lblClDate.setLocation(xPos, yPos);
		lblClDate.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		//lblClDate.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		//headerPnl.add(lblClDate);	
		
		xPos += lblClDate.getWidth();
		dpClDate = new BaseMonthPicker();		
		dpClDate.setToolTipText(StringUtils.trimAllVN(lblClDate.getText() + TOOLTIP_SUBFIX_COMBO));
		dpClDate.setLocation(xPos, yPos);
		dpClDate.setDate(new Date());
		dpClDate.setSize(new Dimension(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT));		
		//headerPnl.add(dpClDate);
		
		// Lost focus 
//		cbbToObfiDate.getEditor().addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {}
//			public void focusLost(FocusEvent e) {
//				if (validateCombo()){
//					m_model.setData(getTableData());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					if(flgDisable)setEnable(true);
//				}else{
//					m_model.setData(new ArrayList<PipeHVo>());
//					m_model.fireTableDataChanged();
//					m_table.repaint();
//					setEnable(false);
//				}
//		}});
		// マーキン入力チェックリスト
		//yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		xPos = 15;
//		lblAsteriskEmp = new RequiredLabel("*");
//		lblAsteriskEmp.setBounds(new Rectangle(xPos - 10, yPos, 10,
//				FaceContants.LABLE_HEIGHT));
//		pnlLeft.add(lblAsteriskEmp);
//		
		lblEmp = new EditLabel("入力担当者");
		lblEmp.setLocation(xPos, yPos);
		lblEmp.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);

		List<ComboObjectVo> listEmp = new ArrayList<ComboObjectVo>();
		ComboService combSer = new ComboServiceImpl();
		try {
			listEmp = combSer.getLstLoginUser();
		} catch (BizException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		xPos += I_LBL_LENGTH;
		cbbEmp = new BaseComboBox(ApplicationUtils.createComboDataModel(listEmp, 24, 24, ArrayListComboBoxModel.CODE_SHOW_TYPE));
		cbbEmp.setToolTipText(StringUtils.trimAllVN(lblEmp.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbEmp.setLocation(xPos, yPos);
		cbbEmp.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		cbbEmp.setPopupWidth(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE);
//		cbbEmp.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					if (validateCombo()){
//						
//						if(flgDisable)
//							setEnable(true);
//					}else{
//						setEnable(false);
//					}
//					
//					if (cbbEmp.getSelectedIndex() <= 0) {
//							btnF8.setEnabled(false);
//					}
//				}
//			}
//		});
		
		
		// 部材集計表(パイプ) - 部材集計表(パイプ以外)		
		//yPos +=  FaceContants.VERTICAL_SPACE;
		xPos = 15;
		lblMnDate1 = new EditLabel("発注日");
		lblMnDate1.setLocation(xPos, yPos);
		lblMnDate1.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		xPos += I_LBL_LENGTH;
		cbbFromMnDate1 = new BaseDatePicker();
		cbbFromMnDate1.setToolTipText(StringUtils.trimAllVN(lblMnDate1.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbFromMnDate1.setLocation(xPos, yPos);
		cbbFromMnDate1.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		cbbFromMnDate1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(cbbToMnDate1.getText().trim()) && !"".equals(cbbFromMnDate1.getText().trim())) {
						Date fromDate = cbbFromMnDate1.getDate();
						if (fromDate.compareTo(cbbToMnDate1.getDate()) > 0) {
							cbbToMnDate1.setDate(fromDate);
						}
					}
				} catch (Exception ex) {
				}
				
//				if (validateCombo()){
//					if(flgDisable)setEnable(true);
//				}else{
//					setEnable(false);
//				}
			}
		});

		xPos += TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE;
		lblToMnDate1 = new EditLabel("～");
		lblToMnDate1.setLocation(xPos, yPos);
		lblToMnDate1.setBackground(this.getBackground());
		lblToMnDate1.setBorder(null);
		lblToMnDate1.setHorizontalAlignment(JLabel.CENTER);
		lblToMnDate1.setSize(FIELDS_SPACE, FaceContants.LABLE_HEIGHT);
		
		xPos += FIELDS_SPACE;
		cbbToMnDate1 = new BaseDatePicker();
		cbbToMnDate1.setToolTipText(StringUtils.trimAllVN(lblMnDate.getText() + TOOLTIP_SUBFIX_COMBO));
		cbbToMnDate1.setLocation(xPos, yPos);
		cbbToMnDate1.setSize(TEXT_FIELD_WIDTH + ADDED_WIDTH_DATE, FaceContants.LABLE_HEIGHT);
		cbbToMnDate1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(cbbToMnDate1.getText().trim()) && !"".equals(cbbFromMnDate1.getText().trim())) {
						Date toDate = cbbToMnDate1.getDate();
						if (toDate.compareTo(cbbFromMnDate1.getDate()) < 0) {
							cbbFromMnDate1.setDate(toDate);
						}
					}
				} catch (Exception ex) {
				}
//				if (validateCombo()){
//					if(flgDisable)setEnable(true);
//				}else{
//					setEnable(false);
//				}
			}
		});
		
		
		pnlLeft.setPreferredSize(new Dimension(470, 100));
		pnlLeft.setBorder(new TitledBorder("出力条件"));
		Y_BODY_LENGTH = yPos + FaceContants.LABLE_HEIGHT + 2 * FaceContants.VERTICAL_SPACE;
		
		return pnlLeft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.jface.BaseMasterFrame#initRightBody(com.pipe.jface.BasePanel)
	 */
	@Override
	protected BasePanel getBodyCenter() {
		psTable = getTable();
		pnlCenter.setLayout(new GridLayout(1, 1));
		pnlCenter.add(psTable, createBothConstraints(0, 1, 1, 1, 1, 1));
		return pnlCenter;
	}

	
	/*
	 * (non-Javadoc)
	 *ngoan, co nhan thuc, gia dinh co ban,tinh cach hay
	 *song cung ban trai,bo qua vi tc luc tre con,trong sang
	 */
	@Override
	protected void doF1() {
	}

	@Override
	protected void doF2() {
		//if (m_table != null && m_table.isFocusOwner()) {
			setCheckboxValue(m_table, true, 0);
			m_table.repaint();
		//} 
	}

	@Override
	protected void doF3() {
		//if (m_table != null && m_table.isFocusOwner()) {
			setCheckboxValue(m_table, false, 0);
			m_table.repaint();
		//}
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	private void setCheckboxValue(InspectTable table, boolean value, int column) {
		final int rowCount = table.getRowCount();
		if (rowCount > 0) {
			TableModel tm = table.getModel();
			for (int i = rowCount - 1; i >= 0; i--) {
				tm.setValueAt(value, i, column);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF4()
	 */
	@Override
	protected void doF4() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF5()
	 */
	@Override
	protected void doF5() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF6()
	 */
	@Override
	protected void doF6() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF7()
	 */
	@Override
	protected void doF7() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF8()
	 */
	@Override
	protected void doF8() {
		
		//  set default for printer
		List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbbTypeReport.getModel()).getAnArrayList();
		if (lstDataCombo != null) {
			String menuGrp = lstDataCombo.get(cbbTypeReport.getSelectedIndex()).getValue2();
			String menuExe = lstDataCombo.get(cbbTypeReport.getSelectedIndex()).getValue3();
			ReportConstants.DEFAULT_PRINTER = FPrinterConstants.getPrtidBykey(ApplicationConstants.LOGIN_USER_ID, menuGrp, menuExe);
			ApplicationConstants.PRINT_MENU_GRP = menuGrp;
			ApplicationConstants.PRINT_MENU_EXE = menuExe;
		}
		
//		try {
//			Thread t = new Thread(new Runnable() {
//			    	@SuppressWarnings("unchecked")
//					public  void run() { 
//			    		SplashScreen sp = new SplashScreen();
//	    				sp.setVisible(true);
//	    				sp.setAlwaysOnTop(true);
//			    		try {	
//			    				ReportVo reportVo = null;
//			    				// common variable
//								String strData = null;
//								CommonCreateReport report = null;
//								
//				    			if (!("400200".equalsIgnoreCase(getGrpExeCode()) || // 仕入集計表
//				    					"250200".equalsIgnoreCase(getGrpExeCode()))// 発注済チェックリスト
//				    					//"300220".equalsIgnoreCase(getGrpExeCode()))	// 内作関係ブロック別材料集計表
//				    			){
//					    			// get data display in table
//					    			List<List<Object>> tableData = m_model.getData();
//					    			List listSignNo = new ArrayList();
//					    			for(int i = 0 ; i < tableData.size(); i++ )
//					    			{
//					    				boolean bChecked = Boolean.parseBoolean( tableData.get(i).get(0).toString() );
//					    				if( bChecked  == true )
//					    				{
//					    					listSignNo.add(tableData.get(i).get(1));
//					    				}
//					    			}
//					    			if(listSignNo.size() == 0){
//					    				sp.setVisible(false);
//							    		sp.dispose();
//							    		MstMsgVo msgVo = MessageConstants.getMstMsgVo("E0228");
//										MessageBox.message(getFrame(), msgVo);
//										return;
//					    			}
//			    			    }
//				    			// validateHachuList
//				    			if("250200".equalsIgnoreCase(getGrpExeCode())){
//				    				if(!validateHachuList(getConditionVo())){
//				    					sp.setVisible(false);
//							    		sp.dispose();
//				    					MstMsgVo msgVo = MessageConstants.getMstMsgVo("E0206");
//										MessageBox.message(getFrame(), msgVo);
//										cbbFromMnDate.requestFocus();
//										return;
//				    				}
//				    				
//				    				reportVo = printService.getR015Report(getConditionVo());
//				    				String fromDate =  DateUtils.getNihonDate(String.valueOf(getConditionVo().getHachuFromDate()));
//				    				String toDate =  DateUtils.getNihonDate(String.valueOf(getConditionVo().getHachuToDate()));
//									report = new R015CreateReport(reportVo,fromDate , toDate);
//									report.setParentFrame(getFrame());
//							    	strData = MCtlConstants.getValueByCKey("PRT_TPDT");
//				    			}
//				    			
//						    	try {
//						    		Thread.sleep(50);
//						    	} catch (InterruptedException e) {
//						    		e.printStackTrace();
//						    	}
//								//thread start
//								try {
//									Thread.sleep(50);
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}
//								// 部材集計表(パイプ以外) - 部材集計表(パイプ)
//								if("150200".equalsIgnoreCase(getGrpExeCode())||
//										"150210".equalsIgnoreCase(getGrpExeCode())){
//									String strDateFrom = cbbFromMnDate1.getText();
//									String strDateTo = cbbToMnDate1.getText();
//									if ((!StringUtils.isValid(strDateFrom)&&!StringUtils.isValid(strDateTo))||
//											(StringUtils.isValid(strDateFrom)&&StringUtils.isValid(strDateTo)) ){
//									} else{
//										sp.setVisible(false);
//							    		sp.dispose();
//										MstMsgVo msgVo = MessageConstants.getMstMsgVo("E0300");
//										MessageBox.message(getFrame(), msgVo);
//										if (!StringUtils.isValid(strDateFrom)){
//											cbbFromMnDate1.requestFocus();
//										}else{
//											cbbToMnDate1.requestFocus();
//										}
//										return;
//									}
//								}
//								// set default for printer
//								// マーキン表
//								if ("350210".equalsIgnoreCase(getGrpExeCode())) {
//									ApplicationConstants.mediaSizeName = MediaSizeName.JIS_B5;
//								
//								// 重量集計表(自社) -  重量集計表(全て) - 重量集計表(処理後のみ)
//								} else if ("300220".equalsIgnoreCase(getGrpExeCode())||
//										"300230".equalsIgnoreCase(getGrpExeCode())||
//										"300240".equalsIgnoreCase(getGrpExeCode())) {
//									ApplicationConstants.mediaSizeName = MediaSizeName.JIS_B4;
//								}else {
//									ApplicationConstants.mediaSizeName = MediaSizeName.ISO_A4;
//								}
//								
//								// 管製作一覧表が選択された場合
//								if("150230".equalsIgnoreCase(getGrpExeCode())){	
//									reportVo = printService.getR006Report(getConditionVo());
//									report = new R006CreateReport(reportVo);
//									report.setParentFrame(getFrame());
//							    	strData = MCtlConstants.getValueByCKey("PRT_TPDT");
//							    // 納期別作業表
//								}else if("200200".equalsIgnoreCase(getGrpExeCode())){
//									reportVo = printService.getR013Report(getConditionVo());
//									report = new R013CreateReport(reportVo);
//									report.setParentFrame(getFrame());
//							    	strData = MCtlConstants.getValueByCKey("PRT_DAWK");
//							    
//							    // 部材集計表(パイプ以外)
//								} else if ("150210".equalsIgnoreCase(getGrpExeCode())){
//									if(custService == null)custService = new MCustServiceImpl();
//									MCustVo custVo = custService.getCustVoByCode(cbbCustCode.getSelectedKey());
//									getParameterMinMax();
//									reportVo = printService.getR00121Report(getConditionVo(),custVo.getOodrType());	
//									String lclsTitle = NameConstants.getName("CLSNAME" + "1");
//									report = new R0012CreateReport(reportVo, strSignMin, strSignMax, strDlvrMin, strDlvrMax, strObfiMin, strObfiMax, lclsTitle,getConditionVo());
//									report.setParentFrame(getFrame());
//							    	strData = MCtlConstants.getValueByCKey("PRT_MSM1");
//							    	
//							    // 仮付表
//								}else if("150220".equalsIgnoreCase(getGrpExeCode())){
//									getParameterMinMax();
//									reportVo = printService.getR016Report(getConditionVo());
//									report = new R016CreateReport(reportVo,strSignMin, strSignMax, strDlvrMin, strDlvrMax);
//									report.setParentFrame(getFrame());
//							    	strData = MCtlConstants.getValueByCKey("PRT_TMPT");
//							    // 請求本数内訳表
//								} else if ("300260".equalsIgnoreCase(getGrpExeCode())) {
//									getParameterMinMax();
//									PipeHVo pipeh = getConditionVo();
//									reportVo = printService.getR505Report(pipeh);
//									report = new R505CreateReport(reportVo, pipeh,dpClDate.getIntegerYearMonth());
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_CNBD");
//								
//								// 仕入集計表
//								} else if ("400200".equalsIgnoreCase(getGrpExeCode())){
//									PipeHVo pipeh = getConditionVo();
//									//sp.setVisible(false);
//						    		//sp.dispose();
//									if((pipeh.getHachuFromDate() ==-1)||pipeh.getHachuToDate() ==-1){
//										MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0005").setExtraInfor("発注日"));
//										if(!StringUtils.isValid(cbbFromMnDate.getText())){
//											cbbFromMnDate.requestFocus();
//										}else{
//											cbbToMnDate.requestFocus();
//										}
//										
//										return;
//									}
//									reportVo = printService.getR014Report(pipeh);
//									report = new R014CreateReport(reportVo);
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_SSUM");
//								
//								// 径別製作数一覧表
//								}else if ("300200".equalsIgnoreCase(getGrpExeCode())){
//									PipeHVo pipeh = getConditionVo();
//									reportVo = printService.getR017Report(pipeh, savePdcls);
//									report = new R017CreateReport(reportVo, savePdcls,cbbCustCode.getSelectedKey(),dpClDate.getIntegerYearMonth());
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_PNLT");
//									
//								// 部材集計表(パイプ)
//								} else if ("150200".equalsIgnoreCase(getGrpExeCode())) {
//									//Add by HUNG NV R503									
//									PipeHVo pipeh = getConditionVo();
//									getParameterMinMax();
//									
//									// Mn Date from
//									Date selectedDate = cbbFromMnDate1.getDate();
//									
//									int iMnDateFrom = UN_SELECTED_DATE;
//									if (selectedDate != null) {
//										iMnDateFrom = NumberUtils.getIntFromString(sdfPersisting.format(selectedDate));
//									}
//									
//									selectedDate = cbbToMnDate1.getDate();
//									int iMnDateTo = UN_SELECTED_DATE;
//									if (selectedDate != null) {
//										iMnDateTo = NumberUtils.getIntFromString(sdfPersisting.format(selectedDate));
//									}
//									reportVo = printService.getR503Report(pipeh, iMnDateFrom, iMnDateTo);
//									
//									report = new R503CreateReport(reportVo, pipeh, strDlvrMin, strDlvrMax, strSignMin, strSignMax, strObfiMin, strObfiMax);
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_MSM0");
//								
//								// マーキン表
//								}else if ("350210".equalsIgnoreCase(getGrpExeCode())) {
//									//Add by HUNG NV 504
//									PipeHVo pipeh = getConditionVo();
//									getParameterMinMax();
//									reportVo = printService.getR504Report(pipeh);
//									report = new R504CreateReport(reportVo, strDlvrMin, strDlvrMax);
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_MKNT");								
//								// マーキン入力チェックリスト
//								}else if ("350200".equalsIgnoreCase(getGrpExeCode())) {
//									//Add by HUNG NV 511
//									PipeHVo pipeh = getConditionVo();
//									getParameterMinMax();
//									int iIndex = cbbEmp.getSelectedIndex();
//									List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbbEmp.getModel()).getAnArrayList();
//									String strAddUser = lstDataCombo.get(iIndex).getValue2();
////									String strAddUser = objEmp.getValue2();
//									reportVo = printService.getR511Report(pipeh, strAddUser);
//									report = new R511CreateReport(reportVo, pipeh, strDlvrMin, strDlvrMax, strSignMin, strSignMax,(String)cbbEmp.getSelectedItem());
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_MKCK");
//									
//								// 重量集計表(自社)
//								} else if ("300220".equalsIgnoreCase(getGrpExeCode())) {
//									// modify by QuangPV
//									getParameterMinMax();
//									PipeHVo pipeh = getConditionVo();
//									// update table F_PIPESUM
//									if(!printService.updateFPipeSum(pipeh)){
//										sp.setVisible(false);
//							    		sp.dispose();
//										MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0301").setExtraInfor("管データ集計ファイル"));
//							    		return;
//									}
//									
//									reportVo = printService.getR5081Report(pipeh);
//									if( ((R5081ReportVo)reportVo).countItem() > 0 ){																	
//										report = new R5081CreateReport(reportVo, pipeh,dpClDate.getIntegerYearMonth());
//										report.setParentFrame(getFrame());
//										strData = MCtlConstants.getValueByCKey("PRT_WSC1");
//										ApplicationConstants.mediaSizeName = MediaSizeName.ISO_A4;
//									}else{
//										reportVo = printService.getR009Report(pipeh);
//										report = new R009CreateReport(reportVo, pipeh,dpClDate.getIntegerYearMonth());
//										report.setParentFrame(getFrame());
//										strData = MCtlConstants.getValueByCKey("PRT_WSMK");	
//									}
//								// 重量集計表(全て)
//			    				}else if ("300230".equalsIgnoreCase(getGrpExeCode())) {
//									//Add by HUNG NV 508, 5081
//									PipeHVo pipeh = getConditionVo();
//									
//									// update table F_PIPESUM
//									if(!printService.updateFPipeSum(pipeh)){
//										sp.setVisible(false);
//							    		sp.dispose();
//										MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0301").setExtraInfor("管データ集計ファイル"));
//							    		return;
//									}
//									
//									reportVo = printService.getR5081Report(pipeh);
//									if( ((R5081ReportVo)reportVo).countItem() > 0 )//Print R5081
//									{																				
////										reportVo = printService.getR5081Report(pipeh);
//										report = new R5081CreateReport(reportVo, pipeh,dpClDate.getIntegerYearMonth());
//										report.setParentFrame(getFrame());
//										strData = MCtlConstants.getValueByCKey("PRT_WSC1");
//										ApplicationConstants.mediaSizeName = MediaSizeName.ISO_A4;
//									}
//									else//Print R508
//									{
//										reportVo = printService.getR508Report(pipeh);
//										report = new R508CreateReport(reportVo,dpClDate.getIntegerYearMonth());
//										report.setParentFrame(getFrame());
//										strData = MCtlConstants.getValueByCKey("PRT_TPDT");
//									}
//								}
//								// 重量集計表(処理後)
//								else if ("300240".equalsIgnoreCase(getGrpExeCode())) {
//									//Add by HUNG NV 515, 5081
//									PipeHVo pipeh = getConditionVo();
//									
//									// update table F_PIPESUM
//									if(!printService.updateFPipeSum(pipeh)){
//										sp.setVisible(false);
//							    		sp.dispose();
//										MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("E0301").setExtraInfor("管データ集計ファイル"));
//							    		return;
//									}
//									
//									reportVo = printService.getR5081Report(pipeh);
//									if( ((R5081ReportVo)reportVo).countItem() > 0 )//Print R5081
//									{																				
////										reportVo = printService.getR5081Report(pipeh);
//										report = new R5081CreateReport(reportVo, pipeh,dpClDate.getIntegerYearMonth());
//										report.setParentFrame(getFrame());
//										strData = MCtlConstants.getValueByCKey("PRT_WSC2");
//										ApplicationConstants.mediaSizeName = MediaSizeName.ISO_A4;
//									}
//									else//Print R515
//									{
//										reportVo = printService.getR515Report(pipeh);
//										report = new R515CreateReport(reportVo,dpClDate.getIntegerYearMonth());
//										report.setParentFrame(getFrame());
//										strData = MCtlConstants.getValueByCKey("PRT_WSC2");
//									}
//								}
//								// 内作関係ブロック別材料集計表 
//								else if ("150240".equalsIgnoreCase(getGrpExeCode())) {
//									//Add by HUNG NV 507 内作関係ブロック別材料集計表
//									PipeHVo pipeh = getConditionVo();
//									getParameterMinMax();
//									reportVo = printService.getR507Report(pipeh);
//									report = new R507CreateReport(reportVo);
//									report.setParentFrame(getFrame());
//									strData = MCtlConstants.getValueByCKey("PRT_IBMS");
//								}
//								// データがない場合
//						    	if (reportVo.isEmpty()){
//						    		sp.setVisible(false);
//						    		sp.dispose();
//						    		MstMsgVo msgVo = MessageConstants.getMstMsgVo("E0003");
//									MessageBox.message(getFrame(), msgVo);
//									return;
//								}
//						    	
//						    	sp.setVisible(false);
//						    	sp.dispose();
//						    	
//						    	if(StringUtils.isValid(strData))strData = strData.substring(1, 2);
//						    	// default page number
//						    	report.setNumber(NumberUtils.getIntFromString(txtNumPage.getText()));
//						    	if(rdoPreview.isSelected()){
//						    		if("0".equalsIgnoreCase(strData)){
//							    		report.previewReport( ReportConstants.PRINTER_NO_DIRECT);
//							    	}else if("1".equalsIgnoreCase(strData)){
//							    		report.previewReport( ReportConstants.PRINTER_DIRECT);
//							    	}
//						    	}else if(rdoPrint.isSelected()){
//						    		if("0".equalsIgnoreCase(strData)){
//							    		report.printReport( ReportConstants.PRINTER_NO_DIRECT);
//							    	}else if("1".equalsIgnoreCase(strData)){
//							    		report.printReport( ReportConstants.PRINTER_DIRECT); 
//							    	}
//						    	}else{
//						    		// output pdf
//						    		String strPath = MOutCtlContants.getValue(ApplicationConstants
//						    				.getLoginUser().getUserId(), MOutCtlContants.I_PATH1);
//						    		String[] fileName = new String[] { "pdf"};
//						    		JFileChooser fileChoser = new JFileChooser();
//						    		fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName,
//						    				"ファイル (*.pdf)"));
//						    		if (StringUtils.isValid(strPath)) {
//						    			fileChoser.setCurrentDirectory(FileUtils.getFileObj(strPath));
//						    		} else {
//						    			fileChoser.setCurrentDirectory(FileUtils.getFileObj(fileChoser
//						    					.getFileSystemView().getDefaultDirectory()
//						    					.getAbsolutePath()));
//						    		}
//
//						    		int rVal = fileChoser.showSaveDialog(getFrame());
//
//						    		if (rVal == JFileChooser.APPROVE_OPTION) {
//						    			final String strFilePath = fileChoser.getCurrentDirectory()
//						    					.toString()
//						    					+ "\\" + fileChoser.getSelectedFile().getName() + ".pdf";
//						    			if (FileUtils.isValidPath(strFilePath))report.createPdfReport( strFilePath);
//						    		}
//						    	}
//						    	
//								//thread end
//						    	try {
//						    		Thread.sleep(50);
//						    	} catch (InterruptedException e) {
//						    		e.printStackTrace();
//						    	}finally{
//						    		if(sp != null){
//								    	sp.setVisible(false);
//								    	sp.dispose();
//						    		}
//						    	}						    	
//			    		} catch (Exception ie) {
//			    			if(sp != null){
//								sp.setVisible(false);
//								sp.dispose();
//							}
//			    			ie.printStackTrace();
//			    			logger.error(ie.getMessage());
//			    			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//						} finally {							
//			    		}
//			    	}
//			    });
//				t.start();
//								
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF9()
	 */
	@Override
	protected void doF9() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF10()
	 */
	@Override
	protected void doF10() {
		if(validateCombo()){
			m_model.setData(getTableData());
			m_model.fireTableDataChanged();
			m_table.repaint();
			cbbCustCode.requestFocus();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doF11()
	 */
	@Override
	protected void doF11() {
		resetData();
		setDefaultFirstFocus(cbbCustCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#enableBtn()
	 */
	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = { true, true, true, true, true, true, true, true,
				true, true, true, true, true, true, true };
		fBtn[3] = false;
		fBtn[5] = false;
		fBtn[7] = false;
		fBtn[9] = true;
		return fBtn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doFirst()
	 */
	@Override
	protected void doFirst() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {
			int selectIndex = 0;
			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doLast()
	 */
	@Override
	protected void doLast() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getRowCount();
			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doNext()
	 */
	@Override
	protected void doNext() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex + ROW_PER_PAGE;

			if (selectIndex > m_table.getRowCount())
				selectIndex = m_table.getRowCount();

			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doPre()
	 */
	@Override
	protected void doPre() {
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(pnlCenter.getHeight()
				/ FaceContants.TABLE_ROW_HEIGHT) - 1;
		if (m_table.getRowCount() > 0) {

			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex - ROW_PER_PAGE;

			if (selectIndex < 0)
				selectIndex = 0;

			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doDoubleClick(int)
	 */
	protected void doDoubleClick(int row) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doSingleClick(int)
	 */
	protected void doSingleClick(int row) {
	}

	@SuppressWarnings("unchecked")
	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		exeVo = exeMenu;
		if (PermissionPolicy.checkMnExePermission(
				PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
						.getLoginUser().getUserUser(), exeMenu) == false) {
		}
		// set default
		int iCount = 0;
		List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbbTypeReport.getModel()).getAnArrayList();
		if (lstDataCombo != null) {
			if (exeVo != null){
				for (ComboObjectVo obj : lstDataCombo) {
					if (exeVo.getMenugrpCode().equalsIgnoreCase(StringUtils.trimAll(obj.getValue2()))&&
							exeVo.getMenuexeCode().equalsIgnoreCase(StringUtils.trimAll(obj.getValue3())) ) {
						cbbTypeReport.setSelectedIndex(iCount);
						cbbTypeReport.repaint();
						break;
					}
					iCount++;
				}
			}
		}
		
	}
	
	// get GroupCode + MenuCode
	@SuppressWarnings("unchecked")
	public String getGrpExeCode(){
		String strCode = "";
		List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbbTypeReport.getModel()).getAnArrayList();
		if (lstDataCombo != null) {
			strCode = lstDataCombo.get(cbbTypeReport.getSelectedIndex()).getValue2() + lstDataCombo.get(cbbTypeReport.getSelectedIndex()).getValue3();
		}
		return strCode.trim();
	}

	@Override
	protected BasePanel getBodyRight() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void setFrameSize() {
		X_FRAME_LENGTH = 800;
		Y_FRAME_LENGTH = 600;
		setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		setPreferredSize(new Dimension(X_FRAME_LENGTH , Y_FRAME_LENGTH));
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		setLocation((maximumWindowBounds.width - X_FRAME_LENGTH) / 2, (maximumWindowBounds.height - Y_FRAME_LENGTH) / 2);
	}
	
	protected void resetData() {
		// TODO Auto-generated method stub
		cbbCustCode.setSelectedIndex(0);
		cbbCustCode.repaint();
		cbbShipNo.setSelectedIndex(0);
		cbbShipNo.repaint();
		cbbSect.setSelectedIndex(0);
		cbbSect.repaint();
		cbbHurry.setSelectedIndex(0);
		cbbHurry.repaint();
		cbbFromDlvrDate.setDate(null);
		cbbToDlvrDate.setDate(null);
		cbbFromObfiDate.setDate(null);
		cbbToObfiDate.setDate(null);
		dpClDate.setDate(new Date());
		//dpClDate.setDate(null);
		// マーキン入力チェックリスト
		if("350200".equalsIgnoreCase(getGrpExeCode())){
//			cbbEmp.setSelectedIndex(0);
//			if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INT_PEMP").trim())) {
//				cbbEmp.setSelectedItem(ApplicationConstants.getLoginUser().getUserId());
//			}
			//if ("1".equalsIgnoreCase(MCtlConstants.getValueByCKey("INT_PEMP").trim())) {
				//cbbEmp.setSelectedItem(ApplicationConstants.getLoginUser().getUserId());
//				String strLogin = ApplicationConstants.getLoginUser().getUserId();
//				int iCount = 0;
//				List<ComboObjectVo> lstDataCombo = (ArrayList<ComboObjectVo>) ((ArrayListComboBoxModel) cbbEmp.getModel()).getAnArrayList();
//				if (lstDataCombo != null) {
//					if (StringUtils.isValid(strLogin)) {
//						for (ComboObjectVo obj : lstDataCombo) {
//							if (strLogin.equalsIgnoreCase(StringUtils.trimAll(obj.getValue3()))) {
//								cbbEmp.setSelectedIndex(iCount);
//								cbbEmp.repaint();
//								break;
//							}
//							iCount++;
//						}
//					}
//				}
			//}
			cbbEmp.setSelectedIndex(0);
			cbbEmp.repaint();
		}
//		// 径別製作数一覧表
//		if("300200".equalsIgnoreCase(getGrpExeCode())){
//			cbbFromDlvrDate.setDate(null);
//			cbbToDlvrDate.setDate(null);
//			cbbFromObfiDate.setDate(null);
//			cbbToObfiDate.setDate(null);
//		}
		
		// 仕入集計表 - 発注済チェックリスト
		if("400200".equalsIgnoreCase(getGrpExeCode())||
				"250200".equalsIgnoreCase(getGrpExeCode())){
			cbbFromMnDate.setDate(null);
			cbbToMnDate.setDate(null);
		}
		
		if("150200".equalsIgnoreCase(getGrpExeCode())||
				"150210".equalsIgnoreCase(getGrpExeCode())){
			cbbFromMnDate1.setDate(null);
			cbbToMnDate1.setDate(null);
		}
		
		if("150240".equalsIgnoreCase(getGrpExeCode())){
			cbbFromObfiDate1.setDate(null);
			cbbToObfiDate1.setDate(null);
		}
		
		getTable();
		m_table.repaint();
		psTable.getViewport().removeAll();
		psTable.getViewport().add(m_table);
		setDispTabFocus();
		
		//setDefaultFirstFocus(cbbCustCode);
		
	}
	
	protected void resetDate() {
		// TODO Auto-generated method stub
		cbbFromDlvrDate.setDate(null);
		cbbToDlvrDate.setDate(null);
		cbbFromObfiDate.setDate(null);
		cbbToObfiDate.setDate(null);
		cbbFromMnDate.setDate(null);
		cbbToMnDate.setDate(null);
		cbbFromMnDate1.setDate(null);
		cbbToMnDate1.setDate(null);
		dpClDate.setDate(new Date());
		cbbHurry.setSelectedIndex(0);
		cbbHurry.repaint();
	}
	
	protected void setEnable(boolean flgStatus) {
		// TODO Auto-generated method stub
		cbbFromDlvrDate.setEnabled(flgStatus);
		cbbToDlvrDate.setEnabled(flgStatus);
		cbbFromObfiDate.setEnabled(flgStatus);
		cbbToObfiDate.setEnabled(flgStatus);
		cbbHurry.setEnabled(flgStatus);
		// マーキン入力チェックリスト
		//if("350200".equalsIgnoreCase(getGrpExeCode()))cbbEmp.setEnabled(flgStatus);
		btnF8.setEnabled(flgStatus);
	}
	
	/* 
	 * set for R17
	 * QuangPV
	 */
	protected void setEnableR17(boolean flgStatus) {
		// TODO Auto-generated method stub
		resetData();
		if(flgStatus){
			cbbShipNo.setEnabled(flgStatus);
			cbbSect.setEnabled(flgStatus);
		}else{
			cbbShipNo.setEnabled(flgStatus);
			cbbSect.setEnabled(flgStatus);
			cbbFromDlvrDate.setEnabled(flgStatus);
			cbbToDlvrDate.setEnabled(flgStatus);
			cbbFromObfiDate.setEnabled(flgStatus);
			cbbToObfiDate.setEnabled(flgStatus);
			cbbHurry.setEnabled(flgStatus);
		}
	}
	
	/* 
	 * Min - Max
	 * phuongvn
	 */
	@SuppressWarnings("unchecked")
	protected void getParameterMinMax(){
		
		// get data display in table
		List<List<Object>> tableData = m_model.getData();
		List listDlvrDate = new ArrayList();
		List listSignNo = new ArrayList();
		List listObfiDate = new ArrayList();
		
		for(int i = 0 ; i < tableData.size(); i++ )	{
			boolean bChecked = Boolean.parseBoolean( tableData.get(i).get(0).toString() );
			if( bChecked  == true )	{
				// 記号
				listSignNo.add(tableData.get(i).get(1));
				// 納期
				listDlvrDate.add(tableData.get(i).get(2));
				// 入図日
				listObfiDate.add(tableData.get(i).get(4));
			}
		}
		
		// get Min, Max 記号
		for(int i = 0; i < listSignNo.size(); i ++)	{
			
			String tmpStr =  StringUtils.toString(listSignNo.get(i));
			if(i==0){
				strSignMin = tmpStr;
				strSignMax = tmpStr;
			} else {
				if(StringUtils.compareS(strSignMin, tmpStr) > 0){
					strSignMin =  tmpStr;
				}
				if(StringUtils.compareS(strSignMax, tmpStr) < 0){
					strSignMax =  tmpStr;					
				}
			}			
		}		
		
		// get Min, Max 納期
		for(int i = 0; i < listDlvrDate.size(); i ++){
			String tmpStr =  StringUtils.trimAll(StringUtils.toString(listDlvrDate.get(i)));
			
			// split yobi
			if(tmpStr.indexOf("(") > -1 && tmpStr.indexOf(")") > -1 ){
				
				String tempStr = tmpStr.substring(0, tmpStr.indexOf("("));	
				if(DateUtils.isValidDate(tempStr, "yyyy/MM/dd")){
					tmpStr = tempStr;
				}			
			}			
			
			if(i==0){
				strDlvrMin = tmpStr;
				strDlvrMax = tmpStr;
				
			} else	{			
				if(DateUtils.isValidDate(tmpStr, "yyyy/mm/dd"))	{		
					
					if((StringUtils.compareS(strDlvrMin, tmpStr) > 0) && DateUtils.isValidDate(strDlvrMin, "yyyy/mm/dd") ){
						strDlvrMin =  tmpStr;
					}
					
					if(DateUtils.isValidDate(strDlvrMax, "yyyy/mm/dd")){
						if(StringUtils.compareS(strDlvrMax, tmpStr) < 0 ){
							strDlvrMax =  tmpStr;					
						}
					} else {
						strDlvrMax =  tmpStr;	
					}					
				} else {
					if(!DateUtils.isValidDate(strDlvrMin, "yyyy/mm/dd")){
						if(StringUtils.compareS(strDlvrMin, tmpStr) < 0){
							strDlvrMin =  tmpStr;
						}
					} else {
						strDlvrMin =  tmpStr;
					}
					
					if(!DateUtils.isValidDate(strDlvrMax, "yyyy/mm/dd")){					
						if(StringUtils.compareS(strDlvrMax, tmpStr) > 0){
							strDlvrMax =  tmpStr;					
						}
					}				
				}
			}				
		}		
		// get Min, Max 入図日
		for(int i = 0; i < listObfiDate.size(); i ++)
		{
			String tmpStr =DateUtils.getDateWithSplit(Integer.parseInt(StringUtils.toString(listObfiDate.get(i))));
			
			if(i==0){
				strObfiMin = tmpStr;
				strObfiMax = tmpStr;				
			} else {
				if(strObfiMin.equals("")){
					strObfiMin = tmpStr;
				}
				
				if(StringUtils.compareS(strObfiMin, tmpStr) > 0){
					strObfiMin = tmpStr;
				}	
				
				if(StringUtils.compareS(strObfiMax, tmpStr) < 0){
					strObfiMax = tmpStr;				
				}				
			}			
		}		
		
		// get yobi		
		String tmpDlvrMin = strDlvrMin;
		String tmpDlvrMax = strDlvrMax;
		String tmpObfiMin = strObfiMin;
		String tmpObfiMax = strObfiMax;
		
		if(DateUtils.isValidDate(strDlvrMin, "yyyy/MM/dd")){
			tmpDlvrMin = DateUtils.getDateWithSplitYobi(strDlvrMin);
		}
		
		if(DateUtils.isValidDate(strDlvrMax, "yyyy/MM/dd")){
			tmpDlvrMax = DateUtils.getDateWithSplitYobi(strDlvrMax); 
		}
		
		if(DateUtils.isValidDate(strObfiMin, "yyyy/MM/dd")){
			tmpObfiMin = DateUtils.getDateWithSplitYobi(strObfiMin); 
		}
		
		if(DateUtils.isValidDate(strObfiMax, "yyyy/MM/dd")){
			tmpObfiMax = DateUtils.getDateWithSplitYobi(strObfiMax); 
		}		
		strDlvrMin = tmpDlvrMin;
		strDlvrMax = tmpDlvrMax;
		strObfiMin = tmpObfiMin;
		strObfiMax = tmpObfiMax;		
	}
}