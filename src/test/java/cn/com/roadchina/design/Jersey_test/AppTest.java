package cn.com.roadchina.design.Jersey_test;


import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pc.pconsumption.framework.SystemGlobal;
import com.pc.pconsumption.po.ConsumptionPO;
import com.pc.pconsumption.resources.ConsumptionResources;


/**
 * Unit test for simple App.
 */
public class AppTest
{
	@Before
	public void init(){
		try {
			SystemGlobal.getSystemGlobalSql();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void queryAll(){
		ConsumptionResources re = new ConsumptionResources();
		try {
//			List<ConsumptionPO> obj = re.queryPOs();
			JSONObject obj =  re.queryPOs();
			System.out.println(obj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
