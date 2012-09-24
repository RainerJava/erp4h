/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名		：
 * 
 *     ファイル名			：ProjectJRViewer.java
 *
 *     記述				：
 *     
 *     作成日			：2009/12/25   
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.jface.preview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRImageMapRenderer;
import net.sf.jasperreports.engine.JRPrintAnchorIndex;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintImageAreaHyperlink;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.print.JRPrinterAWT;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.view.JRHyperlinkListener;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRPrintSaveContributor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.ReportConstants;
import com.fas.common.constants.dbtable.FPrinterConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.utils.SwingUtils;
import com.fas.service.common.printer.PrinterService;
import com.fas.service.common.printer.PrinterServiceImpl;
import com.fas.vo.fprinter.FPrinterVo;

/**
 * @author PC12
 * 
 */
public class ProjectJRViewer extends javax.swing.JPanel implements JRHyperlinkListener {
	private static final Log log = LogFactory.getLog(ProjectJRViewer.class);

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 * Maximum size (in pixels) of a buffered image that would be used by {@link ProjectJRViewer JRViewer} to render a report page.
	 * <p>
	 * If rendering a report page would require an image larger than this threshold (i.e. image width x image height > maximum size), the report page will be
	 * rendered directly on the viewer component.
	 * </p>
	 * <p>
	 * If this property is zero or negative, buffered images will never be user to render a report page. By default, this property is set to 0.
	 * </p>
	 */
	public static final String VIEWER_RENDER_BUFFER_MAX_SIZE = JRProperties.PROPERTY_PREFIX + "viewer.render.buffer.max.size";

	/**
	 *
	 */
	protected static final int TYPE_FILE_NAME = 1;
	protected static final int TYPE_INPUT_STREAM = 2;
	protected static final int TYPE_OBJECT = 3;

	/**
	 * The DPI of the generated report.
	 */
	public static final int REPORT_RESOLUTION = 72;

	protected float MIN_ZOOM = 0.5f;
	protected float MAX_ZOOM = 10f;
	protected int zooms[] = { 50, 75, 100, 125, 150, 175, 200, 250, 400, 800 };
	protected int defaultZoomIndex = 2;
	StopAction stopAction = null;
	EndAction endAction = null;
	protected int type = TYPE_FILE_NAME;
	protected boolean isXML = false;
	protected String reportFileName = null;
	protected SimpleFileResolver fileResolver = null;
	JasperPrint jasperPrint = null;
	private int pageIndex = 0;
	private boolean pageError;
	protected float zoom = 0f;
    /** */
    private boolean isRequiredConfirm = false;

	// QuangPV
	private int typePrint = 0;
	private int pageNum = 0;
	// !QuangPV
	private JRGraphics2DExporter exporter = null;

	/**
	 * the screen resolution.
	 */
	private int screenResolution = REPORT_RESOLUTION;

	/**
	 * the zoom ration adjusted to the screen resolution.
	 */
	protected float realZoom = 0f;

	private DecimalFormat zoomDecimalFormat = new DecimalFormat("#.##");
	private ResourceBundle resourceBundle = null;

	private int downX = 0;
	private int downY = 0;

