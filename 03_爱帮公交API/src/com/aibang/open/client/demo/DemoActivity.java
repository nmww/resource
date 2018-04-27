package com.aibang.open.client.demo;

import org.apache.http.HttpHost;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.aibang.open.client.AibangApi;
import com.aibang.open.client.exception.AibangIOException;
import com.aibang.open.client.exception.AibangServerException;

public class DemoActivity extends Activity implements OnClickListener {

	private AibangApi mAibang;
	private AibangAsyncTask mAsyncTask;
	// 这里请使用您申请的API KEY
	private static final String API_KEY = "f41c8afccc586de03a99c86097e98ccb";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mAibang = new AibangApi(API_KEY);
		findViewById(R.id.search).setOnClickListener(this);
		findViewById(R.id.biz).setOnClickListener(this);
		findViewById(R.id.bus).setOnClickListener(this);
		findViewById(R.id.post_comment).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (mAsyncTask != null) {
			mAsyncTask.cancel(true);
		}
		switch (v.getId()) {
		case R.id.search:
			mAsyncTask = new AibangAsyncTask("search");
			break;
		case R.id.biz:
			mAsyncTask = new AibangAsyncTask("biz");
			break;
		case R.id.bus:
			mAsyncTask = new AibangAsyncTask("bus");
			break;
		case R.id.post_comment:
			mAsyncTask = new AibangAsyncTask("post_comment");
			break;
		}
		if (mAsyncTask != null) {
			mAsyncTask.execute();
		}
	}

	private class AibangAsyncTask extends AsyncTask<Void, Void, JSONObject> {
		public AibangAsyncTask(String action) {
			mAction = action;
		}

		protected void onPreExecute() {
			Toast.makeText(DemoActivity.this, "正在请求...", Toast.LENGTH_SHORT)
					.show();
		}

		protected void onPostExecute(JSONObject result) {
			if (result == null) {
				String err = "位置错误";
				if (mException != null) {
					if (mException instanceof AibangServerException) {
						AibangServerException e = (AibangServerException) mException;
						err = e.getStatusCode() + "\n" + e.getMessage() + "\n "
								+ e.getStackTrace();
					} else if (mException instanceof AibangIOException) {
						err = "网络错误\n" + mException.getStackTrace();
					} else {
						err = "" + mException.getStackTrace();
					}
				}
				TextView tv = (TextView) findViewById(R.id.result);
				tv.setText(err);
				Toast.makeText(DemoActivity.this, "出错了", Toast.LENGTH_SHORT)
						.show();
			} else {
				TextView tv = (TextView) findViewById(R.id.result);
				tv.setText(result.toString());
				System.out.println("------result----------------" + result);
			}
		}

		@Override
		protected JSONObject doInBackground(Void... params) {
			String result = null;
			mAibang.setProxy(getProxy());
			try {
				if ("search".equals(mAction)) {
					result = mAibang.search("北京", "五道口", null, null, null,
							null, null, null, null, null);
				} else if ("biz".equals(mAction)) {
					result = mAibang.biz("829423550-695247739");
				} else if ("bus".equals(mAction)) {
					result = mAibang.bus("北京", "五道口", "中关村", null, null, null,
							null, null, null, null);
				} else if ("post_comment".equals(mAction)) {
					result = mAibang.postComment("username",
							"829423550-695247739", 5, null, "哇，好白痴的程序呀～～～");
				}
			} catch (Exception e) {
				mException = e;
			}
			try {
				return new JSONObject(result);
			} catch (Exception e) {
				mException = e;
			}
			return null;
		}

		private String mAction;
		private Exception mException;
	}

	private HttpHost getProxy() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		HttpHost none_host = null;
		if (cm == null) {
			return none_host;
		}

		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return none_host;
		}

		if (ni.getType() == ConnectivityManager.TYPE_WIFI) {
			return null;
		} else if (ni.getType() == ConnectivityManager.TYPE_MOBILE) {
			String extra = ni.getExtraInfo();
			if (TextUtils.isEmpty(extra)) {
				return none_host;
			}

			extra = extra.toLowerCase();
			if (extra.contains("cmnet") || extra.contains("ctnet")
					|| extra.contains("uninet") || extra.contains("3gnet")) {
				return none_host;
			} else if (extra.contains("cmwap") || extra.contains("uniwap")
					|| extra.contains("3gwap")) {
				return new HttpHost("10.0.0.172");
			} else if (extra.contains("ctwap")) {
				return new HttpHost("10.0.0.200");
			}
		}
		return none_host;
	}

}
