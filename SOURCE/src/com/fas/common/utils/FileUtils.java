/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：FileUtils.java
*
*     記述				：
*     
*     作成日			：2009/06/11   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

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

public class FileUtils {
	
	/** */
	static Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public FileUtils() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strFileName
	 * @param strText
	 * @throws IOException
	 */
	public static void outputFile(String strFileName, String strText) throws IOException {
		
		if(strText == null || strText.equals(""))
			return;

		if(!fileExists(strFileName)) {
			File file = new File(strFileName);
			file.createNewFile();
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream(strFileName, true);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		bufferedWriter.write(strText);
		bufferedWriter.newLine();
		
		bufferedWriter.close();
		outputStreamWriter.close();
		fileOutputStream.close();
		
	}

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFileName
     * @return
     */
    public static boolean fileExists(String strFileName) {
    	
        String filePath = strFileName;
        File file = new File(filePath);
        return file.exists();
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
    synchronized public static String generateFileName() {
    	
    	Date dCurrent = new Date();
    	String strName = "";
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dCurrent);
    	strName = strName + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DAY_OF_MONTH) + cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE) +  cal.get(Calendar.SECOND) + cal.get(Calendar.MILLISECOND);

    	return strName;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strDate
     * @return
     */
    public static java.sql.Date  getDate(String strDate) {
    	
    	try {
	    	return java.sql.Date.valueOf(strDate.replaceAll("/", "-"));	    	
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    }
    
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        
    	if (dir.isDirectory()) {
            
        	String[] children = dir.list();
            
            for (int i=0; i<children.length; i++) {
                
            	boolean success = deleteDir(new File(dir, children[i]));
            
                if (!success) {
                    return false;
                }
            }
        }

    	// The directory is now empty so delete it
        return dir.delete();
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param msg
     * @param cmd
     * @param finishFile
     * @param stopFile
     * @return
     */
    public static int runCommand(String msg, String cmd, String finishFile, String stopFile) {
        
    	System.gc();
        int exitCode = -1;
        
        try {
        	
            Runtime runtime = Runtime.getRuntime();
            
            if(fileExists(stopFile)) {
            	removeFile(stopFile);
                return 0;
            }
            
            exitCode = -2;
            System.out.println(cmd);
            Process process = runtime.exec(cmd);
            
            try {
                
            	long intCheck = 0L;
                
                do {
                    intCheck++;
                    Thread.sleep(500L);
                    if(fileExists(finishFile)) {
                        process.waitFor();
                        break;
                    }
                    
                    if(!fileExists(stopFile))
                        continue;
                    
                    removeFile(stopFile);
                    
                    break;
                    
                } while(600L > intCheck);
                
                exitCode = process.exitValue();
            
            } catch(Exception e) {
            	e.printStackTrace();
            }
            
            process.getInputStream();
            process.getOutputStream();
            process.getErrorStream();
            process.destroy();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exitCode;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strCmd
     * @return
     */
    public static int runCmdAndWait(final String strCmd) {
        
    	System.gc();
        int exitCode = -1;
        
        try {
        	
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(strCmd);
            exitCode = process.exitValue();
            
        } catch(Exception e) {
        	exitCode = -1;
            e.printStackTrace();
        }
        return exitCode;
    }    
   
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strCmd
     * @return
     */
    public static int runCmd(final String strCmd) {
        
    	System.gc();
        int exitCode = -1;
        
        try {
            
	        Thread worker = new Thread() {
		          public void run() {
		            // Something that takes a long time . . . in real life,
		            // this
		            // might be a DB query, remote method invocation, etc.
		            try {
		              Thread.sleep(5000);
		            } catch (InterruptedException ex) {
		            }
		            
		            try {
			            // Report the result using invokeLater().
			            SwingUtilities.invokeLater(new Runnable() {
				              public void run() {
				            	  try {
				            		  Runtime runtime = Runtime.getRuntime();
				            		  logger.info(strCmd);
				                      runtime.exec(strCmd);
				            	  } catch (Exception e) {
				            		  e.printStackTrace();
				            	  }
				              }
			            });
		            } catch (Exception e) {
		            	
		            }
		          }
		      };
	        
		      worker.start();
		      
		      exitCode = 0;
		        
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exitCode;
    }    
        
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFilePathName
     */
    public static void removeFile(String strFilePathName) {
       
    	File file = new File(strFilePathName);
        file.delete();
        
    }  
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFilePath
     * @param strFileName
     * @throws IOException
     */
    public static void makeFile(String strFilePath, String strFileName) throws IOException {

        removeFile(strFilePath + "\\" + strFileName);
        createDir(strFilePath);
        
        try {
            if(!fileExists(strFilePath + "\\" + strFileName)) {
                File file = new File(strFilePath + "\\" + strFileName);
                file.createNewFile();
                System.out.println("Create File : " + strFilePath + "\\" + strFileName);
            }
        }
        catch(IOException e) {
        	
        }

    }	
	
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strPathDir
     */
    public static void createDir(String strPathDir) {
    	
        if (!fileExists(strPathDir)) {
            File file = new File(strPathDir);
            System.out.println("Create directory : " + " " + strPathDir);
            file.mkdir();
            file.mkdirs();
        }
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strDirPath
     */
    public static void removeDir(String strDirPath) {
    	
        if(fileExists(strDirPath)) {
        
        	File objFiles[] = (new File(strDirPath)).listFiles();
            
        	if(objFiles != null) {
                for(int i = 0; i < objFiles.length; i++)
                    if(objFiles[i].isDirectory())
                    	removeDir(strDirPath + "\\" + objFiles[i].getName());
                    else
                        objFiles[i].delete();

            }
        	
        	removeFile(strDirPath);
        }
    } 
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFilePathName
     */
    public static void createFile(String strFilePathName) {
        
    	try {
            if(!fileExists(strFilePathName)) {
                File file = new File(strFilePathName);
                file.createNewFile();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFilePathName
     * @param strFileName
     */
    public static void createFile(String strFilePathName, String strFileName) {
    	
        try {
            if(!fileExists(strFilePathName + "\\" + strFileName) && fileExists(strFilePathName)) {
                File file = new File(strFilePathName + "\\" + strFileName);
                file.createNewFile();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }  
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strPathFileNameFrom
     * @param strPathFileNameTo
     */
    public static void copyFile(String strPathFileNameFrom, String strPathFileNameTo) throws FileNotFoundException, IOException {
        
    	FileInputStream fIn = new FileInputStream(strPathFileNameFrom);
    	FileOutputStream fOut = new FileOutputStream(strPathFileNameTo);
        int b;
        
        try {
            while((b = fIn.read()) != -1) 
            	fOut.write((byte)b);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        	fIn.close();
        	fOut.close();
        }
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFilePath
     * @return
     */
    public static File getFileObj(String strFilePath) {
    	File rFile = new File(strFilePath);
    	return rFile;
    }
   
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFileName
     * @return
     */
    public static boolean isFile(String strFileName) {
    	
        File file = new File(StringUtils.trimString(strFileName));
        
        if (file.exists() == true) {
        	return file.isFile();
        } else {
        	return false;
        }
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFileName
     * @param strHeader
     * @param strEnd
     * @return
     */
    public static int isValidFile(String strFileName, String strHeader, String strEnd, String strEncode, String strCheckType) {
    	
    	try {
    	
	    	BufferedReader bufferReader = null;
	    	int iRow = 1;

	    	try {
	    		
		    	if (isFile(strFileName) == true) {
		    		
		    		bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(strFileName), strEncode));
	
		    		String strValue = "";
		    		String strFooter = "";
		    		/**hanbai インポート */
		    		if ("hanbai".equalsIgnoreCase(strCheckType)) { 
			    		strValue = StringUtils.trimString(bufferReader.readLine());
			    		/**	ヘッダチェック */
			    		if (strValue.length() < 3 || !strValue.substring(0, 3).equals(strHeader)) {
			    			return -1;
			    		} else {
			    			/**	エンドチェック */
			    			while ((strValue = bufferReader.readLine()) != null) {
			    				++iRow;
			    				strFooter = strValue;
			    			}
			    			strFooter = StringUtils.trimString(strFooter);
				    		if (strFooter.length() < 3 || !strFooter.substring(0, 3).equals(strEnd)) {
	
				    			return -1;
				    		} else {
				    			return iRow;
				    		}
			    		}
		    		} else {
			    		strValue = StringUtils.trimString(bufferReader.readLine());
			    		/**	ヘッダチェック */
			    		if (strValue.length() < 1 || !strValue.substring(0, 1).equals(strHeader)) {
			    			return -1;
			    		} else {
			    			/**	エンドチェック */
			    			while ((strValue = bufferReader.readLine()) != null) {
			    				++iRow;
			    				strFooter = strValue;
			    			}
			    			strFooter = StringUtils.trimString(strFooter);
				    		if (strFooter.length() < 1 || !strFooter.substring(0, 1).equals(strEnd)) {
	
				    			return -1;
				    		} else {
				    			return iRow;
				    		}
			    		}
		    		}
		    		
		    	} else {
		    		return -1;
		    	}
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    		return -1;
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    		return -1;
	    	} finally {
	    		bufferReader.close();
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    		return -1;
    	}
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFileName
     * @return
     * @throws FileNotFoundException
     */
    public static FileOutputStream getOutputStream(String strFileName) throws FileNotFoundException, IOException {
    	
    	FileOutputStream outPutStream;
    	
    	if (fileExists(strFileName)) {
    		outPutStream = new FileOutputStream(strFileName);
    	} else {
            File file = new File(strFileName);
            file.createNewFile();
    		outPutStream = new FileOutputStream(strFileName);
    	}
    	
    	return outPutStream;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFileName
     * @return
     */
    public static boolean isValidPath(String strFileName) {
    	
    	boolean isValid = true;
    	
    	try  {
	    	if (!fileExists(strFileName)) {
	            File file = new File(strFileName);
	            file.createNewFile();
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    		isValid = false;
    	}
    	
    	return isValid;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strFileName
     * @return
     */
    public static boolean isValidFolder(String strFolder) {
    	
    	boolean isValid = true;
    	
    	try  {
	    	if (!fileExists(strFolder)) {
	            File file = new File(strFolder);
	            isValid = file.isDirectory();
	    	}
    	} catch (Exception e) {
    		isValid = false;
    	}
    	
    	return isValid;
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
    public static InputStream getInputStreamFromUrl(String key) {
    	
    	InputStream inputStream = null;
    	ClassLoader loader = FileUtils.class.getClassLoader();
    	
    	try {
    		inputStream = loader.getResourceAsStream(key);
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		logger.error(e.getStackTrace());
    		e.printStackTrace();
    	}
    	
    	return inputStream;
    }
    
    public static void main(String[] args) {
    	try {
    		InputStream in = getInputStreamFromUrl("com/hanbai/report/config/fbwin_export_report.jrxml");
    		System.out.println(in.read());
    	} catch (Exception e) {
    		System.out.println(e.getStackTrace());
    	}
    }
}

