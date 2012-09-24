package com.fas.jface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class EmptyLeftBorder extends AbstractBorder {

	private static final long serialVersionUID = -1240749032172287908L;

	protected int thickness;
	protected Color lineColor;
	protected Insets insets = new Insets(1, 1, 1, 1);

	public EmptyLeftBorder(Color color) {
		this(color, 1, new Insets(1, 1, 1, 1));
	}

	public EmptyLeftBorder(Color color, int thickness) {
		this(color, thickness, new Insets(1, 1, 1, 1));
	}

	public EmptyLeftBorder(Color color, int thickness, Insets insets) {
		this.lineColor = color;
		this.thickness = thickness;
		this.insets = insets;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Color oldColor = g.getColor();
		g.setColor(lineColor);
		for (int i = 0; i < thickness; i++) {
			// bottom border
			g.drawLine(x, y + height - i - 1, x + width, y + height - i - 1);
			// right border
			g.drawLine(x + width - i - 1, y, x + width - i - 1, y + height);
			// top border
			g.drawLine(x, y + i, x + width, y + i);
		}
		g.setColor(oldColor);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		return this.insets;
	}

	/**
	 * Returns the color of the border.
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * Returns the thickness of the border.
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * Returns whether or not the border is opaque.
	 */
	@Override
	public boolean isBorderOpaque() {
		return false;
	}

}
