package org.jdesktop.xswingx.plaf;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.UIResource;
import javax.swing.text.Document;

import org.jdesktop.xswingx.BuddySupport;
import org.jdesktop.xswingx.NativeSearchFieldSupport;

import com.fas.jface.text.CdInputSearchText;

/**
 * The default {@link CdInputSearchText} UI delegate.
 * 
 * @author Peter Weishapl <petw@gmx.net>
 * 
 */
public class HaSearchFieldUI extends BuddyTextFieldUI {
	/**
	 * The search field that we're a UI delegate for. Initialized by the
	 * <code>installUI</code> method, and reset to null by
	 * <code>uninstallUI</code>.
	 * 
	 * @see #installUI
	 * @see #uninstallUI
	 */
	protected CdInputSearchText searchField;

	private Handler handler;

	public static final Insets NO_INSETS = new Insets(0, 0, 0, 0);

	public HaSearchFieldUI(TextUI delegate) {
		super(delegate);
	}

	private Handler getHandler() {
		if (handler == null) {
			handler = new Handler();
		}
		return handler;
	}

	/**
	 * Calls {@link #installDefaults()}, adds the search, clear and popup
	 * button to the search field and registers a {@link PropertyChangeListener}
	 * ad {@link DocumentListener} and an {@link ActionListener} on the popup
	 * button.
	 */
	public void installUI(JComponent c) {
		searchField = (CdInputSearchText) c;

		super.installUI(c);

		installDefaults();
		layoutButtons();

		configureListeners();
	}

	private void configureListeners() {
		if (isNativeSearchField()) {
			searchField.removePropertyChangeListener(getHandler());
		} else {
			searchField.addPropertyChangeListener(getHandler());
		}

		// add support for instant search mode in any case.
		searchField.getDocument().addDocumentListener(getHandler());
	}

	private boolean isNativeSearchField() {
		return NativeSearchFieldSupport.isNativeSearchField(searchField);
	}

	@Override
	protected BuddyLayoutAndBorder createBuddyLayoutAndBorder() {
		return new BuddyLayoutAndBorder() {
			/**
			 * This does nothing, if the search field is rendered natively on
			 * Leopard.
			 */
			@Override
			protected void replaceBorderIfNecessary() {
				if (!isNativeSearchField()) {
					super.replaceBorderIfNecessary();
				}
			}

			/**
			 * Return zero, when the search field is rendered natively on
			 * Leopard, to make painting work correctly.
			 */
			@Override
			public Dimension preferredLayoutSize(Container parent) {
				if (isNativeSearchField()) {
					return new Dimension();
				} else {
					return super.preferredLayoutSize(parent);
				}
			}

			/**
			 * Prevent 'jumping' when text is entered: Include the clear button,
			 * when layout style is Mac. When layout style is Vista: Take the
			 * clear button's preferred width if its either greater than the
			 * search button's pref. width or greater than the popup button's
			 * pref. width when a popup menu is installed and not using a
			 * seperate popup button.
			 */
			@Override
			public Insets getBorderInsets(Component c) {
				Insets insets = super.getBorderInsets(c);
				if (searchField != null && !isNativeSearchField()) {
						JButton refButton = searchButton();

						int refWidth = refButton.getPreferredSize().width;
							insets.right = refWidth;

				}
				return insets;
			}
		};
	}

	private void layoutButtons() {
		BuddySupport.removeAll(searchField);

		if (isNativeSearchField()) {
			return;
		}

			BuddySupport.addRight(searchButton(), searchField);


	}

	private boolean isMacLayoutStyle() {
		return false;
	}

	/**
	 * Initialize the search fields various properties based on the
	 * corresponding "SearchField.*" properties from defaults table. The
	 * {@link CdInputSearchText}s layout is set to the value returned by
	 * <code>createLayout</code>. Also calls
	 * {@link #replaceBorderIfNecessary()} and {@link #updateButtons()}. This
	 * method is called by {@link #installUI(JComponent)}.
	 * 
	 * @see #installUI
	 * @see #createLayout
	 * @see CdInputSearchText#customSetUIProperty(String, Object)
	 */
	protected void installDefaults() {
		if (isNativeSearchField()) {
			return;
		}

//		if (UIManager.getBoolean("SearchField.useSeperatePopupButton")) {
//			searchField.customSetUIProperty("useSeperatePopupButton", Boolean.TRUE);
//		} else {
//			searchField.customSetUIProperty("useSeperatePopupButton", Boolean.FALSE);
//		}
//
//		searchField.customSetUIProperty("layoutStyle", UIManager.get("SearchField.layoutStyle"));
//		searchField.customSetUIProperty("promptFontStyle", UIManager.get("SearchField.promptFontStyle"));
//
//		if (shouldReplaceResource(searchField.getOuterMargin())) {
//			searchField.setOuterMargin(UIManager.getInsets("SearchField.buttonMargin"));
//		}

		updateButtons();

		searchButton().setIcon(getNewIcon(searchButton().getIcon(), "SearchField.icon"));
	}

