package com.radiusnetworks.ibeaconreference;

import java.io.File;

import com.radiusnetworks.ibeacon.IBeaconManager;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class UserMesFragment extends Fragment {
	private Button startIB;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_user_message, container, false);
		/////////////////
		startIB=(Button) view.findViewById(R.id.starIB);
		startIB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(verifyBluetooth()==false){
					
				}else{
					Intent intent=new Intent(getActivity(),IBeaconActivity.class);
					startActivity(intent);
				}
				
			}
		});

		////////////////
		return view;
	}
	
	private boolean verifyBluetooth() {
		boolean flag = true;
		try {
			if (!IBeaconManager.getInstanceForApplication(getActivity()).checkAvailability()) {
				flag = false;
				final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("请开启蓝牙。");
				builder.setMessage("需要通过蓝牙信号进行用户定位.");
				builder.setPositiveButton(android.R.string.ok, null);
				builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

					@Override
					public void onDismiss(DialogInterface dialog) {
						//finish();
						//System.exit(0);
					}

				});
				builder.show();
			}
		} catch (RuntimeException e) {

			final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Bluetooth LE not available");
			builder.setMessage("Sorry, this device does not support Bluetooth LE.");
			builder.setPositiveButton(android.R.string.ok, null);
			builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					// finish();
					System.exit(0);
				}
			});
			builder.show();

			return false;
		}
		return flag;

	}

	

}
