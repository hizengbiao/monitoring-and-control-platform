package com.troyforever.env.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.troyforever.env.bean.Base;
import com.troyforever.env.bean.Shed;
import com.troyforever.env.bean.User;
import com.troyforever.env.dao.BaseDao;

@Controller
@RequestMapping()
public class BaseController {

	ApplicationContext tx = new ClassPathXmlApplicationContext("applicationContext.xml") ;
	
	BaseDao baseDao = (BaseDao) tx.getBean("baseDao") ;
	
	@RequestMapping("/admin/base")
	public ModelAndView index ( HttpServletRequest request )
	{
		ModelAndView view = new ModelAndView("/admin/base");
		
		List<Base> bases = baseDao.findAll() ;
		
		view.addObject("bases", bases) ;
		
		return view ;
	}
	
	@RequestMapping("/admin/baseAdd")
	public String add ( )
	{
		return "/admin/baseAdd" ;
	}
	
	@RequestMapping("/admin/baseAdd.do")
	public @ResponseBody String add ( HttpServletRequest request )
	{
		String name = request.getParameter("name") ;
		String address = request.getParameter("address") ;
		
		Base base = new Base() ;
		base.setName(name);
		base.setAddress(address);
		
		if ( baseDao.save(base) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/baseEdit")
	public String edit ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Base base = baseDao.findById(id) ;
		
		request.setAttribute("base", base);
		
		return "/admin/baseEdit" ;
	}
	
	@RequestMapping("/admin/baseEdit.do")
	public @ResponseBody String editDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		String name = request.getParameter("name") ;
		String address = request.getParameter("address") ;
		
		Base base = baseDao.findById(id) ;
		
		base.setName(name); 
		base.setAddress(address);
		
		if ( baseDao.update(base) )
			return "1001" ;
		else
			return "1002" ;
		
	}
	
	@RequestMapping("/admin/baseDelete")
	public @ResponseBody String delete ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Base base = baseDao.findById(id) ;
		
		if ( base.getSheds().size() == 0 && base.getUsers().size() == 0 )
		{
			if ( baseDao.delete(id))
				return "1001" ;
			else
				return "1002" ;
		}
		else 
			return "1003" ;
	}
	
	@RequestMapping("/admin/base/detail")
	public String detail ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Base base = baseDao.findById(id) ;
		
		request.setAttribute("base", base);
		
		return "/admin/baseDetail" ;
	}
}
