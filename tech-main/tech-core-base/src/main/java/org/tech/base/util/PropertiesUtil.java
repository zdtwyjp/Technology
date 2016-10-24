package org.tech.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 资源文件工具类
 */
public class PropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * 加载资源文件
	 * 
	 * @param path
	 * @return
	 */
	public static Properties loadProperties(String path) {
		InputStream inputStream = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream(path);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			log.error("加载资源文件[" + path + "失败！]", e);
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * 从Jar文件中加载资源文件
	 * 
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static Properties loadPropertiesFromJarFile(URL url, String filePath) {
		Properties p = new Properties();
		String urlStr = url.toString();
		// 找到!/ 截断之前的字符串
		String jarPath = urlStr.substring(0, urlStr.indexOf("!/") + 2);
		URL jarURL = null;
		JarFile jarFile = null;
		JarURLConnection jarCon = null;
		try {
			jarURL = new URL(jarPath);
		} catch (MalformedURLException e) {
			log.error("Load jarPath error!", e);
			e.printStackTrace();
		}
		try {
			jarCon = (JarURLConnection) jarURL.openConnection();
		} catch (IOException e) {
			log.error("JarURL openConnection error!", e);
			e.printStackTrace();
		}
		try {
			jarFile = jarCon.getJarFile();
		} catch (IOException e) {
			log.error("JarConnection getJarFile error!", e);
			e.printStackTrace();
		}
		Enumeration<JarEntry> jarEntrys = jarFile.entries();
		while (jarEntrys.hasMoreElements()) {
			JarEntry entry = jarEntrys.nextElement();
			String name = entry.getName();
			if (filePath.equals(name) && !entry.isDirectory()) {
				// 开始读取文件内容
				InputStream is = null;
				try {
					is = jarFile.getInputStream(entry);
				} catch (IOException e) {
					log.error("JarFile getInputStream error!", e);
					e.printStackTrace();
				}
				try {
					p.load(is);
				} catch (IOException e) {
					log.error("Load properties from inputstream error!", e);
					e.printStackTrace();
				}
				try {
					is.close();
				} catch (IOException e) {
					log.error("Inputstream close error!", e);
					e.printStackTrace();
				}
			}
		}
		return p;
	}
}
