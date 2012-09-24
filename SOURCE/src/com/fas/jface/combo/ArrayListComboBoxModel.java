package com.fas.jface.combo;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.fas.common.utils.StringUtils;
import com.fas.vo.base.ComboObjectVo;

public class ArrayListComboBoxModel extends AbstractListModel implements ComboBoxModel {
	
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private Object selectedItem;
	/** */
	private String selectedItemKey;
	/** */
	private List<?> anArrayList;
	/** */
	private int iLevel = 2;
	/** */
	private int iShowType = 1;
	/** */
	public int LEVEL1_LENGTH = 10;
	/** */
	public int LEVEL2_LENGTH = 30;	
	/** */
	public int LEVEL3_LENGTH = 30;
	/**コードを表示する */
	public static int CODE_SHOW_TYPE = 1;
	/**値１を表示する */
	public static int VALUE1_SHOW_TYPE = 2;	
	/**値２を表示する */
	public static int VALUE2_SHOW_TYPE = 3;	
	/**コード&&値１を表示する */
	public static int CODE_VALUE1_SHOW_TYPE = 4;		
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param arrayList
	 */
	public ArrayListComboBoxModel(List<?> arrayList) {
		anArrayList = arrayList;
		iLevel = 2;
		init();
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param arrayList
	 * @param initLevel
	 */
	public ArrayListComboBoxModel(List<?> arrayList, int initLevel) {
		anArrayList = arrayList;
		iLevel = initLevel;
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
		iShowType = CODE_SHOW_TYPE;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	public Object getSelectedItem() {
		if (selectedItem != null && selectedItem instanceof ComboObjectVo) {
			ComboObjectVo combo = (ComboObjectVo) selectedItem;
			selectedItemKey = combo.getCode();
			if (iShowType == VALUE1_SHOW_TYPE) {
				return combo.getValue1();
			} else if (iShowType == VALUE2_SHOW_TYPE) {
				return combo.getValue2();
			} else if (iShowType == CODE_VALUE1_SHOW_TYPE) {
				return combo.getCode() + combo.getValue1();
			} else {
				return combo.getCode();
			}
		} if (selectedItem instanceof String) {
			String strReturn = StringUtils.objectToStr(selectedItem).trim();
			
			if (iLevel == 1) {
				selectedItemKey = strReturn;
				return strReturn;
			} else {
				selectedItemKey = StringUtils.subString(strReturn, LEVEL1_LENGTH).trim();
				
				if (iShowType == VALUE1_SHOW_TYPE) {
					return StringUtils.subString(strReturn, LEVEL1_LENGTH, LEVEL2_LENGTH).trim();
				} else 	if (iShowType == VALUE2_SHOW_TYPE) {
					return StringUtils.subString(strReturn, LEVEL1_LENGTH + LEVEL2_LENGTH, LEVEL3_LENGTH).trim();
				} else if (iShowType == CODE_VALUE1_SHOW_TYPE) {
					return StringUtils.subString(strReturn, LEVEL1_LENGTH + LEVEL2_LENGTH).trim();
				} else {
					return StringUtils.subString(strReturn, LEVEL1_LENGTH).trim();
				}
			}
		} else {
			selectedItemKey = StringUtils.objectToStr(selectedItem);
			return selectedItem;
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
	public Object getSelectedObject() {
		if (selectedItem != null && selectedItem instanceof ComboObjectVo) {
			ComboObjectVo combo = (ComboObjectVo) selectedItem;
			return combo;
		} else {
			return null;
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param iValue
	 * @return
	 */
	public Object getObjAt(int iValue) {
    	Object obj = null;
    	if (iValue >= 0 && iValue < anArrayList.size()) {
    		obj = anArrayList.get(iValue);
    	}
    	return obj;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	public void setSelectedItem(Object newValue) {
		selectedItem = newValue;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return anArrayList.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Object getElementAt(int i) {
		
		Object obj = anArrayList.get(i);
		
		if (obj != null && obj instanceof ComboObjectVo) {
			
			ComboObjectVo combo = (ComboObjectVo) obj;
			String strReturn = "";
			
			if (iLevel == 1) {
				strReturn = combo.getCode();
			} else if (iLevel == 2) {
				strReturn = StringUtils.fillMaxLen(combo.getCode(), LEVEL1_LENGTH) + StringUtils.fillMaxLen(combo.getValue1(), LEVEL2_LENGTH);
			} else {
				strReturn = StringUtils.fillMaxLen(combo.getCode(), LEVEL1_LENGTH) + StringUtils.fillMaxLen(combo.getValue1(), LEVEL2_LENGTH) + StringUtils.fillMaxLen(combo.getValue2(), LEVEL3_LENGTH);
			} 
			
			return strReturn;
		} else {
			return obj;
		}
	}
	public String getSelectedValue1()
	{
		if (selectedItem != null && selectedItem instanceof ComboObjectVo) {
			ComboObjectVo combo = (ComboObjectVo) selectedItem;
				return combo.getValue1();
		}if (selectedItem instanceof String) {
			String strReturn = StringUtils.objectToStr(selectedItem).trim();
			return StringUtils.subString(strReturn, LEVEL1_LENGTH, LEVEL2_LENGTH).trim();
		} 
		return null;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the iShowType
	 */
	public int getIShowType() {
		return iShowType;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param showType the iShowType to set
	 */
	public void setIShowType(int showType) {
		iShowType = showType;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the selectedItemKey
	 */
	public String getSelectedItemKey() {
		return selectedItemKey;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param selectedItemKey the selectedItemKey to set
	 */
	public void setSelectedItemKey(String selectedItemKey) {
		this.selectedItemKey = selectedItemKey;
	}

	/**
	 * @return
	 */
	public int getiLevel() {
		return iLevel;
	}

	/**
	 * @param iLevel
	 */
	public void setiLevel(int iLevel) {
		this.iLevel = iLevel;
	}

	public List<?> getAnArrayList() {
		return anArrayList;
	}

	public void setAnArrayList(List<?> anArrayList) {
		this.anArrayList = anArrayList;
	}	
}