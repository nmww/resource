package com.mrwu.demo;

public class DemoBean {

	private String title;

	/**
	 * 标识是否可以删除
	 */
	private boolean canRemove = false;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isChecked() {
		return canRemove;
	}

	public void setChecked(boolean canRemove) {
		this.canRemove = canRemove;
	}

	public DemoBean(String title, boolean canRemove) {
		this.title = title;
		this.canRemove = canRemove;
	}

}
