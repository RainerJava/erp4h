/** *********************************************************************************
 *
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 *
 *     ファイル名		：CodeValue.java
 *
 *     記述				：
 *
 *     作成日			：2009/10/14
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.vo.base;

import java.io.Serializable;
import java.util.List;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC12</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class CodeValue implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String value;

    public CodeValue(){
    	
    }
    public CodeValue(String code,String value){
    	this.code=code;
        this.value=value.toString();
    }
    public int hashCode() {
        return super.hashCode();
    }
    

    /* (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return value;
    }
    
    public static CodeValue[] toListToArray(List<CodeValue> list){
        if(list==null){
            throw new IllegalArgumentException("List is NULL");
        }
        CodeValue[] values=new CodeValue[list.size()];
        for(int i=0;i<list.size();i++){
            values[i]=(CodeValue)list.get(i);
        }
        return values;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof CodeValue){
			CodeValue cv = (CodeValue) obj;
			if(cv != null){
				String c = cv.getCode();
				String v = cv.getValue();
				if(this.code != null){
					if(!this.code.equals(c))return false;
				}else{
					if(c != null)return false;
				}
				
				if(this.value != null){
					return this.value.equals(v);
				}else{
					return v == null;
				}
			}
			return false;
		}else{
			return super.equals(obj);
		}
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}