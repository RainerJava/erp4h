package com.fas.jface.table;

import java.awt.Component;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;

public class BTable extends JTable {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected BTableCellRenderer cellRenderer = new BTableCellRenderer();
	/** */
	protected BTableHeaderRenderer headerRender = new BTableHeaderRenderer();
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BTable(){
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
	public BTable(int row, int col){
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
	public BTable(TableModel model){
		super(model);
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
	void init() {
		getTableHeader().setDefaultRenderer(headerRender);
		setDefaultRenderer(BTableCellRenderer.class, cellRenderer);
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
	}

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

	/* (non-Javadoc)
	 * @see javax.swing.JTable#getCellRenderer(int, int)
	 */
	public TableCellRenderer getCellRenderer(int row,int col) {
		return cellRenderer;
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
	protected class BTableHeaderRenderer extends JLabel implements TableCellRenderer {
		
		  /** */
		private static final long serialVersionUID = 1L;

		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 */
		public BTableHeaderRenderer() {
			setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
			setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
			setBorder(FaceContants.TABLE_HEADER_BORDER);
		}
		  
		/* (non-Javadoc)
		 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int rowIndex, int vColIndex) {
			
		    setText((value == null) ? "" : value.toString());

		    return this;
		}
	}
}
