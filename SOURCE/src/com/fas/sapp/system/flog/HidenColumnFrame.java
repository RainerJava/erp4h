/**
 * 
 */
package com.fas.sapp.system.flog;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.button.ActionButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.BaseComboBox;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.TimeInputText;

public class HidenColumnFrame extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	private ActionButton btnF8;
	private ActionButton btnF9;
	private ActionButton btnF10;
	private JPanel panelState;
	private BaseCheckBox[] arrChkState;
	private BaseCheckBox chkState;
	private String[] colheadnm;
	private boolean[] isShow;
	private ActionButton btnF12;
	private BaseLabel lblHelpInfor;
	
	public boolean[] getColumnShow()
	{
		return isShow;
	}
	public void setColumnShow()
	{
		for(int i = 0; i<arrChkState.length;i++)
		{
			isShow[i] = arrChkState[i].isSelected();
		}
	}

	/** */
	public HidenColumnFrame() {
		super();		
		init();
	}

	public HidenColumnFrame(BaseFrame owner, boolean modal) {
		super(owner, modal);		
		init();
	}

	public HidenColumnFrame(BaseFrame owner, boolean modal, String prodCode,
			String prodName) {
		super(owner, modal);
		init();
	}
	public HidenColumnFrame(BaseFrame owner, boolean modal,String[] colheadnm, boolean[] isShow) {
		super(owner, modal);

		this.colheadnm = new String[colheadnm.length];
		this.isShow = new boolean[isShow.length];
		for (int i = 0; i < colheadnm.length; i++) {
			this.colheadnm[i] = colheadnm[i];
			this.isShow[i] = isShow[i];
		}

		init();
		initCheck();
	}

	protected void initHeader(BasePanel pnl) {

	}

	@Override
	protected BasePanel getHeader() {
    	KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	focusManager.addPropertyChangeListener(
    	    new PropertyChangeListener() {
    	        public void propertyChange(PropertyChangeEvent e) {
    	            String prop = e.getPropertyName();
    	            if (("focusOwner".equals(prop))) {
    	            	if (((e.getNewValue()) instanceof InspectRadioButton) || ((e.getNewValue()) instanceof BaseCalendarText) || ((e.getNewValue()) instanceof BaseInputText)  || ((e.getNewValue()) instanceof BaseCheckBox) 
    	            			|| ((e.getNewValue()) instanceof JButton) || ((e.getNewValue()) instanceof BaseComboBox)|| ((e.getNewValue()) instanceof TimeInputText)) {
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
		return null;
	}

	protected void init() {
		this.setTitle("表示選択");
		this.setResizable(false);
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};

		setMouseCurrsor(btnF8, mouseAdapter);
		setMouseCurrsor(btnF9, mouseAdapter);
		setMouseCurrsor(btnF10, mouseAdapter);
		setMouseCurrsor(btnF12, mouseAdapter);
	}
	private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
		if (btn != null) {
			btn.addMouseListener(mouseAd);
		}
	}
	protected void initMainPnl(BasePanel pnl) {
		int yPos = 0;
		int xPos = 0;

		panelState = new JPanel();
		panelState.setLayout(new BoxLayout(panelState, BoxLayout.Y_AXIS));
		panelState
				.setBackground(ColorConstants.DEFAULT_CHECKBOX_FOCUS_BACKGROUND_COLOR);

		JScrollPane scrollState = new JScrollPane(panelState);
		scrollState.getVerticalScrollBar().setUnitIncrement(5);
		scrollState.setLocation(xPos, yPos);
		scrollState.setSize(new Dimension(X_FRAME_LENGTH - 10, 350));
		pnl.add(scrollState);
	}

	void initCheck() {
		arrChkState = new BaseCheckBox[colheadnm.length];

		for (int i = 0; i < colheadnm.length; i++) {			
			chkState = new BaseCheckBox(colheadnm[i]);
			chkState.setSelected(isShow[i]);
			chkState.setToolTipText(colheadnm[i] + "を選択して下さい。");
			chkState.setAllowChangeColor(false);
			chkState.setBackground(ColorConstants.DEFAULT_CHECKBOX_FOCUS_BACKGROUND_COLOR);
			
			arrChkState[i] = chkState;
			
			panelState.add(chkState);
		}
		arrChkState[0].setEnabled(false);
		arrChkState[1].setEnabled(false);
	}

	@Override
	protected BasePanel getBodyPanel() {
		mainPnl = new BasePanel();
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		mainPnl.setLocation(10, 2 + Y_HEADER_LENGTH);
		
		BasePanel pnlMain = new BasePanel();
		pnlMain.setSize(X_BODY_LENGTH, Y_BODY_LENGTH);
		pnlMain.setLocation(10, 10);
		//pnlMain.setBorder(FaceContants.LBL_BORDER);
		initMainPnl(pnlMain);

		mainPnl.add(pnlMain);
		return mainPnl;
	}

	protected void initFooterPnl(BasePanel pnl) {
		int X_BTN_WIDTH = 71;
		int Y_BTN_HEIGHT = 36;
		int X_BTN_SPACE = 4;

		int yPos = 5;
		int xPos = 10;

		//xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	F8Action f8Action = new F8Action("f8Action");
    	btnF8 = new ActionButton();
    	btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF8.setText("<html><center><font size=-1>OK</font></center><center><font size=-1>F8(A)</font></center>");
    	btnF8.addActionListener(f8Action);
    	btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
    	btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
    	btnF8.getActionMap().put("f8Action", f8Action);
    	btnF8.setToolTipText("[F8]:表示選択を設定します。");
    	pnl.add(btnF8); 

    	
    	F9Action f9Action = new F9Action("f9Action");
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF9 = new ActionButton();
    	btnF9.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF9.setText("<html><center><font size=-1>全選択</font></center><center><font size=-1>F9(R)</font></center>");
    	btnF9.addActionListener(f9Action);
    	btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), f9Action.getValue(Action.NAME));
    	btnF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt R"), f9Action.getValue(Action.NAME));
    	btnF9.getActionMap().put("f9Action", f9Action);
    	btnF9.setToolTipText("[F9]:全チェックボックスを選択します。");
    	pnl.add(btnF9); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF10 = new ActionButton();
    	F10Action f10Action = new F10Action("f10Action");
    	btnF10.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF10.setText("<html><center><font size=-1>全解除</font></center><font size=-1>F10(V)</font>");
    	btnF10.addActionListener(f10Action);
    	btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), f10Action.getValue(Action.NAME));
    	btnF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt V"), f10Action.getValue(Action.NAME));
    	btnF10.getActionMap().put("f10Action", f10Action);
    	btnF10.setToolTipText("[F10]:全チェックボックスを解除します。");
    	pnl.add(btnF10); 

    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF12 = new ActionButton();
    	F12Action f12Action = new F12Action("f12Action");
    	btnF12.addActionListener(f12Action);
    	btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEIGHT));
    	btnF12.setText("<html><center><font size=-1>終了</font></center><center><font size=-1>F12(Q)</font></center>");
    	btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
    	btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), f12Action.getValue(Action.NAME));
    	btnF12.setToolTipText("[F12]:終了します。");
    	btnF12.getActionMap().put("f12Action", f12Action);
    	pnl.add(btnF12); 
	}

	@Override
