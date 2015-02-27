package com.juhe.pockettools.constelltion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.juhe.pockettools.R;

public class ConstelltionBasicView extends LinearLayout {
	private ImageView img_constelltion;
	private TextView txt_constelltion;
	private TextView txt_date_range;
	private TextView item_composite_index;
	private TextView item_match_index;
	private TextView item_luky_num;
	private TextView item_luky_color;
	private ConstelltionItemView img_health;
	private ConstelltionItemView img_love;
	private ConstelltionItemView img_money;
	private ConstelltionItemView img_work;

	public ConstelltionBasicView(Context context) {
		super(context);
		initView();
	}

	public ConstelltionBasicView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_constelltion_basic, this, true);
		img_constelltion = ((ImageView) findViewById(R.id.img_constelltion));
		txt_constelltion = ((TextView) findViewById(R.id.txt_constelltion));
		txt_date_range = ((TextView) findViewById(R.id.txt_date_range));
		item_composite_index = ((TextView) findViewById(R.id.item_composite_index));
		item_match_index = ((TextView) findViewById(R.id.item_match_index));
		item_luky_num = ((TextView) findViewById(R.id.item_luky_num));
		item_luky_color = ((TextView) findViewById(R.id.item_luky_color));
		img_health = ((ConstelltionItemView) findViewById(R.id.img_health));
		img_love = ((ConstelltionItemView) findViewById(R.id.img_love));
		img_money = ((ConstelltionItemView) findViewById(R.id.img_money));
		img_work = ((ConstelltionItemView) findViewById(R.id.img_work));
		img_health.setViewColor(Color.rgb(166, 181, 20));
		img_love.setViewColor(Color.rgb(252, 86, 98));
		img_money.setViewColor(Color.rgb(255, 192, 25));
		img_work.setViewColor(Color.rgb(97, 171, 244));
	}

//	public void a(q paramq, String paramString) {
//		if ((paramq != null) && (paramString.equals("today"))) {
//			item_composite_index.setText(paramq.e);
//			item_match_index.setText(paramq.f);
//			item_luky_num.setText(paramq.g);
//			item_luky_color.setText(paramq.h);
//			img_health.setIndex(paramq.j);
//			img_love.setIndex(paramq.i);
//			img_money.setIndex(paramq.k);
//			img_work.setIndex(paramq.l);
//		}
//
//		if (paramq != null && paramString.equals("tomorrow")) {
//			item_composite_index.setText(paramq.o);
//			item_match_index.setText(paramq.p);
//			item_luky_num.setText(paramq.q);
//			item_luky_color.setText(paramq.r);
//			img_health.setIndex(paramq.t);
//			img_love.setIndex(paramq.s);
//			img_money.setIndex(paramq.u);
//			img_work.setIndex(paramq.v);
//		}
//	}

	public void setSelectedConstelltion(int index) {
		img_constelltion
				.setBackgroundResource(ConstalltionConstants.icons[index]);
		txt_constelltion.setText(ConstalltionConstants.names[index]);
		txt_date_range.setText(ConstalltionConstants.dates[index]);
	}
}
