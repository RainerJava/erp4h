package com.fas.jface.table.frozenTable;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import com.fas.common.utils.StringUtils;
import com.fas.jface.text.IntegerNumberText;

public class HaCellIntegerTextBoxEditorTrOrdrH extends DefaultCellEditor implements TableCellEditor {
	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private IntegerNumberText txtInput;	
	
	public HaCellIntegerTextBoxEditorTrOrdrH(IntegerNumberText txtInput) {
		super(txtInput);
		this.txtInput = txtInput;
	
		init();
	}

	private void init() {		
		txtInput.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInput.setAlignmentX(SwingConstants.RIGHT);
	}
	
	public void setMinValue (int iMinValue)
	{
		txtInput.setMinValue(iMinValue);
	}
	
	public void setMaxValue (int iMaxValue)
	{
		txtInput.setMaxValue(iMaxValue);
	}
	
	public void setText(String str) {
		txtInput.setText(str);
	}

	public String getText() {
		return txtInput.getText();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.txtInput.setText(StringUtils.objectToStr(value));
		return this.txtInput;
	}

	public Object getCellEditorValue() {
		return getText();
	}
	
	public void requestFocus() {
		txtInput.requestFocus();
	}	
}
