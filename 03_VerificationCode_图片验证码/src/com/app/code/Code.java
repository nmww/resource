package com.app.code;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class Code {

	private static final char[] CHARS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	
	//���code��ʵ��
	private static Code bpUtil;
	private Code(){};
	public static Code getInstance() {
		if(bpUtil == null)
			bpUtil = new Code();
		return bpUtil;
	}
	//default settings
	private static final int DEFAULT_CODE_LENGTH = 4;//��֤��ĳ���  ������4λ
	private static final int DEFAULT_FONT_SIZE = 40;//�����С
	private static final int DEFAULT_LINE_NUMBER = 3;//������������
	private static final int BASE_PADDING_LEFT = 20; //��߾�
	private static final int RANGE_PADDING_LEFT = 35;//��߾෶Χֵ  
	private static final int BASE_PADDING_TOP = 42;//�ϱ߾�
	private static final int RANGE_PADDING_TOP = 15;//�ϱ߾෶Χֵ
	private static final int DEFAULT_WIDTH = 260;//Ĭ�Ͽ��.ͼƬ���ܿ�
	private static final int DEFAULT_HEIGHT = 70;//Ĭ�ϸ߶�.ͼƬ���ܸ�
	private  final int DEFAULT_COLOR=0xdf;//Ĭ�ϱ�����ɫֵ
	
	//settings decided by the layout xml
	//canvas width and height
	private int width = DEFAULT_WIDTH;
	private int height = DEFAULT_HEIGHT; 
	
	//random word space and pading_top
	private int base_padding_left = BASE_PADDING_LEFT;
	private int range_padding_left = RANGE_PADDING_LEFT;
	private int base_padding_top = BASE_PADDING_TOP;
	private int range_padding_top = RANGE_PADDING_TOP;
	
	//number of chars, lines; font size
	private int codeLength = DEFAULT_CODE_LENGTH;
	private int line_number = DEFAULT_LINE_NUMBER;
	private int font_size = DEFAULT_FONT_SIZE;
	
	//variables
	private String code;//�������ɵ���֤��
	private int padding_left, padding_top;
	private Random random = new Random();//�������������
	
	/*
	 * ��֤��ͼƬ  
	 */
	/*ALPHA_8 ��Each pixel is stored as a single translucency (alpha) channel.
	 *  ��ԭͼ��ÿһ�������԰�͸����ʾ��

	ARGB_4444 ��This field was deprecated in API level 13. Because of the poor quality of this configuration, it is advised to use ARGB_8888 instead. 
	 ����API13�Ժ�ͱ������ˣ�����ʹ��8888����

	ARGB_8888 ��Each pixel is stored on 4 bytes. Each channel (RGB and alpha for translucency) is stored with 8 bits of precision (256 possible values.) This configuration is very flexible and offers the best quality. It should be used whenever possible.
	��ÿ������ռ4���ֽڣ�ÿ����ɫ8λԪ�����������������ź������        

	RGB_565 ��Each pixel is stored on 2 bytes and only the RGB channels are encoded: red is stored with 5 bits of precision (32 possible values), green is stored with 6 bits of precision (64 possible values) and blue is stored with 5 bits of precision. 
	�����Ӧ�ú���������ˣ�

	ͼƬɫ��Խ������ռ�õ��ڴ�ռ�ҲԽ��*/
	private Bitmap createBitmap() {
		padding_left = 0;
		
		//�������Ի�ȡͼ���ļ���Ϣ,����ͼ����С���ת�����ŵȲ���,������ָ����ʽ����ͼ���ļ���
		Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		/*����  Canvas(Bitmap bitmap): 
               ��bitmap���󴴽�һ�������������ݶ�������bitmap�ϣ����bitmap����Ϊnull��*/
		Canvas c = new Canvas(bp);

		//�������ɵ���֤��
		code = createCode();
		//Ĭ�Ϻ�����
		c.drawColor(Color.rgb(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR));
		//paint�����ʣ���ͼ���ߣ�������ɫ����ʽ������
		Paint paint = new Paint();
		paint.setTextSize(font_size);
		
		//�޶��ַ��ĸ���
		for (int i = 0; i < code.length(); i++) {
			randomTextStyle(paint);//�����ϵ�����
			randomPadding();
			//���������д�������ϣ������趨�߾�
			c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
		}
        //�趨�����ߵ�����
		for (int i = 0; i < line_number; i++) {
			drawLine(c, paint);
		}
		
		c.save( Canvas.ALL_SAVE_FLAG );//���棨�����������еĶ������б��棩 
		/**
		 * //drawText()���save()��restore()������ʡȥ��
		 * context.save()��ʱ�򱣴���context��ǰ��״ֵ̬��
		 * Ȼ����ִ����һЩֵ���޸ģ�����ı���Ӱ��С��
		 * �ں���������context.restore()������context֮ǰ��״ֵ̬��ԭ��ȥ��
         * ע�͵�save��restore����ʱ����drawText����ʱcontext�Ļ���״̬�Ͳ��ᱣ���뻹ԭ
		 */
		c.restore();
		return bp;
	}
	/*
	 * ���ַ��е�������ĸȫ��ת��ΪСд
	 */
	public String getCode() {
		return code.toLowerCase();
	}
	
	public Bitmap getBitmap(){
		return createBitmap();
	}
	/*
	 *  ���������������֤��  
	 */
	private String createCode() {
		StringBuilder buffer = new StringBuilder();//�����ʾֵΪ�ɱ��ַ����е������ַ����Ķ���
		for (int i = 0; i < codeLength; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);//�������һ�����ڵ���0��С��101����������
		}
		return buffer.toString();
	}
	/*
	 * �û����ͻ��ʻ�������
	 */
	private void drawLine(Canvas canvas, Paint paint) {
		int color = randomColor();
		int startX = random.nextInt(width);
		int startY = random.nextInt(height);
		int stopX = random.nextInt(width);
		int stopY = random.nextInt(height);
		paint.setStrokeWidth(1);//�����߿�
		paint.setColor(color);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}
	
	//����һ�������ɫ
	private int randomColor() {
		return randomColor(1);
	}

	/*
	 * ��ɫ
	 */
	private int randomColor(int rate) {
		//���ֽھ���2��8�η�256��2����Ϊÿ��λ��Ҫ����1��0���α仯��8���ǵ�8λ��ÿλ�仯2�κ����Ͻ�һλ�������ڱ仯256�Ρ�
		int red = random.nextInt(256) / rate;
		int green = random.nextInt(256) / rate;
		int blue = random.nextInt(256) / rate;
		return Color.rgb(red, green, blue);
	}
	/*
	 * ����
	 */
	private void randomTextStyle(Paint paint) {
		int color = randomColor();
		paint.setColor(color);
		paint.setFakeBoldText(random.nextBoolean());  //trueΪ���壬falseΪ�Ǵ���
		float skewX = random.nextInt(11) / 10;
		skewX = random.nextBoolean() ? skewX : -skewX;
		paint.setTextSkewX(skewX); //float���Ͳ�����������ʾ��б��������б
//		paint.setUnderlineText(true); //trueΪ�»��ߣ�falseΪ���»���
//		paint.setStrikeThruText(true); //trueΪɾ���ߣ�falseΪ��ɾ����
	}
	/*
	 * �߾�(���)
	 */
	private void randomPadding() {
		padding_left += base_padding_left + random.nextInt(range_padding_left);
		padding_top = base_padding_top + random.nextInt(range_padding_top);
	}
}
