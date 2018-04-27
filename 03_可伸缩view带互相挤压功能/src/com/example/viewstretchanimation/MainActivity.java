package com.example.viewstretchanimation;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener,
		StretchAnimation.AnimationListener {

	private final static String TAG = "MainActivity";

	// ��Ļ���
	private int screentWidth = 0;
	// ��Ļ�߶�
	private int screentHeight = 0;

	// View����չ��Ŀ��
	private int maxSize;

	// View����չ��С���
	private int minSize;

	// ��ǰ�����View
	private View currentView;

	// ��ʾ����Ǹ�View
	private View preView;

	// ������ViewGroup
	private LinearLayout mainContain;

	private StretchAnimation stretchanimation;

	private TextView tvLog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mainContain = (LinearLayout) this.findViewById(R.id.main_contain);

		initCommonData();

		initViewData(0);

		
	}

	/**
	 * @param index
	 *   ��ʼ��ʱ��һ�������� ���㿪ʼ
	 */
	private void initViewData(int index) {

		tvLog = (TextView) this.findViewById(R.id.tv_log);
		View child;
		int sizeValue = 0;
		LayoutParams params = null;
		int childCount = mainContain.getChildCount();
		if (index < 0 || index >= childCount) {
			throw new RuntimeException("index ������Χ");
		}

		for (int i = 0; i < childCount; i++) {

			child = mainContain.getChildAt(i);
			child.setOnClickListener(this);
			params = child.getLayoutParams();

			if (i == index) {
				preView = child;
				sizeValue = maxSize;
			} else {
				sizeValue = minSize;
			}
			if (stretchanimation.getmType() == com.example.viewstretchanimation.StretchAnimation.TYPE.horizontal) {
				
				params.width = sizeValue;
			} else if (stretchanimation.getmType() == com.example.viewstretchanimation.StretchAnimation.TYPE.vertical) {
				params.height = sizeValue;
			}

			child.setLayoutParams(params);
		}

	}

	private void initCommonData() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screentWidth = metric.widthPixels; // ��Ļ��ȣ����أ�
		screentHeight = metric.heightPixels;
		//������Ļ���
		measureSize(screentWidth);
		// StretchAnimationʵ����ʱ�޸� StretchAnimation.TYPE.horizontal ˮƽЧ��
		//maxSIze�����������ֵ��minSize����������Сֵ��500��500����Ķ���ʱ��
		stretchanimation = new StretchAnimation(maxSize, minSize,
				StretchAnimation.TYPE.horizontal, 500);
		// �������Ĳ�ֵ�� ����Ч�� 
		stretchanimation.setInterpolator(new BounceInterpolator());
		// �������ŵ���ʱ�� 
		stretchanimation.setDuration(800);
		// // �����������Ļص�   
		stretchanimation.setOnAnimationListener(this);
		// ���Ŷ��� ��������Ҫ���ŵ�View  
		stretchanimation.startAnimation(currentView) ;
	}

	/**
	 * ����View �� max min ���� ��������Ը������Ҫ������max
	 * 
	 * @param screenSize
	 * @param index
	 *  ���㿪ʼ
	 */
	private void measureSize(int layoutSize) {
		int halfWidth = layoutSize / 2;
		maxSize = halfWidth - 50;
		minSize = (layoutSize - maxSize) / (mainContain.getChildCount() - 1);

		Log.i(TAG, "maxWidth=" + maxSize + " minWidth = " + minSize);

	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		View tempView = null;
		switch (id) {

		case R.id.btnOne:
			tempView = mainContain.getChildAt(0);
			System.out.println("--------1-------------");
			break;
		case R.id.btnTwo:
			tempView = mainContain.getChildAt(1);
			break;
		case R.id.btnThree:
			tempView = mainContain.getChildAt(2);
			break;
		case R.id.btnFour:
			tempView = mainContain.getChildAt(3);
			break;
		}
		if (tempView == preView) {
			Log.d(TAG, "");
			String addInfo = ((Button) tempView).getText().toString()
					+ "��������ִ��";
			printAddViewDebugInfo(addInfo);
			return;
		} else {
			currentView = tempView;
		}
		Log.i(TAG, ((Button) currentView).getText().toString() + " click");
		clickEvent(currentView);
		onOffClickable(false);
		//�����ťʱִ�д˴���   start animation
		String addInfo = ((Button) currentView).getText().toString()
				+ "start animation";
		printAddViewDebugInfo(addInfo);
		//��ť������ʼ
		stretchanimation.startAnimation(currentView);

	}

	private void clickEvent(View view) {
		View child;
		int childCount = mainContain.getChildCount();
		LinearLayout.LayoutParams params;
		for (int i = 0; i < childCount; i++) {
			child = mainContain.getChildAt(i);
			if (preView == child) {
				params = (android.widget.LinearLayout.LayoutParams) child
						.getLayoutParams();

				if (preView != view) {
					params.weight = 1.0f;
				}
				child.setLayoutParams(params);

			} else {
				params = (android.widget.LinearLayout.LayoutParams) child
						.getLayoutParams();
				params.weight = 0.0f;
				
				if (stretchanimation.getmType() == StretchAnimation.TYPE.horizontal) {
					params.width = minSize;
				} else if (stretchanimation.getmType() == StretchAnimation.TYPE.vertical) {
					params.height = minSize;
				}

				child.setLayoutParams(params);
			}
		}
		preView = view;
		printDebugMsg() ;
	}

	// ������Ϣ
	private void printDebugMsg() {
		View child;
		int childCount = mainContain.getChildCount();
		StringBuilder sb = new StringBuilder();
		sb.append("preView = " + ((Button) preView).getText().toString() + " ");
		sb.append("click = " + ((Button) currentView).getText().toString()
				+ " ");
		for (int i = 0; i < childCount; i++) {
			child = mainContain.getChildAt(i);
			LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) child
					.getLayoutParams();
			sb.append(params.weight + " ");
		}
		Log.d(TAG, sb.toString());
	}

	// LinearLayout������childView �ɵ������
	// �������ڲ���ʱӦ������Ϊ���ɵ��������ʱ����Ϊ�ɵ��
	private void onOffClickable(boolean isClickable) {
		View child;
		int childCount = mainContain.getChildCount();
		for (int i = 0; i < childCount; i++) {
			child = mainContain.getChildAt(i);
			child.setClickable(isClickable);
		}
	}

	@Override
	public void animationEnd(View v) {

		Log.i(TAG, ("-----" + ((Button) v).getText().toString())
				+ " annation end");
		//��ť��������ʱִ�д˴���   annation end
		String addStr = ((Button) v).getText().toString() + " annation end";
		printAddViewDebugInfo(addStr);
		onOffClickable(true);
	}

	private void printAddViewDebugInfo(String addinfo) {
		String temp = tvLog.getText().toString();
		tvLog.setText("��ӡ��䣺"+"\n"+temp + "\n" + addinfo);
	}
}
