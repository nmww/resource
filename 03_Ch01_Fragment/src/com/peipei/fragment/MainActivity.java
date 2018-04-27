package com.peipei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends FragmentActivity {

	FragmentOfLife frag_First = new FragmentOfLife();
	FragmentSecond frag_Second = new FragmentSecond();
	Fragment mContext;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		// 实例化碎片类 添加到帧布局中
		// FragementActivity ---> FragementManager ---> FragementTrasaction(启动)
		// ---> add添加碎片 ---> 提交

		FragmentTransaction tran = getSupportFragmentManager()
				.beginTransaction();
		tran.setCustomAnimations(android.R.anim.fade_in,
				android.R.anim.slide_out_right);
		tran.add(R.id.container, frag_First).commit();
		mContext = frag_First;
	}

	public void setViewFirst(View v) {
		setFragmentView(frag_First);
		// switchContent(mContext,frag_First);
	}

	public void setViewSecond(View v) {
		setFragmentView(frag_Second);
		// switchContent(mContext,frag_Second);
	}

	/**
	 * @describe：以替换的方式显示Fragment。
	 */
	private void setFragmentView(Fragment frag_Second2) {
		FragmentTransaction tran = getSupportFragmentManager()
				.beginTransaction();
		tran.replace(R.id.container, frag_Second2);
		tran.commit();// 提交
	}

	/**
	 * @describe：以隐藏的方式显示Fragment。
	 */
	public void switchContent(Fragment from, Fragment to) {
		FragmentManager fraManager = this.getSupportFragmentManager();
		if (mContext != to) {
			mContext = to;
			FragmentTransaction transaction = fraManager.beginTransaction()
					.setCustomAnimations(android.R.anim.fade_in,
							android.R.anim.slide_out_right);
			if (!to.isAdded()) {// 先判断是否被add过
				transaction.hide(from).add(R.id.container, to).commit();// 隐藏当前的fragment，add下一个到Activity中。
				// transaction.remove(from);
			} else {
				transaction.hide(from).show(to).commit();// 隐藏当前的fragment，显示下一个。
			}
		}
	}

	// public void test(View v) {
	// FragmentTransaction tran = getSupportFragmentManager()
	// .beginTransaction();
	// tran.replace(R.id.container, new FragmentOfLife());
	// tran.addToBackStack(null);// 加入栈
	// tran.commit();
	// }
	//
	// public void test1(View v) {
	// FragmentTransaction tran = getSupportFragmentManager()
	// .beginTransaction();
	// tran.replace(R.id.container, new FragmentSecond());
	// tran.addToBackStack(null);
	// tran.commit();
	// }

}
