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

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class InformationFragment extends Fragment {
	private Handler mHandler;

	private ListView listView;
	private ArrayList<String> list1;
	private ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// title = getArguments().getString(TITLE);
		// message = getArguments().getString(MESSAGE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_information, container, false);
		//////////////////////////////////
		mHandler = new Handler();

		listView = (ListView) view.findViewById(R.id.listview);
		final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		 
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				
				StringRequest stringRequest = new StringRequest(Method.POST, HttpUtil.url + "WebServer/getInformation",
						new Response.Listener<String>() {
							@Override
							public void onResponse(String s) {

								JSONObject jsonObject;
								try {
									lists.clear();
									ArrayList<String> list = new ArrayList<String>();
									list.add("UID");
									list.add("名称");
									list.add("类别");
									list.add("状态");
									lists.add(list);
									jsonObject = new JSONObject(s);
									for (int i = 1; i <= jsonObject.length(); i++) {  //red  1
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='1'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
										
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //red  2
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='2'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
										
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //red  3
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='3'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
										
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //red  4
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='4'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
										
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //noRed  1
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(!infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='1'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //noRed  2
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(!infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='2'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //noRed  3
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(!infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='3'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
									}
									for (int i = 1; i <= jsonObject.length(); i++) {  //noRed  4
										JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
										if(!infomation.getString("status").equals("警告")){
											if(infomation.getString("UID").charAt(0)=='4'){
												list1 = new ArrayList<String>();
												list1.add(infomation.getString("UID"));
												list1.add(infomation.getString("name"));
												list1.add(infomation.getString("category"));
												list1.add(infomation.getString("status"));
												lists.add(list1);
											}
										}
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError volleyError) {
								Toast.makeText(getActivity(), "网络异常，稍后再试！", Toast.LENGTH_LONG).show();
							}
						}) {

					@Override
					protected Map<String, String> getParams() throws AuthFailureError {
						Map<String, String> map = new HashMap<String, String>();
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

				final InformationAdapter adapter = new InformationAdapter(getActivity(), lists);
				listView.setAdapter(adapter);

				mHandler.postDelayed(this, 5000);
			}
		});

		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				LinearLayout layout = (LinearLayout) listView.getChildAt(arg2);// 获得子item的layout
				TextView et = (TextView) layout.findViewById(R.id.text1);// 从layout中获得控件,根据其id
				TextView et_name = (TextView) layout.findViewById(R.id.text2);
				Toast.makeText(getActivity(), et_name.getText(), Toast.LENGTH_LONG).show();
				if (et.getText().equals("UID")) {

				} else {
					// arg1.setBackgroundResource(R.color.abc_search_url_text_pressed);
					Intent intent = new Intent(getActivity(), Record.class);
					intent.putExtra("UID", et.getText());
					intent.putExtra("name", et_name.getText());
					startActivity(intent);
				}
			}
		});
		//////////////////////////////////
		return view;
	}

}



