package com.juhe.pockettools.ctrip;


import android.os.Parcel;
import android.os.Parcelable;

public class XiechengViewlistBean implements Parcelable{

	private String DistrictName;//景点所在城市名称
	private String view_name;//景点名称
	private String star;//星级
	private String point;//评分
	private String market_price;//市场价
	private String price;//优惠价
	private String picture;//图片
	private String id;//景点编号
	private String address;//景点地址
	
	public XiechengViewlistBean(){
	}
	
	public String getDistrictName() {
		return DistrictName;
	}
	public void setDistrictName(String districtName) {
		DistrictName = districtName;
	}
	public String getView_name() {
		return view_name;
	}
	public void setView_name(String view_name) {
		this.view_name = view_name;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(DistrictName);
		out.writeString(view_name);
		out.writeString(star);
		out.writeString(point);
		out.writeString(market_price);
		out.writeString(price);
		out.writeString(picture);
		out.writeString(id);
		out.writeString(address);
	}
	
	public static final Parcelable.Creator<XiechengViewlistBean> CREATOR = new Creator<XiechengViewlistBean>() {

		@Override
		public XiechengViewlistBean createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new XiechengViewlistBean(in);
		}

		@Override
		public XiechengViewlistBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new XiechengViewlistBean[size];
		}
	};

	public XiechengViewlistBean(Parcel in) {
		DistrictName = in.readString();
		view_name = in.readString();
		star = in.readString();
		point = in.readString();
		market_price = in.readString();
		price = in.readString();
		picture = in.readString();
		id = in.readString();
		address = in.readString();
	}
	
}
