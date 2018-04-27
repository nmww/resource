package com.yongke.albchtaobaotest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.alibaba.sdk.android.callback.CallbackContext;
import com.taobao.munion.base.Log;

public class AbstractActivity extends Activity {
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Log.i("TAG", "resultCode---1");
			// 返回成功，淘宝将返回正确的信息，信息保存在intent中的result项中.
			Toast.makeText(this, data.getStringExtra("result"),
					Toast.LENGTH_SHORT).show();
		} else if (resultCode == RESULT_CANCELED) {
			Log.i("TAG", "resultCode---2");
			// 用户主动取消操作.
		} else if (resultCode == -2) {
			// error,淘宝将返回错误码，同样解析intent中的result项，形式如下�?
			Log.i("TAG", "resultCode---3");
			Toast.makeText(this, data.getStringExtra("result"),
					Toast.LENGTH_SHORT).show();
		}
		Log.i("TAG", "resultCode=================");
		Log.i("TAG", "result=" + data.getStringExtra("result"));
		CallbackContext.onActivityResult(requestCode, resultCode, data);
	}
}
