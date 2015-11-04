package com.walter.lesson16;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditActivity extends Activity {
EditText edt1,edt2,edt3;
RadioButton rbMale,rbFemale;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		edt1=(EditText) findViewById(R.id.editText1);
		edt2=(EditText) findViewById(R.id.editText2);
		edt3=(EditText) findViewById(R.id.editText3);
		String names=getIntent().getExtras().getString("names");
		String age=getIntent().getExtras().getString("age");
		String gender=getIntent().getExtras().getString("gender");
		String location=getIntent().getExtras().getString("location");
		edt1.setText(names);
		edt2.setText(location);
		edt3.setText(age);
		if(gender.equals("Female"))
		{
			 rbFemale.setSelected(true);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
