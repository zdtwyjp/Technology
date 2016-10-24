package org.tech.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * 编码解码工具类
 * 
 * @author YangJunping
 * 
 */
public class Base64Util {
	/**
	 * 编码
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String str) {
		if (str == null)
			return "";
		Base64 base64 = new Base64();
//		try {
//			str = java.net.URLEncoder.encode(str, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return new String(base64.encode(str.getBytes()));
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String decode(String str) {
		if (str == null)
			return "";
		Base64 base64 = new Base64();
		str = new String(base64.decode(str.getBytes()));
		try {
			return java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		String s = encode("admin:5");
		System.out.println(s);
		System.out.println(decode(s));
	}
}
