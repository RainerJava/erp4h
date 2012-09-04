package BusinessLogicLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataAccessLayer.MySQLConnect;
import DataAccessLayer.MySQLConnectUnit;

/**
 * @author HieuLV
 * @return Thông báo kêt nối đến CSDL thành công hoặc thất bại,
 * nếu kết nối thất bại sẽ tự động thoát ứng dụng sau 5s đếm ngược.
 */
public class ConnectDatabase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MySQLConnectUnit connect;
	static JLabel Status;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectDatabase frame = new ConnectDatabase();
					frame.setVisible(true);
					//showMessage();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ConnectDatabase(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Status=new JLabel("Prepare...");
		contentPane.add(Status,BorderLayout.SOUTH);

	}
	
	private static void showMessage() throws Exception{
		MySQLConnect con=new MySQLConnect("localhost", "roo", "352007", "erp4h");
		con.driverTest();
		Status.setText(con.ThongBao);
		Thread.sleep(4000);
		con.getConnect();
		System.out.println(con.ThongBao);
		Status.setText(con.ThongBao);
		Thread.sleep(4000);
		//con.Close();
		
	}
}
