package com.nnrs.socket.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {
	//获取文件大小
	public static long fileSize(String path) {
		File file = new File(path);
		
		return file.length();
	}
//	public static void main(String[] args) {
//		System.out.println(FileUtil.fileSize(DirConst.getPath()+"zip/球状闪电.zip"));
//	}
	//将下载书籍存放
	public static String novelPlace(List[] lists,String novelName)  {
		String result = novelName+"下载失败";
		BufferedWriter bw = null;
		List<StringBuilder> list1 = lists[0];
		List<String> list2 = lists[1];
		String filepath = getPath()+"resources/"+novelName;
		File file = new File(filepath);
		if(file.exists()) {
			result = novelName+"已经下载";
		}else {
			file.mkdir();
			for(int i = 0; i < list2.size(); i++) {
				try {
					bw = new BufferedWriter(new FileWriter(filepath+"/"+list2.get(i)));
					bw.write(list1.get(i).toString());
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					if(bw != null) {
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			result = novelName+"下载成功";
		}
		return result;
	}
	// 检测文件是否存在
	public static boolean isFileExist(String fileName) {
		boolean result = false;
		String path = getPath()+"resources/"+fileName;
		File file = new File(path);
		if(file.exists()) {
			result = true;
		}
		return result;
	}
	public static String getPath(){
		String path =  Thread.currentThread().getContextClassLoader().getResource("index").getPath();;
		return path.substring(1, path.indexOf("bin"));
	}
}
