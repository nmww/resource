package com.zhangyx.MyLauncherGuide.activity.viewPage;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhangyx.MyLauncherGuide.R;

/**
 * ViewPager 引导 com.zhangyx.MyLauncherGuide.activity.viewPage.ViewPagerActivity
 * 
 * @author Admin-zhangyx
 * 
 *         create at 2015-1-21 下午4:24:29
 */
public class ViewPagerActivity extends FragmentActivity {

	private ViewPager mVPActivity;
	private Fragment1 mFragment1;
	private Fragment2 mFragment2;
	private Fragment3 mFragment3;
	private Fragment4 mFragment4;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	private PagerAdapter mPgAdapter;
	private RelativeLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		initView();
	}

	private void initView() {
		container = (RelativeLayout) this.findViewById(R.id.container);
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);

		mFragment1 = new Fragment1();
		mFragment2 = new Fragment2();
		mFragment3 = new Fragment3();
		mFragment4 = new Fragment4();

		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mListFragment.add(mFragment4);
		// 1.设置幕后item的缓存数目
		mVPActivity.setOffscreenPageLimit(3);
		// 2.设置页与页之间的间距
		mVPActivity.setPageMargin(50);
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		mVPActivity.setAdapter(mPgAdapter);

		// 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
		container.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mVPActivity.dispatchTouchEvent(event);
			}
		});
	}
}
