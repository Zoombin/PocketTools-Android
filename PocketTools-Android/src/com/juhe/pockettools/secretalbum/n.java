package com.juhe.pockettools.secretalbum;

import java.io.Serializable;

public class n implements Serializable {

	private static final long serialVersionUID = 1L;
	private int b;
	private int c;
	private String d;
	private String e;
	private String f;

	public n() {
	}

	public n(int paramInt1, int paramInt2, String paramString1,
			String paramString2, String paramString3) {
		this.b = paramInt1;
		this.c = paramInt2;
		this.d = paramString1;
		this.e = paramString2;
		this.f = paramString3;
	}

	public int a() {
		return this.b;
	}

	public void a(int paramInt) {
		this.b = paramInt;
	}

	public void a(String paramString) {
		this.d = paramString;
	}

	public int b() {
		return this.c;
	}

	public void b(int paramInt) {
		this.c = paramInt;
	}

	public void b(String paramString) {
		this.e = paramString;
	}

	public String c() {
		return this.d;
	}

	public void c(String paramString) {
		this.f = paramString;
	}

	public String d() {
		return this.e;
	}

	public String e() {
		return this.f;
	}
}
