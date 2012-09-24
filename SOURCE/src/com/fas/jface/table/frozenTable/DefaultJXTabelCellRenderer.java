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

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;






/**
 * The standard class for rendering (displaying) individual cells
 * in a {@link net.sf.xframe.swing.JXTable JXTable}.
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public class DefaultJXTabelCellRenderer extends DefaultTableCellRenderer {

    /**
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
     *      java.lang.Object, boolean, boolean, int, int)
     */
    public final Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (table instanceof KTable) {
            final KTable kTable = (KTable) table;
            final JXTable jxTable = kTable.getEnclosingJXTable();
            if (kTable.getType() == KTable.TYPE_LOCKED) {
                return getTableCellRendererComponent(jxTable, value, isSelected, hasFocus, row, column);
            } else if (kTable.getType() == KTable.TYPE_SCROLL) {
                final int frozenColumns = jxTable.getFrozenColumns();
                return getTableCellRendererComponent(jxTable, value, isSelected, hasFocus, row, column + frozenColumns);
            }
            
           
        }
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return c;
    }

    /**
     * Returns the default table cell renderer.
     *
     * @param jxTable  the <code>JXTable</code>
     * @param value  the value to assign to the cell at
     *          <code>[row, column]</code>
     * @param isSelected true if cell is selected
     * @param hasFocus true if cell has focus
     * @param row  the row of the cell to render
     * @param column the column of the cell to render
     * @return the default table cell renderer
     */
    public Component getTableCellRendererComponent(final JXTable jxTable, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {
        final int frozenColumns = jxTable.getFrozenColumns();
        if (column < frozenColumns) {
            final JTable table = jxTable.getLockedTable();
            final TableCellRenderer renderer = table.getColumnModel().getColumn(column).getCellRenderer();
            return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        } else {
            final JTable table = jxTable.getScrollTable();
            final TableCellRenderer renderer = table.getColumnModel().getColumn(column - frozenColumns).getCellRenderer();
            return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column - frozenColumns);
        }
        
       
    }
}
