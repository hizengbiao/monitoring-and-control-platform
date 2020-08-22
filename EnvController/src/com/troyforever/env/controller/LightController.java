package com.troyforever.env.controller;

import com.troyforever.env.bean.Shed;
import com.troyforever.env.dao.Factory;

public class LightController {

	public static double setLight ( Integer id, Integer level )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		double light = shed.getLight() ;
		
		switch (level) {
		case 2:
			light += 2 ;
			break;
		case 1:
			light += 1 ;		
			break;
		case 0:
			light += 0 ;
			break;
		case -1:
			light -= 1 ;
			break;
		case -2:
			light -= 2 ;
			break;

		default:
			break;
		}
		
		shed.setLight(light);
		
		Factory.getShedDao().update(shed) ;
		
		return light ;
	}
	
	public static Double putLight ( Integer id, Double value )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		shed.setLight(value);
		
		if ( Factory.getShedDao().update(shed) )
			return value ;
		else
			return (double) -999 ;
	}
}
