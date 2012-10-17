package org.erp4h.dal;

import java.io.File;
import java.sql.ResultSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.erp4h.dal.ConnectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataAccess {
	private static String dbType = "";
	private static String driver = "";
	private static String host = "";
	private static String port = "";
	private static String dbname = "";
	private static String user = "";
	private static String password = "";

	public static ConnectUtils getDataAccess() {
		return new ConnectUtils("localhost", "erp4h", "3306", "root", "352007");
	}

	public static ConnectUtils getDataAccess(String dbType) {
		return new ConnectUtils(dbType, host, user, password, dbname);
	}

	private static void getInfomationDatabase(String rdbms) {
		dbType = rdbms;
		try {
			File fXmlFile = new File(System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "XML"
					+ System.getProperty("file.separator") + "dbtypes.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element: "
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName(rdbms);
			System.out.println("---------------------------");
			System.out.println(fXmlFile.getPath());
			System.out.println(nList.getLength());
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("RDBMS: "
							+ getTagValue("dbType", eElement));

					System.out.println("Driver: "
							+ getTagValue("dbDriver", eElement));
					driver = getTagValue("dbDriver", eElement);

					System.out
							.println("URL: " + getTagValue("dbUrl", eElement));

					System.out.println("Host name: "
							+ getTagValue("dbHost", eElement));
					host = getTagValue("dbHost", eElement);

					System.out.println("Port number: "
							+ getTagValue("dbPort", eElement));
					host = getTagValue("dbPort", eElement);

					System.out.println("User name: "
							+ getTagValue("dbUser", eElement));
					user = getTagValue("dbUser", eElement);

					System.out.println("Password: "
							+ getTagValue("dbPassword", eElement));
					password = getTagValue("dbPassword", eElement);

					System.out.println("Data base name: "
							+ getTagValue("dbName", eElement));
					dbname = getTagValue("dbName", eElement);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	static ConnectUtils connect;

	public static void main(String[] args) throws Exception {
		getInfomationDatabase("Oracle");

		connect = org.erp4h.dal.DataAccess.getDataAccess();
		ResultSet rs = connect.Select("tblUser");
		while (rs.next()) {
			System.out.println(rs.getString("UserID"));
		}
	}
}