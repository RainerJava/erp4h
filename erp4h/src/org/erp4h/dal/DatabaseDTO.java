package org.erp4h.dal;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DatabaseDTO {
	private String dbType;
	private String dbDriver;
	private String dbHostName;
	private String dbPortNumber;
	private String dbName;
	private String dbUserName;
	private String dbPassword;
	private String dbUrl;

	public DatabaseDTO() {
		super();
	}

	public DatabaseDTO(String dbType) {
		getDatabaseInfomation(dbType);
	}

	public String getDbType() {
		return dbType;
	}

	private void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	private void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbHostName() {
		return dbHostName;
	}

	private void setDbHostName(String dbHostName) {
		this.dbHostName = dbHostName;
	}

	public String getDbPortNumber() {
		return dbPortNumber;
	}

	private void setDbPortNumber(String dbPortNumber) {
		this.dbPortNumber = dbPortNumber;
	}

	public String getDbName() {
		return dbName;
	}

	private void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	private void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	private void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	private void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	private void getDatabaseInfomation(String rdbms) {
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
					setDbType(getTagValue("dbType", eElement));
					// get Driver
					setDbDriver(getTagValue("dbDriver", eElement));
					// get Host name
					setDbHostName(getTagValue("dbHostName", eElement));
					// get Port number
					setDbPortNumber(getTagValue("dbPortNumber", eElement));
					// get Data base name
					setDbName(getTagValue("dbName", eElement));
					// get User name
					setDbUserName(getTagValue("dbUserName", eElement));
					// get Password
					setDbPassword(getTagValue("dbPassword", eElement));
					// get URL
					setDbUrl(createUrl(this.dbType));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

	private String createUrl(String dbType) {
		String url = "";
		switch (dbType) {
		case "MySQL":
			url = "jdbc:mysql://" + this.dbHostName + ":" + this.dbPortNumber
					+ "/" + this.dbName;
			break;
		case "Oracle":
			url = "jdbc:oracle:thin:@" + this.dbHostName + ":"
					+ this.dbPortNumber + ":" + this.dbName;
			break;
		}
		return url;
	}
}
