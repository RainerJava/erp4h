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
package org.jdesktop.xframe.table;

import java.awt.AWTEventMulticaster;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Enumeration;
import java.util.EventListener;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdesktop.xframe.JXTable;

/**
 * Wrapper for the two headers of locked and scrollable table.
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public class JXTableHeader extends JComponent implements TableColumnModelListener, Accessible {

    /**
     * The table for which this object is the header;
     * the default is <code>null</code>.
     */
    private JXTable table;

    /**  The <code>TableColumnModel</code> of the table header. */
    private TableColumnModel columnModel;

    /** The header of the locked table. */
    private final JTableHeader lockedHeader;

    /** The header of the scrollable table. */
    private final JTableHeader scrollHeader;

    /** Reference to the mouse listener. */
    private transient MouseListener mouseListener;

    /** Reference to the mouse motion listener. */
    private transient MouseMotionListener mouseMotionListener;

    /** Reference to the mouse wheel listener. */
    private transient MouseWheelListener mouseWheelListener;

    /**
     *  Constructs a <code>JXTableHeader</code> which is initialized with
     *  <code>cm</code> as the column model.  If <code>cm</code> is
     *  <code>null</code> this method will initialize the table header
     *  with a default <code>TableColumnModel</code>.
     *
     * @param cm the column model for the table
     */
    public JXTableHeader(final TableColumnModel cm) {
        this(cm, new ColumnGroupHeader(null), new ColumnGroupHeader(null));
    }

    /**
     *  Constructs a <code>JXTableHeader</code> which is initialized with
     *  <code>cm</code> as the column model.  If <code>cm</code> is
     *  <code>null</code> this method will initialize the table header
     *  with a default <code>TableColumnModel</code>.
     *
     * @param cm the column model for the table
     * @param theLockedHeader the header of the locked table
     * @param theScrollHeader the header of the scrollable table
     */
    public JXTableHeader(final TableColumnModel cm, final JTableHeader theLockedHeader, final JTableHeader theScrollHeader) {
        columnModel = cm;
        lockedHeader = theLockedHeader;
        scrollHeader = theScrollHeader;
    }

    /**
     * Returns the default column model object which is
     * a <code>DefaultTableColumnModel</code>.  A subclass can override this
     * method to return a different column model object
     *
     * @return the default column model object
     */
    protected TableColumnModel createDefaultColumnModel() {
        return new DefaultTableColumnModel();
    }

    /**
     * adds a column group to the header.
     *
     * @param columnGroup the new column group
     * @throws IllegalArgumentException if the columns of the column group
     *                                  are not all in the locked header or
     *                                  or not all in the scrollable header
     */
    public void addColumnGroup(final ColumnGroup columnGroup) throws IllegalArgumentException {
        final JTableHeader header = getHeader(columnGroup);
        if (header == null) {
            throw new IllegalArgumentException("Invalid header: interleaving frozen and non-frozen columns.");
        } else if (header instanceof ColumnGroupHeader) {
            ((ColumnGroupHeader) header).addColumnGroup(columnGroup);
        } else {
            throw new IllegalArgumentException("Invalid header: must be instance of ColumnGroupHeader.");
        }
    }

    /**
     * Chooses one of the headers (locked or scrollable) in which the
     * column group belongs to.
     *
     * @param columnGroup the column group of interest
     * @return the locked header, if all columns of the column group are
     *         within the frozen columns or the scrollable header, if all
     *         columns of the column group are within the scrollable columns
     *         or <code>null</code> if the column group intersects the
     *         frozen and scrollable columns.
     */
    private JTableHeader getHeader(final ColumnGroup columnGroup) {
        int fromIndex = columnModel.getColumnCount();
        int toIndex = 0;
        final JTableHeader noSubHeader = new JTableHeader();
        JTableHeader header = noSubHeader;
        Enumeration enumeration = columnGroup.getColumns();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof TableColumn) {
                final TableColumn column = (TableColumn) obj;
                int index = columnModel.getColumnIndex(column.getIdentifier());
                if (index < fromIndex) {
                    fromIndex = index;
                }
                if (index > toIndex) {
                    toIndex = index;
                }
            } else {
                JTableHeader thisHeader = getHeader((ColumnGroup) obj); // recurse in to nested column groups
                if (thisHeader == null) {
                    return null; // header of a sub-group intersects: stop recursion
                } else if (header == scrollHeader && thisHeader == lockedHeader) {
                    return null; // header of one sub-group intersects header of a previous sub-group
                } else if (header == lockedHeader && thisHeader == scrollHeader) {
                    return null; // header of one sub-group intersects header of a previous sub-group
                } else {
                    header = thisHeader; // remember header of current sub-group
                }
            }
        }
        final int frozenColumns = table != null ? table.getFrozenColumns() : 0;
        final JTableHeader columnsHeader;
        if (toIndex < frozenColumns) {
            columnsHeader = lockedHeader; // all directly grouped columns are locked
        } else if (fromIndex >= frozenColumns) {
            columnsHeader = scrollHeader; // all directly grouped columns are scrollable
        } else {
            return null; // directly grouped columns intersect
        }
        if (header != noSubHeader && header != columnsHeader) {
            return null; // header of directly grouped columns intersect with header of a nested column group
        }
        return columnsHeader; // no intersection, return header
    }

    /**
     * Sets the font of the table.
     *
     * @param font the new font
     */
    public void setFont(final Font font) {
        lockedHeader.setFont(font);
        scrollHeader.setFont(font);
    }

    /**
     * Sets whether the user can resize columns by dragging between headers.
     *
     * @param resizingAllowed true if table view should allow resizing
     * @see #getResizingAllowed
     */
    public void setResizingAllowed(final boolean resizingAllowed) {
        lockedHeader.setResizingAllowed(resizingAllowed);
        scrollHeader.setResizingAllowed(resizingAllowed);
    }

    /**
     * Returns true if the user is allowed to resize columns by dragging
     * between their headers, false otherwise. The default is true. You can
     * resize columns programmatically regardless of this setting.
     *
     * @return <code>true</code> if resizing is allowed, else <code>false</code>
     * @see #setResizingAllowed
     */
    public boolean getResizingAllowed() {
        return lockedHeader.getResizingAllowed();
    }

    /**
     * Makes the component visible or invisible.
     * Overrides <code>Component.setVisible</code>.
     *
     * @param isVisible true to make the component visible; false to make it
     *                  invisible
     */
    public void setVisible(final boolean isVisible) {
        lockedHeader.setVisible(isVisible);
        scrollHeader.setVisible(isVisible);
    }

    /**
     * Adds the specified mouse listener to receive mouse events from
     * this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown and no action is performed.
     *
     * @param    l   the mouse listener
     * @see      #removeMouseListener
     * @see      #getMouseListeners
     */
    public void addMouseListener(final MouseListener l) {
        lockedHeader.addMouseListener(l);
        scrollHeader.addMouseListener(l);
    }

    /**
     * Returns the lockedHeader.
     * @return the lockedHeader.
     */
    public JTableHeader getLockedHeader() {
        return lockedHeader;
    }
    /**
     * Returns the scrollHeader.
     * @return the scrollHeader.
     */
    public JTableHeader getScrollHeader() {
        return scrollHeader;
    }

    /**
     * Associates a table header with a table.
     * @param theTable the associated table
     */
    public void setTable(final JXTable theTable) {
        table = theTable;
    }

    /**
     * Returns the table associated with this header.
     * @return  the <code>table</code> property
     */
    public JXTable getTable() {
        return table;
    }

    /**
     * Sets whether the user can drag column headers to reorder columns.
     *
     * @param   reorderingAllowed   true if the table view should allow
     *                  reordering; otherwise false
     * @see #getReorderingAllowed
     */
    public void setReorderingAllowed(final boolean reorderingAllowed) {
        lockedHeader.setReorderingAllowed(reorderingAllowed);
        scrollHeader.setReorderingAllowed(reorderingAllowed);
    }

    /**
     * Returns true if the user is allowed to rearrange columns by
     * dragging their headers, false otherwise. The default is true. You can
     * rearrange columns programmatically regardless of this setting.
     *
     * @return  the <code>reorderingAllowed</code> property
     * @see #setReorderingAllowed
     */
    public boolean getReorderingAllowed() {
        return lockedHeader.getReorderingAllowed();
    }

    /**
     * Returns the the dragged column, if and only if, a drag is in
     * process, otherwise returns <code>null</code>.
     *
     * @return  the dragged column, if a drag is in
     *      process, otherwise returns <code>null</code>
     * @see #getDraggedDistance
     */
    public TableColumn getDraggedColumn() {
        return lockedHeader.getDraggedColumn();
    }

    /**
     * Returns the column's horizontal distance from its original
     * position, if and only if, a drag is in process. Otherwise, the
     * the return value is meaningless.
     *
     * @return  the column's horizontal distance from its original
     *      position, if a drag is in process, otherwise the return
     *      value is meaningless
     * @see #getDraggedColumn
     */
    public int getDraggedDistance() {
        int distance = lockedHeader.getDraggedDistance();
        if (distance != 0)  {
            return distance;
        }
        return scrollHeader.getDraggedDistance();
    }

    /**
     * Returns the resizing column.  If no column is being
     * resized this method returns <code>null</code>.
     *
     * @return  the resizing column, if a resize is in process, otherwise
     *      returns <code>null</code>
     */
    public TableColumn getResizingColumn() {
        TableColumn column = lockedHeader.getResizingColumn();
        if (column != null) {
            return column;
        }
        return scrollHeader.getResizingColumn();
    }

    /**
     * Obsolete as of Java 2 platform v1.3.  Real time repaints, in response to
     * column dragging or resizing, are now unconditional.
     * @param flag flag
     */
    public void setUpdateTableInRealTime(final boolean flag) {
        lockedHeader.setUpdateTableInRealTime(flag);
        scrollHeader.setUpdateTableInRealTime(flag);
    }

    /**
     * Obsolete as of Java 2 platform v1.3.  Real time repaints, in response to
     * column dragging or resizing, are now unconditional.
     * @return flag
     */
    public boolean getUpdateTableInRealTime() {
        return lockedHeader.getUpdateTableInRealTime();
    }

    /**
     * Sets the default renderer to be used when no <code>headerRenderer</code>
     * is defined by a <code>TableColumn</code>.
     * @param  defaultRenderer  the default renderer
     */
    public void setDefaultRenderer(final TableCellRenderer defaultRenderer) {
        lockedHeader.setDefaultRenderer(defaultRenderer);
        scrollHeader.setDefaultRenderer(defaultRenderer);
    }

    /**
     * Returns the default renderer used when no <code>headerRenderer</code>
     * is defined by a <code>TableColumn</code>.
     * @return the default renderer
     */
    public TableCellRenderer getDefaultRenderer() {
        return lockedHeader.getDefaultRenderer();
    }

    /**
     * Returns the index of the column that <code>point</code> lies in, or -1 if it
     * lies out of bounds.
     *
     * @param   point   the location of interest
     * @return  the index of the column that <code>point</code> lies in, or -1 if it
     *      lies out of bounds
     */
    public int columnAtPoint(final Point point) {
        if (point.y < lockedHeader.getWidth()) {
            return lockedHeader.columnAtPoint(point);
        } else {
            Point p = new Point(point.x, point.y - lockedHeader.getWidth());
            return scrollHeader.columnAtPoint(p);
        }
    }

    /**
     * Returns the rectangle containing the header tile at <code>column</code>.
     * When the <code>column</code> parameter is out of bounds this method uses the
     * same conventions as the <code>JTable</code> method <code>getCellRect</code>.
     *
     * @param column the column of interest
     * @return  the rectangle containing the header tile at <code>column</code>
     * @see JXTable#getCellRect
     */
    public Rectangle getHeaderRect(final int column) {
        final int frozenColumns = table.getFrozenColumns();
        if (column < table.getFrozenColumns()) {
            return lockedHeader.getHeaderRect(column);
        } else {
            return scrollHeader.getHeaderRect((column - frozenColumns));
        }
    }

    /**
     * Returns the look and feel (L&F) object that renders this component.
     * @return the look and feel (L&F) object
     * @see javax.swing.table.JTableHeader#getUI()
     */
    public TableHeaderUI getUI() {
        return lockedHeader.getUI();
    }

    /**
     * Sets the look and feel (L&F) object that renders this component.
     *
     * @param ui the <code>TableHeaderUI</code> L&F object
     * @see javax.swing.table.JTableHeader#setUI(javax.swing.plaf.TableHeaderUI)
     */
    public void setUI(final TableHeaderUI ui) {
        lockedHeader.setUI(ui);
        scrollHeader.setUI(ui);
    }

    /**
     * Notification from the <code>UIManager</code> that the look and feel
     * (L&F) has changed.
     * Replaces the current UI object with the latest version from the
     * <code>UIManager</code>.
     *
     * @see JComponent#updateUI
     */
    public void updateUI() {
        lockedHeader.updateUI();
        scrollHeader.updateUI();
    }

    /**
     * Returns the suffix used to construct the name of the look and feel
     * (L&F) class used to render this component.
     * @return the string "TableHeaderUI"
     *
     * @see javax.swing.JComponent#getUIClassID
     * @see javax.swing.UIDefaults#getUI
     */
    public String getUIClassID() {
        return lockedHeader.getUIClassID();
    }

    /**
     * Sets the column model for this table to <code>newModel</code> and registers
     * for listener notifications from the new column model.
     *
     * @param cm the new data source for this table
     * @exception IllegalArgumentException
     *              if <code>newModel</code> is <code>null</code>
     * @see #getColumnModel
     */
    public void setColumnModel(final TableColumnModel cm) throws IllegalArgumentException {
        if (cm == null) {
            throw new IllegalArgumentException("Cannot set a null ColumnModel");
        }
        final TableColumnModel old = columnModel;
        if (cm != old) {
            if (old != null) {
                old.removeColumnModelListener(this);
            }
            columnModel = cm;
            final TableColumnModel cm1 = createDefaultColumnModel();
            final TableColumnModel cm2 = createDefaultColumnModel();
            for (int i = 0; i < cm.getColumnCount(); i++) {
                cm1.addColumn(cm.getColumn(i));
                cm2.addColumn(cm.getColumn(i));
            }
            final int frozenColumns = table.getFrozenColumns();
            // Remove the locked columns from the scrollable table
            for (int i = 0; i < frozenColumns; i++) {
                cm2.removeColumn(cm2.getColumn(0));
            }
            // Remove the scrollable columns from the locked table
            while (cm1.getColumnCount() > frozenColumns) {
                cm1.removeColumn(cm1.getColumn(frozenColumns));
            }
            columnModel.addColumnModelListener(this);
            lockedHeader.setColumnModel(cm1);
            scrollHeader.setColumnModel(cm2);
        }
    }

    /**
     * Returns the <code>TableColumnModel</code> that contains all column information
     * of this table header.
     *
     * @return  the <code>columnModel</code> property
     * @see #setColumnModel
     */
    public TableColumnModel getColumnModel() {
        return columnModel;
    }

    /**
     * Invoked when a column is added to the table column model.
     * <p>
     * Application code will not use these methods explicitly, they
     * are used internally by <code>JTable</code>.
     *
     * @param e  the event received
     * @see TableColumnModelListener
     */
    public void columnAdded(final TableColumnModelEvent e) {
        final int frozenColumns = table.getFrozenColumns();
        if (e.getToIndex() < frozenColumns)  {
            lockedHeader.columnAdded(e);
        } else if (e.getFromIndex() >= frozenColumns) {
            TableColumnModelEvent event = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                frozenColumns, e.getToIndex());
            scrollHeader.columnAdded(event);
        } else {
            final TableColumnModelEvent e1 = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                    e.getFromIndex(), frozenColumns - 1);
            final TableColumnModelEvent e2 = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                    frozenColumns, e.getToIndex());
            lockedHeader.columnAdded(e1);
            scrollHeader.columnAdded(e2);
        }
    }

    /**
     * Invoked when a column is removed from the table column model.
     * <p>
     * Application code will not use these methods explicitly, they
     * are used internally by <code>JTable</code>.
     *
     * @param e  the event received
     * @see TableColumnModelListener
     */
    public void columnRemoved(final TableColumnModelEvent e) {
        final int frozenColumns = table.getFrozenColumns();
        if (e.getToIndex() < frozenColumns)  {
            lockedHeader.columnRemoved(e);
        } else if (e.getFromIndex() >= frozenColumns) {
            TableColumnModelEvent event = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                frozenColumns, e.getToIndex());
            scrollHeader.columnRemoved(event);
        } else {
            final TableColumnModelEvent e1 = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                    e.getFromIndex(), frozenColumns - 1);
            final TableColumnModelEvent e2 = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                    frozenColumns, e.getToIndex());
            lockedHeader.columnRemoved(e1);
            scrollHeader.columnRemoved(e2);
        }
    }

    /**
     * Invoked when a column is repositioned.
     * <p>
     * Application code will not use these methods explicitly, they
     * are used internally by <code>JTable</code>.
     *
     * @param e the event received
     * @see TableColumnModelListener
     */
    public void columnMoved(final TableColumnModelEvent e) {
        final int frozenColumns = table.getFrozenColumns();
        if (e.getToIndex() < frozenColumns)  {
            lockedHeader.columnMoved(e);
        } else if (e.getFromIndex() >= frozenColumns) {
            TableColumnModelEvent event = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                frozenColumns, e.getToIndex());
            scrollHeader.columnMoved(event);
        } else {
            final TableColumnModelEvent e1 = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                    e.getFromIndex(), frozenColumns - 1);
            final TableColumnModelEvent e2 = new TableColumnModelEvent((TableColumnModel) e.getSource(),
                    frozenColumns, e.getToIndex());
            lockedHeader.columnMoved(e1);
            scrollHeader.columnMoved(e2);
        }
    }

    /**
     * Invoked when a column is moved due to a margin change.
     * <p>
     * Application code will not use these methods explicitly, they
     * are used internally by <code>JTable</code>.
     *
     * @param e the event received
     * @see TableColumnModelListener
     */
    public void columnMarginChanged(final ChangeEvent e) {
        lockedHeader.columnMarginChanged(e);
        scrollHeader.columnMarginChanged(e);
    }

    /**
     * Invoked when the selection model of the <code>TableColumnModel</code>
     * is changed.  This method currently has no effect (the header is not
     * redrawn).
     * <p>
     * Application code will not use these methods explicitly, they
     * are used internally by <code>JTable</code>.
     *
     * @param e the event received
     * @see TableColumnModelListener
     */
    public void columnSelectionChanged(final ListSelectionEvent e) {
        lockedHeader.columnSelectionChanged(e);
        scrollHeader.columnSelectionChanged(e);
    }

    /**
     * Sizes the header and marks it as needing display.  Equivalent
     * to <code>revalidate</code> followed by <code>repaint</code>.
     */
    public void resizeAndRepaint() {
        lockedHeader.resizeAndRepaint();
        scrollHeader.resizeAndRepaint();
    }

    /**
     *  Sets the header's <code>draggedColumn</code> to <code>aColumn</code>.
     *  <p>
     *  Application code will not use this method explicitly, it is used
     *  internally by the column dragging mechanism.
     *
     *  @param  aColumn  the column being dragged, or <code>null</code> if
     *          no column is being dragged
     */
    public void setDraggedColumn(final TableColumn aColumn) {
        final int frozenColumns = table.getFrozenColumns();
        if (columnModel.getColumnIndex(aColumn) < frozenColumns) {
            lockedHeader.setDraggedColumn(aColumn);
        } else {
            scrollHeader.setDraggedColumn(aColumn);
        }
    }

    /**
     * Sets the header's <code>draggedDistance</code> to <code>distance</code>.
     * @param distance  the distance dragged
     */
    public void setDraggedDistance(final int distance) {
        lockedHeader.setDraggedDistance(distance);
        scrollHeader.setDraggedDistance(distance);
    }

    /**
     *  Sets the header's <code>resizingColumn</code> to <code>aColumn</code>.
     *  <p>
     *  Application code will not use this method explicitly, it
     *  is used internally by the column sizing mechanism.
     *
     *  @param  aColumn  the column being resized, or <code>null</code> if
     *          no column is being resized
     */
    public void setResizingColumn(final TableColumn aColumn) {
        final int frozenColumns = table.getFrozenColumns();
        if (columnModel.getColumnIndex(aColumn) < frozenColumns) {
            lockedHeader.setResizingColumn(aColumn);
        } else {
            scrollHeader.setResizingColumn(aColumn);
        }
    }

    /**
     * Gets the AccessibleContext associated with this JTableHeader.
     * For JTableHeaders, the AccessibleContext takes the form of an
     * AccessibleJTableHeader.
     * A new AccessibleJTableHeader instance is created if necessary.
     *
     * @return an AccessibleJTableHeader that serves as the
     *         AccessibleContext of this JTableHeader
     */
    public AccessibleContext getAccessibleContext() {
        return lockedHeader.getAccessibleContext();
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
        scrollHeader.addMouseMotionListener(new JXMouseMultiListener(l, KTable.TYPE_SCROLL));
        lockedHeader.addMouseMotionListener(new JXMouseMultiListener(l, KTable.TYPE_LOCKED));
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
        scrollHeader.addMouseWheelListener(new JXMouseMultiListener(l, KTable.TYPE_SCROLL));
        lockedHeader.addMouseWheelListener(new JXMouseMultiListener(l, KTable.TYPE_LOCKED));
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
                x = e.getX() + lockedHeader.getWidth();
            }
            return new MouseEvent(
                    table.getTableHeader(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
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
                x = e.getX() + lockedHeader.getWidth();
            }
            return new MouseWheelEvent(
                    e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                    e.getClickCount(), e.isPopupTrigger(), e.getScrollType(), e.getScrollAmount(), e.getWheelRotation());
        }
    }

    /**
     * Registers the text to display in a tool tip.
     * The text displays when the cursor lingers over the component.
     * <p>
     * See <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/tooltip.html">How to Use Tool Tips</a>
     * in <em>The Java Tutorial</em>
     * for further documentation.
     *
     * @param text  the string to display; if the text is <code>null</code>,
     *              the tool tip is turned off for this component
     * @see javax.swing.JComponent#TOOL_TIP_TEXT_KEY
     */
    public void setToolTipText(final String text) {
        lockedHeader.setToolTipText(text);
        scrollHeader.setToolTipText(text);
    }

    /**
     * Returns the tooltip string that has been set with
     * <code>setToolTipText</code>.
     *
     * @return the text of the tool tip
     * @see javax.swing.JComponent#TOOL_TIP_TEXT_KEY
     */
    public String getToolTipText() {
        return lockedHeader.getToolTipText();
    }

    /**
     * Allows the renderer's tips to be used if there is text set.
     * @param  e  the location of the event identifies the proper
     *              renderer and, therefore, the proper tip
     * @return the tool tip for this component
     */
    //!QuangPV
//    public String getToolTipText(final MouseEvent e) {
//        if (e.getY() < lockedHeader.getWidth()) {
//            return lockedHeader.getToolTipText(e);
//        } else {
//            MouseEvent event = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(),
//                e.getX(), e.getY(), e.getClickCount(), e.isPopupTrigger(), e.getButton());
//            return scrollHeader.getToolTipText(event);
//        }
//    }

    /**
     * Returns the tooltip location in this component's coordinate system.
     * If <code>null</code> is returned, Swing will choose a location.
     * The default implementation returns <code>null</code>.
     *
     * @param event  the <code>MouseEvent</code> that caused the
     *      <code>ToolTipManager</code> to show the tooltip
     * @return always returns <code>null</code>
     */
    public Point getToolTipLocation(final MouseEvent event) {
        return null;
    }
}