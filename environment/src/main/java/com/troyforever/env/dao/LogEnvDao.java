package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.LogEnv;

public interface LogEnvDao {
	
	List<LogEnv> findAll ( ) ;
	
	List<LogEnv> findByShed ( Integer shedId ) ;
	
	List<LogEnv> findByShedAndType ( Integer shedId, Integer type ) ;
	
	LogEnv findById ( Integer id ) ;
	
	Boolean save ( LogEnv logEnv ) ;
	
	Boolean update ( LogEnv logEnv ) ;
	
	Boolean delete ( Integer id ) ;
}
