package com.zihao.draglist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.zihao.draglist.adapter.DragListAdapter;
import com.zihao.draglist.list.DragListView;

/**
 * ����קListView����
 */
public class DragListActivity extends Activity {

	private ArrayList<String> dataList;// ����Դ
	private DragListAdapter mDragListAdapter = null;// ������
	private DragListView dragListView;// �Զ������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drag_list_main);
		initView();
	}

	/**
	 * ��ʼ����ͼ
	 */
	private void initView() {
		initData();
		dragListView = (DragListView) findViewById(R.id.other_drag_list);
		mDragListAdapter = new DragListAdapter(this, dataList);
		dragListView.setAdapter(mDragListAdapter);
	}

	/**
	 * ��ʼ������
	 */
	public void initData() {
		// ���ݽ��
		dataList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			dataList.add("����" + i + "Ŀ¼");
		}
	}

}
