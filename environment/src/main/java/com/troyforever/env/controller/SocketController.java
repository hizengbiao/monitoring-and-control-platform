package com.troyforever.env.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.troyforever.env.bean.*;
import com.troyforever.env.dao.*;
import com.troyforever.env.helper.* ;

@Controller
@RequestMapping("/socket")
public class SocketController {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	BaseDao basedao = (BaseDao) context.getBean("baseDao") ;
	UserDao userdao = (UserDao) context.getBean("userDao") ;
	ShedDao sheddao = (ShedDao) context.getBean("shedDao") ;
	CorpDao corpDao = (CorpDao) context.getBean("corpDao") ;
	UserShedDao usersheddao = (UserShedDao) context.getBean("userShedDao") ;
	ShedCorpDao shedcorpdao = (ShedCorpDao) context.getBean("shedCorpDao") ;
	
	@RequestMapping("/login")
	public @ResponseBody Object login ( HttpServletRequest request )
	{
		String username = request.getParameter("username") ;
		String password = request.getParameter("password") ;
		
		User user = userdao.findByUsername(username) ;
		
		Map map = new HashMap () ; 
		
		if ( user == null )
		{
			map.put("msg", "1000") ;
		}
		else if ( user.getPassword().equals(password) )
		{
			map.put("msg", "1001") ;
			
			Base base = basedao.findById(user.getBase().getId()) ;
			base.setSheds(null);
			base.setUsers(null);
			
			map.put("base", base) ;
			
			user.setBase(null);
			user.setLogOpes(null);
			user.setSettings(null);
			user.setUserSheds(null);
			
			map.put("user", user) ;
			
			List<UserShed> list = usersheddao.findByUser(user.getId()) ;
			
			List sheds = new ArrayList () ;
			
			for ( int i = 0 ; i < list.size() ; i ++ )
			{
				Shed shed = sheddao.findById(list.get(i).getShed().getId()) ;
				shed.setBase(null);
				shed.setLogEnvs(null);
				shed.setSettings(null);
				shed.setShedCorps(null);
				shed.setLogOpes(null);
				shed.setUserSheds(null);
				sheds.add(shed) ;
			}
			
			map.put("sheds", sheds) ;
			
			return map ;
		}
		else {
			map.put("msg", "1002") ;
		}
		
		return map ;
	}
	
	@RequestMapping("/shed/get")
	public @ResponseBody Object shed ( HttpServletRequest request )
	{
		Map map = new HashMap() ;
		
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ; 
		
		Shed shed = sheddao.findById(shedId) ;
		shed.setBase(null);
		shed.setLogEnvs(null);
		shed.setSettings(null);
		shed.setShedCorps(null);
		shed.setLogOpes(null);
		shed.setUserSheds(null);
		
		map.put("shed", shed) ;
		
		List<ShedCorp> shedCorps = shedcorpdao.findByShed(shedId) ;
		
		List<Corp> corps = new ArrayList<Corp>();
		
		for ( ShedCorp shedCorp : shedCorps )
		{
			Corp corp = corpDao.findById(shedCorp.getCorp().getId()) ;
			
			corp.setShedCorps(null);
			
			corps.add(corp) ;
		}
		
		if ( corps.size() != 0 )
		{
			map.put("corps", corps) ;
			map.put("msg", "suc") ;
		}
		else
			map.put("msg", "fail") ;
		
		map.put("temp", TempHelper.getTemp(shedId)+"") ;
		map.put("light",LightHelper.getLight(shedId)+"") ;
		map.put("humi", HumiHelper.getHumi(shedId)+"") ;
		map.put("gas", GasHelper.getGas(shedId)+"") ;
		map.put("outtemp", OuttempHelper.getOuttemp(shedId)+"") ;
		
		return map ;
	}
	
	@RequestMapping("/shed/get/{type}")
	public @ResponseBody Object getByType ( HttpServletRequest request, @PathVariable Integer type )
	{
		Map map = new HashMap() ;
		
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ; 
		
		switch (type) {
		case 1:
			map.put("temp", TempHelper.getTemp(shedId)) ;
			break;
			
		case 2:
			map.put("light", LightHelper.getLight(shedId)) ;		
			break;
		case 3:
			map.put("humi", HumiHelper.getHumi(shedId)) ;
			break;
		case 4:
			map.put("gas", GasHelper.getGas(shedId)) ;
			break;
		case 5:
			map.put("outtemp", OuttempHelper.getOuttemp(shedId)) ;
			break;
		default:
			break;
		}
		
		return map ;
	}
	
	@RequestMapping("/corp/get")
	public @ResponseBody Object getCorp ( HttpServletRequest request )
	{
		
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ;
		
		List<ShedCorp> list = shedcorpdao.findByShed(shedId) ;
		
		List<Corp> corps = new ArrayList<Corp>() ;
		
		for ( ShedCorp shedCorp : list )
		{
			Corp corp = corpDao.findById(shedCorp.getCorp().getId()) ;
			corp.setShedCorps(null);
			
			corps.add(corp) ;
		}
		
		return corps ;
	}
	
	@RequestMapping("/shed/set")
	public @ResponseBody Object setAll ( HttpServletRequest request )
	{
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ;
		Double temp = Double.parseDouble(request.getParameter("temp")) ;
		Double light = Double.parseDouble(request.getParameter("light")) ;
		Double humi = Double.parseDouble(request.getParameter("humi")) ;
		Double gas = Double.parseDouble(request.getParameter("gas")) ;
		
		Map map = new HashMap() ;
		
		if ( TempHelper.putTemp(shedId, temp) != -999 )
			map.put("temp", "温度预设成功") ;
		else
			map.put("temp", "温度预设失败") ;
		
		if ( LightHelper.putLight(shedId, light) != -999 )
			map.put("light", "光照预设成功") ;
		else
			map.put("light", "光照预设失败") ;
		
		if ( HumiHelper.putHumi(shedId, humi) != -999 )
			map.put("humi", "湿度预设成功") ;
		else
			map.put("humi", "湿度预设失败") ;
		
		if ( GasHelper.putGas(shedId, gas) != -999 )
			map.put("gas", "CO2浓度预设成功") ;
		else
			map.put("gas", "CO2浓度预设失败") ;
		
		return map ;
	}
}
