package com.fndroid.multimediademo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LightsensorActivity extends AppCompatActivity {
	private TextView light;

	private SensorManager manager;
	private MySensorEventListener mSensorEventListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lightsensor);
		light = (TextView) findViewById(R.id.light);
		startLightSensor();
	}

	private void startLightSensor() {
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
		mSensorEventListener = new MySensorEventListener();
		manager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
	}

	private class MySensorEventListener implements SensorEventListener {
		@Override
		public void onSensorChanged(SensorEvent event) {
			light.setText("光照强度为:" + event.values[0] + "");
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (manager != null) {
			manager.unregisterListener(mSensorEventListener);
		}
	}
}
