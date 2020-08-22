package com.troyforever.env.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.ant.ListTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.troyforever.env.bean.LogOpe;
import com.troyforever.env.bean.Shed;
import com.troyforever.env.bean.User;
import com.troyforever.env.dao.LogEnvDao;
import com.troyforever.env.dao.LogOpeDao;
import com.troyforever.env.dao.ShedDao;
import com.troyforever.env.dao.UserDao;

@Controller
public class LogController {

	ApplicationContext tx = new ClassPathXmlApplicationContext("applicationContext.xml") ;

	ShedDao shedDao = (ShedDao) tx.getBean("shedDao") ;
	UserDao userDao = (UserDao) tx.getBean("userDao") ;
 	LogEnvDao logEnvDao = (LogEnvDao) tx.getBean("logEnvDao") ;
	LogOpeDao logOpeDao = (LogOpeDao) tx.getBean("logOpeDao") ;
	
	@RequestMapping("/admin/envlog")
	public ModelAndView envlog ( )
	{
		ModelAndView view = new ModelAndView("/admin/envlog");
		
		return view ;
	}
	
	@RequestMapping("/admin/opelog")
	public String opelog ( HttpServletRequest request )
	{	
		List<User> users = userDao.findAll() ;
		List<Shed> sheds = shedDao.findAll() ;
		
		request.setAttribute("users", users);
		request.setAttribute("sheds", sheds);
		
		return "/admin/opelog" ;
	}
	
	@RequestMapping("/admin/opelog.do")
	public @ResponseBody Object opelogDo ( HttpServletRequest request )
	{
		Integer userId = Integer.parseInt(request.getParameter("userId")) ;
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ;
		List<LogOpe> logOpes = null ;
		
		if ( userId != 0 )
		{	
			if ( shedId != 0 )
			{				
				logOpes = logOpeDao.findByUserAndShed(userId, shedId) ;
			}
			else
			{
				logOpes = logOpeDao.findByUser(userId) ;
			}
		}
		else
		{
			if ( shedId != 0 )
			{
				logOpes = logOpeDao.findByShed(shedId) ;
			}
			else
			{
				logOpes = logOpeDao.findAll();
			}
		}
		
		if ( logOpes != null )
		{
			for ( int i = 0 ; i < logOpes.size() ; i ++ )
			{
				Shed shed = shedDao.findById(logOpes.get(i).getShed().getId()) ;
				
				shed.setBase(null);
				shed.setLogEnvs(null);
				shed.setLogOpes(null);
				shed.setSettings(null);
				shed.setShedCorps(null);
				shed.setUserSheds(null);
				
				User user = userDao.findById(logOpes.get(i).getUser().getId()) ;
				
				user.setSettings(null);
				user.setLogOpes(null);
				user.setUserSheds(null);
				user.setBase(null);
				
				logOpes.get(i).setShed(shed);
				logOpes.get(i).setUser(user);
			}
		}
		
		return logOpes ;
	}
}
