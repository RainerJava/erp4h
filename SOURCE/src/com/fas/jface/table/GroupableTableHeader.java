package com.fas.jface.table;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Groupable TableHeader.
 *
 * @author Nobuo Tamemasa
 * @author Martin Miller - Corrections for JDK 1.5
 */
public class GroupableTableHeader extends JTableHeader {

    protected Vector columnGroups = null;

    public GroupableTableHeader(TableColumnModel model) {
        super(model);
        setUI(new GroupableTableHeaderUI());
        setReorderingAllowed(false);
    }

    @Override
    public void setReorderingAllowed(boolean b) {
        reorderingAllowed = false;
    }

    @SuppressWarnings("unchecked")
    public void addColumnGroup(ColumnGroup g) {
        if (columnGroups == null) {
            columnGroups = new Vector();
        }

        columnGroups.addElement(g);
    }

    @SuppressWarnings("unchecked")
    public Enumeration getColumnGroups(TableColumn col) {
        if (columnGroups == null) {
            return null;
        }
        Iterator it = columnGroups.iterator();
        while (it.hasNext()) {
            ColumnGroup cGroup = (ColumnGroup) it.next();
            Vector v_ret = cGroup.getColumnGroups(col, new java.util.Vector());
            if (v_ret != null) {
                return v_ret.elements();
            }
        }

        return null;
    }

    public void setColumnMargin() {
        if (columnGroups == null) {
            return;
        }
        int columnMargin = getColumnModel().getColumnMargin() - (int) getTable().getIntercellSpacing().getWidth();
        Iterator it = columnGroups.iterator();
        while (it.hasNext()) {
            ColumnGroup cGroup = (ColumnGroup) it.next();
            cGroup.setColumnMargin(columnMargin);
        }
    }
}
