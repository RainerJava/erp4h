package com.fas.jface.text;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;



public class DoubleNumberText extends BaseInputText {
	private int integerDigits = 6;
	private int fractionDigits = 3;
	private boolean allowNegative = true;
	
	private String oldText = "";
	private DecimalFormat format = new DecimalFormat("#,###.###");

	/** */
	private static final long serialVersionUID = 1L;

	public DoubleNumberText(String text) {
		super(text);
		setHorizontalAlignment(RIGHT);
		initEvent();
	}

	public DoubleNumberText(String text, int integerDigits, int fractionDigits) {
		super(text);
		setHorizontalAlignment(RIGHT);
		this.integerDigits = integerDigits;
		this.fractionDigits = fractionDigits;
		initEvent();
	}
	
	public void setAllowNegative(boolean allowNegative) {
		this.allowNegative = allowNegative;
	}

	private boolean isCharNumber(char c) {
		if ((c >= '0' && c <= '9'))
			return true;

		return false;
	}
	
	private boolean validDigits(String numberText){
		int intDigits = StringUtils.getLengthInteger(numberText);
		int fractDigits = StringUtils.getLengthFraction(numberText);
		
		return intDigits <= this.integerDigits && fractDigits <= this.fractionDigits;		
	}
	
	public void paste() {
    	
    	super.paste();

    	try{
        	Document doc = getDocument();
    		String test = doc.getText(0, doc.getLength());
    		if(test.indexOf("\n") != -1){
        		test = StringUtils.replaceAll(test, "\n", "");
    		}
        	if (StringUtils.isDouble(test) && validDigits(test)){        		
					setText(test);				
        	} else {
        		setText("");
        	}
    	} catch (BadLocationException e){
    	} catch (Exception e) {
		}
    }	
	
	private boolean validateDigit(char c){
		return isCharNumber(c) || (c == '.') || (c == '-');
	}
	
	private void reFormat(String numberText){		
		String tmp = numberText.replace(",", "");
		if(StringUtils.isDouble(tmp)){
			Double value = Double.parseDouble(tmp);
			String text = format.format(value);
			//System.out.println("reFormat:" + text);
			
			if(!numberText.equals(text)){
				int position = getCaretPosition();
				if(text.length() > numberText.length()) {position += 1;}
				setText(text, position);	
			}		
		}
	}
	
	private void setForeground(String numberText){
		numberText = numberText.replace(",", "");
		if(StringUtils.isDouble(numberText)){
			Double value = Double.parseDouble(numberText);
			if(value < 0){
				setForeground(Color.RED);
			} else {
				setForeground(Color.BLACK);
			}
		} else {
			setForeground(Color.BLACK);
		}
	}
	
	public void setText(String str) {
    	super.setText(str);
    	setForeground(str);
    }
	
	private void setText(String text, int focusIndex){
		setText(text);
		if(focusIndex >= 0 && focusIndex < text.length()){
			setCaretPosition(focusIndex);
		}
	}
	
	public Double getValue() {
		Double value = new Double(0);
		try {
			String numberText = getText().replace(",", "");
			value = NumberUtils.getDoubleFromString(numberText);
		}catch(Exception ex) {
			
		}
		return value;
	}
	
	private void initEvent() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

				String numberText = getText();//.replace(",", "");
				char keyChar = e.getKeyChar();

				int positionPoint = getText().indexOf(".");
				int positionCaret = 0;

				positionCaret = getCaretPosition();

				// allow digit '0-9','.','-'
				Pattern regex = Pattern.compile("[0-9.-]*",
						java.util.regex.Pattern.CASE_INSENSITIVE);
				Matcher matcher = regex.matcher(Character.toString(keyChar));

				if (!matcher.matches()) {
					e.consume();
					return;
				}

				int intDigits = StringUtils.getLengthInteger(numberText);
				int fractDigits = StringUtils.getLengthFraction(numberText);
				
				int startSelection = getSelectionStart();
				int endSelection = getSelectionEnd();				

				if (isCharNumber(keyChar)) {
					if ((intDigits >= integerDigits && positionCaret <= positionPoint) || (intDigits == integerDigits && positionPoint < 0)) {
						e.consume();
						return;
					}

					if (fractDigits >= fractionDigits && positionCaret > positionPoint) {
						e.consume();
						return;
					}
				} else if(keyChar == '.' || keyChar == '-'){
					if(keyChar == '-' && !allowNegative){
						e.consume();
						return;
					}
					
					if(keyChar == '-' && getValue() < 0){
						e.consume();
						return;
					}
					
//					if(positionCaret == 0 && keyChar == '0'){
//						e.consume();
//						return;					
//					}
					
					if(startSelection == endSelection && isCharNumber(keyChar)){
						numberText = new StringBuffer(numberText).insert(positionCaret, keyChar).toString();
					} else {					
						numberText = new StringBuffer(numberText).replace(startSelection, endSelection, Character.toString(keyChar)).toString();
					}
					if(!StringUtils.isDouble(numberText.replace(",", ""))){
						e.consume();
						return;
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				String numberText = getText().replace(",", "");
				char keyChar = e.getKeyChar();
				
				int intDigits = StringUtils.getLengthInteger(numberText);
				int positionCaret = getCaretPosition();
				
				if(intDigits > integerDigits){
					numberText = oldText;
					setText(oldText);
					setCaretPosition(positionCaret);
				}
				
				setForeground(numberText);
				
				if(keyChar != '.')
					reFormat(getText());
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				oldText = getText();
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
					selectAll();

				} else {
					setBackground(SystemColor.control);
				}
			}
		});
	}
}
