package com.troyforever.env.controller;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.sql.Delete;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.troyforever.env.bean.Base;
import com.troyforever.env.bean.Corp;
import com.troyforever.env.bean.Shed;
import com.troyforever.env.bean.ShedCorp;
import com.troyforever.env.bean.User;
import com.troyforever.env.bean.UserShed;
import com.troyforever.env.dao.BaseDao;
import com.troyforever.env.dao.CorpDao;
import com.troyforever.env.dao.ShedCorpDao;
import com.troyforever.env.dao.ShedDao;
import com.troyforever.env.dao.UserDao;
import com.troyforever.env.dao.UserShedDao;

@Controller
public class ShedController {
	
	ApplicationContext tx = new ClassPathXmlApplicationContext("applicationContext.xml") ;
	
	BaseDao baseDao = (BaseDao) tx.getBean("baseDao") ;
	ShedDao shedDao = (ShedDao) tx.getBean("shedDao") ;
	CorpDao corpDao = (CorpDao) tx.getBean("corpDao") ;
	UserDao userDao = (UserDao) tx.getBean("userDao") ;
	ShedCorpDao shedCorpDao = (ShedCorpDao) tx.getBean("shedCorpDao") ;
	UserShedDao userShedDao = (UserShedDao) tx.getBean("userShedDao") ;
	
	@RequestMapping("/admin/shed")
	public ModelAndView index ( HttpServletRequest request )
	{
		ModelAndView view = new ModelAndView("/admin/shed");
		
		List<Shed> sheds = shedDao.findAll() ;
		
		for ( int i = 0 ; i < sheds.size() ; i ++ )
			sheds.get(i).setBase(baseDao.findById(sheds.get(i).getBase().getId()));
		
		view.addObject("sheds", sheds) ;
		
		return view ;
		
	}
	
	@RequestMapping("/admin/shedAdd")
	public String add ( HttpServletRequest request )
	{
		List<Base> bases = baseDao.findAll() ;
		
		request.setAttribute("bases", bases);
		
		return "/admin/shedAdd" ;
	}
	
	@RequestMapping("/admin/shedAdd.do")
	public @ResponseBody String addDo ( HttpServletRequest request)
	{
		Integer baseId = Integer.parseInt(request.getParameter("baseId")) ;
		
		String code = request.getParameter("code") ;
		
		Base base = baseDao.findById(baseId) ;
		
		Shed shed = new Shed() ;
		
		shed.setCode(code);
		shed.setBase(base);
		
		if ( shedDao.save(shed) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/shedEdit")
	public String edit ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Shed shed = shedDao.findById(id) ;
		
		List<Base> bases = baseDao.findAll() ;
		
		request.setAttribute("shed", shed);
		request.setAttribute("bases", bases);
		
		return "/admin/shedEdit" ;
	}
	
	@RequestMapping("/admin/shedEdit.do")
	public @ResponseBody String editDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Integer baseId = Integer.parseInt(request.getParameter("baseId")) ;
		
		String code = request.getParameter("code") ;
		
		Shed shed = shedDao.findById(id) ;
		
		shed.setCode(code); 
		
		shed.setBase(baseDao.findById(baseId));
		
		if ( shedDao.update(shed) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/shedDelete")
	public @ResponseBody String delete ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Shed shed = shedDao.findById(id) ;
		
		if ( shed.getShedCorps().size() == 0 )
		{
			if ( shedDao.delete(id) )
				return "1001" ;
			else
				return "1002" ;
		}
		else
			return "1003" ;
	}
	
	@RequestMapping("/admin/shed/detail")
	public String detail ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Shed shed = shedDao.findById(id) ;
		shed.setBase(baseDao.findById(shed.getBase().getId()));
		
		for ( ShedCorp shedCorp : shed.getShedCorps() )
		{
			Corp corp = corpDao.findById(shedCorp.getCorp().getId()) ;
			shedCorp.setCorp(corp);
		}
			
		request.setAttribute("shed", shed);
		
		return "/admin/shedDetail" ;
	}
	
	@RequestMapping("/admin/shed/addCorp")
	public String addCorp ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		Shed shed = shedDao.findById(id) ;
		List<Corp> corps = corpDao.findAll() ;
		
		request.setAttribute("shed", shed);
		request.setAttribute("corps", corps);
		return "/admin/shedCorpAdd" ;
	}
	
	@RequestMapping("/admin/shed/addCorp.do")
	public @ResponseBody String addCorpDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		Integer corpId = Integer.parseInt(request.getParameter("corpId")) ;
		
		Shed shed = shedDao.findById(id) ;
		Corp corp = corpDao.findById(corpId) ;
		
		ShedCorp shedCorp = new ShedCorp() ;
		shedCorp.setShed(shed);
		shedCorp.setCorp(corp);
		
		if ( shedCorpDao.save(shedCorp) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/shed/addUser")
	public String addUser ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Shed shed = shedDao.findById(id) ;
		
		List<User> users = userDao.findAll() ;
		
		request.setAttribute("shed", shed);
		request.setAttribute("users", users);
		
		return "/admin/shedUserAdd" ;
	}
	
	@RequestMapping("/admin/shed/addUser。do")
	public @ResponseBody String addUserDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		Integer userId = Integer.parseInt(request.getParameter("userId")) ;
		
		System.out.println(id);
		System.out.println(userId);
		
		Shed shed = shedDao.findById(id) ;
		User user = userDao.findById(userId) ;
		
		UserShed userShed = new UserShed() ;
		userShed.setUser(user); 
		userShed.setShed(shed);
		
		if ( userShedDao.save(userShed) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/shed/deleteCorp")
	public @ResponseBody String deleteCorp ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		if ( shedCorpDao.delete(id) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/shed/deleteUser")
	public @ResponseBody String deleteUser ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		if ( userShedDao.delete(id) )
			return "1001" ;
		else
			return "1002" ;
	}
}
