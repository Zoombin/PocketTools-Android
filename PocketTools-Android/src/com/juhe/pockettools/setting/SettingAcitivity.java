package com.juhe.pockettools.setting;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.HorizontalListView;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;

public class SettingAcitivity extends FullscreenActivity implements
		OnItemClickListener {

	ImageView img_bg;
	TopActiveBarView action_bar;
	HorizontalListView wapper_bg_listview;
	WapperBgAdapter adapter;
	
	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_setting);
		img_bg = ((ImageView) findViewById(R.id.img_bg));
		img_bg.setBackgroundResource(Config.getBgDrawableResId());
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setTiltleText("设置背景");
		action_bar.setSplitLineVisible(false);
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				finish();
			}
		});
		wapper_bg_listview = ((HorizontalListView) findViewById(R.id.wapper_bg_listview));
		List<WapperBgEntity> list = new ArrayList<WapperBgEntity>();
		WapperBgEntity entity1 = new WapperBgEntity();
		entity1.setId(0);
		entity1.setBg(R.drawable.bg1);
		list.add(entity1);
		
		WapperBgEntity entity2 = new WapperBgEntity();
		entity2.setId(0);
		entity2.setBg(R.drawable.bg2);
		list.add(entity2);
		
		WapperBgEntity entity3 = new WapperBgEntity();
		entity3.setId(0);
		entity3.setBg(R.drawable.bg3);
		list.add(entity3);
		
		adapter = new WapperBgAdapter(this, list);
		wapper_bg_listview.setAdapter(adapter);
		wapper_bg_listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Config.setBgIndex(position);
		adapter.setSelected(position);
		img_bg.setBackgroundResource(Config.getBgDrawableResId());
	}
}
