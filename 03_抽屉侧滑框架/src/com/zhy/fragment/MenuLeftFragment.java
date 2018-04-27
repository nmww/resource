package com.zhy.fragment;

import com.zhy.demo_zhy_17_drawerlayout.R;
import com.zhy.demo_zhy_17_drawerlayout.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 左侧菜单
 */
public class MenuLeftFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_menu, container, false);
	}
}
