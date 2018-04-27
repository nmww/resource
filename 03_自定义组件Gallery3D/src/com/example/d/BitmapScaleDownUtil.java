package com.example.d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;

/**
 * @author pengyiming
 * @date 2013-9-30
 * @function Bitmap���Ŵ�������
 */
public class BitmapScaleDownUtil {
	/* ���ݶ�begin */
	private final String TAG = "BitmapScaleDownUtil";

	/* ���ݶ�end */

	/* ������begin */
	/**
	 * @function ��ȡ��Ļ��С
	 * @param display
	 * @return ��Ļ���
	 */
	public static int[] getScreenDimension(Display display) {
		int[] dimension = new int[2];
		dimension[0] = display.getWidth();
		dimension[1] = display.getHeight();

		return dimension;
	}

	/**
	 * @function ��ȡ����ʽ����Bitmap
	 * @param res
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return ȡ�����Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		// step1����inJustDecodeBounds��Ϊtrue���Խ���Bitmap��ʵ�ߴ�
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// step2������Bitmapȡ������
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// step3����inJustDecodeBounds��Ϊfalse����ȡ�����н���Bitmap
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	/**
	 * @function ����Bitmapȡ������
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return ȡ������
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Ĭ��ȡ������Ϊ1:1
		int inSampleSize = 1;

		// Bitmapԭʼ�ߴ�
		final int width = options.outWidth;
		final int height = options.outHeight;

		// ȡ���ȡ������
		if (height > reqHeight || width > reqWidth) {
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);

			// ȡ������ΪX:1������X>=1
			inSampleSize = Math.max(widthRatio, heightRatio);
		}

		return inSampleSize;
	}
	/* ������end */
}
