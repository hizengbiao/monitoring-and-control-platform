package com.troyforever.env.sqlHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
	
	public static Connection getConnection ( ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver") ;
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/environment?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8","root","1314520") ;
		
		return conn ;
	}
}
