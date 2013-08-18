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
			log.info("������ʼ��ʧ�ܣ�");
			e.printStackTrace();
			throw new ServletException("������ʼ��ʧ�ܣ�");
		}
	}
	
	/**
	 * ��Ҫ��ͬ����������
	 * ͬ��������ָ�Ľ� ���ݿ��е����� �� ���ص�xml �ļ��е����� ����һ��
	 * @param date Ĭ���ǲ�ָ���� ֱ�ӻ�ȡϵͳ����
	 */
	private void synchroData(String date){
		if(StringUtils.isNullOrEmpty(date)){
			//���Ϊ�� �� ��ͬ�� 
			return ;
		}
		Thread thread = new Thread(new SynchroDataXMLDB());
		thread.start();
	}
	
}
