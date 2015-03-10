package com.juhe.pockettools.train;

import java.io.Serializable;
import java.util.List;

public class TrainSEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private Result result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {
		private TrainInfo train_info;
		private List<Station> station_list;

		public TrainInfo getTrain_info() {
			return train_info;
		}

		public void setTrain_info(TrainInfo train_info) {
			this.train_info = train_info;
		}

		public List<Station> getStation_list() {
			return station_list;
		}

		public void setStation_list(List<Station> station_list) {
			this.station_list = station_list;
		}

	}

	public class TrainInfo {
		private String name;
		private String start;
		private String end;
		private String starttime;
		private String endtime;
		private String mileage;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public String getEnd() {
			return end;
		}

		public void setEnd(String end) {
			this.end = end;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public String getEndtime() {
			return endtime;
		}

		public void setEndtime(String endtime) {
			this.endtime = endtime;
		}

		public String getMileage() {
			return mileage;
		}

		public void setMileage(String mileage) {
			this.mileage = mileage;
		}

	}

	public class Station {
//		{"resultcode":"200","reason":"Successed!",
//			"result":{"train_info":{"name":"G102","start":"上海虹桥","end":"北京南","starttime":"07:00","endtime":"12:23","mileage":"1318km"},
//			"station_list":[{"train_id":"1","station_name":"上海虹桥","arrived_time":"-","leave_time":"07:00","mileage":"-","fsoftSeat":"-","ssoftSeat":"-","hardSead":"-",
//				"softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"-","swz":"-","tdz":"-","gjrw":"-","stay":"-"},
//				{"train_id":"2","station_name":"常州北","arrived_time":"07:40","leave_time":"07:42","mileage":"165km","fsoftSeat":"129.5","ssoftSeat":"74.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"74.5","swz":"239.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"3","station_name":"南京南","arrived_time":"08:14","leave_time":"08:16","mileage":"295km","fsoftSeat":"229.5","ssoftSeat":"134.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"134.5","swz":"429.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"4","station_name":"滁州","arrived_time":"08:34","leave_time":"08:36","mileage":"354km","fsoftSeat":"274.5","ssoftSeat":"164.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"164.5","swz":"514.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"5","station_name":"枣庄","arrived_time":"09:50","leave_time":"09:52","mileage":"691km","fsoftSeat":"519.0","ssoftSeat":"309.0","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"309.0","swz":"974.0","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"6","station_name":"济南西","arrived_time":"10:42","leave_time":"10:44","mileage":"912km","fsoftSeat":"673.5","ssoftSeat":"398.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"398.5","swz":"1263.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"7","station_name":"德州东","arrived_time":"11:08","leave_time":"11:10","mileage":"1004km","fsoftSeat":"738.5","ssoftSeat":"438.5","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"438.5","swz":"1383.5","tdz":"-","gjrw":"-","stay":"2"},{"train_id":"8","station_name":"北京南","arrived_time":"12:23","leave_time":"12:23","mileage":"1318km","fsoftSeat":"933.0","ssoftSeat":"553.0","hardSead":"-","softSeat":"-","hardSleep":"-","softSleep":"-","wuzuo":"553.0","swz":"1748.0","tdz":"-","gjrw":"-","stay":"-"}]},"error_code":0}
		
		private String train_id;
		private String station_name;
		private String arrived_time;
		private String leave_time;
		private String mileage;
		private String fsoftSeat;
		private String ssoftSeat;
		private String hardSead;
		private String softSeat;
		private String hardSleep;
		private String softSleep;
		private String wuzuo;
		private String swz;
		private String tdz;
		private String gjrw;
		private String stay;

		public String getSoftSleep() {
			return softSleep;
		}

		public void setSoftSleep(String softSleep) {
			this.softSleep = softSleep;
		}

		public String getTrain_id() {
			return train_id;
		}

		public void setTrain_id(String train_id) {
			this.train_id = train_id;
		}

		public String getStation_name() {
			return station_name;
		}

		public void setStation_name(String station_name) {
			this.station_name = station_name;
		}

		public String getArrived_time() {
			return arrived_time;
		}

		public void setArrived_time(String arrived_time) {
			this.arrived_time = arrived_time;
		}

		public String getLeave_time() {
			return leave_time;
		}

		public void setLeave_time(String leave_time) {
			this.leave_time = leave_time;
		}

		public String getMileage() {
			return mileage;
		}

		public void setMileage(String mileage) {
			this.mileage = mileage;
		}

		public String getFsoftSeat() {
			return fsoftSeat;
		}

		public void setFsoftSeat(String fsoftSeat) {
			this.fsoftSeat = fsoftSeat;
		}

		public String getSsoftSeat() {
			return ssoftSeat;
		}

		public void setSsoftSeat(String ssoftSeat) {
			this.ssoftSeat = ssoftSeat;
		}

		public String getHardSead() {
			return hardSead;
		}

		public void setHardSead(String hardSead) {
			this.hardSead = hardSead;
		}

		public String getSoftSeat() {
			return softSeat;
		}

		public void setSoftSeat(String softSeat) {
			this.softSeat = softSeat;
		}

		public String getHardSleep() {
			return hardSleep;
		}

		public void setHardSleep(String hardSleep) {
			this.hardSleep = hardSleep;
		}

		public String getWuzuo() {
			return wuzuo;
		}

		public void setWuzuo(String wuzuo) {
			this.wuzuo = wuzuo;
		}

		public String getSwz() {
			return swz;
		}

		public void setSwz(String swz) {
			this.swz = swz;
		}

		public String getTdz() {
			return tdz;
		}

		public void setTdz(String tdz) {
			this.tdz = tdz;
		}

		public String getGjrw() {
			return gjrw;
		}

		public void setGjrw(String gjrw) {
			this.gjrw = gjrw;
		}

		public String getStay() {
			return stay;
		}

		public void setStay(String stay) {
			this.stay = stay;
		}

	}
}
