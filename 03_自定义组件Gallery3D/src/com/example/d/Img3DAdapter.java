package com.example.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * @author pengyiming
 * @date 2013-9-30
 * @function GalleryFlow������
 */
public class Img3DAdapter extends BaseAdapter {
	/* ���ݶ�begin */
	private final String TAG = "ImageAdapter";
	private Context mContext;

	// ͼƬ����
	private int[] mImageIds;
	// ͼƬ�ؼ�����
	private ImageView[] mImages;
	// ͼƬ�ؼ�LayoutParams
	private GalleryFlow.LayoutParams mImagesLayoutParams;

	/* ���ݶ�end */

	/* ������begin */
	public Img3DAdapter(Context context, int[] imageIds) {
		mContext = context;
		mImageIds = imageIds;
		mImages = new ImageView[mImageIds.length];

		mImagesLayoutParams = new GalleryFlow.LayoutParams(
				(new Gallery.LayoutParams(200, 300)));
		// Gallery.LayoutParams.MATCH_PARENT,
		// Gallery.LayoutParams.MATCH_PARENT);
	}

	/**
	 * @function ����ָ����ߴ��������Ƶ�Bitmap�������Ƶ�ImageView�ؼ���
	 * @param imageWidth
	 * @param imageHeight
	 * @return void
	 */
	public void createImages(int imageWidth, int imageHeight) {
		// ԭͼ�뵹Ӱ�ļ��5px
		final int gapHeight = 5;

		int index = 0;
		for (int imageId : mImageIds) {
			/* step1 ������ʽ����ԭͼ�����ɵ�Ӱ */
			// ����ԭͼ������ԭͼBitmap����
			// Bitmap originalImage =
			// BitmapFactory.decodeResource(mContext.getResources(), imageId);
			Bitmap originalImage = BitmapScaleDownUtil
					.decodeSampledBitmapFromResource(mContext.getResources(),
							imageId, imageWidth, imageHeight);
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();

			// Y�᷽����ʵ�ʾ���X�ᷭת
			Matrix matrix = new Matrix();
			matrix.setScale(1, -1);
			// �ҽ�ȡԭͼ�°벿�ִ�����ӰBitmap����
			Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
					height / 2, width, height / 2, matrix, false);

			/* step2 ���� */
			// ����һ���ɰ���ԭͼ+���+��Ӱ����ͼBitmap����
			Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height
					+ gapHeight + height / 2), Config.ARGB_8888);
			// ����ͼBitmap����֮�ϴ�������
			Canvas canvas = new Canvas(bitmapWithReflection);
			// �����Ч��
			canvas.setDrawFilter(new PaintFlagsDrawFilter(0,
					Paint.ANTI_ALIAS_FLAG));
			// ����ԭͼ
			canvas.drawBitmap(originalImage, 0, 0, null);
			// ���Ƽ��
			Paint gapPaint = new Paint();
			gapPaint.setColor(0xFFCCCCCC);
			canvas.drawRect(0, height, width, height + gapHeight, gapPaint);
			// ���Ƶ�Ӱ
			canvas.drawBitmap(reflectionImage, 0, height + gapHeight, null);

			/* step3 ��Ⱦ */
			// ����һ�����Խ������Ⱦ��������Ⱦ��Ӱ
			Paint paint = new Paint();
			LinearGradient shader = new LinearGradient(0, height, 0, (height
					+ gapHeight + height / 2), 0x70ffffff, 0x00ffffff,
					TileMode.CLAMP);
			// ���û�����Ⱦ��
			paint.setShader(shader);
			// ����ͼƬ���ģʽ
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			// ��Ⱦ��Ӱ+���
			canvas.drawRect(0, height, width,
					(height + gapHeight + height / 2), paint);

			/* step4 ��ImageView�ؼ��ϻ��� */
			ImageView imageView = new ImageView(mContext);
			imageView.setImageBitmap(bitmapWithReflection);
			imageView.setLayoutParams(mImagesLayoutParams);
			// ��log
			imageView.setTag(index);

			/* step5 �ͷ�heap */
			originalImage.recycle();
			reflectionImage.recycle();
			// bitmapWithReflection.recycle();

			mImages[index++] = imageView;
		}
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return mImages[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mImages[position % mImages.length];
	}
	/* ������end */
}