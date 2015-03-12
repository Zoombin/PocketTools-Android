package com.juhe.pockettools.violation;

import java.io.Serializable;
import java.util.List;

public class ViolationDetailEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int resultcode;

	//
	private int error_code;

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	// 返回结果集
	private Result result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getResultcode() {
		return resultcode;
	}

	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {
		String province;
		String city;
		String hphm;
		String hpzl;
		List<Info> lists;

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getHphm() {
			return hphm;
		}

		public void setHphm(String hphm) {
			this.hphm = hphm;
		}

		public String getHpzl() {
			return hpzl;
		}

		public void setHpzl(String hpzl) {
			this.hpzl = hpzl;
		}

		public List<Info> getLists() {
			return lists;
		}

		public void setLists(List<Info> lists) {
			this.lists = lists;
		}

	}

	public static class Info {
		String date;
		String area;
		String act;
		String code;
		String fen;
		String money;
		String handled;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getAct() {
			return act;
		}

		public void setAct(String act) {
			this.act = act;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getFen() {
			return fen;
		}

		public void setFen(String fen) {
			this.fen = fen;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getHandled() {
			return handled;
		}

		public void setHandled(String handled) {
			this.handled = handled;
		}
	}
}