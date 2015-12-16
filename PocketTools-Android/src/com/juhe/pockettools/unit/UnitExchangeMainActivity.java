package com.juhe.pockettools.unit;

import java.text.NumberFormat;
import java.util.ArrayList;

import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;

//import com.fotoable.helpr.wallpaper.w;

public class UnitExchangeMainActivity extends FullscreenActivity {
	private static final String TAG = "UnitExchangeMainActivity";
	FrameLayout layout_unit;
	private TopActiveBarView topactivebarview;
	private EditText leftinput;
	private EditText rightinput;
	private CheckBox btn_rate_dir;
	private WheelView unit_1;
	private WheelView unit_2;
	private TextView left_detail;
	private TextView right_detail;
	private WheelAdapter wheeladapter = null;
	private Unit unit;
	private ArrayList<Item> list = new ArrayList<Item>();
	private Type type = Type.LENGTH;
	private boolean isLeft = false;
	private Item left;
	private Item right;

	private String formatDoubleToString(double paramDouble) {
		// Log.v("UnitExchangeMainActivity",
		// "UnitExchangeMainActivity formatDoubleToString :" + paramDouble);
		NumberFormat numberformat = NumberFormat.getInstance();
		numberformat.setGroupingUsed(false);
		numberformat.setMaximumFractionDigits(3);
		numberformat.setMinimumIntegerDigits(1);
		return numberformat.format(paramDouble);
	}

	private void refreshWheel(int index) {
		if (index > Type.values().length) {
			return;
		}
		type = Type.values()[index];
		list = unit.getList(type);
		left = ((Item) list.get(0));
		right = ((Item) list.get(0));
		wheeladapter = new WheelAdapter(this);
		wheeladapter.setData(list);
		unit_1.setViewAdapter(wheeladapter);
		unit_2.setViewAdapter(wheeladapter);
		unit_1.setCurrentItem(0);
		unit_2.setCurrentItem(0);
		left_detail.setText(left.text);
		right_detail.setText(right.text);
		EditText edittext;
		if (!isLeft) {
			edittext = leftinput;
		} else {
			edittext = rightinput;
		}
		showResult(edittext);
	}

	private void clickbutton(Button clickbutton) {
		leftinput.setText("1");
		rightinput.setText("1");

		LinearLayout ly_unit_type = (LinearLayout) findViewById(R.id.ly_unit_type);

		for (int i = 0; i < ly_unit_type.getChildCount(); i++) {
			final Button button = (Button) ly_unit_type.getChildAt(i);
			if (button == clickbutton) {
				button.setSelected(true);
				refreshWheel(Integer.valueOf((String) clickbutton.getTag())
						.intValue() - 100);
			} else {
				button.setSelected(false);
			}
		}
	}

	private void showResult(EditText edittext) {
		String leftdetail = left_detail.getText().toString();
		String rightdetail = right_detail.getText().toString();
		double d1 = 0.0D;
		double d2 = 0.0D;
		if (leftinput.getText().toString().length() > 0) {
			d1 = Double.valueOf(leftinput.getText().toString()).doubleValue();
		}
		if (rightinput.getText().toString().length() > 0) {
			d2 = Double.valueOf(rightinput.getText().toString()).doubleValue();
		}
		double leftvalue = 0.0D;
		double rightvalue = 0.0D;
		ArrayList<Item> itemlist = unit.getList(type);
		for (Item item : itemlist) {
			if (leftdetail.equals(item.text)) {
				leftvalue = item.value;
			}
			if (rightdetail.equals(item.text)) {
				rightvalue = item.value;
			}
		}

		if (edittext == leftinput) {
			if (type != Type.TEMPERATURE) {
				leftinput.setText(formatDoubleToString(d2 * leftvalue
						/ rightvalue));
			} else {
				if (leftdetail.equals(rightdetail)) {
					leftinput.setText(formatDoubleToString(d2));
				} else {
					if (leftdetail.equals("摄氏度")) {
						leftinput
								.setText(formatDoubleToString(32.0D + 9.0D * d1 / 5.0D));
					} else {
						leftinput
								.setText(formatDoubleToString(5.0D * (d2 - 32.0D) / 9.0D));
					}
				}
			}
		} else {
			if (type != Type.TEMPERATURE) {
				rightinput.setText(formatDoubleToString(d1 * rightvalue
						/ leftvalue));
			} else {
				if (leftdetail.equals(rightdetail)) {
					rightinput.setText(formatDoubleToString(d1));
				} else {
					if (leftdetail.equals("摄氏度")) {
						rightinput
								.setText(formatDoubleToString(32.0D + 9.0D * d1 / 5.0D));
					} else {
						rightinput
								.setText(formatDoubleToString(5.0D * (d2 - 32.0D) / 9.0D));
					}
				}
			}
		}
	}

	private void setValue(WheelView wheelview, int index) {
		if (index > list.size()) {
			return;
		}
		if (wheelview == unit_1) {
			left = ((Item) list.get(index));
		} else {
			right = ((Item) list.get(index));
		}
		left_detail.setText(left.text);
		right_detail.setText(right.text);
		EditText edittext;
		if (isLeft) {
			edittext = leftinput;
		} else {
			edittext = rightinput;
		}
		showResult(edittext);
	}

