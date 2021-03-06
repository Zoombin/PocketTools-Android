package com.juhe.pockettools.constelltion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import com.zoombin.koudai.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.utils.Config;

//import com.fotoable.helpr.wallpaper.w;

@SuppressLint("NewApi")
public class ConstelltionSelectView extends FrameLayout {
	private TopActiveBarView action_bar;
	private ListView constelltion_list;
	private OnSelectListener listener;
	private SelectedAdapter selectedadapter;

	public ConstelltionSelectView(Context context) {
		super(context);
		initView();
	}

	public ConstelltionSelectView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_constelltion_select_list, this, true);
		ImageView img_bg = (ImageView) findViewById(R.id.img_bg);
		img_bg.setBackgroundColor(getResources().getColor(Config.getColor()));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		constelltion_list = ((ListView) findViewById(R.id.constelltion_list));
		action_bar.setTiltleText("选择星座");
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void query() {
				
			}

			@Override
			public void cancel() {
				listener.finish();
			}
		});
		selectedadapter = new SelectedAdapter(getContext());
		constelltion_list.setAdapter(selectedadapter);
		constelltion_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if ((position < 12) && listener != null) {
					listener.setPosition(position);
				}
			}
		});
	}

	public void setListener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void finish();

		public abstract void setPosition(int index);
	}
}
