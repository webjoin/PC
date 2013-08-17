package com.pc.pconsumption.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemUtils {
	
	public static void main(String[] args) {
		System.out.println(getStrByDatePattern(new Date(), "yyyy-MM-dd hh:mm:ss"));
	}
	/** 针对 字符串判断 
	 *  if(str != null && "".equals(str))
	 *    return true
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		if(str != null && "".equals(str))
			return true;
		return false;
	}
	/**
	 * 只要这个对象不是null 就放回 true
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj){
		if(obj != null)
			return true;
		return false;
	}
	/** 时间字符 yyyy-MM-dd hh:mm:ss
	 * @param date  new Date();
	 * @param pattern yyyy-MM-dd hh:mm:ss
	 * @return 匹配不到 返回 空对象
	 */
	public static String getStrByDatePattern(Date date,String pattern){
		if( isNotNull(date) && isNotBlank(pattern))
			return new SimpleDateFormat(pattern).format(date);
		return null;
	}
	
	public static Date getDateByStr(String date,String pattern){
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Timestamp getCurrentTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}

}
