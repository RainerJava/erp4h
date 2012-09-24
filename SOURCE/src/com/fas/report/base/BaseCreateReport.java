/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseCreateReport.java
*
*     記述			：
*     
*     作成日			：2010/05/01
*
*     作成者			：PC12
*
*     備考			：
*
**************************************************************************************/
package com.fas.report.base;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.ReportConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.StringUtils;
import com.fas.service.common.printer.PrinterService;
import com.fas.service.common.printer.PrinterServiceImpl;
import com.fas.vo.base.BaseVo;
import com.fas.vo.base.ReportBaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC12</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public abstract class BaseCreateReport {
	
	/** */
	protected JFrame parentFrame;
	/** */
	protected PrinterService hanbaiPrinterService;
	/** */
	protected ReportPreview preview;	
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BaseCreateReport() {
		hanbaiPrinterService = new PrinterServiceImpl();
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param paramVo
	 * @param dataSource
	 * @throws BizException
	 */
	public abstract void previewReport(BaseVo paramVo, BaseVo dataSource) throws BizException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param paramVo
	 * @param dataSource
	 * @throws BizException
	 */
	public abstract void previewReport(BaseVo paramVo, BaseVo dataSource,int iTyppe) throws BizException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param paramVo
	 * @param dataSource
	 * @throws BizException
	 */
	public abstract void printReport(BaseVo paramVo, BaseVo dataSource,int iTyppe) throws BizException;
	
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
		this.parentFrame = parentFrame;
	}

	/* (non-Javadoc)
	 * @see com.inspect.report.BaseCreateReport#previewReport(com.inspect.vo.report.R501ParamVo, com.inspect.vo.report.R501ReportVo)
	 */
	public void previewReport( String templatePath, ReportBaseVo dataSource, Map<String, String> paramMap, int typePrint) throws BizException {
		
		try {
			//REPORT PATH
			InputStream inputStream = FileUtils.getInputStreamFromUrl(templatePath);
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
			
			//PRINT PREVIEW
			preview = new ReportPreview(jasperPrint, typePrint, 1);
			preview.setParentFrame(parentFrame);
			preview.setVisible(true);
			
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {

		}
	}
	
	/**
	 * @param templatePath
	 * @param dataSource
	 * @param paramMap
	 * @param typePrint
	 * @throws BizException
	 */
	public void printReport( String templatePath, ReportBaseVo dataSource, Map<String, String> paramMap, int typePrint) throws BizException {
		
		try {			
			//REPORT PATH
			InputStream inputStream = FileUtils.getInputStreamFromUrl(templatePath);
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
			
			//PRINT REPORT
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
			printRequestAttributeSet.add(new Copies(1));
			printRequestAttributeSet.add(new JobName("品質規格表", null));

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
						ApplicationConstants.MENU_GRP, 
						ApplicationConstants.MENU_EXE, 
						newPrinter);
				
				ReportConstants.DEFAULT_PRINTER = newPrinter.toUpperCase();
			}
			
			if (inputStream != null) {
				inputStream.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {
			
		}	
	}
	
	/**
	 * @param templatePath
	 * @param dataSource
	 * @param paramMap
	 * @param typePrint
	 * @param destPath
	 * @throws BizException
	 */
	public void createExelReport( String templatePath, ReportBaseVo dataSource, Map<String, String> paramMap, int typePrint, String destPath) throws BizException {
		
		try {
			//REPORT PATH
			InputStream inputStream = FileUtils.getInputStreamFromUrl(templatePath);
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
						
			JasperPrint print = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
			
			//EXPORT EXCEL
			JRXlsExporter exporterXLS = new JRXlsExporter();
			FileOutputStream output = new FileOutputStream(new File(destPath));
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print); 
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output); 
			exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
			exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE); 
			exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE); 
			exporterXLS.exportReport();  
			
			if (inputStream != null) {
				inputStream.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {
		}	
	}
		
	/**
	 * @param templatePath
	 * @param dataSource
	 * @param paramMap
	 * @param typePrint
	 * @param destPath
	 * @throws BizException
	 */
	public void createPdfReport501( String templatePath, ReportBaseVo dataSource, Map<String, String> paramMap, int typePrint, String destPath) throws BizException {
		
		try {
			//REPORT PATH
			InputStream inputStream = FileUtils.getInputStreamFromUrl(templatePath);
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
			
			//EXPORT PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint, destPath);
			
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} finally {

		}
	}	
}

