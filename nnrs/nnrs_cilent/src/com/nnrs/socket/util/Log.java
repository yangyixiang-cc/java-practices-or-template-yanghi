package com.nnrs.socket.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 记录日志类
 */
public class Log {
	public static void println(String dosome){
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream(FileUtil.getPath()+"bin/localhistory.log", true));
			//获取当前时间
			Date nowTime = new Date();
			//日期格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		    String strTime = sdf.format(nowTime);
		    //这时会输出到log文件中
		    ps.println(strTime+"："+dosome);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				ps.close();
			}
		}
	}
}
