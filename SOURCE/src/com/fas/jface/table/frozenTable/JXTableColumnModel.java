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

import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;



/**
 * Column model of all locked and scrollable colums.
 */
public class JXTableColumnModel implements TableColumnModel {

    /** the JXTable where this column model is currently used. */
    private final JXTable table;

    /** Column model of locked table. */
    private TableColumnModel lockedColumnModel;

    /** Column model of scrollable table. */
    private TableColumnModel scrollColumnModel;

    /**
     * Constructor.
     *
     * @param theTable the JXTable that uses this column model
     * @param theLockedColumnModel column model of locked table
     * @param theScrollColumnModel column model of scrollable table
     */
    public JXTableColumnModel(final JXTable theTable,
                              final TableColumnModel theLockedColumnModel,
                              final TableColumnModel theScrollColumnModel) {
        lockedColumnModel = theLockedColumnModel;
        this.table = theTable;
        scrollColumnModel = theScrollColumnModel;
    }

    /**
     * Convinience method to access the locked table from the JXTable.
     *
     * @return the locked table
     */
//    private JTable getLockedTable() {
//        return this.table.getLockedTable();
//    }

    /**
     * Convinience method to access the scrollable table from the JXTable.
     *
     * @return the scrollable table
     */
//    private JTable getScrollTable() {
//        return this.table.getScrollTable();
//    }

    /**
     * Convinience method to access the number of frozen columns.
     *
     * @return number of frozen columns
     */
    private int getFrozenColumns() {
        return this.table.getFrozenColumns();
    }

    /**
     * Returns the number of columns in the model.
     * @return the number of columns in the model
     */
    public int getColumnCount() {
        return lockedColumnModel.getColumnCount() + scrollColumnModel.getColumnCount() - 1;
    }

    /**
     * Returns the width between the cells in each column.
     * @return the margin, in pixels, between the cells
     */
    public int getColumnMargin() {
        return lockedColumnModel.getColumnMargin();
    }

    /**
     * Returns the number of selected columns.
     *
     * @return the number of selected columns; or 0 if no columns are selected
     */
    public int getSelectedColumnCount() {
        return lockedColumnModel.getSelectedColumnCount() + scrollColumnModel.getSelectedColumnCount();
    }

    /**
     * Returns the total width of all the columns.
     * @return the total computed width of all columns
     */
    public int getTotalColumnWidth() {
        return lockedColumnModel.getTotalColumnWidth() + scrollColumnModel.getTotalColumnWidth();
    }

    /**
     * Returns true if columns may be selected.
     * @return true if columns may be selected
     * @see #setColumnSelectionAllowed
     */
    public boolean getColumnSelectionAllowed() {
        return lockedColumnModel.getColumnSelectionAllowed();
    }

    /**
     * Returns the index of the column that lies on the
     * horizontal point, <code>xPosition</code>;
     * or -1 if it lies outside the any of the column's bounds.
     *
     * In keeping with Swing's separable model architecture, a
     * TableColumnModel does not know how the table columns actually appear on
     * screen.  The visual presentation of the columns is the responsibility
     * of the view/controller object using this model (typically JTable).  The
     * view/controller need not display the columns sequentially from left to
     * right.  For example, columns could be displayed from right to left to
     * accomodate a locale preference or some columns might be hidden at the
     * request of the user.  Because the model does not know how the columns
     * are laid out on screen, the given <code>xPosition</code> should not be
     * considered to be a coordinate in 2D graphics space.  Instead, it should
     * be considered to be a width from the start of the first column in the
     * model.  If the column index for a given X coordinate in 2D space is
     * required, <code>JTable.columnAtPoint</code> can be used instead.
     *
     * @return  the index of the column; or -1 if no column is found
     * @see javax.swing.JTable#columnAtPoint
     */
    public int[] getSelectedColumns() {
        final int[] lockedSelectedColumns = lockedColumnModel.getSelectedColumns();
        final int[] scrollSelectedColumns = scrollColumnModel.getSelectedColumns();
        final int[] selectedColumns = new int[lockedSelectedColumns.length + scrollSelectedColumns.length];
        for (int i = 0; i < lockedSelectedColumns.length; i++) {
            selectedColumns[i] = lockedSelectedColumns[i];
        }
        for (int i = 0; i < scrollSelectedColumns.length; i++) {
            selectedColumns[lockedSelectedColumns.length + i] = scrollSelectedColumns[i];
        }
        return selectedColumns;
    }

