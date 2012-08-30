package BusinessLogicLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Center.abc;

public class MenuActionListener implements ActionListener {
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void actionPerformed(ActionEvent e) {
		System.out.println("Selected: " + e.getActionCommand());
		System.out.println("Selected: " + e.getSource());
		try {
			Class.forName("Center.abc");
			System.out.println("OK");
			Class c   = Class.forName("Center.abc"); 
			abc a = (abc)c.newInstance();
			a.show();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}
