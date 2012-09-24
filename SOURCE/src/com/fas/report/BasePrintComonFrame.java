/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：BasePrintComonFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/02/15
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.report;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.ReportConstants;
import com.fas.common.constants.dbtable.FPrinterConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.button.ActionButton;
import com.fas.jface.button.ExitMasterButton;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.MasterFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.table.InspectTable;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.IntegerNumberText;
import com.fas.jface.text.PasswordInputText;
import com.fas.jface.text.TimeInputText;
import com.fas.vo.menuexe.MenuExeVo;

/**
 * @author PC14
 * 
 */
public abstract class BasePrintComonFrame extends MasterFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(BasePrintComonFrame.class);
	
	/** */
	protected ExitMasterButton btnF12;
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
	protected ActionButton btnPre;
	/** */
	protected ActionButton btnNext;
	/** */
	protected ActionButton btnLast;
	/** */
	protected ActionButton btnFirst;
	/** */
	protected IntegerNumberText txtNumPage;
	/** */
	protected InspectRadioButton rdoPreview;
	/** */
	protected InspectRadioButton rdoPrint;
	/** */
	protected String CUR_MODE = "";	
	/** */
	protected BasePanel prtPnl;
	/** */
	protected boolean isRequireResetTabDispFocus = true;
	private Timer timer;
	private PropertyChangeListener pro;

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

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public BasePrintComonFrame(BaseFrame frame, String title) {
		super(title);
		parentFrame = frame;
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
	public BasePrintComonFrame(BaseFrame frame) {
		super(frame);
		parentFrame = frame;
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
	public BasePrintComonFrame() {
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
		// set border
		pnlLeft.setBorder(FaceContants.PANEL_BORDER);
		pnlCenter.setBorder(FaceContants.PANEL_BORDER);
		pnlRight.setBorder(FaceContants.PANEL_BORDER);
		//headerPnl.setBorder(FaceContants.PANEL_BORDER);
		//footerPnl.setBorder(FaceContants.PANEL_BORDER);
		getFooter();
		setTitle(getSubName());
		CUR_MODE = "";
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		ExitMasterWindow exitWin = new ExitMasterWindow(btnF12);
		addWindowListener(exitWin);

//		MouseAdapter mouseAdapter = new MouseAdapter() {
//			public void mouseEntered(MouseEvent e) {
//				getRootPane().setCursor(
//						Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//
//			public void mouseExited(MouseEvent e) {
//				getRootPane().setCursor(
//						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//			}
//		};

//		setMouseCurrsor(btnF2, mouseAdapter);
//		setMouseCurrsor(btnF3, mouseAdapter);
//		setMouseCurrsor(btnF8, mouseAdapter);
//		setMouseCurrsor(btnF11, mouseAdapter);
//		setMouseCurrsor(btnF12, mouseAdapter);
//		setMouseCurrsor(btnFirst, mouseAdapter);
//		setMouseCurrsor(btnPre, mouseAdapter);
//		setMouseCurrsor(btnNext, mouseAdapter);
//		setMouseCurrsor(btnLast, mouseAdapter);
		// set ToolTip for footer　
		pro = new PropertyChangeListener() {
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
							|| ((e.getNewValue()) instanceof InspectTable)
							|| ((e.getNewValue()) instanceof PasswordInputText)) {
						JComponent comp = (JComponent) e.getNewValue();
						String name = comp.getToolTipText();
						if (name != null) {
							setHelpInfor(name);
						}
					}
				}
			}
		};
		KeyboardFocusManager focusManager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(pro);
		
		isRequireResetTabDispFocus = true;
	}

