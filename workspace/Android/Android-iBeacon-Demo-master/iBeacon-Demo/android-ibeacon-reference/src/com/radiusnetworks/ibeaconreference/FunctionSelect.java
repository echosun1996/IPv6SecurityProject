package com.radiusnetworks.ibeaconreference;

import com.radiusnetworks.ibeacon.IBeaconManager;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity; // ע���������ǵ����V4�İ�����Ҫ����app�İ���
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
	// ��ʼ����������ʾ
	private TextView titleTv;
	// ����3��Fragment����
	private InformationFragment information;
	private UserMesFragment user;
	// ֡���ֶ����������Fragment����
	private FrameLayout frameLayout;
	// ����ÿ��ѡ���е���ؿؼ�
	private RelativeLayout informationLayout;
	private RelativeLayout userLayout;
	private ImageView informationImage;
	private ImageView userImage;
	private TextView informationText;
	private TextView userText;
	// ���弸����ɫ
	private int gray = 0xFF7397A3;// 0x5F9EA0;//FF7597B3
	private int dark = 0xff000000;
	// ����FragmentManager���������
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_select);
		fragmentManager = getSupportFragmentManager();
		initView(); // ��ʼ������ؼ�
		setChioceItem(0); // ��ʼ��ҳ�����ʱ��ʾ��һ��ѡ�

	}

	/**
	 * ��ʼ��ҳ��
	 */
	private void initView() {
		// ��ʼ��ҳ�������
		titleTv = (TextView) findViewById(R.id.title_text_tv);
		titleTv.setText("�豸�б�");
		// ��ʼ���ײ��������Ŀؼ�
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
	 * ���õ��ѡ����¼�����
	 *
	 * @param index
	 *            ѡ��ı�ţ�0, 1, 2, 3
	 */
	private void setChioceItem(int index) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		switch (index) {
		case 0:
			clearChioce(); // ���, ����ѡ��, ��������Fragment
			hideFragments(fragmentTransaction);//�������в�Ϊ�յ�fragment
			informationText.setTextColor(dark);
			informationImage.setColorFilter(dark);
			//informationLayout.setBackgroundColor(gray);
			// ���fg1Ϊ�գ��򴴽�һ������ӵ�������
			if (information == null) {
				information = new InformationFragment();
				fragmentTransaction.add(R.id.content, information);
			} else {
				// �����Ϊ�գ���ֱ�ӽ�����ʾ����
				fragmentTransaction.show(information);
			}
			titleTv.setText("�豸�б�");
			break;
		case 2:
			clearChioce(); // ���, ����ѡ��, ��������Fragment
			hideFragments(fragmentTransaction);
			userText.setTextColor(dark);
			userImage.setColorFilter(dark);
			//userLayout.setBackgroundColor(gray);
			if (user == null) {
				user = new UserMesFragment();
				fragmentTransaction.add(R.id.content, user);
			} else {
				fragmentTransaction.show(user);//��ʾ�����ص�
			}
			titleTv.setText("��");
			break;
		}
		fragmentTransaction.commit(); // �ύ
	}

	/**
	 * ��ѡ������һ��ѡ�ʱ������ѡ�����ΪĬ��
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
	 * ����Fragment
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
	            Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
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
