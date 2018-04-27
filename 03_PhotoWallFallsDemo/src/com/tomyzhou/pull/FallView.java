package com.tomyzhou.pull;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

//自定义瀑布流控件
public class FallView extends LinearLayout {

	private List<LinearLayout> list;
	private LinearLayout ll_list1;
	private LinearLayout ll_list2;
	private LinearLayout ll_list3; // 三列

	/**
	 * 
	 * @param context
	 * @param viewList
	 *            需要显示的图片列表
	 */
	public FallView(Context context, List<View> viewList) {
		super(context);
		list = new ArrayList<LinearLayout>();
		View view = View.inflate(context, R.layout.layout_fall, this);

		ll_list1 = (LinearLayout) view.findViewById(R.id.ll_list1);
		ll_list2 = (LinearLayout) view.findViewById(R.id.ll_list2);
		ll_list3 = (LinearLayout) view.findViewById(R.id.ll_list3);

		initView(viewList);
	}

	// 添加每个LinearLayout里面的图片
	/**
	 * @param number
	 *            每个LinearLayout里面Image的个数
	 */
	private void initView(List<View> viewList) {
		System.out.println(viewList.size());
		for (int i = 0; i < viewList.size(); i++) {
			View view = viewList.get(i);
			if (i % 3 == 0) {
				ll_list1.addView(view);
			}
			if (i % 3 == 1) {
				ll_list2.addView(view);
			}
			if (i % 3 == 2) {
				ll_list3.addView(view);
			}
		}
	}

}
