package com.troyforever.env.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.springframework.web.filter.OncePerRequestFilter;

import com.troyforever.env.bean.User;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String [] noFilter = {"images","js","css","login","fonts","socket"} ;
		
		String uri = request.getRequestURI() ;
		
		boolean doFilter = true ;
		
		for ( String s : noFilter )
		{
			if ( uri.indexOf(s) != -1 )
			{
				doFilter = false ;
				break ;
			}
		}
		
		if ( doFilter )
		{
			User user = (User) request.getSession().getAttribute("user") ;
			
			User admin = (User) request.getSession().getAttribute("admin") ;
			
			if ( uri.indexOf("admin") != -1 )
			{
				if ( admin != null )
					filterChain.doFilter(request, response);
				else
					response.sendRedirect(request.getContextPath() + "/admin/login");
			}
			else
			{
				if ( user != null )
					filterChain.doFilter(request, response);
				else
				{
					response.sendRedirect(request.getContextPath() + "/login");
				}
					
			}
				
			
//			if ( user == null )
//			{
//				if ( uri.indexOf("admin") == -1 )
//					response.sendRedirect(request.getContextPath() + "/login");
//				else
//					response.sendRedirect(request.getContextPath() + "/admin/login");
//			}
//			else {
//				
//				if ( uri.indexOf("admin") == -1 ){
//					if ( role == null )
//						filterChain.doFilter(request, response);
//					else
//					{
//						request.getSession().removeAttribute("user");
//						request.getSession().removeAttribute("role");
//						response.sendRedirect(request.getContextPath() + "/login");
//					}
//				}
//				else
//				{
//					
//					if ( role != null )
//						filterChain.doFilter(request, response);
//					else
//					{
//						request.getSession().removeAttribute("user");
//						response.sendRedirect(request.getContextPath() + "/admin/login");
//					}
//				}
//			}
		}
		else
			filterChain.doFilter(request, response);
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request)
	{
		String header = request.getHeader("X-Requested-With");
		if ( header != null && header.equals("XMLHttpRequest"))
			return true ;
		else
			return false ;
	}

}
