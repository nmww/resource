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

	// 屏幕宽度
	private int screentWidth = 0;
	// 屏幕高度
	private int screentHeight = 0;

	// View可伸展最长的宽度
	private int maxSize;

	// View可伸展最小宽度
	private int minSize;

	// 当前点击的View
	private View currentView;

	// 显示最长的那个View
	private View preView;

	// 主布局ViewGroup
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
	 *   初始化时哪一个是最大的 从零开始
	 */
	private void initViewData(int index) {

		tvLog = (TextView) this.findViewById(R.id.tv_log);
		View child;
		int sizeValue = 0;
		LayoutParams params = null;
		int childCount = mainContain.getChildCount();
		if (index < 0 || index >= childCount) {
			throw new RuntimeException("index 超出范围");
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
		screentWidth = metric.widthPixels; // 屏幕宽度（像素）
		screentHeight = metric.heightPixels;
		//设置屏幕宽度
		measureSize(screentWidth);
		// StretchAnimation实例化时修改 StretchAnimation.TYPE.horizontal 水平效果
		//maxSIze是伸缩的最大值，minSize是伸缩的最小值，500是500毫秒的动画时间
		stretchanimation = new StretchAnimation(maxSize, minSize,
				StretchAnimation.TYPE.horizontal, 500);
		// 设置它的插值器 弹球效果 
		stretchanimation.setInterpolator(new BounceInterpolator());
		// 动画播放的总时间 
		stretchanimation.setDuration(800);
		// // 动画播放完后的回调   
		stretchanimation.setOnAnimationListener(this);
		// 播放动画 参数是你要播放的View  
		stretchanimation.startAnimation(currentView) ;
	}

	/**
	 * 测量View 的 max min 长度 这里你可以根据你的要求设置max
	 * 
	 * @param screenSize
	 * @param index
	 *  从零开始
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
					+ "动画不能执行";
			printAddViewDebugInfo(addInfo);
			return;
		} else {
			currentView = tempView;
		}
		Log.i(TAG, ((Button) currentView).getText().toString() + " click");
		clickEvent(currentView);
		onOffClickable(false);
		//点击按钮时执行此代码   start animation
		String addInfo = ((Button) currentView).getText().toString()
				+ "start animation";
		printAddViewDebugInfo(addInfo);
		//按钮动画开始
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

	// 调试信息
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

	// LinearLayout下所有childView 可点击开关
	// 当动画在播放时应该设置为不可点击，结束时设置为可点击
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
		//按钮伸缩结束时执行此代码   annation end
		String addStr = ((Button) v).getText().toString() + " annation end";
		printAddViewDebugInfo(addStr);
		onOffClickable(true);
	}

	private void printAddViewDebugInfo(String addinfo) {
		String temp = tvLog.getText().toString();
		tvLog.setText("打印语句："+"\n"+temp + "\n" + addinfo);
	}
}
