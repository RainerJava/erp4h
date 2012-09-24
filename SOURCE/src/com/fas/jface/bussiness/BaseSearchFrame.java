/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：BaseSearchFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2010/03/02
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.jface.bussiness;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.border.DropShadowBorder;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.NumberUtils;
import com.fas.jface.button.ActionButton;
import com.fas.jface.button.ExitMasterButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.DoubleText;
import com.fas.jface.text.PasswordInputText;

/**
 * @author PC12
 * 
 */
public abstract class BaseSearchFrame extends InspectFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(BaseSearchFrame.class);
	/** */
	protected JDialog dlg;
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
	/** */
	protected BaseLabel lblHelpInfor;
	/** */
	final int SPACE_WIDTH = 5;
	/** */
	final int SPACE_HEIGHT = 5;

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
	public BaseSearchFrame(BaseFrame frame, String title) {
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
	public BaseSearchFrame(BaseFrame frame) {
		super("");
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
	public BaseSearchFrame() {
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
		setTitle(getSubName());
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		ExitMasterWindow exitWin = new ExitMasterWindow(btnF12);
		addWindowListener(exitWin);

//		MouseAdapter mouseAdapter = new MouseAdapter() {
//			public void mouseEntered(MouseEvent e) {
//				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//
//			public void mouseExited(MouseEvent e) {
//				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//			}
//		};
//
//		setMouseCurrsor(btnF4, mouseAdapter);
//		setMouseCurrsor(btnF5, mouseAdapter);
//		setMouseCurrsor(btnF6, mouseAdapter);
//		setMouseCurrsor(btnF7, mouseAdapter);
//		setMouseCurrsor(btnF8, mouseAdapter);
//		setMouseCurrsor(btnF9, mouseAdapter);
//		setMouseCurrsor(btnF10, mouseAdapter);
//		setMouseCurrsor(btnF11, mouseAdapter);
//		setMouseCurrsor(btnF12, mouseAdapter);
		
		btnF4.setFocusable(false);
		btnF5.setFocusable(false);
		btnF6.setFocusable(false);
		btnF7.setFocusable(false);
		btnF8.setFocusable(false);
		btnF9.setFocusable(false);
		btnF10.setFocusable(false);
		btnF11.setFocusable(false);
		btnF12.setFocusable(false);
		
		// set ToolTip for footer　
    	KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	focusManager.addPropertyChangeListener(
    	    new PropertyChangeListener() {
    	        public void propertyChange(PropertyChangeEvent e) {
    	            String prop = e.getPropertyName();
    	            if (("focusOwner".equals(prop))) {
    	            	if (((e.getNewValue()) instanceof BaseInputText) 
    	            					|| ((e.getNewValue()) instanceof BaseCalendarText)
    	            					|| ((e.getNewValue()) instanceof BaseComboBox)
    	            					|| ((e.getNewValue()) instanceof PasswordInputText)
    	            					|| ((e.getNewValue()) instanceof BaseCheckBox)
    	            					|| ((e.getNewValue()) instanceof DoubleText)
    	            					|| ((e.getNewValue()) instanceof JScrollPane)
    	            					||((e.getNewValue()) instanceof JButton)) {
    		    	                JComponent comp = (JComponent)e.getNewValue();
    		    	                String name = comp.getToolTipText();
    		    	                if (name != null) {
    		    	                	setHelpInfor(name);
    		    	                }
    	    	            	}
    	    	            }
    	        }
    	    }
    	);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.gui.InspectFrame#getBodyPanel()
	 */
	protected BasePanel getBodyPanel() {
		// パネルの生成
		mainPnl = new BasePanel();
		int xPos = 9;
		int yPos = 0;
		mainPnl.setLayout(null);
		mainPnl.setBorder(null);

		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		mainPnl.setLocation(xPos, yPos);

		BasePanel tblBtnPnl;
		tblBtnPnl = getCommandPnl();
		tblBtnPnl.setBorder(new DropShadowBorder());
		xPos = 3;
		yPos = Y_BODY_LENGTH - tblBtnPnl.getHeight();
		tblBtnPnl.setLocation(xPos, yPos);

		mainPnl.add(tblBtnPnl);

		xPos = 3;
		yPos = 0;
		BasePanel lInputPnl = new BasePanel();
		lInputPnl.setLayout(null);
		lInputPnl.setBorder(new DropShadowBorder());
		lInputPnl.setLocation(xPos, yPos);
		lInputPnl.setSize(new Dimension(X_BODY_LENGTH - xPos, Y_BODY_LENGTH - tblBtnPnl.getHeight()));
		initBody(lInputPnl);
		mainPnl.add(lInputPnl);

		return mainPnl;
	}

	protected BasePanel getCommandPnl() {
		int xPos = 7;
		int yPos = 4;

		final int X_BTN_WIDTH = 71;
		final int Y_BTN_HEGITH = 36;
		final int X_BTN_SPACE = 5;
		final int BTN_PANEL_HEIGHT = 45;
		final int BTN_PANEL_WIDTH = X_BODY_LENGTH - xPos;

		BasePanel btnPnl = new BasePanel();

		boolean enbaleB[] = enableBtn();

		if (enbaleB[4]) {
			F4Action f4Action = new F4Action("f4Action");
			btnF4 = new ActionButton();
			btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
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
			btnF5.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
			btnF5.setText("<html><center><font size=-1>品規問合せ</font></center><center><font size=-1>F5(T)</font></center>");
			btnF5.addActionListener(f5Action);
			btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), f5Action.getValue(Action.NAME));
			btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt T"), f5Action.getValue(Action.NAME));
			btnF5.getActionMap().put("f5Action", f5Action);
			btnF5.setToolTipText("[F5]：品規問合せ。");
			btnPnl.add(btnF5);
		}

		if (enbaleB[6]) {
			F6Action f6Action = new F6Action("f6Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF6 = new ActionButton();
			btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
			btnF6.setText("<html><center><font size=-1>全選択</font></center><center><font size=-1>F6(D)</font></center>");
			btnF6.addActionListener(f6Action);
			btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), f6Action.getValue(Action.NAME));
			btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f6Action.getValue(Action.NAME));
			btnF6.getActionMap().put("f6Action", f6Action);
			btnPnl.add(btnF6);
		}

		if (enbaleB[7]) {
			F7Action f7Action = new F7Action("f7Action");
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			btnF7 = new ActionButton();
			btnF7.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
			btnF7.setText("<html><center><font size=-1>全解除</font></center><center><font size=-1>F7(W)</font></center>");
			btnF7.addActionListener(f7Action);
			btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), f7Action.getValue(Action.NAME));
			btnF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt W"), f7Action.getValue(Action.NAME));
			btnF7.getActionMap().put("f7Action", f7Action);
			btnPnl.add(btnF7);
		}

		if (enbaleB[8]) {
			xPos += X_BTN_WIDTH + X_BTN_SPACE;
			F8Action f8Action = new F8Action("f8Action");
			btnF8 = new ActionButton();
			btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
			btnF8.setText("<html><center><font size=-1>実　行</font></center><center><font size=-1>F8(A)</font></center>");
			btnF8.addActionListener(f8Action);
			btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
			btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
			btnF8.getActionMap().put("f8Action", f8Action);
			btnF8.setToolTipText("[F8]：出力処理を行います。");
			btnPnl.add(btnF8);
		}
		
	   	if (enbaleB[9]) {
//	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	F9Action f9Action = new F9Action("f9Action");
	    	btnF9 = new ActionButton();
	    	btnF9.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
	    	btnF9.setText("<html><center><font size=-1>検査完了</font></center><center><font size=-1>F9(R)</font></center>");
	    	btnF9.addActionListener(f9Action);
	    	btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), f9Action.getValue(Action.NAME));
	    	btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt R"), f9Action.getValue(Action.NAME));
	    	btnF9.getActionMap().put("f9Action", f9Action);
	    	btnF9.setToolTipText("[F19]：検査完了");
	    	btnPnl.add(btnF9); 
    	}
    	
    	if (enbaleB[10]) {
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	F10Action f10Action = new F10Action("f10Action");
	    	btnF10 = new ActionButton();
	    	btnF10.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
	    	btnF10.setText("<html><center><font size=-1>検査問合せ</font></center><center><font size=-1>F10(V)</font></center>");
	    	btnF10.addActionListener(f10Action);
	    	btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), f10Action.getValue(Action.NAME));
	    	btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt V"), f10Action.getValue(Action.NAME));
	    	btnF10.getActionMap().put("f10Action", f10Action);
	    	btnF10.setToolTipText("[F10]：検査問合わせ。");
	    	btnPnl.add(btnF10); 
    	}
    	
    	if (enbaleB[11]) {
	    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
	    	btnF11 = new ActionButton();
	    	F11Action f11Action = new F11Action("f11Action");
	    	btnF11.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
	    	btnF11.setText("<html><center><font size=-1>中　止</font></center><font size=-1>F11(C)</font>");
	    	btnF11.addActionListener(f11Action);
	    	btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), f11Action.getValue(Action.NAME));
	    	btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt C"), f11Action.getValue(Action.NAME));
	    	btnF11.getActionMap().put("f11Action", f11Action);
	    	btnF11.setToolTipText("[F11]：処理を中止します。");
	    	btnPnl.add(btnF11); 
    	}

		xPos += X_BTN_WIDTH + X_BTN_SPACE;
		btnF12 = new ExitMasterButton();
		btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
		btnF12.setText("<html><center><font size=-1>終　了</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.setToolTipText("[F12]：処理を終了します。");
		btnF12.setConfirmRerquired(false);
		btnF12.setFrame(getFrame());
		btnPnl.add(btnF12);

		btnPnl.setSize(new Dimension(BTN_PANEL_WIDTH, BTN_PANEL_HEIGHT));
		mainPnl.add(btnPnl);

		xPos = BTN_PANEL_WIDTH + 10;
		BasePanel tblBtnPnl = new BasePanel();
		tblBtnPnl.setLayout(null);
		tblBtnPnl.setSize(new Dimension(X_BTN_WIDTH * 7 + X_BTN_SPACE * 6, BTN_PANEL_HEIGHT));

		return btnPnl;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pnl
	 */
	protected abstract void initBody(BasePanel pnl);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
	 */
	protected void setFrameSize() {
		X_FRAME_LENGTH = 620;
		Y_FRAME_LENGTH = 655;

		X_HEADER_LENGTH = X_FRAME_LENGTH;
		Y_HEADER_LENGTH = 0;

		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 30;

		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH - Y_HEADER_LENGTH - Y_FOOTER_LENGTH;
	}

	@Override
	protected BasePanel getFooter() {
		// パネルの生成
		footerMainPnl = new BasePanel();
		footerMainPnl.setLayout(null);
		footerMainPnl.setBorder(null);
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
		Timer timer = new Timer(1000, new ActionListener() {
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
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	protected abstract int getBodyWidth();

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
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

	protected abstract void doF4();

	class F5Action extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public F5Action(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			doF5();
		}
	}

	protected abstract void doF5();
	
	class F6Action extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public F6Action(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			doF6();
		}
	}

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
			    doF8();	
	    	} finally {
	    		hideProgressBar();
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
	 * @author PC12
	 * 
	 */
	public class ExitMasterWindow extends WindowAdapter {

		/** */
		private JButton btnAction;

		/**
		 * <DL>
		 * O
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
}
