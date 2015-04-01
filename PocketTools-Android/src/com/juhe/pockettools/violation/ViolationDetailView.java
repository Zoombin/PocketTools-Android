package com.juhe.pockettools.violation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.utils.Config;
import com.juhe.pockettools.violation.ViolationDetailEntity.Info;
//import com.juhe.pockettools.wallpaper.w;
import java.util.ArrayList;
import java.util.List;

public class ViolationDetailView extends FrameLayout {
	private TopActiveBarView action_bar;
	private ListView list_violation_detail;
	private TextView detail_no_data;
	private a d;
	private DetailAdapter adapter;
	private ViolationDetailHeadView detailheadview;

	public ViolationDetailView(Context context) {
		super(context);
		initView();
	}

	public ViolationDetailView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_violation_detail, this, true);
		((ImageView) findViewById(R.id.img_bg)).setBackground(Config
				.getBgDrawable());
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		list_violation_detail = ((ListView) findViewById(R.id.list_violation_detail));
		detail_no_data = ((TextView) findViewById(R.id.detail_no_data));
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void cancel() {
				// ViolationDetailView.this.cl
			}

			@Override
			public void query() {
				// TODO Auto-generated method stub

			}
		});
		adapter = new DetailAdapter(getContext());
		detailheadview = new ViolationDetailHeadView(getContext(), null);
		list_violation_detail.addHeaderView(detailheadview);
		list_violation_detail.setAdapter(adapter);
	}

	public void setData(List<Info> list, String totalcode, String totalmoney) {
		detailheadview.setData(totalcode, totalmoney);
		adapter.setData(list);
	}

	public void showLayout(boolean hasdata) {
		if (hasdata) {
			list_violation_detail.setVisibility(View.VISIBLE);
			detail_no_data.setVisibility(View.INVISIBLE);
		} else {
			list_violation_detail.setVisibility(View.INVISIBLE);
			detail_no_data.setVisibility(View.VISIBLE);
		}
		action_bar.setProgressVisiable(View.INVISIBLE);
	}

	public void setListener(a parama) {
		this.d = parama;
	}

	public void setTitle(String text) {
		action_bar.setTiltleText(text);
	}

	public static abstract interface a {
		public abstract void a();
	}
}
