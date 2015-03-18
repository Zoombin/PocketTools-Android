package com.juhe.pockettools.utils;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.juhe.pockettools.HelprApplication;

public class SavePhotoTool {
	private static final String TAG = "savePhotoTool";

	public static File savePhote(Bitmap bitmap) {
		Context context = HelprApplication.getContext();
		boolean hasSDCard = false;
		if (Environment.getExternalStorageState().equals("mounted")) {
			hasSDCard = true;
		}
		Log.v("savePhotoTool", "savePhotoToolbHaveSdcard :" + hasSDCard);
		String pabsoluteath = context.getDir("Helpr", 1).getAbsolutePath();
		;
		File photoFile;
		if (hasSDCard) {
			pabsoluteath = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_DCIM).toString()
					+ "/Camera/";
			photoFile = new File(pabsoluteath);
			if (!photoFile.exists()) {
				photoFile.mkdirs();
			}

			Log.v(TAG, "savePhotoToolmediaStorageDir :" + photoFile);
		} else {
			pabsoluteath = context.getDir("Helpr", 1).getAbsolutePath();
			photoFile = new File(pabsoluteath);
			if (!photoFile.exists()) {
				photoFile.mkdirs();
			}
			
			Log.v(TAG, "savePhotoToolmediaAbsoluteDir :" + photoFile);
		}
		photoFile = new File(pabsoluteath, "img"
				+ System.currentTimeMillis() + ".jpg");

		FileOutputStream fileoutputstream;

		try {
			fileoutputstream = new FileOutputStream(photoFile);
		} catch (Exception e) {
			fileoutputstream = null;
		}
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileoutputstream);
		try {
			fileoutputstream.flush();
			fileoutputstream.close();
			scanMedia(photoFile.getAbsolutePath(), context);
			return photoFile;
		} catch (Exception e) {
			return null;
		}
	}

	public static void scanMedia(String filepath, Context context) {
		MediaScannerConnection.scanFile(context, new String[] { filepath },
				null, new OnScanCompletedListener() {

					@Override
					public void onScanCompleted(String path, Uri uri) {

					}
				});
	}
}
