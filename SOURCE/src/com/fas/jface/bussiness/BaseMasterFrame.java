/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseReportFrame.java
*
*     記述				：
*     
*     作成日			：2009/12/07   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.bussiness;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.TableColumn;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.button.ActionButton;
import com.fas.jface.button.ExitMasterButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.table.ColumnData;
import com.fas.jface.table.GenericCellRenderer;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.MasterTable;
import com.fas.jface.table.MasterTableDataModel;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.PasswordInputText;
import com.fas.jface.text.TimeInputText;
import com.fas.jface.utils.SwingUtils;


/**
 * @author PC13
 *
 */
public abstract class BaseMasterFrame extends InspectFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(BaseMasterFrame.class);
	/** */
	protected JDialog dlg;
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
	protected int LBODY_PANEL_WIDTH = 400;
	/** */
	protected int RBODY_PANEL_WIDTH = 500;
	/** */
	protected String CUR_MODE = "";
	/** */
	protected MasterTable m_table;
	/** */
	protected MasterTableDataModel m_data;
	/** */
	protected int ROW_PER_PAGE;
	/** */
	protected boolean isRequireResetTabDispFocus = true;
	private Timer timer;
	private PropertyChangeListener pro;
	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strInfor
	 */
	protected void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BaseMasterFrame(BaseFrame frame, String title) {
        super(title);
        parentFrame = frame;
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
	public BaseMasterFrame(BaseFrame frame) {
        super("");
        parentFrame = frame;
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
	public BaseMasterFrame() {
        super();
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
		setTitle(getSubName());
		CUR_MODE = "";
        setResizable(false);
        setDefaultCloseOperation( DO_NOTHING_ON_CLOSE ); 
        ExitMasterWindow exitWin = new ExitMasterWindow(btnF12);
        addWindowListener(exitWin);
        btnF12.setParentFrame(parentFrame);
        
//        MouseAdapter mouseAdapter = new MouseAdapter() {
//        	public void mouseEntered(MouseEvent e) {
//	    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        	}
//    
//	    	public void mouseExited(MouseEvent e) {
//	    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//	    	}
//        };
        
//        setMouseCurrsor(btnF1, mouseAdapter);
//        setMouseCurrsor(btnF2, mouseAdapter);
//        setMouseCurrsor(btnF3, mouseAdapter);
//        setMouseCurrsor(btnF4, mouseAdapter);
//        setMouseCurrsor(btnF5, mouseAdapter);
//        setMouseCurrsor(btnF6, mouseAdapter);
//        setMouseCurrsor(btnF7, mouseAdapter);
//        setMouseCurrsor(btnF8, mouseAdapter);
//        setMouseCurrsor(btnF9, mouseAdapter);
//        setMouseCurrsor(btnF10, mouseAdapter);
//        setMouseCurrsor(btnF11, mouseAdapter);
//        setMouseCurrsor(btnF12, mouseAdapter);
//        setMouseCurrsor(btnFirst, mouseAdapter);
//        setMouseCurrsor(btnPre, mouseAdapter);
//        setMouseCurrsor(btnNext, mouseAdapter);
//        setMouseCurrsor(btnLast, mouseAdapter);
        isRequireResetTabDispFocus = true;
    }
    
//    /**
//     * <DL>
//     *   <DT>メソッド記述：</DT>
//     * 		<DD></DD>
//     * <BR>
//     *
//     * </DL>
//     * @param btn
//     * @param mouseAd
//     */
//    private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
//    	if (btn != null) {
//    		btn.addMouseListener(mouseAd);
//    	}
//    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract String getSubName();
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public String getSysName() {
    	return getSubName();
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
    protected String getPCName() {
    	if (ApplicationConstants.loginUser != null) {
    		return ApplicationConstants.loginUser.getLoginPC();
    	} else {
    		return "";
    	}
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
    protected String getUserName() {
    	if (ApplicationConstants.loginUser != null) {
    		return ApplicationConstants.loginUser.getUserId();
    	} else {
    		return "";
    	}
    }
    
    /* (non-Javadoc)
     * @see com.linc.jface.base.gui.LincFrame#getBodyPanel()
     */
    protected BasePanel getBodyPanel() {
    	//パネルの生成
    	mainPnl = new BasePanel();
    	mainPnl.setLayout(null);
    	mainPnl.setBorder(null);

    	mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
    	int xPos = 9;
    	int yPos = 4;    	
    	mainPnl.setLocation(xPos, yPos);

    	final int BTN_PANEL_HEIGHT = 45;
    	final int SPACE_WIDTH = 5;
    	final int SPACE_HEIGHT = 5;
    	final int BODY_PANEL_HEGHT = Y_BODY_LENGTH - BTN_PANEL_HEIGHT - SPACE_HEIGHT;
    	LBODY_PANEL_WIDTH = getRBodyWidth();
    	RBODY_PANEL_WIDTH = X_BODY_LENGTH - LBODY_PANEL_WIDTH - SPACE_WIDTH - 6;
    	
       	xPos = 3;
       	yPos = 1;
    	lInputPnl = new BasePanel();
    	lInputPnl.setLayout(null);
    	lInputPnl.setBorder(FaceContants.TITLE_BORDER);
    	lInputPnl.setLocation(xPos, yPos);
    	lInputPnl.setSize(new Dimension(LBODY_PANEL_WIDTH, BODY_PANEL_HEGHT));
    	initLeftBody(lInputPnl);
    	mainPnl.add(lInputPnl);
    	
    	xPos += LBODY_PANEL_WIDTH + SPACE_WIDTH;
    	rInputPnl = new BasePanel();
    	rInputPnl.setLayout(null);
    	rInputPnl.setBorder(FaceContants.BEVEL_BORDER);
    	rInputPnl.setLocation(xPos, yPos);
    	rInputPnl.setSize(new Dimension(RBODY_PANEL_WIDTH, BODY_PANEL_HEGHT));
    	initRightBody(rInputPnl);
    	mainPnl.add(rInputPnl);
    	
    	xPos = 3;
    	yPos += BODY_PANEL_HEGHT + SPACE_HEIGHT;
    	BasePanel btnPnl = new BasePanel();
    	btnPnl.setLayout(null);
    	btnPnl.setLocation(xPos, yPos);
    	
    	boolean enbaleB[] = enableBtn();
    	int X_BTN_WIDTH = 71;
    	int Y_BTN_HEIGHT = 36;
    	int X_BTN_SPACE = 4;
    	yPos = 4;
    	xPos = 7 - X_BTN_WIDTH - X_BTN_SPACE;
    	
    	if (enbaleB[1]) {
	    	F1Action f1Action = new F1Action("f1Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF1 = new ActionButton();
	    	btnF1.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF1.setText("<html><center><font size=-1>Excel出力</font></center><center><font size=-1>F1(E)</font></center>");
	    	btnF1.addActionListener(f1Action);
	    	btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), f1Action.getValue(Action.NAME));
	    	btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt E"), f1Action.getValue(Action.NAME));
	    	btnF1.getActionMap().put("f1Action", f1Action);
	    	btnF1.setFocusable(false);
	    	btnF1.setToolTipText("[F1]：データを出力します。");
	    	btnPnl.add(btnF1);  
    	}
    	
    	if (enbaleB[2]) {
	    	F2Action f2Action = new F2Action("f2Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF2 = new ActionButton();
	    	btnF2.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF2.setText("<html><center><font size=-1>マスタ複写</font></center><center><font size=-1>F2(F)</font></center>");
	    	btnF2.addActionListener(f2Action);
	    	btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
	    	btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt F"), f2Action.getValue(Action.NAME));
	    	btnF2.getActionMap().put("f2Action", f2Action);
	    	btnF2.setToolTipText("[F2]：データを複写します。");
	    	btnF2.setFocusable(false);
	    	btnPnl.add(btnF2);  
    	}
    	
    	if (enbaleB[3]) {
	    	F3Action f3Action = new F3Action("f3Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF3 = new ActionButton();
	    	btnF3.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF3.setText("<html><center><font size=-1>分類名</font></center><center><font size=-1>F3(G)</font></center>");
	    	btnF3.addActionListener(f3Action);
	    	btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), f3Action.getValue(Action.NAME));
	    	btnF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt G"), f3Action.getValue(Action.NAME));
	    	btnF3.getActionMap().put("f3Action", f3Action);
	    	btnF3.setFocusable(false);
	    	btnPnl.add(btnF3);  
    	}
    	
    	if (enbaleB[4]) {
	    	F4Action f4Action = new F4Action("f4Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF4 = new ActionButton();
	    	btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF4.setText("<html><center><font size=-1>コード検索</font></center><center><font size=-1>F4(S)</font></center>");
	    	btnF4.addActionListener(f4Action);
	    	btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), f4Action.getValue(Action.NAME));
	    	btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), f4Action.getValue(Action.NAME));
	    	btnF4.getActionMap().put("f4Action", f4Action);
	    	btnF4.setToolTipText("[F4]：データを検索します。");
	    	btnF4.addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent e) {
					btnF4.setSearchEnable(true);
					btnF4.setEnabled(true);					
				}

				public void focusLost(FocusEvent e) {
					btnF4.setEnabled(false);	
				}
	    	});
	    	btnPnl.add(btnF4);
    	}
    	
    	if (enbaleB[5]) {
	    	F5Action f5Action = new F5Action("f5Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF5 = new ActionButton();
	    	btnF5.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF5.setText("<html><center><font size=-1>分類名</font></center><center><font size=-1>F5(T)</font></center>");
	    	btnF5.addActionListener(f5Action);
	    	btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), f5Action.getValue(Action.NAME));
	    	btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt T"), f5Action.getValue(Action.NAME));
	    	btnF5.getActionMap().put("f5Action", f5Action);
	    	btnF5.setFocusable(false);
	    	btnPnl.add(btnF5);  
    	}
    	
    	if (enbaleB[6]) {
	    	F6Action f6Action = new F6Action("f6Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF6 = new ActionButton();
	    	btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF6.setText("<html><center><font size=-1>削除</font></center><center><font size=-1>F6(D)</font></center>");
	    	btnF6.addActionListener(f6Action);
	    	btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), f6Action.getValue(Action.NAME));
	    	btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f6Action.getValue(Action.NAME));
	    	btnF6.getActionMap().put("f6Action", f6Action);
	    	btnF6.setToolTipText("[F6]：データを削除します。");
	    	btnF6.setFocusable(false);
	    	btnPnl.add(btnF6);  
    	}
    	
    	if (enbaleB[7]) {
	    	F7Action f7Action = new F7Action("f7Action");
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF7 = new ActionButton();
	    	btnF7.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF7.setText("<html><center><font size=-1>   </font></center><center><font size=-1>F7(X)</font></center>");
	    	btnF7.addActionListener(f7Action);
	    	btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), f7Action.getValue(Action.NAME));
	    	btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt X"), f7Action.getValue(Action.NAME));
	    	btnF7.getActionMap().put("f7Action", f7Action);
	    	btnF7.setFocusable(false);
	    	btnPnl.add(btnF7);  
    	}
    	
    	if (enbaleB[8]) {
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	F8Action f8Action = new F8Action("f8Action");
	    	btnF8 = new ActionButton();
	    	btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF8.setText("<html><center><font size=-1>登録</font></center><center><font size=-1>F8(A)</font></center>");
	    	btnF8.addActionListener(f8Action);
	    	btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
	    	btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
	    	btnF8.getActionMap().put("f8Action", f8Action);
	    	btnF8.setToolTipText("[F8]：データを登録します。");
	    	btnF8.setFocusable(false);
	    	btnPnl.add(btnF8); 
    	}
    	
    	if (enbaleB[9]) {
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	F9Action f9Action = new F9Action("f9Action");
	    	btnF9 = new ActionButton();
	    	btnF9.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF9.setText("<html><center><font size=-1>   </font></center><center><font size=-1>F9(A)</font></center>");
	    	btnF9.addActionListener(f9Action);
	    	btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), f9Action.getValue(Action.NAME));
	    	btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt K"), f9Action.getValue(Action.NAME));
	    	btnF9.getActionMap().put("f9Action", f9Action);
	    	btnF9.setFocusable(false);
	    	btnPnl.add(btnF9); 
    	}
    	
    	if (enbaleB[10]) {
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	F10Action f10Action = new F10Action("f10Action");
	    	btnF10 = new ActionButton();
	    	btnF10.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
	    	btnF10.setText("<html><center><font size=-1>データ表示</font></center><center><font size=-1>F10(V)</font></center>");
	    	btnF10.addActionListener(f10Action);
	    	btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), f10Action.getValue(Action.NAME));
	    	btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt V"), f10Action.getValue(Action.NAME));
	    	btnF10.getActionMap().put("f10Action", f10Action);
	    	btnF10.setToolTipText("[F10]：データを表示します。");
	    	btnF10.setFocusable(false);
	    	btnPnl.add(btnF10); 
    	}
    	
    	if (enbaleB[11]) {
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
	    	btnF11.setFocusable(false);
	    	btnPnl.add(btnF11); 
    	}
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF12 = new ExitMasterButton();
    	btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
    	btnF12.setToolTipText("[F12]：処理を終了します。");
    	btnF12.setConfirmRerquired(false);
    	btnF12.setFrame(getFrame());
    	btnF12.setFocusable(false);
    	btnPnl.add(btnF12); 
    	
    	xPos += X_BTN_SPACE + X_BTN_WIDTH + 10;
    	X_BTN_WIDTH = 20;
    	
    	btnFirst = new ActionButton();
    	btnFirst.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnFirst.setText("<html><center><font size=-1>≪</font>");
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
    	btnLast.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent e) {
    			 doLast();
    		 }
    	});
    	btnPnl.add(btnLast); 
    	
    	btnPnl.setSize(new Dimension(X_BODY_LENGTH - X_BTN_SPACE, BTN_PANEL_HEIGHT));
    	
    	mainPnl.add(btnPnl);

    	return mainPnl;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param pnl
     */
    protected abstract void initHeader(BasePanel pnl);
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param pnl
     */
    protected abstract void initLeftBody(BasePanel pnl);
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param pnl
     */
    protected abstract void initRightBody(BasePanel pnl);
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param com
     */
    protected void setDefaultFirstFocus(final JComponent com) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			if (com.isEnabled()) {
    				com.requestFocusInWindow();
    				if (com instanceof JTextField) {
    					com.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
    				}
    			}
    		}
    	});
    }
    
    /* (non-Javadoc)
     * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
     */
    protected void setFrameSize() {
    	X_FRAME_LENGTH = NumberUtils.getIntFromDouble(Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - 2;
    	Y_FRAME_LENGTH = NumberUtils.getIntFromDouble(Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 70;
//    	X_FRAME_LENGTH = 800;
//    	Y_FRAME_LENGTH = 600;
    	X_BODY_LENGTH = X_FRAME_LENGTH;
    	Y_BODY_LENGTH = Y_FRAME_LENGTH - 70;
    	X_HEADER_LENGTH = X_FRAME_LENGTH;
    	Y_HEADER_LENGTH = 40;
    	X_FOOTER_LENGTH = X_FRAME_LENGTH;
    	Y_FOOTER_LENGTH = 30;
    }
	
	@Override
	protected BasePanel getFooter() {
    	//パネルの生成
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

	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected BasePanel getHeader() {
    	//パネルの生成
		headerMainPnl = new BasePanel();
		headerMainPnl.setLayout(null);
		headerMainPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));
		initHeader(headerMainPnl);
		pro = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (("focusOwner".equals(prop))) {
					if (((e.getNewValue()) instanceof InspectRadioButton) 
							|| ((e.getNewValue()) instanceof BaseCalendarText) 
							|| ((e.getNewValue()) instanceof BaseInputText) 
							|| ((e.getNewValue()) instanceof BaseCheckBox) 
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
		};
    	KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	focusManager.addPropertyChangeListener(pro);
    	
    	return headerMainPnl;
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
	protected abstract String getHelpInfor();
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	protected abstract int getRBodyWidth();
	
	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public void hideProgressBar() {
		if (dlg != null) {
			dlg.setVisible(false);
		}
	}
	
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	class DoReportAction extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 475632632896118804L;

		public DoReportAction(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		 public void actionPerformed(ActionEvent e) {
	    	try {
				dlg = new JDialog(getFrame(), "実行中です。しばらくお待ち下さい。", true);
		    	
			    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			    dlg.setPreferredSize(new Dimension(250, 90));
			    BaseLabel lblMachi = new BaseLabel("<html><center><b><font size=+1>只今処理中です。</font></b></center><center><b><font size=+1>しばらくお待ち下さい。</font></b></center>");
			    lblMachi.setForeground(Color.WHITE);
			    lblMachi.setBackground(Color.BLUE);
			    lblMachi.setSize(250, 90);
			    lblMachi.setHorizontalAlignment(BaseLabel.CENTER);
			    lblMachi.setVerticalAlignment(BaseLabel.CENTER);
			    lblMachi.setLocation(0, 0);
			    dlg.setUndecorated(true);
			    dlg.setLayout(null);
			    dlg.add(lblMachi);
			    dlg.setLocation(NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 250)/ 2) - 3, NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 90) / 2) - 32);
			    dlg.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    dlg.pack();
			    dlg.setResizable(false);
	    	} finally {
	    		hideProgressBar();
	    	}
		}
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
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract void doF1();

	
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
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract void doF2();
	
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
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract void doF3();
	
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
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract void doF4();
	
	
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
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract void doF5();
	
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
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract void doF6();	
	
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
	protected abstract void doF7();		
	
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
	protected abstract void doF8();		
	
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
	protected abstract void doF9();	
	
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
	protected abstract void doF10();		
	
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
	protected abstract void doF11();	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	protected abstract boolean[] enableBtn();
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doFirst() {
		if (m_table.getRowCount() > 0) {
			m_table.getSelectionModel().setSelectionInterval(0, 0);
			doSingleClick(0);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	protected void invokeSetStatusLater() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				isRequireResetTabDispFocus = false;
				setStatus(CUR_MODE);
			}
		});
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param mode
	* </DL>
	*/
	protected void setStatus(String mode) {
		
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doLast() {

		if (m_table.getRowCount() > 0) {
			m_table.getSelectionModel().setSelectionInterval(m_table.getRowCount() - 1, m_table.getRowCount() - 1);
			doSingleClick(m_table.getRowCount() - 1);
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
	protected void doNext() {
		if (m_table.getRowCount() > 0) {
			int cRow = m_table.getSelectedRow();
			int nRow = cRow + ROW_PER_PAGE;
			if (m_table.getRowCount() - 1 < nRow) nRow = m_table.getRowCount() - 1;
			
			m_table.getSelectionModel().setSelectionInterval(nRow, nRow);
			doSingleClick(nRow);
			
			cRow = nRow + ROW_PER_PAGE;
			if (cRow >= m_table.getRowCount()) cRow = m_table.getRowCount();
			SwingUtils.scrollRowColumn(m_table , cRow , 0);
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
	protected void doPre() {
		if (m_table.getRowCount() > 0) {
			int cRow = m_table.getSelectedRow();
			int nRow = cRow - ROW_PER_PAGE;
			if (0 > nRow) nRow = 0;
			
			m_table.getSelectionModel().setSelectionInterval(nRow, nRow);
			doSingleClick(nRow);
			
			cRow = nRow - ROW_PER_PAGE;
			if (cRow < 0) cRow = 0;
			SwingUtils.scrollRowColumn(m_table , cRow , 0);
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
	protected void initTable() {
	  	
		m_table.setModel(m_data);
	  	
		ColumnData mCols[] = m_data.getColumnData();
	  	
	    for (int k = 0; k < mCols.length; k++) {
	    	GenericCellRenderer renderer = new GenericCellRenderer();
	        renderer.setHorizontalAlignment(mCols[k].m_alignment);
	        TableColumn column = new TableColumn(k, mCols[k].m_width, renderer, null);
	        m_table.addColumn(column);  
	    }
	    
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
		    	//action to be performed
	    		int row = m_table.getSelectedRow();
	    		
				if(0 == row){
					row = m_table.getRowCount() - 1;
				} else {
					row = row - 1;
				}

	    		if (row >= 0 && row < m_table.getRowCount()) {
					doSingleClick(row);
					m_table.setRowSelectionInterval(row , row);
					m_table.setColumnSelectionInterval(0 , 2);
	    		}
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "keyUp");
	    m_table.getActionMap().put("keyUp", keyUp);
	    
	    AbstractAction keyDown = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	//action to be performed
	    		int row = m_table.getSelectedRow();
	    		
				if(m_table.getRowCount() - 1 == row){
					row = 0;
				} else {
					row = row + 1;
				}

	    		if (row >= 0 && row < m_table.getRowCount()) {
					doSingleClick(row);
					m_table.setRowSelectionInterval(row , row);
					m_table.setColumnSelectionInterval(0 , 2);
	    		}
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "keyDown");
	    m_table.getActionMap().put("keyDown", keyDown);

	    AbstractAction lastRow = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				doLast();
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, InputEvent.CTRL_DOWN_MASK), "lastRow");
	    m_table.getActionMap().put("lastRow", lastRow);
		
	    AbstractAction firstRow = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				doFirst();
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, InputEvent.CTRL_DOWN_MASK), "firstRow");
	    m_table.getActionMap().put("firstRow", firstRow);
	    
	    m_table.getTableHeader().addMouseListener(m_data.setMouseListener(m_table));
	    m_table.getTableHeader().setPreferredSize(new Dimension(m_table.getTableHeader().getWidth(), FaceContants.TABLE_HEADER_HEIGHT));
	  	m_table.setLocation(1, 0);
	}
	
	protected void initTableAction(final InspectTable m_table) {
	  	
//		m_table.setModel(m_data);
//	  	
//		ColumnData mCols[] = m_data.getColumnData();
//	  	
//	    for (int k = 0; k < mCols.length; k++) {
//	    	GenericCellRenderer renderer = new GenericCellRenderer();
//	        renderer.setHorizontalAlignment(mCols[k].m_alignment);
//	        TableColumn column = new TableColumn(k, mCols[k].m_width, renderer, null);
//	        m_table.addColumn(column);  
//	    }
	    
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
		    	//action to be performed
	    		int row = m_table.getSelectedRow();
	    		
				if(0 == row){
					row = m_table.getRowCount() - 1;
				} else {
					row = row - 1;
				}

	    		if (row >= 0 && row < m_table.getRowCount()) {
					doSingleClick(row);
					m_table.setRowSelectionInterval(row , row);
					m_table.setColumnSelectionInterval(0 , 2);
	    		}
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "keyUp");
	    m_table.getActionMap().put("keyUp", keyUp);
	    
	    AbstractAction keyDown = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	//action to be performed
	    		int row = m_table.getSelectedRow();
	    		
				if(m_table.getRowCount() - 1 == row){
					row = 0;
				} else {
					row = row + 1;
				}

	    		if (row >= 0 && row < m_table.getRowCount()) {
					doSingleClick(row);
					m_table.setRowSelectionInterval(row , row);
					m_table.setColumnSelectionInterval(0 , 2);
	    		}
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "keyDown");
	    m_table.getActionMap().put("keyDown", keyDown);

	    AbstractAction lastRow = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				doLast();
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, InputEvent.CTRL_DOWN_MASK), "lastRow");
	    m_table.getActionMap().put("lastRow", lastRow);
		
	    AbstractAction firstRow = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				doFirst();
		    }
		};
	    m_table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, InputEvent.CTRL_DOWN_MASK), "firstRow");
	    m_table.getActionMap().put("firstRow", firstRow);
	    
