package com.nnrs.socket.util;

import java.io.File;

public class FileUtil {
	//获取文件大小
	public static long fileSize(String path) {
		File file = new File(path);
		return file.length();  
	}
	public static int chapterNum(String fileName) throws Exception {
		File dir = new File(getPath()+"resources/"+fileName);
		File[] files = dir.listFiles();
		return files.length;
	}
	public static String getPath(){
		String path =  Thread.currentThread().getContextClassLoader().getResource("connection_information.properties").getPath();;
		return path.substring(1, path.indexOf("bin"));
	}
}
