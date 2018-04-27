package com.mrwu.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.mrwu.demo.DemoAdapter.ViewHolder;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	/**
	 * 返回按钮
	 */
	private ViewGroup btnCancle = null;

	/**
	 * 确定按钮
	 */
	private ViewGroup btnAdd = null;

	/**
	 * 选择所有
	 */
	private Button btnSelectAll = null;

	/**
	 * 清除所有
	 */
	private Button btnDelete = null;

	/**
	 * ListView列表
	 */
	private ListView lvListView = null;

	/**
	 * 适配对象
	 */
	private DemoAdapter adpAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化视图
		initView();

		// 初始化控件
		initData();

	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		btnCancle = (ViewGroup) findViewById(R.id.btnCancle);
		btnCancle.setOnClickListener(this);

		btnAdd = (ViewGroup) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);

		btnSelectAll = (Button) findViewById(R.id.btnSelectAll);
		btnSelectAll.setOnClickListener(this);

		lvListView = (ListView) findViewById(R.id.lvListView);
		lvListView.setOnItemClickListener(this);

	}

	/**
	 * 初始化视图
	 */
	private void initData() {

		// 模拟假数据
		List<DemoBean> demoDatas = new ArrayList<DemoBean>();

		demoDatas.add(new DemoBean("张三", false));
		demoDatas.add(new DemoBean("李四", false));
		demoDatas.add(new DemoBean("王五", false));
		demoDatas.add(new DemoBean("赵六1", false));
		demoDatas.add(new DemoBean("赵六2", false));
		demoDatas.add(new DemoBean("赵六3", false));
		// demoDatas.add(new DemoBean("赵六4", false));
		// demoDatas.add(new DemoBean("赵六5", false));
		// demoDatas.add(new DemoBean("赵6六", false));
		// demoDatas.add(new DemoBean("赵六7", false));
		// demoDatas.add(new DemoBean("赵六33", false));
		// demoDatas.add(new DemoBean("赵六22", false));
		// demoDatas.add(new DemoBean("赵六11", false));
		// demoDatas.add(new DemoBean("赵22六", false));
		// demoDatas.add(new DemoBean("赵34六", false));

		adpAdapter = new DemoAdapter(this, demoDatas);

		lvListView.setAdapter(adpAdapter);

	}

	/**
	 * 按钮点击事件
	 */
	@Override
	public void onClick(View v) {

		/*
		 * 当点击返回的时候
		 */
		if (v == btnCancle) {
			finish();

		}

		/*
		 * 当点击增加的时候
		 */
		if (v == btnAdd) {

			adpAdapter.add(new DemoBean("赵六", true));

			adpAdapter.notifyDataSetChanged();
		}

		/*
		 * 当点击删除的时候
		 */
		if (v == btnDelete) {
			adpAdapter.removeChecked();
			adpAdapter.notifyDataSetChanged();

		}

		/*
		 * 当点击全选的时候
		 */
		if (v == btnSelectAll) {

			if (btnSelectAll.getText().toString().trim().equals("全选")) {

				// 所有item全部选中
				adpAdapter.setAllCheck(true);

				adpAdapter.notifyDataSetChanged();

				btnSelectAll.setText("全不选");
			} else {

				// 所有项目全部不选中
				adpAdapter.setAllCheck(false);

				adpAdapter.notifyDataSetChanged();

				btnSelectAll.setText("全选");
			}

		}
	}

	/**
	 * 当ListView 子项点击的时候
	 */
	@Override
	public void onItemClick(AdapterView<?> listView, View itemLayout,
			int position, long id) {

		if (itemLayout.getTag() instanceof ViewHolder) {

			ViewHolder holder = (ViewHolder) itemLayout.getTag();

			// 会自动出发CheckBox的checked事件
			holder.cbCheck.toggle();

		}

	}
}
