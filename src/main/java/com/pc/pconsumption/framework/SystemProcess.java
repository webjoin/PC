package com.pc.pconsumption.framework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.pc.pconsumption.po.ConsumptionPO;

public class SystemProcess<T> {

	public Object getObjecFromObj(T obj , Class<T> clazz){
		try {
//			Method[] methods = clazz.getDeclaredMethods();
//			for (Method method : methods) {
//				String methodName= method.getName();
//				if(methodName.startsWith("get")){
//					continue;
//				}
//				String name = methodName.substring(3);
//				name = name.substring(0, 1).toLowerCase()+name.substring(1);
//				System.out.println(name);
//			}
			Field[] fields = clazz.getDeclaredFields();
			StringBuilder sb =new StringBuilder("<"+clazz.getSimpleName()+">\n");
			for (Field field : fields) {
				String fieldName = field.getName();
				if(!"serialVersionUID".equals(fieldName)){
					sb.append("\t<"+fieldName+">");
//					sb.append(obj.)
					sb.append("</"+fieldName+">\n");
				}
			}
			sb.append("</"+clazz.getSimpleName()+">");
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static void main(String[] args) {
		SystemProcess<ConsumptionPO>  sp = new SystemProcess<ConsumptionPO>();
		ConsumptionPO po = new ConsumptionPO();
		sp.getObjecFromObj(po, ConsumptionPO.class);
		
	}
}
