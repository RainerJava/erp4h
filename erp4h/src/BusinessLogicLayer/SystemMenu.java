package BusinessLogicLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
//import javax.swing.JOptionPane;

import Center.SystemMenu1;
import DataTranferObject.MenuDTO;
import DataTranferObject.UserRightDTO;


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
	
	private UserRightBLL bllUserRight;
	private UserRightDTO dtoUserRight;
	
	public SystemMenu(){
		super();
	}

	//Nho them quyen truy cap vao menu item va phan he
	public SystemMenu(String PhanHe) throws Exception{
		bllMenu=new MenuBLL();
		arrMenu=bllMenu.getMenuArray(PhanHe, "MenuFiliationID, MenuPosition");
		
		bllUserRight=new UserRightBLL();
		dtoUserRight=bllUserRight.getByID(SystemParameters.PhanHe);
		System.out.println(dtoUserRight.getUserRight());
		for(int i=0;i<arrMenu.size();i++){
			dtoMenu=arrMenu.get(i);
			if(dtoMenu.getMenuFiliationID()==0){
				mnuElement=new JMenu(dtoMenu.getMenuValue());
				mnuElement.setMnemonic(dtoMenu.getMnemonic().charAt(0));
				add(mnuElement);
			}else{
				if(dtoUserRight.getUserRight().indexOf(";"+String.valueOf(dtoMenu.getMenuID())+";")!=-1){
					mnuItem=new JMenuItem(dtoMenu.getMenuValue());
					mnuItem.setActionCommand(dtoMenu.getMenuAction());
					if(dtoMenu.getMenuIcon()!=null){
						mnuItem.setIcon(new ImageIcon(SystemMenu1.class.getResource(dtoMenu.getMenuIcon())));
					}
					if(dtoMenu.getMenuHotKey()!=null){
						mnuItem.setAccelerator(KeyStroke.getKeyStroke(dtoMenu.getMenuHotKey().charAt(0),java.awt.event.InputEvent.CTRL_DOWN_MASK));
					}
					mnuItem.addActionListener(new MenuActionListener());
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
