/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：エスイーシー化成
* 
*     ファイル名		：HaCellDatePickerEditor.java
*
*     記述				：
*     
*     作成日			：2010/07/09   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.fas.jface.text.BaseDatePicker;

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

public class HaCellDatePickerEditor extends AbstractCellEditor implements TableCellEditor {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private BaseDatePicker datePicker = new BaseDatePicker();
	
	/** */
	private InspectTable m_table;
	
	public HaCellDatePickerEditor(InspectTable m_table){
		this.m_table = m_table;
		
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopCellEditing();
				moveNextCell();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		java.util.Date date = (java.util.Date) value;
		datePicker.setDate(date);
		return datePicker;
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		return datePicker.getDate();
	}
	
	
	public void requestFocus() {
		datePicker.requestFocus();
	}

	private void moveUpCell() {

		int selectedRow = m_table.getSelectedRow();
		int selectedCol = m_table.getSelectedColumn();

		if (selectedRow > 0) {
			selectedRow--;
		}
		
//		System.out.println("moveUpCell " + selectedRow + " " + selectedCol);

		m_table.changeSelection(selectedRow, selectedCol, false, false);

	}

	private void moveDownCell() {

		int selectedRow = m_table.getSelectedRow();
		int selectedCol = m_table.getSelectedColumn();

		if (selectedRow + 1 < m_table.getRowCount()) {
			selectedRow++;
		}
		
//		System.out.println("moveDownCell " + selectedRow + " " + selectedCol);

		m_table.changeSelection(selectedRow, selectedCol, false, false);

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

//		System.out.println("moveNextCell " + selectedRow + " " + selectedCol);

		m_table.changeSelection(selectedRow, selectedCol, false, false);

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

//		System.out.println("movePreviousCell " + selectedRow + " " + selectedCol);

		m_table.changeSelection(selectedRow, selectedCol, false, false);
	}

}

