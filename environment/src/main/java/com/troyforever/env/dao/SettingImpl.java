package com.troyforever.env.dao;

import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.troyforever.env.bean.Setting;

public class SettingImpl extends HibernateDaoSupport implements SettingDao {

	@Override
	public List<Setting> findAll() {
		
		String hql = "from Setting" ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<Setting> findByUser(Integer userId) {
		
		String hql = "from Setting s where s.user.id = " + userId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public List<Setting> findByShed(Integer shedId) {
		
		String hql = "from Setting s where s.shed.id = " + shedId ;
		
		List list = getHibernateTemplate().find(hql) ;
		
		if ( list.size() == 0 )
			return null ;
		else
			return list ;
	}

	@Override
	public Setting findById(Integer id) {
		
		return getHibernateTemplate().get(Setting.class, id) ;
	}

	@Override
	public Boolean save(Setting setting) {
		try {
			getHibernateTemplate().save(setting) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public Boolean update(Setting setting) {
		
		try {
			getHibernateTemplate().update(setting);
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
