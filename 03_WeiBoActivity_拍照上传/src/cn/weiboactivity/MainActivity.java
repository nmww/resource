package cn.weiboactivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	String srcurl = "/sdcard/";
	String Filename = ".jpg";
	
	private static final int NONE = 0;
	private static final int PHOTO_GRAPH = 1;// ����
	private static final int PHOTO_ZOOM = 2; // ����
	private static final int PHOTO_RESOULT = 3;// ���
	private static final String IMAGE_UNSPECIFIED = "image/*";

	private ImageView image;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private EditText edit;
	private TextView text;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());*/
		
		image = (ImageView) findViewById(R.id.image);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(onClickListener);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(onClickListener);
		button3 = (Button) findViewById(R.id.button3);

		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				FileOperate.OpenOrCreateFile(Filename);
				String fileContent = FileOperate.bin2Xml(srcurl + Filename);
				WebService.upImage(fileContent, Filename);
				// Bitmap bitmap = BitmapFactory.decodeFile(srcurl);
				// image.setImageBitmap(bitmap);
			}
		});

		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
    Toast.makeText(MainActivity.this, "�뿪��GPS����...",Toast.LENGTH_SHORT).show();
					// ���ؿ���GPS�������ý���
					Intent intent = new Intent(
							Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivityForResult(intent, 0);
					return;
				}
				manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						1000, 0, locationListener);
			}
		});

	}

	private final View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == button2) { // ������ȡͼƬ
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,// ��ȡ����ͼƬ��Ϣ
						IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTO_ZOOM);

				//Bitmap bitmap = BitmapFactory.decodeFile(srcurl + Filename);
				//imageView.setImageBitmap(bitmap);
			} else if (v == button1) { // �����ջ�ȡͼƬ
				Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(intent, PHOTO_GRAPH);
			}
		}
	};

	@SuppressLint("ParserError")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		/**
		 * ������ʾ��imageview
		 */
		if (resultCode == NONE)
			return;
		// ����
		if (requestCode == PHOTO_GRAPH/*&&null!=bit*/) {
			// �����ļ�����·��
			/*File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			System.out.println("paizhao"+picture);
			startPhotoZoom(Uri.fromFile(picture));*/
			String path="/storage/sdcard/"+System.currentTimeMillis()+".png";
			Bitmap bit=(Bitmap) data.getExtras().get("data");
		    //��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ
				
				try {
					OutputStream out=new FileOutputStream(path);
					bit.compress(CompressFormat.PNG, 100, out);//��ʽ�����������·��
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
				image.setImageBitmap(bit);
			}
		}
		/**
		 * ���ѡȡͼƬ��ʾ��imageview
		 */

		if (data == null)
			return;

		// ��ȡ�������ͼƬ
		if (requestCode == PHOTO_ZOOM && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();//���ص�data.getData()�Ǹ�Uri
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			
            //��cursor��Uriָ���ͼƬ��Ϣ���ң�query��������
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			//filePathColumn����Ҫ������Щ�ֶε���˼���������Ǹ��ַ�������
			cursor.moveToFirst();//��cursor�ƶ�����һ����¼��

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		}
		// ������
		if (requestCode == PHOTO_RESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				//File fImage = new File("D:\\sdcard.img"); 
				try{
					
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
				//FileOutputStream stream = new FileOutputStream(fImage); 
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)ѹ���ļ�
				// �˴����԰�Bitmap���浽sd���У������뿴��http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
				stream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				//ByteArrayOutputStream stream = new ByteArrayOutputStream();
				image.setImageBitmap(photo); // ��ͼƬ��ʾ��ImageView�ؼ���
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
     * ����ͼƬ
     * 
     * @param uri
     * @param requestCode
     */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 400);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_RESOULT);
	}

	private LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			if (location != null) {
				text.setText("����:");
				text.append(String.valueOf(location.getLongitude()));
				text.append("\nγ��:");
				text.append(String.valueOf(location.getLatitude()));
			}

		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};
}
