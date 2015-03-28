package com.juhe.pockettools.violation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.juhe.pockettools.R;
import com.juhe.pockettools.violation.ViolationCitySelectDialog.OnCityListener;
//import com.fotoable.helpr.Utils.k;
//import com.fotoable.helpr.a.b;

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
	private ImageView engine_line;
	private ImageView frame_line;
	private ImageView regist_line;
	
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
		engine_line = (ImageView) findViewById(R.id.engine_line);
		frame_line = (ImageView) findViewById(R.id.frame_line);
		regist_line = (ImageView) findViewById(R.id.regist_line);
		
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
		 violation_car_engine.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence c, int arg1, int arg2, int arg3) {
				listener.setEngineno(c.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 violation_car_frame_num.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence c, int arg1, int arg2, int arg3) {
				listener.setClassno(c.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 violation_car_num.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence c, int arg1, int arg2, int arg3) {
				listener.setHphm(c.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
//		 violation_car_regist.addTextChangedListener(new );
	}

	private void setCityInfo(CityEntity cityentity) {
		violation_car_city.setText(cityentity.getCity_name());
		violation_car_num.setText(cityentity.getAbbr());
		if (cityentity.isEngine()) {
			engine_line.setVisibility(View.VISIBLE);
			violation_car_engine_container.setVisibility(View.VISIBLE);
			violation_car_engine.setHint("发动机号后" + cityentity.getEngineno() + "位");
		} else {
			engine_line.setVisibility(View.GONE);
			violation_car_engine_container.setVisibility(View.GONE);
			violation_car_engine.setText("");
		}
		if (cityentity.isClassa()) {
			frame_line.setVisibility(View.VISIBLE);
			violation_car_frame_num_container.setVisibility(View.VISIBLE);
			violation_car_frame_num.setHint("车架号后" + cityentity.getClassno() + "位");
		} else {
			frame_line.setVisibility(View.GONE);
			violation_car_frame_num_container.setVisibility(View.GONE);
			violation_car_frame_num.setText("");
		}
		if (cityentity.isRegist()) {
			regist_line.setVisibility(View.VISIBLE);
			violation_car_regist_container.setVisibility(View.VISIBLE);
			violation_car_regist.setHint("完整注册号后" + cityentity.getRegistno() + "位");
		} else {
			regist_line.setVisibility(View.GONE);
			violation_car_regist_container.setVisibility(View.GONE);
			violation_car_regist.setText("");
		}
		listener.setCity(cityentity);
	}
	
	public void setSelectCityListener() {
		final CityEntity cityentity = ViolationMainActivity.citymap.get("江苏").get(3);
		violation_city_waitbar.setVisibility(View.INVISIBLE);
		setCityInfo(cityentity);
		violation_car_city.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (ViolationMainActivity.cityselectdialog == null) {
					ViolationMainActivity.cityselectdialog = new ViolationCitySelectDialog(getContext());
				}
				
				ViolationMainActivity.cityselectdialog.initView("江苏", cityentity);
				ViolationMainActivity.cityselectdialog.setListener(new OnCityListener() {
					
					@Override
					public void getCity(String province_code, CityEntity cityentity) {
						setCityInfo(cityentity);
						
					}
				});
			}
		});
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

	public void setListener(OnConditionListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnConditionListener {
		public abstract void setHpzl(hpzlEntity hpzl);

		public abstract void setCity(CityEntity city);

		public abstract void setEngineno(String engineno);

		public abstract void setClassno(String classno);
		
		public abstract void setHphm(String hphm);
	}
}
