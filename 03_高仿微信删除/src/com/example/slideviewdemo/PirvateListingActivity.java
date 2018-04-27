package com.example.slideviewdemo;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class PirvateListingActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private static final String TAG = "MainActivity";
	private ListViewCompat mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privatelisting);
		initView();
	}

	private void initView() {
		mListView = (ListViewCompat) findViewById(R.id.list);
		PrivateListingAdapter mAdapter = new PrivateListingAdapter(this);
		ArrayList<MessageBean> mMessageList = new ArrayList<MessageBean>();
		for (int i = 0; i < 20; i++) {
			MessageBean item = new MessageBean();
			if (i % 3 == 0) {
				item.iconRes = R.drawable.default_qq_avatar;
				item.title = "腾讯新闻";
				item.msg = "青岛爆炸满月：大量鱼虾死亡";
				item.time = "晚上18:18";
			} else {
				item.iconRes = R.drawable.wechat_icon;
				item.title = "微信团队";
				item.msg = "欢迎你使用微信";
				item.time = "12月18日";
			}
			mMessageList.add(item);
		}
		mAdapter.setmMessageItems(mMessageList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.e(TAG, "onItemClick position=" + position);
	}

	@Override
	public void onClick(View v) {

	}
}
