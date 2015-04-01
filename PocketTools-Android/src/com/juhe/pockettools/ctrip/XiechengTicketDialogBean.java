package com.juhe.pockettools.ctrip;

import android.os.Parcel;
import android.os.Parcelable;

public class XiechengTicketDialogBean implements Parcelable{
	private String title;
	private String text;
	
	public XiechengTicketDialogBean(){
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(text);
		out.writeString(title);
		
	}
	
	public static final Parcelable.Creator<XiechengTicketDialogBean> CREATOR = new Creator<XiechengTicketDialogBean>() {

		@Override
		public XiechengTicketDialogBean createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new XiechengTicketDialogBean(in);
		}

		@Override
		public XiechengTicketDialogBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new XiechengTicketDialogBean[size];
		}
	};
	
	public XiechengTicketDialogBean(Parcel in){
		title = in.readString();
		text = in.readString();
	}

}
