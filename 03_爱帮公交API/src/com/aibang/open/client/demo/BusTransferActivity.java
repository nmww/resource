package com.aibang.open.client.demo;

import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aibang.open.client.AibangApi;
import com.aibang.open.client.exception.AibangIOException;
import com.aibang.open.client.exception.AibangServerException;

/**
 * @author：Peipei.zhao
 * @date：2015-3-22
 * @describe：公交换乘查询，输入始发站和终点站查询公交线路。
 */
public class BusTransferActivity extends Activity implements OnClickListener {

	private AibangApi mAibang;
	private AibangAsyncTask mAsyncTask;
	// 这里请使用您申请的API KEY
	private static final String API_KEY = "f41c8afccc586de03a99c86097e98ccb";
	private EditText et_start;
	private EditText et_end;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		mAibang = new AibangApi(API_KEY);
		findViewById(R.id.btn_query).setOnClickListener(this);
		et_start = (EditText) findViewById(R.id.et_start);
		et_end = (EditText) findViewById(R.id.et_end);
	}

	@Override
	public void onClick(View v) {
		if (mAsyncTask != null) {
			mAsyncTask.cancel(true);
		}
		switch (v.getId()) {
		case R.id.btn_query:
			mAsyncTask = new AibangAsyncTask();
			break;
		}
		if (mAsyncTask != null) {
			mAsyncTask.execute();
		}
	}

	/** 爱帮异步机制类 */
	private class AibangAsyncTask extends AsyncTask<Void, Void, JSONObject> {

		private Exception mException;

		@Override
		protected JSONObject doInBackground(Void... params) {
			String result = null;
			mAibang.setProxy(getProxy());
			try {
				result = mAibang.bus("北京", et_start.getText().toString(),
						et_end.getText().toString(), null, null, null, null,
						null, null, null);
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

		protected void onPreExecute() {
			Toast.makeText(BusTransferActivity.this, "正在请求...",
					Toast.LENGTH_SHORT).show();
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
				Toast.makeText(BusTransferActivity.this, "出错了",
						Toast.LENGTH_SHORT).show();
			} else {
				jsonParser(result.toString());
			}
		}
	}

	/** JSON字符串解析 */
	private void jsonParser(String result) {
		StringBuffer buffer = new StringBuffer();
		try {
			JSONObject json = new JSONObject(result);
			JSONObject buses = json.getJSONObject("buses");
			JSONArray bus = buses.getJSONArray("bus");
			for (int i = 0; i < bus.length(); i++) {
				int time = bus.getJSONObject(i).getInt("time");
				Log.i("time", "" + time);
				int dist = bus.getJSONObject(i).getInt("dist");
				Log.i("dist", "" + dist);
				JSONObject segment = bus.getJSONObject(i)
						.getJSONObject("segments").getJSONArray("segment")
						.getJSONObject(0);
				String line_name = segment.getString("line_name");
				Log.i("line_name", "" + line_name);
				String start_stat = segment.getString("start_stat");
				Log.i("start_stat", "" + start_stat);
				String end_stat = segment.getString("end_stat");
				Log.i("end_stat", "" + end_stat);
				buffer.append("\n\n估计耗费时间：" + time + "\n总距离：" + dist
						+ "\n线路名称：" + line_name + "\n起点站名：" + start_stat
						+ "\n终点站名：" + end_stat);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		TextView tv = (TextView) findViewById(R.id.result);
		tv.setText(buffer.toString());
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