//	/**
//	 * <DL>
//	 * <DT>メソッド記述：</DT>
//	 * <DD></DD>
//	 * <BR>
//	 * 
//	 * </DL>
//	 * 
//	 * @param btn
//	 * @param mouseAd
//	 */
//	private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
//		if (btn != null) {
//			btn.addMouseListener(mouseAd);
//		}
//	}

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
	protected abstract String getSubName();

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
	public String getSysName() {
		return getSubName();
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
	protected String getPCName() {
		if (ApplicationConstants.loginUser != null) {
			return ApplicationConstants.loginUser.getLoginPC();
		} else {
			return "";
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
	protected String getUserName() {
		if (ApplicationConstants.loginUser != null) {
			return ApplicationConstants.loginUser.getUserId();
		} else {
			return "";
		}
	}

	/**
	 * 
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>build layout for middle area (containing left and center panel)</DD>
	 * <BR>
	 * </DL>
	 */
	protected void buildMiddle() {
		BasePanel middle = new BasePanel();
		GroupLayout layout = new GroupLayout(middle);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(5)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
					.addComponent(pnlLeft))
				.addGap(7)
				.addComponent(pnlCenter)
				.addGap(5));
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(pnlLeft)
					.addComponent(pnlCenter)
				);

		middle.setLayout(layout);
		mainPanel.add(middle, BorderLayout.CENTER);
	}

	@Override
	protected BasePanel getFooter() {
		// パネルの生成
		int xPos = 5;
		int yPos = 4;
		final int iwidth = 350;
		final int iheight = 55;
		
		BasePanel btnPnl = new BasePanel();
		btnPnl.setLocation(xPos, yPos);

		boolean enbaleB[] = enableBtn();
		int X_BTN_WIDTH = 71; 
		int Y_BTN_HEIGHT = 36;
		int X_BTN_SPACE = 4;
		final int BTN_PANEL_WIDTH = X_BODY_LENGTH - xPos;

		// 左
		BasePanel pnlLeft = new BasePanel();
		BasePanel pnlRight = new BasePanel();
		//pnlRight.setBorder(new DropShadowBorder());
		//pnlLeft.setBorder(new DropShadowBorder());
		
		pnlLeft.setBorder(new TitledBorder("出力方法"));
		pnlLeft.setLocation(xPos, yPos);
		pnlLeft.setSize(iwidth + 120, iheight);
		ButtonGroup groupTemp = new ButtonGroup();
		rdoPreview = new InspectRadioButton(NameConstants.getName("PRT_PROC"+ "2"));
		rdoPreview.setLocation(120, 20);
		rdoPreview.setSize(100,22);
		rdoPreview.setToolTipText( NameConstants.getPName("PRT_PROC"+ "2") + "を選択して下さい。");
		groupTemp.add(rdoPreview);
		pnlLeft.add(rdoPreview);
		
		rdoPrint = new InspectRadioButton(NameConstants.getName("PRT_PROC"+ "3"));
		rdoPrint.setLocation(260, 20);
		rdoPrint.setSize(100,22);
		rdoPrint.setToolTipText( NameConstants.getPName("PRT_PROC"+ "3")+"を選択して下さい。");
		groupTemp.add(rdoPrint);
		pnlLeft.add(rdoPrint);
		btnPnl.add(pnlLeft);
		
		// 右 chkPreview,chkPrint
		xPos+= iwidth + 126;
		pnlRight.setBorder(new TitledBorder("出力部数"));
		pnlRight.setLocation(xPos, yPos);
		pnlRight.setSize(307, iheight);
		txtNumPage = new IntegerNumberText();
		txtNumPage.setLocation(100, 20);
		txtNumPage.setText(MCtlConstants.getValueByCKey("COPY_NUM").trim());
		txtNumPage.setMaxLength(3);
		txtNumPage.setSize(100,22);
		txtNumPage.setToolTipText( "出力部数を入力して下さい。");
		pnlRight.add(txtNumPage);
		
		JLabel lblNumPage =  new JLabel("部");
		lblNumPage.setSize(20,22);
		lblNumPage.setLocation(200 + xPos, 20);
		pnlRight.add(lblNumPage);
		btnPnl.add(pnlRight);
		
		xPos = 5;
		yPos += iheight + 10;
		
		if (enbaleB[1]) {
			F2Action f2Action = new F2Action("f2Action");
			btnF2 = new ActionButton();
			btnF2.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF2.setText("<html><center><font size=-1>全選択</font></center><center><font size=-1>F2(F)</font></center>");
			btnF2.addActionListener(f2Action);
			btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F2"),
					f2Action.getValue(Action.NAME));
			btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("alt F"),
					f2Action.getValue(Action.NAME));
			btnF2.getActionMap().put("f2Action", f2Action);
			btnF2.setToolTipText("[F2]：全選択します。");
			btnF2.setFocusable(false);
			btnPnl.add(btnF2);
		}
		
		if (enbaleB[2]) {
			F3Action f3Action = new F3Action("f3Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF3 = new ActionButton();
			btnF3.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF3.setText("<html><center><font size=-1>全解除</font></center><center><font size=-1>F3(G)</font></center>");
			btnF3.addActionListener(f3Action);
			btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F3"),
					f3Action.getValue(Action.NAME));
			btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("alt G"),
					f3Action.getValue(Action.NAME));
			btnF3.getActionMap().put("f3Action", f3Action);
			btnF3.setToolTipText("[F3]：全解除します。");
			btnF3.setFocusable(false);
			btnPnl.add(btnF3);
		}

		if (enbaleB[3]) {
			F1Action f1Action = new F1Action("f1Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF1 = new ActionButton();
			btnF1.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF1.setText("<html><center><font size=-1></font></center><center><font size=-1>F3(G)</font></center>");
//			btnF3.addActionListener(f3Action);
//			btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("F3"),
//					f3Action.getValue(Action.NAME));
//			btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("alt G"),
//					f3Action.getValue(Action.NAME));
//			btnF3.getActionMap().put("f3Action", f3Action);
//			btnF3.setFocusable(false);
			btnF1.setEnabled(false);
			btnPnl.add(btnF1);
		}

		if (enbaleB[4]) {
			F4Action f4Action = new F4Action("f4Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF4 = new ActionButton();
			btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF4.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
//			btnF4.addActionListener(f4Action);
//			btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("F4"),
//					f4Action.getValue(Action.NAME));
//			btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("alt S"),
//					f4Action.getValue(Action.NAME));
//			btnF4.getActionMap().put("f4Action", f4Action);
//			btnF4.setFocusable(false);
//			btnF4.setToolTipText("[F4]：検索画面を表示します。");
//			btnF4.addFocusListener(new FocusListener() {
//
//				public void focusGained(FocusEvent e) {
//					btnF4.setSearchEnable(true);
//					btnF4.setEnabled(true);
//				}
//
//				public void focusLost(FocusEvent e) {
//					btnF4.setEnabled(false);
//				}
//			});
			btnF4.setEnabled(false);
			btnPnl.add(btnF4);
		}

		if (enbaleB[5]) {
			F5Action f5Action = new F5Action("f5Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF5 = new ActionButton();
			btnF5.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF5.setText("<html><center><font size=-1>分類名</font></center><center><font size=-1>F5(T)</font></center>");
			btnF5.addActionListener(f5Action);
			btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F5"),
					f5Action.getValue(Action.NAME));
			btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("alt T"),
					f5Action.getValue(Action.NAME));
			btnF5.getActionMap().put("f5Action", f5Action);
			btnF5.setFocusable(false);
			btnPnl.add(btnF5);
		}

		if (enbaleB[6]) {
			F6Action f6Action = new F6Action("f6Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF6 = new ActionButton();
			btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF6.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
//			btnF6.addActionListener(f6Action);
//			btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("F6"),
//					f6Action.getValue(Action.NAME));
//			btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("alt D"),
//					f6Action.getValue(Action.NAME));
//			btnF6.getActionMap().put("f6Action", f6Action);
//			btnF6.setToolTipText("[F6]：選択データを削除します。");
//			btnF6.setFocusable(false);
			btnF6.setEnabled(false);
			btnPnl.add(btnF6);
		}

		if (enbaleB[7]) {
			F7Action f7Action = new F7Action("f7Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF7 = new ActionButton();
			btnF7.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF7.setText("<html><center><font size=-1>   </font></center><center><font size=-1>F7(X)</font></center>");
			btnF7.addActionListener(f7Action);
			btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F7"),
					f7Action.getValue(Action.NAME));
			btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("alt X"),
					f7Action.getValue(Action.NAME));
			btnF7.getActionMap().put("f7Action", f7Action);
			btnF7.setFocusable(false);
			btnPnl.add(btnF7);
		}

		if (enbaleB[8]) {
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			F8Action f8Action = new F8Action("f8Action");
			btnF8 = new ActionButton();
			btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF8.setText("<html><center><font size=-1>実　行</font></center><center><font size=-1>F8(A)</font></center>");
			btnF8.addActionListener(f8Action);
			btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F8"),
					f8Action.getValue(Action.NAME));
			btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("alt A"),
					f8Action.getValue(Action.NAME));
			btnF8.getActionMap().put("f8Action", f8Action);
			btnF8.setToolTipText("[F8]：実行します。");
			btnF8.setFocusable(false);
			btnPnl.add(btnF8);
		}

		if (enbaleB[9]) {
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			F9Action f9Action = new F9Action("f9Action");
			btnF9 = new ActionButton();
			btnF9.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
							Y_BTN_HEIGHT));
			btnF9.setText("<html><center><font size=-1></font></center><center><font size=-1></font></center>");
