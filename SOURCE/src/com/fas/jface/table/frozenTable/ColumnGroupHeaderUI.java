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
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.table.TableHeaderRenderer;

/**
 * UI of table headers with column groups.
 *
 * @see net.sf.xframe.swing.JXTable
 * @see net.sf.xframe.swing.table.ColumnGroupHeader
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public class ColumnGroupHeaderUI extends BasicTableHeaderUI {

    /** Maximum header width. */
    private static final long MAX_WIDTH = Integer.MAX_VALUE; //0x7fffffffL

    /**
     * Constructor.
     */
    public ColumnGroupHeaderUI() {
    }

    /**
     * @see javax.swing.plaf.ComponentUI#paint(java.awt.Graphics, javax.swing.JComponent)
     */
    public final void paint(final Graphics graphics, final JComponent component) {
        final Rectangle rectangle = graphics.getClipBounds();
        if (super.header.getColumnModel() == null) {
            return;
        }
        int columnIndex = 0;
        final Dimension dimension = super.header.getSize();
        final Rectangle rectangle1 = new Rectangle(0, 0, dimension.width, dimension.height);
        final Hashtable hashtable = new Hashtable();
        int margin = super.header.getColumnModel().getColumnMargin();
        for (final Enumeration enumeration = super.header.getColumnModel().getColumns(); enumeration.hasMoreElements();) {
            rectangle1.height = dimension.height;
            rectangle1.y = 0;
            final TableColumn tablecolumn = (TableColumn) enumeration.nextElement();
            final Enumeration enumeration1 = ((ColumnGroupHeader) super.header).getColumnGroups(tablecolumn);
            if (enumeration1 != null) {
                int height = 0;
                while (enumeration1.hasMoreElements()) {
                    final ColumnGroup columngroup = (ColumnGroup) enumeration1.nextElement();
                    Rectangle rectangle2 = (Rectangle) hashtable.get(columngroup);
                    if (rectangle2 == null) {
                        rectangle2 = new Rectangle(rectangle1);
                        final Dimension dimension1 = columngroup.getSize(super.header.getTable());
                        rectangle2.width = dimension1.width;
                        rectangle2.width -= margin;
                        rectangle2.height = dimension1.height;
                        hashtable.put(columngroup, rectangle2);
                    }
                    paintCell(graphics, rectangle2, columngroup);
                    height += rectangle2.height;
                    rectangle1.height = dimension.height - height;
                    rectangle1.y = height;
                }
            }
            rectangle1.width = tablecolumn.getWidth() - margin;
            if (rectangle1.intersects(rectangle)) {
                paintCell(graphics, rectangle1, columnIndex);
            }
            rectangle1.x += tablecolumn.getWidth();
            columnIndex++;
        }
    }

    /**
     * Paints a column cell.
     *
     * @param graphics the graphics
     * @param rect the rect
     * @param columnIndex the column index
     */
    private void paintCell(final Graphics graphics, final Rectangle rect, final int columnIndex) {
        final TableColumn tableColumn = super.header.getColumnModel().getColumn(columnIndex);
        Object obj = tableColumn.getHeaderRenderer();
        if (obj == null) {
            obj = new DefaultColumnGroupHeaderRenderer();
        }
        final TableCellRenderer renderer = (TableCellRenderer) obj;        
        final JTable table = super.header.getTable();
        final Object name = tableColumn.getHeaderValue();
        final Component component = renderer.getTableCellRendererComponent(table, name, false, false, -1, columnIndex);
        super.rendererPane.add(component);
        super.rendererPane.paintComponent(graphics, component, super.header, rect.x, rect.y, rect.width, rect.height, true);
    }

    /**
     * Paints a column group cell.
     *
     * @param graphics the graphics
     * @param rect the rect
     * @param columnGroup the column group
     */
    private void paintCell(final Graphics graphics, final Rectangle rect, final ColumnGroup columnGroup) {
        final TableCellRenderer renderer = columnGroup.getHeaderRenderer();
        final JTable table = super.header.getTable();
        final Object name = columnGroup.getHeaderValue();        
        final Component component = renderer.getTableCellRendererComponent(table, name, false, false, -1, -1);
        super.rendererPane.add(component);
        super.rendererPane.paintComponent(graphics, component, super.header, rect.x, rect.y, rect.width, rect.height, true);
    }

    /**
     * @see javax.swing.plaf.ComponentUI#getPreferredSize(javax.swing.JComponent)
     */
    public final Dimension getPreferredSize(final JComponent component) {
        long width = 0L;
        for (final Enumeration enumeration = super.header.getColumnModel().getColumns(); enumeration.hasMoreElements();) {
            final TableColumn tablecolumn = (TableColumn) enumeration.nextElement();
            width += tablecolumn.getPreferredWidth();
        }
        final TableColumnModel tableColumnModel = super.header.getColumnModel();
        final long size = width + tableColumnModel.getColumnMargin() * tableColumnModel.getColumnCount();
        return new Dimension((int) (size > MAX_WIDTH ? MAX_WIDTH : size), getHeaderHeight());
    }

    /**
     * Returns the height og the header.
     *
     * @return header height
     */
    private int getHeaderHeight() {
        int height = 0;
        final TableColumnModel tablecolumnmodel = super.header.getColumnModel();
        for (int column = 0; column < tablecolumnmodel.getColumnCount(); column++) {
            final TableColumn tableColumn = tablecolumnmodel.getColumn(column);
            Object obj = tableColumn.getHeaderRenderer();
            if (obj == null) {
                obj = new DefaultColumnGroupHeaderRenderer();
            }
            final TableCellRenderer renderer = (TableCellRenderer) obj;
            final JTable table = super.header.getTable();
            final Object name = tableColumn.getHeaderValue();
            final Component component = (renderer).getTableCellRendererComponent(table, name, false, false, -1, column);
            int k = component.getPreferredSize().height;
            final Enumeration enumeration = ((ColumnGroupHeader) super.header).getColumnGroups(tableColumn);
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    final ColumnGroup columngroup = (ColumnGroup) enumeration.nextElement();
                    k += columngroup.getSize(super.header.getTable()).height;
                }
            }
            height = Math.max(height, k);
        }
        return height;
    }
}
