/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：エスイーシー化成
 * 
 *     ファイル名		：DoubleText.java
 *
 *     記述				：
 *     	
 *     作成日			：2010/04/12   
 *
 *     作成者			：Nguyen Quang Hung
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.jface.text;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.common.constants.screen.ColorConstants;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD> <BR>
 * 
 * @author KIKAKU7
 * 
 */
public class DoubleText extends JTextField implements NumericPlainDocument.InsertErrorListener, FocusListener, KeyListener {

	/** */
	private static final long serialVersionUID = 1L;
	private int integerDigits = 6;
	private int fractionDigits = 3;
	private String numberTextResult = "";
	
	/* global */
	private String numberTextBefore = "";
	private boolean isModify = false;
	private boolean isSpecialKey = false;
	private boolean isBeingPressed = false; // dung de xu ly bam tung fim 1 chu ko lien tuc

	private DecimalFormat format;

	public DoubleText() {
		this(null, 0, null);
	}

	public DoubleText(String text, int integerDigits, int fractionDigits) {
		super(null, text, integerDigits + fractionDigits + 2);

		this.integerDigits = integerDigits;
		this.fractionDigits = fractionDigits;

		init(text);
	}

	public DoubleText(String text, int columns, DecimalFormat format) {
		super(null, text, columns);

		if (format != null) {
			this.format = format;
		}

		init(text);
	}

	public DoubleText(int columns, DecimalFormat format) {
		this(null, columns, format);
	}

	public DoubleText(String text) {
		this(text, 0, null);
	}

	public DoubleText(String text, int columns) {
		this(text, columns, null);
	}

	private void init(String doubleText) {

		if (format == null) {
			format = new DecimalFormat("#,###.###");
			format.setMaximumIntegerDigits(integerDigits);
			format.setMaximumFractionDigits(fractionDigits);
			format.setMinimumFractionDigits(fractionDigits);
		}
		format.setGroupingUsed(true);
		format.setGroupingSize(3);
		format.setParseIntegerOnly(false);

		NumericPlainDocument numericDoc = (NumericPlainDocument) getDocument();
		numericDoc.setFormat(format);
		numericDoc.addInsertErrorListener(this);

		setHorizontalAlignment(JTextField.RIGHT);
		setPreferredSize(new Dimension(200, 20));
		setFont(FontConstants.TEXT_FIELD_FONT);

		addFocusListener(this);
		addKeyListener(this);

		setText(doubleText);
	}

	public void setText(String t) {
		this.numberTextResult = normalize(t);
		super.setText(this.numberTextResult);
		updateForeColor();
	}

	private void updateForeColor() {
		if (getDoubleValue() >= 0) {
			setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
		} else {
			setForeground(Color.RED);
		}
	}

	public void setFormat(DecimalFormat format) {
		((NumericPlainDocument) getDocument()).setFormat(format);
	}

	public DecimalFormat getFormat() {
		return ((NumericPlainDocument) getDocument()).getFormat();
	}

	public void formatChanged() {
		// Notify change of format attributes.
		setFormat(getFormat());
	}

	// Methods to get the field value
	public Long getLongValue() throws ParseException {
		return ((NumericPlainDocument) getDocument()).getLongValue();
	}

	public Double getDoubleValue() {

		Double doubleValue;

		try {
			doubleValue = ((NumericPlainDocument) getDocument()).getDoubleValue();
		} catch (ParseException e) {
			doubleValue = 0.0;
			e.printStackTrace();
		}

		return doubleValue;
	}

	public Double getValue() {
		return getDoubleValue();
	}

	public Number getNumberValue() {

		Number numberValue;

		try {
			numberValue = ((NumericPlainDocument) getDocument()).getNumberValue();
		} catch (ParseException e) {
			numberValue = 0;
			e.printStackTrace();
		}

		return numberValue;
	}

	// Methods to install numeric values
	public void setValue(Number number) {
		setText(getFormat().format(number));
	}

	public void setValue(long l) {
		setText(getFormat().format(l));
	}

	public void setValue(double d) {
		setText(getFormat().format(d));
	}

