package com.beicai.juhedatedemo.activity;

import java.util.List;

public class KuaiDi {

	/**
	 * 邮件公司名字  
	 * 缩写
	 * 邮件号
	 */
	String company;
	String com;
	String no;
	
    List<KuaiDiList> list;
    public KuaiDi(){};
	public KuaiDi(String company, String com, String no,
			List<KuaiDiList> list) {
		super();
		this.company = company;
		this.com = com;
		this.no = no;
		this.list = list;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	public List<KuaiDiList> getList() {
		return list;
	}
	public void setList(List<KuaiDiList> list) {
		this.list = list;
	}

	
    
}
