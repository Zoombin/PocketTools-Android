package com.juhe.pockettools.constelltion;

import com.juhe.pockettools.R;

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
	private ImageView img_constelltion_detail;
	private TextView txt_constelltion_detail;
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
		img_constelltion_detail = ((ImageView) findViewById(R.id.img_constelltion_detail));
		txt_constelltion_detail = ((TextView) findViewById(R.id.txt_constelltion_detail));
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

	public void a(q paramq, String paramString) {
//		if ((paramq != null) && (paramString.equals("week"))) {
//			title_date.setText("第" + paramq.z + "周运势");
//			String str11 = paramq.B.replaceAll("\",\"", "");
//			title_1.setText(str11.substring(0, 2));
//			txt_detail_1.setText(str11.substring(3, str11.length()));
//			String str12 = paramq.C.replaceAll("\",\"", "");
//			title_2.setText(str12.substring(0, 2));
//			txt_detail_2.setText(str12.substring(3, str12.length()));
//			String str13 = paramq.A.replaceAll("\",\"", "");
//			title_3.setText(str13.substring(0, 2));
//			txt_detail_3.setText(str13.substring(3, str13.length()));
//			String str14 = paramq.D.replaceAll("\",\"", "");
//			title_4.setText(str14.substring(0, 2));
//			txt_detail_4.setText(str14.substring(3, str14.length()));
//			String str15 = paramq.E.replaceAll("\",\"", "");
//			title_5.setText(str15.substring(0, 2));
//			txt_detail_5.setText(str15.substring(3, str15.length()));
//		}
//		String str1;
//		do {
//			do {
//				do {
//					return;
//					if ((paramq != null) && (paramString.equals("month"))) {
//						title_date.setText(paramq.H + "运势");
//						String str6 = paramq.J.replaceAll("\",\"", "");
//						title_1.setText("总运势");
//						txt_detail_1.setText(str6.substring(0, str6.length()));
//						String str7 = paramq.L.replaceAll("\",\"", "");
//						title_2.setText("健康");
//						txt_detail_2.setText(str7.substring(0, str7.length()));
//						String str8 = paramq.K.replaceAll("\",\"", "");
//						title_3.setText("爱情");
//						txt_detail_3.setText(str8.substring(0, str8.length()));
//						String str9 = paramq.N.replaceAll("\",\"", "");
//						title_4.setText("财运");
//						txt_detail_4.setText(str9.substring(0, str9.length()));
//						String str10 = paramq.O.replaceAll("\",\"", "");
//						title_5.setText("工作");
//						txt_detail_5.setText(str10.substring(0, str10.length()));
//						return;
//					}
//				} while ((paramq == null) || (!paramString.equals("year")));
//				title_date.setText(paramq.T + "运势");
//				title_1.setText("总运势描述: " + paramq.V);
//				if (paramq.S != null) {
//					String str5 = paramq.S.replaceAll("\",\"", "");
//					if ((str5 != null) && (str5.length() > 2)) {
//						txt_detail_1.setText(str5.substring(2, -2 + str5.length()));
//					}
//				}
//				title_2.setText("职业");
//				if (paramq.Y != null) {
//					String str4 = paramq.Y.replaceAll("\",\"", "");
//					if ((str4 != null) && (str4.length() > 2)) {
//						txt_detail_2.setText(str4.substring(2, -2 + str4.length()));
//					}
//				}
//				title_3.setText("爱情");
//				if (paramq.W != null) {
//					String str3 = paramq.W.replaceAll("\",\"", "");
//					if ((str3 != null) && (str3.length() > 2)) {
//						txt_detail_3.setText(str3.substring(2, -2 + str3.length()));
//					}
//				}
//				title_4.setText("健康");
//				if (paramq.X != null) {
//					String str2 = paramq.X.replaceAll("\",\"", "");
//					if ((str2 != null) && (str2.length() > 2)) {
//						txt_detail_4.setText(str2.substring(2, -2 + str2.length()));
//					}
//				}
//				title_5.setText("财务");
//			} while (paramq.Z == null);
//			str1 = paramq.Z.replaceAll("\",\"", "");
//		} while ((str1 == null) || (str1.length() <= 2));
//		txt_detail_5.setText(str1.substring(2, -2 + str1.length()));
	}

	public void setSelectedDetailConstelltion(int index) {
		img_constelltion_detail.setBackgroundResource(ConstalltionConstants.icons[index]);
		txt_constelltion_detail.setText(ConstalltionConstants.names[index]);
	}
}
