package com.fas.jface.table;

public class ColumnData {
	
	 public String  m_title;
	 public int     m_width;
	 public int     m_alignment;
	 public boolean requidSort = true;
	  
	 /**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param title
	 * @param width
	 * @param alignment
	 */
	 public ColumnData(String title, int width, int alignment) {
		  m_title = title;
		  m_width = width;
		  m_alignment = alignment;
	 }
	  
	 /**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param title
	 * @param width
	 * @param alignment
	 * @param boolSort
	 */
	public ColumnData(String title, int width, int alignment, boolean boolSort) {
		  m_title = title;
		  m_width = width;
		  m_alignment = alignment;
		  requidSort = boolSort;
	 }
		 
	  /**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	 public ColumnData() {
	 }

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the m_title
	 */
	public String getM_title() {
		return m_title;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param m_title the m_title to set
	 */
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the m_width
	 */
	public int getM_width() {
		return m_width;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param m_width the m_width to set
	 */
	public void setM_width(int m_width) {
		this.m_width = m_width;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the m_alignment
	 */
	public int getM_alignment() {
		return m_alignment;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param m_alignment the m_alignment to set
	 */
	public void setM_alignment(int m_alignment) {
		this.m_alignment = m_alignment;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the requidSort
	 */
	public boolean isRequidSort() {
		return requidSort;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param requidSort the requidSort to set
	 */
	public void setRequidSort(boolean requidSort) {
		this.requidSort = requidSort;
	}
}
