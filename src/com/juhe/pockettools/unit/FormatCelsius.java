package com.juhe.pockettools.unit;

class FormatCelsius implements Item.DoubleInterFace {
	FormatCelsius(Unit paramm) {
	}

	public double getValue(double paramDouble) {
		return 5.0D * (paramDouble - 32.0D) / 9.0D;
	}
}
