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

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.xframe.JXTable;

/**
 *
 * Table header for headers with column groups.
 *
 * @see net.sf.xframe.swing.JXTable
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public class ColumnGroupHeader extends JTableHeader {

    /** List of columns and column groups. */
    private Vector columns;

    /**
     * Constructor.
     *
     * @param tableColumnModel the column model
     */
    public ColumnGroupHeader(final TableColumnModel tableColumnModel) {
        super(tableColumnModel);
        columns = null;
        setUI(new ColumnGroupHeaderUI());
    }

    /**
     * @see javax.swing.table.JTableHeader#setReorderingAllowed(boolean)
     */
    public final void setReorderingAllowed(final boolean flag) {
        super.setReorderingAllowed(flag);
    }

    /**
     * Adds a column group to the header.
     *
     * @param columnGroup the new column group
     */
    public final void addColumnGroup(final ColumnGroup columnGroup) {
        if (columns == null) {
            columns = new Vector();
            setReorderingAllowed(false);
        }
        columns.addElement(columnGroup);
    }

    /**
     * Returns Enumeration of all hierarchical column groups spaning a given
     * column.
     *
     * @param tableColumn the column
     * @return Enumeration of all hierarchical column groups
     */
    final Enumeration getColumnGroups(final TableColumn tableColumn) {
        if (columns != null) {
            for (final Enumeration enumeration = columns.elements(); enumeration.hasMoreElements();) {
                final ColumnGroup columngroup = (ColumnGroup) enumeration.nextElement();
                final Vector vector = columngroup.getColumnGroups(tableColumn, new Vector());
                if (vector != null) {
                return vector.elements();
                }
            }
        }
        return null;
    }

    /**
     * Allows the renderer's tips to be used if there is text set.
     * @param e the location of the event identifies the proper renderer and,
     *            therefore, the proper tip
     * @return the tool tip for this component
     */
    public String getToolTipText(final MouseEvent e) {
        final int x;
        final KTable kTable1 = (KTable) getTable();
        final JXTable jxTable = kTable1.getEnclosingJXTable();
        if (kTable1.getType() == KTable.TYPE_LOCKED) {
            x = e.getX();
        } else {
            x = e.getX() + jxTable.getLockedTable().getWidth();
        }
        final MouseEvent event = new MouseEvent(jxTable.getTableHeader(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                e.getClickCount(), e.isPopupTrigger(), e.getButton());
        return jxTable.getTableHeader().getToolTipText(event);
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
        final KTable kTable1 = (KTable) getTable();
        final JXTable jxTable = kTable1.getEnclosingJXTable();
        if (kTable1.getType() == KTable.TYPE_LOCKED) {
            x = e.getX();
        } else {
            x = e.getX() + jxTable.getLockedTable().getWidth();
        }
        final MouseEvent event = new MouseEvent(jxTable.getTableHeader(), e.getID(), e.getWhen(), e.getModifiers(), x, e.getY(),
                e.getClickCount(), e.isPopupTrigger(), e.getButton());
        return jxTable.getTableHeader().getToolTipLocation(event);
    }
}
