package com.example.picturemohu;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView imageView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  
        imageView = (ImageView) this.findViewById(R.id.imageView);
        //�����¼��ӿ�
        imageView.setOnTouchListener(new TouchListener());
    }
  
    private final class TouchListener implements OnTouchListener {
          
        /** ��¼��������Ƭģʽ���ǷŴ���С��Ƭģʽ */
        private int mode = 0;// ��ʼ״̬ 
        /** ������Ƭģʽ */
        private static final int MODE_DRAG = 1;
        /** �Ŵ���С��Ƭģʽ */
        private static final int MODE_ZOOM = 2;
          
        /** ���ڼ�¼��ʼʱ�������λ�� */
        private PointF startPoint = new PointF();
        /** ���ڼ�¼����ͼƬ�ƶ�������λ�� */
        private Matrix matrix = new Matrix();
        /** ���ڼ�¼ͼƬҪ��������ʱ�������λ�� */
        private Matrix currentMatrix = new Matrix();
      
        /** ������ָ�Ŀ�ʼ���� */
        private float startDis;
        /** ������ָ���м�� */
        private PointF midPoint;
  
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            /** ͨ�������㱣������λ MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
            // ��ָѹ����Ļ
            case MotionEvent.ACTION_DOWN:
                mode = MODE_DRAG;
                // ��¼ImageView��ǰ���ƶ�λ��
                currentMatrix.set(imageView.getImageMatrix());
                startPoint.set(event.getX(), event.getY());
                break;
            // ��ָ����Ļ���ƶ������¼��ᱻ���ϴ���
            case MotionEvent.ACTION_MOVE:
                // ����ͼƬ
                if (mode == MODE_DRAG) {
                    float dx = event.getX() - startPoint.x; // �õ�x����ƶ�����
                    float dy = event.getY() - startPoint.y; // �õ�y����ƶ�����
                    // ��û���ƶ�֮ǰ��λ���Ͻ����ƶ�
                    matrix.set(currentMatrix);
                    matrix.postTranslate(dx, dy);
                }
                // �Ŵ���СͼƬ
                else if (mode == MODE_ZOOM) {
                    float endDis = distance(event);// ��������
                    if (endDis > 10f) { // ������ָ��£��һ���ʱ�����ش���10
                        float scale = endDis / startDis;// �õ����ű���
                        matrix.set(currentMatrix);
                        matrix.postScale(scale, scale,midPoint.x,midPoint.y);
                    }
                }
                break;
            // ��ָ�뿪��Ļ
            case MotionEvent.ACTION_UP:
                // �������뿪��Ļ��������Ļ�ϻ��д���(��ָ)
            case MotionEvent.ACTION_POINTER_UP:
                mode = 0;
                break;
            // ����Ļ���Ѿ��д���(��ָ)������һ������ѹ����Ļ
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = MODE_ZOOM;
                /** ����������ָ��ľ��� */
                startDis = distance(event);
                /** ����������ָ����м�� */
                if (startDis > 10f) { // ������ָ��£��һ���ʱ�����ش���10
                    midPoint = mid(event);
                    //��¼��ǰImageView�����ű���
                    currentMatrix.set(imageView.getImageMatrix());
                }
                break;
            }
            imageView.setImageMatrix(matrix);
            return true;
        }
  
        /** ����������ָ��ľ��� */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** ʹ�ù��ɶ���������֮��ľ��� */
            return FloatMath.sqrt(dx * dx + dy * dy);
        }
  
        /** ����������ָ����м�� */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }
  
    }
}
