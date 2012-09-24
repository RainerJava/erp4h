package com.fas.jface.text;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.fas.jface.button.ActionButton;

public class LinkButton extends ActionButton
{
    /** */
	private static final long serialVersionUID = 1L;
	
	String text;
    public LinkButton (final String text)
    {
        super (text);
        setText("<html><b style='color: red'>"+ text + "</b></html>");
        
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setBackground(Color.lightGray);
      
        addMouseListener (new MouseAdapter ()
        {
            public void mouseEntered (MouseEvent e){
                setText ("<html><b style='color: red'><u>"+ text + "</u></b></html>");
            }
            public void mouseExited (MouseEvent e){
            	setText ("<html><b style='color: red'>"+ text + "</b></html>");
            }
        });
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};

		addMouseListener(mouseAdapter);
    } 
}
