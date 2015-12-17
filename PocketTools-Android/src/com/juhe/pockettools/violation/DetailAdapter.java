package com.juhe.pockettools.violation;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zoombin.koudai.R;
import com.juhe.pockettools.violation.ViolationDetailEntity.Info;

class DetailAdapter extends BaseAdapter {
	Context context;
	List<Info> list;

	public DetailAdapter(Context context) {
		this.context = context;
		this.list = new ArrayList<Info>();
	}

	public void setData(List<Info> list) {
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
			final Info entity = list.get(position);
			ViewHolder holder;
			
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.view_violation_detail_item, parent, false);
				
				holder = new ViewHolder();
				
				holder.detail_action = (TextView) convertView.findViewById(R.id.detail_action);
				holder.detail_area = (TextView) convertView.findViewById(R.id.detail_area);
				holder.detail_time = (TextView) convertView.findViewById(R.id.detail_time);
				holder.detail_money = (TextView) convertView.findViewById(R.id.detail_money);
				holder.detail_code = (TextView) convertView.findViewById(R.id.detail_code);
				holder.detail_handle = (TextView) convertView.findViewById(R.id.detail_handle);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.detail_action.setText(entity.getAct());
			holder.detail_area.setText(entity.getArea());
			holder.detail_time.setText(entity.getDate());
			holder.detail_money.setText(entity.getMoney());
			holder.detail_code.setText(entity.getCode());
			if (entity.getHandled().equals("1")) {
				holder.detail_handle.setText("处理");
			} else {
				holder.detail_handle.setText("未处理");
			}
		}
		return convertView;
	}

	public class ViewHolder {
		TextView detail_action;
		TextView detail_area;
		TextView detail_time;
		TextView detail_money;
		TextView detail_code;
		TextView detail_handle;
	}
}
