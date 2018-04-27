package com.example.slideviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class SlideView extends LinearLayout {

	private static final String TAG = "SlideView";

	private Context mContext;
	private LinearLayout mViewContent;
	private RelativeLayout mHolder;
	private Scroller mScroller;
	private OnSlideListener mOnSlideListener;

	private int mHolderWidth = 120;

	private int mLastX = 0;
	private int mLastY = 0;
	private static final int TAN = 2;

	public interface OnSlideListener {
		public static final int SLIDE_STATUS_OFF = 0;
		public static final int SLIDE_STATUS_START_SCROLL = 1;
		public static final int SLIDE_STATUS_ON = 2;

		/**
		 * @param view
		 *            current SlideView
		 * @param status
		 *            SLIDE_STATUS_ON or SLIDE_STATUS_OFF
		 */
		public void onSlide(View view, int status);
	}

	public SlideView(Context context) {
		super(context);
		initView();
	}

	public SlideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	//加载初始信息（布局）
	private void initView() {
		mContext = getContext();
		mScroller = new Scroller(mContext);

		setOrientation(LinearLayout.HORIZONTAL);// 方向
		// 加载滑动出来的布局
		View.inflate(mContext, R.layout.privatelisting_delete_merge, this);
		mViewContent = (LinearLayout) findViewById(R.id.view_content);
		//删除的优化：
		mHolderWidth = Math.round(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, mHolderWidth, getResources()
						.getDisplayMetrics()));
	}

	// 删除的控件
	public void setButtonText(CharSequence text) {
		((TextView) findViewById(R.id.delete)).setText(text);
	}

	// 在linelayout中加载布局
	public void setContentView(View view) {
		mViewContent.addView(view);
	}

	//将上述接口中的标志穿的方法中
	public void setOnSlideListener(OnSlideListener onSlideListener) {
		mOnSlideListener = onSlideListener;
	}

	//记录初始值
	public void shrink() {
		if (getScrollX() != 0) {
			this.smoothScrollTo(0, 0);// 默认的初始滑动位置
		}
	}
    //开始事件相应的处理
	public void onRequireTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		int scrollX = getScrollX();// 滑动位置的x坐标值
		Log.d(TAG, "x=" + x + "  y=" + y);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {//按下
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();// 滑动随时出现的位置
			}
			if (mOnSlideListener != null) {
				mOnSlideListener.onSlide(this,
						OnSlideListener.SLIDE_STATUS_START_SCROLL);
			}
			break;
		}
		case MotionEvent.ACTION_MOVE: {//移动
			int deltaX = x - mLastX;
			int deltaY = y - mLastY;
			if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {
				break;
			}

			int newScrollX = scrollX - deltaX;
			if (deltaX != 0) {
				if (newScrollX < 0) {
					newScrollX = 0;
				} else if (newScrollX > mHolderWidth) {
					newScrollX = mHolderWidth;
				}
				this.scrollTo(newScrollX, 0);
			}
			break;
		}
		case MotionEvent.ACTION_UP: {//抬起
			int newScrollX = 0;
			if (scrollX - mHolderWidth * 0.75 > 0) {// 3/4的位置
				newScrollX = mHolderWidth;
			}
			this.smoothScrollTo(newScrollX, 0);
			if (mOnSlideListener != null) {
				mOnSlideListener.onSlide(this,// 三项运算符
						newScrollX == 0 ? OnSlideListener.SLIDE_STATUS_OFF
								: OnSlideListener.SLIDE_STATUS_ON);
			}
			break;
		}
		default:
			break;
		}

		mLastX = x;
		mLastY = y;
	}

	// 缓慢滚动到指定位置
	private void smoothScrollTo(int destX, int destY) {
		int scrollX = getScrollX();
		int delta = destX - scrollX;
		mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 3);
		invalidate();
	}

	//判断是否滑动完成
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {// 完成返回false 否则返回true
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

}
