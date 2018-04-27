package com.example.specialeffects;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Spinner sprAnim = (Spinner) findViewById(R.id.sprAnim);
		//读取array文件将字符添加到字符数组中
		String[] ls = getResources().getStringArray(R.array.anim_type);
		//将数组添加到字符集合中
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ls.length; i++) {
			list.add(ls[i]);
		}
		
		/*
		 * 将数据添加到下拉列表适配器中
		 */
		ArrayAdapter<String> animType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		animType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprAnim.setAdapter(animType);
		sprAnim.setSelection(0);
        /**
         * button监厛实现切换效果
         */
		Button btn = (Button) findViewById(R.id.btn_next);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainActivity.this, Animation_TargetActivity.class);
				startActivityForResult(it, 0);

				switch (sprAnim.getSelectedItemPosition()) {
					case 0://淡入淡出效果
						overridePendingTransition(R.anim.fade, R.anim.hold);
						break;
					case 1://放大淡出效果
						overridePendingTransition(R.anim.my_scale_action,
								R.anim.my_alpha_action);
						break;
					case 2://转动淡出效果1
						overridePendingTransition(R.anim.scale_rotate,
								R.anim.my_alpha_action);
						break;
					case 3://转动淡出效果2
						overridePendingTransition(R.anim.scale_translate_rotate,
								R.anim.my_alpha_action);
						break;
					case 4://左上角展开淡出效果
						overridePendingTransition(R.anim.scale_translate,
								R.anim.my_alpha_action);
						break;
					case 5://压缩变小淡出效果
						overridePendingTransition(R.anim.hyperspace_in,
								R.anim.hyperspace_out);
						break;
					case 6://压缩变小淡出效果
						overridePendingTransition(R.anim.push_left_in,
								R.anim.push_left_out);
						break;
					case 7://又往左推出效果
						overridePendingTransition(R.anim.push_up_in,
								R.anim.push_up_out);
						break;
					case 8://下往上推出效果
						overridePendingTransition(R.anim.slide_left,
								R.anim.slide_right);
						break;
					case 9://左右交错出效果
						overridePendingTransition(R.anim.wave_scale,
								R.anim.my_alpha_action);
						break;
					case 10://放大淡出出效果
						overridePendingTransition(R.anim.zoom_enter,
								R.anim.zoom_exit);
						break;
					case 11://缩小效果
						overridePendingTransition(R.anim.slide_up_in,
								R.anim.slide_down_out);
						break;
				}
			}
		});
	}
}
