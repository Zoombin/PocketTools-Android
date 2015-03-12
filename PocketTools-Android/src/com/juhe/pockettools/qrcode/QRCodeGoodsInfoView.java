package com.juhe.pockettools.qrcode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
//import com.nostra13.universalimageloader.core.c.a;
//import com.nostra13.universalimageloader.core.d;
import java.util.ArrayList;
import java.util.HashMap;

import com.juhe.pockettools.R;

public class QRCodeGoodsInfoView extends LinearLayout {
	private TextView goods_name;
	private TextView goods_company;
	private TextView goods_price;
	private TextView goods_specification;
	private TextView goods_brand;
	private TextView goods_qrcode;
	private ImageView goods_img;
	private ListView goods_place_price_list;
	private LinearLayout goods_info_container;
	private FrameLayout data_from_container;
	private QRCodeGoodsPriceListAdapter adapter;

	public QRCodeGoodsInfoView(Context context) {
		super(context);
		initView();
	}

	public QRCodeGoodsInfoView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_qrcode_shop_info, this, true);
		goods_brand = ((TextView) findViewById(R.id.goods_brand));
		goods_company = ((TextView) findViewById(R.id.goods_company));
		goods_price = ((TextView) findViewById(R.id.goods_price));
		goods_specification = ((TextView) findViewById(R.id.goods_specification));
		goods_qrcode = ((TextView) findViewById(R.id.goods_qrcode));
		goods_name = ((TextView) findViewById(R.id.goods_name));
		goods_img = ((ImageView) findViewById(R.id.goods_img));
		goods_place_price_list = ((ListView) findViewById(R.id.goods_place_price_list));
		adapter = new QRCodeGoodsPriceListAdapter(getContext());
		goods_place_price_list.setAdapter(adapter);
		goods_info_container = ((LinearLayout) findViewById(R.id.goods_info_container));
		data_from_container = ((FrameLayout) findViewById(R.id.data_from_container));
	}

	public void setGoodsInfo(QRCodeEntity.Summary summary) {
//		goods_brand.setText(summary.get);
//		goods_company.setText(summary.get);
		goods_price.setText(summary.getInterval());
//		goods_specification.setText(summary.get);
		goods_qrcode.setText(summary.getBarcode());
		goods_name.setText(summary.getName());
	}
	
//	public void a() {
//		if (goods_info_container.getVisibility() != 0) {
//			new ArrayList().add(getContext().getResources().getString(
//					2131230897));
//			goods_company.setText("--");
//			goods_brand.setText("--");
//			goods_specification.setText("--");
//			goods_price.setText("--");
//			goods_qrcode.setText("--");
//		}
//		goods_name.setText(getContext().getResources().getString(2131230897));
//		adapter.a(new ArrayList());
//	}
//
//	public void setGoodsImage(Bitmap paramBitmap) {
//		goods_img.setImageBitmap(paramBitmap);
//	}
//
//	public void setGoodsImage(String paramString) {
//		d locald = d.a();
//		com.nostra13.universalimageloader.core.c localc = new c.a().a(null)
//				.b(null).c(null).b(true).d(false).e(true)
//				.a(Bitmap.Config.RGB_565).d();
//		locald.a(paramString, goods_img, localc, new b(this), new c(this));
//	}
//
//	public void setGoodsInfo(ArrayList<String> paramArrayList) {
//		data_from_container.setVisibility(0);
//		goods_info_container.setVisibility(0);
//		goods_name.setText((CharSequence) paramArrayList.get(0));
//		goods_company.setText((CharSequence) paramArrayList.get(1));
//		goods_brand.setText((CharSequence) paramArrayList.get(2));
//		goods_specification.setText((CharSequence) paramArrayList.get(3));
//		goods_price.setText((CharSequence) paramArrayList.get(4));
//		goods_qrcode.setText((CharSequence) paramArrayList.get(5));
//	}
//
//	public void setGoodsPriceListInfo(
//			ArrayList<HashMap<String, String>> paramArrayList) {
//		adapter.a(paramArrayList);
//	}
}
