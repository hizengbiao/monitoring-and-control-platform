package com.troyforever.env.dao;

public class Factory {

	public static BaseDao getBaseDao ( )
	{
		return new BaseImpl() ;
	}
	
	public static ShedDao getShedDao ( )
	{
		return new ShedImpl() ;
	}
}
