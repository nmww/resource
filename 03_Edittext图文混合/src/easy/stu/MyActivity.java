package easy.stu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity {
	/** Called when the activity is first created. */
	Button b;
	MyEditText e;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		e = (MyEditText) findViewById(R.id.myEdit);
		
		b = (Button) findViewById(R.id.myButton);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				e.insertDrawable(R.drawable.easy);
			}
		});
	}
}