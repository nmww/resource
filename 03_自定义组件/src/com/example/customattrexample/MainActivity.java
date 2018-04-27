package com.example.customattrexample;

import com.example.customattrexample.view.MyTextView;
import com.lou.zidingyizujian.R;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private MyTextView myTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myTextView = (MyTextView) findViewById(R.id.myView);
		myTextView.setTextCustom();
		myTextView.setText(123+"");
	}


}
