package com.example.skin;

import android.app.Activity;


public class BaseActivity extends Activity {
	
	private SkinSettingManager mSettingManager;
	
	@Override
	protected void onResume() {
		super.onResume();
		 ExitApplication.getInstance().addActivity(this);
	     Define.setTitle(this);       
		 mSettingManager=new SkinSettingManager(this);
		 mSettingManager.initSkins();
	}
}