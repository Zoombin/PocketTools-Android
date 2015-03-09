package com.juhe.pockettools.qrcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import com.juhe.pockettools.R;

public class QRCodeGoodsPriceListAdapter extends BaseAdapter {
	// public static String a = "goods_place";
	// public static String b = "goods_price";
	private ArrayList<HashMap<String, String>> list = new ArrayList();
	private Context context;

	public QRCodeGoodsPriceListAdapter(Context context) {
		this.context = context;
	}

	public void setData(ArrayList<HashMap<String, String>> list) {
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
		if (list.size() <= 0) {
			return convertView;
		}
		HashMap localHashMap = (HashMap) list.get(position);
		// String str1 = (String) localHashMap.get(a);
		// String str2 = (String) localHashMap.get(b);
		ViewHolder holder;

		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.view_qrcode_goods_price_list_item, null);
			holder = new ViewHolder();
			holder.list_right = ((TextView) convertView.findViewById(R.id.list_right));
			holder.list_left = ((TextView) convertView.findViewById(R.id.list_left));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

//		holder.list_left.setText(str1);
//		holder.list_right.setText(str2);
		return convertView;
	}

	private class ViewHolder {
		TextView list_right;
		TextView list_left;
	}
}
