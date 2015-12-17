package com.juhe.pockettools.train;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zoombin.koudai.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.violation.ViolationMainActivity;
import com.juhe.pockettools.violation.hpzlEntity;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class TrainMainItem1View extends FrameLayout {
	private EditText train_edit_start;
	private EditText train_edit_end;
	private Button train_btn_select_type;
	private Button train_btn_search;
	private ListView train_list;
	private TrainAdapter adapter;
	private Context context;
	private TrainTimesResultView k;

	public TrainMainItem1View(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainMainItem1View(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private int traintypeindex = 0;
	String[] traintypes = { "全部", "高速动车", "快速" , "空调特快", "动车组", "直达特快", "其他"};
	String traintype = "全部";

	private void initView() {

		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_train_main_item1, this, true);
		train_edit_start = ((EditText) findViewById(R.id.train_edit_start));
		train_edit_end = ((EditText) findViewById(R.id.train_edit_end));
		train_btn_select_type = ((Button) findViewById(R.id.train_btn_select_type));
		train_btn_search = ((Button) findViewById(R.id.train_btn_search));
		train_list = ((ListView) findViewById(R.id.train_list));
		train_btn_select_type.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getContext())
						.setTitle("请选择类型类型")
						.setSingleChoiceItems(traintypes, traintypeindex,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {

										traintype = traintypes[item];
										traintypeindex = item;
										train_btn_select_type.setText(traintype);
										getData();
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
		adapter = new TrainAdapter(context);
		train_list.setAdapter(adapter);
		// train_list.setOnItemClickListener(new j(this));
		train_btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getData();
			}
		});
	}

	private void getData() {
		String start = train_edit_start.getText().toString();
		String end = train_edit_end.getText().toString();
		if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
			return;
		}
		Parameters params = new Parameters();
		params.add("start", start);
		params.add("end", end);
		if (!traintype.equals("全部")) {
			String traint;
//			 "高速动车", "快速" , "空调特快", "动车组", "直达特快", "其他"};
			if (traintype.equals("高速动车")) {
				traint = "G";
			}
			else if (traintype.equals("快速")) {
				traint = "K";
			}
			else if (traintype.equals("空调特快")) {
				traint = "T";
			}
			else if (traintype.equals("动车组")) {
				traint = "D";
			}
			else if (traintype.equals("直达特快")) {
				traint = "Z";
			} else {
				traint = "Q";
			}
			params.add("traintype", traint);
		}
		params.add("key", "5d13fc2cdf35af76249cfb8f8c67d424");
		JuheData.executeWithAPI(22, "http://apis.juhe.cn/train/s2s",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
							TrainS2SEntity entity = new Gson().fromJson(result,
									TrainS2SEntity.class);
							if (entity.getError_code() != 0
									&& entity.getError_code() != 200) {
								Toast.makeText(context, entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}

							adapter.setData(entity.getResult().getData());
						} else {
							Toast.makeText(context, reason, Toast.LENGTH_SHORT)
									.show();
						}
					}
				});
	}

	// private void a(int paramInt) {
	// switch (paramInt) {
	// default:
	// return;
	// case 1:
	// this.j = "";
	// train_btn_select_type.setText(2131231127);
	// return;
	// case 2:
	// this.j = "G";
	// train_btn_select_type.setText(2131231128);
	// return;
	// case 3:
	// this.j = "D";
	// train_btn_select_type.setText(2131231129);
	// return;
	// case 4:
	// this.j = "Z";
	// train_btn_select_type.setText(2131231130);
	// return;
	// case 5:
	// this.j = "T";
	// train_btn_select_type.setText(2131231131);
	// return;
	// case 6:
	// this.j = "K";
	// train_btn_select_type.setText(2131231132);
	// return;
	// }
	// this.j = "Q";
	// train_btn_select_type.setText(2131231133);
	// }

	// private void a(String paramString1, String paramString2, String
	// paramString3) {
	// this.g.a(new ArrayList());
	// String str1 = HelprRequestCore.a("TRAIN_API_KEY");
	// if (str1 == null) {
	// return;
	// }
	// HashMap localHashMap = new HashMap();
	// localHashMap.put("type", "站站查询");
	// FlurryAgent.logEvent("HelprSearch_train火车查询", localHashMap);
	// b localb = new b();
	// String str2 = String
	// .format("http://apis.juhe.cn/train/s2s?start=%s&end=%s&traintype=%s&key=%s",
	// new Object[] { paramString1, paramString2,
	// paramString3, str1 });
	// localb.b(context, str2, new m(this));
	// }
	//
	// private void a(JSONObject paramJSONObject) {
	// this.l.clear();
	// JSONObject localJSONObject1 = com.fotoable.b.a.c(paramJSONObject,
	// "result");
	// JSONArray localJSONArray;
	// if ((localJSONObject1 != null) && (localJSONObject1.length() > 0)) {
	// localJSONArray = com.fotoable.b.a.b(localJSONObject1, "data");
	// if ((localJSONArray == null) || (localJSONArray.length() <= 0)) {
	// }
	// }
	// for (int m = 0;; m++) {
	// if (m >= localJSONArray.length()) {
	// this.g.a(this.l);
	// return;
	// }
	// JSONObject localJSONObject2 = com.fotoable.b.a.a(localJSONArray, m);
	// a locala = new a();
	// locala.f = com.fotoable.b.a.a(localJSONObject2, "arrived_time");
	// locala.d = com.fotoable.b.a.a(localJSONObject2, "end_station");
	// locala.i = com.fotoable.b.a.a(localJSONObject2, "fsoftSeat");
	// locala.j = com.fotoable.b.a.a(localJSONObject2, "hardSead");
	// locala.l = com.fotoable.b.a.a(localJSONObject2, "hardSleep");
	// locala.e = com.fotoable.b.a.a(localJSONObject2, "leave_time");
	// locala.g = com.fotoable.b.a.a(localJSONObject2, "mileage");
	// locala.k = com.fotoable.b.a.a(localJSONObject2, "softSeat");
	// locala.m = com.fotoable.b.a.a(localJSONObject2, "softSleep");
	// locala.h = com.fotoable.b.a.a(localJSONObject2, "ssoftSeat");
	// locala.c = com.fotoable.b.a.a(localJSONObject2, "start_staion");
	// locala.a = com.fotoable.b.a.a(localJSONObject2, "trainOpp");
	// locala.b = com.fotoable.b.a.a(localJSONObject2, "train_typename");
	// this.l.add(locala);
	// }
	// }
	//
	// private void b() {
	// if (this.h == null) {
	// ArrayList localArrayList = new ArrayList();
	// HashMap localHashMap1 = new HashMap();
	// localHashMap1.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231134));
	// localHashMap1.put(com.fotoable.helpr.commonview.i.b,
	// Integer.valueOf(-7829368));
	// localHashMap1.put(com.fotoable.helpr.commonview.i.c,
	// Integer.valueOf(14));
	// localHashMap1.put(com.fotoable.helpr.commonview.i.d,
	// Integer.valueOf(2130837669));
	// localArrayList.add(localHashMap1);
	// HashMap localHashMap2 = new HashMap();
	// localHashMap2.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231127));
	// localArrayList.add(localHashMap2);
	// HashMap localHashMap3 = new HashMap();
	// localHashMap3.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231128));
	// localArrayList.add(localHashMap3);
	// HashMap localHashMap4 = new HashMap();
	// localHashMap4.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231129));
	// localArrayList.add(localHashMap4);
	// HashMap localHashMap5 = new HashMap();
	// localHashMap5.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231130));
	// localArrayList.add(localHashMap5);
	// HashMap localHashMap6 = new HashMap();
	// localHashMap6.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231131));
	// localArrayList.add(localHashMap6);
	// HashMap localHashMap7 = new HashMap();
	// localHashMap7.put(com.fotoable.helpr.commonview.i.a, getResources()
	// .getString(2131231132));
	// localArrayList.add(localHashMap7);
	// this.h = new com.fotoable.helpr.commonview.i(context, localArrayList);
	// this.h.a(new l(this));
	// }
	// }

	// public void setListener(q paramq) {
	// this.a = paramq;
	// }

	private class TrainAdapter extends BaseAdapter {
		private Context context;
		private List<TrainS2SEntity.Data> list;

		public TrainAdapter(Context context) {
			this.context = context;
			list = new ArrayList<TrainS2SEntity.Data>();
		}

		public void setData(List<TrainS2SEntity.Data> list) {
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
			TrainS2SEntity.Data entity = (TrainS2SEntity.Data) list
					.get(position);
			ViewHolder holder;

			if (convertView == null) {
				convertView = ((LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.view_train_main_item1_result_item,
								null);

				holder = new ViewHolder();

				holder.times = (TextView) convertView.findViewById(R.id.times);
				holder.type = (TextView) convertView.findViewById(R.id.type);
				holder.begin = (TextView) convertView.findViewById(R.id.begin);
				holder.begintime = (TextView) convertView
						.findViewById(R.id.begintime);
				holder.end = (TextView) convertView.findViewById(R.id.end);
				holder.endtime = (TextView) convertView
						.findViewById(R.id.endtime);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.times.setText(entity.getTrainOpp());
			holder.type.setText(entity.getTrain_typename());
			holder.begin.setText(entity.getStart_staion());
			holder.begintime.setText(entity.getLeave_time());
			holder.end.setText(entity.getEnd_station());
			holder.endtime.setText(entity.getArrived_time());

			return convertView;
		}
	}

	private class ViewHolder {
		private TextView times;
		private TextView type;
		private TextView begin;
		private TextView begintime;
		private TextView end;
		private TextView endtime;
	}
}
