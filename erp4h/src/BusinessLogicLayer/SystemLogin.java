package BusinessLogicLayer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import DataTranferObject.UserDTO;
/**
 * 
 * @author hieulv
 *
 */
public class SystemLogin extends JPanel {
	//khai bao cac thanh phan
	private JTextField tfTaiKhoan;
	private JPasswordField tfMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	//khai bao doi tuong user 
	UserDTO dtoUser;
	UserBLL bllUser;
	
	public SystemLogin() {
		setLayout(null);
		
		tfTaiKhoan = new JTextField();
		tfTaiKhoan.setBounds(354, 11, 86, 20);
		add(tfTaiKhoan);
		tfTaiKhoan.setColumns(10);
		
		tfMatKhau = new JPasswordField();
		tfMatKhau.setBounds(354, 42, 86, 20);
		add(tfMatKhau);
		
		btnDangNhap = new JButton("Login");
		btnDangNhap.setBounds(233, 266, 91, 23);
		add(btnDangNhap);
		btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnDangNhapActionPerformed(evt);
            }
        });
		
		btnThoat = new JButton("Close");
		btnThoat.setBounds(349, 266, 91, 23);
		add(btnThoat);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SystemLogin.class.getResource("/Images/stock.jpg")));
		lblNewLabel.setBounds(10, 14, 165, 275);
		add(lblNewLabel);
		
		JLabel lblTaiKhoan = new JLabel("Tai khoan");
		lblTaiKhoan.setBounds(258, 14, 86, 14);
		add(lblTaiKhoan);
		
		JLabel lblMatKhau = new JLabel("Mat khau");
		lblMatKhau.setBounds(258, 45, 86, 14);
		add(lblMatKhau);
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnThoatActionPerformed(evt);
            }
        });
	}
	private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt){
		String stTaiKhoan=tfTaiKhoan.getText();
		if(!checkEmpty(tfTaiKhoan, stTaiKhoan, "Tai khoan")){
			return;
		}
		String stMatKhau=tfMatKhau.getText();
		if(!checkEmpty(tfMatKhau, stMatKhau, "Mat khau")){
			return;
		}
		if(!checkPassword(tfMatKhau, stMatKhau, "Mat khau")){
			return;
		}
	}
	
	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt){
		System.exit(0);
	}
	
	private boolean checkEmpty(JTextField tfField, String value, String field){
		if(value.isEmpty()){
			JOptionPane.showMessageDialog(null, "Field " + field + " could not be empty", "Thong bao", JOptionPane.ERROR_MESSAGE);
			tfField.requestFocus();System.getProperties();
			return false;
		}
		return true;	
	}
	
	private boolean checkUserID(JTextField tfField, String value, String field){
		try {
			dtoUser=new UserBLL().getByID("\"" + value + "\"");
			if(dtoUser!=null){
				System.out.println(dtoUser.getUserID());
				System.out.println("aaa");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, field + " [" + value + "] " +" khong ton tai", "Thong bao", 1);
				tfField.requestFocus();
				
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, field + " [" + value + "] " +" khong ton tai", "Thong bao", 1);
			tfField.requestFocus();
			e.printStackTrace();
			return false;
		}
	}
	private boolean checkPassword(JTextField tfField, String value, String field){
		String stTaiKhoan=tfTaiKhoan.getText();
		String stMatKhau=tfMatKhau.getText();
		if(checkUserID(tfTaiKhoan, stTaiKhoan, "Tai khoan")){
			if(!dtoUser.getPassword().equals(stMatKhau)){
				JOptionPane.showMessageDialog(null, field + " khong hop le", "Thong bao", 1);
				tfField.requestFocus();
				return false;
			}
			return false;
		}
		return true;
	}
}
