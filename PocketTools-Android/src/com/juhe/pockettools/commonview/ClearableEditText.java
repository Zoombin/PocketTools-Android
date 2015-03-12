package com.juhe.pockettools.commonview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ClearableEditText extends EditText implements
		View.OnFocusChangeListener, View.OnTouchListener, TextWatcher {
	private Drawable xD;
	private ClearableEditTextListener mListener;
	private View.OnTouchListener touchListener;
	private View.OnFocusChangeListener f;

	public ClearableEditText(Context context) {
		super(context);
		init();
	}

	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ClearableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		xD = getCompoundDrawables()[2];
		if (xD == null) {
			xD = getResources()
					.getDrawable(android.R.drawable.presence_offline);
		}
		xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
		setClearIconVisible(false);
		super.setOnTouchListener(this);
		super.setOnFocusChangeListener(this);
		addTextChangedListener(this);
	}

	public static boolean getClearIconVisible(CharSequence text) {
		return !getIsVisible(text);
	}

	public static boolean getIsVisible(CharSequence text) {
		return (text == null) || (text.length() == 0);
	}

	public void clearText(EditText edittext, String text) {
		if (isFocused()) {
			setClearIconVisible(getClearIconVisible(text));
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			setClearIconVisible(getClearIconVisible(getText()));
		} else {
			setClearIconVisible(false);
		}

		if (f != null) {
			f.onFocusChange(v, hasFocus);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {

			if (event.getAction() == MotionEvent.ACTION_UP) {
				boolean tappedX = event.getX() > (getWidth()
						- getPaddingRight() - xD.getIntrinsicWidth());
				if (tappedX) {
					setText("");
					if (mListener != null) {
						mListener.a();
					}
				}
				return true;
			}
		}
		if (touchListener != null) {
			return touchListener.onTouch(v, event);
		}
		return false;
	}

	protected void setClearIconVisible(boolean visible) {
		Drawable x = visible ? xD : null;
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
	}

	public void setListener(ClearableEditTextListener listener) {
		mListener = listener;
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener f) {
		this.f = f;
	}

	public void setOnTouchListener(View.OnTouchListener touchListener) {
		this.touchListener = touchListener;
	}

	public static abstract interface ClearableEditTextListener {
		public abstract void a();
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		setClearIconVisible(s.length() > 0);
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}
}
