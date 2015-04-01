package com.juhe.pockettools.parking;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ParkingLot implements Parcelable {

	private int CCID;
	private String CCMC;// 名称
	private String CCDZ;// 地址
	private int KCW;// 空车位
	private int ZCW;// 总车位
	private String CCTP;// 图片
	private String BTTCJG;// 白天停车价格
	private String WSTCJG;// 晚上停车价格
	private String YYKSSJ;// 营业开始时间
	private String YYJSSJ;// 营业结束时间

	private int CCFL;// 停车场分类 { "占道停车场": "1", "路外露天停车场": "2", "非露天地上停车场": "3",
						// "非露天地下停车场": "4",}
	private int CCLX;// 停车场类型 { "平面自走式": "1", "机械式": "2", "立体自动车库": "3",}
	// private String TP;//出入口图片
	private int SFKF;// 是否开放 (0：不开放；1：开放；)
	private String JCSFA;// 计次收费大型车
	private String JCSFB;// 计次收费小型车

	private double lat;
	private double lon;

	private ArrayList<ParkingEntrance> entranceList;// 出入口
	private ArrayList<ParkingYC> ycList;// 预测

	private String QYCS;// 区域城市

	public int getCCID() {
		return CCID;
	}

	public void setCCID(int cCID) {
		CCID = cCID;
	}

	public String getCCMC() {
		return CCMC;
	}

	public void setCCMC(String cCMC) {
		CCMC = cCMC;
	}

	public String getCCDZ() {
		return CCDZ;
	}

	public void setCCDZ(String cCDZ) {
		CCDZ = cCDZ;
	}

	public String getCCTP() {
		return CCTP;
	}

	public void setCCTP(String cCTP) {
		CCTP = cCTP;
	}

	public String getBTTCJG() {
		return BTTCJG;
	}

	public void setBTTCJG(String bTTCJG) {
		BTTCJG = bTTCJG;
	}

	public String getWSTCJG() {
		return WSTCJG;
	}

	public void setWSTCJG(String wSTCJG) {
		WSTCJG = wSTCJG;
	}

	public String getYYKSSJ() {
		return YYKSSJ;
	}

	public void setYYKSSJ(String yYKSSJ) {
		YYKSSJ = yYKSSJ;
	}

	public String getYYJSSJ() {
		return YYJSSJ;
	}

	public void setYYJSSJ(String yYJSSJ) {
		YYJSSJ = yYJSSJ;
	}

	public String getCCFL() {
		String s;
		switch (CCFL) {
		case 1:
			s = "占道停车场";
			break;
		case 2:
			s = "路外露天停车场";
			break;
		case 3:
			s = "非露天地上停车场";
			break;
		case 4:
			s = "非露天地下停车场";
			break;

		default:
			s = "";
			break;
		}
		return s;
	}

	public void setCCFL(int cCFL) {
		CCFL = cCFL;
	}

	public String getCCLX() {
		String s;
		switch (CCLX) {
		case 1:
			s = "平面自走式";
			break;
		case 2:
			s = "机械式";
			break;
		case 3:
			s = "立体自动车库";
			break;

		default:
			s = "";
			break;
		}
		return s;
	}

	public void setCCLX(int cCLX) {
		CCLX = cCLX;
	}

	public int getSFKF() {
		return SFKF;
	}

	public void setSFKF(int sFKF) {
		SFKF = sFKF;
	}

	public String getJCSFA() {
		return JCSFA;
	}

	public void setJCSFA(String jCSFA) {
		JCSFA = jCSFA;
	}

	public String getJCSFB() {
		return JCSFB;
	}

	public void setJCSFB(String jCSFB) {
		JCSFB = jCSFB;
	}

	public int getKCW() {
		return KCW;
	}

	public void setKCW(int kCW) {
		KCW = kCW;
	}

	public int getZCW() {
		return ZCW;
	}

	public void setZCW(int zCW) {
		ZCW = zCW;
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

	public String getQYCS() {
		return QYCS;
	}

	public void setQYCS(String qYCS) {
		QYCS = qYCS;
	}

	public ArrayList<ParkingEntrance> getEntranceList() {
		return entranceList;
	}

	public void setEntranceList(ArrayList<ParkingEntrance> entranceList) {
		this.entranceList = entranceList;
	}

	public ArrayList<ParkingYC> getYcList() {
		return ycList;
	}

	public void setYcList(ArrayList<ParkingYC> ycList) {
		this.ycList = ycList;
	}

	public static final Parcelable.Creator<ParkingLot> CREATOR = new Parcelable.Creator<ParkingLot>() {

		@SuppressWarnings("unchecked")
		@Override
		public ParkingLot createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ParkingLot parkingLot = new ParkingLot();
			parkingLot.CCID = source.readInt();
			parkingLot.CCMC = source.readString();
			parkingLot.CCDZ = source.readString();
			parkingLot.KCW = source.readInt();
			parkingLot.ZCW = source.readInt();
			parkingLot.CCTP = source.readString();
			parkingLot.BTTCJG = source.readString();
			parkingLot.WSTCJG = source.readString();
			parkingLot.YYKSSJ = source.readString();
			parkingLot.YYJSSJ = source.readString();
			parkingLot.CCFL = source.readInt();
			parkingLot.CCLX = source.readInt();
			parkingLot.SFKF = source.readInt();
			parkingLot.JCSFA = source.readString();
			parkingLot.JCSFB = source.readString();
			parkingLot.lat = source.readDouble();
			parkingLot.lon = source.readDouble();
			parkingLot.entranceList = source
					.readArrayList(ParkingEntrance.class.getClassLoader());
			parkingLot.ycList = source.readArrayList(ParkingYC.class
					.getClassLoader());
			parkingLot.QYCS = source.readString();
			return parkingLot;
		}

		@Override
		public ParkingLot[] newArray(int size) {
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
		dest.writeInt(CCID);
		dest.writeString(CCMC);
		dest.writeString(CCDZ);
		dest.writeInt(KCW);
		dest.writeInt(ZCW);
		dest.writeString(CCTP);
		dest.writeString(BTTCJG);
		dest.writeString(WSTCJG);
		dest.writeString(YYKSSJ);
		dest.writeString(YYJSSJ);
		dest.writeInt(CCFL);
		dest.writeInt(CCLX);
		dest.writeInt(SFKF);
		dest.writeString(JCSFA);
		dest.writeString(JCSFB);
		dest.writeDouble(lat);
		dest.writeDouble(lon);
		dest.writeList(entranceList);
		dest.writeList(ycList);
		dest.writeString(QYCS);
	}

}
