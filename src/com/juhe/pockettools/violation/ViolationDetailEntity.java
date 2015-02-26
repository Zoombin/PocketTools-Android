package com.juhe.pockettools.violation;

public class ViolationDetailEntity {
	
	// 违章行为
	private String act;
	
	// 违章地点
	private String area;
	
	// 违章时间
	private String date;
	
	// 违章罚款(仅供参考)
	private String money;
	
	// 违章代码(仅供参考)
	private String code;
	
	// 是否处理,1处理 0未处理 空未知
	private String handled;

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHandled() {
		return handled;
	}

	public void setHandled(String handled) {
		this.handled = handled;
	}
}
