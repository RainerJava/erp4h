/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：エスイーシー化成
 * 
 *     ファイル名		：HaCellIntegerTextBoxEditor.java
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
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import com.fas.common.utils.StringUtils;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.IntegerNumberText;

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

public class HaCellIntegerTextBoxEditor extends DefaultCellEditor implements TableCellEditor {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private IntegerNumberText txtInput;

	/** */
	private InspectTable m_table;

	public HaCellIntegerTextBoxEditor(IntegerNumberText txtInput, InspectTable table) {
		super(txtInput);
		this.txtInput = txtInput;
		this.m_table = table;
		init();
	}

	private void init() {
		
//		txtInput.setHorizontalAlignment(SwingConstants.RIGHT);

		txtInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopCellEditing();
			}
		});

		txtInput.addKeyListener(new KeyListener() {

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
				
				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
					stopCellEditing();
					movePreviousCell();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					stopCellEditing();
					moveNextCell();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					stopCellEditing();
					moveDownCell();
				}
				
				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_DOWN) {
					stopCellEditing();
					moveUpCell();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					stopCellEditing();
					moveUpCell();
				}
				
				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_UP) {
					stopCellEditing();
					moveDownCell();
				}

			}
		});

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
