/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：AbstractFrame.java
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

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.NumberUtils;
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

public abstract class AbstractMasterFrame extends BaseFrame {

	/** */
	private static final long serialVersionUID = 1L;
    /** */
    private Object[] param;
    /** */
    protected Container mainPanel = null;
    /** */
    private  boolean load = true;
    /** */
    protected int X_FRAME_LENGTH = 1024;
    /** */
    protected int Y_FRAME_LENGTH = 748;
    /** */
    protected int X_BODY_LENGTH = 1024;
    /** */
    protected int Y_BODY_LENGTH = 400;
    /** */
    protected int X_HEADER_LENGTH = 1024;
    /** */
    protected int Y_HEADER_LENGTH = 40;
    /** */
    protected int X_FOOTER_LENGTH = 1024;
    /** */
    protected int Y_FOOTER_LENGTH = 30;
    /** */
    protected int BTN_PANEL_HEIGHT = 45;
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     */
    public AbstractMasterFrame(String name) {
        init(name);
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public AbstractMasterFrame(BaseFrame baseFrame) {
        super(baseFrame);
        init("");
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     * @param param
     */
    public AbstractMasterFrame(String name, Object[] param) {
         init(name);
         setParam(param);
    }
    
    /* (non-Javadoc)
     * @see java.awt.Component#setVisible(boolean)
     */
    public void setVisible(boolean b) {
    	super.setVisible(b);
    	if (b == false) {
	    	load = false;
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
    protected abstract BasePanel getBodyCenter();
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract BasePanel getHeader();
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract BasePanel getFooter();
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract BasePanel getBodyLeft();
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract BasePanel getBodyRight();
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected Container getmainPanel() {
        return mainPanel;
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
    protected void setFrameBackgroupColor(java.awt.Color color) {
    	if (mainPanel != null) {
    		mainPanel.setBackground(color);
    	}
    }

     /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param param
     */
    public void setParam(Object[] param) {
         this.param = param;
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
    public Object[] getParam() {
         return this.param;
     }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     */
    private void init(String name) {
    	setFrameSize();
    	setLocation(NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - X_FRAME_LENGTH)/ 2) - 3, NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Y_FRAME_LENGTH) / 2) - 16);
        setTitle(name);
        setPreferredSize(new Dimension(X_FRAME_LENGTH , Y_FRAME_LENGTH));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = getContentPane();
    }
	/**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    protected boolean getAuth() {
    	LoginUser user = getLoginUser();
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
	public boolean isLoad() {
		return load;
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
	protected String getFrameName() {
		return "";
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
	protected Color getFrameBackColor() {
		return ColorConstants.DEFAULT_BACK_COLOR;
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
	protected Color getFrameForeColor() {
		return ColorConstants.FRAME_DEFAULT_FORE_COLOR;
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
    protected String getFrameID() {
    	return "";
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    protected void setFrameSize() {
    }
    
    /* (non-Javadoc)
     * @see java.awt.Window#show()
     */
    @SuppressWarnings("deprecation")
	public void show(){
    	super.show();
    }
    
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected static void initData() throws BizException {
		ApplicationConstants.initData();
	}
}

