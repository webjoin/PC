package com.pc.pconsumption.vo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ConsumptionVO implements Serializable  {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -2502182191296545904L;
//	主键 编号  唯一
	private long id; 
//	早餐费
	private float morningFee;
//	中餐费
	private float lunchFee;
//	晚餐费
	private float supperFee;
//	车费
	private float trafficFee;
//	日常
	private float everydayFee;
//	小吃
	private float snackFee;
//	特殊费用(包括：同学聚餐 出游  500以上的单个费用)
	private float specialFee;
//	共计
	private float totalFee;
//	记录时间 唯一 同一天的记录不能出现多条
	private String  recordTime;
//	评语
	private String remark;
//	备注 
	private String note;
//	在页面展根据此字段分组
	private String groupByDate; //日期格式 yyyy-MM
	
	public ConsumptionVO() {}
	public ConsumptionVO(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getMorningFee() {
		return morningFee;
	}
	public void setMorningFee(float morningFee) {
		this.morningFee = morningFee;
	}
	public float getLunchFee() {
		return lunchFee;
	}
	public void setLunchFee(float lunchFee) {
		this.lunchFee = lunchFee;
	}
	public float getSupperFee() {
		return supperFee;
	}
	public void setSupperFee(float supperFee) {
		this.supperFee = supperFee;
	}
	public float getTrafficFee() {
		return trafficFee;
	}
	public void setTrafficFee(float trafficFee) {
		this.trafficFee = trafficFee;
	}
	public float getEverydayFee() {
		return everydayFee;
	}
	public void setEverydayFee(float everydayFee) {
		this.everydayFee = everydayFee;
	}
	public float getSnackFee() {
		return snackFee;
	}
	public void setSnackFee(float snackFee) {
		this.snackFee = snackFee;
	}
	public float getSpecialFee() {
		return specialFee;
	}
	public void setSpecialFee(float specialFee) {
		this.specialFee = specialFee;
	}
	public float getTotalFee() {
		if(this.totalFee == 0){
			float fee= 0;
			fee=this.morningFee  +  //早餐费
			this.lunchFee    +  //中餐费
			this.supperFee   +  //晚餐费
			this.trafficFee  +  //交通费
			this.everydayFee +  //日常生活费
			this.snackFee    +  //小吃费 
			this.specialFee;    //特殊费用
			return fee;
		}
		return this.totalFee;
	}
	public void setTotalFee(float totalFee) {
		this.totalFee = totalFee;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getGroupByDate() {
		
		return this.groupByDate;
	}
	public void setGroupByDate(String groupByDate) {
		this.groupByDate = groupByDate;
	}
	
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("everydayFee:"+this.everydayFee+",");
			sb.append("groupByDate:'"+this.groupByDate+"',");
			sb.append("id:'"+this.id+"',");
			sb.append("lunchFee:"+this.lunchFee+",");
			sb.append("morningFee:"+this.morningFee+",");
			sb.append("note:'"+this.note+"',");
			sb.append("remark:'"+this.remark+"',");
			sb.append("snackFee:"+this.snackFee+",");
			sb.append("specialFee:"+this.specialFee+",");
			sb.append("supperFee:"+this.supperFee+",");
			sb.append("totalFee:"+this.totalFee+",");
			sb.append("trafficFee:"+this.trafficFee);
			sb.append("}");
			return sb.toString();
		}
	
}
