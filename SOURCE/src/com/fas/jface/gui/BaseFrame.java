/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseFrame.java
*
*     記述				：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.SocketException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXFrame;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.FrameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.utils.DateUtils;
import com.fas.jface.BaseDataManager;
import com.fas.jface.MessageBox;
import com.fas.sapp.login.LoginFrame;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class BaseFrame extends JXFrame {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	Logger logger = Logger.getLogger(this.getClass());
	/** */
	private static Map session = new HashMap();
    /** 変更フラグ */
    private boolean changeFlg;
    /** */
    private boolean cached = false;    
	/** */
	protected BaseFrame parentFrame = null;
	/** */
	protected BaseFrame childFrame = null;
	/** */
	protected MenuExeVo exeMenu;
	
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public BaseFrame(BaseFrame baseFrame) {
        super();
        parentFrame = baseFrame;
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
    public BaseFrame(){
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
     * @return
     */
    public boolean isCached(){
    	return cached;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param cached
     */
    public void setCached(boolean cached){
    	this.cached = cached;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    @SuppressWarnings("unchecked")
	private void init(){
        setBackground(ColorConstants.DEFAULT_BACK_COLOR);
        getContentPane().setBackground(ColorConstants.DEFAULT_BACK_COLOR);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("\\iconpipe.jpg"));
        // set press key
		Set forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
		Set newForwardKeys = new HashSet(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke("shift ENTER"));
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newForwardKeys);
    }
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD> ログイン情報を取得する。</DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public LoginUser getLoginUser(){
    	LoginUser user = ApplicationConstants.getLoginUser();
        return user;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param user
     */
    public void setLoginUser(LoginUser user){
        
    	if(user==null){
            throw new IllegalArgumentException();
        }
        
        BaseDataManager.setLoginUser(user);
    }
	    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param frame
     * @param t
     */
    public void systemError(JFrame frame, Throwable t) {
    	
    	t.printStackTrace();
    	logger.error( "Exception caught", t );
    	Throwable t1=t.getCause();

	    if(t1 instanceof SocketException){
    		MessageBox.systemError("", frame);
    	} else{        	
    		String errMessage=t.getMessage();
    		if(errMessage!=null && "(500)Internal Server Error".equals(errMessage)){
        		MessageBox.systemError("", frame);        			
    		}else{
        		MessageBox.systemError(frame);    		
    		}
    	}	    	
    }
	    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>指定のコンポーネントにListenerをセット</DD>
     * <BR>
     *
     * </DL>
     * @param com
     */
    public void setChangeListener(Component com){
    	// ｺﾝﾎﾟｰﾈﾝﾄ判定
    	if(com instanceof JTextField){
    		// テキスト
			JTextField field = (JTextField)com;
			field.getDocument().addDocumentListener(new setDocumentListener());
		} else if(com instanceof JCheckBox) {
			// チェックボックス
			JCheckBox chk = (JCheckBox)com;
			chk.addChangeListener(new setChangeListener());
		} else if(com instanceof JRadioButton) {
			// ラジオボタン
			JRadioButton radio = (JRadioButton)com;
			radio.addChangeListener(new setChangeListener());
		} else if(com instanceof JButton) {
			// ボタン
			JButton btn = (JButton)com;
			btn.addChangeListener(new setChangeListener());
		} else if(com instanceof JComboBox) {
			// コンボボックス
			JComboBox cbb = (JComboBox)com;
			cbb.addItemListener(new setItemListener());
		} else if(com instanceof JList) {
			// リスト
			JList list = (JList)com;
			list.addListSelectionListener(new setListSelectionListener());
		} else if(com instanceof JTextArea) {
			JTextArea area = (JTextArea)com;
			area.getDocument().addDocumentListener(new setDocumentListener());
		}
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param comAry
     */
    public void setChangeListener(Component[] comAry){
    	// 要素分ループ
    	for ( int i=0; i<comAry.length; i++ ) {
    		// ｺﾝﾎﾟｰﾈﾝﾄ取得
    		Component com = comAry[i];
    		// リスナーセット
    		setChangeListener(com);
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
    class setDocumentListener implements DocumentListener{
		public void changedUpdate(DocumentEvent e){ setChangeFlg(); } // 変更時
		public void insertUpdate(DocumentEvent e){ setChangeFlg(); }  // 追加時
		public void removeUpdate(DocumentEvent e){ setChangeFlg(); }  // 削除時
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
    class setChangeListener implements ChangeListener{
    	public void stateChanged(ChangeEvent e){ setChangeFlg(); }
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
    class setItemListener implements ItemListener {
    	public void itemStateChanged(ItemEvent e){ setChangeFlg();}
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
    class setListSelectionListener implements ListSelectionListener {
    	public void valueChanged(ListSelectionEvent e){ setChangeFlg();}
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    protected void setChangeFlg(){
		// コンポーネントの状態に変化が発生したら変更フラグを立てる
    	changeFlg = true;
	}
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public void setRemoveChangeFlg(){
		// フラグを戻す
    	changeFlg = false;
	}
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public void setCompulsionChangeFlg(){
		// 強制的に変更フラグを立てる
    	changeFlg = true;
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
    public boolean getChangeFlg(){
    	return changeFlg;
    }
    

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public void firstAction(){
    	return;
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
    public String getSystemTime() {
    	return DateUtils.getCurrentTimeHHmmss();
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
    public String getSystemDate() {
    	return DateUtils.getDateCurrentWithSplit();
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
    public String getSystemDateTime() {
    	return DateUtils.getCurrentDateTime();
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
    protected static boolean checkLogin() {
    	
    	LoginUser user = BaseDataManager.getLoginUser();
    	
    	if (user == null) {
    		return false;
    	} else {
    		return true;
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
    protected static void requestLogin() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.pack();
		loginFrame.setVisible(true);
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
    protected BaseFrame getFrame() {
    	return this;
    }
    /**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(BaseFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the exeMenu 
	 */
	public MenuExeVo getExeMenu() {
		return exeMenu;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame pFrame
	 */
	public void backToMenu(String strKey, String strParentKey) {
		FrameConstants.removeNameFrame(strKey);
		FrameConstants.removeNameFrame(strParentKey);
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 */
	public void backToBeforInterFace(String strKey) {
		FrameConstants.removeNameFrame(strKey);
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param exeMenu the exeMenu to set
	 */
	public void setExeMenu(MenuExeVo exeMenu) {
		this.exeMenu = exeMenu;
		ApplicationConstants.MENU_GRP = exeMenu.getMenugrpCode();
		ApplicationConstants.MENU_EXE = exeMenu.getMenuexeCode();
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
    public class SplashScreen extends JWindow {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SplashScreen() {
			JPanel content = (JPanel) getContentPane();
			content.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
			content.setBackground(Color.blue);
			int width = 250;
			int height = 90;
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width - width) / 2;
			int y = (screen.height - height) / 2;
			setBounds(x, y, width, height);
			JXBusyLabel sms = new JXBusyLabel();
			sms.setBusy(true);
			sms.setText("<html><center><font size=5 COLOR=white>只今処理中です。</font></center><center><font size=5 COLOR=white>しばらくお待ちください。</font></center>");
			sms.setHorizontalAlignment(SwingConstants.CENTER);
			content.add(sms,
							BorderLayout.CENTER);
		
		}
	}

	public void stopTimerRemoveListener() {
	}
}

