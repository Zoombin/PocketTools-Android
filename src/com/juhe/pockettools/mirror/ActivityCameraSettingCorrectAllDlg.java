package com.juhe.pockettools.mirror;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.baidu.platform.comapi.map.i;
import com.baidu.platform.comapi.map.j;
import com.baidu.platform.comapi.map.k;

public class ActivityCameraSettingCorrectAllDlg {
	private static String h = "ActivityCameraDegreeAdjust";
	private static String i = "PreviewAdjustFront";
	private static String j = "PictureAdjustFront";
	private static String k = "PreviewAdjustBack";
	private static String l = "PictureAdjustBack";
	private static String p = "FirstStartFrontcamera";
	AlertDialog dialog;
	Context context;
	Camera camera;
	int d = 0;
	AlertDialog e;
	AlertDialog f;
	private String g = "ActivityCameraSettingCorrectAllDlg";
	private OrientationListener orientationlistener;
	private int n = 0;
	private int o = 0;

	public ActivityCameraSettingCorrectAllDlg(Context context, Camera camera, int paramInt, OrientationListener orientationlistener) {
		this.context = context;
		this.camera = camera;
		this.d = paramInt;
		this.orientationlistener = orientationlistener;
	}

	@SuppressLint("NewApi")
	public static int a(int paramInt, Context paramContext) {
		Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
		Camera.getCameraInfo(paramInt, localCameraInfo);
		int i1 = localCameraInfo.orientation;
		String str = l;
//		if (a(paramInt)) {
//			str = j;
//		}
		return i1
				+ paramContext.getSharedPreferences(str, 32768).getInt(str, 0);
	}

	public static int setConfigDisplayOrientation(boolean paramBoolean, Context context) {
		String str = k;
		if (paramBoolean) {
			str = i;
		}
		return context.getSharedPreferences(h, 32768).getInt(str, 0);
	}

//	public static void a(Context paramContext) {
//		AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext)
//				.create();
//		localAlertDialog.getWindow().clearFlags(2);
//		localAlertDialog.setCanceledOnTouchOutside(false);
//		localAlertDialog.show();
//		LinearLayout localLinearLayout = (LinearLayout) LayoutInflater.from(
//				paramContext).inflate(2130903117, null);
//		localAlertDialog.setContentView(localLinearLayout,
//				new ViewGroup.LayoutParams(-1, -1));
//		((Button) localLinearLayout.findViewById(2131362158))
//				.setOnClickListener(new j(localAlertDialog, paramContext));
//	}

//	public static void a(Context paramContext, boolean paramBoolean,
//			int paramInt) {
//		if (paramInt % 90 != 0) {
//			return;
//		}
//		SharedPreferences localSharedPreferences = paramContext
//				.getSharedPreferences(h, 32768);
//		String str = k;
//		if (paramBoolean) {
//			str = i;
//		}
//		int i1 = localSharedPreferences.getInt(str, 0);
//		localSharedPreferences.edit().putInt(str, (i1 + paramInt) % 360)
//				.commit();
//	}
//
//	public static boolean a(int paramInt) {
//		Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
//		Camera.getCameraInfo(paramInt, localCameraInfo);
//		return localCameraInfo.facing == 1;
//	}
//
//	private void d() {
//		this.e = new AlertDialog.Builder(context).create();
//		this.e.getWindow().clearFlags(2);
//		this.e.setCanceledOnTouchOutside(false);
//		this.e.show();
//		LinearLayout localLinearLayout = (LinearLayout) LayoutInflater.from(
//				context).inflate(2130903114, null);
//		ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(
//				-1, -1);
//		this.e.setContentView(localLinearLayout, localLayoutParams);
//		Button localButton = (Button) localLinearLayout
//				.findViewById(2131362153);
//		this.n = 0;
//		this.o = 0;
//		localButton.setOnClickListener(new h(this));
//		((Button) localLinearLayout.findViewById(2131362154))
//				.setOnClickListener(new i(this));
//		this.e.show();
//	}
//
//	private boolean e() {
//		String str = p;
//		return context.getSharedPreferences(str, 32768).getBoolean(str, true);
//	}
//
//	private void f() {
//		String str = p;
//		context.getSharedPreferences(str, 32768).edit().putBoolean(str, false)
//				.commit();
//	}
//
//	public void a() {
//		dialog = new AlertDialog.Builder(context).create();
//		dialog.getWindow().clearFlags(2);
//		dialog.setCanceledOnTouchOutside(false);
//		dialog.show();
//		View localView = LayoutInflater.from(context).inflate(2130903112, null);
//		ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(
//				-1, -1);
//		((Button) localView.findViewById(2131362143)).setOnClickListener(new g(
//				this));
//		dialog.setContentView(localView, localLayoutParams);
//	}
//
//	public void b() {
//		if (dialog != null) {
//			dialog.dismiss();
//		}
//		if (this.e != null) {
//			this.e.dismiss();
//		}
//		if (this.f != null) {
//			this.f.dismiss();
//		}
//	}
//
//	public void c() {
//		if (!e()) {
//			return;
//		}
//		f();
//		this.f = new AlertDialog.Builder(context).create();
//		this.f.getWindow().clearFlags(2);
//		this.f.setCanceledOnTouchOutside(false);
//		View localView = LayoutInflater.from(context).inflate(2130903115, null);
//		WindowManager.LayoutParams localLayoutParams = this.f.getWindow()
//				.getAttributes();
//		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//		((Activity) context).getWindowManager().getDefaultDisplay()
//				.getMetrics(localDisplayMetrics);
//		localLayoutParams.gravity = 48;
//		localLayoutParams.x = 0;
//		localLayoutParams.y = com.fotoable.helpr.Utils.k.a(context, 50.0F);
//		localLayoutParams.height = com.fotoable.helpr.Utils.k.a(context, 150.0F);
//		localLayoutParams.width = -2;
//		this.f.getWindow().setAttributes(localLayoutParams);
//		this.f.show();
//		LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(
//				-2, -2);
//		this.f.setContentView(localView, localLayoutParams1);
//		((FrameLayout) localView.findViewById(2131362155))
//				.setOnClickListener(new k(this));
//		((FrameLayout) localView.findViewById(2131362156))
//				.setOnClickListener(new l(this));
//	}

	public static abstract interface OrientationListener {
		public abstract void a();

		public abstract void a(int paramInt);

		public abstract void b();
	}
}
