package com.fas.jface.table;

import java.util.*;
import javax.swing.table.*;

import org.jdesktop.swingx.JXTableHeader;

 

/**
  * GroupableTableHeader
  *
  * @version 2010/09/01
  * @author PhuongNT
  */

@SuppressWarnings("serial")
public class GroupColumnHeader extends JXTableHeader {
  private static final String uiClassID = "GroupableTableHeaderUI";
  protected Vector columnGroups = null;
    
  public GroupColumnHeader(TableColumnModel model) {
	  super(model);    
	  setUI(new GroupColumnHeaderUI());
	  setReorderingAllowed(true);    
  }
  public void updateUI(){
	  setUI(new GroupColumnHeaderUI());
  }
  
  public void setReorderingAllowed(boolean b) {
	  reorderingAllowed = false;
  }
    
  public void addColumnGroup(GroupColumn g) {
    if (columnGroups == null) {
      columnGroups = new Vector();
    }
    columnGroups.addElement(g);
  }

  public Enumeration getColumnGroups(TableColumn col) {
    if (columnGroups == null) return null;
    Enumeration e = columnGroups.elements();
    while (e.hasMoreElements()) {
    	GroupColumn cGroup = (GroupColumn)e.nextElement();
      Vector v_ret = (Vector)cGroup.getColumnGroups(col,new Vector());
      if (v_ret != null) { 
    	  return v_ret.elements();
      }
    }
    return null;
  }
  
  public void setColumnMargin() {
    if (columnGroups == null) return;
    int columnMargin = getColumnModel().getColumnMargin();
    Enumeration e = columnGroups.elements();
    while (e.hasMoreElements()) {
    	GroupColumn cGroup = (GroupColumn)e.nextElement();
	    cGroup.setColumnMargin(0);
    }
  }
  
}

