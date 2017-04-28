package com.radiusnetworks.ibeaconreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

public class Record extends Activity {

	TextView tv_record;
	private ListView listView;
	private TextView textRecord;
	private ImageView ImageRecord;
	private ArrayList<String> list1;
	private String UID="";
	private String name="";
	private TextView et;
	private ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);

		tv_record = (TextView) findViewById(R.id.tv_record);
		
		listView = (ListView) findViewById(R.id.listview_record);
		ImageRecord=(ImageView) findViewById(R.id.image_record);
		textRecord=(TextView) findViewById(R.id.text_record);
		
		Intent intent = getIntent();
		UID = intent.getStringExtra("UID");
		name = intent.getStringExtra("name");
		
		tv_record.setText(name);
		
		final RequestQueue requestQueue = Volley
				.newRequestQueue(Record.this);
		ArrayList<String> list = new ArrayList<String>();
		list.add("时间");
		list.add("消息");
		list.add("状态");
		lists.add(list);

		StringRequest stringRequest = new StringRequest(Method.POST,
				HttpUtil.url + "WebServer/getMessage",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {

						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(s);
							for (int i = 1; i <= jsonObject.length(); i++) {
								JSONObject message = jsonObject
										.getJSONObject("message" + i);
								list1 = new ArrayList<String>();
								list1.add(message.getString("time"));
								list1.add(message.getString("message"));
								list1.add(message.getString("status"));
								lists.add(list1);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(Record.this, "网络异常，稍后再试！",
								Toast.LENGTH_LONG).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("UID",UID);
				return map;
			}

		};
		requestQueue.add(stringRequest);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MyRecordAdapter adapter = new MyRecordAdapter(Record.this, lists);
		listView.setAdapter(adapter);
		
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				LinearLayout layout = (LinearLayout) listView.getChildAt(arg2);// 获得子item的layout
				et = (TextView) layout.findViewById(R.id.text5);// 从layout中获得控件,根据其id
				Toast.makeText(Record.this, et.getText(), Toast.LENGTH_LONG)
						.show();	
				if (et.getText().equals("时间")){
					
				}else{
					arg1.setBackgroundResource(R.color.yidu);
					
					StringRequest stringRequest = new StringRequest(Method.POST,
							HttpUtil.url + "WebServer/updateStatus",
							new Response.Listener<String>() {
								@Override
								public void onResponse(String s) {
									Toast.makeText(Record.this, s,
											Toast.LENGTH_LONG).show();
								}
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError volleyError) {
									Toast.makeText(Record.this, "网络异常，稍后再试！",
											Toast.LENGTH_LONG).show();
								}
							}) {
						@Override
						protected Map<String, String> getParams() throws AuthFailureError {
							Map<String, String> map = new HashMap<String, String>();
							map.put("time",(String)et.getText());
							return map;
						}

					};
					requestQueue.add(stringRequest);
					
				}
				
				
			}
			
			
			
		});
		
		ImageRecord.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Record.this.finish();
				
			}
		});

        textRecord.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Record.this.finish();
				
			}
		});
	}

}
