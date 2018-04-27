package com.example.skin;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 第二个界面
 * 
 * @date 2014-9-28
 * @author xiaohua Deng
 */
public class Main2Activity extends BaseActivity implements OnClickListener {
	private TextView title;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
		       
        findViewById(R.id.backa).setOnClickListener(this);
        title = (TextView) findViewById(R.id.titleTv);
        title.setText("第二个界面");
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.backa:
			finish();		
			break;
		default:
			break;
		}
	}
}
