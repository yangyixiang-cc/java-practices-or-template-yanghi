package com.nnrs.socket.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.regex.Pattern;

public class NumberUtil {
	public static int nTranferC(String chineseNumber) {
		String aval = "零一二三四五六七八九";
		String bval = "十百千万亿";
		int[] bnum = { 10, 100, 1000, 10000, 100000000 };
		int num = 0;
		Pattern pattern = Pattern.compile("[0-9]*");
		if (pattern.matcher(chineseNumber).matches()) {
			num = Integer.valueOf(chineseNumber);
		} else {
			char[] arr = chineseNumber.toCharArray();
			int len = arr.length;
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 0; i < len; i++) {
				char s = arr[i];
				if (s == '零') {
					continue;
				}
				int index = bval.indexOf(s);
				if (index == -1) {
					stack.push(aval.indexOf(s));
				} else {
					int tempsum = 0;
					int val = bnum[index];
					if (stack.isEmpty()) {
						stack.push(val);
						continue;
					}
					while (!stack.isEmpty() && stack.peek() < val) {
						tempsum += stack.pop();
					}
					if (tempsum == 0) {
						stack.push(val);
					} else {
						stack.push(tempsum * val);
					}
				}
			}
			while (!stack.isEmpty()) {
				num += stack.pop();
			}
		}

		return num;
	}

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

