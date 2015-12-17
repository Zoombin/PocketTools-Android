package com.juhe.pockettools.pm;

import android.graphics.Color;

import com.juhe.pockettools.HelprApplication;
import com.zoombin.koudai.R;

public class PMTools {
	public static int getQuality(int quality) {
		if (quality <= 100) {
			return 1;
		}
		if (quality <= 200) {
			return 2;
		}
		if (quality <= 300) {
			return 3;
		}
		if (quality <= 400) {
			return 4;
		}
		return 5;
	}

	public static String getQualityStr(int quality) {
		int qualityIndex = getQuality(quality);
		switch (qualityIndex) {
		case 1:
			return HelprApplication.getContext().getResources().getString(R.string.air_state_1);
		case 2:
			return HelprApplication.getContext().getResources().getString(R.string.air_state_2);
		case 3:
			return HelprApplication.getContext().getResources().getString(R.string.air_state_3);
		case 4:
			return HelprApplication.getContext().getResources().getString(R.string.air_state_4);
		case 5:
			return HelprApplication.getContext().getResources().getString(R.string.air_state_5);
		default:
			return HelprApplication.getContext().getResources().getString(R.string.air_state_1);
		}
	}

	public static int getQualityBackground(int quality) {
		int qualityIndex = getQuality(quality);
		switch (qualityIndex) {
		case 1:
			return Integer.valueOf(Color.rgb(11, 191, 34));
		case 2:
			return Integer.valueOf(Color.rgb(237, 189, 23));
		case 3:
			return Integer.valueOf(Color.rgb(239, 139, 9));
		case 4:
			return Integer.valueOf(Color.rgb(234, 76, 60));
		case 5:
			return Integer.valueOf(Color.rgb(203, 35, 67));
		default:
			return Integer.valueOf(Color.rgb(11, 191, 34));
		}
	}
}
