package org.erp4h.center;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.erp4h.com.bll.MenuActionListener;
import org.erp4h.com.bll.MenuBLL;
import org.erp4h.dto.MenuDTO;



public class SystemMenu1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) throws Exception{
		SystemMenu1 window = new SystemMenu1();
		window.setVisible(true);
	}
	JMenu mnu,mnuName;
	JMenuBar mnuBar;
	JMenuItem mnuItem;
	
	public SystemMenu1() throws Exception{
		setName("abc");
		setTitle("Menu demo");
		setBounds(100, 100, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(this.getName());
		MenuBLL bllMenu = new MenuBLL();
		ArrayList<MenuDTO> arrMenu=bllMenu.getMenuArray(null,"MenuFiliationID,MenuPosition");
		
		mnuBar=new JMenuBar();
		for(int i=0;i<arrMenu.size();i++){
			MenuDTO dtoMenu=arrMenu.get(i);
			if(dtoMenu.getMenuFiliationID()==0 & dtoMenu.getPhanHeID()==1){
				mnu=new JMenu(dtoMenu.toString());
				mnu.setMnemonic(dtoMenu.getMnemonic().charAt(0));
				mnuBar.add(mnu);
				//System.out.println(mnu.getText());
			}
			else if(dtoMenu.getMenuFiliationID()!=0 & dtoMenu.getPhanHeID()==1){
				if(dtoMenu.getMenuIcon()!=null){
					mnuItem=new JMenuItem(dtoMenu.getMenuValue());
					mnuItem.setIcon(new ImageIcon(SystemMenu1.class.getResource(dtoMenu.getMenuIcon())));
					mnuItem.addActionListener(new MenuActionListener());
					System.out.println(dtoMenu.getMenuHotKey());
				}else{
					mnuItem=new JMenuItem(dtoMenu.getMenuValue());
					mnuItem.addActionListener(new MenuActionListener());
					System.out.println(dtoMenu.getMenuHotKey());
				}
				if(dtoMenu.getMenuHotKey()!=null){
					mnuItem.setAccelerator(KeyStroke.getKeyStroke(dtoMenu.getMenuHotKey().charAt(0),java.awt.event.InputEvent.CTRL_DOWN_MASK));
				}else{
					
				}
				mnuBar.getMenu(dtoMenu.getMenuFiliationID()-1).add(mnuItem);
				//System.out.println(mnuItem.getText());
			}
		}
		
			//System.out.println(this.mnuBar.getTopLevelAncestor().getName());
		Container ct=null;
		setJMenuBar(mnuBar);
		if(mnuBar.getTopLevelAncestor() instanceof JFrame){
			ct=mnuBar.getTopLevelAncestor();
			System.out.println(ct.getName());
			System.out.println(ct.getClass());
			System.out.println(ct.getComponentCount());
		}
		for(int i=0;i<this.getComponentCount();i++){
			System.out.println(this.getComponent(i).getClass());
			System.out.println(this.getComponent(i).getName());
		}
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