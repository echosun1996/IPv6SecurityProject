package com.radiusnetworks.ibeaconreference;

import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Post {
	static int reply;
	static public int sendMessage(final Context context, final String UID, final TextView textView, final String name,
			final int type) {
		final RequestQueue request = Volley.newRequestQueue(context);
		StringRequest result = new StringRequest(Method.POST, HttpUtil.url + "WebServer/updateIbeacon",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						reply = Integer.valueOf(s);
						if (reply == 0) {
							// 0改名字1门禁改名字2门禁复位3复位
							if (type == 0) {
								textView.setText(name);
								Toast.makeText(context, "修改失败", Toast.LENGTH_LONG).show();
							} else if (type == 1) {
								Toast.makeText(context, "复位失败", Toast.LENGTH_LONG).show();
							}
						} else {
							if (type == 0)
								Toast.makeText(context, "修改成功", Toast.LENGTH_LONG).show();
							else if (type == 1)
								Toast.makeText(context, "复位成功", Toast.LENGTH_LONG).show();	
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(context, "网络异常，稍后再试！", Toast.LENGTH_LONG).show();
						Log.i("IBeaconActivity", "getgetgetgetgetget");
					}
				}) {
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				StringBuffer strbuf = new StringBuffer();
				if (type == 0)
					strbuf.append("{'UID':'" + UID + "','type':'rename','info':{'rename':'"
							+ textView.getText().toString() + "'}}");
				else if (type == 1)
					strbuf.append("{'UID':'" + UID + "','type':'reset','info':{'reset':'1'}}");
				map.put("Object", strbuf.toString());
				return map;
			}
		};
		request.add(result);// get all data
		return reply;
	}

	static public int sendACSmessae(final Context context, final String ID, final TextView textView, final String name,
			final int type) {
		final RequestQueue request = Volley.newRequestQueue(context);
		StringRequest result = new StringRequest(Method.POST, HttpUtil.url + "WebServer/updateACS",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						reply = Integer.valueOf(s);
						if (reply == 0) {
							// 0改名字1开关
							if (type == 0) {
								textView.setText(name);
								Toast.makeText(context, "修改失败", Toast.LENGTH_LONG).show();
							} else if (type == 1) {
								Toast.makeText(context, "开关失败", Toast.LENGTH_LONG).show();
							}
						} else {
							if (type == 0)
								Toast.makeText(context, "修改成功", Toast.LENGTH_LONG).show();
							else if (type == 1)
								Toast.makeText(context, "开关成功", Toast.LENGTH_LONG).show();	
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(context, "网络异常，稍后再试！", Toast.LENGTH_LONG).show();
						Log.i("IBeaconActivity", "getgetgetgetgetget");
					}
				}) {
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				StringBuffer strbuf = new StringBuffer();
				if (type == 0)
					strbuf.append("{'ID':'" + ID + "','type':'rename','info':{'rename':'"
							+ textView.getText().toString() + "'}}");
				else if (type == 1)
					strbuf.append("{'ID':'" + ID + "','type':'reset','info':{'reset':'"+name+"'}}");
				map.put("Object", strbuf.toString());
				return map;
			}
		};
		request.add(result);// get all data
		return reply;
	}
}


