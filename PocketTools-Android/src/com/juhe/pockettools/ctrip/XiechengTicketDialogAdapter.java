package com.juhe.pockettools.ctrip;

import java.util.ArrayList;



import com.juhe.pockettools.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class XiechengTicketDialogAdapter extends BaseAdapter{
	Context mContext;
	private ArrayList<XiechengTicketDialogBean> list;
	private LayoutInflater mInflater;
	
	public XiechengTicketDialogAdapter(Context mContext,ArrayList<XiechengTicketDialogBean> list) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.list = list;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
			convertView = mInflater.inflate(R.layout.item_dialog_ticketinfo, null);

			holder.dialog_title = (TextView) convertView.findViewById(R.id.dialog_title);
			holder.dialog_text = (TextView) convertView.findViewById(R.id.dialog_text);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.dialog_title.setText(list.get(position).getText().toString());
		holder.dialog_text.setText(list.get(position).getTitle().toString());
		return convertView;
	}
	
	private class ViewHolder{
		private TextView dialog_title;
		private TextView dialog_text;
	}

}
