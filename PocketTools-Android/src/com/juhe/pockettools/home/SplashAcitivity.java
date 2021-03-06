package com.juhe.pockettools.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.zoombin.koudai.R;
import com.juhe.pockettools.utils.Constants;
import com.juhe.pockettools.utils.HelprCommUtil;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;

/**
 * 宣传页
 * 
 * @author daiye
 * 
 */
public class SplashAcitivity extends FullscreenActivity {
	View view;
	private AlphaAnimation animation;
	private SplashAD splashAD;
	private ViewGroup container;

	private void startAnim() {
		animation = new AlphaAnimation(1.0F, 1.0F);
		animation.setDuration(3000L);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(SplashAcitivity.this,
						HelprActivity.class);
				startActivity(intent);
				overridePendingTransition(0, R.anim.hold);
				finish();
			}
		});
	}

	private void createShortcut() {
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource
				.fromContext(this, R.drawable.logo);
		shortcut.putExtra("duplicate", false);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				getString(R.string.app_name));
		Intent shortcutIntent = new Intent();
		shortcutIntent.setClass(this, SplashAcitivity.class);
		shortcutIntent.setAction(Intent.ACTION_MAIN);
		shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		sendBroadcast(shortcut);
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		view = View.inflate(this, R.layout.activity_splash_screen, null);

		container = (ViewGroup) this.findViewById(R.id.splash_container);
		splashAD = new SplashAD(this, container, Constants.APPID,
				Constants.SplashPosID, new SplashADListener() {

					@Override
					public void onNoAD(int arg0) {
						// TODO Auto-generated method stub
						Log.d("Tag", "没广告");
					}

					@Override
					public void onADPresent() {
						// TODO Auto-generated method stub
						Log.d("Tag", "广告有");
					}

					@Override
					public void onADDismissed() {
						// TODO Auto-generated method stub
						Log.d("Tag", "广告被关闭");
					}
				});

		if (HelprCommUtil.hasNavigationBar(this)) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
		setContentView(view);
		SharedPreferences sharedpreferences = getSharedPreferences("config", 0);
		if (!sharedpreferences.getBoolean("isCreatedIcon", false)) {
			SharedPreferences.Editor editor = sharedpreferences.edit();
			editor.putBoolean("isCreatedIcon", true);
			editor.commit();
			createShortcut();
		}
		startAnim();
	}
}
