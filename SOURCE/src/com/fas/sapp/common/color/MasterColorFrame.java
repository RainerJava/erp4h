package com.fas.sapp.common.color;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.EditConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.MessageBox;
import com.fas.jface.bussiness.BaseMasterFrame;
import com.fas.jface.button.ActionButton;
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
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.text.NumberText;
import com.fas.service.common.color.ColorServiceImpl;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.color.MColorVo;
import com.fas.vo.menuexe.MenuExeVo;

public class MasterColorFrame extends BaseMasterFrame {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	static Logger logger = Logger.getLogger(MasterColorFrame.class);
	/** */
	protected BaseLabel lblPermission;
	/** header parameter */
	private BaseLabel lblStatus;
	/** */
	private BaseLabel lblNumber;
	/** */
	private BaseLabel lblLstCd;

	/** body parameter */
	private ColorClsCombobox cboColorCls;
	/** */
	private CdInputText txtColorCode;
	/** */
	private BaseInputText txtColorName;
	/** */
	private BaseInputText txtColorRName;
	/** */
	private NumberText txtOrderNo;
	/** */
	private BaseCheckBox chkDspFlg;
	/** */
	private BaseLabel lblSettingColor;
	/** */
	private ActionButton btnBackColor;
	/** */
	private ActionButton btnFontColor;

	/** Table */
	/** */
	private BasePanel pnlGrid;
	/** */
	private InspectTable m_table;
	/** */
	private InspectTableModel m_model;
	/** */
	private List<MColorVo> lstData;
	/** */
	private BaseScrollPane psTable;
	/** テーブルヘッダーサイズ */
	private static String colHeadNm[] = { "コード", "名 称", "略称" };
	/** */
	private int[] colHeadwidth = { 80, 300, 300 };
	private static int iTableWith = 0;

