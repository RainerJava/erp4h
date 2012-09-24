/*
This file is part of the xframe software package
hosted at http://xframe.sourceforge.net

Copyright (c) 2003 Kurt Riede.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package com.fas.jface.table.frozenTable;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.EventObject;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.text.DoubleText;

/**
 * Internal extension of JTable fixes for common swing problems and additional
 * functionalities like column groups and row manipulations.
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public final class KTable extends JTable {

    /** Identifies a KTable that is a locked table within a {@link net.sf.xframe.swing.JXTable JXTable}. */
    public static final int TYPE_NONE = 0;

    /** Identifies a KTable that is a locked table within a {@link net.sf.xframe.swing.JXTable JXTable}. */
    public static final int TYPE_LOCKED = 1;

    /** Identifies a KTable that is a scrollable table within a {@link net.sf.xframe.swing.JXTable JXTable}. */
    public static final int TYPE_SCROLL = 2;

    /** List of event listeners. */
    private EventListenerList listenerList = new EventListenerList();

    /** Type of tabel, can be {@link #TYPE_LOCKED}, {@link #TYPE_SCROLL} or {@link #TYPE_NONE}. */
    private int type = TYPE_NONE;

    /**
     * Constructor.
     */
    public KTable() {
        super();
        init();
    }

    /**
     * Constructs a table with <code>numRows</code>
     * and <code>numColumns</code> of empty cells using
     * <code>DefaultTableModel</code>.  The columns will have
     * names of the form "A", "B", "C", etc.
     *
     * @param numRows           the number of rows the table holds
     * @param numColumns        the number of columns the table holds
     * @see javax.swing.table.DefaultTableModel
     */
    public KTable(final int numRows, final int numColumns) {
        super(numRows, numColumns);
        init();
    }

    /**
     * Constructs a table to display the values in the
     * two dimensional array,
     * <code>rowData</code>, with column names, <code>columnNames</code>.
     * <code>rowData</code> is an array of rows, so the value of the cell at row 1,
     * column 5 can be obtained with the following code:
     * <p>
     * <pre> rowData[1][5]; </pre>
     * <p>
     * All rows must be of the same length as <code>columnNames</code>.
     * <p>
     * @param rowData           the data for the new table
     * @param columnNames       names of each column
     */
    public KTable(final Object[][] rowData, final Object[] columnNames) {
        super(rowData, columnNames);
        init();
    }

    /**
     * Constructs a table to display the values in the
     * <code>Vector</code> of <code>Vectors</code>, <code>rowData</code>,
     * with column names, <code>columnNames</code>.  The
     * <code>Vectors</code> contained in <code>rowData</code>
     * should contain the values for that row. In other words,
     * the value of the cell at row 1, column 5 can be obtained
     * with the following code:
     * <p>
     * <pre>((Vector)rowData.elementAt(1)).elementAt(5);</pre>
     * <p>
     * @param rowData           the data for the new table
     * @param columnNames       names of each column
     */
    public KTable(final Vector rowData, final Vector columnNames) {
        super(rowData, columnNames);
        init();
    }

    /**
     * Constructs a table that is initialized with
     * <code>dm</code> as the data model, a default column model,
     * and a default selection model.
     *
     * @param dm        the data model for the table
     * @param tableType the type of the ({@link #TYPE_LOCKED}, {@link #TYPE_SCROLL} or {@link #TYPE_NONE})
     */
    public KTable(final TableModel dm, final int tableType) {
        this(dm, null, tableType);
        init();
    }

    /**
     * Constructs a table that is initialized with
     * <code>dm</code> as the data model, <code>cm</code>
     * as the column model, and a default selection model.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     */
    public KTable(final TableModel dm, final TableColumnModel cm) {
        this(dm, cm, TYPE_NONE);
        init();
    }

    /**
     * Constructs a table that is initialized with
     * <code>dm</code> as the data model, <code>cm</code>
     * as the column model, and a default selection model.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @param tableType the type of the ({@link #TYPE_LOCKED}, {@link #TYPE_SCROLL} or {@link #TYPE_NONE})
     */
    public KTable(final TableModel dm, final TableColumnModel cm, final int tableType) {
        this(dm, cm, null, tableType);
        init();
    }

    /**
     * Constructs a table that is initialized with
     * <code>dm</code> as the data model, <code>cm</code> as the
     * column model, and <code>sm</code> as the selection model.
     * If any of the parameters are <code>null</code> this method
     * will initialize the table with the corresponding default model.
     * The <code>autoCreateColumnsFromModel</code> flag is set to false
     * if <code>cm</code> is non-null, otherwise it is set to true
     * and the column model is populated with suitable
     * <code>TableColumns</code> for the columns in <code>dm</code>.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @param sm        the row selection model for the table
     */
    public KTable(final TableModel dm, final TableColumnModel cm, final ListSelectionModel sm) {
        this(dm, cm, sm, TYPE_NONE);
        init();
    }

    /**
     * Constructs a table that is initialized with
     * <code>dm</code> as the data model, <code>cm</code> as the
     * column model, and <code>sm</code> as the selection model.
     * If any of the parameters are <code>null</code> this method
     * will initialize the table with the corresponding default model.
     * The <code>autoCreateColumnsFromModel</code> flag is set to false
     * if <code>cm</code> is non-null, otherwise it is set to true
     * and the column model is populated with suitable
     * <code>TableColumns</code> for the columns in <code>dm</code>.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @param sm        the row selection model for the table
     * @param tableType the type of the ({@link #TYPE_LOCKED}, {@link #TYPE_SCROLL} or {@link #TYPE_NONE})
     */
    public KTable(final TableModel dm, final TableColumnModel cm, final ListSelectionModel sm, final int tableType) {
        super(dm, cm, sm);
        type = tableType;
        init();
    }

    /**
     * Getter method for type property.
     *
     * @return type, can be {@link #TYPE_LOCKED} or {@link #TYPE_SCROLL}
     */
    protected int getType() {
        return type;
    }
    
    
    
    private void init()
    {
    
//    	setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR));    
    	
    	
    	
    	
    	
//    	this.addFocusListener(new FocusAdapter() {
//    		
//		    public void focusGained(FocusEvent fe) {
//		    	
////		    		setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR));
////		    		getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
//		    		getTableHeader().setBackground(ColorConstants.TABLE_HEADER_FOCUS_BACKGROUND_COLOR);
//		    	
//		    }
//	
//		    public void focusLost(FocusEvent fe) {
//		    	
//			    	setBorder(BorderFactory.createEmptyBorder());
////					getTableHeader().setBorder(new EtchedBorder(EtchedBorder.LOWERED));
//					getTableHeader().setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
//		    
//		    }
//		});
    	
    }
    

    

    /**
     * Sets the parent and type property.
     *
     * @param pType type of tabel, can be {@link #TYPE_LOCKED} or {@link #TYPE_SCROLL}
     */
    protected void setParent(final int pType) {
        type = pType;
    }

    /**
     * @see javax.swing.JTable#isCellEditable(int, int)
     */
    public boolean isCellEditable(final int row, final int column) {
        final JXTable table = getEnclosingJXTable();
        if (table != null) {
            return table.isCellEditable(row, type == TYPE_SCROLL ? column + table.getFrozenColumns() : column);
        }
        return super.isCellEditable(row, column);
    }

    /**
     * @see javax.swing.JTable#prepareRenderer(javax.swing.table.TableCellRenderer, int, int)
     */
    public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {
        final JXTable table = getEnclosingJXTable();
        if (type == TYPE_SCROLL) {
            return table.prepareRenderer(renderer, row, column + table.getFrozenColumns());
        } else if (type == TYPE_LOCKED) {
            return table.prepareRenderer(renderer, row, column);
        } else {
            return table.prepareRenderer(renderer, row, column);
        }
    }

    /**
     * Prepares a renderer from the usper class JTable.
     *
     * @param renderer  the <code>TableCellRenderer</code> to prepare
     * @param row       the row of the cell to render, where 0 is the first row
     * @param column    the column of the cell to render,
     *          where 0 is the first column
     * @return          the <code>Component</code> under the event location
     */
    public Component prepareRendererSuper(final TableCellRenderer renderer, final int row, final int column) {
        return super.prepareRenderer(renderer, row, column);
    }

    /**
     * Returns the enclosing JXTable if exists, else <tt>null</tt>.
     *
     * @return enclosing JXTable or <tt>null</tt>
     */
    JXTable getEnclosingJXTable() {
        try {
            return (JXTable) getParent().getParent().getParent();
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * @see javax.swing.JTable#configureEnclosingScrollPane()
     */
    protected void configureEnclosingScrollPane() {
        if (getTableHeader() != null) {
            super.configureEnclosingScrollPane();
        }
    }

    /**
     * @see javax.swing.JTable#unconfigureEnclosingScrollPane()
     */
    protected void unconfigureEnclosingScrollPane() {
        if (getTableHeader() != null) {
            super.configureEnclosingScrollPane();
        }
    }

    /**
     * @see javax.swing.JTable#setTableHeader(javax.swing.table.JTableHeader)
     */
    public void setTableHeader(final JTableHeader tableHeader) {
        unconfigureEnclosingScrollPane();
        super.setTableHeader(tableHeader);
        configureEnclosingScrollPane();
    }

    /**
     * Adds an item listener.
     *
     * @param itemlistener the item listener to be added
     */
    public void addItemListener(final ItemListener itemlistener) {
        listenerList.add(ItemListener.class, itemlistener);
    }

    /**
     * Removes the item listener.
     *
     * @param itemlistener the item listener to be removed
     */
    public void removeItemListener(final ItemListener itemlistener) {
        listenerList.remove(ItemListener.class, itemlistener);
    }

    /**
     * fires an item event.
     *
     * @param itemEvent an item event
     */
    public void fireItemStateChanged(final ItemEvent itemEvent) {
        final Object[] aobj = listenerList.getListenerList();
        for (int i = aobj.length - 2; i >= 0; i -= 2) {
            if (aobj[i] == ItemListener.class) {
                ((ItemListener) aobj[i + 1]).itemStateChanged(itemEvent);
            }
        }
    }

    /**
     * @see javax.swing.JTable#changeSelection(int, int, boolean, boolean)
     */
    public void changeSelection(final int row, final int column, final boolean toggle, final boolean extend) {
        ListSelectionModel rows = getSelectionModel();
        ListSelectionModel columns = getColumnModel().getSelectionModel();
        boolean selected = isCellSelected(row, column);
        int anchorRow = rows.getAnchorSelectionIndex(), anchorColumn = columns.getAnchorSelectionIndex();
        boolean anchorSelected = anchorRow != -1 && anchorColumn != -1 && isCellSelected(anchorRow, anchorColumn);
        changeTableColumnModels(columns, column, toggle, extend, selected, anchorSelected);
        changeTableColumnModels(rows, row, toggle, extend, selected, anchorSelected);
        scrollRectToVisible(getCellRect(row, column, true));
    }

    /**
     * @see javax.swing.JTable#getValueAt(int, int)
     */
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        if (rowIndex < getModel().getRowCount()) {
            return getModel().getValueAt(rowIndex, convertColumnIndexToModel(columnIndex));
        } else {
            return null;
        }
    }

    /**
     * @see javax.swing.JTable#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(final Object obj, final int rowIndex, final int columnIndex) {
    	try{
        if (rowIndex >= getModel().getRowCount()) {
            if (getModel() instanceof DefaultTableModel) {
                ((DefaultTableModel) getModel()).addRow(new Object[getModel().getColumnCount()]);
            } else {
                return;
            }
        }
        getModel().setValueAt(obj, rowIndex, convertColumnIndexToModel(columnIndex));
    	} catch(Exception ex) {}
    }

    /**
     * @see javax.swing.JTable#editCellAt(int, int, java.util.EventObject)
     */
    public boolean editCellAt(final int row, final int column, final EventObject eventobject) {
        final boolean flag = super.editCellAt(row, column, eventobject);
        if (flag) {
            fireItemStateChanged(null);
        }
        return flag;
    }

    /**
     * @see javax.swing.event.CellEditorListener#editingStopped(javax.swing.event.ChangeEvent)
     */
    public void editingStopped(final ChangeEvent changeEvent) {
        final JXTable table = getEnclosingJXTable();
        if (table != null) {
            table.editingStopped(changeEvent);
        }
    }

    /**
     * @see javax.swing.event.CellEditorListener#editingCanceled(javax.swing.event.ChangeEvent)
     */
    public void editingCanceled(final ChangeEvent changeEvent) {
        final JXTable table = getEnclosingJXTable();
        if (table != null) {
            table.editingCanceled(changeEvent);
        }
    }

    /**
     * Delegates <tt>EditingStopped</tt> events to the super class.
     *
     * @param changeEvent event
     * @see javax.swing.event.CellEditorListener#editingStopped(javax.swing.event.ChangeEvent)
     * todo must this method be public?
     */
    public void superEditingStopped(final ChangeEvent changeEvent) {
        super.editingStopped(changeEvent);
    }

    /**
     * Delegates <tt>EditingCanceled</tt> events to the super class.
     *
     * @param changeEvent event
     * @see javax.swing.event.CellEditorListener#editingCanceled(javax.swing.event.ChangeEvent)
     * todo must this method be public?
     */
    public void superEditingCanceled(final ChangeEvent changeEvent) {
        super.editingCanceled(changeEvent);
        fireItemStateChanged(null);
    }

    /**
     * @see java.awt.Component#processKeyEvent(java.awt.event.KeyEvent)
     */
    protected void processKeyEvent(final KeyEvent e) {
    	try{
        if (e.isAltDown() || e.isMetaDown() || e.isAltGraphDown() || e.getID() != KeyEvent.KEY_PRESSED) {
            super.processKeyEvent(e);
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_INSERT && !e.isShiftDown() && !e.isControlDown()) {
            insertRow();
            e.consume();
        } else if (e.getKeyCode() == KeyEvent.VK_DELETE && !e.isShiftDown() && e.isControlDown() && !e.isActionKey()) {
            deleteRow();
            e.consume();
        } else {
            super.processKeyEvent(e);
        }
    	} catch(Exception ex)
        {
        	
        }
    }

    /**
     * Inserts a row brfore the current selected row into the current table
     * model that must be a <code>DefaultTableModel</code>.
     */
    private void insertRow() {
        final int row = getSelectedRow() < 0 ? 0 : getSelectedRow();
        if (getModel() instanceof DefaultTableModel) {
            insertRow((DefaultTableModel) getModel(), row);
        }
    }

    /**
     * Inserts a row into a table model at a given position.
     *
     * @param model the table model
     * @param row the row number
     */
    private void insertRow(final DefaultTableModel model, final int row) {
        model.insertRow(row, new Object[getColumnCount()]);
        removeEditor();
        setRowSelectionInterval(row, row);
        if (getAutoscrolls()) {
            final Rectangle rect = getCellRect(row, getSelectedColumn(), false);
            if (rect != null) {
                scrollRectToVisible(rect);
            }
        }
    }

    /**
     * Deletes the current row in the current table
     * model that must be a <code>DefaultTableModel</code>.
     */
    private void deleteRow() {
        if (getSelectedRow() >= 0 && (getModel() instanceof DefaultTableModel)) {
            deleteRow((DefaultTableModel) getModel());
        }
    }

    /**
     * Deletes the current row in to�he given table model.
     *
     * @param model the table model
     */
    private void deleteRow(final DefaultTableModel model) {
        if (getAutoscrolls()) {
            final Rectangle rectangle = getCellRect(getSelectedRow(), getSelectedColumn(), false);
            if (rectangle != null) {
                scrollRectToVisible(rectangle);
            }
        }
        final String title = translate("confirm", null);
        final String message;
        if (getSelectedRowCount() == 1) {
            message = translate("ask_delete", null);
        } else {
            message = translate("ask_delete_many", new Object[] {new Integer(getSelectedRowCount())});
        }
        if (JOptionPane.showConfirmDialog(null, message, title, 0, 3) == 0) {
            removeEditor();
            final int[] selectedRows = getSelectedRows();
            for (int row = selectedRows.length - 1; row >= 0; row--) {
                if (selectedRows[row] != getRowCount() - 1) {
                    model.removeRow(selectedRows[row]);
                }
            }
        }
    }
	public TableCellEditor getCellEditor(int row, int column) {
		
//		if(column == 7 && type == TYPE_SCROLL){
//			HaCellDoubleTextBoxEditorTrOrdrH txtDoubleEditor = new HaCellDoubleTextBoxEditorTrOrdrH( new DoubleText("0",5,2));		
//			return txtDoubleEditor;	
//		}else {
//			HaCellIntegerTextBoxEditorTrOrdrH txtEditor = new HaCellIntegerTextBoxEditorTrOrdrH(new IntegerNumberText());
//			txtEditor.setMinValue(-999999999);
//			txtEditor.setMaxValue(999999999);
//			
//			return txtEditor;	
//		}		
		if(column == 10 && type == TYPE_LOCKED ){
			HaCellCheckboxEditor chkEditor = new HaCellCheckboxEditor(new BaseCheckBox(), this);
			return chkEditor;
		} else if (column == 9 && type == TYPE_SCROLL ) {
//			HaCellIntegerTextBoxEditorTrOrdrH txtEditor = new HaCellIntegerTextBoxEditorTrOrdrH(new IntegerNumberText());
//			txtEditor.setMinValue(0);
//			txtEditor.setMaxValue(99999);
			HaCellDoubleTextBoxEditorTrOrdrH txtDoubleEditor = new HaCellDoubleTextBoxEditorTrOrdrH( new DoubleText("",4,1), this);		
			return txtDoubleEditor;	
				
		}
		return null;	
	}    
    

    /**
     * Translates a given key to a message according to the current locales of
     * the instance.
     *
     * @param key the key to translate
     * @param args arguments
     * @return translated message
     */
    private String translate(final String key, final Object[] args) {
        final String bundleName = this.getClass().getPackage().getName() + ".messages";
        final Locale locale = this.getLocale();
        final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        final String prefix = this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1);
        final String resourceKey = prefix + "." + key;
        final String pattern;
        try {
            pattern = bundle.getString(resourceKey);
        } catch (MissingResourceException e) {
            return resourceKey;
        }
        return MessageFormat.format(pattern, args);
    }

    /**
     * Selection change handler.
     *
     * <pre>
     *       toggle    extend    selected                          index becomes
     *                                                            anchor    lead
     *      ======================================================================
     *       false     false     irrelevant  select (only) index    x         x
     *
     *       true      false     false       select index           x         x
     *
     *       true      false     true        deselect index         x         x
     *
     *       true      true      irrelevant                         x
     *
     *       otherwise:
     *       false     true*     irrelevant                                   x
     *
     *       if (anchorSelected)
     *           deselect range between anchor and lead
     *           then select range between anchor and index
     *       else
     *           not (select range between anchor and lead)+
     *           then deselect range between anchor and index
     *
     *       for single selection mode this implies:
     *       false     true*     irrelevant                         ?         x
     *       if (anchor selected)
     *           select index (will implicitly deselect anchor if different)
     *       else
     *           deselect index (will change no selection if anchor != index)
     *
     *       ? : dependent on what the list selection model does
     *       (whether it ignores the first argument to set/removeSelection-
     *       Interval altogether in single selection mode)
     *
     *       (*) if anchor is -1, regarded as false
     *       (+) this isn't done because it is very unintuitive.
     *       For symmetry it should be done, but ListSelectionModel isn't
     *       symmetric between selected and unselected indices in the first
     *       place. Indices that were never selected suddenly becoming it
     *       by changing a totally different cell would be strange.
     * </pre>
     *
     * @param sm the selection model
     * @param index the index within the selection model
     * @param toggle see description above
     * @param extend if true, extend the current selection
     * @param selected index of selection
     * @param anchorSelected index of anchor selection
     */
    private static void changeTableColumnModels(final ListSelectionModel sm, final int index, final boolean toggle,
            final boolean extend, final boolean selected, final boolean anchorSelected) {
        if (toggle) {
            if (extend) {
                sm.setAnchorSelectionIndex(index);
            } else {
                if (selected) {
                    sm.removeSelectionInterval(index, index);
                } else {
                    sm.addSelectionInterval(index, index);
                }
            }
        } else {
            if (extend) {
                int anchor = sm.getAnchorSelectionIndex();
                if (anchor == -1) {
                    if (selected) {
                        sm.removeSelectionInterval(index, index);
                    } else {
                        sm.addSelectionInterval(index, index);
                    }
                } else {
                    int lead = sm.getLeadSelectionIndex();
                    if (lead == -1) {
                        lead = index;
                    }
                    if (anchorSelected) {
                        final boolean old = sm.getValueIsAdjusting();
                        sm.setValueIsAdjusting(true);
                        // This has some redundant tests, but minimizes
                        // the number of potentially expensive calls to remove-
                        // SelectionInterval.
                        if (anchor <= index) {
                            if (index < lead) {
                                sm.removeSelectionInterval(index + 1, lead);
                            } else if (lead < anchor) {
                                sm.removeSelectionInterval(lead, anchor - 1);
                            }
                        }
                        if (index <= anchor) {
                            if (lead < index) {
                                sm.removeSelectionInterval(lead, index - 1);
                            } else if (anchor < lead) {
                                sm.removeSelectionInterval(anchor + 1, lead);
                            }
                        }
                        sm.addSelectionInterval(anchor, index);
                        sm.setValueIsAdjusting(old);
                    } else {
                        sm.removeSelectionInterval(anchor, index);
                    }
                }
            } else {
                sm.setSelectionInterval(index, index);
            }
        }
    }   

    /**
     * Allows the renderer's tips to be used if there is text set.
     * @param e the location of the event identifies the proper renderer and,
     *            therefore, the proper tip
     * @return the tool tip for this component
     */
    public String getToolTipText(final MouseEvent e) {
        final int x;
        final JXTable jxTable = getEnclosingJXTable();
        if (type == TYPE_LOCKED) {
            x = e.getX();
        } else {
            x = e.getX() + jxTable.getLockedTable().getWidth();
        }
        final MouseEvent event = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                e.getClickCount(), e.isPopupTrigger(), e.getButton());
        return jxTable.getToolTipText(event);
    }

    /**
     * Returns the tooltip location in this component's coordinate system.
     * If <code>null</code> is returned, Swing will choose a location.
     * The default implementation returns <code>null</code>.
     *
     * @param e  the <code>MouseEvent</code> that caused the
     *      <code>ToolTipManager</code> to show the tooltip
     * @return always returns <code>null</code>
     */
    public Point getToolTipLocation(final MouseEvent e) {
        final int x;
        final JXTable jxTable = getEnclosingJXTable();
        if (type == TYPE_LOCKED) {
            x = e.getX();
        } else {
            x = e.getX() + jxTable.getLockedTable().getWidth();
        }
        final MouseEvent event = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                e.getClickCount(), e.isPopupTrigger(), e.getButton());
        return jxTable.getToolTipLocation(event);
    }   
}
