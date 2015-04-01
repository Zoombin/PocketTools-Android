package com.juhe.pockettools.parking;

import java.util.List;

import com.juhe.pockettools.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkingLotListAdapter extends BaseAdapter {

	private List<ParkingLot> list;
	private LayoutInflater mInflater;

	public ParkingLotListAdapter(Context context, List<ParkingLot> list) {
		// TODO Auto-generated constructor stub

		this.list = list;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ParkingLot getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.item_listview_parkinglot,
					null);
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_image);

			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvAddr = (TextView) convertView.findViewById(R.id.tv_addr);
			holder.tvCW = (TextView) convertView.findViewById(R.id.tv_cw);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageLoader.getInstance().displayImage(getItem(position).getCCTP(),
				holder.iv);
		holder.tvName.setText(getItem(position).getCCMC());
		holder.tvAddr.setText(getItem(position).getCCDZ());
		int kcw = getItem(position).getKCW();
		if(kcw>0){
			holder.tvCW.setText(getItem(position).getKCW()+"/"+getItem(position).getZCW());
		}else{
			holder.tvCW.setText("已满");
		}
		
		return convertView;
	}

	private class ViewHolder {
		ImageView iv;
		TextView tvName, tvAddr, tvCW;
	}

}