	private ColorServiceImpl colorService = new ColorServiceImpl();

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param frame
	 * @param title
	 *            </DL>
	 */
	public MasterColorFrame(BaseFrame frame, String title) {
		super(frame, title);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param frame
	 *            </DL>
	 */
	public MasterColorFrame(BaseFrame frame) {
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
	public MasterColorFrame() {
		super();
		init();
	}

	private class ColorClsCombobox extends BaseComboBox {

		public ColorClsCombobox(ComboBoxModel aModel) {
			super(aModel);
		}

		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * </DL>
		 */
		public void resetValue() {
			setSelectedIndex(0);
		}

		public Object getSelectObjValue() {
			int iValue = getSelectedIndex();
			if (dataModel instanceof ArrayListComboBoxModel) {
				ArrayListComboBoxModel aDataModel = (ArrayListComboBoxModel) dataModel;
				return aDataModel.getObjAt(iValue);
			} else {
				return null;
			}
		}

		public void setSelectedIndex(int anIndex) {
			super.setSelectedIndex(anIndex);
			UpdateColorList();
		}
	}

	@Override
	protected void doDoubleClick(int row) {
		if (row < 0) {
			return;
		}

		if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			doSingleClick(row);
			// 修正モードを変わります。
			CUR_MODE = EditConstants.EDIT_MODE;
			setStatus(CUR_MODE);
		}
	}

	@Override
	protected void doF1() {
		try {
			if (colorService.countAllColorVo() <= 0) {
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0001"));
				return;
			}
			ExportExcel ex = new ExportExcel();
			try
			{
				try
				{
					ex.DoExport(colorService.exportExcel());
				}
				catch(WriteException writeEx)
				{}
			}
			catch(SQLException sqlEx)
			{}
			
		} catch (BizException e) {
			logger.error(e.getMessage());
			MessageBox.message(getFrame(), MessageConstants
					.getMstMsgVo("E0123"));
		}
	}

	@Override
	protected void doF10() {
		UpdateColorList();
	}

	@Override
	protected void doF11() {
		if (!EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
				CUR_MODE = EditConstants.VIEW_MODE;
				setStatus(CUR_MODE);
				resetData(true);
			}
		} else {
			resetData(true);
		}
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
		try {
			if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
				if (MessageBox.message(this, MessageConstants
						.getMstMsgVo("Q0003")) == MessageBox.YES) {
					MColorVo dataVo = colorService.getMColorVo(cboColorCls
							.getSelectedKey(), txtColorCode.getText().trim());
					// data is not exist
					if (dataVo == null) {
						MessageBox.message(this, MessageConstants
								.getMstMsgVo("E0003"));
						return;
					} else {
						List lstData = ((ArrayListComboBoxModel) cboColorCls
								.getModel()).getAnArrayList();
						String strName = "";
						if (lstData != null) {
							for (Object obj : lstData) {
								ComboObjectVo cboObj = (ComboObjectVo) obj;
								if (cboObj != null) {
									if (StringUtils.isEquals(dataVo
											.getColorclsCode(), cboObj
											.getCode())) {
										strName = cboObj.getValue1();
										break;
									}
								}
							}
						}

						String description = "種別:" + strName + ", コード: "
								+ dataVo.getColorCode();
						colorService.delColorVo(dataVo);
						ApplicationUtils.logData(
								ApplicationConstants.LOGIN_USER_ID, exeMenu
										.getMenugrpCode(), exeMenu
										.getMenuexeCode(),
								LogContants.LOGACT_DE, description);

						resetData(true);
						UpdateColorList();
						CUR_MODE = EditConstants.VIEW_MODE;
						setScreenMode(CUR_MODE);
					}
				}
			}
		} catch (Exception e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void doF7() {
	}

	@Override
	protected void doF8() {
		try {
			copyColorName();
			if (validateData()) {
				MColorVo colorVo = colorService.getMColorVo(cboColorCls
						.getSelectedKey(), txtColorCode.getText().trim());
				if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
					if (MessageBox.message(this, MessageConstants
							.getMstMsgVo("Q0002")) == MessageBox.YES) {
						if (colorVo == null) {
							MessageBox.message(this, MessageConstants
									.getMstMsgVo("E0003"));
							return;
						} else {
							MColorVo dataVo = convertTxt2Vo();
							List lstData = ((ArrayListComboBoxModel) cboColorCls
									.getModel()).getAnArrayList();
							String strName = "";
							if (lstData != null) {
								for (Object obj : lstData) {
									ComboObjectVo cboObj = (ComboObjectVo) obj;
									if (cboObj != null) {
										if (StringUtils.isEquals(dataVo
												.getColorclsCode(), cboObj
												.getCode())) {
											strName = cboObj.getValue1();
											break;
										}
									}
								}
							}

							String description = "種別:" + strName + ", コード: "
									+ dataVo.getColorCode();
							ApplicationUtils.logData(
									ApplicationConstants.LOGIN_USER_ID, exeMenu
											.getMenugrpCode(), exeMenu
											.getMenuexeCode(),
									LogContants.LOGACT_UP, description);
							colorService.updateMColorVo(dataVo);
							doF10();
							UpdateColorList();
						}
					}
				} else if (EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
					if (MessageBox.message(this, MessageConstants
							.getMstMsgVo("Q0002")) == MessageBox.YES) {
						if (colorVo != null) {
							MessageBox.message(this, MessageConstants
									.getMstMsgVo("E0109"));
							return;
						} else {
							MColorVo dataVo = convertTxt2Vo();
							List lstData = ((ArrayListComboBoxModel) cboColorCls
									.getModel()).getAnArrayList();
							String strName = "";
							if (lstData != null) {
								for (Object obj : lstData) {
									ComboObjectVo cboObj = (ComboObjectVo) obj;
									if (cboObj != null) {
										if (StringUtils.isEquals(dataVo
												.getColorclsCode(), cboObj
												.getCode())) {
											strName = cboObj.getValue1();
											break;
										}
									}
								}
							}

							String description = "種別:" + strName + ", コード: "
									+ dataVo.getColorCode();
							ApplicationUtils.logData(
									ApplicationConstants.LOGIN_USER_ID, exeMenu
											.getMenugrpCode(), exeMenu
											.getMenuexeCode(),
									LogContants.LOGACT_AD, description);

							colorService.insertColorVo(dataVo);
							doF10();
							UpdateColorList();

						}
					}
				}
			}
		} catch (Exception e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(e.getMessage());
		}
		invokeSetStatusLater();
	}

	@Override
	protected void doF9() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doFirst()
	 */
	protected void doFirst() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = 0;
			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	@Override
	protected void doLast() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getRowCount();
			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	@Override
	protected void doNext() {
		if (m_table.getRowCount() > 0) {
			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex + ROW_PER_PAGE;

			if (selectIndex > m_table.getRowCount())
				selectIndex = m_table.getRowCount();

			m_table.changeSelection(selectIndex - 1, 0, false, false);
		}
	}

