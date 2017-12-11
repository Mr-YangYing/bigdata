package cn.bytd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String DEFAULT_DATE_PATTERN="yyyy-MM-dd HH:mm:ss";//默认的日期格式
	private DateUtil() {} //私有化构造方法,避免外界创建对象
	
	/**
	 * Date日期转换为String字符串
	 * @param date 传入的日期
	 * @param pattern 传入的格式
	 * @return 按照指定日期格式装换后的日期字符串
	 */
	public static String date2String(Date date,String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		if (!hasLength(pattern)) {           //判断如果日期格式为空则给一个我们自己定义的默认的格式
			pattern=DEFAULT_DATE_PATTERN;
		}
		dateFormat.applyPattern(pattern);//将给定模式字符串应用于此日期格式
		return dateFormat.format(date);

	}
	
	/**
	 * Date日期转换为String字符串,如果用户只传入日期,使用该方法
	 * @param date 传入的日期
	 * @return 按照默认的日期格式装换后的日期字符串
	 */
	public static String date2String(Date date){
		return date2String(date, null);
	}
	
	/**
	 * String字符串转换为Date日期
	 * @param date 传入的日期
	 * @param pattern 传入格式
	 * @return 返回指定格式的日期
	 * @throws ParseException 转换异常
	 */
	public static Date string2date(String date,String pattern) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		if (!hasLength(pattern)) {   //判断如果日期格式为空则给一个我们自己定义的默认的格式
			pattern=DEFAULT_DATE_PATTERN;
		}
		dateFormat.applyPattern(pattern);//将给定模式字符串应用于此日期格式
		return dateFormat.parse(date);
	}
	/**
	 * String字符串转换为Date日期 ,不指定格式
	 * @param date 传入的日期
	 * @return 返回指定格式的日期
	 * @throws ParseException 转换异常
	 */
	public static Date string2date(String date) throws ParseException{
		return string2date(date, null);
	}
	
	/**
	 * 判断字符串非空,既不是引用为空,也不是空字符
	 * @param str 传入一个字符串
	 * @return true:字符串非空;false:字符串为空
	 */
	private static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
	
	
	/**
	 * 把传入的时间设置为开始时间,把时分秒设置为0:yyyy-MM-dd 0:00:00
	 * @param now
	 * @return
	 */
	public static Date getStartTime(Date now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE), 0, 0, 0);
		now = calendar.getTime();
		return now;
	}

	/**
	 * 把传入的时间设置为结束时间:yyyy-MM-dd 23:59:59
	 * 1.把时分秒设置为0
	 * 2.把天数加1
	 * 3.把秒数减1
	 * @param now
	 * @return
	 */
	public static Date getEndTime(Date now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE), 0, 0, 0);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.SECOND,-1);
		now = calendar.getTime();
		return now;
	}
}
