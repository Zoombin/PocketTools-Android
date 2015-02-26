package com.juhe.pockettools.violation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.exchange.ExChangeMainActivity;
import com.juhe.pockettools.exchange.ExchangeRateManager;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.SizeTool;
import com.juhe.pockettools.violation.ViolationConditionView.OnConditionListener;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
//import com.juhe.pockettools.wallpaper.w;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViolationMainActivity extends FullscreenActivity {
	ListView list_recently_search;
	private TopActiveBarView action_bar;
	private ViolationConditionView violation_condition;
	private ViolationCitySelectDialog cityselectdialog;
	private AlertDialog hpzldialog;
	private boolean f = false;
	private boolean g = false;
	private boolean h = false;
	private boolean i = false;
	private String j = "";
	private String k = "";
	private String l = "";
	private String m = "";
	private CityEntity cityentity;
	private hpzlEntity hpzlentity;
	private ViolationDetailView violationdetailview;
	private ViolationHistoryAdapter adapter;
	private ArrayList<HashMap<String, String>> historylist;

	private void a() {
//		com.fotoable.helpr.Utils.k.a(this);
		ViewGroup localViewGroup = (ViewGroup) findViewById(android.R.id.content);
		violationdetailview = new ViolationDetailView(this);
		violationdetailview.setTitle(this.m);
//		violationdetailview.setListener(new s(this));
		localViewGroup.addView(violationdetailview);
		Animation localAnimation = AnimationUtils.loadAnimation(this,
				R.anim.fragment_slide_left_enter);
		violationdetailview.setAnimation(localAnimation);
	}

	private void b() {
//		if (cityentity == null) {
//		}
//		do {
//			return;
//			if (!cityentity.g) {
//				this.g = true;
//			}
//			if (!cityentity.e) {
//				this.f = true;
//			}
//			if (!cityentity.i) {
//				this.h = true;
//			}
//			action_bar.setSelected(false);
//		} while ((!this.f) || (!this.g) || (!this.h) || (!this.i));
//		action_bar.setSureSelect(true);
	}

	private String d() {
//		String str = HelprRequestCore.a("VIOLATION_API_KEY");
//		if (str == null) {
//			return "";
//		}
//		StringBuilder localStringBuilder = new StringBuilder();
//		localStringBuilder.append("http://v.juhe.cn/wz/query?").append("key=")
//				.append(str).append("&city=").append(cityentity.l).append("&hphm=")
//				.append(this.m);
//		if (cityentity.g) {
//			localStringBuilder.append("&classno=").append(this.k);
//		}
//		if (cityentity.e) {
//			localStringBuilder.append("&engineno=").append(this.j);
//		}
//		if (cityentity.i) {
//			localStringBuilder.append("&registno=").append(this.l);
//		}
//		localStringBuilder.append("&hpzl=").append(hpzlentity.b);
//		return localStringBuilder.toString();
		return "";
	}

	private void setViolationdetailviewAnimation() {
		if (violationdetailview != null) {
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.fragment_slide_right_exit);
			animation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation arg0) {
//					this.b.removeView(ViolationMainActivity.b(this.a));
//				    ViolationMainActivity.a(this.a, null);
				}
			});
			violationdetailview.startAnimation(animation);
		}
	}

	@Override
	public void onBackPressed() {
		if (violationdetailview != null) {
			setViolationdetailviewAnimation();
			return;
		}
		finish();
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_violation_main);
		// ((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		violation_condition = ((ViolationConditionView) findViewById(R.id.violation_condition));
		list_recently_search = ((ListView) findViewById(R.id.list_recently_search));
		adapter = new ViolationHistoryAdapter(this);
		list_recently_search.setAdapter(adapter);

		historylist = new ArrayList<HashMap<String, String>>();
		adapter.setData(historylist);
