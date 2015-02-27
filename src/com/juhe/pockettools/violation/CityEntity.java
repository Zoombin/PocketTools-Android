package com.juhe.pockettools.violation;

import java.io.Serializable;

public class CityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	int a;
	String abbr;
	// 省份代码
	String province_code;
	// 省份名称
	String province;
	// 是否需要发动机号0,不需要 1,需要
	boolean engine;
	// 需要几位发动机号0,全部 1-9 ,需要发动机号后N位
	int engineno;
	// 是否需要车架号0,不需要 1,需要
	boolean classa;
	// 需要几位车架号0,全部 1-9 需要车架号后N位
	int classno;
	// 是否需要登记证书号0,不需要 1,需要
	boolean regist;
	// 需要几位登记证书0,全部 1-9 需要登记证书后N位
	int registno;
	// 城市代码
	String city_code;
	// 城市名称
	String city_name;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public boolean isEngine() {
		return engine;
	}

	public void setEngine(boolean engine) {
		this.engine = engine;
	}

	public int getEngineno() {
		return engineno;
	}

	public void setEngineno(int engineno) {
		this.engineno = engineno;
	}

	public boolean isClassa() {
		return classa;
	}

	public void setClassa(boolean classa) {
		this.classa = classa;
	}

	public int getClassno() {
		return classno;
	}

	public void setClassno(int classno) {
		this.classno = classno;
	}

	public boolean isRegist() {
		return regist;
	}

	public void setRegist(boolean regist) {
		this.regist = regist;
	}

	public int getRegistno() {
		return registno;
	}

	public void setRegistno(int registno) {
		this.registno = registno;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
}
