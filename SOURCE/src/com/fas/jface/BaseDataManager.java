/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseDataManager.java
*
*     記述				：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import com.fas.common.utils.StringUtils;
import com.fas.vo.user.LoginUser;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class BaseDataManager {

	/** Key：画面遷移情報 */
	public final static String KEY_FRAME_MOVEMENT = "Movement";
	/** Key：ログインユーザー情報 */
	public final static String KEY_LOGIN_USER = "LoginUser";

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BaseDataManager() {
	}
	
	/** */
	private static ThreadLocal history = new ThreadLocal() {
		/* (非 Javadoc)
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		protected Object initialValue() {
			Map map = null;
			return map;
		}
	};	

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @param obj
	 */
	public static void set(String key, Object obj) {
		// VM記憶領域よりMapを取得
		Map map = (HashMap) history.get();
		if (map == null) {
			// MapがNullの場合は、初期化
			map = new HashMap();
		}
		
		// 指定したKeyで指定されたオブジェクトをセット
		map.put(key, obj);
	
		// Mapを再度VM記憶領域へセット
		history.set(map);
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
	public static void setMovement(JFrame frame) {
		List ar = (ArrayList) get(KEY_FRAME_MOVEMENT);
		if (ar == null) {
			ar = new ArrayList();
		}
		ar.add(frame);
		set(KEY_FRAME_MOVEMENT, ar);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>データ領域のMapからオブジェクトを取得.</DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		// 返り値用変数初期化
		Object obj = null;
		// VMデータ領域よりMapを取得
		Map map = (Map) history.get();
		if (map != null) {
			// MapがNullでない場合指定されたKeyをもとにオブジェクトを取得
			obj = map.get(key);
		}
		return obj;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public static LoginUser getLoginUser() {
		Object obj = get(KEY_LOGIN_USER);
		if (obj == null) {
			return null;
		}
		return (LoginUser) obj;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param user
	 */
	public static void setLoginUser(LoginUser user) {
		set(KEY_LOGIN_USER, user);
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public static JFrame getMovement() {
		
		List ar = (ArrayList) get(KEY_FRAME_MOVEMENT);
		
		if (ar == null) {
			ar = new ArrayList();
		} else {
			Object o = ar.remove(ar.size() - 1);
			if (o == null) {
				return null;
			}
			return (JFrame) o;
		}
		return null;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cls
	 */
	public static void setMovement(Class cls) {
		List ar = (ArrayList) get(KEY_FRAME_MOVEMENT);

		List history = new ArrayList();
		for (int i = 0; i < ar.size(); i++) {
			history.add(ar.get(i));
		}
		history = getHistory(history, cls, true);
		set(KEY_FRAME_MOVEMENT, history);
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param ar
	 * @param cls
	 * @return
	 */
	private static List getHistory(List ar, Class cls) {
		return getHistory(ar, cls, false);
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param ar
	 * @param cls
	 * @param remain
	 * @return
	 */
	private static List getHistory(List ar, Class cls, boolean remain) {
		
		List save = new ArrayList();
		
		for (int i = 0; i < ar.size(); i++) {
			save.add(ar.get(i));
		}
		
		for (int i = ar.size(); i > 0; i--) {
			Object o = ar.remove(i - 1);
			
			if (cls.getName().equals(o.getClass().getName())) {
				if (remain == true) {
					ar.add(o);
				}
				return ar;
			}
		}
		return save;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cls
	 * @return
	 */
	public static JFrame getMovement(Class cls) {
		
		List ar = (ArrayList) get(KEY_FRAME_MOVEMENT);
		
		try {
			ar = getHistory(ar, cls);
			if (ar == null) {
				ar = new ArrayList();
			} else {
				for (int i = 0; i < ar.size(); i++) {
					Object o = ar.remove(ar.size() - 1);
					if (o == null) {
						return null;
					}
					return (JFrame) o;
				}
			}
			return null;
		} finally {
			set(KEY_FRAME_MOVEMENT, ar);
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @return
	 */
	public static Object remove(String key) {
		
		Object obj = null;
		Map map = (Map) history.get();
		if (map != null) {
			obj = map.remove(key);
		}
		return obj;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public static void removeAll() {
		Map map = (Map) history.get();
		if (map != null) {
			map.clear();
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cls
	 * @return
	 */
	public static JFrame getBeforeMovement(Class cls) {
		
		List ar = (ArrayList) get(KEY_FRAME_MOVEMENT);
		
		if (ar == null) {
			ar = new ArrayList();
		} else {
			for (int i = ar.size(); i > 0; i--) {
				Object o = ar.remove(i - 1);
				if (cls.getName().equals(o.getClass().getName())) {
					return (JFrame) o;
				}
			}
		}
		return null;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public static String getLoginUserId() {
	
		LoginUser user = getLoginUser();
		
		if (user != null) {
			return StringUtils.trimString(user.getUserId());
		} else {
			return "";
		}
	}
}

