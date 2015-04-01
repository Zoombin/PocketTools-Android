package com.juhe.pockettools.parking;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 停车预测
 * 
 * @author shuoli
 *
 */
public class ParkingYC implements Parcelable {

	private int KCWZT;// 预测状态 {"1": "充足","2": "够用","3": "较少","4": "紧张","5":
						// "未知", "6": "车位关闭"}
	private String YCSJ;// 预测时间

	public String getKCWZT() {
		String s;
		switch (KCWZT) {
		case 1:
			s = "充足";
			break;
		case 2:
			s = "够用";
			break;
		case 3:
			s = "较少";
			break;
		case 4:
			s = "紧张";
			break;
		case 5:
			s = "未知";
			break;
		case 6:
			s = "车位关闭";
			break;
		default:
			s = "";
			break;
		}
		return s;
	}

	public void setKCWZT(int kCWZT) {
		KCWZT = kCWZT;
	}

	public String getYCSJ() {
		return YCSJ;
	}

	public void setYCSJ(String yCSJ) {
		YCSJ = yCSJ;
	}

	public static Parcelable.Creator<ParkingYC> CREATOR = new Parcelable.Creator<ParkingYC>() {

		@Override
		public ParkingYC createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ParkingYC parkingYC = new ParkingYC();
			parkingYC.KCWZT = source.readInt();
			parkingYC.YCSJ = source.readString();
			return parkingYC;
		}

		@Override
		public ParkingYC[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
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
		dest.writeInt(KCWZT);
		dest.writeString(YCSJ);
	}

}