	private java.util.List hyperlinkListeners = new ArrayList();
	private Map linksMap = new HashMap();
	private MouseListener mouseListener = new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			hyperlinkClicked(evt);
		}
	};

	protected KeyListener keyNavigationListener = new KeyListener() {
		public void keyTyped(KeyEvent evt) {
		}

		public void keyPressed(KeyEvent evt) {
			keyNavigate(evt);
		}

		public void keyReleased(KeyEvent evt) {
		}
	};

	protected List saveContributors = new ArrayList();
	protected File lastFolder = null;
	protected JRSaveContributor lastSaveContributor = null;

	protected JFrame parentFrame = null;

	/** Creates new form JRViewer */
	public ProjectJRViewer(String fileName, boolean isXML) throws JRException {
		this(fileName, isXML, null);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(InputStream is, boolean isXML) throws JRException {
		this(is, isXML, null);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(String fileName, boolean isXML, Locale locale) throws JRException {
		this(fileName, isXML, locale, null);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(InputStream is, boolean isXML, Locale locale) throws JRException {
		this(is, isXML, locale, null);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(JasperPrint jrPrint, Locale locale, int iType, int iNum) {
		this(jrPrint, locale, null);
		typePrint = iType;
		pageNum = iNum;
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(JasperPrint jrPrint, Locale locale) {
		this(jrPrint, locale, null);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(String fileName, boolean isXML, Locale locale, ResourceBundle resBundle) throws JRException {
		initResources(locale, resBundle);

		setScreenDetails();

		setZooms();

		initComponents();

		loadReport(fileName, isXML);

		cbbZoom.setSelectedIndex(defaultZoomIndex);

		initSaveContributors();

		addHyperlinkListener(this);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(InputStream is, boolean isXML, Locale locale, ResourceBundle resBundle) throws JRException {
		initResources(locale, resBundle);

		setScreenDetails();

		setZooms();

		initComponents();

		loadReport(is, isXML);

		cbbZoom.setSelectedIndex(defaultZoomIndex);

		initSaveContributors();

		addHyperlinkListener(this);
	}

	/** Creates new form JRViewer */
	public ProjectJRViewer(JasperPrint jrPrint, Locale locale, ResourceBundle resBundle) {
		initResources(locale, resBundle);

		setScreenDetails();

		setZooms();

		initComponents();

		loadReport(jrPrint);

		cbbZoom.setSelectedIndex(defaultZoomIndex);

		initSaveContributors();

		addHyperlinkListener(this);
	}

	public boolean isRequiredConfirm() {
		return isRequiredConfirm;
	}

	public void setRequiredConfirm(boolean isRequiredConfirm) {
		this.isRequiredConfirm = isRequiredConfirm;
	}

	private void setScreenDetails() {
		screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
	}

	/**
	 *
	 */
	public void clear() {
		emptyContainer(this);
		jasperPrint = null;
	}

	/**
	 *
	 */
	protected void setZooms() {
	}

	/**
	 *
	 */
	public void addSaveContributor(JRSaveContributor contributor) {
		saveContributors.add(contributor);
	}

	/**
	 *
	 */
	public void removeSaveContributor(JRSaveContributor contributor) {
		saveContributors.remove(contributor);
	}

	/**
	 *
	 */
	public JRSaveContributor[] getSaveContributors() {
		return (JRSaveContributor[]) saveContributors.toArray(new JRSaveContributor[saveContributors.size()]);
	}

	/**
	 * Replaces the save contributors with the ones provided as parameter.
	 */
	public void setSaveContributors(JRSaveContributor[] saveContributors) {
		this.saveContributors = new ArrayList();
		if (saveContributors != null) {
			this.saveContributors.addAll(Arrays.asList(saveContributors));
		}
	}

	/**
	 *
	 */
	public void addHyperlinkListener(JRHyperlinkListener listener) {
		hyperlinkListeners.add(listener);
	}

	/**
	 *
	 */
	public void removeHyperlinkListener(JRHyperlinkListener listener) {
		hyperlinkListeners.remove(listener);
	}

	/**
	 *
	 */
	public JRHyperlinkListener[] getHyperlinkListeners() {
		return (JRHyperlinkListener[]) hyperlinkListeners.toArray(new JRHyperlinkListener[hyperlinkListeners.size()]);
	}

	/**
	 *
	 */
	protected void initResources(Locale locale, ResourceBundle resBundle) {
		if (locale != null)
			setLocale(locale);
		else
			setLocale(Locale.getDefault());

		if (resBundle == null) {
			this.resourceBundle = ResourceBundle.getBundle("net/sf/jasperreports/view/viewer", getLocale());
		} else {
			this.resourceBundle = resBundle;
		}
	}

	/**
	 *
	 */
	protected String getBundleString(String key) {
		return resourceBundle.getString(key);
	}

	/**
	 *
	 */
	protected void initSaveContributors() {
		final String[] DEFAULT_CONTRIBUTORS = { "net.sf.jasperreports.view.save.JRPrintSaveContributor", "net.sf.jasperreports.view.save.JRPdfSaveContributor",
				"net.sf.jasperreports.view.save.JRRtfSaveContributor", "net.sf.jasperreports.view.save.JROdtSaveContributor",
				"net.sf.jasperreports.view.save.JRDocxSaveContributor", "net.sf.jasperreports.view.save.JRHtmlSaveContributor",
				"net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor", "net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor",
				"net.sf.jasperreports.view.save.JRCsvSaveContributor", "net.sf.jasperreports.view.save.JRXmlSaveContributor",
				"net.sf.jasperreports.view.save.JREmbeddedImagesXmlSaveContributor" };

		for (int i = 0; i < DEFAULT_CONTRIBUTORS.length; i++) {
			try {
				Class saveContribClass = JRClassLoader.loadClassForName(DEFAULT_CONTRIBUTORS[i]);
				Constructor constructor = saveContribClass.getConstructor(new Class[] { Locale.class, ResourceBundle.class });
				JRSaveContributor saveContrib = (JRSaveContributor) constructor.newInstance(new Object[] { getLocale(), resourceBundle });
				saveContributors.add(saveContrib);
			} catch (Exception e) {
			}
		}
	}

	/**
	 *
	 */
	public void gotoHyperlink(JRPrintHyperlink hyperlink) {
		switch (hyperlink.getHyperlinkType()) {
		case JRHyperlink.HYPERLINK_TYPE_REFERENCE: {
			if (isOnlyHyperlinkListener()) {
				System.out.println("Hyperlink reference : " + hyperlink.getHyperlinkReference());
				System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_LOCAL_ANCHOR: {
			if (hyperlink.getHyperlinkAnchor() != null) {
				Map anchorIndexes = jasperPrint.getAnchorIndexes();
				JRPrintAnchorIndex anchorIndex = (JRPrintAnchorIndex) anchorIndexes.get(hyperlink.getHyperlinkAnchor());
				if (anchorIndex.getPageIndex() != pageIndex) {
					setPageIndex(anchorIndex.getPageIndex());
					refreshPage();
				}
				Container container = pnlInScroll.getParent();
				if (container instanceof JViewport) {
					JViewport viewport = (JViewport) container;

					int newX = (int) (anchorIndex.getElementAbsoluteX() * realZoom);
					int newY = (int) (anchorIndex.getElementAbsoluteY() * realZoom);

					int maxX = pnlInScroll.getWidth() - viewport.getWidth();
					int maxY = pnlInScroll.getHeight() - viewport.getHeight();

					if (newX < 0) {
						newX = 0;
					}
					if (newX > maxX) {
						newX = maxX;
					}
					if (newY < 0) {
						newY = 0;
					}
					if (newY > maxY) {
						newY = maxY;
					}

					viewport.setViewPosition(new Point(newX, newY));
				}
			}

			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_LOCAL_PAGE: {
			int page = pageIndex + 1;
			if (hyperlink.getHyperlinkPage() != null) {
				page = hyperlink.getHyperlinkPage().intValue();
			}

			if (page >= 1 && page <= jasperPrint.getPages().size() && page != pageIndex + 1) {
				setPageIndex(page - 1);
				refreshPage();
				Container container = pnlInScroll.getParent();
				if (container instanceof JViewport) {
					JViewport viewport = (JViewport) container;
					viewport.setViewPosition(new Point(0, 0));
				}
			}

			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_REMOTE_ANCHOR: {
			if (isOnlyHyperlinkListener()) {
				System.out.println("Hyperlink reference : " + hyperlink.getHyperlinkReference());
				System.out.println("Hyperlink anchor    : " + hyperlink.getHyperlinkAnchor());
				System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_REMOTE_PAGE: {
			if (isOnlyHyperlinkListener()) {
				System.out.println("Hyperlink reference : " + hyperlink.getHyperlinkReference());
				System.out.println("Hyperlink page      : " + hyperlink.getHyperlinkPage());
				System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_CUSTOM: {
			if (isOnlyHyperlinkListener()) {
				System.out.println("Hyperlink of type " + hyperlink.getLinkType());
				System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_NONE:
		default: {
			break;
		}
		}
	}

	protected boolean isOnlyHyperlinkListener() {
		int listenerCount;
		if (hyperlinkListeners == null) {
			listenerCount = 0;
		} else {
			listenerCount = hyperlinkListeners.size();
			if (hyperlinkListeners.contains(this)) {
				--listenerCount;
			}
		}
		return listenerCount == 0;
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		tlbToolBar = new javax.swing.JPanel();
		btnSave = new javax.swing.JButton();
		btnPrint = new javax.swing.JButton();

		btnReload = new javax.swing.JButton();
		pnlSep01 = new javax.swing.JPanel();
		btnFirst = new javax.swing.JButton();
		btnPrevious = new javax.swing.JButton();
		btnNext = new javax.swing.JButton();
		btnLast = new javax.swing.JButton();
		txtGoTo = new javax.swing.JTextField();
		pnlSep02 = new javax.swing.JPanel();
		btnActualSize = new javax.swing.JToggleButton();
		btnFitPage = new javax.swing.JToggleButton();
		btnFitWidth = new javax.swing.JToggleButton();
		pnlSep03 = new javax.swing.JPanel();
		pnlSep04 = new javax.swing.JPanel();
		btnZoomIn = new javax.swing.JButton();
		btnZoomOut = new javax.swing.JButton();
		cbbZoom = new javax.swing.JComboBox();

		// hanbai system
		btnFirst1 = new javax.swing.JButton();
		pnlSep05 = new javax.swing.JPanel();
		btnPrevious1 = new javax.swing.JButton();
		pnlSep06 = new javax.swing.JPanel();
		btnNext1 = new javax.swing.JButton();
		pnlSep07 = new javax.swing.JPanel();
		btnLast1 = new javax.swing.JButton();
		pnlSep08 = new javax.swing.JPanel();
		btnF5 = new javax.swing.JButton();
		pnlSep09 = new javax.swing.JPanel();
		btnF6 = new javax.swing.JButton();
		pnlSep10 = new javax.swing.JPanel();
		btnF7 = new javax.swing.JButton();
		pnlSep11 = new javax.swing.JPanel();
		btnPrint1 = new javax.swing.JButton();
		pnlSep12 = new javax.swing.JPanel();
		btnF9 = new javax.swing.JButton();
		pnlSep13 = new javax.swing.JPanel();
		btnF10 = new javax.swing.JButton();
		pnlSep14 = new javax.swing.JPanel();
		btnF11 = new javax.swing.JButton();
		pnlSep15 = new javax.swing.JPanel();
		btnF12 = new javax.swing.JButton();

		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (int i = 0; i < zooms.length; i++) {
			model.addElement("" + zooms[i] + "%");
		}
		cbbZoom.setModel(model);

		pnlMain = new javax.swing.JPanel();
		scrollPane = new javax.swing.JScrollPane();
		scrollPane.getHorizontalScrollBar().setUnitIncrement(5);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);

		pnlInScroll = new javax.swing.JPanel();
		pnlPage = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		pnlLinks = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		lblPage = new PageRenderer(this);
		pnlStatus = new javax.swing.JPanel();
		lblStatus = new javax.swing.JLabel();

		setLayout(new java.awt.BorderLayout());

		setMinimumSize(new java.awt.Dimension(450, 150));
		setPreferredSize(new java.awt.Dimension(450, 150));
		tlbToolBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 2));

		btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/save.GIF")));
//		btnSave.setToolTipText("保存します。");
		btnSave.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnSave.setMaximumSize(new java.awt.Dimension(23, 23));
		btnSave.setMinimumSize(new java.awt.Dimension(23, 23));
		btnSave.setPreferredSize(new java.awt.Dimension(23, 23));
		btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});
		btnSave.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnSave);

		PrintAction printAction = new PrintAction("print");

		btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/print.GIF")));
//		btnPrint.setToolTipText("印刷します。");
		btnPrint.setMargin(new java.awt.Insets(2, 2, 2, 2));
//		btnPrint.setMaximumSize(new java.awt.Dimension(23, 23));
//		btnPrint.setMinimumSize(new java.awt.Dimension(23, 23));
		btnPrint.setPreferredSize(new java.awt.Dimension(25, 23));
		btnPrint.addActionListener(printAction);
		btnPrint.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnPrint);

		btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/reload.GIF")));
//		btnReload.setToolTipText("リロード");
		btnReload.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnReload.setMaximumSize(new java.awt.Dimension(23, 23));
		btnReload.setMinimumSize(new java.awt.Dimension(23, 23));
		btnReload.setPreferredSize(new java.awt.Dimension(23, 23));
		btnReload.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReloadActionPerformed(evt);
			}
		});
		btnReload.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnReload);

		pnlSep01.setMaximumSize(new java.awt.Dimension(10, 10));
		tlbToolBar.add(pnlSep01);

		GoFirstAction goFirstAction = new GoFirstAction("goFirst");
		btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/first.GIF")));
//		btnFirst.setToolTipText("先頭頁を表示します。");
		btnFirst.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnFirst.setMaximumSize(new java.awt.Dimension(23, 23));
		btnFirst.setMinimumSize(new java.awt.Dimension(23, 23));
		btnFirst.setPreferredSize(new java.awt.Dimension(23, 23));
		btnFirst.addActionListener(goFirstAction);
		btnFirst.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnFirst);

		GoPreviousAction goPreviousAction = new GoPreviousAction("goPrevious");
		btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/previous.GIF")));
//		btnPrevious.setToolTipText("前頁します。");
		btnPrevious.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnPrevious.setMaximumSize(new java.awt.Dimension(23, 23));
		btnPrevious.setMinimumSize(new java.awt.Dimension(23, 23));
		btnPrevious.setPreferredSize(new java.awt.Dimension(23, 23));
		btnPrevious.addActionListener(goPreviousAction);
		btnPrevious.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnPrevious);

//		txtGoTo.setToolTipText(getBundleString("go.to.page"));
		txtGoTo.setMaximumSize(new java.awt.Dimension(40, 23));
		txtGoTo.setMinimumSize(new java.awt.Dimension(40, 23));
		txtGoTo.setPreferredSize(new java.awt.Dimension(40, 23));
		txtGoTo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtGoToActionPerformed(evt);
			}
		});
		txtGoTo.addKeyListener(keyNavigationListener);
		tlbToolBar.add(txtGoTo);

		GoNextAction goNextAction = new GoNextAction("goNext");
		btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/next.GIF")));
//		btnNext.setToolTipText("次頁します。");
		btnNext.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnNext.setMaximumSize(new java.awt.Dimension(23, 23));
		btnNext.setMinimumSize(new java.awt.Dimension(23, 23));
		btnNext.setPreferredSize(new java.awt.Dimension(23, 23));
		btnNext.addActionListener(goNextAction);
		btnNext.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnNext);

		GoLastAction goLastAction = new GoLastAction("goLast");

		btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/last.GIF")));
//		btnLast.setToolTipText("最終頁します。");
		btnLast.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnLast.setMaximumSize(new java.awt.Dimension(23, 23));
		btnLast.setMinimumSize(new java.awt.Dimension(23, 23));
		btnLast.setPreferredSize(new java.awt.Dimension(23, 23));
		btnLast.addActionListener(goLastAction);
		btnLast.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnLast);

		pnlSep02.setMaximumSize(new java.awt.Dimension(10, 10));
		tlbToolBar.add(pnlSep02);

		lblStatus.setFont(new java.awt.Font("Dialog", 1, 10));
		lblStatus.setText(" Page i of n");
		tlbToolBar.add(lblStatus);

		pnlSep03.setMaximumSize(new java.awt.Dimension(10, 10));
		tlbToolBar.add(pnlSep03);

		btnZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/zoomout.GIF")));
