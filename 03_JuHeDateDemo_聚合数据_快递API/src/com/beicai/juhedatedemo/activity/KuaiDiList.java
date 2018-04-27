package com.beicai.juhedatedemo.activity;

public class KuaiDiList {

  /**
   * 返回的没到一个地方的信息 收件时间   收件状态   收件区域
   */
	String datetime;
	String remark;
	String zone;
	public KuaiDiList(){};
	public KuaiDiList(String datetime, String remark, String zone) {
		super();
		this.datetime = datetime;
		this.remark = remark;
		this.zone = zone;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
}
