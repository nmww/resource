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
 * Extensions)����;�������ʼ���չ���;����趨ĳ����չ�����ļ���һ��Ӧ�ó������򿪵ķ�ʽ����
 * ��������չ���ļ������ʵ�ʱ����������Զ�ʹ��ָ��Ӧ�ó������򿪡�������ָ��һЩ�ͻ����Զ�����ļ������Լ�һЩý���ļ��򿪷�ʽ��
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
	 * ������ť
	 */
	public void onClick(View v) {
//		 sharedText();
		// ����ͼƬ
//		sharedImage();
		
		showShare();
	}

	/**
	 * ���ݷ���
	 */
	private void sharedText() {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, "������Ҫ������������ݷ���");
		sendIntent.setType("text/plain");
		// startActivity(sendIntent);
		// ���÷��������
		startActivity(Intent.createChooser(sendIntent, "��ѡ������λ��"));
	}
	/**
	 * ����ͼƬ
	 */
	private void sharedImage() {
		// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.ic_launcher);
		// // ��ͼƬתΪ������
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
		//�Զ��������ķ�ʽ���д���
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		sendIntent.setType("image/*");
		startActivity(Intent.createChooser(sendIntent, "��ѡ������λ��"));
	}
	
	 private void showShare() {
		 	//��ʼ��
	        ShareSDK.initSDK(this);
	        
	        OnekeyShare oks = new OnekeyShare();
	        //�ر�sso��Ȩ
	        oks.disableSSOWhenAuthorize();
	        
	        // ����ʱNotification��ͼ�������
	        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
	        // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
	        oks.setTitle(getString(R.string.share));
	        // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
	        oks.setTitleUrl("http://sharesdk.cn");
	        // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
	        oks.setText("���Ƿ����ı�");
	        // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
	        oks.setImagePath("/sdcard/test.jpg");
	        // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
	        oks.setUrl("http://sharesdk.cn");
	        // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
	        oks.setComment("���ǲ��������ı�");
	        // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
	        oks.setSite(getString(R.string.app_name));
	        // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
	        oks.setSiteUrl("http://sharesdk.cn");

	        // ��������GUI
	        oks.show(this);
	   }
}
