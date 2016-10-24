package org.tech.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.tech.base.exception.BaseException;

public class FileUtil {

	/**
	 * 获取后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (fileName == null || fileName.trim().length() < 1) {
			return "";
		}
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return "";
		}
		return fileName.substring(index + 1);
	}

	/**
	 * 根据文件名获取下载ContentType
	 * 
	 * @param fileName
	 * @return
	 */
	public static String setContentType(String fileName) {
		String contentType = "application/octet-stream";
		if (fileName.lastIndexOf(".") < 0) {
			return contentType;
		}
		fileName = fileName.toLowerCase();
		fileName = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (fileName.equals("html") || fileName.equals("htm")
				|| fileName.equals("shtml")) {
			contentType = "text/html";
		} else if (fileName.equals("css")) {
			contentType = "text/css";
		} else if (fileName.equals("xml")) {
			contentType = "text/xml";
		} else if (fileName.equals("gif")) {
			contentType = "image/gif";
		} else if (fileName.equals("jpeg") || fileName.equals("jpg")) {
			contentType = "image/jpeg";
		} else if (fileName.equals("js")) {
			contentType = "application/x-javascript";
		} else if (fileName.equals("atom")) {
			contentType = "application/atom+xml";
		} else if (fileName.equals("rss")) {
			contentType = "application/rss+xml";
		} else if (fileName.equals("mml")) {
			contentType = "text/mathml";
		} else if (fileName.equals("txt")) {
			contentType = "text/plain";
		} else if (fileName.equals("jad")) {
			contentType = "text/vnd.sun.j2me.app-descriptor";
		} else if (fileName.equals("wml")) {
			contentType = "text/vnd.wap.wml";
		} else if (fileName.equals("htc")) {
			contentType = "text/x-component";
		} else if (fileName.equals("png")) {
			contentType = "image/png";
		} else if (fileName.equals("tif") || fileName.equals("tiff")) {
			contentType = "image/tiff";
		} else if (fileName.equals("wbmp")) {
			contentType = "image/vnd.wap.wbmp";
		} else if (fileName.equals("ico")) {
			contentType = "image/x-icon";
		} else if (fileName.equals("jng")) {
			contentType = "image/x-jng";
		} else if (fileName.equals("bmp")) {
			contentType = "image/x-ms-bmp";
		} else if (fileName.equals("svg")) {
			contentType = "image/svg+xml";
		} else if (fileName.equals("jar") || fileName.equals("var")
				|| fileName.equals("ear")) {
			contentType = "application/java-archive";
		} else if (fileName.equals("doc")) {
			contentType = "application/msword";
		} else if (fileName.equals("pdf")) {
			contentType = "application/pdf";
		} else if (fileName.equals("rtf")) {
			contentType = "application/rtf";
		} else if (fileName.equals("xls")) {
			contentType = "application/vnd.ms-excel";
		} else if (fileName.equals("ppt")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (fileName.equals("7z")) {
			contentType = "application/x-7z-compressed";
		} else if (fileName.equals("rar")) {
			contentType = "application/x-rar-compressed";
		} else if (fileName.equals("swf")) {
			contentType = "application/x-shockwave-flash";
		} else if (fileName.equals("rpm")) {
			contentType = "application/x-redhat-package-manager";
		} else if (fileName.equals("der") || fileName.equals("pem")
				|| fileName.equals("crt")) {
			contentType = "application/x-x509-ca-cert";
		} else if (fileName.equals("xhtml")) {
			contentType = "application/xhtml+xml";
		} else if (fileName.equals("zip")) {
			contentType = "application/zip";
		} else if (fileName.equals("mid") || fileName.equals("midi")
				|| fileName.equals("kar")) {
			contentType = "audio/midi";
		} else if (fileName.equals("mp3")) {
			contentType = "audio/mpeg";
		} else if (fileName.equals("ogg")) {
			contentType = "audio/ogg";
		} else if (fileName.equals("m4a")) {
			contentType = "audio/x-m4a";
		} else if (fileName.equals("ra")) {
			contentType = "audio/x-realaudio";
		} else if (fileName.equals("3gpp") || fileName.equals("3gp")) {
			contentType = "video/3gpp";
		} else if (fileName.equals("mp4")) {
			contentType = "video/mp4";
		} else if (fileName.equals("mpeg") || fileName.equals("mpg")) {
			contentType = "video/mpeg";
		} else if (fileName.equals("mov")) {
			contentType = "video/quicktime";
		} else if (fileName.equals("flv")) {
			contentType = "video/x-flv";
		} else if (fileName.equals("m4v")) {
			contentType = "video/x-m4v";
		} else if (fileName.equals("mng")) {
			contentType = "video/x-mng";
		} else if (fileName.equals("asx") || fileName.equals("asf")) {
			contentType = "video/x-ms-asf";
		} else if (fileName.equals("wmv")) {
			contentType = "video/x-ms-wmv";
		} else if (fileName.equals("avi")) {
			contentType = "video/x-msvideo";
		}
		return contentType;
	}

	/**
	 * 获得文件的大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String formatFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "GB";
		}
		return fileSizeString;
	}

	/**
	 * 文件拷贝
	 * 
	 * @param newPath
	 * @param oldPath
	 * @param newFileName
	 * @param oldFileName
	 * @throws Exception
	 */
//	public static void copyFile(String newPath, String oldPath,
//			String newFileName, String oldFileName) throws Exception {
//		File fromFilePath = new File(oldPath);
//		File toFilePath = new File(newPath);
//		if (!toFilePath.exists()) {
//			toFilePath.mkdirs();
//		}
//		File fromPicFile = new File(fromFilePath, oldFileName);
//		File toPicFile = new File(toFilePath, newFileName);
//		if (fromPicFile.exists()) {
//			FileInputStream in = new FileInputStream(fromPicFile);
//			FileOutputStream output = new FileOutputStream(toPicFile);
//			byte[] buffer = new byte[1024];
//			int len = 0;
//			while ((len = in.read(buffer)) > 0) {
//				output.write(buffer, 0, len);
//			}
//			output.flush();
//			output.close();
//			in.close();
//		}
//	}

	/**
	 * 删除文档
	 * 
	 * @param filePath
	 */
	public static void removeFile(String filePath) {
		if (null == filePath || filePath.trim().length() < 0) {
			return;
		}
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		if (null == filePath || filePath.trim().length() < 0) {
			return -1l;
		}
		File file = new File(filePath);
		if (file.exists()) {
			return file.length();
		}
		return -1l;
	}
	
	/**
	 * 加载文件内容
	 * @param filePath
	 * @return
	 * @throws BaseException 
	 */
	public static String getFileContent(String filePath) throws BaseException {
		int len=0;
        StringBuffer str = new StringBuffer("");
        File file = new File(filePath);
        FileInputStream is = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        try {
            is = new FileInputStream(file);
            isr = new InputStreamReader(is, "UTF-8");
            in = new BufferedReader(isr);
            String line = null;
            while((line=in.readLine()) != null ) {
                if (len != 0) {
                	// 处理换行符的问题
                    str.append("\r\n"+line);
                } else {
                    str.append(line);
                }
                len++;
            }
        } catch (IOException e) {
            throw new BaseException("读取Widgets失败。", e);
        } finally {
        	if(in != null){
        		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(isr != null){
        		try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(is != null){
        		try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return str.toString();
	}

}
