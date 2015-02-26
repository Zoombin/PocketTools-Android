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
	private int id;

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
