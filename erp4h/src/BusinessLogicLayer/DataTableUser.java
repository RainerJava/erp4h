package BusinessLogicLayer;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import DataTranferObject.UserDTO;


public class DataTableUser extends AbstractTableModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8246256321563064214L;
	
    Object[][] data;
    String[] colName;
	Class<?>[] colClass;
	UserBLL bllUser;
	ArrayList<UserDTO> arrUser;
	public DataTableUser() throws Exception{
		colName=bllUser.getColumnName();
		arrUser=bllUser.getUserArray();
		data=new Object[bllUser.getRowCount()][];
		ArrayList<Object[]> rowList=new ArrayList<Object[]>();
		for(int i=0;i<arrUser.size();i++){
			UserDTO dtoUser=arrUser.get(i);
			Object cellValue=dtoUser.getUserID();
			rowList.add((Object[]) cellValue);
		}
		for(int i=0;i<arrUser.size();i++){
			data[i]=(Object[])arrUser.get(i).getEmail();
		}
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getColumnName(int col){
		return null;
	}
}
