package com.juhe.pockettools.mirror;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.zoombin.koudai.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.mirror.ActivityCameraSettingCorrectAllDlg.OrientationListener;

@SuppressLint("NewApi")
public class CameraMirrorActivity extends FullscreenActivity {
	private static final String TAG = "CamTestActivity";
	Preview preview;
	FrameLayout surfaceContainer;
	FrameLayout mirror_bottom_bar;
	ImageView btn_close_mirror;
	ImageView btn_correct_mirror;
	Camera camera;
	Activity activity;
	Context context;
	ActivityCameraSettingCorrectAllDlg settingdlg;
	int displayorientation;
	private int cameraid;
	private boolean onoff = false;

	public int getCameraDisplayOrientation(int cameraid) {
		Camera.CameraInfo info = new Camera.CameraInfo();
		Camera.getCameraInfo(cameraid, info);
		int rotation = getWindowManager().getDefaultDisplay().getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		return result;
	}

	public void initSettingDlg() {
		settingdlg = new ActivityCameraSettingCorrectAllDlg(this, camera,
				cameraid, new OrientationListener() {

					@Override
					public void b() {
						// TODO Auto-generated method stub

					}

					@Override
					public void a(int paramInt) {
						// TODO Auto-generated method stub

					}

					@Override
					public void a() {
						// TODO Auto-generated method stub

					}
				});
	}

	public void showSettingDlg() {
		if (settingdlg == null) {
			return;
		}
//		settingdlg.b();
//		settingdlg.a();
	}

	public void showOrientationDlg() {
		if (settingdlg == null) {
			return;
		}
//		settingdlg.b();
//		settingdlg.c();
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		context = this;
		activity = this;
		setContentView(R.layout.activity_camera_mirror_main);
		surfaceContainer = ((FrameLayout) findViewById(R.id.surfaceContainer));
		mirror_bottom_bar = ((FrameLayout) findViewById(R.id.mirror_bottom_bar));
		btn_close_mirror = ((ImageView) findViewById(R.id.btn_close_mirror));
		btn_correct_mirror = ((ImageView) findViewById(R.id.btn_correct_mirror));
		preview = new Preview(this, new Preview.CameraSizeL() {

			@Override
			public void setSize(Size size) {

			}
		});
		surfaceContainer.addView(preview);
		btn_close_mirror.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_correct_mirror.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showSettingDlg();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (onoff) {
			onoff = false;
			if (camera != null) {
				camera.setPreviewCallback(null);
				camera.stopPreview();
//				preview.startPreview(null);
				camera.release();
				camera = null;
			}
		}
		if (settingdlg != null) {
//			settingdlg.b();
			settingdlg = null;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		Camera.CameraInfo cameraInfo;
		int mNumberOfCameras;
		int mCameraId = 0;
		try {
			mNumberOfCameras = Camera.getNumberOfCameras();
			cameraInfo = new Camera.CameraInfo();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "摄像头异常！", Toast.LENGTH_SHORT).show();
			return;
		}
		for (int camIdx = 0; camIdx < mNumberOfCameras; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
				mCameraId = camIdx;
				cameraid = mCameraId;
				break;
			}
		}
		if (cameraid >= mNumberOfCameras) {
			finish();
		} else {
			camera = Camera.open(cameraid);
			initSettingDlg();
			preview.startPreview(camera);
			displayorientation = (ActivityCameraSettingCorrectAllDlg.setConfigDisplayOrientation(true, this) + getCameraDisplayOrientation(cameraid));
			camera.setDisplayOrientation(displayorientation % 360);
			showOrientationDlg();
			onoff = true;
		}
	}

}
