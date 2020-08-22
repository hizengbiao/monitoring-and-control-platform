package com.troyforever.env.dao;

import java.util.List;
import com.troyforever.env.bean.ShedCorp;

public interface ShedCorpDao {

	List<ShedCorp> findAll ( ) ;
	
	List<ShedCorp> findByShed ( Integer shedId ) ;
	
	List<ShedCorp> findByCorp ( Integer corpId ) ;
	
	ShedCorp findById ( Integer id ) ;
	
	Boolean save ( ShedCorp shedCorp ) ;
	
	Boolean update ( ShedCorp shedCorp ) ;
	
	Boolean delete ( Integer id ) ;
}
