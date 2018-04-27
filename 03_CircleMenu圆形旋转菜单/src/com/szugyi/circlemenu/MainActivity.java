package com.szugyi.circlemenu;

/*
 * Copyright 2013 Csaba Szugyiczki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;
import com.szugyi.circlemenu.view.CircleLayout.OnItemClickListener;
import com.szugyi.circlemenu.view.CircleLayout.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener,
		OnItemClickListener {
	TextView selectedTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 自定义的组件Circlelayout
		CircleLayout circleMenu = (CircleLayout) findViewById(R.id.main_circle_layout);
		circleMenu.setOnItemSelectedListener(this);
		circleMenu.setOnItemClickListener(this);

		// 设置被选择的那个圈对应的名字
		selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
		selectedTextView.setText(((CircleImageView) circleMenu
				.getSelectedItem()).getName());
	}

	// 选择某一个圆圈时候，文本显示选择的
	@Override
	public void onItemSelected(View view, int position, long id, String name) {
		selectedTextView.setText(name);
	}

	// 点击某一个圆圈时的点击事件
	@Override
	public void onItemClick(View view, int position, long id, String name) {
		Toast.makeText(getApplicationContext(),
				getResources().getString(R.string.start_app) + " " + name,
				Toast.LENGTH_SHORT).show();
	}

}
