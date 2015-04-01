package com.juhe.pockettools.ctrip;

import android.os.Parcel;
import android.os.Parcelable;

public class XiechengViewIntroduceBean implements Parcelable{
	
	private String work_time;//营业时间
	private String view_item;//景点特设
	private String nearby_activity;//近期活动
	private String view_introduce;//景点介绍
	
	public XiechengViewIntroduceBean(){}

	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}

	public String getView_item() {
		return view_item;
	}

	public void setView_item(String view_item) {
		this.view_item = view_item;
	}

	public String getNearby_activity() {
		return nearby_activity;
	}

	public void setNearby_activity(String nearby_activity) {
		this.nearby_activity = nearby_activity;
	}

	public String getView_introduce() {
		return view_introduce;
	}

	public void setView_introduce(String view_introduce) {
		this.view_introduce = view_introduce;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(work_time);
		out.writeString(view_item);
		out.writeString(nearby_activity);
		out.writeString(view_introduce);
	}
	
	public static final Parcelable.Creator<XiechengViewIntroduceBean> CREATOR = new Creator<XiechengViewIntroduceBean>() {

		@Override
		public XiechengViewIntroduceBean createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new XiechengViewIntroduceBean(in);
		}

		@Override
		public XiechengViewIntroduceBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new XiechengViewIntroduceBean[size];
		}
	};
	
	public XiechengViewIntroduceBean(Parcel in){
		work_time = in.readString();
		view_item = in.readString();
		nearby_activity = in.readString();
		view_introduce = in.readString();
	}

}
