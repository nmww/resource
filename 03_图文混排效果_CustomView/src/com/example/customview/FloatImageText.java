package com.example.customview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class FloatImageText extends View{

	   private Bitmap mBitmap;  
	    private final Rect bitmapFrame = new Rect(); //�������� 
	    private final Rect tmp = new Rect();  
	    //��ȡ��Ļ�ֱ���
	    private int mTargetDentity = DisplayMetrics.DENSITY_DEFAULT;  
	  
	    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
	    private String mText;  
	    private ArrayList<TextLine> mTextLines;  
	    private final int[] textSize = new int[2];  
	  
	    public FloatImageText(Context context, AttributeSet attrs, int defStyle) {  
	        super(context, attrs, defStyle);  
	        init();  
	    }  
	  
	    public FloatImageText(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        init();  
	    }  
	  
	    public FloatImageText(Context context) {  
	        super(context);  
	        init();  
	    }  
	  
	    private void init() { 
	    	//��ȡ��Ļ�ֱ���
	        mTargetDentity = getResources().getDisplayMetrics().densityDpi;  
	        mTextLines = new ArrayList<TextLine>();  
	  
	        mPaint.setTextSize(30);//���������С  
	        mPaint.setColor(Color.RED);//����������ɫ  
	  
	    }  
	  
	    @Override  
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	        int w = 0, h = 0;  
	        // ����ͼ��Ŀ��
	        w += bitmapFrame.width();  
	        h += bitmapFrame.height();  
	  
	        // �ı����  
	        if (null != mText && mText.length() > 0) {  
	            mTextLines.clear();  
	            int size = resolveSize(Integer.MAX_VALUE, widthMeasureSpec);  
	            measureAndSplitText(mPaint, mText, size);  
	            final int textWidth = textSize[0], textHeight = textSize[1];  
	            w += textWidth; // ���ݿ��  
	            if (h < textHeight) { // ���ݸ߶�  
	                h = (int) textHeight;  
	            }  
	        }  
	  
	        w = Math.max(w, getSuggestedMinimumWidth());  
	        h = Math.max(h, getSuggestedMinimumHeight());  
	  
	        setMeasuredDimension(resolveSize(w, widthMeasureSpec),  
	                resolveSize(h, heightMeasureSpec));  
	    }  
	  
	    @Override  
	    protected void onDraw(Canvas canvas) {  
	        // ����ͼ��  
	        if (null != mBitmap) {  
	            canvas.drawBitmap(mBitmap, null, bitmapFrame, null);  
	        }  
	  
	        // �����ı�  
	        TextLine line;  
	        final int size = mTextLines.size();  
	        for (int i = 0; i < size; i++) {  
	            line = mTextLines.get(i);  
	            canvas.drawText(line.text, line.x, line.y, mPaint);  
	        }  
	        System.out.println(mTextLines);  
	    }  
	  
	    public void setImageBitmap(Bitmap bm) {  
	        setImageBitmap(bm, null);  
	    }  
	  
	    public void setImageBitmap(Bitmap bm, int left, int top) {  
	        setImageBitmap(bm, new Rect(left, top, 0, 0));  
	    }  
	  
	    public void setImageBitmap(Bitmap bm, Rect bitmapFrame) {  
	        mBitmap = bm;  
	        computeBitmapSize(bitmapFrame);  
	        requestLayout();  
	        invalidate();  
	    }  
	  
	    public void setText(String text) {  
	        mText = text;  
	        requestLayout();  
	        invalidate();  
	    }  
	  
	    private void computeBitmapSize(Rect rect) {  
	        if (null != rect) {  
	            bitmapFrame.set(rect);  
	        }  
	        if (null != mBitmap) {  
	            if (rect.right == 0 && rect.bottom == 0) {  
	                final Rect r = bitmapFrame;  
	                r.set(r.left, r.top,  
	                        r.left + mBitmap.getScaledHeight(mTargetDentity), r.top  
	                                + mBitmap.getScaledHeight(mTargetDentity));  
	            }  
	        } else {  
	            bitmapFrame.setEmpty();  
	        }  
	    }  
	  
	    private void measureAndSplitText(Paint p, String content, int maxWidth) {  
	        FontMetrics fm = mPaint.getFontMetrics();  
	        final int lineHeight = (int) (fm.bottom - fm.top);  
	  
	        final Rect r = new Rect(bitmapFrame);   
	  
	        final int length = content.length();  
	        int start = 0, end = 0, offsetX = 0, offsetY = 0;  
	        int availWidth = maxWidth;  
	        TextLine line;  
	        boolean onFirst = true;  
	        boolean newLine = true;  
	        while (start < length) {  
	            end++;  
	            if (end == length) { // ��ʣ�Ĳ���һ�е��ı�  
	                if (start <= length - 1) {  
	                    if (newLine)  
	                        offsetY += lineHeight;  
	                    line = new TextLine();  
	                    line.text = content.substring(start, end - 1);  
	                    line.x = offsetX;  
	                    line.y = offsetY;  
	                    mTextLines.add(line);  
	                }  
	                break;  
	            }  
	            p.getTextBounds(content, start, end, tmp);  
	            if (onFirst) { // ȷ��ÿ���ַ���������  
	                onFirst = false;  
	                final int height = lineHeight + offsetY;  
	                if (r.top >= height) { // �������Է���һ������  
	                    offsetX = 0;  
	                    availWidth = maxWidth;  
	                    newLine = true;  
	                } else if (newLine  
	                        && (r.bottom >= height && r.left >= tmp.width())) { // �в���߿��Է�����  
	                    offsetX = 0;  
	                    availWidth = r.left;  
	                    newLine = false;  
	                } else if (r.bottom >= height  
	                        && maxWidth - r.right >= tmp.width()) { // �в��ұ�  
	                    offsetX = r.right;  
	                    availWidth = maxWidth - r.right;  
	                    newLine = true;  
	                } else { // �ײ�  
	                    offsetX = 0;  
	                    availWidth = maxWidth;  
	                    if (offsetY < r.bottom)  
	                        offsetY = r.bottom;  
	                    newLine = true;  
	                }  
	            }  
	  
	            if (tmp.width() > availWidth) { // ����һ���ܷ��õ�����ַ���  
	                onFirst = true;  
	                line = new TextLine();  
	                line.text = content.substring(start, end - 1);  
	                line.x = offsetX;  
	                mTextLines.add(line);  
	                if (newLine) {  
	                    offsetY += lineHeight;  
	                    line.y = offsetY;  
	                } else {  
	                    line.y = offsetY + lineHeight;  
	                }  
	  
	                start = end - 1;  
	            }  
	        }  
	        textSize[1] = offsetY;  
	    }  
	  
	    class TextLine {  
	        String text;  
	        int x;  
	        int y;  
	  
	        @Override  
	        public String toString() {  
	            return "TextLine [text=" + text + ", x=" + x + ", y=" + y + "]";  
	        }  
	    } 
}
