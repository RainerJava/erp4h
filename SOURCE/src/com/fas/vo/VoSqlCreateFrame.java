package com.fas.vo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fas.common.utils.FileUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.file.BaseFileFilter;

/**
 * @author PC12 
 * Input : File with format:   項目ID tab 項目名称 tab ﾀｲﾌﾟ 
 * 
 * Output: Print VO item to console
 */
public class VoSqlCreateFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JLabel label;
	protected JButton btnFileChooser;
	protected JTextField txtFilePath;
	protected JButton btnCreateVo;
	/** */
	private JRadioButton rdoCreateVo; 
	private JRadioButton rdoCreateSQL; 

	public VoSqlCreateFrame(String title) {
		super(title);
	}

	public Component createComponents() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(30, // top
				30, // left
				10, // bottom
				30) // right
		);

		label = new JLabel("I am waiting...");

		btnFileChooser = new JButton("Choose file follow this definition:");
		btnFileChooser.setMnemonic(KeyEvent.VK_I);
		btnFileChooser.addActionListener(new DialogFileAction(this));

		label.setLabelFor(btnFileChooser);
		pane.add(btnFileChooser);
		
		JLabel label1 = new JLabel("LCLS_CODE    大分類コード    char    2    ●    △    1");
		pane.add(label1);
		
		txtFilePath = new JTextField();
		txtFilePath.setBounds(new Rectangle(10, 40, 360, 25));
		pane.add(txtFilePath);


		ButtonGroup groupDsp = new ButtonGroup();
		rdoCreateVo = new JRadioButton("Create VO", false);
		rdoCreateVo.setSelected(true);
		rdoCreateVo.setBounds(new Rectangle(10, 50, 60, 25));
		groupDsp.add(rdoCreateVo);

		rdoCreateSQL = new JRadioButton("Create SQL", false);
		rdoCreateSQL.setBounds(new Rectangle(100, 50, 60, 25));
		groupDsp.add(rdoCreateSQL);
		pane.add(rdoCreateVo);
		pane.add(rdoCreateSQL);

		btnCreateVo = new JButton("Print to Console");
		btnCreateVo.addActionListener(new CreateVoAction(this));
		pane.add(btnCreateVo);

		pane.setLayout(new GridLayout(0, 1));
		pane.add(label);

		return pane;
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @author PC12
	 * 
	 */
	class DialogFileAction implements ActionListener {

		VoSqlCreateFrame adapter;

		DialogFileAction(VoSqlCreateFrame adapter) {
			this.adapter = adapter;
		}

		public void addPropertyChangeListener(PropertyChangeListener listener) {
		}

		public Object getValue(String key) {
			return null;
		}

		public boolean isEnabled() {
			return false;
		}

		public void putValue(String key, Object value) {
		}

		public void removePropertyChangeListener(PropertyChangeListener listener) {
		}

		public void setEnabled(boolean b) {
		}

		public void actionPerformed(ActionEvent e) {
			String[] fileName = new String[] { "txt", "csv", "dat" };
			JFileChooser fileChoser = new JFileChooser();
			fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName, "ファイル (*.txt,*.csv,*.dat)"));
			int rVal = fileChoser.showOpenDialog(VoSqlCreateFrame.this);

			if (rVal == JFileChooser.APPROVE_OPTION) {
				txtFilePath.setText(fileChoser.getCurrentDirectory().toString() + "\\" + fileChoser.getSelectedFile().getName());
			}
		}
	}

	class CreateVoAction implements ActionListener {

		VoSqlCreateFrame adapter;

		CreateVoAction(VoSqlCreateFrame adapter) {
			this.adapter = adapter;
		}

		public void addPropertyChangeListener(PropertyChangeListener listener) {
		}

		public Object getValue(String key) {
			return null;
		}

		public boolean isEnabled() {
			return false;
		}

		public void putValue(String key, Object value) {
		}

		public void removePropertyChangeListener(PropertyChangeListener listener) {
		}

		public void setEnabled(boolean b) {
		}

		public void actionPerformed(ActionEvent e) {
			String inputFile = txtFilePath.getText();

			if (FileUtils.isFile(inputFile) == false) {
				label.setText("Please choose a valid file");
				label.setForeground(Color.RED);
				return;
			}

			if (rdoCreateVo.isSelected()){
				createVoFromFile(inputFile);
			} else if(rdoCreateSQL.isSelected()){
				createSqlFromFile(inputFile);
			}
		}
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param inputFile
	 */
	private void createSqlFromFile(String inputFile){
		System.out.println("==================================================================");
		System.out.println("  Create SQL from file    " + inputFile + "    が始めます。");
		System.out.println("==================================================================");
		System.out.println();

		try {
			FileInputStream fstream = new FileInputStream(inputFile);

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			Filename fullFileName = new Filename(inputFile, '\\', '.');
			List<String> lineBYline = new ArrayList<String>();
			String[] line = {""};
			String temp = "";

			//READ file to lineBYline
			while ((strLine = br.readLine()) != null) {
				lineBYline.add(strLine);
				line = strLine.split("\t");
				if (line.length < 5) {
					label.setText("Input File Format-->  項目ID  tab  項目名称  tab  ﾀｲﾌﾟ");
					label.setForeground(Color.RED);
					return;
				}
			}

			/************************ 
			 * SELECT ALL
			 ************************/
			System.out.println("<" + fullFileName.filename() + "101>");
			System.out.println("\tSELECT");
			for (int index = 0; index < lineBYline.size(); index++){
				line = lineBYline.get(index).split("\t");

				if (line[2].equalsIgnoreCase("numeric")) {
					temp = "\t\tCOALESCE(" + line[0] + ",0) AS " + line[0] + ",";
				} else {
					temp = "\t\tRTRIM(COALESCE(" + line[0] + ",'')) AS " + line[0] + ",";
				}

				//bo dau , o cuoi cung di
				if (index == lineBYline.size() -1){
					temp = temp.substring(0, temp.length()-1);
				}

				System.out.println(temp);
			}

			//table name is the same with FileName
			System.out.println("\tFROM " + fullFileName.filename());
			System.out.println(getOrderBy(lineBYline));
			System.out.println("</" + fullFileName.filename() + "101>");

			/************************ 
			 * SELECT ONE
			 ************************/
			System.out.println("\n<" + fullFileName.filename() + "102>");
			System.out.println("\tSELECT");
			for (int index = 0; index < lineBYline.size(); index++){
				line = lineBYline.get(index).split("\t");

				if (line[2].equalsIgnoreCase("numeric")) {
					temp = "\t\tCOALESCE(" + line[0] + ",0) AS " + line[0] + ",";
				} else {
					temp = "\t\tRTRIM(COALESCE(" + line[0] + ",'')) AS " + line[0] + ",";
				}

				//bo dau , o cuoi cung di
				if (index == lineBYline.size() -1){
					temp = temp.substring(0, temp.length()-1);
				}

				System.out.println(temp);
			}

			//table name is the same with FileName
			System.out.println("\tFROM " + fullFileName.filename());
			System.out.println(getWhere(lineBYline));
			System.out.println("</" + fullFileName.filename() + "102>");

			/************************ 
			 * INSERT
			 ************************/
			temp = "";
			System.out.println("\n<" + fullFileName.filename() + "103>");
			System.out.println("\tINSERT INTO " + fullFileName.filename() + "(");

			for (int index = 0; index < lineBYline.size(); index++){
				line = lineBYline.get(index).split("\t");

				if (index == lineBYline.size() -1){
					temp = line[0];
				} else {
					temp = line[0] + ",";
				}

				System.out.println("\t\t" + temp);
			}

			System.out.println("\tVALUES(");
			for (int index = 0; index < lineBYline.size(); index++){
				if (index == lineBYline.size() -1){
					temp = "?";
				} else {
					temp = "?,";
				}
				System.out.println("\t\t" + temp);
			}
			System.out.println("\t)");
			System.out.println("</" + fullFileName.filename() + "103>");


			/************************ 
			 * UPDATE
			 ************************/
			temp = "";
			System.out.println("\n<" + fullFileName.filename() + "104>");
			System.out.println("\tUPDATE " + fullFileName.filename() + " SET");

			for (int index = 0; index < lineBYline.size(); index++){
				line = lineBYline.get(index).split("\t");

				//bo qua ADD_USER, ADD_PC, ADD_DATE, ADD_TIME
				if (StringUtils.isValid(line[0]) && line[0].indexOf("ADD_", 0)<0){
					if (index == lineBYline.size() -1){
						temp = line[0] + "= ?";
					} else {
						temp = line[0] + "= ?,";
					}
					System.out.println("\t\t" + temp);
				}

			}
			System.out.println(getWhere(lineBYline));
			System.out.println("</" + fullFileName.filename() + "104>");

			/************************ 
			 * DELETE
			 ************************/
			temp = "";
			System.out.println("\n<" + fullFileName.filename() + "105>");
			System.out.println("\tDELETE " + fullFileName.filename());
			System.out.println(getWhere(lineBYline));
			System.out.println("</" + fullFileName.filename() + "105>");

			// Close the input stream
			in.close();
		} catch (Exception e1) {// Catch exception if any
			System.err.println("Error: " + e1.getMessage());
		}

		label.setText("SQLを作成しました。");
		label.setForeground(Color.BLUE);
		System.out.println();
		System.out.println();
		System.out.println("===========================終わり==============================");
	}

	private String getWhere(List<String> lineBYline){
		String[] line = {""};
		String temp = "\tWHERE 1=1 ";

		for (int index = 0; index < lineBYline.size(); index++){
			line = lineBYline.get(index).split("\t");

			try {
				if (StringUtils.isValid(line[6])) {
					temp += " \n\t\tAND " + line[0] + " = ?";
				}
			} catch (Exception e) {
				//eat the Exception
				//e.printStackTrace();
			}
		}
		return temp;
	}
	private String getOrderBy(List<String> lineBYline){
		String[] line = {""};
		String temp = "";

		for (int index = 0; index < lineBYline.size(); index++){
			line = lineBYline.get(index).split("\t");
			try {
				if (StringUtils.isValid(line[6])) {
					temp += " \n\t\t" + line[0] + ",";
				}
			} catch (Exception e) {
				//eat the Exception
				//e.printStackTrace();
			}
		}

		if (StringUtils.isValid(temp)){
			temp = "\tORDER BY " + temp.substring(0, temp.length()-1);
		}

		return temp;
	}


	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param inputFile
	 */
	private void createVoFromFile(String inputFile){
		System.out.println("==================================================================");
		System.out.println("  Create VO from file    " + inputFile + "    始めます。");
		System.out.println("==================================================================");
		System.out.println();

		try {
			FileInputStream fstream = new FileInputStream(inputFile);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			String temp = "";
			while ((strLine = br.readLine()) != null) {
				String[] field;

				field = strLine.split("\t");
				if (field.length < 3) {
					label.setText("Input File Format-->  項目ID  tab  項目名称  tab  ﾀｲﾌﾟ");
					label.setForeground(Color.RED);
					return;
				}

				temp = "/** " + field[1] + " */";
				System.out.println(temp);

				if (field[2].equalsIgnoreCase("numeric")) {
					//xem co fai la integer or long
					if ("0".equals(field[3].substring(field[3].indexOf(",") + 1))){
						temp = "private int ";
					} else {
						temp = "private long ";
					}
					temp = temp + changeFieldName(field[0]) + " = 0;";
				} else {
					temp = "private String ";
					temp = temp + changeFieldName(field[0]) + " = \"\";";
				}
				System.out.println(temp);
				System.out.println();
			}
			// Close the input stream
			in.close();
		} catch (Exception e1) {// Catch exception if any
			System.err.println("Error: " + e1.getMessage());
		}
		label.setText("VOを作成しました。");
		label.setForeground(Color.BLUE);
		System.out.println();
		System.out.println();
		System.out.println("===========================終わり==============================");
	}


	/**
	 * @param input AAA_BBB_CCC
	 * @return aaaBbbCcc
	 */
	private String changeFieldName(String input) {
		String[] field;
		String result;
		result = input.toLowerCase();

		field = result.split("_");

		result = field[0];
		for (int i = 1; i < field.length; i++)
			result += field[i].substring(0, 1).toUpperCase() + field[i].substring(1, field[i].length());

		return result;
	}


	/**
	 * This class assumes that the string used to initialize fullPath has a
	 * directory path, filename, and extension. The methods won't work if it
	 * doesn't.
	 */
	class Filename {
		private String fullPath;
		private char pathSeparator, extensionSeparator;

		public Filename(String str, char sep, char ext) {
			fullPath = str;
			pathSeparator = sep;
			extensionSeparator = ext;
		}

		public String extension() {
			int dot = fullPath.lastIndexOf(extensionSeparator);
			return fullPath.substring(dot + 1);
		}

		public String filename() { // gets filename without extension
			int dot = fullPath.lastIndexOf(extensionSeparator);
			int sep = fullPath.lastIndexOf(pathSeparator);
			return fullPath.substring(sep + 1, dot);
		}

		public String path() {
			int sep = fullPath.lastIndexOf(pathSeparator);
			return fullPath.substring(0, sep);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}

		// Create the top-level container and add contents to it.
		VoSqlCreateFrame frame = new VoSqlCreateFrame("VO Create");
		Component contents = frame.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setVisible(true);
	}
}