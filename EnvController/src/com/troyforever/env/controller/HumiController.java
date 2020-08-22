package com.troyforever.env.controller;

import com.troyforever.env.bean.Shed;
import com.troyforever.env.dao.Factory;

public class HumiController {

	public static double setHumi ( Integer id, Integer level )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		double humi = shed.getHumi() ;
		
		switch (level) {
		case 2:
			humi += 2 ;
			break;
		case 1:
			humi += 1 ;		
			break;
		case 0:
			humi += 0 ;
			break;
		case -1:
			humi -= 1 ;
			break;
		case -2:
			humi -= 2 ;
			break;

		default:
			break;
		}
		
		shed.setHumi(humi);
		
		Factory.getShedDao().update(shed) ;
		
		return humi ;
	}
	
	public static Double putHumi ( Integer id, Double value )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		shed.setHumi(value);
		
		if ( Factory.getShedDao().update(shed) )
			return value ;
		else
			return (double) -999 ;
	}
}
