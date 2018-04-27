package com.slidingmenu.lib.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.slidingmenu.lib.R;
/**
 * 测试的Fragment页面内容
 * 
 * @author a
 *
 */
public class DawableFragment extends Fragment {
	
	private int drawableId = -1;
	
	public DawableFragment() { 
		this(R.color.white);
	}
	
	public DawableFragment(int drawableId) {
		this.drawableId = drawableId;
		//是否保留Fragment的实例，保留实例将不会调用OnDestroy方法
		//setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (savedInstanceState != null)
			drawableId = savedInstanceState.getInt("drawableId");
		LinearLayout linearLayout = new LinearLayout(getActivity());
		linearLayout.setBackgroundResource(drawableId);
		
		final MainActivity activity=(MainActivity)getActivity();
		Button btn = new Button(inflater.getContext());
		btn.setText("左面抽屉");
		btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                activity.showLeft();
            }
        });
	      Button right = new Button(inflater.getContext());
	      right.setText("右面抽屉");
	      right.setOnClickListener(new OnClickListener() {
	            
	            @Override
	            public void onClick(View v) {
	                activity.showRight();
	            }
	        });
	      linearLayout.addView(btn);
	      linearLayout.addView(right);
		return linearLayout;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("drawableId", drawableId);
	}
	
}
