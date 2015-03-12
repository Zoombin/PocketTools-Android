package com.juhe.pockettools.applesn;

import java.io.Serializable;

public class AppleSnEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private Result result;

	public class Result {
		private String phone_model;
		private String serial_number;
		private String imei_number;
		private String active;
		private String warranty_status;
		private String warranty;
		private String tele_support;
		private String tele_support_status;
		private String made_area;
		private String start_date;
		private String end_date;
		private String color;
		private String size;
		public String getPhone_model() {
			return phone_model;
		}
		public void setPhone_model(String phone_model) {
			this.phone_model = phone_model;
		}
		public String getSerial_number() {
			return serial_number;
		}
		public void setSerial_number(String serial_number) {
			this.serial_number = serial_number;
		}
		public String getImei_number() {
			return imei_number;
		}
		public void setImei_number(String imei_number) {
			this.imei_number = imei_number;
		}
		public String getActive() {
			return active;
		}
		public void setActive(String active) {
			this.active = active;
		}
		public String getWarranty_status() {
			return warranty_status;
		}
		public void setWarranty_status(String warranty_status) {
			this.warranty_status = warranty_status;
		}
		public String getWarranty() {
			return warranty;
		}
		public void setWarranty(String warranty) {
			this.warranty = warranty;
		}
		public String getTele_support() {
			return tele_support;
		}
		public void setTele_support(String tele_support) {
			this.tele_support = tele_support;
		}
		public String getTele_support_status() {
			return tele_support_status;
		}
		public void setTele_support_status(String tele_support_status) {
			this.tele_support_status = tele_support_status;
		}
		public String getMade_area() {
			return made_area;
		}
		public void setMade_area(String made_area) {
			this.made_area = made_area;
		}
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
	}

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
}
