package com.juhe.pockettools.courier;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.juhe.pockettools.R;

public class PackageSelectView extends LinearLayout {
	private Button btn_ems;
	private Button btn_sf;
	private Button btn_st;
	private Button btn_yt;
	private Button btn_yd;
	private Button btn_tt;
	private Button btn_zt;
	private Button btn_ht;
	private OnSelectListener listener;
	private View.OnClickListener l = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			clearButton();
			v.setSelected(true);
			listener.setCourier(v.getTag().toString());
		}
	};

	public PackageSelectView(Context context) {
		super(context);
		initView();
	}

	public PackageSelectView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_package_company_select, this, true);
		btn_ems = ((Button) findViewById(R.id.btn_ems));
		btn_sf = ((Button) findViewById(R.id.btn_sf));
		btn_st = ((Button) findViewById(R.id.btn_st));
		btn_yt = ((Button) findViewById(R.id.btn_yt));
		btn_yd = ((Button) findViewById(R.id.btn_yd));
		btn_tt = ((Button) findViewById(R.id.btn_tt));
		btn_zt = ((Button) findViewById(R.id.btn_zt));
		btn_ht = ((Button) findViewById(R.id.btn_ht));
		btn_ems.setTag("ems");
		btn_sf.setTag("sf");
		btn_st.setTag("sto");
		btn_yt.setTag("yt");
		btn_yd.setTag("yd");
		btn_tt.setTag("tt");
		btn_zt.setTag("zto");
		btn_ht.setTag("ht");
		btn_ems.setOnClickListener(l);
		btn_sf.setOnClickListener(l);
		btn_st.setOnClickListener(l);
		btn_yt.setOnClickListener(l);
		btn_yd.setOnClickListener(l);
		btn_tt.setOnClickListener(l);
		btn_zt.setOnClickListener(l);
		btn_ht.setOnClickListener(l);
		btn_ems.setSelected(true);
		btn_sf.setSelected(false);
		btn_st.setSelected(false);
		btn_yt.setSelected(false);
		btn_yd.setSelected(false);
		btn_tt.setSelected(false);
		btn_zt.setSelected(false);
		btn_ht.setSelected(false);
	}

	private void clearButton() {
		btn_ems.setSelected(false);
		btn_sf.setSelected(false);
		btn_st.setSelected(false);
		btn_yt.setSelected(false);
		btn_yd.setSelected(false);
		btn_tt.setSelected(false);
		btn_zt.setSelected(false);
		btn_ht.setSelected(false);
	}

	public void setLisntener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void setCourier(String courier);
	}
}