	public String normalize(String txt) {
		String result = "";
		try {
			if (StringUtils.isValid(txt) == false) {
				txt = "0";
			}

			txt = StringUtils.replaceAll(txt, ",", "");

			if (StringUtils.isDouble(txt) == false) {
				txt = "0";
			}

			result = getFormat().format(Double.parseDouble(txt));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	// Override to handle insertion error
	public void insertFailed(NumericPlainDocument doc, int offset, String str, AttributeSet a) {
		// By default, just beep
		Toolkit.getDefaultToolkit().beep();
	}

	// Method to create default model
	protected Document createDefaultModel() {
		return new NumericPlainDocument();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.text.ImeText#setEditable(boolean)
	 */
	public void setEditable(boolean b) {
		super.setEditable(b);
		if (b == true) {
			setFocusable(true);
			setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
		} else {
			setFocusable(false);
			setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent e) {
		if (this.isEditable() == true)
			this.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
		else
			this.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);

		setText(normalize(getText()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent e) {
		if (this.isEditable() == true) {
			this.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
			selectAll();

			// chi set kieu go vao la LATIN
			Character.Subset[] subsets = null;
			subsets = new Character.Subset[] { java.awt.im.InputSubset.LATIN_DIGITS };
			if(	getInputContext() != null)
				getInputContext().setCharacterSubsets(subsets);

		} else {
			this.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {

		numberTextBefore = getText();
		
		if (isBeingPressed == true) {
			setText(numberTextResult);
			return;
		} else {
			isBeingPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Make the ENTER key act like the TAB key
			transferFocus();
			return;
		}
		int key = e.getKeyCode();

//		System.out.println("---------keyPressed-----------" + numberTextResult + "   " + key + "-------------------------------");

		// xu ly nhung fi'm dac biet o trong key press
		// xu ly khi bam ky tu o trong key release
		if ((key != KeyEvent.VK_DELETE) 
				&& (key != KeyEvent.VK_BACK_SPACE) 
				&& (key != KeyEvent.VK_MINUS) 
				&& (key != 109) 
				&& (key != KeyEvent.VK_PLUS)
				&& (key != 107)
				&& (key != 59)) {
			return;
		}

		// vi tri cua dau .
		int positionPoint = 999;
		// vi tri cua con tro hien thoi
		int positionCaret = 0;
		// so luong so nguyen trong text hien thoi
		int intDigits = StringUtils.getLengthInteger(numberTextResult);
		// so luong so thuc trong text hien thoi
		int fracDigits = StringUtils.getLengthFraction(numberTextResult);
		// vi tri cua dau .
		positionPoint = -1 == numberTextResult.indexOf(".") ? 999 : numberTextResult.indexOf(".") + 1;
		// vi tri cua con tro hien thoi
		positionCaret = getCaretPosition();
		boolean isEditingInt = positionCaret < positionPoint;

		if (intDigits == 0 && fracDigits == 0 && isEditingInt) {
//			System.out.println("---------keyPressed A-----------" + numberText + "   " + e.getKeyCode() + "-------------------------------");
			numberTextResult = "";
			return;
		} else {
//			System.out.println("---------keyPressed B-----------" + numberText + "   " + e.getKeyCode() + "-------------------------------");
			numberTextResult = numberTextBefore;
		}
//		 System.out.println("---------keyPressed 0-----------" + numberText + "   " + e.getKeyCode() + "-------------------------------");

		try {
			if (getSelectedText() != null) {
				numberTextResult = numberTextResult.substring(0, getSelectionStart()) + numberTextResult.substring(getSelectionEnd());
//				 System.out.println("---------keyPressed 1-----------" + numberTextResult + "   " + e.getKeyCode() + "-------------------------------");
			} else {
//				 System.out.println("---------keyPressed 2-----------" + numberTextResult + "   " + e.getKeyCode() + "-------------------------------");

				if (key == KeyEvent.VK_MINUS || key == 109) {
					// System.out.println("VK_MINUS start:" + positionCaret);
					if ("-".equals(numberTextResult.substring(0, 1)) && intDigits > 0) {
						numberTextResult = numberTextResult.substring(1);
					} else if (!"-".equals(numberTextResult.substring(0, 1)) && intDigits > 0) {
						numberTextResult = "-" + numberTextResult;
					}
					if (positionCaret == 0) {
						isModify = true;
					}
					// System.out.println("VK_MINUS after:" + numberText);
					return;
				}

				if (key == KeyEvent.VK_PLUS || key == 107 || key == 59) {
					// System.out.println("VK_PLUS start:" + numberText);
					if ("-".equals(numberTextResult.substring(0, 1)) && intDigits > 0) {
						numberTextResult = numberTextResult.substring(1);
						isModify = true;
					}
					// System.out.println("VK_PLUS after:" + numberText);
					return;
				}

//				 System.out.println("---------keyPressed 3-----------" + numberText + "   " + e.getKeyCode() + "-------------------------------");

				if (isEditingInt) {
//					System.out.println("isEditingInt positionCaret: " + positionCaret + " numberText" + numberTextResult);

					if (intDigits < 4 && fracDigits == 0)
						return;

					if (key == KeyEvent.VK_BACK_SPACE && positionCaret > 0) {
						// System.out.println("VK_BACK_SPACE start:" + numberText);
						numberTextResult = numberTextResult.substring(0, positionCaret - 1) + numberTextResult.substring(positionCaret);
						// System.out.println("VK_BACK_SPACE after:" + numberText);
					} else if (key == KeyEvent.VK_DELETE) {
						// System.out.println("VK_DELETE start:" + numberText);
						if (",".equalsIgnoreCase(numberTextResult.substring(positionCaret, positionCaret + 1))) {
							numberTextResult = numberTextResult.substring(0, positionCaret) + numberTextResult.substring(positionCaret + 2);
							// System.out.println("VK_DELETE after:" + numberText);
						} else {
							if (positionCaret == positionPoint - 1) {
								numberTextResult = numberTextResult.substring(0, positionPoint - 1) + "." + numberTextResult.substring(positionPoint + 1);
								// System.out.println("VK_DELETE after:" + numberText);
							} else {
								numberTextResult = numberTextResult.substring(0, positionCaret) + numberTextResult.substring(positionCaret + 1);
								// System.out.println("VK_DELETE after:" + numberText);
							}
						}
					}
				} else {
//					System.out.println("isEditingFlaction positionCaret: " + positionCaret + " numberText" + numberText);
					if ((key == KeyEvent.VK_BACK_SPACE)) {

						if (positionCaret == positionPoint) {
							numberTextResult = numberTextResult.substring(0, positionPoint - 2) + "." + numberTextResult.substring(positionPoint);
						} else {
							numberTextResult = numberTextResult.substring(0, positionCaret - 1) + "0" + numberTextResult.substring(positionCaret);
						}
						// System.out.println("VK_BACK_SPACE after:" + numberText);
					} else if (key == KeyEvent.VK_DELETE && positionCaret < numberTextResult.length()) {
						// System.out.println("VK_DELETE start:" + numberText);
						numberTextResult = numberTextResult.substring(0, positionCaret) + numberTextResult.substring(positionCaret + 1);
						// System.out.println("VK_DELETE after:" + numberText);
					}

					if(positionCaret == numberTextResult.length()){
						return;
					}
					
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		numberTextResult = normalize(numberTextResult);
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {

		if (isBeingPressed == true) {
			isBeingPressed = false;
		} else {
			return;
		}

		int key = e.getKeyCode();

		if ((key == KeyEvent.VK_ENTER)
			|| (key == KeyEvent.VK_TAB) 
			|| (key == KeyEvent.VK_SHIFT) 
			|| e.isActionKey()) {
			return;
		}
		// xu ly nhung fi'm dac biet nay o trong key press
		if ((key != KeyEvent.VK_DELETE) 
				&& (key != KeyEvent.VK_BACK_SPACE) 
				&& (key != KeyEvent.VK_MINUS) 
				&& (key != KeyEvent.VK_PLUS)
				&& (key != 109) 
				&& (key != 107) 
				&& (key != 59)) {
			numberTextResult = getText();
			isSpecialKey = false;
		} else {
			isSpecialKey = true;
		}

		// vi tri cua dau .
		int positionPoint = 999;
		// vi tri cua con tro hien thoi
		int positionCaret = 0;
		// so luong so nguyen trong text hien thoi
		int intDigits = StringUtils.getLengthInteger(numberTextResult);
		// vi tri cua dau .
		positionPoint = -1 == numberTextResult.indexOf(".") ? 999 : numberTextResult.indexOf(".") + 1;
		// vi tri cua con tro hien thoi
		positionCaret = getCaretPosition();
		
		boolean isEditingInt = positionCaret < positionPoint;

		if (getSelectedText() == null) {
			try {
				/**********************
				 * Xu li nhung ky tu dac biet
				 **********************/
				//1. dau .
				if ((key == KeyEvent.VK_PERIOD)
						|| (key == KeyEvent.VK_DECIMAL)) {
					setCaretPosition(positionPoint);
					return;
				}
				
				//2. dau +
				if (key == KeyEvent.VK_PLUS
						|| key == 107
						|| key == 59) {
					// System.out.println("VK_PLUS start:" + numberText);
					if (isModify) {
						setText(numberTextResult);
						setCaretPosition(positionCaret - 1);
					}
					// System.out.println("VK_PLUS after:" + numberText);
					isModify = false;
					return;
				}
				
				//3. dau -
				if (key == KeyEvent.VK_MINUS
						|| key == 109) {
					// System.out.println("VK_MINUS start:" + positionCaret);
					if ("-".equals(numberTextResult.substring(0, 1)) && intDigits > 0) {
						if (isModify) {
							positionCaret = 1;
						} else {
							positionCaret = positionCaret + 1;
						}
						// setCaretPosition(positionCaret);
					} else if (!"-".equals(numberTextResult.substring(0, 1)) && intDigits > 0) {
						positionCaret = positionCaret - 1;
					}
					// System.out.println("VK_MINUS after:" + numberText);
					setText(numberTextResult);
					setCaretPosition(positionCaret);
					isModify = false;
					return;
				}

				//truong hop dac biet
				if (!isSpecialKey){
					if (!isEditingInt){
//					System.out.println("oeoe1: " + numberTextResult);					
						numberTextResult = getText().substring(0,numberTextResult.length()-1);
//					System.out.println("oeoe2: " + numberTextResult);					
					} else {
						if (intDigits > this.integerDigits) {
//						System.out.println("oeoe 3: " + numberTextResult);					
							numberTextResult = numberTextBefore;
							setText(numberTextResult);
							setCaretPosition(positionCaret - 1);
//						System.out.println("oeoe 4: " + numberTextResult);
							return;
						} 
					}
				}

				setText(numberTextResult);

				/**********************
				 * Dang sua fan nguyen
				 **********************/
				if (isEditingInt) {
//					 System.out.println("isEditingInt");

					if ((key == KeyEvent.VK_BACK_SPACE)) {
						if (intDigits == 0) {
							// System.out.println("VK_BACK_SPACE FIRST:" + positionCaret);
							setCaretPosition(1);
						} else {
							// System.out.println("VK_BACK_SPACE LAST:" + positionCaret);
							setCaretPosition(positionCaret);
						}
					} else if ((key == KeyEvent.VK_DELETE)) {
						if (intDigits == 0)
							setCaretPosition(1);
						else
							setCaretPosition(positionCaret);
					} else {
						if (intDigits == 0) {
							setCaretPosition(1);
							return;
						} else if (intDigits == 1 && intDigits < this.integerDigits) {
							setCaretPosition(1);
							return;
						} else if (intDigits < 4) {
							setCaretPosition(positionCaret);
							return;
						}

						if ((intDigits - 1) % 3 == 0) {
							setCaretPosition(positionCaret + 1);
						} else {
							setCaretPosition(positionCaret);
						}
					}
				} else {
					/**********************
					 * Dang sua fan thap fan
					 **********************/
//					System.out.println("positionCaret:" + positionCaret + " positionPoint:" + positionPoint);
					if ((key == KeyEvent.VK_BACK_SPACE)) {
						if (positionCaret == positionPoint) {
							setCaretPosition(positionPoint - 1);
						} else
							setCaretPosition(positionCaret);
					} else if ((key == KeyEvent.VK_DELETE)) {
						setCaretPosition(positionCaret);
						return;
					} else {
						if (positionCaret == numberTextResult.length() - 1) {
//							System.out.println("case1");
							setCaretPosition(numberTextResult.length() - 1);
						} else if (positionCaret > numberTextResult.length() - 1) {
//							System.out.println("case2");
							setCaretPosition(numberTextResult.length());
						} else {
//							System.out.println("case3");
							setCaretPosition(positionCaret);
						}
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return;
	}

	// Test code
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}

		JFrame f = new JFrame("Numeric Text Field Example");
		final DoubleText tf = new DoubleText("", 6, 3);

		tf.setValue((double) 123456.789);

		JLabel lbl = new JLabel("Type a number: ");
		f.getContentPane().add(tf, "East");
		f.getContentPane().add(lbl, "West");

		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					// tf.normalize();
					Long l = tf.getLongValue();
					System.out.println("Value is (Long)" + l);
				} catch (ParseException e1) {
					Double d = tf.getDoubleValue();
					System.out.println("Value is (Double)" + d);
				}
			}
		});
		f.setSize(400, 100);
		f.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD> <BR>
 * 
 * @author KIKAKU7
 * 
 */
class NumericPlainDocument extends PlainDocument {
	/** */
	private static final long serialVersionUID = 1L;

	public NumericPlainDocument() {
		setFormat(null);
	}

	public NumericPlainDocument(DecimalFormat format) {
		setFormat(format);
	}

	public NumericPlainDocument(AbstractDocument.Content content, DecimalFormat format) {
		super(content);
		setFormat(format);

		try {
			format.parseObject(content.getString(0, content.length()), parsePos);
		} catch (Exception e) {
			throw new IllegalArgumentException("Initial content not a valid number");
		}

		if (parsePos.getIndex() != content.length() - 1) {
			throw new IllegalArgumentException("Initial content not a valid number");
		}
	}

	public void setFormat(DecimalFormat fmt) {
		this.format = fmt != null ? fmt : (DecimalFormat) defaultFormat.clone();

		decimalSeparator = format.getDecimalFormatSymbols().getDecimalSeparator();
		groupingSeparator = format.getDecimalFormatSymbols().getGroupingSeparator();
		positivePrefix = format.getPositivePrefix();
		positivePrefixLen = positivePrefix.length();
		negativePrefix = format.getNegativePrefix();
		negativePrefixLen = negativePrefix.length();
		positiveSuffix = format.getPositiveSuffix();
		positiveSuffixLen = positiveSuffix.length();
		negativeSuffix = format.getNegativeSuffix();
		negativeSuffixLen = negativeSuffix.length();
	}

	public DecimalFormat getFormat() {
		return format;
	}

	public Number getNumberValue() throws ParseException {
		try {
			String content = getText(0, getLength());
			parsePos.setIndex(0);
			Number result = format.parse(content, parsePos);
			if (parsePos.getIndex() != getLength()) {
				throw new ParseException("Not a valid number: " + content, 0);
			}

			return result;
		} catch (BadLocationException e) {
			throw new ParseException("Not a valid number", 0);
		}
	}

	public Long getLongValue() throws ParseException {
		Number result = getNumberValue();
		if ((result instanceof Long) == false) {
			throw new ParseException("Not a valid long", 0);
		}

		return (Long) result;
	}

	public Integer getIntValue() throws ParseException {
		Number result = getNumberValue();
		if ((result instanceof Long) == false) {
			throw new ParseException("Not a valid long", 0);
		}
		String strResult = "" + result;
		return NumberUtils.getIntFromString(strResult.substring(0, strResult.indexOf(".")));
	}

	public Double getDoubleValue() throws ParseException {
		Number result = getNumberValue();
		if ((result instanceof Long) == false && (result instanceof Double) == false) {
			throw new ParseException("Not a valid double", 0);
		}

		if (result instanceof Long) {
			result = new Double(result.doubleValue());
		}

		return (Double) result;
	}

	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		if (str == null || str.length() == 0) {
			return;
		}

		Content content = getContent();
		int length = content.length();
		int originalLength = length;

		parsePos.setIndex(0);

		// Create the result of inserting the new data,
		// but ignore the trailing newline
		String targetString = content.getString(0, offset) + str + content.getString(offset, length - offset - 1);

		// Parse the input string and check for errors
		do {
			boolean gotPositive = targetString.startsWith(positivePrefix);
			boolean gotNegative = targetString.startsWith(negativePrefix);

			length = targetString.length();

			// If we have a valid prefix, the parse fails if the
			// suffix is not present and the error is reported
			// at index 0. So, we need to add the appropriate
			// suffix if it is not present at this point.
			if (gotPositive == true || gotNegative == true) {
				String suffix;
				int suffixLength;
				int prefixLength;

				if (gotPositive == true && gotNegative == true) {
					// This happens if one is the leading part of
					// the other - e.g. if one is "(" and the other "(("
					if (positivePrefixLen > negativePrefixLen) {
						gotNegative = false;
					} else {
						gotPositive = false;
					}
				}

				if (gotPositive == true) {
					suffix = positiveSuffix;
					suffixLength = positiveSuffixLen;
					prefixLength = positivePrefixLen;
				} else {
					// Must have the negative prefix
					suffix = negativeSuffix;
					suffixLength = negativeSuffixLen;
					prefixLength = negativePrefixLen;
				}

				// If the string consists of the prefix alone,
				// do nothing, or the result won't parse.
				if (length == prefixLength) {
					break;
				}

				// We can't just add the suffix, because part of it
				// may already be there. For example, suppose the
				// negative prefix is "(" and the negative suffix is
				// "$)". If the user has typed "(345$", then it is not
				// correct to add "$)". Instead, only the missing part
				// should be added, in this case ")".
				if (targetString.endsWith(suffix) == false) {
					int i;
					for (i = suffixLength - 1; i > 0; i--) {
						if (targetString.regionMatches(length - i, suffix, 0, i)) {
							targetString += suffix.substring(i);
							break;
						}
					}

					if (i == 0) {
						// None of the suffix was present
						targetString += suffix;
					}

					length = targetString.length();
				}
			}

			format.parse(targetString, parsePos);

			int endIndex = parsePos.getIndex();
			if (endIndex == length) {
				break; // Number is acceptable
			}

			// Parse ended early
			// Since incomplete numbers don't always parse, try
			// to work out what went wrong.
			// First check for an incomplete positive prefix
			if (positivePrefixLen > 0 && endIndex < positivePrefixLen && length <= positivePrefixLen
					&& targetString.regionMatches(0, positivePrefix, 0, length)) {
				break; // Accept for now
			}

			// Next check for an incomplete negative prefix
			if (negativePrefixLen > 0 && endIndex < negativePrefixLen && length <= negativePrefixLen
					&& targetString.regionMatches(0, negativePrefix, 0, length)) {
				break; // Accept for now
			}

			// Allow a number that ends with the group
			// or decimal separator, if these are in use
			char lastChar = targetString.charAt(originalLength - 1);
			int decimalIndex = targetString.indexOf(decimalSeparator);
			if (format.isGroupingUsed() && lastChar == groupingSeparator && decimalIndex == -1) {
				// Allow a "," but only in integer part
				break;
			}

			if (format.isParseIntegerOnly() == false && lastChar == decimalSeparator && decimalIndex == originalLength - 1) {
				// Allow a ".", but only one
				break;
			}

			// No more corrections to make: must be an error
			if (errorListener != null) {
				errorListener.insertFailed(this, offset, str, a);
			}
			return;
		} while (true == false);

		// Finally, add to the model
		super.insertString(offset, str, a);
	}

	public void addInsertErrorListener(InsertErrorListener l) {
		if (errorListener == null) {
			errorListener = l;
			return;
		}
		throw new IllegalArgumentException("InsertErrorListener already registered");
	}

	public void removeInsertErrorListener(InsertErrorListener l) {
		if (errorListener == l) {
			errorListener = null;
		}
	}

	public interface InsertErrorListener {
		public abstract void insertFailed(NumericPlainDocument doc, int offset, String str, AttributeSet a);
	}

	protected InsertErrorListener errorListener;

	protected DecimalFormat format;

	protected char decimalSeparator;

	protected char groupingSeparator;

	protected String positivePrefix;

	protected String negativePrefix;

	protected int positivePrefixLen;

	protected int negativePrefixLen;

	protected String positiveSuffix;

	protected String negativeSuffix;

	protected int positiveSuffixLen;

	protected int negativeSuffixLen;

	protected ParsePosition parsePos = new ParsePosition(0);

	protected static DecimalFormat defaultFormat = new DecimalFormat();
}