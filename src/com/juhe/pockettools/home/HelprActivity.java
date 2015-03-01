package com.juhe.pockettools.home;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.tabrootbutton.mainTabScrollView;
import com.juhe.pockettools.tabrootbutton.mainTabScrollView.TabL;
import com.juhe.pockettools.utils.HelprCommUtil;

@SuppressLint("NewApi")
public class HelprActivity extends FullscreenActivity {

	View view;
	private GridView gd_modules;
	private ModulesAdapter adapter;
	private mainTabScrollView main_tabview;
	List<ModuleInfo> infos;
	List<ModuleInfo> dailyinfos;
	List<ModuleInfo> lifeinfos;
	List<ModuleInfo> phoneinfos;
	List<ModuleInfo> marketinfos;
	
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		view = View.inflate(this, R.layout.activity_helpr_activity, null);
		if (HelprCommUtil.hasNavigationBar(this)) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
		setContentView(view);

		initModuleData();
		
		main_tabview = (mainTabScrollView) findViewById(R.id.main_tabview);
		main_tabview.setListener(new TabL() {

			@Override
			public void setTab(int textresource) {
				if (textresource == R.string.daily_tool) {
					infos = dailyinfos;
				} else if (textresource == R.string.Life_query) {
					infos = lifeinfos;
				} else if (textresource == R.string.phone_housekeeper) {
					infos = phoneinfos;
				} else if (textresource == R.string.pocket_market) {
					infos = marketinfos;
				}
				adapter.setData(infos);
			}
		});

		infos = dailyinfos;
		gd_modules = (GridView) findViewById(R.id.gd_modules);
		adapter = new ModulesAdapter(this, infos);
		gd_modules.setAdapter(adapter);
	}
	
	private void initModuleData() {
		// 日常工具
		dailyinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info1 = new ModuleInfo();
		info1.setName("新闻");
		info1.setId(1);
		dailyinfos.add(info1);
		
		ModuleInfo info2 = new ModuleInfo();
		info2.setName("空气质量");
		info2.setId(2);
		dailyinfos.add(info2);
		
		ModuleInfo info3 = new ModuleInfo();
		info3.setName("天气预报");
		info3.setId(3);
		dailyinfos.add(info3);
		
		ModuleInfo info4 = new ModuleInfo();
		info4.setName("车辆违章");
		info4.setId(4);
		dailyinfos.add(info4);
		
		ModuleInfo info5 = new ModuleInfo();
		info5.setName("来电查询");
		info5.setId(5);
		dailyinfos.add(info5);
		
		ModuleInfo info6 = new ModuleInfo();
		info6.setName("聊天机器人");
		info6.setId(6);
		dailyinfos.add(info6);
		
		ModuleInfo info7 = new ModuleInfo();
		info7.setName("苹果序列号");
		info7.setId(7);
		dailyinfos.add(info7);
		
		// 生活服务
		lifeinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info8 = new ModuleInfo();
		info8.setName("星座");
		info8.setId(8);
		lifeinfos.add(info8);
		
		ModuleInfo info9 = new ModuleInfo();
		info9.setName("老黄历");
		info9.setId(9);
		lifeinfos.add(info9);
		
		ModuleInfo info10 = new ModuleInfo();
		info10.setName("周公解梦");
		info10.setId(10);
		lifeinfos.add(info10);
		
		ModuleInfo info11 = new ModuleInfo();
		info11.setName("秘密相册");
		info11.setId(11);
		lifeinfos.add(info11);
		
		ModuleInfo info12 = new ModuleInfo();
		info12.setName("火车订票");
		info12.setId(12);
		lifeinfos.add(info12);
		
		ModuleInfo info13 = new ModuleInfo();
		info13.setName("加油站");
		info13.setId(13);
		lifeinfos.add(info13);
		
		ModuleInfo info14 = new ModuleInfo();
		info14.setName("快递");
		info14.setId(14);
		lifeinfos.add(info14);
		
		// 阅读发现
		phoneinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info15 = new ModuleInfo();
		info15.setName("电影");
		info15.setId(15);
		phoneinfos.add(info15);
		
		ModuleInfo info16 = new ModuleInfo();
		info16.setName("航班动态");
		info16.setId(16);
		phoneinfos.add(info16);
		
		ModuleInfo info17 = new ModuleInfo();
		info17.setName("停车场");
		info17.setId(17);
		phoneinfos.add(info17);
		
		ModuleInfo info18 = new ModuleInfo();
		info18.setName("POI");
		info18.setId(18);
		phoneinfos.add(info18);
		
		ModuleInfo info19 = new ModuleInfo();
		info19.setName("条码比价");
		info19.setId(19);
		phoneinfos.add(info19);
		
		ModuleInfo info20 = new ModuleInfo();
		info20.setName("话费充值");
		info20.setId(20);
		phoneinfos.add(info20);
		
		ModuleInfo info21 = new ModuleInfo();
		info21.setName("游戏充值");
		info21.setId(21);
		phoneinfos.add(info21);
		
		ModuleInfo info22 = new ModuleInfo();
		info22.setName("彩票购买");
		info22.setId(22);
		phoneinfos.add(info22);
		
		// 口袋商城
		marketinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info23 = new ModuleInfo();
		info23.setName("流量直充");
		info23.setId(23);
		marketinfos.add(info23);
		
		ModuleInfo info24 = new ModuleInfo();
		info24.setName("尺子");
		info24.setId(24);
		marketinfos.add(info24);
		
		ModuleInfo info25 = new ModuleInfo();
		info25.setName("镜子");
		info25.setId(25);
		marketinfos.add(info25);
		
		ModuleInfo info26 = new ModuleInfo();
		info26.setName("科学计算器");
		info26.setId(26);
		marketinfos.add(info26);
		
		ModuleInfo info27 = new ModuleInfo();
		info27.setName("尺码对照表");
		info27.setId(27);
		marketinfos.add(info27);
		
		ModuleInfo info28 = new ModuleInfo();
		info28.setName("单位换算");
		info28.setId(28);
		marketinfos.add(info28);
		
		ModuleInfo info29 = new ModuleInfo();
		info29.setName("汇率换算");
		info29.setId(29);
		marketinfos.add(info29);
		
		ModuleInfo info30 = new ModuleInfo();
		info30.setName("手电筒");
		info30.setId(30);
		marketinfos.add(info30);
	}
}
