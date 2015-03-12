package com.juhe.pockettools.train;

import java.io.Serializable;
import java.util.List;

public class TrainS2SEntity implements Serializable {

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
		private List<Data> data;
		private int totalcount;

		public List<Data> getData() {
			return data;
		}

		public void setData(List<Data> data) {
			this.data = data;
		}

		public int getTotalcount() {
			return totalcount;
		}

		public void setTotalcount(int totalcount) {
			this.totalcount = totalcount;
		}

	}

	public class Data {
		// "trainOpp": "G104",
		// "train_typename": "高铁",
		// "start_staion": "上海虹桥",
		// "end_station": "苏州北",
		// "leave_time": "07:10",
		// "arrived_time": "07:33",
		// "mileage": "81"
		private String trainOpp;
		private String train_typename;
		private String start_staion;
		private String end_station;
		private String leave_time;
		private String arrived_time;
		private String mileage;

		public String getTrainOpp() {
			return trainOpp;
		}

		public void setTrainOpp(String trainOpp) {
			this.trainOpp = trainOpp;
		}

		public String getTrain_typename() {
			return train_typename;
		}

		public void setTrain_typename(String train_typename) {
			this.train_typename = train_typename;
		}

		public String getStart_staion() {
			return start_staion;
		}

		public void setStart_staion(String start_staion) {
			this.start_staion = start_staion;
		}

		public String getEnd_station() {
			return end_station;
		}

		public void setEnd_station(String end_station) {
			this.end_station = end_station;
		}

		public String getLeave_time() {
			return leave_time;
		}

		public void setLeave_time(String leave_time) {
			this.leave_time = leave_time;
		}

		public String getArrived_time() {
			return arrived_time;
		}

		public void setArrived_time(String arrived_time) {
			this.arrived_time = arrived_time;
		}

		public String getMileage() {
			return mileage;
		}

		public void setMileage(String mileage) {
			this.mileage = mileage;
		}

	}
}
