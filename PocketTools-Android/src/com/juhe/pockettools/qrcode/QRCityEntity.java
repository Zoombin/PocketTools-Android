package com.juhe.pockettools.qrcode;

import java.io.Serializable;
import java.util.List;

import com.juhe.pockettools.city.SortModel;

public class QRCityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private List<SortModel> result;

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

	public List<SortModel> getResult() {
		return result;
	}

	public void setResult(List<SortModel> result) {
		this.result = result;
	}

}
