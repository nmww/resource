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

		// 1�� ���������
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myProgressDialog.initDialog();
			}
		});
		// ��ͨ���ν�����
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ProgressDialog pd = new ProgressDialog(Dialog_TestActivity.this);
				pd.setMax(9);// ��������ʾ�����ֵ������max
				pd.setTitle("״̬�л���..");
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.show();
				// �ڴ�����һ���߳�
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
		// ͨ���Ҽ�Run�����run����
		// Ϊ�˱�����ѭ��������һ�����Ʊ�����ʹ�ó��򲻻�����
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
					run = false;// �����쳣��ʱ������ֹͣ
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