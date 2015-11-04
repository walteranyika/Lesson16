package com.walter.lesson16;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public Database(Context context) {
		super(context, "lost_kids", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String children_table =
				    "CREATE TABLE IF NOT EXISTS children "+
	                "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+ 
					" names TEXT NOT NULL, " +
					" location TEXT NOT NULL, "	+ 
					" age TEXT NOT NULL," +
					" gender TEXT NOT NULL)";
		db.execSQL(children_table);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String drop_children = "DROP TABLE IF EXISTS children";
		db.execSQL(drop_children);

	}

	public void save(Child s) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("names", s.getNames());
		values.put("location", s.getLocation());
		values.put("age", s.getAge());
		values.put("gender", s.getGender());
		database.insert("children", null, values);
		database.close();
	}

	public ArrayList<Child> fetch() {
		ArrayList<Child> data = new ArrayList<Child>();
		SQLiteDatabase database = this.getWritableDatabase();
		String sql = "SELECT * FROM children";
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {
				String names = cursor.getString(1);
				String location = cursor.getString(2);
				String age = cursor.getString(3);
				String gender = cursor.getString(4);
				Child x = new Child(names, location, age, gender);
				data.add(x);
			} while (cursor.moveToNext());
		}
		database.close();
		return data;
	}

	public int countChildren() {
		String selectQuery = "SELECT  * FROM children";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		int count = cursor.getCount();
		database.close();
		return count;

	}

	public void delete(String names)
	{
	  SQLiteDatabase db=this.getWritableDatabase();
	  db.delete("children", "names=?", new String[]{names});
	  db.close();		
	}
	
	public void edit(ContentValues values,String id)
	{
	  SQLiteDatabase db=this.getWritableDatabase();
	  db.update("children", values, "id=?", new String[]{id});	
	  db.close();
	}
	
	public Child fetchOneChild(String names)
	{
		String sql="SELECT * FROM children WHERE " +
				    "names LIKE '%"+names+"%'";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, null);
		if(cursor.moveToFirst())
		{
			String name = cursor.getString(1);
			String location = cursor.getString(2);
			String age = cursor.getString(3);
			String gender = cursor.getString(4);
			Child x = new Child(name, location, age, gender);
			db.close();
			return x;
		}		
		return null;
	}
	
	
	
	
	
	
	
	
	
}
