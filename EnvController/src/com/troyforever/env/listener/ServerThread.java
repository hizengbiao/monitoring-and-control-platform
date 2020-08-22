package com.troyforever.env.listener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.troyforever.env.controller.GasController;
import com.troyforever.env.controller.HumiController;
import com.troyforever.env.controller.LightController;
import com.troyforever.env.controller.TempController;
import com.troyforever.env.sensor.GasSensor;
import com.troyforever.env.sensor.HumiSensor;
import com.troyforever.env.sensor.LightSensor;
import com.troyforever.env.sensor.OuttempSensor;
import com.troyforever.env.sensor.TempSensor;

public class ServerThread extends Thread {

	Socket socket ;
	
	DataInputStream in = null ;
	
	DataOutputStream out = null ;
	
	public ServerThread(Socket socket) {
		
		this.socket = socket ;
		
		try {
			in = new DataInputStream(socket.getInputStream()) ;
			out = new DataOutputStream(socket.getOutputStream()) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run ( )
	{
		try {
			Integer id = in.readInt() ;
			
			String method = in.readUTF() ;
			
			Integer type = in.readInt() ;
			
			if ( method.equals("get") )
			{
				switch (type) {
				case 1:
					out.writeDouble(TempSensor.getTemp(id));
					break;
				case 2:
					out.writeDouble(LightSensor.getLight(id));
					break;
				case 3:
					out.writeDouble(HumiSensor.getHimi(id));
					break;
				case 4:
					out.writeDouble(GasSensor.getGas(id));
					break;
				case 5:
					out.writeDouble(OuttempSensor.getOuttemp(id));
				default:
					out.writeDouble(-999);
					break;
				}
			}
			
			if ( method.equals("set") )
			{
				Integer level = in.readInt() ;
				
				switch (type) {
				case 1:
					out.writeDouble(TempController.setTemp(id, level));
					break;
				case 2:
					out.writeDouble(LightController.setLight(id, level));
					break;
				case 3:
					out.writeDouble(HumiController.setHumi(id, level));
					break;
				case 4:
					out.writeDouble(GasController.setGas(id, level));
					break;
				default:
					out.writeDouble(-999);
					break;
				}
				
			}
			
			if ( method.equals("put") )
			{
				Double value = in.readDouble() ;
				
				switch (type) {
				case 1:
					out.writeDouble(TempController.putTemp(id, value));
					break;
				case 2:
					out.writeDouble(LightController.putLight(id, value));
					break;
				case 3:
					out.writeDouble(HumiController.putHumi(id, value));
					break;
				case 4:
					out.writeDouble(GasController.putGas(id, value));
					break;
				default:
					out.writeDouble(-999);
					break;
				}
				
			}
			
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
