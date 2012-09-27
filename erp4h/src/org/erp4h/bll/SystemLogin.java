package org.erp4h.bll;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.erp4h.common.SystemParameters;
import org.erp4h.system.dto.UserDTO;


/**
 * 
 * @author hieulv
 * 
 */
public class SystemLogin extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6752779567034686972L;
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
			@Override
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

		JLabel lblTaiKhoan = new JLabel("Tài khoản");
		lblTaiKhoan.setBounds(224, 14, 86, 14);
		add(lblTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setBounds(224, 37, 86, 14);
		add(lblMatKhau);
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThoatActionPerformed(evt);
			}
		});
	}

	private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt)
			throws Exception {
		String stTaiKhoan = tfTaiKhoan.getText();
		if (!checkEmpty(tfTaiKhoan, stTaiKhoan, "Tài khoản")) {
			return;
		}
		String stMatKhau = tfMatKhau.getText();
		if (!checkEmpty(tfMatKhau, stMatKhau, "Mật khẩu")) {
			return;
		}
		if (!checkPassword(tfMatKhau, stMatKhau, "Mật khẩu")) {
			return;
		}
		createUserRight();
		System.out.println(SystemParameters.CURRENT_GROUP_RIGHT);
		System.out.println(SystemParameters.CURRENT_USER_RIGHT);
	}

	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println(SystemParameters.CURRENT_USER.getUserName());

		System.exit(0);
	}

	private boolean checkEmpty(JTextField tfField, String value, String field) {
		if (value.isEmpty()) {
			JOptionPane.showMessageDialog(null, field + " khong duoc rong",
					"Thông báo", 1);
			tfField.requestFocus();
			return false;
		}
		return true;
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

	private boolean checkUserID(JTextField tfField, String value, String field) {
		try {
			dtoUser = new UserBLL().getByID("\"" + value + "\"");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n"
					+ this.getClass().getName() + "[checkUserID] " + field
					+ " [" + value + "] " + "không tồn tại.", "Thông báo", 1);
			tfField.requestFocus();
			return false;
		}

	}

	private void createUserRight() throws Exception {

		SystemParameters.CURRENT_USER = dtoUser;
		System.out.println(SystemParameters.CURRENT_USER.getUserName());
		SystemParameters.CURRENT_USER_RIGHT = new UserRightBLL().getRight(
				"PhanHeID=1 and UserID=" + "\"" + tfTaiKhoan.getText() + "\"",
				null);
		HashSet<Integer> hs = new HashSet<Integer>();
		ArrayList<Integer> arrGroup = new GroupUserBLL().getGroup("UserID=\""
				+ dtoUser.getUserID() + "\"", null);
		for (int i = 0; i < arrGroup.size(); i++) {
			ArrayList<String> arr = new GroupRightBLL().getRight("GroupID="
					+ arrGroup.get(i), null);
			System.out.println("arr: " + arr);
		}
		
	}
}