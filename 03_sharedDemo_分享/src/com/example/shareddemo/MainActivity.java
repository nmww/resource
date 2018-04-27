package com.example.shareddemo;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 
 * MIME(Multipurpose Internet Mail
 * Extensions)多用途互联网邮件扩展类型就是设定某种扩展名的文件用一种应用程序来打开的方式类型
 * ，当该扩展名文件被访问的时候，浏览器会自动使用指定应用程序来打开。多用于指定一些客户端自定义的文件名，以及一些媒体文件打开方式。
 * 
 * @author dagang
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button bt_shared;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_shared = (Button) findViewById(R.id.bt_shared);

		bt_shared.setOnClickListener(this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 单击按钮
	 */
	public void onClick(View v) {
//		 sharedText();
		// 分享图片
//		sharedImage();
		
		showShare();
	}

	/**
	 * 内容分享
	 */
	private void sharedText() {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, "今天我要分享的内容数据分享");
		sendIntent.setType("text/plain");
		// startActivity(sendIntent);
		// 设置分享的内容
		startActivity(Intent.createChooser(sendIntent, "请选择分享的位置"));
	}
	/**
	 * 分享图片
	 */
	private void sharedImage() {
		// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.ic_launcher);
		// // 将图片转为二进制
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// bitmap.compress(Bitmap.CompressFormat.JPEG, 10, out);
		// try {
		// out.flush();
		// out.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// Toast.makeText(this,new String(out.toByteArray()),
		// Toast.LENGTH_SHORT).show();

		File file = new File("/mnt/sdcard/test.jpeg");

		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		//以二进制流的方式进行传递
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		sendIntent.setType("image/*");
		startActivity(Intent.createChooser(sendIntent, "请选择分享的位置"));
	}
	
	 private void showShare() {
		 	//初始化
	        ShareSDK.initSDK(this);
	        
	        OnekeyShare oks = new OnekeyShare();
	        //关闭sso授权
	        oks.disableSSOWhenAuthorize();
	        
	        // 分享时Notification的图标和文字
	        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
	        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
	        oks.setTitle(getString(R.string.share));
	        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
	        oks.setTitleUrl("http://sharesdk.cn");
	        // text是分享文本，所有平台都需要这个字段
	        oks.setText("我是分享文本");
	        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
	        oks.setImagePath("/sdcard/test.jpg");
	        // url仅在微信（包括好友和朋友圈）中使用
	        oks.setUrl("http://sharesdk.cn");
	        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
	        oks.setComment("我是测试评论文本");
	        // site是分享此内容的网站名称，仅在QQ空间使用
	        oks.setSite(getString(R.string.app_name));
	        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
	        oks.setSiteUrl("http://sharesdk.cn");

	        // 启动分享GUI
	        oks.show(this);
	   }
}
