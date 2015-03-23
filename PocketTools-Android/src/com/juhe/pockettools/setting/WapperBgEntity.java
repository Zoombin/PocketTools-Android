package com.juhe.pockettools.setting;

import java.io.Serializable;

public class WapperBgEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int bg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBg() {
		return bg;
	}

	public void setBg(int bg) {
		this.bg = bg;
	}

}
