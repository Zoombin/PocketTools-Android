package com.juhe.pockettools.exchange;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.AutoResizeTextView;
import com.juhe.pockettools.commonview.ClearableEditText;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.juhe.pockettools.utils.HelprCommUtil;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

//import com.juhe.pockettools.wallpaper.w;

public class ExChangeMainActivity extends FullscreenActivity {
	private ListView exchange_main_listveiw;
	private TopActiveBarView action_bar;
	private ArrayList<ExChangeEntity> exchangelist = new ArrayList<ExChangeEntity>();
	private ListViewAdatper adapter;
	private float inputvalue = 1.0F;
	private float g = 1.0F;
	private ClearableEditText txt_input;
	private RelativeLayout ly_input;
	private Button btn_input_ok;
	private ExChangeEntity exchangeentity;
	private TextView txt_refresh;

	private void inputToData() {
		txt_input.clearFocus();
		ly_input.setVisibility(View.INVISIBLE);
		hideSoftKeyborad(txt_input);
		try {
			if ((exchangeentity != null)
					&& (txt_input.getText().toString().length() > 0)) {
				this.g = Float.valueOf(txt_input.getText().toString())
						.floatValue();
				if (this.g > 0.0F) {
					inputvalue = (exchangeentity.fSellPri / 100.0F * this.g);
				}
				adapter.notifyDataSetChanged();
			}
			return;
		} catch (Exception localException) {
		}
	}

	private void setItemData(ViewHolder holder, ExChangeEntity entity,
			float value) {
		if ((entity == null) || (holder == null)) {
			return;
		}
		holder.currencyCodeLabel.setText(entity.code);
		holder.currencyNameLabel.setText(entity.name);
		float f1 = 100.0F * (value / entity.mBuyPri);
		holder.currencyResultLabel.setText(String.format("%.2f", f1));
		String str = String.format("flags/%s.png", entity.cn.toLowerCase());
		holder.img_flag.setImageBitmap(HelprCommUtil.getImage(str));

	}

	private void showInputView(ExChangeEntity entity) {
		ly_input.setVisibility(View.VISIBLE);
		txt_input.requestFocus();
		txt_input.setText("");
		txt_input.setHint("请输入" + entity.name + "金额");
		showSoftKeyboard(txt_input);
		exchangeentity = entity;
	}

	private void getNetData() {
		txt_refresh.setVisibility(View.INVISIBLE);
		action_bar.setProgressVisiable(View.VISIBLE);
		Parameters params = new Parameters();
		params.add("key", "ecfbba891b63a81c297ec1afb690d5ad");
		JuheData.executeWithAPI(23,
				"http://web.juhe.cn:8080/finance/exchange/rmbquot",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						txt_refresh.setVisibility(View.VISIBLE);
						action_bar.setProgressVisiable(View.INVISIBLE);

						if (err == 0) {
							exchangelist = ExchangeRateManager.getInstance()
									.formatList(result);
							if (exchangelist != null) {
								if (adapter == null) {
									adapter = new ListViewAdatper(
											ExChangeMainActivity.this);
								}
								adapter.setData(exchangelist);
								exchange_main_listveiw.setAdapter(adapter);
							}
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	public void hideSoftKeyborad(View paramView) {
		if (paramView != null) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
		}
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_exchange_main);
		getWindow().setSoftInputMode(3);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		btn_input_ok = ((Button) findViewById(R.id.btn_input_ok));
		ly_input = ((RelativeLayout) findViewById(R.id.ly_input));
		txt_input = ((ClearableEditText) findViewById(R.id.txt_input));
		txt_input.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int arg1, KeyEvent arg2) {
				if (arg1 == 6) {
					((InputMethodManager) v.getContext().getSystemService(
							Context.INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(v.getWindowToken(), 0);
					inputToData();
					return true;
				}
				return false;
			}
		});
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setTiltleText("汇率转换");
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void cancel() {
				finish();
			}

			@Override
			public void query() {

			}
		});
		action_bar.setProgressVisiable(View.VISIBLE);
		txt_refresh = ((TextView) findViewById(R.id.txt_refresh));
		txt_refresh.setVisibility(View.INVISIBLE);
		txt_refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getNetData();
			}
		});
		exchange_main_listveiw = ((ListView) findViewById(R.id.exchange_main_listveiw));
		exchange_main_listveiw
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (view != null) {
							ExChangeEntity entity = (ExChangeEntity) exchangelist
									.get(position);
							showInputView(entity);
						}
					}
				});

		btn_input_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				inputToData();
			}
		});
		adapter = new ListViewAdatper(this);
		exchange_main_listveiw.setAdapter(adapter);
		getNetData();
	}

	public void showSoftKeyboard(View paramView) {
		if (paramView.requestFocus()) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
					.showSoftInput(paramView, 1);
		}
	}

	private class ListViewAdatper extends BaseAdapter {
		private ArrayList<ExChangeEntity> exchangelist = new ArrayList<ExChangeEntity>();
		private Context context;

		public ListViewAdatper(Context context) {
			this.context = context;
		}

		public void setData(ArrayList<ExChangeEntity> exchangelist) {
			this.exchangelist = exchangelist;
			notifyDataSetChanged();
		}

		public int getCount() {
			return exchangelist.size();
		}

		public Object getItem(int paramInt) {
			if (exchangelist.size() <= 0) {
				return null;
			}
			return exchangelist.get(paramInt);
		}

		public long getItemId(int paramInt) {
			return paramInt;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			final ExChangeEntity entity = (ExChangeEntity) exchangelist
					.get(position);

			ViewHolder holder;
			if (convertView == null) {
				convertView = ((LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.view_exchange_listview_item, null);

				holder = new ViewHolder();

				holder.img_flag = (ImageView) convertView
						.findViewById(R.id.img_flag);
				holder.currencyCodeLabel = (TextView) convertView
						.findViewById(R.id.currencyCodeLabel);
				holder.currencyNameLabel = (TextView) convertView
						.findViewById(R.id.currencyNameLabel);
				holder.currencyResultLabel = (AutoResizeTextView) convertView
						.findViewById(R.id.currencyResultLabel);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Typeface typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Thin.otf");
			holder.currencyResultLabel.setTypeface(typeface);
			setItemData(holder, entity, inputvalue);

			return convertView;
		}
	}

	private class ViewHolder {
		public ImageView img_flag;
		public TextView currencyCodeLabel;
		public TextView currencyNameLabel;
		public AutoResizeTextView currencyResultLabel;
	}
}
