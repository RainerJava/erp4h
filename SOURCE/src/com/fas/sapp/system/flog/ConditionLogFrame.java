/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名		：エスイーシー化成
 * 
 *     ファイル名		：ConditionLogFrame.java
 *
 *     記述			：
 *     
 *     作成日			：Apr 12, 2010   
 *
 *     作成者			：Bui Ngoc Viet
 *
 *     備考			：
 *
 **************************************************************************************/

package com.fas.sapp.system.flog;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.fas.common.constants.FrameConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.button.ExitMasterButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.BaseDatePicker;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.TimeInputText;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.system.flog.FLogService;
import com.fas.service.system.flog.FLogServiceImpl;
import com.fas.vo.flog.FLogConditionVo;

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
public class ConditionLogFrame extends InspectFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** log */
	static Logger logger = Logger.getLogger(ConditionLogFrame.class);
	/** テーブルヘッダーサイズ */

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
	protected ExitMasterButton btnF12;
	private InspectRadioButton rdUserGroup;
	private InspectRadioButton rdUser;
	private BaseComboBox cbbName;
	private BaseInputText txtName;
	private BaseDatePicker dateFrom;
	private BaseDatePicker dateTo;
	private TimeInputText txtTimeFrom;
	private TimeInputText txtTimeTo;
	private BaseComboBox cbbMExe;
	private BaseCheckBox chkAll;
	private BaseCheckBox chkAD;
	private BaseCheckBox chkCL;
	private BaseCheckBox chkDE;
	private BaseCheckBox chkIN;
	private BaseCheckBox chkLI;
	private BaseCheckBox chkLO;
	private BaseCheckBox chkOP;
	private BaseCheckBox chkUP;
	private BaseInputText txtPC;
	private boolean flagCheckAll = false;
	private boolean flagCheckItem = false;
	private BaseLabel lblHelpInfor;

	private ComboService comboService;
	private PropertyChangeListener pro;
	private Timer timer;

	/** log service */
	// private LogService logService = new LogServiceImpl();

	private String getHelpInfor() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#getSubName()
	 */
	private String getSubName() {
		if (exeMenu != null) {
			return StringUtils.emptyIfNull(exeMenu.getMenuexeName());
		} else {
			return "";
		}
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param frame
	 * @param title
	 *            </DL>
	 */
	public ConditionLogFrame(BaseFrame frame, String title) {
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
	public ConditionLogFrame(BaseFrame frame) {
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
	public ConditionLogFrame() {
		super();
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.gui.AbstractFrame#initKodomo()
	 */
	protected void initKodomo() {
		comboService = new ComboServiceImpl();
	}

	// private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
	// if (btn != null) {
	// btn.addMouseListener(mouseAd);
	// }
	// }

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
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ExitMasterWindow exitWin = new ExitMasterWindow(btnF12);
		addWindowListener(exitWin);
		btnF12.setParentFrame(parentFrame);

		setDispTabFocus();

		// MouseAdapter mouseAdapter = new MouseAdapter() {
		// public void mouseEntered(MouseEvent e) {
		// getRootPane().setCursor(
		// Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// }
		//
		// public void mouseExited(MouseEvent e) {
		// getRootPane().setCursor(
		// Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// }
		// };
		// setMouseCurrsor(btnF1, mouseAdapter);
		// setMouseCurrsor(btnF2, mouseAdapter);
		// setMouseCurrsor(btnF3, mouseAdapter);
		// setMouseCurrsor(btnF4, mouseAdapter);
		// setMouseCurrsor(btnF5, mouseAdapter);
		// setMouseCurrsor(btnF6, mouseAdapter);
		// setMouseCurrsor(btnF7, mouseAdapter);
		// setMouseCurrsor(btnF8, mouseAdapter);
		// setMouseCurrsor(btnF9, mouseAdapter);
		// setMouseCurrsor(btnF10, mouseAdapter);
		// setMouseCurrsor(btnF11, mouseAdapter);
		// setMouseCurrsor(btnF12, mouseAdapter);
		reset();
	}

	protected void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}

	protected void setFrameSize() {

		X_FRAME_LENGTH = 610;
		Y_FRAME_LENGTH = 320;

		X_HEADER_LENGTH = 0;
		Y_HEADER_LENGTH = 0;

		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = 240;

		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 65;
	}

	protected BasePanel getHeader() {
		pro = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (("focusOwner".equals(prop))) {
					if (((e.getNewValue()) instanceof InspectRadioButton) || ((e.getNewValue()) instanceof BaseCalendarText)
							|| ((e.getNewValue()) instanceof BaseInputText) || ((e.getNewValue()) instanceof BaseCheckBox)
							|| ((e.getNewValue()) instanceof JButton) || ((e.getNewValue()) instanceof BaseComboBox)
							|| ((e.getNewValue()) instanceof TimeInputText)) {
						JComponent comp = (JComponent) e.getNewValue();
						String name = comp.getToolTipText();
						if (name != null) {
							setHelpInfor(name);
						}
					}
				}
			}
		};
		// パネルの生成
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(pro);
		return null;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param com
	 *            </DL>
	 */
	private void initMainPnl(BasePanel com) {
		com.setBorder(new TitledBorder("出力条件"));

		int yPos = 26;
		int xPos = 15;
		int I_LBL_LENGTH = 110;
		int I_LBL_HEIGHT = FaceContants.LABLE_HEIGHT;

		EditLabel lblUserGroup = new EditLabel("ユーザー");
		lblUserGroup.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lblUserGroup);

		ButtonGroup groupTemp = new ButtonGroup();
		rdUserGroup = new InspectRadioButton("グループ", false);
		rdUserGroup.setBounds(new Rectangle(xPos + I_LBL_LENGTH, yPos, 100, FaceContants.TEXT_HEIGHT));
		groupTemp.add(rdUserGroup);
		rdUser = new InspectRadioButton("担当者", true);
		rdUser.setBounds(new Rectangle(xPos + 300, yPos, 100, FaceContants.TEXT_HEIGHT));

		groupTemp.add(rdUser);
		com.add(rdUserGroup);
		com.add(rdUser);
		rdUserGroup.setSelected(!isUsed());
		rdUserGroup.setEnabled(!isUsed());
		rdUser.setSelected(isUsed());
		rdUser.setEnabled(isUsed());

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lbName = new EditLabel("担当者");
		lbName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lbName);
		try {
			if (isUsed()) {
				cbbName = new BaseComboBox(ApplicationUtils.createComboDataModel(comboService.getLstUser(), 8, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
			} else {
				cbbName = new BaseComboBox(ApplicationUtils.createComboDataModel(comboService.getLstUserGrp(), 8, 20, ArrayListComboBoxModel.CODE_SHOW_TYPE));
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbbName.setToolTipText("担当者 を選択して下さい。");
		cbbName.setBounds(xPos + I_LBL_LENGTH, yPos, 150, I_LBL_HEIGHT);
		com.add(cbbName);

		txtName = new BaseInputText("", 0, BaseInputText.IM_NONE, 20);
		txtName.setToolTipText("担当者 を入力して下さい。");
		txtName.setBounds(xPos + I_LBL_LENGTH + 150, yPos, 170, I_LBL_HEIGHT);
		txtName.setEditable(false);
		com.add(txtName);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblCust2Name = new EditLabel("日  付 ");
		lblCust2Name.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lblCust2Name);
		
		
		dateFrom = new BaseDatePicker();
		dateFrom.setToolTipText(StringUtils.trimAllVN(lblCust2Name.getText() + "を選択して下さい。"));
		dateFrom.setLocation(xPos + I_LBL_LENGTH + 1, yPos);
		dateFrom.setSize(150, 22);		
		dateFrom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(dateTo.getText().trim()) && !"".equals(dateFrom.getText().trim())) {
						Date fromDate = dateFrom.getDate();
						if (fromDate.compareTo(dateTo.getDate()) > 0) {
							dateTo.setDate(fromDate);
						}
					}
				} catch (Exception ex) {
				}
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
			}
		});
		
		
	
		com.add(dateFrom);
		


		
		JLabel lblTo = new JLabel(" ～");
		lblTo.setBounds(dateFrom.getX() + 150 , yPos, 20, I_LBL_HEIGHT);
		lblTo.setAlignmentX(CENTER_ALIGNMENT);
		lblTo.setLocation(dateFrom.getX() + 150, yPos);
		com.add(lblTo);	
		
		dateTo = new BaseDatePicker();
		dateTo.setToolTipText(StringUtils.trimAllVN(lblCust2Name.getText() +  "を選択して下さい。"));
		dateTo.setLocation(lblTo.getX() + 20, yPos);
		dateTo.setSize(150, 22);
		dateTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!"".equals(dateTo.getText().trim()) && !"".equals(dateFrom.getText().trim())) {
						Date toDate = dateTo.getDate();
						if (toDate.compareTo(dateFrom.getDate()) < 0) {
							dateFrom.setDate(toDate);
						}
					}
				} catch (Exception ex) {
//				}
//				if (validateCombo()){
//					if(flgDisable)setEnable(true);
//				}else{
//					setEnable(false);
				}
			}
		});
		
		com.add(dateTo);
		

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// Required Edit Label
		EditLabel lblTime = new EditLabel("時  刻");
		lblTime.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lblTime);
		// End Required Edit Label
		txtTimeFrom = new TimeInputText("", "##:##");
		txtTimeFrom.setToolTipText("時刻を入力して下さい。");
		txtTimeFrom.setBounds(xPos + I_LBL_LENGTH, yPos, 65, I_LBL_HEIGHT);
		com.add(txtTimeFrom);
		JLabel lblTimeTo = new JLabel("～");
		lblTimeTo.setBounds(xPos + I_LBL_LENGTH + 70, yPos, 20, I_LBL_HEIGHT);
		com.add(lblTimeTo);

		txtTimeTo = new TimeInputText("", "##:##");
		txtTimeTo.setToolTipText("時刻を入力して下さい。");
		txtTimeTo.setBounds(xPos + I_LBL_LENGTH + 20 + 65, yPos, 66, I_LBL_HEIGHT);
		com.add(txtTimeTo);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// Required Edit Label
		EditLabel lblKName = new EditLabel("機  能");
		lblKName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lblKName);
		// End Required Edit Label
		try {
			cbbMExe = new BaseComboBox(ApplicationUtils.createComboDataModel(comboService.getLstMenuExe(), 10, 20, ArrayListComboBoxModel.VALUE1_SHOW_TYPE));
		} catch (BizException e) {
			e.printStackTrace();
		}
		cbbMExe.setToolTipText("機能を選択して下さい。");
		cbbMExe.setBounds(xPos + I_LBL_LENGTH, yPos, 200, I_LBL_HEIGHT);
		com.add(cbbMExe);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblRName = new EditLabel("操  作");
		lblRName.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lblRName);

		chkAll = new BaseCheckBox("全 て");
		// chkAll.setToolTipText("得意先区分を入力して下さい。");
		// chkAll.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkAll.setBounds(new Rectangle(xPos + I_LBL_LENGTH, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkAll.setToolTipText("操作区分を選択して下さい。");
		com.add(chkAll);

		chkAD = new BaseCheckBox(NameConstants.getName("LOGACT" + "AD"));
		//chkAD = new BaseCheckBox("AD");
		// chkAD.setToolTipText("得意先区分を入力して下さい。");
		// chkAD.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		chkAD.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 100, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkAD.setToolTipText("操作区分を選択して下さい。");
		com.add(chkAD);

		chkCL = new BaseCheckBox(NameConstants.getName("LOGACT" + "CL"));
		// chkCL.setToolTipText("得意先区分を入力して下さい。");
		// chkCL.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkCL.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 200, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkCL.setToolTipText("操作区分を選択して下さい。");
		com.add(chkCL);

		chkDE = new BaseCheckBox(NameConstants.getName("LOGACT" + "DE"));
		// chkDE.setToolTipText("得意先区分を入力して下さい。");
		// chkDE.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		chkDE.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 300, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkDE.setToolTipText("操作区分を選択して下さい。");
		com.add(chkDE);

		chkIN = new BaseCheckBox(NameConstants.getName("LOGACT" + "IN"));
		// chkIN.setToolTipText("得意先区分を入力して下さい。");
		// chkIN.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		chkIN.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 400, yPos, 60, FaceContants.LABLE_HEIGHT));
		chkIN.setToolTipText("操作区分を選択して下さい。");
		com.add(chkIN);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		// ----------
		chkLI = new BaseCheckBox(NameConstants.getName("LOGACT" + "LI"));
		// chkLI.setToolTipText("得意先区分を入力して下さい。");
		// chkAD.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		chkLI.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 100, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkLI.setToolTipText("操作区分を選択して下さい。");
		com.add(chkLI);

		chkLO = new BaseCheckBox(NameConstants.getName("LOGACT" + "LO"));
		// chkLO.setToolTipText("得意先区分を入力して下さい。");
		// chkCL.setLocation(xPos + I_LBL_LENGTH, yPos);
		chkLO.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 200, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkLO.setToolTipText("操作区分を選択して下さい。");
		com.add(chkLO);

		chkOP = new BaseCheckBox(NameConstants.getName("LOGACT" + "OP"));
		// chkOP.setToolTipText("得意先区分を入力して下さい。");
		// chkDE.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		chkOP.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 300, yPos, 100, FaceContants.LABLE_HEIGHT));
		chkOP.setToolTipText("操作区分を選択して下さい。");
		com.add(chkOP);

		chkUP = new BaseCheckBox(NameConstants.getName("LOGACT" + "UP"));
		// chkUP.setToolTipText("得意先区分を入力して下さい。");
		// chkIN.setLocation(xPos + I_LBL_LENGTH + 150, yPos);
		chkUP.setBounds(new Rectangle(xPos + I_LBL_LENGTH + 400, yPos, 60, FaceContants.LABLE_HEIGHT));
		chkUP.setToolTipText("操作区分を選択して下さい。");
		com.add(chkUP);

		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblLabel = new EditLabel("PC 名");
		lblLabel.setBounds(xPos, yPos, I_LBL_LENGTH, I_LBL_HEIGHT);
		com.add(lblLabel);

		txtPC = new BaseInputText("", 0, BaseInputText.IM_OFF, 20);
		txtPC.setToolTipText("PC名 を入力して下さい。");
		txtPC.setBounds(xPos + I_LBL_LENGTH, yPos, 300, I_LBL_HEIGHT);
		com.add(txtPC);

		// event
		chkAll.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkAllItemStateChanged(evt);
			}
		});
		chkAD.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});
		chkCL.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});
		chkDE.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});
		chkIN.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});

		chkLI.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});
		chkLO.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});
		chkUP.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});
		chkOP.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				chkItemStateChanged(evt);
			}
		});

		cbbName.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cbbNameItemStateChanged(evt);
			}
		});
	}

	private void cbbNameItemStateChanged(java.awt.event.ItemEvent evt) {
		String s = StringUtils.objectToStr(cbbName.getSelectedValue1());
		txtName.setText(s);
	}

	private void chkAllItemStateChanged(java.awt.event.ItemEvent evt) {
		if (flagCheckItem)
			return;
		flagCheckAll = true;
		chkAD.setSelected(chkAll.isSelected());
		chkCL.setSelected(chkAll.isSelected());
		chkDE.setSelected(chkAll.isSelected());
		chkIN.setSelected(chkAll.isSelected());

		chkLI.setSelected(chkAll.isSelected());
		chkLO.setSelected(chkAll.isSelected());
		chkOP.setSelected(chkAll.isSelected());
		chkUP.setSelected(chkAll.isSelected());
		flagCheckAll = false;
	}

	private void chkItemStateChanged(java.awt.event.ItemEvent evt) {
		if (flagCheckAll)
			return;
		flagCheckItem = true;
		chkAll.setSelected(chkAD.isSelected() && chkCL.isSelected() && chkDE.isSelected() && chkIN.isSelected() &&

		chkLI.isSelected() && chkLO.isSelected() && chkOP.isSelected() && chkUP.isSelected());
		flagCheckItem = false;
	}

	class ShowCalendarAction implements com.fas.jface.button.Action {

		BaseCalendarText calendarObj;

		ShowCalendarAction(BaseCalendarText calendarObj) {
			this.calendarObj = calendarObj;
		}

		public void execute() {
			calendarObj.showCalendarDialog();
		}
	}

	private boolean isUsed() {
		return MCtlConstants.checkValue("SYSTEM" + "USR_TYPE", "1");
	}

	private FLogConditionVo convertToCondition() {
		FLogConditionVo logCondition = new FLogConditionVo();
		logCondition.setLogForUser(isUsed());
		logCondition.setName(txtName.getText().trim());
		logCondition.setDateFrom(dateFrom.getIntegerValue());
		logCondition.setDateTo(dateTo.getIntegerValue());
		logCondition.setTimeFrom(txtTimeFrom.getIntegerValueFloor());
		logCondition.setTimeTo(txtTimeTo.getIntegerValueCeiling());
		String strMenuExe = cbbMExe.getSelectedKey();
		if (strMenuExe.length() > 0) {
			String strGrp = strMenuExe.substring(0, 3);
			String strMenu = strMenuExe.substring(4, strMenuExe.length());
			logCondition.setMenuExeGrp(strGrp);
			logCondition.setMenuExeCode(strMenu);
		}

		logCondition.setAD(chkAD.isSelected());
		logCondition.setLI(chkLI.isSelected());
		logCondition.setDE(chkDE.isSelected());
		logCondition.setUP(chkUP.isSelected());

		logCondition.setCL(chkCL.isSelected());
		logCondition.setIN(chkIN.isSelected());
		logCondition.setOP(chkOP.isSelected());
		logCondition.setLO(chkLO.isSelected());

		logCondition.setPCName(txtPC.getText().trim());
		return logCondition;
	}

	/**
	 * 画面TABキー移動設定
	 */
	protected void setDispTabFocus() {

		List<Object> focusList = new ArrayList<Object>();

		focusList.add(cbbName);
		focusList.add(dateFrom);
		focusList.add(dateTo);
		focusList.add(txtTimeFrom);
		focusList.add(txtTimeTo);
		focusList.add(cbbMExe);
		focusList.add(chkAll);
		focusList.add(chkAD);
		focusList.add(chkCL);
		focusList.add(chkDE);
		focusList.add(chkIN);

		focusList.add(chkLI);
		focusList.add(chkLO);
		focusList.add(chkOP);
		focusList.add(chkUP);
		focusList.add(txtPC);

		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.BaseMasterFrame#doDoubleClick(int)
	 */

	@Override
	protected BasePanel getBodyPanel() {
		mainPnl = new BasePanel();
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		mainPnl.setLocation(0, 0);

		BasePanel pnlMain = new BasePanel();
		pnlMain.setSize(X_BODY_LENGTH, Y_BODY_LENGTH);
		pnlMain.setLocation(5, 1);
		pnlMain.setBorder(FaceContants.TITLE_BORDER);
		initMainPnl(pnlMain);

		mainPnl.add(pnlMain);

		return mainPnl;
	}

	private void initFooterMainPnl(BasePanel footerMainPnl) {
		int X_BTN_WIDTH = 71;
		int Y_BTN_HEIGHT = 36;
		int X_BTN_SPACE = 4;
		int yPos = 5;
		int xPos = 5;
		// int BTN_PANEL_HEIGHT = 45;
		// int BTN_PANEL_WIDTH = X_BODY_LENGTH;

		F4Action f4Action = new F4Action("f4Action");
		btnF4 = new ActionButton();
		btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF4.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF4.addActionListener(f4Action);
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), f4Action.getValue(Action.NAME));
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), f4Action.getValue(Action.NAME));
		btnF4.getActionMap().put("f4Action", f4Action);

		btnF4.setToolTipText("");
		btnF4.setEnabled(false);
		footerMainPnl.add(btnF4);

		F2Action f2Action = new F2Action("f2Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF2 = new ActionButton();
		btnF2.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF2.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF2.addActionListener(f2Action);
		btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt F"), f2Action.getValue(Action.NAME));
		btnF2.getActionMap().put("f2Action", f2Action);
		btnF2.setEnabled(false);
		footerMainPnl.add(btnF2);

		F6Action f6Action = new F6Action("f6Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF6 = new ActionButton();
		btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF6.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF6.addActionListener(f6Action);
		btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), f6Action.getValue(Action.NAME));
		btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f6Action.getValue(Action.NAME));
		btnF6.getActionMap().put("f6Action", f6Action);
		btnF6.setEnabled(false);
		footerMainPnl.add(btnF6);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		F8Action f8Action = new F8Action("f8Action");
		btnF8 = new ActionButton();
		btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF8.setText("<html><center><font size=-1>実行</font></center><center><font size=-1>F8(A)</font></center>");
		btnF8.addActionListener(f8Action);
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
		btnF8.getActionMap().put("f8Action", f8Action);
		btnF8.setToolTipText("[F8]：検索処理を実行します。");
		footerMainPnl.add(btnF8);

		F9Action f9Action = new F9Action("f9Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF9 = new ActionButton();
		btnF9.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF9.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF9.addActionListener(f9Action);
		btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), f9Action.getValue(Action.NAME));
		btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f9Action.getValue(Action.NAME));
		btnF9.getActionMap().put("f9Action", f9Action);
		btnF9.setEnabled(false);
		footerMainPnl.add(btnF9);

		F10Action f10Action = new F10Action("f10Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF10 = new ActionButton();
		btnF10.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF10.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
		btnF10.addActionListener(f10Action);
		btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), f10Action.getValue(Action.NAME));
		btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f10Action.getValue(Action.NAME));
		btnF10.getActionMap().put("f10Action", f10Action);
		btnF10.setEnabled(false);
		footerMainPnl.add(btnF10);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF11 = new ActionButton();
		F11Action f11Action = new F11Action("f11Action");
		btnF11.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF11.setText("<html><center><font size=-1>中止</font></center><font size=-1>F11(C)</font>");
		btnF11.addActionListener(f11Action);
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), f11Action.getValue(Action.NAME));
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt C"), f11Action.getValue(Action.NAME));
		btnF11.getActionMap().put("f11Action", f11Action);
		btnF11.setToolTipText("[F11]：処理を中止します。");
		footerMainPnl.add(btnF11);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;

		btnF12 = new ExitMasterButton();
		btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.setToolTipText("[F12]：処理を終了します。");
		btnF12.setConfirmRerquired(false);
		btnF12.setFrame(getFrame());
		btnF12.setFocusable(false);
		footerMainPnl.add(btnF12);

		// btnF12 = new ExitMasterButton();
		// F12Action f12Action = new F12Action("f12Action");
		// btnF12.addActionListener(f12Action);
		// btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		// btnF12
		// .setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
		// btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		// KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
		// btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		// KeyStroke.getKeyStroke("alt Q"),
		// f12Action.getValue(Action.NAME));
		// btnF12.getActionMap().put("f12Action", f12Action);
		// btnF12.setToolTipText("[F12]：処理を終了します。");
		// footerMainPnl.add(btnF12);
	}

	@Override
	protected BasePanel getFooter() {
		int xPos = 1;
		int yPos = 1;
		final int I_LBL_HEIGHT = 22;

		footerMainPnl = new BasePanel();
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH, Y_FOOTER_LENGTH));
		footerMainPnl.setLocation(0, Y_BODY_LENGTH);

		BasePanel pnlButton = new BasePanel();
		pnlButton.setSize(X_FOOTER_LENGTH - 2, Y_FOOTER_LENGTH - 20);
		pnlButton.setLocation(5, 1);
		pnlButton.setBorder(FaceContants.TITLE_BORDER);
		initFooterMainPnl(pnlButton);

		footerMainPnl.add(pnlButton);

		BasePanel statusBar = new BasePanel();
		statusBar.setSize(new Dimension(X_FOOTER_LENGTH - 4, Y_FOOTER_LENGTH - 4));
		statusBar.setLocation(5, 50);
		statusBar.setLayout(null);
		statusBar.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor.setSize(new Dimension(270, I_LBL_HEIGHT));
		lblHelpInfor.setLocation(xPos, yPos);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);

		xPos += 270 + 2;
		final BaseLabel lblDate = new BaseLabel();
		lblDate.setSize(new Dimension(160, I_LBL_HEIGHT));
		lblDate.setLocation(xPos, yPos);
		lblDate.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblDate.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblDate.setText(getSystemDateTime());
		lblDate.setBorder(FaceContants.LABEL_BORDER);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDate.setText(getSystemDateTime());
				repaint();
			}
		});
		timer.start();
		statusBar.add(lblDate);

		xPos += 160 + 2;
		BaseLabel lblUser = new BaseLabel();
		lblUser.setSize(new Dimension(85, I_LBL_HEIGHT));
		lblUser.setLocation(xPos, yPos);
		lblUser.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblUser.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblUser.setBorder(FaceContants.LABEL_BORDER);
		lblUser.setText(getUserName());
		statusBar.add(lblUser);

		xPos += 85 + 2;
		BaseLabel lblPc = new BaseLabel();
		lblPc.setSize(new Dimension(90, I_LBL_HEIGHT));
		lblPc.setLocation(xPos, yPos);
		lblPc.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblPc.setText(getPCName());
		lblPc.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblPc.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblPc);

		xPos += 90 + 2;
		BaseLabel lblSpace = new BaseLabel();
		lblSpace.setSize(new Dimension(X_FOOTER_LENGTH - xPos, I_LBL_HEIGHT));
		lblSpace.setLocation(xPos, yPos);
		lblSpace.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblSpace.setText("");
		lblSpace.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblSpace.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblSpace);
		footerMainPnl.add(statusBar);
		return footerMainPnl;
	}

	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}

	class F1Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F1Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF1();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>最初</DD>
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF2();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>上へ</DD>
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF3();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>下へ</DD>
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			btnF4.setEnabled(true);
			doF4();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>最後</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF4() {
		// SearchTantousyaFrame search = new SearchTantousyaFrame(getFrame());
		//
		// search.pack();
		// search.setModal(true);
		// search.setVisible(true);
		// SearchVo searchData = search.getReturnData();
		// if (searchData != null) {
		// cbbName.setSelectedItem(searchData.getProPer1());
		// }
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF5();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>追加</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF5() {

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF6();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>削除</DD>
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
	protected void doF8() {
		if (!validation())
			return;
		FLogConditionVo con = convertToCondition();
		FLogService log = new FLogServiceImpl();
		try {
			if (log.countLogInfor(con) == 0) {
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0003").setExtraInfor(""));
				return;
			}
		} catch (BizException e1) {
			e1.printStackTrace();
		}

		try {

			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						SplashScreen sp = new SplashScreen();
						sp.setVisible(true);
						sp.setAlwaysOnTop(true);
						Thread.sleep(1000);
						FLogConditionVo con = convertToCondition();
						ResultLogFrame srf = new ResultLogFrame(con);
						if (srf != null) {
							srf.setParentFrame(getFrame());
							srf.pack();
							srf.setVisible(true);
							getFrame().setVisible(false);
							sp.setVisible(false);
							sp.dispose();
							FrameConstants.addNameFrame("800" + "390" + "1", "操作ログ確認");
							srf.setExtendedState(JFrame.NORMAL);
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
				}
			});
			t.start();
		} catch (Exception e) {
		}
	}

	private boolean validation() {
		if (dateFrom.getIntegerValue() > dateTo.getIntegerValue()) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0003").setExtraInfor("日  付"));
			dateFrom.requestFocus();
			return false;
		}
		return true;
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF10();
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF11();
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
	protected void doF11() {
		reset();
	}

	private Calendar cal = Calendar.getInstance();

	private void reset() {
		cbbName.setSelectedIndex(0);
		cbbName.repaint();
		cbbMExe.setSelectedIndex(0);
		cbbName.repaint();
		txtName.setText("");
		txtPC.setText("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int yearMonth = DateUtils.getCurrentDate() / 100 * 100; // yyyyMM00
		try {
			dateFrom.setDate(sdf.parse("" + (yearMonth + cal.getActualMinimum(Calendar.DATE))));
			dateTo.setDate(sdf.parse("" + (yearMonth + cal.getActualMaximum(Calendar.DATE))));
		} catch (ParseException e) {
			dateFrom.setDate(null);
			dateTo.setDate(null);
		}
		// dateFrom.setText(DateUtils.getDateWithSplitYobi(DateUtils.getDateCurrent()));

		txtTimeFrom.setText("00:00");
		txtTimeTo.setText("23:59");
		chkAll.setSelected(true);
		chkIN.setSelected(true);
		chkAD.setSelected(true);
		chkLI.setSelected(true);
		chkDE.setSelected(true);

		chkOP.setSelected(true);
		chkUP.setSelected(true);
		chkLO.setSelected(true);
		chkCL.setSelected(true);
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF12();
		}
	}

	public class ExitMasterWindow extends WindowAdapter {

		/** */
		private JButton btnAction;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public ExitMasterWindow(JButton btnButton) {
			btnAction = btnButton;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
		 */
		public void windowClosing(WindowEvent e) {
			btnAction.doClick();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.WindowAdapter#windowOpened(java.awt.event.WindowEvent)
		 */
		public void windowOpened(WindowEvent e) {
			if (parentFrame != null) {
				parentFrame.setFocusableWindowState(true);
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
	 */
	protected void doF12() {
		// if (isHenkouChuu) {
		// if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001"))
		// == MessageBox.YES) {
		// dispose();
		// }
		// } else {
		// dispose();
		// }
		dispose();
	}

	@Override
	public void stopTimerRemoveListener() {
		if (timer.isRunning()) {
			timer.stop();
			timer = null;
		}
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.removePropertyChangeListener(pro);
	}
}
