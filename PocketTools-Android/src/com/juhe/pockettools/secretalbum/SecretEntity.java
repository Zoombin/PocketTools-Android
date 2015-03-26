package com.juhe.pockettools.secretalbum;

import java.io.Serializable;

public class SecretEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String filename;
	private boolean checked;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
