package com.troyforever.env.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.troyforever.env.helper.GasHelper;
import com.troyforever.env.helper.HumiHelper;
import com.troyforever.env.helper.LightHelper;
import com.troyforever.env.helper.OuttempHelper;
import com.troyforever.env.helper.TempHelper;

@Controller
public class UserController {
	
	ApplicationContext tx = new ClassPathXmlApplicationContext("applicationContext.xml") ;
	
	UserDao userDao = (UserDao) tx.getBean("userDao") ;
	ShedDao shedDao = (ShedDao) tx.getBean("shedDao") ;
	BaseDao baseDao = (BaseDao) tx.getBean("baseDao") ;
	CorpDao corpDao = (CorpDao) tx.getBean("corpDao") ;
	
	UserShedDao userShedDao = (UserShedDao) tx.getBean("userShedDao") ;
	ShedCorpDao shedCorpDao = (ShedCorpDao) tx.getBean("shedCorpDao") ;
	
	@RequestMapping("/login")
	public String login ( )
	{
		return "login" ;
	}
	
	@RequestMapping("/login.do")
	public @ResponseBody String loginDo ( HttpServletRequest request, HttpSession session )
	{
		String username = request.getParameter("username") ;
		String password = request.getParameter("password") ;
		
		User user = userDao.findByUsername(username) ;
		
		if ( user == null )
			return "1000" ;
		else if ( user.getPassword().equals(password)) {
			session.setAttribute("user", user);
			return "1001" ;
		}
		else
			return "1002" ;
	}
	
	@RequestMapping("/realtime")
	public String realtime ( HttpServletRequest request )
	{
		Map map = new HashMap() ;
		
		String str = request.getParameter("shedId") ;
		Integer shedId = null ;
		if ( str != null )
		{
			shedId = Integer.parseInt(str) ; 
			Shed shed = shedDao.findById(shedId) ;
			request.getSession().setAttribute("shed", shed);
		}
		else
			shedId = ((Shed)request.getSession().getAttribute("shed")).getId() ;
		
		map.put("temp", TempHelper.getTemp(shedId)+"") ;
		map.put("light",LightHelper.getLight(shedId)+"") ;
		map.put("humi", HumiHelper.getHumi(shedId)+"") ;
		map.put("gas", GasHelper.getGas(shedId)+"") ;
		map.put("outtemp", OuttempHelper.getOuttemp(shedId)+"") ;
		
		request.setAttribute("map", map);
		
		return "realtime" ;
	}
	
	@RequestMapping("/setting")
	public String setting ( HttpServletRequest  request )
	{
		Shed shed = (Shed) request.getSession().getAttribute("shed") ;
		
		String temp = TempHelper.getTemp(shed.getId()) + "" ;
		String light = LightHelper.getLight(shed.getId()) + "" ;
		String humi = HumiHelper.getHumi(shed.getId()) + "" ;
		String gas = GasHelper.getGas(shed.getId()) + "" ;
		
		Map map = new HashMap() ;
		map.put("temp", temp) ;
		map.put("light", light) ;
		map.put("humi", humi) ;
		map.put("gas", gas) ;
		
		request.setAttribute("map", map);
		
		return "setting" ;
	}
	
	@RequestMapping("/change")
	public @ResponseBody Object change ( HttpServletRequest request )
	{
		Shed shed = (Shed) request.getSession().getAttribute("shed") ;
		Integer templevel = Integer.parseInt(request.getParameter("temp")) ;
		Integer lightlevel = Integer.parseInt(request.getParameter("light")) ;
		Integer humilevel = Integer.parseInt(request.getParameter("humi")) ;
		Integer gaslevel = Integer.parseInt(request.getParameter("gas")) ;
		
		String temp = TempHelper.setTemp(shed.getId(), templevel) + "" ;
		String light = LightHelper.setLight(shed.getId(), lightlevel) + "";
		String humi = HumiHelper.setHumi(shed.getId(), humilevel) + "";
		String gas = GasHelper.setGas(shed.getId(), gaslevel) + "" ;
		
		Map map = new HashMap() ;
		map.put("temp", temp) ;
		map.put("light", light) ;
		map.put("humi", humi) ;
		map.put("gas", gas) ;
		
		return map ;
	}
	
	@RequestMapping("/setting.do")
	public @ResponseBody String settingDo ( HttpServletRequest request )
	{
		Shed shed = (Shed) request.getSession().getAttribute("shed") ;
		
		Double temp = Double.parseDouble(request.getParameter("temp")) ;
		Double light = Double.parseDouble(request.getParameter("light")) ;
		Double humi = Double.parseDouble(request.getParameter("humi")) ;
		Double gas = Double.parseDouble(request.getParameter("gas")) ;
		
		String str = "" ;
		
		if (TempHelper.putTemp(shed.getId(), temp) != -999 )
			str += "温度预设成功" ;
		else
			str += "温度预设失败" ;
		
		if (LightHelper.putLight(shed.getId(), light) != -999 )
			str += ",光照预设成功" ;
		else
			str += ",光照预设失败" ;
		
		if (HumiHelper.putHumi(shed.getId(), humi) != -999 )
			str += ",湿度预设成功" ;
		else
			str += ",湿度预设失败" ;
		
		if (GasHelper.putGas(shed.getId(), gas) != -999 )
			str += ",CO2浓度预设成功" ;
		else
			str += ",CO2浓度预设失败" ;
		
		
		return "1001" ;
			
	}
	
