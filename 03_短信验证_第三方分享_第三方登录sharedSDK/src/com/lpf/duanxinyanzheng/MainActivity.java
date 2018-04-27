package com.lpf.duanxinyanzheng;

import java.util.HashMap;

import com.beicai.lingli.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SMSSDK.initSDK(this, "4f59ff9b52a8", "338cb1a38ef583b1bb79c23ce1b59c86");
	}
	public void test(View v){
		System.out.println("------test-----------");
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
		public void afterEvent(int event, int result, Object data) {
		// 解析注册结果
			System.out.println("------result-----------"+result);
		if (result == SMSSDK.RESULT_COMPLETE) {
		@SuppressWarnings("unchecked")
		HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
		String country = (String) phoneMap.get("country");
		String phone = (String) phoneMap.get("phone"); 
		 
		// 提交用户信息
		registerUser(country, phone);
		}
		}
		});
		registerPage.show(this);
	}
	public void registerUser(String country,String phone){
		System.out.println("-------registerUser----------");
		System.out.println(country+"-------registerUser----------"+phone);
	}
	public void showTongxun(View v){
		ContactsPage contactsPage = new ContactsPage();
		contactsPage.show(this);
	}
	
}
