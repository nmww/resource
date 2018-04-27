package com.example.city;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Maindialog extends Activity {

	private CityPicker cityPicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citypicker);
		cityPicker = (CityPicker) findViewById(R.id.citypicker);
	}

}
