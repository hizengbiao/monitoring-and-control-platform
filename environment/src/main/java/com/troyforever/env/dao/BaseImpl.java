package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.Base;

public class BaseImpl extends HibernateDaoSupport implements BaseDao {

	@Override
	public List<Base> findAll() {
		String hql = "from Base" ;
		List list = getHibernateTemplate().find(hql) ;
		if ( list.size() == 0 )
			return null ;
		else 
			return list ;
	}

	@Override
	public Base findById(Integer id) {
		
		return getHibernateTemplate().get(Base.class, id) ;
	}

	@Override
	public Boolean save(Base base) {
		try {
			getHibernateTemplate().save(base) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(Base base) {
		try {
			getHibernateTemplate().update(base);
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
