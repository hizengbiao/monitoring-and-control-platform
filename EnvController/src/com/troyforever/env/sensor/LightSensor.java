package com.troyforever.env.sensor;

import com.troyforever.env.dao.Factory;

import com.troyforever.env.bean.Shed;

public class LightSensor {

	public static double getLight ( Integer id )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		return shed.getLight() ;
	}
}
