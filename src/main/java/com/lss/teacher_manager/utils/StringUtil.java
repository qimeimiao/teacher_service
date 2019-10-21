package com.lss.teacher_manager.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtil {
	
	protected final static Logger log = LoggerFactory.getLogger(StringUtil.class);

	public final static String getSysUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * @名称：trim
	 * @功能：去除字符串两端空格
	 * @参数：需要处理的字符串
	 * @返回: 去掉了两端空格的字符串，如果str为null，则返回""
	 */
	public static String trim(String str) {
		if (str != null) {
			return str.trim();
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @名称：isNullString
	 * @功能：去掉两端空格后，判断字符串是否为空
	 * @参数：需要处理的字符串
	 * @返回: null、""、"null"都返回true
	 */
	public static boolean isNullString(String str) {
		return (null == str || StringUtils.isBlank(str.trim()) || "null".equals(str.trim().toLowerCase()) ? true : false);
	}

	/**
	 * 
	 * @名称：formatString
	 * @功能：格式化字符串
	 * @参数：需要处理的字符串
	 * @返回: 传入的字符串参数如果为空，返回""；如果不为空，返回字符串本身值
	 */
	public static String formatString(String str) {
		if (isNullString(str)) {
			return "";
		} else {
			return str;
		}
	}
	
	
	public static String formatString(Object str) {
		if (str==null) {
			return "";
		} else {
			return str.toString();
		}
	}

	/**
	 * 
	 * @名称：subStringByByte
	 * @功能：截取字符串，字母、汉字都可以，汉字不会截取半
	 * @参数：str: 需要处理的字符串；n：截取的长度，字母数，如果为汉字，一个汉字等于两个字母数
	 * @返回: 截取后的字符串
	 */
	public static String subStringByByte(String str, int n) {
		int num = 0;
		try {
			byte[] buf = str.getBytes("GBK");
			if (n >= buf.length) {
				return str;
			}
			boolean bChineseFirstHalf = false;
			for (int i = 0; i < n; i++) {
				if (buf[i] < 0 && !bChineseFirstHalf) {
					bChineseFirstHalf = true;
				} else {
					num++;
					bChineseFirstHalf = false;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// 将错误信息保存在日志里
			log.error(StringUtil.getTrace(e));
			
			e.printStackTrace();
		}
		return str.substring(0, num);
	}

	/**
	 * 
	 * @名称：isNumber
	 * @功能：判断是不是数字
	 * @参数：需要处理的字符串
	 * @返回: 如果是数字返回true，不是则返回false
	 */
	public static boolean isNumber(String strNumber) {
		boolean bool = false;
		try {
			Double.parseDouble(strNumber);
			bool = true;
		} catch (NumberFormatException e) {
			bool = false;
		}
		return bool;
	}

	public static String UUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * 
	 * @名称：getNewString
	 * @功能：将集合内的字符串组装成一个新的以"$n"为分隔的字符串
	 * @参数：需要处理的字符串集合
	 * @返回: 返回一个新的以"$n"为分隔的字符串
	 */
	public static String getNewString(List<String> strList) {
		StringBuffer buff = new StringBuffer();
		for (String str : strList) {
			buff.append(str).append("$n");
		}

		return buff.substring(0, buff.length() - 2);
	}

	/***************************************************************************
	 * 敏感字符检查
	 * 
	 * @param str
	 *            传入字符串
	 */
	public static boolean inputFiltration(String str) {
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(str).find()) {
			return false;
		}
		return true;
	}

	/***************************************************************************
	 * 替换敏感符号
	 * 
	 * @param str传入字符串
	 * @return 处理过后的字符
	 */
	public static String replaceInput(String str) {
		return "".equals(str) || str == null ? "" : str.replaceAll(".*([';]+|(--)+).*", " ");
	}
	
	/**
	 * *
	 * @名称：getTrace
	 * @功能：处理异常字符串
	 * @编写者：张浩
	 * @时间：2015-9-15下午3:30:03
	 */
	public static String getTrace(Throwable e) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
