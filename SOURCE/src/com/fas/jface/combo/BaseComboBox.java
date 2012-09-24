/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：InspectComboBox.java
*
*     記述				：
*     
*     作成日			：2010/02/15   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.combo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.StringUtils;
import com.fas.vo.base.ComboObjectVo;

/**
 * @author PC13
 *
 */
public class BaseComboBox extends JComboBox {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected int popupWidth;
	/** */
	protected int popupHeight;
	/** */
	private Border DEFAULT_BORDER;
	/** */
	public int LEVEL1_LENGTH = 10;
	/** */
	public int LEVEL2_LENGTH = 30;	
	/** */
	public int LEVEL3_LENGTH = 30;
	/** */
	public static int CODE_SHOW_TYPE = 1;
	/**値１を表示する */
	public static int VALUE1_SHOW_TYPE = 2;	
	/**値２を表示する */
	public static int VALUE2_SHOW_TYPE = 3;	
	/**コード&&値１を表示する */
	public static int CODE_VALUE1_SHOW_TYPE = 4;
	/** */
	private boolean isKeyDownShowPopup = true;
	/** */
	private boolean isKeyUpShowPopup = true;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param aModel
	 */
	public BaseComboBox(ComboBoxModel aModel) {
	    super(aModel);		   
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new ComboBoxRenderer());
	    init();
	}
		
	public BaseComboBox(ComboBoxModel aModel, int showType) {
	    super(aModel);	    
	    if (aModel instanceof ArrayListComboBoxModel) {
	    	ArrayListComboBoxModel model = (ArrayListComboBoxModel) aModel;
	    	LEVEL1_LENGTH = model.LEVEL1_LENGTH;
	    	LEVEL2_LENGTH = model.LEVEL2_LENGTH;
	    	LEVEL3_LENGTH = model.LEVEL3_LENGTH;	    	
	    }	  
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new ComboBoxRenderer(showType, LEVEL1_LENGTH, LEVEL2_LENGTH, LEVEL3_LENGTH));
	    init();
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param items
	 */
	public BaseComboBox(final Object[] items) {
	    super(items);
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new ComboBoxRenderer());
	    init();
	}
	  
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param items
	 */
	public BaseComboBox(Vector items) {
	    super(items);
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new ComboBoxRenderer());
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
	private void init() {
		
		DEFAULT_BORDER = getBorder();
		setSize(FaceContants.COMBOX_WIDTH, FaceContants.TEXT_HEIGHT);
		setPopupWidth(FaceContants.POPUP_WIDTH);
		setFont(FontConstants.COMBOBOX_FONT);
        //setPopupHeight(countComponents() * FaceContants.LABLE_HEIGHT);
		addFocusListener(new FocusListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
			 */
			public void focusGained(FocusEvent e) {
				if (isEnabled()) {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR);
				} else {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(FaceContants.DOT_LINE_BORDER);
			}

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
			 */
			public void focusLost(FocusEvent e) {
				if (isEnabled()) {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_BACKGROUND_COLOR);
				} else {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(DEFAULT_BORDER);
			}
			
		});		
		
        //Make the ENTER key act like the TAB key 
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
              int key = evt.getKeyCode();
              if (isKeyDownShowPopup) {
            	  if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) showPopup();
              }
              if (isKeyUpShowPopup) {
            	  if (key == KeyEvent.VK_UP) showPopup();
              }
              if (key == KeyEvent.VK_ENTER)  transferFocus();
            }
        });	
	}
	
	 
	 
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strKey
	* </DL>
	*/
	public void setSelectedItem(String strKey) {
		
		int iSelectedIndex = 0;
		
		if (getModel() instanceof ArrayListComboBoxModel) {
			ArrayListComboBoxModel lstModel = (ArrayListComboBoxModel) getModel();
			
			if (lstModel != null) {
				 List<ComboObjectVo> lstData = (ArrayList<ComboObjectVo>)lstModel.getAnArrayList();
				if (lstData != null) {
					for (int i = 0; i < lstData.size(); i++) {
						ComboObjectVo obj = lstData.get(i);
						if (obj != null) {
							if (StringUtils.isEquals(strKey, obj.getCode())) {
								iSelectedIndex = i;
								break;
							}
						}
					}
					if (iSelectedIndex >= 0) {
						setSelectedIndex(iSelectedIndex);
					}
				}
			}
		}
		
	}
	  
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param width
	 */
	public void setPopupWidth(int width) {
	    popupWidth = width;
	}
	
	 public void setPopupHeight (int iHeight)
    {
    	popupHeight = iHeight;
    }
	
	@Override
	public Insets getInsets() {
		return new Insets(1, 2, 1, 1);
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
	public Dimension getPopupSize() {
	    Dimension size = getSize();
	    if (popupWidth < 1) popupWidth = size.width;	    
	    return new Dimension(popupWidth, size.height);
	}
	
    /* (non-Javadoc)
     * @see javax.swing.JComboBox#getSelectedIndex()
     */
    public int getSelectedIndex() {
        
        Object sObject = dataModel.getSelectedItem();
        int i;
        Object obj;
        String cStr = "";
        
        if (dataModel instanceof ArrayListComboBoxModel) {
        	cStr = ((ArrayListComboBoxModel) dataModel).getSelectedItemKey();
        } else {
	        if (sObject instanceof String) {
	        	cStr = (String) sObject;
	        }
        }
        
		for (i = 0; i < dataModel.getSize(); i++) {
			obj = dataModel.getElementAt(i);
			if (obj instanceof String) {
				String strI = StringUtils.subString((String) obj, ((ArrayListComboBoxModel) dataModel).LEVEL1_LENGTH);
				if (StringUtils.isEquals(cStr, strI)) {
					return i;
				}
			} 
//			else if (obj instanceof PipeSVo) {
//				if (sObject == null || (sObject.equals("") && ((PipeSVo) obj).getGyoNo() == 0)) {
//					return 0;
//				}
//				if (sObject != null && sObject.equals(((PipeSVo) obj).getGyoNo())) {
//					return i;
//				}
//			}
		}
		return -1;
    }

    /**
     * @return
     */
    public String getSelectedKey() {
    	return StringUtils.emptyIfNull(((ArrayListComboBoxModel) dataModel).getSelectedItemKey());
    	
    }
    
//	public PipeSVo getSelectedPipes() {
//		if (dataModel != null && dataModel instanceof GyoNoComboModel) {
//			return ((GyoNoComboModel) dataModel).getSelectedPipes();
//		}
//		return null;
//	}
	
    public String getSelectedValue1()
    {
    	return StringUtils.emptyIfNull(((ArrayListComboBoxModel) dataModel).getSelectedValue1());
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComboBox#getMaximumRowCount()
     */
    public int getMaximumRowCount() {
    	try {
	    	if(popupHeight > 0) {
	    		return popupHeight;
	    	} else {
	    		Dimension screenSize = getToolkit().getScreenSize();
	    	    int screenHeight = (int)screenSize.getHeight();
	    	    
	    	    int record = FaceContants.COMBOBOX_LIST_HEIGHT;
	    	    int topHeight, bottomHeight, pHeight;
	    	    
	    	    java.awt.Point a = getLocationOnScreen();
	    	    topHeight = a.y;
	    	    bottomHeight = screenHeight - topHeight - getHeight() - FaceContants.LABLE_HEIGHT;
	    	    
	    	    pHeight = (topHeight > bottomHeight)?topHeight:bottomHeight;
	    	    record = pHeight / FaceContants.LABLE_HEIGHT;
	    	    
//	    	    System.out.println("topHeight: " + topHeight);
//	    	    System.out.println("bottomHeight: " + bottomHeight);
//	    	    System.out.println("getMaximumRowCount: " + record);
	    	    return record;
	    	}    	
    	} catch(Exception ex){
    		return FaceContants.COMBOBOX_LIST_HEIGHT;
    	}
    }

	/**
	 * @return the isShowPopup
	 */
	public boolean isKeyDownShowPopup() {
		return isKeyDownShowPopup;
	}

	/**
	 * @param isShowPopup the isShowPopup to set
	 */
	public void setKeyDownShowPopup(boolean isKeyDownShowPopup) {
		this.isKeyDownShowPopup = isKeyDownShowPopup;
	}
	
	/**
	 * @return the isShowPopup
	 */
	public boolean isKeyUpShowPopup() {
		return isKeyUpShowPopup;
	}

	/**
	 * @param isShowPopup the isShowPopup to set
	 */
	public void setKeyUpShowPopup(boolean isKeyUpShowPopup) {
		this.isKeyUpShowPopup = isKeyUpShowPopup;
	}
}


/**
 * @author PC13
 *
 */
class SteppedComboBoxUI extends BasicComboBoxUI {
	
	public SteppedComboBoxUI() {
		super();

	}
	
	/* (non-Javadoc)
	 * @see javax.swing.plaf.metal.MetalComboBoxUI#createPopup()
	 */
	protected ComboPopup createPopup() {
		
	    BasicComboPopup popup = new BasicComboPopup( comboBox ) {
	        
	    	/** */
			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
	    	 * @see javax.swing.plaf.basic.BasicComboPopup#show()
	    	 */
	    	public void show() {
	    		Dimension popupSize = ((BaseComboBox)comboBox).getPopupSize();
	    		popupSize.setSize( popupSize.width, getPopupHeightForRowCount( comboBox.getMaximumRowCount() ) );
	    		Rectangle popupBounds = computePopupBounds( 0, comboBox.getBounds().height, popupSize.width, popupSize.height);
	    		scroller.setMaximumSize( popupBounds.getSize() );
	    		scroller.setPreferredSize( popupBounds.getSize() );
	    		scroller.setMinimumSize( popupBounds.getSize() );
	    		list.invalidate();            
	    		int selectedIndex = comboBox.getSelectedIndex();
	    		if ( selectedIndex == -1 ) {
	    			list.clearSelection();
	    		} else {
	    			list.setSelectedIndex( selectedIndex );
	    		}
	    		list.ensureIndexIsVisible( list.getSelectedIndex() );
	    		setLightWeightPopupEnabled( comboBox.isLightWeightPopupEnabled() );

	    		show( comboBox, popupBounds.x, popupBounds.y );
	    	}
	    };
	    
	    popup.getAccessibleContext().setAccessibleParent(comboBox);
	    
	    popup.getList().setSelectionBackground(ColorConstants.DEFAULT_COMBOBOX_SELECTION_BACKGROUND_COLOR);
	    popup.getList().setSelectionForeground(ColorConstants.DEFAULT_COMBOBOX_SELECTION_FOR_COLOR);
	    popup.getList().setBackground(ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR);
	    
	    return popup;
	}
}

class ComboBoxRenderer extends JLabel implements ListCellRenderer {

	/** */
	private static final long serialVersionUID = 1L;
	private int showType = 0;
	private int lenght1 = 0;
	private int lenght2 = 0;
	private int lenght3 = 0;

	public ComboBoxRenderer() {
		setOpaque(true);
		setHorizontalAlignment(LEFT);
		setVerticalAlignment(CENTER);
		setPreferredSize(new Dimension(getWidth(), FaceContants.COMBOBOX_LIST_HEIGHT));
	}
	
	public ComboBoxRenderer(int ishowType, int levelLenght1, int levelLenght2, int levelLenght3) {
		setOpaque(true);
		setHorizontalAlignment(LEFT);
		setVerticalAlignment(CENTER);
		setPreferredSize(new Dimension(getWidth(), FaceContants.COMBOBOX_LIST_HEIGHT));
		showType = ishowType;
		lenght1 = levelLenght1;
		lenght2 = levelLenght2;
		lenght3 = levelLenght3;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getInsets()
	 */
	public Insets getInsets() {
		return new Insets(1, 2, 1, 1);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}	
		
		
		if(showType == BaseComboBox.CODE_SHOW_TYPE) {
			setText( StringUtils.subString(StringUtils.objectToStr(value), 0, lenght1).trim());
		} else if (showType == BaseComboBox.VALUE1_SHOW_TYPE) {
			setText( StringUtils.subString(StringUtils.objectToStr(value), lenght1, lenght2).trim());
		} else 	if (showType == BaseComboBox.VALUE2_SHOW_TYPE) {

			setText( StringUtils.subString(StringUtils.objectToStr(value), lenght1 + lenght2, lenght3).trim());
		} else if (showType == BaseComboBox.CODE_VALUE1_SHOW_TYPE) {

			setText( StringUtils.subString(StringUtils.objectToStr(value), lenght1 + lenght2).trim());
		} else {
			
			setText(StringUtils.objectToStr(value).trim());
		}		
		
		return this;
	}
	
}
