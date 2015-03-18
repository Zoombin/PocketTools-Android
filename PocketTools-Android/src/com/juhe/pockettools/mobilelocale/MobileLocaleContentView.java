package com.juhe.pockettools.mobilelocale;

import com.juhe.pockettools.R;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MobileLocaleContentView extends LinearLayout {
	TextView locale_company;
	TextView locale_cardtype;
	TextView locale_location;
	TextView locale_area_num;
	TextView locale_post_num;

	public MobileLocaleContentView(Context paramContext) {
		super(paramContext);
		initView();
	}

	public MobileLocaleContentView(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_mobilelocale_content, this, true);
		locale_company = ((TextView) findViewById(R.id.locale_company));
		locale_cardtype = ((TextView) findViewById(R.id.locale_cardtype));
		locale_location = ((TextView) findViewById(R.id.locale_location));
		locale_area_num = ((TextView) findViewById(R.id.locale_area_num));
		locale_post_num = ((TextView) findViewById(R.id.locale_post_num));
		b();
	}

	private void b() {
//		k.a(this,
//				getResources().getDisplayMetrics().widthPixels
//						/ k.a(getContext(), 320.0F), getContext());
	}

	public void setData(String localecompany, String localecardtype,
			String localelocation, String localearea_num, String localepost_num) {
		locale_company.setText(localecompany);
		locale_cardtype.setText(localecardtype);
		locale_location.setText(localelocation);
		locale_area_num.setText(localearea_num);
		locale_post_num.setText(localepost_num);
	}
}
