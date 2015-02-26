package com.juhe.pockettools.violation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
//import com.fotoable.helpr.Utils.k;
//import com.fotoable.helpr.a.b;
import java.util.ArrayList;
import java.util.HashMap;

import com.juhe.pockettools.R;

public class ViolationConditionView extends FrameLayout {
	private Button violation_car_city;
	private Button violation_car_type;
	private EditText violation_car_num;
	private EditText violation_car_engine;
	private EditText violation_car_frame_num;
	private EditText violation_car_regist;
	private LinearLayout violation_car_engine_container;
	private LinearLayout violation_car_frame_num_container;
	private LinearLayout violation_car_regist_container;
	private ProgressBar violation_city_waitbar;
	private ProgressBar violation_cartype_waitbar;
	private OnConditionListener listener;
	private String m;
	private CityEntity cityentity;
	private hpzlEntity o;

	public ViolationConditionView(Context context) {
		super(context);
		initView();
	}

	public ViolationConditionView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_violation_search_condition, this, true);
		violation_car_city = ((Button) findViewById(R.id.violation_car_city));
		violation_car_type = ((Button) findViewById(R.id.violation_car_type));
		violation_car_num = ((EditText) findViewById(R.id.violation_car_num));
		violation_car_engine = ((EditText) findViewById(R.id.violation_car_engine));
		violation_car_frame_num = ((EditText) findViewById(R.id.violation_car_frame_num));
		violation_car_regist = ((EditText) findViewById(R.id.violation_car_regist));
		violation_car_regist_container = ((LinearLayout) findViewById(R.id.violation_car_regist_container));
		violation_car_engine_container = ((LinearLayout) findViewById(R.id.violation_car_engine_container));
		violation_car_frame_num_container = ((LinearLayout) findViewById(R.id.violation_car_frame_num_container));
		violation_city_waitbar = ((ProgressBar) findViewById(R.id.violation_city_waitbar));
		violation_cartype_waitbar = ((ProgressBar) findViewById(R.id.violation_cartype_waitbar));
		// violation_car_city.setOnClickListener(new d(this));
		// violation_car_engine.addTextChangedListener(new f(this));
		// violation_car_frame_num.addTextChangedListener(new g(this));
		// violation_car_num.addTextChangedListener(new h(this));
		// violation_car_regist.addTextChangedListener(new i(this));
	}

	private int cartypeitem = 1;
	public void setSelectCarTypeListener() {
		// 小型车牌
		hpzlEntity entity = ViolationMainActivity.hpzllist.get(1);
		listener.setHpzl(entity);
		violation_cartype_waitbar.setVisibility(View.INVISIBLE);
		violation_car_type.setText(entity.getCar());
		
		violation_car_type.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String[] l = new String[ViolationMainActivity.hpzllist
						.size()];
				for (int i = 0; i < ViolationMainActivity.hpzllist.size(); i++) {
					l[i] = ViolationMainActivity.hpzllist.get(i).getCar();
				}

				new AlertDialog.Builder(getContext())
						.setTitle("请选择车辆类型")
						.setSingleChoiceItems(l, cartypeitem,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										cartypeitem = item;
										hpzlEntity entity = ViolationMainActivity.hpzllist
										.get(item);
										violation_car_type.setText(entity.getCar());
										listener.setHpzl(entity);
										dialog.cancel();
									}
								})
						.setPositiveButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();// 显示对话框

			}
		});
	}

	public void a() {
		// String str1 = "北京";
		// if (b.a().d().e != null) {
		// str1 = b.a().d().e;
		// }
		// String str2 = str1.replace("市", "").replace("省", "");
		// ArrayList localArrayList1 = (ArrayList) z.a.get(str2);
		// ArrayList localArrayList2;
		// String str4;
		// if ((localArrayList1 == null) || (b.a().d().b == null)) {
		// String str3 = (String) z.b.get(0);
		// localArrayList2 = (ArrayList) z.a.get(str3);
		// str4 = str3;
		// }
		// for (;;) {
		// for (int i1 = 0;; i1++) {
		// if (i1 >= localArrayList2.size()) {
		// }
		// for (;;) {
		// if (cityentity == null) {
		// cityentity = ((y) localArrayList2.get(0));
		// }
		// a(str4, cityentity);
		// return;
		// if (b.a().d() == null) {
		// break;
		// }
		// String str5 = b.a().d().b;
		// if ((str5 == null)
		// || (!str5.equals(((y) localArrayList2.get(i1)).k))) {
		// break;
		// }
		// cityentity = ((y) localArrayList2.get(i1));
		// }
		// }
		// str4 = str2;
		// localArrayList2 = localArrayList1;
		// }
	}

	public void a(String paramString, CityEntity cityentity) {
		// this.m = paramString;
		// this.cityentity = cityentity;
		// if (listener != null) {
		// listener.a(cityentity);
		// }
		// violation_car_city.setText(paramy.k);
		// violation_car_num.setText(paramy.d);
		// violation_car_num.setSelection(violation_car_num.getText().length());
		// if (paramy.g) {
		// violation_car_frame_num_container.setVisibility(0);
		// if (paramy.h == 0) {
		// violation_car_frame_num.setHint("完整车架号");
		// if (!paramy.e) {
		// break label258;
		// }
		// violation_car_engine_container.setVisibility(0);
		// if (paramy.f != 0) {
		// break label219;
		// }
		// violation_car_frame_num.setHint("完整发动机号");
		// }
		// }
		// for (;;) {
		// if (paramy.i) {
		// violation_car_regist_container.setVisibility(0);
		// if (paramy.j == 0) {
		// violation_car_frame_num.setHint("完整注册号");
		// return;
		// violation_car_frame_num.setHint("车架号后" + String.valueOf(paramy.h) +
		// "位");
		// break;
		// violation_car_frame_num_container.setVisibility(8);
		// break;
		// label219: violation_car_engine.setHint("发动机号后" +
		// String.valueOf(paramy.f)
		// + "位");
		// continue;
		// label258: violation_car_engine_container.setVisibility(8);
		// continue;
		// }
		// violation_car_regist.setHint("注册号后" + String.valueOf(paramy.j) +
		// "位");
		// return;
		// }
		// }
		// violation_car_regist_container.setVisibility(View.GONE);
	}

	public void a(String paramString1, String paramString2) {
		violation_car_city.setText(paramString1);
		violation_car_type.setText(paramString2);
	}

	public void b() {
//		if (z.c.size() > 2) {
//		}
//		for (this.o = ((hpzlEntity) z.c.get(1));; this.o = ((hpzlEntity) z.c
//				.get(0))) {
//			a(this.o);
//			do {
//				return;
//			} while (z.c.size() != 1);
//		}
	}

	public void c() {
		violation_city_waitbar.setVisibility(View.INVISIBLE);
	}

	public void d() {
		violation_cartype_waitbar.setVisibility(View.INVISIBLE);
	}

	public void setListener(OnConditionListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnConditionListener {
		public abstract void a();

		public abstract void setHpzl(hpzlEntity paramx);

		public abstract void a(CityEntity paramy);

		public abstract void a(String paramString);

		public abstract void a(String paramString, CityEntity paramy);

		public abstract void b(String paramString);

		public abstract void c(String paramString);

		public abstract void d(String paramString);
	}
}