	@RequestMapping("/tend")
	public String tend (HttpServletRequest request )
	{
		return "tend" ;
	}
	
	@RequestMapping("/logout")
	public void logout ( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		HttpSession session = request.getSession() ;
		
		session.removeAttribute("user");
		session.removeAttribute("shed");
		
		response.sendRedirect(request.getContextPath() + "/login" );
	}
	
	@RequestMapping("/index")
	public String index ( HttpServletRequest request )
	{
		HttpSession session = request.getSession() ;
		
		User user = (User) session.getAttribute("user") ;
		
		List<UserShed> userSheds = userShedDao.findByUser(user.getId()) ;
		
		if ( userSheds != null )
		{
			for ( UserShed userShed : userSheds )
			{
				Shed shed = shedDao.findById(userShed.getShed().getId()) ;
				userShed.setShed(shed);
			}
		}
		
		request.setAttribute("userSheds", userSheds);
		
		return "index" ;
	}
	
	@RequestMapping("/admin/user")
	public ModelAndView user ( )
	{
		ModelAndView view = new ModelAndView("/admin/user");
		
		List<User> users = userDao.findAll() ;
		
		view.addObject("users", users) ;
		
		return view ;
	}
	
	@RequestMapping("/admin/userAdd")
	public String add ( HttpServletRequest request )
	{
		List<Base> bases = baseDao.findAll() ;
		
		request.setAttribute("bases", bases);
		
		return "/admin/userAdd" ;
	}
	
	@RequestMapping("/admin/userAdd.do")
	public @ResponseBody String addDo ( HttpServletRequest request )
	{
		Integer baseId = Integer.parseInt(request.getParameter("baseId")) ;
		
		String username = request.getParameter("username") ;
		String name = request.getParameter("name") ;
		String phone = request.getParameter("phone") ;
		String password = request.getParameter("password") ;
		
		Base base = baseDao.findById(baseId) ;
		
		User user = new User() ;
		user.setUsername(username); 
		user.setPassword(password);
		user.setName(name);
		user.setPhone(phone);
		user.setBase(base);
		
		if ( userDao.save(user) )
			return "1001" ;
		else {
			return "1002" ;
		}
	}
	
	@RequestMapping("/admin/userEdit")
	public String edit ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		User user = userDao.findById(id) ;
		
		List<Base> bases = baseDao.findAll() ;
		
		request.setAttribute("user", user);
		request.setAttribute("bases", bases);
		
		return "/admin/userEdit" ;
	}
	
	@RequestMapping("/admin/userEdit.do")
	public @ResponseBody String editDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Integer baseId = Integer.parseInt(request.getParameter("baseId")) ;
		
		String password = request.getParameter("password") ;
		String name = request.getParameter("name") ;
		String phone = request.getParameter("phone") ;
		
		User user = userDao.findById(id) ;
		Base base = baseDao.findById(baseId) ;
		
		user.setPassword(password);
		user.setName(name);
		user.setPhone(phone);
		user.setBase(base);
		
		if ( userDao.update(user) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/userDelete")
	public @ResponseBody String delete ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		User user = userDao.findById(id) ;
		
		if ( user.getUserSheds().size() == 0 )
		{
			if ( userDao.delete(id) )
				return "1001" ;
			else 
				return "1002" ;
		}
		else
			return "1003" ;
		
	}
	
	@RequestMapping("/admin/user/detail")
	public String detail ( HttpServletRequest  request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		User user = userDao.findById(id) ;
		
		List<UserShed> userSheds = userShedDao.findByUser(id) ;
		
		if ( userSheds != null )
		{
			for ( UserShed userShed : userSheds )
			{
				Shed shed = shedDao.findById(userShed.getShed().getId()) ;
				Base base = baseDao.findById(shed.getBase().getId()) ;
				
				base.setSheds(null);
				base.setUsers(null);
				
				shed.setBase(base);
				
				shed.setLogEnvs(null);
				shed.setLogOpes(null);
				shed.setSettings(null);
				shed.setShedCorps(null);
				shed.setUserSheds(null);
				userShed.setShed(shed);
			}
			
		}
		
		request.setAttribute("user", user);
		request.setAttribute("userSheds", userSheds);
		
		return "admin/userDetail" ;
	}
	
	@RequestMapping("/admin/user/shedAdd")
	public String shedAdd ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		User user = userDao.findById(id) ;
		
		List<Shed> sheds = shedDao.findAll() ;
		
		request.setAttribute("sheds", sheds);
		request.setAttribute("user", user);
		
		return "/admin/userShedAdd" ;
	}
	
	@RequestMapping("/admin/user/shedAdd.do")
	public @ResponseBody String shedAddDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ;
		
		User user = userDao.findById(id) ;
		Shed shed = shedDao.findById(shedId) ;
		
		UserShed userShed = new UserShed() ;
		userShed.setUser(user);
		userShed.setShed(shed);
	 	
		if ( userShedDao.save(userShed) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/user/shedDelete")
	public @ResponseBody String shedDelete ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		if ( userShedDao.delete(id) )
			return "1001" ;
		else
			return "1002" ;
	}
	

			
}
