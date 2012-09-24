/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：R100CreateReport.java
*
*     記述				：
*     
*     作成日			：2010/04/28
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/

package com.fas.report;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.ReportConstants;
import com.fas.common.constants.dbtable.FPrinterConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.StringUtils;
import com.fas.report.base.ReportPreview;
import com.fas.service.common.printer.PrinterService;
import com.fas.service.common.printer.PrinterServiceImpl;
import com.fas.vo.fprinter.FPrinterVo;
import com.fas.vo.report.ReportVo;


/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC14</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public abstract class CommonCreateReport {
	/** */
	/** */
	protected ReportPreview preview;
	private int numPage = 1;
	private int iTypeReport = 0;
	/** */
	protected JFrame parentFrame;
	protected String title;
	protected ReportVo dataVo;
	protected PrinterService hanbaiPrinterService = new PrinterServiceImpl();
	protected Map<String, String> paramMap = new HashMap<String, String>();
	protected String templatePath = "";
	private JasperPrint jasperPrint = null;
	
	protected abstract void initParameters();
	protected abstract void initPath();
	
	public void setNumber(int inum){
		numPage = inum;
	}
	public void initReport()
	{		
		InputStream inputStream = FileUtils.getInputStreamFromUrl(templatePath);

		//JasperReport jasperReport = null;
		try {
			//jasperReport = JasperCompileManager.compileReport(inputStream);
			jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), paramMap, dataVo);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void previewReport( int typePrint) throws BizException {
		try {
			preview = new ReportPreview(jasperPrint, typePrint, numPage);
			if(iTypeReport == 1 ) {
				preview.setParentFrame(parentFrame);
			}else if(iTypeReport == 2 ) {
				preview.setParentFrame(parentFrame, title);
			}
			preview.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {
		}
	}
	
	public void printReport(int typePrint) throws BizException {
		
		try {
			
			PrinterJob job = PrinterJob.getPrinterJob();
			/* Create an array of PrintServices */
			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
			int selectedService = 0;
			/* Scan found services to see if anyone suits our needs */
			for(int i = 0; i < services.length;i++){
				if(services[i].getName().toUpperCase().contains(ReportConstants.DEFAULT_PRINTER)){
					/*If the service is named as what we are querying we select it */
					selectedService = i;
					break;
				}
			}
			
			job.setPrintService(services[selectedService]);
			job.defaultPage().setOrientation(PageFormat.LANDSCAPE);
			PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			//MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
			MediaSizeName mediaSizeName = ApplicationConstants.mediaSizeName;
			printRequestAttributeSet.add(mediaSizeName);
			
			
			printRequestAttributeSet.add(new Copies(numPage));
			printRequestAttributeSet.add(new JobName("管製作一覧表", null));

			JRPrintServiceExporter exporter = new JRPrintServiceExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			/* We set the selected service and pass it as a paramenter */
			exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
			exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
			exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			if(typePrint == ReportConstants.PRINTER_NO_DIRECT){
				exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			}else if(typePrint == ReportConstants.PRINTER_DIRECT){
				exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
			}
			
			exporter.exportReport();	
			
			String newPrinter = "";
			newPrinter = exporter.getPrintService().getName();
			if (StringUtils.isValid(newPrinter)){
				hanbaiPrinterService.setDefaultPrinter(
						ApplicationConstants.LOGIN_USER_ID, 
						ApplicationConstants.PRINT_MENU_GRP, 
						ApplicationConstants.PRINT_MENU_EXE, 
						newPrinter);
				
				ReportConstants.DEFAULT_PRINTER = newPrinter.toUpperCase();
				// update Map
				PrinterService printerService = new PrinterServiceImpl();
				Map<String, FPrinterVo> mapPrinterVo = printerService.getMapFPrinterVo(ApplicationConstants.LOGIN_USER_ID);
				FPrinterConstants.setMapPrinterVo(mapPrinterVo);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {
			
		}	
	}
	
	public void createPdfReport(String destPath) throws BizException {	
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, destPath);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {

		}
	}	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		iTypeReport = 1;
		this.parentFrame = parentFrame;
	}


	public ReportPreview getReportPreview()
	{
		return preview;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame, String title) {
		iTypeReport = 2;
		this.parentFrame = parentFrame;
		this.title = title;
	}

}

