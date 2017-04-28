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
							// 0������1�Ž�������2�Ž���λ3��λ
							if (type == 0) {
								textView.setText(name);
								Toast.makeText(context, "�޸�ʧ��", Toast.LENGTH_LONG).show();
							} else if (type == 1) {
								Toast.makeText(context, "��λʧ��", Toast.LENGTH_LONG).show();
							}
						} else {
							if (type == 0)
								Toast.makeText(context, "�޸ĳɹ�", Toast.LENGTH_LONG).show();
							else if (type == 1)
								Toast.makeText(context, "��λ�ɹ�", Toast.LENGTH_LONG).show();	
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(context, "�����쳣���Ժ����ԣ�", Toast.LENGTH_LONG).show();
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
							// 0������1����
							if (type == 0) {
								textView.setText(name);
								Toast.makeText(context, "�޸�ʧ��", Toast.LENGTH_LONG).show();
							} else if (type == 1) {
								Toast.makeText(context, "����ʧ��", Toast.LENGTH_LONG).show();
							}
						} else {
							if (type == 0)
								Toast.makeText(context, "�޸ĳɹ�", Toast.LENGTH_LONG).show();
							else if (type == 1)
								Toast.makeText(context, "���سɹ�", Toast.LENGTH_LONG).show();	
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(context, "�����쳣���Ժ����ԣ�", Toast.LENGTH_LONG).show();
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