//	    m_table.getTableHeader().addMouseListener(m_data.setMouseListener(m_table));
//	    m_table.getTableHeader().setPreferredSize(new Dimension(m_table.getTableHeader().getWidth(), FaceContants.TABLE_HEADER_HEIGHT));
//	  	m_table.setLocation(1, 0);
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param row
	 */
	protected abstract void doDoubleClick(int row);

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param row
	 */
	protected abstract void doSingleClick(int row);
	
	
	
	/**
	 * @author PC13
	 *
	 */
	public class ExitMasterWindow extends WindowAdapter {

		/** */
		private JButton btnAction;

		
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public ExitMasterWindow(JButton btnButton) {
			btnAction = btnButton;
		}

	    /* (non-Javadoc)
	     * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	     */
	    public void windowClosing( WindowEvent e ) {  
	    	btnAction.doClick();
	    }
	    
	    /* (non-Javadoc)
	     * @see java.awt.event.WindowAdapter#windowOpened(java.awt.event.WindowEvent)
	     */
	    public void windowOpened(WindowEvent e) {
	    	if (parentFrame != null) {
	    		parentFrame.setFocusableWindowState(true);
	    	}
	    }
	}
	
	protected class SplashScreen extends JWindow {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SplashScreen() {

			JPanel content = (JPanel) getContentPane();
			
			content.setBackground(Color.blue);
			int width = 320;
			int height = 115;
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width - width) / 2;
			int y = (screen.height - height) / 2;
			setBounds(x, y, width, height);

			// content.add(new JLabel("只今処理中です。しばらくお待ちください。"),
			// BorderLayout.CENTER);
			JLabel sms = new JLabel("<html><center><b><font size=+1>只今処理中です。</font></b></center><center><b><font size=+1>しばらくお待ち下さい。</font></b></center>");
			sms.setHorizontalAlignment(SwingConstants.CENTER);
			content.add(sms,
							BorderLayout.CENTER);
			//Color oraRed = Color.red;
			//content.setBorder(BorderFactory.createLineBorder(oraRed, 3));
		}
	}
	protected class ExportExcel
	{
		public ExportExcel()
		{
			
		}
		 public void DoExport(final String str)
		{
			String strPath = MOutCtlContants.getValue(ApplicationConstants
					.getLoginUser().getUserId(), MOutCtlContants.I_PATH1);
			String[] fileName = new String[] { "csv", "dat" };
			JFileChooser fileChoser = new JFileChooser();
			fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName,
					"ファイル (*.csv, *.dat)"));
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
						+ "\\" + fileChoser.getSelectedFile().getName() + ".csv";

				if (FileUtils.isValidPath(strFilePath)) {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								SplashScreen sp = new SplashScreen();
								sp.setVisible(true);
								sp.setAlwaysOnTop(true);
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								// ファイルを出力
								FileOutputStream outPutSream = FileUtils
										.getOutputStream(strFilePath);
								BufferedWriter bufferWriter = new BufferedWriter(
										new OutputStreamWriter(outPutSream,
												ApplicationConstants.ENCODE_MODE));
								bufferWriter.write(str);
								bufferWriter.flush();
								bufferWriter.close();
								sp.setVisible(false);
								sp.dispose();

							} catch (Exception ie) {								
							}

						}
					});
					t.start();
				} 
			}
		}
		 
		 public void DoExport(final ArrayList<ArrayList<String>> rs) throws SQLException, RowsExceededException, WriteException
			{
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
				if (rVal == JFileChooser.APPROVE_OPTION) 
				{
					final String strFilePath = fileChoser.getCurrentDirectory().toString()+ "\\" + fileChoser.getSelectedFile().getName().replaceAll(".xls", "") + ".xls";
					try
					{
						int iCount = rs.size();					
						WritableWorkbook workbook = Workbook.createWorkbook(new File( strFilePath ));		    
						WritableSheet s1 = workbook.createSheet("Sheet1", 0);
						for(int i = 0; i < iCount;i++)
						{
							ArrayList<String> arr = rs.get(i);
							for(int j = 0 ; j < arr.size(); j++)
							{
								try
								{
									Label l1 = new Label(j, i, arr.get(j));
									s1.addCell(l1);
								}
								catch(WriteException WriteEx)
								{}
							}
						}
						workbook.write();
						workbook.close();
					}
					catch(IOException IOex)
					{}				
				}
			}
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
