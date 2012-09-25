package org.erp4h.com.bll;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.erp4h.dal.ConnectDB;


/**
 * @author HieuLV
 * @return Thông báo kêt nối đến CSDL thành công hoặc thất bại,
 * nếu kết nối thất bại sẽ tự động thoát ứng dụng sau 5s đếm ngược.
 */
public class ConnectDatabase {
	static JPanel contentPane;
	static JLabel status;
	static JFrame frame;
	public static void main(String[] args) throws Exception{
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 509, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		status = new JLabel("prepare ...");
		contentPane.add(status, BorderLayout.SOUTH);
		ConnectDB con=new ConnectDB("192.168.12.250", "erp4hUser", "352007", "erp4h");
		frame.setVisible(true);
		Thread.sleep(1000);
		for (int i = 0; i < 3; i++){
			switch(i){
			case 0:
				if(con.driverTest()){
					status.setText(con.ThongBao);
				}else{
					for(int j=3;j==0;j--){
						status.setText(con.ThongBao);
						Thread.sleep(1000);
						status.setText("Chuong trinh tu dong sau "+j+" giay ");
					}
				}
				break;
			case 1:
				if(con.getConnect()!=null){
					status.setText(con.ThongBao);
				}else{
					status.setText(con.ThongBao);
					Thread.sleep(1000);
					int j=3;
					while(j>=0){
						if(j!=0){
							status.setText("Chuong trinh tu dong sau "+j+" giay ");
							Thread.sleep(1000);
						}else{
							System.exit(0);
						}
						j--;
					}
				}
				break;
			}
			Thread.sleep(1000);
		}  
	}
	public ConnectDatabase() throws InterruptedException{
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 509, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		contentPane.add(status, BorderLayout.SOUTH);
	}
}