	private void initBottomBar() {
		LinearLayout ly_unit_type = (LinearLayout) findViewById(R.id.ly_unit_type);
		for (int i = 0; i < ly_unit_type.getChildCount(); i++) {
			final Button button = (Button) ly_unit_type.getChildAt(i);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					clickbutton(button);
				}
			});
			if (Integer.valueOf((String) button.getTag()).intValue() != 100) {
				button.setSelected(false);
			} else {
				button.setSelected(true);
			}
		}
	}

	private void initWheelView() {
		unit_1 = ((WheelView) findViewById(R.id.unit_1));
		unit_2 = ((WheelView) findViewById(R.id.unit_2));
		unit_1.setVisibleItems(10);
		unit_1.setWheelBackground(R.drawable.uint_wheel_bg);
		unit_1.setWheelForeground(R.drawable.uint_wheel_val);
		unit_1.setDrawShadows(false);
		unit_1.setViewAdapter(wheeladapter);
		unit_1.setCurrentItem(0);
		unit_1.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheelview) {
				btn_rate_dir.setClickable(false);
			}

			@Override
			public void onScrollingFinished(WheelView wheelview) {
				btn_rate_dir.setClickable(true);
				setValue(wheelview, wheelview.getCurrentItem());
			}
		});
		unit_2.setVisibleItems(10);
		unit_2.setWheelBackground(R.drawable.uint_wheel_bg);
		unit_2.setWheelForeground(R.drawable.uint_wheel_val);
		unit_2.setDrawShadows(false);
		unit_2.setViewAdapter(wheeladapter);
		unit_2.setCurrentItem(0);
		unit_2.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheelview) {
				btn_rate_dir.setClickable(false);
			}

			@Override
			public void onScrollingFinished(WheelView wheelview) {
				btn_rate_dir.setClickable(true);
				setValue(wheelview, wheelview.getCurrentItem());
			}
		});
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_unit_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		layout_unit = (FrameLayout) findViewById(R.id.layout_unit);
		layout_unit.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// 比较Activity根布局与当前布局的大小
						int heightDiff = layout_unit.getRootView().getHeight()
								- layout_unit.getHeight();
						if (heightDiff < 100) {
							EditText edittext;
							if (isLeft) {
								edittext = leftinput;
							} else {
								edittext = rightinput;
							}
							showResult(edittext);
						}
					}
				});

		// ((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		topactivebarview = ((TopActiveBarView) findViewById(R.id.action_bar));
		topactivebarview.setTiltleText("单位转换");
		topactivebarview
				.setListener(new TopActiveBarView.InterfaceTopActiveBar() {

					@Override
					public void cancel() {
						btn_rate_dir.setClickable(false);
						// k.a(this.a);
						finish();
					}

					@Override
					public void query() {
						// TODO Auto-generated method stub

					}
				});
		topactivebarview.setProgressVisiable(View.INVISIBLE);
		leftinput = ((EditText) findViewById(R.id.leftinput));
		rightinput = ((EditText) findViewById(R.id.rightinput));
		btn_rate_dir = ((CheckBox) findViewById(R.id.btn_rate_dir));
		btn_rate_dir.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton checkbotton,
					boolean checked) {
				EditText edittext;
				isLeft = checked;
				if (isLeft) {
					edittext = leftinput;
				} else {
					edittext = rightinput;
				}
				showResult(edittext);
			}
		});
		left_detail = ((TextView) findViewById(R.id.left_detail));
		right_detail = ((TextView) findViewById(R.id.right_detail));
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/HelveticaNeue-Thin.otf");
		leftinput.setTypeface(typeface);
		rightinput.setTypeface(typeface);
		leftinput.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if ((actionId == EditorInfo.IME_ACTION_DONE)
						|| (actionId == EditorInfo.IME_ACTION_NEXT)) {
					((InputMethodManager) v.getContext().getSystemService(
							"input_method")).hideSoftInputFromWindow(
							v.getWindowToken(), 0);
					EditText edittext;
					if (isLeft) {
						edittext = leftinput;
					} else {
						edittext = rightinput;
					}
					showResult(edittext);
					return true;
				}
				return false;
			}
		});
		rightinput.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if ((actionId == EditorInfo.IME_ACTION_DONE)
						|| (actionId == EditorInfo.IME_ACTION_NEXT)) {
					((InputMethodManager) v.getContext().getSystemService(
							"input_method")).hideSoftInputFromWindow(
							v.getWindowToken(), 0);
					EditText edittext;
					if (isLeft) {
						edittext = leftinput;
					} else {
						edittext = rightinput;
					}
					showResult(edittext);
					return true;
				}
				return false;
			}
		});

		unit = new Unit();
		list = unit.getList(type);
		left = ((Item) list.get(0));
		right = ((Item) list.get(0));
		left_detail.setText(left.text);
		right_detail.setText(right.text);
		wheeladapter = new WheelAdapter(this);
		wheeladapter.setData(list);
		initWheelView();
		initBottomBar();
	}

	private class WheelAdapter extends
			kankan.wheel.widget.adapters.AbstractWheelTextAdapter {
		ArrayList<Item> list = new ArrayList<Item>();

		protected WheelAdapter(Context context) {
			super(context, R.layout.view_unit_wheel_item, NO_RESOURCE);
			setItemTextResource(R.id.unit_name);
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		public View getItem(int index, View convertView, ViewGroup parent) {
			return super.getItem(index, convertView, parent);
		}

		@Override
		public CharSequence getItemText(int index) {
			return ((Item) list.get(index)).text;
		}

		public void setData(ArrayList<Item> list) {
			this.list = list;
			notifyDataChangedEvent();
		}
	}
}
