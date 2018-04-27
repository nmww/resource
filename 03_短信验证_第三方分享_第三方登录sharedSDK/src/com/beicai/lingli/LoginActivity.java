package com.beicai.lingli;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void test(View v){
		/*Platform wechat= ShareSDK.getPlatform(this, Wechat.NAME);
		wechat.setPlatformActionListener(paListener);
		wechat.authorize();*/
	}
 
	public void showTongxun(View v){
		showShare();
	}
	private void showShare() {
		 ShareSDK.initSDK(this);
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 
		 
		// 分享时Notification的图标和文字
		 oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文本");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 
		 oks.setImagePath(Environment.getExternalStorageDirectory()+"/test.jpg");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
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
	
	private void authorize(Platform plat) {/*
		 if (plat == null) {
		 popupOthers();
		 return;
		 }
		//判断指定平台是否已经完成授权
		if(plat.isValid()) {
		 String userId = plat.getDb().getUserId();
		 if (userId != null) {
		 UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
		 login(plat.getName(), userId, null);
		 return;
		 }
		 }
		 plat.setPlatformActionListener(this);
		 // true不使用SSO授权，false使用SSO授权
		plat.SSOSetting(true);
		 //获取用户资料
		 plat.showUser(null);
		 */}
}
