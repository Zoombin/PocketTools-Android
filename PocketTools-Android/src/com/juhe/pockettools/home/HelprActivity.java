package com.juhe.pockettools.home;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.HelprCommUtil;

@SuppressLint("NewApi")
public class HelprActivity extends FullscreenActivity implements
		android.widget.RadioGroup.OnCheckedChangeListener {

	View view;
	private GridView gd_modules;
	private ModulesAdapter adapter;
	List<ModuleInfo> infos;
	List<ModuleInfo> toolinfos;
	List<ModuleInfo> lifeinfos;
	List<ModuleInfo> readinfos;
	private RadioGroup radioGroup;
	private RadioButton btn_tool;
	private RadioButton btn_life;
	private RadioButton btn_read;

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		view = View.inflate(this, R.layout.activity_helpr_activity, null);
		if (HelprCommUtil.hasNavigationBar(this)) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
		setContentView(view);

		initModuleData();

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		int width = displayMetrics.widthPixels / 3;
		
		btn_tool = (RadioButton) findViewById(R.id.btn_tool);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width * 120 / 250);
		btn_tool.setLayoutParams(params);
		btn_life = (RadioButton) findViewById(R.id.btn_life);
		btn_life.setLayoutParams(params);
		btn_read = (RadioButton) findViewById(R.id.btn_read);
		btn_read.setLayoutParams(params);
		radioGroup.setOnCheckedChangeListener(this);

		infos = toolinfos;
		gd_modules = (GridView) findViewById(R.id.gd_modules);
		adapter = new ModulesAdapter(this, infos);
		gd_modules.setAdapter(adapter);
	}

	private void initModuleData() {
		// 日常工具
		toolinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info1 = new ModuleInfo();
		info1.setName("新闻");
		info1.setIcon(R.drawable.gr_new);
		info1.setId(1);
		toolinfos.add(info1);

		ModuleInfo info2 = new ModuleInfo();
		info2.setName("空气质量");
		info2.setIcon(R.drawable.gr_pm25);
		info2.setId(2);
		toolinfos.add(info2);

		ModuleInfo info3 = new ModuleInfo();
		info3.setName("天气预报");
		info3.setIcon(R.drawable.gr_weather);
		info3.setId(3);
		toolinfos.add(info3);

		ModuleInfo info4 = new ModuleInfo();
		info4.setName("车辆违章");
		info4.setIcon(R.drawable.gr_violation);
		info4.setId(4);
		toolinfos.add(info4);

		ModuleInfo info5 = new ModuleInfo();
		info5.setName("来电查询");
		info5.setIcon(R.drawable.gr_mobilelocale);
		info5.setId(5);
		toolinfos.add(info5);

		ModuleInfo info6 = new ModuleInfo();
		info6.setName("聊天机器人");
		info6.setIcon(R.drawable.gr_tuling);
		info6.setId(6);
		toolinfos.add(info6);

		ModuleInfo info7 = new ModuleInfo();
		info7.setName("苹果序列号");
		info7.setIcon(R.drawable.gr_applesn);
		info7.setId(7);
		toolinfos.add(info7);

		// 生活服务
		lifeinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info8 = new ModuleInfo();
		info8.setName("星座");
		info8.setIcon(R.drawable.gr_constelltion);
		info8.setId(8);
		lifeinfos.add(info8);

		ModuleInfo info9 = new ModuleInfo();
		info9.setName("老黄历");
		info9.setIcon(R.drawable.gr_calendar);
		info9.setId(9);
		lifeinfos.add(info9);

		ModuleInfo info10 = new ModuleInfo();
		info10.setName("周公解梦");
		info10.setIcon(R.drawable.gr_dream);
		info10.setId(10);
		lifeinfos.add(info10);

		ModuleInfo info11 = new ModuleInfo();
		info11.setName("秘密相册");
		info11.setIcon(R.drawable.gr_secret);
		info11.setId(11);
		lifeinfos.add(info11);

		ModuleInfo info12 = new ModuleInfo();
		info12.setName("火车订票");
		info12.setIcon(R.drawable.gr_train);
		info12.setId(12);
		lifeinfos.add(info12);

		ModuleInfo info13 = new ModuleInfo();
		info13.setName("加油站");
		info13.setIcon(R.drawable.gr_oil);
		info13.setId(13);
		lifeinfos.add(info13);

		ModuleInfo info14 = new ModuleInfo();
		info14.setName("快递");
		info14.setIcon(R.drawable.gr_courier);
		info14.setId(14);
		lifeinfos.add(info14);

		// 阅读发现
		readinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info15 = new ModuleInfo();
		info15.setName("电影");
		info15.setIcon(R.drawable.gr_moive);
		info15.setId(15);
		readinfos.add(info15);

		ModuleInfo info16 = new ModuleInfo();
		info16.setName("航班动态");
		info16.setIcon(R.drawable.gr_air);
		info16.setId(16);
		readinfos.add(info16);

		ModuleInfo info17 = new ModuleInfo();
		info17.setName("停车场");
		info17.setIcon(R.drawable.gr_stop);
		info17.setId(17);
		readinfos.add(info17);

		ModuleInfo info18 = new ModuleInfo();
		info18.setName("POI");
		info18.setIcon(R.drawable.gr_poi);
		info18.setId(18);
		readinfos.add(info18);

		ModuleInfo info19 = new ModuleInfo();
		info19.setName("条码比价");
		info19.setIcon(R.drawable.gr_qrcode);
		info19.setId(19);
		readinfos.add(info19);

		ModuleInfo info20 = new ModuleInfo();
		info20.setName("话费充值");
		info20.setIcon(R.drawable.gr_phone);
		info20.setId(20);
		readinfos.add(info20);

		ModuleInfo info21 = new ModuleInfo();
		info21.setName("游戏充值");
		info21.setIcon(R.drawable.gr_game);
		info21.setId(21);
		readinfos.add(info21);

		ModuleInfo info22 = new ModuleInfo();
		info22.setName("彩票购买");
		info22.setIcon(R.drawable.gr_caipiao);
		info22.setId(22);
		readinfos.add(info22);

		ModuleInfo info23 = new ModuleInfo();
		info23.setName("流量直充");
		info23.setIcon(R.drawable.gr_liuliang);
		info23.setId(23);
		readinfos.add(info23);

		ModuleInfo info24 = new ModuleInfo();
		info24.setName("尺子");
		info24.setIcon(R.drawable.gr_ruler);
		info24.setId(24);
		readinfos.add(info24);

		ModuleInfo info25 = new ModuleInfo();
		info25.setName("镜子");
		info25.setIcon(R.drawable.gr_mirror);
		info25.setId(25);
		readinfos.add(info25);

		ModuleInfo info26 = new ModuleInfo();
		info26.setName("科学计算器");
		info26.setIcon(R.drawable.gr_calculator);
		info26.setId(26);
		readinfos.add(info26);

		ModuleInfo info27 = new ModuleInfo();
		info27.setName("尺码对照表");
		info27.setIcon(R.drawable.gr_sizatable);
		info27.setId(27);
		readinfos.add(info27);

		ModuleInfo info28 = new ModuleInfo();
		info28.setName("单位换算");
		info28.setIcon(R.drawable.gr_unit);
		info28.setId(28);
		readinfos.add(info28);

		ModuleInfo info29 = new ModuleInfo();
		info29.setName("汇率换算");
		info29.setIcon(R.drawable.gr_exchange);
		info29.setId(29);
		readinfos.add(info29);

		ModuleInfo info30 = new ModuleInfo();
		info30.setName("手电筒");
		info30.setId(30);
		info30.setIcon(R.drawable.gr_flashlight);
		readinfos.add(info30);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.btn_tool) {
			infos = toolinfos;
		} else if (checkedId == R.id.btn_life) {
			infos = lifeinfos;
		} else if (checkedId == R.id.btn_read) {
			infos = readinfos;
		}
		adapter.setData(infos);
	}
}
