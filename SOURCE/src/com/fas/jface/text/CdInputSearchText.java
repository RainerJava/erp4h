package com.fas.jface.text;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.plaf.LookAndFeelAddons;
import org.jdesktop.xswingx.BuddyButton;
import org.jdesktop.xswingx.NativeSearchFieldSupport;
import org.jdesktop.xswingx.plaf.HaSearchFieldAddon;
import org.jdesktop.xswingx.plaf.HaTextUIWrapper;

import com.fas.common.utils.StringUtils;
import com.fas.jface.button.ActionButton;
import com.fas.sapp.business.SearchTypeObj;

public class CdInputSearchText extends BaseInputText {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private ActionButton btnSearch = null;
	/** */
	private boolean numberMode = false;
	/** */
	private SearchTypeObj searchType = null;
	/** */
	private SearchTypeObj baseSearchType = null;

	/**
	 * A text field with a find icon in which the user enters text that identifies
	 */
	private JButton findButton;
	

	public CdInputSearchText() {
		super();
		init();
	}

	public CdInputSearchText(String name, int columns, int ime,int maxlength) {
		super(name, columns, ime, maxlength);
		init();
	}
	
	
	public CdInputSearchText(String name, int columns, int maxlength) {
		super(name, columns, maxlength);
		init();
	}
	
	public void setBtnSearch(ActionButton btnBase) {
		btnSearch = btnBase;
	}

    /* (non-Javadoc)
     * @see com.fas.jface.text.BaseInputText#checkMode(java.lang.String)
     */
    protected  boolean checkMode(String charString){
        char[] cr=charString.toCharArray();
        char c=cr[0];
        if (numberMode)
        	return Character.isDigit(c);
        else 
        	return StringUtils.isCode("" + c);
    }
    
	public boolean isNumberMode() {
		return numberMode;
	}

	public void setNumberMode(boolean numberMode) {
		this.numberMode = numberMode;
	}

	private void init(){
		setIMType(IM_OFF);

		LookAndFeelAddons.contribute(new HaSearchFieldAddon());

		// use the native search field if possible.
		setUseNativeSearchFieldIfPossible(true);
	}

    public void focusLost(FocusEvent e) {
    	super.focusLost(e);
    	if (btnSearch != null) {
    		SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					if (!btnSearch.isFocusOwner()) {
						btnSearch.setEnabled(false);
					}
				}
    		});
    	}
    }

    public void focusGained(FocusEvent e) {
    	super.focusGained(e);
    	if (btnSearch != null) {
    		SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					if (!btnSearch.isFocusOwner()) {						
						btnSearch.setEnabled(true);
						
					}
				}
			});
    		if (searchType != null && baseSearchType != null) {
    			searchType.setSearchType(baseSearchType.getSearchType());
    		}    		
		}
    }
    

	public void setEditable(boolean b) {
		super.setEditable(b);
		updateButtonState();
	}

	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		updateButtonState();
	}
    
	protected void updateButtonState() {
		getFindButton().setEnabled(isEnabled() && isEditable());
		getFindButton().setVisible(isVisible());
	}
    
	public final void setFindAction(ActionListener findAction) {
		NativeSearchFieldSupport.setFindAction(this, findAction);
	}
	
	
	public final JButton getFindButton() {
		if (findButton == null) {
			findButton = createFindButton();
			findButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		return findButton;
	}

	
	protected JButton createFindButton() {
		BuddyButton btn = new BuddyButton();

		return btn;
	}
	

	public boolean isUseNativeSearchFieldIfPossible() {
		return NativeSearchFieldSupport.isSearchField(this);
	}

	public void setUseNativeSearchFieldIfPossible(boolean useNativeSearchFieldIfPossible) {
		HaTextUIWrapper.getDefaultWrapper().uninstall(this);
		NativeSearchFieldSupport.setSearchField(this, useNativeSearchFieldIfPossible);
		HaTextUIWrapper.getDefaultWrapper().install(this, true);
		updateUI();
	}

	public SearchTypeObj getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchTypeObj searchType) {
		baseSearchType = searchType;
		this.searchType = searchType;
	}

	public SearchTypeObj getBaseSearchType() {
		return baseSearchType;
	}

	public void setBaseSearchType(SearchTypeObj baseSearchType) {
		this.baseSearchType = baseSearchType;
	}
}



