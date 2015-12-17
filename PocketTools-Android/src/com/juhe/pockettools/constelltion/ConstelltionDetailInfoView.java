package com.juhe.pockettools.constelltion;

import com.zoombin.koudai.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConstelltionDetailInfoView extends LinearLayout {
	private LinearLayout layout_title;
	private ImageView img_constelltion_detail;
	private TextView txt_constelltion_detail;
	private TextView txt_date_range_detail;
	private TextView title_date;
	private TextView title_1;
	private TextView title_2;
	private TextView title_3;
	private TextView title_4;
	private TextView title_5;
	private TextView title_6;
	private TextView txt_detail_1;
	private TextView txt_detail_2;
	private TextView txt_detail_3;
	private TextView txt_detail_4;
	private TextView txt_detail_5;
	private TextView txt_detail_6;

	public ConstelltionDetailInfoView(Context context) {
		super(context);
		initView();
	}

	public ConstelltionDetailInfoView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.constelltion_detail_info_view, this, true);
		layout_title = (LinearLayout) findViewById(R.id.layout_title);
		img_constelltion_detail = ((ImageView) findViewById(R.id.img_constelltion_detail));
		txt_constelltion_detail = ((TextView) findViewById(R.id.txt_constelltion_detail));
		txt_date_range_detail = (TextView)  findViewById(R.id.txt_date_range_detail);
		title_date = ((TextView) findViewById(R.id.title_date));
		title_1 = ((TextView) findViewById(R.id.title_1));
		title_2 = ((TextView) findViewById(R.id.title_2));
		title_3 = ((TextView) findViewById(R.id.title_3));
		title_4 = ((TextView) findViewById(R.id.title_4));
		title_5 = ((TextView) findViewById(R.id.title_5));
		title_6 = ((TextView) findViewById(R.id.title_6));
		txt_detail_1 = ((TextView) findViewById(R.id.txt_detail_1));
		txt_detail_2 = ((TextView) findViewById(R.id.txt_detail_2));
		txt_detail_3 = ((TextView) findViewById(R.id.txt_detail_3));
		txt_detail_4 = ((TextView) findViewById(R.id.txt_detail_4));
		txt_detail_5 = ((TextView) findViewById(R.id.txt_detail_5));
		txt_detail_6 = ((TextView) findViewById(R.id.txt_detail_6));
		title_6.setVisibility(View.GONE);
		txt_detail_6.setVisibility(View.GONE);
		Typeface typeface = Typeface.createFromAsset(getResources()
				.getAssets(), "fonts/FZKTJT.ttf");
		title_1.setTypeface(typeface);
		title_2.setTypeface(typeface);
		title_3.setTypeface(typeface);
		title_4.setTypeface(typeface);
		title_5.setTypeface(typeface);
		title_6.setTypeface(typeface);
		txt_detail_1.setTypeface(typeface);
		txt_detail_2.setTypeface(typeface);
		txt_detail_3.setTypeface(typeface);
		txt_detail_4.setTypeface(typeface);
		txt_detail_5.setTypeface(typeface);
		txt_detail_6.setTypeface(typeface);
	}

	public void setDayData(ConstalltionDayEntity entity) {
		title_date.setVisibility(View.GONE);
		title_1.setText(entity.getSummary());
		txt_detail_1.setVisibility(View.GONE);
		title_2.setVisibility(View.GONE);
		txt_detail_2.setVisibility(View.GONE);
		title_3.setVisibility(View.GONE);
		txt_detail_3.setVisibility(View.GONE);
		title_4.setVisibility(View.GONE);
		txt_detail_4.setVisibility(View.GONE);
		title_5.setVisibility(View.GONE);
		txt_detail_5.setVisibility(View.GONE);
		title_6.setVisibility(View.GONE);
		txt_detail_6.setVisibility(View.GONE);
	}
	
	public void setWeekData(ConstalltionWeekEntity entity) {
		// {"date":"2015年02月22日-2015年02月28日","health":"健康：身体小心炎症。","job":"求职：接受安排是顺势之举。 ","love":"恋情：受本能驱使，缺乏情感理智。与伴侣相处，可能有蛮横不讲理的表现。 ","money":"财运：花销随心情簸荡，财务控制力下降。 ",
		// "name":"白羊座","weekth":9,"work":"工作：知道自己想要什么，但行动无力，受制于人的可能性大。或企图走捷径，不惜放弃原则。 ","resultcode":"200","error_code":0}

		title_date.setText("第" + entity.getWeekth() + "周运势");
		title_date.setVisibility(View.VISIBLE);
		title_1.setText(entity.getHealth().split("：")[0]);
		txt_detail_1.setText(entity.getHealth().split("：")[1]);
		title_2.setText(entity.getJob().split("：")[0]);
		txt_detail_2.setText(entity.getJob().split("：")[1]);
		title_3.setText(entity.getLove().split("：")[0]);
		txt_detail_3.setText(entity.getLove().split("：")[1]);
		title_4.setText(entity.getMoney().split("：")[0]);
		txt_detail_4.setText(entity.getMoney().split("：")[1]);
		title_5.setText(entity.getWork().split("：")[0]);
		txt_detail_5.setText(entity.getWork().split("：")[1]);

		title_1.setVisibility(View.VISIBLE);
		txt_detail_1.setVisibility(View.VISIBLE);
		title_2.setVisibility(View.VISIBLE);
		txt_detail_2.setVisibility(View.VISIBLE);
		title_3.setVisibility(View.VISIBLE);
		txt_detail_3.setVisibility(View.VISIBLE);
		title_4.setVisibility(View.VISIBLE);
		txt_detail_4.setVisibility(View.VISIBLE);
		title_5.setVisibility(View.VISIBLE);
		txt_detail_5.setVisibility(View.VISIBLE);
		
		title_6.setVisibility(View.GONE);
		txt_detail_6.setVisibility(View.GONE);
	}
	
	public void setMonthData(ConstalltionWeekEntity entity) {
		// {"date":"2015年02月22日-2015年02月28日","health":"健康：身体小心炎症。","job":"求职：接受安排是顺势之举。 ","love":"恋情：受本能驱使，缺乏情感理智。与伴侣相处，可能有蛮横不讲理的表现。 ","money":"财运：花销随心情簸荡，财务控制力下降。 ",
		// "name":"白羊座","weekth":9,"work":"工作：知道自己想要什么，但行动无力，受制于人的可能性大。或企图走捷径，不惜放弃原则。 ","resultcode":"200","error_code":0}

		title_date.setVisibility(View.GONE);
		
		title_1.setText("总运势");
		txt_detail_1.setText(entity.getAll());
		title_2.setText("健康");
		txt_detail_2.setText(entity.getHealth());
		title_3.setText("爱情");
		txt_detail_3.setText(entity.getLove());
		title_4.setText("财运");
		txt_detail_4.setText(entity.getMoney());
		title_5.setText("工作");
		txt_detail_5.setText(entity.getWork());
		
		title_1.setVisibility(View.VISIBLE);
		txt_detail_1.setVisibility(View.VISIBLE);
		title_2.setVisibility(View.VISIBLE);
		txt_detail_2.setVisibility(View.VISIBLE);
		title_3.setVisibility(View.VISIBLE);
		txt_detail_3.setVisibility(View.VISIBLE);
		title_4.setVisibility(View.VISIBLE);
		txt_detail_4.setVisibility(View.VISIBLE);
		title_5.setVisibility(View.VISIBLE);
		txt_detail_5.setVisibility(View.VISIBLE);
		title_6.setVisibility(View.GONE);
		txt_detail_6.setVisibility(View.GONE);
	}
	
	public void setYearData(ConstalltionYearEntity entity) {
		title_date.setVisibility(View.GONE);
		title_1.setText(entity.getMima().getInfo());
		txt_detail_1.setText(entity.getMima().getText().get(0));
		title_2.setText("职业");
		txt_detail_2.setText(entity.getCareer().get(0));
		title_3.setText("爱情");
		txt_detail_3.setText(entity.getLove().get(0));
		title_4.setText("健康");
		txt_detail_4.setText(entity.getHealth().get(0));
		title_5.setText("财务");
		txt_detail_5.setText(entity.getFinance().get(0));
		
		title_1.setVisibility(View.VISIBLE);
		txt_detail_1.setVisibility(View.VISIBLE);
		title_2.setVisibility(View.VISIBLE);
		txt_detail_2.setVisibility(View.VISIBLE);
		title_3.setVisibility(View.VISIBLE);
		txt_detail_3.setVisibility(View.VISIBLE);
		title_4.setVisibility(View.VISIBLE);
		txt_detail_4.setVisibility(View.VISIBLE);
		title_5.setVisibility(View.VISIBLE);
		txt_detail_5.setVisibility(View.VISIBLE);
		
		title_6.setVisibility(View.GONE);
		txt_detail_6.setVisibility(View.GONE);
	}

	public void setSelectedDetailConstelltion(int index) {
		layout_title.setVisibility(View.VISIBLE);
		img_constelltion_detail.setBackgroundResource(ConstalltionConstants.icons[index]);
		txt_constelltion_detail.setText(ConstalltionConstants.names[index]);
		txt_date_range_detail.setText(ConstalltionConstants.dates[index]);
	}
	
	public void disableTitleLayout() {
		layout_title.setVisibility(View.GONE);
	}
}
