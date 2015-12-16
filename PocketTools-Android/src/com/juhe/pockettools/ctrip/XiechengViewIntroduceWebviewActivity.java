package com.juhe.pockettools.ctrip;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.Config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class XiechengViewIntroduceWebviewActivity extends Activity implements
		OnClickListener {

	private WebView wv;
	private ImageView iv_back;
	private TextView tv_title;
	private Context mContext;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_view_introduce_webview);
		mContext = this;
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.title);

		wv = (WebView) findViewById(R.id.view_introduce_webview);

		XiechengViewIntroduceBean bean = getIntent().getParcelableExtra(
				"View_introduce");
		String work_time = bean.getWork_time();
		String view_item = bean.getView_item();
		String view_introduce = bean.getView_introduce();

		wv.getSettings().setDefaultTextEncodingName("utf-8");
		WebSettings webSettings = wv.getSettings();
		webSettings.setJavaScriptEnabled(true);
		String s = "<div style=\"border-bottom: "
				+ "1px solid #ddd; font-family:  Helvetica,STHeiti,Droid Sans Fallback; "
				+ "padding: 5px 0;\">"
				+ " <h3 style=\"font-weight: normal; font-size: 15px;"
				+ " padding: 5px 0; margin: 0;\">营业时间</h3>"
				+ "<p style=\"font-size: 13px; margin: 0; line-height: 18px;\">@string1</p>"
				+ "</div>"
				+ "<div style=\"border-bottom: 1px solid #ddd; "
				+ "font-family:  Helvetica,STHeiti,Droid Sans Fallback; padding: 5px 0;\">"
				+ "<h3 style=\"font-weight: normal; font-size: 15px;"
				+ " padding: 5px 0; margin: 0;\">景点特色</h3>"
				+ "<p style=\"font-size: 13px; margin: 0; line-height: 18px;\">@string2</p>"
				+ "</div><h3 style=\"font-weight: normal; "
				+ "font-size: 15px; padding: 5px 0; margin: 0;\">景点介绍</h3>@string3";
		String test = s.replace("@string1", work_time);
		String test2 = test.replace("@string2", view_item);
		view_introduce = view_introduce.replace("src=",
				"width=100% padding: 5px src=");
		String test3 = test2.replace("@string3", view_introduce);
		wv.loadDataWithBaseURL(null, test3, "text/html", "utf-8", null);
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {

		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		// 返回按钮
		iv_back = (ImageView) findViewById(R.id.iv_title_back);
		iv_back.setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(this);
		// 右侧按钮

		// title
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("景点介绍");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.iv_title_back) {
			finish();
		}

	}

}
