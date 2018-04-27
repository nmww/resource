package com.zhangyx.MyLauncherGuide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyLauncherGuide.activity.scrollView.ScollerViewActivity;
import com.zhangyx.MyLauncherGuide.activity.splash.SplashActivity;
import com.zhangyx.MyLauncherGuide.activity.viewFlipper.ViewFlipperActivity;
import com.zhangyx.MyLauncherGuide.activity.viewPage.ViewPagerActivity;
import com.zhangyx.MyLauncherGuide.utils.AnimationUtil;

/**
 * APP 引导方式分类 com.zhangyx.MyLauncherGuide.MainActivity
 * 
 * @author Admin-zhangyx
 * 
 *         create at 2015-1-21 下午2:04:27
 */
public class MainActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.btnSplash)
	private Button btnSplash;

	@ViewInject(R.id.btnViewPage)
	private Button btnViewPage;

	@ViewInject(R.id.btnViewFlipper)
	private Button btnViewFlipper;

	@ViewInject(R.id.btnScrollView)
	private Button btnScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		btnSplash.setOnClickListener(this);
		btnViewPage.setOnClickListener(this);
		btnViewFlipper.setOnClickListener(this);
		btnScrollView.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSplash) {
			startActivity(new Intent(this, SplashActivity.class));
		} else if (v == btnViewPage) {
			startActivity(new Intent(this, ViewPagerActivity.class));
		} else if (v == btnViewFlipper) {
			startActivity(new Intent(this, ViewFlipperActivity.class));
		} else if (v == btnScrollView) {
			startActivity(new Intent(this, ScollerViewActivity.class));
		}
		AnimationUtil.activityZoomAnimation(this);
	}

}
