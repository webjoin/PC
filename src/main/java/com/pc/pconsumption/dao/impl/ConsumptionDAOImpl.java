package com.pc.pconsumption.dao.impl;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;


import com.pc.pconsumption.dao.ConsumptionDAO;
import com.pc.pconsumption.framework.*;
import com.pc.pconsumption.po.*;
import com.pc.pconsumption.util.*;

public  class ConsumptionDAOImpl extends AbstractDAOUtil implements ConsumptionDAO  {
	private static transient Logger log = Logger.getLogger(ConsumptionDAOImpl.class.getName());
	public void save(ConsumptionPO po)  throws Exception{
		Connection conn = DBHelper.open();
		//不需要 自动提交 
		conn.setAutoCommit(false);   
		PreparedStatement st = null;
		String sql = SystemGlobal.getValue(ConfigKeys.JDBC_SAVE);
		if(!isNew()){ //Update
			sql = SystemGlobal.getValue(ConfigKeys.JDBC_UPDATE);
		}
		 try {
			 st = conn.prepareStatement(sql);
			 st.setFloat(1, po.getMorningFee());
			 st.setFloat(2, po.getLunchFee());
			 st.setFloat(3, po.getSupperFee());
			 st.setFloat(4, po.getTrafficFee());
			 // 日常生活费
			 st.setFloat(5, po.getEverydayFee());
			 st.setFloat(6, po.getSnackFee());
			 st.setFloat(7, po.getSpecialFee());
			 st.setFloat(8, po.getTotalFee());
			 st.setString(9, po.getRemark());
			 st.setString(10, po.getNote());
			 st.setString(11, po.getRecordTime());
			 st.setString(12, po.getGroupByDate());
			 if(!isNew()){ // Update 
				 st.setLong(13, po.getId());
			 }
			 st.executeUpdate();
			 conn.commit();
			 log.info("["+new Timestamp(System.currentTimeMillis())+"] "+ "执行sql="+sql+  po.getRecordTime()+"的记录保存成功 ！");
			 st.close();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			log.info("保存记录失败！");
			throw new Exception("保存记录失败！"+e.getMessage());
		}finally{
			DBHelper.close(conn, st, null);
		}
	}
	public void saveBatch(List<ConsumptionPO> pos) throws Exception{
		Connection conn = DBHelper.open();
		//不需要 自动提交 
		conn.setAutoCommit(false);   
		PreparedStatement st = null;
		String sql = SystemGlobal.getValue(ConfigKeys.JDBC_SAVE);
		if(!isNew()){ //Update
			sql = SystemGlobal.getValue(ConfigKeys.JDBC_UPDATE);
		}
		 try {
			 st = conn.prepareStatement(sql);
			 for (int i = 0; i < pos.size(); i++) {
				 ConsumptionPO po = pos.get(i);
				 st.setFloat(1, po.getMorningFee());
				 st.setFloat(2, po.getLunchFee());
				 st.setFloat(3, po.getSupperFee());
				 st.setFloat(4, po.getTrafficFee());
				 // 日常生活费
				 st.setFloat(5, po.getEverydayFee());
				 st.setFloat(6, po.getSnackFee());
				 st.setFloat(7, po.getSpecialFee());
				 st.setFloat(8, po.getTotalFee());
				 st.setString(9, po.getRemark());
				 st.setString(10, po.getNote());
				 st.setString(11, po.getRecordTime());
				 st.setString(12, po.getGroupByDate());
				 if(!isNew()){ // Update 
					 st.setLong(13, po.getId());
				 }
				 log.info("["+SystemUtils.getCurrentTimestamp()+"] "+"执行sql="+sql+"参数如下：\n"+po);
				 st.addBatch();
			}
//			 log.info("["+new Timestamp(System.currentTimeMillis())+"] "+  po.getRecordTime()+"的记录保存成功 ！");
			 st.executeBatch();
			 conn.commit();
			 log.info("["+SystemUtils.getCurrentTimestamp()+"] "+"执行sql="+sql+"成功！");
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			log.info("["+SystemUtils.getCurrentTimestamp()+"] "+"执行sql="+sql+"保存记录失败！");
			throw new Exception("保存记录失败！"+e.getMessage());
		}finally{
			DBHelper.close(conn, st, null);
		}
	}
	/** 更新数据
	 * @since
	 * {@value}
	 * @author Elijah
	 */
	public void update(ConsumptionPO po) throws Exception{
		setNew(false);
		try {
			save(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setNew(true);
	}
	/**
	 * @param pos
	 * @throws Exception
	 */
	public void updateBatch(List<ConsumptionPO> pos) throws Exception{
		setNew(false);
		try {
			this.saveBatch(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setNew(true);
	}
	/* (non-Javadoc)
	 * @see com.pc.pconsumption.dao.ConsumptionDAO#delete(com.pc.pconsumption.po.ConsumptionPO)
	 */
	public void delete(ConsumptionPO po)throws Exception {
		Connection conn = DBHelper.open();
		String sql = SystemGlobal.getValue(ConfigKeys.JDBC_DLETE);
		PreparedStatement ps = null; 
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setLong(1, po.getId());
			ps.executeUpdate();
			ps.close();
			conn.commit();
			conn.close();
			log.info("["+SystemUtils.getCurrentTimestamp()+"]" + po.getRecordTime()+"的记录删除成功！");
		} catch (SQLException e) {
			conn.rollback();
			log.info("["+SystemUtils.getCurrentTimestamp()+"]" + po.getRecordTime()+"的记录删除失败！");
			e.printStackTrace();
		}finally{
			DBHelper.close(conn, ps, null);
		}
	}
	/* (non-Javadoc)
	 * @see com.pc.pconsumption.dao.ConsumptionDAO#queryCons(java.lang.String)
	 */
	public List<ConsumptionPO> queryCons(String sql) throws Exception{
		ResultSet rs = null;
		Connection conn = DBHelper.open();
		PreparedStatement ps  = null;
		List<ConsumptionPO> ls= new ArrayList<ConsumptionPO>();
		try {
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			rs = ps.executeQuery();
			while(rs.next()){
				ConsumptionPO po = new ConsumptionPO();
				po.setId(rs.getLong(IConsumptionPOValue.ID));
				po.setMorningFee(rs.getFloat(IConsumptionPOValue.MORNINGFEE));
				po.setLunchFee(rs.getFloat(IConsumptionPOValue.LUNCHFEE));
				po.setSupperFee(rs.getFloat(IConsumptionPOValue.SUPPERFEE));
				po.setTrafficFee(rs.getFloat(IConsumptionPOValue.TRAFFICFEE));
				po.setEverydayFee(rs.getFloat(IConsumptionPOValue.EVERYDAYFEE));
				po.setSnackFee(rs.getFloat(IConsumptionPOValue.SNACKFEE));
				po.setSpecialFee(rs.getFloat(IConsumptionPOValue.SPECIALFEE));
				po.setTotalFee(rs.getFloat(IConsumptionPOValue.TOTALFEE));
				po.setRemark(rs.getString(IConsumptionPOValue.REMARK));
				po.setNote(rs.getString(IConsumptionPOValue.NOTE));
				po.setRecordTime(rs.getString(IConsumptionPOValue.RECORDTIME));
				po.setGroupByDate(rs.getString(IConsumptionPOValue.GROUPBYDATE));
				ls.add(po);
			}
			rs.close();
			conn.commit();
			conn.close();
			log.info("["+SystemUtils.getCurrentTimestamp()+"] "+"执行sql="+sql);
			return ls;
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		return null;
	}
	public List<ConsumptionPO> queryCons(String sql, int startNum, int endNum)throws Exception {
		return null;
	}
	

}
