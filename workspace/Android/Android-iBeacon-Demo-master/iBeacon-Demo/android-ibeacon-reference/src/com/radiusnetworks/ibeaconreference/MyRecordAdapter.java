package com.radiusnetworks.ibeaconreference;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyRecordAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ArrayList<String>> lists;
	
	public MyRecordAdapter(Context context, ArrayList<ArrayList<String>> lists) {
		super();
		this.context = context;
		this.lists = lists;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int index, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ArrayList<String> list = lists.get(index);
		if(view == null){
			view = inflater.inflate(R.layout.list_item_record, null);
		}
		view.setBackgroundColor(Color.WHITE);
		TextView textView1 = (TextView) view.findViewById(R.id.text5);
		TextView textView2 = (TextView) view.findViewById(R.id.text6);
		TextView textView3 = (TextView) view.findViewById(R.id.text7);
		textView1.setTextColor(Color.BLACK);
		textView2.setTextColor(Color.BLACK);
		textView3.setTextColor(Color.BLACK);
		textView1.setText(list.get(0));
		textView2.setText(list.get(1));
		textView3.setText(list.get(2));
		
		TextView tv= (TextView)view.findViewById(R.id.text7);
		if(tv.getText().equals("0")){
			view.setBackgroundResource(R.color.yidu);
		}else if(tv.getText().equals("2")){
			view.setBackgroundResource(R.color.zhengchang);
		}else if(tv.getText().equals("3")){
			view.setBackgroundResource(R.color.baojing);
		}else{
			
		}
		if(index == 0){
			view.setBackgroundResource(R.color.head_record);
			textView1.setTextColor(Color.WHITE);
			textView2.setTextColor(Color.WHITE);
			textView3.setTextColor(Color.WHITE);

		}/*else{
			if(index%2 != 0){
				view.setBackgroundColor(Color.argb(250 ,  255 ,  255 ,  255 )); 
			}else{
				view.setBackgroundColor(Color.argb(250 ,  224 ,  243 ,  250 ));    
			}
		}*/
		
		return view;
	}

}
