package com.juhe.pockettools.parking;

import java.util.List;

import com.zoombin.koudai.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ParkingYCAdapter extends BaseAdapter {

	private List<ParkingYC> list;

	private LayoutInflater mInflater;

	public ParkingYCAdapter(Context context, List<ParkingYC> list) {
		// TODO Auto-generated constructor stub

		this.list = list;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ParkingYC getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_listview_parkingyc,
					null);
			holder = new ViewHolder();
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			holder.tvYC = (TextView) convertView.findViewById(R.id.tv_yc);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTime.setText(getItem(position).getYCSJ());
		holder.tvYC.setText(getItem(position).getKCWZT());
		return convertView;
	}

	class ViewHolder {
		TextView tvTime, tvYC;
	}

}
