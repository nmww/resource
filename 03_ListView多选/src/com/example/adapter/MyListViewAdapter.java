package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.info.Food;
import com.example.listviewselectitem.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {
	// ������ݵ�list
	private ArrayList<Food> foodlist;
	// ��������CheckBox��ѡ��״��
	private static HashMap<Integer, Boolean> isSelected;
	// ������
	private Context context;
	// �������벼��
	private LayoutInflater inflater = null;

	// ������
	public MyListViewAdapter(ArrayList<Food> list, Context context) {
		this.context = context;
		this.foodlist = list;
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		// ��ʼ������
		initDate();
	}

	// ��ʼ��isSelected������
	private void initDate() {
		for (int i = 0; i < foodlist.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		return foodlist.size();
	}

	@Override
	public Object getItem(int position) {
		return foodlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// ���ViewHolder����
			holder = new ViewHolder();
			// ���벼�ֲ���ֵ��convertview
			convertView = inflater.inflate(R.layout.item, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.food_imager);
			holder.txt1 = (TextView) convertView.findViewById(R.id.food_name);
			holder.txt2 = (TextView) convertView.findViewById(R.id.price);
			holder.cb = (CheckBox) convertView.findViewById(R.id.check_box);
			// Ϊview���ñ�ǩ
			convertView.setTag(holder);
		} else {
			// ȡ��holder
			holder = (ViewHolder) convertView.getTag();
		}
		// ��ȡ����
		Food food = foodlist.get(position);

		// ��������䵽��ǰconvertView�Ķ�Ӧ�ؼ���

		holder.imageView.setImageResource(food.food_img);
		holder.txt1.setText(food.food_name);
		holder.txt2.setText(food.food_price);
		// ����list��TextView����ʾ
		// ����isSelected������checkbox��ѡ��״��
		holder.cb.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		MyListViewAdapter.isSelected = isSelected;
	}

	public static class ViewHolder {
		public TextView txt1;
		public TextView txt2;
		public ImageView imageView;
		public CheckBox cb;
	}
}