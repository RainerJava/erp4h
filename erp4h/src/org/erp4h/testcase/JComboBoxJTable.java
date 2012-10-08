package org.erp4h.testcase;

import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class JComboBoxJTable {

	public static void main(String[] args) throws Exception {
		JFrame f = new JFrame();
		f.setLayout(null);
		JLabel lab = new JLabel("Select");
		final JComboBox combo = new JComboBox();
		combo.addItem("--Select--");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/erp4h", "root", "352007");
		final Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from tblKhoaPhong");

		while (rs.next()) {
			combo.addItem(rs.getString("khoaphongid"));
		}

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ItemSelectable is = (ItemSelectable) actionEvent.getSource();
				int ide = Integer.parseInt(selectedString(is));
				System.out.println("Selected: " + selectedString(is));
				try {
					ResultSet rs1 = st
							.executeQuery("Select * from employee where id="
									+ ide + "");
					String id = "", name = "", address = "", contactNo = "", email = "";
					while (rs1.next()) {
						id = rs1.getString(1);
						name = rs1.getString(2);
						address = rs1.getString(3);
						contactNo = rs1.getString(4);
						email = rs1.getString(5);
					}
					String data[][] = { { id, name, address, contactNo, email } };
					String columnNames[] = { "ID", "Name", "Address",
							"Contact No", "Email" };

					JTable table = new JTable(data, columnNames);
					JScrollPane scrollPane = new JScrollPane(table);
					JFrame fr = new JFrame();
					fr.add(scrollPane);
					fr.pack();
					fr.setVisible(true);
				} catch (Exception e) {
				}
			}
		};
		combo.addActionListener(actionListener);
		lab.setBounds(20, 20, 100, 20);
		combo.setBounds(120, 20, 80, 20);
		f.add(lab);
		f.add(combo);

		f.setVisible(true);
		f.setSize(300, 120);
	}

	static private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}
}
