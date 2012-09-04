package BusinessLogicLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;

import DataTranferObject.MenuDTO;


public class SystemMenu extends JMenuBar implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	private JMenu mnuElement;
	private JMenuItem mnuItem;
	private MenuBLL bllMenu;
	private ArrayList<MenuDTO> arrMenu;
	private MenuDTO dtoMenu;
	
	public SystemMenu(){
		super();
	}

	//Nho them quyen truy cap vao menu item va phan he
	public SystemMenu(String PhanHe) throws Exception{
		bllMenu=new MenuBLL();
		arrMenu=bllMenu.getMenuArray(PhanHe, "MenuFiliationID, MenuPosition");
		
		for(int i=0;i<arrMenu.size();i++){
			dtoMenu=arrMenu.get(i);
			if(dtoMenu.getMenuFiliationID()==0){
				mnuElement=new JMenu(dtoMenu.getMenuValue());
				mnuElement.setMnemonic(dtoMenu.getMnemonic().charAt(0));
				add(mnuElement);
			}else{
				mnuItem=new JMenuItem(dtoMenu.getMenuValue());
				mnuItem.setActionCommand(dtoMenu.getMenuAction());
				mnuItem.addActionListener(this);
				if(dtoMenu.getMnemonic()!=null){
					mnuItem.setMnemonic(dtoMenu.getMnemonic().charAt(0));
				}
				getMenu(dtoMenu.getMenuFiliationID()-1).add(mnuItem);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog((Component) e.getSource(), "Info");
		System.out.println(e.getSource());
		System.out.println(e.getActionCommand());
	}
}
