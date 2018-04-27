package com.example.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button;
	Custom custom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {
		button = (Button) this.findViewById(R.id.button1);
		 custom = (Custom) this.findViewById(R.id.custom);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				custom.updateTime();
			}
		});
	}
}
