package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.Shed;

public interface ShedDao {

	List<Shed> findAll ( ) ;
	
	List<Shed> findByBase ( Integer baseId ) ;
	
	Shed findById ( Integer id ) ;
	
	Boolean save ( Shed shed ) ;
	
	Boolean update ( Shed shed ) ;
	
	Boolean delete ( Integer id ) ;
}
