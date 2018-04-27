package com.zhangyx.MyLauncherGuide.activity.viewFlipper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyLauncherGuide.R;
import com.zhangyx.MyLauncherGuide.SuccessLaunchActivity;
import com.zhangyx.MyLauncherGuide.utils.AnimationUtil;

public class ViewFlipperActivity extends Activity implements OnGestureListener {

	@ViewInject(R.id.vf_activity)
	private ViewFlipper mVFActivity;

	@ViewInject(R.id.tvInNew)
	private TextView tvInNew;

	//声明手势对象mGestureDetector
	private GestureDetector mGestureDetector;//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewflipper);
		ViewUtils.inject(this);
		initView();
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		//初始化手势对象mGestureDetector
		mGestureDetector = new GestureDetector(this);
		tvInNew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ViewFlipperActivity.this,
						SuccessLaunchActivity.class));
				AnimationUtil.finishActivityAnimation(ViewFlipperActivity.this);
			}
		});
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	// 用来判断View切换是否正在进行
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		/*
		 * e1 手势起点的移动事件 e2 当前手势点的移动事件 velocityX 每秒x轴方向移动的像素 velocityY
		 * 每秒y轴方向移动的像素
		 */
		if (e1.getX() > e2.getX()) {
			mVFActivity.showNext();
		} else if (e1.getX() < e2.getX()) {
			mVFActivity.showPrevious();
		} else {
			return false;
		}
		return true;
	}

	//将屏幕的触摸事件传给---手势控件GestureDetector
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

}
