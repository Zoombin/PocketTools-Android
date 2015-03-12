package com.juhe.pockettools.train;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
//import com.fotoable.b.a;
import com.juhe.pockettools.R;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrainTimesSearchResultView extends FrameLayout {
	private Context context;
	private TrainTimesSearchResultViewHeader header;
	private ListView train_times_search_list;
	private ResultViewAdapter adapter;
	private OnSelectListener listener;
	private String g = "";
	private String h = "";

	public TrainTimesSearchResultView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainTimesSearchResultView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_train_times_search_result, this, true);
		header = new TrainTimesSearchResultViewHeader(context);
		train_times_search_list = ((ListView) findViewById(R.id.train_times_search_list));
		train_times_search_list.addHeaderView(header);
		adapter = new ResultViewAdapter(context);
		train_times_search_list.setAdapter(adapter);
		train_times_search_list.setVisibility(View.INVISIBLE);
	}

//	private void a(JSONObject paramJSONObject) {
//		this.e.a();
//		JSONObject localJSONObject1 = a.c(paramJSONObject, "result");
//		JSONArray localJSONArray;
//		if ((localJSONObject1 != null) && (localJSONObject1.length() > 0)) {
//			JSONObject localJSONObject2 = a.c(localJSONObject1, "train_info");
//			if (localJSONObject2 != null) {
//				this.e.a = a.a(localJSONObject2, "name");
//				this.e.b = a.a(localJSONObject2, "start");
//				this.e.d = a.a(localJSONObject2, "starttime");
//				this.e.c = a.a(localJSONObject2, "end");
//				this.e.e = a.a(localJSONObject2, "endtime");
//				this.e.f = a.a(localJSONObject2, "mileage");
//			}
//			localJSONArray = a.b(localJSONObject1, "station_list");
//			if (localJSONArray == null) {
//			}
//		}
//		for (int i = 0;; i++) {
//			if (i >= localJSONArray.length()) {
//				this.d.a(this.e.g);
//				header.a(this.e, this.g, this.h);
//				return;
//			}
//			JSONObject localJSONObject3 = a.a(localJSONArray, i);
//			r localr = new r();
//			localr.c = a.a(localJSONObject3, "arrived_time");
//			localr.h = a.a(localJSONObject3, "fsoftSeat");
//			localr.i = a.a(localJSONObject3, "hardSead");
//			localr.k = a.a(localJSONObject3, "hardSleep");
//			localr.d = a.a(localJSONObject3, "leave_time");
//			localr.f = a.a(localJSONObject3, "mileage");
//			localr.j = a.a(localJSONObject3, "softSeat");
//			localr.l = a.a(localJSONObject3, "softSleep");
//			localr.g = a.a(localJSONObject3, "ssoftSeat");
//			localr.b = a.a(localJSONObject3, "station_name");
//			localr.a = a.e(localJSONObject3, "train_id");
//			localr.e = a.a(localJSONObject3, "stay");
//			this.e.g.add(localr);
//		}
//	}
//
//	public void a(String paramString1, String paramString2, String paramString3) {
//		this.g = paramString2;
//		this.h = paramString3;
//		listview.setVisibility(4);
//		this.d.a(new ArrayList());
//		header.a(null, "", "");
//		String str1 = HelprRequestCore.a("TRAIN_API_KEY");
//		if (str1 == null) {
//			return;
//		}
//		b localb = new b();
//		String str2 = String.format(
//				"http://apis.juhe.cn/train/s?name=%s&key=%s", new Object[] {
//						paramString1, str1 });
//		localb.b(this.a, str2, new w(this));
//	}

	public void setData(TrainSEntity.Result result) {
		TrainSEntity.TrainInfo trainifo = result.getTrain_info();
		List<TrainSEntity.Station> list = result.getStation_list();
		
		header.setData(trainifo);
		adapter.setData(list);
		train_times_search_list.setVisibility(View.VISIBLE);
	}
	
	public void setListener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void a();
	}

	private class ResultViewAdapter extends BaseAdapter {
		private Context context;
		private List<TrainSEntity.Station> list;

		public ResultViewAdapter(Context context) {
			this.context = context;
			this.list = new ArrayList<TrainSEntity.Station>();
		}

		public void setData(List<TrainSEntity.Station> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			if (list != null) {
				return list.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int index) {
			return list.get(index);
		}

		@Override
		public long getItemId(int index) {
			return index;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TrainTimesSearchResultViewItem view = (TrainTimesSearchResultViewItem) convertView;
			TrainSEntity.Station entity = (TrainSEntity.Station) list.get(position);
			if (view == null) {
				view = new TrainTimesSearchResultViewItem(
						context, null);
			}
			if (position == 0) {
				view.setData(entity, 0);
				return view;
			}
			if (position == -1 + list.size()) {
				view.setData(entity, 3);
				return view;
			}
			view.setData(entity, 2);
			return view;
		}
	}
}
