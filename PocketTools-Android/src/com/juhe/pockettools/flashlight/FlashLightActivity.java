package com.juhe.pockettools.flashlight;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;

//import com.fotoable.helpr.wallpaper.w;

public class FlashLightActivity extends FullscreenActivity {
	private ToggleButton button_open_flashlight;
	private ImageButton button_close_flashlayout;
	private FrameLayout flashlight_container;
	private Camera camera;
	private Camera.Parameters parameters;
	private boolean cameraopen = false;

	 private CompoundButton.OnCheckedChangeListener buttonflashlightlistener = new ButtonFlashlightListener(this);
	 private View.OnClickListener buttoncloseflashlayoutlistener = new buttonCloseFlashlayoutListener(this);

	private void setFlashOn() {
		try {
			if ((parameters != null)
					&& (parameters.getSupportedFlashModes() != null)) {
				if (parameters.getSupportedFlashModes().contains(Parameters.FLASH_MODE_TORCH)) {
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameters);
					return;
				}
				flashlight_container.setBackgroundColor(getResources()
						.getColor(R.color.white));
				setScreenBrightness(1.0F);
				return;
			}
			flashlight_container.setBackgroundColor(getResources().getColor(
					R.color.white));
			setScreenBrightness(1.0F);
			return;
		} catch (Exception e) {

		}
	}

	private void setScreenBrightness(float screenBrightness) {
		WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
		layoutparams.screenBrightness = screenBrightness;
		getWindow().setAttributes(layoutparams);
		getWindow().addFlags(128);
	}

	private void setFlashOff() {
		try {
			if ((parameters != null)
					&& (parameters.getSupportedFlashModes() != null)) {
				if (parameters.getSupportedFlashModes().contains(Parameters.FLASH_MODE_OFF)) {
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(parameters);
				}
			} else {
				// if (Build.VERSION.SDK_INT < 16) {
				// flashlight_container.setBackgroundDrawable(new
				// BitmapDrawable(
				// getResources(), w.a().d()));
				// }
				// for (;;) {
				// a(-1.0F);
				// return;
				// flashlight_container.setBackground(new
				// BitmapDrawable(getResources(), w
				// .a().d()));
				// }
			}
			return;
		} catch (Exception e) {

		}
	}

	private void closeCamera() {
		setScreenBrightness(-1.0F);
		if (cameraopen) {
			cameraopen = false;
			if (camera != null) {
				camera.setPreviewCallback(null);
				camera.stopPreview();
				camera.release();
				camera = null;
			}
		}
	}

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_flashlight_main);
		// ((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		button_open_flashlight = ((ToggleButton) findViewById(R.id.button_open_flashlight));
		button_open_flashlight.setChecked(false);
		button_open_flashlight.setOnCheckedChangeListener(buttonflashlightlistener);
		button_close_flashlayout = ((ImageButton) findViewById(R.id.button_close_flashlayout));
		button_close_flashlayout.setOnClickListener(buttoncloseflashlayoutlistener);
		flashlight_container = ((FrameLayout) findViewById(R.id.flashlight_container));
		flashlight_container.setClickable(true);
		try {
			camera = Camera.open();
			camera.startPreview();
			parameters = camera.getParameters();
			setFlashOn();
			button_open_flashlight.setChecked(true);
			cameraopen = true;
			return;
		} catch (Exception localException) {
		}
	}

	@Override
	protected void onDestroy() {
		closeCamera();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getRepeatCount() == 0)) {
			closeCamera();
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		super.onPause();
		setFlashOff();
		button_open_flashlight.setChecked(false);
	}

	class ButtonFlashlightListener implements CompoundButton.OnCheckedChangeListener {
		ButtonFlashlightListener(FlashLightActivity activity) {
		}

		public void onCheckedChanged(CompoundButton paramCompoundButton,
				boolean checked) {
			if (checked) {
				setFlashOn();
				return;
			}
			setFlashOff();
		}
	}

	class buttonCloseFlashlayoutListener implements View.OnClickListener {
		buttonCloseFlashlayoutListener(FlashLightActivity activtiy) {
		}

		public void onClick(View paramView) {
			closeCamera();
			finish();
		}
	}
}
