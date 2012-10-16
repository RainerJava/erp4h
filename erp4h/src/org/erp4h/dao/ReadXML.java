package org.erp4h.dao;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {

	public static void main(String argv[]) {

		try {

			File fXmlFile = new File(System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "XML"
					+ System.getProperty("file.separator") + "dbtypes.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("MySQL");
			System.out.println("-----------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("First Name : "
							+ getTagValue("dbName", eElement));
					System.out.println("Last Name : "
							+ getTagValue("dbDriver", eElement));
					System.out.println("Nick Name : "
							+ getTagValue("dbUrl", eElement));
					System.out.println("Salary : "
							+ getTagValue("dbHostName", eElement));

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

}
