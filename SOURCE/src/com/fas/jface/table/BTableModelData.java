package com.fas.jface.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.fas.vo.base.SortObjVo;

public abstract class BTableModelData extends AbstractTableModel {
	
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected ColumnData m_columns[];
	/** */
	protected List mLstData;
    /** */
    protected SortObjVo sortObj;
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BTableModelData() {
		init();
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	private void init() {
		if (sortObj == null) {
			sortObj = new SortObjVo();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
	    return mLstData == null ? 0 : mLstData.size(); 
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
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
	    if (m_columns[column].isRequidSort()) {
			if (column==sortObj.getM_sortCol()) {
				headerTitle += sortObj.isM_sortAsc() ? " △" : " ▽";
			}
	    }
	    
		return headerTitle;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
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
	 *   <DT>メソッド記述：</DT>
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
	    
	    return mLstData.get(nRow);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public abstract int retrieveData();
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	protected abstract void doRefreshTable();

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
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
	     *   <DT>コンストラクター記述：</DT>
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
		    boolean isReSort = false;
		    
		    if (modelIndex < 0)
		    	return;
		    if (sortObj.getM_sortCol() == modelIndex)
		    	sortObj.setM_sortAsc(!sortObj.isM_sortAsc());
		    else
		    	sortObj.setM_sortCol(modelIndex);
		    
		    for (int i = 0; i < m_columns.length; i++) {
	    		TableColumn column = colModel.getColumn(i);
	    	  	column.setHeaderValue(getColumnName(i));
	    	  	if (m_columns[i].isRequidSort()) {
	    	  		isReSort = true;
	    	  	}
		    }

		    if (isReSort) {
		    	doRefreshTable();
		    }
		    
		    return;
		    
	    }
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the m_columns
	 */
	public ColumnData[] getM_columns() {
		return m_columns;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param m_columns the m_columns to set
	 */
	public void setM_columns(ColumnData[] m_columns) {
		this.m_columns = m_columns;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the mLstData
	 */
	public List<?> getMLstData() {
		return mLstData;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lstData the mLstData to set
	 */
	public void setMLstData(List<?> lstData) {
		mLstData = lstData;
	}
}
