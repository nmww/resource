package com.wl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wl.util.MyProgressBar;
import com.wl.util.MyProgressDialog;

public class Dialog_TestActivity extends Activity {
	/** Called when the activity is first created. */
	Button button;
	MyProgressDialog myProgressDialog;
	MyProgressBar myProgressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) this.findViewById(R.id.button);
		myProgressDialog = new MyProgressDialog(Dialog_TestActivity.this);
		myProgressBar = new MyProgressBar(Dialog_TestActivity.this);

		// 1、 跑马进度条
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myProgressDialog.initDialog();
			}
		});
		// 普通条形进度条
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ProgressDialog pd = new ProgressDialog(Dialog_TestActivity.this);
				pd.setMax(9);// 进度条显示的最大值依赖于max
				pd.setTitle("状态切换中..");
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.show();
				// 在此启动一个线程
				Task t = new Task(pd);
				t.start();
			}
		});

		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myProgressBar.initDialog();
			}
		});
	}

	private class Task extends Thread {
		// 通过右键Run来添加run方法
		// 为了避免死循环，定义一个控制变量，使得程序不会死掉
		boolean run = true;
		ProgressDialog pd;

		Task(ProgressDialog p) {
			pd = p;
		}

		@Override
		public void run() {
			while (run) {
				try {

					pd.setProgress(pd.getProgress() + 1);
					if (pd.getProgress() >= 9) {
						pd.cancel();
						run = false;
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					run = false;// 产生异常的时候，让他停止
				}

			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Log.d("11", "onBackPressed()");

		if (myProgressDialog.isShowing()) {
			myProgressDialog.colseDialog();
		}
		if (myProgressBar.isShowing()) {
			myProgressBar.colseDialog();
		}

		super.onBackPressed();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d("11", "onResume()");
		if (myProgressDialog.isShowing()) {
			myProgressDialog.colseDialog();
		}
		if (myProgressBar.isShowing()) {
			myProgressBar.colseDialog();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.d("11", "onPause()");
		if (myProgressDialog.isShowing()) {
			myProgressDialog.colseDialog();
		}
		if (myProgressBar.isShowing()) {
			myProgressBar.colseDialog();
		}
		super.onPause();
	}
}