//	protected BasePanel getFooter() {
//		footerMainPnl = new BasePanel();
//		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH,
//				Y_FOOTER_LENGTH));
//		footerMainPnl.setLocation(10, 2 + Y_HEADER_LENGTH + Y_BODY_LENGTH + 5);
//
//		BasePanel pnlButton = new BasePanel();
//		pnlButton.setSize(X_FOOTER_LENGTH - 2, Y_FOOTER_LENGTH - 2);
//		pnlButton.setLocation(10, 0);
//		pnlButton.setBorder(FaceContants.TITLE_BORDER);
//		initFooterPnl(pnlButton);
//
//		footerMainPnl.add(pnlButton);
//		return footerMainPnl;
//	}
	protected BasePanel getFooter() {
		int xPos = 1;
		int yPos = 1;

		footerMainPnl = new BasePanel();
		footerMainPnl.setPreferredSize(new Dimension(X_FOOTER_LENGTH,
				Y_FOOTER_LENGTH));
		footerMainPnl.setLocation(10, 2 + Y_HEADER_LENGTH + Y_BODY_LENGTH + 5);

		BasePanel pnlButton = new BasePanel();
		pnlButton.setSize(X_FOOTER_LENGTH - 2, Y_FOOTER_LENGTH - 2);
		pnlButton.setLocation(10, 0);
		pnlButton.setBorder(FaceContants.TITLE_BORDER);
		initFooterPnl(pnlButton);

		footerMainPnl.add(pnlButton);

		BasePanel statusBar = new BasePanel();
		statusBar.setSize(340-24, 22);
		statusBar.setLocation(10, 50);
		statusBar.setLayout(null);
		statusBar.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor.setSize(340-24, 22);
		lblHelpInfor.setLocation(xPos, yPos);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);
		footerMainPnl.add(statusBar);
		return footerMainPnl;
	}
	protected void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}
	private String getHelpInfor() {
		return "";
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.AbstractDialog#setFrameSize()
	 */
	protected void setFrameSize() {
		X_FRAME_LENGTH = 330;
		Y_FRAME_LENGTH = 250;

		X_HEADER_LENGTH = X_FRAME_LENGTH - 10;
		Y_HEADER_LENGTH = 35;

		X_BODY_LENGTH = X_FRAME_LENGTH  - 10;
		Y_BODY_LENGTH = 160;

		X_FOOTER_LENGTH = X_FRAME_LENGTH - 10;
		Y_FOOTER_LENGTH = 50;
	}

	@Override
	protected JSeparator getFooterSeparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		// TODO Auto-generated method stub
		return null;
	}
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
			doF4();	
		}
	}
	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>最後</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF4() {	
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
	 * 		<DD>追加</DD>
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
	 * 		<DD>削除</DD>
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
	protected void doF8() {
		setColumnShow();
		dispose();
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
	protected void doF9() {
		for(int i = 0; i < arrChkState.length; i++) arrChkState[i].setSelected(true);
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
	protected void doF10() {
		for(int i = 2; i < arrChkState.length; i++)
			{
				arrChkState[i].setSelected(false);
			}
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
	protected void doF11() 
	{

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
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF12();	
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
	protected void doF12() {
		dispose();
	}

}
