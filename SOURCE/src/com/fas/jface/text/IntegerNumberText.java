package com.fas.jface.text;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.Document;

import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: tls: longtv</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class IntegerNumberText extends BaseInputText { 
	/** */
	private static final long serialVersionUID = 6915906372670442543L;
	NumberFormat nf = NumberFormat.getInstance();
	boolean reformat = true;
	
	JTextField jTextField1 = new JTextField();
	
	private int minValue = Integer.MIN_VALUE;
	private int maxValue = Integer.MAX_VALUE;
	
	public IntegerNumberText(){
		super();
		initEvent();
		setHorizontalAlignment(JTextField.RIGHT);
	}
	
	public IntegerNumberText(int number){
		super(Integer.toString(number));
		initEvent();
		setHorizontalAlignment(JTextField.RIGHT);
	}
	
	public IntegerNumberText(int number, int minValue, int maxValue){
		super(Integer.toString(number));
		this.minValue = minValue;
		this.maxValue = maxValue;
		initEvent();
		setHorizontalAlignment(JTextField.RIGHT);
	}
	
	public IntegerNumberText(int number, int minValue, int maxValue, int columns, int maxlength){
		super(Integer.toString(number), columns, maxlength);
		this.minValue = minValue;
		this.maxValue = maxValue;
		initEvent();
		setHorizontalAlignment(JTextField.RIGHT);
	}
	
    public void paste() {
    	
    	super.paste();

    	try{    		
    		
    		String strPaste = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        	Document doc = getDocument();        	
        	
//        	int beginIndex = getSelectionStart();
//        	int endIndex = beginIndex + strPaste.length();
        	        	
    		String test = getText();
    		if(test.indexOf("\n") != -1){
        		test = StringUtils.replaceAll(test, "\n", "");
    		}
    		//String oldText = StringUtils.replace(test, "", beginIndex, endIndex);
    		if(test.length() > doc.getLength()){
    			setText("");
    			return;
    		}
    		
        	if (StringUtils.isInteger(test)){
        		int number = StringUtils.convertStrToInteger(test);				
				if(number >= minValue && number <= maxValue){
					setText(test);
				} else {
					setText("");
				}
        	} else {
        		setText("");
        	}    	
    	} catch (Exception e) {
		}
    }	
    
    public void setText(String str) {
    	super.setText(str);
    	setForeground(str);
    }
    
    public void setReformat(boolean reformat){
    	this.reformat = reformat;
    }
	
	/**
	 * @return the minValue
	 */
	public int getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	private void setForeground(String numberText){
		numberText = numberText.replace(",", "").replace(".", "");
		if(StringUtils.isInteger(numberText)){
			Integer value = Integer.parseInt(numberText);
			if(value < 0){
				setForeground(Color.RED);
			} else {
				setForeground(Color.BLACK);
			}
		} else {
			setForeground(Color.BLACK);
		}		
	}
		
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param numberText
	* </DL>
	*/
	private void reFormat(String numberText){		
		String tmp = numberText.replace(",", "").replace(".", "");
		if(StringUtils.isInteger(tmp)){
			Integer value = Integer.parseInt(tmp);
			String text = nf.format(value);
			//System.out.println("reFormat:" + text);
			
			int position = getCaretPosition();
			if(!numberText.equals(text) && this.reformat){				
				if(text.length() > numberText.length()) {position += 1;}
				setText(text, position);	
			} 
		}
	}
	
	private void setText(String text, int focusIndex){
		setText(text);
		if(focusIndex >= 0 && focusIndex < text.length()){
			setCaretPosition(focusIndex);
		}
	}
	
	private boolean isCharNumber(char c){
		if((c >= '0' && c <= '9') || (c == '-'))
			return true;
		
		return false;
	}
	
	public int getValue(){
    	int value = 0;
    	try {
    		String numberText = getText().replace(",", "");
    		value = NumberUtils.getIntFromString(numberText);
    	} catch(Exception ex) {
    		value = 0;
    	}
    	return value;
    }
	
	private void initEvent(){
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
				
				char keyChar = e.getKeyChar();
				String numberText = getText();
				
				if(!isCharNumber(keyChar)){
					e.consume();
					return;
				}
				
				if(keyChar == '-') {
					e.consume();
				}
				
				int positionCaret = getCaretPosition();
				int startSelection = getSelectionStart();
				int endSelection = getSelectionEnd();
				
				int number = 0;
				
				if(startSelection == endSelection && isCharNumber(keyChar)){
					numberText = new StringBuffer(numberText).insert(positionCaret, keyChar)
					                                         .toString();
				} else {					
					numberText = new StringBuffer(numberText).replace(startSelection, endSelection, Character.toString(keyChar))
					                                         .toString();
				}
				
				numberText = numberText.replace(",", "").replace(".", "");
				if(StringUtils.isInteger(numberText)){						
					//System.out.println("keyTyped(numberText): " + numberText);
					number = StringUtils.convertStrToInteger(numberText);				
					if(number < minValue || number > maxValue){						
						e.consume();
					} else {
						setForeground(numberText);			
					}
				} else {					
					e.consume();
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
								
				//System.out.println("-------------------------------");
				
				char keyChar = e.getKeyChar();
				//System.out.println("keyReleased(keyChar): " + keyChar);
				
				String numberText = getText();
				//System.out.println("keyReleased(numberText): " + numberText);
				
				int positionCaret = getCaretPosition();
				
				int curValue = getValue();
				if(keyChar == '-' && minValue < 0 && curValue != 0) {
					String str = numberText.substring(0, 1);
					//System.out.println("keyReleased(numberText.substring(0, 1)): " + str);
					if("-".equals(str)) 
					{
						if((curValue*-1) <= maxValue) {
							String newNumberText = numberText.substring(1);
							//System.out.println("keyReleased(newNumberText): " + newNumberText);
							setText(newNumberText, (positionCaret - 1)>=0?(positionCaret - 1):0);
							setForeground(newNumberText);
						}
					} else if((curValue*-1) >= minValue){
						String newNumberText = "-" + numberText;
						setText(newNumberText, positionCaret + 1);
						setForeground(newNumberText);
					}
				} else {				
					//System.out.println("keyReleased(numberText)11: " + numberText);
					setForeground(numberText);
					reFormat(numberText);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if (isEditable() == true)
					setBackground(Color.WHITE);
				else
					setBackground(SystemColor.control);				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if (isEditable() == true) {
					setForeground(getText());
					setBackground(Color.YELLOW);
					setIMType(IM_OFF);
					selectAll();			
				} else {
					setBackground(SystemColor.control);
				}				
			}
		});	
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}

		JFrame f = new JFrame("Numeric Text Field Example");
		final IntegerNumberText tf = new IntegerNumberText(0, -999999, 9999);
		
		JLabel lbl = new JLabel("Type a number: ");
		f.getContentPane().add(tf, "East");
		f.getContentPane().add(lbl, "West");
		
		f.setSize(400, 100);
		f.setVisible(true);
	}
}
