package com.juhe.pockettools.courier;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

//import com.juhe.pockettools.wallpaper.w;

public class PackageMainActivity extends FullscreenActivity {
	private TopActiveBarView action_bar;
	private PackageSelectView package_company_select;
	private EditText edit_package_number;
	private Button btn_package_search;
	private ListView list_recently_search;
//	private String f = "ems";
//	private PackageRencentListAdapter adapter;
//	private ArrayList<HashMap<String, String>> list;
	private PackageInfoView packageinfoview;
	// private boolean j = false;
	// private int k = -1;
	private String com = "ems";

	// private String getCom(String com) {
	// if (com.equals(getResources().getString(2131230928))) {
	// return "ems";
	// }
	// if (com.equals(getResources().getString(2131230929))) {
	// return "sf";
	// }
	// if (com.equals(getResources().getString(2131230930))) {
	// return "sto";
	// }
	// if (com.equals(getResources().getString(2131230931))) {
	// return "yt";
	// }
	// if (com.equals(getResources().getString(2131230932))) {
	// return "yd";
	// }
	// if (com.equals(getResources().getString(2131230933))) {
	// return "tt";
	// }
	// if (com.equals(getResources().getString(2131230934))) {
	// return "zt";
	// }
	// if (com.equals(getResources().getString(2131230934))) {
	// return "ht";
	// }
	// return "ems";
	// }

	private void closePackageInfoView() {
		if (packageinfoview != null) {
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
					ViewGroup viewgroup = (ViewGroup) findViewById(android.R.id.content);
					viewgroup.removeView(packageinfoview);
					packageinfoview = null;
				}
			});
			packageinfoview.startAnimation(animation);
		}
	}

	private void startPackageInfoView(PackageEntity entity) {
		packageinfoview = new PackageInfoView(this, null);
		ViewGroup localViewGroup = (ViewGroup) findViewById(16908290);
		packageinfoview.setListener(new PackageInfoView.OnSelectListener() {

			@Override
			public void close() {
				closePackageInfoView();
			}

			@Override
			public void a(HashMap<String, String> paramHashMap) {
				// TODO Auto-generated method stub

			}
		});

		localViewGroup.addView(packageinfoview);
		Animation localAnimation = AnimationUtils.loadAnimation(this,
				R.anim.fragment_slide_left_enter);
		packageinfoview.setAnimation(localAnimation);
		packageinfoview.setData(entity);
	}

	// private void a(ArrayList<HashMap<String, String>> paramArrayList) {
	// SharedPreferences localSharedPreferences = getSharedPreferences(
	// "recent_search", 0);
	// localSharedPreferences.edit().clear();
	// SharedPreferences.Editor localEditor = localSharedPreferences.edit();
	// localEditor.putInt("list_count", paramArrayList.size());
	// for (int m = 0;; m++) {
	// if (m >= paramArrayList.size()) {
	// localEditor.commit();
	// return;
	// }
	// HashMap localHashMap = (HashMap) paramArrayList.get(m);
	// localEditor.putString("company" + String.valueOf(m),
	// (String) localHashMap.get(PackageRencentListAdapter.a));
	// localEditor.putString("number" + String.valueOf(m),
	// (String) localHashMap.get(PackageRencentListAdapter.b));
	// localEditor.putString("date" + String.valueOf(m),
	// (String) localHashMap.get(PackageRencentListAdapter.c));
	// }
	// }

	// private ArrayList<HashMap<String, String>> getListData() {
	// ArrayList localArrayList = new ArrayList();
	// SharedPreferences localSharedPreferences = getSharedPreferences(
	// "recent_search", 0);
	// int m = localSharedPreferences.getInt("list_count", 0);
	// int n = 0;
	// if (m == 0) {
	// return localArrayList;
	// }
	// while (n < m) {
	// HashMap localHashMap = new HashMap();
	// localHashMap.put(
	// PackageRencentListAdapter.a,
	// localSharedPreferences.getString(
	// "company" + String.valueOf(n), ""));
	// localHashMap.put(
	// PackageRencentListAdapter.b,
	// localSharedPreferences.getString(
	// "number" + String.valueOf(n), ""));
	// localHashMap.put(PackageRencentListAdapter.c,
	// localSharedPreferences.getString(
	// "date" + String.valueOf(n), ""));
	// localArrayList.add(localHashMap);
	// n++;
	// }
	// return localArrayList;
	// }

	public void onBackPressed() {
		if (packageinfoview != null) {
			closePackageInfoView();
		} else {
			finish();
		}
	}

	@SuppressLint("NewApi")
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_package_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		package_company_select = ((PackageSelectView) findViewById(R.id.package_company_select));
		edit_package_number = ((EditText) findViewById(R.id.edit_package_number));
		btn_package_search = ((Button) findViewById(R.id.btn_package_search));
		list_recently_search = ((ListView) findViewById(R.id.list_recently_search));
		list_recently_search.setOverScrollMode(2);
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void query() {

			}

			@Override
			public void cancel() {
				finish();
			}
		});
		action_bar.setTiltleText("快递查询");
		package_company_select
				.setLisntener(new PackageSelectView.OnSelectListener() {

					@Override
					public void setCourier(String courier) {
						com = courier;
					}
				});
		btn_package_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Parameters params = new Parameters();
				params.add("com", com);
				params.add("no", edit_package_number.getText().toString());
				params.add("key", "9b5b7b0a8edf2102283955ffba0f29fe");
				JuheData.executeWithAPI(43, "http://v.juhe.cn/exp/index",
						JuheData.GET, params, new DataCallBack() {

							@Override
							public void resultLoaded(int err, String reason,
									String result) {
								action_bar.setProgressVisiable(View.INVISIBLE);

								if (err == 0) {
									PackageEntity entity = new Gson().fromJson(
											result, PackageEntity.class);
									if (entity.getError_code() != 0
											&& entity.getError_code() != 200) {
										Toast.makeText(getApplicationContext(),
												entity.getReason(),
												Toast.LENGTH_SHORT).show();
										return;
									}
									startPackageInfoView(entity);
								} else {
									Toast.makeText(getApplicationContext(),
											reason, Toast.LENGTH_SHORT).show();
								}
							}
						});
			}
		});
//		adapter = new PackageRencentListAdapter(this);
		// list_recently_search.setAdapter(adapter);
		// list_recently_search.setOnItemClickListener(new OnItemClickListener()
		// {
		// });
		// list = getListData();
		// adapter.setData(list);
	}
}
