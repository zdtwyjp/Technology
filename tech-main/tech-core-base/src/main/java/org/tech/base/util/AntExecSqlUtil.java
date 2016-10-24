package org.tech.base.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ant执行SQL脚本
 * 
 * @author YangJunping
 * 
 */
public class AntExecSqlUtil {
	private static final Logger log = LoggerFactory
			.getLogger(AntExecSqlUtil.class);

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 执行SQL脚本
	 * 
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 * @param sqlFile
	 */
	public static void execute(String driver, String url, String username,
			String password, String sqlFile) {
		log.debug("执行SQL脚本：" + sqlFile);
		if (isEmpty(driver) || isEmpty(url) || isEmpty(username)
				|| isEmpty(password) || isEmpty(sqlFile)) {
			throw new IllegalArgumentException("参数不完整，请查看配置是否正确！");
		}
		SQLExec sqlExec = new SQLExec();
		// 设置数据库参数
		sqlExec.setDriver(driver);
		sqlExec.setUrl(url);
		sqlExec.setUserid(username);
		sqlExec.setPassword(password);
		// 要执行的脚本
		sqlExec.setSrc(new File(sqlFile));
		// 有出错的语句该如何处理
		sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(
				SQLExec.OnError.class, "abort")));
		sqlExec.setPrint(true); // 设置是否输出
		// 输出到文件 sql.out 中；不设置该属性，默认输出到控制台
		sqlExec.setOutput(new File("D:/sql.out"));
		// 要指定这个属性，不然会出错
		sqlExec.setProject(new Project());
		sqlExec.execute();
		log.debug("执行SQL脚本完成.");
	}
}
