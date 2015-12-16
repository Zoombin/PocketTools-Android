package com.juhe.pockettools.setting;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

public class WapperBgEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int bg;
	private Drawable bgDrawable;

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

	public void setBgDrawable(Drawable bgDrawable) {
		this.bgDrawable = bgDrawable;
	}

	public Drawable getBgDrawable() {
		return bgDrawable;
	}
}
