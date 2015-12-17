package com.juhe.pockettools.train;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zoombin.koudai.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
//import com.fotoable.helpr.wallpaper.w;

public class TrainTimesResultView extends FrameLayout {
	private Context context;
	private TopActiveBarView active_top_bar;
	private TrainTimesSearchResultView resultView;
	private a d;

	public TrainTimesResultView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainTimesResultView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_train_times_result, this, true);
//		((ImageView) findViewById(2131361805)).setImageBitmap(w.a().d());
		active_top_bar = ((TopActiveBarView) findViewById(R.id.active_top_bar));
		active_top_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				
			}
		});
		active_top_bar.setProgressVisiable(View.VISIBLE);
		resultView = ((TrainTimesSearchResultView) findViewById(R.id.train_times_result));
//		resultView.setListener(new t(this));
	}

	public void a(String paramString1, String paramString2, String paramString3) {
		active_top_bar.setTiltleText(paramString2);
//		resultView.a(paramString1, paramString2, paramString3);
	}

	public void setListener(a parama) {
		this.d = parama;
	}

	public static abstract interface a {
		public abstract void a();
	}
}