//		list_recently_search.setOnItemClickListener(new k(this));
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void cancel() {
				finish();
			}
			
			@Override
			public void query() {
				// TODO Auto-generated method stub
				
			}
		});
		action_bar.setTiltleText("违章查询");
		action_bar.setSureText(getResources()
				.getString(R.string.package_search));
		action_bar.setSureSelect(false);
		violation_condition.setListener(new OnConditionListener() {
			
			@Override
			public void setHpzl(hpzlEntity entity) {
				hpzlentity = entity;
			}
			
			@Override
			public void d(String paramString) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void c(String paramString) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void b(String paramString) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void a(String paramString, CityEntity paramy) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void a(String paramString) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void a(CityEntity paramy) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void a() {
				// TODO Auto-generated method stub
				
			}
		});
		
		// 调用接口查询data
		// historylist = this.s.a();
		Parameters params = new Parameters();
		params.add("key", "e4ff4be7d2b4e89b3df1cbdd08fcb67e");
		JuheData.executeWithAPI(36,
				"http://v.juhe.cn/wz/citys",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
//							{"resultcode":"200","reason":"成功的返回","result":{"BJ":{"province":"北京","province_code":"BJ","citys":[{"city_name":"北京","city_code":"BJ","abbr":"京","engine":"1","engineno":"0","classa":"0","class":"0","classno":"0","regist":"0","registno":"0"}]},"SH":{"province":"上海","province_code":"SH","citys":[{"city_name":"上海","city_code":"SH","abbr":"沪","engine":"1","engineno":"0","classa":"0","class":"0","classno":"0","regist":"0","registno":"0"}]},"SC":{"province":"四川","province_code":"SC","citys":[{"city_name":"成都","city_code":"SC_CD","abbr":"川","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"}]},"ZJ":{"province":"浙江","province_code":"ZJ","citys":[{"city_name":"杭州","city_code":"ZJ_HZ","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"宁波","city_code":"ZJ_NB","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"义乌","city_code":"ZJ_YW","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"3","regist":"0","registno":"0"},{"city_name":"台州","city_code":"ZJ_TZ","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"慈溪","city_code":"ZJ_CX","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"余姚","city_code":"ZJ_YY","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"永康","city_code":"ZJ_YK","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"3","regist":"0","registno":"0"},{"city_name":"绍兴县","city_code":"ZJ_SXX","abbr":"浙","engine":"1","engineno":"6","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"嘉兴","city_code":"ZJ_JX","abbr":"浙","engine":"1","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"金华","city_code":"ZJ_JH","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"3","regist":"0","registno":"0"},{"city_name":"绍兴","city_code":"ZJ_SX","abbr":"浙","engine":"1","engineno":"6","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"温岭","city_code":"ZJ_WL","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"桐乡","city_code":"ZJ_TX","abbr":"浙","engine":"1","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"海宁","city_code":"ZJ_HN","abbr":"浙","engine":"1","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"诸暨","city_code":"ZJ_ZJ","abbr":"浙","engine":"1","engineno":"6","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"玉环县","city_code":"ZJ_YHX","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"上虞","city_code":"ZJ_SY","abbr":"浙","engine":"1","engineno":"6","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"湖州","city_code":"ZJ_HUZ","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"丽水","city_code":"ZJ_LS","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"衢州","city_code":"ZJ_QZ","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"舟山","city_code":"ZJ_ZS","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"临海","city_code":"ZJ_LH","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"平湖","city_code":"ZJ_PH","abbr":"浙","engine":"1","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"长兴县","city_code":"ZJ_CXX","abbr":"浙","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"}]},"JL":{"province":"吉林","province_code":"JL","citys":[{"city_name":"吉林","city_code":"JL","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"长春","city_code":"JL_CHANGCHUN","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"四平","city_code":"JL_SIPING","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"通化","city_code":"JL_TONGHUA","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"白山","city_code":"JL_BAISHAN","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"辽源","city_code":"JL_LIAOYUAN","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"松原","city_code":"JL_SONGYUAN","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"白城","city_code":"JL_BAICHENG","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"延边","city_code":"JL_YANBIAN","abbr":"吉","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"}]},"LN":{"province":"辽宁","province_code":"LN","citys":[{"city_name":"沈阳","city_code":"LN_SY","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"营口","city_code":"LN_YK","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"葫芦岛","city_code":"LN_HLD","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"鞍山","city_code":"LN_AS","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"抚顺","city_code":"LN_FS","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"丹东","city_code":"LN_DD","abbr":"辽","engine":"1","engineno":"3","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"阜新","city_code":"LN_FX","abbr":"辽","engine":"1","engineno":"0","classa":"0","class":"0","classno":"0","regist":"0","registno":"0"},{"city_name":"辽阳","city_code":"LN_LY","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"铁岭","city_code":"LN_TL","abbr":"辽","engine":"0","engineno":"0","classa":"0","class":"0","classno":"0","regist":"0","registno":"0"},{"city_name":"盘锦","city_code":"LN_PANJIN","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"本溪","city_code":"LN_BENXI","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"海城","city_code":"LN_HAICHENG","abbr":"辽","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"}]},"SD":{"province":"山东","province_code":"SD","citys":[{"city_name":"淄博","city_code":"SD_ZB","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"青岛","city_code":"SD_QD","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"威海","city_code":"SD_WH","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"枣庄","city_code":"SD_ZZ","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"日照","city_code":"SD_RZ","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"临沂","city_code":"SD_LY","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"莱芜","city_code":"SD_LW","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"菏泽","city_code":"SD_HZ","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"潍坊","city_code":"SD_WF","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"聊城","city_code":"SD_LC","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"济宁","city_code":"SD_JN","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"滨州","city_code":"SD_BZ","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"德州","city_code":"SD_DZ","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"东营","city_code":"SD_DY","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"泰安","city_code":"SD_TA","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"6","regist":"0","registno":"0"},{"city_name":"烟台","city_code":"SD_YT","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"4","regist":"0","registno":"0"},{"city_name":"济南","city_code":"SD_JINAN","abbr":"鲁","engine":"0","engineno":"0","classa":"1","class":"1","classno":"0","regist":"0","registno":"0"},{"city_name":"滕州","city_code":"SD_TENGZHOU","abbr":"鲁","engine":"0","engineno":"0","classa":...
//							
//							if (entity.getError_code() != 200 && entity.getError_code() != 0) {
//								Toast.makeText(getApplicationContext(), entity.getReason(),
//										Toast.LENGTH_SHORT).show();
//								return;
//							}
							
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		
		JuheData.executeWithAPI(36,
				"http://v.juhe.cn/wz/hpzl",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
//							{"resultcode":"200","reason":"成功返回","result":[{"car":"大型车","id":"01"},{"car":"小型车","id":"02"},{"car":"使馆汽车","id":"03"},{"car":"领馆汽车","id":"04"},
//							                                              {"car":"境外汽车","id":"05"},{"car":"外籍汽车","id":"06"},{"car":"两,三轮摩托车","id":"07"},{"car":"轻便摩托车","id":"08"},
//							                                              {"car":"使馆摩托车","id":"09"},{"car":"领馆摩托车","id":"10"},{"car":"境外摩托车","id":"11"},{"car":"外籍摩托车","id":"12"},
//							                                              {"car":"低速车","id":"13"},{"car":"拖拉机","id":"14"},{"car":"挂车","id":"15"},{"car":"教练汽车","id":"16"},{"car":"教练摩托车","id":"17"},
//							                                              {"car":"试验汽车","id":"18"},{"car":"试验摩托车","id":"19"},{"car":"临时入境汽车","id":"20"},{"car":"临时入境摩托车","id":"21"},{"car":"临时行驶车","id":"22"},
//							                                              {"car":"警用汽车","id":"23"},{"car":"警用摩托","id":"24"},{"car":"其他","id":"99"}],"error_code":0}
							hpzlListEntity entity = new Gson().fromJson(result, hpzlListEntity.class);
							if (entity.getError_code() != 200 && entity.getError_code() != 0) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							hpzllist = entity.getResult();
							violation_condition.setSelectCarTypeListener();
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	public static List<hpzlEntity> hpzllist;
	
	protected void onDestroy() {
		super.onDestroy();
		if (cityselectdialog != null) {
			cityselectdialog.dismiss();
			cityselectdialog = null;
		}
		if (hpzldialog != null) {
			hpzldialog.dismiss();
			hpzldialog = null;
		}
	}

	protected void onPause() {
		super.onPause();
		if (cityselectdialog != null) {
			cityselectdialog.dismiss();
			cityselectdialog = null;
		}
		if (hpzldialog != null) {
			hpzldialog.dismiss();
			hpzldialog = null;
		}
	}
}
