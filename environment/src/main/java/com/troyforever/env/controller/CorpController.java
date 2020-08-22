package com.troyforever.env.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Detail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.troyforever.env.bean.Corp;
import com.troyforever.env.bean.Shed;
import com.troyforever.env.bean.ShedCorp;
import com.troyforever.env.bean.UserShed;
import com.troyforever.env.dao.BaseDao;
import com.troyforever.env.dao.CorpDao;
import com.troyforever.env.dao.ShedCorpDao;
import com.troyforever.env.dao.ShedDao;

@Controller
public class CorpController {

	ApplicationContext tx = new ClassPathXmlApplicationContext("applicationContext.xml") ;

	ShedDao shedDao = (ShedDao) tx.getBean("shedDao") ;
	CorpDao corpDao = (CorpDao) tx.getBean("corpDao") ;
	BaseDao baseDao = (BaseDao) tx.getBean("baseDao") ;
	ShedCorpDao shedCorpDao = (ShedCorpDao) tx.getBean("shedCorpDao") ;
	
	@RequestMapping("/admin/corp")
	public ModelAndView index ( )
	{
		ModelAndView view = new ModelAndView("/admin/corp") ;
		
		List<Corp> corps = corpDao.findAll() ;

		if ( corps != null ) {
		
			for ( int i = 0 ; i < corps.size() ; i ++ )
			{
				Set<ShedCorp> shedCorps = new HashSet<ShedCorp>() ;
				for ( ShedCorp shedCorp : corps.get(i).getShedCorps() )
				{
					shedCorp.setCorp(null);
					shedCorp.setShed(null);
					shedCorps.add(shedCorp) ;
	 			}
				
				corps.get(i).setShedCorps(shedCorps);
			}	
		}
		
		view.addObject("corps", corps) ;
		
		return view ;
	}
	
	@RequestMapping("/admin/corpAdd")
	public String add ( HttpServletRequest request )
	{
		List<Shed> sheds = shedDao.findAll() ;
		
		request.setAttribute("sheds", sheds);
		
		return "/admin/corpAdd" ;
	}
	
	@RequestMapping("/admin/corpAdd.do")
	public @ResponseBody String addDo ( HttpServletRequest request )
	{
		String name = request.getParameter("name") ;
		String code = request.getParameter("code") ;
		
		Corp corp = new Corp() ;
		corp.setCode(code);
		corp.setName(name);
		
		if ( corpDao.save(corp) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/corpEdit")
	public String edit ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Corp corp = corpDao.findById(id) ;
		
		request.setAttribute("corp", corp);
		
		return "/admin/corpEdit" ;
	}
	
	@RequestMapping("/admin/corpEdit.do")
	public @ResponseBody String editDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		String name = request.getParameter("name") ;
		
		String code = request.getParameter("code") ;
		
		Corp corp = corpDao.findById(id) ;
		corp.setName(name);
		corp.setCode(code);
		
		if ( corpDao.update(corp) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/corpDelete")
	public @ResponseBody String delete ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		if ( corpDao.delete(id) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/corp/detail")
	public String detail ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Corp corp = corpDao.findById(id) ;
		
		Set<ShedCorp> shedCorps = corp.getShedCorps() ;
		
		for ( ShedCorp shedCorp : shedCorps )
		{
			Shed shed = shedDao.findById(shedCorp.getShed().getId()) ;
			shed.setBase(baseDao.findById(shed.getBase().getId()));
			shedCorp.setShed(shed);
		}
		
		request.setAttribute("corp", corp);
		request.setAttribute("shedCorps", shedCorps);
		
		return "/admin/corpDetail" ;
	}
	
	@RequestMapping("/admin/corp/shedAdd")
	public String shedAdd ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Corp corp = corpDao.findById(id) ;
		
		List<Shed> sheds = shedDao.findAll() ;
		
		request.setAttribute("corp", corp);
		request.setAttribute("sheds", sheds);
		
		return "/admin/corpShedAdd" ;
	}
	
	@RequestMapping("/admin/corp/shedAdd.do")
	public @ResponseBody String shedAddDo ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		Integer shedId = Integer.parseInt(request.getParameter("shedId")) ;
		
		Corp corp = corpDao.findById(id) ;
		
		Shed shed = shedDao.findById(shedId) ;
		
		ShedCorp shedCorp = new ShedCorp() ;
		
		shedCorp.setShed(shed); 
		shedCorp.setCorp(corp); 
		
		if ( shedCorpDao.save(shedCorp) )
			return "1001" ;
		else
			return "1002" ;
	}
	
	@RequestMapping("/admin/corp/shedDelete")
	public @ResponseBody String shedDelete ( HttpServletRequest request )
	{
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		
		if ( shedCorpDao.delete(id) )
			return "1001" ;
		else
			return "1002" ;
	}
}
