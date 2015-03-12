package com.juhe.pockettools.qrcode;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juhe.pockettools.R;

public class QRCodeGoodsPriceListAdapter extends BaseAdapter {
	private List<QRCodeEntity.Shop> list = new ArrayList<QRCodeEntity.Shop>();
	private Context context;

	public QRCodeGoodsPriceListAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<QRCodeEntity.Shop> list) {
		if (list == null) {
			return;
		}
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
		QRCodeEntity.Shop entity = (QRCodeEntity.Shop) list.get(position);
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

		holder.list_left.setText(entity.getShopname());
		holder.list_right.setText(entity.getPrice());
		return convertView;
	}

	private class ViewHolder {
		TextView list_right;
		TextView list_left;
	}
}
