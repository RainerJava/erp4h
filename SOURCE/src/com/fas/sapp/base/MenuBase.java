package com.fas.sapp.base;

import java.awt.AWTException;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.border.DropShadowBorder;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.FrameConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.constants.screen.ImageConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.ApplicationUtils;
import com.fas.common.utils.BrowserLaunch;
import com.fas.common.utils.ClassUtils;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.button.MenuButton;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectFrame;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.panel.BaseScrollPane;
import com.fas.jface.table.BTable;
import com.fas.jface.table.BTableModelData;
import com.fas.jface.table.ColumnData;
import com.fas.jface.table.GenericCellRenderer;
import com.fas.sapp.system.SystemConfigFrame;
import com.fas.sapp.system.mctluser.MCtlUserFrame;
import com.fas.sapp.system.menu.MyMenuFrame;
import com.fas.sapp.system.menu.MyOutConfigFrame;
import com.fas.service.system.flog.FLogService;
import com.fas.service.system.flog.FLogServiceImpl;
import com.fas.service.system.mctl.MCtlService;
import com.fas.service.system.mctl.MCtlServiceImpl;
import com.fas.service.system.menuexe.MenuExeService;
import com.fas.service.system.menuexe.MenuExeServiceImpl;
import com.fas.service.system.menugrp.MenuGrpService;
import com.fas.service.system.menugrp.MenuGrpServiceImpl;
import com.fas.service.system.mname.MNameService;
import com.fas.service.system.mname.MNameServiceImpl;
import com.fas.vo.flog.FLogVo;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.menugrp.MenuGrpVo;

