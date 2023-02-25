package ebooks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * 1. 安装Python爬虫所需的模块
 * 2. 调用爬虫程序，爬取图书并存储
 * */
public class Reptile implements Serializable {
    private static final long serialVersionUID = -5182532647273106745L;
    private Map book;

    public Map getBook() {
        return book;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reptile other = (Reptile) obj;
        if (book == null) {
            if (other.book != null)
                return false;
        } else if (!book.equals(other.book))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reptile [book=" + book.values() + "]";
    }

    public void setBook(Map book) {
        this.book = book;
    }

    public void installModule() throws IOException {
        Process p1 = Runtime.getRuntime().exec("python -c \"import requests\"");
        java.io.InputStream is = p1.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        if (br.readLine() == null) {
            System.out.println("requests模块已经安装");
        } else {
            System.out.println("正在安装requests模块--------------------");
            Process p2 = Runtime.getRuntime().exec("pip3 install requests");
            if (!p2.isAlive()) {
                System.out.println("requests模块安装完成");
            }
        }
        br.close();
    }

    public void callPython() throws IOException, InterruptedException {
        System.out.println("正在爬取");
        String url = "./CrawlingEBooks.py";
        String[] args1 = new String[]{"python ", url};
        Process pr = Runtime.getRuntime().exec(args1);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        int i = 1;
        book = new HashMap();
        while ((line = in.readLine()) != null) {
            book.put(i, line);
            i++;
        }
        in.close();
        pr.waitFor();
        pr.destroy();
        book.forEach((x, y) -> {
            System.out.println("爬取完成：" + x + ":" + y);
        });
        System.out.println("爬取完毕");
    }
}
