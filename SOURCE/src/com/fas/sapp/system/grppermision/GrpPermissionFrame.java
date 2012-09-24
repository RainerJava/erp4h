/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDao.java
*
*	記述			：
*
*	作成日		：  2011/08/26  11:55:02 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.sapp.system.grppermision;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXTable;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.EditConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.FocusPolicy;
import com.fas.jface.InspectRadioButton;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.button.ExitMasterButton;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.EditLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.InspectTable;
import com.fas.jface.table.InspectTableRenderer;
import com.fas.jface.text.BaseCalendarText;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.CdInputSearchText;
import com.fas.jface.text.CdInputText;
import com.fas.jface.text.PasswordInputText;
import com.fas.jface.utils.SwingUtils;
import com.fas.sapp.search.SearchUserGroupFrame;
import com.fas.service.system.grppermission.GrpPermissionService;
import com.fas.service.system.grppermission.GrpPermissionServiceImpl;
import com.fas.service.system.permission.PermissionService;
import com.fas.service.system.permission.PermissionServiceImpl;
import com.fas.service.system.usergrp.UserGrpService;
import com.fas.service.system.usergrp.UserGrpServiceImpl;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.search.SearchVo;
import com.fas.vo.usergrp.UserGrpVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public class GrpPermissionFrame extends InspectFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** log */
	static Logger logger = Logger.getLogger(GrpPermissionFrame.class);
	
	/** */
	private BaseLabel lblHelpInfor;
	/** */
	private BaseLabel lblStatus;
	/** */
	private BaseLabel lblNumber;
	/** */
	private CdInputSearchText txtUser;
	/** */
	private PasswordInputText pwdPassword;
	/** */
	private BaseInputText txtInputText;
	/** */
	private BaseCheckBox chkShowLog;
	/** */
	private BaseCheckBox chkExcelOut;
	/** */
	private InspectTable tblMenuGrp;
	/** */
	private InspectTable tblMenuExe;
	/** */
	private InspectMenuGrpTableModel tblMenuGrpDataModel;
	/** */
	private InspectMenuExeTableModel tblMenuExeDataModel;
	/** */
	private List<MenuGrpVo> lstMenuGrpVo;
	/** */
	private List<MenuExeVo> lstMenuExeVo;
	/** */
	private BaseScrollPane psLeftTable;
	/** */
	private BaseScrollPane psCenterTable;
	/** */
	private static String arrMenuGrpHeader[] = { "メニュー名", "権限" };
	/** */
	private int[] arrMenuGrpWidth = { 80, 60 };
	/** */
	private static String arrMenuExeHeader[] = { "機能名", "権限" };
	/**  */
	private int[] arrMenuExeWidth = { 80, 60 };
	/** */
	private int iTableWidth = 0;
	/** */
	private ExitMasterButton btnF12;
	/** */
	private ActionButton btnF11;
	/** */
	private ActionButton btnF10;
	/** */
	private ActionButton btnF9;
	/** */
	private ActionButton btnF8;
	/** */
	private ActionButton btnF1;
	/** */
	private ActionButton btnF2;
	/** */
	private ActionButton btnF3;
	/** */
	private ActionButton btnF4;
	/** */
	private ActionButton btnF5;
	/** */
	private ActionButton btnF6;
	/** */
	private ActionButton btnF7;	
	/** */
	private String CUR_MODE = "";
	/** */
	private GrpPermissionService grpPermissionService;
	/** */
	private UserGrpService userGrpService;
	/** */
	private UserGrpVo userGrp;
	/** */
	private boolean isHenkouChuu = false;
	/** */
	private ActionButton btnPre;
	/** */
	private ActionButton btnNext;
	/** */
	private ActionButton btnLast;
	/** */
	private ActionButton btnFirst;
	/** */
	private int ROW_PER_PAGE;
	/** */
	private BaseLabel lblPermission;
	/** */
	private PropertyChangeListener pro;
	/** */	
	private Timer timer;	
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public GrpPermissionFrame(BaseFrame frame) {
        super(frame);
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
	public GrpPermissionFrame() {
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
        setResizable(false);
        setDefaultCloseOperation( DO_NOTHING_ON_CLOSE ); 
        ExitMasterWindow exitWin = new ExitMasterWindow(btnF12);
        addWindowListener(exitWin);
        btnF12.setParentFrame(parentFrame);
        lstMenuExeVo = new ArrayList<MenuExeVo>();
        lstMenuGrpVo = new ArrayList<MenuGrpVo>();
        CUR_MODE = EditConstants.VIEW_MODE;
        setStatus(CUR_MODE);
		setDispTabFocus();
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
	    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
    
	    	public void mouseExited(MouseEvent e) {
	    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
        };   
        
		pro = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (("focusOwner".equals(prop))) {
					if (((e.getNewValue()) instanceof InspectRadioButton) || ((e.getNewValue()) instanceof BaseCalendarText)
							|| ((e.getNewValue()) instanceof BaseInputText) || ((e.getNewValue()) instanceof BaseCheckBox)
							|| ((e.getNewValue()) instanceof JButton) || ((e.getNewValue()) instanceof BaseComboBox)
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
        
        setMouseCurrsor(btnF1, mouseAdapter);
        setMouseCurrsor(btnF2, mouseAdapter);
        setMouseCurrsor(btnF3, mouseAdapter);
        setMouseCurrsor(btnF4, mouseAdapter);
        setMouseCurrsor(btnF5, mouseAdapter);
        setMouseCurrsor(btnF6, mouseAdapter);
        setMouseCurrsor(btnF7, mouseAdapter);
        setMouseCurrsor(btnF8, mouseAdapter);
        setMouseCurrsor(btnF9, mouseAdapter);
        setMouseCurrsor(btnF10, mouseAdapter);
        setMouseCurrsor(btnF11, mouseAdapter);
        setMouseCurrsor(btnF12, mouseAdapter);
        setMouseCurrsor(btnFirst, mouseAdapter);
        setMouseCurrsor(btnPre, mouseAdapter);
        setMouseCurrsor(btnNext, mouseAdapter);
        setMouseCurrsor(btnLast, mouseAdapter);
	}
	
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param btn
     * @param mouseAd
     */
    private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
    	if (btn != null) {
    		btn.addMouseListener(mouseAd);
    	}
    }

	@Override
	protected BasePanel getBodyPanel() {

		mainPnl = new BasePanel();
		mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
		
		int xPos = 3, yPos = 1;
    	int X_BTN_SPACE = 4;
    	
		BasePanel pnlCondition = new BasePanel();
		pnlCondition.setLocation(xPos, yPos);
		pnlCondition.setSize(X_BODY_LENGTH - 3, 85);
		pnlCondition.setBorder(FaceContants.TITLE_BORDER);
		initInput(pnlCondition);
		
		mainPnl.add(pnlCondition);
		
		yPos += pnlCondition.getHeight() + FaceContants.VERTICAL_SPACE;
		BasePanel pnlTableContainer = new BasePanel();
		pnlTableContainer.setLocation(xPos, yPos);
		pnlTableContainer.setSize(X_BODY_LENGTH - 3, Y_BODY_LENGTH - yPos - FaceContants.VERTICAL_SPACE - 45);
		pnlTableContainer.setBorder(FaceContants.TITLE_BORDER);
		
		yPos += pnlTableContainer.getHeight() + FaceContants.VERTICAL_SPACE;
    	BasePanel pnlButton = new BasePanel();
    	pnlButton.setLocation(xPos, yPos);
    	pnlButton.setSize(X_BODY_LENGTH - 3, 45);
    	initButtonPnl(pnlButton);
    	mainPnl.add(pnlButton);
    	
    	xPos += pnlButton.getWidth() + FaceContants.HORIZONTAL_SPACE;
    	int X_BTN_WIDTH = 20;
    	int Y_BTN_HEGITH = 36;
    	yPos += 4;
    	
    	btnFirst = new ActionButton();
    	btnFirst.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnFirst.setText("<html><center><font size=-1>≪</font>");
    	btnFirst.setToolTipText("項目を選択して下さい。");
    	btnFirst.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent e) {
    			 doFirst();
    		 }
    	});
    	btnFirst.setFocusable(false);
    	mainPnl.add(btnFirst); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnPre = new ActionButton();
    	btnPre.setFocusable(false);
    	btnPre.setToolTipText("項目を選択して下さい。");
    	btnPre.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnPre.setText("<html><center><font size=-1>＜</font>");
    	btnPre.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent e) {
    			 doPre();
    		 }
    	});
    	mainPnl.add(btnPre); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnNext = new ActionButton();
    	btnNext.setFocusable(false);
    	btnNext.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnNext.setText("<html><center><font size=-1>＞</font>");
    	btnNext.setToolTipText("項目を選択して下さい。");
    	btnNext.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent e) {
    			 doNext();
    		 }
    	});
    	mainPnl.add(btnNext); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnLast = new ActionButton();
    	btnLast.setFocusable(false);
    	btnLast.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnLast.setText("<html><center><font size=-1>≫</font>");
    	btnLast.setToolTipText("項目を選択して下さい。");
    	btnLast.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent e) {
    			 doLast();
    		 }
    	});
    	mainPnl.add(btnLast); 
		
		final int PNL_WIDTH = NumberUtils.getIntFromDouble((pnlTableContainer.getWidth()  - 4 * FaceContants.HORIZONTAL_SPACE) / 3);
		final int PNL_HEIGHT = pnlTableContainer.getHeight() - 20;
		ROW_PER_PAGE = NumberUtils.getIntFromDouble(PNL_HEIGHT/ FaceContants.TABLE_ROW_HEIGHT) - 1;

		iTableWidth = PNL_WIDTH;
		xPos = FaceContants.HORIZONTAL_SPACE;
		yPos = 10;
		BasePanel pnlTableContainerLeft = new BasePanel();
		pnlTableContainerLeft.setLocation(xPos, yPos - 4);
		pnlTableContainerLeft.setSize(PNL_WIDTH, PNL_HEIGHT + 10);
		pnlTableContainerLeft.setBorder(BorderFactory.createTitledBorder("メニュー"));
		initLeftBodyPanel(pnlTableContainerLeft);
		pnlTableContainer.add(pnlTableContainerLeft);
		
		xPos += pnlTableContainerLeft.getWidth() + FaceContants.VERTICAL_SPACE;
		BasePanel pnlTableContainerCenter = new BasePanel();
		pnlTableContainerCenter.setLocation(xPos, yPos - 4);
		pnlTableContainerCenter.setSize(PNL_WIDTH, PNL_HEIGHT + 10);
		pnlTableContainerCenter.setBorder(BorderFactory.createTitledBorder("機能"));
		initCenterBodyPanel(pnlTableContainerCenter);
		pnlTableContainer.add(pnlTableContainerCenter);

		xPos += pnlTableContainerCenter.getWidth() + FaceContants.VERTICAL_SPACE;
		BasePanel pnlTableContainerRight = new BasePanel();
		pnlTableContainerRight.setLocation(xPos, yPos + 4);
		pnlTableContainerRight.setSize(PNL_WIDTH, PNL_HEIGHT);
		pnlTableContainerRight.setBorder(BorderFactory.createTitledBorder(""));
		initRightBodyPanel(pnlTableContainerRight);
		pnlTableContainer.add(pnlTableContainerRight);
		
		mainPnl.add(pnlTableContainer);
		
		return mainPnl;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param pnlInput
	* </DL>
	*/
	private void initInput(BasePanel pnlInput) {
		
		int xPos = FaceContants.HORIZONTAL_SPACE, yPos = FaceContants.VERTICAL_SPACE;
		int I_LBL_LENGTH = 100;
		int TXT_WIDTH_1 = 100;
		int TXT_WIDTH_2 = 300;
		
		EditLabel lblUser = new EditLabel("ユーザー");
		lblUser.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		lblUser.setLocation(xPos, yPos);
		pnlInput.add(lblUser);
		
		xPos += lblUser.getWidth();
		txtUser = new CdInputSearchText("", 0, CdInputText.IM_OFF, 10);
		txtUser.setLocation(xPos, yPos);
		txtUser.setSize(TXT_WIDTH_1, FaceContants.TEXT_HEIGHT);
		DocumentFilter filter = new UppercaseDocumentFilter();
		((AbstractDocument) txtUser.getDocument()).setDocumentFilter(filter);
		txtUser.setEnabled(true);
		txtUser.setToolTipText("ユーザーを入力して下さい。");
		pnlInput.add(txtUser);
		
		AbstractAction enter = new AbstractAction() {
			/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				try {
					if (txtUser.isEditable() && StringUtils.isValid(txtUser.getText())) {
						
						userGrp = userGrpService.getUserGrp(txtUser.getText().trim());
						
						if (userGrp == null) {
							CUR_MODE = EditConstants.NEW_MODE;
							setStatus(EditConstants.NEW_MODE);
							isHenkouChuu = true;
							doSetTableData();
							
						} else {
							isHenkouChuu = true;
							CUR_MODE = EditConstants.EDIT_MODE;
							setStatus(EditConstants.EDIT_MODE);
							pwdPassword.setText(userGrp.getPwd());
							txtInputText.setText(userGrp.getUserText());
							
							if (StringUtils.isEquals(userGrp.getExcelOut(), "1")) {
								chkExcelOut.setSelected(true);
								chkExcelOut.setText(NameConstants.getName("EXCELOUT" + "1"));		
							}

							if (StringUtils.isEquals(userGrp.getLogViewflg(), "1")) {
								chkShowLog.setSelected(true);
								chkShowLog.setText(NameConstants.getName("LOGVIEW" + "1"));
							}
							
							doSetTableData();
						}
					} 
				} catch (Exception ex) {
					logger.error(ex.getMessage());
					MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("C0000"));
				}
			}
		};
		txtUser.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		txtUser.getActionMap().put("enter", enter);
		
		txtUser.getFindButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				doF4();
			}
		});
		

		xPos += txtUser.getWidth() + 20;
		EditLabel lblShowLog = new EditLabel("ログ表示");
		lblShowLog.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		lblShowLog.setLocation(xPos, yPos);
		pnlInput.add(lblShowLog);

		xPos += lblShowLog.getWidth();
		chkShowLog = new BaseCheckBox(NameConstants.getName("LOGVIEW" + "0"));
		chkShowLog.setSize(120, FaceContants.TEXT_HEIGHT);
		chkShowLog.setLocation(xPos, yPos);
		chkShowLog.setChkLabel(NameConstants.getName("LOGVIEW" + "1"));
		chkShowLog.setUnChkLabel(NameConstants.getName("LOGVIEW" + "0"));	
		chkShowLog.setToolTipText("ログ表示を選択して下さい。");
		pnlInput.add(chkShowLog);

		
		xPos = FaceContants.HORIZONTAL_SPACE;
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblPassword = new EditLabel("パスワード");
		lblPassword.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		lblPassword.setLocation(xPos, yPos);
		pnlInput.add(lblPassword);

		xPos += lblPassword.getWidth();
		pwdPassword = new PasswordInputText(16);
		pwdPassword.setLocation(xPos, yPos);
		pwdPassword.setSize(120, FaceContants.TEXT_HEIGHT);
		pwdPassword.setToolTipText("パスワードを入力して下さい。");
		pnlInput.add(pwdPassword);
		
		xPos += txtUser.getWidth() + 20;
		EditLabel lblExcelOut = new EditLabel("Excel出力区分");
		lblExcelOut.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		lblExcelOut.setLocation(xPos, yPos);
		pnlInput.add(lblExcelOut);

		xPos += lblExcelOut.getWidth();
		chkExcelOut = new BaseCheckBox(NameConstants.getName("EXCELOUT" + "0"));
		chkExcelOut.setSize(TXT_WIDTH_1, FaceContants.TEXT_HEIGHT);
		chkExcelOut.setLocation(xPos, yPos);
		chkExcelOut.setChkLabel(NameConstants.getName("EXCELOUT" + "1"));
		chkExcelOut.setUnChkLabel(NameConstants.getName("EXCELOUT" + "0"));
		chkExcelOut.setToolTipText("Excel出力区分を選択して下さい。");
		pnlInput.add(chkExcelOut);

		xPos = FaceContants.HORIZONTAL_SPACE;		
		yPos += FaceContants.LABLE_HEIGHT + FaceContants.VERTICAL_SPACE;
		EditLabel lblText = new EditLabel("テキスト");
		lblText.setSize(I_LBL_LENGTH, FaceContants.LABLE_HEIGHT);
		lblText.setLocation(xPos, yPos);
		pnlInput.add(lblText);
		
		xPos += lblText.getWidth();
		txtInputText =  new BaseInputText("", 0, CdInputText.IM_HIRAGANA, 24);
		txtInputText.setLocation(xPos, yPos);
		txtInputText.setSize(TXT_WIDTH_2, FaceContants.TEXT_HEIGHT);
		txtInputText.setToolTipText("テキストを入力して下さい。");
		pnlInput.add(txtInputText);

	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param pnlLeftBody
	* </DL>
	*/
	private void initLeftBodyPanel(BasePanel pnlLeftBody) {
		
		psLeftTable = getLeftTable();

		psLeftTable.setSize(pnlLeftBody.getWidth() - 10, pnlLeftBody.getHeight() - 25);
		psLeftTable.getViewport().add(tblMenuGrp);
		psLeftTable.setLocation(5, 18);

		pnlLeftBody.add(psLeftTable);

	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void doSetTableData() {
		//Get data to table from Service
		tblMenuGrpDataModel.setData(getLeftTableData());
				
		//Repanit table (paint Scroll Vertical)
		tblMenuGrp.repaint();
		psLeftTable.getViewport().removeAll();
		psLeftTable.getViewport().add(tblMenuGrp);
		tblMenuGrp.requestFocus();

		getCenterTableData();

		//Set Pointer to first row
		int iSelectedIndex = 0;
		if (tblMenuGrp.getRowCount() > 0) {
			tblMenuGrp.clearSelection();
			tblMenuGrp.changeSelection(iSelectedIndex, 0, false, true);
		}

	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param btnPnl
	* </DL>
	*/
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
    	btnF1.setText("<html><center><font size=-1>&gt;&gt;</font></center><center><font size=-1>F1(E)</font></center>");
    	btnF1.addActionListener(f1Action);
    	btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), f1Action.getValue(Action.NAME));
    	btnF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt E"), f1Action.getValue(Action.NAME));
    	btnF1.getActionMap().put("f1Action", f1Action);
	    btnPnl.add(btnF1);
	    //btnFirstOrder.addActionListener(f1Action);
  
	    F2Action f2Action = new F2Action("f2Action");
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF2 = new ActionButton();
    	btnF2.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnF2.setText("<html><center><font size=-1>&lt;&lt;</font></center><center><font size=-1>F2(F)</font></center>");
    	btnF2.addActionListener(f2Action);
    	btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
    	btnF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt F"), f2Action.getValue(Action.NAME));
    	btnF2.getActionMap().put("f2Action", f2Action);
    	btnPnl.add(btnF2);  
    	
    	F4Action f4Action = new F4Action("f4Action");
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF4 = new ActionButton();
    	btnF4.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnF4.setText("<html><center><font size=-1>検索</font></center><center><font size=-1>F4(S)</font></center>");
    	btnF4.addActionListener(f4Action);
    	btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), f4Action.getValue(Action.NAME));
    	btnF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), f4Action.getValue(Action.NAME));
    	btnF4.getActionMap().put("f4Action", f4Action);
    	btnF4.setToolTipText("[F4]：データを検索します。");
    	btnPnl.add(btnF4);
    	
    	F6Action f6Action = new F6Action("f6Action");
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	btnF6 = new ActionButton();
    	btnF6.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnF6.setText("<html><center><font size=-1>削除</font></center><center><font size=-1>F6(D)</font></center>");
    	btnF6.addActionListener(f6Action);
    	btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), f6Action.getValue(Action.NAME));
    	btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt D"), f6Action.getValue(Action.NAME));
    	btnF6.getActionMap().put("f6Action", f6Action);
    	btnF6.setToolTipText("[F6]：データを削除します。");
    	btnPnl.add(btnF6); 
    	
    	xPos += X_BTN_WIDTH + X_BTN_SPACE;
    	F8Action f8Action = new F8Action("f8Action");
    	btnF8 = new ActionButton();
    	btnF8.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnF8.setText("<html><center><font size=-1>登録</font></center><center><font size=-1>F8(A)</font></center>");
    	btnF8.addActionListener(f8Action);
    	btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
    	btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
    	btnF8.getActionMap().put("f8Action", f8Action);
    	btnF8.setToolTipText("[F8]：データを登録します。");
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
    	btnF11.setToolTipText("[F11]：処理を中止します。");
    	btnPnl.add(btnF11); 

    	xPos += X_BTN_WIDTH + X_BTN_SPACE;    	
    	btnF12 = new ExitMasterButton();
    	btnF12.setBounds(new Rectangle(xPos, yPos, X_BTN_WIDTH, Y_BTN_HEGITH));
    	btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
    	btnF12.setToolTipText("[F12]：処理を終了します。");
    	btnF12.setConfirmRerquired(false);
    	btnF12.setFrame(getFrame());
    	btnF12.setFocusable(false);
    	
    	btnPnl.add(btnF12); 
    	
    	BTN_PANEL_WIDTH = xPos + X_BTN_WIDTH + 3 * X_BTN_SPACE;
    	btnPnl.setSize(new Dimension(BTN_PANEL_WIDTH, BTN_PANEL_HEIGHT));
    	btnPnl.setBorder(BorderFactory.createTitledBorder(""));
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	private BaseScrollPane getLeftTable() {

		BaseScrollPane tablePnl;

		arrMenuGrpWidth = new int[] { 80, 60 };
		arrMenuGrpWidth[0] = iTableWidth - arrMenuGrpWidth[1] - 28;
		
		tblMenuGrpDataModel = new InspectMenuGrpTableModel(arrMenuGrpHeader);
		tblMenuGrp = new InspectTable(tblMenuGrpDataModel) {
			
			/** */
			private static final long serialVersionUID = 1L;
			/** */
			private int[] align = {BaseLabel.LEFT, BaseLabel.CENTER};  
			/** */
			private InspectTableRenderer renderer = new InspectTableRenderer(align);
			
			
			/* (non-Javadoc)
			 * @see org.jdesktop.swingx.JXTable#getCellRenderer(int, int)
			 */
			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 1) {
					return true;
				} else {
					return false;
				}
			}
			
		};
		
		LeftTableButtonColumn buttonColumn = new LeftTableButtonColumn(tblMenuGrp, 1);
		tblMenuGrp.getSelectionModel().addListSelectionListener(new MenuGrpRowListener());
		tblMenuGrp.getTableHeader().setReorderingAllowed(false);
		
	    F2Action f2Action = new F2Action("f2Action");
		tblMenuGrp.getInputMap().put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
		tblMenuGrp.getActionMap().put("f2Action", f2Action);
	    F8Action f8Action = new F8Action("f8Action");
		tblMenuGrp.getInputMap().put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		tblMenuGrp.getActionMap().put("f8Action", f8Action);
		tblMenuGrp.setSortable(false);
		
		// set data for spread
		tblMenuGrpDataModel.setData(new ArrayList<MenuGrpVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) tblMenuGrp.getColumnModel();
		for (int i = 0; i < arrMenuGrpWidth.length; i++) {
			defModel.getColumn(i).setMinWidth(arrMenuGrpWidth[i]);
			defModel.getColumn(i).setWidth(arrMenuGrpWidth[i]);
		}
		
		tablePnl = new BaseScrollPane(tblMenuGrp);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tablePnl.repaint();
		
		return tablePnl;
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	class EditHeaderRenderer extends JLabel  implements TableCellRenderer {
		/** */
		private static final long serialVersionUID = 1L;

		// This method is called each time a column header 
		// using this renderer needs to be rendered. 
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int rowIndex, int vColIndex) {
			setText(value.toString()); 
			setToolTipText((String)value); 
			this.setBackground(Color.red);
			this.setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
			return this; 
		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	private List<MenuGrpVo> getLeftTableData() {
		
		if (grpPermissionService == null) {
			grpPermissionService = new GrpPermissionServiceImpl();
		}
		
		//Can Xu Li
		try {			
			lstMenuGrpVo = grpPermissionService.getLstMenuGrpVoWithPermission(txtUser.getText().trim());
		} catch (BizException e1) {
			return new ArrayList<MenuGrpVo>();
		}

		return lstMenuGrpVo;
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param pnlCenterBody
	* </DL>
	*/
	private void initCenterBodyPanel(BasePanel pnlCenterBody) {
		
		psCenterTable = getCenterTable();

		psCenterTable.setSize(pnlCenterBody.getWidth() - 10, pnlCenterBody.getHeight() - 25);
		psCenterTable.getViewport().add(tblMenuExe);
		psCenterTable.setLocation(5, 18);

		pnlCenterBody.add(psCenterTable);
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	private BaseScrollPane getCenterTable() {

		BaseScrollPane tablePnl;

		arrMenuExeWidth = new int[] { 80, 60 };
		arrMenuExeWidth[0] = iTableWidth - arrMenuExeWidth[1] - 28;
		
		tblMenuExeDataModel = new InspectMenuExeTableModel(arrMenuExeHeader);
		tblMenuExe = new InspectTable(tblMenuExeDataModel) {
			
			/** */
			private static final long serialVersionUID = 1L;
			/** */
			private int[] align = {BaseLabel.LEFT, BaseLabel.CENTER};  
			/** */
			private InspectTableRenderer renderer = new InspectTableRenderer(align);

			/* (non-Javadoc)
			 * @see org.jdesktop.swingx.JXTable#getCellRenderer(int, int)
			 */
			public TableCellRenderer getCellRenderer(int row, int col) {
				return renderer;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 1) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		CenterTableButtonColumn buttonColumn = new CenterTableButtonColumn(tblMenuExe, 1);
	    F2Action f2Action = new F2Action("f2Action");
	    tblMenuExe.getInputMap().put(KeyStroke.getKeyStroke("F2"), f2Action.getValue(Action.NAME));
	    tblMenuExe.getActionMap().put("f2Action", f2Action);
	    F8Action f8Action = new F8Action("f8Action");
	    tblMenuExe.getInputMap().put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
	    tblMenuExe.getActionMap().put("f8Action", f8Action);
	    tblMenuExe.setSortable(false);
	    tblMenuExe.getTableHeader().setReorderingAllowed(false);
	    
	    
	    AbstractAction tabKey = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (tblMenuGrp.isFocusOwner()) {
					tblMenuExe.requestFocus();
				} else {
					if (txtInputText.isFocusOwner()) {
						tblMenuGrp.requestFocus();
					} else {
						if (tblMenuExe.isFocusOwner()) {
							chkShowLog.requestFocus();
						}
					}
				}
		    }
		};
		tblMenuGrp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabKey");
		tblMenuGrp.getActionMap().put("tabKey", tabKey);
		tblMenuExe.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabKey");
		tblMenuExe.getActionMap().put("tabKey", tabKey);
		
	    AbstractAction shiftTabKey = new AbstractAction() {
	    	/** */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (tblMenuGrp.isFocusOwner()) {
					txtInputText.requestFocus();
				} else {
					if (tblMenuExe.isFocusOwner()) {
						tblMenuGrp.requestFocus();
					} else {
						if (chkShowLog.isFocusOwner()) {
							tblMenuExe.requestFocus();
						}
					}
				}
		    }
		};
		tblMenuGrp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		tblMenuGrp.getActionMap().put("shiftTab", shiftTabKey);				
		tblMenuExe.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK), "shiftTab");
		tblMenuExe.getActionMap().put("shiftTab", shiftTabKey);	
		
		// set data for spread
		tblMenuExeDataModel.setData(new ArrayList<MenuExeVo>());

		// COLUMN setting
		DefaultTableColumnModel defModel = (DefaultTableColumnModel) tblMenuExe.getColumnModel();
		for (int i = 0; i < arrMenuExeWidth.length; i++) {
			defModel.getColumn(i).setMinWidth(arrMenuExeWidth[i]);
			defModel.getColumn(i).setWidth(arrMenuExeWidth[i]);
		}
		
		tablePnl = new BaseScrollPane(tblMenuExe);
		tablePnl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePnl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tablePnl.repaint();
		
		return tablePnl;
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	private List<MenuExeVo> getCenterTableData() {
		
		if (grpPermissionService == null) {
			grpPermissionService = new GrpPermissionServiceImpl();
		}
		
		//Can Xu Li
		try {			
			lstMenuExeVo = grpPermissionService.getLstMenuVoWithPermission(txtUser.getText().trim());
		} catch (BizException e1) {
			e1.printStackTrace();
		}

		return lstMenuExeVo;
	}	

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param pnlRightBody
	* </DL>
	*/
	private void initRightBodyPanel(BasePanel pnlRightBody) {
		
		int xPos = FaceContants.HORIZONTAL_SPACE, yPos = FaceContants.VERTICAL_SPACE;
		int LBL_WIDTH_LEFT = NumberUtils.getIntFromDouble(pnlRightBody.getWidth()/2) - FaceContants.HORIZONTAL_SPACE - 10;
		int LBL_WIDTH_RIGHT = NumberUtils.getIntFromDouble(pnlRightBody.getWidth()/2) - FaceContants.HORIZONTAL_SPACE + 10;
		
		BaseLabel lblFuntion = new BaseLabel("各機能の");
		lblFuntion.setLocation(xPos, yPos);
		lblFuntion.setBorder(null);
		lblFuntion.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblFuntion);
		
		yPos += lblFuntion.getHeight();
		BaseLabel lblAction = new BaseLabel("登録・更新・削除・閲覧・印刷");
		lblAction.setLocation(xPos, yPos);
		lblAction.setBorder(null);
		lblAction.setSize(LBL_WIDTH_LEFT + LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblAction);

		yPos += lblAction.getHeight();		
		BaseLabel lblActionDown = new BaseLabel("　　　　　　　　　を制限します。");
		lblActionDown.setLocation(xPos, yPos);
		lblActionDown.setBorder(null);
		lblActionDown.setSize(LBL_WIDTH_LEFT + LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblActionDown);

		yPos += lblActionDown.getHeight();		
		BaseLabel lblMenuSynbol = new BaseLabel("メニュー    ○：");
		lblMenuSynbol.setLocation(xPos, yPos);
		lblMenuSynbol.setBorder(null);
		lblMenuSynbol.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblMenuSynbol);

		xPos += lblMenuSynbol.getWidth();		
		BaseLabel lblCanUse = new BaseLabel("使用可能");
		lblCanUse.setLocation(xPos, yPos);
		lblCanUse.setBorder(null);
		lblCanUse.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblCanUse);

		yPos += lblCanUse.getHeight();
		xPos = FaceContants.HORIZONTAL_SPACE;
		BaseLabel lblMenuSynbol1 = new BaseLabel("　　　　    □：");
		lblMenuSynbol1.setLocation(xPos, yPos);
		lblMenuSynbol1.setBorder(null);
		lblMenuSynbol1.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblMenuSynbol1);

		xPos += lblMenuSynbol1.getWidth();		
		BaseLabel lblViewOnly = new BaseLabel("使用不可（表示）");
		lblViewOnly.setLocation(xPos, yPos);
		lblViewOnly.setBorder(null);
		lblViewOnly.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblViewOnly);

		yPos += lblViewOnly.getHeight();
		xPos = FaceContants.HORIZONTAL_SPACE;
		BaseLabel lblMenuSynbol2 = new BaseLabel("　　　　    ×：");
		lblMenuSynbol2.setLocation(xPos, yPos);
		lblMenuSynbol2.setBorder(null);
		lblMenuSynbol2.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblMenuSynbol2);

		xPos += lblMenuSynbol1.getWidth();		
		BaseLabel lblDisable = new BaseLabel("使用不可（非表示）");
		lblDisable.setLocation(xPos, yPos);
		lblDisable.setBorder(null);
		lblDisable.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblDisable);
		
		xPos = FaceContants.HORIZONTAL_SPACE;
		yPos += lblActionDown.getHeight() + 15;		
		BaseLabel lblFunctionSynbol = new BaseLabel("機能　　    ○：");
		lblFunctionSynbol.setLocation(xPos, yPos);
		lblFunctionSynbol.setBorder(null);
		lblFunctionSynbol.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblFunctionSynbol);

		xPos += lblFunctionSynbol.getWidth();		
		BaseLabel lblFunctionCanUse = new BaseLabel("使用可能");
		lblFunctionCanUse.setLocation(xPos, yPos);
		lblFunctionCanUse.setBorder(null);
		lblFunctionCanUse.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblFunctionCanUse);

		yPos += lblFunctionCanUse.getHeight();
		xPos = FaceContants.HORIZONTAL_SPACE;
		BaseLabel lblFunctionSynbol1 = new BaseLabel("　　　　    △：");
		lblFunctionSynbol1.setLocation(xPos, yPos);
		lblFunctionSynbol1.setBorder(null);
		lblFunctionSynbol1.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblFunctionSynbol1);

		xPos += lblFunctionSynbol1.getWidth();		
		BaseLabel lblInspectOnly = new BaseLabel("閲覧のみ");
		lblInspectOnly.setLocation(xPos, yPos);
		lblInspectOnly.setBorder(null);
		lblInspectOnly.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblInspectOnly);
		
		yPos += lblInspectOnly.getHeight();
		xPos = FaceContants.HORIZONTAL_SPACE;
		BaseLabel lblFunctionSynbol2 = new BaseLabel("　　　　    □：");
		lblFunctionSynbol2.setLocation(xPos, yPos);
		lblFunctionSynbol2.setBorder(null);
		lblFunctionSynbol2.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblFunctionSynbol2);

		xPos += lblFunctionSynbol2.getWidth();		
		BaseLabel lblViewFunctionOnly = new BaseLabel("使用不可（表示）");
		lblViewFunctionOnly.setLocation(xPos, yPos);
		lblViewFunctionOnly.setBorder(null);
		lblViewFunctionOnly.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblViewFunctionOnly);

		yPos += lblViewOnly.getHeight();
		xPos = FaceContants.HORIZONTAL_SPACE;
		BaseLabel lblFunctionSynbol3 = new BaseLabel("　　　　    ×：");
		lblFunctionSynbol3.setLocation(xPos, yPos);
		lblFunctionSynbol3.setBorder(null);
		lblFunctionSynbol3.setSize(LBL_WIDTH_LEFT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblFunctionSynbol3);

		xPos += lblFunctionSynbol3.getWidth();
		BaseLabel lblNotView = new BaseLabel("使用不可（非表示）");
		lblNotView.setLocation(xPos, yPos);
		lblNotView.setBorder(null);
		lblNotView.setSize(LBL_WIDTH_RIGHT, FaceContants.LABLE_HEIGHT);
		pnlRightBody.add(lblNotView);		
		
	}

	@Override
	protected BasePanel getFooter() {
    	//パネルの生成
		footerMainPnl = new BasePanel();
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
		lblHelpInfor.setSize(new Dimension(X_FOOTER_LENGTH - 355, I_LBL_HEIGHT));
		lblHelpInfor.setLocation(xPos, yPos);
		lblHelpInfor.setText(getHelpInfor());
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);
		
		xPos += lblHelpInfor.getWidth() + 2;
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
		
		xPos = xPos + lblDate.getWidth() + 2;
		BaseLabel lblUser = new BaseLabel();
		lblUser.setSize(new Dimension(85, I_LBL_HEIGHT));
		lblUser.setLocation(xPos, yPos);
		lblUser.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblUser.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblUser.setBorder(FaceContants.LABEL_BORDER);
		lblUser.setText(getUserName());
		statusBar.add(lblUser);
		
		xPos = xPos + lblUser.getWidth() + 2;
		BaseLabel lblPc = new BaseLabel();
		lblPc.setSize(new Dimension(90, I_LBL_HEIGHT));
		lblPc.setLocation(xPos, yPos);
		lblPc.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblPc.setText(getPCName());
		lblPc.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblPc.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblPc);
		
		xPos = xPos + lblPc.getWidth() + 2;
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

		headerMainPnl = new BasePanel();
		headerMainPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));

		int X_WIDTH = 100;
		int xPos = 4;
		int yPos = 6;
		
		lblStatus = new BaseLabel("登　録");
		lblStatus.setBounds(new Rectangle(xPos, yPos, X_WIDTH + 20, FaceContants.LABLE_HEIGHT));
		lblStatus.setBorder(FaceContants.LINE_BORDER);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
		lblStatus.setFont(FontConstants.LBL_STATUS_FONT);
		headerMainPnl.add(lblStatus);
		
		xPos += X_WIDTH + 20;
		BaseLabel lblReg = new BaseLabel("登録件数");
		lblReg.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblReg.setBorder(FaceContants.LINE_BORDER);
		lblReg.setHorizontalAlignment(SwingConstants.CENTER);
		lblReg.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		headerMainPnl.add(lblReg);
		
		xPos += X_WIDTH;
		lblNumber = new BaseLabel("");
		lblNumber.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblNumber.setBorder(FaceContants.LABEL_BORDER);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		headerMainPnl.add(lblNumber);

		xPos = xPos + X_WIDTH + 5;
		lblPermission = new BaseLabel();
		lblPermission.setForeground(Color.RED);
		lblPermission.setBounds(new Rectangle(xPos, yPos, X_WIDTH, FaceContants.LABLE_HEIGHT));
		lblPermission.setBorder(null);
		lblPermission.setHorizontalAlignment(BaseLabel.CENTER);
		headerMainPnl.add(lblPermission);
		
		setTitleInfor();
		
		return headerMainPnl;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}

    /* (non-Javadoc)
     * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
     */
    protected void setFrameSize() {
    	
    	userGrpService = new UserGrpServiceImpl();
    	
    	X_FRAME_LENGTH = 800;
    	Y_FRAME_LENGTH = 600;
    	X_BODY_LENGTH = X_FRAME_LENGTH;
    	Y_BODY_LENGTH = Y_FRAME_LENGTH - 70;
    	X_HEADER_LENGTH = X_FRAME_LENGTH;
    	Y_HEADER_LENGTH = 40;
    	X_FOOTER_LENGTH = X_FRAME_LENGTH;
    	Y_FOOTER_LENGTH = 30;
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
	protected String getHelpInfor() {
		return "";
	}
    
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void setTitleInfor() {
		lblNumber.setText("0件");
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

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode) || EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			txtUser.setEnabled(false);
			pwdPassword.setEnabled(true);
			txtInputText.setEnabled(true);
			chkExcelOut.setEnabled(true);
			chkShowLog.setEnabled(true);
			isHenkouChuu = true;
			setScreenMode(CUR_MODE);
			setDispTabFocus();			
			setFirstFocus(chkShowLog);
		} else {
			resetComponent();
			isHenkouChuu = false;
			setScreenMode(CUR_MODE);
			setDispTabFocus();
			setFirstFocus(chkShowLog);
		}
		try {
			lblNumber.setText(userGrpService.getUserGrpCount() + "件");
		} catch (BizException e) {
			
		}

	}
	
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param mode
	 *            </DL>
	 */
	private void setScreenMode(String mode) {
		
		if (EditConstants.EDIT_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_EDIT_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_EDT_MODE_BACKGROUND_COLOR);
			if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu)) {
				btnF6.setEnabled(true);
				btnF8.setEnabled(true);
			}
			btnF12.setConfirmRerquired(true);
			btnF4.setEnabled(false);
		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(mode)) {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF6.setEnabled(false);
			if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu)) {
				btnF8.setEnabled(true);
			}
			btnF12.setConfirmRerquired(true);
			btnF4.setEnabled(false);
		} else {
			lblStatus.setText(EditConstants.LBL_REGISTER_MODE);
			lblStatus.setBackground(ColorConstants.LABEL_REGISTER_MODE_BACKGROUND_COLOR);
			btnF6.setEnabled(false);
			btnF8.setEnabled(false);
			btnF8.setAffterDoDisable(true);
			btnF12.setConfirmRerquired(false);
			btnF4.setEnabled(true);
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
	 * 		<DD>最初</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF1() {
		tblMenuGrp.requestFocus();
		int iSelectedRow = tblMenuGrp.getSelectedRow();
		if (iSelectedRow < 0 || iSelectedRow >= tblMenuGrp.getRowCount()) {
			iSelectedRow = 0;
		}
		
		if (tblMenuGrp.getRowCount() > 0) {
			SwingUtils.scrollRow(tblMenuGrp, iSelectedRow);
			tblMenuGrp.setRowSelectionInterval(iSelectedRow, iSelectedRow);
		}
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
	 * 		<DD>上へ</DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void doF2() {
		tblMenuExe.requestFocus();
		int iSelectedRow = tblMenuExe.getSelectedRow();
		if (iSelectedRow < 0 || iSelectedRow >= tblMenuExe.getRowCount()) {
			iSelectedRow = 0;
		}
		
		if (tblMenuExe.getRowCount() > 0) {
			SwingUtils.scrollRow(tblMenuExe, iSelectedRow);
			tblMenuExe.setRowSelectionInterval(iSelectedRow, iSelectedRow);
		}		
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
	 * 		<DD>下へ</DD>
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
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			btnF4.setEnabled(true);
			doF4();	
		}
	}
	
	
	/**Search UserGroup
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>最後</DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	protected void doF4() {
		SearchUserGroupFrame search = new SearchUserGroupFrame(getFrame());		
		search.pack();
		search.setModal(true);
		search.setVisible(true);
		SearchVo searchData = search.getReturnData();
		if (searchData != null) {
			txtUser.setText(searchData.getProPer1());
			try
			{
				//Load data to Txt
				userGrp = userGrpService.getUserGrp(txtUser.getText().trim());
				if (userGrp != null) {
					btnF4.setAffterDoDisable(true);
					isHenkouChuu = true;
					CUR_MODE = EditConstants.EDIT_MODE;
					setStatus(EditConstants.EDIT_MODE);
					pwdPassword.setText(userGrp.getPwd());
					txtInputText.setText(userGrp.getUserText());
					
					if (StringUtils.isEquals(userGrp.getExcelOut(), "1")) {
						chkExcelOut.setSelected(true);
						chkExcelOut.setText(NameConstants.getName("EXCELOUT" + "1"));		
					}

					if (StringUtils.isEquals(userGrp.getLogViewflg(), "1")) {
						chkShowLog.setSelected(true);
						chkShowLog.setText(NameConstants.getName("LOGVIEW" + "1"));
					}
					doSetTableData();
				}
				
			}
			catch (BizException ex)
			{}
		}
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
		try {
			UserGrpVo userGrpVo = convertText2Vo();
			if (grpPermissionService.isInUse(userGrpVo)) {
				btnF6.setAffterDoDisable(false);
				MessageBox.message(this, MessageConstants.getMstMsgVo("E0016"));
			} else {
				if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0003")) == MessageBox.YES) {
					btnF6.setAffterDoDisable(true);
					ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(),
							LogContants.LOGACT_DE, "ユーザーID:" + userGrpVo.getUserGrpId());
			    	grpPermissionService.delPermission(userGrpVo);
					PermissionService permission = new PermissionServiceImpl();
					PermissionPolicy.MAP_EXECTL1VO = permission.getMapExect1Vo();
					PermissionPolicy.MAP_EXECTL2VO = permission.getMapExect2Vo();

			    	CUR_MODE = EditConstants.VIEW_MODE;
			    	setStatus(CUR_MODE);
				} else {
					btnF6.setAffterDoDisable(false);
				}
			}
		} catch (BizException e) {
			logger.error(e.getMessage());
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
		}

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
			if (btnF8.isEnabled()) {
				doF8();	
			}
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
		if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0002")) == MessageBox.YES) {
			try {
				
		    	List<MenuExeVo> lstTblMenuExeVo = (ArrayList<MenuExeVo>) tblMenuExeDataModel.getData();
		    	
		    	for (MenuExeVo objVo : lstTblMenuExeVo) {
		    		for (int i = 0; i < lstMenuExeVo.size(); i++) {
		    			MenuExeVo menuExeVo = lstMenuExeVo.get(i);
		    			
		    			if (StringUtils.isEquals(objVo.getMenugrpCode(), menuExeVo.getMenugrpCode()) && 
		    					StringUtils.isEquals(objVo.getMenuexeCode(), menuExeVo.getMenuexeCode())) {
		    				lstMenuExeVo.set(i, objVo);
		    			}
		    		}
		    	}
		    	
		    	UserGrpVo userGrpVo = convertText2Vo();
		    	
		    	if (EditConstants.NEW_MODE.equals(CUR_MODE)) {
					ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(),
								LogContants.LOGACT_AD, "ユーザーID:" + userGrpVo.getUserGrpId());
		    		grpPermissionService.createPermission(lstMenuGrpVo, lstMenuExeVo, userGrpVo);
		    	} else {
					ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeMenu.getMenugrpCode(), exeMenu.getMenuexeCode(),
							LogContants.LOGACT_UP, "ユーザーID:" + userGrpVo.getUserGrpId());
		    		grpPermissionService.updatePermission(lstMenuGrpVo, lstMenuExeVo, userGrpVo);
		    	}
				
				PermissionService permission = new PermissionServiceImpl();
				PermissionPolicy.MAP_EXECTL1VO = permission.getMapExect1Vo();
				PermissionPolicy.MAP_EXECTL2VO = permission.getMapExect2Vo();
		    	
		    	CUR_MODE = EditConstants.VIEW_MODE;
		    	setStatus(CUR_MODE);
				
			} catch (BizException e) {
				logger.error(e.getMessage());
				MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			}
		} else {
			btnF8.setAffterDoDisable(false);
		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	private UserGrpVo convertText2Vo() {
		UserGrpVo userGrpVo = new UserGrpVo();
		userGrpVo.setUserGrpId(txtUser.getText().trim());
		userGrpVo.setPwd(pwdPassword.getText().trim());
		userGrpVo.setUserText(txtInputText.getText().trim());
		
		if (chkExcelOut.isSelected()) {
			userGrpVo.setExcelOut("1");
		} else {
			userGrpVo.setExcelOut("0");
		}
		
		if (chkShowLog.isSelected()) {
			userGrpVo.setLogViewflg("1");
		} else {
			userGrpVo.setLogViewflg("0");
		}
		return userGrpVo;
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
	protected void doF11() {
		if (isHenkouChuu) {
			if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) == MessageBox.YES) {
				isHenkouChuu = false;
				CUR_MODE = EditConstants.VIEW_MODE;
				setStatus(CUR_MODE);
			}
		}
	}

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
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void resetComponent() {
		txtUser.setText("");
		txtUser.setEnabled(true);
		pwdPassword.setText("");
		pwdPassword.setEnabled(false);
		txtInputText.setText("");
		txtInputText.setEnabled(false);
		chkExcelOut.setSelected(false);
		chkExcelOut.setText(NameConstants.getName("EXCELOUT" + "0"));		
		chkExcelOut.setEnabled(false);
		chkShowLog.setSelected(false);
		chkShowLog.setText(NameConstants.getName("LOGVIEW" + "0"));
		chkShowLog.setEnabled(false);
		lstMenuExeVo =  new ArrayList<MenuExeVo>();
		lstMenuGrpVo = new ArrayList<MenuGrpVo>();
		tblMenuExeDataModel.setData(lstMenuExeVo);
		tblMenuGrpDataModel.setData(lstMenuGrpVo);
		tblMenuExe.repaint();
		tblMenuGrp.repaint();
		txtUser.requestFocus();
		lblNumber.setText("0件");
	}
		
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void doFirst() {
		if (tblMenuGrp.isFocusOwner()) {
			if (tblMenuGrp.getRowCount() > 0) {
				int selectIndex = 0;
				tblMenuGrp.changeSelection(selectIndex, 0, false, false);
			}			
		} else {
			if (tblMenuExe.getRowCount() > 0) {
				int selectIndex = 0;
				tblMenuExe.changeSelection(selectIndex, 0, false, false);
			}				
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void doLast() {
		if (tblMenuGrp.isFocusOwner()) {
			if (tblMenuGrp.getRowCount() > 0) {
				int selectIndex = tblMenuGrp.getRowCount();
				tblMenuGrp.changeSelection(selectIndex - 1, 0, false, false);
			}			
		} else {
			if (tblMenuExe.getRowCount() > 0) {
				int selectIndex = tblMenuExe.getRowCount();
				tblMenuExe.changeSelection(selectIndex - 1, 0, false, false);
			}				
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void doNext() {
		if (tblMenuGrp.isFocusOwner()) {
			if (tblMenuGrp.getRowCount() > 0) {
				int selectIndex = tblMenuGrp.getSelectedRow();
				selectIndex = selectIndex + ROW_PER_PAGE;
				
				if (selectIndex > tblMenuGrp.getRowCount())	selectIndex = tblMenuGrp.getRowCount();
				
				tblMenuGrp.changeSelection(selectIndex - 1, 0, false, false);
			}			
		} else {
			if (tblMenuExe.getRowCount() > 0) {
				int selectIndex = tblMenuExe.getSelectedRow();
				selectIndex = selectIndex + ROW_PER_PAGE;
				
				if (selectIndex > tblMenuExe.getRowCount()) selectIndex = tblMenuExe.getRowCount();
				
				tblMenuExe.changeSelection(selectIndex - 1, 0, false, false);
			}				
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* </DL>
	*/
	private void doPre() {
		if (tblMenuGrp.isFocusOwner()) {
			if (tblMenuGrp.getRowCount() > 0) {
				int selectIndex = tblMenuGrp.getSelectedRow();
				selectIndex = selectIndex - ROW_PER_PAGE;
				
				if (selectIndex < 0) selectIndex = 0;
				
				tblMenuGrp.changeSelection(selectIndex, 0, false, false);
			}			
		} else {
			if (tblMenuExe.getRowCount() > 0) {
				int selectIndex = tblMenuExe.getSelectedRow();
				selectIndex = selectIndex - ROW_PER_PAGE;
				
				if (selectIndex < 0) selectIndex = 0;
				
				tblMenuExe.changeSelection(selectIndex, 0, false, false);
			}				
		}		
	}
	
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 	<DD></DD>
	 * <BR>
	 *   <DT>変更歴史：</DT>
	 * 	<DD>著作者: </DD><BR>
	 * 	<DD></DD>
	 * </DL>
	 */
	class InspectMenuExeTableModel extends DefaultTableModel {
		
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private String[] headerName;
	    /** */
	    private List data;
	    
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * </DL>
	    */
	    public InspectMenuExeTableModel(String[]  headerName){
	        if(headerName==null){
	            throw new IllegalArgumentException();
	        }
	        this.headerName=headerName;        
	    }
	    
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * @param data
	    * </DL>
	    */
	    public InspectMenuExeTableModel(String[] headerName,List data) {
	        if(headerName==null){
	            throw new IllegalArgumentException();
	        }
	        this.headerName=headerName;
	        this.data=data;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getRowCount()
	     */
	    public int getRowCount() {
	        if(data==null){
	            return 0;
	        }
	        return data.size();
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#setValueAt(java.lang.Object, int, int)
	     */
	    public void setValueAt(Object value,int row,int column){
	        data.set(row, value);
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getColumnCount()
	     */
	    public int getColumnCount() {
	        return headerName.length;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	     */
	    public String getColumnName(int column) {
	        return (String) headerName[column];
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	if (data != null && rowIndex < data.size()) {
		        MenuExeVo dataVo = (MenuExeVo) data.get(rowIndex);
		        if (dataVo != null) {
					    switch (columnIndex) {
				      	case 0: return dataVo.getMenuexeName();
				      	case 1: 				      	
					      	if ("0".equalsIgnoreCase(StringUtils.trimString(dataVo.getControlType()))) {
					      		return "○";
				      		} else if ("2".equalsIgnoreCase(StringUtils.trimString(dataVo.getControlType()))) {
				      			return "△";
				      		} else if ("5".equalsIgnoreCase(StringUtils.trimString(dataVo.getControlType()))) {
				      			return "□";
				      		} else {
				      			return "×";
				      		}
				      	default: return "";
				    }
		        } else {
		        	return "";
		        }
	    	} else {
	    		return "";
	    	}
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @return
	    * </DL>
	    */
	    public List getData() {
	        return data;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param data
	    * </DL>
	    */
	    public void setData(List data) {
	        this.data = data;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @return
	    * </DL>
	    */
	    public String[] getHeaderName() {
	        return headerName;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * </DL>
	    */
	    public void setHeaderName(String[] headerName) {
	        this.headerName = headerName;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	     */
	    public Class getColumnClass(int columnIndex) {
	    	return getValueAt(0,columnIndex).getClass();
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param iSelectedRow
	    * @return
	    * </DL>
	    */
	    public Object getSelectedObject(int iSelectedRow) {
	    	
	    	if (0 <= iSelectedRow && iSelectedRow < data.size()) {
	    		return data.get(iSelectedRow);
	    	}
	    	
	    	return null;
	    }	
	}
		
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 	<DD></DD>
	 * <BR>
	 *   <DT>変更歴史：</DT>
	 * 	<DD>著作者: </DD><BR>
	 * 	<DD></DD>
	 * </DL>
	 */
	class InspectMenuGrpTableModel extends DefaultTableModel {
		
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private String[] headerName;
	    /** */
	    private List data;
	    
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * </DL>
	    */
	    public InspectMenuGrpTableModel(String[]  headerName){
	        if(headerName==null){
	            throw new IllegalArgumentException();
	        }
	        this.headerName=headerName;        
	    }
	    
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * @param data
	    * </DL>
	    */
	    public InspectMenuGrpTableModel(String[] headerName, List data) {
	        if(headerName==null){
	            throw new IllegalArgumentException();
	        }
	        this.headerName=headerName;
	        this.data=data;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getRowCount()
	     */
	    public int getRowCount() {
	        if(data==null){
	            return 0;
	        }
	        return data.size();
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#setValueAt(java.lang.Object, int, int)
	     */
	    public void setValueAt(Object value,int row,int column){
	        data.set(row, value);
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getColumnCount()
	     */
	    public int getColumnCount() {
	        return headerName.length;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	     */
	    public String getColumnName(int column) {
	        return (String) headerName[column];
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	
	    	if (data != null && rowIndex < data.size()) {
		    	Object objVo = data.get(rowIndex);
		    	
		    	if (objVo instanceof MenuGrpVo) {
			        MenuGrpVo dataVo = (MenuGrpVo) objVo;
			        if (dataVo != null) {
						    switch (columnIndex) {
					      	case 0: return dataVo.getMenugrpName();
					      	case 1:
					      		if ("0".equalsIgnoreCase(StringUtils.trimString(dataVo.getControlType()))) {
						      		return "○";
					      		} else if ("5".equalsIgnoreCase(StringUtils.trimString(dataVo.getControlType()))) {
					      			return "□";
					      		} else {
					      			return "×";
					      		}
					      	default: return "";
					    }
			        } else {
			        	return "";
			        }
		    	} else {
		        	return StringUtils.objectToStr(objVo);
		    	}
	    	} else {
	    		return "";
	    	}
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @return
	    * </DL>
	    */
	    public List getData() {
	        return data;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param data
	    * </DL>
	    */
	    public void setData(List data) {
	        this.data = data;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @return
	    * </DL>
	    */
	    public String[] getHeaderName() {
	        return headerName;
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param headerName
	    * </DL>
	    */
	    public void setHeaderName(String[] headerName) {
	        this.headerName = headerName;
	    }
	    
	    /* (non-Javadoc)
	     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	     */
	    public Class getColumnClass(int columnIndex) {
	    	return getValueAt(0,columnIndex).getClass();
	    }
	    
	    /**
	    * <DL>
	    *   <DT>メソッド記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param iSelectedRow
	    * @return
	    * </DL>
	    */
	    public Object getSelectedObject(int iSelectedRow) {
	    	
	    	if (0 <= iSelectedRow && iSelectedRow < data.size()) {
	    		return data.get(iSelectedRow);
	    	}
	    	
	    	return null;
	    }	
	}
	
	/** Set control Type cho Table ben trai, danh cho cac MenuGrpVo 
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
    class LeftTableButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    	
	    /** */
		private static final long serialVersionUID = 1L;
		/** */
		JXTable table;
	    /** */
	    JButton renderButton;
	    /** */
	    JButton editButton;
	    /** */
	    String text;
	
	    /**
	    * <DL>
	    *   <DT>コンストラクター記述：</DT>
	    * 		<DD></DD>
	    * <BR>
	    * @param table
	    * @param column
	    * </DL>
	    */
	    public LeftTableButtonColumn(JXTable table, int column) {
	        super();
	        this.table = table;
	        renderButton = new JButton();
	        renderButton.setFont(FontConstants.BUTTON_TEXT_FONT);
	        editButton = new JButton();
	        editButton.setFont(FontConstants.BUTTON_TEXT_FONT);
	        editButton.setFocusPainted( false );
	        editButton.addActionListener( this );
	
	        TableColumnModel columnModel = table.getColumnModel();
	        columnModel.getColumn(column).setCellRenderer( this );
	        columnModel.getColumn(column).setCellEditor( this );
	    }
	
	    /* (non-Javadoc)
	     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	     */
	    public Component getTableCellRendererComponent(
	        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	        renderButton.setText( (value == null) ? "" : value.toString() );
	        
	        return renderButton;
	    }
	
	    /* (non-Javadoc)
	     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	     */
	    public Component getTableCellEditorComponent(
	        JTable table, Object value, boolean isSelected, int row, int column)
	    {
	        text = (value == null) ? "" : value.toString();
	        editButton.setText( text );
	        return editButton;
	    }
	
	    /* (non-Javadoc)
	     * @see javax.swing.CellEditor#getCellEditorValue()
	     */
	    public Object getCellEditorValue()
	    {
	    	return ((InspectMenuGrpTableModel) table.getModel()).getSelectedObject(table.getSelectedRow());
	    }
	
	    /* (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
	    public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	        
	        int iSelectedRow = table.getSelectedRow();
	        
	        if (iSelectedRow >= 0) {
	        	MenuGrpVo dataVo = (MenuGrpVo) ((InspectMenuGrpTableModel) table.getModel()).getSelectedObject(iSelectedRow);
	        	if (dataVo != null) {
			    	if ("○".equalsIgnoreCase(text)) {
			    		dataVo.setControlType("5");
			    		text = "□";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    	} else if ("□".equalsIgnoreCase(text)) {
			    		text = "×";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    		dataVo.setControlType("9");
			    	} else {
			    		text = "○";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    		dataVo.setControlType("0");
			    	}
	        	}
	        	((InspectMenuGrpTableModel) table.getModel()).getData().set(iSelectedRow, dataVo);
	        }
	    }
	}	
    
    /** Set control Type cho Table ben phai, danh cho cac MenuExeVo  
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
    class CenterTableButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    	
	    /** */
		private static final long serialVersionUID = 1L;
		/** */
		JXTable table;
	    /** */
	    JButton renderButton;
	    /** */
	    JButton editButton;
	    /** */
	    String text;
	
	    public CenterTableButtonColumn(JXTable table, int column) {
	        super();
	        this.table = table;
	        renderButton = new JButton();
	        renderButton.setFont(FontConstants.BUTTON_TEXT_FONT);
	        editButton = new JButton();
	        editButton.setFont(FontConstants.BUTTON_TEXT_FONT);
	        editButton.setFocusPainted( false );
	        editButton.addActionListener( this );
	
	        TableColumnModel columnModel = table.getColumnModel();
	        columnModel.getColumn(column).setCellRenderer( this );
	        columnModel.getColumn(column).setCellEditor( this );
	    }
	
	    /* (non-Javadoc)
	     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	     */
	    public Component getTableCellRendererComponent(
	        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	        renderButton.setText( (value == null) ? "" : value.toString() );
	        
	        return renderButton;
	    }
	
	    /* (non-Javadoc)
	     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	     */
	    public Component getTableCellEditorComponent(
	        JTable table, Object value, boolean isSelected, int row, int column)
	    {
	        text = (value == null) ? "" : value.toString();
	        editButton.setText( text );
	        return editButton;
	    }
	
	    /* (non-Javadoc)
	     * @see javax.swing.CellEditor#getCellEditorValue()
	     */
	    public Object getCellEditorValue() {
	    	return ((InspectMenuExeTableModel) table.getModel()).getSelectedObject(table.getSelectedRow());
	    }
	
	    /* (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
	    public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	        
	        int iSelectedRow = table.getSelectedRow();
	        
	        if (iSelectedRow >= 0) {
	        	MenuExeVo dataVo = (MenuExeVo) ((InspectMenuExeTableModel) table.getModel()).getSelectedObject(iSelectedRow);
	        	if (dataVo != null) {
			    	if ("○".equalsIgnoreCase(text)) {
			    		dataVo.setControlType("2");
			    		text = "△";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    	} else if ("△".equalsIgnoreCase(text)) {
			    		text = "□";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    		dataVo.setControlType("5");
			    	} else if ("□".equalsIgnoreCase(text)) {
			    		text = "×";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    		dataVo.setControlType("9");
			    	} else {
			    		text = "○";
			    		renderButton.setText(text);
			    		editButton.setText(text);
			    		dataVo.setControlType("0");
			    	}
			    	((InspectMenuExeTableModel) table.getModel()).getData().set(iSelectedRow, dataVo);
	        	}
	        }
	    }
	}
    
    /**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
    class MenuGrpRowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            doSelection();
        }
    }
    
    /**
    * <DL>
    *   <DT>メソッド記述：</DT>
    * 		<DD></DD>
    * <BR>
    * </DL>
    */
    private void doSelection() {    	
    	int iSelectedRow = tblMenuGrp.getSelectedRow();
    	List<MenuExeVo> lstTblMenuExeVo = (ArrayList<MenuExeVo>) tblMenuExeDataModel.getData();
    	
    	for (MenuExeVo objVo : lstTblMenuExeVo) {
    		for (int i = 0; i < lstMenuExeVo.size(); i++) {
    			MenuExeVo menuExeVo = lstMenuExeVo.get(i);
    			
    			if (StringUtils.isEquals(objVo.getMenugrpCode(), menuExeVo.getMenugrpCode()) && 
    					StringUtils.isEquals(objVo.getMenuexeCode(), menuExeVo.getMenuexeCode())) {
    				lstMenuExeVo.set(i, objVo);
    			}
    		}
    	}
    	
    	if (iSelectedRow >= 0) {
        	MenuGrpVo menuGrpVo = (MenuGrpVo) tblMenuGrpDataModel.getSelectedObject(iSelectedRow);
        	
        	if (menuGrpVo == null) {
        		tblMenuExeDataModel.setData(new ArrayList<MenuExeVo>());
        	} else {
        		lstTblMenuExeVo = new ArrayList<MenuExeVo>();
        		for (int i = 0; i < lstMenuExeVo.size(); i++) {
        			MenuExeVo menuExeVo = lstMenuExeVo.get(i);
        			if (StringUtils.isEquals(menuGrpVo.getMenugrpCode(), menuExeVo.getMenugrpCode())) {
        				lstTblMenuExeVo.add(menuExeVo);
        			}
        		}
        		tblMenuExeDataModel.setData(lstTblMenuExeVo);
        		tblMenuExe.repaint();
        		psCenterTable.getViewport().removeAll();
        		psCenterTable.getViewport().add(tblMenuExe);
        		
        		//Set Pointer to first row
        		if (tblMenuExe.getRowCount() > 0) {
        			tblMenuExe.clearSelection();
        			tblMenuExe.changeSelection(0, 0, false, true);
        		}
        	}
    	}
    }
    
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strInfor
	 */
	private void setHelpInfor(String strInfor) {
		lblHelpInfor.setText(strInfor);
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/	
	private void setDispTabFocus() {

		List<Object> focusList = new ArrayList<Object>();

		if (EditConstants.EDIT_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(chkShowLog);
			focusList.add(pwdPassword);
			focusList.add(chkExcelOut);
			focusList.add(txtInputText);
			focusList.add(tblMenuGrp);
			focusList.add(tblMenuExe);
		} else if (EditConstants.NEW_MODE.equalsIgnoreCase(CUR_MODE)) {
			focusList.add(chkShowLog);
			focusList.add(pwdPassword);
			focusList.add(chkExcelOut);
			focusList.add(txtInputText);
			focusList.add(tblMenuGrp);
			focusList.add(tblMenuExe);
		} else {
			focusList.add(txtUser);
		}

		setFocusTraversalPolicy(new FocusPolicy(focusList));
	}	

    /**
    * <DL>
    *   <DT>メソッド記述：</DT>
    * 		<DD></DD>
    * <BR>
    * @param com
    * </DL>
    */
    private void setFirstFocus(final JComponent com) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			if (com.isEnabled()) {
    				com.requestFocusInWindow();
    			}
    		}
    	});
    }
   
    /**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
    class  UppercaseDocumentFilter extends DocumentFilter {
    	
    	private int limit = 10;

        //
        // Override insertString method of DocumentFilter to make the text format
        // to uppercase.
        //
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                 String text, AttributeSet attr)
                throws BadLocationException {
 
        	if (offset < limit) {
        		fb.insertString(offset, text.toUpperCase(), attr);
        	}
        }
 
        //
        // Override replace method of DocumentFilter to make the text format
        // to uppercase.
        //
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                            String text, AttributeSet attrs)
                throws BadLocationException {
        	if (offset < limit) {
        		if (StringUtils.isAlphaBet(text)) {
                    fb.replace(offset, length, text.toUpperCase(), attrs);
        		}
        	}
        }
    }
    
	public void setExeMenu(MenuExeVo exeMenu) {
		super.setExeMenu(exeMenu);
		if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_ALL_PERMISSION, ApplicationConstants.getLoginUser().getUserUser(), exeMenu) == false) {
			// Not permission show red text and disable btn
			lblPermission.setText("更新不可");
			lblPermission.setBorder(null);
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
