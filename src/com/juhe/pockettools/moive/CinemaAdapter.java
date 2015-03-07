package com.juhe.pockettools.moive;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juhe.pockettools.R;

class CinemaAdapter extends BaseAdapter {
	Context context;
	List<CinemaEntity.Result> list;

	public CinemaAdapter(Context context) {
		this.context = context;
		this.list = new ArrayList<CinemaEntity.Result>();
	}

	public void setData(List<CinemaEntity.Result> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
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
		if (list.size() > 0) {
			final CinemaEntity.Result entity = list.get(position);
			ViewHolder holder;
			
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.view_movie_cinema_cell, parent, false);
				
				holder = new ViewHolder();
				
				holder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
				holder.layout_item = (LinearLayout) convertView.findViewById(R.id.layout_item);
						
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.txt_title.setText("第" + (position + 1) + "名   " + entity.getName());
			holder.layout_item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, MovieDetailActivity.class);
					intent.putExtra("cinemaentity", entity);
					context.startActivity(intent);
				}
			});
		}
		return convertView;
	}

	public class ViewHolder {
		TextView txt_title;
		LinearLayout layout_item;
	}
}
