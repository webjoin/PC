package com.pc.pconsumption.service;

import java.util.List;

import com.pc.pconsumption.po.ConsumptionPO;
import com.pc.pconsumption.vo.ConsumptionVO;

/**
 * @author Elijah
 *
 */
public interface ConsumptionService {
	
	/**����һ�м�¼
	 * @author Elijah
	 * @param pos ���Ѷ���
	 * @throws Exception
	 */
	void save(ConsumptionPO po) throws Exception;
	
	void save(ConsumptionVO vo) throws Exception;
	
	/**��������һ����¼
	 * @param pos
	 * @throws Exception
	 */
	void saveBatch(List<ConsumptionVO> pos)throws Exception;
	
	void update(ConsumptionPO po)throws Exception;
	
	void updateBatch(List<ConsumptionVO> pos)throws Exception;
	
	void delete(ConsumptionPO po)throws Exception;
	
	List<ConsumptionPO> queryCons(String sql)throws Exception;
	
	List<ConsumptionPO> queryCons(String sql,int startNum,int endNum)throws Exception;

}
