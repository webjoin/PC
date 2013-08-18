package com.pc.pconsumption.dao;


import java.util.List;

import com.pc.pconsumption.po.ConsumptionPO;


public interface ConsumptionDAO {
	
	void save(ConsumptionPO po) throws Exception;
	
	void saveBatch(List<ConsumptionPO> pos)throws Exception;
	
	void update(ConsumptionPO po)throws Exception;
	void updateBatch(List<ConsumptionPO> pos) throws Exception;
	void delete(ConsumptionPO po)throws Exception;
	
	/**
	 * 查询消费记录
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	List<ConsumptionPO> queryCons(String sql)throws Exception;
	
	List<ConsumptionPO> queryCons(String sql,int startNum,int endNum)throws Exception;
	
	
//	public  BOCssCardPasswordBean[] getBeans(DataContainerInterface dc) ;
//	public  BOCssCardPasswordBean getBean(String _CardPassWord) ;
//	public   BOCssCardPasswordBean[] getBeans(Criteria sql) throws Exception;
//	public   BOCssCardPasswordBean[] getBeans(Criteria sql,int startNum,int endNum,boolean isShowFK);
//	public   BOCssCardPasswordBean[] getBeans(String condition,Map parameter) ;
//	public   BOCssCardPasswordBean[] getBeans(String[] cols,String condition,Map parameter,int startNum,int endNum,boolean isShowFK) ;
//	public   BOCssCardPasswordBean[] getBeans(String[] cols,String condition,Map parameter,int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception;
//	public  int getBeansCount(String condition,Map parameter) throws Exception;
//	public  int getBeansCount(String condition,Map parameter,String[] extendBOAttrs) ;
//	public  void save( BOCssCardPasswordBean aBean) throws Exception;
//	public   void save( BOCssCardPasswordBean[] aBeans) throws Exception;
//	public   void saveBatch( BOCssCardPasswordBean[] aBeans) throws Exception;
//	public   BOCssCardPasswordBean[] getBeansFromQueryBO(String soureBO,Map parameter);
//	public   BOCssCardPasswordBean[] getBeansFromSql(String sql,Map parameter);
//	public  java.math.BigDecimal getNewId() throws Exception;
//	public  java.sql.Timestamp getSysDate() throws Exception;
//	public  BOCssCardPasswordBean wrap(DataContainerInterface source,Map colMatch,boolean canModify);
//	public  BOCssCardPasswordBean copy(DataContainerInterface source,Map colMatch,boolean canModify);
//	public  BOCssCardPasswordBean transfer(IBOCssCardPasswordValue value) ;
//	public  BOCssCardPasswordBean[] transfer(IBOCssCardPasswordValue[] value);
//	public  void save(IBOCssCardPasswordValue aValue) throws Exception;
//	public   void save( IBOCssCardPasswordValue[] aValues) throws Exception;
//	public   void saveBatch( IBOCssCardPasswordValue[] aValues);

}
