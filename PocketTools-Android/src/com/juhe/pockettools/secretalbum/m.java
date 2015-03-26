package com.juhe.pockettools.secretalbum;

import java.io.Serializable;

public class m implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean b;
	private n c;

	public m(n paramn) {
		this.c = paramn;
	}

	public void a(boolean paramBoolean) {
		this.b = paramBoolean;
	}

	public boolean a() {
		return this.b;
	}

	public n b() {
		return this.c;
	}
}
