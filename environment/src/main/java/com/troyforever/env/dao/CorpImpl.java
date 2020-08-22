package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.Corp;

public class CorpImpl extends HibernateDaoSupport implements CorpDao {

	@Override
	public List<Corp> findAll() {
		
		String hql = "from Corp" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else 
			return list ;
	}

	@Override
	public List<Corp> findByShed(Integer shedId) {
		
		String hql = "from Corp c where c.shed.id = " + shedId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public Corp findById(Integer id) {
		
		return getHibernateTemplate().get(Corp.class, id) ;
	}

	@Override
	public Boolean save(Corp corp) {
		try {
			getHibernateTemplate().save(corp) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(Corp corp) {
		try {
			getHibernateTemplate().update(corp);
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
