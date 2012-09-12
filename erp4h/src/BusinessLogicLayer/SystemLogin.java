package BusinessLogicLayer;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataTranferObject.UserDTO;

/**
 * 
 * @author hieulv
 * 
 */
public class SystemLogin extends JPanel {
	// khai bao cac thanh phan
	private JTextField tfTaiKhoan;
	private JPasswordField tfMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	// khai bao doi tuong user
	private UserDTO dtoUser;
	private UserBLL bllUser;

	public SystemLogin() {
		setLayout(null);

		tfTaiKhoan = new JTextField();
		tfTaiKhoan.setBounds(320, 11, 120, 20);
		add(tfTaiKhoan);
		tfTaiKhoan.setColumns(10);

		tfMatKhau = new JPasswordField();
		tfMatKhau.setBounds(320, 34, 120, 20);
		add(tfMatKhau);

		btnDangNhap = new JButton("Login");
		btnDangNhap.setBounds(258, 266, 91, 23);
		add(btnDangNhap);
		btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnDangNhapActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnThoat = new JButton("Close");
		btnThoat.setBounds(349, 266, 91, 23);
		add(btnThoat);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SystemLogin.class
				.getResource("/Images/stock.jpg")));
		lblNewLabel.setBounds(10, 14, 165, 275);
		add(lblNewLabel);

		JLabel lblTaiKhoan = new JLabel("Tai khoan");
		lblTaiKhoan.setBounds(224, 14, 86, 14);
		add(lblTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mat khau");
		lblMatKhau.setBounds(224, 37, 86, 14);
		add(lblMatKhau);
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThoatActionPerformed(evt);
			}
		});
	}

	private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt)
			throws Exception {
		String stTaiKhoan = tfTaiKhoan.getText();
		if (!checkEmpty(tfTaiKhoan, stTaiKhoan, "Tai khoan")) {
			return;
		}
		String stMatKhau = tfMatKhau.getText();
		if (!checkEmpty(tfMatKhau, stMatKhau, "Mat khau")) {
			return;
		}
		if (!checkPassword(tfMatKhau, stMatKhau, "Mat khau")) {
			return;
		}
		createUserRight();
		System.out.println(SystemParameters.CURRENT_GROUP_RIGHT);
	}

	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println(SystemParameters.CURRENT_USER.getUserName());
		System.exit(0);
	}

	private boolean checkEmpty(JTextField tfField, String value, String field) {
		if (value.isEmpty()) {
			JOptionPane.showMessageDialog(null, field + " khong duoc rong",
					"Thong bao", 1);
			tfField.requestFocus();
			return false;
		}
		return true;
	}

	private boolean checkUserID(JTextField tfField, String value, String field) {
		try {
			dtoUser = new UserBLL().getByID("\"" + value + "\"");
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, field + " [" + value + "] "
					+ "khong ton tai", "Thong bao", 1);
			tfField.requestFocus();
			return false;
		}
	}

	private boolean checkPassword(JTextField tfField, String value, String field) {
		String stTaiKhoan = tfTaiKhoan.getText();
		String stMatKhau = tfMatKhau.getText();

		if (checkUserID(tfTaiKhoan, stTaiKhoan, "Tai khoan")) {
			if (dtoUser.getPassword().compareTo(stMatKhau) != 0) {
				JOptionPane.showMessageDialog(null, field + " khong hop le",
						"Thong bao", 1);
				tfField.requestFocus();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	private void createUserRight() throws Exception {
		SystemParameters.CURRENT_GROUP_RIGHT = new GroupRightBLL().getRight(
				"PhanHeID=1 and GroupID=" + dtoUser.getGroupID(), null);
	}
}