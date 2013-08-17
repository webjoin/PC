package com.pc.pconsumption.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;

import com.pc.pconsumption.util.SystemUtils;

public class SystemGlobal {
	private static transient Logger log = Logger.getLogger("");
	public static Map<String,String> defaults = null;

	// Json Container
	private static ObjectMapper mapper = null;
	
	public static ObjectMapper getObjectMapper(){
		if(SystemUtils.isNotNull(mapper)){
			return mapper;
		}
		return new ObjectMapper();
	}
	public static String getValue(String variable){
		return defaults.get(variable);
	}
	
	@SuppressWarnings("unchecked")
	public static void getSystemGlobalSql() throws Exception{
		
		Properties props = new Properties();
		String sqlScript = SystemGlobal.class.getResource("/SystemGlobal.properties").getPath();
		log.info("Ω≈±æ≈‰÷√‘⁄ "+sqlScript);
		InputStream is =  new FileInputStream(sqlScript);
		try {
			props.load(is);
			is.close();
		} catch (IOException e) {
			System.out.println("The file SystemGlobal.properties not found!");
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		defaults = new HashMap<String,String>();
		Enumeration<String> en = (Enumeration<String>) props.propertyNames();
		while(en.hasMoreElements()){
			String key = en.nextElement();
			String value = (String) props.get(key);
			defaults.put(key, value);
		}
	}
	public static void main(String[] args)  {
//		try {
//			getSystemGlobalSql();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Map<String,String> map = defaults;
//		for (Map.Entry entry : map.entrySet()) {
//			System.out.println(entry.getKey()+"     "+entry.getValue());
//		}
//		System.out.println(SystemGlobal.class.getResource(".").getPath());
	}

}