//			btnF9.addActionListener(f9Action);
//			btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("F9"),
//					f9Action.getValue(Action.NAME));
//			btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
//					KeyStroke.getKeyStroke("alt R"),
//					f9Action.getValue(Action.NAME));
//			btnF9.getActionMap().put("f9Action", f9Action);
//			btnF9.setFocusable(false);
			btnF9.setEnabled(false);
			btnPnl.add(btnF9);
		}

		if (enbaleB[10]) {
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			F10Action f10Action = new F10Action("f10Action");
			btnF10 = new ActionButton();
			btnF10.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH,
					Y_BTN_HEIGHT));
			btnF10.setText("<html><center><font size=-1>データ表示</font></center><center><font size=-1>F10(V)</font></center>");
			btnF10.addActionListener(f10Action);
			btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("F10"),
					f10Action.getValue(Action.NAME));
			btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke("alt V"),
					f10Action.getValue(Action.NAME));
			btnF10.getActionMap().put("f10Action", f10Action);
			btnF10.setToolTipText("[F10]：一覧の表示切替をします。");
			btnF10.setFocusable(false);
			btnPnl.add(btnF10);
		}

		if (enbaleB[11]) {
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
			btnPnl.add(btnF11);
		}

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF12 = new ExitMasterButton();
		btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.setToolTipText("[F12]：マスタメンテ処理を終了します。");
		btnF12.setConfirmRerquired(false);
		btnF12.setFrame(getFrame());
		btnF12.setParentFrame(getParentFrame());
		btnF12.setFocusable(false);
		btnPnl.add(btnF12);

		xPos += X_BTN_SPACE + X_BTN_WIDTH + 10;
		X_BTN_WIDTH = 20;

		btnFirst = new ActionButton();
		btnFirst.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnFirst.setText("<html><center><font size=-1>≪</font>");
		btnFirst.setToolTipText("項目を選択して下さい。");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doFirst();
			}
		});
		btnFirst.setFocusable(false);
		btnPnl.add(btnFirst);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnPre = new ActionButton();
		btnPre.setFocusable(false);
		btnPre.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnPre.setText("<html><center><font size=-1>＜</font>");
		btnPre.setToolTipText("項目を選択して下さい。");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPre();
			}
		});
		btnPnl.add(btnPre);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnNext = new ActionButton();
		btnNext.setFocusable(false);
		btnNext.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnNext.setText("<html><center><font size=-1>＞</font>");
		btnNext.setToolTipText("項目を選択して下さい。");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doNext();
			}
		});
		btnPnl.add(btnNext);

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnLast = new ActionButton();
		btnLast.setFocusable(false);
		btnLast.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
		btnLast.setText("<html><center><font size=-1>≫</font>");
		btnLast.setToolTipText("項目を選択して下さい。");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLast();
			}
		});
		btnPnl.add(btnLast);
		btnPnl.setSize(new Dimension(X_BODY_LENGTH - X_BTN_SPACE,
				BTN_PANEL_HEIGHT));

		// パネルの生成(systems information)
		xPos = 7;
		yPos = yPos + BTN_PANEL_HEIGHT;
		final int I_LBL_HEIGHT = 22;

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor
				.setSize(new Dimension(X_FOOTER_LENGTH - 359, I_LBL_HEIGHT));
		lblHelpInfor.setPreferredSize(new Dimension(X_FOOTER_LENGTH - 359,
				I_LBL_HEIGHT));
		lblHelpInfor.setLocation(xPos, yPos);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);

		xPos = X_FOOTER_LENGTH - 359 + 2;
		final BaseLabel lblDate = new BaseLabel();
		lblDate.setSize(new Dimension(160, I_LBL_HEIGHT));
		lblDate.setMinimumSize(new Dimension(160, I_LBL_HEIGHT));
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

		xPos = xPos + 160 + 2;
		BaseLabel lblUser = new BaseLabel();
		lblUser.setSize(new Dimension(85, I_LBL_HEIGHT));
		lblUser.setMinimumSize(new Dimension(85, I_LBL_HEIGHT));
		lblUser.setLocation(xPos, yPos);
		lblUser.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblUser.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblUser.setBorder(FaceContants.LABEL_BORDER);
		lblUser.setText(getUserName());

		xPos = xPos + 85 + 2;
		BaseLabel lblPc = new BaseLabel();
		lblPc.setSize(new Dimension(90, I_LBL_HEIGHT));
		lblPc.setMinimumSize(new Dimension(90, I_LBL_HEIGHT));
		lblPc.setLocation(xPos, yPos);
		lblPc.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblPc.setText(getPCName());
		lblPc.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblPc.setBorder(FaceContants.LABEL_BORDER);

		xPos = xPos + 90 + 2;
		BaseLabel lblSpace = new BaseLabel();
		lblSpace.setSize(new Dimension(10, I_LBL_HEIGHT));
		lblSpace.setLocation(xPos, yPos);
		lblSpace.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblSpace.setText("a");
		lblSpace.setForeground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblSpace.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblSpace.setBorder(FaceContants.LABEL_BORDER);

		// footerPnl.setLayout(new GridBagLayout());
		// footerPnl.add(btnPnl, createBothConstraints(0, 0, 5, 1, 1, 1));
		// footerPnl.add(lblHelpInfor, createHorzConstraints(0, 1, 1, 1, 1));
		// footerPnl.add(lblDate, createHorzConstraints(1, 1, 1, 1, 1));
		// footerPnl.add(lblUser, createHorzConstraints(2, 1, 1, 1, 1));
		// footerPnl.add(lblPc, createHorzConstraints(3, 1, 1, 1, 1));
		// footerPnl.add(lblSpace, createHorzConstraints(4, 1, 1, 1, 0.1));
		footerPnl.setPreferredSize(new Dimension(BTN_PANEL_WIDTH,
				BTN_PANEL_HEIGHT +2* Y_FOOTER_LENGTH + 35));
		GroupLayout footerLayout = new GroupLayout(footerPnl);
		footerPnl.setLayout(footerLayout);
		footerLayout
				.setHorizontalGroup(footerLayout
						.createSequentialGroup()
						.addGroup(
								footerLayout
										.createParallelGroup()
										.addComponent(btnPnl)
										.addGroup(
												footerLayout
														.createSequentialGroup()
														.addGroup(
																footerLayout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING,
																				true)
																		.addComponent(
																				lblHelpInfor,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addComponent(lblDate)
														.addComponent(lblUser)
														.addComponent(
																lblPc,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE)
														.addComponent(lblSpace))));
		
		footerLayout.setVerticalGroup(footerLayout.createSequentialGroup()
				.addComponent(btnPnl).addGroup(
						footerLayout.createParallelGroup().addComponent(
								lblHelpInfor).addComponent(lblDate)
								.addComponent(lblUser).addComponent(lblPc)
								.addComponent(lblSpace)));

		mainPanel.add(footerPnl, BorderLayout.PAGE_END);

		return footerPnl;
	}
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param com
	 */
	protected void setDefaultFirstFocus(final JComponent com) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (com.isEnabled()) {
					com.requestFocusInWindow();
				}
			}
		});
	}

	/**
	 * @return
	 */
	public boolean isChange() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
	 */
	protected void setFrameSize() {
		X_FRAME_LENGTH = 800;
		Y_FRAME_LENGTH = 600;
		this.setMinimumSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH - 70;
		X_HEADER_LENGTH = X_FRAME_LENGTH;
		Y_HEADER_LENGTH = 40;
		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 30;
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
	protected abstract String getHelpInfor();


	/**
	 * @author PC12
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
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doF1();

	/**
	 * @author PC12
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
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doF2();

	/**
	 * @author PC12
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
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doF3();

	/**
	 * @author PC12
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
			doF4();
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
	protected abstract void doF4();

	/**
	 * @author PC12
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
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doF5();

	/**
	 * @author PC12
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
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doF6();

	/**
	 * @author PC12
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
	protected abstract void doF7();

	/**
	 * @author PC12
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
	protected abstract void doF8();

	/**
	 * @author PC12
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
	protected abstract void doF9();

	/**
	 * @author PC12
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
	protected abstract void doF10();

	/**
	 * @author PC12
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
	protected abstract void doF11();

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
	protected abstract boolean[] enableBtn();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doFirst();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doPre();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doNext();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected abstract void doLast();

	/**
	 * @author PC12
	 * 
	 */
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
		 * @see
		 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent
		 * )
		 */
		public void windowClosing(WindowEvent e) {
			btnAction.doClick();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowAdapter#windowOpened(java.awt.event.WindowEvent)
		 */
		public void windowOpened(WindowEvent e) {
			if (parentFrame != null) {
				parentFrame.setFocusableWindowState(true);
			}
		}
	}

	/**
	 * @return
	 */
	protected class ExportExcel {
		public ExportExcel() {

		}

		public void DoExport(final ArrayList<ArrayList<String>> rs)
				throws SQLException, RowsExceededException, WriteException {
			String strPath = MOutCtlContants.getValue(ApplicationConstants
					.getLoginUser().getUserId(), MOutCtlContants.I_PATH1);
			String[] fileName = new String[] { "xls" };
			JFileChooser fileChoser = new JFileChooser();
			fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName,
					"ファイル (*.xls)"));
			if (StringUtils.isValid(strPath)) {
				fileChoser.setCurrentDirectory(FileUtils.getFileObj(strPath));
			} else {
				fileChoser.setCurrentDirectory(FileUtils.getFileObj(fileChoser
						.getFileSystemView().getDefaultDirectory()
						.getAbsolutePath()));
			}
			int rVal = fileChoser.showSaveDialog(getFrame());
			if (rVal == JFileChooser.APPROVE_OPTION) {
				final String strFilePath = fileChoser.getCurrentDirectory()
						.toString()
						+ "\\"
						+ fileChoser.getSelectedFile().getName().replaceAll(
								".xls", "") + ".xls";
				try {
					int iCount = rs.size();
					WritableWorkbook workbook = Workbook
							.createWorkbook(new File(strFilePath));
					WritableSheet s1 = workbook.createSheet("Sheet1", 0);
					for (int i = 0; i < iCount; i++) {
						ArrayList<String> arr = rs.get(i);
						for (int j = 0; j < arr.size(); j++) {
							try {
								Label l1 = new Label(j, i, arr.get(j));
								s1.addCell(l1);
							} catch (WriteException WriteEx) {
							}
						}
					}
					workbook.write();
					workbook.close();
				} catch (IOException IOex) {
				}
			}
		}
	}

	public static GridBagConstraints createFixedHorzConstraints(int gridx,
			int gridy, int gridwidth, int gridheight) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0,
				0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(1, 12, 1, 2), 0, 0);
	}

	public static GridBagConstraints createHorzConstraints(int gridx,
			int gridy, int gridwidth, int gridheight, double weightx) {

		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(1, 0, 1, 0), 0, 0);
	}

	public static GridBagConstraints createBothConstraints(int gridx,
			int gridy, int gridwidth, int gridheight, double weightx,
			double heightx) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, heightx, GridBagConstraints.LAST_LINE_START,
				GridBagConstraints.BOTH, new Insets(1, 0, 1, 0), 0, 0);
	}

	protected void invokeSetStatusLater() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				isRequireResetTabDispFocus = false;
				setStatus(CUR_MODE);
			}
		});
	}

	private void setStatus(String mode) {

	}


	/* (non-Javadoc)
	 * @see com.pipe.jface.gui.BaseFrame#setExeMenu(com.pipe.vo.menuexe.MenuExeVo)
	 */
	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		
		//default printer
		ReportConstants.DEFAULT_PRINTER = FPrinterConstants.getPrtidBykey(ApplicationConstants.LOGIN_USER_ID, ApplicationConstants.MENU_GRP, ApplicationConstants.MENU_EXE);
		
	}

	protected void initTableAction(final InspectTable m_table) {

		// m_table.setModel(m_data);
		//	  	
		// ColumnData mCols[] = m_data.getColumnData();
		//	  	
		// for (int k = 0; k < mCols.length; k++) {
		// GenericCellRenderer renderer = new GenericCellRenderer();
		// renderer.setHorizontalAlignment(mCols[k].m_alignment);
		// TableColumn column = new TableColumn(k, mCols[k].m_width, renderer,
		// null);
		// m_table.addColumn(column);
		// }

		m_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = m_table.getSelectedRow();
				if (e.getClickCount() < 2) {
					doSingleClick(row);
				} else {
					doDoubleClick(row);
				}
			}
		});

		AbstractAction keyUp = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// action to be performed
				int row = m_table.getSelectedRow();

				if (0 == row) {
					row = m_table.getRowCount() - 1;
				} else {
					row = row - 1;
				}

				if (row >= 0 && row < m_table.getRowCount()) {
					doSingleClick(row);
					m_table.setRowSelectionInterval(row, row);
					m_table.setColumnSelectionInterval(0, 2);
				}
			}
		};
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				"keyUp");
		m_table.getActionMap().put("keyUp", keyUp);

		AbstractAction keyDown = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// action to be performed
				int row = m_table.getSelectedRow();

				if (m_table.getRowCount() - 1 == row) {
					row = 0;
				} else {
					row = row + 1;
				}

				if (row >= 0 && row < m_table.getRowCount()) {
					doSingleClick(row);
					m_table.setRowSelectionInterval(row, row);
					m_table.setColumnSelectionInterval(0, 2);
				}
			}
		};
		m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				"keyDown");
		m_table.getActionMap().put("keyDown", keyDown);

		AbstractAction lastRow = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				doLast();
			}
		};
		m_table.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN,
						InputEvent.CTRL_DOWN_MASK), "lastRow");
		m_table.getActionMap().put("lastRow", lastRow);

		AbstractAction firstRow = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				doFirst();
			}
		};
		m_table.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP,
						InputEvent.CTRL_DOWN_MASK), "firstRow");
		m_table.getActionMap().put("firstRow", firstRow);

		// m_table.getTableHeader().addMouseListener(m_data.setMouseListener(m_table));
		// m_table.getTableHeader().setPreferredSize(new
		// Dimension(m_table.getTableHeader().getWidth(),
		// FaceContants.TABLE_HEADER_HEIGHT));
		// m_table.setLocation(1, 0);
	}

	protected abstract void doSingleClick(int row);

	protected abstract void doDoubleClick(int row);

	/**
	 * get sum height of title bar and bottom-border of frame
	 */
	protected int getHeightBorder() {
		JFrame frame = new JFrame();
		frame.pack();
		int height = frame.getInsets().top + frame.getInsets().bottom;
		frame.dispose();
		frame = null;
		return height;
	}
	
	protected Insets getDefaultInset() {
		JFrame frame = new JFrame();
		frame.pack();
		Insets inset = frame.getInsets();
		frame.dispose();
		frame = null;
		return inset;
	}

	protected void attachF8(InspectTable table, boolean transferF8) {
		AbstractAction f8Action = null;
		if (transferF8) {
			f8Action = new F8Action("f8Action");
		} else {
			f8Action = new AbstractAction("f8Action") {
				private static final long serialVersionUID = 7137790980079655431L;

				@Override
				public void actionPerformed(ActionEvent e) {
					// do nothing
				}
			};
		}
		table.getInputMap().put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		table.getActionMap().put("f8Action", f8Action);
	}

	protected void attachF2(InspectTable table) {
		F2Action f2Action = new F2Action("f2Action");
		table.getInputMap().put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		table.getActionMap().put("f2Action", f2Action);
	}
	
	protected void attachF3(InspectTable table) {
		F3Action f3Action = new F3Action("f3Action");
		table.getInputMap().put(KeyStroke.getKeyStroke("F3"), f3Action.getValue(Action.NAME));
		table.getActionMap().put("f3Action", f3Action);
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
