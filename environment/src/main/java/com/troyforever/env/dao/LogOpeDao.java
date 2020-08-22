package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.LogOpe;

public interface LogOpeDao {

	List<LogOpe> findAll ( ) ;
	
	List<LogOpe> findByUser ( Integer userId ) ;
	
	List<LogOpe> findByShed ( Integer shedId ) ;
	
	List<LogOpe> findByUserAndShed ( Integer userId, Integer shedId ) ;
	
	LogOpe findById ( Integer id ) ;
	
	Boolean save ( LogOpe logOpe ) ;
	
	Boolean update ( LogOpe logOpe ) ;
	
	Boolean delete ( Integer id ) ;
}
