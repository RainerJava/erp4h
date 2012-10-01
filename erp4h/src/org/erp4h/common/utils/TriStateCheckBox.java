package org.erp4h.common.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ActionMapUIResource;

public class TriStateCheckBox extends JCheckBox {
	/** This is a type-safe enumerated type */
	public static class State {
		private State() {
		}
	}

	private class TriStateDecorator implements ButtonModel {
		private final ButtonModel other;

		private TriStateDecorator(ButtonModel other) {
			this.other = other;
		}

		@Override
		public void addActionListener(ActionListener l) {
			other.addActionListener(l);
		}

		@Override
		public void addChangeListener(ChangeListener l) {
			other.addChangeListener(l);
		}

		@Override
		public void addItemListener(ItemListener l) {
			other.addItemListener(l);
		}

		@Override
		public String getActionCommand() {
			return other.getActionCommand();
		}

		@Override
		public int getMnemonic() {
			return other.getMnemonic();
		}

		@Override
		public Object[] getSelectedObjects() {
			return other.getSelectedObjects();
		}

		private State getState() {
			if (isSelected() && !isArmed()) {
				// normal black tick
				return SELECTED;
			} else if (isSelected() && isArmed()) {
				// don't care grey tick
				return DONT_CARE;
			} else {
				// normal deselected
				return NOT_SELECTED;
			}
		}// getState

		/**
		 * All these methods simply delegate to the "other" model that is being
		 * decorated.
		 */
		@Override
		public boolean isArmed() {
			return other.isArmed();
		}

		@Override
		public boolean isEnabled() {
			return other.isEnabled();
		}

		@Override
		public boolean isPressed() {
			return other.isPressed();
		}

		@Override
		public boolean isRollover() {
			return other.isRollover();
		}

		@Override
		public boolean isSelected() {
			return other.isSelected();
		}

		private void nextState() {
			State current = getState();
			if (current == NOT_SELECTED) {
				setState(SELECTED);
			} else if (current == SELECTED) {
				setState(DONT_CARE);
			} else if (current == DONT_CARE) {
				setState(NOT_SELECTED);
			}
		}// nextState

		@Override
		public void removeActionListener(ActionListener l) {
			other.removeActionListener(l);
		}

		@Override
		public void removeChangeListener(ChangeListener l) {
			other.removeChangeListener(l);
		}

		@Override
		public void removeItemListener(ItemListener l) {
			other.removeItemListener(l);
		}

		@Override
		public void setActionCommand(String s) {
			other.setActionCommand(s);
		}

		/** Filter: No one may change the armed status except us. */
		@Override
		public void setArmed(boolean b) {
		}

		/**
		 * We disable focusing on the component when it is not enabled.
		 */
		@Override
		public void setEnabled(boolean b) {
			setFocusable(b);
			other.setEnabled(b);
		}

		@Override
		public void setGroup(ButtonGroup group) {
			other.setGroup(group);
		}

		@Override
		public void setMnemonic(int key) {
			other.setMnemonic(key);
		}

		@Override
		public void setPressed(boolean b) {
			other.setPressed(b);
		}

		@Override
		public void setRollover(boolean b) {
			other.setRollover(b);
		}

		@Override
		public void setSelected(boolean b) {
			other.setSelected(b);
		}

		private void setState(State state) {
			if (state == NOT_SELECTED) {
				other.setArmed(false);
				setPressed(false);
				setSelected(false);
			} else if (state == SELECTED) {
				other.setArmed(false);
				setPressed(false);
				setSelected(true);
			} else { // either "null" or DONT_CARE
				other.setArmed(true);
				setPressed(true);
				setSelected(true);
			}
		}// setState
	}// TriStateDecorator

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final State NOT_SELECTED = new State();
	public static final State SELECTED = new State();

	public static final State DONT_CARE = new State();

	private final TriStateDecorator model;

	public TriStateCheckBox() {
		this(null);
	}// TriStateCheckBox

	public TriStateCheckBox(String text) {
		this(text, DONT_CARE);
	}

	public TriStateCheckBox(String text, Icon icon, State initial) {
		super(text, icon);
		// Add a listener for when the mouse is pressed
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				grabFocus();
				model.nextState();
			}
		});
		// Reset the keyboard action map
		ActionMap map = new ActionMapUIResource();
		map.put("pressed", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				grabFocus();
				model.nextState();
			}
		});
		map.put("released", null);
		SwingUtilities.replaceUIActionMap(this, map);
		// set the model to the adapted model
		model = new TriStateDecorator(getModel());
		setModel(model);
		setState(initial);
	}

	public TriStateCheckBox(String text, State initial) {
		this(text, null, initial);
	}

	/** No one may add mouse listeners, not even Swing! */
	@Override
	public void addMouseListener(MouseListener l) {
	}

	/**
	 * Return the current state, which is determined by the selection status of
	 * the model.
	 */
	public State getState() {
		return model.getState();
	}

	@Override
	public void setSelected(boolean b) {
		if (b) {
			setState(SELECTED);
		} else {
			setState(NOT_SELECTED);
		}
	}

	/**
	 * Dat trang thai cua button la SELECTED, NOT_SELECTED hoac DONT_CARE. Neu
	 * trang thai la null thi duoc gan la DONT_CARE.
	 */
	public void setState(State state) {
		model.setState(state);
	}
}
