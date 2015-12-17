package com.juhe.pockettools.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zoombin.koudai.R;

public class WebActivity extends Activity {

	public static final String KEY_URL = "URL";
	private CustomWebView webView; // 网页视图
	private ProgressBar progressBar; // 进度条
	private String url = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		url = getIntent().getStringExtra(KEY_URL);
		if (TextUtils.isEmpty(url)) {
			finish();
		}
		setContentView(R.layout.activity_web);

		webView = (CustomWebView) findViewById(R.id.webView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		initializeCurrentWebView();
		loadUrl(url);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	// 初始化当前网页视图
	private void initializeCurrentWebView() {
		webView.setInitialScale(100);
		// 设置web视图客户端
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
//				progressBar.setVisibility(View.VISIBLE);
				((CustomWebView) view).notifyPageStarted();
				super.onPageStarted(view, url, favicon);

			}

			@Override
			public void onPageFinished(WebView view, String url) {
				((CustomWebView) view).notifyPageFinished();
				super.onPageFinished(view, url);

			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

			}
		});
		// 设置web核客户端
		webView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				((CustomWebView) view).setProgress(newProgress);
				if (newProgress >= 50
						&& progressBar.getVisibility() == View.VISIBLE) {
					progressBar.setVisibility(View.GONE);
				}
			}
		});
	}

	// 载入网页
	private void loadUrl(String url) {
		webView.loadUrl(url);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
