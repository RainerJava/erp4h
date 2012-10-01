package org.erp4h.common.utils;

import java.util.ArrayList;

/**
 * @author hieulv
 */
public class StringUtil {
	/**
	 * @return Hàm này trả v�? một mảng tương ứng với hệ thống menu mà ngư�?i dùng
	 *         được phân quy�?n sử dụng
	 * @param String
	 *            s: là chuỗi đầu vào được lấy từ field UserRight trong bảng
	 *            tblPhanHeUser
	 */
	public ArrayList<Integer> getIntArray(String s) {
		int j = 0;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ';') {
				j++;
			} else {
				if (j != 0) {
					arr.add(Integer.parseInt(s.substring(i - j, i)));
				}
				j = 0;
			}
		}
		return arr;
	}
}