public abstract class MenuBase extends InspectFrame {
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(MenuBase.class);
	/** */
	protected JDialog dlg;
	/** */
	private BaseLabel lblHelpInfor;
	/** */
	protected BasePanel centerSubPnl1;
	/** */
	protected BasePanel centerSubPnl2;
	/** */
	protected BasePanel centerSubPnl3;
	/** */
	private BaseLabel lblCenterPnl1;
	/** */
	private BaseLabel lblCenterPnl2;
	/** */
	private BaseLabel lblCenterPnl3;
	/** */
	private String STR_FUNCTION_NAME;
	/** */
	private LeftMenuButton currLeftButton;
	/** */
	private CenterMenuButton currCenterButton;
	/** */
	private LeftMenuButton btnTopLeft;
	/** */
	private CenterMenuButton btnCenter1;
	/** */
	private CenterMenuButton btnCenter2;
	/** */
	private CenterMenuButton btnCenter3;
	/** */
	private FLogService logService = new FLogServiceImpl();
	/** */
	private int LOG_PANEL_WIDTH = 500;
	/** */
	protected BTable m_table;
	/** */
	protected LogTableData m_data;
	/** */
	private BaseScrollPane logPnl;
	/** */
	private BaseLabel lblKaishaMei;
	/** */
	private BaseLabel lblLeftPnl;
	/** */
	final BaseFrame thisFrame = this.getFrame();
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
	public MenuBase(JFrame frame, String title) {
        super(title);
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
	public MenuBase() {
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
    	// set border
    	mainPnl.setBorder(new DropShadowBorder());
        headerMainPnl.setBorder(new DropShadowBorder());
        footerMainPnl.setBorder(new DropShadowBorder());
        
    	STR_FUNCTION_NAME = "";
    	setTitle(getSubName());
        setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
        
        JMenuBar menuBar = createMenuBar();
	  	setJMenuBar(menuBar);

        Set<AWTKeyStroke> forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> newForwardKeys = new HashSet<AWTKeyStroke>(forwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
        
        Set<AWTKeyStroke> backKeys = getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> newBackKeys = new HashSet<AWTKeyStroke>(backKeys);
        newBackKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newBackKeys);
        
        addWindowListener(new WindowAdapter() {
            /* (non-Javadoc)
             * @see java.awt.event.WindowAdapter#windowActivated(java.awt.event.WindowEvent)
             */
            public void windowActivated(WindowEvent e) {
            	setFocusableWindowState(true);
            }
            
            /* (non-Javadoc)
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            public void windowClosing(WindowEvent e) {
	    		if( FrameConstants.getMap().size()== 0){
	    			 quit();
	    		 }else{
	    			 Set set = FrameConstants.getMap().entrySet();
	    			 Iterator i = set.iterator();
	    			 String strMess = "";
	    			 int iCount = 0;
	    			 while(i.hasNext()){
	    			      Map.Entry me = (Map.Entry)i.next();
	    			      if(iCount == 0){
	    			    	  strMess = strMess +  me.getValue();
	    			      }else{
	    			    	  strMess = strMess + "、"+  me.getValue() ;
	    			      }
	    			      iCount ++;
	    			  }
	    			 MessageBox.message(thisFrame, MessageConstants.getMstMsgVo("W0195").setExtraInfor( strMess));
	    		 }
            }
        });
        // the SystemTray
        //createAndShowGUI();
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    private void quit() {
    	
    	boolean flg = true;
    	
    	if (getFrame() != null) {
    		if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0004")) == MessageBox.NO) {    			
    			flg = false;
    		}
    	}
    	if (flg) {
    		File f = new File("Config\\sysLog.txt");
    		if(f.exists()){
    			f.delete();
    		}    		
			ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, "", "", LogContants.LOGACT_CL, "");
            System.exit(0);
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
    	if (ApplicationConstants.getLoginUser() != null) {
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
    	if (ApplicationConstants.getLoginUser() != null) {
    		return ApplicationConstants.LOGIN_USER_ID;
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
    	mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
    	int xPos = 9;
    	int yPos = 4;
    	final int I_PNL_WIDTH = NumberUtils.getIntFromDouble((X_BODY_LENGTH - 44) / 4);
    	
    	BasePanel pnlLeft = new BasePanel();
    	pnlLeft.setLayout(null);
    	pnlLeft.setLocation(xPos, yPos);
    	pnlLeft.setSize(new Dimension(I_PNL_WIDTH, Y_BODY_LENGTH - 2 * yPos - 20));
    	
    	BasePanel leftSubPnl = new BasePanel();
    	leftSubPnl.setLocation(0, 33);
    	leftSubPnl.setSize(new Dimension(I_PNL_WIDTH, Y_BODY_LENGTH - 2 * yPos - 53));
    	leftSubPnl.setLayout(null);
    	leftSubPnl.setBorder(FaceContants.BEVEL_BORDER);
    	
    	lblLeftPnl = new BaseLabel("メインメニュー（Ｆ１）");
    	lblLeftPnl.setHorizontalAlignment(BaseLabel.CENTER);
    	lblLeftPnl.setBackground(ColorConstants.MENU_LBL_BACK_COLOR);
    	lblLeftPnl.setBorder(FaceContants.LABEL_BORDER);
    	lblLeftPnl.setFont(FontConstants.MENU_TITLE_FONT);
    	lblLeftPnl.setLocation(0, 1);
    	lblLeftPnl.setSize(I_PNL_WIDTH, 30);
    	initLeftMenu(leftSubPnl);
    	pnlLeft.add(lblLeftPnl);
    	pnlLeft.add(leftSubPnl);

    	BasePanel seperatorPnl = new BasePanel();
    	seperatorPnl.setLocation(xPos + I_PNL_WIDTH + 6, yPos);
    	seperatorPnl.setBorder(FaceContants.BEVEL_BORDER);
    	seperatorPnl.setSize(new Dimension(3, Y_BODY_LENGTH - 2 * yPos - 20));
    	mainPnl.add(seperatorPnl);
    	
    	
    	xPos = xPos + I_PNL_WIDTH + 14;
    	BasePanel pnlCenter = new BasePanel();
    	pnlCenter.setLayout(null);
    	pnlCenter.setLocation(xPos, yPos);
    	pnlCenter.setSize(new Dimension(I_PNL_WIDTH * 3 + 14, 390));
    	
    	centerSubPnl1 = new BasePanel();
    	centerSubPnl1.setLocation(0, 33);
    	centerSubPnl1.setSize(new Dimension(I_PNL_WIDTH, 350));
    	centerSubPnl1.setLayout(null);
    	
    	lblCenterPnl1 = new BaseLabel("");
    	lblCenterPnl1.setHorizontalAlignment(BaseLabel.CENTER);
    	lblCenterPnl1.setBackground(ColorConstants.MENU_LBL_BACK_COLOR);
    	lblCenterPnl1.setBorder(FaceContants.LABEL_BORDER);
    	lblCenterPnl1.setFont(FontConstants.MENU_TITLE_FONT);
    	lblCenterPnl1.setLocation(0, 1);
    	lblCenterPnl1.setSize(I_PNL_WIDTH, 30);
    	pnlCenter.add(lblCenterPnl1);
    	centerSubPnl1.setBorder(FaceContants.BEVEL_BORDER);
    	pnlCenter.add(centerSubPnl1);
    	
    	centerSubPnl2 = new BasePanel();
    	centerSubPnl2.setLocation(I_PNL_WIDTH + 7, 33);
    	centerSubPnl2.setSize(new Dimension(I_PNL_WIDTH, 350));
    	centerSubPnl2.setLayout(null);
    	
    	lblCenterPnl2 = new BaseLabel("");
    	lblCenterPnl2.setHorizontalAlignment(BaseLabel.CENTER);
    	lblCenterPnl2.setFont(FontConstants.MENU_TITLE_FONT);
    	lblCenterPnl2.setBackground(ColorConstants.MENU_LBL_BACK_COLOR);
    	lblCenterPnl2.setBorder(FaceContants.LABEL_BORDER);
    	lblCenterPnl2.setLocation(I_PNL_WIDTH + 7, 1);
    	lblCenterPnl2.setSize(I_PNL_WIDTH, 30);
    	centerSubPnl2.setBorder(FaceContants.BEVEL_BORDER);
    	pnlCenter.add(lblCenterPnl2);
    	pnlCenter.add(centerSubPnl2);
    	
    	centerSubPnl3 = new BasePanel();
    	centerSubPnl3.setLocation(2 * I_PNL_WIDTH + 14, 33);
    	centerSubPnl3.setSize(new Dimension(I_PNL_WIDTH, 350));
    	centerSubPnl3.setLayout(null);
    	
    	lblCenterPnl3 = new BaseLabel("");
    	lblCenterPnl3.setFont(FontConstants.MENU_TITLE_FONT);
    	lblCenterPnl3.setHorizontalAlignment(BaseLabel.CENTER);
    	lblCenterPnl3.setBackground(ColorConstants.MENU_LBL_BACK_COLOR);
    	lblCenterPnl3.setBorder(FaceContants.LABEL_BORDER);
    	lblCenterPnl3.setLocation(2 * I_PNL_WIDTH + 14, 1);
    	lblCenterPnl3.setSize(I_PNL_WIDTH, 30);
    	centerSubPnl3.setBorder(FaceContants.BEVEL_BORDER);
    	pnlCenter.add(lblCenterPnl3);
    	pnlCenter.add(centerSubPnl3);
    	
    	/**ログのパネル */
    	logPnl = new BaseScrollPane();
    	logPnl.setLocation(xPos, pnlCenter.getY() + pnlCenter.getHeight() - 4);
    	logPnl.setSize(new Dimension(3 * I_PNL_WIDTH + 14, Y_BODY_LENGTH - pnlCenter.getY() - 5 * yPos - pnlCenter.getHeight()));
    	logPnl.setBorder(FaceContants.BEVEL_BORDER);
    	LOG_PANEL_WIDTH = 3 * I_PNL_WIDTH + 14;
    	initTable();
	    logPnl.getViewport().add(m_table);
	    
    	mainPnl.add(pnlLeft);
    	mainPnl.add(pnlCenter);
    	mainPnl.add(logPnl);

    	initCenterMenu();
    	
    	return mainPnl;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    private void initTable() {
    	
	  	m_data = new LogTableData();
	  	m_table = new BTable();
	  	m_table.setModel(m_data);
	  	
		ColumnData mCols[] = m_data.getColumnData();
	  	
	    for (int k = 0; k < mCols.length; k++) {
	    	GenericCellRenderer renderer = new GenericCellRenderer();
	        renderer.setHorizontalAlignment(mCols[k].m_alignment);
	        TableColumn column = new TableColumn(k, mCols[k].m_width, renderer, null);
	        m_table.addColumn(column);  
	    }
    	
	    m_table.getTableHeader().addMouseListener(m_data.setMouseListener(m_table));
	    m_table.getTableHeader().setPreferredSize(new Dimension(m_table.getTableHeader().getWidth(), FaceContants.TABLE_HEADER_HEIGHT));
	  	m_table.setLocation(0, 0);
	  	m_table.setBorder(null);
	  	m_table.setSize(new Dimension(logPnl.getWidth(), logPnl.getHeight()));
	  	m_table.setShowGrid(false);
	  	m_table.setSurrendersFocusOnKeystroke(true);
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
	protected JMenuBar createMenuBar() {
		
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    
	    JMenu mFile = new JMenu("ファイル（Ｆ）");
	    mFile.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    mFile.setMnemonic('f');
	    
	    JMenuItem jmExit = new JMenuItem("終了（Ｑ）　                   ");
	    jmExit.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmExit.setMnemonic('q');
	    jmExit.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		if( FrameConstants.getMap().size() == 0){
	    			 quit();
	    		 }else{
	    			 Set set = FrameConstants.getMap().entrySet();
	    			 Iterator i = set.iterator();
	    			 String strMess = "";
	    			 int iCount = 0;
	    			 while(i.hasNext()){
	    			      Map.Entry me = (Map.Entry)i.next();
	    			      if(iCount == 0){
	    			    	  strMess = strMess +  me.getValue();
	    			      }else{
	    			    	  strMess = strMess + "、"+  me.getValue() ;
	    			      }
	    			      iCount ++;
	    			  }
	    			 MessageBox.message(thisFrame, MessageConstants.getMstMsgVo("W0195").setExtraInfor( strMess));
	    		 }
	    	 }
	    });
	    mFile.add(jmExit);

	    JMenuItem jmLogout = new JMenuItem("ログアウト（Ｘ）　                   ");
	    jmLogout.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmLogout.setMnemonic('x');
	    jmLogout.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 File f = new File("Config\\sysLog.txt");
 	    		if(f.exists()){
 	    			f.delete();
 	    		}    		
	    		 if( FrameConstants.getMap().size() == 0){
	    			 
	    			 ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, "", "", LogContants.LOGACT_LO, "");
		    		 ApplicationConstants.loginUser = null;
		    		 ApplicationConstants.LOGIN_USER_ID = "";
		    		 try {
			    		 MCtlService ctlService = new MCtlServiceImpl();
			    		 MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());
		    		 } catch (BizException ez) {
		    		 }
	    			 dispose();
	    			
		    		 requestLogin();
	    		 }else{
	    			 Set set = FrameConstants.getMap().entrySet();
	    			 Iterator i = set.iterator();
	    			 String strMess = "";
	    			 int iCount = 0;
	    			 while(i.hasNext()){
	    			      Map.Entry me = (Map.Entry)i.next();
	    			      if(iCount == 0){
	    			    	  strMess = strMess +  me.getValue();
	    			      }else{
	    			    	  strMess = strMess + "、"+  me.getValue() ;
	    			      }
	    			      iCount ++;
	    			  }
	    			 MessageBox.message(thisFrame, MessageConstants.getMstMsgVo("W0195").setExtraInfor( strMess));
	    		 }
	    	 }
	    });
	    mFile.add(jmLogout);
	    menuBar.add(mFile);
	    
	    JMenu mConifg = new JMenu("設定（Ｏ）");
	    mConifg.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    mConifg.setMnemonic('o');
	
	    JMenuItem myMenuConfig = new JMenuItem("マイメニュー設定　                   ");
	    myMenuConfig.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    myMenuConfig.setMnemonic('m');
	    myMenuConfig.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 MyMenuFrame myMenuFame = new MyMenuFrame(getFrame(), true);
	    		 myMenuFame.pack();
	    		 myMenuFame.setVisible(true);	    		 
	    		 if (currLeftButton != null && "000".equalsIgnoreCase(currLeftButton.getMenuGrp())) {
	    			 currLeftButton.doClick();
	    		 }
	    	 }
	    });
	    mConifg.add(myMenuConfig);
	    
	    JMenuItem privateConfig = new JMenuItem("個人設定　");
	    privateConfig.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    privateConfig.setMnemonic('k');
	    privateConfig.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 MenuExeVo exeVo = new MenuExeVo();
	    		 exeVo.setMenuexeCode("");
	    		 exeVo.setMenugrpCode("000");
	    		 exeVo.setMenuexeName("個人設定");
	    		 MCtlUserFrame userMaster = new MCtlUserFrame(getFrame(), "個人設定");
	    		 userMaster.setExeMenu(exeVo);
	    		 userMaster.pack();
	    		 userMaster.setVisible(true);
	    	 }
	    });
	    mConifg.add(privateConfig);
	    
	    JMenuItem systemConfig = new JMenuItem("システム設定　");
	    systemConfig.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    systemConfig.setMnemonic('s');
	    systemConfig.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 SystemConfigFrame systemConfig = new SystemConfigFrame(getFrame(), true);
	    		 systemConfig.pack();
	    		 systemConfig.setVisible(true);
	    		 if (systemConfig.isHenkouShita()) {
	    			 refreshMenu();
	    		 }
	    	 }
	    });
	    mConifg.add(systemConfig);

	    JMenuItem outConfig = new JMenuItem("出力先設定　");
	    outConfig.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    outConfig.setMnemonic('r');
	    outConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyOutConfigFrame myOutConfig = new MyOutConfigFrame(getFrame(), true);
				myOutConfig.pack();
				myOutConfig.setVisible(true);
				
			}
		});
	    mConifg.add(outConfig);

	    menuBar.add(mConifg);
	    
	    
	    JMenu mHelp = new JMenu("ヘルプ（Ｈ）");
	    mHelp.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    mHelp.setMnemonic('h');
	    
	    JMenuItem jmHelp = new JMenuItem("ヘルプ（Ｈ）　                   ");
	    jmHelp.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmHelp.setMnemonic('h');
	    mHelp.add(jmHelp);

