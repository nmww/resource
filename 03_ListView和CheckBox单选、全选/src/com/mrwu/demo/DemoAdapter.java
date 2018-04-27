package com.mrwu.demo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class DemoAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context context = null;

	/**
	 * 数据集合
	 */
	private List<DemoBean> datas = null;

	public DemoAdapter(Context context, List<DemoBean> datas) {
		this.datas = datas;
		this.context = context;
	}

	/**
	 * 首先,默认情况下,所有项目都是没有选中的.这里进行初始化
	 */
	public void setAllCheck(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setChecked(bool);
		}

	}

	@Override
	public int getCount() {
		return null == datas ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder mViewHolder = null;
		/**
		 * 进行ListView 的优化
		 */
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_item_layout, parent, false);
			mViewHolder = new ViewHolder();
			mViewHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.tvTitle);
			mViewHolder.cbCheck = (CheckBox) convertView
					.findViewById(R.id.cbCheckBox);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		mViewHolder.tvTitle.setText(datas.get(position).getTitle());

		mViewHolder.cbCheck.setChecked(datas.get(position).isChecked());

		/*
		 * 设置单选按钮的选中
		 */
		mViewHolder.cbCheck
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						/*
						 * 将选择项加载到map里面寄存
						 */
						// isCheckMap.put(position, isChecked);
						datas.get(position).setChecked(isChecked);
					}
				});

		return convertView;
	}

	/**
	 * 增加一项的时候
	 */
	public void add(DemoBean bean) {
		this.datas.add(0, bean);
	}

	// 移除一个所选的item
	public void removeChecked() {
		// 进行遍历
		for (int i = 0; i < datas.size(); i++) {
			if (datas.get(i).isChecked()) {
				 datas.remove(i);
				//因为数组删除某一个位置上的元素之后，后面的值会往前移动
			    //所以应该从当前的遍历的位置在遍历一次(i--1)。同时数组大小值size-1；
				 i--; 
			} 
		}
	}

	public static class ViewHolder {
		public TextView tvTitle = null;
		public CheckBox cbCheck = null;
		public Object data = null;

	}

	public List<DemoBean> getDatas() {
		return datas;
	}

}
