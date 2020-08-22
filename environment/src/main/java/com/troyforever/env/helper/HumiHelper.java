package com.troyforever.env.helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class HumiHelper {

	public static double getHumi ( Integer shedId )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("get");
			
			out.writeInt(3);
			
			double humi = in.readDouble() ;
			
			socket.close();
			
			return humi ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
	
	public static double setHumi ( Integer shedId, Integer level )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("set");
			
			out.writeInt(3);
			
			out.writeInt(level);
			
			double humi = in.readDouble() ;
			
			socket.close();
			
			return humi ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
	
	public static double putHumi ( Integer shedId, Double level )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("put");
			
			out.writeInt(3);
			
			out.writeDouble(level);
			
			double humi = in.readDouble() ;
			
			socket.close();
			
			return humi ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
}
