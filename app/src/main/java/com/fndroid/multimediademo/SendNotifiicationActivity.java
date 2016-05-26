package com.fndroid.multimediademo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class SendNotifiicationActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_notifiication);
	}

	public void sendNotification(View view) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.big);
		PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this,
				MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
		builder.setLargeIcon(icon);
		builder.setContentTitle("新通知");
		builder.setContentText("汇智网，最前沿的在线互动编程学习平台");
		builder.setSmallIcon(R.drawable.small);
		builder.setContentIntent(intent);
		builder.setAutoCancel(true);
		Notification notification = builder.build();
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.notify(1, notification);
	}
}
