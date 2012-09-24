package com.fas.jface.table.frozenTable;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import com.fas.common.utils.StringUtils;
import com.fas.jface.text.DoubleText;

public class HaCellDoubleTextBoxEditorTrOrdrH extends DefaultCellEditor implements TableCellEditor {
	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private DoubleText txtInput;	
	
	private KTable m_table;
	
	public HaCellDoubleTextBoxEditorTrOrdrH(DoubleText txtInput, KTable table) {
		super(txtInput);
		this.txtInput = txtInput;
		m_table = table;
		init();
	}

	private void init() {		
		txtInput.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtInput.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyPressed(KeyEvent e) {			
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					stopCellEditing();
					moveNextCell();
				}				

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					stopCellEditing();
					movePreviousCell();
				}
			}			
			
		});

	}
	
	private void moveNextCell() {

		int selectedRow = m_table.getSelectedRow();
		int selectedCol = m_table.getSelectedColumn();

		if (selectedCol + 1 < m_table.getColumnCount()) {
			selectedCol++;
		} else {
			selectedRow = (selectedRow + 1 < m_table.getRowCount()) ? selectedRow + 1 : 0;
			selectedCol = 0;
		}
		m_table.changeSelection(selectedRow, 9, false, false);

	}
	
	private void movePreviousCell() {

		int selectedRow = m_table.getSelectedRow();
		int selectedCol = m_table.getSelectedColumn();

		if (selectedCol > 0) {
			selectedCol--;
		} else {
			selectedRow = (selectedRow > 0) ? selectedRow - 1 : m_table.getRowCount() - 1;
			selectedCol = m_table.getColumnCount() - 1;
		}
		m_table.changeSelection(selectedRow, selectedCol, false, false);
	}
	
	public void setText(String str) {
		txtInput.setText(str);
	}

	public String getText() {		
		String value = txtInput.getText();
		int pointIndex = value.indexOf(".");
		String subPoint = value.substring(pointIndex + 1, value.length());	
		char[] arrPoint = subPoint.toCharArray();		
		if(arrPoint.length > 1)
			value = txtInput.getText().substring(0, pointIndex) + "." + arrPoint[0];		
			
		return value;
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
