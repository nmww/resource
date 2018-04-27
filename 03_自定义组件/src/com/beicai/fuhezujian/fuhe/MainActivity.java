package com.beicai.fuhezujian.fuhe;

import com.lou.zidingyizujian.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {   
      private ImageBt ib1;   
      private ImageBt ib2;   
      public void onCreate(Bundle savedInstanceState) {   
             super.onCreate(savedInstanceState);   
             setContentView(R.layout.main);   
             ib1 = (ImageBt) findViewById(R.id.buok);   
             ib2 = (ImageBt) findViewById(R.id.bucancel);   
             ib1.setTextViewText("确定");   
             ib1.setImageResource(R.drawable.ok);   
             ib2.setTextViewText("取消");   
             ib2.setImageResource(R.drawable.error);   
             ib1.setOnClickListener(new View.OnClickListener() {   
                 public void onClick(View v) {   
                              //在这里可以实现点击事件   
                	 System.out.println("点击了确定按钮");
                 }   
             }); 
             
   
      }   
      public void test(View v){
    	  System.out.println("点击了取消按钮");
      }
}  
