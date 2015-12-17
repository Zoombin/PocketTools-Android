package com.juhe.pockettools.dream;

//import com.fotoable.helpr.wallpaper.w;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zoombin.koudai.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class DreamMainFragment extends Fragment {
	private static final String TAG = "DreamMainFragment";
	private DreamMainActivity activity;
	private DreamAdapter adapter = null;
	private ListView dream_main_listveiw;
	private TopActiveBarView action_bar;
	private EditText txtiput;
	private Button btn_dream_search;
	
	private void startDreamDetailFragment(String searchtext) {
		if ((searchtext != null) && (searchtext.length() != 0)) {
			FragmentTransaction transaction = getActivity()
					.getSupportFragmentManager().beginTransaction();
			DreamResultFragment fragment = DreamResultFragment.getDreamResultFragment(
					searchtext, null);
			transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
					R.anim.fragment_slide_left_exit,
					R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);
			transaction.add(R.id.content_frame, fragment);
			transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}

	private void startDreamResultFragment(String title, List<Dream.Result> resultlist) {
		if (getActivity().getSupportFragmentManager().getBackStackEntryCount() < 1) {
			FragmentTransaction transaction = getActivity()
					.getSupportFragmentManager().beginTransaction();
			DreamResultFragment fragment = DreamResultFragment
					.getDreamResultFragment(title, resultlist);
			transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
					R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);
			transaction.add(R.id.content_frame, fragment);
			transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}

	public void setData(List<Category.Result> categorylist) {
		if ((activity != null) && (isAdded())) {
			if (adapter == null) {
				adapter = new DreamAdapter(getActivity());
				adapter.setData(categorylist);
				dream_main_listveiw.setAdapter(adapter);
			}
		} else {
			adapter.setData(categorylist);
		}
	}

	@Override
	public void onActivityCreated(Bundle bundle) {
		super.onActivityCreated(bundle);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = ((DreamMainActivity) activity);
		Log.d("DreamMainFragment", "onAttach");
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		Log.v("DreamMainFragment", "DreamMainFragment onCreate");
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("DreamMainFragment", "DreamMainFragment onCreateView");
		View view = inflater.inflate(R.layout.fragment_dream_main, container,
				false);

		((ImageView) view.findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		
		action_bar = ((TopActiveBarView) view.findViewById(R.id.action_bar));
		action_bar.setTiltleText("周公解梦");
		action_bar.setProgressVisiable(View.VISIBLE);
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void query() {

			}

			@Override
			public void cancel() {
				activity.finish();
			}
		});

		btn_dream_search = (Button) view.findViewById(R.id.btn_dream_search);
		btn_dream_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
			}
		});
		
		txtiput = ((EditText) view.findViewById(R.id.txtiput));
		txtiput.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int arg1, KeyEvent arg2) {
				if (arg1 == 6) {

					
					getData();
					
					return true;
				}
				return false;
			}
		});
		
		dream_main_listveiw = ((ListView) view
				.findViewById(R.id.dream_main_listveiw));
		// dream_main_listveiw.setOnItemClickListener(new j(this));
		if (activity != null) {
			Parameters params = new Parameters();
			params.add("key", "e10ef3445ac25e570094dcf48bece26a");
			JuheData.executeWithAPI(64, "http://v.juhe.cn/dream/category",
					JuheData.GET, params, new DataCallBack() {

						@Override
						public void resultLoaded(int err, String reason,
								String result) {
							action_bar.setProgressVisiable(View.INVISIBLE);

							if (err == 0) {
								Category entity = new Gson().fromJson(
										result, Category.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(activity,
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}
								List<Category.Result> categorylist = entity
										.getResult();
								setData(categorylist);
							} else {
								Toast.makeText(activity, reason,
										Toast.LENGTH_SHORT).show();
							}
						}
					});
		}
		return view;
	}

	private void getData() {

		
		txtiput.clearFocus();
		
		action_bar.setProgressVisiable(View.VISIBLE);
		
		Parameters params = new Parameters();
		params.add("full", 1);
		params.add("q", txtiput.getText().toString());
		params.add("key", "e10ef3445ac25e570094dcf48bece26a");
		JuheData.executeWithAPI(64, "http://v.juhe.cn/dream/query",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						action_bar.setProgressVisiable(View.INVISIBLE);

						if (err == 0) {
							Dream entity = new Gson().fromJson(
									result, Dream.class);
							if (entity.getError_code() != 0
									&& entity.getError_code() != 200) {
								Toast.makeText(activity,
										entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							((InputMethodManager) txtiput.getContext().getSystemService(
									Context.INPUT_METHOD_SERVICE))
									.hideSoftInputFromWindow(txtiput.getWindowToken(), 0);
							
							List<Dream.Result> resultlist = entity
									.getResult();
							startDreamResultFragment(txtiput.getText().toString(), resultlist);
						} else {
							Toast.makeText(activity, reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	private class DreamAdapter extends BaseAdapter {
		private List<Category.Result> categorylist = new ArrayList<Category.Result>();
		private Context context;

		public DreamAdapter(Context context) {
			this.context = context;
		}

		public void setData(List<Category.Result> categorylist) {
			this.categorylist = categorylist;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return categorylist.size();
		}

		@Override
		public Object getItem(int position) {
			if (categorylist.size() <= 0) {
				return null;
			}
			return categorylist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final Category.Result entity = (Category.Result) categorylist
					.get(position);

			ViewHolder holder;

			if (convertView == null) {
				holder = new ViewHolder();

				convertView = ((LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.view_dream_category_item, null);
				holder.layout_item = (FrameLayout) convertView
						.findViewById(R.id.layout_item);
				holder.txt_category = (TextView) convertView
						.findViewById(R.id.txt_category);
				holder.txt_category_detail = (TextView) convertView
						.findViewById(R.id.txt_category_detail);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.layout_item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					action_bar.setProgressVisiable(View.VISIBLE);
					 
					Parameters params = new Parameters();
					params.add("fid", entity.getId());
					params.add("key", "e10ef3445ac25e570094dcf48bece26a");
					JuheData.executeWithAPI(64, "http://v.juhe.cn/dream/category",
							JuheData.GET, params, new DataCallBack() {

								@Override
								public void resultLoaded(int err, String reason,
										String result) {
									action_bar.setProgressVisiable(View.INVISIBLE);

									if (err == 0) {
										Dream entity = new Gson().fromJson(
												result, Dream.class);
										if (entity.getError_code() != 0
												&& entity.getError_code() != 200) {
											Toast.makeText(activity,
													entity.getReason(),
													Toast.LENGTH_SHORT).show();
											return;
										}
										List<Dream.Result> resultlist = entity
												.getResult();
//										setData(categorylist);
//										startDreamResultFragment(txtiput.getText().toString());
									} else {
										Toast.makeText(activity, reason,
												Toast.LENGTH_SHORT).show();
									}
								}
							});
				}
			});
			holder.txt_category.setText(entity.getName());
			// holder.txt_category_detail.setText(entity.category_detail);

			return convertView;
		}
	}

	private class ViewHolder {
		private FrameLayout layout_item;
		private TextView txt_category;
		private TextView txt_category_detail;
	}
}
