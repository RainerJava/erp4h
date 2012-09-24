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

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JScrollBar;

/**
 * Abstract base class for scrollbar binders.
 *
 * @author <a href=mailto:kriede@users.sourceforge.net>Kurt Riede</a>
 */
public abstract class AbstractScrollBinder {

    /** Map from <code>ScrollBar</code> to <code>AdjustmentListeners</code>. */
    private final HashMap components;

    /**
     * Constructor.
     */
    public AbstractScrollBinder() {
        components = new HashMap();
    }

    /**
     * Adds a scrollable component to the scrolling binder.
     *
     * <p>Concrete types must implement this method.</p>
     *
     * @param component a scroll-enabled component
     */
    public abstract void add(final JComponent component);

    /**
     * Removes a scrollable component to the scrolling binder.
     *
     * <p>Concrete types must implement this method.</p>
     *
     * @param component a scroll-enabled component
     */
    public abstract void remove(final JComponent component);

    /**
     * Adds a scrollable component to the scrolling binder.
     *
     * <p>The component can be a <code>JScrollPane</code> or any other instance
     * of a class that has a method
     * <code>getHorizonzalScrollBar()</code> or
     * <code>getVerticalScrollBar()</code>, depending on the concrete binder
     * type.</p>
     *
     * @param component a scroll-enabled component
     * @param methodName name of the method to get the scroll bar
     */
    protected final void add(final JComponent component, final String methodName) {
        final JScrollBar scrollBar = getScrollBar(component, methodName);
        if (scrollBar != null) {
            final AdjustmentListener listener = new BoundAdjustmentListener(methodName);
            scrollBar.addAdjustmentListener(listener);
            components.put(scrollBar, listener);
        }
    }

    /**
     * Removes a scrollabel component from the scrolling binder.
     *
     * @param component a scroll-enabled component
     * @param methodName name of the method to get the scroll bar
     */
    public final void remove(final JComponent component, final String methodName) {
        final JScrollBar scrollBar = getScrollBar(component, methodName);
        final AdjustmentListener listener = (AdjustmentListener) components.get(scrollBar);
        scrollBar.removeAdjustmentListener(listener);
        components.remove(scrollBar);
    }

    /**
     * Removes all components from the scrolling binder.
     */
    public final void removeAll() {
        for (final Iterator iterator = components.keySet().iterator(); iterator.hasNext();) {
            final JScrollBar scrollBar = (JScrollBar) iterator.next();
            final AdjustmentListener listener = (AdjustmentListener) components.get(scrollBar);
            scrollBar.removeAdjustmentListener(listener);
            components.remove(scrollBar);
        }
    }

    /**
     * Return the scrollbar of a component, accessable thru the method given by
     * tre method name.
     *
     * @param component a component
     * @param methodName name of method to get scroll bar
     * @return scroll bar or <code>null</code> if the method doesn't
     *         exist or returns <code>null</code>
     */
    private JScrollBar getScrollBar(final JComponent component, final String methodName) {
        try {
            final Method method = component.getClass().getMethod(methodName, null);
            final JScrollBar scrollBar = (JScrollBar) method.invoke(component, null);
            return scrollBar;
        } catch (Exception e) {
            final String msg = translate("invoke_method_failed", new Object[] {methodName, component.getClass().getName()});
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Translates the given key to a message according to the current locales.
     *
     * @param key key of entry in resource bundle
     * @param args arguments to the messane
     * @return localized message
     */
    private String translate(final String key, final Object[] args) {
        final String bundleName = this.getClass().getPackage().getName() + ".messages";
        final Locale locale = Locale.getDefault();
        final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        final String pattern = bundle.getString("AbstractScrollBinder" + "." + key);
        return MessageFormat.format(pattern, args);
    }

    /**
     * Adjusts all bound scrollbars according to a given adjustment event.
     */
    private final class BoundAdjustmentListener implements AdjustmentListener {

        /** name of method to get scroll bar. */
        private final String methodName;

        /**
         * Constructor.
         *
         * @param theMethodName name of method to get scroll bar
         */
        public BoundAdjustmentListener(final String theMethodName) {
            super();
            this.methodName = theMethodName;
        }

        /**
         * @see java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event.AdjustmentEvent)
         */
        public void adjustmentValueChanged(final AdjustmentEvent e) {
            for (final Iterator iterator = getComponents().keySet().iterator(); iterator.hasNext();) {
                JComponent component = (JComponent) iterator.next();
                if (!component.equals(e.getAdjustable())) {
                    JScrollBar scollBar = (JScrollBar) component;
                    final int extent = scollBar.getModel().getExtent();
                    final int min = scollBar.getModel().getMinimum();
                    final int max = scollBar.getModel().getMaximum();
                    scollBar.setValues(e.getValue(), extent, min, max);
                }
            }
        }
    }

    /**
     * Returns the map of components.
     * @return components map
     */
    protected final HashMap getComponents() {
        return components;
    }
}
