package com.lpf.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class TouchEventActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w("touch", "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);//如果返回true则消费
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.w("touch", "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }

}