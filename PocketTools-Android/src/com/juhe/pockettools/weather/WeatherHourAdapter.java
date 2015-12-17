package com.juhe.pockettools.weather;

import com.zoombin.koudai.R;
import com.juhe.pockettools.home.HelprActivity;
import com.juhe.pockettools.utils.HelprCommUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherHourAdapter extends ArrayAdapter<WeatherHourInfo> {
	private boolean ishome = false;
	private Context context;
	
	private LayoutInflater layoutinflater = (LayoutInflater) getContext()
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	public WeatherHourAdapter(Context context, WeatherHourInfo[] infos) {
		super(context, R.layout.view_weather_hour_item, infos);
		this.context = context;
	}
	
	public WeatherHourAdapter(Context context, WeatherHourInfo[] infos, boolean ishome) {
		super(context, R.layout.view_weather_hour_item, infos);
		this.context = context;
		this.ishome = ishome;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutinflater.inflate(
					R.layout.view_weather_hour_item, parent, false);
			holder = new ViewHolder();
			holder.ly_item_view = (FrameLayout) convertView.findViewById(R.id.ly_item_view);
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

		holder.ly_item_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (ishome) {
					Intent intent = new Intent(context, WeatherMainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
			}
		});
		holder.temp_text.setText(getItem(position).temp);
		holder.hour_text.setText(getItem(position).hour);
		String str = WeatherTools.getWeatherIcon(getItem(position).weather);
		holder.item_icon.setImageBitmap(HelprCommUtil.getImage(str));
		return convertView;
	}

	private class ViewHolder {
		private FrameLayout ly_item_view;
		public TextView hour_text;
		public ImageView item_icon;
		public TextView temp_text;
	}
}