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
	//必须的初始化
		CommonFun.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		listview=(ListView) this.findViewById(R.id.klistview);
		getDate();
	}
   /**
    * 得到请求的数据  传入两个参数
    */
	private void getDate(){
		// 常用快递查询  
        ExpData expData = ExpData.getInstance(); 
        //快递的公司名字   快递号
        expData.search("顺丰", "575677355677", new JsonCallBack() {  
			@Override
			public void jsonLoaded(JSONObject arg0) {
				 // TODO Auto-generated method stub  
                try {  
                	int code = arg0.getInt("resultcode");  
                    if (code == 200) {  
                        JSONObject resultJson = arg0.getJSONObject("result");  
                        System.out.println("返回数据---》"+resultJson.toString());
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
