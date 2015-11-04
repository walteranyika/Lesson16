package com.walter.lesson16;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText edtNames,edtLocation,edtAge;
RadioButton radioMale;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edtNames=(EditText) findViewById(R.id.editTextNames);
		edtLocation=(EditText) findViewById(R.id.editTextLocation);
		edtAge=(EditText) findViewById(R.id.editTextAge);
		radioMale=(RadioButton) findViewById(R.id.radioMale);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void save(View v)
	{
		String names=edtNames.getText().toString();
		String location=edtLocation.getText().toString();
		String age= edtAge.getText().toString();
		String gender =radioMale.isSelected()?"Male":"Female";
		if(!names.equals("") && !location.equals("") && !age.equals(""))
		{
			Child d =new Child(names, location, age, gender);
			Database db=new Database(this);
			db.save(d);
			edtAge.setText("");
			edtLocation.setText("");
			edtNames.setText("");
			Toast.makeText(this, db.countChildren()+" Children",Toast.LENGTH_SHORT).show();
		    ArrayList<Child> all = db.fetch();
		    for (Child child :	 all)
		    {
				Log.d("NAMES", child.getNames());
			}
		}else
		{			
			Toast.makeText(this,"Enter All Info",Toast.LENGTH_SHORT).show();			
		}
		
	}
    public void lost(View v)
    {
    	startActivity(new Intent (this,
    			  ViewRecordsActivity.class));
    }
    public void fetch(View v)
    {
    	Database db=new Database(this);
    	Child x = db.fetchOneChild("tom");
    	if(x!=null)
    	{
    	  Toast.makeText(this, x.getLocation()+" "+x.getAge()+" "+x.getNames()+" "+x.getGender(), Toast.LENGTH_SHORT).show();	
    	}
    	else
    	{    		
      	  Toast.makeText(this,"No child found",Toast.LENGTH_SHORT).show();   		
    	}
    }
    public void edit(View v)
    {
    	Database db=new Database(this);
    	ContentValues values=new ContentValues();
    	values.put("names", "Tom Jane");
    	values.put("age", "15");
    	db.edit(values, "1");
    	startActivity(new Intent(this,ViewRecordsActivity.class));    	
    }    
    public void delete(View v)
    {
    	Database db=new Database(this);
    }      
    
    
    
    

}
