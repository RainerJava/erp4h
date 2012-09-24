/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：CalendarTable.java
 *
 *     記述				：
 *     
 *     作成日			：2009/10/13   
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.jface.table;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import com.fas.common.constants.screen.ColorConstants;

public class CalendarTable extends JTable {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected TableCellRenderer genericRenderer = new GenericCellRenderer();
	/** */
	protected TableCellEditor genericEditor = new GenericCellEditor();	
	/** */
	protected CalendarTableHeaderRenderer calHeaderRender = new CalendarTableHeaderRenderer();
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public CalendarTable(){
		super();
		init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param row
	 * @param col
	 */
	public CalendarTable(int row,int col){
		this(new DefaultTableModel(row,col));
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param model
	 */
	public CalendarTable(TableModel model){
		super(model);
		setCellSelectionEnabled(true);
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
	void init(){
		getTableHeader().setDefaultRenderer(calHeaderRender);
		setUI(new BasicTableUI());
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void updateUIorCells() {
		
		int numcolumns = this.getColumnCount();
		int numRows = this.getRowCount();
		
		for(int col = 0; col<numcolumns; col++) {
			for(int row=0;row<numRows;row++) {
				Object o=getValueAt(row,col);
				if(o instanceof JComponent) {
					JComponent component=(JComponent)o;
					component.updateUI();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.JTable#updateUI()
	 */
	public void updateUI(){
		updateUIorCells();
		repaint();
	}

	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	protected class GenericCellRenderer extends DefaultTableCellRenderer {

		/** */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int col){
			if(value instanceof JComponent==false) {
				return (super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col));
			}
			JComponent c=(JComponent)value;
			return c;
		}
	}

	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	protected class GenericCellEditor extends AbstractCellEditor implements TableCellEditor {
		
		/** */
		private static final long serialVersionUID = 1L;
		
		/** */
		protected JComponent c = null;

		/* (non-Javadoc)
		 * @see javax.swing.CellEditor#getCellEditorValue()
		 */
		public Object getCellEditorValue() {
			return c;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
		 */
		public Component getTableCellEditorComponent(JTable table,Object value, boolean isSelected, int row, int col) {
			c= (JComponent) value;
			return c;
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.JTable#getCellRenderer(int, int)
	 */
	public TableCellRenderer getCellRenderer(int row,int col) {
		
		TableCellRenderer renderer;

		Object o=getValueAt(row,col);
		if(o instanceof JComponent) {
			renderer = genericRenderer;
		} else {
			renderer = super.getCellRenderer(row,col);
		}
		return renderer;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#getCellEditor(int, int)
	 */
	public TableCellEditor getCellEditor(int row,int col) {
		
		TableCellEditor editor;
		Object o=getValueAt(row,col);
		if(o instanceof JComponent) {
			editor=genericEditor;
		} else {
			editor=super.getCellEditor(row,col);
		}
		return editor;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JTable#setDefaultRenderer(java.lang.Class, javax.swing.table.TableCellRenderer)
	 */
	public void setDefaultRenderer(Class arg0, TableCellRenderer arg1) {
		genericRenderer = arg1;
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
	public TableCellRenderer getHeaderRenderer() {
		return calHeaderRender;
	}
	
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	protected class CalendarTableHeaderRenderer extends JLabel implements TableCellRenderer {
		
		  /** */
		private static final long serialVersionUID = 1L;

		  public CalendarTableHeaderRenderer() {
			    setOpaque(true);
			    setForeground(UIManager.getColor("TableHeader.foreground"));
			    setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
			    setBorder(null);
			    setHorizontalAlignment(JLabel.CENTER);
		  }
		  
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int rowIndex, int vColIndex) {
			
		    setText((value == null) ? "" : value.toString());
			if (vColIndex == 0) {
				setForeground(ColorConstants.CALENDAR_NICHIYOUBI_COLOR);
			} else if (vColIndex == 6) {
				setForeground(ColorConstants.CALENDAR_DOYOUBI_COLOR);
			} else {
				setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
			}
		    return this;
		}
	}
}
