package com.fas.jface.table;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.sapp.base.SearchMasterFrame.SearchTableDataModel;
import com.fas.vo.search.SearchVo;

public class SearchTable extends JTable {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected TableCellRenderer searchRenderer = new SearchCellRenderer();
	/** */
	protected TableCellEditor genericEditor = new GenericCellEditor();	
	/** */
	protected SearchTableHeaderRenderer calHeaderRender = new SearchTableHeaderRenderer();
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchTable(){
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
	public SearchTable(int row,int col){
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
	public SearchTable(TableModel model){
		super(model);
		//setCellSelectionEnabled(true);
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
		setDefaultRenderer(SearchCellRenderer.class, searchRenderer);
		setUI(new BasicTableUI());
	  	setRowHeight(FaceContants.TABLE_ROW_HEIGHT);
	  	setAutoCreateColumnsFromModel(false);
	  	setSelectionBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
	  	setSelectionForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
	    getTableHeader().setUpdateTableInRealTime(false);
	  	getTableHeader().setReorderingAllowed(false);
	  	getTableHeader().setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
	  	getTableHeader().setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
	  	setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
	  	getTableHeader().setSize(getWidth(), FaceContants.TABLE_HEADER_HEIGHT);
	  	setRowSelectionAllowed(true);
	  	setGridColor(ColorConstants.TABLE_LINE_COLOR);
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
		return searchRenderer;
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
	protected class SearchTableHeaderRenderer extends JLabel implements TableCellRenderer {
		
		  /** */
		private static final long serialVersionUID = 1L;

		public SearchTableHeaderRenderer() {
			setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
			setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
			setBorder(FaceContants.TABLE_HEADER_BORDER);
		}
		  
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int rowIndex, int vColIndex) {
			
		    setText((value == null) ? "" : value.toString());

		    return this;
		}
	}
	
	protected class SearchCellRenderer extends DefaultTableCellRenderer {

		/** */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			JComponent renderer;
			
			if(value instanceof JComponent == false) {
				Component comp = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
				if (comp instanceof JComponent) {
					renderer = (JComponent) comp;
				} else {
					return comp;
				}
			} else {
				renderer = (JComponent) value;
			}
			
			TableModel tModel = table.getModel();
			SearchVo searchVo = null;
			
			if (tModel instanceof SearchTableDataModel) {
				SearchTableDataModel searchModel = (SearchTableDataModel) tModel;
				Object obj = searchModel.getValueAtRow(row);
				if (obj instanceof SearchVo) {
					searchVo = (SearchVo) obj;
				}
			}
			
			if (row != table.getSelectedRow()) {
				if (row % 2 == 0) {
					renderer.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
					renderer.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
				} else {
					renderer.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
					renderer.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
				}
			}
			
			if (searchVo != null && "1".equalsIgnoreCase(searchVo.getUseFlg())) {
				renderer.setForeground(ColorConstants.TABLE_SHIYOUFUKA_FORE_COLOR);
			}
			
			return renderer;
		}
	}
}