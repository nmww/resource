package com.taobao.tae.sdk.demo;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.Environment;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.login.LoginService;
import com.alibaba.sdk.android.session.SessionListener;
import com.alibaba.sdk.android.session.model.Session;
import com.ut.mini.UTAnalytics;

public class DemoApplication extends Application {

	private static final String KEY_ENV_INDEX = "envIndex";

	private static final String SHARED_PRE = "_tae_sdk_demo";

	@Override
	public void onCreate() {
		super.onCreate();

		UTAnalytics.getInstance().turnOnDebug();
		AlibabaSDK.turnOnDebug();

		int envIndex = 0;
		// AlibabaSDK.setEnvironment(Environment.values()[envIndex]);
		AlibabaSDK.asyncInit(this, new InitResultCallback() {

			@Override
			public void onSuccess() {
				Toast.makeText(DemoApplication.this, "初始化成功 ",
						Toast.LENGTH_SHORT).show();
				LoginService loginService = AlibabaSDK
						.getService(LoginService.class);
				loginService.setSessionListener(new SessionListener() {

					@Override
					public void onStateChanged(Session session) {
						if (session != null) {
							Toast.makeText(
									DemoApplication.this,
									"session状态改变" + session.getUserId()
											+ session.getUser()
											+ session.isLogin(),
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(DemoApplication.this,
									"session is null", Toast.LENGTH_SHORT)
									.show();
						}
					}
				});
				// monkeytest autologin
				// asyncAutoLogin("hongbing_001", "taobao1234", new
				// DefaultCallback<Boolean>(handler) {
				// @Override
				// public void onResult(Boolean result) {
				// if(result) {
				// Toast.makeText(MainActivity.this, "login success.",
				// Toast.LENGTH_LONG).show();
				// } else {
				// Toast.makeText(MainActivity.this, "login failure.",
				// Toast.LENGTH_LONG).show();
				// }
				// }
				// });
			}

			@Override
			public void onFailure(int code, String message) {
				Toast.makeText(DemoApplication.this, "初始化异常" + message,
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private int getEnvIndex() {
		SharedPreferences sharedata = getSharedPreferences(SHARED_PRE, 0);
		int envIndex = sharedata.getInt(KEY_ENV_INDEX, 0);
		return envIndex;
	}
}
