package com.nnrs.socket.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NnrsServer {
    public static void main(String[] args){
    	ServerSocket ss;
		try {
			ss = new ServerSocket(6699);
			 System.out.println("服务已经已经启动，正在监听6699端口...");
		        for (;;) {
		            Socket sock = ss.accept();
		            System.out.println("connected from " + sock.getRemoteSocketAddress());
		            Thread t = new NnrsThread(sock);
		            t.start();  
		        }
		} catch (IOException e) {
			System.out.println("服务端启动失败，可能服务端已经启动或服务器受损，请检查！");
		} 
        
    }

}
