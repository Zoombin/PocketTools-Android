package com.juhe.pockettools.dream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
//import com.fotoable.helpr.wallpaper.w;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DreamResultFragment extends Fragment {
	private static final String TAG = "DreamResultFragment";
	private DreamMainActivity activity;
	private DreamResultAdapter adapter = null;
	private ListView dream_main_listveiw;
	private TopActiveBarView action_bar;
	private List<Dream.Result> list = new ArrayList<Dream.Result>();
	private String title;

	public static DreamResultFragment getDreamResultFragment(String title,
			List<Dream.Result> resultlist) {
		DreamResultFragment localDreamResultFragment = new DreamResultFragment();
		localDreamResultFragment.title = title;
		localDreamResultFragment.list = resultlist;
		return localDreamResultFragment;
	}

	public static String a(String paramString) {
		String str = "";
		if (paramString != null) {
			str = Pattern.compile("\\s*|\t|\r|\n").matcher(paramString)
					.replaceAll("");
		}
		return str;
	}

	private void startDreamDetailFragment(Dream.Result entity) {
		if (getActivity().getSupportFragmentManager().getBackStackEntryCount() < 2) {
			FragmentTransaction transaction = getActivity()
					.getSupportFragmentManager().beginTransaction();
			DreamDetailFragment fragment = DreamDetailFragment
					.getDreamDetailFragment(entity);
			transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
					R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);
			transaction.add(R.id.content_frame, fragment);
			transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}

	private String b(String paramString) {
		if (paramString != null) {
			String[] arrayOfString = paramString.split("\n");
			if (arrayOfString.length > 0) {
				paramString = a(arrayOfString[0]);
			}
		}
		return paramString;
	}

	@Override
	public void onActivityCreated(Bundle paramBundle) {
		super.onActivityCreated(paramBundle);
		if ((activity != null) && (adapter == null)) {
			adapter = new DreamResultAdapter(getActivity());
			adapter.setData(list);
			dream_main_listveiw.setAdapter(adapter);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = ((DreamMainActivity) activity);
		Log.d("DreamResultFragment", "onAttach");
	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Log.v("DreamResultFragment", "DreamResultFragment onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("DreamResultFragment", "DreamResultFragment onCreateView");
		View view = inflater.inflate(R.layout.fragment_dream_result,
				container, false);
//		((ImageView) view.findViewById(R.id.img_bg)).setImageBitmap(w.a()
//				.d());
		dream_main_listveiw = ((ListView) view.findViewById(R.id.dream_main_listveiw));
//		dream_main_listveiw.setOnItemClickListener(new l(this));
		action_bar = ((TopActiveBarView) view.findViewById(R.id.action_bar));
		action_bar.setTiltleText("周公解梦");
		action_bar.setTiltleText(title);
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				activity.close();
			}
		});
		return view;
	}

	private class DreamResultAdapter extends BaseAdapter {
		private List<Dream.Result> list = new ArrayList<Dream.Result>();
		private Context context;

		public DreamResultAdapter(Context context) {
			this.context = context;
		}

		public void setData(List<Dream.Result> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			if (list.size() <= 0) {
				return null;
			}
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final Dream.Result entity = (Dream.Result) list.get(position);
			ViewHolder holder;
			
			if (convertView == null) {
				holder = new ViewHolder();
				
				convertView = ((LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
						R.layout.view_dream_category_item, null);
				holder.layout_item = (FrameLayout) convertView.findViewById(R.id.layout_item);
				holder.txt_category = (TextView) convertView.findViewById(R.id.txt_category);
				holder.txt_category_detail = (TextView) convertView.findViewById(R.id.txt_category_detail);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.layout_item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startDreamDetailFragment(entity);
				}
			});
			holder.txt_category.setText(entity.getTitle());
			holder.txt_category_detail.setText(entity.getDes());
			
			return convertView;
		}
	}

	private class ViewHolder {
		private FrameLayout layout_item;
		private TextView txt_category;
		private TextView txt_category_detail;
	}
}
