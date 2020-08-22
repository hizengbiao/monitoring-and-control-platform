package com.troyforever.env.test;

import java.util.List;

import com.troyforever.env.bean.Base;
import com.troyforever.env.bean.Shed;
import com.troyforever.env.dao.BaseDao;
import com.troyforever.env.dao.BaseImpl;
import com.troyforever.env.dao.ShedDao;
import com.troyforever.env.dao.ShedImpl;

public class Test {
	
	public static void main ( String [] args )
	{
		BaseDao basedao = new BaseImpl() ;
		
		ShedDao sheddao = new ShedImpl() ;
		
		Base base = new Base() ;
		base.setId(1);
		
//		Shed shed = new Shed ( ) ;
//		
//		shed.setCode("hehe");
//		shed.setTemp(1.1);
//		shed.setLight(2.2);
//		shed.setHumi(3.3);
//		shed.setGas(4.4);
//		shed.setOuttemp(5.5);
//		shed.setBase(base);
		
//		List<Shed> list = sheddao.findByBase(base) ;
		System.out.println(Thread.currentThread().getName());
//		
//		for ( int i = 0 ; i < list.size() ; i ++ )
//			System.out.println(list.get(i).getCode());
	}
}
