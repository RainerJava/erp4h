package Center;

import BusinessLogicLayer.UserBLL;

public class test {
	public static void main(String[] arg) throws Exception{
		UserBLL bllUser=new UserBLL();
		//int j=bllUser.ColumnCount;
		//System.out.println("   "+bllUser.getColumnClass());
		//System.out.println("   "+bllUser.getColumnName());
		Object[][] value=bllUser.getUserObj();
		for(int i=0;i<bllUser.RowCount;i++){
			System.out.println(value[i][1]);
			
		}
	}
}
