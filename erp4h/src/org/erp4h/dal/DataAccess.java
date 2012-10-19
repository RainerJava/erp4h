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
	private static String dbDriver = "";
	private static String dbHostName = "";
	private static String dbPortNumber = "";
	private static String dbName = "";
	private static String dbUserName = "";
	private static String dbPassword = "";

	public static ConnectUtils getDataAccess() {
		return new ConnectUtils("localhost", "3306", "erp4h", "root", "352007");
	}

	public static ConnectUtils getDataAccess(String dbType){
		getDatabaseInfomation(dbType);
		return new ConnectUtils(dbType,dbHostName, dbPortNumber, dbName,
				dbUserName, dbPassword);
	}

	private static void getDatabaseInfomation(String rdbms) {
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

					// get RDBMS type
					dbType = getTagValue("dbType", eElement);
					// get Driver
					dbDriver = getTagValue("dbDriver", eElement);
					// get Host name
					dbHostName = getTagValue("dbHostName", eElement);
					// get Port number
					dbPortNumber = getTagValue("dbPortNumber", eElement);
					// get Data base name
					dbName = getTagValue("dbName", eElement);
					// get User name
					dbUserName = getTagValue("dbUserName", eElement);
					//get Password
					dbPassword = getTagValue("dbPassword", eElement);
					// get URL
					dbPassword = getTagValue("dbPassword", eElement);
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
		connect = org.erp4h.dal.DataAccess.getDataAccess("Oracle");
		ResultSet rs = connect.Select("medibv115.d_dmbd");
		while (rs.next()) {
			System.out.println(rs.getString("ten"));
		}
	}
}