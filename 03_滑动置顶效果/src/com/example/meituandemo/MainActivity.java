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
 * ���͵�ַ:http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 * 
 */
public class MainActivity extends Activity implements OnScrollListener {
	private MyScrollView myScrollView;
	private LinearLayout mBuyLayout;
	private WindowManager mWindowManager;
	/**
	 * �ֻ���Ļ���
	 */
	private int screenWidth;
	/**
	 * ������View
	 */
	private static View suspendView;
	/**
	 * ������Ĳ���
	 */
	private static WindowManager.LayoutParams suspendLayoutParams;
	/**
	 * ���򲼾ֵĸ߶�
	 */
	private int buyLayoutHeight;
	/**
	 * myScrollView���丸�಼�ֵĶ�������
	 */
	private int myScrollViewTop;

	/**
	 * ���򲼾����丸�಼�ֵĶ�������
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
	 * �����н����ʱ�򣬼����еĲ��ֻ�����ϵ�ʱ����������ȡ���򲼾ֵĸ߶Ⱥ�myScrollView���븸�಼�ֵĶ���λ�á�
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
	 * �����Ļص���������������Y������ڻ��ߵ��� ���򲼾־��븸�಼�ֶ�����λ�ã�����ʾ����������� ��������Y�ľ���С��
	 * ���򲼾־��븸�಼�ֶ�����λ�ü��Ϲ��򲼾ֵĸ߶Ⱦ��Ƴ������������
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
	 * ��ʾ�����������
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
	 * �Ƴ������������
	 */
	private void removeSuspend() {
		if (suspendView != null) {
			mWindowManager.removeView(suspendView);
			suspendView = null;
		}
	}

}
