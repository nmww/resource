package com.beicai.juhedatedemo.activity;

public class KuaiDiList {

  /**
   * ���ص�û��һ���ط�����Ϣ �ռ�ʱ��   �ռ�״̬   �ռ�����
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
