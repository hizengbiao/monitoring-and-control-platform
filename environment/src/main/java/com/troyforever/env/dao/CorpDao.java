package com.troyforever.env.dao;

import java.util.List;

import com.troyforever.env.bean.*;

public interface CorpDao {

	List<Corp> findAll ( ) ;
	
	List<Corp> findByShed ( Integer shedId ) ;
	
	Corp findById ( Integer id ) ;
	
	Boolean save ( Corp corp ) ;
	
	Boolean update ( Corp corp ) ;
	
	Boolean delete ( Integer id ) ;
}
