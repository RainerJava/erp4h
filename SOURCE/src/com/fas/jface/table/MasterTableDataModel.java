package com.fas.jface.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.fas.vo.base.SortObjVo;

public abstract class MasterTableDataModel extends AbstractTableModel {
	
		/** */
		protected ColumnData m_columns[];
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		protected List<?> m_vector;
	    /** */
	    protected SortObjVo sortObj = new SortObjVo();
	    
		/**
		 * <DL>
		 *   <DT>�R���X�g���N�^�[�L�q�F</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public MasterTableDataModel() {
		}
	
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		public int getRowCount() {
		    return m_vector==null ? 0 : m_vector.size(); 
		}
		
		/**
		 * <DL>
		 *   <DT>���\�b�h�L�q�F</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @return
		 */
		public SortObjVo getSortObj() {
			if (sortObj == null) sortObj =  new SortObjVo(); 
			return sortObj;
		}
	
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		public int getColumnCount() { 
			return m_columns.length; 
		} 
	
		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		public String getColumnName(int column) {

			String headerTitle = m_columns[column].m_title;
		    
			if (column==sortObj.getM_sortCol()) {
				headerTitle += sortObj.isM_sortAsc() ? " ��" : " ��";
			}
		    
			return headerTitle;
		}
		
		/**
		 * <DL>
		 *   <DT>���\�b�h�L�q�F</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @return
		 */
		public ColumnData[] getColumnData() {
			return m_columns;
		}
	 
		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
		 */
		public boolean isCellEditable(int nRow, int nCol) {
			return false;
		}
	
		/**
		 * <DL>
		 *   <DT>���\�b�h�L�q�F</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param nRow
		 * @return
		 */
		public Object getValueAtRow(int nRow) {
		    if (nRow < 0)
		    	return null;
		    
		    return m_vector.get(nRow);
		}
		
		/**
		 * <DL>
		 *   <DT>���\�b�h�L�q�F</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @return
		 */
		public abstract int retrieveData(SortObjVo sortObj);

		/**
		 * <DL>
		 *   <DT>���\�b�h�L�q�F</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public MouseAdapter setMouseListener(JTable m_table) {
			return new ColumnListener(m_table);
		}
		
		/**
		 * @author PC13
		 *
		 */
		protected class ColumnListener extends MouseAdapter {
		  
		    /** */
		    protected JTable m_table;

		
		    /**
		     * <DL>
		     *   <DT>�R���X�g���N�^�[�L�q�F</DT>
		     * 		<DD></DD>
		     * <BR>
		     *
		     * </DL>
		     * @param table
		     */
		    public ColumnListener(JTable table) {
		    	m_table = table;
		    }
		
		    /* (non-Javadoc)
		     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		     */
		    public void mouseClicked(MouseEvent e) {
		    	
			    TableColumnModel colModel = m_table.getColumnModel();
			    int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
			    int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();
			
			    if (modelIndex < 0)
			    	return;
			    if (sortObj.getM_sortCol() == modelIndex)
			    	sortObj.setM_sortAsc(!sortObj.isM_sortAsc());
			    else
			    	sortObj.setM_sortCol(modelIndex);
			    
			    for (int i = 0; i < m_columns.length; i++) {
		    		TableColumn column = colModel.getColumn(i);
		    	  	column.setHeaderValue(getColumnName(i));
			    }

			    m_table.getTableHeader().repaint();  
			    retrieveData(sortObj);
			    m_table.tableChanged(new TableModelEvent(MasterTableDataModel.this)); 
			    m_table.repaint();  
			    return;
			    
		    }
	  }
}
