/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：InspectTableModel.java
*
*     記述				：
*     
*     作成日			：2010/01/13   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.table;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author PC12
 *
 */
public class InspectTableModel extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] headerName;
    private List data;
    
    public InspectTableModel(String[]  headerName){
        if(headerName==null){
            throw new IllegalArgumentException();
        }
        this.headerName=headerName;        
    }
    
    public InspectTableModel(String[] headerName,List data){
        if(headerName==null){
            throw new IllegalArgumentException();
        }
        this.headerName=headerName;
        this.data=data;
    }
    
    public int getRowCount() {
        if(data==null){
            return 0;
        }
        return data.size();
    }
    
    public void setValueAt(Object value,int row,int column){
        List rows=(List)data.get(row);
        rows.set(column,value);
    }
    
    public int getColumnCount() {
        return headerName.length;
    }
    public String getColumnName(int column) {
        return (String)headerName[column];
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
    	if (data != null && rowIndex < data.size()) {
    		List list=(List)data.get(rowIndex);
    		return list.get(columnIndex);
    	} else {
    		return "";
    	}
    }
    
    public List getData() {
        return data;
    }
    public void setData(List data) {
        this.data = data;
    }
    public String[] getHeaderName() {
        return headerName;
    }
    public void setHeaderName(String[] headerName) {
        this.headerName = headerName;
    }
    public Class getColumnClass(int columnIndex) {
        if(data != null){
        	return getValueAt(0,columnIndex).getClass();
        } else {
        	return super.getColumnClass(columnIndex);
        }
    }
}
