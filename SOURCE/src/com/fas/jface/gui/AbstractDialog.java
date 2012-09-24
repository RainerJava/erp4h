/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：AbstractDialog.java
*
*     記述				：
*     
*     作成日			：2010/02/15   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.NumberUtils;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public abstract class AbstractDialog extends BaseDialog {

	/** */
	private static final long serialVersionUID = 1L;
    /** */
    private Object[] param;
    /** */
    protected Container headerContainer = null;
    /** */
    private  boolean load = true;
    /** */
    protected int X_FRAME_LENGTH = 1024;
    /** */
    protected int Y_FRAME_LENGTH = 748;
    /** */
    protected int X_BODY_LENGTH = 1024;
    /** */
    protected int Y_BODY_LENGTH = 600;
    /** */
    protected int X_HEADER_LENGTH = 1024;
    /** */
    protected int Y_HEADER_LENGTH = 35;
    /** */
    protected int X_FOOTER_LENGTH = 1024;
    /** */
    protected int Y_FOOTER_LENGTH = 100;
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     */
    public AbstractDialog(String name) {
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
    public AbstractDialog(BaseFrame pFrame) {
        super();
        init("");
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param pFrame
     * @param modal
     */
    public AbstractDialog(BaseFrame pFrame, boolean modal) {
    	super(pFrame, modal);
        init("");
    }
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param pFrame
     * @param modal
     */
    public AbstractDialog(BaseDialog dialog, boolean modal) {
    	super(dialog, modal);
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
    public AbstractDialog(String name, Object[] param) {
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
    protected abstract BasePanel getBodyPanel();
    
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
    protected Container getHeaderContainer() {
        return headerContainer;
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
    protected abstract JSeparator getHeaderSeparator();
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract JSeparator getFooterSeparator();
    
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
    	if (headerContainer != null) {
    		headerContainer.setBackground(color);
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
    	initKodomo();
    	
    	setLocation(NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - X_FRAME_LENGTH)/ 2) - 3, NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Y_FRAME_LENGTH) / 2) - 32);
        setTitle(name);
        setPreferredSize(new Dimension(X_FRAME_LENGTH + FaceContants.FRAME_LEFT + FaceContants.FRAME_RIGHT, Y_FRAME_LENGTH + FaceContants.FRAME_TOP + FaceContants.FRAME_BOTTOM));
        
        headerContainer = getContentPane();
        
        BasePanel mainPnl = new BasePanel();
        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.Y_AXIS));
        mainPnl.setPreferredSize(new Dimension(X_FRAME_LENGTH, Y_FRAME_LENGTH));
        mainPnl.setBorder(null);
        mainPnl.setBackground(getFrameBackColor());

        JPanel header = getHeader();
        if (header != null) {
        	mainPnl.add(header, BorderLayout.NORTH);
        	JSeparator headerSeparator = getHeaderSeparator();
            if (headerSeparator != null) {
                headerSeparator.setBackground(ColorConstants.DEFAULT_SEPERATOR_BACK_COLOR);
                headerSeparator.setOpaque(true);	
                mainPnl.add(headerSeparator);
            }
        }

        JPanel body = getBodyPanel();
        if (body != null) {
        	mainPnl.add(body, BorderLayout.CENTER);
        }
        
        JPanel footer = getFooter();
        if (footer != null) {       
        	JSeparator footerSeparator = getFooterSeparator();
        	if (footerSeparator != null) {
        		footerSeparator.setBackground(ColorConstants.DEFAULT_SEPERATOR_BACK_COLOR);
        		footerSeparator.setOpaque(true);
        		mainPnl.add(footerSeparator);
        	}
        	mainPnl.add(footer, BorderLayout.SOUTH);
        }    

        headerContainer.add(mainPnl);
        
    }

    protected void initKodomo() {
    	
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

}
