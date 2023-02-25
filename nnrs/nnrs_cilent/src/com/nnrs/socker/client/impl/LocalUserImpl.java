package com.nnrs.socker.client.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.nnrs.socker.client.LocalUser;
import com.nnrs.socket.util.FileUtil;
import com.nnrs.socket.util.NumberUtil;


public class LocalUserImpl implements LocalUser {

	@Override
	public StringBuilder readNovel(String fileName,int chapter) throws Exception {
		StringBuilder chapter_word = new StringBuilder();
		File dir = new File(FileUtil.getPath()+"resources/"+fileName);
		File[] files = dir.listFiles();
		String chapterName = "";
		for(File f : files) {
			int i = NumberUtil.nTranferC(f.getName().substring(1, f.getName().indexOf("章")));
			if(chapter == i) {
				chapterName = f.getName();
				break;
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileUtil.getPath()+"resources/"+fileName+"/"+chapterName),"GBK"));
		String read = "";		    
        while((read = br.readLine()) != null){		    	
            chapter_word.append(read+"\n");
        }
        br.close();
		return chapter_word;
	}
	@Override
	public List<String> showNovelList() throws Exception {
		List<String> list = new ArrayList<>();
		File dir = new File(FileUtil.getPath()+"resources/");
		File[] files = dir.listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				list.add(f.getName());
			}
		}
		return list;
	}


	@Override
	public List<String> showHistory() throws Exception {
		List<String> list = new ArrayList<>();
		String path = FileUtil.getPath()+"bin/localhistory.log";
		BufferedReader fe = new BufferedReader(new FileReader(path));
		String read = "";
		while((read = fe.readLine()) != null) {
			list.add(read);
		}
		return list;
	}
	@Override
	public int novelChapterNum(String fileName) throws Exception {
		File dir = new File(FileUtil.getPath()+"resources/"+fileName);
		File[] files = dir.listFiles();
		return files.length;
	}
	
	@Override
	public List<String> showCatalogue(String fileName) throws Exception {
		List<String> list = new ArrayList<>();
		File dir = new File(FileUtil.getPath()+"resources/"+fileName);
		String[] files = dir.list();
		String chapterName = "";
		int i = 1;
		while(true) {
			for(int j = 0; j < files.length; j++) {
				if(NumberUtil.nTranferC(files[j].substring(1,files[j].indexOf('章')))  != i) {
					continue;
				}
				list.add(files[j].substring(0,files[j].indexOf('.')));
				i++;
			}
			if(list.size() == novelChapterNum(fileName)) {
				break;
			}
		}
		return list;
	}

}
