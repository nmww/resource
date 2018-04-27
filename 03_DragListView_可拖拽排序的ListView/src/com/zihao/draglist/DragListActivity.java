package com.zihao.draglist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.zihao.draglist.adapter.DragListAdapter;
import com.zihao.draglist.list.DragListView;

/**
 * 可拖拽ListView界面
 */
public class DragListActivity extends Activity {

	private ArrayList<String> dataList;// 数据源
	private DragListAdapter mDragListAdapter = null;// 适配器
	private DragListView dragListView;// 自定义组件

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drag_list_main);
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		initData();
		dragListView = (DragListView) findViewById(R.id.other_drag_list);
		mDragListAdapter = new DragListAdapter(this, dataList);
		dragListView.setAdapter(mDragListAdapter);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		// 数据结果
		dataList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			dataList.add("测试" + i + "目录");
		}
	}

}
