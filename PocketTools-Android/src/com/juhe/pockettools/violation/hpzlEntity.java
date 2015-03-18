package com.juhe.pockettools.violation;

import java.io.Serializable;

/**
 * 车牌种类
 * @author daiye
 *
 */
public class hpzlEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 车辆类型
	private String car;
	
	// 车辆类型编号
	private String id;

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