//		btnZoomOut.setToolTipText("縮小");
		btnZoomOut.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnZoomOut.setMaximumSize(new java.awt.Dimension(23, 23));
		btnZoomOut.setMinimumSize(new java.awt.Dimension(23, 23));
		btnZoomOut.setPreferredSize(new java.awt.Dimension(23, 23));
		btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnZoomOutActionPerformed(evt);
			}
		});
		btnZoomOut.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnZoomOut);

		cbbZoom.setEditable(true);
//		cbbZoom.setToolTipText("");
		cbbZoom.setMaximumSize(new java.awt.Dimension(80, 23));
		cbbZoom.setMinimumSize(new java.awt.Dimension(80, 23));
		cbbZoom.setPreferredSize(new java.awt.Dimension(80, 23));
		cbbZoom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbbZoomActionPerformed(evt);
			}
		});
		cbbZoom.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cbbZoomItemStateChanged(evt);
			}
		});
		cbbZoom.addKeyListener(keyNavigationListener);
		tlbToolBar.add(cbbZoom);

		btnZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/zoomin.GIF")));
//		btnZoomIn.setToolTipText("拡大");
		btnZoomIn.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnZoomIn.setMaximumSize(new java.awt.Dimension(23, 23));
		btnZoomIn.setMinimumSize(new java.awt.Dimension(23, 23));
		btnZoomIn.setPreferredSize(new java.awt.Dimension(23, 23));
		btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnZoomInActionPerformed(evt);
			}
		});
		btnZoomIn.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnZoomIn);

		pnlSep04.setMaximumSize(new java.awt.Dimension(10, 10));
		tlbToolBar.add(pnlSep04);
		btnActualSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/actualsize.GIF")));
//		btnActualSize.setToolTipText(getBundleString("actual.size"));
		btnActualSize.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnActualSize.setMaximumSize(new java.awt.Dimension(23, 23));
		btnActualSize.setMinimumSize(new java.awt.Dimension(23, 23));
		btnActualSize.setPreferredSize(new java.awt.Dimension(23, 23));
		btnActualSize.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnActualSizeActionPerformed(evt);
			}
		});
		btnActualSize.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnActualSize);

		btnFitPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/fitpage.GIF")));
//		btnFitPage.setToolTipText(getBundleString("fit.page"));
		btnFitPage.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnFitPage.setMaximumSize(new java.awt.Dimension(23, 23));
		btnFitPage.setMinimumSize(new java.awt.Dimension(23, 23));
		btnFitPage.setPreferredSize(new java.awt.Dimension(23, 23));
		btnFitPage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFitPageActionPerformed(evt);
			}
		});
		btnFitPage.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnFitPage);

		btnFitWidth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/fitwidth.GIF")));
