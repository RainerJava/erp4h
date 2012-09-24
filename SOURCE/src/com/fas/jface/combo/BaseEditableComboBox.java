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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.vo.base.ComboObjectVo;

/**
 * @author PC13
 *
 */
public class BaseEditableComboBox extends JComboBox {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected int popupWidth;
	/** */
	private JFrame thisFrame;
	/** */
	protected JTextField field;
	/** */
	private Border DEFAULT_BORDER;
	/** */
	protected int popupHeight;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param aModel
	 */
	public BaseEditableComboBox(ComboBoxModel aModel) {
	    super(aModel);
	    setUI(new SteppedEditableComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new EditableComboBoxRenderer());
	    init();
	}
	
	/**
	 * @return
	 */
	public BaseEditableComboBox getComboBox() {
		return this;
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
	public BaseEditableComboBox(final Object[] items, JFrame jframe) {
	    super(items);
	    setUI(new SteppedEditableComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new EditableComboBoxRenderer());
	    thisFrame = jframe;
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
	public BaseEditableComboBox(Vector items, JFrame jframe) {
	    super(items);
	    setUI(new SteppedEditableComboBoxUI());
	    popupWidth = 0;
	    popupHeight = 0;
	    setRenderer(new EditableComboBoxRenderer());
	    thisFrame = jframe;
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
		
		this.setEditable(true);
		AutoCompleteDecorator.decorate(this); 
		setSize(FaceContants.COMBOX_WIDTH, FaceContants.TEXT_HEIGHT);
		setPopupWidth(FaceContants.POPUP_WIDTH);
		setFont(FontConstants.COMBOBOX_FONT);
		//setPopupHeight(countComponents() * FaceContants.LABLE_HEIGHT);
       
		DEFAULT_BORDER = getBorder();
        field = (JTextField) getEditor().getEditorComponent();
        
        field.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						int code = e.getKeyCode();
						// set event for pgUp - pgDn
						if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
							showPopup();
							String strSelect = StringUtils.objectToStr(((ArrayListComboBoxModel) dataModel).getSelectedItem());
							
							if(code == KeyEvent.VK_DOWN) {
								int cbbSize = dataModel.getSize();
								if(strSelect.trim().equals("") && cbbSize >= 1 ){
									//field.setText(StringUtils.objectToStr(((ArrayListComboBoxModel) dataModel).getElementAt(0)));
									field.setText("");
								} else {
									field.setText(strSelect);
								}
								
							}							
													
							field.repaint();
						} 
						// set event for press Enter key
						if (code == KeyEvent.VK_ENTER) 
			    		{
			    			if(getSelectedIndex()== -1){
			    				MessageBox.message(thisFrame, MessageConstants.getMstMsgVo("E0194"));
			    			}else{
			    				transferFocus();
			    			}
			    		} 
/*after press Enter すぐ->　transferFocus */
						
/*						if (code != KeyEvent.VK_ENTER) iCount = 1;
						// set event for press Enter key
						if (code == KeyEvent.VK_ENTER) 
			    		{
			    			if(getSelectedIndex()== -1){
			    				MessageBox.message(thisFrame, MessageConstants.getMstMsgVo("E0194"));
			    			}else{
			    				
			    				if (field.isFocusOwner()) {		
			    					//iCount ++ ;
			    					if(iCount == 1){
			    						field.requestFocus();
			    						iCount = 0;
			    					}else{
			    						field.transferFocus();
			    					}
			    				
			    				} else {
			    					getComboBox().transferFocus();
			    				}
			    			}
			    		} 
 */
					}
				});
			}
		});
		
		field.addFocusListener(new FocusListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
			 */
			public void focusGained(FocusEvent e) {
				if (isEnabled()) {
					field.setBackground(ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR);
				} else {
					field.setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(FaceContants.DOT_LINE_BORDER);
			}

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
			 */
			public void focusLost(FocusEvent e) {
				if (isEnabled()) {
					field.setBackground(ColorConstants.DEFAULT_COMBOBOX_BACKGROUND_COLOR);
				} else {
					field.setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(DEFAULT_BORDER);
			}
			
		});			
	}
	
    /* (non-Javadoc)
     * @see javax.swing.JComboBox#setSelectedIndex(int)
     */
    public void setSelectedIndex(int anIndex) {
        int size = dataModel.getSize();
        if ( anIndex == -1 ) {
            setSelectedItem( null );
        } else if ( anIndex < -1 || anIndex >= size ) {
            throw new IllegalArgumentException("setSelectedIndex: " + anIndex + " out of bounds");
        } else {
            //setSelectedItem(dataModel.getElementAt(anIndex));
			field.setText(StringUtils.objectToStr(dataModel.getElementAt(anIndex)));
			field.setCaretPosition(0);
			selectedItemChanged();
        }
    }	
	
    
    
	/**
	 * @param strKey
	 */
	public void setSelectedItem(String strKey) {
		
		int iSelectedIndex = -1;
		
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
	 public void setPopupHeight (int iHeight)
	    {
	    	popupHeight = iHeight;
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
        
        for ( i=0; i < dataModel.getSize(); i++ ) {
            obj = dataModel.getElementAt(i);
            if (obj instanceof String) {
            	String strI = StringUtils.subString((String) obj, ((ArrayListComboBoxModel) dataModel).LEVEL1_LENGTH);
            	if (StringUtils.isEquals(cStr, strI)) {
            		return i;
            	}
            } 
        }
        return -1;
    }

    /**
     * @return
     */
    public String getSelectedKey() {
    	if (getSelectedIndex() >=0) {
    		return StringUtils.emptyIfNull(((ArrayListComboBoxModel) dataModel).getSelectedItemKey());
    	} else {
    		return "";
    	}
    	
    }
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

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}
}


