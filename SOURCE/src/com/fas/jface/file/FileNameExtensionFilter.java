/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：FileNameExtensionFilter.java
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
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

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
public final class FileNameExtensionFilter extends FileFilter {
	
    // Description of this filter.
    private final String description;
    // Known extensions.
    private final String[] extensions;
    // Cached ext
    private final String[] lowerCaseExtensions;

	public FileNameExtensionFilter(String description,
	        String... extensions) {
	    if (extensions == null || extensions.length == 0) {
	        throw new IllegalArgumentException(
	                "Extensions must be non-null and not empty");
	    }
	    this .description = description;
	    this .extensions = new String[extensions.length];
	    this .lowerCaseExtensions = new String[extensions.length];
	    for (int i = 0; i < extensions.length; i++) {
	        if (extensions[i] == null || extensions[i].length() == 0) {
	            throw new IllegalArgumentException(
	                    "Each extension must be non-null and not empty");
	        }
	        this .extensions[i] = extensions[i];
	        lowerCaseExtensions[i] = extensions[i]
	                .toLowerCase(Locale.ENGLISH);
	    }
	}


	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File f) {
	    if (f != null) {
	        if (f.isDirectory()) {
	            return true;
	        }

	        String fileName = f.getName();
	        int i = fileName.lastIndexOf('.');
	        if (i > 0 && i < fileName.length() - 1) {
	            String desiredExtension = fileName.substring(i + 1)
	                    .toLowerCase(Locale.ENGLISH);
	            for (String extension : lowerCaseExtensions) {
	                if (desiredExtension.equals(extension)) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}


	public String getDescription() {
	    return description;
	}
	
	/**
	 * Returns the set of file name extensions files are tested against.
	 *
	 * @return the set of file name extensions files are tested against
	 */
	public String[] getExtensions() {
	    String[] result = new String[extensions.length];
	    System.arraycopy(extensions, 0, result, 0, extensions.length);
	    return result;
	}
	public String toString() {
	    return super .toString() + "[description=" + getDescription()
	            + " extensions="
	            + java.util.Arrays.asList(getExtensions()) + "]";
	}
}


