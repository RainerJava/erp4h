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
package org.jdesktop.xframe;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;
import java.util.EventObject;

import javax.accessibility.Accessible;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdesktop.xframe.scroll.JScrollPaneAdjuster;
import org.jdesktop.xframe.table.ColumnGroupHeader;
import org.jdesktop.xframe.table.JXTableColumnModel;
import org.jdesktop.xframe.table.JXTableHeader;
import org.jdesktop.xframe.table.KTable;


/**
 * A table with freezable columns.
 *
 * <p>The main feature of this table is two allow locking columns while still
 * having a neat user interface. For most of the original methods of class
 * <code>JTable</code> there are delegate methods.</p>
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public class JXTable extends JComponent implements TableModelListener, Scrollable,
                                                   TableColumnModelListener, ListSelectionListener,
                                                   CellEditorListener, Accessible {

    /**
     * Used to set the horizontal scroll bar policy so that
     * horizontal scrollbars are displayed only when needed.
     */
    public static final int HORIZONTAL_SCROLLBAR_AS_NEEDED = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

    /**
     * Used to set the horizontal scroll bar policy so that
     * horizontal scrollbars are never displayed.
     */
    public static final int HORIZONTAL_SCROLLBAR_NEVER = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

    /**
     * Used to set the horizontal scroll bar policy so that
     * horizontal scrollbars are always displayed.
     */
    public static final int HORIZONTAL_SCROLLBAR_ALWAYS = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;

    /** Do not adjust column widths automatically; use a scrollbar. */
    public static final int AUTO_RESIZE_OFF = 0;

    /** When a column is adjusted in the UI, adjust the next column the opposite way. */
    public static final int AUTO_RESIZE_NEXT_COLUMN = 1;

    /** During UI adjustment, change subsequent columns to preserve the total width;
      * this is the default behavior. */
    public static final int AUTO_RESIZE_SUBSEQUENT_COLUMNS = 2;

    /** During all resize operations, apply adjustments to the last column only. */
    public static final int AUTO_RESIZE_LAST_COLUMN = 3;

    /** During all resize operations, proportionately resize all columns. */
    public static final int AUTO_RESIZE_ALL_COLUMNS = 4;

    /** The ScrollPane around the table. */
    private final JScrollPane scrollPane;

    /** the table with the locked columns. */
    private final KTable lockedTable;

    /** The table with the scrollable columns. */
    private final KTable scrollTable;

    /** Scroll pane adjuster. */
    private final JScrollPaneAdjuster adjuster;

    /** Header of the locked table. */
    private JTableHeader lockedHeader;

    /** Header of the scrollable table. */
    private JTableHeader scrollHeader;

    /** The table column model. */
    private TableColumnModel columnModel;

    /** the header. */
    private JXTableHeader collTableHeader;

    /** Number of frozen columns. */
    private int frozenColumns = 1;

    /** Reference to the mouse listener. */
    private transient MouseListener mouseListener;

    /** Reference to the mouse motion listener. */
    private transient MouseMotionListener mouseMotionListener;

    /** Reference to the mouse wheel listener. */
    private transient MouseWheelListener mouseWheelListener;

    /**
     * Constructs a default <code>JTable</code> that is initialized with a default
     * data model, a default column model, and a default selection
     * model.
     */
    public JXTable() {
        this(null, null, null, 0);
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, a default column model,
     * and a default selection model.
     *
     * @param dm        the data model for the table
     */
    public JXTable(final TableModel dm) {
        this(dm, null, null, 0);
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, a default column model,
     * and a default selection model.
     *
     * @param dm the data model for the table
     * @param numFrozenColumns the initial number of frozen columns
     */
    public JXTable(final TableModel dm, final int numFrozenColumns) {
        this(dm, null, null, numFrozenColumns);
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code>
     * as the column model, and a default selection model.
     *
     * @param dm the data model for the table
     * @param cm the column model for the table
     */
    public JXTable(final TableModel dm, final TableColumnModel cm) {
        this(dm, cm, null, 0);
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code>
     * as the column model, and a default selection model.
     *
     * @param dm the data model for the table
     * @param cm the column model for the table
     * @param numFrozenColumns the initial number of frozen columns
     */
    public JXTable(final TableModel dm, final TableColumnModel cm, final int numFrozenColumns) {
        this(dm, cm, null, numFrozenColumns);
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code> as the
     * column model, and <code>sm</code> as the selection model.
     * If any of the parameters are <code>null</code> this method
     * will initialize the table with the corresponding default model.
     * The <code>autoCreateColumnsFromModel</code> flag is set to false
     * if <code>cm</code> is non-null, otherwise it is set to true
     * and the column model is populated with suitable
     * <code>TableColumns</code> for the columns in <code>dm</code>.
     *
     * @param dm the data model for the table
     * @param cm the column model for the table
     * @param sm the row selection model for the table
     */
    public JXTable(final TableModel dm, final TableColumnModel cm, final ListSelectionModel sm) {
        this(dm, cm, sm, 0);
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code> as the
     * column model, and <code>sm</code> as the selection model.
     * If any of the parameters are <code>null</code> this method
     * will initialize the table with the corresponding default model.
     * The <code>autoCreateColumnsFromModel</code> flag is set to false
     * if <code>cm</code> is non-null, otherwise it is set to true
     * and the column model is populated with suitable
     * <code>TableColumns</code> for the columns in <code>dm</code>.
     *
     * @param dm the data model for the table
     * @param cm the column model for the table
     * @param sm the row selection model for the table
     * @param numFrozenColumns the initial number of frozen columns
     */
    public JXTable(final TableModel dm, final TableColumnModel cm, final ListSelectionModel sm, final int numFrozenColumns) {
        super();
        scrollPane = new JScrollPane();
        adjuster = new JScrollPaneAdjuster(scrollPane);
        final TableModel model;
        if (dm == null) {
            model = createDefaultDataModel();
        } else {
            model = dm;
        }
        frozenColumns = numFrozenColumns;
        if (frozenColumns > model.getColumnCount()) {
            frozenColumns = model.getColumnCount();
        }
        final TableColumnModel cm1;
        final TableColumnModel cm2;
        if (cm == null) {
            cm1 = null;
            cm2 = null;
        } else {
            cm1 = this.createDefaultColumnModel();
            cm2 = this.createDefaultColumnModel();
            for (int i = 0; i < cm.getColumnCount(); i++) {
                cm1.addColumn(cm.getColumn(i));
                cm2.addColumn(cm.getColumn(i));
            }
        }
        // create the two tables
        lockedTable = new KTable(model, cm1, sm, KTable.TYPE_LOCKED);
        scrollTable = new KTable(model, cm2, sm, KTable.TYPE_SCROLL);
        lockedHeader = new ColumnGroupHeader(lockedTable.getColumnModel());
        scrollHeader = new ColumnGroupHeader(scrollTable.getColumnModel());
        lockedTable.setTableHeader(lockedHeader);
        scrollTable.setTableHeader(scrollHeader);
        scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, lockedHeader);
        scrollPane.setViewportView(scrollTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setResizingAllowed(true);

        // Put the locked-column table in the row header
        final JViewport viewport = new JViewport();
        viewport.setView(lockedTable);
        scrollPane.setRowHeader(viewport);

        // share a single-selection selection model between both tables
        if (sm == null) {
            scrollTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lockedTable.setSelectionModel(scrollTable.getSelectionModel());
        }
        // The following is a JDK 1.4 feature, but runnable with JDK 1.3
        lockedTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        scrollTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        // Remove the locked columns from the scrollable table
        final TableColumnModel scrollColumnModel = scrollTable.getColumnModel();
        for (int i = 0; i < frozenColumns; i++) {
            scrollColumnModel.removeColumn(scrollColumnModel.getColumn(0));
        }

        // Remove the scrollable columns from the locked table
        final TableColumnModel lockedColumnModel = lockedTable.getColumnModel();
        while (lockedTable.getColumnCount() > frozenColumns) {
            lockedColumnModel.removeColumn(lockedColumnModel.getColumn(frozenColumns));
        }

        lockedTable.setAutoCreateColumnsFromModel(false);
        scrollTable.setAutoCreateColumnsFromModel(false);

        // add size listeners to all locked columns
        for (int i = 0; i < lockedColumnModel.getColumnCount(); i++) {
            lockedColumnModel.getColumn(i).addPropertyChangeListener(new ColumnWidthChangeListener());
        }

        // create the combined column model
        columnModel = new JXTableColumnModel(this, lockedColumnModel, scrollColumnModel);

        // Add the fixed table to the scroll pane
        lockedTable.setPreferredScrollableViewportSize(lockedTable.getPreferredSize());

        adjustActionMaps();

        final JTableHeader theLockedHeader = lockedTable.getTableHeader();
        final JTableHeader theScrollHeader = scrollTable.getTableHeader();
        collTableHeader = new JXTableHeader(columnModel, theLockedHeader, theScrollHeader);
        collTableHeader.setTable(this);

        // add scrollpane to container
        setLayout(new BorderLayout());
        add(scrollPane, "Center");
    }

    /**
     * Returns the underlying scroll pane.
     *
     * @return the underlying scroll pane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Adjusts the action maps of the two tables to allow proper navigation
     * between the two tables.
     */
    private void adjustActionMaps() {
        // collect original left/right/first/last actions
        final Action scrollNextAction = scrollTable.getActionMap().get("selectNextColumnCell");
        final Action lockedNextAction = lockedTable.getActionMap().get("selectNextColumnCell");
        final Action scrollPreviousAction = scrollTable.getActionMap().get("selectPreviousColumnCell");
        final Action lockedPreviousAction = lockedTable.getActionMap().get("selectPreviousColumnCell");
        final Action scrollFirstAction = scrollTable.getActionMap().get("selectFirstColumn");
        final Action lockedLastAction = lockedTable.getActionMap().get("selectLastColumn");
        // set left and right actions
        lockedTable.getActionMap().put("selectNextColumn", new LockedNextAction(lockedNextAction));
        lockedTable.getActionMap().put("selectPreviousColumn", new LockedPreviousAction(lockedPreviousAction));
        scrollTable.getActionMap().put("selectNextColumn", new ScrollNextAction(scrollNextAction));
        scrollTable.getActionMap().put("selectPreviousColumn", new ScrollPreviousAction(scrollPreviousAction));
        // set tab and shift-tab actions
        lockedTable.getActionMap().put("selectNextColumnCell", new LockedNextAction(lockedNextAction));
        lockedTable.getActionMap().put("selectPreviousColumnCell", new LockedPreviousAction(lockedPreviousAction));
        scrollTable.getActionMap().put("selectNextColumnCell", new ScrollNextAction(scrollNextAction));
        scrollTable.getActionMap().put("selectPreviousColumnCell", new ScrollPreviousAction(scrollPreviousAction));
        // set top and end actions
        scrollTable.getActionMap().put("selectFirstColumn", new ScrollFirstAction(scrollFirstAction));
        lockedTable.getActionMap().put("selectLastColumn", new LockedLastAction(lockedLastAction));
    }

    /**
     * Returns the default table model object, which is
     * a <code>DefaultTableModel</code>.  A subclass can override this
     * method to return a different table model object.
     *
     * @return the default table model object
     * @see javax.swing.table.DefaultTableModel
     */
    protected TableModel createDefaultDataModel() {
        return new DefaultTableModel();
    }

    /**
     * Returns the default column model object, which is
     * a <code>DefaultTableColumnModel</code>.  A subclass can override this
     * method to return a different column model object.
     *
     * @return the default column model object
     * @see javax.swing.table.DefaultTableColumnModel
     */
    protected TableColumnModel createDefaultColumnModel() {
        return new DefaultTableColumnModel();
    }

    /**
     * Returns the default selection model object, which is
     * a <code>DefaultListSelectionModel</code>.  A subclass can override this
     * method to return a different selection model object.
     *
     * @return the default selection model object
     * @see javax.swing.DefaultListSelectionModel
     */
    protected ListSelectionModel createDefaultSelectionModel() {
        return new DefaultListSelectionModel();
    }

    /**
     * Returns the default table header object for the scrollable table,
     * which is a <code>JTableHeader</code>.  A subclass can override this
     * method to return a different table header object.
     *
     * @return the default table header object
     * @see javax.swing.table.JTableHeader
     * @see net.sf.xframe.swing.table.JXTableHeader
     */
    public JTableHeader createDefaultScrollTableHeader() {
        return new ColumnGroupHeader(scrollTable.getColumnModel());
    }


    /**
    /**
     * Returns the number of the next row in the table relative to the
     * current selection. If the selection is in the last row, the next row
     * is the first row.
     *
     * @param table a JTable for row calculation
     * @return row number of next row
     */
    protected int nextRow(final JTable table) {
        int row = table.getSelectedRow() + 1;
        if (row == table.getRowCount()) {
            row = 0;
        }
        return row;
    }

    /**
     * Returns the number of the previous row in the table relative to the
     * current selection. If the selection is in the first row, the next row
     * is the first row.
     *
     * @param table a JTable for row calculation
     * @return row number of previous row
     */
    private int previousRow(final JTable table) {
        int row = table.getSelectedRow() - 1;
        if (row == -1) {
            row = table.getRowCount() - 1;
        }
        return row;
    }

    /**
     * Returns the internal table for the locked columns.
     *
     * @return the locked table
     */
    public final JTable getLockedTable() {
        return lockedTable;
    }

    /**
     * Returns the internal table for the scrollable columns.
     *
     * @return the scrollable table
     */
    public final JTable getScrollTable() {
        return scrollTable;
    }

    /**
     * Returns the number of frozen columns.
     *
     * @return number of frozen columns
     */
    public final int getFrozenColumns() {
        return frozenColumns;
    }

    /**
     * Setter method for frozen columns attribute.
     *
     * <p>Changing the number frozen column results in rearranging the columns
     * within the fixed-column and the scrollable table.</p>
     *
     * @param numFrozenColumns of frozen columns
     */
    public final void setFrozenColumns(final int numFrozenColumns) {
        if (numFrozenColumns > lockedTable.getModel().getColumnCount()) {
            return;
        }
        final TableColumnModel scrollColumnModel = scrollTable.getColumnModel();
        final TableColumnModel lockedColumnModel = lockedTable.getColumnModel();
        if (frozenColumns < numFrozenColumns) {
            // move columns from scrollable to locked table
            for (int i = frozenColumns; i < numFrozenColumns; i++) {
                final TableColumn column = scrollColumnModel.getColumn(0);
                lockedColumnModel.addColumn(column);
                scrollColumnModel.removeColumn(column);
                column.addPropertyChangeListener(new ColumnWidthChangeListener());
            }
            lockedTable.setPreferredScrollableViewportSize(lockedTable.getPreferredSize());
        } else if (frozenColumns > numFrozenColumns) {
            // move columns from locked to scrollable table
            for (int i = numFrozenColumns; i < frozenColumns; i++) {
                final TableColumn column = lockedColumnModel.getColumn(lockedColumnModel.getColumnCount() - 1);
                scrollColumnModel.addColumn(column);
                scrollColumnModel.moveColumn(scrollColumnModel.getColumnCount() - 1, 0);
                lockedColumnModel.removeColumn(column);
            }
            lockedTable.setPreferredScrollableViewportSize(lockedTable.getPreferredSize());
        }
        frozenColumns = numFrozenColumns;
    }

    /**
     * Sets the background color of this component.
     *
     * @param bg the desired background <code>Color</code>
     */
    public void setHeaderBackground(final Color bg) {
        lockedHeader.setBackground(bg);
        scrollHeader.setBackground(bg);
    }

    /**
     * Sets the border of the table header.
     *
     * @param border the border to be rendered for this component
     */
    public void setHeaderBorder(final Border border) {
        lockedHeader.setBorder(border);
        scrollHeader.setBorder(border);
    }

    /**
     * Sets the font for the table header.
     *
     * @param font the desired <code>Font</code> for this component
     */
    public void setHeaderFont(final Font font) {
        lockedHeader.setFont(font);
        scrollHeader.setFont(font);
    }

    /**
     *  Sets whether the user can drag column headers to reorder columns.
     *
     * @param reorderingAllowed true if the table view should allow
     *        reordering; otherwise false
     */
    public void setReorderingAllowed(final boolean reorderingAllowed) {
        lockedHeader.setReorderingAllowed(reorderingAllowed);
        scrollHeader.setReorderingAllowed(reorderingAllowed);
    }

    /**
     * Sets whether the user can resize columns by dragging between headers.
     *
     * @param resizingAllowed true if table view should allow resizing
     */
    public void setResizingAllowed(final boolean resizingAllowed) {
        lockedHeader.setResizingAllowed(resizingAllowed);
        scrollHeader.setResizingAllowed(resizingAllowed);
    }

    /**
     * Sets the font for this component.
     *
     * @param font the desired <code>Font</code> for this component
     */
    public void setTableFont(final Font font) {
        lockedTable.setFont(font);
        scrollTable.setFont(font);
    }

    /**
     * Sets the foreground color of this component.
     *
     * @param fg  the desired foreground <code>Color</code>
     */
    public void setForeground(final Color fg) {
        if (lockedTable != null) {
            lockedTable.setForeground(fg);
        }
        if (scrollTable != null) {
            scrollTable.setForeground(fg);
        }
    }

    /**
     * Sets the color used to draw grid lines to <code>gridColor</code> and redisplays.
     * The default color is look and feel dependent.
     *
     * @param gridColor the new color of the grid lines
     */
    public void setGridColor(final Color gridColor) {
        lockedTable.setGridColor(gridColor);
        scrollTable.setGridColor(gridColor);
    }

    /**
     * Sets the background color for selected cells.  Cell renderers
     * can use this color to the fill selected cells.
     * <p>
     * The default value of this property is defined by the look
     * and feel implementation.
     * <p>
     *
     * @param selectionBackground the <code>Color</code> to use for the background
     *                            of selected cells
     */
    public void setSelectionBackground(final Color selectionBackground) {
        lockedTable.setSelectionBackground(selectionBackground);
        scrollTable.setSelectionBackground(selectionBackground);
    }

    /**
     * Sets the foreground color for selected cells.  Cell renderers
     * can use this color to render text and graphics for selected
     * cells.
     * <p>
     * The default value of this property is defined by the look
     * and feel implementation.
     * <p>
     *
     * @param selectionForeground the <code>Color</code> to use in the foreground
     */
    public void setSelectionForeground(final Color selectionForeground) {
        lockedTable.setSelectionForeground(selectionForeground);
        scrollTable.setSelectionForeground(selectionForeground);
    }

    /**
     *  Sets whether the table draws grid lines around cells.
     *  If <code>showGrid</code> is true it does; if it is false it doesn't.
     *  There is no <code>getShowGrid</code> method as this state is held
     *  in two variables -- <code>showHorizontalLines</code> and <code>showVerticalLines</code> --
     *  each of which can be queried independently.
     *
     * @param showGrid true if table view should draw grid lines
     */
    public void setShowGrid(final boolean showGrid) {
        lockedTable.setShowGrid(showGrid);
        scrollTable.setShowGrid(showGrid);
    }

    /**
     *  Sets whether the table draws horizontal lines between cells.
     *  If <code>showHorizontalLines</code> is true it does; if it is false it doesn't.
     *
     * @param showHorizontalLines true if table view should draw horizontal lines
     */
    public void setShowHorizontalLines(final boolean showHorizontalLines) {
        lockedTable.setShowHorizontalLines(showHorizontalLines);
        scrollTable.setShowHorizontalLines(showHorizontalLines);
    }

    /**
     *  Sets whether the table draws vertical lines between cells.
     *  If <code>showVerticalLines</code> is true it does; if it is false it doesn't.
     *
     * @param showVerticalLines true if table view should draw vertical lines
     */
    public void setShowVerticalLines(final boolean showVerticalLines) {
        lockedTable.setShowVerticalLines(showVerticalLines);
        scrollTable.setShowVerticalLines(showVerticalLines);
    }

    /**
     * Updates the selection models of the table, depending on the state of the
     * two flags: <code>toggle</code> and <code>extend</code>. All changes
     * to the selection that are the result of keyboard or mouse events received
     * by the UI are channeled through this method so that the behavior may be
     * overridden by a subclass.
     * <p>
     * This implementation uses the following conventions:
     * <ul>
     * <li> <code>toggle</code>: <em>false</em>, <code>extend</code>: <em>false</em>.
     *      Clear the previous selection and ensure the new cell is selected.
     * <li> <code>toggle</code>: <em>false</em>, <code>extend</code>: <em>true</em>.
     *      Extend the previous selection to include the specified cell.
     * <li> <code>toggle</code>: <em>true</em>, <code>extend</code>: <em>false</em>.
     *      If the specified cell is selected, deselect it. If it is not selected, select it.
     * <li> <code>toggle</code>: <em>true</em>, <code>extend</code>: <em>true</em>.
     *      Leave the selection state as it is, but move the anchor index to the specified location.
     * </ul>
     * @param row affects the selection at <code>row</code>
     * @param column affects the selection at <code>column</code>
     * @param toggle see description above
     * @param extend if true, extend the current selection
     *
     */
    public void changeSelection(final int row, final int column, final boolean toggle, final boolean extend) {
        if (column < frozenColumns) {
            lockedTable.changeSelection(row, column, toggle, extend);
        } else {
            scrollTable.changeSelection(row, column - frozenColumns, toggle, extend);
        }
    }

    /**
     * Programmatically starts editing the cell at <code>row</code> and
     * <code>column</code>, if the cell is editable.  Note that this is
     * a convenience method for <code>editCellAt(int, int, null)</code>.
     *
     * @param row the row to be edited
     * @param column the column to be edited
     * @return false if for any reason the cell cannot be edited
     */
    public boolean editCellAt(final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.editCellAt(row, column);
        } else {
            return scrollTable.editCellAt(row, column - frozenColumns);
        }
    }

    /**
     * Returns the name of the column appearing in the view at
     * column position <code>column</code>.
     *
     * @param column the column in the view being queried
     * @return the name of the column at position <code>column</code>
     *       in the view where the first column is column 0
     */
    public String getColumnName(final int column) {
        if (column < frozenColumns) {
            return lockedTable.getColumnName(column);
        } else {
            return scrollTable.getColumnName(column - frozenColumns);
        }
    }

    /**
     * Returns the index of the column that contains the cell currently
     * being edited.  If nothing is being edited, returns -1.
     *
     * @return the index of the column that contains the cell currently
     *         being edited; returns -1 if nothing being edited
     */
    public int getEditingColumn() {
        if (lockedTable.hasFocus()) {
            return lockedTable.getEditingColumn();
        }

        return scrollTable.getEditingColumn() + frozenColumns;
    }

    /**
     * Returns the index of the row that contains the cell currently
     * being edited.  If nothing is being edited, returns -1.
     *
     * @return  the index of the row that contains the cell currently
     *      being edited; returns -1 if nothing being edited
     */
    public int getEditingRow() {
        if (lockedTable.hasFocus()) {
            return lockedTable.getEditingRow();
        }
        return scrollTable.getEditingRow();
    }

    /**
     * Returns the color used to draw grid lines.
     * The default color is look and feel dependent.
     *
     * @return  the color used to draw grid lines
     * @see     #setGridColor
     */
    public Color getGridColor() {
        return lockedTable.getGridColor();
    }

    /**
     * Returns true if rows can be selected.
     *
     * @return true if rows can be selected, otherwise false
     * @see #setRowSelectionAllowed
     */
    public boolean getRowSelectionAllowed() {
        return lockedTable.getRowSelectionAllowed();
    }

    /**
     * Returns the index of the first selected row, -1 if no row is selected.
     *
     * @return the index of the first selected row
     */
    public int getSelectedRow() {
        return lockedTable.getSelectedRow();
    }

    /**
     * Returns the number of selected rows.
     *
     * @return the number of selected rows, 0 if no rows are selected
     */
    public int getSelectedRowCount() {
        return lockedTable.getSelectedRowCount();
    }

    /**
     * Returns the indices of all selected rows.
     *
     * @return an array of integers containing the indices of all selected rows,
     *         or an empty array if no row is selected
     * @see #getSelectedRow
     */
    public int[] getSelectedRows() {
        return lockedTable.getSelectedRows();
    }

    /**
     * Returns the <code>ListSelectionModel</code> that is used to maintain row
     * selection state.
     *
     * @return  the object that provides row selection state, <code>null</code>
     *          if row selection is not allowed
     */
    public ListSelectionModel getSelectionModel() {
        return lockedTable.getSelectionModel();
    }

    /**
     * Returns the cell value at <code>row</code> and <code>column</code>.
     * <p>
     * <b>Note</b>: The column is specified in the table view's display
     *              order, and not in the <code>TableModel</code>'s column
     *              order.  This is an important distinction because as the
     *              user rearranges the columns in the table,
     *              the column at a given index in the view will change.
     *              Meanwhile the user's actions never affect the model's
     *              column ordering.
     *
     * @param row the row whose value is to be queried
     * @param column the column whose value is to be queried
     * @return the Object at the specified cell
     */
    public Object getValueAt(final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.getValueAt(row, column);
        } else {
            return scrollTable.getValueAt(row, column - frozenColumns);
        }
    }

    /**
     * Sets the value for the cell in the table model at <code>row</code>
     * and <code>column</code>.
     * <p>
     * <b>Note</b>: The column is specified in the table view's display
     *              order, and not in the <code>TableModel</code>'s column
     *              order.  This is an important distinction because as the
     *              user rearranges the columns in the table,
     *              the column at a given index in the view will change.
     *              Meanwhile the user's actions never affect the model's
     *              column ordering.
     *
     * <code>aValue</code> is the new value.
     *
     * @param aValue the new value
     * @param row the row of the cell to be changed
     * @param column the column of the cell to be changed
     * @see #getValueAt
     */
    public void setValueAt(final Object aValue, final int row, final int column) {
        if (column < frozenColumns) {
            lockedTable.setValueAt(aValue, row, column);
        } else {
            scrollTable.setValueAt(aValue, row, column - frozenColumns);
        }
    }

    /**
     * Maps the index of the column in the view at <code>viewColumnIndex</code>
     * to the index of the column in the table model.  Returns the index of the
     * corresponding column in the model.  If <code>viewColumnIndex</code>
     * is less than zero, returns <code>viewColumnIndex</code>.
     *
     * @param viewColumnIndex the index of the column in the view
     * @return the index of the corresponding column in the model
     *
     * @see #convertColumnIndexToView
     */
    public int convertColumnIndexToModel(final int viewColumnIndex) {
        if (viewColumnIndex < frozenColumns) {
            return lockedTable.convertColumnIndexToModel(viewColumnIndex);
        } else {
            return scrollTable.convertColumnIndexToModel(viewColumnIndex - frozenColumns);
        }
    }

    /**
     * Maps the index of the column in the table model at
     * <code>modelColumnIndex</code> to the index of the column in the view.
     * Returns the index of the corresponding column in the view; returns -1 if
     * this column is not being displayed.  If <code>modelColumnIndex</code> is
     * less than zero, returns <code>modelColumnIndex</code>.
     *
     * @param modelColumnIndex the index of the column in the model
     * @return the index of the corresponding column in the view
     *
     * @see #convertColumnIndexToModel
     */
    public int convertColumnIndexToView(final int modelColumnIndex) {
        final int lockedIndex = lockedTable.convertColumnIndexToView(modelColumnIndex);
        final int scrollIndex = scrollTable.convertColumnIndexToView(modelColumnIndex);
        if (lockedIndex >= 0) {
            return lockedIndex;
        } else {
            return scrollIndex;
        }
    }

    /**
     * Returns true if the cell at <code>row</code> and <code>column</code>
     * is editable. Otherwise, invoking <code>setValueAt</code> on the cell
     * will have no effect.
     * <p>
     * <b>Note</b>: The column is specified in the table view's display order,
     * and not in the <code>TableModel</code>'s column order. This is an
     * important distinction because as the user rearranges the columns in the
     * table, the column at a given index in the view will change. Meanwhile the
     * user's actions never affect the model's column ordering.
     * @param row the row whose value is to be queried
     * @param column the column whose value is to be queried
     * @return true if the cell is editable
     * @see #setValueAt
     */
    public boolean isCellEditable(final int row, final int column) {
        return getModel().isCellEditable(row, convertColumnIndexToModel(column));
    }

    /**
     * Returns true if the cell at the specified position is selected.
     * @param row the row being queried
     * @param column the column being queried
     *
     * @return true if the cell at index <code>(row, column)</code> is selected,
     *         where the first row and first column are at index 0
     */
    public boolean isCellSelected(final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.isCellSelected(row, column);
        } else {
            return scrollTable.isCellSelected(row, column - frozenColumns);
        }
    }

    /**
     * Returns true if a cell is being edited.
     *
     * @return  true if the table is editing a cell
     */
    public boolean isEditing() {
        return lockedTable.isEditing();
    }

    /**
     * Returns true if the row at the specified index is selected.
     *
     * @param row the row being queried
     * @return true if the row at index <code>row</code> is selected, where 0 is the
     *         first row
     */
    public boolean isRowSelected(final int row) {
        return lockedTable.isRowSelected(row);
    }

    /**
     * Sets the <code>editingRow</code> variable.
     * @param row the row of the cell to be edited
     */
    public void setEditingRow(final int row) {
        lockedTable.setEditingRow(row);
        scrollTable.setEditingRow(row);
    }

    /**
     * Sets the table's selection mode to allow only single selections, a single
     * contiguous interval, or multiple intervals.
     * <P>
     * <bold>Note:</bold>
     * <code>JTable</code> provides all the methods for handling
     * column and row selection.  When setting states,
     * such as <code>setSelectionMode</code>, it not only
     * updates the mode for the row selection model but also sets similar
     * values in the selection model of the <code>columnModel</code>.
     * If you want to have the row and column selection models operating
     * in different modes, set them both directly.
     * <p>
     * Both the row and column selection models for <code>JTable</code>
     * default to using a <code>DefaultListSelectionModel</code>
     * so that <code>JTable</code> works the same way as the
     * <code>JList</code>. See the <code>setSelectionMode</code> method
     * in <code>JList</code> for details about the modes.
     *
     * The following <code>selectionMode</code> values are allowed:
     * <ul>
     * <li><code>ListSelectionModel.SINGLE_SELECTION</code>
     * Only one list index can be selected at a time.  In this mode the
     * <code>setSelectionInterval</code> and <code>addSelectionInterval</code>
     * methods are equivalent, and only the second index argument is used.</li>
     * <li><code>ListSelectionModel.SINGLE_INTERVAL_SELECTION</code>
     * One contiguous index interval can be selected at a time. In this mode
     * <code>setSelectionInterval</code> and <code>addSelectionInterval</code>
     * are equivalent.</li>
     * <li><code>ListSelectionModel.MULTIPLE_INTERVAL_SELECTION</code>
     * In this mode, there's no restriction on what can be selected. This is
     * the default.</li>
     * </ul>
     *
     * @param selectionMode an integer specifying the type of selections
     *        that are permissible
     */
    public void setSelectionMode(final int selectionMode) {
        lockedTable.setSelectionMode(selectionMode);
        scrollTable.setSelectionMode(selectionMode);
    }

    /**
     * Returns the <code>TableModel</code> that provides the data displayed by
     * this <code>JTable</code>.
     *
     * @return the <code>TableModel</code> that provides the data displayed by
     * this <code>JTable</code>
     */
    public TableModel getModel() {
        return lockedTable.getModel();
    }

    /**
     * Sets whether the rows in this model can be selected.
     *
     * @param rowSelectionAllowed true if this model will allow row selection
     * @see #getRowSelectionAllowed
     */
    public void setRowSelectionAllowed(final boolean rowSelectionAllowed) {
        lockedTable.setRowSelectionAllowed(rowSelectionAllowed);
        scrollTable.setRowSelectionAllowed(rowSelectionAllowed);
    }

    /**
     * Listener for column change events of locked columns to adjust the
     * viewport size of the locked table.
     */
    private final class ColumnWidthChangeListener implements PropertyChangeListener {

        /** Contructor. */
        private ColumnWidthChangeListener() {
            super();
        }

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(final PropertyChangeEvent evt) {
            if ("preferredWidth".equals(evt.getPropertyName())) {
                lockedTable.setPreferredScrollableViewportSize(lockedTable.getPreferredSize());
            }
        }
    }

    /**
     *  Appends <code>aColumn</code> to the end of the array of columns held by
     *  this <code>JTable</code>'s column model.
     *  If the column name of <code>aColumn</code> is <code>null</code>,
     *  sets the column name of <code>aColumn</code> to the name
     *  returned by <code>getModel().getColumnName()</code>.
     *  <p>
     *  To add a column to this <code>JTable</code> to display the
     *  <code>modelColumn</code>'th column of data in the model with a
     *  given <code>width</code>, <code>cellRenderer</code>,
     *  and <code>cellEditor</code> you can use:
     *  <pre>
     *
     *      addColumn(new TableColumn(modelColumn, width, cellRenderer, cellEditor));
     *
     *  </pre>
     *  [Any of the <code>TableColumn</code> constructors can be used
     *  instead of this one.]
     *  The model column number is stored inside the <code>TableColumn</code>
     *  and is used during rendering and editing to locate the appropriates
     *  data values in the model. The model column number does not change
     *  when columns are reordered in the view.
     *
     *  @param  aColumn         the <code>TableColumn</code> to be added
     *  @see    #removeColumn
     */
    public void addColumn(final TableColumn aColumn) {
        scrollTable.addColumn(aColumn);
    }

    /**
     * Adds the rows from <code>index0</code> to <code>index1</code>, inclusive, to
     * the current selection.
     *
     * @param   index0 one end of the interval
     * @param   index1 the other end of the interval
     */
    public void addRowSelectionInterval(final int index0, final int index1) {
        lockedTable.addRowSelectionInterval(index0, index1);
    }

    /**
     * Deselects all selected columns and rows.
     */
    public void clearSelection() {
        lockedTable.clearSelection();
        scrollTable.clearSelection();
    }

    /**
     * Programmatically starts editing the cell at <code>row</code> and
     * <code>column</code>, if the cell is editable.
     * To prevent the <code>JTable</code> from editing a particular table,
     * column or cell value, return false from the <code>isCellEditable</code>
     * method in the <code>TableModel</code> interface.
     *
     * @param   row     the row to be edited
     * @param   column  the column to be edited
     * @param   e       event to pass into <code>shouldSelectCell</code>;
     *                  note that as of Java 2 platform v1.2, the call to
     *                  <code>shouldSelectCell</code> is no longer made
     * @return  false if for any reason the cell cannot be edited
     */
    public boolean editCellAt(final int row, final int column, final EventObject e) {
        if (column < frozenColumns) {
            return lockedTable.editCellAt(row, column, e);
        } else {
            return scrollTable.editCellAt(row, column - frozenColumns, e);
        }
    }

    /**
     * Determines whether the table will create default columns from the model.
     * If true, <code>setModel</code> will clear any existing columns and
     * create new columns from the new model.  Also, if the event in
     * the <code>tableChanged</code> notification specifies that the
     * entire table changed, then the columns will be rebuilt.
     * The default is true.
     *
     * @return  the autoCreateColumnsFromModel of the table
     * @see     #setAutoCreateColumnsFromModel
     */
    public boolean getAutoCreateColumnsFromModel() {
        return scrollTable.getAutoCreateColumnsFromModel();
    }

    /**
     * Returns the cell editor.
     *
     * @return the <code>TableCellEditor</code> that does the editing
     */
    public TableCellEditor getCellEditor() {
        TableCellEditor editor = lockedTable.getCellEditor();
        if (editor != null) {
            return editor;
        }
        return scrollTable.getCellEditor();
    }

    /**
     * Returns an appropriate editor for the cell specified by
     * <code>row</code> and <code>column</code>. If the
     * <code>TableColumn</code> for this column has a non-null editor,
     * returns that.  If not, finds the class of the data in this
     * column (using <code>getColumnClass</code>)
     * and returns the default editor for this type of data.
     * <p>
     * <b>Note:</b>
     * Throughout the table package, the internal implementations always
     * use this method to provide editors so that this default behavior
     * can be safely overridden by a subclass.
     *
     * @param row       the row of the cell to edit, where 0 is the first row
     * @param column    the column of the cell to edit,
     *          where 0 is the first column
     * @return          the editor for this cell;
     *          if <code>null</code> return the default editor for
     *          this type of cell
     */
    public TableCellEditor getCellEditor(final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.getCellEditor(row, column);
        } else {
            return scrollTable.getCellEditor(row, column - frozenColumns);
        }
    }

    /**
     * Returns an appropriate renderer for the cell specified by this row and
     * column. If the <code>TableColumn</code> for this column has a non-null
     * renderer, returns that.  If not, finds the class of the data in
     * this column (using <code>getColumnClass</code>)
     * and returns the default renderer for this type of data.
     * <p>
     * <b>Note:</b>
     * Throughout the table package, the internal implementations always
     * use this method to provide renderers so that this default behavior
     * can be safely overridden by a subclass.
     *
     * @param row       the row of the cell to render, where 0 is the first row
     * @param column    the column of the cell to render,
     *          where 0 is the first column
     * @return the assigned renderer; if <code>null</code>
     *          returns the default renderer
     *          for this type of object
     * @see javax.swing.table.TableColumn#setCellRenderer
     * @see #setDefaultRenderer
     */
    public TableCellRenderer getCellRenderer(final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.getCellRenderer(row, column);
        } else {
            return scrollTable.getCellRenderer(row, column - frozenColumns);
        }
    }

    /**
     * Returns true if both row and column selection models are enabled.
     * Equivalent to <code>getRowSelectionAllowed() &&
     * getColumnSelectionAllowed()</code>.
     *
     * @return true if both row and column selection models are enabled
     *
     * @see #setCellSelectionEnabled
     */
    public boolean getCellSelectionEnabled() {
        return lockedTable.getCellSelectionEnabled();
    }

    /**
     * Returns the <code>TableColumn</code> object for the column in the table
     * whose identifier is equal to <code>identifier</code>, when compared using
     * <code>equals</code>.
     *
     * @param identifier the identifier object
     * @return  the <code>TableColumn</code> object that matches the identifier
     * @exception IllegalArgumentException      if <code>identifier</code> is <code>null</code> or no <code>TableColumn</code>
     * has this identifier
     */
    public TableColumn getColumn(final Object identifier) throws IllegalArgumentException {
        try {
            return lockedTable.getColumn(identifier);
        } catch (IllegalArgumentException e) {
            return scrollTable.getColumn(identifier);
        }
    }

    /**
     * Returns the type of the column appearing in the view at
     * column position <code>column</code>.
     *
     * @param   column   the column in the view being queried
     * @return the type of the column at position <code>column</code>
     *      in the view where the first column is column 0
     */
    public Class getColumnClass(final int column) {
        if (column < frozenColumns) {
            return lockedTable.getColumnClass(column);
        } else {
            return scrollTable.getColumnClass(column - frozenColumns);
        }
    }

    /**
     * Returns the number of columns in the column model. Note that this may
     * be different from the number of columns in the table model.
     *
     * @return  the number of columns in the table
     * @see #getRowCount
     * @see #removeColumn
     */
    public int getColumnCount() {
        return lockedTable.getColumnCount() + scrollTable.getColumnCount();
    }

    /**
     * Returns always false.
     *
     * @return true if columns can be selected, otherwise false
     */
    public boolean getColumnSelectionAllowed() {
        return false;
    }

    /**
     * Returns the editor to be used when no editor has been set in
     * a <code>TableColumn</code>. During the editing of cells the editor is fetched from
     * a <code>Hashtable</code> of entries according to the class of the cells in the column. If
     * there is no entry for this <code>columnClass</code> the method returns
     * the entry for the most specific superclass. The <code>JTable</code> installs entries
     * for <code>Object</code>, <code>Number</code>, and <code>Boolean</code>, all of which can be modified
     * or replaced.
     *
     * @param   columnClass  return the default cell editor for this columnClass
     * @return the default cell editor to be used for this columnClass
     * @see     #setDefaultEditor
     * @see     #getColumnClass
     */
    public TableCellEditor getDefaultEditor(final Class columnClass) {
        return lockedTable.getDefaultEditor(columnClass);
    }

    /**
     * Returns the cell renderer to be used when no renderer has been set in
     * a <code>TableColumn</code>. During the rendering of cells the renderer is fetched from
     * a <code>Hashtable</code> of entries according to the class of the cells in the column. If
     * there is no entry for this <code>columnClass</code> the method returns
     * the entry for the most specific superclass. The <code>JTable</code> installs entries
     * for <code>Object</code>, <code>Number</code>, and <code>Boolean</code>, all of which can be modified
     * or replaced.
     *
     * @param   columnClass   return the default cell renderer
     *                for this columnClass
     * @return  the renderer for this columnClass
     * @see     #setDefaultRenderer
     * @see     #getColumnClass
     */
    public TableCellRenderer getDefaultRenderer(final Class columnClass) {
        return lockedTable.getDefaultRenderer(columnClass);
    }

    /**
     * Gets the value of the <code>dragEnabled</code> property.
     *
     * @return  the value of the <code>dragEnabled</code> property
     * @see #setDragEnabled
     * @since JDK 1.4
     */
    public boolean getDragEnabled() {
        return lockedTable.getDragEnabled();
    }

    /**
     * Returns the component that is handling the editing session.
     * If nothing is being edited, returns null.
     *
     * @return  Component handling editing session
     */
    public Component getEditorComponent() {
        return lockedTable.getEditorComponent();
    }

    /**
     * Returns the horizontal and vertical space between cells.
     * The default spacing is (1, 1), which provides room to draw the grid.
     *
     * @return  the horizontal and vertical spacing between cells
     * @see     #setIntercellSpacing
     */
    public Dimension getIntercellSpacing() {
        return lockedTable.getIntercellSpacing();
    }

    /**
     * Returns the preferred size of the viewport for this table.
     *
     * @return a <code>Dimension</code> object containing the <code>preferredSize</code> of the <code>JViewport</code>
     *         which displays this table
     */
    public Dimension getPreferredScrollableViewportSize() {
        return scrollPane.getPreferredSize();
    }

    /**
     * Returns the number of rows in this table's model.
     * @return the number of rows in this table's model
     *
     * @see #getColumnCount
     */
    public int getRowCount() {
        return lockedTable.getRowCount();
    }

    /**
     * Returns the height of a table row, in pixels.
     * The default row height is 16.0.
     *
     * @return  the height in pixels of a table row
     * @see     #setRowHeight(int)
     */
    public int getRowHeight() {
        return lockedTable.getRowHeight();
    }

    /**
     * Returns the height, in pixels, of the cells in <code>row</code>.
     * @param   row              the row whose height is to be returned
     * @return the height, in pixels, of the cells in the row
     */
    public int getRowHeight(final int row) {
        return lockedTable.getRowHeight(row);
    }

    /**
     * Gets the amount of empty space, in pixels, between cells. Equivalent to:
     * <code>getIntercellSpacing().height</code>.
     * @return the number of pixels between cells in a row
     *
     * @see     #setRowMargin
     */
    public int getRowMargin() {
        return lockedTable.getRowMargin();
    }

    /**
     * Returns <code>visibleRect.height</code> or
     * <code>visibleRect.width</code>,
     * depending on this table's orientation.  Note that as of Swing 1.1.1
     * (Java 2 v 1.2.2) the value
     * returned will ensure that the viewport is cleanly aligned on
     * a row boundary.
     *
     * @param visibleRect The view area visible within the viewport
     * @param orientation Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL.
     * @param direction Less than zero to scroll up/left, greater than zero for down/right.
     * @return <code>visibleRect.height</code> or
     *                  <code>visibleRect.width</code>
     *                  per the orientation
     * @see Scrollable#getScrollableBlockIncrement
     */
    public int getScrollableBlockIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
        return scrollTable.getScrollableBlockIncrement(visibleRect, orientation, direction);
    }

    /**
     * Returns false to indicate that the height of the viewport does not
     * determine the height of the table.
     *
     * @return false
     * @see Scrollable#getScrollableTracksViewportHeight
     */
    public boolean getScrollableTracksViewportHeight() {
        return scrollTable.getScrollableTracksViewportHeight();
    }

    /**
     * Returns false if <code>autoResizeMode</code> is set to
     * <code>AUTO_RESIZE_OFF</code>, which indicates that the
     * width of the viewport does not determine the width
     * of the table.  Otherwise returns true.
     *
     * @return false if <code>autoResizeMode</code> is set
     *   to <code>AUTO_RESIZE_OFF</code>, otherwise returns true
     * @see Scrollable#getScrollableTracksViewportWidth
     */
    public boolean getScrollableTracksViewportWidth() {
        return scrollTable.getScrollableTracksViewportWidth();
    }

    /**
     * Returns the scroll increment (in pixels) that completely exposes one new
     * row or column (depending on the orientation).
     * <p>
     * This method is called each time the user requests a unit scroll.
     *
     * @param visibleRect the view area visible within the viewport
     * @param orientation either <code>SwingConstants.VERTICAL</code>
     *                  or <code>SwingConstants.HORIZONTAL</code>
     * @param direction less than zero to scroll up/left,
     *                  greater than zero for down/right
     * @return the "unit" increment for scrolling in the specified direction
     * @see Scrollable#getScrollableUnitIncrement
     */
    public int getScrollableUnitIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
        return lockedTable.getScrollableUnitIncrement(visibleRect, orientation, direction);
    }

    /**
     * Returns the background color for selected cells.
     *
     * @return the <code>Color</code> used for the background of selected list items
     * @see #setSelectionBackground
     * @see #setSelectionForeground
     */
    public Color getSelectionBackground() {
        return lockedTable.getSelectionBackground();
    }

    /**
     * Returns the foreground color for selected cells.
     *
     * @return the <code>Color</code> object for the foreground property
     * @see #setSelectionForeground
     * @see #setSelectionBackground
     */
    public Color getSelectionForeground() {
        return lockedTable.getSelectionForeground();
    }

    /**
     * Returns true if the table draws horizontal lines between cells, false if it
     * doesn't. The default is true.
     *
     * @return  true if the table draws horizontal lines between cells, false if it
     *          doesn't
     * @see     #setShowHorizontalLines
     */
    public boolean getShowHorizontalLines() {
        return lockedTable.getShowHorizontalLines();
    }

    /**
     * Returns true if the table draws vertical lines between cells, false if it
     * doesn't. The default is true.
     *
     * @return  true if the table draws vertical lines between cells, false if it
     *          doesn't
     * @see     #setShowVerticalLines
     */
    public boolean getShowVerticalLines() {
        return lockedTable.getShowVerticalLines();
    }

    /**
     * Returns true if the editor should get the focus
     * when keystrokes cause the editor to be activated.
     *
     * @return  true if the editor should get the focus
     *          when keystrokes cause the editor to be
     *          activated
     *
     * @see #setSurrendersFocusOnKeystroke
     */
    public boolean getSurrendersFocusOnKeystroke() {
        return lockedTable.getSurrendersFocusOnKeystroke();
    }

    /**
     * Moves the column <code>column</code> to the position currently
     * occupied by the column <code>targetColumn</code> in the view.
     * The old column at <code>targetColumn</code> is
     * shifted left or right to make room.
     *
     * @param   column                  the index of column to be moved
     * @param   targetColumn            the new index of the column
     */
    public void moveColumn(final int column, final int targetColumn) {
        if (column < frozenColumns && targetColumn < frozenColumns) {
            // move within locked table
            lockedTable.moveColumn(column, targetColumn);
        } else if (column < frozenColumns && targetColumn >= frozenColumns) {
            // move from locked to scrollable
            final TableColumn tableColumn = lockedTable.getColumnModel().getColumn(column);
            lockedTable.removeColumn(tableColumn);
            scrollTable.addColumn(tableColumn);
            scrollTable.moveColumn(scrollTable.getColumnCount(), targetColumn - frozenColumns);
        } else if (column >= frozenColumns && targetColumn < frozenColumns) {
            // move from scrollable to locked
            final TableColumn tableColumn = scrollTable.getColumnModel().getColumn(column);
            scrollTable.removeColumn(tableColumn);
            lockedTable.addColumn(tableColumn);
            lockedTable.moveColumn(lockedTable.getColumnCount(), targetColumn);
        } else {
            // move within scrollable
            scrollTable.moveColumn(column, targetColumn);
        }
    }

    /**
     * Prepares the renderer by querying the data model for the
     * value and selection state
     * of the cell at <code>row</code>, <code>column</code>.
     * Returns the component (may be a <code>Component</code>
     * or a <code>JComponent</code>) under the event location.
     * <p>
     * <b>Note:</b>
     * Throughout the table package, the internal implementations always
     * use this method to prepare renderers so that this default behavior
     * can be safely overridden by a subclass.
     *
     * @param renderer  the <code>TableCellRenderer</code> to prepare
     * @param row       the row of the cell to render, where 0 is the first row
     * @param column    the column of the cell to render,
     *          where 0 is the first column
     * @return          the <code>Component</code> under the event location
     * TODO handle the renderer correctly across the KTables
     */
    public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.prepareRendererSuper(renderer, row, column);
        } else {
            return scrollTable.prepareRendererSuper(renderer, row, column - frozenColumns);
        }
    }

    /**
     * Removes <code>aColumn</code> from this <code>JTable</code>'s array
     * of columns. Note: this method does not remove the column of data from the
     * model; it just removes the <code>TableColumn</code> that was
     * responsible for displaying it.
     * @param aColumn the <code>TableColumn</code> to be removed
     * @see #addColumn
     */
    public void removeColumn(final TableColumn aColumn) {
        lockedTable.removeColumn(aColumn);
        scrollTable.removeColumn(aColumn);
    }

    /**
     * Deselects the columns from <code>index0</code> to <code>index1</code>, inclusive.
     *
     * @param   index0 one end of the interval
     * @param   index1 the other end of the interval
     */
    public void removeColumnSelectionInterval(final int index0, final int index1) {
        lockedTable.removeColumnSelectionInterval(index0, index1);
        scrollTable.removeColumnSelectionInterval(index0, index1);
    }

    /**
     * Discards the editor object and frees the real estate it used for
     * cell rendering.
     */
    public void removeEditor() {
        lockedTable.removeEditor();
        scrollTable.removeEditor();
    }

    /**
     * Deselects the rows from <code>index0</code> to <code>index1</code>, inclusive.
     *
     * @param   index0 one end of the interval
     * @param   index1 the other end of the interval
     */
    public void removeRowSelectionInterval(final int index0, final int index1) {
        lockedTable.removeRowSelectionInterval(index0, index1);
    }

    /**
     * Returns the index of the row that <code>point</code> lies in,
     * or -1 if the result is not in the range
     * [0, <code>getRowCount()</code>-1].
     *
     * @param   point   the location of interest
     * @return  the index of the row that <code>point</code> lies in,
     *          or -1 if the result is not in the range
     *          [0, <code>getRowCount()</code>-1]
     */
    public int rowAtPoint(final Point point) {
        return lockedTable.rowAtPoint(point);
    }

    /**
     *  Selects all rows, columns, and cells in the table.
     */
    public void selectAll() {
        lockedTable.selectAll();
    }

    /**
     * Sets this table's <code>autoCreateColumnsFromModel</code> flag.
     * This method calls <code>createDefaultColumnsFromModel</code> if
     * <code>autoCreateColumnsFromModel</code> changes from false to true.
     *
     * @param   autoCreateColumnsFromModel   true if <code>JTable</code> should automatically create columns
     * @see     #getAutoCreateColumnsFromModel
     */
    public void setAutoCreateColumnsFromModel(final boolean autoCreateColumnsFromModel) {
        lockedTable.setAutoCreateColumnsFromModel(autoCreateColumnsFromModel);
    }

    /**
     * Sets the table's auto resize mode when the table is resized.
     *
     * @param   mode One of 5 legal values:
     *                   AUTO_RESIZE_OFF,
     *                   AUTO_RESIZE_NEXT_COLUMN,
     *                   AUTO_RESIZE_SUBSEQUENT_COLUMNS,
     *                   AUTO_RESIZE_LAST_COLUMN,
     *                   AUTO_RESIZE_ALL_COLUMNS
     */
    public void setAutoResizeMode(final int mode) {
        lockedTable.setAutoResizeMode(mode);
        scrollTable.setAutoResizeMode(mode);
    }

    /**
     * Sets the <code>cellEditor</code> variable.
     *
     * @param anEditor  the TableCellEditor that does the editing
     */
    public void setCellEditor(final TableCellEditor anEditor) {
        lockedTable.setCellEditor(anEditor);
        scrollTable.setCellEditor(anEditor);
    }

    /**
     * Sets whether this table allows both a column selection and a
     * row selection to exist simultaneously. When set,
     * the table treats the intersection of the row and column selection
     * models as the selected cells. Override <code>isCellSelected</code> to
     * change this default behavior. This method is equivalent to setting
     * both the <code>rowSelectionAllowed</code> property and
     * <code>columnSelectionAllowed</code> property of the
     * <code>columnModel</code> to the supplied value.
     *
     * @param  cellSelectionEnabled     true if simultaneous row and column
     *                  selection is allowed
     * @see #getCellSelectionEnabled
     * @see #isCellSelected
     */
    public void setCellSelectionEnabled(final boolean cellSelectionEnabled) {
        lockedTable.setCellSelectionEnabled(cellSelectionEnabled);
        scrollTable.setCellSelectionEnabled(cellSelectionEnabled);
    }

    /**
     * Sets a default cell editor to be used if no editor has been set in
     * a <code>TableColumn</code>. If no editing is required in a table, or a
     * particular column in a table, uses the <code>isCellEditable</code>
     * method in the <code>TableModel</code> interface to ensure that this
     * <code>JTable</code> will not start an editor in these columns.
     * If editor is <code>null</code>, removes the default editor for this
     * column class.
     *
     * @param  columnClass  set the default cell editor for this columnClass
     * @param  editor   default cell editor to be used for this columnClass
     * @see     TableModel#isCellEditable
     * @see     #getDefaultEditor
     * @see     #setDefaultRenderer
     */
    public void setDefaultEditor(final Class columnClass, final TableCellEditor editor) {
        lockedTable.setDefaultEditor(columnClass, editor);
        scrollTable.setDefaultEditor(columnClass, editor);
    }

    /**
     * Sets a default cell renderer to be used if no renderer has been set in
     * a <code>TableColumn</code>. If renderer is <code>null</code>,
     * removes the default renderer for this column class.
     *
     * @param  columnClass     set the default cell renderer for this columnClass
     * @param  renderer        default cell renderer to be used for this columnClass
     * @see     #getDefaultRenderer
     * @see     #setDefaultEditor
     */
    public void setDefaultRenderer(final Class columnClass, final TableCellRenderer renderer) {
        lockedTable.setDefaultRenderer(columnClass, renderer);
        scrollTable.setDefaultRenderer(columnClass, renderer);
    }

    /**
     * Sets the <code>dragEnabled</code> property,
     * which must be <code>true</code> to enable
     * automatic drag handling (the first part of drag and drop)
     * on this component.
     * The <code>transferHandler</code> property needs to be set
     * to a non-<code>null</code> value for the drag to do
     * anything.  The default value of the <code>dragEnabled</code>
     * property
     * is <code>false</code>.
     *
     * <p>
     *
     * When automatic drag handling is enabled,
     * most look and feels begin a drag-and-drop operation
     * whenever the user presses the mouse button over a selection
     * and then moves the mouse a few pixels.
     * Setting this property to <code>true</code>
     * can therefore have a subtle effect on
     * how selections behave.
     *
     * <p>
     *
     * Some look and feels might not support automatic drag and drop;
     * they will ignore this property.  You can work around such
     * look and feels by modifying the component
     * to directly call the <code>exportAsDrag</code> method of a
     * <code>TransferHandler</code>.
     *
     * @param b the value to set the <code>dragEnabled</code> property to
     * @see #getDragEnabled
     * @since JDK 1.4
     */
    public void setDragEnabled(final boolean b) {
        lockedTable.setDragEnabled(b);
        scrollTable.setDragEnabled(b);
    }

    /**
     * Sets the <code>editingColumn</code> variable.
     * @param aColumn  the column of the cell to be edited
     */
    public void setEditingColumn(final int aColumn) {
        if (aColumn < frozenColumns) {
            lockedTable.setEditingColumn(aColumn);
        } else {
            scrollTable.setEditingColumn(aColumn - frozenColumns);
        }
    }

    /**
     * Sets the <code>rowMargin</code> and the <code>columnMargin</code> --
     * the height and width of the space between cells -- to
     * <code>intercellSpacing</code>.
     *
     * @param   intercellSpacing        a <code>Dimension</code>
     *                  specifying the new width
     *                  and height between cells
     * @see     #getIntercellSpacing
     */
    public void setIntercellSpacing(final Dimension intercellSpacing) {
        lockedTable.setIntercellSpacing(intercellSpacing);
    }

    /**
     * Sets the data model for this table to <code>newModel</code> and registers
     * with it for listener notifications from the new data model.
     *
     * @param   dataModel        the new data source for this table
     * @see     #getModel
     */
    public void setModel(final TableModel dataModel) {
        // Remove all columns from the scrollable table
        final TableColumnModel scrollColumnModel = scrollTable.getColumnModel();
        while (scrollColumnModel.getColumnCount() > 0) {
            scrollColumnModel.removeColumn(scrollColumnModel.getColumn(0));
        }
        // Remove all columns from the locked table
        final TableColumnModel lockedColumnModel = lockedTable.getColumnModel();
        while (lockedTable.getColumnCount() > 0) {
            lockedColumnModel.removeColumn(lockedColumnModel.getColumn(0));
        }
        lockedTable.setModel(dataModel);
        scrollTable.setModel(dataModel);
        // Create new columns from the new data model
        lockedTable.createDefaultColumnsFromModel();
        scrollTable.createDefaultColumnsFromModel();
        // Check if new model has enough columns for current number of frozen columns
        if (frozenColumns > dataModel.getColumnCount()) {
            frozenColumns = dataModel.getColumnCount();
        }
        // Remove the locked columns from the scrollable table
        for (int i = 0; i < frozenColumns; i++) {
            scrollColumnModel.removeColumn(scrollColumnModel.getColumn(0));
        }
        // Remove the scrollable columns from the locked table
        while (lockedTable.getColumnCount() > frozenColumns) {
            lockedColumnModel.removeColumn(lockedColumnModel.getColumn(frozenColumns));
        }
        // add size listeners to all locked columns
        for (int i = 0; i < lockedColumnModel.getColumnCount(); i++) {
            lockedColumnModel.getColumn(i).addPropertyChangeListener(new ColumnWidthChangeListener());
        }
    }

    /**
     * Sets the preferred size of the viewport for this table.
     *
     * @param size  a <code>Dimension</code> object specifying the <code>preferredSize</code> of a
     *              <code>JViewport</code> whose view is this table
     * @see Scrollable#getPreferredScrollableViewportSize
     */
    public void setPreferredScrollableViewportSize(final Dimension size) {
        scrollTable.setPreferredScrollableViewportSize(size);
    }

    /**
     * Sets the height, in pixels, of all cells to <code>rowHeight</code>,
     * revalidates, and repaints.
     * The height of the cells will be equal to the row height minus
     * the row margin.
     *
     * @param   rowHeight                       new row height
     * @see     #getRowHeight()
     */
    public void setRowHeight(final int rowHeight) {
        lockedTable.setRowHeight(rowHeight);
        scrollTable.setRowHeight(rowHeight);
    }

    /**
     * Sets the height for <code>row</code> to <code>rowHeight</code>,
     * revalidates, and repaints. The height of the cells in this row
     * will be equal to the row height minus the row margin.
     *
     * @param row the row whose height is being changed
     * @param rowHeight new row height, in pixels
     */
    public void setRowHeight(final int row, final int rowHeight) {
        lockedTable.setRowHeight(row, rowHeight);
        scrollTable.setRowHeight(row, rowHeight);
    }

    /**
     * Sets the amount of empty space between cells in adjacent rows.
     *
     * @param  rowMargin  the number of pixels between cells in a row
     * @see     #getRowMargin
     */
    public void setRowMargin(final int rowMargin) {
        lockedTable.setRowMargin(rowMargin);
        scrollTable.setRowMargin(rowMargin);
    }

    /**
     * Selects the rows from <code>index0</code> to <code>index1</code>,
     * inclusive.
     *
     * @param   index0 one end of the interval
     * @param   index1 the other end of the interval
     */
    public void setRowSelectionInterval(final int index0, final int index1) {
        lockedTable.setRowSelectionInterval(index0, index1);
        scrollTable.setRowSelectionInterval(index0, index1);
    }

    /**
     * Sets the row selection model for this table to <code>newModel</code>
     * and registers for listener notifications from the new selection model.
     *
     * @param   newModel        the new selection model
     * @see     #getSelectionModel
     */
    public void setSelectionModel(final ListSelectionModel newModel) {
        lockedTable.setSelectionModel(newModel);
        scrollTable.setSelectionModel(newModel);
    }

    /**
     * Sets whether editors in this JTable get the keyboard focus
     * when an editor is activated as a result of the JTable
     * forwarding keyboard events for a cell.
     * By default, this property is false, and the JTable
     * retains the focus unless the cell is clicked.
     *
     * @param surrendersFocusOnKeystroke true if the editor should get the focus
     *          when keystrokes cause the editor to be
     *          activated
     *
     *
     * @see #getSurrendersFocusOnKeystroke
     */
    public void setSurrendersFocusOnKeystroke(final boolean surrendersFocusOnKeystroke) {
        lockedTable.setSurrendersFocusOnKeystroke(surrendersFocusOnKeystroke);
        scrollTable.setSurrendersFocusOnKeystroke(surrendersFocusOnKeystroke);
    }

    /**
     * Sizes the table columns to fit the available space.
     * @param lastColumnOnly
     * @deprecated As of Swing version 1.0.3,
     * replaced by <code>doLayout()</code>.
     * @see #doLayout()
     */
    public void sizeColumnsToFit(final boolean lastColumnOnly) {
        scrollTable.sizeColumnsToFit(lastColumnOnly);
    }

    /**
     * Obsolete as of Java 2 platform v1.4.  Please use the
     * <code>doLayout()</code> method instead.
     * @param resizingColumn    the column whose resizing made this adjustment
     *                          necessary or -1 if there is no such column
     * @see  #doLayout()
     */
    public void sizeColumnsToFit(final int resizingColumn) {
        scrollTable.sizeColumnsToFit(resizingColumn);
    }

    /**
     * Returns the table header.
     *
     * @return table header
     */
    public JXTableHeader getTableHeader() {
        return collTableHeader;
    }

    /**
     * @see javax.swing.JComponent#setFont(java.awt.Font)
     */
    public void setFont(final Font f) {
        final double f12 = 1d + (2d / (2d + 2d + 2d + 2d + 2d)); // 1.2
        lockedTable.setFont(f);
        scrollTable.setFont(f);
        final Graphics graphics = lockedTable.getGraphics();
        final int height;
        if (graphics != null) {
            final Rectangle2D rectangle2D = graphics.getFontMetrics(f).getMaxCharBounds(lockedTable.getGraphics());
            height = (int) (rectangle2D.getHeight() + 1);
        } else {
            height = (int) (f.getSize() * f12 + 2 * lockedTable.getRowMargin());
        }
        lockedTable.setRowHeight(height);
        scrollTable.setRowHeight(height);
    }

    /**
     * Sets whether the columns in this model can be selected.
     *
     * @param b true if this model will allow column selection
     * @see #getColumnSelectionAllowed
     */
    public void setColumnSelectionAllowed(final boolean b) {
        lockedTable.setColumnSelectionAllowed(b);
        scrollTable.setColumnSelectionAllowed(b);
    }

    /**
     * Returns the horizontal scrollbar of the table.
     *
     * @return the horizontal scrollbar
     */
    public JScrollBar getHorizontalScrollBar() {
        return scrollPane.getHorizontalScrollBar();
    }

    /**
     * Returns the vertical scrollbar of the table.
     *
     * @return the vertical scrollbar
     */
    public JScrollBar getVerticalScrollBar() {
        return scrollPane.getVerticalScrollBar();
    }

    /**
     * Determines when the horizontal scrollbar appears in the scrollpane.
     * The options are:<ul>
     * <li>JXTable.HORIZONTAL_SCROLLBAR_AS_NEEDED
     * <li>JXTable.HORIZONTAL_SCROLLBAR_NEVER
     * <li>JXTable.HORIZONTAL_SCROLLBAR_ALWAYS
     * </ul>
     *
     * @param policy one of the three values listed above
     * @see #getHorizontalScrollBarPolicy
     */
    public void setHorizontalScrollBarPolicy(final int policy) {
        scrollPane.setHorizontalScrollBarPolicy(policy);
    }

    /**
     * Returns the horizontal scroll bar policy value.
     * @return the <code>horizontalScrollBarPolicy</code> property
     * @see #setHorizontalScrollBarPolicy
     */
    public int getHorizontalScrollBarPolicy() {
        return scrollPane.getHorizontalScrollBarPolicy();
    }

    /**
     * Determines when the vertical scrollbar appears in the scrollpane.
     * Legal values are:
     * <ul>
     * <li>JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
     * <li>JScrollPane.VERTICAL_SCROLLBAR_NEVER
     * <li>JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
     * </ul>
     *
     * @param policy one of the three values listed above
     * @throws IllegalArgumentException if <code>policy</code>
     *         is not one of the legal values shown above
     * @see #getVerticalScrollBarPolicy
     */
    public void setVerticalScrollBarPolicy(final int policy) throws IllegalArgumentException {
        scrollPane.setVerticalScrollBarPolicy(policy);
    }

    /**
     * Returns the vertical scroll bar policy value.
     * @return the <code>verticalScrollBarPolicy</code> property
     * @see #setVerticalScrollBarPolicy
     */
    public int getVerticalScrollBarPolicy() {
        return scrollPane.getVerticalScrollBarPolicy();
    }

    /**
     * Action of <tt>Goto First</tt> key events in scrollable table.
     */
    private final class ScrollFirstAction extends AbstractAction {
        /** the original action. */
        private final Action originalAction;
        /** Contructor.
         * @param theOriginalAction the original action */
        private ScrollFirstAction(final Action theOriginalAction) {
            super();
            this.originalAction = theOriginalAction;
        }
        /** @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
        public void actionPerformed(final ActionEvent e) {
            if (e.getSource() == scrollTable && frozenColumns > 0 && scrollTable.getSelectedColumn() == 0) {
                scrollTable.transferFocusBackward();
                lockedTable.changeSelection(scrollTable.getSelectedRow(), 0, false, false);
            } else {
                originalAction.actionPerformed(e);
            }
        }
    }

    /**
     * Action of <tt>Goto Last</tt> key events in locked table.
     */
    private final class LockedLastAction extends AbstractAction {
        /** the original action. */
        private final Action originalAction;
        /** Contructor.
         * @param theOriginalAction the original action */
        private LockedLastAction(final Action theOriginalAction) {
            super();
            this.originalAction = theOriginalAction;
        }
        /** @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
        public void actionPerformed(final ActionEvent e) {
            if (e.getSource() == lockedTable && scrollTable.getColumnCount() > 0
                && lockedTable.getSelectedColumn() == lockedTable.getColumnCount() - 1) {
                lockedTable.transferFocus();
                scrollTable.changeSelection(scrollTable.getSelectedRow(), scrollTable.getColumnCount() - 1, false, false);
            } else {
                originalAction.actionPerformed(e);
            }
        }
    }

    /**
     * Action of <tt>Next</tt> key events in locked table.
     */
    private final class LockedNextAction extends AbstractAction {
        /** the original action. */
        private final Action originalAction;
        /** Contructor.
         * @param theOriginalAction the original action */
        private LockedNextAction(final Action theOriginalAction) {
            super();
            this.originalAction = theOriginalAction;
        }
        /** @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
        public void actionPerformed(final ActionEvent e) {
            if (lockedTable.getSelectedColumn() == lockedTable.getColumnCount() - 1 && scrollTable.getColumnCount() > 0) {
                lockedTable.transferFocus();
                scrollTable.changeSelection(lockedTable.getSelectedRow(), 0, false, false);
            } else {
                originalAction.actionPerformed(e);
            }
        }
    }

    /**
     * Action of <tt>Next</tt> key events in scrollable table.
     */
    private final class ScrollNextAction extends AbstractAction {
        /** the original action. */
        private final Action originalAction;
        /** Contructor.
         * @param theOriginalAction the original action */
        private ScrollNextAction(final Action theOriginalAction) {
            super();
            this.originalAction = theOriginalAction;
        }
        /** @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
        public void actionPerformed(final ActionEvent e) {
            if (scrollTable.getSelectedColumn() == scrollTable.getColumnCount() - 1 && lockedTable.getColumnCount() > 0) {
                scrollTable.transferFocusBackward();
                lockedTable.changeSelection(nextRow(scrollTable), 0, false, false);
            } else {
                originalAction.actionPerformed(e);
            }
        }
    }

    /**
     * Action of <tt>Previous</tt> key events in scrollable table.
     */
    private final class ScrollPreviousAction extends AbstractAction {
        /** the original action. */
        private final Action originalAction;
        /** Contructor.
         * @param theOriginalAction the original action */
        private ScrollPreviousAction(final Action theOriginalAction) {
            super();
            this.originalAction = theOriginalAction;
        }
        /** @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
        public void actionPerformed(final ActionEvent e) {
            if (scrollTable.getSelectedColumn() == 0 && frozenColumns > 0) {
                scrollTable.transferFocusBackward();
                lockedTable.changeSelection(scrollTable.getSelectedRow(), lockedTable.getColumnCount() - 1, false, false);
            } else {
                originalAction.actionPerformed(e);
            }
        }
    }

    /**
     * Action of <tt>Previous</tt> key events in locked table.
     */
    private final class LockedPreviousAction extends AbstractAction {
        /** the original action. */
        private final Action originalAction;
        /** Contructor.
         * @param theOriginalAction the original action */
        private LockedPreviousAction(final Action theOriginalAction) {
            super();
            this.originalAction = theOriginalAction;
        }
        /** @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
        public void actionPerformed(final ActionEvent e) {
            if (lockedTable.getSelectedColumn() == 0 && scrollTable.getColumnCount() > 0) {
                lockedTable.transferFocus();
                scrollTable.changeSelection(previousRow(scrollTable), scrollTable.getColumnCount() - 1, false, false);
            } else {
                originalAction.actionPerformed(e);
            }
        }
    }

    /**
     * @return the table column model
     */
    public TableColumnModel getColumnModel() {
        return columnModel;
    }

    /**
     * Sets the column model for this table to <code>newModel</code> and registers
     * for listener notifications from the new column model. Also sets
     * the column model of the <code>JTableHeader</code> to <code>columnModel</code>.
     *
     * @param   cm        the new data source for this table
     * @exception IllegalArgumentException      if <code>columnModel</code> is <code>null</code>
     * @see     #getColumnModel
     */
    public void setColumnModel(final TableColumnModel cm) throws IllegalArgumentException {
        if (columnModel == null) {
            throw new IllegalArgumentException("Cannot set a null ColumnModel");
        }
        columnModel = cm;
        final TableColumnModel cm1 = this.createDefaultColumnModel();
        final TableColumnModel cm2 = this.createDefaultColumnModel();
        for (int i = 0; i < cm.getColumnCount(); i++) {
            cm1.addColumn(cm.getColumn(i));
            cm2.addColumn(cm.getColumn(i));
        }
        lockedTable.setColumnModel(cm1);
        scrollTable.setColumnModel(cm2);
        // Remove the locked columns from the scrollable table
        final TableColumnModel scrollColumnModel = scrollTable.getColumnModel();
        for (int i = 0; i < frozenColumns; i++) {
            scrollColumnModel.removeColumn(scrollColumnModel.getColumn(0));
        }
        // Remove the scrollable columns from the locked table
        while (lockedTable.getColumnCount() > frozenColumns) {
            cm1.removeColumn(cm1.getColumn(frozenColumns));
        }
        // add size listeners to all locked columns
        for (int i = 0; i < cm1.getColumnCount(); i++) {
            cm1.getColumn(i).addPropertyChangeListener(new ColumnWidthChangeListener());
        }
    }

    /**
     * This fine grain notification tells listeners the exact range
     * of cells, rows, or columns that changed.
     *
     * @param e a tableModelEvent
     */
    public void tableChanged(final TableModelEvent e) {
        final int column = e.getColumn();
        if (column < frozenColumns) {
            lockedTable.tableChanged(e);
        } else {
            TableModelEvent event = new TableModelEvent((TableModel)
                e.getSource(), e.getFirstRow(), e.getLastRow(),
                e.getColumn()  - frozenColumns, e.getType());
            scrollTable.tableChanged(event);
        }
    }

    /**
     * Tells listeners that a column was moved due to a margin change.
     *
     * @param e a ChangeEvent
     */
    public void columnMarginChanged(final ChangeEvent e) {
        lockedTable.columnMarginChanged(e);
        scrollTable.columnMarginChanged(e);
    }

    /**
     * Tells listeners that the selection model of the
     * TableColumnModel changed.
     *
     * @param e a ListSelectionEvent
     */
    public void columnSelectionChanged(final ListSelectionEvent e) {
        lockedTable.columnSelectionChanged(e);
        scrollTable.columnSelectionChanged(e);
    }

    /**
     * Tells listeners that a column was added to the model.
     *
     * @param e a TableColumnModelEvent
     */
    public void columnAdded(final TableColumnModelEvent e) {
        lockedTable.columnAdded(e);
        scrollTable.columnAdded(e);
    }

    /**
     * Tells listeners that a column was repositioned.
     *
     * @param e a TableColumnModelEvent
     */
    public void columnMoved(final TableColumnModelEvent e) {
        lockedTable.columnMoved(e);
        scrollTable.columnMoved(e);
    }

    /**
     * Tells listeners that a column was removed from the model.
     *
     * @param e a TableColumnModelEvent
     */
    public void columnRemoved(final TableColumnModelEvent e) {
        final int from = e.getFromIndex();
        final int to = e.getToIndex();
        if (to < frozenColumns) {
            lockedTable.columnRemoved(e);
        } else if (from >= frozenColumns) {
            scrollTable.columnRemoved(e);
        } else {
            TableColumnModelEvent e1 = new TableColumnModelEvent((TableColumnModel) e.getSource(), from, frozenColumns - 1);
            TableColumnModelEvent e2 = new TableColumnModelEvent((TableColumnModel) e.getSource(), frozenColumns, to);
            lockedTable.columnRemoved(e1);
            scrollTable.columnRemoved(e2);
        }
    }

    /**
     * Called whenever the value of the selection changes.
     * @param e the event that characterizes the change.
     */
    public void valueChanged(final ListSelectionEvent e) {
        lockedTable.valueChanged(e);
        scrollTable.valueChanged(e);
    }

    /**
     * This tells the listeners the editor has canceled editing.
     *
     * @param e a ChangeEvent
     */
    public void editingCanceled(final ChangeEvent e) {
        lockedTable.superEditingCanceled(e);
        scrollTable.superEditingCanceled(e);
    }

    /**
     * This tells the listeners the editor has ended editing.
     *
     * @param e a ChangeEvent
     */
    public void editingStopped(final ChangeEvent e) {
        lockedTable.superEditingStopped(e);
        scrollTable.superEditingStopped(e);
    }

    /**
     * Sets the <code>tableHeader</code> working with this <code>JTable</code>
     * to <code>newHeader</code>.
     * It is legal to have a <code>null</code> <code>tableHeader</code>.
     *
     * @param   newHeader new tableHeader
     * @see     #getTableHeader
     */
    public void setTableHeader(final JXTableHeader newHeader) {
        if (collTableHeader != newHeader) {
            final JXTableHeader oldHeader = collTableHeader;
            if (oldHeader != null) {
                oldHeader.setTable(null);
            }
            collTableHeader = newHeader;
            lockedHeader = newHeader.getLockedHeader();
            scrollHeader = newHeader.getScrollHeader();
            lockedHeader.setColumnModel(lockedTable.getColumnModel());
            scrollHeader.setColumnModel(scrollTable.getColumnModel());
            lockedHeader.setTable(lockedTable);
            scrollHeader.setTable(scrollTable);
            lockedTable.setTableHeader(lockedHeader);
            scrollTable.setTableHeader(scrollHeader);
            scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, lockedHeader);
        }
    }

    /**
     * Returns the auto resize mode of the table.  The default mode
     * is AUTO_RESIZE_SUBSEQUENT_COLUMNS.
     *
     * @return  the autoResizeMode of the table
     *
     * @see     #setAutoResizeMode(int)
     * @see     #doLayout()
     */
    public int getAutoResizeMode() {
        return lockedTable.getAutoResizeMode();
    }

    /**
     * Creates default columns for the table from
     * the data model using the <code>getColumnCount</code> method
     * defined in the <code>TableModel</code> interface.
     * <p>
     * Clears any existing columns before creating the
     * new columns based on information from the model.
     *
     * @see     #getAutoCreateColumnsFromModel
     */
    public void createDefaultColumnsFromModel() {
        createDefaultLockedColumnsFromModel();
        createDefaultScrollColumnsFromModel();
    }

    /**
     * Creates default columns for the locked table from
     * the data model using the <code>getColumnCount</code> method
     * defined in the <code>TableModel</code> interface.
     * <p>
     * Clears any existing columns before creating the
     * new columns based on information from the model.
     * <p>
     * If the model contains more than the number of frozen columns,
     * only the number of frozen columns is created.
     *
     * @see     #getAutoCreateColumnsFromModel
     */
    private void createDefaultLockedColumnsFromModel() {
        final TableModel m = getModel();
        if (m != null) {
            // Remove any current columns
            final TableColumnModel cm = lockedTable.getColumnModel();
            while (cm.getColumnCount() > 0) {
                cm.removeColumn(cm.getColumn(0));
            }
            // Create new columns from the data model info
            int columnCount = Math.min(frozenColumns - 1, m.getColumnCount());
            for (int i = 0; i < columnCount; i++) {
                TableColumn newColumn = new TableColumn(i);
                addColumn(newColumn);
            }
        }
    }

    /**
     * Creates default columns for the scrollable table from
     * the data model using the <code>getColumnCount</code> method
     * defined in the <code>TableModel</code> interface and the number
     * of frozen columns .
     * <p>
     * Clears any existing columns before creating the
     * new columns based on information from the model.
     * <p>
     * If the model contains less than the number of frozen columns,
     * no columns are created.
     *
     * @see     #getAutoCreateColumnsFromModel
     */
    private void createDefaultScrollColumnsFromModel() {
        TableModel m = getModel();
        if (m != null) {
            // Remove any current columns
            TableColumnModel cm = scrollTable.getColumnModel();
            while (cm.getColumnCount() > 0) {
                cm.removeColumn(cm.getColumn(0));
            }
            // Create new columns from the data model info
            int columnCount = m.getColumnCount() - frozenColumns;
            if (columnCount > 0) {
                for (int i = frozenColumns; i < m.getColumnCount(); i++) {
                    TableColumn newColumn = new TableColumn(i);
                    addColumn(newColumn);
                }
            }
        }
    }

    /**
     * Selects the columns from <code>index0</code> to <code>index1</code>,
     * inclusive.
     *
     * @param   index0 one end of the interval
     * @param   index1 the other end of the interval
     * @exception IllegalArgumentException      if <code>index0</code> or
     *                      <code>index1</code> lie outside
     *                                          [0, <code>getColumnCount()</code>-1]
     */
    public void setColumnSelectionInterval(final int index0, final int index1) throws IllegalArgumentException {
        if (index1 < frozenColumns) {
            final int count = scrollTable.getColumnCount();
            if (count > 0) {
                scrollTable.removeColumnSelectionInterval(0, count - 1);
            }
            lockedTable.setColumnSelectionInterval(index0, index1);
        } else if (index0 >= frozenColumns) {
            final int count = lockedTable.getColumnCount();
            if (count > 0) {
                lockedTable.removeColumnSelectionInterval(0, count - 1);
            }
            scrollTable.setColumnSelectionInterval(index0 - frozenColumns, index1 - frozenColumns);
        } else {
            lockedTable.setColumnSelectionInterval(index0, frozenColumns - 1);
            scrollTable.setColumnSelectionInterval(frozenColumns, index1 + frozenColumns);
        }
    }

    /**
     * Adds the columns from <code>index0</code> to <code>index1</code>,
     * inclusive, to the current selection.
     *
     * @param   index0 one end of the interval
     * @param   index1 the other end of the interval
     * @exception IllegalArgumentException      if <code>index0</code> or
     *                      <code>index1</code> lie outside
     *                                          [0, <code>getColumnCount()</code>-1]
     */
    public void addColumnSelectionInterval(final int index0, final int index1) throws IllegalArgumentException {
        if (index1 < frozenColumns) {
            lockedTable.setColumnSelectionInterval(index0, index1);
        } else if (index0 >= frozenColumns) {
            scrollTable.addColumnSelectionInterval(index0 - frozenColumns, index1 - frozenColumns);
        } else {
            lockedTable.addColumnSelectionInterval(index0, frozenColumns - 1);
            scrollTable.addColumnSelectionInterval(frozenColumns, index1 + frozenColumns);
        }
    }

    /**
     * Returns the index of the first selected column,
     * -1 if no column is selected.
     * @return the index of the first selected column
     */
    public int getSelectedColumn() {
        final int i = lockedTable.getSelectedColumn();
        return i == -1 ?  frozenColumns + scrollTable.getSelectedColumn() : i;
    }

    /**
     * Returns the indices of all selected columns.
     *
     * @return an array of integers containing the indices of all selected columns,
     *         or an empty array if no column is selected
     * @see #getSelectedColumn
     */
    public int[] getSelectedColumns() {
        int[] i1 = lockedTable.getSelectedColumns();
        int[] i2 = scrollTable.getSelectedColumns();
        int[] i3 = new int[i1.length + i2.length];
        for (int i = 0; i < i1.length; i++) {
            i3[i] = i1[i];
        }
        for (int i = 0; i < i2.length; i++) {
            i3[i + i1.length] = i2[i] + frozenColumns;
        }
        return i3;
    }

    /**
     * Returns the number of selected columns.
     *
     * @return the number of selected columns, 0 if no columns are selected
     */
    public int getSelectedColumnCount() {
        return lockedTable.getSelectedColumnCount() + scrollTable.getSelectedColumnCount();
    }

    /**
     * Returns true if the column at the specified index is selected.
     *
     * @param   column   the column in the column model
     * @return true if the column at index <code>column</code> is selected, where
     *              0 is the first column
     * @exception IllegalArgumentException      if <code>column</code> is not in the
     *                                          valid range
     */
    public boolean isColumnSelected(final int column) throws IllegalArgumentException {
        return lockedTable.isColumnSelected(column) || scrollTable.isColumnSelected(column - frozenColumns);
    }

    /**
     * Returns the index of the column that <code>point</code> lies in,
     * or -1 if the result is not in the range
     * [0, <code>getColumnCount()</code>-1].
     *
     * @param   point   the location of interest
     * @return  the index of the column that <code>point</code> lies in,
     *      or -1 if the result is not in the range
     *      [0, <code>getColumnCount()</code>-1]
     * @see     #rowAtPoint
     */
    public int columnAtPoint(final Point point) {
        int lockedWidth = lockedTable.getWidth();
        if (point.x < lockedWidth) {
            return lockedTable.columnAtPoint(point);
        } else {
            Point p = new Point(point.x - lockedWidth, point.y);
            return frozenColumns + scrollTable.columnAtPoint(p);
        }
    }

    /**
     * Returns a rectangle for the cell that lies at the intersection of
     * <code>row</code> and <code>column</code>.
     * If <code>includeSpacing</code> is true then the value returned
     * has the full height and width of the row and column
     * specified. If it is false, the returned rectangle is inset by the
     * intercell spacing to return the true bounds of the rendering or
     * editing component as it will be set during rendering.
     * <p>
     * If the column index is valid but the row index is less
     * than zero the method returns a rectangle with the
     * <code>y</code> and <code>height</code> values set appropriately
     * and the <code>x</code> and <code>width</code> values both set
     * to zero. In general, when either the row or column indices indicate a
     * cell outside the appropriate range, the method returns a rectangle
     * depicting the closest edge of the closest cell that is within
     * the table's range. When both row and column indices are out
     * of range the returned rectangle covers the closest
     * point of the closest cell.
     * <p>
     * In all cases, calculations that use this method to calculate
     * results along one axis will not fail because of anomalies in
     * calculations along the other axis. When the cell is not valid
     * the <code>includeSpacing</code> parameter is ignored.
     *
     * @param   row                   the row index where the desired cell
     *                                is located
     * @param   column                the column index where the desired cell
     *                                is located in the display; this is not
     *                                necessarily the same as the column index
     *                                in the data model for the table; the
     *                                {@link #convertColumnIndexToView(int)}
     *                                method may be used to convert a data
     *                                model column index to a display
     *                                column index
     * @param   includeSpacing        if false, return the true cell bounds -
     *                                computed by subtracting the intercell
     *                    spacing from the height and widths of
     *                    the column and row models
     *
     * @return  the rectangle containing the cell at location
     *          <code>row</code>,<code>column</code>
     */
    public Rectangle getCellRect(final int row, final int column, final boolean includeSpacing) {
        if (column < frozenColumns) {
            return lockedTable.getCellRect(row, column, includeSpacing);
        } else {
            return scrollTable.getCellRect(row, column - frozenColumns, includeSpacing);
        }
    }

    /**
     * Returns the L&F object that renders this component.
     *
     * @return the <code>TableUI</code> object that renders this component
     */
    public TableUI getUI() {
        return lockedTable.getUI();
    }

    /**
     * Sets the L&F object that renders this component and repaints.
     *
     * @param tableUi  the TableUI L&F object
     * @see javax.swing.JTable#setUI(javax.swing.plaf.TableUI)
     */
    public void setUI(final TableUI tableUi) {
        lockedTable.setUI(tableUi);
        scrollTable.setUI(tableUi);
    }

    /**
     * Prepares the editor by querying the data model for the value and
     * selection state of the cell at <code>row</code>, <code>column</code>.
     * <p>
     * <b>Note:</b>
     * Throughout the table package, the internal implementations always
     * use this method to prepare editors so that this default behavior
     * can be safely overridden by a subclass.
     *
     * @param editor  the <code>TableCellEditor</code> to set up
     * @param row     the row of the cell to edit,
     *            where 0 is the first row
     * @param column  the column of the cell to edit,
     *            where 0 is the first column
     * @return the <code>Component</code> being edited
     */
    public Component prepareEditor(final TableCellEditor editor, final int row, final int column) {
        if (column < frozenColumns) {
            return lockedTable.prepareEditor(editor, row, column);
        } else {
            return scrollTable.prepareEditor(editor, row, column - frozenColumns);
        }
    }

    /**
     * Appends <code>aColumn</code> after the currently last frozen column
     * held by this <code>JTable</code>'s column model.
     * If the column name of <code>aColumn</code> is <code>null</code>,
     * sets the column name of <code>aColumn</code> to the name
     * returned by <code>getModel().getColumnName()</code>.
     * <p>
     * The number of frozenn column is increased by one with a call to
     * this method.
     * <p>
     * To add a column to this <code>JTable</code> to display the
     * <code>modelColumn</code>'th column of data in the model with a
     * given <code>width</code>, <code>cellRenderer</code>,
     * and <code>cellEditor</code> you can use:
     * <pre>
     *
     *     addColumn(new TableColumn(modelColumn, width, cellRenderer, cellEditor));
     *
     * </pre>
     * [Any of the <code>TableColumn</code> constructors can be used
     * instead of this one.]
     * The model column number is stored inside the <code>TableColumn</code>
     * and is used during rendering and editing to locate the appropriates
     * data values in the model. The model column number does not change
     * when columns are reordered in the view.
     *
     * @param  aColumn         the <code>TableColumn</code> to be added
     * @see    #removeColumn
     */
    public void addFrozenColumn(final TableColumn aColumn) {
        lockedTable.addColumn(aColumn);
        frozenColumns++;
    }

    /**
     * Removes <code>aColumn</code> from this <code>JXTable</code>'s
     * frozen columns.  Note: this method does not remove the column
     * of data from the model; it just removes the frozen
     * <code>TableColumn</code> that was responsible for displaying it.
     * <p>
     * The number of frozenn column is decreased by one with a call to
     * this method.
     *
     * @param  aColumn         the <code>TableColumn</code> to be removed
     * @see    #addFrozenColumn
     */
    public void removeFrozenColumn(final TableColumn aColumn) {
        lockedTable.removeColumn(aColumn);
        frozenColumns++;
    }

    /**
     * Returns the scroll pane adjuster.
     * @return the scroll pane adjuster
     */
    protected JScrollPaneAdjuster getAdjuster() {
        return adjuster;
    }

    /**
     * Sets the <code>transferHandler</code> property,
     * which is <code>null</code> if the component does
     * not support data transfer operations.
     * <p>
     * If <code>newHandler</code> is not null, and the system property
     * <code>suppressSwingDropSupport</code> is not true, this will
     * install a <code>DropTarget</code> on the <code>JComponent</code>.
     * The default for the system property is false, so that a
     * <code>DropTarget</code> will be added.
     *
     * @param newHandler  mechanism for transfer of data to
     *    and from the component
     *
     * @see TransferHandler
     * @see javax.swing.JComponent#setTransferHandler(javax.swing.TransferHandler)
     */
    public void setTransferHandler(final TransferHandler newHandler) {
        //The KTables are the ones that need the handler
        getLockedTable().setTransferHandler(newHandler);
        getScrollTable().setTransferHandler(newHandler);
    }

    /**
     * Gets the <code>transferHandler</code> property.
     *
     * @return  the value of the <code>transferHandler</code> property
     *
     * @see TransferHandler
     * @see #setTransferHandler
     */
    public TransferHandler getTransferHandler() {
        //Both KTables should have the same handler. Return any of those.
        return getLockedTable().getTransferHandler();
    }

    /**
     * Adds the specified mouse listener to receive mouse events from this
     * component. If listener <code>l</code> is <code>null</code>, no
     * exception is thrown and no action is performed.
     * @param l the mouse listener
     * @see java.awt.event.MouseEvent
     * @see java.awt.event.MouseListener
     * @see #removeMouseListener
     * @see #getMouseListeners
     *
     * @see java.awt.Component#addMouseListener(java.awt.event.MouseListener)
     */
    public synchronized void addMouseListener(final MouseListener l) {
        if (l == null) {
            return;
        }
        mouseListener = AWTEventMulticaster.add(mouseListener, l);
        scrollTable.addMouseListener(new JXMouseMultiListener(l, KTable.TYPE_SCROLL));
        lockedTable.addMouseListener(new JXMouseMultiListener(l, KTable.TYPE_LOCKED));
    }

    /**
     * Removes the specified mouse listener so that it no longer
     * receives mouse events from this component. This method performs
     * no function, nor does it throw an exception, if the listener
     * specified by the argument was not previously added to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown and no action is performed.
     *
     * @param    l   the mouse listener
     * @see      java.awt.event.MouseEvent
     * @see      java.awt.event.MouseListener
     * @see      #addMouseListener
     * @see      #getMouseListeners
     * @see java.awt.Component#removeMouseListener(java.awt.event.MouseListener)
     */
    public synchronized void removeMouseListener(final MouseListener l) {
        if (l == null) {
            return;
        }
        mouseListener = AWTEventMulticaster.remove(mouseListener, l);
    }

    /**
     * Returns an array of all the mouse listeners
     * registered on this component.
     *
     * @return all of this component's <code>MouseListener</code>s
     *         or an empty array if no mouse
     *         listeners are currently registered
     *
     * @see      #addMouseListener
     * @see      #removeMouseListener
     * @since    1.4
     * @see java.awt.Component#getMouseListeners()
     */
    public synchronized MouseListener[] getMouseListeners() {
        return (MouseListener[]) getListeners(MouseListener.class);
    }

    /**
     * Adds the specified mouse motion listener to receive mouse motion
     * events from this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown and no action is performed.
     *
     * @param    l   the mouse motion listener
     * @see      java.awt.event.MouseEvent
     * @see      java.awt.event.MouseMotionListener
     * @see      #removeMouseMotionListener
     * @see      #getMouseMotionListeners
     * @see java.awt.Component#addMouseMotionListener(java.awt.event.MouseMotionListener)
     */
    public synchronized void addMouseMotionListener(final MouseMotionListener l) {
        if (l == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticaster.add(mouseMotionListener, l);
        scrollTable.addMouseMotionListener(new JXMouseMultiListener(l, KTable.TYPE_SCROLL));
        lockedTable.addMouseMotionListener(new JXMouseMultiListener(l, KTable.TYPE_LOCKED));
    }

    /**
     * Removes the specified mouse motion listener so that it no longer
     * receives mouse motion events from this component. This method performs
     * no function, nor does it throw an exception, if the listener
     * specified by the argument was not previously added to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown and no action is performed.
     *
     * @param    l   the mouse motion listener
     * @see      java.awt.event.MouseEvent
     * @see      java.awt.event.MouseMotionListener
     * @see      #addMouseMotionListener
     * @see      #getMouseMotionListeners
     * @see java.awt.Component#removeMouseMotionListener(java.awt.event.MouseMotionListener)
     */
    public synchronized void removeMouseMotionListener(final MouseMotionListener l) {
        if (l == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticaster.remove(mouseMotionListener, l);
    }

    /**
     * Returns an array of all the mouse motion listeners
     * registered on this component.
     *
     * @return all of this component's <code>MouseMotionListener</code>s
     *         or an empty array if no mouse motion
     *         listeners are currently registered
     *
     * @see      #addMouseMotionListener
     * @see      #removeMouseMotionListener
     * @see java.awt.Component#getMouseMotionListeners()
     */
    public synchronized MouseMotionListener[] getMouseMotionListeners() {
        return (MouseMotionListener[]) getListeners(MouseMotionListener.class);
    }

    /**
     * Adds the specified mouse wheel listener to receive mouse wheel events
     * from this component.  Containers also receive mouse wheel events from
     * sub-components.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param    l   the mouse wheel listener.
     * @see      java.awt.event.MouseWheelEvent
     * @see      java.awt.event.MouseWheelListener
     * @see      #removeMouseWheelListener
     * @see      #getMouseWheelListeners
     * @see java.awt.Component#addMouseWheelListener(java.awt.event.MouseWheelListener)
     */
    public synchronized void addMouseWheelListener(final MouseWheelListener l) {
        if (l == null) {
            return;
        }
        mouseWheelListener = AWTEventMulticaster.add(mouseWheelListener, l);
        scrollTable.addMouseWheelListener(new JXMouseMultiListener(l, KTable.TYPE_SCROLL));
        lockedTable.addMouseWheelListener(new JXMouseMultiListener(l, KTable.TYPE_LOCKED));
    }

    /**
     * Removes the specified mouse wheel listener so that it no longer
     * receives mouse wheel events from this component. This method performs
     * no function, nor does it throw an exception, if the listener
     * specified by the argument was not previously added to this component.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param    l   the mouse wheel listener.
     * @see      java.awt.event.MouseWheelEvent
     * @see      java.awt.event.MouseWheelListener
     * @see      #addMouseWheelListener
     * @see      #getMouseWheelListeners
     * @see java.awt.Component#removeMouseWheelListener(java.awt.event.MouseWheelListener)
     */
    public synchronized void removeMouseWheelListener(final MouseWheelListener l) {
        if (l == null) {
            return;
        }
        mouseWheelListener = AWTEventMulticaster.remove(mouseWheelListener, l);
    }

    /**
     * Returns an array of all the mouse wheel listeners
     * registered on this component.
     *
     * @return all of this component's <code>MouseWheelListener</code>s
     *         or an empty array if no mouse wheel
     *         listeners are currently registered
     *
     * @see      #addMouseWheelListener
     * @see      #removeMouseWheelListener
     * @see java.awt.Component#getMouseWheelListeners()
     */
    public synchronized MouseWheelListener[] getMouseWheelListeners() {
        return (MouseWheelListener[]) getListeners(MouseWheelListener.class);
    }

    /**
     * Returns an array of all the objects currently registered
     * as <code><em>Foo</em>Listener</code>s
     * upon this <code>Component</code>.
     * <code><em>Foo</em>Listener</code>s are registered using the
     * <code>add<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You can specify the <code>listenerType</code> argument
     * with a class literal, such as
     * <code><em>Foo</em>Listener.class</code>.
     * For example, you can query a
     * <code>Component</code> <code>c</code>
     * for its mouse listeners with the following code:
     *
     * <pre>MouseListener[] mls = (MouseListener[])(c.getListeners(MouseListener.class));</pre>
     *
     * If no such listeners exist, this method returns an empty array.
     *
     * @param listenerType the type of listeners requested; this parameter
     *          should specify an interface that descends from
     *          <code>java.util.EventListener</code>
     * @return an array of all objects registered as
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or an empty array if no such listeners have been added
     *
     * @see #getMouseListeners
     */
    public EventListener[] getListeners(final Class listenerType) {
        final EventListener l;
        if (listenerType == MouseListener.class) {
            l = mouseListener;
        } else if (listenerType == MouseMotionListener.class) {
            l = mouseMotionListener;
        } else if (listenerType == MouseWheelListener.class) {
            l = mouseWheelListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticaster.getListeners(l, listenerType);
    }

    /**
     * @see javax.swing.JComponent#setBackground(java.awt.Color)
     */
    public void setBackground(final Color bg) {
        scrollPane.setBackground(bg);
    }

    /**
     * @see javax.swing.JComponent#setOpaque(boolean)
     */
    public void setOpaque(final boolean isOpaque) {
        scrollPane.setOpaque(isOpaque);
    }

    /**
     * Internal mouse listener delegate for all three mouse listener interfaces.
     */
    private final class JXMouseMultiListener implements MouseListener, MouseMotionListener, MouseWheelListener {

        /** Reference to the enclosing mouse listener. */
        private final transient EventListener parentEventListener;

        /** Type of tabel, can be {@link KTable#TYPE_LOCKED}, {@link KTable#TYPE_SCROLL} or {@link KTable#TYPE_NONE}. */
        private final int tableType;

        /**
         * Constructor.
         * @param l mouse listener of sourrounding JTable
         * @param type table tye ({@link KTable#TYPE_LOCKED} or {@link KTable#TYPE_SCROLL})
         */
        private JXMouseMultiListener(final EventListener l, final int type) {
            super();
            parentEventListener = l;
            tableType = type;
        }

        /**
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        public void mouseClicked(final MouseEvent e) {
            ((MouseListener) parentEventListener).mouseClicked(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        public void mouseEntered(final MouseEvent e) {
            ((MouseListener) parentEventListener).mouseEntered(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        public void mouseExited(final MouseEvent e) {
            ((MouseListener) parentEventListener).mouseExited(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
         */
        public void mousePressed(final MouseEvent e) {
            ((MouseListener) parentEventListener).mousePressed(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
         */
        public void mouseReleased(final MouseEvent e) {
            ((MouseListener) parentEventListener).mouseReleased(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
         */
        public void mouseDragged(final MouseEvent e) {
            ((MouseMotionListener) parentEventListener).mouseDragged(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
         */
        public void mouseMoved(final MouseEvent e) {
            ((MouseMotionListener) parentEventListener).mouseMoved(translateMouseEvent(e));
        }

        /**
         * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
         */
        public void mouseWheelMoved(final MouseWheelEvent e) {
            ((MouseWheelListener) parentEventListener).mouseWheelMoved(translateMouseWheelEvent(e));
        }

        /**
         * Translates the coordinates of a KTable's mouse event into
         * coordinated of the surrounding JTable.
         *
         * @param e mouse event of KTable
         * @return mouse event for JTable
         */
        private MouseEvent translateMouseEvent(final MouseEvent e) {
            final int x;
            if (tableType == KTable.TYPE_LOCKED) {
                x = e.getX();
            } else {
                x = e.getX() + lockedTable.getWidth();
            }
            return new MouseEvent(
                    e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                    e.getClickCount(), e.isPopupTrigger(), e.getButton());
        }

        /**
         * Translates the coordinates of a KTable's mouse wheel event into
         * coordinated of the surrounding JTable.
         *
         * @param e mouse event of KTable
         * @return mouse event for JTable
         */
        private MouseWheelEvent translateMouseWheelEvent(final MouseWheelEvent e) {
            final int x;
            if (tableType == KTable.TYPE_LOCKED) {
                x = e.getX();
            } else {
                x = e.getX() + lockedTable.getWidth();
            }
            return new MouseWheelEvent(
                    e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                    e.getClickCount(), e.isPopupTrigger(), e.getScrollType(), e.getScrollAmount(), e.getWheelRotation());
        }
    }

    /**
     * @see javax.swing.JScrollPane#setUI(javax.swing.plaf.ScrollPaneUI)
     */
    public void setScrollPaneUI(final ScrollPaneUI scrollpaneui) {
        scrollPane.setUI(scrollpaneui);
    }
}
