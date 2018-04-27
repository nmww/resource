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
	
	//获得code的实例
	private static Code bpUtil;
	private Code(){};
	public static Code getInstance() {
		if(bpUtil == null)
			bpUtil = new Code();
		return bpUtil;
	}
	//default settings
	private static final int DEFAULT_CODE_LENGTH = 4;//验证码的长度  这里是4位
	private static final int DEFAULT_FONT_SIZE = 40;//字体大小
	private static final int DEFAULT_LINE_NUMBER = 3;//多少条干扰线
	private static final int BASE_PADDING_LEFT = 20; //左边距
	private static final int RANGE_PADDING_LEFT = 35;//左边距范围值  
	private static final int BASE_PADDING_TOP = 42;//上边距
	private static final int RANGE_PADDING_TOP = 15;//上边距范围值
	private static final int DEFAULT_WIDTH = 260;//默认宽度.图片的总宽
	private static final int DEFAULT_HEIGHT = 70;//默认高度.图片的总高
	private  final int DEFAULT_COLOR=0xdf;//默认背景颜色值
	
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
	private String code;//保存生成的验证码
	private int padding_left, padding_top;
	private Random random = new Random();//即随机数发生器
	
	/*
	 * 验证码图片  
	 */
	/*ALPHA_8 ：Each pixel is stored as a single translucency (alpha) channel.
	 *  （原图的每一个像素以半透明显示）

	ARGB_4444 ：This field was deprecated in API level 13. Because of the poor quality of this configuration, it is advised to use ARGB_8888 instead. 
	 （在API13以后就被弃用了，建议使用8888）。

	ARGB_8888 ：Each pixel is stored on 4 bytes. Each channel (RGB and alpha for translucency) is stored with 8 bits of precision (256 possible values.) This configuration is very flexible and offers the best quality. It should be used whenever possible.
	（每个像素占4个字节，每个颜色8位元，反正很清晰，看着很舒服）        

	RGB_565 ：Each pixel is stored on 2 bytes and only the RGB channels are encoded: red is stored with 5 bits of precision (32 possible values), green is stored with 6 bits of precision (64 possible values) and blue is stored with 5 bits of precision. 
	（这个应该很容易理解了）

	图片色彩越清晰所占用的内存空间也越大*/
	private Bitmap createBitmap() {
		padding_left = 0;
		
		//用它可以获取图像文件信息,进行图像剪切、旋转、缩放等操作,并可以指定格式保存图像文件。
		Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		/*画布  Canvas(Bitmap bitmap): 
               以bitmap对象创建一个画布，则将内容都绘制在bitmap上，因此bitmap不得为null。*/
		Canvas c = new Canvas(bp);

		//保存生成的验证码
		code = createCode();
		//默认红绿蓝
		c.drawColor(Color.rgb(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR));
		//paint：画笔，画图工具，管理颜色，样式，字体
		Paint paint = new Paint();
		paint.setTextSize(font_size);
		
		//限定字符的个数
		for (int i = 0; i < code.length(); i++) {
			randomTextStyle(paint);//画布上的字体
			randomPadding();
			//将字体逐个写到画布上，并且设定边距
			c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
		}
        //设定干扰线的条数
		for (int i = 0; i < line_number; i++) {
			drawLine(c, paint);
		}
		
		c.save( Canvas.ALL_SAVE_FLAG );//保存（将画布上所有的东西进行保存） 
		/**
		 * //drawText()里的save()和restore()不可以省去，
		 * context.save()的时候保存了context当前的状态值，
		 * 然后你执行了一些值的修改，比如改变阴影大小，
		 * 在函数最后调用context.restore()方法将context之前的状态值还原回去。
         * 注释掉save和restore后，这时调用drawText函数时context的画笔状态就不会保存与还原
		 */
		c.restore();
		return bp;
	}
	/*
	 * 把字符中的所有字母全部转换为小写
	 */
	public String getCode() {
		return code.toLowerCase();
	}
	
	public Bitmap getBitmap(){
		return createBitmap();
	}
	/*
	 *  随机产生、创建验证码  
	 */
	private String createCode() {
		StringBuilder buffer = new StringBuilder();//此类表示值为可变字符序列的类似字符串的对象
		for (int i = 0; i < codeLength; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);//随机产生一个大于等于0，小于101的整形数。
		}
		return buffer.toString();
	}
	/*
	 * 用画布和画笔画干扰线
	 */
	private void drawLine(Canvas canvas, Paint paint) {
		int color = randomColor();
		int startX = random.nextInt(width);
		int startY = random.nextInt(height);
		int stopX = random.nextInt(width);
		int stopY = random.nextInt(height);
		paint.setStrokeWidth(1);//设置线宽
		paint.setColor(color);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}
	
	//返回一个随机颜色
	private int randomColor() {
		return randomColor(1);
	}

	/*
	 * 颜色
	 */
	private int randomColor(int rate) {
		//低字节就是2的8次方256，2是因为每个位须要经过1和0两次变化；8就是低8位，每位变化2次后向上进一位，最后等于变化256次。
		int red = random.nextInt(256) / rate;
		int green = random.nextInt(256) / rate;
		int blue = random.nextInt(256) / rate;
		return Color.rgb(red, green, blue);
	}
	/*
	 * 字体
	 */
	private void randomTextStyle(Paint paint) {
		int color = randomColor();
		paint.setColor(color);
		paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
		float skewX = random.nextInt(11) / 10;
		skewX = random.nextBoolean() ? skewX : -skewX;
		paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
//		paint.setUnderlineText(true); //true为下划线，false为非下划线
//		paint.setStrikeThruText(true); //true为删除线，false为非删除线
	}
	/*
	 * 边距(随机)
	 */
	private void randomPadding() {
		padding_left += base_padding_left + random.nextInt(range_padding_left);
		padding_top = base_padding_top + random.nextInt(range_padding_top);
	}
}
