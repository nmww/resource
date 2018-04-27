package com.example.bitmaptest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @author：Peipei.zhao
 * @date：2015-3-21
 * @describe：原生位图、压缩位图、背景图片对比展示
 */
public class MainActivity extends Activity {

	private ImageView imageView;
	private Bitmap bitmap;// 位图
	private int image;// 图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image = R.drawable.photo4;
		imageView = (ImageView) this.findViewById(R.id.iv_bitmap);
	}

	/** 原生位图 */
	public void bornBitmap(View v) {
		cleanImageView();
		bitmap = BitmapFactory.decodeResource(getResources(), image);
		imageView.setImageBitmap(bitmap);
	}

	/** 压缩位图 */
	public void scaleBitmap(View v) {
		cleanImageView();
		// 图片压缩比例由最后两个参数决定
		imageView.setImageBitmap(ScaleBitmapUtil
				.decodeSampledBitmapFromResource(getResources(), image, 200,
						100));
	}

	/** 背景图片 */
	public void backgroundImage(View v) {
		cleanImageView();
		imageView.setBackgroundResource(image);
	}

	/** ImageView清空 */
	private void cleanImageView() {
		imageView.setImageBitmap(null);
		imageView.setBackgroundResource(0);
	}

}