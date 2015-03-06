package com.juhe.pockettools.home;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.applesn.AppleSnActivity;
import com.juhe.pockettools.calculator.CalculatorMainActivity;
import com.juhe.pockettools.constelltion.ConstelltionMainActivity;
import com.juhe.pockettools.courier.PackageMainActivity;
import com.juhe.pockettools.dream.DreamMainActivity;
import com.juhe.pockettools.exchange.ExChangeMainActivity;
import com.juhe.pockettools.flashlight.FlashLightActivity;
import com.juhe.pockettools.mirror.CameraMirrorActivity;
import com.juhe.pockettools.mobilelocale.MobileLocaleMainActivity;
import com.juhe.pockettools.poi.PoiSearchActivity;
import com.juhe.pockettools.ruler.RulerMainActivity;
import com.juhe.pockettools.sizetable.SizeTableActivity;
import com.juhe.pockettools.tuling.ChatActivity;
import com.juhe.pockettools.unit.UnitExchangeMainActivity;
import com.juhe.pockettools.violation.ViolationMainActivity;
import com.juhe.pockettools.web.WebActivity;

public class ModulesAdapter extends BaseAdapter {

	private Context context;
	private List<ModuleInfo> infos;

	public ModulesAdapter(Context context, List<ModuleInfo> infos) {
		this.context = context;
		this.infos = infos;
	}

	public void setData(List<ModuleInfo> infos) {
		this.infos = infos;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void clearData() {
		infos.clear();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ModuleInfo info = infos.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.home_module_item, parent, false);

			holder = new ViewHolder();

			holder.layout_module = (RelativeLayout) convertView
					.findViewById(R.id.layout_module);

			holder.iv_module = (ImageView) convertView
					.findViewById(R.id.iv_module);

			holder.tv_module = (TextView) convertView
					.findViewById(R.id.tv_module);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.layout_module.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				switch (info.getId()) {
				case 1:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(WebActivity.KEY_URL, "http://m.toutiao.com");
					context.startActivity(intent);
					break;
				case 2:

					break;
				case 3:

					break;
				case 4:
					intent = new Intent(context, ViolationMainActivity.class);
					context.startActivity(intent);
					break;
				case 5:
					intent = new Intent(context, MobileLocaleMainActivity.class);
					context.startActivity(intent);
					break;
				case 6:
					intent = new Intent(context, ChatActivity.class);
					context.startActivity(intent);
					break;
				case 7:
					intent = new Intent(context, AppleSnActivity.class);
					context.startActivity(intent);
					break;
				case 8:
					intent = new Intent(context, ConstelltionMainActivity.class);
					context.startActivity(intent);
					break;
				case 9:

					break;
				case 10:
					intent = new Intent(context, DreamMainActivity.class);
					context.startActivity(intent);
					break;
				case 11:

					break;
				case 12:

					break;
				case 13:

					break;
				case 14:
					intent = new Intent(context, PackageMainActivity.class);
					context.startActivity(intent);
					break;
				case 15:

					break;
				case 16:

					break;
				case 17:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(
							WebActivity.KEY_URL,
							"http://map.baidu.com/mobile/webapp/index/index#search/search/qt=s&wd=%E5%81%9C%E8%BD%A6%E5%9C%BA&c=224&searchFlag=bigBox&version=5&exptype=dep/vt=");
					context.startActivity(intent);
					break;
				case 18:
					intent = new Intent(context, PoiSearchActivity.class);
					context.startActivity(intent);
					break;
				case 19:

					break;
				case 20:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(
							WebActivity.KEY_URL,
							"http://op.juhe.cn/ofpay/pay/recharge");
					context.startActivity(intent);
					break;
				case 21:

					break;
				case 22:

					break;
				case 23:

					break;
				case 24:
					intent = new Intent(context, RulerMainActivity.class);
					context.startActivity(intent);
					break;
				case 25:
					intent = new Intent(context, CameraMirrorActivity.class);
					context.startActivity(intent);
					break;
				case 26:
					intent = new Intent(context, CalculatorMainActivity.class);
					context.startActivity(intent);
					break;
				case 27:
					intent = new Intent(context, SizeTableActivity.class);
					context.startActivity(intent);
					break;
				case 28:
					intent = new Intent(context, UnitExchangeMainActivity.class);
					context.startActivity(intent);
					break;
				case 29:
					intent = new Intent(context, ExChangeMainActivity.class);
					context.startActivity(intent);
					break;
				case 30:
					intent = new Intent(context, FlashLightActivity.class);
					context.startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
		holder.tv_module.setText(info.getName());

		return convertView;
	}

	public class ViewHolder {
		RelativeLayout layout_module;
		ImageView iv_module;
		TextView tv_module;
	}
}
