package com.pc.pconsumption.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.pc.pconsumption.dao.ConsumptionDAO;
import com.pc.pconsumption.dao.impl.ConsumptionDAOImpl;
import com.pc.pconsumption.po.ConsumptionPO;
import com.pc.pconsumption.service.ConsumptionService;
import com.pc.pconsumption.vo.ConsumptionVO;

public class ConsumptionServiceImpl implements ConsumptionService{

	private ConsumptionDAO dao = new ConsumptionDAOImpl();
	
	public void save(ConsumptionPO po) throws Exception {
		dao.save(po);
	}
	
	public void save(ConsumptionVO vo) throws Exception {
		ConsumptionPO po = new ConsumptionPO();
		BeanUtils.copyProperties(po, vo);
		dao.save(po);
	}

	
	public void saveBatch(List<ConsumptionVO> vos) throws Exception {
		List<ConsumptionPO> pos = new ArrayList<ConsumptionPO>();
		if(vos != null && vos.size()>0){
			for (int i = 0; i < vos.size(); i++) {
				ConsumptionPO po = new ConsumptionPO();
				BeanUtils.copyProperties(po, vos.get(i));
				pos.add(po);
			}
			try {
				dao.saveBatch(pos);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	
	public void update(ConsumptionPO po) throws Exception {
		dao.update(po);
	}
	public void updateBatch(List<ConsumptionVO> vos)throws Exception{
		List<ConsumptionPO> pos = new ArrayList<ConsumptionPO>();
		BeanUtils.copyProperties(pos, vos);
		dao.updateBatch(pos);
	}

	
	public void delete(ConsumptionPO po) throws Exception {
		dao.delete(po);
	}

	
	public List<ConsumptionPO> queryCons(String sql) throws Exception {
		List<ConsumptionPO> ls = dao.queryCons(sql);
		return ls;
	}

	
	public List<ConsumptionPO> queryCons(String sql, int startNum, int endNum)
			throws Exception {
		return null;
	}
	
	

}
