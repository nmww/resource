package com.example.customattrexample.view;

import com.lou.zidingyizujian.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextView1 extends TextView{
	private float dimension;
	private int color;
	public TextView1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TextView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		loadViewAttr(context,attrs);
	}

	public TextView1(Context context) {
		super(context,null);
	}
    public void loadViewAttr(Context context,AttributeSet attrs){
    	TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.textview1);
    	dimension = array.getDimension(R.styleable.textview1_size, 0);
    	color = array.getColor(R.styleable.textview1_color, 0);
    	array.recycle();
        setAttr();
    }
	 public void setAttr(){
		 this.setTextColor(color);
		 this.setTextSize(dimension);
	 }

}
