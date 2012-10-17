package org.erp4h.assets;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.erp4h.dal.ConnectUtils;


public class TableData extends AbstractTableModel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    Object[][] contents;
    String[] columnNames;

	
	Class<?>[] columnClasses;
    ConnectUtils connect;
    
    public TableData() throws Exception{
    	this.connect=org.erp4h.dal.DataAccess.getDataAccess();
    	getTableContents();
    }

	@Override
    public Class<?> getColumnClass(int column){
    	Class<?> dataType=super.getColumnClass(column);
    	if(column==9){
    		dataType=Float.class;
    	}
    	else if(column==5){
    		dataType=java.util.Date.class;
    	}
    	return dataType;
    }
    @Override
    public int getColumnCount() {
    	return contents[0].length;
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public int getRowCount() {
        return contents.length;
    }
    protected void getTableContents() throws Exception {
		ResultSet rs=this.connect.Select("tblThietBi");
		ResultSetMetaData rsMeta=rs.getMetaData();
		
		ArrayList<String> colNameList=new ArrayList<String>();
		@SuppressWarnings("rawtypes")
		ArrayList<Class> colClassList=new ArrayList<Class>();
		for(int i=0;i<rsMeta.getColumnCount();i++){
			colNameList.add(rsMeta.getColumnName(i+1));
			//colClassList.add(rsMeta.getColumnType(i+1));
			System.out.println(rsMeta.getColumnType(i+1));
			switch(rsMeta.getColumnType(i+1)){
			case Types.INTEGER:
				colClassList.add(Integer.class);
				break;
			case Types.FLOAT:
				colClassList.add(Float.class);
				break;
			case Types.DOUBLE:
			case Types.REAL:
				colClassList.add(Double.class);
				break;
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				colClassList.add(Date.class);
				break;
			default:
				colClassList.add(String.class);
				break;
			}
		}
		columnNames=new String[colNameList.size()];
		colNameList.toArray(columnNames);
		
		columnClasses=new Class[colClassList.size()];
		colClassList.toArray(columnClasses);
		
		ArrayList<Object[]> rowList=new ArrayList<Object[]>();
		while(rs.next()){
			ArrayList<Object>cellList=new ArrayList<Object>();
			for(int i=0;i<columnClasses.length;i++){
				Object cellValue=null;
				if(columnClasses[i]==String.class){
					cellValue=rs.getString(columnNames[i]);
				}
				else if(columnClasses[i]==Integer.class){
					cellValue=new Integer(rs.getInt(columnNames[i]));
				}
				else if(columnClasses[i]==Float.class){
					cellValue=new Float(rs.getFloat(columnNames[i]));
				}
				else if(columnClasses[i]==Double.class){
					cellValue=new Double(rs.getDouble(columnNames[i]));
				}
				else if(columnClasses[i]==Date.class){
					cellValue=rs.getDate(columnNames[i]);
				}
				else{
					System.out.println("Khong the gan du lieu!");
				}
				cellList.add(cellValue);
			}
			Object[] cells=cellList.toArray();
			rowList.add(cells);
		}
		contents=new Object[rowList.size()][];
		for(int i=0;i<contents.length;i++){
			contents[i]=rowList.get(i);
		}
	}
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return contents[rowIndex][columnIndex];
    }
    @Override
    public boolean isCellEditable(int row,int column){
    	return true;
    }
}