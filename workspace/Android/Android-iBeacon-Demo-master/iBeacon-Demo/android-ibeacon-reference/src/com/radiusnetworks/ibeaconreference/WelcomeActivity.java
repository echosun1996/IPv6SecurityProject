package com.radiusnetworks.ibeaconreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {
	private Button login;
	private int flag=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		 Handler handler = new Handler();
		    //当计时结束,跳转至主界面
		    handler.postDelayed(new Runnable() {
		      @Override
		      public void run() {
		    	  if(flag==0){
		    		  Intent intent = new Intent(WelcomeActivity.this,Login.class);
				        startActivity(intent);
				        WelcomeActivity.this.finish();
		    	  }else{
		    		  
		    	  }
		      
		      }
		    }, 3000);
		
		login=(Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag=1;
				Intent intent=new Intent(WelcomeActivity.this,Login.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		});
	}
}
