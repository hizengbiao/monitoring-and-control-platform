package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.UserShed;

public class UserShedImpl extends HibernateDaoSupport implements UserShedDao {

	@Override
	public List<UserShed> findAll() {
		
		String hql = "from UserShed" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<UserShed> findByUser(Integer userId) {
		
		String hql = "from UserShed u where u.user.id = " + userId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<UserShed> findByShed(Integer shedId) {
		
		String hql = "from UserShed u where u.shed.id = " + shedId ;
		
		List list =getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public UserShed findById(Integer id) {
		
		return getHibernateTemplate().get(UserShed.class, id) ;
	}

	@Override
	public Boolean save(UserShed userShed) {
		
		try {
			getHibernateTemplate().save(userShed) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(UserShed userShed) {
		
		try {
			getHibernateTemplate().update(userShed);
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
