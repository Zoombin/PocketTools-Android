package com.juhe.pockettools.violation;

import android.app.AlertDialog;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.platform.comapi.map.y;
import com.juhe.pockettools.R;

public class ViolationCitySelectDialog {
	OnCityListener cityListener;
	private AlertDialog dialog;
	private Context context;
	private ViolationCitySelectView violationcityselectview;

	public ViolationCitySelectDialog(Context context) {
		this.context = context;
		violationcityselectview = new ViolationCitySelectView(context, null);
	}

	public void dismiss() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	public void setListener(OnCityListener cityListener) {
		this.cityListener = cityListener;
	}

	public void initView(String paramString, CityEntity cityentity) {
		if ((paramString == null) || (cityentity == null)) {
			violationcityselectview = null;
			return;
		}
		if (dialog != null) {
			dialog.show();
			return;
		}
		dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(false);
		Window localWindow = dialog.getWindow();
		localWindow.clearFlags(2);
		localWindow.setWindowAnimations(R.style.datepicker_dialog_style);
		localWindow.setGravity(80);
		WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
		localLayoutParams.copyFrom(dialog.getWindow().getAttributes());
		localLayoutParams.width = -1;
		localLayoutParams.height = -2;
		dialog.show();
		dialog.getWindow().setAttributes(localLayoutParams);
		violationcityselectview.setProvinceWheelData(paramString, cityentity);
		violationcityselectview.setListener(new ViolationCitySelectView.OnCityListener() {
			
			@Override
			public void getCity(String province_code, CityEntity cityentity) {
				cityListener.getCity(province_code, cityentity);
				dialog.cancel();
			}
		});
		dialog.setContentView(violationcityselectview);
	}

	public static abstract interface OnCityListener {
		public abstract void getCity(String province_code, CityEntity cityentity);
	}
}
