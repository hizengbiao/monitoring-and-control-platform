package com.troyforever.env.sensor;

import com.troyforever.env.bean.Shed;
import com.troyforever.env.dao.Factory;

public class HumiSensor {

	public static double getHimi ( Integer id )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		return shed.getHumi() ;
	}
}
