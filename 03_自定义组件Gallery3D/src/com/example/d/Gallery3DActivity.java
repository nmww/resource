package com.example.d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * @author pengyiming
 * @date 2013-9-30
 */
public class Gallery3DActivity extends Activity {
	/* ���ݶ�begin */
	private final String TAG = "Gallery3DActivity";
	private Context mContext;

	// ͼƬ���ű��ʣ������Ļ�ߴ����С���ʣ�
	public static final int SCALE_FACTOR = 5;

	// ͼƬ��ࣨ���Ƹ�ͼƬ֮��ľ��룩
	private final int GALLERY_SPACING = -10;

	// �ؼ�
	private GalleryFlow mGalleryFlow;

	/* ���ݶ�end */

	/* ������begin */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();

		setContentView(R.layout.activity_gallery3_d);
		initGallery();
	}

	private void initGallery() {
		// ͼƬID
		int[] images = { R.drawable.p1, R.drawable.p2, R.drawable.p3,
				R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7,
				R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11,
				R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15 };

		Img3DAdapter adapter = new Img3DAdapter(mContext, images);
		// ����ͼƬ�Ŀ��
		int[] dimension = BitmapScaleDownUtil
				.getScreenDimension(getWindowManager().getDefaultDisplay());
		int imageWidth = dimension[0] / SCALE_FACTOR;
		int imageHeight = dimension[1] / SCALE_FACTOR;
		// ��ʼ��ͼƬ
		adapter.createImages(imageWidth, imageHeight);

		// ����Adapter����ʾλ��λ�ڿؼ��м䣬����ʹ�����Ҿ���"����"����
		mGalleryFlow = (GalleryFlow) findViewById(R.id.gallery_flow);
		mGalleryFlow.setSpacing(GALLERY_SPACING);
		mGalleryFlow.setAdapter(adapter);
		mGalleryFlow.setSelection(Integer.MAX_VALUE / 2);
	}
	/* ������end */
}
