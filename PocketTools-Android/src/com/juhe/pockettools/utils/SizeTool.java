package com.juhe.pockettools.utils;

import android.content.Context;

public class SizeTool {
	
	public static int getLength(Context context, float length) {
		return (int) (0.5F + length
				* context.getResources().getDisplayMetrics().density);
	}
}
