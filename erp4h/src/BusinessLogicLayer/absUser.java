package BusinessLogicLayer;

import javax.swing.table.AbstractTableModel;

public class absUser extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] colName;
	Class<?>[] colClass;
	Object[][] value;
	UserBLL bllUser;
	public absUser(){
		try {
			UserBLL bllUser=new UserBLL();
			colName=bllUser.getColumnName();
			value=bllUser.getUserObj();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int getColumnCount() {
		return value[0].length;
	}

	@Override
	public int getRowCount() {
		return value.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		return value[rowIndex][colIndex];
	}
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }
}
