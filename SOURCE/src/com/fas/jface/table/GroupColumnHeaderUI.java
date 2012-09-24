package com.fas.jface.table;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicTableHeaderUI;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.jface.table.TableHeaderRenderer;


public class GroupColumnHeaderUI extends BasicTableHeaderUI {
  
  public void paint(Graphics g, JComponent c) {
    Rectangle clipBounds = g.getClipBounds();
    if (header.getColumnModel() == null) return;
    ((GroupColumnHeader)header).setColumnMargin();
    int column = 0;
    Dimension size = header.getSize();
    Rectangle cellRect  = new Rectangle(0, 0, size.width , size.height);
    Hashtable h = new Hashtable();
    //int columnMargin = header.getColumnModel().getColumnMargin();
    int columnMargin = 0;
    Enumeration enumeration = header.getColumnModel().getColumns();
    while (enumeration.hasMoreElements()) {
      cellRect.height = size.height;
      cellRect.y      = 0;
      TableColumn aColumn = (TableColumn)enumeration.nextElement();
      Enumeration cGroups = ((GroupColumnHeader)header).getColumnGroups(aColumn);
      if (cGroups != null) {
        int groupHeight = 0;
        while (cGroups.hasMoreElements()) {
        	GroupColumn cGroup = (GroupColumn)cGroups.nextElement();
          Rectangle groupRect = (Rectangle)h.get(cGroup);
          if (groupRect == null) {
            groupRect = new Rectangle(cellRect);
            Dimension d = cGroup.getSize(header.getTable());
            groupRect.width  = d.width;
            groupRect.height = d.height;    
            h.put(cGroup, groupRect);
          }
          paintCell(g, groupRect, cGroup);
          groupHeight += groupRect.height;
          cellRect.height = size.height - groupHeight;
          cellRect.y      = groupHeight;
        }
      }      
      cellRect.width = aColumn.getWidth() + columnMargin;
      if (cellRect.intersects(clipBounds)) {
        paintCell(g, cellRect, column);
      }
      cellRect.x += cellRect.width;
      column++;
    }
  }

  private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
    TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
    TableHeaderRenderer renderer = new TableHeaderRenderer(){
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        
        	// set icon for header
		    setOpaque(true);
			setText(value.toString());
	        Icon sortIcon = null;
	        java.util.List<? extends RowSorter.SortKey> sortKeys = table.getRowSorter().getSortKeys();   
		    if (sortKeys.size() > 0 && sortKeys.get(0).getColumn() ==
		            table.convertColumnIndexToModel(column)) {
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
	    	setIcon(sortIcon);
	    	setForeground(table.getTableHeader().getForeground());
	        setBackground(ColorConstants.TABLE_HEADER_FOCUS_BACKGROUND_COLOR);
	        setFont(table.getTableHeader().getFont());
	       // setHorizontalAlignment(JLabel.LEFT);
	        Border TABLE_HEADER_BORDER = new EtchedBorder(EtchedBorder.LOWERED);
			setBorder(TABLE_HEADER_BORDER);
	        //setBorder(UIManager.getBorder("TableHeader.cellBorder"));	
	        
	        return this;
        }
    
    };
    
    Component c = renderer.getTableCellRendererComponent(
        header.getTable(), aColumn.getHeaderValue(),false, false, 0, columnIndex);
        
    rendererPane.add(c);
    rendererPane.paintComponent(g, c, header, cellRect.x, cellRect.y,
        cellRect.width, cellRect.height, true);
  }

  private void paintCell(Graphics g, Rectangle cellRect,GroupColumn cGroup) {
	  TableHeaderRenderer renderer = cGroup.getHeaderRenderer();
	  Component component = renderer.getTableCellRendererComponent(
	  header.getTable(), cGroup.getHeaderValue(),false, false, 0, 0);
	  rendererPane.add(component);
	  rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
	   cellRect.width, cellRect.height, true);

  }
  
  private Dimension createHeaderSize(long width) {
    TableColumnModel columnModel = header.getColumnModel();
    width += columnModel.getColumnMargin() * columnModel.getColumnCount();
    if (width > Integer.MAX_VALUE) {
      width = Integer.MAX_VALUE;
    }
  
    return new Dimension((int)width,  FaceContants.TABLE_HEADER_HEIGHT);
  }

  public Dimension getPreferredSize(JComponent c) {
    long width = 0;
    Enumeration enumeration = header.getColumnModel().getColumns();
    while (enumeration.hasMoreElements()) {
      TableColumn aColumn = (TableColumn)enumeration.nextElement();
      width = width + aColumn.getPreferredWidth();
    }
    return createHeaderSize(width);
  }
}
