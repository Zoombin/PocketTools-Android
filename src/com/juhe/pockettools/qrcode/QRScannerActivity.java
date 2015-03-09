package com.juhe.pockettools.qrcode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;

public class QRScannerActivity extends FullscreenActivity {

	private static final long A = 200L;
	private static final String TAG = "ZBarScannerActivity";
	private static final float p = 0.5F;
//	private final MediaPlayer.OnCompletionListener B = new g(this);
//	private a h;
//	private Camera i;
//	private ImageScanner j;
//	private Handler k;
	private boolean l = true;
	private String m = "";
//	private MediaPlayer n;
	private boolean o;
	private boolean q;
//	private QRCodeResultHandler r;
	private QRCodeGoodsInfoView view_goods_info;
	private TextView view_txt;
	private FrameLayout qrcode_result_container;
	private ImageView goods_init_state;
	private TopActiveBarView action_bar;
	private ImageView x;
//	private a y;

	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
//		if (!b()) {
//			d();
//			return;
//		}
		setContentView(R.layout.activity_qr_scan);
//		((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		view_goods_info = ((QRCodeGoodsInfoView) findViewById(R.id.view_goods_info));
		goods_init_state = ((ImageView) findViewById(R.id.goods_init_state));
		view_txt = ((TextView) findViewById(R.id.view_txt));
//		this.k = new Handler();
//		a();
//		this.h = new a(this, this, this.b);
		((FrameLayout) findViewById(R.id.scan_container)).addView(this.h);
//		this.a = ((FinderView) findViewById(R.id.finder_view));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				finish();
			}
		});
		action_bar.setTiltleText("扫码比价");
//		this.r = new QRCodeResultHandler(this);
//		this.r.a(new i(this));
//		int i1 = getResources().getDisplayMetrics().widthPixels;
//		int i2 = com.fotoable.helpr.Utils.k.a(this, 10.0F);
//		int i3 = com.fotoable.helpr.Utils.k.a(this, 70.0F);
//		int i4 = (int) (0.6666667F * (i1 - i2 * 2) + i3);
		qrcode_result_container = ((FrameLayout) findViewById(R.id.qrcode_result_container));
//		((FrameLayout.LayoutParams) this.u.getLayoutParams()).topMargin = i4;
		qrcode_result_container.requestLayout();
//		this.y = new a(null, null, null);
	}
}
