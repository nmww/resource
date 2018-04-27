/**
 * SuccessLaunchActivity.java [V 1..0.0]
 * classes : com.zhangyx.MyLauncherGuide.SuccessLaunchActivity
 * zhangyx Create at 2015-1-21 下午1:58:49
 */
package com.zhangyx.MyLauncherGuide;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyLauncherGuide.utils.AnimationUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 引导成功界面
 *com.zhangyx.MyLauncherGuide.SuccessLaunchActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-21 下午1:58:49
 */
public class SuccessLaunchActivity extends Activity{

	@ViewInject(R.id.btnBack)
	private Button btnBack;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_successlaunch);
		ViewUtils.inject(this);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SuccessLaunchActivity.this,MainActivity.class));
				AnimationUtil.finishActivityAnimation(SuccessLaunchActivity.this);
			}
		});
	}

}
