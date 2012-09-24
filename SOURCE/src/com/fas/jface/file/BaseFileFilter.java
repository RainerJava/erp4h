/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseFileFilter.java
*
*     記述				：
*     
*     作成日			：2009/10/13   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC12</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class BaseFileFilter extends FileFilter{

	String[] extensions;
	
	String description;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param ext
	 */
	public BaseFileFilter(String ext) {
		    this(new String[] { ext }, null);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param exts
	 * @param descr
	 */
	public BaseFileFilter(String[] exts, String descr) {
	    // Clone and lowercase the extensions
	    extensions = new String[exts.length];
	    for (int i = exts.length - 1; i >= 0; i--) {
	      extensions[i] = exts[i].toLowerCase();
	    }
	    // Make sure we have a valid (if simplistic) description
	    description = (descr == null ? exts[0] + " files" : descr);
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File f) {
	    // We always allow directories, regardless of their extension
	    if (f.isDirectory()) {
	      return true;
	    }

	    // Ok, it's a regular file, so check the extension
	    String name = f.getName().toLowerCase();
	    for (int i = extensions.length - 1; i >= 0; i--) {
	      if (name.endsWith(extensions[i])) {
	        return true;
	      }
	    }
	    return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription() {
	    return description;
	}

}

