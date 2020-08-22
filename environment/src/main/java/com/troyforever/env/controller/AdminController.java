package com.troyforever.env.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.troyforever.env.bean.User;
import com.troyforever.env.dao.BaseDao;
import com.troyforever.env.dao.ShedDao;
import com.troyforever.env.dao.UserDao;
import com.troyforever.env.dao.UserShedDao;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	ApplicationContext tx = new ClassPathXmlApplicationContext("applicationContext.xml") ;
	
	UserDao userDao = (UserDao) tx.getBean("userDao") ;
	ShedDao shedDao = (ShedDao) tx.getBean("shedDao") ;
	BaseDao baseDao = (BaseDao) tx.getBean("baseDao") ;
	
	UserShedDao userShedDao = (UserShedDao) tx.getBean("userShedDao") ;
	
	@RequestMapping({"/main","/"})
	public String main ( )
	{
		return "admin/main" ;
	}
	
	@RequestMapping("/index")
	public String index ( )
	{
		return "admin/index" ;
	}
	
	@RequestMapping("/login")
	public String login ( )
	{
		return "admin/login" ;
	}
	
	@RequestMapping("login.do")
	public @ResponseBody String loginDo ( HttpServletRequest request, HttpServletResponse response, HttpSession session ) throws IOException
	{
		
		String username = request.getParameter("username") ;
		String password = request.getParameter("password") ;
		
		User user = userDao.findByUsername(username) ;
		
		if ( user == null )
			return "1000" ;
		else if ( user.getPassword().equals(password)) {
			if ( user.getIsAdmin() == 1 )
			{
				session.setAttribute("admin", user);
				return "1001" ;
			}
			else
			{
				return "1003" ;
			}
		}
		else
			return "1002" ;
	}
	
	@RequestMapping("/logout")
	public String logout ( HttpSession session )
	{
		session.removeAttribute("admin");
		
		return "admin/login" ;
	}
	
	@RequestMapping("center")
	public ModelAndView center ( HttpSession session )
	{
		User admin = (User) session.getAttribute("admin") ;
		
		ModelAndView view = new ModelAndView("/admin/center") ;
		
		view.addObject("admin", admin) ;
		
		return view ;
	}
	
	@RequestMapping("center.do")
	public @ResponseBody String centerDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		String name = (String) request.getParameter("name") ;
		String phone = (String) request.getParameter("phone") ;
		String password = (String) request.getParameter("password") ;
		
		User user = userDao.findById(id) ;
		
		user.setName(name);
		user.setPhone(phone);
		user.setPassword(password);
		
		if ( userDao.update(user) )
		{
			request.getSession().setAttribute("admin", user);
			return "1001" ;
		}
		else
			return "1002" ;

	}
}
