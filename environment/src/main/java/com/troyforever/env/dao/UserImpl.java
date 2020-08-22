package com.troyforever.env.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.User;

public class UserImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public List<User> findAll() {
		
		String hql = "from User" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<User> findByBase( Integer baseId ) {
		
		String hql = "from User u where u.base.id = " + baseId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public User findById(Integer id) {
		
		return getHibernateTemplate().get(User.class, id) ;
	}

	@Override
	public User findByUsername(String username) {

		String hql = "from User u where u.username = '" + username + "'" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return (User) list.get(0) ;
	}

	@Override
	public Boolean save(User user) {
		
		try {
			getHibernateTemplate().save(user) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(User user) {
		
		try {
			getHibernateTemplate().update(user);
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
