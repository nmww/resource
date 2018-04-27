package com.example.customattrexample.view;

import com.lou.zidingyizujian.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	private float dimension;
	private int color;
	
	public MyTextView(Context context) {
		this(context, null);
	}

	public MyTextView(Context context, AttributeSet attr) {
		super(context, attr);
		init(context, attr);
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.customTextView);
		dimension = a.getDimension(R.styleable.customTextView_customtextSize, 0);		
		color = a.getColor(R.styleable.customTextView_customtextColor, 0);
		a.recycle();
	}
	
	public void setTextCustom(){
		setTextColor(color);
		setTextSize(dimension);
	}

}
