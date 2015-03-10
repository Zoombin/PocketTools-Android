package com.juhe.pockettools.train;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;

import com.juhe.pockettools.R;

public class TrainTimesSearchResultViewHeader extends FrameLayout {
	private Context context;
	private TextView train_station_count;
	private TextView train_times;
	private TextView train_txt_begin;
	private TextView train_txt_begin_time;
	private TextView train_txt_end;
	private TextView train_txt_end_time;

	public TrainTimesSearchResultViewHeader(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainTimesSearchResultViewHeader(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_train_times_search_result_header, this, true);
		train_station_count = ((TextView) findViewById(R.id.train_station_count));
		train_times = ((TextView) findViewById(R.id.train_times));
		train_txt_begin = ((TextView) findViewById(R.id.train_txt_begin));
		train_txt_begin_time = ((TextView) findViewById(R.id.train_txt_begin_time));
		train_txt_end = ((TextView) findViewById(R.id.train_txt_end));
		train_txt_end_time = ((TextView) findViewById(R.id.train_txt_end_time));
	}

	public void setData(TrainSEntity.TrainInfo trainifo) {
//		"train_info":{"name":"G102","start":"上海虹桥","end":"北京南","starttime":"07:00","endtime":"12:23","mileage":"1318km"},
		train_station_count.setText("总里程：" + trainifo.getMileage());
		train_times.setText(trainifo.getName());
		train_txt_begin.setText(trainifo.getStart());
		train_txt_begin_time.setText(trainifo.getStarttime());
		train_txt_end.setText(trainifo.getEnd());
		train_txt_end_time.setText(trainifo.getEndtime());
	}
	
//	public void a(v paramv, String paramString1, String paramString2) {
//		if (paramv == null) {
//			train_station_count.setText("");
//			train_times.setText("");
//			train_txt_begin.setText("");
//			train_txt_begin_time.setText("");
//			train_txt_end.setText("");
//			train_txt_end_time.setText("");
//		}
//		String[] arrayOfString1;
//		do {
//			do {
//				return;
//				String str = "共" + String.valueOf(paramv.g.size()) + "站  总里程:"
//						+ paramv.f;
//				train_station_count.setText(str);
//				train_times.setText(paramv.a);
//				train_txt_begin.setText(paramv.b);
//				train_txt_begin_time.setText(paramv.d);
//				train_txt_end.setText(paramv.c);
//				train_txt_end_time.setText(paramv.e);
//				if (paramString1 != null) {
//					String[] arrayOfString2 = paramString1.split("•");
//					if (arrayOfString2.length >= 2) {
//						train_txt_begin.setText(arrayOfString2[0]);
//						train_txt_end.setText(arrayOfString2[1]);
//					}
//				}
//			} while (paramString2 == null);
//			arrayOfString1 = paramString2.split(",");
//		} while (arrayOfString1.length < 2);
//		train_txt_begin_time.setText(arrayOfString1[0]);
//		train_txt_end_time.setText(arrayOfString1[1]);
//	}
}