/**
 * @author PC13
 *
 */
class SteppedEditableComboBoxUI extends BasicComboBoxUI {
	
	public SteppedEditableComboBoxUI() {
		super();
		
		

	}
	
	/* (non-Javadoc)
	 * @see javax.swing.plaf.metal.MetalComboBoxUI#createPopup()
	 */
	protected ComboPopup createPopup() {
		
	    BasicComboPopup popup = new BasicComboPopup( comboBox ) {
	    	
	    	/** */
			private static final long serialVersionUID = 1L;
			
			
//			public KeyListener getKeyListener() {
//				return new KeyAdapter() {
//		            public void keyPressed(KeyEvent evt) {
//		            	final int key = evt.getKeyCode();
//		            	final int iSelectedIndex = list.getSelectedIndex();
//
//		            	if (key == KeyEvent.VK_ENTER) {
//		            		if (iSelectedIndex >= 0 && iSelectedIndex < comboBox.getModel().getSize()) {
//			    				((BaseEditableComboBox) comboBox).field.setText(StringUtils.objectToStr(comboBox.getModel().getElementAt(iSelectedIndex)));
//			    				((BaseEditableComboBox) comboBox).field.setCaretPosition(0);
//			    				list.clearSelection();
//			    			} else {
//			    				//((BaseEditableComboBox) comboBox).field.setText("");
//			    				list.clearSelection();
//			    			}
//		            	}
//		            }	
//		        };
//			}			
			
			/* (non-Javadoc)
	    	 * @see javax.swing.plaf.basic.BasicComboPopup#show()
	    	 */
	    	public void show() {
	    		Dimension popupSize = ((BaseEditableComboBox)comboBox).getPopupSize();
	    		popupSize.setSize( popupSize.width, getPopupHeightForRowCount( comboBox.getMaximumRowCount() ) );
	    		Rectangle popupBounds = computePopupBounds( 0, comboBox.getBounds().height, popupSize.width, popupSize.height);
	    		scroller.setMaximumSize( popupBounds.getSize() );
	    		scroller.setPreferredSize( popupBounds.getSize() );
	    		scroller.setMinimumSize( popupBounds.getSize() );
	    		list.invalidate();            
	    		
	    		int selectedIndex = -1;
	    		ArrayListComboBoxModel dataModel = (ArrayListComboBoxModel) comboBox.getModel();
	    		BaseEditableComboBox editCombo = (BaseEditableComboBox) comboBox;
	    		
	    	    int i;
	    	    Object obj;
	    	    String cStr = "";
    	        
    	        cStr = StringUtils.subString((String) editCombo.getField().getText(), dataModel.LEVEL1_LENGTH);
	    	        
    	        for ( i=0; i < dataModel.getSize(); i++ ) {
    	            obj = dataModel.getElementAt(i);
    	            if (obj instanceof String) {
    	            	String strI = StringUtils.emptyIfNull((String) obj);
    	            	if (strI.indexOf(cStr) >= 0) {
    	            		selectedIndex = i;
    	            		break;
    	            	}
    	            } 
    	        }
	    		
	    		if ( selectedIndex == -1 ) {
	    			list.clearSelection();
	    		} else {
	    			list.setSelectedIndex( selectedIndex );
	    		}
	    		list.ensureIndexIsVisible( list.getSelectedIndex() );
	    		setLightWeightPopupEnabled( comboBox.isLightWeightPopupEnabled() );
	    		
	    		show( comboBox, popupBounds.x, popupBounds.y );
	    		repaint();
	    	}
	    };
	    
	    popup.getAccessibleContext().setAccessibleParent(comboBox);
	    
	    popup.getList().setSelectionBackground(ColorConstants.DEFAULT_COMBOBOX_SELECTION_BACKGROUND_COLOR);
	    popup.getList().setSelectionForeground(ColorConstants.DEFAULT_COMBOBOX_SELECTION_FOR_COLOR);
	    popup.getList().setBackground(ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR);
	    popup.repaint();
	    return popup;
	}
}

class EditableComboBoxRenderer extends JLabel implements ListCellRenderer {

	/** */
	private static final long serialVersionUID = 1L;

	public EditableComboBoxRenderer() {
		setOpaque(true);
		setHorizontalAlignment(LEFT);
		setVerticalAlignment(CENTER);
		setPreferredSize(new Dimension(getWidth(), FaceContants.COMBOBOX_LIST_HEIGHT));
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
		setText(StringUtils.objectToStr(value).trim());
		
		return this;
	}
	
}
