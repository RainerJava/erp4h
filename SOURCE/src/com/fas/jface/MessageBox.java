/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MessageBox.java
*
*     記述				：
*     
*     作成日			：2009/12/04  
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.StringUtils;
import com.fas.jface.panel.BaseOptionPane;
import com.fas.vo.msg.MstMsgVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC12</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class MessageBox {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MessageBox() {
	}

    /** */
    private static final String ERROR = "注意メッセージ";
    /** */
    private static final String INFO = "情報メッセージ";
    /** */
    private static final String CONFIRM = "問合せメッセージ";
    /** */
    private static final String WARNING = "警告メッセージ";
    /** */
    public static final int YES = JOptionPane.YES_OPTION;
    /** */
    public static final int NO = JOptionPane.NO_OPTION;
    
    /**
     * @param msgCd
     * @param message
     * @return
     */
    public static int message(Component parentComponent, String msgCd, String message){
    	String msgType = "";
    	msgType = msgCd.substring(0, 1);
    	
    	if (msgType.equalsIgnoreCase("E"))
    		BaseOptionPane.showMessageDialog(parentComponent, message, ERROR + " [" + msgCd + "]", JOptionPane.ERROR_MESSAGE);
    	else if (msgType.equalsIgnoreCase("I"))
        	BaseOptionPane.showMessageDialog(parentComponent, message, INFO + " [" + msgCd + "]", JOptionPane.INFORMATION_MESSAGE);
    	else if (msgType.equalsIgnoreCase("W"))
            BaseOptionPane.showMessageDialog(parentComponent, message, WARNING + " [" + msgCd + "]", JOptionPane.WARNING_MESSAGE);
    	else if (msgType.equalsIgnoreCase("Q"))
            return BaseOptionPane.showConfirmDialog(parentComponent, message, CONFIRM + " [" + msgCd + "]", JOptionPane.YES_NO_OPTION);

    	return 0;
    }
    

    /**
     * @param parentComponent
     * @param mstVo
     * @return
     */
    public static int message(Component parentComponent, MstMsgVo mstVo) {
    	
    	String msgType = "";
    	msgType = mstVo.getMsgCd().substring(0, 1);
    	String message = "";
    	
    	int defaultButton;
		try {
			defaultButton = Integer.parseInt(mstVo.getButtonType());
		} catch (NumberFormatException e) {
			defaultButton = 1;
			e.printStackTrace();
		}
    	final Object[] options = {"はい(Y)", "いいえ(N)"};
    	if (defaultButton == 0) {
    		defaultButton = 1;
    	}
    	
    	--defaultButton;
    	String extraInfor = mstVo.getExtraInfor();
    	
    	if (StringUtils.isValid(extraInfor)) {
    		extraInfor = "[" + extraInfor + "]" + "\n\n";
    	}
    	
    	message = extraInfor;
    	
    	if (StringUtils.isValid(mstVo.getMsg1()) == true)
    		message += mstVo.getMsg1() + "\n";
    	if (StringUtils.isValid(mstVo.getMsg2()) == true)
    		message += mstVo.getMsg2() + "\n";
    	if (StringUtils.isValid(mstVo.getMsg3()) == true)
    		message += mstVo.getMsg3() + "\n";
    	if (StringUtils.isValid(mstVo.getMsg4()) == true)
    		message += mstVo.getMsg4() + "\n";
    	
    	if (msgType.equalsIgnoreCase("E") || msgType.equalsIgnoreCase("C"))
    		BaseOptionPane.showMessageDialog(parentComponent, message, ERROR + " [" + mstVo.getMsgCd() + "]", JOptionPane.ERROR_MESSAGE);
    	else if (msgType.equalsIgnoreCase("I"))
        	BaseOptionPane.showMessageDialog(parentComponent, message, INFO + " [" + mstVo.getMsgCd() + "]", JOptionPane.INFORMATION_MESSAGE);
    	else if (msgType.equalsIgnoreCase("W"))
            BaseOptionPane.showMessageDialog(parentComponent, message, WARNING + " [" + mstVo.getMsgCd() + "]", JOptionPane.WARNING_MESSAGE);
    	else if (msgType.equalsIgnoreCase("Q")){
    		
    		final JOptionPane pane = new JOptionPane(message,
    				JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION, null);
    		// focus to default button
			if (defaultButton == 1) {
				pane.setInitialValue(options[1]);
			} else {
				pane.setInitialValue(options[0]);
			}
    		pane.setOptions(options);
            JDialog dialog = pane.createDialog(parentComponent, CONFIRM + " [" + mstVo.getMsgCd() + "]");
            KeyListener keyListener = new KeyListener() {

				public void keyPressed(KeyEvent e) {
					final int keyCode = e.getKeyCode();
					if (keyCode == KeyEvent.VK_LEFT
							|| keyCode == KeyEvent.VK_RIGHT) {
						Component[] components = pane.getComponents();
						for (int i = 0; i < components.length; i++) {
							Component[] comps = ((Container) components[i])
									.getComponents();
							for (int j = 0; j < comps.length; j++) {
								if (comps[j] instanceof JButton) {
									if (!comps[j].equals((JButton) e
											.getSource())) {
										((JButton) comps[j]).requestFocus();
									}
								}
							}
						}
					} else if (keyCode == KeyEvent.VK_Y) {
						pane.setValue(options[0]);
					} else if (keyCode == KeyEvent.VK_N) {
						pane.setValue(options[1]);
					}
				}

                public void keyReleased(KeyEvent e) {
                }

                public void keyTyped(KeyEvent e) {
                }
            };
            Component[] components = pane.getComponents();
            boolean isYesButton = true;//mark the first button as the "yes" button
            for (int i = 0; i < components.length; i++) {
                Component[] comps = ((Container) components[i]).getComponents();
                for (int j = 0; j < comps.length; j++) {
                    if (comps[j] instanceof JButton) {
                        JButton button = (JButton) comps[j];
                        button.addKeyListener(keyListener);
                        //set mnemonic for "yes" and "no" buttons
                    	if (isYesButton) {
							button.setMnemonic('Y');
							isYesButton = false;
						}else{
							button.setMnemonic('N');
						}
                    }
                }
            }
            //display the dialog
            dialog.setVisible(true);
			String selectedValue = null;
			if (pane.getValue() != null) {
				selectedValue = pane.getValue().toString();
			}
			if (selectedValue == null || selectedValue.equals("-1")) {
				return 1;// user don't chose any options, set 1 as selected value
			}
			int index = 0;
			for (Object object : options){
				if(((String)object).equals(selectedValue)){
					return index;
				}
				index++;
			}
//    		return HanbaiJOptionPane.showOptionDialog(parentComponent, message, CONFIRM + " [" + mstVo.getMsgCd() + "]", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,options[defaultButton]);
    	}
    	return 0;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>メッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param message
     */
    public static void error(String message){
    	BaseOptionPane.showMessageDialog(null, message, ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>エラーメッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     */
    public static void error(Component component, String message) {        
    	BaseOptionPane.showMessageDialog(component, message, ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param errCd
     * @param message
     */
    private static void error(Component component, String errCd, String message) {   
    	BaseOptionPane.showMessageDialog(component, message, ERROR + " [" + errCd + "]", JOptionPane.ERROR_MESSAGE);
    }
	    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param errCd
     * @param message
     */
    private static void error(String errCd, String message){
    	BaseOptionPane.showMessageDialog(null, message, ERROR + " [" + errCd + "]", JOptionPane.ERROR_MESSAGE);
    }
    
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>エラーメッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param components
     * @param message
     */
    private static void errorMsg(Component components[], String message){
    	Component com = null;
        Object[] objs = null;
    	if(components != null){
    		com = components[0];
        	objs = new Object[components.length];
        	for(int i=0; i<objs.length; i++){
        		Object obj = components[i].getName();
        		objs[i] = obj;
        	}
    	}
    	
    	errorMsg(com , message, objs);    	
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>エラーメッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     */
    private static void errorMsg(Component component, String message){
    	Component com[] = {component};
    	errorMsg(com , message);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>エラーメッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     * @param param
     */
    private static void errorMsg(Component component, String message, Object param[]) {
    	if(param != null){
            message = MessageFormat.format(message , param);
    	}
    	error(component,message);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>システムエラーメッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     */
    public static void systemError(Component component){
    	systemError(MessageConstants.SYS_ERR_MSG, component);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>システムエラーメッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param message
     * @param component
     */
    public static void systemError(String message, Component component){
    	BaseOptionPane.showMessageDialog(null, message, ERROR, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }	
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>メッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param message
     */
    public static void info(String message){
    	BaseOptionPane.showMessageDialog(null, message, INFO, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>メッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     */
    public static void info(Component component, String message){
    	BaseOptionPane.showMessageDialog(component, message, INFO, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param errCd
     * @param message
     */
    private static void info(Component component, String errCd, String message) {   
    	BaseOptionPane.showMessageDialog(component, message, INFO + " [" + errCd + "]", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>メッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     */
    public static void warning(JFrame parent, String message){
    	//BaseOptionPane.showMessageDialog(component, message, WARNING, JOptionPane.WARNING_MESSAGE);
    	JDialog  dilog = new WarningDialog(parent, message);
    	dilog.show();
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param errCd
     * @param message
     */
    private static void info(String infoCd, String message) {
    	BaseOptionPane.showMessageDialog(null, message, INFO + " [" + infoCd + "]", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>メッセージ表示</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     * @param param
     */
    private static void info(Component component, String message, Object param[]){
    	if(param != null){
            message = MessageFormat.format(message , param);
    	}
    	info(component, message);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>確認ダイアログ</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     * @return
     */
    public static int confirm(Component component, String message) {
        return BaseOptionPane.showConfirmDialog(component, message, CONFIRM, JOptionPane.YES_NO_OPTION);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param errCd
     * @param message
     * @return
     */
    private static int confirm(Component component, String cfCd, String message) {
        return BaseOptionPane.showConfirmDialog(component, message, CONFIRM + " [" + cfCd + "]", JOptionPane.YES_NO_OPTION);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>確認ダイアログ</DD>
     * <BR>
     *
     * </DL>
     * @param component
     * @param message
     * @param param
     * @return
     */
    private static int confirm(Component component, String message, Object param[]) {
    	
    	if(param != null){
            message = MessageFormat.format(message, param);
    	}
    	
        return BaseOptionPane.showConfirmDialog(component, message, CONFIRM, JOptionPane.YES_NO_OPTION);
        
    }
}

/**
 * @author nvhung
 *
 */
class WarningDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** */
    private static final String WARNING = "警告メッセージ";
    
	/**
	 * @param parent
	 * @param msg
	 */
    public WarningDialog(JFrame parent, String msg) {
    	super(parent, WARNING, true);

    	Box b = Box.createVerticalBox();
    	b.add(Box.createGlue());
    	JLabel label = new JLabel(StringUtils.subString(msg, msg.length()/2 -1));
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setFont(FontConstants.WARNING_FONT);
    	label.setForeground(Color.RED);
    	b.add(label);

    	JLabel downLabel = new JLabel(StringUtils.subString(msg, msg.length()/2 - 1, msg.length()/2 + 2) );
    	downLabel.setHorizontalAlignment(JLabel.CENTER);
    	downLabel.setFont(FontConstants.WARNING_FONT);
    	downLabel.setForeground(Color.RED);
    	b.add(downLabel);

    	b.add(Box.createGlue());

    	getContentPane().add(b, "Center");

    	JPanel p2 = new JPanel();
    	JButton ok = new JButton("理解");
    	ok.setPreferredSize(new Dimension(120,60));
    	p2.add(ok);
    	getContentPane().add(p2, "South");

    	ok.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evt) {
    			setVisible(false);
    			dispose();
    		}
    	});

    	Toolkit tk = Toolkit.getDefaultToolkit();
    	Dimension screenSize = tk.getScreenSize();
    	int screenHeight = screenSize.height;
    	int screenWidth = screenSize.width;
    	setSize(730, 500);
    	setLocation(screenWidth / 2 - 350, screenHeight / 2 - 250);	    
    }
 }   

