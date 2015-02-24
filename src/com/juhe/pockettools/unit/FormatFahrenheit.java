package com.juhe.pockettools.unit;

class FormatFahrenheit implements Item.DoubleInterFace {
	FormatFahrenheit(Unit paramm) {
	}

	public double getValue(double paramDouble) {
		return 32.0D + 9.0D * paramDouble / 5.0D;
	}
}
