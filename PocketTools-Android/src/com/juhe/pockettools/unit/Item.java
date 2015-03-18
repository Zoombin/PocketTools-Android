package com.juhe.pockettools.unit;

public class Item {
	public int index;
	public double value;
	public String text;
	public DoubleInterFace doubleinterface = null;

	public static abstract interface DoubleInterFace {
		public abstract double getValue(double paramDouble);
	}
}