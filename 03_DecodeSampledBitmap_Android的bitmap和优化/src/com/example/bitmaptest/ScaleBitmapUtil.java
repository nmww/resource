package com.example.bitmaptest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author��Peipei.zhao
 * @date��2015-3-21
 * @describe������λͼ������
 */
public class ScaleBitmapUtil {

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// �Ƚ�inJustDecodeBounds��������Ϊtrue����������ڴ���䡣
		options.inJustDecodeBounds = true;
		// ��ͼƬ����ѡ������
		BitmapFactory.decodeResource(res, resId, options);
		// ��ͼƬ����ָ��������ѹ��
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		// ��ͼƬ������ɺ��ٽ����ڴ�ķ��䣬�����ڴ�й¶�ķ�����
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	// ����ͼƬ��ѹ������
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// ͼƬ��ԭʼ�ߴ�
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);// ��������
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// ѡ�񳤿�߽�С�ı�������Ϊѹ��������
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

}
