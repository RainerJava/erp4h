package org.erp4h.testcase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int parentVersion = 10;
}

class contain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int containVersion = 11;
}

public class SerialTest extends parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int version = 66;
	contain con = new contain();

	public int getVersion() {
		return version;
	}

	public static void main(String args[]) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SerialTest st = new SerialTest();
		oos.writeObject(st);
		oos.flush();
		oos.close();
	}
}
