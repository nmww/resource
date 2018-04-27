package com.example.skin;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * 主界面
 * 
 * @date 2014-9-28
 * @author xiaohua Deng
 */
public class MainActivity extends BaseActivity implements OnClickListener {
	
	private long currentBackPressedTime = 0;
	private static final int BACK_PRESSED_INTERVAL = 2000;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn1:
			startActivity(new Intent(MainActivity.this,SkinActivity.class));			
			break;
		case R.id.btn2:
			startActivity(new Intent(MainActivity.this,Main2Activity.class));			
			break;
		default:
			break;
		}
		
	} 
    
    @Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
			currentBackPressedTime = System.currentTimeMillis();
			Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
		} else {
			ExitApplication.getInstance().exit(this);
		}
	}
    
}
