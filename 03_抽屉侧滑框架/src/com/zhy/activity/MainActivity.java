package com.zhy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.nineoldandroids.view.ViewHelper;
import com.zhy.demo_zhy_17_drawerlayout.R;

/**
 * 主界面
 */
public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;// 控件对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initEvents();

	}

	/**
	 * 初始化抽屉控件视图
	 */
	private void initView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);// 关闭右侧菜单的滑动出现效果
	}

	/**
	 * 初始化DrawerLayout事件监听
	 */
	private void initEvents() {
		// 设置监听
		mDrawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int newState) {
			}

			// slideOffset 表示 滑动的幅度（0-1）
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals(
						getResources().getString(R.string.left_tag))) {// 展开左侧菜单
					float leftScale = 1 - 0.3f * scale;

					// 设置左侧菜单缩放效果
					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));

					// 设置中间View缩放效果
					ViewHelper.setTranslationX(mContent,
							mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else {// 展开右侧菜单
					// 设置中间View缩放效果
					ViewHelper.setTranslationX(mContent,
							-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			// 菜单打开
			@Override
			public void onDrawerOpened(View drawerView) {
			}

			// 菜单关闭
			@Override
			public void onDrawerClosed(View drawerView) {
				/*
				 * mDrawerLayout.setDrawerLockMode(
				 * DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
				 */
			}
		});
	}

	/**
	 * 打开右侧菜单
	 * 
	 * @param view
	 */
	public void OpenRightMenu(View view) {
		mDrawerLayout.openDrawer(Gravity.RIGHT);// 展开侧边的菜单
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.RIGHT);// 打开手势滑动
	}

	/**
	 * 打开右侧菜单
	 * 
	 * @param view
	 */
	public void OpenLeftMenu(View view) {
		mDrawerLayout.openDrawer(Gravity.LEFT);// 展开侧边的菜单
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.LEFT);// 打开手势滑动
	}
}