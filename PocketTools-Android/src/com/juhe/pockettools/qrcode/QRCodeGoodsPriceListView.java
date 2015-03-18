package com.juhe.pockettools.qrcode;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class QRCodeGoodsPriceListView extends ListView {
	public QRCodeGoodsPriceListView(Context context) {
		super(context);
	}

	public QRCodeGoodsPriceListView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}

	public QRCodeGoodsPriceListView(Context context, AttributeSet attributeSet,
			int paramInt) {
		super(context, attributeSet, paramInt);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}