package com.example.viewstretchanimation;


/**
 * StretchAnimationֻ����viewˮƽ������ߴ�ֱ����
 */
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class StretchAnimation {

	 private final static String TAG = "SizeChange";  
	    private Interpolator mInterpolator; // �ö����Ϸ���Ϊ��ֵ��  
	    private View mView;    // ��Ҫ������view  
	    private int mCurrSize; //��ǰ��С  
	    private int mRawSize;  
	    private int mMinSize; // ��С��С �̶�ֵ  
	    private int mMaxSize; // ����С �̶�ֵ  
	    private boolean isFinished = true;// ����������ʶ  
	    private TYPE mType = TYPE.vertical;  
	    private final static int FRAMTIME = 20;// һ֡��ʱ�� ����  
	    public static enum TYPE {  
	        horizontal, // �ı�viewˮƽ����Ĵ�С  
	        vertical    // �ı�view��ֱ����Ĵ�С  
	    }  
	  
	    private int mDuration;  // �������е�ʱ��  
	    private long mStartTime;// ������ʼʱ��  
	    private float mDurationReciprocal;   
	    private int mDSize; // ��Ҫ�ı�view��С������  
	  
	    public StretchAnimation(int maxSize, int minSize, TYPE type, int duration) {  
	        if (minSize >= maxSize) {  
	            throw new RuntimeException("View�����ı�ֵ����С����С�ı�ֵ");  
	        }  
	        mMinSize = minSize;  
	        mMaxSize = maxSize;  
	        mType = type;  
	        mDuration = duration;  
	    }  
	  
	    public void setInterpolator(Interpolator interpolator) {  
	        mInterpolator = interpolator;  
	    }  
	  
	    public TYPE getmType() {  
	        return mType;  
	    }  
	  
	    public boolean isFinished() {  
	        return isFinished;  
	    }  
	  
	    public void setDuration(int duration) {  
	        mDuration = duration;  
	    }  
	  
	    //�ı�view��С
	    private void changeViewSize() {  
	  
	        if (mView != null && mView.getVisibility() != View.GONE) {  
	            LayoutParams params = mView.getLayoutParams();  
	            if (mType == TYPE.vertical) {  
	                params.height = mCurrSize;  
	            } else if (mType == TYPE.horizontal) {  
	                params.width = mCurrSize;  
	            }  
	            Log.i(TAG, "CurrSize = " + mCurrSize + " Max=" + mMaxSize + " min="  
	                    + mMinSize);  
	            mView.setLayoutParams(params);  
	        }  
	    }  
	  
	 // ������handler���½��� 
	    private Handler mHandler = new Handler() {  
	  
	        @Override  
	        public void handleMessage(Message msg) {  
	  
	            if (msg.what == 1) {  
	                if (!computeViewSize()) {  
	  
	                    mHandler.sendEmptyMessageDelayed(1, FRAMTIME);  
	                } else {  
	                    if (animationlistener != null) {  
	                        animationlistener.animationEnd(mView);  
	                    }  
	                }  
	            }  
	            super.handleMessage(msg);  
	        }  
	  
	    };  
	  
	    /** 
	     * @return ����true ��ʾ������� 
	     */  
	    private boolean computeViewSize() {  
	  
	        if (isFinished) {  
	            return isFinished;  
	        }  
	        int timePassed = (int) (AnimationUtils.currentAnimationTimeMillis() - mStartTime);  
	  
	        if (timePassed <= mDuration) {  
	            float x = timePassed * mDurationReciprocal;  
	            if (mInterpolator != null) {  
	            	//getInterpolation (float input) ����������Դ��붯������ʱ��ֵ(input��Χ [0��1] )��0����ʼ��1����
	            	//��������ȡ�仯����
	                x = mInterpolator.getInterpolation(x);  
	            }  
	            mCurrSize = mRawSize + Math.round(x * mDSize);  
	        } else {  
	  
	            isFinished = true;  
	            mCurrSize = mRawSize + mDSize;  
	  
	        }  
	        changeViewSize();  
	        return isFinished;  
	    }  
	  
	    //��ʼ��������
	    public void startAnimation(View view) {  
	  
	        if (view != null) {  
	            mView = view;  
	        } else {  
	            Log.e(TAG, "view ����Ϊ��");  
	            return;  
	        }  
	        LayoutParams params = mView.getLayoutParams();  
	  
	        if (isFinished) {  
	            mDurationReciprocal = 1.0f / (float) mDuration;  
	            if (mType == TYPE.vertical) {  
	                mRawSize = mCurrSize = mView.getHeight();  
	            } else if (mType == TYPE.horizontal) {  
	                mRawSize = mCurrSize = mView.getWidth();  
	            }  
	            Log.i(TAG, "mRawSize=" + mRawSize);  
	            if (mCurrSize > mMaxSize || mCurrSize < mMinSize) {  
	                throw new RuntimeException(  
	                        "View �Ĵ�С����� currentViewSize > mMaxSize || currentViewSize < mMinSize");  
	            }  
	            isFinished = false;  
	            // ������ʼʱ��
	            mStartTime = AnimationUtils.currentAnimationTimeMillis();  
	            if (mCurrSize < mMaxSize) {  
	                mDSize = mMaxSize - mCurrSize;  
	            } else {  
	                mDSize = mMinSize - mMaxSize;  
	            }  
	            Log.i(TAG, "mDSize=" + mDSize);  
	            mHandler.sendEmptyMessage(1);  
	        }  
	    }  
	  
	    private AnimationListener animationlistener;  
	  
	    interface AnimationListener {  
	        public void animationEnd(View v);  
	    }  
	  
	    public void setOnAnimationListener(AnimationListener listener) {  
	        animationlistener = listener;  
	    }  
}
