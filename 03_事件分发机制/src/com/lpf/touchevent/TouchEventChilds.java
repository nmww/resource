package com.lpf.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventChilds extends LinearLayout {
    private int i = 9;
    public TouchEventChilds(Context context) {
        super(context);
    }

    public TouchEventChilds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("touch", "childTouchEventChilds | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("touch", "childTouchEventChilds | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("touch", "childTouchEventChilds | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return true;
    }

}