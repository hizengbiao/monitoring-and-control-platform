package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.Shed;

public class ShedImpl extends HibernateDaoSupport implements ShedDao {

	@Override
	public List<Shed> findAll() {
		
		String hql = "from Shed" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<Shed> findByBase(Integer baseId) {
		
		String hql = "from Shed s where s.base.id = " + baseId ; 
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public Shed findById(Integer id) {

		return getHibernateTemplate().get(Shed.class, id) ;
	}

	@Override
	public Boolean save(Shed shed) {
		
		try {
			getHibernateTemplate().save(shed) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(Shed shed) {
		
		try {
			getHibernateTemplate().update(shed);
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
