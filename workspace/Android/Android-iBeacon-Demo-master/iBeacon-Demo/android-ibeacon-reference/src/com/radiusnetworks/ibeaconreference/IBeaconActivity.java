package com.radiusnetworks.ibeaconreference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.MonitorNotifier;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

public class IBeaconActivity extends Activity implements IBeaconConsumer {
	protected static final String TAG = "IBeaconActivity";
	private ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();// +all
																						// data
	private ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();// +最后显示
	private ArrayList<ArrayList<String>> list_v = new ArrayList<ArrayList<String>>();
	private ListView listview = null;
	private BeaconAdapter adapter = null;
	private ArrayList<IBeacon> arrayL = new ArrayList<IBeacon>();// ibeacon data
	private ArrayList<String> list_single;
	private BeaconServiceUtility beaconUtill = null;
	private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);
	String str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ibeacon);
		beaconUtill = new BeaconServiceUtility(this);
        //获取服务器数据
		final RequestQueue requestQueue = Volley.newRequestQueue(IBeaconActivity.this);
		StringRequest stringRequest = new StringRequest(Method.GET, HttpUtil.url + "WebServer/getInformation",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(s);
							for (int i = 1; i <= jsonObject.length(); i++) {
								JSONObject infomation = jsonObject.getJSONObject("infomation" + i);
								// Log.e("IbeaconID",infomation.getString("IbeaconID"));
								// Log.e("name",infomation.getString("name"));
								// Log.e("category",infomation.getString("category"));
								// Log.e("UID",infomation.getString("UID"));
								// Log.e("status",infomation.getString("status"));
								list_single = new ArrayList<String>();
								list_single.add(infomation.getString("IbeaconID"));
								list_single.add(infomation.getString("name"));
								list_single.add(infomation.getString("category"));
								list_single.add(infomation.getString("UID"));
								list_single.add(infomation.getString("status"));
								listAll.add(list_single);// get all data
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(IBeaconActivity.this, "网络异常，稍后再试！", Toast.LENGTH_LONG).show();
						Log.i(TAG, "getgetgetgetgetget");
					}
				}) {
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				return map;
			}

		};
		requestQueue.add(stringRequest);// get all data

		// 创建listview,添加适配器
		listview = (ListView) findViewById(R.id.listview);
		adapter = new BeaconAdapter(IBeaconActivity.this, list_v);
		listview.setAdapter(adapter);
		// 为listview添加HeaderView,固定第一行数据
		LayoutInflater inflater = (LayoutInflater) getSystemService(IBeaconActivity.this.LAYOUT_INFLATER_SERVICE);
		View headerView = inflater.inflate(R.layout.list_item_head, listview, false);
		listview.addHeaderView(headerView);
		/* 给listview加上滑动监听 */
		listview.setOnScrollListener(new MyListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == SCROLL_STATE_FLING) {
					list_v.clear();
					list_v.addAll(lists);
					adapter.notifyDataSetChanged();// 重绘界面
				}
			}
		});
		/* 给listview加上Item点击监听 */
		listview.setOnItemClickListener(new MyListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//点击表头无效
				if(position==0)return;
				//因从表头开始计数，故减一
				position = position - 1;
				final String UID = list_v.get(position).get(4);
				final String Name = list_v.get(position).get(1);
				String category = list_v.get(position).get(2);
				Dialog dialog = new Dialog(IBeaconActivity.this, R.style.MyDialog);
				if (category.equals("门禁")) {
					dialog.setContentView(R.layout.dialog_ent);
					final TextView ent_name;
					TextView ent_category;
					ent_name = (TextView) dialog.findViewById(R.id.ent_name);
					ent_category = (TextView) dialog.findViewById(R.id.ent_category);
					ent_name.setText(Name);
					ent_category.setText(category);
					ent_name.setOnFocusChangeListener(new MyListener(){
						String name;
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if(hasFocus){
								name = ent_name.getText().toString();
							}else{
								Post.sendMessage(IBeaconActivity.this,UID, ent_name, name,0);
							}
						}
					});
                    new ACS().setAcsTable(IBeaconActivity.this,dialog, UID);	
				} else if (category.equals("空气质量")) {
					dialog.setContentView(R.layout.dialog_air);
					final TextView air_name;
					TextView air_category;
					air_name = (TextView) dialog.findViewById(R.id.air_name);
					air_category = (TextView) dialog.findViewById(R.id.air_category);
					air_name.setText(Name);
					air_category.setText(category);
					air_name.setOnFocusChangeListener(new MyListener(){
						String name;
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if(hasFocus){
								name = air_name.getText().toString();
							}else{
								Post.sendMessage(IBeaconActivity.this, UID, air_name, name, 0);
							}
						}
					});
					new Air().setAir(IBeaconActivity.this,dialog, UID);	
				    //获取空气质量	
				} else {
					dialog.setContentView(R.layout.dialog_acs);
					final TextView acs_name;
					TextView acs_category;
					final TextView acs_status;
					final Button acs_btn;
					acs_btn = (Button) dialog.findViewById(R.id.acs_button);
					acs_name = (TextView) dialog.findViewById(R.id.acs_name);
					acs_category = (TextView) dialog.findViewById(R.id.acs_category);
					acs_status = (TextView) dialog.findViewById(R.id.acs_status);
					acs_name.setText(Name);
					acs_category.setText(category);
					String status = list_v.get(position).get(5);
					Log.e("status", status);
					if (status.equals("正常")) {
						acs_status.setTextColor(Color.GREEN);
						acs_btn.setEnabled(false);
					} else {
						acs_btn.setEnabled(true);
						acs_status.setTextColor(Color.RED);
						if (category.equals("火警")) {
							status = "有火情";
						} else if (category.equals("人体红外")) {
							status = "存在入侵";
						} else if (category.equals("窗磁")) {
							status = "门窗已打开";
						}
					}
					acs_status.setText(status);
					acs_name.setOnFocusChangeListener(new MyListener(){
						String name;
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if(hasFocus){
								name = acs_name.getText().toString();
							}else{
								Post.sendMessage(IBeaconActivity.this, UID, acs_name, name, 0);
							}
						}
					});
					acs_btn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int flag = Post.sendMessage(IBeaconActivity.this, UID, null, null, 1);
							Log.e("flag", ""+flag);
							if(flag==1){
							  acs_status.setText("正常");
							  acs_status.setTextColor(Color.GREEN);
							  acs_btn.setEnabled(false);
							}				
						}
					});
				}
				dialog.show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
		beaconUtill.onStart(iBeaconManager, this);
	}

	@Override
	protected void onStop() {
		beaconUtill.onStop(iBeaconManager, this);
		super.onStop();
	}

	@Override
	public void onIBeaconServiceConnect() {

		iBeaconManager.setRangeNotifier(new RangeNotifier() {
			@Override
			public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
				arrayL.clear();
				arrayL.addAll((ArrayList<IBeacon>) iBeacons);
				ArrayList<ArrayList<String>> list_Notifi = new ArrayList<ArrayList<String>>();
				Log.e("服务器ibeacon数量", listAll.size() + "");
				Log.e("附近ibeacon数量", arrayL.size() + "");
                //排序加入
				for (int i = 0; i <= arrayL.size() - 1; i++) {
					for (int j = 0; j <= listAll.size() - 1; j++) {
						if (arrayL.get(i).getProximityUuid().equals(listAll.get(j).get(0))) {// 有一样的id
							list_single = new ArrayList<String>();
							list_single.add(listAll.get(j).get(0));// IBID
							list_single.add(listAll.get(j).get(1));// name
							list_single.add(listAll.get(j).get(2));// category
							list_single.add("" + arrayL.get(i).getAccuracy());// 距离
							list_single.add(listAll.get(j).get(3));// UID
							list_single.add(listAll.get(j).get(4));// Status
							if (list_Notifi.size() > 0) {
								int k;
								for (k = 1; k < list_Notifi.size(); k++) {
									if (Float.parseFloat(list_single.get(3)) <= Float
											.parseFloat(list_Notifi.get(k).get(3))) {
										list_Notifi.add(k, list_single);// 比第一个距离小
										break;
									}
								}
								if (k >= list_Notifi.size())
									list_Notifi.add(k, list_single);
							} else if (list_Notifi.size() == 0) {
								list_Notifi.add(list_single);
							}
						}
					}
				}
				//Log.e("列表中ibeacon数量", lists.size() + "");
				lists.clear();
				lists.addAll(list_Notifi);
			}
		});

		iBeaconManager.setMonitorNotifier(new MonitorNotifier() {
			@Override
			public void didEnterRegion(Region region) {
				Log.e("BeaconDetactorService", "didEnterRegion");
				// logStatus("I just saw an iBeacon for the first time!");
			}

			@Override
			public void didExitRegion(Region region) {
				Log.e("BeaconDetactorService", "didExitRegion");
				// logStatus("I no longer see an iBeacon");
			}

			@Override
			public void didDetermineStateForRegion(int state, Region region) {
				Log.e("BeaconDetactorService", "didDetermineStateForRegion");
				// logStatus("I have just switched from seeing/not seeing
				// iBeacons: " + state);
			}

		});

		try {
			iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		/// 数据刷新变慢
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private class BeaconAdapter extends BaseAdapter {
		private LayoutInflater mInflater = null;
		ArrayList<ArrayList<String>> data;

		public BeaconAdapter(Context context, ArrayList<ArrayList<String>> data) {
			// 根据context上下文加载布局，这里的是Demo17Activity本身，即this
			this.mInflater = LayoutInflater.from(context);
			this.data = data;
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
				ViewHolder holder;

				if (convertView != null) {
					holder = (ViewHolder) convertView.getTag();
				} else {
					holder = new ViewHolder();
					convertView = mInflater.inflate(R.layout.list_item_ibeacon, null);
					holder.textView1 = (TextView) convertView.findViewById(R.id.text1);
					holder.textView2 = (TextView) convertView.findViewById(R.id.text2);
					holder.textView3 = (TextView) convertView.findViewById(R.id.text3);
					holder.textView4 = (TextView) convertView.findViewById(R.id.text4);
					convertView.setTag(holder);
				}
				String t = data.get(position).get(0);
				if (data.get(position).get(0) != null)
					holder.textView1.setText(t.substring(t.length() - 5, t.length()));
				else
				holder.textView1.setText(t);
				holder.textView2.setText(data.get(position).get(1));
				holder.textView3.setText(data.get(position).get(2));
				holder.textView4.setText(data.get(position).get(3));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}

		private class ViewHolder {
			private TextView textView1;
			private TextView textView2;
			private TextView textView3;
			private TextView textView4;
		}

	}

}
