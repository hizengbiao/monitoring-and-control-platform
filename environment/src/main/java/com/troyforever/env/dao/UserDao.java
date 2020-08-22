package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.User;

public interface UserDao {

	List<User> findAll ( ) ;
	
	List<User> findByBase ( Integer baseId ) ;
	
	User findById ( Integer id ) ;
	
	User findByUsername ( String username ) ;
	
	Boolean save ( User user ) ;
	
	Boolean update ( User user ) ;
	
	Boolean delete ( Integer id ) ;
}
