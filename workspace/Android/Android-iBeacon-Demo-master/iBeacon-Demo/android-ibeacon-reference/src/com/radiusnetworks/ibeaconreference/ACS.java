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

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ACS {
	String s = "";
	String UID;
	public void paint(final Context context,Dialog dialog) {
		Log.e("!", "96666");
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		try {
			JSONObject js = new JSONObject(s);
			for (int i = 1; i <= js.getInt("amounts"); i++) {
				JSONObject json = js.getJSONObject("infomation" + i);
				ArrayList<String> col = new ArrayList<String>();
				col.add(json.getString("UID"));
				col.add(json.getString("name"));
				col.add(json.getString("status"));
				col.add(json.getString("time"));
				list.add(col);
//              Log.e("UID",json.getString("UID"));
//				Log.e("name", json.getString("name"));
//				Log.e("status", json.getString("status"));
//				Log.e("time", json.getString("time"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("List.size",""+list.size());
		// 创建listview,添加适配器
		IBeaconActivity iBeaconActivity = (IBeaconActivity) context;
		ListView listview = (ListView) dialog.findViewById(R.id.ent_table);
		AcsAdapter adapter = new AcsAdapter(context, list);
		listview.setAdapter(adapter);
		// 为listview添加HeaderView,固定第一行数据
		LayoutInflater inflater = (LayoutInflater) iBeaconActivity.getSystemService("layout_inflater");
		View headerView = inflater.inflate(R.layout.ent_table_head, listview, false);
		listview.addHeaderView(headerView);
	}

	public void setAcsTable(final Context context,final Dialog dialog, final String UID) {
		Log.e("!!!", "96666");
		this.UID = UID;
		final RequestQueue request = Volley.newRequestQueue(context);
		StringRequest result = new StringRequest(Method.POST, HttpUtil.url + "WebServer/getACS",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						Log.e("s", s);
						ACS.this.s = s;
						paint(context,dialog);
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
				map.put("UID", UID);
				return map;
			}
		};
		request.add(result);// get all data

	}

	private class AcsAdapter extends BaseAdapter {
		private LayoutInflater mInflater = null;
		ArrayList<ArrayList<String>> data;
		Context context;
		public AcsAdapter(Context context, ArrayList<ArrayList<String>> data) {
			// 根据context上下文加载布局，这里的是Demo17Activity本身，即this
			this.mInflater = LayoutInflater.from(context);
			this.data = data;
			this.context = context;
		}

		@Override
		public int getCount() {
			// 在此适配器中所代表的数据集中的条目数
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// 获取数据集中与指定索引对应的数据项
			return position;
		}

		@Override
		public long getItemId(int position) {
			// 获取在列表中与指定索引对应的行id
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			try {
				final ViewHolder holder;
				if (convertView != null) {
					holder = (ViewHolder) convertView.getTag();
				} else {
					holder = new ViewHolder();
					convertView = mInflater.inflate(R.layout.ent_table, null);
					holder.text1 = (TextView) convertView.findViewById(R.id.e_text1);
					holder.text2 = (TextView) convertView.findViewById(R.id.e_text2);
					holder.btn3 = (Switch) convertView.findViewById(R.id.e_btn3);
					holder.text4 = (TextView) convertView.findViewById(R.id.e_text4);
					convertView.setTag(holder);
				}
				holder.text1.setText(data.get(position).get(0));
				holder.text2.setText(data.get(position).get(1));
				int status = Integer.valueOf(data.get(position).get(2));
				if(status==1)
				holder.btn3.setChecked(true);
				else if(status==0)
				holder.btn3.setChecked(false);
				holder.text4.setText(data.get(position).get(3));
				holder.text2.setOnFocusChangeListener(new MyListener(){
					String name;
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
							name = holder.text2.getText().toString();
						}else{
							String ID = holder.text1.getText().toString().trim();
							Post.sendACSmessae(context,ID,holder.text2, name, 0);
						}
					}
				});
				
				holder.btn3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						String ID = holder.text1.getText().toString().trim();
						String name;
						if(isChecked)
							name = "0";
						else 
							name = "1";
					    int flag = Post.sendACSmessae(context,ID,null, name, 1);
					    if(flag==1)
					    	holder.btn3.setChecked(isChecked);
					    else
					    	holder.btn3.setChecked(!isChecked);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}

		private class ViewHolder {
			private TextView text1;
			private TextView text2;
			private Switch btn3;
			private TextView text4;
		}

	}
}
