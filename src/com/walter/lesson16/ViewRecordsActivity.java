package com.walter.lesson16;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class ViewRecordsActivity extends Activity {
	ListView list;
	CustomAdapter adapter;
	Database db;
	ArrayList<Child> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_records);
		list = (ListView) findViewById(R.id.listView1);
		db=new Database(this);
		data=db.fetch();
		adapter = new CustomAdapter(this, data);
		list.setAdapter(adapter);
	    registerForContextMenu(list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_records, menu);
		return true;
	}
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		menu.add("Delete");
		menu.add("Share");
		menu.add("Edit");
		menu.setHeaderTitle("Lost Children");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info =(AdapterContextMenuInfo)item.getMenuInfo();
		int pos= info.position;
		if(item.getTitle().equals("Delete"))
		{
			Child x= data.get(pos);
			Database db=new Database(this);
			db.delete(x.getNames());
			data.remove(pos);
			adapter.notifyDataSetChanged();
		}	
		else if(item.getTitle().equals("Edit"))
		{
			Child x= data.get(pos);
			Intent e =new Intent(this,EditActivity.class);
			e.putExtra("names", x.getNames());
			e.putExtra("age", x.getAge());
			e.putExtra("gender", x.getGender());
			e.putExtra("location", x.getLocation());
			startActivity(e);
			
		}
		return super.onContextItemSelected(item);
	}
	
	
	
	
	
	
	
	
	
}
