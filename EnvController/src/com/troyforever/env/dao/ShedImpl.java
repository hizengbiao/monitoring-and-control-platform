package com.troyforever.env.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.troyforever.env.bean.Base;
import com.troyforever.env.bean.Shed;
import com.troyforever.env.sqlHelper.SQLHelper;

public class ShedImpl implements ShedDao {

	@Override
	public List<Shed> findAll() {
		
		String sql = "select * from env_shed" ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			ResultSet result = conn.prepareStatement(sql).executeQuery() ;
			
			if ( result.next() )
			{
				List<Shed> list = new ArrayList<Shed>() ;
				
				result.previous() ;
				
				while ( result.next() )
				{
					Shed shed = new Shed() ;
					
					shed.setId(result.getInt(1));
					shed.setCode(result.getString(2));
					shed.setTemp(result.getDouble(3));
					shed.setLight(result.getDouble(4));
					shed.setHumi(result.getDouble(5));
					shed.setGas(result.getDouble(6));
					shed.setOuttemp(result.getDouble(7));
					
					Base base = new Base() ;
					base.setId(result.getInt(8));
					
					shed.setBase(base);
					
					list.add(shed) ;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	@Override
	public List<Shed> findByBase(Base base) {
		
		String sql = "select * from env_shed where baseId = " + base.getId() ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			ResultSet result = conn.prepareStatement(sql).executeQuery() ;
			
			if ( result.next() )
			{
				List<Shed> list = new ArrayList<Shed>() ;
				
				result.previous() ;
				
				while ( result.next() )
				{
					Shed shed = new Shed() ;
					
					shed.setId(result.getInt(1));
					shed.setCode(result.getString(2));
					shed.setTemp(result.getDouble(3));
					shed.setLight(result.getDouble(4));
					shed.setHumi(result.getDouble(5));
					shed.setGas(result.getDouble(6));
					shed.setOuttemp(result.getDouble(7));
					
					shed.setBase(base);
					
					list.add(shed) ;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}

	@Override
	public Shed findById(Integer id) {
		String sql = "select * from env_shed where id = " + id ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			ResultSet result = conn.prepareStatement(sql).executeQuery() ;
			
			if ( result.next() )
			{
				Shed shed = new Shed() ;
				
				shed.setId(result.getInt(1));
				shed.setCode(result.getString(2));
				shed.setTemp(result.getDouble(3));
				shed.setLight(result.getDouble(4));
				shed.setHumi(result.getDouble(5));
				shed.setGas(result.getDouble(6));
				shed.setOuttemp(result.getDouble(7));
				
				Base base = new Base() ;
				base.setId(result.getInt(8));
				
				shed.setBase(base);
				conn.close();
				return shed ;
			}
			else
			{
				conn.close();
				return null ;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}

	@Override
	public Boolean save(Shed shed) {
		String sql = "insert into env_shed ( code, temp, light, humi, gas, outtemp, baseId ) values"
				+ "(?,?,?,?,?,?,?)" ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			PreparedStatement statement = conn.prepareStatement(sql) ;
			
			statement.setString(1, shed.getCode());
			statement.setDouble(2, shed.getTemp());
			statement.setDouble(3, shed.getLight());
			statement.setDouble(4, shed.getHumi());
			statement.setDouble(5, shed.getGas());
			statement.setDouble(6, shed.getOuttemp());
			statement.setInt(7, shed.getBase().getId());
			
			if ( statement.executeUpdate() == 0 )
			{	
				conn.close();
				return false ;
			}
			else
			{
				conn.close();
				return true ;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}

	@Override
	public Boolean update(Shed shed) {
		String sql = "update env_shed set code = ?, temp = ?, light = ?, humi = ?, gas = ?, outtemp = ?, baseId = ? where id = ?" ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			PreparedStatement statement = conn.prepareStatement(sql) ;
			
			statement.setString(1, shed.getCode());
			statement.setDouble(2, shed.getTemp());
			statement.setDouble(3, shed.getLight());
			statement.setDouble(4, shed.getHumi());
			statement.setDouble(5, shed.getGas());
			statement.setDouble(6, shed.getOuttemp());
			statement.setInt(7, shed.getBase().getId());
			statement.setInt(8, shed.getId());
			
			if ( statement.executeUpdate() == 0 )
			{	
				conn.close();
				return false ;
			}
			else
			{
				conn.close();
				return true ;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}

	@Override
	public Boolean delete(Shed shed) {
		String sql = "delete from env_shed where id = " + shed.getId() ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			PreparedStatement statement = conn.prepareStatement(sql) ;
			
			if ( statement.executeUpdate() == 0 )
			{	
				conn.close();
				return false ;
			}
			else
			{
				conn.close();
				return true ;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}

	@Override
	public Boolean delete(Integer id) {
		String sql = "delete from env_shed where id = " + id ;
		
		try {
			Connection conn = SQLHelper.getConnection() ;
			PreparedStatement statement = conn.prepareStatement(sql) ;
			
			if ( statement.executeUpdate() == 0 )
			{	
				conn.close();
				return false ;
			}
			else
			{
				conn.close();
				return true ;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}

}
