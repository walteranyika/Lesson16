package com.walter.lesson16;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//Ctrl+Shift+O
////Ctrl+Shift+F

public class CustomAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<Child> data;
	public CustomAdapter(Context context, ArrayList<Child> data) {
		this.mContext = context;
		this.data = data;
	}
	@Override
	public int getCount() {
		return data.size();// # of items in your arraylist
	}
	@Override
	public Object getItem(int position) {
		return data.get(position);// get the actual movie
	}
	@Override
	public long getItemId(int id) {
		return id;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(R.layout.list_item_layout, parent,					false);
			viewHolder = new ViewHolder();
			viewHolder.textViewNames = (TextView) convertView.findViewById(R.id.textViewNames);
			viewHolder.textViewLoc = (TextView) convertView.findViewById(R.id.textViewLoc);
			viewHolder.textViewAge = (TextView) convertView.findViewById(R.id.textViewAge);
			viewHolder.textViewGender = (TextView) convertView.findViewById(R.id.textViewGender);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Child b = data.get(position);
		viewHolder.textViewNames.setText(b.getNames());
		viewHolder.textViewLoc.setText(b.getLocation());
		viewHolder.textViewAge.setText(b.getAge());
		viewHolder.textViewGender.setText(b.getGender());
		return convertView;
	}
	static class ViewHolder {
		TextView textViewNames;
		TextView textViewLoc;
		TextView textViewAge;
		TextView textViewGender;
	}

}
