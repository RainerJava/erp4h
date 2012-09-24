/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：MyMenuFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/03/02   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.sapp.system.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.ExitWindow;
import com.fas.jface.FocusPolicy;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.BTable;
import com.fas.jface.table.BTableModelData;
import com.fas.jface.table.ColumnData;
import com.fas.jface.table.GenericCellRenderer;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.textarea.BaseTextArea;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.system.menu.MenuService;
import com.fas.service.system.menu.MenuServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.menuexe.MenuExeVo;

/**
 * @author PC13
 * 
 */
public class MyMenuFrame extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected ActionButton btnF12;
	/** */
	protected ActionButton btnF11;
	/** */
	protected ActionButton btnF10;
	/** */
	protected ActionButton btnF9;
	/** */
	protected ActionButton btnF8;
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
	private BaseLabel lblHelpInfor;
	/** */
	private ActionButton btnAdd;
	/** */
	private ActionButton btnDel;
	/** */
	private ActionButton btnDelAll;
	/** */
	private ActionButton btnFirstOrder;
	/** */
	private ActionButton btnUpOrder;
	/** */
	private ActionButton btnDownOrder;
	/** */
	private ActionButton btnLastOrder;
	/** */
	protected int LBODY_PANEL_WIDTH = 400;
	/** */
	protected int RBODY_PANEL_WIDTH = 500;
	/** */
	protected String CUR_MODE = "";
	/** */
	protected BTable m_rtable;
	/** */
	protected MenuExeTableData m_rdata;
	/** */
	protected BTable m_ltable;
	/** */
	protected MenuExeTableData m_ldata;
	/** */
	private MenuService menuService;
	/** */
	protected int ROW_PER_PAGE;
	/** */
	private InspectRadioButton rdKinou;
	/** */
	private InspectRadioButton rdFile;
	/** */
	private BaseInputText txtFile;
	/** */
	private BaseTextArea txtFileMei;
	/** */
	private ActionButton btnFileMei;
	/** */
	private BaseTextArea txtParameter;
	/** */
	private ActionButton btnParameter;
	/** */
	private MenuGrpCombobox comboMnGrp;
	/** */
	static Logger logger = Logger.getLogger(MyMenuFrame.class);
	/** */
	private boolean isHenkouChuu = false;
	private PropertyChangeListener pro;
	private Timer timer;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public MyMenuFrame() {
		super();
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
	public MyMenuFrame(BaseFrame pFrame, boolean modal) {
		super(pFrame, modal);
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

		setTitle("マイメニュー登録");
		CUR_MODE = "";
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new ExitWindow(btnF12));

		setResizable(false);
		// setIconImage(ImageConstants.TITLE.getImage());

		// MouseAdapter mouseAdapter = new MouseAdapter() {
		// public void mouseEntered(MouseEvent e) {
		// getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// }
		//
		// public void mouseExited(MouseEvent e) {
		// getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// }
		// };
		//
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
		// setMouseCurrsor(btnAdd, mouseAdapter);
		// setMouseCurrsor(btnDel, mouseAdapter);
		// setMouseCurrsor(btnDelAll, mouseAdapter);
		// setMouseCurrsor(btnFirstOrder, mouseAdapter);
		// setMouseCurrsor(btnUpOrder, mouseAdapter);
		// setMouseCurrsor(btnDownOrder, mouseAdapter);
		// setMouseCurrsor(btnLastOrder, mouseAdapter);
		// setMouseCurrsor(btnFileMei, mouseAdapter);
		// setMouseCurrsor(btnParameter, mouseAdapter);

		btnF1.setFocusable(false);
		btnF2.setFocusable(false);
		btnF3.setFocusable(false);
		btnF4.setFocusable(false);
		btnF5.setFocusable(false);
		btnF6.setFocusable(false);
		btnF7.setFocusable(false);
		btnF8.setFocusable(false);
		btnF11.setFocusable(false);
		btnF12.setFocusable(false);
		btnFileMei.setFocusable(false);
		btnParameter.setFocusable(false);
		
		pro = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (("focusOwner".equals(prop))) {
					if (((e.getNewValue()) instanceof InspectRadioButton) 
							|| ((e.getNewValue()) instanceof MenuGrpCombobox) 
							|| ((e.getNewValue()) instanceof BaseInputText) 
							|| ((e.getNewValue()) instanceof BaseTextArea) 
							|| ((e.getNewValue()) instanceof JButton)) {
						JComponent comp = (JComponent) e.getNewValue();
						String name = StringUtils.emptyIfNull(comp.getToolTipText());
						setHelpInfor(name);
					}
				}
			}
		};
		
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(pro);

		isHenkouChuu = false;

		AbstractAction ltabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_ltable.isFocusOwner()) {
					rdFile.requestFocus();
				}
			}
		};
		m_ltable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabKey");
		m_ltable.getActionMap().put("tabKey", ltabKey);

		AbstractAction lShiftTabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_ltable.isFocusOwner()) {
					comboMnGrp.requestFocus();
				}
			}
		};
		m_ltable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "lShiftTabKey");
		m_ltable.getActionMap().put("lShiftTabKey", lShiftTabKey);

		AbstractAction rtabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_rtable.isFocusOwner()) {
					rdKinou.requestFocus();
				}
			}
		};
		m_rtable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "rtabKey");
		m_rtable.getActionMap().put("rtabKey", rtabKey);

		AbstractAction rShiftTabKey = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_rtable.isFocusOwner()) {
					rdFile.requestFocus();
				}
			}
		};
		m_rtable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "rShiftTabKey");
		m_rtable.getActionMap().put("rShiftTabKey", rShiftTabKey);
	}

	// /**
	// * <DL>
	// * <DT>メソッド記述：</DT>
	// * <DD></DD>
	// * <BR>
	// *
	// * </DL>
	// * @param btn
	// * @param mouseAd
	// */
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
	 * 
	 * @param strInfor
	 */
	protected void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}

	@Override
	protected BasePanel getBodyPanel() {
		// パネルの生成
		mainPnl = new BasePanel();
		mainPnl.setLayout(null);
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		int xPos = 3;
		int yPos = 1;

		int SPACE_WIDTH = 5;
		int SPACE_HEIGHT = 5;
		int BODY_PANEL_HEGHT = Y_BODY_LENGTH - SPACE_WIDTH - 45;
		LBODY_PANEL_WIDTH = 435;
		RBODY_PANEL_WIDTH = X_BODY_LENGTH - LBODY_PANEL_WIDTH - SPACE_WIDTH - 2 * xPos;

		/** 左ボディーテーブル */
		xPos = 3;
		yPos = 1;
		BasePanel lInputPnl = new BasePanel();
		lInputPnl.setLayout(null);
		lInputPnl.setLocation(xPos, yPos);
		lInputPnl.setSize(new Dimension(LBODY_PANEL_WIDTH, BODY_PANEL_HEGHT));
		initLeftBody(lInputPnl);
		lInputPnl.setBorder(null);
		mainPnl.add(lInputPnl);

		/** マイメニュー一覧テーブルパネル */
		xPos += LBODY_PANEL_WIDTH + SPACE_WIDTH;
		BasePanel rInputPnl = new BasePanel();
		rInputPnl.setLayout(null);
		rInputPnl.setLocation(xPos, yPos);
		rInputPnl.setSize(new Dimension(RBODY_PANEL_WIDTH, BODY_PANEL_HEGHT));
		initRightBody(rInputPnl);
		rInputPnl.setBorder(null);
		mainPnl.add(rInputPnl);

		/** ボタンパネル */
		xPos = 3;
		yPos += BODY_PANEL_HEGHT + SPACE_HEIGHT;
		BasePanel btnPnl = new BasePanel();
		btnPnl.setLayout(null);
		btnPnl.setLocation(xPos, yPos);
		initButtonPnl(btnPnl);
		mainPnl.add(btnPnl);

		switchTourokuMode();

		return mainPnl;
	}

	private void initButtonPnl(BasePanel btnPnl) {

		int X_BTN_WIDTH = 71;
		int Y_BTN_HEGITH = 36;
		int X_BTN_SPACE = 4;
		int yPos = 4;
		int xPos = 7 - X_BTN_WIDTH - X_BTN_SPACE;
		int BTN_PANEL_HEIGHT = 45;
		int BTN_PANEL_WIDTH = X_BODY_LENGTH;

		F1Action f1Action = new F1Action("f1Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF1 = new ActionButton();
		btnF1.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF1.setText("<html><center><font size=-1>最初 へ</font></center><center><font size=-1>F1(E)</font></center>");
		btnF1.addActionListener(f1Action);
		btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), f1Action.getValue(Action.NAME));
		btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt E"), f1Action.getValue(Action.NAME));
		btnF1.getActionMap().put("f1Action", f1Action);
		btnPnl.add(btnF1);
		btnFirstOrder.addActionListener(f1Action);

		F2Action f2Action = new F2Action("f2Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF2 = new ActionButton();
		btnF2.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF2.setText("<html><center><font size=-1>上 へ</font></center><center><font size=-1>F2(F)</font></center>");
		btnF2.addActionListener(f2Action);
		btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt F"), f2Action.getValue(Action.NAME));
		btnF2.getActionMap().put("f2Action", f2Action);
		btnPnl.add(btnF2);
		m_rtable.getInputMap().put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		m_rtable.getActionMap().put("f2Action", f2Action);
		m_ltable.getInputMap().put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		m_ltable.getActionMap().put("f2Action", f2Action);
		btnUpOrder.addActionListener(f2Action);

		F3Action f3Action = new F3Action("f3Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF3 = new ActionButton();
		btnF3.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF3.setText("<html><center><font size=-1>下 へ</font></center><center><font size=-1>F3(G)</font></center>");
		btnF3.addActionListener(f3Action);
		btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), f3Action.getValue(Action.NAME));
		btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt G"), f3Action.getValue(Action.NAME));
		btnF3.getActionMap().put("f3Action", f3Action);
		btnPnl.add(btnF3);
		btnDownOrder.addActionListener(f3Action);

		F4Action f4Action = new F4Action("f4Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF4 = new ActionButton();
		btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF4.setText("<html><center><font size=-1>最後 へ</font></center><center><font size=-1>F4(S)</font></center>");
		btnF4.addActionListener(f4Action);
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), f4Action.getValue(Action.NAME));
		btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), f4Action.getValue(Action.NAME));
		btnF4.getActionMap().put("f4Action", f4Action);
		btnPnl.add(btnF4);
		btnLastOrder.addActionListener(f4Action);

		F5Action f5Action = new F5Action("f5Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF5 = new ActionButton();
		btnF5.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF5.setText("<html><center><font size=-1>&gt; 追加</font></center><center><font size=-1>F5(T)</font></center>");
		btnF5.addActionListener(f5Action);
		btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), f5Action.getValue(Action.NAME));
		btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt T"), f5Action.getValue(Action.NAME));
		btnF5.getActionMap().put("f5Action", f5Action);
		btnPnl.add(btnF5);
		btnAdd.addActionListener(f5Action);

		F6Action f6Action = new F6Action("f6Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF6 = new ActionButton();
		btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF6.setText("<html><center><font size=-1>&lt; 削除</font></center><center><font size=-1>F6(D)</font></center>");
		btnF6.addActionListener(f6Action);
		btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), f6Action.getValue(Action.NAME));
		btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f6Action.getValue(Action.NAME));
		btnF6.getActionMap().put("f6Action", f6Action);
		btnPnl.add(btnF6);
		btnDel.addActionListener(f6Action);

		F7Action f7Action = new F7Action("f7Action");
		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF7 = new ActionButton();
		btnF7.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF7.setText("<html><center><font size=-1>&lt;&lt; 全削除</font></center><center><font size=-1>F7(W)</font></center>");
		btnF7.addActionListener(f7Action);
		btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), f7Action.getValue(Action.NAME));
		btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt W"), f7Action.getValue(Action.NAME));
		btnF7.getActionMap().put("f7Action", f7Action);
		btnPnl.add(btnF7);
		btnDelAll.addActionListener(f7Action);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		F8Action f8Action = new F8Action("f8Action");
		btnF8 = new ActionButton();
		btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF8.setText("<html><center><font size=-1>登録</font></center><center><font size=-1>F8(A)</font></center>");
		btnF8.addActionListener(f8Action);
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
		btnF8.getActionMap().put("f8Action", f8Action);
		btnPnl.add(btnF8);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF11 = new ActionButton();
		F11Action f11Action = new F11Action("f11Action");
		btnF11.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF11.setText("<html><center><font size=-1>中止</font></center><font size=-1>F11(C)</font>");
		btnF11.addActionListener(f11Action);
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), f11Action.getValue(Action.NAME));
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt C"), f11Action.getValue(Action.NAME));
		btnF11.getActionMap().put("f11Action", f11Action);
		btnPnl.add(btnF11);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF12 = new ActionButton();
		F12Action f12Action = new F12Action("f12Action");
		btnF12.addActionListener(f12Action);
		btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), f12Action.getValue(Action.NAME));
		btnF12.setToolTipText("[F12]：処理を終了します。");
		btnF12.getActionMap().put("f12Action", f12Action);
		btnPnl.add(btnF12);

		BTN_PANEL_WIDTH = xPos + X_BTN_WIDTH + 3 * X_BTN_SPACE;
		btnPnl.setSize(new Dimension(BTN_PANEL_WIDTH, BTN_PANEL_HEIGHT));
		btnPnl.setBorder(BorderFactory.createTitledBorder(""));
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	private void switchTourokuMode() {
		if (rdKinou.isSelected()) {
			btnFileMei.setEnabled(false);
			btnParameter.setEnabled(false);
			txtFile.setEnabled(false);
			txtFileMei.setEnabled(false);
			txtParameter.setEnabled(false);
			m_ltable.setEnabled(true);
			comboMnGrp.setEnabled(true);
			txtFile.setText("");
			txtFileMei.setText("");
			txtParameter.setText("");
			isHenkouChuu = false;
			comboMnGrp.setSelectedIndex(0);
		} else {
			comboMnGrp.setSelectedIndex(0);
			btnFileMei.setEnabled(true);
			btnParameter.setEnabled(true);
			txtFile.setEnabled(true);
			txtFileMei.setEnabled(true);
			txtParameter.setEnabled(true);
			m_ltable.setEnabled(false);
			comboMnGrp.setEnabled(false);
			isHenkouChuu = false;
		}
		setDispTabFoucus();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param lInputPnl
	 */
	private void initLeftBody(BasePanel lInputPnl) {

		int xPos = 8;
		int yPos = 9;
		rdKinou = new InspectRadioButton("機能");
		rdKinou.setLocation(xPos, yPos);
		rdKinou.setSize(100, FaceContants.TEXT_HEIGHT);
		lInputPnl.add(rdKinou);

		List<ComboObjectVo> lstData = null;
		ComboService comService = new ComboServiceImpl();
		try {
			lstData = comService.getLstMenuGrpVo(ApplicationConstants.getLoginUser());
		} catch (BizException e) {
			logger.error(e.getMessage());
			lstData = new ArrayList<ComboObjectVo>();
		}
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData);
		aModel.setIShowType(ArrayListComboBoxModel.VALUE1_SHOW_TYPE);
		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE;
		comboMnGrp = new MenuGrpCombobox(aModel);
		comboMnGrp.setLocation(xPos, yPos);
		comboMnGrp.setSize(250, FaceContants.TEXT_HEIGHT);
		comboMnGrp.setToolTipText("メニューグループを選択してください。");
		comboMnGrp.setPopupWidth(250);
		lInputPnl.add(comboMnGrp);

		// 左のテーブル
		yPos += FaceContants.TEXT_HEIGHT + 1;
		BaseScrollPane menuExeTablePnl = new BaseScrollPane();
		menuExeTablePnl.setLocation(xPos, yPos);
		menuExeTablePnl.setSize(comboMnGrp.getWidth(), 203);
		m_ldata = new MenuExeTableData(false);
		m_ltable = new BTable();
		initTable(m_ltable, m_ldata);
		m_ltable.setSize(new Dimension(menuExeTablePnl.getWidth(), menuExeTablePnl.getHeight()));
		menuExeTablePnl.getViewport().add(m_ltable);
		lInputPnl.add(menuExeTablePnl);

		yPos += menuExeTablePnl.getHeight() + FaceContants.VERTICAL_SPACE;
		rdFile = new InspectRadioButton("ファイル");
		rdFile.setLocation(xPos, yPos);
		rdFile.setSize(100, FaceContants.TEXT_HEIGHT);
		lInputPnl.add(rdFile);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdFile);
		btnGroup.add(rdKinou);

		ActionListener rdAction = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switchTourokuMode();
			}

		};

		rdKinou.setSelected(true);
		rdKinou.addActionListener(rdAction);
		rdFile.addActionListener(rdAction);

		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblMeishou = new EditLabel("名称");
		lblMeishou.setLocation(xPos, yPos);
		lblMeishou.setSize(80, FaceContants.TEXT_HEIGHT);
		lInputPnl.add(lblMeishou);

		xPos += lblMeishou.getWidth();
		txtFile = new BaseInputText();
		txtFile.setLocation(xPos, yPos);
		txtFile.setSize(250, FaceContants.TEXT_HEIGHT);
		txtFile.setToolTipText("メニューに表示する名前を入力してください。");
		lInputPnl.add(txtFile);

		xPos = 8;
		yPos += FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblFileMei = new EditLabel("ファイル名");
		lblFileMei.setLocation(xPos, yPos);
		lblFileMei.setSize(80, 2 * FaceContants.TEXT_HEIGHT);
		lInputPnl.add(lblFileMei);

		xPos += lblFileMei.getWidth();
		txtFileMei = new BaseTextArea();
		txtFileMei.setLocation(1, 0);
		txtFileMei.setSize(285, 2 * FaceContants.TEXT_HEIGHT);
		txtFileMei.setLineWrap(true);
		txtFileMei.setToolTipText("ファイルを指定して下さい。");
		BaseScrollPane psFileMei = new BaseScrollPane();
		psFileMei.setSize(285, 2 * FaceContants.TEXT_HEIGHT);
		psFileMei.getViewport().add(txtFileMei);
		psFileMei.setLocation(xPos, yPos);
		lInputPnl.add(psFileMei);

		xPos += txtFileMei.getWidth() + 1;
		btnFileMei = new ActionButton();
		btnFileMei.setLocation(xPos, yPos);
		btnFileMei.setSize(60, 2 * FaceContants.TEXT_HEIGHT);
		btnFileMei.setText("参照");
		btnFileMei.addAction(new DialogFileAction(txtFileMei));
		lInputPnl.add(btnFileMei);

		xPos = 8;
		yPos += 2 * FaceContants.TEXT_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblParameter = new EditLabel("パラメータ");
		lblParameter.setLocation(xPos, yPos);
		lblParameter.setSize(80, 2 * FaceContants.TEXT_HEIGHT);
		lInputPnl.add(lblParameter);

		xPos += lblFileMei.getWidth();
		txtParameter = new BaseTextArea();
		txtParameter.setLocation(xPos, yPos);
		txtParameter.setSize(285, 2 * FaceContants.TEXT_HEIGHT);
		txtParameter.setLineWrap(true);
		txtParameter.setToolTipText("パラメータを指定して下さい。");
		BaseScrollPane psParameter = new BaseScrollPane();
		psParameter.setSize(285, 2 * FaceContants.TEXT_HEIGHT);
		psParameter.getViewport().add(txtParameter);
		psParameter.setLocation(xPos, yPos);
		lInputPnl.add(psParameter);

		xPos += txtParameter.getWidth() + 1;
		btnParameter = new ActionButton();
		btnParameter.setLocation(xPos, yPos);
		btnParameter.setSize(60, 2 * FaceContants.TEXT_HEIGHT);
		btnParameter.setText("参照");
		btnParameter.addAction(new DialogFileAction(txtParameter));
		lInputPnl.add(btnParameter);

		yPos = 1;
		xPos = 8 + menuExeTablePnl.getWidth() + 50;
		BasePanel pnlAddDel = new BasePanel();
		pnlAddDel.setLocation(xPos, yPos);
		pnlAddDel.setSize(120, 115);
		pnlAddDel.setBorder(BorderFactory.createTitledBorder("追加と削除"));
		initAddDelPnl(pnlAddDel);
		lInputPnl.add(pnlAddDel);

		yPos = pnlAddDel.getHeight() + 8;
		BasePanel pnlOrderChange = new BasePanel();
		pnlOrderChange.setLocation(xPos, yPos);
		pnlOrderChange.setSize(120, 142);
		pnlOrderChange.setBorder(BorderFactory.createTitledBorder("順序変更"));
		initOrderChangePnl(pnlOrderChange);
		lInputPnl.add(pnlOrderChange);

		NextFocusAction nextFocusAction = new NextFocusAction("nextFocusAction");
		PrevFocusAction prevFocusAction = new PrevFocusAction("prevFocusAction");
		txtFileMei.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), nextFocusAction.getValue(Action.NAME));
		txtParameter.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), nextFocusAction.getValue(Action.NAME));
		txtFileMei.getActionMap().put("nextFocusAction", nextFocusAction);
		txtParameter.getActionMap().put("nextFocusAction", nextFocusAction);
		txtFileMei.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), prevFocusAction.getValue(Action.NAME));
		txtParameter.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), prevFocusAction.getValue(Action.NAME));
		txtFileMei.getActionMap().put("prevFocusAction", prevFocusAction);
		txtParameter.getActionMap().put("prevFocusAction", prevFocusAction);
	}

	/**
	 * @author PC13
	 * 
	 */
	class NextFocusAction extends AbstractAction {
		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public NextFocusAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent evt) {
			((Component) evt.getSource()).transferFocus();
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class PrevFocusAction extends AbstractAction {
		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public PrevFocusAction(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent evt) {
			((Component) evt.getSource()).transferFocusBackward();
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlAddDel
	 */
	private void initAddDelPnl(BasePanel pnlAddDel) {

		int xPos = 13;
		int yPos = 20;
		int X_BTN_WIDTH = 90;
		int Y_BTN_HEIGHT = 25;

		btnAdd = new ActionButton();
		btnAdd.setLocation(xPos, yPos);
		btnAdd.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnAdd.setText("<html><center><font size=-1>&gt; 追加</font></center>");
		pnlAddDel.add(btnAdd);

		yPos += Y_BTN_HEIGHT + FaceContants.VERTICAL_SPACE;
		btnDel = new ActionButton();
		btnDel.setLocation(xPos, yPos);
		btnDel.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnDel.setText("<html><center><font size=-1>&lt; 削除</font></center>");
		pnlAddDel.add(btnDel);

		yPos += Y_BTN_HEIGHT + FaceContants.VERTICAL_SPACE;
		btnDelAll = new ActionButton();
		btnDelAll.setLocation(xPos, yPos);
		btnDelAll.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnDelAll.setText("<html><center><font size=-1>&lt;&lt; 全削除</font></center>");
		pnlAddDel.add(btnDelAll);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnlOrderChange
	 */
	private void initOrderChangePnl(BasePanel pnlOrderChange) {

		int xPos = 13;
		int yPos = 20;
		int X_BTN_WIDTH = 90;
		int Y_BTN_HEIGHT = 25;

		btnFirstOrder = new ActionButton();
		btnFirstOrder.setLocation(xPos, yPos);
		btnFirstOrder.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnFirstOrder.setText("<html><center><font size=-1>最初 へ</font></center>");
		pnlOrderChange.add(btnFirstOrder);

		yPos += Y_BTN_HEIGHT + FaceContants.VERTICAL_SPACE;
		btnUpOrder = new ActionButton();
		btnUpOrder.setLocation(xPos, yPos);
		btnUpOrder.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnUpOrder.setText("<html><center><font size=-1>上 へ</font></center>");
		pnlOrderChange.add(btnUpOrder);

		yPos += Y_BTN_HEIGHT + FaceContants.VERTICAL_SPACE;
		btnDownOrder = new ActionButton();
		btnDownOrder.setLocation(xPos, yPos);
		btnDownOrder.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnDownOrder.setText("<html><center><font size=-1>下 へ</font></center>");
		pnlOrderChange.add(btnDownOrder);

		yPos += Y_BTN_HEIGHT + FaceContants.VERTICAL_SPACE;
		btnLastOrder = new ActionButton();
		btnLastOrder.setLocation(xPos, yPos);
		btnLastOrder.setSize(X_BTN_WIDTH, Y_BTN_HEIGHT);
		btnLastOrder.setText("<html><center><font size=-1>最後 へ</font></center>");
		pnlOrderChange.add(btnLastOrder);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param rInputPnl
	 */
	private void initRightBody(BasePanel rInputPnl) {

		int xPos = 0;
		int yPos = 9;
		BaseScrollPane myMenuTablePnl = new BaseScrollPane();
		myMenuTablePnl.setLocation(xPos, yPos);
		myMenuTablePnl.setSize(rInputPnl.getWidth() - 1, rInputPnl.getHeight() - yPos);

		m_rdata = new MenuExeTableData(true);
		m_rtable = new BTable();

		initTable(m_rtable, m_rdata);

		m_rtable.setSize(new Dimension(myMenuTablePnl.getWidth() - 2, myMenuTablePnl.getHeight()));
		m_rtable.setLocation(1, 0);
		myMenuTablePnl.getViewport().add(m_rtable);
		rInputPnl.add(myMenuTablePnl);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void initTable(BTable table, BTableModelData dataModel) {

		table.setModel(dataModel);

		ColumnData mCols[] = dataModel.getColumnData();

		for (int k = 0; k < mCols.length; k++) {
			GenericCellRenderer renderer = new GenericCellRenderer();
			renderer.setHorizontalAlignment(mCols[k].m_alignment);
			TableColumn column = new TableColumn(k, mCols[k].m_width, renderer, null);
			table.addColumn(column);
		}
		table.setShowGrid(false);
		table.getTableHeader().addMouseListener(dataModel.setMouseListener(table));
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), FaceContants.TABLE_HEADER_HEIGHT));
		table.setLocation(1, 0);
	}

	@Override
	protected BasePanel getFooter() {
		// パネルの生成
		footerMainPnl = new BasePanel();
		footerMainPnl.setLayout(null);
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH, Y_FOOTER_LENGTH));
		int xPos = 1;
		int yPos = 1;
		final int I_LBL_HEIGHT = 22;

		BasePanel statusBar = new BasePanel();
		statusBar.setSize(new Dimension(X_FOOTER_LENGTH - 4, Y_FOOTER_LENGTH - 4));
		statusBar.setLocation(2, 3);
		statusBar.setLayout(null);
		statusBar.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor.setSize(new Dimension(X_FOOTER_LENGTH - 359, I_LBL_HEIGHT));
		lblHelpInfor.setLocation(xPos, yPos);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);

		xPos = X_FOOTER_LENGTH - 359 + 2;
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

		xPos = xPos + 160 + 2;
		BaseLabel lblUser = new BaseLabel();
		lblUser.setSize(new Dimension(85, I_LBL_HEIGHT));
		lblUser.setLocation(xPos, yPos);
		lblUser.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblUser.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblUser.setBorder(FaceContants.LABEL_BORDER);
		lblUser.setText(getUserName());
		statusBar.add(lblUser);

		xPos = xPos + 85 + 2;
		BaseLabel lblPc = new BaseLabel();
		lblPc.setSize(new Dimension(90, I_LBL_HEIGHT));
		lblPc.setLocation(xPos, yPos);
		lblPc.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblPc.setText(getPCName());
		lblPc.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblPc.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblPc);

		xPos = xPos + 90 + 2;
		BaseLabel lblSpace = new BaseLabel();
		lblSpace.setSize(new Dimension(10, I_LBL_HEIGHT));
		lblSpace.setLocation(xPos, yPos);
		lblSpace.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblSpace.setText("");
		lblSpace.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblSpace.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblSpace);

		footerMainPnl.add(statusBar);

		return footerMainPnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	protected String getHelpInfor() {
		return "";
	}

	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected BasePanel getHeader() {
		// パネルの生成
		headerMainPnl = new BasePanel();
		headerMainPnl.setLayout(null);
		headerMainPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));

		int xPos = 8;
		int yPos = 5;

		EditLabel lblLoginUser = new EditLabel("ログインユーザー");
		lblLoginUser.setSize(140, FaceContants.LABLE_HEIGHT);
		lblLoginUser.setLocation(xPos, yPos);
		headerMainPnl.add(lblLoginUser);

		xPos += lblLoginUser.getWidth();
		InspectRadioButton rdTantousha = new InspectRadioButton("担当者");
		rdTantousha.setSize(70, FaceContants.LABLE_HEIGHT);
		rdTantousha.setLocation(xPos, yPos);
		rdTantousha.setEnabled(false);
		headerMainPnl.add(rdTantousha);

		xPos += rdTantousha.getWidth();
		InspectRadioButton rdUserGrp = new InspectRadioButton("ユーザーグループ");
		rdUserGrp.setSize(140, FaceContants.LABLE_HEIGHT);
		rdUserGrp.setLocation(xPos, yPos);
		rdUserGrp.setEnabled(false);
		headerMainPnl.add(rdUserGrp);

		if (ApplicationConstants.getLoginUser().isGroup()) {
			rdUserGrp.setSelected(true);
		} else {
			rdTantousha.setSelected(true);
		}

		ButtonGroup btnGrpUser = new ButtonGroup();
		btnGrpUser.add(rdTantousha);
		btnGrpUser.add(rdUserGrp);

		xPos += rdUserGrp.getWidth();
		BaseLabel lblUserGrp = new BaseLabel("");
		lblUserGrp.setSize(120, FaceContants.LABLE_HEIGHT);
		lblUserGrp.setLocation(xPos, yPos);
		lblUserGrp.setBorder(FaceContants.LABEL_BORDER);
		lblUserGrp.setText(ApplicationConstants.getLoginUser().getUserUser());
		headerMainPnl.add(lblUserGrp);

		xPos = 8;
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblUserName = new EditLabel("ユーザー名");
		lblUserName.setSize(140, FaceContants.LABLE_HEIGHT);
		lblUserName.setLocation(xPos, yPos);
		headerMainPnl.add(lblUserName);

		xPos += lblUserName.getWidth();
		BaseInputText txtLoginUser = new BaseInputText();
		txtLoginUser.setSize(80, FaceContants.LABLE_HEIGHT);
		txtLoginUser.setLocation(xPos, yPos);
		txtLoginUser.setBorder(FaceContants.LABEL_BORDER);
		txtLoginUser.setText(ApplicationConstants.getLoginUser().getUserId());
		txtLoginUser.setEditable(false);
		headerMainPnl.add(txtLoginUser);

		xPos += txtLoginUser.getWidth();
		BaseLabel lblUserNameInfor = new BaseLabel("");
		lblUserNameInfor.setSize(250, FaceContants.LABLE_HEIGHT);
		lblUserNameInfor.setLocation(xPos, yPos);
		lblUserNameInfor.setBorder(FaceContants.LABEL_BORDER);
		lblUserNameInfor.setText(ApplicationConstants.getLoginUser().getUserName());
		headerMainPnl.add(lblUserNameInfor);

		return headerMainPnl;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}

	protected void setFrameSize() {
		menuService = new MenuServiceImpl();
		X_FRAME_LENGTH = 770;
		Y_FRAME_LENGTH = 550;
		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH - 90;
		X_HEADER_LENGTH = X_FRAME_LENGTH;
		Y_HEADER_LENGTH = 60;
		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 30;
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
		int iRow = m_rtable.getSelectedRow();
		m_rdata.movePos(iRow, 0);
		if (m_rtable.getRowCount() > 0) {
			m_rtable.setRowSelectionInterval(0, 0);
		}
		ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
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
		int iRow = m_rtable.getSelectedRow();
		m_rdata.movePos(iRow, iRow - 1);
		if ((iRow - 1) >= 0) {
			m_rtable.setRowSelectionInterval(iRow - 1, iRow - 1);
		}
		ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
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
		int iRow = m_rtable.getSelectedRow();
		m_rdata.movePos(iRow, iRow + 2);
		if ((iRow + 1) < m_rtable.getRowCount()) {
			m_rtable.setRowSelectionInterval(iRow + 1, iRow + 1);
		}
		ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
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
		int iRow = m_rtable.getSelectedRow();
		m_rdata.movePos(iRow, m_rdata.getRowCount());
		if (m_rdata.getRowCount() > 0) {
			m_rtable.setRowSelectionInterval(m_rdata.getRowCount() - 1, m_rdata.getRowCount() - 1);
		}
		ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
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
		if (m_rdata.getRowCount() <= NumberUtils.getIntFromString(MCtlConstants.getValue("SYSTEM" + "MAX_MENU"))) {
			if (rdKinou.isSelected()) {
				int row = m_ltable.getSelectedRow();
				if (row >= 0) {
					MenuExeVo mnExeVo = (MenuExeVo) m_ldata.getValueAtRow(row);
					m_rdata.addKomoku(mnExeVo);
				}
			} else {
				MenuExeVo mneVo = getFileMenuExeVo();
				if (mneVo != null) {
					m_rdata.addKomoku(mneVo);
				}
			}
			ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
		} else {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0075"));
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	private MenuExeVo getFileMenuExeVo() {

		boolean isValid = true;
		JComponent comp = null;

		if (!StringUtils.isValid(txtFile.getText())) {
			isValid = false;
			comp = txtFile;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
		}

		if (isValid && !StringUtils.checkLenString(txtFile.getText(), 42)) {
			isValid = false;
			comp = txtFile;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !StringUtils.isValid(txtFileMei.getText())) {
			isValid = false;
			comp = txtFileMei;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
		}

		if (isValid && !StringUtils.checkLenString(txtFileMei.getText(), 200)) {
			isValid = false;
			comp = txtFileMei;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid && !StringUtils.checkLenString(txtFileMei.getText() + " " + txtParameter.getText(), 200)) {
			isValid = false;
			comp = txtParameter;
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
		}

		if (isValid) {
			MenuExeVo menuExeVo = new MenuExeVo();
			menuExeVo.setUserId(ApplicationConstants.getLoginUser().getUserId());
			menuExeVo.setMenugrpCode("000");
			menuExeVo.setMenuexeCode("000");
			menuExeVo.setMenuexeName(txtFile.getText());
			menuExeVo.setMenuexeHelp(txtFile.getText());
			menuExeVo.setClassName(txtFileMei.getText());
			menuExeVo.setPathName(txtFileMei.getText() + " " + txtParameter.getText());
			menuExeVo.setMenuexeSeq("0");
			menuExeVo.setFunType("1");
			txtFile.setText("");
			txtFileMei.setText("");
			txtParameter.setText("");
			return menuExeVo;
		} else {
			if (comp != null) {
				setFocusComponent(comp);
			}
			return null;
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
		int row = m_rtable.getSelectedRow();
		if (row >= 0) {
			m_rdata.removeKomoku(row);
		}
		ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
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
		if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0003")) == MessageBox.YES) {
			m_rdata.removeAll();
			ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
		}
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
		if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0002")) == MessageBox.YES) {
			try {
				m_rdata.setMLstData(menuService.createMyMenu(getLoginUser(), m_rdata.getMLstData()));
				ApplicationUtils.doRefreshTableWithoutDatabase(m_rtable, m_rdata);
				MessageBox.message(this, MessageConstants.getMstMsgVo("I0002"));
				isHenkouChuu = false;
			} catch (BizException e) {
				logger.error(e.getMessage());
				MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			}
		}
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
		if (isHenkouChuu) {
			if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
				ApplicationUtils.doRefreshTable(m_rtable, m_rdata);
				rdKinou.setSelected(true);
				isHenkouChuu = false;
			}
		}
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

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF12() {
		if (isHenkouChuu) {
			if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
				dispose();
			}
		} else {
			dispose();
		}
		stopTimerRemoveListener(this);
	}

	/**
	 * @author PC13
	 * 
	 */
	class MenuGrpCombobox extends BaseComboBox {

		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public MenuGrpCombobox(ComboBoxModel aModel) {
			super(aModel);
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
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComboBox#setSelectedIndex(int)
		 */
		public void setSelectedIndex(int anIndex) {
			super.setSelectedIndex(anIndex);
			ApplicationUtils.doRefreshTable(m_ltable, m_ldata);
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public void resetValue() {
			setSelectedIndex(0);
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @return
		 */
		public Object getSelectObjValue() {
			int iValue = getSelectedIndex();
			if (dataModel instanceof ArrayListComboBoxModel) {
				ArrayListComboBoxModel aDataModel = (ArrayListComboBoxModel) dataModel;
				return aDataModel.getObjAt(iValue);
			} else {
				return null;
			}
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class MenuExeTableData extends BTableModelData {

		/** */
		private static final long serialVersionUID = 1L;

		/** */
		private boolean isMyMenuData = true;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public MenuExeTableData() {
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
		 * @param myMenu
		 */
		public MenuExeTableData(boolean myMenu) {
			isMyMenuData = myMenu;
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
			mLstData = new ArrayList<MenuExeVo>();
			setDefaultData();
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public void setDefaultData() {
			m_columns = new ColumnData[] { new ColumnData("機 能 名", RBODY_PANEL_WIDTH, JLabel.LEFT, false) };
			retrieveData();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int nRow, int nCol) {

			if (nRow < 0 || nRow >= getRowCount())
				return "";
			MenuExeVo row = (MenuExeVo) mLstData.get(nRow);

			switch (nCol) {
			case 0:
				return row.getMenuexeName();
			}

			return "";
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param mnExeVo
		 */
		public void addKomoku(MenuExeVo mnExeVo) {
			int iPos = getPosition(mnExeVo);
			if (iPos >= 0) {
				mLstData.add(iPos, mnExeVo);
				isHenkouChuu = true;
			}
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD>削除</DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param mnExeVo
		 */
		public void removeKomoku(int iPos) {
			if (iPos >= 0 && iPos < mLstData.size()) {
				mLstData.remove(iPos);
				isHenkouChuu = true;
			}
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD>全削除</DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public void removeAll() {
			mLstData.clear();
			isHenkouChuu = true;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param mnExeVo
		 * @return
		 */
		private boolean isExist(MenuExeVo mnExeVo) {

			boolean bReturn = false;

			for (Object obj : mLstData) {
				MenuExeVo mnExe = (MenuExeVo) obj;

				if (mnExeVo.isThesameMenuExe(mnExe)) {
					bReturn = true;
					break;
				}
			}

			return bReturn;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param mnExeVo
		 * @return
		 */
		private int getPosition(MenuExeVo mnExeVo) {

			int iReturn = -1;

			if ("000".equalsIgnoreCase(mnExeVo.getMenugrpCode()) && "000".equalsIgnoreCase(mnExeVo.getMenuexeCode())) {
				iReturn = mLstData.size();
			} else {
				if (!isExist(mnExeVo)) {
					if (mLstData.size() > 0) {
						iReturn = mLstData.size();
						for (int i = 0; i < mLstData.size(); i++) {
							MenuExeVo mnExe = (MenuExeVo) mLstData.get(i);
							if (mnExeVo.sequenceCompare(mnExe) >= 0) {
								iReturn = i;
								break;
							}
						}
					} else {
						iReturn = 0;
					}
				}
			}

			return iReturn;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param iCurrPos
		 * @param iNewPos
		 */
		private void movePos(int iCurrPos, int iNewPos) {

			if (iCurrPos != iNewPos) {
				if (iCurrPos >= 0 && iCurrPos <= mLstData.size()) {
					if (iNewPos >= 0 && iNewPos <= mLstData.size()) {
						Object objCurr = mLstData.get(iCurrPos);
						mLstData.add(iNewPos, objCurr);
						if (iCurrPos < iNewPos) {
							mLstData.remove(iCurrPos);
						} else {
							mLstData.remove(iCurrPos + 1);
						}
					}
				}
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.pipe.jface.table.BTableModelData#retrieveData()
		 */
		public int retrieveData() {

			try {

				List<MenuExeVo> lstData;
				ComboObjectVo comboObj = (ComboObjectVo) comboMnGrp.getSelectObjValue();

				if (isMyMenuData) {
					lstData = menuService.getLstMyMenuExe(ApplicationConstants.getLoginUser());
				} else {
					if (comboObj == null) {
						lstData = menuService.getLstMenuExeVoWithPermis("000", ApplicationConstants.getLoginUser());
					} else {
						lstData = menuService.getLstMenuExeVoWithPermis(StringUtils.trimAll(comboObj.getCode()), ApplicationConstants.getLoginUser());
					}
				}

				mLstData.clear();

				if (lstData != null) {
					mLstData = lstData;
					return 1;
				} else {
					return 0;
				}
			} catch (BizException e) {
				e.printStackTrace();
				return -1;
			}
		}

		@Override
		protected void doRefreshTable() {
			if (isMyMenuData) {
				ApplicationUtils.doRefreshTable(m_rtable, m_rdata);
			} else {
				ApplicationUtils.doRefreshTable(m_ltable, m_ldata);
			}
		}
	}

	/**
	 * @author PC13
	 * 
	 */
	class DialogFileAction implements com.fas.jface.button.Action {

		/** */
		BaseTextArea txtInputText;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param txtInput
		 */
		public DialogFileAction(BaseTextArea txtInput) {
			txtInputText = txtInput;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public void execute() {
			String[] fileName = new String[] {};
			JFileChooser fileChoser = new JFileChooser();
			fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName, ""));
			fileChoser.setCurrentDirectory(FileUtils.getFileObj(""));
			int rVal = fileChoser.showSaveDialog(MyMenuFrame.this);

			if (rVal == JFileChooser.APPROVE_OPTION) {
				txtInputText.setText(fileChoser.getCurrentDirectory().toString() + ApplicationConstants.FILE_SEPERATE + fileChoser.getSelectedFile().getName());
			}
		}
	}

	/**
	 * 画面TABキー移動設定
	 */
	protected void setDispTabFoucus() {

		List<Object> focusList = new ArrayList<Object>();

		if (rdKinou.isSelected()) {
			// フォーカスの設定
			focusList.add(rdKinou);
			focusList.add(comboMnGrp);
			focusList.add(m_ltable);
			focusList.add(rdFile);
			focusList.add(btnAdd);
			focusList.add(btnDel);
			focusList.add(btnDelAll);
			focusList.add(btnFirstOrder);
			focusList.add(btnUpOrder);
			focusList.add(btnDownOrder);
			focusList.add(btnLastOrder);
			focusList.add(m_rtable);
		} else {
			// フォーカスの設定
			focusList.add(rdKinou);
			focusList.add(rdFile);
			focusList.add(txtFile);
			focusList.add(txtFileMei);
			focusList.add(txtParameter);
			focusList.add(btnAdd);
			focusList.add(btnDel);
			focusList.add(btnDelAll);
			focusList.add(btnFirstOrder);
			focusList.add(btnUpOrder);
			focusList.add(btnDownOrder);
			focusList.add(btnLastOrder);
			focusList.add(m_rtable);
		}
		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}

	public void stopTimerRemoveListener(JDialog f) {
		if (timer != null && timer.isRunning()) {
			timer.stop();
			timer = null;
		}
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		if (pro != null) {
			focusManager.removePropertyChangeListener(pro);
		}
		f = null;
	}
}
