package com.juhe.pockettools.mobilelocale;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.mobilelocale.MobileLocaleEntity.Result;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
//import com.juhe.pockettools.wallpaper.w;

public class MobileLocaleMainActivity extends FullscreenActivity {
	private TopActiveBarView action_bar;
	private MobileLocaleContentView locale_content;
	private EditText edit_locale_number;
	private Button btn_locale_search;

	private void getData(String phonenum) {
		String str1 = phonenum.trim();
		if (str1.equals("")) {
			locale_content.setVisibility(View.INVISIBLE);
			Toast.makeText(this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
			return;
		}
		
		action_bar.setProgressVisiable(View.VISIBLE);
		
		Parameters params = new Parameters();
		params.add("phone", str1);
		params.add("key", "b8b61acf82e1866e41a67c53e295f51b");
		JuheData.executeWithAPI(11,
				"http://apis.juhe.cn/mobile/get",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						action_bar.setProgressVisiable(View.INVISIBLE);
						if (err == 0) {
							MobileLocaleEntity entity = new Gson().fromJson(result, MobileLocaleEntity.class);
							if (entity.getError_code() != 0) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							locale_content.setVisibility(View.VISIBLE);
							Result re = entity.getResult();
							locale_content.setData(re.getCompany(), re.getCard(), re.getProvince() + "  " + re.getCity(), re.getAreacode(),  re.getZip());
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_mobile_locale_main);
//		((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		locale_content = ((MobileLocaleContentView) findViewById(R.id.locale_content));
		edit_locale_number = ((EditText) findViewById(R.id.edit_locale_number));
		btn_locale_search = ((Button) findViewById(R.id.btn_locale_search));
		btn_locale_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData(edit_locale_number.getText().toString());
			}
		});
		action_bar.setTiltleText("手机归属地");
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void cancel() {
				finish();
			}
			
			@Override
			public void a() {
				
			}
		});
		action_bar.setProgressVisiable(View.INVISIBLE);
	}
}
