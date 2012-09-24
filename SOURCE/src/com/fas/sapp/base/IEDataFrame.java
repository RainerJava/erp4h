/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：IEDataFrame.java
*
*     記述				：
*     
*     作成日			：2010/02/15   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/

package com.fas.sapp.base;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.ExitWindow;
import com.fas.jface.button.Action;
import com.fas.jface.button.ActionButton;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.file.BaseFileFilter;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.menuexe.MenuExeVo;
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

public abstract class IEDataFrame extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected BaseInputText txtFilePath;
	/** */
	protected static int iCount = 100;
	/** */
	protected static JProgressBar jProgressBar;
	/** */
	protected static JDialog dlg;
	/** */
	private ActionButton btnFileCancel;
	/** */
	protected ActionButton btnFileChooser;
	/** */
	protected ActionButton btnFileDo;
	/** */
	protected SortObjVo sortObj;
	/** */
	protected MenuExeVo exeMenu;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public IEDataFrame(BaseFrame owner, boolean modal) {
        super(owner, modal);
        init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public IEDataFrame() {
        super();
        init();
	}
	
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    private void init(){
        setTitle(getSubName());
        setResizable(false);
        setDefaultCloseOperation( DISPOSE_ON_CLOSE ); 
        addWindowListener(new ExitWindow(btnFileCancel));
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
    protected abstract String getSubName();
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected String getDefaultPath() {
    	
    	String strPath = MOutCtlContants.getValue(ApplicationConstants.getLoginUser().getUserId(), MOutCtlContants.I_PATH1);
    	
    	if (StringUtils.isValid(strPath)) {
    		return strPath;
    	} else {
    	     JFileChooser fr = new JFileChooser();
    	     FileSystemView fw = fr.getFileSystemView();
    	     return fw.getDefaultDirectory().getAbsolutePath();
    	}
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
    protected abstract String getLabel();
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected String getDirPath() {
    	
    	String strPath = MOutCtlContants.getValue(ApplicationConstants.getLoginUser().getUserId(), MOutCtlContants.I_PATH1);
    	
    	if (StringUtils.isValid(strPath)) {
    		return strPath;
    	} else {
	   	     JFileChooser fr = new JFileChooser();
		     FileSystemView fw = fr.getFileSystemView();
		     return fw.getDefaultDirectory().getAbsolutePath();
    	}
    }
    
    /* (non-Javadoc)
     * @see com.linc.jface.base.gui.LincFrame#getBodyPanel()
     */
    protected BasePanel getBodyPanel() {
    	//パネルの生成
    	mainPnl = new BasePanel();
    	mainPnl.setLayout(null);
    	mainPnl.setPreferredSize(new Dimension(X_BODY_LENGTH, Y_BODY_LENGTH));
    	mainPnl.setBackground(ColorConstants.FRAME_DEFAULT_COLOR);
        
	  	BasePanel pnlCenter = new BasePanel();
    	pnlCenter.setLayout(null);
    	pnlCenter.setSize(X_BODY_LENGTH, Y_BODY_LENGTH);
    	pnlCenter.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
    	pnlCenter.setLocation(0, 0);
    	
    	BaseLabel lblMain = new BaseLabel();
    	lblMain.setBounds(new Rectangle(10, 10, 320, 25));
    	lblMain.setText(getLabel());
    	lblMain.setFont(FontConstants.LABEL_TEXT_FONT);
    	lblMain.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
    	lblMain.setBorder(null);    	
    	pnlCenter.add(lblMain);
    	
    	txtFilePath = new BaseInputText();
    	txtFilePath.setBounds(new Rectangle(10, 40, 260, 25));
    	txtFilePath.setText(getDefaultPath());
    	pnlCenter.add(txtFilePath);
    	
    	ActionButton btnFileDo = new ActionButton();
    	btnFileDo.setBounds(new Rectangle(180, 75, 90, 25));
    	btnFileDo.setText("CSV出力");
    	btnFileDo.setBackground(ColorConstants.DEFAULT_BUTTON_COLOR);
    	btnFileDo.setFont(FontConstants.BUTTON_TEXT_FONT);    	
    	btnFileDo.addAction(new IEDataAction());
    	pnlCenter.add(btnFileDo);    	
    	
    	btnFileCancel = new ActionButton("閉じる");
    	btnFileCancel.setBounds(new Rectangle(280, 75, 90, 25));
    	btnFileCancel.setBackground(ColorConstants.DEFAULT_BUTTON_COLOR);
    	btnFileCancel.setFont(FontConstants.BUTTON_TEXT_FONT);
    	btnFileCancel.addAction(new Action() {
    		public void execute() {
    			dispose();
    		}
    	});
    	pnlCenter.add(btnFileCancel);  
    	
    	btnFileChooser = new ActionButton();
    	btnFileChooser.setBounds(new Rectangle(280, 40, 90, 25));
    	btnFileChooser.setText("参照");
    	btnFileChooser.setBackground(ColorConstants.DEFAULT_BUTTON_COLOR);
    	btnFileChooser.setFont(FontConstants.BUTTON_TEXT_FONT);   	
    	btnFileChooser.addAction(new DialogFileAction());
    	pnlCenter.add(btnFileChooser);
    	
    	mainPnl.add(pnlCenter);
    	
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			btnFileChooser.requestFocus();
    		}
    	});

    	return mainPnl;
    }
    
    /* (non-Javadoc)
     * @see com.linc.jface.base.gui.AbstractFrame#setFrameSize()
     */
    protected void setFrameSize() {
    	X_FRAME_LENGTH = 384;
    	Y_FRAME_LENGTH = 128;
    	X_BODY_LENGTH = 384;
    	Y_BODY_LENGTH = 128;
    	X_HEADER_LENGTH = 0;
    	Y_HEADER_LENGTH = 0;
    	X_FOOTER_LENGTH = 0;
    	Y_FOOTER_LENGTH = 0;
    }

	@Override
	protected BasePanel getFooter() {
		return null;
	}

	@Override
	protected JSeparator getFooterSeparator() {
		return null;
	}

	@Override
	protected BasePanel getHeader() {
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		return null;
	}
	
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	
	class DialogFileAction implements Action {
		
	    public void execute() {
	    	String[] fileName = new String[] { "csv", "dat"};
	    	JFileChooser fileChoser = new JFileChooser();
	    	fileChoser.addChoosableFileFilter(new BaseFileFilter(fileName, "ファイル (*.csv, *.dat)"));
	    	fileChoser.setCurrentDirectory(FileUtils.getFileObj(getDirPath()));
			int rVal = fileChoser.showSaveDialog(IEDataFrame.this);
			  
			if (rVal == JFileChooser.APPROVE_OPTION) {
				txtFilePath.setText(fileChoser.getCurrentDirectory().toString() + "\\" +  fileChoser.getSelectedFile().getName() +".csv");
			}
	    }
	}
	
	/**
	 * <DL>
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	class IEDataAction implements Action {
		
		/* (non-Javadoc)
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void execute() {
	    	
			dlg = new JDialog(new JFrame(), "実行中です。しばらくお待ち下さい。", true);
	    	
	    	if (iCount <= 0) {
	    		iCount = 1;
	    	}
	    	
		    jProgressBar = new JProgressBar(0, iCount);
		    dlg.add(BorderLayout.CENTER, jProgressBar);
		    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		    dlg.setSize(300, 55);
		    dlg.setLocationRelativeTo(new JFrame());	
		    jProgressBar.setIndeterminate(true);
		    dlg.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    dlg.setResizable(false);
		    doIEData();	
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected abstract boolean doIEData();

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	protected void setMaxProgress(int iMax) {
		if (iMax > 0) {
			jProgressBar.setMaximum(iMax);
		} else {
			jProgressBar.setMaximum(1);
		}
	}
}