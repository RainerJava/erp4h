/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ReportPreview.java
*
*     記述				：
*     
*     作成日			：2009/10/22   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.report.base;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.view.JRSaveContributor;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.jface.preview.ProjectJRViewer;
import com.fas.report.PrintCommonFrame;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class ReportPreview extends javax.swing.JFrame {

    /** */
	private static final long serialVersionUID = 1L;
	
	/** */
	private JFrame parentFrame;
	
	private int iTypeReport = 0;
	
    /** */
    private javax.swing.JPanel pnlMain;
    
    /** */
    private boolean isRequiredConfirm = false;
    
    /** */
    ProjectJRViewer viewer = null;
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param jasperPrint
	 * @throws JRException
	 */
	public ReportPreview(JasperPrint jasperPrint, int typePrint, int pageNum) throws JRException {
        
		getRevision();
        
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
        	 public void windowClosed(WindowEvent e) {
        		 if (parentFrame != null && !parentFrame.isVisible()) {
        			 parentFrame.setEnabled(true);

        			 if(parentFrame.getClass() == PrintCommonFrame.class )
        			 { 
	        			 if(iTypeReport == 1){
	        				 ((PrintCommonFrame)parentFrame).dispose();/// TODO: khong hieu sao dispose khong chay dc.
//	            			 ((PrintCommonFrame)parentFrame).Close(); 
	        			 }
//	        			 else if(iTypeReport == 2){
//	        				 ((TrOrderhFrame)parentFrame).dispose();/// TODO: khong hieu sao dispose khong chay dc.
//	            			 ((TrOrderhFrame)parentFrame).Close(); 
//	        			 }
	        			
	        			 parentFrame = null;
	        			// parentFrame.dispose(); 
        			 }
//        			 else if(parentFrame.getClass() == TrOrderhFrame.class )
//        			 {
////    					 if( parentFrame.isActive() )
//    						 parentFrame.show(true);
//        			 }
        			 else if(parentFrame.getClass() == ReportPreview.class )
        			 {
//    					 if( parentFrame.isActive() )
    						 parentFrame.show(true);
        			 }

        			
        		 } else {
        			 ApplicationConstants.closeFile();
        			 //System.exit(0);
        		 }
        	 }
        });
        viewer = new ProjectJRViewer(jasperPrint, Locale.JAPANESE, typePrint, pageNum);
        viewer = limitJRViewerFormats(viewer);
        
        int pageWidthPixels = jasperPrint.getPageWidth();
        int pageHeightPixels = jasperPrint.getPageHeight();
        
        //System.out.println("Width:" + pageWidthPixels + " Height:" + pageHeightPixels);
        
        float zoom = 0f;
        
        try {
        	if ((pageWidthPixels > 590 && pageWidthPixels< 600) 
        			&& (pageHeightPixels > 835 && pageHeightPixels< 850)) {
        		zoom = 	Float.parseFloat(MCtlConstants.getValue("SYSTEMZOOM_A4T"));
        	} else if ((pageHeightPixels > 590 && pageHeightPixels < 600) 
        			&& (pageWidthPixels > 835 && pageWidthPixels< 850)){
        		zoom = 	Float.parseFloat(MCtlConstants.getValue("SYSTEMZOOM_A4Y"));
        	} else if ((pageWidthPixels > 450 && pageWidthPixels < 505)
        			&& (pageHeightPixels > 705 && pageHeightPixels < 710)){
        		zoom = 	Float.parseFloat(MCtlConstants.getValue("SYSTEMZOOM_B5T"));
        	} else if ((pageHeightPixels > 450 && pageHeightPixels < 505)
        			&& (pageWidthPixels > 705 && pageWidthPixels < 710)){
        		zoom = 	Float.parseFloat(MCtlConstants.getValue("SYSTEMZOOM_B5Y"));
        	}   
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		
		if (zoom > 0)
			viewer.setZoomRatio(zoom);
		else
			viewer.setFitWidthZoomRatio();
        /*
		if (StringUtils.isValid(OutCtlContants.getPath(ApplicationConstants.LOGIN_USER_ID))) {
	        File saveDir = new File(OutCtlContants.getPath(ApplicationConstants.LOGIN_USER_ID));
	        viewer.setLastFolder(saveDir);
	        viewer.setParentFrame(parentFrame);
		}
        */
        this.pnlMain.add(viewer, BorderLayout.CENTER);
        
        SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
        		requestFocus();
        	}
        });
    }
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param jasperPrint
	 * @throws JRException
	 */
	public ReportPreview(JasperPrint jasperPrint) throws JRException {
        
		getRevision();
        
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
        	 public void windowClosed(WindowEvent e) {
        		 if (parentFrame != null) {
        			 parentFrame.setVisible(true);
        			 parentFrame.setEnabled(true);
        		 } else {
        			 ApplicationConstants.closeFile();
        			 System.exit(0);
        		 }
        	 }
        });
        viewer = new ProjectJRViewer(jasperPrint, Locale.JAPANESE);
        viewer = limitJRViewerFormats(viewer);

        float zoom = 0f;
        try {
			zoom = 	Float.parseFloat(MCtlConstants.getValue("SYSTEMZOOM_A4T"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		
		if (zoom > 0)
			viewer.setZoomRatio(zoom);
		else
			viewer.setFitWidthZoomRatio();
        /*
		if (StringUtils.isValid(OutCtlContants.getPath(ApplicationConstants.LOGIN_USER_ID))) {
	        File saveDir = new File(OutCtlContants.getPath(ApplicationConstants.LOGIN_USER_ID));
	        viewer.setLastFolder(saveDir);
	        viewer.setParentFrame(parentFrame);
		}
        */
        this.pnlMain.add(viewer, BorderLayout.CENTER);
        
        SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
        		requestFocus();
        	}
        });
    }
	public boolean isRequiredConfirm() {
		return isRequiredConfirm;
	}
	
	public void setRequiredConfirm(boolean isRequiredConfirm) {
		this.isRequiredConfirm = isRequiredConfirm;
		if (viewer != null) {
			viewer.setRequiredConfirm(isRequiredConfirm);
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param viewer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ProjectJRViewer limitJRViewerFormats(ProjectJRViewer viewer) {

	    JRSaveContributor[] save = viewer.getSaveContributors();
	    
	    for (int i=0; i<save.length; i++) {
	    	viewer.removeSaveContributor(save[i]);
	    }

	    final String[] DEFAULT_CONTRIBUTORS = {
	    /*"net.sf.jasperreports.view.save.JRPrintSaveContributor",*/
	    "net.sf.jasperreports.view.save.JRPdfSaveContributor"
	    /*
	    "net.sf.jasperreports.view.save.JROdtSaveContributor",
	    "net.sf.jasperreports.view.save.JRRtfSaveContributor",
	    "net.sf.jasperreports.view.save.JRHtmlSaveContributor",
	    "net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor",
	    "net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor",
	    "net.sf.jasperreports.view.save.JRCsvSaveContributor",
	    "net.sf.jasperreports.view.save.JRXmlSaveContributor",
	    "net.sf.jasperreports.view.save.JREmbeddedImagesXmlSaveContributor"
	    */
	    };
	            
	    for(int i = 0; i < DEFAULT_CONTRIBUTORS.length; i++) {
		      try {
			        Class saveContribClass = JRClassLoader.loadClassForName(DEFAULT_CONTRIBUTORS[i]);
			        ResourceBundle jrViewerResBundel = ResourceBundle.getBundle("net/sf/jasperreports/view/viewer", viewer.getLocale() );
			        Constructor constructor = saveContribClass.getConstructor(new Class[]{Locale.class, ResourceBundle.class});
			        JRSaveContributor saveContrib = (JRSaveContributor)constructor.newInstance(new Object[]{viewer.getLocale(), jrViewerResBundel });
			        viewer.addSaveContributor(saveContrib);
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
	    }
	    
	    return viewer;
	}

     
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    @SuppressWarnings("static-access")
	private void initComponents() {
    	
        pnlMain = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pnlMain.setLayout(new java.awt.BorderLayout());
        
        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);
		// Maximum Window
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setMaximizedBounds(env.getMaximumWindowBounds());
		pack();
		setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		
		//setIconImage(ImageConstants.HANBAI_ICON.getImage());

    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public static String getRevision() {
        return "";
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
        setTitle(parentFrame.getTitle());
        parentFrame.setVisible(false);
        viewer.setParentFrame(parentFrame);
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
        setTitle(title);
        parentFrame.setVisible(false);
        viewer.setParentFrame(parentFrame);
	}
}


