package com.zhangyx.MyLauncherGuide.activity.scrollView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyLauncherGuide.R;
import com.zhangyx.MyLauncherGuide.SuccessLaunchActivity;
import com.zhangyx.MyLauncherGuide.utils.AnimationUtil;

public class ScollerViewActivity extends Activity implements
		OnScrollChangedListener {

	@ViewInject(R.id.ll_anim)
	private LinearLayout mLLAnim;

	@ViewInject(R.id.tvInNew)
	private TextView tvInNew;

	// 自定义控件也可以
	// @ViewInject(R.id.sv_main)
	private MyScrollView mSVmain;
	private int mScrollViewHeight;

	private int mStartAnimateTop;
	private boolean hasStart = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scrollview);
		ViewUtils.inject(this);
		initView();
	}

	private void initView() {
		mSVmain = (MyScrollView) findViewById(R.id.sv_main);
		mSVmain.setOnScrollChangedListener(this);
		mLLAnim.setVisibility(View.INVISIBLE);
		tvInNew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ScollerViewActivity.this,
						SuccessLaunchActivity.class));
				AnimationUtil.finishActivityAnimation(ScollerViewActivity.this);
			}
		});
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		mScrollViewHeight = mSVmain.getHeight();
		mStartAnimateTop = mScrollViewHeight / 5 * 4;
	}

	// 竖向滑动时定义的回调接口
	@Override
	public void onScrollChanged(int top, int oldTop) {
		int animTop = mLLAnim.getTop() - top;// top滚动多少距离
		Log.i("TAG", "-------animTop>"+animTop);
		Log.i("TAG", "-------top>"+top);
		if (top > oldTop) {
			if (animTop < mStartAnimateTop && !hasStart) {
				Animation anim = AnimationUtils
						.loadAnimation(this, R.anim.show);
				mLLAnim.setVisibility(View.VISIBLE);
				mLLAnim.startAnimation(anim);
				hasStart = true;
				Log.i("滚动位置", mLLAnim.getTop() + "  " + animTop + "  " + top
						+ "  " + oldTop + "五分之四" + mStartAnimateTop);
			}
		} else {
			if (animTop > mStartAnimateTop && hasStart) {
				Animation anim = AnimationUtils.loadAnimation(this,
						R.anim.close);
				mLLAnim.setVisibility(View.INVISIBLE);
				mLLAnim.startAnimation(anim);
				hasStart = false;
				Log.e("滚动位置", mLLAnim.getTop() + "  " + animTop + "  " + top
						+ "  " + oldTop + "五分之四" + mStartAnimateTop);
			}
		}
	}

}
