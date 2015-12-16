package com.juhe.pockettools.courier;

import java.util.HashMap;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
//import com.juhe.pockettools.Utils.CalendarUtil;
//import com.juhe.pockettools.wallpaper.w;
import com.juhe.pockettools.utils.Config;

public class PackageInfoView extends LinearLayout {
	public static String a = "package_time";
	public static String b = "package_info";
	private TopActiveBarView action_bar;
	private ListView package_info_list;
	private PackageInfoAdapter adapter;
	private String f = "v.juhe.cn";
	private OnSelectListener listener;
	private String h = "";
	PackageEntity entity;
	
	public PackageInfoView(Context context) {
		super(context);
		initView();
	}

	public PackageInfoView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_package_show_info, this, true);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		package_info_list = ((ListView) findViewById(R.id.package_info_list));
		adapter = new PackageInfoAdapter(getContext());
		package_info_list.setAdapter(adapter);
		action_bar.setTiltleText("物流详情");
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				listener.close();
			}
		});
		action_bar.setProgressVisiable(View.INVISIBLE);
	}

	private void b(String paramString1, String paramString2) {
		this.h = paramString2;
		if (paramString1.equals("sf")) {
			action_bar.setTiltleText(2131230929);
		}
		for (;;) {
			if (paramString1.equals("sto")) {
				action_bar.setTiltleText(2131230930);
			}
			if (paramString1.equals("yt")) {
				action_bar.setTiltleText(2131230931);
			}
			if (paramString1.equals("yd")) {
				action_bar.setTiltleText(2131230932);
			}
			if (paramString1.equals("tt")) {
				action_bar.setTiltleText(2131230933);
			}
			if (paramString1.equals("zto")) {
				action_bar.setTiltleText(2131230934);
			}
			if (paramString1.equals("ht")) {
				action_bar.setTiltleText(2131230935);
			}
			if (paramString1.equals("ems")) {
				action_bar.setTiltleText(2131230928);
			}
			return;
		}
	}
	
	public void setData(PackageEntity entity) {
		this.entity = entity;
		adapter.setData(entity.getResult());
		adapter.notifyDataSetChanged();
	}

	public void setListener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void close();

		public abstract void a(HashMap<String, String> paramHashMap);
	}
}
