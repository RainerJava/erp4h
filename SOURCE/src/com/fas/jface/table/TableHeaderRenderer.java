package com.fas.jface.table;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import com.fas.jface.label.BaseLabel;
/**
 * @author PC14
 * 
 */
public class TableHeaderRenderer extends BaseLabel implements TableCellRenderer {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		      boolean hasFocus, int rowIndex, int vColIndex) {
		
		setOpaque(true);
		setText(value.toString());
        Icon sortIcon = null;
        
        java.util.List<? extends RowSorter.SortKey> sortKeys = table.getRowSorter().getSortKeys();
            
	    if (sortKeys.size() > 0 && sortKeys.get(0).getColumn() ==
	            table.convertColumnIndexToModel(vColIndex)) {
	        switch(sortKeys.get(0).getSortOrder()) {
	        
	        case ASCENDING:
	            sortIcon = UIManager.getIcon(
	                "Table.ascendingSortIcon");
	            break;
	        case DESCENDING:
	            sortIcon = UIManager.getIcon(
	                "Table.descendingSortIcon");
	            break;
	        case UNSORTED:
	            sortIcon = UIManager.getIcon(
	                "Table.naturalSortIcon");
	            break;
	        }
	    }
	    setHorizontalTextPosition(JLabel.LEFT);
	    //setHorizontalAlignment(JLabel.CENTER);
	    setVerticalTextPosition(JLabel.CENTER);
    	setIcon(sortIcon);
        setFont(table.getTableHeader().getFont());
        //setHorizontalTextPosition(JLabel.LEADING);
        //setHorizontalAlignment(JLabel.CENTER);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));	
        
		return this;
		 
	}
}

