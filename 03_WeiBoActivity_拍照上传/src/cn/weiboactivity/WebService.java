package cn.weiboactivity;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class WebService {


	static String mothName = "upload";

	public static boolean upImage(String fileContent, String Filename) {
		String namespace = "http://tempuri.org/";
		String URL = "http://192.168.1.115:8080/WebService1.asmx";
		SoapObject object = new SoapObject(namespace, mothName);
		object.addProperty("filename", Filename);
		object.addProperty("fileContent", fileContent);
		SoapSerializationEnvelope soap = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		soap.dotNet = true;
		soap.setOutputSoapObject(object);
		HttpTransportSE httptran = new HttpTransportSE(URL);

		try {
			httptran.call(namespace+mothName, soap);
			 Object result = soap.getResponse();
			 Log.i("connectWebService",result.toString());	
		} catch (IOException e) {

			e.printStackTrace();
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		}
       return false;
	}


}
