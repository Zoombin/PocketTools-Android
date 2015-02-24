package com.juhe.pockettools.unit;

import java.text.NumberFormat;
import java.util.ArrayList;

import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.home.FullscreenActivity;

//import com.fotoable.helpr.wallpaper.w;

public class UnitExchangeMainActivity extends FullscreenActivity {
	private static final String TAG = "UnitExchangeMainActivity";
	private TopActiveBarView topactivebarview;
	private EditText leftinput;
	private EditText rightinput;
	private Button btn_rate_dir;
	private FrameLayout ly_btn_rate;
	// private boolean g = false;
	private WheelView unit_1;
	private WheelView unit_2;
	private TextView left_detail;
	private TextView right_detail;
	private WheelAdapter wheeladapter = null;
	private Unit unit;
	private ArrayList<Item> list = new ArrayList<Item>();
	private Type type = Type.LENGTH;
	private boolean isRight = true;
	private Item left;
	private Item right;

	// private boolean s = true;

	private double getTempratureValue(double value1, double value2) {
		if ((type == Type.TEMPERATURE) && (left.index != right.index)) {
			if ((!isRight) || (right.doubleinterface == null)) {
				value1 = right.doubleinterface.getValue(value2);
				if (left.doubleinterface == null) {
					return value1;
				}
			}
		}
		return left.doubleinterface.getValue(value2);
	}

	private String formatDoubleToString(double paramDouble) {
		// Log.v("UnitExchangeMainActivity",
		// "UnitExchangeMainActivity formatDoubleToString :" + paramDouble);
		NumberFormat numberformat = NumberFormat.getInstance();
		numberformat.setGroupingUsed(false);
		numberformat.setMaximumFractionDigits(3);
		numberformat.setMinimumIntegerDigits(1);
		return numberformat.format(paramDouble);
	}

	// private void a() {
	// isselected = btn_rate_dir.isSelected();
	// if (isselected) {
	// }
	// for (EditText localEditText = leftinput;; localEditText = rightinput) {
	// a(localEditText);
	// return;
	// }
	// }

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
		if (!isRight) {
			edittext = leftinput;
		} else {
			edittext = rightinput;
		}
		showResult(edittext);
	}

	private void clickbutton(Button clickbutton) {
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
		double d1 = 0.0D;
		boolean bool1;
		boolean bool3;
		if (edittext == leftinput) {
			bool1 = true;
			isRight = bool1;
			Button localButton = btn_rate_dir;
			boolean bool2 = isRight;
			bool3 = false;
			if (!bool2) {
				bool3 = true;
			}
			localButton.setSelected(bool3);
			if (leftinput.getText().toString().length() <= 0) {
				for (double d2 = Double.valueOf(leftinput.getText().toString())
						.doubleValue();; d2 = d1) {
					if (rightinput.getText().toString().length() > 0) {
						d1 = Double.valueOf(rightinput.getText().toString())
								.doubleValue();
					}
					if (isRight) {
						double d4 = getTempratureValue(d2 / left.value
								* right.value, d2);
						if (leftinput.getText().toString().length() == 0) {
							leftinput.setText("0");
						}
						rightinput.setText(formatDoubleToString(d4));
						return;
						// bool1 = false;
						// break;
					}
					double d3 = getTempratureValue(d1 / right.value
							* left.value, d1);
					if (rightinput.getText().toString().length() == 0) {
						rightinput.setText("0");
					}
					leftinput.setText(formatDoubleToString(d3));
					return;
				}
			}
		} else {
			bool1 = true;
			isRight = bool1;
			Button localButton = btn_rate_dir;
			boolean bool2 = isRight;
			bool3 = false;
			if (!bool2) {
				bool3 = true;
			}
			localButton.setSelected(bool3);
			if (leftinput.getText().toString().length() > 0) {
				for (double d2 = Double.valueOf(leftinput.getText().toString())
						.doubleValue();; d2 = d1) {
					if (rightinput.getText().toString().length() > 0) {
						d1 = Double.valueOf(rightinput.getText().toString())
								.doubleValue();
					}
					if (isRight) {
						double d4 = getTempratureValue(d2 / left.value
								* right.value, d2);
						if (leftinput.getText().toString().length() == 0) {
							leftinput.setText("0");
						}
						rightinput.setText(formatDoubleToString(d4));
						return;
						// bool1 = false;
						// break;
					}
					double d3 = getTempratureValue(d1 / right.value
							* left.value, d1);
					if (rightinput.getText().toString().length() == 0) {
						rightinput.setText("0");
					}
					leftinput.setText(formatDoubleToString(d3));
					return;
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
		if (!isRight) {
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
				// UnitExchangeMainActivity.b(this.a, true);
			}

			@Override
			public void onScrollingFinished(WheelView wheelview) {
				// UnitExchangeMainActivity.b(this.a, false);

				setValue(wheelview, wheelview.getCurrentItem());
				// UnitExchangeMainActivity.a(this.a, paramWheelView,
				// paramWheelView.getCurrentItem());

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
				// UnitExchangeMainActivity.b(this.a, true);
			}

			@Override
			public void onScrollingFinished(WheelView wheelview) {

				// UnitExchangeMainActivity.b(this.a, false);

				setValue(wheelview, wheelview.getCurrentItem());
				// UnitExchangeMainActivity.a(this.a, paramWheelView,
				// paramWheelView.getCurrentItem());
			}
		});
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_unit_main);
		// ((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		topactivebarview = ((TopActiveBarView) findViewById(R.id.action_bar));
		topactivebarview.setTiltleText("单位转换");
		topactivebarview
				.setListener(new TopActiveBarView.InterfaceTopActiveBar() {

					@Override
					public void cancel() {
						// UnitExchangeMainActivity.a(this.a, false);
						// k.a(this.a);
						finish();
					}

					@Override
					public void a() {
						// TODO Auto-generated method stub

					}
				});
		topactivebarview.setProgressVisiable(View.INVISIBLE);
		leftinput = ((EditText) findViewById(R.id.leftinput));
		rightinput = ((EditText) findViewById(R.id.rightinput));
		btn_rate_dir = ((Button) findViewById(R.id.btn_rate_dir));
		left_detail = ((TextView) findViewById(R.id.left_detail));
		right_detail = ((TextView) findViewById(R.id.right_detail));
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/HelveticaNeue-Thin.otf");
		leftinput.setTypeface(typeface);
		rightinput.setTypeface(typeface);
		ly_btn_rate = ((FrameLayout) findViewById(R.id.ly_btn_rate));
		ly_btn_rate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// UnitExchangeMainActivity.a(this.a);
			}
		});
		leftinput.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if ((actionId == EditorInfo.IME_ACTION_DONE)
						|| (actionId == EditorInfo.IME_ACTION_NEXT)) {
					((InputMethodManager) v.getContext().getSystemService(
							"input_method")).hideSoftInputFromWindow(
							v.getWindowToken(), 0);
					// UnitExchangeMainActivity.a(this.a,
					// UnitExchangeMainActivity.b(this.a));
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
					// UnitExchangeMainActivity.a(this.a,
					// UnitExchangeMainActivity.c(this.a));
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
		// new com.fotoable.helpr.Utils.i(leftinput).a(new h(this));
		// new com.fotoable.helpr.Utils.i(rightinput).a(new i(this));
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
