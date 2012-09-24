package com.fas.jface;

import java.util.Hashtable;
import java.util.Map;

import com.fas.jface.gui.BaseFrame;

/**
 * @author PC13
 *
 */
public class BaseCacheManager {

	/** */
	private  static Map cache = new Hashtable();
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BaseCacheManager() {
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 */
	public static void set(BaseFrame frame) {
		cache.remove(frame.getClass().getName());
		cache.put(frame.getClass().getName(), frame);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 * @return
	 */
	public static BaseFrame get(Class frame) {
		return (BaseFrame) cache.get(frame.getName());
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public static void commit() {
		cache.clear();
	}	
}

