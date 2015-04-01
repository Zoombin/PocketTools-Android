package com.juhe.pockettools.parking;

import android.os.Parcel;
import android.os.Parcelable;

public class ParkingEntrance implements Parcelable {

	private int FX;// 出入口方向 (1:北; 2:南; 3:西; 4:东)
	private int LX;// 出入口类型 (0:入口坐标; 1:出口坐标; 2:出入口坐标)
	private double lat;
	private double lon;

	public String getFX() {
		String s;
		switch (FX) {
		case 1:
			s = "北";
			break;
		case 2:
			s = "南";
			break;
		case 3:
			s = "西";
			break;
		case 4:
			s = "东";
			break;

		default:
			s = "";
			break;
		}
		return s;
	}

	public void setFX(int fX) {
		FX = fX;
	}

	public String getLXString() {
		String s = null;
		switch (LX) {
		case 0:
			s = "入口";
			break;
		case 1:
			s = "出口";
			break;
		case 2:
			s = "出入口";
			break;
		default:
			s = "";
			break;
		}
		return s;
	}

	public int getLX() {
		return LX;
	}

	public void setLX(int lX) {
		LX = lX;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public static Parcelable.Creator<ParkingEntrance> CREATOR = new Creator<ParkingEntrance>() {

		@Override
		public ParkingEntrance[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ParkingEntrance createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ParkingEntrance entrance = new ParkingEntrance();
			entrance.FX = source.readInt();
			entrance.LX = source.readInt();
			entrance.lat = source.readDouble();
			entrance.lon = source.readDouble();
			return entrance;
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(FX);
		dest.writeInt(LX);
		dest.writeDouble(lat);
		dest.writeDouble(lon);
	}

}
