package com.juhe.pockettools.secret;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.juhe.pockettools.R;
import com.juhe.pockettools.secret.LocusPassWordView.OnCompleteListener;
import com.juhe.pockettools.secretalbum.SecretAlbumActivity;
import com.juhe.pockettools.utils.Config;

public class LoginActivity extends Activity {

	private LocusPassWordView lpwv;
	private static final int SPLASH_SHOW_TIME = 2000;
	Handler handler = new Handler();
	Intent intent = new Intent();

	TextView title;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		title = (TextView) findViewById(R.id.login_toast);
		lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);

		if (lpwv.isPasswordEmpty()) {
			title.setVisibility(View.GONE);
			lpwv.setVisibility(View.GONE);

			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					intent.setClass(getApplicationContext(),
							SecretAlbumActivity.class);
					startActivity(intent);
					finish();
				}
			}, SPLASH_SHOW_TIME);
		} else {
			lpwv.setVisibility(View.VISIBLE);
			lpwv.setOnCompleteListener(new OnCompleteListener() {
				@Override
				public void onComplete(String mPassword) {
					// 如果密码正确,则进入主页面。
					if (lpwv.verifyPassword(mPassword)) {
						// Toast.makeText(LoginActivity.this, "登录成功！",
						// Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								SecretAlbumActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(LoginActivity.this, "密码输入错误,请重新输入",
								Toast.LENGTH_SHORT).show();
						lpwv.clearPassword();
					}
				}
			});
		}
	}
}