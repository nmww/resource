package com.peipei.sharesdk;

import java.util.HashMap;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * @author：Peipei.zhao
 * @date：2014-12-30
 * @describe：MOB的SecurityCodeSDK短信验证码和ShareSDK在线分享。
 */
@SuppressLint("SdCardPath")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/** SecurityCodeSDK短信验证码 */
	public void messageActivity(View v) {
		SMSSDK.initSDK(this, "4fc138aaddd7", "67cc824beaa143b194d00ae7f3c1b948");
		// 打开注册页面
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
					@SuppressWarnings("unchecked")
					HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
					String country = (String) phoneMap.get("country");
					String phone = (String) phoneMap.get("phone");
					// 提交用户信息
					registerUser(country, phone);
				}
			}
		});
		registerPage.show(this);
	}

	/** 用户的信息处理 */
	public void registerUser(String country, String phone) {
		Log.i("用户的信息", "country:" + country + "\n" + "phone:" + phone);
	}

	/** ShareSDK在线分享 */
	public void shareActivity(View v) {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭SSO授权
		oks.disableSSOWhenAuthorize();
		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.ic_launcher,
				getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
		// Url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");
		// 启动分享GUI
		oks.show(this);
	}
}
