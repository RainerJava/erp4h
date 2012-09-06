package BusinessLogicLayer;

import javax.swing.table.AbstractTableModel;

public class absUser extends AbstractTableModel{

	String[] colName;
	Class<?>[] colClass;
	Object[][] value;
	UserBLL bllUser;
	public absUser(){
		try {
			UserBLL bllUser=new UserBLL();
			colName=bllUser.ColumnName;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private Object[][] setValue(){
		value=new Object[bllUser.RowCount][];
		for(int i=0;i<value.length;i++){
			Object o=(Object)bllUser.getUserArray().get(i);
			value[i]=(Object[])bllUser.getUserArray().get(i);
		}
		return value;
	}
	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return null;
	}
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }
    public Class<?> getColumnClass(int col){
    	Class<?> dataType=super.getColumnClass(col);
    	return dataType;
    }
}
