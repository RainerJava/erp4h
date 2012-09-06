package Center;

import BusinessLogicLayer.UserBLL;

public class test {
	public static void main(String[] arg) throws Exception{
		UserBLL bllUser=new UserBLL();
		int j=bllUser.numCol;
		for(int i=0;i<bllUser.numCol;i++){
			System.out.println(i+"   "+bllUser.arrColumnClass.get(i));
		}
	}
}
