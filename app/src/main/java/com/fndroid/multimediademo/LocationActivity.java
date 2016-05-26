package com.fndroid.multimediademo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {
	private TextView mTextView;
	private LocationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		mTextView = (TextView) findViewById(R.id.tv_location);
		getLocation();
	}

	public void getLocation() {
		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
				PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
				Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		update(location);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener
				() {
			@Override
			public void onLocationChanged(Location location) {
				update(location);
			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				update(null);
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onProviderDisabled(String provider) {

			}
		});
	}

	private void update(Location location) {
		if (location != null) {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("当前位置信息为：\n");
			sBuilder.append("经度：" + location.getLongitude() + "\n");
			sBuilder.append("纬度：" + location.getLatitude() + "\n");
			mTextView.setText(sBuilder);
		}
	}
}
