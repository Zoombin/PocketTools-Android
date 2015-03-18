package com.juhe.pockettools.train;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TrainTimesSearchResultViewItem extends FrameLayout {
	private Context context;
	private TextView train_station;
	private TextView train_stand_time;
	private TextView train_distance;
	private FrameLayout train_seat_ying;
	private TextView train_seat_ying_price;
	private FrameLayout train_seat_ruan;
	private TextView train_seat_ruan_price;
	private FrameLayout train_bed_ying;
	private TextView train_bed_ying_price;
	private FrameLayout train_bed_ruan;
	private TextView train_bed_ruan_price;
	private FrameLayout train_gao_one;
	private TextView train_gao_one_price;
	private FrameLayout train_gao_two;
	private TextView train_gao_two_price;

	public TrainTimesSearchResultViewItem(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainTimesSearchResultViewItem(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_train_times_search_result_item, this, true);
		train_station = ((TextView) findViewById(R.id.train_station));
		train_stand_time = ((TextView) findViewById(R.id.train_stand_time));
		train_distance = ((TextView) findViewById(R.id.train_distance));
		train_seat_ying = ((FrameLayout) findViewById(R.id.train_seat_ying));
		train_seat_ying_price = ((TextView) findViewById(R.id.train_seat_ying_price));
		train_seat_ruan = ((FrameLayout) findViewById(R.id.train_seat_ruan));
		train_seat_ruan_price = ((TextView) findViewById(R.id.train_seat_ruan_price));
		train_bed_ying = ((FrameLayout) findViewById(R.id.train_bed_ying));
		train_bed_ying_price = ((TextView) findViewById(R.id.train_bed_ying_price));
		train_bed_ruan = ((FrameLayout) findViewById(R.id.train_bed_ruan));
		train_bed_ruan_price = ((TextView) findViewById(R.id.train_bed_ruan_price));
		train_gao_one = ((FrameLayout) findViewById(R.id.train_gao_one));
		train_gao_one_price = ((TextView) findViewById(R.id.train_gao_one_price));
		train_gao_two = ((FrameLayout) findViewById(R.id.train_gao_two));
		train_gao_two_price = ((TextView) findViewById(R.id.train_gao_two_price));
	}

	public void setData(TrainSEntity.Station entity, int paramInt) {
		if (entity == null) {
			return;
		}
//		{"resultcode":"200","reason":"Successed!",
//		"result":{"train_info":{"name":"G102","start":"上海虹桥","end":"北京南","starttime":"07:00","endtime":"12:23","mileage":"1318km"},
//		"station_list":[{"train_id":"1","station_name":"上海虹桥","arrived_time":"-","leave_time":"07:00","mileage":"-","fsoftSeat":"-","ssoftSeat":"-","hardSead":"-",
//			"softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"-","swz":"-","tdz":"-","gjrw":"-","stay":"-"},
//			{"train_id":"2","station_name":"常州北","arrived_time":"07:40","leave_time":"07:42","mileage":"165km","fsoftSeat":"129.5","ssoftSeat":"74.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"74.5","swz":"239.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"3","station_name":"南京南","arrived_time":"08:14","leave_time":"08:16","mileage":"295km","fsoftSeat":"229.5","ssoftSeat":"134.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"134.5","swz":"429.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"4","station_name":"滁州","arrived_time":"08:34","leave_time":"08:36","mileage":"354km","fsoftSeat":"274.5","ssoftSeat":"164.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"164.5","swz":"514.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"5","station_name":"枣庄","arrived_time":"09:50","leave_time":"09:52","mileage":"691km","fsoftSeat":"519.0","ssoftSeat":"309.0","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"309.0","swz":"974.0","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"6","station_name":"济南西","arrived_time":"10:42","leave_time":"10:44","mileage":"912km","fsoftSeat":"673.5","ssoftSeat":"398.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"398.5","swz":"1263.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"7","station_name":"德州东","arrived_time":"11:08","leave_time":"11:10","mileage":"1004km","fsoftSeat":"738.5","ssoftSeat":"438.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"438.5","swz":"1383.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"8","station_name":"北京南","arrived_time":"12:23","leave_time":"12:23","mileage":"1318km","fsoftSeat":"933.0","ssoftSeat":"553.0","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"553.0","swz":"1748.0","tdz":"-","gjrw":"-","stay":"-"}]},"error_code":0}

		
		train_station.setText(entity.getStation_name());
		train_stand_time.setText(entity.getLeave_time());
		train_distance.setText(entity.getMileage());
		train_seat_ying_price.setText(entity.getHardSead() + "元");
		train_seat_ruan_price.setText(entity.getSoftSeat() + "元");
		train_bed_ying_price.setText(entity.getHardSleep() + "元");
		train_bed_ruan_price.setText(entity.getSoftSleep() + "元");
		train_gao_one_price.setText(entity.getSwz() + "元");
		train_gao_two_price.setText(entity.getWuzuo() + "元");
	}
}
