package com.fndroid.multimediademo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PickImageActivity extends AppCompatActivity {
	private static final String TAG = "PickImageActivity";
	private static final int PICK = 1;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_image);
		img = (ImageView) findViewById(R.id.showPickImage);
	}

	public void pick(View view) {
		Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media
				.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, PICK);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK && resultCode == RESULT_OK && data != null){
			Uri imgUri = data.getData();
			String[] imgPathColumn = new String[]{MediaStore.Images.Media.DATA};
			Cursor cursor = getContentResolver().query(imgUri, imgPathColumn, null, null, null);
			cursor.moveToFirst();
			String imgPath = cursor.getString(cursor.getColumnIndex(imgPathColumn[0]));
			cursor.close();
			img.setImageBitmap(BitmapFactory.decodeFile(imgPath));
		}
	}
}