    /**
     * @see javax.swing.table.TableColumnModel#getColumnIndexAtX(int)
     */
    public int getColumnIndexAtX(final int xPosition) {
        if (xPosition <= lockedColumnModel.getTotalColumnWidth()) {
            return lockedColumnModel.getColumnIndexAtX(xPosition);
        } else {
            return getFrozenColumns()
                    + scrollColumnModel.getColumnIndexAtX(xPosition - lockedColumnModel.getTotalColumnWidth());
        }
    }

    /**
     * Sets the <code>TableColumn</code>'s column margin to
     * <code>newMargin</code>.  This method posts
     * a <code>columnMarginChanged</code> event to its listeners.
     *
     * @param   newMargin       the width, in pixels, of the new column margins
     * @see     #getColumnMargin
     */
    public void setColumnMargin(final int newMargin) {
        lockedColumnModel.setColumnMargin(newMargin);
        scrollColumnModel.setColumnMargin(newMargin);
    }

    /**
     * Moves the column and its header at <code>columnIndex</code> to
     * <code>newIndex</code>.  The old column at <code>columnIndex</code>
     * will now be found at <code>newIndex</code>.  The column that used
     * to be at <code>newIndex</code> is shifted left or right
     * to make room.  This will not move any columns if
     * <code>columnIndex</code> equals <code>newIndex</code>. This method
     * posts a <code>columnMoved</code> event to its listeners.
     *
     * @param   columnIndex                     the index of column to be moved
     * @param   newIndex                        index of the column's new location
     * @exception IllegalArgumentException      if <code>columnIndex</code> or
     *                                          <code>newIndex</code>
     *                                          are not in the valid range
     */
    public void moveColumn(final int columnIndex, final int newIndex) throws IllegalArgumentException {
        if (columnIndex < getFrozenColumns() && newIndex < getFrozenColumns()) {
            // move within locked table
            lockedColumnModel.moveColumn(columnIndex, newIndex);
        } else if (columnIndex < getFrozenColumns() && newIndex >= getFrozenColumns()) {
            // move from locked to scrollable
            final TableColumn tableColumn = lockedColumnModel.getColumn(columnIndex);
            lockedColumnModel.removeColumn(tableColumn);
            scrollColumnModel.addColumn(tableColumn);
            scrollColumnModel.moveColumn(scrollColumnModel.getColumnCount(), newIndex - getFrozenColumns());
        } else if (columnIndex >= getFrozenColumns() && newIndex < getFrozenColumns()) {
            // move from scrollable to locked
            final TableColumn tableColumn = scrollColumnModel.getColumn(columnIndex);
            scrollColumnModel.removeColumn(tableColumn);
            lockedColumnModel.addColumn(tableColumn);
            lockedColumnModel.moveColumn(lockedColumnModel.getColumnCount(), newIndex);
        } else {
            // move within scrollable
            scrollColumnModel.moveColumn(columnIndex, newIndex);
        }
    }

    /**
     * Sets whether the columns in this model may be selected.
     * @param flag   true if columns may be selected; otherwise false
     * @see #getColumnSelectionAllowed
     */
    public void setColumnSelectionAllowed(final boolean flag) {
        lockedColumnModel.setColumnSelectionAllowed(flag);
        scrollColumnModel.setColumnSelectionAllowed(flag);
    }

    /**
     * Returns the index of the first column in the table
     * whose identifier is equal to <code>identifier</code>,
     * when compared using <code>equals</code>.
     *
     * @param           columnIdentifier        the identifier object
     * @return          the index of the first table column
     *                  whose identifier is equal to <code>identifier</code>
     * @exception IllegalArgumentException      if <code>identifier</code>
     *              is <code>null</code>, or no
     *              <code>TableColumn</code> has this
     *              <code>identifier</code>
     * @see             #getColumn
     */
    public int getColumnIndex(final Object columnIdentifier) throws IllegalArgumentException {
        try {
            return lockedColumnModel.getColumnIndex(columnIdentifier);
        } catch (IllegalArgumentException e) {
            return getFrozenColumns() + scrollColumnModel.getColumnIndex(columnIdentifier);
        }
    }