	@Override
	protected void doPre() {
		if (m_table.getRowCount() > 0) {

			int selectIndex = m_table.getSelectedRow();
			selectIndex = selectIndex - ROW_PER_PAGE;

			if (selectIndex < 0)
				selectIndex = 0;

			m_table.changeSelection(selectIndex, 0, false, false);
		}
	}

	private boolean validateData() {
		boolean isValid = true;
		JComponent comp = null;

		// check color_name
		if (!StringUtils.isValid(txtColorName.getText().trim())) {
			isValid = false;
			comp = txtColorName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor("名称"));
		}

		// check len color_name
		if (isValid
				&& !StringUtils.checkLenString(txtColorName.getText().trim(),
						40)) {
			isValid = false;
			comp = txtColorName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor("名称"));
		}

		// check colorRname
		if (isValid && !StringUtils.isValid(txtColorRName.getText().trim())) {
			isValid = false;
			comp = txtColorRName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor("略称"));
		}

		// check len colorRname
		if (isValid
				&& !StringUtils.checkLenString(txtColorRName.getText().trim(),
						6)) {
			isValid = false;
			comp = txtColorRName;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor("略称"));
		}

		// dsorderNo
		if (isValid && !StringUtils.isValid(txtOrderNo.getText().trim())) {
			isValid = false;
			comp = txtOrderNo;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005")
					.setExtraInfor("表示順"));
		}

		// check len dsporderNo
		if (isValid
				&& !StringUtils.checkLenString(txtOrderNo.getText().trim(), 3)) {
			isValid = false;
			comp = txtOrderNo;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129")
					.setExtraInfor("表示順"));
		}

		if (comp != null) {
			setDefaultFirstFocus(comp);
		}

		return isValid;
	}

	class SettingBackColorAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public SettingBackColorAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			btnBackColor.setEnabled(true);
			changeBackColor();
		}
	}

	private void changeBackColor() {
		Color newColor = JColorChooser.showDialog(this, "", lblSettingColor
				.getBackground());
		if (newColor != null) {
			lblSettingColor.setBackground(newColor);
			lblSettingColor.repaint();
		}
	}

	class SettingFontColorAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public SettingFontColorAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			btnFontColor.setEnabled(true);
			changeFontColor();
		}
	}

	private void changeFontColor() {
		Color newColor = JColorChooser.showDialog(this, "", lblSettingColor
				.getForeground());
		if (newColor != null) {
			lblSettingColor.setForeground(newColor);
			lblSettingColor.repaint();
		}
	}

	@Override
	protected void doSingleClick(int row) {
		if (row < 0) {
			return;
		}

		// 修正時は、何にもしません。
		if (EditConstants.VIEW_MODE.equalsIgnoreCase(CUR_MODE) == false) {
			return;
		}

		CUR_MODE = EditConstants.VIEW_MODE;
		convertTableRow2Txt();
		setStatus(EditConstants.VIEW_MODE);
	}

	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = { true, true, true, true, true, true, true, true,
				true, true, true, true, true, true, true };
		fBtn[3] = true;
		fBtn[5] = true;
		fBtn[6] = true;
		fBtn[7] = false;
		fBtn[9] = true;

		return fBtn;
	}

	@Override
	protected String getHelpInfor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getRBodyWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getSubName() {
		// TODO Auto-generated method stub
		return null;
	}

	private void init() {
		setResizable(false);
		CUR_MODE = EditConstants.VIEW_MODE;
		setDispTabFocus();
		setStatus(CUR_MODE);

		btnF2.setText("<html><center><font size=-1></font></center>");
		btnF3.setText("<html><center><font size=-1></font></center>");
		btnF4.setText("<html><center><font size=-1></font></center>");
		btnF5.setText("<html><center><font size=-1></font></center>");
		btnF9.setText("<html><center><font size=-1></font></center>");

		chkDspFlg.setText(NameConstants.getName("DSPFLG" + 0));
	}

	@Override
	protected void initHeader(BasePanel pnl) {
		int X_WIDTH = 100;
		int xPos = 25;
		int yPos = 10;

		lblStatus = new BaseLabel("登　録");
		lblStatus.setBounds(new Rectangle(xPos, yPos, X_WIDTH + 20,
				FaceContants.LABLE_HEIGHT));
		lblStatus.setBorder(FaceContants.LINE_BORDER);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus
				.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		pnl.add(lblStatus);

		xPos += X_WIDTH + 20;
		BaseLabel lblReg = new BaseLabel("登録件数");
		lblReg.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblReg.setBorder(FaceContants.LINE_BORDER);
		lblReg.setHorizontalAlignment(SwingConstants.CENTER);
		lblReg.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		pnl.add(lblReg);

		xPos += X_WIDTH;
		lblNumber = new BaseLabel("");
		lblNumber.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblNumber.setBorder(FaceContants.LABEL_BORDER);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		pnl.add(lblNumber);

		xPos += X_WIDTH;
		BaseLabel lblLatestCd = new BaseLabel("最終コード");
		lblLatestCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblLatestCd.setBorder(FaceContants.LINE_BORDER);
		lblLatestCd.setHorizontalAlignment(SwingConstants.CENTER);
		lblLatestCd.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		pnl.add(lblLatestCd);

		xPos += X_WIDTH;
		lblLstCd = new BaseLabel("");
		lblLstCd.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblLstCd.setBorder(FaceContants.LABEL_BORDER);
		lblLstCd.setHorizontalAlignment(SwingConstants.RIGHT);
		pnl.add(lblLstCd);

		xPos = xPos + X_WIDTH + 5;
		lblPermission = new BaseLabel();
		lblPermission.setForeground(Color.RED);
		lblPermission.setBounds(new Rectangle(xPos, yPos, X_WIDTH,
				FaceContants.LABLE_HEIGHT));
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.RIGHT);
		pnl.add(lblPermission);
	}

	@Override
	protected void initLeftBody(BasePanel com) {

	}

	@Override
	protected void initRightBody(BasePanel com) {
		int yPos = 10;
		int xPos = 25;

		int I_LBL_LENGTH = 100;
		int I_LBL_LENGTH_150 = 150;
		int TXT_TEXT_FIELD_LENGTH_200 = 200;
		int TXT_TEXT_FIELD_LENGTH_300 = 300;
		int TXT_TEXT_FIELD_LENGTH_400 = 400;
		int TXT_TEXT_FIELD_LENGTH_80 = 80;

		int X_BTN_WIDTH = 22;
		int Y_BTN_HEIGHT = FaceContants.LABLE_HEIGHT;

		// cboColorCls
		RequiredLabel lblAsteriskColorCls = new RequiredLabel("*");
		lblAsteriskColorCls.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		com.add(lblAsteriskColorCls);

		EditLabel lblColorCls = new EditLabel("種別");
		lblColorCls.setLocation(xPos, yPos);
		lblColorCls.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		com.add(lblColorCls);

		List<ComboObjectVo> lstData = null;
		try {
			lstData = new ComboServiceImpl().getLstColorCls();
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstData = new ArrayList<ComboObjectVo>();
		}

		cboColorCls = new ColorClsCombobox(ApplicationUtils
				.createComboDataModel(lstData, 15, 30,
						ArrayListComboBoxModel.CODE_VALUE1_SHOW_TYPE));
		cboColorCls.setPopupWidth(TXT_TEXT_FIELD_LENGTH_300);
		cboColorCls.setToolTipText("種別を入力して下さい。");
		cboColorCls.setLocation(xPos + I_LBL_LENGTH, yPos);
		cboColorCls.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_300,
				FaceContants.LABLE_HEIGHT));

		com.add(cboColorCls);

		// colorCode
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsteriskColorCode = new RequiredLabel("*");
		lblAsteriskColorCode.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		com.add(lblAsteriskColorCode);

		EditLabel lblColorCode = new EditLabel("コード");
		lblColorCode.setLocation(xPos, yPos);
		lblColorCode.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		com.add(lblColorCode);

		txtColorCode = new CdInputText("", 0, CdInputText.IM_OFF, 2);
		txtColorCode.setToolTipText("コードを入力して下さい。");
		txtColorCode.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtColorCode.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_80,
				FaceContants.LABLE_HEIGHT));

		AbstractAction enter = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (cboColorCls.getSelectedIndex() > 0) {

					if (!StringUtils.isValid(txtColorCode.getText().trim())) {
						setDefaultFirstFocus(txtColorCode);
						return;
					}

					try {
						MColorVo colorVo = colorService.getMColorVo(cboColorCls
								.getSelectedKey(), txtColorCode.getText());
						if (colorVo == null) {
							resetData(false);
							CUR_MODE = EditConstants.NEW_MODE;
							setStatus(CUR_MODE);
							setDefaultFirstFocus(txtColorName);
							return;
						} else {
							convertVo2Txt(colorVo);
							CUR_MODE = EditConstants.EDIT_MODE;
							setStatus(CUR_MODE);
							setDefaultFirstFocus(txtColorName);
						}
					} catch (BizException ex) {
						logger.log(Priority.ERROR, ex.getMessage());
						MessageBox.message(getFrame(), MessageConstants
								.getMstMsgVo("C0001"));
					}
				} else {
					setDefaultFirstFocus(cboColorCls);
				}
			}
		};
		txtColorCode.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		txtColorCode.getActionMap().put("enter", enter);
		com.add(txtColorCode);

		// colorName
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsterisColorName = new RequiredLabel("*");
		lblAsterisColorName.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		com.add(lblAsterisColorName);

		EditLabel lblColorName = new EditLabel("名称");
		lblColorName.setLocation(xPos, yPos);
		lblColorName.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		com.add(lblColorName);

		txtColorName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 40);
		txtColorName.setToolTipText("名称を入力して下さい。");
		txtColorName.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtColorName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_400,
				FaceContants.LABLE_HEIGHT));

		txtColorName.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent arg0) {
				copyColorName();
			}

			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		com.add(txtColorName);

		// colorRname
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsterisColorRName = new RequiredLabel("*");
		lblAsterisColorRName.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		com.add(lblAsterisColorRName);

		EditLabel lblColorRName = new EditLabel("略称");
		lblColorRName.setLocation(xPos, yPos);
		lblColorRName.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		com.add(lblColorRName);

		txtColorRName = new BaseInputText("", 0, BaseInputText.IM_HIRAGANA, 6);
		txtColorRName.setToolTipText("略称を入力して下さい。");
		txtColorRName.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtColorRName.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_80,
				FaceContants.LABLE_HEIGHT));
		com.add(txtColorRName);

		// DSPOrder_NO
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		RequiredLabel lblAsterisDspOrderNo = new RequiredLabel("*");
		lblAsterisDspOrderNo.setBounds(new Rectangle(xPos - 10, yPos, 10,
				FaceContants.LABLE_HEIGHT));
		com.add(lblAsterisDspOrderNo);

		EditLabel lblDspOrderNo = new EditLabel("表示順");
		lblDspOrderNo.setLocation(xPos, yPos);
		lblDspOrderNo.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		com.add(lblDspOrderNo);

		txtOrderNo = new NumberText("", 0, 3);
		txtOrderNo.setToolTipText("表示順を入力して下さい。");
		txtOrderNo.setLocation(xPos + I_LBL_LENGTH, yPos);
		txtOrderNo.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_80,
				FaceContants.LABLE_HEIGHT));
		com.add(txtOrderNo);

		// USE_FLG
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblDslFlg = new EditLabel("表示区分");
		lblDslFlg.setLocation(xPos, yPos);
		lblDslFlg
				.setSize(new Dimension(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT));
		com.add(lblDslFlg);

		chkDspFlg = new BaseCheckBox();
		chkDspFlg.setToolTipText("表示区分を入力して下さい。");
		chkDspFlg.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkDspFlg.setSize(new Dimension(TXT_TEXT_FIELD_LENGTH_200,
				FaceContants.LABLE_HEIGHT));
		chkDspFlg.setChkLabel("非表示");
		chkDspFlg.setUnChkLabel("表示");
		com.add(chkDspFlg);

		// color
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblSttColor = new EditLabel("プレビュー");
		lblSttColor.setLocation(xPos, yPos);
		lblSttColor.setSize(new Dimension(I_LBL_LENGTH,
				FaceContants.LABLE_HEIGHT));
		com.add(lblSttColor);

		int xPosColor = xPos + I_LBL_LENGTH;
		lblSettingColor = new BaseLabel("テスト");
		lblSettingColor.setLocation(xPosColor, yPos);
		lblSettingColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettingColor.setSize(new Dimension(I_LBL_LENGTH_150,
				FaceContants.LABLE_HEIGHT));
		com.add(lblSettingColor);

		xPosColor += I_LBL_LENGTH_150 + 2;
		SettingBackColorAction backColorAction = new SettingBackColorAction(
				"backColorAction");
		btnBackColor = new ActionButton();
		btnBackColor.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT));
		btnBackColor.setLocation(xPosColor, yPos);
		btnBackColor.setText("<html><center><font size=-1>B</font></center>");
		btnBackColor.addActionListener(backColorAction);
		btnBackColor.getActionMap().put("backColorAction", backColorAction);
		com.add(btnBackColor);

		xPosColor += X_BTN_WIDTH + 2;
		SettingFontColorAction fontColorAction = new SettingFontColorAction(
				"fontColorAction");
		btnFontColor = new ActionButton();
		btnFontColor.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
				Y_BTN_HEIGHT));
		btnFontColor.setLocation(xPosColor, yPos);
		btnFontColor.setText("<html><center><font size=-1>F</font></center>");
		btnFontColor.addActionListener(fontColorAction);
		btnFontColor.getActionMap().put("fontColorAction", fontColorAction);
		com.add(btnFontColor);

		// table color
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE + 5;
		pnlGrid = new BasePanel();
		pnlGrid.setSize(rInputPnl.getWidth() - xPos - 10, rInputPnl.getHeight()
				- yPos - 10);
		pnlGrid.setLocation(xPos, yPos);
		pnlGrid.setBorder(FaceContants.TITLE_BORDER);

		colHeadwidth = new int[] { 80, 300, 400 };
		colHeadwidth[1] = pnlGrid.getWidth() - 40 - colHeadwidth[0]
				- colHeadwidth[2] - 25;
		iTableWith = pnlGrid.getWidth() - 40;
		psTable = getTable();
		psTable.setSize(pnlGrid.getWidth() - 40, pnlGrid.getHeight() - 40);
		psTable.getViewport().add(m_table);
		psTable.setLocation(20, 20);
		pnlGrid.add(psTable);

		com.add(pnlGrid);

		com.setBorder(null);
		com.setLocation(0, 0);
	}

	private void copyColorName() {
		String name = txtColorName.getText().trim();
		if (StringUtils.isValid(name)) {
			if (!StringUtils.isValid(txtColorRName.getText().trim())) {
				int len = 6;
				txtColorRName.setText(StringUtils.subStringUseEncode(name, 0,
						len));
			}
		}
	}

	private void setTitleInfor() {
		try {
			if (colorService == null)
				colorService = new ColorServiceImpl();
			lblNumber.setText("");
			lblLstCd.setText("");
			if (cboColorCls.getSelectedIndex() > 0) {
				lblNumber.setText(colorService.getCountVo(cboColorCls
						.getSelectedKey())
						+ "件");
				lblLstCd.setText(colorService.getMaxColorCode(cboColorCls
						.getSelectedKey()));
			}
		} catch (BizException e) {
			logger.error(e.getMessage());
		}
	}

	private void setScreenMode(String mode) {
		btnF2.setEnabled(false);
		btnF3.setEnabled(false);
		btnF4.setEnabled(false);
		btnF5.setEnabled(false);
		btnF9.setEnabled(false);

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_EDIT_MODE);
			lblStatus
					.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);

			if (PermissionPolicy.checkMnExePermission(
					PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
							.getLoginUser().getUserUser(), exeMenu)) {
				btnF8.setEnabled(true);
				btnF8.setAffterDoDisable(true);
				btnF6.setEnabled(true);
			}

			btnF10.setEnabled(false);
			btnF11.setEnabled(true);
			btnF12.setConfirmRerquired(true);

			m_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlGrid.setAlpha(0.5f);

		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus
					.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF2.setEnabled(false);
			if (PermissionPolicy.checkMnExePermission(
					PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
							.getLoginUser().getUserUser(), exeMenu)) {
				btnF8.setEnabled(true);
			}

			btnF6.setEnabled(false);
			btnF10.setEnabled(false);
			btnF11.setEnabled(true);
			btnF12.setConfirmRerquired(true);

			m_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlGrid.setAlpha(0.5f);
		} else if (EditConstants.COPY_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_COPY_MODE);
			// lblStatus.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
			lblStatus
					.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);
			btnF10.setEnabled(false);
			btnF11.setEnabled(true);
			btnF12.setConfirmRerquired(true);

			m_table.setEnabled(false);
			btnFirst.setEnabled(false);
			btnNext.setEnabled(false);
			btnPre.setEnabled(false);
			btnLast.setEnabled(false);
			pnlGrid.setAlpha(0.5f);
		} else {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus
					.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);
			btnF10.setEnabled(true);
			btnF11.setEnabled(true);
			btnF12.setConfirmRerquired(false);

			m_table.setEnabled(true);
			btnFirst.setEnabled(true);
			btnNext.setEnabled(true);
			btnPre.setEnabled(true);
			btnLast.setEnabled(true);
			pnlGrid.setAlpha(1f);
		}
	}

	/**
	 * Allow enter value in control
	 */
	protected void setDispTabFocus() {
		List<Object> focusList = new ArrayList<Object>();
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(txtColorName);
			focusList.add(txtColorRName);
			focusList.add(txtOrderNo);
			focusList.add(chkDspFlg);

			focusList.add(btnBackColor);
			focusList.add(btnFontColor);

		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(txtColorName);
			focusList.add(txtColorRName);
			focusList.add(txtOrderNo);
			focusList.add(chkDspFlg);

			focusList.add(btnBackColor);
			focusList.add(btnFontColor);
		} else {
			focusList.add(txtColorCode);
		}
		focusList.add(m_table);

		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}

	protected void setStatus(String mode) {
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)
				|| EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			cboColorCls.setEnabled(false);
			txtColorCode.setEditable(false);

			txtColorName.setEditable(true);
			txtColorRName.setEditable(true);
			txtOrderNo.setEditable(true);

			chkDspFlg.setEnabled(true);

			btnBackColor.setEnabled(true);
			btnFontColor.setEnabled(true);

			if (isRequireResetTabDispFocus) {
				setDispTabFocus();
				setDefaultFirstFocus(cboColorCls);
			}

			setScreenMode(CUR_MODE);
		} else {
			// view mode
			cboColorCls.setEnabled(true);
			txtColorCode.setEditable(true);

			txtColorName.setEditable(false);
			txtColorRName.setEditable(false);
			txtOrderNo.setEditable(false);

			chkDspFlg.setEnabled(false);

			btnBackColor.setEnabled(false);
			btnFontColor.setEnabled(false);

			if (isRequireResetTabDispFocus) {
				setDispTabFocus();
				setDefaultFirstFocus(cboColorCls);
			}
			setScreenMode(CUR_MODE);
		}

		isRequireResetTabDispFocus = true;
	}

	private BaseScrollPane getTable() {
		BaseScrollPane tablePnl;

		colHeadwidth = new int[] { 80, 300, 400 };
		colHeadwidth[1] = iTableWith - colHeadwidth[0] - colHeadwidth[2] - 25;

		m_model = new InspectTableModel(colHeadNm);
		m_table = new InspectTable(m_model) {
			private static final long serialVersionUID = 1L;
			private int[] align = { BaseLabel.CENTER, BaseLabel.LEFT,
					BaseLabel.LEFT };
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
		m_model.setData(new ArrayList<MColorVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) m_table
				.getColumnModel();
		for (int i = 0; i < colHeadwidth.length; i++) {
			defModel.getColumn(i).setMinWidth(colHeadwidth[i]);
			defModel.getColumn(i).setWidth(colHeadwidth[i]);
			defModel.getColumn(i).setMaxWidth(colHeadwidth[i] + 200);
		}

		// EVENT
		// mouse click
		// m_table.addMouseListener(new TableMouseClick());
		initTableAction(m_table);

		AbstractAction tabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_table.isFocusOwner()) {
					if (txtColorCode.isEditable()) {
						txtColorCode.requestFocus();
					} else if (txtColorCode.isEditable()) {
						txtColorCode.requestFocus();
					}
				} else {
					m_table.requestFocus();
				}
			}
		};

		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				"tabKey");
		m_table.getActionMap().put("tabKey", tabKey);
		m_table.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		m_table.getActionMap().put("shiftTab", tabKey);

		// change selected row
		ListSelectionModel rowSM = m_table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				convertTableRow2Txt();
			}
		});

		m_table.getTableHeader().setReorderingAllowed(false);

		tablePnl = new BaseScrollPane(m_table);
		tablePnl
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tablePnl;
	}

	private List getTableData() {

		if (colorService == null)
			colorService = new ColorServiceImpl();

		List retList = new ArrayList();
		if (cboColorCls.getSelectedIndex() > 0) {
			try {
				lstData = colorService.getLstMColorVo(cboColorCls
						.getSelectedKey(), 0);
			} catch (BizException e1) {
				e1.printStackTrace();
			}

			for (int i = 0; i < lstData.size(); i++) {
				List<String> list = new ArrayList<String>();
				list.add(lstData.get(i).getColorCode());
				list.add(lstData.get(i).getColorName());
				list.add(lstData.get(i).getColorRname());

				retList.add(list);
			}
		}

		return retList;
	}

	void UpdateColorList() {
		getTable();
		m_model.setData(getTableData());

		// Repanit table (paint Scroll Vertical)
		m_table.repaint();
		psTable.getViewport().removeAll();
		psTable.getViewport().add(m_table);
		m_table.requestFocus();
		if (m_model.getData().size() > 0) {
			m_table.changeSelection(0, 0, false, false);
		}
		resetData(true);
		setTitleInfor();
	}

	private void convertTableRow2Txt() {

		int selectIndex = m_table.getSelectedRow();
		String colorCode = "";

		if (lstData != null && lstData.size() > 0 && selectIndex >= 0) {
			for (int i = 0; i < m_table.getColumnCount(); i++) {
				if (m_table.getColumnName(i).equals(colHeadNm[0])) {
					colorCode = m_table.getValueAt(selectIndex, i).toString();
					break;
				}
			}
		}

		MColorVo selectedVo = new MColorVo();

		for (int i = 0; i < lstData.size(); i++) {
			if (colorCode.equalsIgnoreCase(lstData.get(i).getColorCode())) {
				selectedVo = lstData.get(i);
				break;
			}
		}

		convertVo2Txt(selectedVo);
	}

	private void convertVo2Txt(MColorVo dataVo) {
		txtColorCode.setText(dataVo.getColorCode());
		txtColorName.setText(dataVo.getColorName());
		txtColorRName.setText(dataVo.getColorRname());
		txtOrderNo.setText(StringUtils.convertIntegerToStr(dataVo
				.getDsporderNo()));

		if ("1".equalsIgnoreCase(dataVo.getDspFlg())) {
			chkDspFlg.setSelected(true);
			chkDspFlg.setText(NameConstants.getName("DSPFLG" + 1));
		} else {
			chkDspFlg.setSelected(false);
			chkDspFlg.setText(NameConstants.getName("DSPFLG" + 0));
		}

		lblSettingColor.setBackground(new Color(dataVo.getBclrRed(), dataVo
				.getBclrGreen(), dataVo.getBclrBlue()));
		lblSettingColor.setForeground(new Color(dataVo.getFclrRed(), dataVo
				.getFclrGreen(), dataVo.getFclrBlue()));
	}

	private MColorVo convertTxt2Vo() {
		MColorVo dataVo = new MColorVo();
		dataVo.setColorclsCode(cboColorCls.getSelectedKey());
		dataVo.setColorCode(txtColorCode.getText().trim());
		dataVo.setColorName(txtColorName.getText().trim());
		dataVo.setColorRname(txtColorRName.getText().trim());
		dataVo.setDsporderNo(NumberUtils.getIntFromString(txtOrderNo.getText()
				.trim()));

		dataVo.setBclrRed(lblSettingColor.getBackground().getRed());
		dataVo.setBclrGreen(lblSettingColor.getBackground().getGreen());
		dataVo.setBclrBlue(lblSettingColor.getBackground().getBlue());

		dataVo.setFclrRed(lblSettingColor.getForeground().getRed());
		dataVo.setFclrGreen(lblSettingColor.getForeground().getGreen());
		dataVo.setFclrBlue(lblSettingColor.getForeground().getBlue());

		if (chkDspFlg.isSelected() == false) {
			dataVo.setDspFlg("0");
		} else {
			dataVo.setDspFlg("1");
		}

		return dataVo;
	}

	private void resetData(boolean resetColorCode) {
		if (resetColorCode) {
			txtColorCode.setText("");
		}
		txtColorName.setText("");
		txtColorRName.setText("");
		txtOrderNo.setText("");
		chkDspFlg.setSelected(false);

		lblSettingColor.setBackground(Color.WHITE);
		lblSettingColor.setForeground(Color.BLACK);

		CUR_MODE = EditConstants.VIEW_MODE;
		setStatus(EditConstants.VIEW_MODE);
	}

	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		if (PermissionPolicy.checkMnExePermission(
				PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants
						.getLoginUser().getUserUser(), exeMenu) == false) {
			// Not permission show red text and disable btn
			lblPermission.setText("更新不可");
			lblPermission.setBorder(null);
		}

	}
}
