package BusinessLogicLayer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import DataTranferObject.UserDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SystemLogin11 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Khai bao cac thanh phan tren frame
	private JTextField tfTaiKhoan;
	private JPasswordField tfMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	//Khai bao bien
	private UserDTO dtoUser;

	public SystemLogin() {
		setLayout(null);
		
		tfTaiKhoan = new JTextField();
		tfTaiKhoan.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtTaiKhoan.getText()!=null){
					System.out.println("khlkj");
					txtTaiKhoan.setFocusable(true);
				}
			}
		});
		tfTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getSource().toString());
			}
		});
		tfTaiKhoan.setBounds(375, 11, 115, 20);
		add(tfTaiKhoan);
		tfTaiKhoan.setColumns(10);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(375, 35, 115, 20);
		add(txtMatKhau);
		
		JButton btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		
		btnDangNhap.addActionListener(new ActionListener() {
			int i=0;
			public void actionPerformed(ActionEvent e) {
				try {
					dtoUser= new UserBLL().getByID("\""+tfTaiKhoan.getText()+"\"");
					//txtMatKhau.getText();
					if(dtoUser.getPassword().equals(new String(txtMatKhau.getPassword()))){
						System.out.println("OK");
					}else{
						System.out.println("Sai mat khau"+txtMatKhau.getPassword().toString());
						txtMatKhau.requestFocus(true);
					}
					
					System.out.println(dtoUser.getUserName());
				} catch (Exception e1) {
					i++;
					if(i>4){
						//thong bao nhap sai qua so lan quy dinh va thoat chuong trinh
						System.exit(0);
					}
					System.out.println("Khong hop le...");
					//e1.printStackTrace();
				}
			}
		});
		btnDangNhap.setBounds(399, 266, 91, 23);
		add(btnDangNhap);
		
		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnThoat.setBounds(298, 266, 91, 23);
		add(btnThoat);

	}
	private boolean checkEmpty(JTextField tfField, String value, String field){
		if(value.isEmpty()){
			JOptionPane.showMessageDialog(null, "Field " + field + "could not be empty", "Thong bao", JOptionPane.ERROR_MESSAGE);
			tfField.requestFocus();
		}
		return true;
	}
	private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt){
		String stTaiKhoan=tfTaiKhoan.getText();
		if(!checkEmpty(tfTaiKhoan, stTaiKhoan, "Tai khoan")){
			return;
		}
		String stMatKhau=tfMatKhau.getPassword().toString();
		if(!checkEmpty(tfMatKhau, stMatKhau, "Mat Khau")){
			return;
		}
	}
}
