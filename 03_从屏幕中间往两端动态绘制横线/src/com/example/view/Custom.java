package com.example.view;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Custom extends View {
	private float upX;
	private float upY;
	private float downX;
	private float downY;
	private float moveX;
	private float moveY;
	private int width;
	private int width1;
	private int width2;
	private int j;
	int[] color = { Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN };
	private int cr = color[0];

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			Log.e("------1", "" + width1);
			if (width1 != 0) {
				width1--;
				invalidate();
			} else if (width1 == 0) {
				invalidate();
				width = Custom.this.getMeasuredWidth() / 2;
				width1 = width;
				j = (int) (Math.random() * 4);
				cr = color[j];
			}
			if (width2 != Custom.this.getMeasuredWidth()) {
				Log.e("------3", "" + width2);
				width2++;
				invalidate();

			} else if (width2 == Custom.this.getMeasuredWidth()) {
				width = Custom.this.getMeasuredWidth() / 2;
				width2 = width;
			}

		};
	};

	private Paint paint;
	private Paint paint1;
	private Timer timer;

	public Custom(Context context) {
		super(context);
		paint = new Paint();
		paint1 = new Paint();
		// 设置画笔的颜色

		timer = new Timer();
	}

	public Custom(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint();
		paint1 = new Paint();
		// 设置画笔的颜色
		paint.setColor(Color.BLUE);
		timer = new Timer();
	}

	public Custom(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint1 = new Paint();
		// 设置画笔的颜色
		paint.setColor(Color.BLUE);
		timer = new Timer();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setAntiAlias(false);
		paint.setColor(cr);
		// 设置画笔的样式
		paint.setStyle(Style.STROKE);
		// 设置笔刷的粗细度
		paint.setStrokeWidth(10);
		Log.e("------2", "" + width);
		canvas.drawLine(width, 0, width1, 0, paint);// 画线
		paint1.setAntiAlias(false);
		paint1.setColor(cr);
		// 设置画笔的样式
		paint1.setStyle(Style.STROKE);
		paint1.setStrokeWidth(10);
		canvas.drawLine(width, 0, width2, 0, paint1);// 画线

		// canvas.drawLine(downX, downY, moveX, moveY, paint);// 画线

	}

	// 滑动画线
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = event.getX();
			downY = event.getY();
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			moveX = event.getX();
			moveY = event.getY();
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			upX = event.getX();
			upY = event.getY();
			invalidate();
			break;
		}
		moveX = event.getX();
		moveY = event.getY();
		return true;
	}

	public void updateTime() {

		width = this.getMeasuredWidth() / 2;
		width1 = width;
		width2 = width;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(10);
			}
		}, 0, 5);
	}
}
