package com.juhe.pockettools.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.HelprApplication;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.RightPublishTextView;
import com.juhe.pockettools.constelltion.ConstalltionConstants;
import com.juhe.pockettools.constelltion.ConstalltionDayEntity;
import com.juhe.pockettools.constelltion.ConstelltionBasicView;
import com.juhe.pockettools.constelltion.ConstelltionDetailInfoView;
import com.juhe.pockettools.constelltion.ConstelltionMainActivity;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class ConstelltionFragment extends Fragment {

	private LinearLayout ly_constelltion;
	private ConstelltionBasicView constelltion_basicview;
	private ConstelltionDetailInfoView constelltion_detail_info;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_constelltion, container, false);
		createView(v);
		initData();
		return v;
	}
	
	private void createView(View v) {
		constelltion_basicview = ((ConstelltionBasicView) v.findViewById(R.id.constelltion_basicview));
		constelltion_detail_info = ((ConstelltionDetailInfoView) v.findViewById(R.id.constelltion_detail_info));
		
		ly_constelltion = (LinearLayout) v.findViewById(R.id.ly_constelltion);
		ly_constelltion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HelprApplication.getContext(), ConstelltionMainActivity.class);
				startActivity(intent);
			}
		});
		constelltion_basicview.setSelectedConstelltion(0);
	}
	
	private void initData() {
		Parameters params = new Parameters();
		params.add("consName", ConstalltionConstants.names[0]);
		params.add("type", "today");
		params.add("key", "4d928c345f98533ccad7e86942bd36f8");
		JuheData.executeWithAPI(58,
				"http://web.juhe.cn:8080/constellation/getAll", JuheData.GET,
				params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
								ConstalltionDayEntity entity = new Gson()
										.fromJson(result,
												ConstalltionDayEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(
											HelprApplication.getContext(),
											Integer.toString(entity
													.getError_code()),
											Toast.LENGTH_SHORT).show();
									return;
								}

								constelltion_detail_info.setDayData(entity);
								constelltion_detail_info.disableTitleLayout();
								constelltion_basicview.setSelectedConstelltion(0);
								constelltion_basicview.setData(entity);
						} else {
							Toast.makeText(HelprApplication.getContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
