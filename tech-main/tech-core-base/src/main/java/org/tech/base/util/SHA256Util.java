package org.tech.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256工具类
 * 
 * @author YangJunping
 * 
 */
public class SHA256Util {
	/**
	 * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
	 * 
	 * @param str
	 *            要加密的字符串
	 * @return
	 */
	public static String getSHA256(String str) {
		MessageDigest md = null;
		String strDes = null;
		if (str == null) {
			str = "";
		}

		byte[] bt = str.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(bt);
			// 转换为十六进制串
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	/**
	 * 转换为十六进制
	 * 
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * 获取混淆加密串
	 * 
	 * @param str
	 * @return
	 */
	public static String getSHA256MixPassword(String password) {
		return Constants.MD5_MIX_STR + password;
	}

	public static void main(String args[]) throws InterruptedException {
		String s = getSHA256("000000");
		System.out.println(s);
		s = getSHA256(getSHA256MixPassword(s));
		System.out.println(s);
//		System.out.println(ServiceUtil.getMixSHA256("y1"));
		System.out.println(getSHA256("12870"));
	}
}
