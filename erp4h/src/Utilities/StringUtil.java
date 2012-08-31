package Utilities;

import java.util.ArrayList;
/**
 * @author hieulv
 */
public class StringUtil {
	/**
	 * tra ve mot mang tuong ung voi he thong menu
	 * su dung cho phan quyen nguoi dung
	 */
	public ArrayList<Integer> getRightArray(String s){
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
		return arr;
	}
	
	public static void main(String[] args){
		ArrayList<Integer> ra=new ArrayList<Integer>();
		ra=new StringUtil().getRightArray(";;123;334;");
		for(int i=0;i<ra.size();i++){
			System.out.println(ra.get(i)+1);
		}
	}
}
