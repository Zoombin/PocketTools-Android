package com.juhe.pockettools.ctrip;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zoombin.koudai.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class XiechengViewlistAdapter extends BaseAdapter {
	Context context;
	private ArrayList<XiechengViewlistBean> viewlist;
	private LayoutInflater mInflater;
	ImageLoader imageLoader;

	public XiechengViewlistAdapter(Context context,
			ArrayList<XiechengViewlistBean> viewlist) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.viewlist = viewlist;
		System.out.println(viewlist.size());
		mInflater = LayoutInflater.from(context);
		// mInflater = (LayoutInflater) context
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewlist.size();
	}

	@Override
	public XiechengViewlistBean getItem(int position) {
		// TODO Auto-generated method stub
		return viewlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = mInflater.inflate(R.layout.item_viewlist, null);

			holder.photo = (ImageView) convertView
					.findViewById(R.id.view_image_1);
			holder.view_name = (TextView) convertView
					.findViewById(R.id.view_name_1);
			holder.view_star = (TextView) convertView
					.findViewById(R.id.view_star_1);
			holder.view_point = (TextView) convertView
					.findViewById(R.id.view_point_1);
			holder.market_price = (TextView) convertView
					.findViewById(R.id.view_market_price_1);
			holder.price = (TextView) convertView
					.findViewById(R.id.view_price_1);
			holder.item_viewlist_star = (TextView) convertView
					.findViewById(R.id.textView2);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		imageLoader.displayImage(viewlist.get(position).getPicture(),
				holder.photo);
		holder.view_name.setText(viewlist.get(position).getView_name());
		holder.view_star.setText(viewlist.get(position).getStar());
		holder.view_point.setText(viewlist.get(position).getPoint());
		holder.market_price.setText(viewlist.get(position).getMarket_price());
		holder.price.setText(viewlist.get(position).getPrice());

		return convertView;

	}

	private class ViewHolder {
		private ImageView photo;
		private TextView view_name;
		private TextView view_star;
		private TextView view_point;
		private TextView market_price;
		private TextView price;
		private TextView item_viewlist_star;
	}

}
