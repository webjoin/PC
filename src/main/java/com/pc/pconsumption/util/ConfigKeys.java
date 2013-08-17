package com.pc.pconsumption.util;

public class ConfigKeys {
	public static final String JDBC_SAVE   = "jdbc.save";
	public static final String JDBC_UPDATE = "jdbc.update";
	public static final String JDBC_DLETE  = "jdbc.delete";
	public static final String JDBC_QUERY  = "jdbc.query";
	public static final String JDBC_QUERY_SINGLE  = "jdbc.singleQuery";
	public static final String SYNCHRO_DATE = "synchro.date";
	public static final String JDBC_DRIVER = "jdbc.driver";
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_USER = "jdbc.user";
	public static final String JDBC_PASS = "jdbc.pass"; 
	
	public static class  XmlClazz{
		/**
		 * 数据同步文件地址 
		 */
		public static final String XML_PATH = "/p-consumption.xml";
	}
	
	private ConfigKeys(){}
}
