package com.example.circle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn;
	private MenuView menuView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initData();
		setListeners();
	}

	private void initViews() {
		btn = (Button) findViewById(R.id.btn);
		menuView = (MenuView) findViewById(R.id.menu);
	}

	private void initData() {

	}

	private void setListeners() {
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			if (menuView.isShown())
				menuView.in();
			else
				menuView.out();
			break;

		default:
			break;
		}
	}

}
