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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


/**
 * Default renderer for table headers with column groups.
 *
 * @see net.sf.xframe.swing.JXTable
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public class DefaultColumnGroupHeaderRenderer extends DefaultTableCellRenderer implements ColumnGroupHeaderRenderer {

    /**
     * Constructor.
     */
    public DefaultColumnGroupHeaderRenderer() {
    }

    /**
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(
     *      javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public final Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
        final boolean hasFocus,  final int row, final int column) {
        final JTableHeader tableHeader = table.getTableHeader();
        if (tableHeader != null) {
            setForeground(tableHeader.getForeground());
            setBackground(tableHeader.getBackground());
            setBorder(BorderFactory.createRaisedBevelBorder());
            setFont(tableHeader.getFont());
        }

        setHorizontalTextPosition(JLabel.LEADING);
        setHorizontalAlignment(JLabel.CENTER);
        setText(value != null ? value.toString() : "");
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return this;
    }
}
