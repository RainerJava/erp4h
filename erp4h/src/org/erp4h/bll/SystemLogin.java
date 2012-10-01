package org.erp4h.bll;

import java.awt.Color;

import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.erp4h.common.SystemParameters;
import org.erp4h.system.dto.UserDTO;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import org.erp4h.common.utils.DatePicker;
import org.erp4h.common.utils.XTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

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
	//
	Border grayline;
	private JLabel lblDate;
	private DatePicker datePicker;
	public SystemLogin() throws Exception {
		this.setBounds(0, 0, 500, 280);
		grayline = BorderFactory.createLineBorder(Color.gray);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 250, 110, 100 };
		gridBagLayout.rowHeights = new int[] { 20, 25, 20, 25, 20, 25, 0, 90, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0 };
		setLayout(gridBagLayout);

		JLabel lblTaiKhoan = new JLabel("Tài khoản");
		GridBagConstraints gbc_lblTaiKhoan = new GridBagConstraints();
		gbc_lblTaiKhoan.anchor = GridBagConstraints.SOUTH;
		gbc_lblTaiKhoan.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblTaiKhoan.insets = new Insets(0, 0, 5, 5);
		gbc_lblTaiKhoan.gridx = 1;
		gbc_lblTaiKhoan.gridy = 0;
		add(lblTaiKhoan, gbc_lblTaiKhoan);

		tfTaiKhoan = new JTextField();
		tfTaiKhoan.setBorder(grayline);
		GridBagConstraints gbc_tfTaiKhoan = new GridBagConstraints();
		gbc_tfTaiKhoan.fill = GridBagConstraints.BOTH;
		gbc_tfTaiKhoan.insets = new Insets(0, 0, 5, 0);
		gbc_tfTaiKhoan.gridwidth = 2;
		gbc_tfTaiKhoan.gridx = 1;
		gbc_tfTaiKhoan.gridy = 1;
		add(tfTaiKhoan, gbc_tfTaiKhoan);
		tfTaiKhoan.setColumns(10);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		GridBagConstraints gbc_lblMatKhau = new GridBagConstraints();
		gbc_lblMatKhau.anchor = GridBagConstraints.SOUTH;
		gbc_lblMatKhau.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblMatKhau.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatKhau.gridx = 1;
		gbc_lblMatKhau.gridy = 2;
		add(lblMatKhau, gbc_lblMatKhau);

		tfMatKhau = new JPasswordField();
		tfMatKhau.setBorder(grayline);
		GridBagConstraints gbc_tfMatKhau = new GridBagConstraints();
		gbc_tfMatKhau.fill = GridBagConstraints.BOTH;
		gbc_tfMatKhau.insets = new Insets(0, 0, 5, 0);
		gbc_tfMatKhau.gridwidth = 2;
		gbc_tfMatKhau.gridx = 1;
		gbc_tfMatKhau.gridy = 3;
		add(tfMatKhau, gbc_tfMatKhau);

		lblDate = new JLabel("Ngày");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDate.anchor = GridBagConstraints.SOUTH;
//		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 4;
		add(lblDate, gbc_lblDate);

		datePicker = new DatePicker();
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.anchor = GridBagConstraints.NORTH;
		gbc_datePicker.gridwidth = 2;
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.gridx = 1;
		gbc_datePicker.gridy = 5;
		add(datePicker, gbc_datePicker);
		
		btnDangNhap = new JButton("Đăng nhập");
		GridBagConstraints gbc_btnDangNhap = new GridBagConstraints();
		gbc_btnDangNhap.anchor = GridBagConstraints.NORTH;
		gbc_btnDangNhap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDangNhap.insets = new Insets(0, 0, 0, 5);
		gbc_btnDangNhap.gridx = 1;
		gbc_btnDangNhap.gridy = 8;
		add(btnDangNhap, gbc_btnDangNhap);
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

		btnThoat = new JButton("Thoát");
		GridBagConstraints gbc_btnThoat = new GridBagConstraints();
		gbc_btnThoat.anchor = GridBagConstraints.NORTH;
		gbc_btnThoat.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnThoat.gridx = 2;
		gbc_btnThoat.gridy = 8;
		add(btnThoat, gbc_btnThoat);

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
			dtoUser = new UserBLL().getByID("'" + value + "'");
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

		ArrayList<Integer> arrGroup = new GroupUserBLL().getGroup("UserID=\""
				+ dtoUser.getUserID() + "\"", null);
		for (int i = 0; i < arrGroup.size(); i++) {
			ArrayList<String> arr = new GroupRightBLL().getRight("GroupID="
					+ arrGroup.get(i), null);
			System.out.println("arr: " + arr);
		}
	}
}