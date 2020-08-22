package com.troyforever.env.helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GasHelper {

	public static double getGas ( Integer shedId )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("get");
			
			out.writeInt(4);
			
			double gas = in.readDouble() ;
			
			socket.close();
			
			return gas ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
	
	public static double setGas ( Integer shedId, Integer level )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("set");
			
			out.writeInt(4);
			
			out.writeInt(level);
			
			double gas = in.readDouble() ;
			
			socket.close();
			
			return gas ;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return -999 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -999 ;
		}
	}
	
	public static double putGas ( Integer shedId, Double level )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("put");
			
			out.writeInt(4);
			
			out.writeDouble(level);
			
			double gas = in.readDouble() ;
			
			socket.close();
			
			return gas ;
			
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
