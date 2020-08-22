package com.troyforever.env.helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TempHelper {

	public static double getTemp ( Integer shedId )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("get");
			
			out.writeInt(1);
			
			double temp = in.readDouble() ;
			
			socket.close();
			
			return temp ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
	
	public static double setTemp ( Integer shedId, Integer level )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("set");
			
			out.writeInt(1);
			
			out.writeInt(level);
			
			double value = in.readDouble() ;
			
			socket.close();
			
			return value ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
	
	
	public static double putTemp ( Integer shedId, Double level )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("put");
			
			out.writeInt(1);
			
			out.writeDouble(level);
			
			double value = in.readDouble() ;
			
			socket.close();
			
			return value ;
			
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
