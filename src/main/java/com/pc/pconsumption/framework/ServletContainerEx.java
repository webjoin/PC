package com.pc.pconsumption.framework;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.mysql.jdbc.StringUtils;
import com.pc.pconsumption.util.ConfigKeys;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class ServletContainerEx extends ServletContainer{

	private static transient Logger log = Logger.getLogger(ServletContainerEx.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -5357334069372619272L;

	public void init() throws ServletException {
		super.init();
		try {
			SystemGlobal.getSystemGlobalSql();
			synchroData(SystemGlobal.getValue(ConfigKeys.SYNCHRO_DATE));
		} catch (Exception e) {
			log.info("容器初始化失败！");
			e.printStackTrace();
			throw new ServletException("容器初始化失败！");
		}
	}
	
	/**
	 * 主要是同步本月数据
	 * 同步数据是指的将 数据库中的数据 和 本地的xml 文件中的数据 保持一致
	 * @param date 默认是不指定的 直接获取系统日期
	 */
	private void synchroData(String date){
		if(StringUtils.isNullOrEmpty(date)){
			//如果为空 则 不同步 
			return ;
		}
		Thread thread = new Thread(new SynchroDataXMLDB());
		thread.start();
	}
	
}
