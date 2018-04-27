package com.tomyzhou.pull;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class FallDemoActivity extends Activity {
	private ScrollView sv_main;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		sv_main = new ScrollView(this);

		List<View> viewList = new ArrayList<View>();
		for (int i = 0; i < 60; i++) {
			if (i % 2 == 0) {
				View view = View.inflate(this, R.layout.myimage, null);
				ImageView iv_image = (ImageView) view
						.findViewById(R.id.iv_image);
				iv_image.setImageResource(R.drawable.a1);
				TextView tv_image = (TextView) view.findViewById(R.id.tv_image);
				tv_image.setText("第" + i + "个图的描述");
				viewList.add(view);
			}
			if (i % 2 == 1) {
				View view = View.inflate(this, R.layout.myimage, null);
				ImageView iv_image = (ImageView) view
						.findViewById(R.id.iv_image);
				iv_image.setImageResource(R.drawable.a14);
				TextView tv_image = (TextView) view.findViewById(R.id.tv_image);
				tv_image.setText("第" + i + "个图的描述");
				viewList.add(view);
			}
		}

		FallView fv = new FallView(this, viewList);
		sv_main.addView(fv);
		setContentView(sv_main);

	}
}