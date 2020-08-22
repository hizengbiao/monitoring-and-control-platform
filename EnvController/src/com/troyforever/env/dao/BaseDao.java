package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.Base;

public interface BaseDao {
	
	List<Base> findAll ( ) ;
	
	Base findById ( Integer id ) ;
	
	Boolean save ( Base base ) ;
	
	Boolean update ( Base base ) ;
	
	Boolean delete ( Integer id ) ;
	
	Boolean delete ( Base base ) ;
}
