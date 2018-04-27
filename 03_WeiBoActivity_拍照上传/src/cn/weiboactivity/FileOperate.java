package cn.weiboactivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class FileOperate {
	
	private static FileInputStream fis;
	
   public static void OpenOrCreateFile(String Filename){
		// 获取扩展SD卡设备状态
		String sDStateString = android.os.Environment.getExternalStorageState();
		
		if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {
		// String strFile="downfromserive"+Math.random()+".png";
		// 获取扩展存储设备的文件目录
		File SDFile = android.os.Environment.getExternalStorageDirectory();
		
		File destDir = new File("/sdcard/");
    		File parent = destDir.getParentFile();
		// File destDir = new File(SDFile.getAbsolutePath() +	// destDirStr);
		if (!parent.exists())
			parent.mkdir();
	    // 打开文件
		File myFile = new File(destDir + File.separator + Filename);
		
		// 判断是否存在,不存在则创建
		if (!myFile.exists()) {
		try {
		myFile.createNewFile();	} catch (IOException e) {
	}
		}
		}
		}
	
	
	public static String bin2Xml(String filename){
		String fileContent=null;
		try {
			 fis = new FileInputStream(new File(filename));
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];  
			 int count = 0;   
	         while((count = fis.read(buffer)) >= 0){   
	             baos.write(buffer, 0, count);      
		       }
	        fileContent = new String(Base64.encode(baos.toByteArray()));
	        
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return fileContent;
         }

}
