package com.juhe.pockettools.commonview;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TopActiveBarView extends FrameLayout {
	private InterfaceTopActiveBar interfacetopactivebar;
	private ImageView bar_cancel;
	private TextView bar_content;
	private Button bar_sure;
	private FrameLayout right_contaienr;
	private FrameLayout bar_divider_line;

	public TopActiveBarView(Context context) {
		super(context);
		initView();
	}

	public TopActiveBarView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService("layout_inflater"))
				.inflate(R.layout.view_top_active_bar, this, true);
		bar_cancel = ((ImageView) findViewById(R.id.bar_cancel));
		bar_content = ((TextView) findViewById(R.id.bar_content));
		bar_sure = ((Button) findViewById(R.id.bar_sure));
		right_contaienr = ((FrameLayout) findViewById(R.id.right_contaienr));
		bar_divider_line = ((FrameLayout) findViewById(R.id.bar_divider_line));
		bar_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (interfacetopactivebar != null) {
					interfacetopactivebar.cancel();
				}
			}
		});
		bar_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (interfacetopactivebar != null) {
					interfacetopactivebar.query();
				}
			}
		});
	}

	public void a() {
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) bar_sure
				.getLayoutParams();
		params.width = -2;
		bar_sure.setLayoutParams(params);
	}

	public void b() {
		bar_sure.setBackgroundColor(0);
	}

	public boolean getSureSelect() {
		return bar_sure.isSelected();
	}

	public String getTitleText() {
		return bar_content.getText().toString();
	}

	public void setListener(InterfaceTopActiveBar interfacetopactivebar) {
		this.interfacetopactivebar = interfacetopactivebar;
	}

	public void setProgressVisiable(int paramInt) {
		right_contaienr.setVisibility(paramInt);
	}

	public void setSplitLineVisible(boolean visible) {
		FrameLayout framelayout = bar_divider_line;
		if (visible) {
			framelayout.setVisibility(View.VISIBLE);
		} else {
			framelayout.setVisibility(View.INVISIBLE);
		}
	}

	public void setSureSelect(boolean paramBoolean) {
		bar_sure.setSelected(paramBoolean);
	}

	public void setSureText(int paramInt) {
		bar_sure.setVisibility(0);
		bar_sure.setText(paramInt);
	}

	public void setSureText(String paramString) {
		if (paramString.equals("")) {
			return;
		}
		bar_sure.setVisibility(0);
		bar_sure.setText(paramString);
	}

	public void setSureTextColor(int paramInt) {
		bar_sure.setTextColor(paramInt);
	}

	public void setTiltleText(int paramInt) {
		bar_content.setText(paramInt);
	}

	public void setTiltleText(String paramString) {
		bar_content.setText(paramString);
	}

	public static abstract interface InterfaceTopActiveBar {
		public abstract void query();

		public abstract void cancel();
	}
}
