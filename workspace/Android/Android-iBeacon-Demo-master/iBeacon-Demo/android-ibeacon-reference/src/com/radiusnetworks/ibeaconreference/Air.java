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

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Air {
	
	private void paint(Dialog dialog,int temper,int humid) {
		// TODO Auto-generated method stub
		MeterView temperView = (MeterView) dialog.findViewById(R.id.temper_view1);
		temperView.setProgress(temper, "温度","℃");
		MeterView humidView = (MeterView) dialog.findViewById(R.id.humid_view1);
		humidView.setProgress(humid, "湿度","%");
	}
	public void setAir(final Context context, final Dialog dialog, final String UID) {
		
		final RequestQueue request = Volley.newRequestQueue(context);
		StringRequest result = new StringRequest(Method.POST, HttpUtil.url + "WebServer/getHUM",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						String str[] = s.split("@");
						Toast.makeText(context,str[0]+"  "+str[1], Toast.LENGTH_LONG).show();
						paint(dialog,Integer.valueOf(str[0]),Integer.valueOf(str[1]));
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
				map.put("UID",UID);
				return map;
			}
		};
		request.add(result);// get all data
	}

}

 
