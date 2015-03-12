package com.juhe.pockettools.dream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.home.SplashAcitivity;
import java.util.ArrayList;

public class DreamMainActivity extends FullscreenActivity {
	private static final String TAG = "DreamMainActivity";
	private ArrayList<Category.Result> categorylist = new ArrayList<Category.Result>();
	FragmentManager fragmentmanager;
	
	public ArrayList<Category.Result> getList() {
		return categorylist;
	}

	public void close() {
		fragmentmanager = getSupportFragmentManager();
		if (fragmentmanager.getBackStackEntryCount() > 0) {
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentmanager.popBackStack(
					fragmentmanager.getBackStackEntryAt(
							-1 + fragmentmanager.getBackStackEntryCount())
							.getId(), 1);
			transaction.commitAllowingStateLoss();
			return;
		}
		finish();
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_dream_main);
		if (bundle == null) {
			DreamMainFragment fragment = new DreamMainFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_frame, fragment).commit();
		}
		// new Thread(new f(this)).start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			close();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
