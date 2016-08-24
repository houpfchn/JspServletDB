package com.sevenun.util;

import java.sql.*;
import java.util.Properties;
import java.io.*;

public class DBUtil {
	
	private static Connection conn;
	
	public static Connection getConnection() {
		if(conn != null) {
			return conn;
		} else {
			Properties prop = new Properties();
			InputStream inputStream = DBUtil.class.getResourceAsStream("/db.properties");
			
			try {
				prop.load(inputStream);
				
				String url = prop.getProperty("db_url");
				String user = prop.getProperty("db_user");
				String pwd = prop.getProperty("db_pwd");
				String driver = prop.getProperty("db_driver");
				
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
		}
	}
}
