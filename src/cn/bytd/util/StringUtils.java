package cn.bytd.util;

/**
 * 
 * 说明:判断字符串是否为空
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午2:26:35
 *
 */
public class StringUtils {
	
	/**
	 * 判断字符串是否有值
	 * @param str
	 * @return
	 */
	public static Boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}
