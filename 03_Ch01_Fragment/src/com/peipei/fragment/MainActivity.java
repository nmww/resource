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
		// ʵ������Ƭ�� ��ӵ�֡������
		// FragementActivity ---> FragementManager ---> FragementTrasaction(����)
		// ---> add�����Ƭ ---> �ύ

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
	 * @describe�����滻�ķ�ʽ��ʾFragment��
	 */
	private void setFragmentView(Fragment frag_Second2) {
		FragmentTransaction tran = getSupportFragmentManager()
				.beginTransaction();
		tran.replace(R.id.container, frag_Second2);
		tran.commit();// �ύ
	}

	/**
	 * @describe�������صķ�ʽ��ʾFragment��
	 */
	public void switchContent(Fragment from, Fragment to) {
		FragmentManager fraManager = this.getSupportFragmentManager();
		if (mContext != to) {
			mContext = to;
			FragmentTransaction transaction = fraManager.beginTransaction()
					.setCustomAnimations(android.R.anim.fade_in,
							android.R.anim.slide_out_right);
			if (!to.isAdded()) {// ���ж��Ƿ�add��
				transaction.hide(from).add(R.id.container, to).commit();// ���ص�ǰ��fragment��add��һ����Activity�С�
				// transaction.remove(from);
			} else {
				transaction.hide(from).show(to).commit();// ���ص�ǰ��fragment����ʾ��һ����
			}
		}
	}

	// public void test(View v) {
	// FragmentTransaction tran = getSupportFragmentManager()
	// .beginTransaction();
	// tran.replace(R.id.container, new FragmentOfLife());
	// tran.addToBackStack(null);// ����ջ
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
