package org.erp4h.common.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author hieulv
 */
public class StringUtil {
	/**
	 * @return Hàm này trả v�? một mảng tương ứng với hệ thống menu mà ngư�?i
	 *         dùng được phân quy�?n sử dụng
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

	public String Encrypt(String str) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("DES");
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		SecretKey secretKey = keyGenerator.generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return str;
	}

	public static void main(String[] args) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			SecretKey secretKey = keyGenerator.generateKey();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			FileInputStream fis = new FileInputStream(StringUtil.class
					.getResource(".").getPath() + "data.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			int len = bis.available();
			byte[] buff = new byte[len];
			byte[] enc = new byte[len];
			while (bis.available() != 0) {
				len = bis.read(buff);
				int byteCount = cipher.update(buff, 0, len, enc);
				System.out.println(byteCount);
			}
			bis.close();
			fis.close();
			cipher.doFinal();

			FileOutputStream fos = new FileOutputStream("output.txt");
			fos.write(enc);
			fos.close();

			FileOutputStream fosk = new FileOutputStream("key.txt");
			fosk.write(secretKey.getEncoded());
			String s1 = secretKey.getFormat();
			System.out.println(s1);
			fosk.close();
		} catch (Exception e) {
			System.out.println("asdasd");
		}
	}
}
