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
import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Column groups to be used with the JXTable class.
 *
 * @see net.sf.xframe.swing.JXTable
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public final class ColumnGroup {

    /** Cell renderer. */
    private TableCellRenderer renderer;

    /** Columns within a column group. */
    private Vector columns;

    /** Name of a column group. */
    private String name;

    /**
     * Constructor.
     *
     * @param theName the name
     */
    public ColumnGroup(final String theName) {
        this(null, theName);
    }

    /**
     * Constructor.
     *
     * @param tableCellRenderer the table cell renderer
     * @param theName the name of the column group
     */
    public ColumnGroup(final TableCellRenderer tableCellRenderer, final String theName) {
        columns = new Vector();
        if (tableCellRenderer == null) {
            renderer = new DefaultColumnGroupHeaderRenderer();
        } else {
            renderer = tableCellRenderer;
        }
        name = theName;
    }

    /**
     * Adds a column to the column group.
     *
     * @param tableColumn a table column
     */
    public void add(final TableColumn tableColumn) {
        if (tableColumn != null) {
            columns.addElement(tableColumn);
        }
    }

    /**
     * Adds a column group to a a column group.
     *
     * @param columnGroup a column group
     */
    public void add(final ColumnGroup columnGroup) {
        if (columnGroup != null) {
            columns.addElement(columnGroup);
        }
    }

    /**
     * Returns en Enumeration of all columns of a column group.
     *
     * @return Enumeration of all columns
     */
    public Enumeration getColumns() {
        return columns.elements();
    }

    /**
     * todo comment this method.
     *
     * @param tableColumn a table column
     * @param vector a vector to collect column groups
     * @return Vector og column groups
     */
    Vector getColumnGroups(final TableColumn tableColumn, final Vector vector) {
        vector.addElement(this);
        if (columns.contains(tableColumn)) {
            return vector;
        }
        for (final Enumeration enumeration = columns.elements(); enumeration.hasMoreElements();) {
            final Object obj = enumeration.nextElement();
            if (obj instanceof ColumnGroup) {
                final Vector vector1 = ((ColumnGroup) obj).getColumnGroups(tableColumn, (Vector) vector.clone());
                if (vector1 != null) {
                    return vector1;
                }
            }
        }
        return null;
    }

    /**
     * Returns the header renderer.
     *
     * @return header renderer
     */
    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    /**
     * Sets the header renderer.
     *
     * @param tableCellRenderer the new header renderer
     */
    public void setHeaderRenderer(final TableCellRenderer tableCellRenderer) {
        if (tableCellRenderer != null) {
            renderer = tableCellRenderer;
        }
    }

    /**
     * Returns the name of the header.
     *
     * @return header name
     */
    public Object getHeaderValue() {
        return name;
    }

    /**
     * Returns the size of the header.
     *
     * @param table a table
     * @return size of header
     */
    public Dimension getSize(final JTable table) {
        final Component component = renderer.getTableCellRendererComponent(table, getHeaderValue(), false, false, -1, -1);
        final int height = component.getPreferredSize().height;
        int width = 0;
        for (final Enumeration enumeration = columns.elements(); enumeration.hasMoreElements();) {
            final Object obj = enumeration.nextElement();
            if (obj instanceof TableColumn) {
                final TableColumn tablecolumn = (TableColumn) obj;
                width += tablecolumn.getWidth();
            } else {
                width += ((ColumnGroup) obj).getSize(table).width;
            }
        }
        return new Dimension(width, height);
    }
}
