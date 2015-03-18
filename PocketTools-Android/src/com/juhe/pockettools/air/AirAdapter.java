package com.juhe.pockettools.air;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.train.TrainS2SEntity;

public class AirAdapter extends BaseAdapter {
	private Context context;
	private List<AirEntity.Result> list;

	public AirAdapter(Context context) {
		this.context = context;
		list = new ArrayList<AirEntity.Result>();
	}

	public void setData(List<AirEntity.Result> list) {
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
		AirEntity.Result entity = (AirEntity.Result) list.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.view_air_main_item, null);

			holder = new ViewHolder();

			holder.flightnum = (TextView) convertView.findViewById(R.id.flightnum);
			holder.airline = (TextView) convertView.findViewById(R.id.airline);
			holder.depcity = (TextView) convertView.findViewById(R.id.depcity);
			holder.deptime = (TextView) convertView
					.findViewById(R.id.deptime);
			holder.arrcity = (TextView) convertView.findViewById(R.id.arrcity);
			holder.arrtime = (TextView) convertView.findViewById(R.id.arrtime);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.flightnum.setText(entity.getFlightNum());
		holder.airline.setText(entity.getAirline());
		holder.depcity.setText(entity.getDepCity());
		holder.deptime.setText(entity.getDepTime());
		holder.arrcity.setText(entity.getArrCity());
		holder.arrtime.setText(entity.getArrTime());

		return convertView;
	}

	private class ViewHolder {
		private TextView flightnum;
		private TextView airline;
		private TextView depcity;
		private TextView deptime;
		private TextView arrcity;
		private TextView arrtime;
	}
}
