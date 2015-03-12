package com.juhe.pockettools.weather;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.HelprCommUtil;

public class WeatherDayAdapter extends BaseAdapter {
	private LayoutInflater layoutinflater;
	private Context context;
	private List<Weather.Future> list;

	public WeatherDayAdapter(Context context) {
		this.context = context;
		layoutinflater = LayoutInflater.from(context);
	}

	public void setData(List<Weather.Future> list) {
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
		Weather.Future entity = (Weather.Future) list.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = layoutinflater.inflate(
					R.layout.view_weather_daylistview_item, parent, false);
			holder = new ViewHolder();
			holder.week_label = ((TextView) convertView
					.findViewById(R.id.week_label));
			holder.weather_label = ((TextView) convertView
					.findViewById(R.id.weather_label));
			holder.weather_icon = ((ImageView) convertView
					.findViewById(R.id.weather_icon));
			holder.tempup_label = ((TextView) convertView
					.findViewById(R.id.tempup_label));
			holder.tempdn_label = ((TextView) convertView
					.findViewById(R.id.tempdn_label));
			holder.tempdn_label.setTextColor(Color.rgb(159, 227, 254));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.week_label.setText(entity.getWeek());
		holder.weather_label.setText(entity.getWeather());
		String str = WeatherTools.getWeatherIcon(entity.getWeather());
		holder.weather_icon.setImageBitmap(HelprCommUtil.getImage(str));
		String[] temp = entity.getTemperature().split("~");
		holder.tempup_label.setText(temp[1]);
		holder.tempdn_label.setText(temp[0]);

		return convertView;

	}

	private class ViewHolder {
		public TextView week_label;
		public ImageView weather_icon;
		public TextView weather_label;
		public TextView tempup_label;
		public TextView tempdn_label;
	}


}
