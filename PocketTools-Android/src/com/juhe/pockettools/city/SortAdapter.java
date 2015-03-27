package com.juhe.pockettools.city;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.juhe.pockettools.R;

public class SortAdapter extends BaseAdapter implements SectionIndexer {

	private List<SortModel> list = null;
	private Context context;

	public SortAdapter(Context context, List<SortModel> list) {
		this.context = context;
		this.list = list;
	}

	public void updateListView(List<SortModel> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的char ascii值
	 */
	@Override
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SortModel entity = list.get(position);
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.view_city_listview_item, null);
			holder.title = ((TextView) convertView.findViewById(R.id.title));
			holder.catalog = ((TextView) convertView.findViewById(R.id.catalog));

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 根据position获取分类的首字母的char ascii值
		int section = getSectionForPosition(position);
		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			holder.catalog.setVisibility(View.VISIBLE);
			holder.catalog.setText(entity.getSortLetters());
		} else {
			holder.catalog.setVisibility(View.GONE);
		}

		holder.title.setText(entity.getCityName());

		return convertView;
	}

	public class ViewHolder {
		TextView catalog;
		TextView title;
	}
}
