package com.spinner.litz;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class SpinnerTestActivity extends Activity {
	public static ArrayList<String> list = new ArrayList<String>();
	
	private CustomerSpinner spinner;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
        
        spinner = (CustomerSpinner)findViewById(R.id.spinner);
        spinner.setList(list);
    }     
    public void init(){          
    	list.add("路飞");
    	list.add("索隆");
    	list.add("山治");
    	list.add("乔巴");
    	list.add("罗宾");
    	list.add("娜美");
    	list.add("乌索普");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK){
    		list.clear();
    	}
    	return super.onKeyDown(keyCode, event);
    }
}