	/**
	 * Removes all installed listeners, the layout and resets the search field
	 * original border and removes all children.
	 */
	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);

		searchField.removePropertyChangeListener(getHandler());
		searchField.getDocument().removeDocumentListener(getHandler());

		searchField.setLayout(null);
		searchField.removeAll();
		searchField = null;
	}

	/**
	 * Returns true if <code>o</code> is <code>null</code> or of instance
	 * {@link UIResource}.
	 * 
	 * @param o
	 *            an object
	 * @return true if <code>o</code> is <code>null</code> or of instance
	 *         {@link UIResource}
	 */
	protected boolean shouldReplaceResource(Object o) {
		return o == null || o instanceof UIResource;
	}

	/**
	 * Convience method for only replacing icons if they have not been
	 * customized by the user. Returns the icon from the defaults table
	 * belonging to <code>resKey</code>, if
	 * {@link #shouldReplaceResource(Object)} with the <code>icon</code> as a
	 * parameter returns <code>true</code>. Otherwise returns
	 * <code>icon</code>.
	 * 
	 * @param icon
	 *            the current icon
	 * @param resKey
	 *            the resource key identifying the default icon
	 * @return the new icon
	 */
	protected Icon getNewIcon(Icon icon, String resKey) {
		Icon uiIcon = UIManager.getIcon(resKey);
		if (shouldReplaceResource(icon)) {
			return uiIcon;
		}
		return icon;
	}

	/**
	 * Convienence method.
	 * 
	 * @see CdInputSearchText#getFindButton()
	 * @return the search button
	 */
	protected final JButton searchButton() {
		return searchField.getFindButton();
	}

	/**
	 * Returns <code>true</code> if
	 * {@link CdInputSearchText#isUseSeperatePopupButton()} is <code>true</code>
	 * and a search popup menu has been set.
	 * 
	 * @return the popup button is used in addition to the search button
	 */
	public boolean usingSeperatePopupButton() {
		return false;
	}

	/**
	 * Returns the number of pixels between the popup button and the clear (or
	 * search) button as specified in the default table by
	 * 'SearchField.popupOffset'. Returns 0 if
	 * {@link #usingSeperatePopupButton()} returns <code>false</code>
	 * 
	 * @return number of pixels between the popup button and the clear (or
	 *         search) button
	 */
	protected int getPopupOffset() {
		if (usingSeperatePopupButton()) {
			return UIManager.getInt("SearchField.popupOffset");
		}
		return 0;
	}

	/**
	 * Sets the visibility of the search, clear and popup buttons depending on
	 * the search mode, layout stye, search text, search popup menu and the use
	 * of a seperate popup button. Also resets the search buttons pressed and
	 * rollover icons if the search field is in regular search mode or clears
	 * the icons when the search field is in instant search mode.
	 */
	protected void updateButtons() {

		searchButton().setVisible(true);

			searchButton().setRolloverIcon(getNewIcon(searchButton().getRolloverIcon(), "SearchField.rolloverIcon"));
			searchButton().setPressedIcon(getNewIcon(searchButton().getPressedIcon(), "SearchField.pressedIcon"));
	}

	private boolean hasText() {
		return searchField.getText() != null && searchField.getText().length() > 0;
	}

	class Handler implements PropertyChangeListener, ActionListener, DocumentListener {
		public void propertyChange(PropertyChangeEvent evt) {
			String prop = evt.getPropertyName();
			Object src = evt.getSource();

			if (src.equals(searchField)) {
				if ("findPopupMenu".equals(prop) || "searchMode".equals(prop)
						|| "useSeperatePopupButton".equals(prop) || "searchMode".equals(prop)
						|| "layoutStyle".equals(prop)) {
					layoutButtons();
					updateButtons();
				} else if ("document".equals(prop)) {
					Document doc = (Document) evt.getOldValue();
					if (doc != null) {
						doc.removeDocumentListener(this);
					}
					doc = (Document) evt.getNewValue();
					if (doc != null) {
						doc.addDocumentListener(this);
					}
				}
			}
		}

		/**
		 * Shows the search popup menu, if installed.
		 */
		public void actionPerformed(ActionEvent e) {

		}

		public void changedUpdate(DocumentEvent e) {
			update();
		}

		public void insertUpdate(DocumentEvent e) {
			update();
		}

		public void removeUpdate(DocumentEvent e) {
			update();
		}

		/**
		 * Called when the search text changes. Calls
		 * {@link CdInputSearchText#postActionEvent()} In instant search mode or
		 * starts the search field instant search timer if the instant search
		 * delay is greater 0.
		 */
		private void update() {
			if (true) {
//				searchField.getInstantSearchTimer().stop();
//				// only use timer when delay greater 0.
//				if (searchField.getInstantSearchDelay() > 0) {
//					searchField.getInstantSearchTimer().setInitialDelay(searchField.getInstantSearchDelay());
//					searchField.getInstantSearchTimer().start();
//				} else {
					searchField.postActionEvent();
//				}
			}

			updateButtons();
		}
	}
}
