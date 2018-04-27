package com.beicai.wanquanzidingyi;
import com.lou.zidingyizujian.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

public class CustomProgressDialog extends ProgressDialog {
	public CustomProgressDialog(Context context) {
		super(context);
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customprogressdialog);

	}

	public static CustomProgressDialog show(Context context) {
		CustomProgressDialog dialog = new CustomProgressDialog(context,	R.style.dialog);
		dialog.show();
		return dialog;
	}

	public static CustomProgressDialog dismiss(Context context) {
		CustomProgressDialog dialog = new CustomProgressDialog(context,
				R.style.dialog);
		dialog.dismiss();
		return dialog;
	}

}