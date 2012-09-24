/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：エスイーシー化成
 * 
 *     ファイル名		：HaCellCheckboxEditor.java
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

package com.fas.jface.table.frozenTable;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import com.fas.jface.checkbox.BaseCheckBox;

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

public class HaCellCheckboxEditor extends DefaultCellEditor implements TableCellEditor {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private BaseCheckBox chkInput;

	/** */
	private KTable m_table;

	public HaCellCheckboxEditor(BaseCheckBox checkBox, KTable table) {
		super(checkBox);
		this.chkInput = checkBox;
		this.m_table = table;
		init();
	}

	private void init() {
		
		chkInput.setHorizontalAlignment(SwingConstants.CENTER);

		chkInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				stopCellEditing();
			}
		});
	

		chkInput.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyPressed(KeyEvent e) {

//				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_TAB) {
//					stopCellEditing();
//					movePreviousCell();
//				}
//
//				if (e.getKeyCode() == KeyEvent.VK_TAB) {
//					stopCellEditing();
//					moveNextCell();
//				}
//				
//				if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
//					stopCellEditing();
//					movePreviousCell();
//				}
//				
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					stopCellEditing();
//					moveNextCell();
//				}
//				
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
		
		chkInput.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(!m_table.hasFocus()){
					if(chkInput.isSelected()){
						chkInput.setSelected(false);
					} else {
						chkInput.setSelected(true);
					}
				}
						
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});

	}

	public void setSelectedItem(Boolean str) {
		chkInput.setSelected(str);
	}

	public Boolean isSelected() {
		return chkInput.isSelected();
	}

	public Component getTableCellEditorComponent(JTable table, boolean value, boolean isSelected, int row, int column) {
		this.chkInput.setSelected(value);
		return this.chkInput;
	}

	public Object getCellEditorValue() {
		return isSelected();
	}

	public void requestFocus() {
		chkInput.requestFocus();
	}

//	private void moveUpCell() {
//
//		int selectedRow = m_table.getSelectedRow();
//		int selectedCol = m_table.getSelectedColumn();
//
//		if (selectedRow > 0) {
//			selectedRow--;
//		}
//		
////		System.out.println("moveUpCell " + selectedRow + " " + selectedCol);
//
//		m_table.changeSelection(selectedRow, selectedCol, false, false);
//
//	}
//
//	private void moveDownCell() {
//
//		int selectedRow = m_table.getSelectedRow();
//		int selectedCol = m_table.getSelectedColumn();
//
//		if (selectedRow + 1 < m_table.getRowCount()) {
//			selectedRow++;
//		}
//		
////		System.out.println("moveDownCell " + selectedRow + " " + selectedCol);
//
//		m_table.changeSelection(selectedRow, selectedCol, false, false);
//
//	}
//
//	private void moveNextCell() {
//
//		int selectedRow = m_table.getSelectedRow();
//		int selectedCol = m_table.getSelectedColumn();
//
//		if (selectedCol + 1 < m_table.getColumnCount()) {
//			selectedCol++;
//		} else {
//			selectedRow = (selectedRow + 1 < m_table.getRowCount()) ? selectedRow + 1 : 0;
//			selectedCol = 0;
//		}
//
////		System.out.println("moveNextCell " + selectedRow + " " + selectedCol);
//
//		m_table.changeSelection(selectedRow, selectedCol, false, false);
//
//	}
//
//	private void movePreviousCell() {
//
//		int selectedRow = m_table.getSelectedRow();
//		int selectedCol = m_table.getSelectedColumn();
//
//		if (selectedCol > 0) {
//			selectedCol--;
//		} else {
//			selectedRow = (selectedRow > 0) ? selectedRow - 1 : m_table.getRowCount() - 1;
//			selectedCol = m_table.getColumnCount() - 1;
//		}
//
////		System.out.println("movePreviousCell " + selectedRow + " " + selectedCol);
//
//		m_table.changeSelection(selectedRow, selectedCol, false, false);
//	}

}
