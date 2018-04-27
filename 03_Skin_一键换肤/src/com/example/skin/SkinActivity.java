package com.example.skin;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 皮肤设置界面
 * 
 * @date 2014-9-28
 * @author xiaohua Deng
 */
public class SkinActivity extends BaseActivity implements OnClickListener{
	
	private SkinSettingManager mSettingManager;
	private TextView title;
	
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.myskin);
        mSettingManager=new SkinSettingManager(this);
		mSettingManager.initSkins();
		
		findViewById(R.id.imageView1).setOnClickListener(this);
        findViewById(R.id.imageView2).setOnClickListener(this);
        findViewById(R.id.imageView3).setOnClickListener(this);
        findViewById(R.id.imageView4).setOnClickListener(this);
        findViewById(R.id.imageView5).setOnClickListener(this);
        findViewById(R.id.imageView6).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.backa).setOnClickListener(this);
        title = (TextView) findViewById(R.id.titleTv);
        title.setText("设置背景");
        
       }
    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView1:
			mSettingManager.toggleSkins(0);	
			title.setBackgroundResource(R.drawable.color7);
			Define.setValue(SkinActivity.this, R.style.Theme_color7, R.drawable.color7);
			Toast.makeText(this, "温馨小提示：自己也可以手动搭配标题栏哦。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageView2:
			mSettingManager.toggleSkins(1);	
			title.setBackgroundResource(R.drawable.color2);
			Define.setValue(SkinActivity.this, R.style.Theme_color2, R.drawable.color2);
			Toast.makeText(this, "温馨小提示：自己也可以手动搭配标题栏哦。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageView3:
			mSettingManager.toggleSkins(2);
			title.setBackgroundResource(R.drawable.color6);
			Define.setValue(SkinActivity.this, R.style.Theme_color6, R.drawable.color6);
			Toast.makeText(this, "温馨小提示：自己也可以手动搭配标题栏哦。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageView4:
			mSettingManager.toggleSkins(3);
			title.setBackgroundResource(R.drawable.color3);
			Define.setValue(SkinActivity.this, R.style.Theme_color3, R.drawable.color3);
			Toast.makeText(this, "温馨小提示：自己也可以手动搭配标题栏哦。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageView5:
			mSettingManager.toggleSkins(4);
			title.setBackgroundResource(R.drawable.color5);
			Define.setValue(SkinActivity.this, R.style.Theme_color5, R.drawable.color5);
			Toast.makeText(this, "温馨小提示：自己也可以手动搭配标题栏哦。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageView6:
			mSettingManager.toggleSkins(5);
			title.setBackgroundResource(R.drawable.color1);
			Define.setValue(SkinActivity.this, R.style.Theme_color1, R.drawable.color1);
			Toast.makeText(this, "温馨小提示：自己也可以手动搭配标题栏哦。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn1:
			title.setBackgroundResource(R.drawable.color1);
			Define.setValue(SkinActivity.this, R.style.Theme_color1, R.drawable.color1);
			break;
		case R.id.btn2:
			title.setBackgroundResource(R.drawable.color2);
			Define.setValue(SkinActivity.this, R.style.Theme_color2, R.drawable.color2);
			break;
		case R.id.btn3:
			title.setBackgroundResource(R.drawable.color3);
			Define.setValue(SkinActivity.this, R.style.Theme_color3, R.drawable.color3);
			break;
		case R.id.btn4:
			title.setBackgroundResource(R.drawable.color4);
			Define.setValue(SkinActivity.this, R.style.Theme_color4, R.drawable.color4);
			break;
		case R.id.btn5:
			title.setBackgroundResource(R.drawable.color5);
			Define.setValue(SkinActivity.this, R.style.Theme_color5, R.drawable.color5);
			break;
		case R.id.btn6:
			title.setBackgroundResource(R.drawable.color6);
			Define.setValue(SkinActivity.this, R.style.Theme_color6, R.drawable.color6);
			break;
		case R.id.btn7:
			title.setBackgroundResource(R.drawable.color7);
			Define.setValue(SkinActivity.this, R.style.Theme_color7, R.drawable.color7);
			break;
		case R.id.btn8:
			title.setBackgroundResource(R.drawable.color8);
			Define.setValue(SkinActivity.this, R.style.Theme_color8, R.drawable.color8);
			break;
		case R.id.backa:
			finish();	
			break;
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {         	
            finish();	
            return false; 
        } 
        return false; 
    }
		    
}
