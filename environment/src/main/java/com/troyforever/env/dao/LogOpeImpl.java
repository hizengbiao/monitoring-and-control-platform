package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.LogOpe;

public class LogOpeImpl extends HibernateDaoSupport implements LogOpeDao {

	@Override
	public List<LogOpe> findAll() {
		
		String hql = "from LogOpe" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<LogOpe> findByUser(Integer userId) {
		
		String hql = "from LogOpe l where l.user.id = " + userId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<LogOpe> findByShed(Integer shedId) {
		
		String hql = "from LogOpe l where l.shed.id = " + shedId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
 		if ( list.size() == 0 )
 			return null ;
 		else
 			return list ;
	}
	
	@Override
	public List<LogOpe> findByUserAndShed(Integer userId, Integer shedId) {
		String hql = "from LogOpe l where l.user.id = " + userId + " and l.shed.id = " + shedId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
 		if ( list.size() == 0 )
 			return null ;
 		else
 			return list ;
	}


	@Override
	public LogOpe findById(Integer id) {
		
		return getHibernateTemplate().get(LogOpe.class, id) ;
	}

	@Override
	public Boolean save(LogOpe logOpe) {
		
		try {
			getHibernateTemplate().save(logOpe) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(LogOpe logOpe) {
		try {
			getHibernateTemplate().update(logOpe);
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			getHibernateTemplate().delete(findById(id));
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}


}
