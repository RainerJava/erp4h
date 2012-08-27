package Center;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import BusinessLogicLayer.MenuBLL;
import DataTranferObject.MenuDTO;

public class SystemMenu extends JFrame {
	JMenuBar mnuBar;
	JMenu mnu,mnuName;
	JMenuItem mnuItem;
	public SystemMenu() throws Exception{
		setTitle("Menu demo");
		setBounds(100, 100, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBLL bllMenu = new MenuBLL();
		ArrayList<MenuDTO> arrMenu=bllMenu.getArrayMenu(null,"MenuFiliationID,MenuID");
		
		mnuBar=new JMenuBar();
		for(int i=0;i<arrMenu.size();i++){
			MenuDTO dtoMenu=arrMenu.get(i);
			if(dtoMenu.getMenuFiliationID()==0 & dtoMenu.getPhanHeID()==1){
				mnu=new JMenu(dtoMenu.toString());
				mnuBar.add(mnu);
				System.out.println(mnu.getText());
			}
			else if(dtoMenu.getMenuFiliationID()!=0 & dtoMenu.getPhanHeID()==1){
				mnuItem=new JMenuItem(dtoMenu.getMenuValue());
				mnuBar.getMenu(dtoMenu.getMenuFiliationID()-1).add(mnuItem);
				System.out.println(mnuItem.getText());
			}
		}
		setJMenuBar(mnuBar);
	}
	
	public static void main(String[] args) throws Exception{
		SystemMenu window = new SystemMenu();
		window.setVisible(true);
	}
}
/*
//Get the selected menu path
MenuElement[] path = MenuSelectionManager.defaultManager().getSelectedPath();

if (path.length == 0) {
 // No menus are opened or menu items selected
}

//Retrieve the labels of all the menu elements in the path
for (int i=0; i<path.length; i++) {
 Component c = path[i].getComponent();

 if (c instanceof JMenuItem) {
     JMenuItem mi = (JMenuItem)c;
     String label = mi.getText();
     // Note: JMenu is a subclass of JMenuItem; also JMenuBar does not have a label
 }
}
*/