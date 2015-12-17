package com.juhe.pockettools.ctrip;

import java.util.ArrayList;



import com.zoombin.koudai.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class XiechengTicketlistAdapter extends BaseAdapter {
	Context mContext;
	private ArrayList<XiechengViewDetailBean> alist;
	private LayoutInflater mInflater;

	public XiechengTicketlistAdapter(Context mContext,
			ArrayList<XiechengViewDetailBean> aList) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.alist = aList;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return alist.get(position);
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
			convertView = mInflater.inflate(R.layout.item_view_tickets, null);

			holder.ticket_kind = (TextView) convertView.findViewById(R.id.ticket_kind);
			holder.market_price = (TextView) convertView.findViewById(R.id.market_price_3);
			holder.price = (TextView) convertView.findViewById(R.id.price_3);
			holder.time = (TextView) convertView.findViewById(R.id.ticketlistview_time);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ticket_kind.setText(alist.get(position).getTicket_kind().toString());
		holder.market_price.setText(alist.get(position).getMarket_price().toString());
		holder.price.setText(alist.get(position).getPrice().toString());
		holder.time.setText(alist.get(position).getAdvanceBookingTime().toString());
		return convertView;
	}

	private class ViewHolder {
		private TextView ticket_kind;
		private TextView market_price;
		private TextView price;
		private TextView time;
	}

}
