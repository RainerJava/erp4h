/**
 * 
 */
package com.fas.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fas.common.DBConnectionManager;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.exception.BizException;
import com.fas.vo.holiday.HolidayVo;

/**
 * @author PC13
 *
 */
public class XMLUtils {

	/**
	 * 
	 */
	public XMLUtils() {
	}

	/**
	 * @param xmlFilePath
	 * @return
	 */
	protected static Document parseXmlFile(String xmlFilePath){
		
		//ファクトリを取得する。
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;
		
		try {
			// ファクトリを使ってビルダを作ります。
			DocumentBuilder db = dbf.newDocumentBuilder();
			// XMLファイルをパーザーする。
			dom = db.parse(xmlFilePath);
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(SAXException se) {
			se.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return dom;
	}	

	/**
	 * @param strConfigFilePath
	 * @throws BizException
	 */
	public static void initDatabaseConnectInfor(String configFilePath) throws BizException {
		
		Document dom = parseXmlFile(configFilePath);
		
		if (dom == null) {
			throw new BizException("XMLパーサーのエラーが発生しました。");
		} else {
			
			Element elDoc = dom.getDocumentElement();
			NodeList nl = elDoc.getElementsByTagName("DbConnect");
			
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					Element el = (Element)nl.item(i);
					ApplicationConstants.DB_USER = getTextValue(el, "User");
					ApplicationConstants.DB_PASSWORD = getTextValue(el, "Pass");
					ApplicationConstants.DB_URL = getTextValue(el, "JavaConnectURL");
				}
			}
			
			try {
				new DBConnectionManager();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
		}
	}
	
	/**
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	/**
	 * @param xmlFileName
	 * @param sqlTag
	 * @return
	 * @throws BizException
	 */
	public static String getSQLFromXML(String xmlFileName, String sqlTag) throws BizException {
		
		Document dom = parseXmlFile(ApplicationConstants.XML_SQL_PATH + xmlFileName);
		String strSQL = "";
		
		if (dom == null) {
			throw new BizException("XMLパーサーのエラーが発生しました。");
		} else {

			NodeList nl = dom.getElementsByTagName("System");

			if(nl != null && nl.getLength() > 0) {
				strSQL = getTextValue((Element)nl.item(0), sqlTag);
			}
		}
		
		return strSQL;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param holidayFilePath
	 * @return
	 * @throws BizException
	 */
	public static List<HolidayVo> getLstHoliday(String holidayFilePath) throws BizException {
		
		List<HolidayVo> lstHoliday = new ArrayList<HolidayVo>();
		Document dom = parseXmlFile(holidayFilePath);
		
		if (dom == null) {
			throw new BizException("XMLパーサーのエラーが発生しました。");
		} else {

			Element elDoc = dom.getDocumentElement();
			NodeList forceHoliday = elDoc.getElementsByTagName("ForceHoliday");
			
			String strName = "";
			String strStartDate = "";
			String strEndDate = "";
			int iStartDate = 0;
			int iEndDate = 0;
			HolidayVo holidayVo = null;

			if (forceHoliday != null && forceHoliday.getLength() > 0) {
				for(int i = 0 ; i < forceHoliday.getLength();i++) {
					Element el = (Element) forceHoliday.item(i);
					strName = StringUtils.emptyIfNull(el.getAttribute("Name"));
					strStartDate = StringUtils.emptyIfNull(el.getAttribute("StartDate"));
					strEndDate = StringUtils.emptyIfNull(el.getAttribute("EndDate"));
					if (strStartDate.length() > 9 && strEndDate.length() > 9) {
						strStartDate = strStartDate.substring(0, 10).replaceAll("-", "");
						strEndDate = strEndDate.substring(0, 10).replaceAll("-", "");
						iStartDate = NumberUtils.getIntFromString(strStartDate);
						iEndDate = NumberUtils.getIntFromString(strEndDate);
						for (int d = iStartDate; d <= iEndDate; d++) {
							holidayVo = new HolidayVo();
							holidayVo.setShujitsumei(strName);
							holidayVo.setShujitsubi(d + "");
							lstHoliday.add(holidayVo);
						}
					}
				}
			}
			
			NodeList dayOfW = elDoc.getElementsByTagName("DayOfWeekHoliday");
			Map<String, String> mapMonth = new HashMap<String, String>();
			mapMonth.put("January", "01");
			mapMonth.put("February", "02");
			mapMonth.put("March", "03");
			mapMonth.put("April", "04");
			mapMonth.put("May", "05");
			mapMonth.put("June", "06");
			mapMonth.put("July", "07");
			mapMonth.put("August", "08");
			mapMonth.put("September", "09");
			mapMonth.put("October", "10");
			mapMonth.put("November", "11");
			mapMonth.put("December", "12");
			
			Map<String, String> mapDay = new HashMap<String, String>();
			mapDay.put("Sunday", "日");
			mapDay.put("Monday", "月");
			mapDay.put("Tuesday", "火");
			mapDay.put("Wednesday", "水");
			mapDay.put("Thursday", "木");
			mapDay.put("Friday", "金");
			mapDay.put("Saturday", "土");

			if (dayOfW != null && dayOfW.getLength() > 0) {
				
				String strMonth = "";
				String strDayOfW = "";
				String strDayInWInMonth = "";
				String strDate = "";
				
				for (int i = 0; i < dayOfW.getLength(); i++ ) {
					
					Element el = (Element) dayOfW.item(i);
					strName = StringUtils.emptyIfNull(el.getAttribute("Name"));
					strMonth = StringUtils.emptyIfNull(mapMonth.get(el.getAttribute("Month")));
					strDayOfW = StringUtils.emptyIfNull(mapDay.get(el.getAttribute("DayOfWeek")));
					strDayInWInMonth = StringUtils.emptyIfNull(el.getAttribute("DayOfWeekInMonth"));
					
					if (!strMonth.equalsIgnoreCase("") && !strDayOfW.equalsIgnoreCase("")) {
							for (int y = 1900; y < 2099; y++) {
								strDate = y + "";
								int iStartDay = 1;
								int iLoop = 1;
		
								if ("First".equalsIgnoreCase(strDayInWInMonth)) {
									while (iStartDay < 31) {
										strDate = y + "/" + strMonth + "/" + StringUtils.fillNumberMaxLen(iStartDay + "", 2);
										if (strDayOfW.equalsIgnoreCase(DateUtils.getDayOfWeek(strDate))) {
											holidayVo = new HolidayVo();
											holidayVo.setShujitsubi(strDate.replaceAll("/", ""));
											holidayVo.setShujitsumei(strName);
											lstHoliday.add(holidayVo);
											break;
										}
										iStartDay++;
									}
									
								} else if ("Second".equalsIgnoreCase(strDayInWInMonth)) {
									while (iStartDay < 31) {
										strDate = y + "/" + strMonth + "/" + StringUtils.fillNumberMaxLen(iStartDay + "", 2);
										if (strDayOfW.equalsIgnoreCase(DateUtils.getDayOfWeek(strDate))) {
											if (iLoop == 2) {
												holidayVo = new HolidayVo();
												holidayVo.setShujitsubi(strDate.replaceAll("/", ""));
												holidayVo.setShujitsumei(strName);
												lstHoliday.add(holidayVo);
												break;
											} else {
												iLoop++;
											}
										}
										iStartDay++;
									}
								} else if ("Third".equalsIgnoreCase(strDayInWInMonth)) {
									while (iStartDay < 31) {
										strDate = y + "/" + strMonth + "/" + StringUtils.fillNumberMaxLen(iStartDay + "", 2);
										if (strDayOfW.equalsIgnoreCase(DateUtils.getDayOfWeek(strDate))) {
											if (iLoop == 3) {
												holidayVo = new HolidayVo();
												holidayVo.setShujitsubi(strDate.replaceAll("/", ""));
												holidayVo.setShujitsumei(strName);
												lstHoliday.add(holidayVo);
												break;
											} else {
												iLoop++;
											}
										}
										iStartDay++;
									}
								} else {
									while (iStartDay < 31) {
										strDate = y + "/" + strMonth + "/" + StringUtils.fillNumberMaxLen(iStartDay + "", 2);
										if (strDayOfW.equalsIgnoreCase(DateUtils.getDayOfWeek(strDate))) {
											if (iLoop == 3) {
												holidayVo = new HolidayVo();
												holidayVo.setShujitsubi(strDate.replaceAll("/", ""));
												holidayVo.setShujitsumei(strName);
												lstHoliday.add(holidayVo);
												break;
											} else {
												iLoop++;
											}
										}
										iStartDay++;
									}
								}
							}
					}
					
				}
			}
			
			
			NodeList holiday = elDoc.getElementsByTagName("Holiday");
			String strDate = "";
			
			if (holiday != null && forceHoliday.getLength() > 0) {
				for(int i = 0 ; i < holiday.getLength();i++) {
					Element el = (Element) holiday.item(i);
					strName = StringUtils.emptyIfNull(el.getAttribute("Name"));
					strStartDate = StringUtils.emptyIfNull(el.getAttribute("StartDate"));
					strEndDate = StringUtils.emptyIfNull(el.getAttribute("EndDate"));
					if (strStartDate.length() > 9 && strEndDate.length() > 9) {
						strStartDate = strStartDate.substring(0, 10).replaceAll("-", "");
						strEndDate = strEndDate.substring(0, 10).replaceAll("-", "");
						iStartDate = NumberUtils.getIntFromString(strStartDate);
						iEndDate = NumberUtils.getIntFromString(strEndDate);
						if (iStartDate > 1900000) {
							for (int d = iStartDate; d <= iEndDate; d++) {
								strDate = d + "";
								strDate = strDate.substring(4, strDate.length());
								for (int yy = 1900; yy < 2099; yy++) {
									holidayVo = new HolidayVo();
									holidayVo.setShujitsumei(strName);
									holidayVo.setShujitsubi(yy + strDate);
									lstHoliday.add(holidayVo);
								}
							}
						}
					}
				}
			}
		}
		
		return lstHoliday;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//initDatabaseConnectInfor("file:\\C:\\HY\\実行環境\\Config\\Config.xml");
			//System.out.println(getSQLFromXML("F_LOGIN.xml","F_LOGIN001"));
			getLstHoliday("file:\\C:\\HY\\実行環境\\Bin\\Holiday.xml");
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

}
