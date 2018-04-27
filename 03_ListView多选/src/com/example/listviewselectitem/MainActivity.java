package com.example.listviewselectitem;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.adapter.MyListViewAdapter;
import com.example.adapter.MyListViewAdapter.ViewHolder;
import com.example.info.Food;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	private ListView listView;
	private Button ok;
	private ArrayList<Food> foods = new ArrayList<Food>();
	private MyListViewAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// ��ʼ���ؼ�

		initData();// ��ʼ����������

		adapter = new MyListViewAdapter(foods, getApplicationContext());
		listView.setAdapter(adapter);
	}

	/**
	 * ��ʼ���ؼ�
	 * */
	public void initView() {
		listView = (ListView) findViewById(R.id.drink_list);// listview�б�ؼ�
		ok = (Button) findViewById(R.id.order_btn);// ȷ����ť

		ok.setOnClickListener(this);
		listView.setOnItemClickListener(this);
	}

	/**
	 * ��ʼ����������
	 * */
	public void initData() {
		
		
		
		
		Class cls = R.drawable.class;
		try {
			foods.add(new Food(cls.getDeclaredField("d1").getInt(null), "⨺���֭",
					"10"));
			foods.add(new Food(cls.getDeclaredField("d2").getInt(null), "��֭",
					"12"));
			foods.add(new Food(cls.getDeclaredField("d3").getInt(null), "ơ��",
					"15"));
			foods.add(new Food(cls.getDeclaredField("d4").getInt(null), "����֭",
					"10"));
			foods.add(new Food(cls.getDeclaredField("d5").getInt(null), "�����̲�",
					"8"));
			foods.add(new Food(cls.getDeclaredField("d6").getInt(null), "����֭",
					"10"));
			foods.add(new Food(cls.getDeclaredField("d7").getInt(null), "���ʱ���",
					"12"));
			foods.add(new Food(cls.getDeclaredField("d8").getInt(null), "Ҭ��֭",
					"10"));
			foods.add(new Food(cls.getDeclaredField("d9").getInt(null), "�����̲�",
					"9"));
			foods.add(new Food(cls.getDeclaredField("d10").getInt(null), "ʯ��֭",
					"10"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ť�ĵ���¼�����
	 * */
	@Override
	public void onClick(View v) {
		int mID = v.getId();
		switch (mID) {
		case R.id.order_btn:
			myPrice();// �����ܼ۲����
			break;
		}
	}

	/**
	 * �����ܼ۸�ķ���
	 * */
	public void myPrice() {
		HashMap<Integer, Boolean> map = MyListViewAdapter.getIsSelected();
		String str = "";
		int money = 0;
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i)) {
				str += (i + " ");
				money += Integer.parseInt(foods.get(i).food_price);
			}
		}
		MyListViewAdapter.getIsSelected().get("");
		Toast.makeText(getApplicationContext(),
				"��ѡ����" + str + "��,�ܼ�ǮΪ:" + money, Toast.LENGTH_SHORT).show();
	}

	/**
	 * listview��item��ѡ��ķ���
	 * */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ȡ��ViewHolder����������ʡȥ��ͨ������findViewByIdȥʵ����������Ҫ��cbʵ���Ĳ���
		ViewHolder holder = (ViewHolder) view.getTag();
		// �ı�CheckBox��״̬
		holder.cb.toggle();
		// ��CheckBox��ѡ��״����¼����
		MyListViewAdapter.getIsSelected().put(position, holder.cb.isChecked());

	}
}
