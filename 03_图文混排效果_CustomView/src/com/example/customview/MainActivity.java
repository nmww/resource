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
		  
	        view.setText("�����﷢���˷������ǻ������������������������������ռ�  ����������ˮ�������������˼���ζ�ݻ���Ϊ�ȼ�����i����·�ļ�����Ϊ�����������·���Ϸɻ����뿪���䰮ˮ��������ʥ���ڸ�»��");  
	        Bitmap bm = BitmapFactory.decodeResource(getResources(),  
	                R.drawable.ic_launcher); 
	        //��ʾ��ͼƬbm,ͼƬ�ڽ����е�λ�ã�ͼƬ���붥���ĸ߶�
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
