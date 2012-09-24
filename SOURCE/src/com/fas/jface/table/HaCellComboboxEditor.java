/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：エスイーシー化成
 * 
 *     ファイル名		：HaCellComboboxEditor.java
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.fas.common.utils.StringUtils;
import com.fas.jface.combo.BaseComboBox;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC12</DD><BR>
 * <DD></DD>
 * </DL>
 */

public class HaCellComboboxEditor extends DefaultCellEditor implements TableCellEditor {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private BaseComboBox cbbInput;

	/** */
	private InspectTable m_table;

	public HaCellComboboxEditor(BaseComboBox cbbInput, InspectTable table) {
		super(cbbInput);
		this.cbbInput = cbbInput;
		this.m_table = table;
		init();
	}

	private void init() {

		cbbInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				stopCellEditing();
			}
		});

		cbbInput.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyPressed(KeyEvent e) {

				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_TAB) {
					stopCellEditing();
					movePreviousCell();
				}

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					stopCellEditing();
					moveNextCell();
				}
				
//				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
//					stopCellEditing();
//					movePreviousCell();
//				}
//				
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
//					stopCellEditing();
//					moveNextCell();
//				}
				
//				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//					stopCellEditing();
//					moveDownCell();
//				}
//				
//				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_DOWN) {
//					stopCellEditing();
//					moveUpCell();
//				}
//				
//				if (e.getKeyCode() == KeyEvent.VK_UP) {
//					stopCellEditing();
//					moveUpCell();
//				}
//				
//				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_UP) {
//					stopCellEditing();
//					moveDownCell();
//				}

			}
		});

	}

	public void setSelectedItem(String str) {
		cbbInput.setSelectedItem(str);
	}

	public String getSelectedKey() {
		return cbbInput.getSelectedKey();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.cbbInput.setSelectedItem(StringUtils.objectToStr(value));
		return this.cbbInput;
	}

	public Object getCellEditorValue() {
		return getSelectedKey();
	}

	public void requestFocus() {
		cbbInput.requestFocus();
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
