package com.juhe.pockettools.applesn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

//import com.fotoable.helpr.wallpaper.w;

public class AppleSnActivity extends FullscreenActivity {
//	private static final String TAG = "AppleSnActivity";
	TopActiveBarView action_bar;
	private EditText edit_sn_number;
	private Button btn_sn_search;
	private TextView tv_help;
	private LinearLayout ly_info;
	private TextView phone_model;
	private TextView serial_number;
	private TextView imei_number;
	private TextView active;
	private TextView warranty_status;
	private TextView warranty;
	private TextView tele_support;
	private TextView tele_support_status;
	private TextView made_area;
	private TextView end_date;
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_applesn_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		
		btn_sn_search = ((Button) findViewById(R.id.btn_sn_search));
		btn_sn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
			}
		});
		edit_sn_number = ((EditText) findViewById(R.id.edit_sn_number));
		edit_sn_number.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int arg1, KeyEvent arg2) {
				if (arg1 == 6) {
					((InputMethodManager) v.getContext().getSystemService(
							Context.INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(v.getWindowToken(), 0);
					getData();
					return true;
				}
				return false;
			}
		});
		
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setTiltleText("苹果保修查询");
		action_bar.setSplitLineVisible(false);
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void cancel() {
				finish();
			}

			@Override
			public void query() {

			}
		});
		tv_help = (TextView) findViewById(R.id.tv_help);
		ly_info = (LinearLayout) findViewById(R.id.ly_info);
		phone_model = (TextView) findViewById(R.id.phone_model);
		serial_number = (TextView) findViewById(R.id.serial_number);
		imei_number = (TextView) findViewById(R.id.imei_number);
		active = (TextView) findViewById(R.id.active);
		warranty_status = (TextView) findViewById(R.id.warranty_status);
		warranty = (TextView) findViewById(R.id.warranty);
		tele_support = (TextView) findViewById(R.id.tele_support);
		tele_support_status = (TextView) findViewById(R.id.tele_support_status);
		made_area = (TextView) findViewById(R.id.made_area);
		end_date = (TextView) findViewById(R.id.end_date);
		action_bar.setProgressVisiable(View.INVISIBLE);
	}
	
	private void getData() {
		action_bar.setProgressVisiable(View.VISIBLE);
		edit_sn_number.clearFocus();
		hideSoftKeyborad(edit_sn_number);
		if (edit_sn_number.getText().toString().length() > 0) {
			String sn = edit_sn_number.getText().toString();
			Parameters params = new Parameters();
			params.add("sn", sn);
			params.add("key", "e2a4d76a04bc042a8b47045baf6c9873");
			JuheData.executeWithAPI(37,
					"http://apis.juhe.cn/appleinfo/index",
					JuheData.GET, params, new DataCallBack() {

						@Override
						public void resultLoaded(int err, String reason,
								String result) {
							action_bar.setProgressVisiable(View.INVISIBLE);
							
							if (err == 0) {
								AppleSnEntity entity = new Gson().fromJson(result, AppleSnEntity.class);
								if (entity.getError_code() != 0 && entity.getError_code() != 200) {
									Toast.makeText(getApplicationContext(), entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}
								tv_help.setVisibility(View.GONE);
								ly_info.setVisibility(View.VISIBLE);
								AppleSnEntity.Result resultentity = entity.getResult();
								phone_model.setText(resultentity.getPhone_model());
								serial_number.setText(resultentity.getSerial_number());
								imei_number.setText(resultentity.getImei_number());
								active.setText(resultentity.getActive());
								warranty_status.setText(resultentity.getWarranty_status());
								warranty.setText(resultentity.getWarranty());
								tele_support.setText(resultentity.getTele_support());
								tele_support_status.setText(resultentity.getTele_support_status());
								made_area.setText(resultentity.getMade_area());
								end_date.setText(resultentity.getEnd_date());
							} else {
								Toast.makeText(getApplicationContext(), reason,
										Toast.LENGTH_SHORT).show();
							}
						}
					});
		}
	}
	
	public void hideSoftKeyborad(View paramView) {
		if (paramView != null) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
		}
	}

}
