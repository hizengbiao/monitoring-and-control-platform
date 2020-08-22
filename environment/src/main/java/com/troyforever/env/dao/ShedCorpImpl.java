package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.ShedCorp;

public class ShedCorpImpl extends HibernateDaoSupport implements ShedCorpDao {

	@Override
	public List<ShedCorp> findAll() {
		
		String hql = "from ShedCorp" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<ShedCorp> findByShed(Integer shedId) {
		
		String hql = "from ShedCorp s where s.shed.id = " + shedId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<ShedCorp> findByCorp(Integer corpId) {
		
		String hql = "from ShedCorp s where s.corp.id = " + corpId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public ShedCorp findById(Integer id) {
		
		return getHibernateTemplate().get(ShedCorp.class, id) ;
	}

	@Override
	public Boolean save(ShedCorp shedCorp) {
		
		try {
			getHibernateTemplate().save(shedCorp) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(ShedCorp shedCorp) {
		try {
			getHibernateTemplate().update(shedCorp);
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
