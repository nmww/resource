package com.example.dimimagesdemo;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView img;
	Bitmap sentBitmap;
	private int image;// ͼƬ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image = R.drawable.logo_qq;
		img=(ImageView) this.findViewById(R.id.img);
		sentBitmap = BitmapFactory.decodeResource(getResources(), image);
		//����ͼƬ�������趨�黯�뾶
		img.setImageBitmap(BitmapUtil.createBlurBitmap(sentBitmap,  5));	
	}
}
