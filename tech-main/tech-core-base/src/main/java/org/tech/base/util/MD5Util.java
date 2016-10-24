package org.tech.base.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class MD5Util {
	public static String getMd5(String str) {
		if (str == null)
			return null;
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			byte[] strBytes = str.getBytes("UTF8");

			messagedigest.update(strBytes);
			byte[] digestBytes = messagedigest.digest();
			char[] digestChars = Hex.encodeHex(digestBytes);

			StringBuilder sb = new StringBuilder();
			sb.append(digestChars, 0, digestChars.length);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(getMd5("1_test副本.docx"));
	}
}
