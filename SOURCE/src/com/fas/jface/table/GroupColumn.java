package com.fas.jface.table;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.jface.table.TableHeaderRenderer;


/**
  * ColumnGroup
  *
  * @version 2010/09/01
  * @author PhuongNT
  */
 
public class GroupColumn {
	
	protected TableHeaderRenderer renderer;
	@SuppressWarnings("unchecked")
	protected Vector v;
	protected String text;
	protected int margin=0;
	public GroupColumn(String text) {
		this(null,text);      
	}

  @SuppressWarnings("serial")
	public GroupColumn(TableHeaderRenderer renderer,String text) {
	    if (renderer == null) {
	    	this.renderer = new TableHeaderRenderer() {
	    		public Component getTableCellRendererComponent(JTable table, Object value,
			                         boolean isSelected, boolean hasFocus, int row, int column) {
	    			
				    // set icon for header
				    setOpaque(true);
					setText(value.toString());
					/*
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
			    	setIcon(sortIcon);
			    	*/
			    	setForeground(table.getTableHeader().getForeground());
		    	    setBackground(ColorConstants.TABLE_HEADER_FOCUS_BACKGROUND_COLOR);
			        setFont(table.getTableHeader().getFont());
			        setHorizontalTextPosition(JLabel.LEADING);
			        setHorizontalAlignment(JLabel.CENTER);
			        //setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			        Border TABLE_HEADER_BORDER = new EtchedBorder(EtchedBorder.LOWERED);
					setBorder(TABLE_HEADER_BORDER);
			        
				    return this;
	    		}
	    	};
	      
	    } else {
	    	
	      this.renderer = renderer;
	    }
	    
	    this.text = text;
	    v = new Vector();
	  }

  
  /**
   * @param obj    TableColumn or ColumnGroup
   */
  public void add(Object obj) {
    if (obj == null) { return; }
    v.addElement(obj);
  }

  
  /**
   * @param c    TableColumn
   * @param v    ColumnGroups
   */
  @SuppressWarnings("unchecked")
	public Vector getColumnGroups(TableColumn c, Vector g) {
	    g.addElement(this);
	    if (v.contains(c)) return g;    
	    Enumeration e = v.elements();
	    while (e.hasMoreElements()) {
	      Object obj = e.nextElement();
	      if (obj instanceof GroupColumn) {
	        Vector groups = 
	          (Vector)((GroupColumn)obj).getColumnGroups(c,(Vector)g.clone());
	        if (groups != null) return groups;
	      }
	    }
	    return null;
	 }
	    
  public TableHeaderRenderer getHeaderRenderer() {
    return renderer;
  }
    
  public void setHeaderRenderer(TableHeaderRenderer renderer) {
    if (renderer != null) {
      this.renderer = renderer;
    }
  }
    
  public Object getHeaderValue() {
    return text;
  }
  
  @SuppressWarnings("unchecked")
public Dimension getSize(JTable table) {
    Component comp = renderer.getTableCellRendererComponent(
        table, getHeaderValue(), false, false,-1, -1);
    int height = comp.getPreferredSize().height; 
    int width  = 0;
    Enumeration e = v.elements();
    while (e.hasMoreElements()) {
      Object obj = e.nextElement();
      if (obj instanceof TableColumn) {
        TableColumn aColumn = (TableColumn)obj;
        width += aColumn.getWidth();
        width += margin;
      } else {
        width += ((GroupColumn)obj).getSize(table).width;
      }
    }
    return new Dimension(width, FaceContants.TABLE_HEADER_HEIGHT);
  }

  @SuppressWarnings("unchecked")
public void setColumnMargin(int margin) {
    this.margin = margin;
    Enumeration e = v.elements();
    while (e.hasMoreElements()) {
      Object obj = e.nextElement();
      if (obj instanceof GroupColumn) {
        ((GroupColumn)obj).setColumnMargin(margin);
      }
    }  
  }
}