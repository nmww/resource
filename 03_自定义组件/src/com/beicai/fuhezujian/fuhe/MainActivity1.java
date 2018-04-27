package com.beicai.fuhezujian.fuhe;

import com.lou.zidingyizujian.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity1 extends Activity {   
      private EditT ib1;   
      public void onCreate(Bundle savedInstanceState) {   
             super.onCreate(savedInstanceState);   
             setContentView(R.layout.main1);   
             ib1 = (EditT) findViewById(R.id.et1);   
           
             
   
      }  
      public void test(View v){
    	  System.out.println("=======================");
    	  System.out.println(ib1.getText());
    	  Toast.makeText(this, ib1.getText(), Toast.LENGTH_LONG).show();
      }
}  