//		btnFitWidth.setToolTipText(getBundleString("fit.width"));
		btnFitWidth.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnFitWidth.setMaximumSize(new java.awt.Dimension(23, 23));
		btnFitWidth.setMinimumSize(new java.awt.Dimension(23, 23));
		btnFitWidth.setPreferredSize(new java.awt.Dimension(23, 23));
		btnFitWidth.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFitWidthActionPerformed(evt);
			}
		});
		btnFitWidth.addKeyListener(keyNavigationListener);
		tlbToolBar.add(btnFitWidth);

		add(tlbToolBar, java.awt.BorderLayout.NORTH);

		pnlMain.setLayout(new java.awt.BorderLayout());
		pnlMain.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				pnlMainComponentResized(evt);
			}
		});

		scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pnlInScroll.setLayout(new java.awt.GridBagLayout());

		pnlPage.setLayout(new java.awt.BorderLayout());
		pnlPage.setMinimumSize(new java.awt.Dimension(100, 100));
		pnlPage.setPreferredSize(new java.awt.Dimension(100, 100));

		jPanel4.setLayout(new java.awt.GridBagLayout());
		jPanel4.setMinimumSize(new java.awt.Dimension(100, 120));
		jPanel4.setPreferredSize(new java.awt.Dimension(100, 120));

		pnlLinks.setLayout(null);
		pnlLinks.setMinimumSize(new java.awt.Dimension(5, 5));
		pnlLinks.setPreferredSize(new java.awt.Dimension(5, 5));
		pnlLinks.setOpaque(false);
		pnlLinks.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				pnlLinksMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				pnlLinksMouseReleased(evt);
			}
		});
		pnlLinks.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				pnlLinksMouseDragged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		jPanel4.add(pnlLinks, gridBagConstraints);

		jPanel5.setBackground(java.awt.Color.gray);
		jPanel5.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel5.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		jPanel4.add(jPanel5, gridBagConstraints);

		jPanel6.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel6.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		jPanel4.add(jPanel6, gridBagConstraints);

		jPanel7.setBackground(java.awt.Color.gray);
		jPanel7.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel7.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel4.add(jPanel7, gridBagConstraints);

		jPanel8.setBackground(java.awt.Color.gray);
		jPanel8.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel8.setPreferredSize(new java.awt.Dimension(5, 5));
		jLabel1.setText("jLabel1");
		jPanel8.add(jLabel1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		jPanel4.add(jPanel8, gridBagConstraints);

		jPanel9.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel9.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel4.add(jPanel9, gridBagConstraints);

		lblPage.setBackground(java.awt.Color.white);
		lblPage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
		lblPage.setOpaque(true);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(lblPage, gridBagConstraints);

		pnlPage.add(jPanel4, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		pnlInScroll.add(pnlPage, gridBagConstraints);

		scrollPane.setViewportView(pnlInScroll);
		pnlMain.add(scrollPane, java.awt.BorderLayout.CENTER);
		add(pnlMain, java.awt.BorderLayout.CENTER);

		// Hanbai Bar
		pnlStatus.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

		btnFirst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/first.GIF")));
		btnFirst1.setToolTipText("先頭頁を表示します。");
		btnFirst1.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnFirst1.setBounds(new Rectangle(7, 6, 70, 36));
		btnFirst1.setText("<html><center><font size=-1>先頭頁</font></center><center><font size=-1>F1(E)</font></center>");
		btnFirst1.addActionListener(goFirstAction);
		btnFirst1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), goFirstAction.getValue(Action.NAME));
		btnFirst1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt E"), goFirstAction.getValue(Action.NAME));
		btnFirst1.getActionMap().put("goFirst", goFirstAction);
		btnFirst1.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnFirst1);

		pnlSep05.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep05);

		btnPrevious1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/previous.GIF")));
		btnPrevious1.setToolTipText("前頁を表示します。");
		btnPrevious1.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnPrevious1.setBounds(new Rectangle(7, 6, 70, 36));
		btnPrevious1.setText("<html><center><font size=-1>前　頁</font></center><center><font size=-1>F2(F)</font></center>");
		btnPrevious1.addActionListener(goPreviousAction);
		btnPrevious1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), goPreviousAction.getValue(Action.NAME));
		btnPrevious1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt F"), goPreviousAction.getValue(Action.NAME));
		btnPrevious1.getActionMap().put("goPrevious", goPreviousAction);
		btnPrevious1.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnPrevious1);

		pnlSep06.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep06);

		btnNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/next.GIF")));
		btnNext1.setToolTipText("次頁を表示します。");
		btnNext1.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnNext1.setBounds(new Rectangle(7, 6, 70, 36));
		btnNext1.setText("<html><center><font size=-1>次　頁</font></center><center><font size=-1>F3(G)</font></center>");
		btnNext1.addActionListener(goNextAction);
		btnNext1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), goNextAction.getValue(Action.NAME));
		btnNext1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt G"), goNextAction.getValue(Action.NAME));
		btnNext1.getActionMap().put("goNext", goNextAction);
		btnNext1.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnNext1);

		pnlSep07.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep07);

		btnLast1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/last.GIF")));
		btnLast1.setToolTipText("最終頁を表示します。");
		btnLast1.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnLast1.setBounds(new Rectangle(7, 6, 70, 36));
		btnLast1.setText("<html><center><font size=-1>最終頁</font></center><center><font size=-1>F4(S)</font></center>");
		btnLast1.addActionListener(goLastAction);
		btnLast1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), goLastAction.getValue(Action.NAME));
		btnLast1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt S"), goLastAction.getValue(Action.NAME));
		btnLast1.getActionMap().put("goLast", goLastAction);
		btnLast1.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnLast1);

		pnlSep08.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep08);

		btnF5.setBounds(new Rectangle(7, 6, 70, 36));
		btnF5.setText("<html><center><font size=-1></font></center><center><font size=-1>F5(T)</font></center>");
		btnF5.setEnabled(false);
		btnF5.setForeground(pnlStatus.getBackground());
		pnlStatus.add(btnF5);

		pnlSep09.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep09);

		btnF6.setBounds(new Rectangle(7, 6, 70, 36));
		btnF6.setText("<html><center><font size=-1></font></center><center><font size=-1>F6(D)</font></center>");
		btnF6.setEnabled(false);
		btnF6.setForeground(pnlStatus.getBackground());
		pnlStatus.add(btnF6);

		pnlSep10.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep10);

		btnF7.setBounds(new Rectangle(7, 6, 70, 36));
		btnF7.setText("<html><center><font size=-1></font></center><center><font size=-1>F7(W)</font></center>");
		btnF7.setEnabled(false);
		btnF7.setForeground(pnlStatus.getBackground());
		pnlStatus.add(btnF7);

		pnlSep11.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep11);

		btnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/print.GIF")));
		btnPrint1.setToolTipText("印刷します。");
		btnPrint1.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnPrint1.setBounds(new Rectangle(7, 6, 70, 36));
		btnPrint1.setText("<html><center><font size=-1>  印　刷  </font></center><center><font size=-1>  F8(A)  </font></center>");
		btnPrint1.addActionListener(printAction);
		btnPrint1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), printAction.getValue(Action.NAME));
		btnPrint1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), printAction.getValue(Action.NAME));
		btnPrint1.getActionMap().put("print", printAction);
		btnPrint1.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnPrint1);

		pnlSep12.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep12);

		btnF9.setBounds(new Rectangle(7, 6, 70, 36));
		btnF9.setText("<html><center><font size=-1></font></center><center><font size=-1>F9(R)</font></center>");
		btnF9.setEnabled(false);
		btnF9.setForeground(pnlStatus.getBackground());
		pnlStatus.add(btnF9);

		pnlSep13.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep13);

		btnF10.setBounds(new Rectangle(7, 6, 70, 36));
		btnF10.setText("<html><center><font size=-1></font></center><center><font size=-1>F10(V)</font></center>");
		btnF10.setEnabled(false);
		btnF10.setForeground(pnlStatus.getBackground());
		pnlStatus.add(btnF10);

		pnlSep14.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep14);

		stopAction = new StopAction("stop", this, parentFrame);
		btnF11.setBounds(new Rectangle(7, 6, 70, 36));
		btnF11.setToolTipText("中止します。");
		btnF11.setText("<html><center><font size=-1>中　止</font></center><center><font size=-1>F11(C)</font></center>");
		btnF11.addActionListener(stopAction);
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), stopAction.getValue(Action.NAME));
		btnF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt C"), stopAction.getValue(Action.NAME));
		btnF11.getActionMap().put("stop", stopAction);
		btnF11.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnF11);

		pnlSep15.setMaximumSize(new java.awt.Dimension(10, 10));
		pnlStatus.add(pnlSep15);

		endAction = new EndAction("end", this, parentFrame);
		btnF12.setBounds(new Rectangle(7, 6, 70, 36));
		btnF12.setToolTipText("終了します。");
		btnF12.setText("<html><center><font size=-1>閉じる</font></center><center><font size=-1>F12(Q)</font></center>");
		btnF12.addActionListener(endAction);
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), endAction.getValue(Action.NAME));
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), endAction.getValue(Action.NAME));
		btnF12.getActionMap().put("end", endAction);
		btnF12.addKeyListener(keyNavigationListener);
		pnlStatus.add(btnF12);

		footerMain = new BasePanel();

		footerMain.setLayout(new java.awt.BorderLayout());
		footerMain.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		footerMain.add(pnlStatus, java.awt.BorderLayout.CENTER);
		footerStatusPnl = getFooter();
		footerMain.add(footerStatusPnl, java.awt.BorderLayout.SOUTH);

		add(footerMain, java.awt.BorderLayout.SOUTH);
		addKeyListener(keyNavigationListener);

		// QuangPV
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};

		setMouseCurrsor(btnFirst1, mouseAdapter);
		setMouseCurrsor(btnPrevious1, mouseAdapter);
		setMouseCurrsor(btnNext1, mouseAdapter);
		setMouseCurrsor(btnLast1, mouseAdapter);
		setMouseCurrsor(btnPrint1, mouseAdapter);
		setMouseCurrsor(btnF11, mouseAdapter);
		setMouseCurrsor(btnF12, mouseAdapter);
		setMouseCurrsor(btnFirst, mouseAdapter);
		setMouseCurrsor(btnLast, mouseAdapter);
		setMouseCurrsor(btnNext, mouseAdapter);
		setMouseCurrsor(btnPrevious, mouseAdapter);
		setMouseCurrsor(btnPrint, mouseAdapter);
		setMouseCurrsor(btnReload, mouseAdapter);
		setMouseCurrsor(btnSave, mouseAdapter);
		setMouseCurrsor(btnZoomIn, mouseAdapter);
		setMouseCurrsor(btnZoomOut, mouseAdapter);
		setMouseCurrsor(btnActualSize, mouseAdapter);
		setMouseCurrsor(btnFitPage, mouseAdapter);
		setMouseCurrsor(btnFitWidth, mouseAdapter);
		setMouseCurrsor(btnZoomOut, mouseAdapter);
	}

	private void setMouseCurrsor(JButton btn, MouseAdapter mouseAd) {
		if (btn != null) {
			btn.addMouseListener(mouseAd);
		}
	}

	private void setMouseCurrsor(JToggleButton btn, MouseAdapter mouseAd) {
		if (btn != null) {
			btn.addMouseListener(mouseAd);
		}
	}

	// !QuangPV

	// </editor-fold>//GEN-END:initComponents

	void txtGoToActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtGoToActionPerformed
		try {
			int pageNumber = Integer.parseInt(txtGoTo.getText());
			if (pageNumber != pageIndex + 1 && pageNumber > 0 && pageNumber <= jasperPrint.getPages().size()) {
				setPageIndex(pageNumber - 1);
				refreshPage();
			}
		} catch (NumberFormatException e) {
		}
	}// GEN-LAST:event_txtGoToActionPerformed

	void cbbZoomItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cbbZoomItemStateChanged
		// Add your handling code here:
		btnActualSize.setSelected(false);
		btnFitPage.setSelected(false);
		btnFitWidth.setSelected(false);
	}// GEN-LAST:event_cbbZoomItemStateChanged

	void pnlMainComponentResized(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_pnlMainComponentResized
		// Add your handling code here:
		if (btnFitPage.isSelected()) {
			fitPage();
			btnFitPage.setSelected(true);
		} else if (btnFitWidth.isSelected()) {
			setRealZoomRatio(((float) pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth());
			btnFitWidth.setSelected(true);
		}

	}// GEN-LAST:event_pnlMainComponentResized

	void btnActualSizeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnActualSizeActionPerformed
		// Add your handling code here:
		if (btnActualSize.isSelected()) {
			btnFitPage.setSelected(false);
			btnFitWidth.setSelected(false);
			cbbZoom.setSelectedIndex(-1);
			setZoomRatio(1);
			btnActualSize.setSelected(true);
		}
	}// GEN-LAST:event_btnActualSizeActionPerformed

	void btnFitWidthActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFitWidthActionPerformed
		// Add your handling code here:
		if (btnFitWidth.isSelected()) {
			btnActualSize.setSelected(false);
			btnFitPage.setSelected(false);
			cbbZoom.setSelectedIndex(-1);
			setRealZoomRatio(((float) pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth());
			btnFitWidth.setSelected(true);
		}
	}// GEN-LAST:event_btnFitWidthActionPerformed

	void btnFitPageActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFitPageActionPerformed
		// Add your handling code here:
		if (btnFitPage.isSelected()) {
			btnActualSize.setSelected(false);
			btnFitWidth.setSelected(false);
			cbbZoom.setSelectedIndex(-1);
			fitPage();
			btnFitPage.setSelected(true);
		}
	}// GEN-LAST:event_btnFitPageActionPerformed

	void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSaveActionPerformed
		// Add your handling code here:

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setLocale(this.getLocale());
		fileChooser.updateUI();
		for (int i = 0; i < saveContributors.size(); i++) {
			fileChooser.addChoosableFileFilter((JRSaveContributor) saveContributors.get(i));
		}

		if (saveContributors.contains(lastSaveContributor)) {
			fileChooser.setFileFilter(lastSaveContributor);
		} else if (saveContributors.size() > 0) {
			fileChooser.setFileFilter((JRSaveContributor) saveContributors.get(0));
		}

		if (lastFolder != null) {
			fileChooser.setCurrentDirectory(lastFolder);
		}

		int retValue = fileChooser.showSaveDialog(this);
		if (retValue == JFileChooser.APPROVE_OPTION) {
			FileFilter fileFilter = fileChooser.getFileFilter();
			File file = fileChooser.getSelectedFile();

			lastFolder = file.getParentFile();

			JRSaveContributor contributor = null;

			if (fileFilter instanceof JRSaveContributor) {
				contributor = (JRSaveContributor) fileFilter;
			} else {
				int i = 0;
				while (contributor == null && i < saveContributors.size()) {
					contributor = (JRSaveContributor) saveContributors.get(i++);
					if (!contributor.accept(file)) {
						contributor = null;
					}
				}

				if (contributor == null) {
					contributor = new JRPrintSaveContributor(getLocale(), this.resourceBundle);
				}
			}

			lastSaveContributor = contributor;

			try {
				contributor.save(jasperPrint, file);
			} catch (JRException e) {
				if (log.isErrorEnabled())
					log.error("Save error.", e);

				JOptionPane.showMessageDialog(this, getBundleString("error.saving"));
			}
		}
	}// GEN-LAST:event_btnSaveActionPerformed

	void pnlLinksMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_pnlLinksMouseDragged
		// Add your handling code here:

		Container container = pnlInScroll.getParent();
		if (container instanceof JViewport) {
			JViewport viewport = (JViewport) container;
			Point point = viewport.getViewPosition();
			int newX = point.x - (evt.getX() - downX);
			int newY = point.y - (evt.getY() - downY);

			int maxX = pnlInScroll.getWidth() - viewport.getWidth();
			int maxY = pnlInScroll.getHeight() - viewport.getHeight();

			if (newX < 0) {
				newX = 0;
			}
			if (newX > maxX) {
				newX = maxX;
			}
			if (newY < 0) {
				newY = 0;
			}
			if (newY > maxY) {
				newY = maxY;
			}

			viewport.setViewPosition(new Point(newX, newY));
		}
	}// GEN-LAST:event_pnlLinksMouseDragged

	void pnlLinksMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_pnlLinksMouseReleased
		// Add your handling code here:
		pnlLinks.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}// GEN-LAST:event_pnlLinksMouseReleased

	void pnlLinksMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_pnlLinksMousePressed
		// Add your handling code here:
		pnlLinks.setCursor(new Cursor(Cursor.MOVE_CURSOR));

		downX = evt.getX();
		downY = evt.getY();
	}// GEN-LAST:event_pnlLinksMousePressed

	/**
	 * @author PC12
	 * 
	 */
	class PrintAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		private PrinterService hanbaiPrinterService = new PrinterServiceImpl();

		public PrintAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {// GEN-HEADEREND:event_btnPrintActionPerformed

			Thread thread = new Thread(new Runnable() {

				public void run() {

					try {

						btnPrint.setEnabled(false);
						btnPrint1.setEnabled(false);

						ProjectJRViewer.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						// JasperPrintManager.printReport(jasperPrint, true);

						PrinterJob job = PrinterJob.getPrinterJob();

						/* Create an array of PrintServices */
						PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
						int selectedService = 0;

						/* Scan found services to see if anyone suits our needs */
						for (int i = 0; i < services.length; i++) {
							if (services[i].getName().toUpperCase().contains(ReportConstants.DEFAULT_PRINTER)) {
								/* If the service is named as what we are querying we select it */
								selectedService = i;
								break;
							}
						}

						job.setPrintService(services[selectedService]);
						job.defaultPage().setOrientation(PageFormat.LANDSCAPE);
						PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

						printRequestAttributeSet.add(ApplicationConstants.mediaSizeName);
						printRequestAttributeSet.add(new Copies(pageNum));
						JRPrintServiceExporter exporter = new JRPrintServiceExporter();
						exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						/* We set the selected service and pass it as a paramenter */
						exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
						exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
						exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
						exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
						// QuangPV
						if (typePrint == ReportConstants.PRINTER_DIRECT) {
							exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
						} else if (typePrint == ReportConstants.PRINTER_NO_DIRECT) {
							exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
						}

						exporter.exportReport();
						String newPrinter = "";

						newPrinter = exporter.getPrintService().getName().trim();

						Boolean[] printStatus = exporter.getPrintStatus();

						// System.out.println("print status:" + printStatus.toString() + printStatus[0]);

						/**
						 * printStatus[0] == true; --> user press OK update Print Flag
						 * */
						if (printStatus[0]) {
							if (StringUtils.isValid(newPrinter) && newPrinter.equalsIgnoreCase(ReportConstants.DEFAULT_PRINTER.trim()) == false) {

								hanbaiPrinterService.setDefaultPrinter(ApplicationConstants.LOGIN_USER_ID, ApplicationConstants.PRINT_MENU_GRP,
										ApplicationConstants.PRINT_MENU_EXE, newPrinter);

								ReportConstants.DEFAULT_PRINTER = newPrinter.toUpperCase();
								// update Map
								PrinterService printerService = new PrinterServiceImpl();
								Map<String, FPrinterVo> mapPrinterVo = printerService.getMapFPrinterVo(ApplicationConstants.LOGIN_USER_ID);
								FPrinterConstants.setMapPrinterVo(mapPrinterVo);
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						if (log.isErrorEnabled())
							log.error("Print error.", ex);

						JOptionPane.showMessageDialog(ProjectJRViewer.this, getBundleString("error.printing"));
					} finally {
						ProjectJRViewer.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						btnPrint.setEnabled(true);
						btnPrint1.setEnabled(true);
					}
				}
			});

			thread.start();
		}
	}

	/**
	 * @author PC12
	 * 
	 */
	class StopAction extends AbstractAction {
		/** */
		private static final long serialVersionUID = 1L;
		/** */
		private JFrame parentFrame;
		/** */
		private JPanel panel;

		public StopAction(String name) {
			super(name);
		}

		public StopAction(String name, JPanel jpanel, JFrame parentFrame) {
			super(name);
			this.panel = jpanel;
			this.parentFrame = parentFrame;
		}

		public void actionPerformed(ActionEvent e) {

			stopTimer();
			if (this.panel != null) {
				SwingUtils.getParentFrame(panel).dispose();
			}

			if (this.parentFrame != null) {
				this.parentFrame.setEnabled(true);
				this.parentFrame.setVisible(true);
			}
			
			if (isRequiredConfirm) {
				if (this.parentFrame != null) {
					this.parentFrame.toFront();
				}
				MessageBox.warning(parentFrame, MCtlConstants.getValueByCKey("WARNING"));
			}
		}

		public void setParentFrame(JFrame parentFrame) {
			this.parentFrame = parentFrame;
		}
	}

	/**
	 * @author PC12
	 * 
	 */
	public class EndAction extends AbstractAction {
		private JFrame parentFrame;
		private JPanel panel;

		/** */
		private static final long serialVersionUID = 1L;

		public EndAction(String name) {
			super(name);
		}

		public EndAction(String name, JPanel panel) {
			super(name);
			this.panel = panel;
		}

		public EndAction(JPanel jpanel) {
			super();
			this.panel = jpanel;
		}

		public EndAction(String name, JPanel jpanel, JFrame parentFrame) {
			super(name);
			this.panel = jpanel;
			this.parentFrame = parentFrame;
		}

		public void actionPerformed(ActionEvent e) {
			stopTimer();
			if (isRequiredConfirm) {
				if (this.panel != null) {
					SwingUtils.getParentFrame(panel).dispose();
				}

				if (this.parentFrame != null) {
					this.parentFrame.setEnabled(true);
					this.parentFrame.setVisible(true);
					this.parentFrame.toFront();
				}
				MessageBox.warning(parentFrame, MCtlConstants.getValueByCKey("WARNING"));
			} else {
				if (parentFrame != null) {
					this.parentFrame.setEnabled(false);
					this.parentFrame.setVisible(false);
					parentFrame = null;
				}
				if (this.panel != null) {
					SwingUtils.getParentFrame(panel).dispose();
				}
			}

			ApplicationConstants.closeFile();

		}

		public void setParentFrame(JFrame parentFrame) {
			this.parentFrame = parentFrame;
		}

	}

	/**
	 * @author PC12
	 * 
	 */
	class GoLastAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public GoLastAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			setPageIndex(jasperPrint.getPages().size() - 1);
			refreshPage();
		}
	}

	/**
	 * @author PC12
	 * 
	 */
	class GoNextAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public GoNextAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			setPageIndex(pageIndex + 1);
			refreshPage();
		}
	}

	/**
	 * @author PC12
	 * 
	 */
	class GoPreviousAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public GoPreviousAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			setPageIndex(pageIndex - 1);
			refreshPage();
		}
	}

	/**
	 * @author PC12
	 * 
	 */
	class GoFirstAction extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public GoFirstAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			setPageIndex(0);
			refreshPage();
		}
	}

	void btnReloadActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_btnReloadActionPerformed
	{// GEN-HEADEREND:event_btnReloadActionPerformed
		// Add your handling code here:
		if (type == TYPE_FILE_NAME) {
			try {
				loadReport(reportFileName, isXML);
			} catch (JRException e) {
				if (log.isErrorEnabled())
					log.error("Reload error.", e);

				jasperPrint = null;
				setPageIndex(0);
				refreshPage();

				JOptionPane.showMessageDialog(this, getBundleString("error.loading"));
			}

			forceRefresh();
		}
	}// GEN-LAST:event_btnReloadActionPerformed

	protected void forceRefresh() {
		zoom = 0;// force pageRefresh()
		realZoom = 0f;
		setZoomRatio(1);
	}

	void btnZoomInActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_btnZoomInActionPerformed
	{// GEN-HEADEREND:event_btnZoomInActionPerformed
		// Add your handling code here:
		btnActualSize.setSelected(false);
		btnFitPage.setSelected(false);
		btnFitWidth.setSelected(false);

		int newZoomInt = (int) (100 * getZoomRatio());
		int index = Arrays.binarySearch(zooms, newZoomInt);
		if (index < 0) {
			setZoomRatio(zooms[-index - 1] / 100f);
		} else if (index < cbbZoom.getModel().getSize() - 1) {
			setZoomRatio(zooms[index + 1] / 100f);
		}
	}// GEN-LAST:event_btnZoomInActionPerformed

	void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_btnZoomOutActionPerformed
	{// GEN-HEADEREND:event_btnZoomOutActionPerformed
		// Add your handling code here:
		btnActualSize.setSelected(false);
		btnFitPage.setSelected(false);
		btnFitWidth.setSelected(false);

		int newZoomInt = (int) (100 * getZoomRatio());
		int index = Arrays.binarySearch(zooms, newZoomInt);
		if (index > 0) {
			setZoomRatio(zooms[index - 1] / 100f);
		} else if (index < -1) {
			setZoomRatio(zooms[-index - 2] / 100f);
		}
	}// GEN-LAST:event_btnZoomOutActionPerformed

	void cbbZoomActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_cbbZoomActionPerformed
	{// GEN-HEADEREND:event_cbbZoomActionPerformed
		// Add your handling code here:
		float newZoom = getZoomRatio();

		if (newZoom < MIN_ZOOM) {
			newZoom = MIN_ZOOM;
		}

		if (newZoom > MAX_ZOOM) {
			newZoom = MAX_ZOOM;
		}

		setZoomRatio(newZoom);
	}// GEN-LAST:event_cbbZoomActionPerformed

	/**
	*/
	void hyperlinkClicked(MouseEvent evt) {
		JPanel link = (JPanel) evt.getSource();
		JRPrintHyperlink element = (JRPrintHyperlink) linksMap.get(link);
		hyperlinkClicked(element);
	}

	protected void hyperlinkClicked(JRPrintHyperlink hyperlink) {
		try {
			JRHyperlinkListener listener = null;
			for (int i = 0; i < hyperlinkListeners.size(); i++) {
				listener = (JRHyperlinkListener) hyperlinkListeners.get(i);
				listener.gotoHyperlink(hyperlink);
			}
		} catch (JRException e) {
			if (log.isErrorEnabled())
				log.error("Hyperlink click error.", e);

			JOptionPane.showMessageDialog(this, getBundleString("error.hyperlink"));
		}
	}

	/**
	*/
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	*/
	private void setPageIndex(int index) {
		if (jasperPrint != null && jasperPrint.getPages() != null && jasperPrint.getPages().size() > 0) {
			if (index >= 0 && index < jasperPrint.getPages().size()) {
				pageIndex = index;
				pageError = false;
				btnFirst.setEnabled((pageIndex > 0));
				btnFirst1.setEnabled((pageIndex > 0));

				btnPrevious.setEnabled((pageIndex > 0));
				btnPrevious1.setEnabled((pageIndex > 0));

				btnNext.setEnabled((pageIndex < jasperPrint.getPages().size() - 1));
				btnNext1.setEnabled((pageIndex < jasperPrint.getPages().size() - 1));
				btnLast.setEnabled((pageIndex < jasperPrint.getPages().size() - 1));
				btnLast1.setEnabled((pageIndex < jasperPrint.getPages().size() - 1));

				txtGoTo.setEnabled(btnFirst.isEnabled() || btnLast.isEnabled());
				txtGoTo.setText("" + (pageIndex + 1));
//				lblStatus.setText(MessageFormat.format(getBundleString("page"), new Object[] { new Integer(pageIndex + 1),
//						new Integer(jasperPrint.getPages().size()) }));
				int page = pageIndex + 1;
				lblStatus.setText(StringUtils.convertIntegerToStr(page) + " / " +StringUtils.convertIntegerToStr(jasperPrint.getPages().size()));
			}
		} else {
			btnFirst.setEnabled(false);
			btnFirst1.setEnabled(false);
			btnPrevious.setEnabled(false);
			btnPrevious1.setEnabled(false);
			btnNext.setEnabled(false);
			btnNext1.setEnabled(false);
			btnLast.setEnabled(false);
			btnLast1.setEnabled(false);
			txtGoTo.setEnabled(false);
			txtGoTo.setText("");
			lblStatus.setText("");
		}
	}

	/**
	*/
	protected void loadReport(String fileName, boolean isXmlReport) throws JRException {
		if (isXmlReport) {
			jasperPrint = JRPrintXmlLoader.load(fileName);
		} else {
			jasperPrint = (JasperPrint) JRLoader.loadObject(fileName);
		}

		type = TYPE_FILE_NAME;
		this.isXML = isXmlReport;
		reportFileName = fileName;
		fileResolver = new SimpleFileResolver(Arrays.asList(new File[] { new File(fileName).getParentFile(), new File(".") }));
		fileResolver.setResolveAbsolutePath(true);
		btnReload.setEnabled(true);
		setPageIndex(0);
	}

	/**
	*/
	protected void loadReport(InputStream is, boolean isXmlReport) throws JRException {
		if (isXmlReport) {
			jasperPrint = JRPrintXmlLoader.load(is);
		} else {
			jasperPrint = (JasperPrint) JRLoader.loadObject(is);
		}

		type = TYPE_INPUT_STREAM;
		this.isXML = isXmlReport;
		btnReload.setEnabled(false);
		setPageIndex(0);
	}

	/**
	*/
	protected void loadReport(JasperPrint jrPrint) {
		jasperPrint = jrPrint;
		type = TYPE_OBJECT;
		isXML = false;
		btnReload.setEnabled(false);
		setPageIndex(0);
	}

	/**
	*/
	protected void refreshPage() {
		if (jasperPrint == null || jasperPrint.getPages() == null || jasperPrint.getPages().size() == 0) {
			pnlPage.setVisible(false);
			btnSave.setEnabled(false);
			btnPrint.setEnabled(false);
			btnActualSize.setEnabled(false);
			btnFitPage.setEnabled(false);
			btnFitWidth.setEnabled(false);
			btnZoomIn.setEnabled(false);
			btnZoomOut.setEnabled(false);
			cbbZoom.setEnabled(false);

			if (jasperPrint != null) {
				JOptionPane.showMessageDialog(this, getBundleString("no.pages"));
			}

			return;
		}

		pnlPage.setVisible(true);
		btnSave.setEnabled(true);
		btnPrint.setEnabled(true);
		btnPrint1.setEnabled(true);
		btnActualSize.setEnabled(true);
		btnFitPage.setEnabled(true);
		btnFitWidth.setEnabled(true);
		btnZoomIn.setEnabled(zoom < MAX_ZOOM);
		btnZoomOut.setEnabled(zoom > MIN_ZOOM);
		cbbZoom.setEnabled(true);

		Dimension dim = new Dimension((int) (jasperPrint.getPageWidth() * realZoom) + 8, // 2 from border, 5 from shadow and 1 extra pixel for image
				(int) (jasperPrint.getPageHeight() * realZoom) + 8);
		pnlPage.setMaximumSize(dim);
		pnlPage.setMinimumSize(dim);
		pnlPage.setPreferredSize(dim);

		long maxImageSize = JRProperties.getLongProperty(VIEWER_RENDER_BUFFER_MAX_SIZE);
		boolean renderImage;
		if (maxImageSize <= 0) {
			renderImage = false;
		} else {
			long imageSize = JRPrinterAWT.getImageSize(jasperPrint, realZoom);
			renderImage = imageSize <= maxImageSize;
		}

		lblPage.setRenderImage(renderImage);

		if (renderImage) {
			setPageImage();
		}

		pnlLinks.removeAll();
		linksMap = new HashMap();

		createHyperlinks();

		if (!renderImage) {
			lblPage.setIcon(null);

			pnlMain.validate();
			pnlMain.repaint();
		}
	}

	protected void setPageImage() {
		Image image;
		if (pageError) {
			image = getPageErrorImage();
		} else {
			try {
				image = JasperPrintManager.printPageToImage(jasperPrint, pageIndex, realZoom);
			} catch (Exception e) {
				if (log.isErrorEnabled())
					log.error("Print page to image error.", e);

				pageError = true;

				image = getPageErrorImage();
				JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("net/sf/jasperreports/view/viewer").getString("error.displaying"));
			}
		}
		ImageIcon imageIcon = new ImageIcon(image);
		lblPage.setIcon(imageIcon);
	}

	protected Image getPageErrorImage() {
		Image image = new BufferedImage((int) (jasperPrint.getPageWidth() * realZoom) + 1, (int) (jasperPrint.getPageHeight() * realZoom) + 1,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D grx = (Graphics2D) image.getGraphics();
		AffineTransform transform = new AffineTransform();
		transform.scale(realZoom, realZoom);
		grx.transform(transform);

		drawPageError((Graphics2D) grx);

		return image;
	}

	protected void createHyperlinks() {
		java.util.List pages = jasperPrint.getPages();
		JRPrintPage page = (JRPrintPage) pages.get(pageIndex);
		createHyperlinks(page.getElements(), 0, 0);
	}

	protected void createHyperlinks(List elements, int offsetX, int offsetY) {
		if (elements != null && elements.size() > 0) {
			for (Iterator it = elements.iterator(); it.hasNext();) {
				JRPrintElement element = (JRPrintElement) it.next();

				JRImageMapRenderer imageMap = null;
				if (element instanceof JRPrintImage) {
					JRRenderable renderer = ((JRPrintImage) element).getRenderer();
					if (renderer instanceof JRImageMapRenderer) {
						imageMap = (JRImageMapRenderer) renderer;
						if (!imageMap.hasImageAreaHyperlinks()) {
							imageMap = null;
						}
					}
				}
				boolean hasImageMap = imageMap != null;

				JRPrintHyperlink hyperlink = null;
				if (element instanceof JRPrintHyperlink) {
					hyperlink = (JRPrintHyperlink) element;
				}
				boolean hasHyperlink = !hasImageMap && hyperlink != null && hyperlink.getHyperlinkType() != JRHyperlink.HYPERLINK_TYPE_NONE;
				boolean hasTooltip = hyperlink != null && hyperlink.getHyperlinkTooltip() != null;

				if (hasHyperlink || hasImageMap || hasTooltip) {
					JPanel link;
					if (hasImageMap) {
						Rectangle renderingArea = new Rectangle(0, 0, element.getWidth(), element.getHeight());
						link = new ImageMapPanel(renderingArea, imageMap);
					} else // hasImageMap
					{
						link = new JPanel();
						if (hasHyperlink) {
							link.addMouseListener(mouseListener);
						}
					}

					if (hasHyperlink) {
						link.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					link.setLocation((int) ((element.getX() + offsetX) * realZoom), (int) ((element.getY() + offsetY) * realZoom));
					link.setSize((int) (element.getWidth() * realZoom), (int) (element.getHeight() * realZoom));
					link.setOpaque(false);

					String toolTip = getHyperlinkTooltip(hyperlink);
					if (toolTip == null && hasImageMap) {
						toolTip = "";// not null to register the panel as having a tool tip
					}
					link.setToolTipText(toolTip);

					pnlLinks.add(link);
					linksMap.put(link, element);
				}

				if (element instanceof JRPrintFrame) {
					JRPrintFrame frame = (JRPrintFrame) element;
					int frameOffsetX = offsetX + frame.getX() + frame.getLineBox().getLeftPadding().intValue();
					int frameOffsetY = offsetY + frame.getY() + frame.getLineBox().getTopPadding().intValue();
					createHyperlinks(frame.getElements(), frameOffsetX, frameOffsetY);
				}
			}
		}
	}

	protected class ImageMapPanel extends JPanel implements MouseListener, MouseMotionListener {
		private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

		protected final List imageAreaHyperlinks;

		public ImageMapPanel(Rectangle renderingArea, JRImageMapRenderer imageMap) {
			try {
				imageAreaHyperlinks = imageMap.getImageAreaHyperlinks(renderingArea);// FIXMECHART
			} catch (JRException e) {
				throw new JRRuntimeException(e);
			}

			addMouseListener(this);
			addMouseMotionListener(this);
		}

		public String getToolTipText(MouseEvent event) {
			String tooltip = null;
			JRPrintImageAreaHyperlink imageMapArea = getImageMapArea(event);
			if (imageMapArea != null) {
				tooltip = getHyperlinkTooltip(imageMapArea.getHyperlink());
			}

			if (tooltip == null) {
				tooltip = super.getToolTipText(event);
			}

			return tooltip;
		}

		public void mouseDragged(MouseEvent e) {
			pnlLinksMouseDragged(e);
		}

		public void mouseMoved(MouseEvent e) {
			JRPrintImageAreaHyperlink imageArea = getImageMapArea(e);
			if (imageArea != null && imageArea.getHyperlink().getHyperlinkType() != JRHyperlink.HYPERLINK_TYPE_NONE) {
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				e.getComponent().setCursor(Cursor.getDefaultCursor());
			}
		}

		protected JRPrintImageAreaHyperlink getImageMapArea(MouseEvent e) {
			return getImageMapArea((int) (e.getX() / realZoom), (int) (e.getY() / realZoom));
		}

		protected JRPrintImageAreaHyperlink getImageMapArea(int x, int y) {
			JRPrintImageAreaHyperlink image = null;
			if (imageAreaHyperlinks != null) {
				for (ListIterator it = imageAreaHyperlinks.listIterator(imageAreaHyperlinks.size()); image == null && it.hasPrevious();) {
					JRPrintImageAreaHyperlink area = (JRPrintImageAreaHyperlink) it.previous();
					if (area.getArea().containsPoint(x, y)) {
						image = area;
					}
				}
			}
			return image;
		}

		public void mouseClicked(MouseEvent e) {
			JRPrintImageAreaHyperlink imageMapArea = getImageMapArea(e);
			if (imageMapArea != null) {
				hyperlinkClicked(imageMapArea.getHyperlink());
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			pnlLinksMousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			e.getComponent().setCursor(Cursor.getDefaultCursor());
			pnlLinksMouseReleased(e);
		}
	}

	protected String getHyperlinkTooltip(JRPrintHyperlink hyperlink) {
		String toolTip;
		toolTip = hyperlink.getHyperlinkTooltip();
		if (toolTip == null) {
			toolTip = getFallbackTooltip(hyperlink);
		}
		return toolTip;
	}

	protected String getFallbackTooltip(JRPrintHyperlink hyperlink) {
		String toolTip = null;
		switch (hyperlink.getHyperlinkType()) {
		case JRHyperlink.HYPERLINK_TYPE_REFERENCE: {
			toolTip = hyperlink.getHyperlinkReference();
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_LOCAL_ANCHOR: {
			if (hyperlink.getHyperlinkAnchor() != null) {
				toolTip = "#" + hyperlink.getHyperlinkAnchor();
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_LOCAL_PAGE: {
			if (hyperlink.getHyperlinkPage() != null) {
				toolTip = "#page " + hyperlink.getHyperlinkPage();
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_REMOTE_ANCHOR: {
			toolTip = "";
			if (hyperlink.getHyperlinkReference() != null) {
				toolTip = toolTip + hyperlink.getHyperlinkReference();
			}
			if (hyperlink.getHyperlinkAnchor() != null) {
				toolTip = toolTip + "#" + hyperlink.getHyperlinkAnchor();
			}
			break;
		}
		case JRHyperlink.HYPERLINK_TYPE_REMOTE_PAGE: {
			toolTip = "";
			if (hyperlink.getHyperlinkReference() != null) {
				toolTip = toolTip + hyperlink.getHyperlinkReference();
			}
			if (hyperlink.getHyperlinkPage() != null) {
				toolTip = toolTip + "#page " + hyperlink.getHyperlinkPage();
			}
			break;
		}
		default: {
			break;
		}
		}
		return toolTip;
	}

	/**
	*/
	private void emptyContainer(Container container) {
		Component[] components = container.getComponents();

		if (components != null) {
			for (int i = 0; i < components.length; i++) {
				if (components[i] instanceof Container) {
					emptyContainer((Container) components[i]);
				}
			}
		}

		components = null;
		container.removeAll();
		container = null;
	}

	/**
	*/
	private float getZoomRatio() {
		float newZoom = zoom;

		try {
			newZoom = zoomDecimalFormat.parse(String.valueOf(cbbZoom.getEditor().getItem())).floatValue() / 100f;
		} catch (ParseException e) {
		}

		return newZoom;
	}

	/**
	*/
	public void setZoomRatio(float newZoom) {
		if (newZoom > 0) {
			cbbZoom.getEditor().setItem(zoomDecimalFormat.format(newZoom * 100) + "%");

			if (zoom != newZoom) {
				zoom = newZoom;
				realZoom = zoom * screenResolution / REPORT_RESOLUTION;

				refreshPage();
			}
		}
	}

	/**
	*/
	private void setRealZoomRatio(float newZoom) {
		if (newZoom > 0 && realZoom != newZoom) {
			zoom = newZoom * REPORT_RESOLUTION / screenResolution;
			realZoom = newZoom;

			cbbZoom.getEditor().setItem(zoomDecimalFormat.format(zoom * 100) + "%");

			refreshPage();
		}
	}

	/**
	 *
	 */
	public void setFitWidthZoomRatio() {
		if (pnlInScroll.getVisibleRect().getWidth() == 0) {
			setRealZoomRatio((jasperPrint.getPageWidth() - 20f) / jasperPrint.getPageWidth());
		} else
			setRealZoomRatio(((float) pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth());
	}

	public void setFitPageZoomRatio() {
		setRealZoomRatio(((float) pnlInScroll.getVisibleRect().getHeight() - 20f) / jasperPrint.getPageHeight());
	}

	/**
	 * 
	 */
	protected JRGraphics2DExporter getGraphics2DExporter() throws JRException {
		return new JRGraphics2DExporter();
	}

	/**
	 * 
	 */
	protected void paintPage(Graphics2D grx) {
		if (pageError) {
			paintPageError(grx);
			return;
		}

		try {
			if (exporter == null) {
				exporter = getGraphics2DExporter();
			} else {
				exporter.reset();
			}

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, grx.create());
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(realZoom));
			exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(1)); // lblPage border
			exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(1));
			if (type == TYPE_FILE_NAME) {
				exporter.setParameter(JRExporterParameter.FILE_RESOLVER, fileResolver);
			}
			exporter.exportReport();
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Page paint error.", e);

			pageError = true;

			paintPageError(grx);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JOptionPane.showMessageDialog(ProjectJRViewer.this, getBundleString("error.displaying"));
				}
			});
		}

	}

	protected void paintPageError(Graphics2D grx) {
		AffineTransform origTransform = grx.getTransform();

		AffineTransform transform = new AffineTransform();
		transform.translate(1, 1);
		transform.scale(realZoom, realZoom);
		grx.transform(transform);

		try {
			drawPageError(grx);
		} finally {
			grx.setTransform(origTransform);
		}
	}

	protected void drawPageError(Graphics grx) {
		grx.setColor(Color.white);
		grx.fillRect(0, 0, jasperPrint.getPageWidth() + 1, jasperPrint.getPageHeight() + 1);
	}

	protected void keyNavigate(KeyEvent evt) {
		boolean refresh = true;
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_PAGE_DOWN:
			dnNavigate(evt);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_PAGE_UP:
			upNavigate(evt);
			break;
		case KeyEvent.VK_HOME:
			homeEndNavigate(0);
			break;
		case KeyEvent.VK_END:
			homeEndNavigate(jasperPrint.getPages().size() - 1);
			break;
		default:
			refresh = false;
		}

		if (refresh) {
			refreshPage();
		}
	}

	private void dnNavigate(KeyEvent evt) {
		int bottomPosition = scrollPane.getVerticalScrollBar().getValue();
		scrollPane.dispatchEvent(evt);
		if ((scrollPane.getViewport().getHeight() > pnlPage.getHeight() || scrollPane.getVerticalScrollBar().getValue() == bottomPosition)
				&& pageIndex < jasperPrint.getPages().size() - 1) {
			setPageIndex(pageIndex + 1);
			if (scrollPane.isEnabled())
				scrollPane.getVerticalScrollBar().setValue(0);
		}
	}

	private void upNavigate(KeyEvent evt) {
		if ((scrollPane.getViewport().getHeight() > pnlPage.getHeight() || scrollPane.getVerticalScrollBar().getValue() == 0) && pageIndex > 0) {
			setPageIndex(pageIndex - 1);
			if (scrollPane.isEnabled())
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		} else {
			scrollPane.dispatchEvent(evt);
		}
	}

	private void homeEndNavigate(int pageNumber) {
		setPageIndex(pageNumber);
		if (scrollPane.isEnabled())
			scrollPane.getVerticalScrollBar().setValue(0);
	}

	/**
	 *
	*/
	private void fitPage() {
		float heightRatio = ((float) pnlInScroll.getVisibleRect().getHeight() - 20f) / jasperPrint.getPageHeight();
		float widthRatio = ((float) pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth();
		setRealZoomRatio(heightRatio < widthRatio ? heightRatio : widthRatio);
	}

	/**
	*/
	class PageRenderer extends JLabel {
		private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

		private boolean renderImage;
		ProjectJRViewer viewer = null;

		public PageRenderer(ProjectJRViewer viewer) {
			this.viewer = viewer;
		}

		public void paintComponent(Graphics g) {
			if (isRenderImage()) {
				super.paintComponent(g);
			} else {
				viewer.paintPage((Graphics2D) g.create());
			}
		}

		public boolean isRenderImage() {
			return renderImage;
		}

		public void setRenderImage(boolean renderImage) {
			this.renderImage = renderImage;
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	protected javax.swing.JToggleButton btnActualSize;
	protected javax.swing.JButton btnFirst;
	protected javax.swing.JToggleButton btnFitPage;
	protected javax.swing.JToggleButton btnFitWidth;
	protected javax.swing.JButton btnLast;
	protected javax.swing.JButton btnNext;
	protected javax.swing.JButton btnPrevious;
	protected javax.swing.JButton btnPrint;
	protected javax.swing.JButton btnReload;
	protected javax.swing.JButton btnSave;
	protected javax.swing.JButton btnZoomIn;
	protected javax.swing.JButton btnZoomOut;
	protected javax.swing.JComboBox cbbZoom;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private PageRenderer lblPage;
	protected javax.swing.JLabel lblStatus;
	private javax.swing.JPanel pnlInScroll;
	private javax.swing.JPanel pnlLinks;
	private javax.swing.JPanel pnlMain;
	private javax.swing.JPanel pnlPage;
	protected javax.swing.JPanel pnlSep01;
	protected javax.swing.JPanel pnlSep02;
	protected javax.swing.JPanel pnlSep03;
	protected javax.swing.JPanel pnlSep04;
	protected javax.swing.JPanel pnlStatus;
	private javax.swing.JScrollPane scrollPane;
	protected javax.swing.JPanel tlbToolBar;
	protected javax.swing.JTextField txtGoTo;
	// End of variables declaration//GEN-END:variables

	// Start of Hanbai Project
	protected javax.swing.JButton btnFirst1;
	protected javax.swing.JButton btnPrevious1;
	protected javax.swing.JButton btnNext1;
	protected javax.swing.JButton btnLast1;
	protected javax.swing.JButton btnF5;
	protected javax.swing.JButton btnF6;
	protected javax.swing.JButton btnF7;
	protected javax.swing.JButton btnPrint1;
	protected javax.swing.JButton btnF9;
	protected javax.swing.JButton btnF10;
	protected javax.swing.JButton btnF11;
	protected javax.swing.JButton btnF12;
	protected javax.swing.JPanel pnlSep05;
	protected javax.swing.JPanel pnlSep06;
	protected javax.swing.JPanel pnlSep07;
	protected javax.swing.JPanel pnlSep08;
	protected javax.swing.JPanel pnlSep09;
	protected javax.swing.JPanel pnlSep10;
	protected javax.swing.JPanel pnlSep11;
	protected javax.swing.JPanel pnlSep12;
	protected javax.swing.JPanel pnlSep13;
	protected javax.swing.JPanel pnlSep14;
	protected javax.swing.JPanel pnlSep15;

	// Status bar
	protected BasePanel footerMain;
	protected BasePanel footerStatusPnl;
	protected BasePanel statusBar;
	protected BaseLabel lblHelpInfor;
	protected BaseLabel lblDate;
	protected BaseLabel lblUser;
	protected BaseLabel lblPc;

	private Timer timer;

	protected BasePanel getFooter() {
		// パネルの生成
		footerStatusPnl = new BasePanel();

		footerStatusPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));
		footerStatusPnl.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
		int xPos = 1;
		int yPos = 1;

		statusBar = new BasePanel();
		statusBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
		statusBar.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);

		lblHelpInfor = new BaseLabel();
		lblHelpInfor.setVerticalAlignment(BaseLabel.CENTER);
		lblHelpInfor
				.setText("                                                                                                                                                                                                        ");
		lblHelpInfor.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblHelpInfor.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblHelpInfor.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblHelpInfor);

		lblDate = new BaseLabel();
		lblDate.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblDate.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblDate.setText(getSystemDateTime());
		lblDate.setBorder(FaceContants.LABEL_BORDER);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDate.setText(getSystemDateTime());
				repaint();
			}
		});
		timer.start();
		statusBar.add(lblDate);

		lblUser = new BaseLabel();
		lblUser.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblUser.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblUser.setBorder(FaceContants.LABEL_BORDER);
		lblUser.setText(getUserName());
		statusBar.add(lblUser);

		lblPc = new BaseLabel();
		lblPc.setFont(FontConstants.STATUS_BAR_LABEL_FONT);
		lblPc.setText(getPCName());
		lblPc.setBackground(ColorConstants.STATUS_BAR_DEFAULT_COLOR);
		lblPc.setBorder(FaceContants.LABEL_BORDER);
		statusBar.add(lblPc);
		footerStatusPnl.add(statusBar);

		return footerStatusPnl;
	}

	protected String getUserName() {
		if (ApplicationConstants.loginUser != null) {
			return ApplicationConstants.loginUser.getUserId();
		} else {
			return "";
		}
	}

	protected String getPCName() {
		if (ApplicationConstants.loginUser != null) {
			return ApplicationConstants.loginUser.getLoginPC();
		} else {
			return "";
		}
	}

	protected String getSystemDateTime() {
		return DateUtils.getCurrentDateTime();
	}

	/**
	 * @return the lastFolder
	 */
	public File getLastFolder() {
		return lastFolder;
	}

	/**
	 * @param lastFolder
	 *            the lastFolder to set
	 */
	public void setLastFolder(File lastFolder) {
		this.lastFolder = lastFolder;
	}

	/**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @param parentFrame
	 *            the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		stopAction.setParentFrame(parentFrame);
		endAction.setParentFrame(parentFrame);
	}

	public void stopTimer() {
		if (timer != null && timer.isRunning()) {
			timer.stop();
			timer = null;
		}
	}
}
