package Utilities;

import java.util.ArrayList;
/**
 * @author hieulv
 */
public class StringUtil {
	/**
	 * @return Hàm này trả về một mảng tương ứng với hệ thống menu mà người dùng được phân quyền sử dụng
	 * @param String s: là chuỗi đầu vào được lấy từ field UserRight trong bảng tblPhanHeUser
	 */
	public ArrayList<Integer> getUserRightArray(String s){
		int j=0;
		ArrayList<Integer>arr=new ArrayList<Integer>();
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)!=';'){
				j++;
			}
			else{
				if(j!=0){
					arr.add(Integer.parseInt(s.substring(i-j, i)));
				}
				j=0;
			}
		}
		if(j>0){
			System.out.println(j);
		}
		return arr;
	}
	
	public static void main(String[] args){
		ArrayList<Integer> ra=new ArrayList<Integer>();
		ra=new StringUtil().getUserRightArray(";;123;334;");
		for(int i=0;i<ra.size();i++){
			System.out.println(ra.get(i)+1);
		}
	}
}
