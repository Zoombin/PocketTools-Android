package com.juhe.pockettools.courier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zoombin.koudai.R;

class PackageInfoAdapter extends BaseAdapter {
	private List<PackageEntity.PEntity> list;
	private Context context;

	public PackageInfoAdapter(Context context) {
		this.context = context;
		list = new ArrayList<PackageEntity.PEntity>();
	}

	public void setData(PackageEntity.Result result) {
		this.list = result.getList();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public Object getItem(int position) {
		if (list.size() == 0) {
			return null;
		}
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final PackageEntity.PEntity entity =list.get(position);
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_package_info_list_item,
					null);
			
			holder = new ViewHolder();
			
			holder.item1 = ((LinearLayout) convertView.findViewById(R.id.item1));
			holder.item2 = ((TextView) convertView.findViewById(R.id.item2));
			holder.item1_content = ((TextView) convertView.findViewById(R.id.item1_content));
			holder.item1_time = ((Button) convertView.findViewById(R.id.item1_time));

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		String[] str = entity.getDatetime().split(" ");
		holder.item2.setText(str[0]);
		holder.item1_content.setText(entity.getRemark());
		holder.item1_time.setText(str[1]);
//		holder.item1.setText(text);

		return convertView;
	}

	class ViewHolder {
		TextView item2;
		TextView item1_content;
		Button item1_time;
		LinearLayout item1;
	}
}
