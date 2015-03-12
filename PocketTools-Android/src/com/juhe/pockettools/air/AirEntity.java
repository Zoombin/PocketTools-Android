package com.juhe.pockettools.air;

import java.io.Serializable;
import java.util.List;

import com.juhe.pockettools.train.TrainS2SEntity.Result;

public class AirEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private List<Result> result;

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

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public class Result {
		// "FlightNum": "DZ6223",
		// "AirlineCode": "DZ",
		// "Airline": "东海航空",
		// "DepCity": "北京",
		// "ArrCity": "武汉",
		// "DepCode": "PEK",
		// "ArrCode": "WUH",
		// "OnTimeRate": "90.25%",
		// "DepTerminal": "null",
		// "ArrTerminal": "null",
		// "FlightDate": "2015-01-15",
		// "PEKDate": "2015-01-15",
		// "DepTime": "2015-01-15 00:25",
		// "ArrTime": "2015-01-15 04:20",
		// "Dexpected": "null",
		// "Aexpected": "null"
		private String FlightNum;
		private String AirlineCode;
		private String Airline;
		private String DepCity;
		private String ArrCity;
		private String OnTimeRate;
		private String DepTerminal;
		private String ArrTerminal;
		private String FlightDate;
		private String PEKDate;
		private String DepTime;
		private String ArrTime;
		private String Dexpected;
		private String Aexpected;

		public String getFlightNum() {
			return FlightNum;
		}

		public void setFlightNum(String flightNum) {
			FlightNum = flightNum;
		}

		public String getAirlineCode() {
			return AirlineCode;
		}

		public void setAirlineCode(String airlineCode) {
			AirlineCode = airlineCode;
		}

		public String getAirline() {
			return Airline;
		}

		public void setAirline(String airline) {
			Airline = airline;
		}

		public String getDepCity() {
			return DepCity;
		}

		public void setDepCity(String depCity) {
			DepCity = depCity;
		}

		public String getArrCity() {
			return ArrCity;
		}

		public void setArrCity(String arrCity) {
			ArrCity = arrCity;
		}

		public String getOnTimeRate() {
			return OnTimeRate;
		}

		public void setOnTimeRate(String onTimeRate) {
			OnTimeRate = onTimeRate;
		}

		public String getDepTerminal() {
			return DepTerminal;
		}

		public void setDepTerminal(String depTerminal) {
			DepTerminal = depTerminal;
		}

		public String getArrTerminal() {
			return ArrTerminal;
		}

		public void setArrTerminal(String arrTerminal) {
			ArrTerminal = arrTerminal;
		}

		public String getFlightDate() {
			return FlightDate;
		}

		public void setFlightDate(String flightDate) {
			FlightDate = flightDate;
		}

		public String getPEKDate() {
			return PEKDate;
		}

		public void setPEKDate(String pEKDate) {
			PEKDate = pEKDate;
		}

		public String getDepTime() {
			return DepTime;
		}

		public void setDepTime(String depTime) {
			DepTime = depTime;
		}

		public String getArrTime() {
			return ArrTime;
		}

		public void setArrTime(String arrTime) {
			ArrTime = arrTime;
		}

		public String getDexpected() {
			return Dexpected;
		}

		public void setDexpected(String dexpected) {
			Dexpected = dexpected;
		}

		public String getAexpected() {
			return Aexpected;
		}

		public void setAexpected(String aexpected) {
			Aexpected = aexpected;
		}

	}

}
