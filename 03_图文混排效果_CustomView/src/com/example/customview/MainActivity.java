package com.example.customview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 FloatImageText view = new FloatImageText(this);  
		  
	        view.setText("电视里发生了房间里是积分拉萨积分拉萨积分拉萨减肥啦空间  撒旦法发大水发撒旦法看完了鸡肉味容积率为热键礼物i经二路文件容量为积分拉萨解放路口上飞机撒离开房间爱水立方法拉圣诞节福禄寿");  
	        Bitmap bm = BitmapFactory.decodeResource(getResources(),  
	                R.drawable.ic_launcher); 
	        //显示的图片bm,图片在界面中的位置，图片距离顶部的高度
	        view.setImageBitmap(bm, 380 - bm.getWidth(),50);  
	  
	        setContentView(view);  
	    }  
	  
	   /* @Override  
	    public boolean onCreateOptionsMenu(Menu menu) {  
	        // Inflate the menu; this adds items to the action bar if it is present.  
	        getMenuInflater().inflate(R.menu.activity_main, menu);  
	  
	        return true;  
	    } */ 
}