//	    JMenuItem jmAbout = new JMenuItem("アバウト（Ａ）　                   ");
//	    jmAbout.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
//	    jmAbout.setMnemonic('a');
//	    mHelp.add(jmAbout);
  
	    menuBar.add(mHelp);
	    
	    
	    JMenu mUrl = new JMenu("ＵＲＬ（U）");
	    mUrl.setLocation(400, 0);
	    mUrl.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    mUrl.setMnemonic('u');
	    
	    JMenuItem jmGoogle = new JMenuItem("Google　                   ");
	    jmGoogle.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmGoogle.setMnemonic('g');
	    jmGoogle.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		BrowserLaunch.openURL("http://www.google.co.jp");
	    	}
	    });
	    mUrl.add(jmGoogle);

	    JMenuItem jmYahoo = new JMenuItem("Yahoo Japan　                   ");
	    jmYahoo.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmYahoo.setMnemonic('y');
	    jmYahoo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		BrowserLaunch.openURL("http://www.yahoo.co.jp");
	    	}
	    });
	    mUrl.add(jmYahoo);
	    
	    JMenuItem jmGMap = new JMenuItem("Google Map           ");
	    jmGMap.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmGMap.setMnemonic('m');
	    jmGMap.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		BrowserLaunch.openURL("http://maps.google.co.jp/");
	    	}
	    });
	    mUrl.add(jmGMap);
	    
	    JMenuItem jmTenki = new JMenuItem("天気予報	           ");
	    jmTenki.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
	    jmTenki.setMnemonic('t');
	    jmTenki.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		BrowserLaunch.openURL("http://www.tenki.jp/");
	    	}
	    });
	    mUrl.add(jmTenki);
	    
	    menuBar.add(mUrl);
	    
	    menuBar.setPreferredSize(new Dimension(X_BODY_LENGTH, 25));
	    return menuBar;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	private void refreshMenu() {
		lblKaishaMei.setText(MCtlConstants.getValueByCKey("CMP_NAME"));
		updateComponentTreeUI0(this);
		lblCenterPnl1.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
		lblCenterPnl2.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
		lblCenterPnl3.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
		lblLeftPnl.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
	}
	
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param c
     */
    private static void updateComponentTreeUI0(Component c) {
       
    	if ((c instanceof JLabel) ||(c instanceof JMenu) || (c instanceof JMenuItem) || (c instanceof JPanel) || (c instanceof JMenuBar)) {
            c.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
        }
    	
		if (c instanceof LeftMenuButton) {
			c.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
			c.setForeground(ColorConstants.MENU_BUTTON_FORE_COLOR);
		}
		
		if (c instanceof CenterMenuButton) {
			c.setBackground(ColorConstants.EXE_BUTTON_BACK_COLOR);
			c.setForeground(ColorConstants.EXE_BUTTON_FORE_COLOR);
		} 
		
        Component[] children = null;
        if (c instanceof JMenu) {
            children = ((JMenu)c).getMenuComponents();
        }
        else if (c instanceof Container) {
            children = ((Container)c).getComponents();
        }
        if (children != null) {
            for(int i = 0; i < children.length; i++) {
                updateComponentTreeUI0(children[i]);
            }
        }
    }
	
	/* (non-Javadoc)
	 * @see com.pipe.sapp.base.MenuBase#initLeftMenu(com.pipe.jface.BasePanel)
	 */
	protected void initLeftMenu(BasePanel pnlLeft) {
		
		int xPos = 5;
		int yPos = 8 - FaceContants.MENU_BUTTON_HEIGHT - FaceContants.BUTTON_HEIGHT_SPACE;
		final int MENU_BUTTON_WIDTH = NumberUtils.getIntFromDouble((X_BODY_LENGTH - 44) / 4) - 10;
		LeftButtonMouseAdapter btnMouseAdapter = new LeftButtonMouseAdapter();
		List<MenuGrpVo> lstGrpVo = null;
		
		try {
			MenuGrpService menuGrpService = new MenuGrpServiceImpl();
			lstGrpVo = menuGrpService.getLstMenuGrpVo();
		} catch (BizException e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
		}

		addKeyListener(new MainFrameKeyEvent());
		
		if (lstGrpVo != null) {
			for (MenuGrpVo obj : lstGrpVo) {
				yPos = yPos + FaceContants.MENU_BUTTON_HEIGHT + FaceContants.BUTTON_HEIGHT_SPACE;
				LeftMenuButton leftMenu = new LeftMenuButton(ImageConstants.BUTTON_ICON, obj.getMenugrpName());
				leftMenu.setSize(MENU_BUTTON_WIDTH, FaceContants.MENU_BUTTON_HEIGHT);
				leftMenu.setLocation(xPos, yPos);
				leftMenu.addMouseListener(btnMouseAdapter);
				leftMenu.setMenuGrp(obj.getMenugrpCode());
				leftMenu.addKeyListener(new MainFrameKeyEvent());
				leftMenu.addActionListener(new EnterKeyAction());
				if (PermissionPolicy.checkMnGrpPermission(ApplicationConstants.getLoginUser().getUserUser(), obj) &&
						PermissionPolicy.checkMnGrpPermission(PermissionPolicy.MNG_ALL_PERMISSION, getLoginUser().getUserUser(), obj)) {
					leftMenu.setEnabled(true);
				} else {
					leftMenu.setEnabled(false);
					if (!PermissionPolicy.checkMnGrpPermission(PermissionPolicy.MNG_VIEW_PERMISSION, getLoginUser().getUserUser(), obj) &&
							!"000".equals(obj.getMenugrpCode())) {
						leftMenu.setText("");
						leftMenu.setIcon(null);
					}
				}
				
				pnlLeft.add(leftMenu);
				
				if ("000".equals(obj.getMenugrpCode())) {
					leftMenu.setEnabled(true);
				}
				
				if (currLeftButton == null && leftMenu.isEnabled()) {
					currLeftButton = leftMenu;
					btnTopLeft = leftMenu;
					STR_FUNCTION_NAME = currLeftButton.getText();
					currLeftButton.setBackground(ColorConstants.MENU_BUTTON_FOCUS_BACK_COLOR);
				} 
			}
		} else {
			STR_FUNCTION_NAME = "";
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
	protected void initCenterMenu() {
		
		int xPos = 5;
		int yPos = 8 - FaceContants.MENU_BUTTON_HEIGHT - FaceContants.BUTTON_HEIGHT_SPACE;
		final int MENU_BUTTON_WIDTH = NumberUtils.getIntFromDouble((X_BODY_LENGTH - 44) / 4) - 10;
		List<MenuExeVo> lstVo = null;
		MenuExeService menuService = new MenuExeServiceImpl();
		CenterButtonMouseAdapter btnMouseAdapter = new CenterButtonMouseAdapter();
		
		try {
			if (currLeftButton != null) {
				lstVo = menuService.getLstMenuExeVo(currLeftButton.getMenuGrp());
			} else {
				lstVo = menuService.getLstMenuExeVo("000");
			}
		} catch (BizException e) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
		}
		
		currCenterButton = null;
		
		centerSubPnl1.removeAll();
		centerSubPnl2.removeAll();
		centerSubPnl3.removeAll();
		int iCount = 0;
		btnCenter1 = null;
		btnCenter2 = null;
		btnCenter3 = null;
		
		if (lstVo != null) {
    		
			lblCenterPnl1.setText(STR_FUNCTION_NAME + "（Ｆ２）");
    		
			for (MenuExeVo obj : lstVo) {

				yPos = yPos + FaceContants.MENU_BUTTON_HEIGHT + FaceContants.BUTTON_HEIGHT_SPACE;
				CenterMenuButton btnCenterMenu = new CenterMenuButton(obj.getMenuexeName());
				btnCenterMenu.setSize(MENU_BUTTON_WIDTH, FaceContants.MENU_BUTTON_HEIGHT);
				btnCenterMenu.setLocation(xPos, yPos);
				
				if (!StringUtils.isValid(obj.getMenuexeName())) {
					btnCenterMenu.setEnabled(false);
				} else {
					btnCenterMenu.setText("  " + (iCount + 1) + "." + btnCenterMenu.getText());
					btnCenterMenu.setMenuExeVo(obj);
					
					if (PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_VIEW_DATA_PERMISSION, getLoginUser().getUserUser(), obj)) {
						btnCenterMenu.setEnabled(true);
					} else {
						btnCenterMenu.setEnabled(false);
						if (!PermissionPolicy.checkMnExePermission(PermissionPolicy.MNEXE_VIEW_MN_PERMISSION, getLoginUser().getUserUser(), obj) &&
							!"000".equals(obj.getMenugrpCode())) {
							btnCenterMenu.setText("");
							btnCenterMenu.setIcon(null);
						}
					}
				}
				
				if ("000".equals(obj.getMenugrpCode()) && StringUtils.isValid(obj.getMenuexeName())) {
					btnCenterMenu.setEnabled(true);
				}

				btnCenterMenu.setHorizontalAlignment(SwingConstants.LEFT);
				btnCenterMenu.setBackground(ColorConstants.EXE_BUTTON_BACK_COLOR);
				btnCenterMenu.setForeground(ColorConstants.EXE_BUTTON_FORE_COLOR);
				btnCenterMenu.setBorder(FaceContants.MENU_BUTTON_BORDER);
				btnCenterMenu.addKeyListener(new MainFrameKeyEvent());
				btnCenterMenu.addActionListener(new EnterKeyAction());
				btnCenterMenu.addMouseListener(btnMouseAdapter);
				
				if (iCount < 10) {
					centerSubPnl1.add(btnCenterMenu);
					
					if (btnCenter1 == null && btnCenterMenu.isEnabled()) {
						btnCenter1 = btnCenterMenu;
					}
				} else if (iCount >= 10 && iCount < 20) {
					centerSubPnl2.add(btnCenterMenu);
					if (iCount == 10) {
						lblCenterPnl2.setText(STR_FUNCTION_NAME + "（Ｆ３）");
					}
					
					if (btnCenter2 == null && btnCenterMenu.isEnabled()) {
						btnCenter2 = btnCenterMenu;
					}
				} else {
					centerSubPnl3.add(btnCenterMenu);
					if (iCount == 20) {
						lblCenterPnl3.setText(STR_FUNCTION_NAME + "（Ｆ４）");
					}
					
					if (btnCenter3 == null && btnCenterMenu.isEnabled()) {
						btnCenter3 = btnCenterMenu;
					}					
				}
				
				iCount++;
				
				if (iCount == 10 || iCount == 20) {
					yPos = 8 - FaceContants.MENU_BUTTON_HEIGHT - FaceContants.BUTTON_HEIGHT_SPACE;
				}
				
				if (currCenterButton == null) {
					currCenterButton = btnCenterMenu;
				}
			}
		} else {
			lblCenterPnl1.setText("");
			lblCenterPnl2.setText("");
			lblCenterPnl3.setText("");
		}
		
		if (iCount <= 10) {
			lblCenterPnl2.setText("");
			lblCenterPnl3.setText("");
		} else if (iCount <= 20) {
			lblCenterPnl3.setText("");
		}
		
		centerSubPnl1.repaint();
		centerSubPnl2.repaint();
		centerSubPnl3.repaint();
	}
	
	/**
	 * @author Administrator
	 * @date 2011/09/09
	 * @description
	 */
	class LeftMenuButton extends MenuButton {

		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private String MENUGRP = "000";
		/** */
		private Color OLD_COLOR = ColorConstants.MENU_BUTTON_BACK_COLOR;
		
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public LeftMenuButton(){
		    super();
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
		public LeftMenuButton(Icon icon){
		    super(icon);
		    init();
		}
		
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param _name
		 */
		public LeftMenuButton(String _name){
		    super(_name);
		    init();
		}
		
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param icon
		 * @param _name
		 */
		public LeftMenuButton(Icon icon, String _name){
		    super(icon, _name);
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
			setForeground(ColorConstants.MENU_BUTTON_FORE_COLOR);
			setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
			setBorder(FaceContants.MENU_BUTTON_BORDER);
			setHorizontalAlignment(SwingConstants.LEFT);			
		}
		
		/* (non-Javadoc)
		 * @see com.pipe.jface.button.ActionButton#setEnabled(boolean)
		 */
		public void setEnabled(boolean b) {
			super.setEnabled(b);
			if (isEnabled()) {
				setForeground(ColorConstants.MENU_BUTTON_FORE_COLOR);
			}
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param menuGrp
		 */
		public void setMenuGrp(String menuGrp) {
			MENUGRP = menuGrp;
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
		public String getMenuGrp() {
			return MENUGRP;
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param color
		 */
		public void setOldColor(Color color) {
			OLD_COLOR = color;
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
		public Color getOldColor() {
			return OLD_COLOR;
		}
	}
	
	/**
	 * @author PC13
	 *
	 */
	class CenterMenuButton extends MenuButton {

		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private String MENUGRP = "000";
		/** */
		private String MENUEXE = "";
		/** */
		private Color OLD_COLOR = ColorConstants.EXE_BUTTON_BACK_COLOR;
		/** */
		private MenuExeVo menuExeVo = null;
		
		// co dung de bat 1 window khi lien tuc click, hoac press space, enter vao menu-item 
		private boolean actionPerformable = true;
		
		public boolean isActionPerformable() {
			return actionPerformable;
		}

		public void setActionPerformable(boolean actionPerformable) {
			this.actionPerformable = actionPerformable;
		}

		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public CenterMenuButton(){
		    super();
		    init();
		}
		
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param _name
		 */
		public CenterMenuButton(String _name){
		    super(_name);
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
			setForeground(ColorConstants.EXE_BUTTON_FORE_COLOR);
		}
		
		/* (non-Javadoc)
		 * @see com.pipe.jface.button.ActionButton#setEnabled(boolean)
		 */
		public void setEnabled(boolean b) {
			super.setEnabled(b);
			if (isEnabled()) {
				setForeground(ColorConstants.EXE_BUTTON_FORE_COLOR);
			}
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param menuGrp
		 */
		public void setMenuGrp(String menuGrp) {
			MENUGRP = menuGrp;
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
		public String getMenuGrp() {
			return MENUGRP;
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param menuGrp
		 */
		public void setMenuExe(String menuExe) {
			MENUEXE = menuExe;
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
		public String getMenuExe() {
			return MENUEXE;
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param color
		 */
		public void setOldColor(Color color) {
			OLD_COLOR = color;
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
		public Color getOldColor() {
			return OLD_COLOR;
		}
		
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param exeVo
		 */
		public void setMenuExeVo(MenuExeVo exeVo) {
			menuExeVo = exeVo;
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
		public MenuExeVo getMenuExeVo() {
			return menuExeVo;
		}
	}
	
	/**
	 * @author PC13
	 *
	 */
	class LeftButtonMouseAdapter extends MouseAdapter {

	    /* (non-Javadoc)
	     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
	     */
	    public void mouseEntered(MouseEvent e) {

	    	if (e.getSource() instanceof LeftMenuButton) {
	    	
	    		LeftMenuButton btnLeft = (LeftMenuButton) e.getSource();
		    	
		    	if (btnLeft != null && btnLeft.isEnabled()) {
		    		btnLeft.setOldColor(btnLeft.getBackground());
		    		btnLeft.setBackground(ColorConstants.MENU_BUTTON_OVER_BACK_COLOR);
		    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    	}
	    	}
	    	
	    }

	    /* (non-Javadoc)
	     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	     */
	    public void mouseExited(MouseEvent e) {
	    	if (e.getSource() instanceof LeftMenuButton) {
		    	LeftMenuButton btnLeft = (LeftMenuButton) e.getSource();
		    	
		    	if (btnLeft != null && btnLeft.isEnabled()) {
		    		btnLeft.setBackground(btnLeft.getOldColor());
		    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    	}
	    	}
	    }
	    
	    /* (non-Javadoc)
	     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	     */
	    public void mouseClicked(MouseEvent e) {

	    	if (e.getSource() instanceof LeftMenuButton) {
		    	LeftMenuButton btnLeft = (LeftMenuButton) e.getSource();
		    	if (btnLeft != null && btnLeft.isEnabled()) {
		    		if (currLeftButton != null) {
		    			currLeftButton.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
		    		}
		    		currLeftButton = btnLeft;
		    		btnLeft.setBackground(ColorConstants.MENU_BUTTON_FOCUS_BACK_COLOR);
		    		btnLeft.setOldColor(btnLeft.getBackground());
		    		STR_FUNCTION_NAME = btnLeft.getText();
		    		initCenterMenu();
		    	}
	    	}
	    }
	}
	
	/**
	 * @author PC13
	 *
	 */
	class CenterButtonMouseAdapter extends MouseAdapter {

	    /* (non-Javadoc)
	     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
	     */
	    public void mouseEntered(MouseEvent e) {

	    	if (e.getSource() instanceof CenterMenuButton) {
	    	
	    		CenterMenuButton btnCenter = (CenterMenuButton) e.getSource();
		    	
		    	if (btnCenter != null && btnCenter.isEnabled()) {
		    		btnCenter.setOldColor(btnCenter.getBackground());
		    		btnCenter.setBackground(ColorConstants.EXE_BUTTON_OVER_BACK_COLOR);
		    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    	}
	    	}
	    	
	    }

	    /* (non-Javadoc)
	     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	     */
	    public void mouseExited(MouseEvent e) {
	    	if (e.getSource() instanceof CenterMenuButton) {
	    		CenterMenuButton btnCenter = (CenterMenuButton) e.getSource();
		    	
		    	if (btnCenter != null && btnCenter.isEnabled()) {
		    		btnCenter.setBackground(btnCenter.getOldColor());
		    		getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
     * @param com
     */
    protected void setDefaultFirstFocus(final JComponent com) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			com.requestFocus();
    		}
    	});
    }
    
    /* (non-Javadoc)
     * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
     */
    protected void setFrameSize() {
    	
    	logService = new FLogServiceImpl();
    	
    	X_FRAME_LENGTH = NumberUtils.getIntFromDouble(Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - 2;
    	Y_FRAME_LENGTH = NumberUtils.getIntFromDouble(Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 60;
    	X_BODY_LENGTH = X_FRAME_LENGTH;
    	Y_BODY_LENGTH = Y_FRAME_LENGTH - 60;
    	X_HEADER_LENGTH = X_FRAME_LENGTH;
    	Y_HEADER_LENGTH = 30;
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
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		lblDate.setText(getSystemDateTime());
            		lblDate.repaint();
            	}
        	});
        timer.start();
		statusBar.add(lblDate);
		
        Timer timerLog = new Timer(90000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		m_data.doRefreshTable();
            	}
        	});
        timerLog.start();
		
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
		headerMainPnl.setPreferredSize(new Dimension(X_HEADER_LENGTH, Y_HEADER_LENGTH));
        
		BasePanel headerSubPnl = new BasePanel();
		headerSubPnl.setSize(X_HEADER_LENGTH - 2, Y_HEADER_LENGTH - 2);
		headerSubPnl.setBorder(FaceContants.BEVEL_BORDER);
		headerSubPnl.setLayout(null);
		headerSubPnl.setLocation(1, 0);
		
		final int I_LBL_HEIGHT = 23;
		final int I_LBL_LENGTH = NumberUtils.getIntFromDouble((X_HEADER_LENGTH - 24) / 3);
		int xPos = 5;
		int yPos = NumberUtils.getIntFromDouble((Y_HEADER_LENGTH - I_LBL_HEIGHT - 2) / 2) + 1;

		lblKaishaMei = new BaseLabel(MCtlConstants.getValueByCKey("CMP_NAME"));
		lblKaishaMei.setLocation(xPos, yPos);
		lblKaishaMei.setSize(I_LBL_LENGTH, I_LBL_HEIGHT);
		lblKaishaMei.setBorder(FaceContants.BEVEL_BORDER);
		headerSubPnl.add(lblKaishaMei);
		
		xPos = xPos + I_LBL_LENGTH + 10;
		BaseLabel lblLoginMei = new BaseLabel("LoginName: " + getLoginUserFullName());
		lblLoginMei.setLocation(xPos, yPos);
		lblLoginMei.setSize(I_LBL_LENGTH, I_LBL_HEIGHT);
		lblLoginMei.setBorder(FaceContants.BEVEL_BORDER);
		headerSubPnl.add(lblLoginMei);
		
		xPos = xPos + I_LBL_LENGTH + 2;
		BaseLabel lblLoginDate = new BaseLabel("LoginDate: " + getLoginTime());
		lblLoginDate.setLocation(xPos, yPos);
		lblLoginDate.setSize(I_LBL_LENGTH, I_LBL_HEIGHT);
		lblLoginDate.setBorder(FaceContants.BEVEL_BORDER);
		headerSubPnl.add(lblLoginDate);
		
    	headerMainPnl.add(headerSubPnl);

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
	protected abstract String getLoginTime();
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	protected abstract String getLoginUserFullName();
	
	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}
	
	/**
	 * @author PC13
	 *
	 */
	class ExistAction extends AbstractAction {
		
		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param name
		 */
		public ExistAction(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			
			quit();
		}
	}
	
	/**
	 * @author PC13
	 *
	 */
	class EnterKeyAction implements ActionListener {
		
		/** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param name
		 */
		public EnterKeyAction() {
		}
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() instanceof LeftMenuButton) {
				LeftMenuButton btnLeft = (LeftMenuButton) e.getSource();
				
		    	if (btnLeft != null && btnLeft.isEnabled()) {
		    		requestFocus(btnLeft);
		    		if (currLeftButton != null) {
		    			currLeftButton.setBackground(ColorConstants.MENU_BUTTON_BACK_COLOR);
		    		}
		    		currLeftButton = btnLeft;
		    		btnLeft.setBackground(ColorConstants.MENU_BUTTON_FOCUS_BACK_COLOR);
		    		btnLeft.setOldColor(btnLeft.getBackground());
		    		STR_FUNCTION_NAME = btnLeft.getText();
		    		initCenterMenu();
		    	}
			}

			if (e.getSource() instanceof CenterMenuButton) {
				CenterMenuButton btnCenter = (CenterMenuButton) e.getSource();
				if (btnCenter != null && btnCenter.isEnabled() && btnCenter.isActionPerformable()) {
					MenuExeVo exeVo = btnCenter.getMenuExeVo();
					if (currCenterButton != null) {
						currCenterButton.setBackground(ColorConstants.EXE_BUTTON_BACK_COLOR);
					}
					currCenterButton = btnCenter;
					btnCenter.setBackground(ColorConstants.EXE_BUTTON_FOCUS_BACK_COLOR);
					btnCenter.setActionPerformable(false);
					doCenterMouseClick(exeVo);
				}
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
	protected void doCenterMouseClick(final MenuExeVo exeVo) {
		try {
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						SplashScreen sp = new SplashScreen();
						sp.setVisible(true);
						sp.setAlwaysOnTop(true);
						//Thread.sleep(100);
						final MNameService nameService = new MNameServiceImpl();
						final MCtlService ctlService = new MCtlServiceImpl();
						if (exeVo != null) {
							System.out.println("Menu Group: " + exeVo.getMenugrpCode() + "Menu Exe:" + exeVo.getMenuexeCode());
							
							if (StringUtils.isValid(exeVo.getPathName())) {
								if ("0".equalsIgnoreCase(exeVo.getFunType())) {
						    		Class<?>[] classParm = {BaseFrame.class};
						    	    Object[] objectParm = {getFrame()};
						    	    final Frame frame = isNotInUse(exeVo);
						    	    if (frame == null) {
						    	    	final BaseFrame iFrame = (BaseFrame) ClassUtils.getInstanceOfClass(exeVo.getPathName().trim(), classParm, objectParm);
										if (iFrame != null) {
											iFrame.setParentFrame(getFrame());
											iFrame.setTitle(StringUtils.emptyIfNull(exeVo.getMenuexeName()));
											iFrame.setExeMenu(exeVo);
											iFrame.pack();
											ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, exeVo.getMenugrpCode(), exeVo.getMenuexeCode(), LogContants.LOGACT_OP, "");
											SwingUtilities.invokeLater(new Runnable() {
												public void run() {
													setFocusableWindowState(false);
													iFrame.setVisible(true);
//													if (!(iFrame instanceof PipeSearchFrame) && !(iFrame instanceof MaakinFrame) && !(iFrame instanceof FPipeCLFrame) && !(iFrame instanceof TrPipesFrame)&&
//															!(iFrame instanceof MSPProdFrame)&&!(iFrame instanceof CustFrame)&&!(iFrame instanceof ShiireSakiFrame)&&!(iFrame instanceof MProdFrame)&&
//															!(iFrame instanceof CustPclsnmFrame)&&!(iFrame instanceof MIncrFrame)&&!(iFrame instanceof CompTypeUpdateFrame)) {
//														iFrame.setExtendedState(JFrame.NORMAL);
//													}
													FrameConstants.addNameFrame(exeVo.getMenugrpCode()+ exeVo.getMenuexeCode(), StringUtils.emptyIfNull(exeVo.getMenuexeName()));
													try {
														// Reload data Control for system
														MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());
														// Reload data Name for system
														NameConstants.setMapNameVo(nameService.getMapNameVo());
													} catch (BizException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													try {
														Thread.sleep(10);
													} catch (InterruptedException e) {
														
													}
												}
											});
											setFocusableWindowState(true);
										}
									} else {
										SwingUtilities.invokeLater(new Runnable() {
											public void run() {
												setState(Frame.ICONIFIED );
												frame.setVisible(true);
												frame.toFront();
												frame.setExtendedState(JFrame.NORMAL);
												try {
													Thread.sleep(10);
												} catch (InterruptedException e) {
													
												}
											}
										});
									}
								} else {
									FileUtils.runCmdAndWait(exeVo.getPathName());
								}
							}
						}
						
						currCenterButton.setActionPerformable(true);
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
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param jFrame
	* @return
	* </DL>
	*/
	protected  void createAndShowGUI() {
	        //Check the SystemTray support
	        if (!SystemTray.isSupported()) {
	            System.out.println("SystemTray is not supported");
	            return;
	        }
	        Image image = Toolkit.getDefaultToolkit().getImage("\\iconpipe.jpg");        
	        final PopupMenu popup = new PopupMenu();
	        final TrayIcon trayIcon =new TrayIcon(image, "管材集計システム");
	        final SystemTray tray = SystemTray.getSystemTray();
	        
	        // Create a popup menu components
//	        MenuItem aboutItem = new MenuItem("アバウト");
	        MenuItem shuItem = new MenuItem("終了");
	        MenuItem logoutItem = new MenuItem("ログアウト");
	        Menu displayMenu = new Menu("URL");
	        MenuItem googleItem = new MenuItem("Google");
	        MenuItem yahooItem = new MenuItem("Yahoo Japan");
	        MenuItem googleMapItem = new MenuItem("Google Map");
	        MenuItem tenkiItem = new MenuItem("天気情報");
	        MenuItem exitItem = new MenuItem("Exit");
	        
	        //Add components to popup menu
//	        popup.add(aboutItem);
//	        popup.addSeparator();
	        popup.add(shuItem);
	        popup.add(logoutItem);
	        popup.addSeparator();
	        popup.add(displayMenu);
	        displayMenu.add(googleItem);
	        displayMenu.add(yahooItem);
	        displayMenu.add(googleMapItem);
	        displayMenu.add(tenkiItem);
	        popup.add(exitItem);
	        
	        trayIcon.setPopupMenu(popup);
	        
	        try {
	            tray.add(trayIcon);
	        } catch (AWTException e) {
	            System.out.println("TrayIcon could not be added.");
	            return;
	        }
	        
	        trayIcon.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null,
	                        "This dialog box is run from System Tray");
	            }
	        });
	        
//	        aboutItem.addActionListener(new ActionListener() {
//	            public void actionPerformed(ActionEvent e) {
//	               // JOptionPane.showMessageDialog(null,
//	               //         "This dialog box is run from the About menu item");
//	            }
//	        });
	        
	        shuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if( FrameConstants.getMap().size() == 0){
		    			 quit();
		    		 }else{
		    			 Set set = FrameConstants.getMap().entrySet();
		    			 Iterator i = set.iterator();
		    			 String strMess = "";
		    			 int iCount = 0;
		    			 while(i.hasNext()){
		    			      Map.Entry me = (Map.Entry)i.next();
		    			      if(iCount == 0){
		    			    	  strMess = strMess +  me.getValue();
		    			      }else{
		    			    	  strMess = strMess + "、"+  me.getValue() ;
		    			      }
		    			      iCount ++;
		    			  }
		    			 MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("W0195").setExtraInfor( strMess));
		    		 }
	            }
	        });
	        
	        logoutItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 File f = new File("Config\\sysLog.txt");
	  	    		if(f.exists()){
	  	    			f.delete();
	  	    		}    		
	 	    		 if( FrameConstants.getMap().size() == 0){
	 	    			 
	 	    			 ApplicationUtils.logData(ApplicationConstants.LOGIN_USER_ID, "", "", LogContants.LOGACT_LO, "");
	 		    		 ApplicationConstants.loginUser = null;
	 		    		 ApplicationConstants.LOGIN_USER_ID = "";
	 		    		 try {
	 			    		 MCtlService ctlService = new MCtlServiceImpl();
	 			    		 MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());
	 		    		 } catch (BizException ez) {
	 		    		 }
	 		    		thisFrame.dispose();
	 	    			
	 		    		 requestLogin();
	 	    		 }else{
	 	    			 Set set = FrameConstants.getMap().entrySet();
	 	    			 Iterator i = set.iterator();
	 	    			 String strMess = "";
	 	    			 int iCount = 0;
	 	    			 while(i.hasNext()){
	 	    			      Map.Entry me = (Map.Entry)i.next();
	 	    			      if(iCount == 0){
	 	    			    	  strMess = strMess +  me.getValue();
	 	    			      }else{
	 	    			    	  strMess = strMess + "、"+  me.getValue() ;
	 	    			      }
	 	    			      iCount ++;
	 	    			  }
	 	    			 MessageBox.message(thisFrame, MessageConstants.getMstMsgVo("W0195").setExtraInfor( strMess));
	 	    		 }
	            }
	        });
	       
	        
	        ActionListener listener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                MenuItem item = (MenuItem)e.getSource();
	                //TrayIcon.MessageType type = null;
	                System.out.println(item.getLabel());
	                if ("Google".equals(item.getLabel())) {
	                	BrowserLaunch.openURL("http://www.google.co.jp");
	                    
	                } else if ("Yahoo Japan".equals(item.getLabel())) {
	                    //type = TrayIcon.MessageType.WARNING;
	                	BrowserLaunch.openURL("http://www.yahoo.co.jp");
	                    
	                } else if ("Google Map".equals(item.getLabel())) {
	                    //type = TrayIcon.MessageType.INFO;
	                	BrowserLaunch.openURL("http://maps.google.co.jp/");
	                    
	                } else if ("天気情報".equals(item.getLabel())) {
	                    //type = TrayIcon.MessageType.NONE;
	                	BrowserLaunch.openURL("http://www.tenki.jp/");
	                }
	            }
	        };
	        
	        googleItem.addActionListener(listener);
	        yahooItem.addActionListener(listener);
	        googleMapItem.addActionListener(listener);
	        tenkiItem.addActionListener(listener);
	        
	        exitItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                tray.remove(trayIcon);
	                //System.exit(0);
	            }
	        });
	    }
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param jFrame
	* @return
	* </DL>
	*/
	private Frame isNotInUse(MenuExeVo exeVo) {
		
		Frame frameReturn = null;
		Runtime r = Runtime.getRuntime();
		r.gc();
		String strNameFrame = FrameConstants.getNameFrame((exeVo.getMenugrpCode()+ exeVo.getMenuexeCode()));// many interface use same Class
		if (exeVo != null) {
			for (Frame frame : Frame.getFrames()) {
				if (StringUtils.isEquals(StringUtils.trimAll(exeVo.getPathName()), frame.getClass().getName()) &&(strNameFrame != null)){
					if (frame.isShowing()) {
						frameReturn = frame;
						break;
					}
				}
			}
		}
		return frameReturn;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param comp
	 */
	private void requestFocus(final JComponent comp) {
		if (comp != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					comp.requestFocus();
				}
			});
		}
	}
	
	/**
	 * @author PC13
	 *
	 */
	class MainFrameKeyEvent extends KeyAdapter {
		
	    /* (non-Javadoc)
	     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	     */
	    public void keyPressed(KeyEvent e) {
	    	
	    	if (e.getKeyCode() == KeyEvent.VK_F1) {
	    		requestFocus(btnTopLeft);
	    	}
	    	
	    	if (e.getKeyCode() == KeyEvent.VK_F2) {
	    		requestFocus(btnCenter1);
	    	}
	    	
	    	if (e.getKeyCode() == KeyEvent.VK_F3) {
	    		requestFocus(btnCenter2);
	    	}
	    	
	    	if (e.getKeyCode() == KeyEvent.VK_F4) {
	    		requestFocus(btnCenter3);
	    	}
	    	
	    	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    		
	    	}

	    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    		
	    	}
	    }
	}

	/**
	 * @author PC13
	 *
	 */
	class LogTableData extends BTableModelData {
		
		/** */
		private static final long serialVersionUID = 1L;
	
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public LogTableData() {
		    mLstData = new ArrayList<FLogVo>();
		    setDefaultData();
		}
	
		/**
		 * <DL>
		 *   <DT>メソッド記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public void setDefaultData() {
			m_columns = new ColumnData[] {
					new ColumnData( "時刻", 60, JLabel.LEFT, false ),
					new ColumnData( "担当者", 80, JLabel.LEFT, false ),
					new ColumnData( "機能", 120, JLabel.CENTER, false ),
					new ColumnData( "Action", 50, JLabel.LEFT, false ),
					new ColumnData( "Memo", LOG_PANEL_WIDTH - 310, JLabel.LEFT, false )
			};
			retrieveData();
	  	}
	
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int nRow, int nCol) {
		   
			if (nRow < 0 || nRow>=getRowCount())
		    	return "";
		    
		    FLogVo row = (FLogVo) mLstData.get(nRow);
		    
		    switch (nCol) {
		      	case 0: return DateUtils.getTimeWithSplit(row.getActTime());
		      	case 1: return row.getUserUser();
		      	case 2: return row.getMenuexeCode();
		      	case 3: return row.getActionType();
		      	case 4: return row.getText();
		    }
		
		    return "";
		}

		/* (non-Javadoc)
		 * @see com.pipe.jface.table.BTableModelData#retrieveData()
		 */
		public int retrieveData() {
			
			try {
				
				List<FLogVo> lstData = null;
				lstData = logService.getLstLogInfor(sortObj);
				
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
	    	ApplicationUtils.doRefreshTable(m_table, m_data);	
		}
	}
}
