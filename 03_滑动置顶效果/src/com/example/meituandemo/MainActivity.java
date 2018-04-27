package com.example.meituandemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

import com.example.meituandemo.MyScrollView.OnScrollListener;

/**
 * 博客地址:http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 * 
 */
public class MainActivity extends Activity implements OnScrollListener {
	private MyScrollView myScrollView;
	private LinearLayout mBuyLayout;
	private WindowManager mWindowManager;
	/**
	 * 手机屏幕宽度
	 */
	private int screenWidth;
	/**
	 * 悬浮框View
	 */
	private static View suspendView;
	/**
	 * 悬浮框的参数
	 */
	private static WindowManager.LayoutParams suspendLayoutParams;
	/**
	 * 购买布局的高度
	 */
	private int buyLayoutHeight;
	/**
	 * myScrollView与其父类布局的顶部距离
	 */
	private int myScrollViewTop;

	/**
	 * 购买布局与其父类布局的顶部距离
	 */
	private int buyLayoutTop;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myScrollView = (MyScrollView) findViewById(R.id.scrollView);
		mBuyLayout = (LinearLayout) findViewById(R.id.buy);

		myScrollView.setOnScrollListener(this);
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = mWindowManager.getDefaultDisplay().getWidth();
	}

	/**
	 * 窗口有焦点的时候，即所有的布局绘制完毕的时候，我们来获取购买布局的高度和myScrollView距离父类布局的顶部位置。
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			buyLayoutHeight = mBuyLayout.getHeight();
			buyLayoutTop = mBuyLayout.getTop();
			myScrollViewTop = myScrollView.getTop();
		}
	}

	/**
	 * 滚动的回调方法，当滚动的Y距离大于或者等于 购买布局距离父类布局顶部的位置，就显示购买的悬浮框 当滚动的Y的距离小于
	 * 购买布局距离父类布局顶部的位置加上购买布局的高度就移除购买的悬浮框。
	 */
	@Override
	public void onScroll(int scrollY) {
		if (scrollY >= buyLayoutTop) {
			if (suspendView == null) {
				showSuspend();
			}
		} else if (scrollY <= buyLayoutTop + buyLayoutHeight) {
			if (suspendView != null) {
				removeSuspend();
			}
		}
	}

	/**
	 * 显示购买的悬浮框
	 */
	private void showSuspend() {
		if (suspendView == null) {
			suspendView = LayoutInflater.from(this).inflate(
					R.layout.buy_layout, null);
			if (suspendLayoutParams == null) {
				suspendLayoutParams = new LayoutParams();
				suspendLayoutParams.type = LayoutParams.TYPE_PHONE;
				suspendLayoutParams.format = PixelFormat.RGBA_8888;
				suspendLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				suspendLayoutParams.gravity = Gravity.TOP;
				suspendLayoutParams.width = screenWidth;
				suspendLayoutParams.height = buyLayoutHeight;
				suspendLayoutParams.x = 30;
				suspendLayoutParams.y = myScrollViewTop;
			}
		}
		mWindowManager.addView(suspendView, suspendLayoutParams);
	}

	/**
	 * 移除购买的悬浮框
	 */
	private void removeSuspend() {
		if (suspendView != null) {
			mWindowManager.removeView(suspendView);
			suspendView = null;
		}
	}

}
