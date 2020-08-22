package com.troyforever.env.controller;

import com.troyforever.env.bean.Shed;
import com.troyforever.env.dao.Factory;

public class GasController {

	public static double setGas ( Integer id, Integer level )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		double gas = shed.getGas() ;
		
		switch (level) {
		case 2:
			gas += 2 ;
			break;
		case 1:
			gas += 1 ;		
			break;
		case 0:
			gas += 0 ;
			break;
		case -1:
			gas -= 1 ;
			break;
		case -2:
			gas -= 2 ;
			break;

		default:
			break;
		}
		
		shed.setGas(gas);
		
		Factory.getShedDao().update(shed) ;
		
		return gas ;
	}
	
	public static Double putGas ( Integer id, Double value )
	{
		Shed shed = Factory.getShedDao().findById(id) ;
		
		shed.setGas(value);
		
		if ( Factory.getShedDao().update(shed) )
			return value ;
		else
			return (double) -999 ;
	}
}