    /**
     * Returns an <code>Enumeration</code> of all the columns in the model.
     * @return an <code>Enumeration</code> of all the columns in the model
     */
    public Enumeration getColumns() {
        return new JXColumnsEnumeration();
    }

    /**
     * Returns the current selection model.
     *
     * @return a <code>ListSelectionModel</code> object
     * @see #setSelectionModel
     */
    public ListSelectionModel getSelectionModel() {
        return lockedColumnModel.getSelectionModel();
    }

    /**
     * Sets the selection model.
     *
     * @param newModel  a <code>ListSelectionModel</code> object
     * @see #getSelectionModel
     */
    public void setSelectionModel(final ListSelectionModel newModel) {
        lockedColumnModel.setSelectionModel(newModel);
        scrollColumnModel.setSelectionModel(newModel);
    }

    /**
     * Adds a listener for table column model events.
     *
     * @param x  a <code>TableColumnModelListener</code> object
     */
    public void addColumnModelListener(final TableColumnModelListener x) {
        lockedColumnModel.addColumnModelListener(x);
        scrollColumnModel.addColumnModelListener(x);
        // TODO Check if the listener must get wrapped to convert column indices
    }

    /**
     * Removes a listener for table column model events.
     *
     * @param x  a <code>TableColumnModelListener</code> object
     */
    public void removeColumnModelListener(final TableColumnModelListener x) {
        lockedColumnModel.removeColumnModelListener(x);
        scrollColumnModel.removeColumnModelListener(x);
    }

    /**
     * Returns the <code>TableColumn</code> object for the column at
     * <code>columnIndex</code>.
     *
     * @param   columnIndex     the index of the desired column
     * @return  the <code>TableColumn</code> object for
     *              the column at <code>columnIndex</code>
     */
    public TableColumn getColumn(final int columnIndex) {
        if (columnIndex < getFrozenColumns()) {
            return lockedColumnModel.getColumn(columnIndex);
        } else {
            return scrollColumnModel.getColumn(columnIndex - getFrozenColumns());
        }
    }

    /**
     *  Appends <code>aColumn</code> to the end of the
     *  <code>tableColumns</code> array.
     *  This method posts a <code>columnAdded</code>
     *  event to its listeners.
     *
     * @param   aColumn         the <code>TableColumn</code> to be added
     * @see     #removeColumn
     */
    public void addColumn(final TableColumn aColumn) {
        scrollColumnModel.addColumn(aColumn);
// TODO        lockedColumnModel.setPreferredScrollableViewportSize(getLockedTable().getPreferredSize());
    }

    /**
     *  Deletes the <code>TableColumn</code> <code>column</code> from the
     *  <code>tableColumns</code> array.  This method will do nothing if
     *  <code>column</code> is not in the table's column list.
     *  This method posts a <code>columnRemoved</code>
     *  event to its listeners.
     *
     * @param   column          the <code>TableColumn</code> to be removed
     * @see     #addColumn
     */
    public void removeColumn(final TableColumn column) {
        lockedColumnModel.removeColumn(column);
        scrollColumnModel.removeColumn(column);
//      TODO        lockedColumnModel.setPreferredScrollableViewportSize(getLockedTable().getPreferredSize());
    }

    /**
     * Enumeration over all locked and scrollable colums.
     */
    private final class JXColumnsEnumeration implements Enumeration {

        /** Enumeration over locked columns. */
        private Enumeration lockedEnumeration = lockedColumnModel.getColumns();

        /** Enumeration over scrollable columns. */
        private Enumeration scrollEnumeration = scrollColumnModel.getColumns();

        /** Current Enumeration. */
        private Enumeration currentEnumeration = lockedEnumeration;

        /**
         * @see java.util.Enumeration#hasMoreElements()
         */
        public boolean hasMoreElements() {
            final boolean hasMore = currentEnumeration.hasMoreElements();
            if (hasMore) {
                return hasMore;
            } else {
                currentEnumeration = scrollEnumeration;
                return currentEnumeration.hasMoreElements();
            }
        }

        /**
         * @see java.util.Enumeration#nextElement()
         */
        public Object nextElement() {
            try {
                return currentEnumeration.nextElement();
            } catch (NoSuchElementException e) {
                currentEnumeration = scrollEnumeration;
                return currentEnumeration.nextElement();
            }
        }
    }
}