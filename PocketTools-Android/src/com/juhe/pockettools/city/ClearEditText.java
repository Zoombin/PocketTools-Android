package com.juhe.pockettools.city;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.zoombin.koudai.R;

public class ClearEditText extends EditText implements TextWatcher,
		View.OnFocusChangeListener {
	private Drawable drawable;

	public ClearEditText(Context context) {
		this(context, null);
	}

	public ClearEditText(Context context, AttributeSet attributeSet) {
		this(context, attributeSet, android.R.attr.editTextStyle);
	}

	public ClearEditText(Context context, AttributeSet attributeSet,
			int defStyle) {
		super(context, attributeSet, defStyle);
		initView();
	}

	public static Animation getAnimation(int interpolator) {
		TranslateAnimation animation = new TranslateAnimation(
				0.0F, 10.0F, 0.0F, 0.0F);
		animation
				.setInterpolator(new CycleInterpolator(interpolator));
		animation.setDuration(1000L);
		return animation;
	}

	private void initView() {
		drawable = getCompoundDrawables()[2];
		if (drawable == null) {
			drawable = getResources().getDrawable(R.drawable.emotionstore_progresscancelbtn);
		}
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		setClearIconVisible(false);
		setOnFocusChangeListener(this);
		addTextChangedListener(this);
	}

	public void setAnimation() {
		setAnimation(getAnimation(5));
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			int i = getText().length();
			boolean bool = false;
			if (i > 0) {
				bool = true;
			}
			setClearIconVisible(bool);
			return;
		}
		setClearIconVisible(false);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		setClearIconVisible(s.length() > 0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if ((getCompoundDrawables()[2] != null)
				&& (event.getAction() == MotionEvent.ACTION_UP)) {
			if ((event.getX() > getWidth() - getPaddingRight()
					- drawable.getIntrinsicWidth())
					&& (event.getX() < getWidth() - getPaddingRight())) {
				setText("");
				return super.onTouchEvent(event);
			}
		}
		return false;
	}

	protected void setClearIconVisible(boolean visible) {
		Drawable x = visible ? drawable : null;
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
	}
}
