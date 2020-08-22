package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.UserShed;

public interface UserShedDao {

	List<UserShed> findAll ( ) ;
	
	List<UserShed> findByUser ( Integer userId ) ;
	
	List<UserShed> findByShed ( Integer shedId ) ;
	
	UserShed findById ( Integer id ) ;
	
	Boolean save ( UserShed userShed ) ;
	
	Boolean update ( UserShed userShed ) ;
	
	Boolean delete ( Integer id ) ;
}
