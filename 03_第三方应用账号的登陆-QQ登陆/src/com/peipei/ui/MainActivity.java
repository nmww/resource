package com.peipei.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peipei.util.AppConstants;
import com.peipei.util.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * @author：Peipei.zhao
 * @date：2015-3-4
 * @describe：应用QQ账号登陆其他软件，即第三方登陆。
 */
@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = MainActivity.class.getName();
	private Button btn_login;// 登陆按钮
	public static Tencent mTencent;
	private TextView tv_userInfo;// 用户信息
	private ImageView iv_userLogo;// 用户头像
	private UserInfo mInfo;
	public static String mAppID;// APP的ID
	private EditText mEtAppid = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		if (TextUtils.isEmpty(mAppID)) {
			mAppID = "222222";
			mEtAppid = new EditText(this);
			mEtAppid.setText(mAppID);
			try {
				new AlertDialog.Builder(this).setTitle("请输入APP_ID")
						.setCancelable(false)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(mEtAppid)
						.setPositiveButton("Commit", mAppidCommitListener)
						.setNegativeButton("Use Default", mAppidCommitListener)
						.show();
			} catch (Exception e) {
			}
		} else {
			if (mTencent == null) {
				mTencent = Tencent.createInstance(mAppID, this);
			}
		}
	}

	/** 控件初始化 */
	private void initView() {
		btn_login = (Button) findViewById(R.id.new_login_btn);
		tv_userInfo = (TextView) findViewById(R.id.user_nickname);
		iv_userLogo = (ImageView) findViewById(R.id.user_logo);
		btn_login.setOnClickListener(this);
		updateLoginButton();
	}

	/** 登陆按钮UI更新 */
	private void updateLoginButton() {
		if (mTencent != null && mTencent.isSessionValid()) {
			btn_login.setTextColor(Color.RED);
			btn_login.setText("退出帐号");
		} else {
			btn_login.setTextColor(Color.BLUE);
			btn_login.setText("登陆");
		}
	}

	/** APP的ID提交监听 */
	private DialogInterface.OnClickListener mAppidCommitListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			mAppID = AppConstants.APP_ID;
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				// 获取输入的AppID
				String editTextContent = mEtAppid.getText().toString().trim();
				if (!TextUtils.isEmpty(editTextContent)) {
					mAppID = editTextContent;
				}
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				// 默认AppID
				break;
			}
			mTencent = Tencent.createInstance(mAppID, MainActivity.this);
			// 有奖分享处理
			// handlePrizeShare();
		}
	};

	/** 登陆验证 */
	private void onClickLogin() {
		if (!mTencent.isSessionValid()) {
			mTencent.login(this, "all", loginListener);
			Log.d("SDKQQAgentPref",
					"FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
		} else {
			mTencent.logout(this);
			updateUserInfo();
			updateLoginButton();
		}
	}

	/** 登陆监听 */
	IUiListener loginListener = new BaseUiListener() {
		@Override
		protected void doComplete(JSONObject values) {
			Log.d("SDKQQAgentPref",
					"AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
			initOpenidAndToken(values);
			updateUserInfo();
			updateLoginButton();
		}
	};

	public static void initOpenidAndToken(JSONObject jsonObject) {
		try {
			String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
			String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
			String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
			if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
					&& !TextUtils.isEmpty(openId)) {
				mTencent.setAccessToken(token, expires);
				mTencent.setOpenId(openId);
			}
		} catch (Exception e) {
		}
	}

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			if (null == response) {
				Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
				return;
			}
			Util.showResultDialog(MainActivity.this, response.toString(),
					"登录成功");
			// 有奖分享处理
			// handlePrizeShare();
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {
		}

		@Override
		public void onError(UiError e) {
			Util.toastMessage(MainActivity.this, "onError: " + e.errorDetail);
			Util.dismissDialog();
		}

		@Override
		public void onCancel() {
			Util.toastMessage(MainActivity.this, "onCancel: ");
			Util.dismissDialog();
		}
	}

	/** 用户信息获取更新 */
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				JSONObject response = (JSONObject) msg.obj;
				if (response.has("nickname")) {
					try {
						tv_userInfo.setVisibility(android.view.View.VISIBLE);// 设置文本可见
						tv_userInfo.setText(response.getString("nickname"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else if (msg.what == 1) {
				Bitmap bitmap = (Bitmap) msg.obj;
				iv_userLogo.setImageBitmap(bitmap);// 设置用户头像
				iv_userLogo.setVisibility(android.view.View.VISIBLE);
			}
		}
	};

	/** 用户信息获取发送 */
	private void updateUserInfo() {
		if (mTencent != null && mTencent.isSessionValid()) {
			IUiListener listener = new IUiListener() {

				@Override
				public void onError(UiError e) {

				}

				@Override
				public void onComplete(final Object response) {
					Message msg = new Message();
					msg.obj = response;
					msg.what = 0;
					mHandler.sendMessage(msg);
					new Thread() {

						@Override
						public void run() {
							JSONObject json = (JSONObject) response;
							if (json.has("figureurl")) {
								Bitmap bitmap = null;
								try {
									bitmap = Util.getbitmap(json
											.getString("figureurl_qq_2"));
								} catch (JSONException e) {
									e.printStackTrace();
								}
								Message msg = new Message();
								msg.obj = bitmap;
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						}
					}.start();
				}

				@Override
				public void onCancel() {
				}
			};
			mInfo = new UserInfo(this, mTencent.getQQToken());
			mInfo.getUserInfo(listener);
		} else {
			tv_userInfo.setText("");
			tv_userInfo.setVisibility(android.view.View.GONE);// 设置文本隐藏
			iv_userLogo.setVisibility(android.view.View.GONE);// 设置头像隐藏
		}
	}

	@Override
	public void onClick(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);// 震动动画效果
		switch (v.getId()) {
		case R.id.new_login_btn:// 登陆按钮
			onClickLogin();
			v.startAnimation(shake);
			return;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode="
				+ resultCode);
		if (requestCode == Constants.REQUEST_API) {
			if (resultCode == Constants.RESULT_LOGIN) {
				Tencent.handleResultData(data, loginListener);
				Log.d(TAG, "-->onActivityResult handle logindata");
			}
		} else if (requestCode == Constants.REQUEST_APPBAR) { // APP内应用吧登录
			if (resultCode == Constants.RESULT_LOGIN) {
				updateUserInfo();
				updateLoginButton();
				Util.showResultDialog(MainActivity.this,
						data.getStringExtra(Constants.LOGIN_INFO), "登录成功");
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
