package com.example.d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * @author pengyiming
 * @date 2013-9-30
 */
public class Gallery3DActivity extends Activity {
	/* 数据段begin */
	private final String TAG = "Gallery3DActivity";
	private Context mContext;

	// 图片缩放倍率（相对屏幕尺寸的缩小倍率）
	public static final int SCALE_FACTOR = 5;

	// 图片间距（控制各图片之间的距离）
	private final int GALLERY_SPACING = -10;

	// 控件
	private GalleryFlow mGalleryFlow;

	/* 数据段end */

	/* 函数段begin */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();

		setContentView(R.layout.activity_gallery3_d);
		initGallery();
	}

	private void initGallery() {
		// 图片ID
		int[] images = { R.drawable.p1, R.drawable.p2, R.drawable.p3,
				R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7,
				R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11,
				R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15 };

		Img3DAdapter adapter = new Img3DAdapter(mContext, images);
		// 计算图片的宽高
		int[] dimension = BitmapScaleDownUtil
				.getScreenDimension(getWindowManager().getDefaultDisplay());
		int imageWidth = dimension[0] / SCALE_FACTOR;
		int imageHeight = dimension[1] / SCALE_FACTOR;
		// 初始化图片
		adapter.createImages(imageWidth, imageHeight);

		// 设置Adapter，显示位置位于控件中间，这样使得左右均可"无限"滑动
		mGalleryFlow = (GalleryFlow) findViewById(R.id.gallery_flow);
		mGalleryFlow.setSpacing(GALLERY_SPACING);
		mGalleryFlow.setAdapter(adapter);
		mGalleryFlow.setSelection(Integer.MAX_VALUE / 2);
	}
	/* 函数段end */
}
