package com.troyforever.env.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.troyforever.env.bean.Base;
import com.troyforever.env.sqlHelper.SQLHelper;

public class BaseImpl implements BaseDao {

	@Override
	public List<Base> findAll() {
		
		String sql = "select * from env_base" ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			
			PreparedStatement state = conn.prepareStatement(sql) ;
			
			ResultSet result = state.executeQuery() ;
			
			List<Base> list = new ArrayList<Base>() ;
			
			if ( result.next() )
			{
				result.previous() ;
				
				while ( result.next() )
				{
					Base base = new Base() ;
					base.setId(result.getInt(1));
					base.setName(result.getString(2));
					base.setAddress(result.getString(3));
					
					list.add(base) ;
 				}
				
				conn.close();
				
				return list ;
			}
			else
			{
				conn.close();
				return null ;
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Base findById(Integer id) {
		
		String sql = "select * from env_base where id = " + id ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			ResultSet result = conn.prepareStatement(sql).executeQuery() ;
			
			if ( result.next() )
			{
				Base base = new Base() ;
				
				base.setId(result.getInt(1));
				base.setName(result.getString(2));
				base.setAddress(result.getString(3));
				
				return base ;
			}
			else
				return null ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Boolean save(Base base) {
		
		String sql = "insert into env_base ( name, address ) values (?,?) " ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, base.getName() );
			
			statement.setString(2, base.getAddress());
			
			if ( statement.executeUpdate() == 0 )
				return false ;
			else
				return true ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean update(Base base) {
		
		String sql = "update env_base set name = ? , address = ? where id = ?" ; 
		
		try {
			PreparedStatement statement = SQLHelper.getConnection().prepareStatement(sql);
			
			statement.setString(1, base.getName() );
			
			statement.setString(2, base.getAddress());
			
			statement.setInt(3, base.getId());
			
			if ( statement.executeUpdate() == 0 )
				return false ;
			else
				return true ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		
		String sql = "delete from env_base where id = ?" ; 
		
		try {
			PreparedStatement statement = SQLHelper.getConnection().prepareStatement(sql);
			
			statement.setInt(1, id);
			
			if ( statement.executeUpdate() == 0 )
				return false ;
			else
				return true ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Base base) {
		
		String sql = "delete from env_base where id = ?" ; 
		
		try {
			PreparedStatement statement = SQLHelper.getConnection().prepareStatement(sql);
			
			statement.setInt(1, base.getId());
			
			if ( statement.executeUpdate() == 0 )
				return false ;
			else
				return true ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
