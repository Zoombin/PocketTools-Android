package com.juhe.pockettools.dream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import com.fotoable.helpr.wallpaper.w;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DreamDetailFragment extends Fragment {
	private static final String a = "DreamMainFragment";
	private DreamMainActivity activity;
	private TopActiveBarView action_bar;
	private TextView textview;
	private Dream.Result dream;
	private DreamDetailAdapter adapter = null;
	private ListView dream_main_listveiw;
	private List<String> list = new ArrayList<String>();

	public static DreamDetailFragment getDreamDetailFragment(Dream.Result entity) {
		DreamDetailFragment fragment = new DreamDetailFragment();
		fragment.dream = entity;
		return fragment;
	}

	private ArrayList<String> getList(String str) {
		ArrayList<String> list = new ArrayList<String>();
		String[] str1 = str.split("\n");
		for (int i = 0; i < str1.length; i++) {
			list.add(str1[i]);
		}
		return list;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = ((DreamMainActivity) activity);
		Log.d("DreamMainFragment", "onAttach");
	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Log.v("DreamMainFragment", "DreamMainFragment onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("DreamMainFragment", "DreamMainFragment onCreateView");
		View view = inflater.inflate(R.layout.fragment_dream_detail,
				container, false);
//		((ImageView) view.findViewById(R.id.img_bg)).setImageBitmap(w.a()
//				.d());
		action_bar = ((TopActiveBarView) view.findViewById(R.id.action_bar));
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				activity.close();
			}
		});
		dream_main_listveiw = ((ListView) view.findViewById(R.id.dream_main_listveiw));
		adapter = new DreamDetailAdapter(getActivity());
		dream_main_listveiw.setAdapter(adapter);
		action_bar.setTiltleText(dream.getTitle());
//		action_bar.setProgressVisiable(View.VISIBLE);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if (dream.getList() != null) {
					list = Arrays.asList(dream.getList());
					adapter.setData(list);
				}
			}
		}, 300L);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.v("DreamMainFragment", "DreamMainFragment onresume");
	}

	private class DreamDetailAdapter extends BaseAdapter {
		private List<String> list = new ArrayList<String>();
		private Context context;

		public DreamDetailAdapter(Context context) {
			this.context = context;
		}

		public void setData(List<String> list) {
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
			String str = (String) list.get(position);
			ViewHolder holder;
			
			if (convertView == null) {
				holder = new ViewHolder();
				
				convertView = ((LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
						R.layout.view_dream_detail_item, null);
				holder.txt_category_detail = (TextView) convertView.findViewById(R.id.txt_category_detail);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.txt_category_detail.setText(str);
			return convertView;
		}
	}

	private class ViewHolder {
		private TextView txt_category_detail;
	}
}
