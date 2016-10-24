package org.tech.base.util;

public class StringUtil {

	/**
	 * 空对象转空串
	 * 
	 * @param obj
	 * @return
	 * @author YangJunping
	 */
	public static String nullToSpace(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * 判断字符串是否为空 (null 或者 "")
	 * 
	 * @param str
	 * @return
	 * @author YangJunping
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否为空Data
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyData(String str) {
		if (isEmpty(str)) {
			return true;
		}
		if (str.trim().equals("{}")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (isEmpty(fileName)) {
			return "";
		}
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index + 1);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @param num
	 * @return
	 */
	public static String getSubStringStr(String str, int num) {
		if (isEmpty(str)) {
			return "";
		}
		if (str.length() < num) {
			return str;
		}
		return str.substring(0, num) + "...";
	}

	/**
	 * 将首字母转为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String changeFirstCharToUppercase(String str) {
		if (isEmpty(str)) {
			return "";
		}
		String firstChar = str.substring(0, 1);
		str = firstChar.toUpperCase() + str.substring(1);
		return str;
	}

	/**
	 * 从URL中获取文件文件名
	 * 
	 * @param url
	 * @return
	 */
	public static String getFileNameFromUrl(String url) {
		String fileName = "";
		if (url != null && url.trim().length() > 0) {
			String[] arr = url.split("/");
			int len = arr.length;
			if (len > 0) {
				fileName = arr[len - 1];
			}
		}
		return fileName;
	}
}
