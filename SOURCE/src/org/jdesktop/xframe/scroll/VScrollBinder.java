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
package org.jdesktop.xframe.scroll;

import javax.swing.JComponent;

/**
 * Binds vertical scrollbars of two or more components.
 *
 * <p>All added components must have a
 * <code>getVerticalScrollbar()</code> method that returns a
 * <code>JScrollBar</code> object.</p>
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public final class VScrollBinder extends AbstractScrollBinder {

    /**
     * Contructor.
     */
    public VScrollBinder() {
        super();
    }

    /**
     * Adds a vertical scrollable component to the vertical scrolling binder.
     *
     * <p>The component can be a <code>JScrollPane</code> or any other instance
     * of a class that has a method
     * <code>getVerticalScrollBar()</code>.</p>
     *
     * @param component a scroll-enabled component
     * @see net.sf.xframe.swing.scroll.AbstractScrollBinder#add(javax.swing.JComponent)
     */
    public void add(final JComponent component) {
        add(component, "getVerticalScrollBar");
    }

    /**
     * @see net.sf.xframe.swing.scroll.AbstractScrollBinder#remove(javax.swing.JComponent)
     */
    public void remove(final JComponent component) {
        remove(component, "getVerticalScrollBar");
    }
}
