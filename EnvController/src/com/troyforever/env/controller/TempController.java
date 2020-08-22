package com.troyforever.env.controller;

import com.troyforever.env.bean.Shed;
import com.troyforever.env.dao.Factory;

public class TempController {

	public static double setTemp ( Integer id, Integer level )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		double temp = shed.getTemp() ;
		
		switch (level) {
		case 2:
			temp += 2 ;
			break;
		case 1:
			temp += 1 ;		
			break;
		case 0:
			temp += 0 ;
			break;
		case -1:
			temp -= 1 ;
			break;
		case -2:
			temp -= 2 ;
			break;

		default:
			break;
		}
		
		shed.setTemp(temp);
		
		Factory.getShedDao().update(shed) ;
		
		return temp ;
	}
	
	public static Double putTemp ( Integer id, Double value )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		shed.setTemp(value);
		
		if ( Factory.getShedDao().update(shed) )
			return value ;
		else
			return (double) -999 ;
	}
}
