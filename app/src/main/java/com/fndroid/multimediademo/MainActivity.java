package com.fndroid.multimediademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void notificationActivity(View view){
		startActivity(new Intent(this, SendNotifiicationActivity.class));
	}

	public void cameraActivity(View view){
		startActivity(new Intent(this, CameraActivity.class));
	}

	public void lightSensorActivity(View view){
	    startActivity(new Intent(this, LightsensorActivity.class));
	}

	public void pickImgActivity(View view){
	    startActivity(new Intent(this, PickImageActivity.class));
	}

	public void locationActivity(View view){
	    startActivity(new Intent(this, LocationActivity.class));
	}

}
