package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.Setting;

public interface SettingDao {

	List<Setting> findAll ( ) ;
	
	List<Setting> findByUser ( Integer userId ) ;
	
	List<Setting> findByShed ( Integer shedId ) ;
	
	Setting findById ( Integer id ) ;
	
	Boolean save ( Setting setting ) ;
	
	Boolean update ( Setting setting ) ;
	
	Boolean delete ( Integer id ) ;
}
