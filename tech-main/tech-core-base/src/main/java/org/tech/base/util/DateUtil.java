package org.tech.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 */
public class DateUtil {
	/** 精确到年份 */
	public static final String PATTERN_TO_YEAR = "yyyy";
	/** 精确到分钟 */
	public static final String PATTERN_TO_DAY = "yyyy-MM-dd";
	/** 精确到分钟 */
	public static final String PATTERN_TO_MINUTES = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_TO_MINUTES_NO_LINE = "yyyyMMddHHmm";
	/** 精确到秒 */
	public static final String PATTERN_TO_SECONDS = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_TO_SECONDS_NO_LINE = "yyyyMMddHHmmss";
	
	public static SimpleDateFormat FORMATE_TO_YEAR = new SimpleDateFormat(
			PATTERN_TO_YEAR);
	
	public static SimpleDateFormat FORMATE_TO_DAY = new SimpleDateFormat(
			PATTERN_TO_DAY);
	
	public static SimpleDateFormat FORMATE_TO_SECONDS = new SimpleDateFormat(
			PATTERN_TO_SECONDS);
	
	public static SimpleDateFormat FORMATE_TO_SECONDS_NO_LINE = new SimpleDateFormat(
			PATTERN_TO_SECONDS_NO_LINE);

	public static SimpleDateFormat FORMATE_TO_MINUTES_NO_LINE = new SimpleDateFormat(
			PATTERN_TO_MINUTES_NO_LINE);
	
	public static SimpleDateFormat FORMATE_TO_MINUTES = new SimpleDateFormat(
			PATTERN_TO_MINUTES);

	/**
	 * 判断日期类型
	 * 
	 * @param strDate
	 *            成功返回date类型，失败返回null
	 * @return
	 */
	public static Date isDate(String strDate) {
		Date returnDate = null;
		try {
			DateFormat df = DateFormat.getDateInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String cDate = sdf.format(df.parse(strDate)).toString();
			returnDate = df.parse(cDate);
		} catch (Exception e) {
			return null;
		}
		return returnDate;
	}

	public static String getCurrentDate() {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String formatDate = dateformat.format(new Date());
		return formatDate;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 *            默认精确到秒
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		if (pattern == null || pattern.trim().length() < 1) {
			pattern = PATTERN_TO_SECONDS;
		}
		SimpleDateFormat form = new SimpleDateFormat(pattern);
		return form.format(date);
	}
	
	public static long getCurrSecond(){
		return new Date().getTime()/1000;
	}

	public static long getDateTimes(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lo = 0;
		try {
			Date date = sdf.parse(dateStr);
			lo = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lo;
	}
	
	public static long getDateSecond(Integer year,Integer month){
		return getDateTimes(year+"-"+month+"-02 00:00:00");
	}
	
	/**
	 * 时间间隔：小时
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getDateInterval(Date start, Date end){
		if(start == null || end == null){
			throw new IllegalArgumentException("非法参数！");
		}
		long t = (end.getTime() - start.getTime())/(1000 * 3600);
		return t;
	}
	
	/**
	 * 计算时间间隔：X天X小时X分钟X秒
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getDateIntervalStr(Date start, Date end){
		if(start == null || end == null){
			throw new IllegalArgumentException("非法参数！");
		}
		String ret = "";
		long inter = end.getTime() - start.getTime();
		// 计算相隔天数
		long days = inter/(1000 * 3600 * 24);
		// 计算天数后，剩余的时间
		long leaveDays = inter%(1000 * 3600 * 24);
		if(days > 0){
			ret += days + "天";
		}
		// 计算小时
		long hours = leaveDays/(1000 * 3600);
		// 计算小时剩余的时间
		long leaveHours = leaveDays%(1000 * 3600);
		if(hours > 0){
			ret += hours + "小时";
		}
		// 计算分钟
		long minutes = leaveHours/(1000 * 60);
		// 计算分钟剩余的时间
		long leaveMinutes = leaveHours%(1000 * 60);
		if(minutes > 0){
			ret += minutes + "分钟";
		}
		// 计算毫秒
		long seconds = leaveMinutes/1000;
		if(seconds > 0){
			ret += seconds + "秒";
		}
		return ret;
	}
	
	/**
	 * 获取消息时间：刚刚 3分钟前 1小时前 2天前
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getMessageDateIntervalStr(Date start, Date end){
		if(start == null || end == null){
			throw new IllegalArgumentException("非法参数！");
		}
		long inter = end.getTime() - start.getTime();
		// 计算相隔天数
		long days = inter/(1000 * 3600 * 24);
		if(days > 0){
			return days + "天前";
		}
		// 计算小时
		long hours = inter/(1000 * 3600);
		if(hours > 0){
			return hours + "小时前";
		}
		// 计算分钟
		long minutes = inter/(1000 * 60);
		if(minutes > 0){
			return minutes + "分钟前";
		}
		// 计算毫秒
		long seconds = inter/1000;
		if(seconds > 0){
			return "刚刚";
		}
		return "";
	}

}
