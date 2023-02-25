package com.ebooks.util;

import java.io.File;



public class FileUtil {
	public static String getPath(){
		String path =  Thread.currentThread().getContextClassLoader().getResource("index").getPath();;
		
		return path.substring(1, path.indexOf("bin"));
	}
	// �?测文件是否存�?
		public static boolean isFileExist(String fileName) {
			boolean result = false;
			String path = FileUtil.getPath()+"book/"+fileName;
			File file = new File(path);
			if(file.exists()) {
				result = true;
			}
			return result;
		}
}

