/*
 * Zirco Browser for Android
 * 
 * Copyright (C) 2010 J. Devauchelle and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.juhe.pockettools.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;

/**
 * A convenient extension of WebView.
 */
public class CustomWebView extends WebView {

	private Context mContext;

	private int mProgress = 100;

	private boolean mIsLoading = false;

	private String mLoadedUrl;

	private static boolean mBoMethodsLoaded = false;

	private static Method mOnPauseMethod = null;
	private static Method mOnResumeMethod = null;
	private static Method mSetFindIsUp = null;
	private static Method mNotifyFindDialogDismissed = null;

	/**
	 * Constructor.
	 * 
	 * @param context
	 *            The current context.
	 */
	public CustomWebView(Context context) {
		super(context);

		mContext = context;

		initializeOptions();
		loadMethods();
	}

	/**
	 * Constructor.
	 * 
	 * @param context
	 *            The current context.
	 * @param attrs
	 *            The attribute set.
	 */
	public CustomWebView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext = context;

		initializeOptions();
		loadMethods();
	}

	/**
	 * Initialize the WebView with the options set by the user through
	 * preferences.
	 */
	public void initializeOptions() {
		WebSettings webSettings = getSettings();
		
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDatabaseEnabled(true);
		webSettings.setDomStorageEnabled(true);
	    String str = getContext().getDir("database", 0).getPath();
	    webSettings.setGeolocationEnabled(true);
	    webSettings.setGeolocationDatabasePath(str);
	    webSettings.setDomStorageEnabled(true);
	    webSettings.setBuiltInZoomControls(false);
	    webSettings.setPluginState(WebSettings.PluginState.ON);
	    
//		webSettings.setSupportZoom(false);
//		webSettings.setJavaScriptEnabled(true);
//		addJavascriptInterface(this, "android");
//		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//		webSettings.setBuiltInZoomControls(true);// support zoom
////		webSettings.setPluginEnabled(true);// support flash
//		webSettings.setUseWideViewPort(true);// 这个很关键
//		webSettings.setLoadWithOverviewMode(true);
//
//		DisplayMetrics metrics = new DisplayMetrics();
//		int mDensity = metrics.densityDpi;
//		if (mDensity == 240) {
//			webSettings.setDefaultZoom(ZoomDensity.FAR);
//		} else if (mDensity == 160) {
//			webSettings.setDefaultZoom(ZoomDensity.MEDIUM);
//		} else if (mDensity == 120) {
//			webSettings.setDefaultZoom(ZoomDensity.CLOSE);
//		} else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
//			webSettings.setDefaultZoom(ZoomDensity.FAR);
//		} else if (mDensity == DisplayMetrics.DENSITY_TV) {
//			webSettings.setDefaultZoom(ZoomDensity.FAR);
//		}
		
//		 webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	}

	@Override
	public void loadUrl(String url) {
		mLoadedUrl = url;
		super.loadUrl(url);
	}

	/**
	 * Set the current loading progress of this view.
	 * 
	 * @param progress
	 *            The current loading progress.
	 */
	public void setProgress(int progress) {
		mProgress = progress;
	}

	/**
	 * Get the current loading progress of the view.
	 * 
	 * @return The current loading progress of the view.
	 */
	public int getProgress() {
		return mProgress;
	}

	/**
	 * Triggered when a new page loading is requested.
	 */
	public void notifyPageStarted() {
		mIsLoading = true;
	}

	/**
	 * Triggered when the page has finished loading.
	 */
	public void notifyPageFinished() {
		mProgress = 100;
		mIsLoading = false;
	}

	/**
	 * Check if the view is currently loading.
	 * 
	 * @return True if the view is currently loading.
	 */
	public boolean isLoading() {
		return mIsLoading;
	}

	/**
	 * Get the loaded url, e.g. the one asked by the user, without redirections.
	 * 
	 * @return The loaded url.
	 */
	public String getLoadedUrl() {
		return mLoadedUrl;
	}

	/**
	 * Reset the loaded url.
	 */
	public void resetLoadedUrl() {
		mLoadedUrl = null;
	}

	public boolean isSameUrl(String url) {
		if (url != null) {
			return url.equalsIgnoreCase(this.getUrl());
		}
		return false;
	}

	/**
	 * Perform an 'onPause' on this WebView through reflexion.
	 */
	public void doOnPause() {
		if (mOnPauseMethod != null) {
			try {
				mOnPauseMethod.invoke(this);
			} catch (IllegalArgumentException e) {
				Log.e("CustomWebView", "doOnPause(): " + e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e("CustomWebView", "doOnPause(): " + e.getMessage());
			} catch (InvocationTargetException e) {
				Log.e("CustomWebView", "doOnPause(): " + e.getMessage());
			}
		}
	}

	/**
	 * Perform an 'onResume' on this WebView through reflexion.
	 */
	public void doOnResume() {
		if (mOnResumeMethod != null) {
			try {
				mOnResumeMethod.invoke(this);
			} catch (IllegalArgumentException e) {
				Log.e("CustomWebView", "doOnResume(): " + e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e("CustomWebView", "doOnResume(): " + e.getMessage());
			} catch (InvocationTargetException e) {
				Log.e("CustomWebView", "doOnResume(): " + e.getMessage());
			}
		}
	}

	public void doSetFindIsUp(boolean value) {
		if (mSetFindIsUp != null) {
			try {
				mSetFindIsUp.invoke(this, value);
			} catch (IllegalArgumentException e) {
				Log.e("CustomWebView", "doSetFindIsUp(): " + e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e("CustomWebView", "doSetFindIsUp(): " + e.getMessage());
			} catch (InvocationTargetException e) {
				Log.e("CustomWebView", "doSetFindIsUp(): " + e.getMessage());
			}
		}
	}

	public void doNotifyFindDialogDismissed() {
		if (mNotifyFindDialogDismissed != null) {
			try {
				mNotifyFindDialogDismissed.invoke(this);
			} catch (IllegalArgumentException e) {
				Log.e("CustomWebView",
						"doNotifyFindDialogDismissed(): " + e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e("CustomWebView",
						"doNotifyFindDialogDismissed(): " + e.getMessage());
			} catch (InvocationTargetException e) {
				Log.e("CustomWebView",
						"doNotifyFindDialogDismissed(): " + e.getMessage());
			}
		}
	}

	/**
	 * Load static reflected methods.
	 */
	private void loadMethods() {
		if (!mBoMethodsLoaded) {
			try {
				mOnPauseMethod = WebView.class.getMethod("onPause");
				mOnResumeMethod = WebView.class.getMethod("onResume");
			} catch (SecurityException e) {
				Log.e("CustomWebView", "loadMethods(): " + e.getMessage());
				mOnPauseMethod = null;
				mOnResumeMethod = null;
			} catch (NoSuchMethodException e) {
				Log.e("CustomWebView", "loadMethods(): " + e.getMessage());
				mOnPauseMethod = null;
				mOnResumeMethod = null;
			}

			try {
				mSetFindIsUp = WebView.class.getMethod("setFindIsUp",
						Boolean.TYPE);
				mNotifyFindDialogDismissed = WebView.class
						.getMethod("notifyFindDialogDismissed");
			} catch (SecurityException e) {
				Log.e("CustomWebView", "loadMethods(): " + e.getMessage());
				mSetFindIsUp = null;
				mNotifyFindDialogDismissed = null;
			} catch (NoSuchMethodException e) {
				Log.e("CustomWebView", "loadMethods(): " + e.getMessage());
				mSetFindIsUp = null;
				mNotifyFindDialogDismissed = null;
			}

			mBoMethodsLoaded = true;
		}
	}
}
