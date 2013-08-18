package com.pc.pconsumption.vo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ConsumptionVO implements Serializable  {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -2502182191296545904L;
//	���� ���  Ψһ
	private long id; 
//	��ͷ�
	private float morningFee;
//	�вͷ�
	private float lunchFee;
//	��ͷ�
	private float supperFee;
//	����
	private float trafficFee;
//	�ճ�
	private float everydayFee;
//	С��
	private float snackFee;
//	�������(������ͬѧ�۲� ����  500���ϵĵ�������)
	private float specialFee;
//	����
	private float totalFee;
//	��¼ʱ�� Ψһ ͬһ��ļ�¼���ܳ��ֶ���
	private String  recordTime;
//	����
	private String remark;
//	��ע 
	private String note;
//	��ҳ��չ���ݴ��ֶη���
	private String groupByDate; //���ڸ�ʽ yyyy-MM
	
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
			fee=this.morningFee  +  //��ͷ�
			this.lunchFee    +  //�вͷ�
			this.supperFee   +  //��ͷ�
			this.trafficFee  +  //��ͨ��
			this.everydayFee +  //�ճ������
			this.snackFee    +  //С�Է� 
			this.specialFee;    //�������
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
