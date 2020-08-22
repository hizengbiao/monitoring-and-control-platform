package com.troyforever.env.helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class OuttempHelper {

	public static double getOuttemp ( Integer shedId )
	{
		try {
			Socket socket = new Socket(Info.IP, Info.PORT ) ;
			
			DataInputStream in = new DataInputStream(socket.getInputStream()) ;
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()) ;
			
			out.writeInt(shedId);
			
			out.writeUTF("get");
			
			out.writeInt(5);
			
			double outtemp = in.readDouble() ;
			
			socket.close();
			
			return outtemp ;
			
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
