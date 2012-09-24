/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：XFrameTableModel.java
*
*     記述				：
*     
*     作成日			：2010/03/23   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.table;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author PC14
 *
 */
public class XFrameTableModel extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] headerName;
    private List data;
    
    public XFrameTableModel(){
    	super(0,0);
    }
    
    public XFrameTableModel(String[]  headerName){
        if(headerName==null){
            throw new IllegalArgumentException();
        }
        this.headerName=headerName;        
    }
    
    public XFrameTableModel(String[] headerName,List data){
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
        List list=(List)data.get(rowIndex);
        return list.get(columnIndex);
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
//    public Class getColumnClass(int columnIndex) {
//    	return getValueAt(0,columnIndex).getClass();
//    }
}
