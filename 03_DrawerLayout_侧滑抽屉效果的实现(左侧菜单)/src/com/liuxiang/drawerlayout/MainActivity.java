package com.liuxiang.drawerlayout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @date：2015-4-2
 * @describe：侧滑抽屉效果的实现-DrawerLayout
 */
public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;// 抽屉布局
	private ListView lv_drawer;// 抽屉内容
	private TextView tv_content;
	private ActionBarHelper mActionBar;
	private ActionBarDrawerToggle mDrawerToggle;
	String[] dataList = { "item-01", "item-02", "item-03", "item-04",
			"item-05", "item-06", "item-07", "item-08", "item-09", "item-10",
			"item-11", "item-12", };// 数据源

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);

		// 初始化控件
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		lv_drawer = (ListView) findViewById(R.id.left_drawer);
		tv_content = (TextView) findViewById(R.id.content_text);

		// 主界面默认显示左侧菜单的第一项内容
		tv_content.setText(dataList[0]);

		// 初始化侧滑菜单
		mDrawerLayout.setDrawerListener(new DemoDrawerListener());
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// 左侧菜单列表设置适配器
		lv_drawer.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList));
		// 左侧菜单ListView的item点击事件
		lv_drawer.setOnItemClickListener(new DrawerItemClickListener());
		mActionBar = createActionBarHelper();
		mActionBar.init();

		// ActionBarDrawerToggle provides convenient helpers for tying together
		// the
		// prescribed interactions between a top-level sliding drawer and the
		// action bar.
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar home/up action should open or close the drawer.
		 * mDrawerToggle will take care of this.
		 */
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/** ListView的item监听类 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			tv_content.setText(dataList[position]);
			mActionBar.setTitle(dataList[position]);// 头部UI
			mDrawerLayout.closeDrawer(lv_drawer);// 关闭抽屉
		}
	}

	/**
	 * A drawer listener can be used to respond to drawer events such as
	 * becoming fully opened or closed. You should always prefer to perform
	 * expensive operations such as drastic relayout when no animation is
	 * currently in progress, either before or after the drawer animates.
	 * 
	 * When using ActionBarDrawerToggle, all DrawerLayout listener methods
	 * should be forwarded if the ActionBarDrawerToggle is not used as the
	 * DrawerLayout listener directly.
	 */
	private class DemoDrawerListener implements DrawerLayout.DrawerListener {

		/** 抽屉的开启 */
		@Override
		public void onDrawerOpened(View drawerView) {
			mDrawerToggle.onDrawerOpened(drawerView);
			mActionBar.onDrawerOpened();
		}

		/** 抽屉的关闭 */
		@Override
		public void onDrawerClosed(View drawerView) {
			mDrawerToggle.onDrawerClosed(drawerView);
			mActionBar.onDrawerClosed();
		}

		/** 抽屉的滑动 */
		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
		}

		@Override
		public void onDrawerStateChanged(int newState) {
			mDrawerToggle.onDrawerStateChanged(newState);
		}
	}

	/**
	 * Create a compatible helper that will manipulate the action bar if
	 * available.
	 */
	private ActionBarHelper createActionBarHelper() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return new ActionBarHelperICS();// 安卓4.0系统
		} else {
			return new ActionBarHelper();
		}
	}

	/**
	 * Stub action bar helper; this does nothing.
	 */
	private class ActionBarHelper {
		public void init() {
		}

		public void onDrawerClosed() {
		}

		public void onDrawerOpened() {
		}

		public void setTitle(CharSequence title) {
		}
	}

	/**
	 * Action bar helper for use on ICS and newer devices.
	 */
	@SuppressLint("NewApi")
	private class ActionBarHelperICS extends ActionBarHelper {
		private final ActionBar mActionBar;
		private CharSequence mDrawerTitle;
		private CharSequence mTitle;

		ActionBarHelperICS() {
			mActionBar = getActionBar();
		}

		@Override
		public void init() {
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
			mTitle = mDrawerTitle = getTitle();
		}

		/** 抽屉的关闭 */
		@Override
		public void onDrawerClosed() {
			super.onDrawerClosed();
			mActionBar.setTitle(mTitle);
		}

		/** 抽屉的开启 */
		@Override
		public void onDrawerOpened() {
			super.onDrawerOpened();
			mActionBar.setTitle(mDrawerTitle);// 标题设置DrawerLayout
		}

		@Override
		public void setTitle(CharSequence title) {
			mTitle = title;
		}
	}

}
