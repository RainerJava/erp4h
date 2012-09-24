package com.fas.jface.table;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Groupable TableHeaderUI.
 *
 * @author Nobuo Tamemasa
 * @author Martin Miller - Corrections for JDK 1.5
 */
public class GroupableTableHeaderUI extends BasicTableHeaderUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        Rectangle clipBounds = g.getClipBounds();

        if (header.getColumnModel() == null) {
            return;
        }
        int column = 0;

        Dimension size = header.getSize();
        Rectangle cellRect = new Rectangle(0, 0, size.width, size.height);
        Hashtable<ColumnGroup, Rectangle> h = new Hashtable<ColumnGroup, Rectangle>();
        int columnMargin = header.getColumnModel().getColumnMargin() - (int) header.getTable().getIntercellSpacing().getWidth();

        Enumeration enumeration = header.getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            cellRect.height = size.height;
            cellRect.y = 0;

            TableColumn aColumn = (TableColumn) enumeration.nextElement();
            Enumeration cGroups = ((GroupableTableHeader) header).getColumnGroups(aColumn);
            if (cGroups != null) {
                int groupHeight = 0;
                while (cGroups.hasMoreElements()) {
                    ColumnGroup cGroup = (ColumnGroup) cGroups.nextElement();
                    Rectangle groupRect = h.get(cGroup);
                    if (groupRect == null) {
                        groupRect = new Rectangle(cellRect);
                        Dimension d = cGroup.getSize(header.getTable());
                        groupRect.width = d.width;
                        groupRect.height = d.height;
                        h.put(cGroup, groupRect);
                    }
                    paintCell(g, groupRect, cGroup);
                    groupHeight += groupRect.height;
                    cellRect.height = size.height - groupHeight;
                    cellRect.y = groupHeight;
                }
            }
            cellRect.width = aColumn.getWidth() + columnMargin;
            if (cellRect.intersects(clipBounds)) {
                paintCell(g, cellRect, column);
            }
            cellRect.x += cellRect.width;
            column++;
        }
    }

    private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
        TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
        TableCellRenderer renderer = header.getDefaultRenderer();
        Component component = renderer.getTableCellRendererComponent(header.getTable(), aColumn.getHeaderValue(), false, false, -1, columnIndex);
        if (aColumn.getHeaderRenderer() != null) {
            SubTableHeaderRenderer subHeader = (SubTableHeaderRenderer) aColumn.getHeaderRenderer();
            component.setBackground(subHeader.getBackground());
            rendererPane.add(component);
            rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
        } else {
            rendererPane.add(component);
            rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
        }
    }

    private void paintCell(Graphics g, Rectangle cellRect, ColumnGroup cGroup) {
        TableCellRenderer renderer = cGroup.getHeaderRenderer();
        Component component = renderer.getTableCellRendererComponent(header.getTable(), cGroup.getHeaderValue(), false, false, -1, -1);
        rendererPane.add(component);
        rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
    }

    private int getHeaderHeight() {
        int height = 0;
        TableColumnModel columnModel = header.getColumnModel();
        for (int column = 0; column < columnModel.getColumnCount(); column++) {
            TableColumn aColumn = columnModel.getColumn(column);
            TableCellRenderer renderer = header.getDefaultRenderer();
            Component comp = renderer.getTableCellRendererComponent(header.getTable(), aColumn.getHeaderValue(), false, false, -1, column);
            int cHeight = comp.getPreferredSize().height;
            Enumeration e = ((GroupableTableHeader) header).getColumnGroups(aColumn);
            if (e != null) {
                while (e.hasMoreElements()) {
                    ColumnGroup cGroup = (ColumnGroup) e.nextElement();
                    cHeight += cGroup.getSize(header.getTable()).height;
                }
            }
            height = Math.max(height, cHeight);
        }

        return height;
    }

    private Dimension createHeaderSize(long width) {
        TableColumnModel columnModel = header.getColumnModel();
        width += columnModel.getColumnMargin() * columnModel.getColumnCount();
        if (width > Integer.MAX_VALUE) {
            width = Integer.MAX_VALUE;
        }

        return new Dimension((int) width, getHeaderHeight());
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        long width = 0;
        Enumeration enumeration = header.getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            TableColumn aColumn = (TableColumn) enumeration.nextElement();
            width = width + aColumn.getPreferredWidth();
        }

        return createHeaderSize(width);
    }
}
