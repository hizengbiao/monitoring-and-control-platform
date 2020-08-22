package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.LogEnv;

public class LogEnvImpl extends HibernateDaoSupport implements LogEnvDao {

	@Override
	public List<LogEnv> findAll() {
		
		String hql = "from LogEnv" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<LogEnv> findByShed(Integer shedId) {
		
		String hql = "from LogEnv l where l.shed.id = " + shedId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<LogEnv> findByShedAndType(Integer shedId, Integer type) {
		
		String hql = "from LogEnv l where l.shed.id = " + shedId + " and l.type = " + type ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public LogEnv findById(Integer id) {
		
		return getHibernateTemplate().get(LogEnv.class, id) ;
	}

	@Override
	public Boolean save(LogEnv logEnv) {

		try {
			getHibernateTemplate().save(logEnv) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(LogEnv logEnv) {
		
		try {
			getHibernateTemplate().update(logEnv);
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
