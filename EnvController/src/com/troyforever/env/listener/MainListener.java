package com.troyforever.env.listener;

import java.net.ServerSocket;
import java.net.Socket;

public class MainListener {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ServerSocket server = null ;
		
		Socket you = null ;
		
		while ( true )
		{
			try {
				server = new ServerSocket(5244) ;
			} catch (Exception e) {
				System.out.println("正在监听客户端请求");
			}
			
			try {
				System.out.println("等待客户端呼叫");
				you = server.accept() ;
				System.out.println("客户端地址：" + you.getInetAddress() );
			} catch (Exception e) {
				System.out.println("正在等待客户");
			}
			
			if ( you != null )
			{
				new ServerThread(you).start();
			}
		}

	}

}
