package com.pc.pconsumption.util;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;





public class DBHelper {
	private  static String DRIVER ;
	private static String URL  ;
	private static String USER;
	private static String PASS;
	private transient static Logger log = Logger.getLogger(DBHelper.class.getName());
	static{
		String mysql = "/mysql.properties";
		String sqlConn = DBHelper.class.getResource(mysql).getPath();
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(sqlConn);
			prop.load(fis);
			DRIVER = (String) prop.get(ConfigKeys.JDBC_DRIVER);
			URL = (String) prop.get(ConfigKeys.JDBC_URL);
			USER = (String) prop.get(ConfigKeys.JDBC_USER);
			PASS = (String) prop.get(ConfigKeys.JDBC_PASS);
			fis.close();
		} catch (FileNotFoundException e) {
			log.info("文件"+mysql+"没有找到...");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("读取文件"+mysql+"失败...");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		open();
	}
	public static Connection open(){
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	public static void close(Connection conn,Statement st,ResultSet rs){
		try {
			if(st != null)
				st.close();
			if(rs != null)
				rs.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
