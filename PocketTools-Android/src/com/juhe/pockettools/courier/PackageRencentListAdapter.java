package com.juhe.pockettools.courier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import com.juhe.pockettools.R;

public class PackageRencentListAdapter extends BaseAdapter {
	public static String PACKAGE_COMPANY = "package_company";
	public static String PACKAGE_NUMBER = "package_number";
	public static String PACKAGE_DATE = "package_date";
	private ArrayList<HashMap<String, String>> list;
	private Context context;

	public PackageRencentListAdapter(Context context) {
		this.context = context;
		list = new ArrayList<HashMap<String, String>>();
	}

	public void setData(ArrayList<HashMap<String, String>> list) {
		this.list = list;
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
		HashMap<String, String> map = (HashMap<String, String>) list.get(-1 + list.size()
				- position);

		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_package_recently_list_item,
					null);
			
			holder = new ViewHolder();
			
			holder.txt_package_company = ((TextView) convertView.findViewById(R.id.txt_package_company));
			holder.txt_package_number = ((TextView) convertView.findViewById(R.id.txt_package_number));
			holder.txt_package_date = ((TextView) convertView.findViewById(R.id.txt_package_date));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		String package_company = (String) map.get(PACKAGE_COMPANY);
		String package_number = (String) map.get(PACKAGE_NUMBER);
		String package_date = (String) map.get(PACKAGE_DATE);
		holder.txt_package_company.setText(package_company);
		holder.txt_package_number.setText(package_number);
		holder.txt_package_date.setText(package_date);
		
		return convertView;
	}

	class ViewHolder {
		TextView txt_package_company;
		TextView txt_package_number;
		TextView txt_package_date;
	}
}
