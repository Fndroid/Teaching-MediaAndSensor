package com.fndroid.multimediademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
	private ImageView mImageView;

	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		mImageView = (ImageView) findViewById(R.id.image);
	}

	public void openCamera(View view) {
		File image = new File(Environment.getExternalStorageDirectory(), "img.png");
		try {
			if (image.exists()) {
				image.delete();
			}
			image.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageUri = Uri.fromFile(image);
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			try {
				Bitmap image = BitmapFactory.decodeStream(getContentResolver().openInputStream
						(imageUri));
				mImageView.setImageBitmap(image);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
