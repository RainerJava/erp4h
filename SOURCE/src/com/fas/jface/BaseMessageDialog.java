/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseMessageDialog.java
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

package com.fas.jface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.button.Action;
import com.fas.jface.button.ActionButton;
import com.fas.jface.gui.BaseDialog;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.utils.SwingUtils;

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

public class BaseMessageDialog extends BaseDialog {

	/** */
	private static final long serialVersionUID = 1L;

    /** */
    private int closenOperation=DISPOSE;
    /** */
    private final static int HIDDEN=1;
    /** */
    private final static int DISPOSE=2;
    
    /** */
    private Component owner;
    
    /** */
    private BaseLabel label ;

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param mode
     */
    public void setClosenOperation(int mode){
        this.closenOperation=mode;
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public BaseMessageDialog(){
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
     * @param owner
     */
    public BaseMessageDialog(Component owner){
        super(SwingUtils.getParentFrame(owner),false);
        this.owner=owner;
        init();
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param owner
     * @param modal
     */
    public BaseMessageDialog(Component owner,boolean modal){
        super(SwingUtils.getParentFrame(owner),modal);
        this.owner=owner;
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
    private void init(){
        setSize(new Dimension(300,150));
        setResizable(false);
        BasePanel panel = new BasePanel();    	
    	panel.setLayout(null);
    	label = getLabel();
    	ActionButton button=new ActionButton("ＯＫ");
    	button.setBounds(110,90,60,20);
    	button.addAction(new CloseAction());
    	panel.add(button);
    	panel.add(label);
    	getContentPane().add(panel);
        setLocationRelativeTo(owner);
      	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseEvent());
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param message
     */
    public void setMessage(String message){
        label.setText(message);
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
    private BaseLabel getLabel() {
    	BaseLabel label=new BaseLabel();
    	label.setBackground(ColorConstants.DEFAULT_BACK_COLOR);
    	label.setForeground(Color.BLACK);
    	label.setBorder(null);
    	label.setBounds(0,5,250,100);
        return label;
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
    private class CloseAction implements Action {
        public void execute() {
            if(closenOperation==HIDDEN){
                setVisible(false);
            }else{
                dispose();
            }
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
    private class CloseEvent extends WindowAdapter{
        /* (non-Javadoc)
         * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
         */
        public void windowClosing(WindowEvent e) {
            if(closenOperation==HIDDEN){
                setVisible(false);
            }else{
                dispose();
            }
        }
    
        /* (non-Javadoc)
         * @see java.awt.event.WindowAdapter#windowDeactivated(java.awt.event.WindowEvent)
         */
        public void windowDeactivated(WindowEvent e) {
            if(closenOperation==HIDDEN){
                setVisible(false);
            }else{
                dispose();
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
     * @param args
     */
    public static void main(String[] args){
    	BaseMessageDialog dialog = new BaseMessageDialog();
        dialog.setVisible(true);
    }	
}

