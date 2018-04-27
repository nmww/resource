package com.example.bitmaptest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @author��Peipei.zhao
 * @date��2015-3-21
 * @describe��ԭ��λͼ��ѹ��λͼ������ͼƬ�Ա�չʾ
 */
public class MainActivity extends Activity {

	private ImageView imageView;
	private Bitmap bitmap;// λͼ
	private int image;// ͼƬ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image = R.drawable.photo4;
		imageView = (ImageView) this.findViewById(R.id.iv_bitmap);
	}

	/** ԭ��λͼ */
	public void bornBitmap(View v) {
		cleanImageView();
		bitmap = BitmapFactory.decodeResource(getResources(), image);
		imageView.setImageBitmap(bitmap);
	}

	/** ѹ��λͼ */
	public void scaleBitmap(View v) {
		cleanImageView();
		// ͼƬѹ�����������������������
		imageView.setImageBitmap(ScaleBitmapUtil
				.decodeSampledBitmapFromResource(getResources(), image, 200,
						100));
	}

	/** ����ͼƬ */
	public void backgroundImage(View v) {
		cleanImageView();
		imageView.setBackgroundResource(image);
	}

	/** ImageView��� */
	private void cleanImageView() {
		imageView.setImageBitmap(null);
		imageView.setBackgroundResource(0);
	}

}