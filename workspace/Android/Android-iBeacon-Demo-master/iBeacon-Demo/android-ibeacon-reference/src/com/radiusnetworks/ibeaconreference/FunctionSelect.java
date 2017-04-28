package com.radiusnetworks.ibeaconreference;

import com.radiusnetworks.ibeacon.IBeaconManager;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity; // 注意这里我们导入的V4的包，不要导成app的包了
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FunctionSelect extends FragmentActivity implements View.OnClickListener {
	 private long exitTime = 0;
	// 初始化顶部栏显示
	private TextView titleTv;
	// 定义3个Fragment对象
	private InformationFragment information;
	private UserMesFragment user;
	// 帧布局对象，用来存放Fragment对象
	private FrameLayout frameLayout;
	// 定义每个选项中的相关控件
	private RelativeLayout informationLayout;
	private RelativeLayout userLayout;
	private ImageView informationImage;
	private ImageView userImage;
	private TextView informationText;
	private TextView userText;
	// 定义几个颜色
	private int gray = 0xFF7397A3;// 0x5F9EA0;//FF7597B3
	private int dark = 0xff000000;
	// 定义FragmentManager对象管理器
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_select);
		fragmentManager = getSupportFragmentManager();
		initView(); // 初始化界面控件
		setChioceItem(0); // 初始化页面加载时显示第一个选项卡

	}

	/**
	 * 初始化页面
	 */
	private void initView() {
		// 初始化页面标题栏
		titleTv = (TextView) findViewById(R.id.title_text_tv);
		titleTv.setText("设备列表");
		// 初始化底部导航栏的控件
		informationImage = (ImageView) findViewById(R.id.first_image);
		userImage = (ImageView) findViewById(R.id.third_image);

		informationText = (TextView) findViewById(R.id.first_text);
		userText = (TextView) findViewById(R.id.third_text);

		informationLayout = (RelativeLayout) findViewById(R.id.first_layout);
		userLayout = (RelativeLayout) findViewById(R.id.third_layout);

		informationLayout.setOnClickListener((OnClickListener) FunctionSelect.this);
		userLayout.setOnClickListener((OnClickListener) FunctionSelect.this);
	}

	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.first_layout)
			setChioceItem(0);
		else if (v.getId() == R.id.third_layout)
			setChioceItem(2);

	}

	/**
	 * 设置点击选项卡的事件处理
	 *
	 * @param index
	 *            选项卡的标号：0, 1, 2, 3
	 */
	private void setChioceItem(int index) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		switch (index) {
		case 0:
			clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
			hideFragments(fragmentTransaction);//隐藏所有不为空的fragment
			informationText.setTextColor(dark);
			informationImage.setColorFilter(dark);
			//informationLayout.setBackgroundColor(gray);
			// 如果fg1为空，则创建一个并添加到界面上
			if (information == null) {
				information = new InformationFragment();
				fragmentTransaction.add(R.id.content, information);
			} else {
				// 如果不为空，则直接将它显示出来
				fragmentTransaction.show(information);
			}
			titleTv.setText("设备列表");
			break;
		case 2:
			clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
			hideFragments(fragmentTransaction);
			userText.setTextColor(dark);
			userImage.setColorFilter(dark);
			//userLayout.setBackgroundColor(gray);
			if (user == null) {
				user = new UserMesFragment();
				fragmentTransaction.add(R.id.content, user);
			} else {
				fragmentTransaction.show(user);//显示被隐藏的
			}
			titleTv.setText("我");
			break;
		}
		fragmentTransaction.commit(); // 提交
	}

	/**
	 * 当选中其中一个选项卡时，其他选项卡重置为默认
	 */
	private void clearChioce() {
		informationText.setTextColor(gray);
		informationImage.setColorFilter(gray);
		//informationLayout.setBackgroundColor(white);
		userText.setTextColor(gray);
		userImage.setColorFilter(gray);
		//userLayout.setBackgroundColor(white);
	}

	/**
	 * 隐藏Fragment
	 *
	 * @param fragmentTransaction
	 */
	private void hideFragments(FragmentTransaction fragmentTransaction) {
		if (information != null) {
			fragmentTransaction.hide(information);
		}
		
		if (user != null) {
			fragmentTransaction.hide(user);
		}
	}
	 
	@SuppressWarnings("deprecation")
	public void onBackPressed() {
	        if(System.currentTimeMillis() - exitTime > 2000) {
	            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
	            exitTime = System.currentTimeMillis();
	       } else {
//	           finish();
//	           System.exit(0);
//	           android.os.Process.killProcess(android.os.Process.myPid());    
	    	   int currentVersion = android.os.Build.VERSION.SDK_INT;  
	            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {  
	                Intent startMain = new Intent(Intent.ACTION_MAIN);  
	                startMain.addCategory(Intent.CATEGORY_HOME);  
	                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	                startActivity(startMain);  
	            	
	                System.exit(0);  
	            } else {// android2.1  
	                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);  
	                am.restartPackage(getPackageName());  
	            }  
	          
	       }
	    }
}
