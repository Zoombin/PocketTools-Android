package com.juhe.pockettools.weather;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.HelprCommUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherHourAdapter extends ArrayAdapter<WeatherHourInfo> {
	private LayoutInflater layoutinflater = (LayoutInflater) getContext()
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	public WeatherHourAdapter(Context context, WeatherHourInfo[] infos) {
		super(context, R.layout.view_weather_hour_item, infos);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutinflater.inflate(
					R.layout.view_weather_hour_item, parent, false);
			holder = new ViewHolder();
			holder.hour_text = ((TextView) convertView
					.findViewById(R.id.hour_text));
			holder.temp_text = ((TextView) convertView
					.findViewById(R.id.temp_text));
			holder.item_icon = ((ImageView) convertView
					.findViewById(R.id.item_icon));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.temp_text.setText(getItem(position).temp);
		holder.hour_text.setText(getItem(position).hour);
		String str = WeatherTools.getWeatherIcon(getItem(position).weather);
		holder.item_icon.setImageBitmap(HelprCommUtil.getImage(str));
		return convertView;
	}

	private class ViewHolder {
		public TextView hour_text;
		public ImageView item_icon;
		public TextView temp_text;
	}
}