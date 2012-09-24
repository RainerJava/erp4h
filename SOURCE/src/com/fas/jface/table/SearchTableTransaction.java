package com.fas.jface.table;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputAdapter;
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

public class SearchTableTransaction extends JTable {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
//	protected TableCellRenderer searchRenderer = new SearchCellRenderer();
	protected DefaultTableCellRenderer searchRenderer;
	/** */
	protected TableCellEditor genericEditor = new GenericCellEditor();
	/** */
	private boolean isBorderRequire = true;
	 
	private int preSelectedRow = 0;
	private int curSelectedRow = 0;
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
	public SearchTableTransaction(){
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
	public SearchTableTransaction(int row,int col){
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
	public SearchTableTransaction(TableModel model){
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
	  	
	  	isBorderRequire = true;
	 // set border when focus
		this.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent fe) {
		    	if (isBorderRequire) {
		    		setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR));
		    		getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
		    		getTableHeader().setBackground(ColorConstants.TABLE_HEADER_FOCUS_BACKGROUND_COLOR);
		    	}
		    }
		    @Override
		    public void focusLost(FocusEvent fe) {
		    	if (isBorderRequire) {
			    	setBorder(BorderFactory.createEmptyBorder());
					getTableHeader().setBorder(new EtchedBorder(EtchedBorder.LOWERED));
					getTableHeader().setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
		    	}
		    }
		});
//		// add mouseMoved event
//		this.addMouseMotionListener(new MouseInputAdapter (){
//			public void mouseMoved(MouseEvent e) {
////				for (int x = 0; x < getRowCount(); x++){
////					if(curSelectedRow != x){
////						m_table[x].setBorder(null);
////						 if (x % 2 == 0) {
////							 m_table.get).setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
////								paneArrRows[x].setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
////						} else {
////								paneArrRows[x].setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
////								paneArrRows[x].setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
////						}									
////						
////						paneArrRows[x].setAlpha(0.5f);	 
////					}
////				 }
////				
////				 for (int x = 0; x < TABLE_ROWS; x++)
////				 {
////					 if(curSelectedRow != x)
////					 {
////						for (int y = 0; y < TABLE_COLUMNS; y++) {
////							if (cell.hashCode() == arrCellComponent[x][y].hashCode()) {
////								// set current focus panel border
//////								setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR));
////								setBackground(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR);
////								setForeground(ColorConstants.TABLE_ROLLOVER_FOREGROUND_COLOR);
//////								setAlpha(1f);
////							}
////						}
////					 }
////				 }
//				// get the coordinates of the mouse click
//				Point p = e.getPoint();
//	 
//				// get the row index that contains that coordinate
//				int rowNumber = rowAtPoint( p );
//				int colNumber = columnAtPoint(p);
//	 
//				// Get the ListSelectionModel of the JTable
//				ListSelectionModel model = getSelectionModel();
//	 
//				// set the selected interval of rows. Using the "rowNumber"
//				// variable for the beginning and end selects only that one row.
////				model.setSelectionInterval( rowNumber, rowNumber );
////				getCellRenderer(rowNumber, colNumber)
//				get
//
//				
//			}
//		});
		
//		cell.addFocusListener(new FocusListener(){
//			public void focusGained(FocusEvent e) {
//				for (int x = 0; x < TABLE_ROWS; x++)
//					for (int y = 0; y < TABLE_COLUMNS; y++) {
//						if (cell.hashCode() == arrCellComponent[x][y].hashCode()) {
//							// set current focus panel border
//							curSelectedRow = x;
//							paneArrRows[x].setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR));
//							paneArrRows[x].setBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
//							paneArrRows[x].setForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
//							
//							paneArrRows[x].setAlpha(1f);
//						}
//					}
//
//				if (preSelectedRow != curSelectedRow) {
//					preSelectedRow = curSelectedRow;
//					Rectangle r = new Rectangle(0, 0, paneArrRows[curSelectedRow].getWidth(), paneArrRows[curSelectedRow].getHeight());
//					paneArrRows[curSelectedRow].scrollRectToVisible(r);
//				}
//			}
//
//			/*
//			 * (non-Javadoc)
//			 * 
//			 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
//			 */
//			public void focusLost(FocusEvent e) {
//				paneArrRows[preSelectedRow].setBorder(null);
//				if (preSelectedRow % 2 == 0) {
//					paneArrRows[preSelectedRow].setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
//					paneArrRows[preSelectedRow].setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
//				} else {
//					paneArrRows[preSelectedRow].setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
//					paneArrRows[preSelectedRow].setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
//				}
//				
//				if (preSelectedRow >= TABLE_ROWS){
//					paneArrRows[preSelectedRow].setAlpha(0.5f);
//				} else {
//					paneArrRows[preSelectedRow].setAlpha(1f);
//				}
//
//				// reset background color for component
//				for (Component comp : arrCellComponent[preSelectedRow]) {
//
//					if (comp instanceof JLabel)
//						continue;
//
//					if (preSelectedRow % 2 == 0) {
//						comp.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
//						comp.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
//					} else {
//						comp.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
//						comp.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
//					}
//				}
//			}
//		});
		
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

	/**
	 * @return the searchRenderer
	 */
	public DefaultTableCellRenderer getSearchRenderer() {
		return searchRenderer;
	}

	/**
	 * @param searchRenderer the searchRenderer to set
	 */
	public void setSearchRenderer(DefaultTableCellRenderer searchRenderer) {
		this.searchRenderer = searchRenderer;
	}

	/**
	 * @return the isBorderRequire
	 */
	public boolean isBorderRequire() {
		return isBorderRequire;
	}

	/**
	 * @param isBorderRequire the isBorderRequire to set
	 */
	public void setBorderRequire(boolean isBorderRequire) {
		this.isBorderRequire = isBorderRequire;
	}

	
	
}