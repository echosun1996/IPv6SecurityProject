package com.radiusnetworks.ibeaconreference;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login extends Activity {
	public static final String TAG =Login.class.getName(); 
	ImageView iv_showCode; 
	Button login_login;
	EditText login_username, login_password,et_putCodes;
	private String realCode;  
	private String check = "f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        login_login = (Button) findViewById(R.id.login_login);
        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        iv_showCode=(ImageView) findViewById(R.id.iv_showCode);
        et_putCodes=(EditText) findViewById(R.id.et_putCodes);
        
        login_username.setText("admin");
        login_password.setText("admin");
        //将验证码用图片的形式显示出来  
        iv_showCode.setImageBitmap(Code.getInstance().createBitmap());  
        realCode = Code.getInstance().getCode().toLowerCase();  
        iv_showCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_showCode.setImageBitmap(Code.getInstance().createBitmap());  
                realCode = Code.getInstance().getCode().toLowerCase();  
                Log.v(TAG,"realCode"+realCode);  
				
			}
		}) ;

        login_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*加密*/
				String temp=login_password.getText()+"";
				final String p = MessageDigestUtil.MessageDigest(temp);
				
				if (validate()) {
					final RequestQueue requestQueue = Volley
							.newRequestQueue(Login.this);
					
					//GuardElement
					
					StringRequest stringRequest = new StringRequest(Method.POST,
							HttpUtil.url + "WebServer/login",
							new Response.Listener<String>() {
								@Override
								public void onResponse(String s) {
									  String phoneCode = et_putCodes.getText().toString().toLowerCase();  
							         // String msg = "生成的验证码："+realCode+"输入的验证码:"+phoneCode;  
							
//							          if (phoneCode.equals(realCode)) {  
//							              Toast.makeText(Login.this, phoneCode + "验证码正确", Toast.LENGTH_SHORT).show();  
//							          } else {  
//							              Toast.makeText(Login.this, phoneCode + "验证码错误", Toast.LENGTH_SHORT).show();  
//							          }  
									if (s.equals("t")) {
										if (phoneCode.equals(realCode)) {
											Intent intent = new Intent(Login.this, FunctionSelect.class);
											startActivity(intent);
										}
											else {
												Toast.makeText(Login.this,
														"验证码错误", Toast.LENGTH_SHORT)
														.show();
											}
//										
									} else {
										Toast.makeText(Login.this,
												"用户名、密码或验证码错误", Toast.LENGTH_SHORT)
												.show();
									}

								}
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError volleyError) {
									Toast.makeText(Login.this, "网络异常，稍后再试！",
											Toast.LENGTH_LONG).show();
								}
							}) {
						@Override
						protected Map<String, String> getParams()
								throws AuthFailureError {
							Map<String, String> map = new HashMap<String, String>();
							map.put("login_username", login_username.getText()
									.toString());
							/*加密*/
							//String temp=login_password.getText()+"";
							//String check = "f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
							//temp=DigestUtils.sha1Hex(temp+check);
							/*加密结束*/
							map.put("login_password", p+"");
							return map;
						}

					};
					requestQueue.add(stringRequest);
				}
			
			}
		});
    }


    private boolean validate() {
		String name = login_username.getText().toString();
		if (name.equals("")) {
			showDialog("用户名是必填项！");
			return false;
		}

		String phoneCode = login_password.getText().toString();
		if (phoneCode.equals("")) {
			showDialog("密码是必填项！");
			return false;
		}
		
		String Code =et_putCodes.getText().toString();
		if (Code.equals("")) {
			showDialog("验证码是必填项！");
			return false;
		}

		return true;
	}
    
    private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
    
}
