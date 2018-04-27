package com.beicai.juhedatedemo.activity;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkland.juheapi.common.CommonFun;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.exp.ExpData;

public class MainActivity extends Activity {

	KuaiDi kuaiDi;
	List<KuaiDiList> mKuaiDiList;
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//����ĳ�ʼ��
		CommonFun.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		listview=(ListView) this.findViewById(R.id.klistview);
		getDate();
	}
   /**
    * �õ����������  ������������
    */
	private void getDate(){
		// ���ÿ�ݲ�ѯ  
        ExpData expData = ExpData.getInstance(); 
        //��ݵĹ�˾����   ��ݺ�
        expData.search("˳��", "575677355677", new JsonCallBack() {  
			@Override
			public void jsonLoaded(JSONObject arg0) {
				 // TODO Auto-generated method stub  
                try {  
                	int code = arg0.getInt("resultcode");  
                    if (code == 200) {  
                        JSONObject resultJson = arg0.getJSONObject("result");  
                        System.out.println("��������---��"+resultJson.toString());
                        Gson gson = new Gson();
    					kuaiDi = gson.fromJson(resultJson.toString(),new TypeToken<KuaiDi>() {}.getType());
    					System.out.println("------=>"+kuaiDi.getCompany());
    					
    					mKuaiDiList = kuaiDi.getList();
    				
                        KuaiDiAdapter mKuaiDiAdapter=new KuaiDiAdapter(MainActivity.this,mKuaiDiList);
                        listview.setAdapter(mKuaiDiAdapter);
                    }  
                } catch (JSONException e) {  
                    e.printStackTrace();  
                }  
			}  
        });  
	}
}
