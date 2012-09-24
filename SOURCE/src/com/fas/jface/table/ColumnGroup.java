package com.fas.jface.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.table.TableColumnExt;

/**
 * ColumnGroup.
 *
 * @author Nobuo Tamemasa
 * @author Martin Miller
 * @version 2.0 - Support for SwingX's TableColumnExt Visible Property
 */
public class ColumnGroup {

    protected TableCellRenderer renderer;
    protected Vector<Object> v;
    protected String text;
    protected int margin = 0;

    public ColumnGroup(String text) {
        this(null, text);
    }

    public ColumnGroup(TableCellRenderer renderer, String text) {
        this.renderer = renderer == null ? new GroupTableCellRenderer() : renderer;
        this.text = text;
        v = new Vector<Object>();
    }

    /**
     * @param obj TableColumn or ColumnGroup
     */
    public void add(Object obj) {
        if (obj == null) {
            return;
        }
        v.addElement(obj);
    }

    /**
     * @param c TableColumn
     * @param v ColumnGroups
     */
    @SuppressWarnings(value = "unchecked")
    public Vector getColumnGroups(TableColumn c, Vector<Object> g) {
        g.addElement(this);

        if (v.contains(c)) {
            return g;
        }
        Iterator it = v.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof ColumnGroup) {
                Vector groups = ((ColumnGroup) obj).getColumnGroups(c, (java.util.Vector) g.clone());
                if (groups != null) {
                    return groups;
                }
            }
        }

        return null;
    }

    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    public Object getHeaderValue() {
        return text;
    }

    public Dimension getSize(JTable table) {
        Component comp = renderer.getTableCellRendererComponent(table, getHeaderValue(), false, false, -1, -1);
        int height = comp.getPreferredSize().height;
        int width = 0;

        Iterator it = v.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof TableColumnExt) { // Martin 2008-03-19
                TableColumnExt aColumn = (TableColumnExt) obj;
                if (aColumn.isVisible()) {
                    width += aColumn.getWidth();
                    width += margin;
                }
            } else if (obj instanceof TableColumn) {
                TableColumn aColumn = (TableColumn) obj;
                width += aColumn.getWidth();
                width += margin;
            } else {
                width += ((ColumnGroup) obj).getSize(table).width;
            }
        }

        return new Dimension(width, height);
    }

    public void setColumnMargin(int margin) {
        this.margin = margin;

        Iterator it = v.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof ColumnGroup) {
                ((ColumnGroup) obj).setColumnMargin(margin);
            }
        }
    }
    protected Color backColor = null;

    protected Color getMyBackground() {
        return backColor;
    }

    protected void setMyBackground(Color backColor) {
        this.backColor = backColor;
    }

    public class GroupTableCellRenderer extends DefaultTableCellRenderer {

        public GroupTableCellRenderer() {
            super();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            JTableHeader header = table.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                //setBackground(header.getBackground());
                setBackground(getMyBackground() == null ? header.getBackground() : getMyBackground());
                setFont(header.getFont());
            }

            setHorizontalAlignment(JLabel.CENTER);
            setText((value == null) ? "" : value.toString());
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));

            return this;
        }
    }
}