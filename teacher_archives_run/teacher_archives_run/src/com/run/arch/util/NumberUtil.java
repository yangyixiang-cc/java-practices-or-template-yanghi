package com.run.arch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class NumberUtil {

	/**
	 * 根据传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 *
	 * @param sformat yyyyMMddhhmmss
	 * @return
	 */
	private static String getDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 根据传入的时间对象，返回当前时间的字符串
	 *
	 * @return String
	 */
	public static String dateToStr(Date date) {
		if (Objects.isNull(date)){
			return "null";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 根据传入的时间字符串，返回Date对象 yyyy-MM-dd HH:mm:ss
	 * @param timeStr
	 * @return
	 */

	public static Date strToDate(String timeStr){
		if (Objects.isNull(timeStr)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getRandomNum(int num) {
		String numStr = "";
		for (int i = 0; i < num; i++) {
			numStr += (int) (10 * (Math.random()));
		}
		return numStr;
	}

	/**
	 * 生成id
	 * 
	 * @return
	 */
	public static Long getGeneratID() {
		String sformat = "MMddhhmmssSSS";
		int num = 3;
		String idStr = getDate(sformat) + getRandomNum(num);
		Long id = Long.valueOf(idStr);
		return id;
	}



}

