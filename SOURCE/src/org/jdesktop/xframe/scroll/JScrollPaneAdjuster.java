package org.jdesktop.xframe.scroll;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Work around for syncronization of scrolling in table header and viewport.
 *
 * JScrollPane's support for synchronization between row/column headers and the
 * main view is asymmetric. While it adjusts the headers if the position in the
 * view changes, it doesn't do so if the headers' positions changed. This means
 * that even a simple scrollRectToVisible call on a row/column header component
 * will scroll the header, but not properly adjust the main view. This happens
 * for example if you use a JTable as row header and the user selects by
 * keyboard or by dragging, as then implicit scrolling happens. This is a known
 * bug (#4202002).
 *
 * <p>Until that is fixed (3 years have already passed), the following class
 * manually synchronizes the positions. I have found that it doesn't always
 * work properly, when revalidation of the components is involved (i.e. size
 * changes, like a JTable column being resized, are involved).</p>
 *
 * <p>
 * <a href="http://www.chka.de/swing/components/JScrollPane-bugfix.html" target="_blank">
 * www.chka.de/swing/components/JScrollPane-bugfix.html</a>
 * </p>
 */
public final class JScrollPaneAdjuster implements PropertyChangeListener, Serializable {

    /** The scroll pnae to be adjusted. */
    private JScrollPane scrollPane;

    /** The horizontal adjuster. */
    private transient Adjuster hAdjuster;

    /** The vertical adjuster. */
    private transient Adjuster vAdjuster;

    /**
     * Constructor.
     *
     * @param theScrollPane the scroll pane
     */
    public JScrollPaneAdjuster(final JScrollPane theScrollPane) {
        scrollPane = theScrollPane;
        hAdjuster = new Adjuster(scrollPane.getViewport(), scrollPane.getColumnHeader(), Adjuster.HORIZONTAL_TYPE);
        vAdjuster = new Adjuster(scrollPane.getViewport(), scrollPane.getRowHeader(), Adjuster.VERTICAL_TYPE);
        scrollPane.addPropertyChangeListener(this);
    }

    /**
     * Dispose the adjuster.
     */
    public void dispose() {
        hAdjuster.dispose();
        vAdjuster.dispose();
        scrollPane.removePropertyChangeListener(this);
        scrollPane = null;
    }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(final PropertyChangeEvent e) {
        final String name = e.getPropertyName();
        if (name.equals("viewport")) {
            hAdjuster.setViewport((JViewport) e.getNewValue());
            vAdjuster.setViewport((JViewport) e.getNewValue());
        } else if (name.equals("rowHeader")) {
            vAdjuster.setHeader((JViewport) e.getNewValue());
        } else if (name.equals("columnHeader")) {
            hAdjuster.setHeader((JViewport) e.getNewValue());
        }
    }

    /**
     * Serialization.
     *
     * @param in the input stream
     * @throws IOException if an I/O error occurs.
     * @throws ClassNotFoundException if the class of a serialized object
     *         could not be found.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        hAdjuster = new Adjuster(scrollPane.getViewport(), scrollPane.getColumnHeader(), Adjuster.HORIZONTAL_TYPE);
        vAdjuster = new Adjuster(scrollPane.getViewport(), scrollPane.getRowHeader(), Adjuster.VERTICAL_TYPE);
    }

    /**
     * The adjustment worker.
     */
    private final class Adjuster implements ChangeListener, Runnable {

        /** Indicator of horizontal adjusters. */
        public static final int HORIZONTAL_TYPE = 1;

        /** Indicator of vertical adjusters. */
        public static final int VERTICAL_TYPE = 2;

        /** The viewport of the scroll pane. */
        private JViewport viewport;

        /** the header. */
        private JViewport header;

        /** Type of Adjuster (HORIZONTAL_TYPE | VERTICAL_TYPE). */
        private int type;

        /**
         * Constructor.
         *
         * @param theViewport the viewport
         * @param theHeader the header
         * @param theType the type
         */
        private Adjuster(final JViewport theViewport, final JViewport theHeader, final int theType) {
            viewport = theViewport;
            header = theHeader;
            type = theType;
            if (theHeader != null) {
                theHeader.addChangeListener(this);
            }
        }

        /**
         * Sets a new viewport.
         *
         * @param newViewport the new viewport
         */
        public void setViewport(final JViewport newViewport) {
            viewport = newViewport;
        }

        /**
         * Sets a new header.
         *
         * @param newHeader the new header
         */
        public void setHeader(final JViewport newHeader) {
            if (header != null) {
                header.removeChangeListener(this);
            }
            header = newHeader;
            if (header != null) {
                header.addChangeListener(this);
            }
        }

        /**
         * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
         */
        public void stateChanged(final ChangeEvent e) {
            if (viewport == null || header == null) {
                return;
            }
            if (type == HORIZONTAL_TYPE) {
                if (viewport.getViewPosition().x != header.getViewPosition().x) {
                    SwingUtilities.invokeLater(this);
                }
            } else {
                if (viewport.getViewPosition().y != header.getViewPosition().y) {
                    SwingUtilities.invokeLater(this);
                }
            }
        }

        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
            if (viewport == null || header == null) {
                return;
            }
            final Point viewportPosition = viewport.getViewPosition();
            final Point headerPosition = header.getViewPosition();
            if (type == HORIZONTAL_TYPE) {
                if (viewportPosition.x != headerPosition.x) {
                    viewport.setViewPosition(new Point(headerPosition.x, viewportPosition.y));
                }
            } else {
                if (viewportPosition.y != headerPosition.y) {
                    viewport.setViewPosition(new Point(viewportPosition.x, headerPosition.y));
                }
            }
        }

        /**
         * Disposes an Adjuster.
         */
        public void dispose() {
            if (header != null) {
                header.removeChangeListener(this);
            }
            viewport = null;
            header = null;
        }
    }
}