package com.juhe.pockettools.train;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.HashMap;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class TrainMainItem2View extends FrameLayout {
	private Context context;
//	private q b;
	private EditText train_edit_times;
	private Button train_btn_search;
	private TrainTimesSearchResultView train_times_search_result;

	public TrainMainItem2View(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainMainItem2View(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_train_main_item2, this,
				true);
		train_edit_times = ((EditText) findViewById(R.id.train_edit_times));
		train_btn_search = ((Button) findViewById(R.id.train_btn_search));
		train_times_search_result = ((TrainTimesSearchResultView) findViewById(R.id.train_times_search_result));
//		train_times_search_result.setListener(new n(this));
		train_btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
			}
		});
//		train_edit_times.setOnEditorActionListener(new p(this));
	}

	private void getData() {
		String name = train_edit_times.getText().toString();
		if (train_edit_times == null || train_edit_times.getText().toString().length() == 0) {
			return;
		}
		
		Parameters params = new Parameters();
		params.add("name", name);
		params.add("key", "5d13fc2cdf35af76249cfb8f8c67d424");
		JuheData.executeWithAPI(22,
				"http://apis.juhe.cn/train/s",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						
						if (err == 0) {
							TrainSEntity entity = new Gson().fromJson(result, TrainSEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(context, entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							
							train_times_search_result.setData(entity.getResult());
						} else {
							Toast.makeText(context, reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
//	private void b() {
//		if ((train_edit_times != null) && (train_edit_times.getText().toString().length() > 0)) {
//			if (this.b != null) {
//				this.b.b();
//			}
//			k.a((Activity) this.a);
//			train_times_search_result.a(train_edit_times.getText().toString(), null, null);
//			HashMap localHashMap = new HashMap();
//			localHashMap.put("type", "车次查询");
//			FlurryAgent.logEvent("HelprSearch_train火车查询", localHashMap);
//		}
//	}
//
//	public void setListener(q paramq) {
//		this.b = paramq;
//	}
}
