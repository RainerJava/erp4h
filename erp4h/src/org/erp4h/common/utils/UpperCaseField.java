package org.erp4h.common.utils;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class UpperCaseField extends JTextField {

	static class UpperCaseDocument extends PlainDocument {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void insertString(int offs, String str, AttributeSet a) 
            throws BadLocationException {

            if (str == null) {
                return;
            }
            char[] upper = str.toCharArray();
            for (int i = 0; i < upper.length; i++) {
                upper[i] = Character.toUpperCase(upper[i]);
            }
            super.insertString(offs, new String(upper), a);
        }
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public UpperCaseField(int cols) {
        super(cols);
    }

    @Override
	protected Document createDefaultModel() {
        return new UpperCaseDocument();
    }
}
