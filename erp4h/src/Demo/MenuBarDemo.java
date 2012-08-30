package Demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBarDemo extends JMenuBar implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenu menuFile = new JMenu("File");
	JMenu menuEdit = new JMenu("Edit");
	JMenuItem itmNew = new JMenuItem("New");
	JMenuItem itmClose = new JMenuItem("Close");
	JMenuItem itmUndo = new JMenuItem("Undo");
	JMenuItem itmCut = new JMenuItem("Cut");
	
	public MenuBarDemo(){
        menuFile.add(itmNew);
        menuFile.addSeparator();
        menuFile.add(itmClose);
        menuEdit.add(itmUndo);
        menuEdit.addSeparator();
        menuEdit.add(itmCut);
        add(menuFile);
        add(menuEdit);
        
        itmNew.setActionCommand("new");
        itmNew.addActionListener(this);
        itmClose.setActionCommand("close");
        itmClose.addActionListener(this);
        itmUndo.setActionCommand("undo");
        itmUndo.addActionListener(this);
        itmCut.setActionCommand("cut");
        itmCut.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("new")) {
                JOptionPane.showMessageDialog(null,"New");
        } else if (e.getActionCommand().equals("close")) {
                JOptionPane.showMessageDialog(null,"Close");
        } else if (e.getActionCommand().equals("undo")) {
                JOptionPane.showMessageDialog(null,"Undo");
        } else if (e.getActionCommand().equals("cut")) {        
                JOptionPane.showMessageDialog(null,"Cut");
        }
	}
}
