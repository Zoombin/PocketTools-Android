package com.juhe.pockettools.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.aboutus.AboutUsActivity;
import com.juhe.pockettools.air.AirMainActivity;
import com.juhe.pockettools.applesn.AppleSnActivity;
import com.juhe.pockettools.calculator.CalculatorMainActivity;
import com.juhe.pockettools.calendar.activity.CalendarActivity;
import com.juhe.pockettools.constelltion.ConstelltionMainActivity;
import com.juhe.pockettools.courier.PackageMainActivity;
import com.juhe.pockettools.ctrip.XiechengMainActivity;
import com.juhe.pockettools.dream.DreamMainActivity;
import com.juhe.pockettools.exchange.ExChangeMainActivity;
import com.juhe.pockettools.flashlight.FlashLightActivity;
import com.juhe.pockettools.mirror.CameraMirrorActivity;
import com.juhe.pockettools.mobilelocale.MobileLocaleMainActivity;
import com.juhe.pockettools.moive.MovieMainActivity;
import com.juhe.pockettools.oil.OilMainActivity;
import com.juhe.pockettools.parking.ParkingLotSearchActivity;
import com.juhe.pockettools.pm.PMMainActivity;
import com.juhe.pockettools.poi.PoiSearchActivity;
import com.juhe.pockettools.ruler.RulerMainActivity;
import com.juhe.pockettools.secret.SetPasswordActivity;
import com.juhe.pockettools.sizetable.SizeTableActivity;
import com.juhe.pockettools.train.TrainMainActivity;
import com.juhe.pockettools.tuling.ChatActivity;
import com.juhe.pockettools.unit.UnitExchangeMainActivity;
import com.juhe.pockettools.utils.Constants;
import com.juhe.pockettools.violation.ViolationMainActivity;
import com.juhe.pockettools.weather.WeatherMainActivity;
import com.juhe.pockettools.web.WebActivity;
import com.qq.e.ads.appwall.APPWall;
import com.zbar.lib.CaptureActivity;

public class itemButton extends FrameLayout {
	TextView item_text;
	ImageView item_image;
	RelativeLayout itembutton_container;
	Context context;

	public itemButton(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public itemButton(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		LayoutInflater localLayoutInflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		localLayoutInflater.inflate(R.layout.view_maintab_grid_item2, this,
				true);
		this.item_image = ((ImageView) findViewById(R.id.item_image));
		this.item_text = ((TextView) findViewById(R.id.item_text));
		this.itembutton_container = ((RelativeLayout) findViewById(R.id.itembutton_container));
	}

	public void setData(final int id, int icon, String name) {
		this.item_image.setBackgroundResource(icon);
		this.item_text.setText(name);
		this.itembutton_container.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent;
				switch (id) {
				case 1:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(WebActivity.KEY_URL, "http://m.toutiao.com");
					context.startActivity(intent);
					break;
				case 2:
					intent = new Intent(context, PMMainActivity.class);
					context.startActivity(intent);
					break;
				case 3:
					intent = new Intent(context, WeatherMainActivity.class);
					context.startActivity(intent);
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
					intent = new Intent(context, CalendarActivity.class);
					context.startActivity(intent);
					break;
				case 10:
					intent = new Intent(context, DreamMainActivity.class);
					context.startActivity(intent);
					break;
				case 11:
					intent = new Intent(context, SetPasswordActivity.class);
					context.startActivity(intent);
					break;
				case 12:
					intent = new Intent(context, TrainMainActivity.class);
					context.startActivity(intent);
					break;
				case 13:
					intent = new Intent(context, OilMainActivity.class);
					context.startActivity(intent);
					break;
				case 14:
					intent = new Intent(context, PackageMainActivity.class);
					context.startActivity(intent);
					break;
				case 15:
					intent = new Intent(context, MovieMainActivity.class);
					context.startActivity(intent);
					break;
				case 16:
					intent = new Intent(context, AirMainActivity.class);
					context.startActivity(intent);
					break;
				case 17:
//					intent = new Intent(context, WebActivity.class);
//					intent.putExtra(
//							WebActivity.KEY_URL,
//							"http://map.baidu.com/mobile/webapp/index/index#search/search/qt=s&wd=%E5%81%9C%E8%BD%A6%E5%9C%BA&c=224&searchFlag=bigBox&version=5&exptype=dep/vt=");
					intent = new Intent(context, ParkingLotSearchActivity.class);
					context.startActivity(intent);
					break;
				case 18:
					intent = new Intent(context, PoiSearchActivity.class);
					context.startActivity(intent);
					break;
				case 19:
					intent = new Intent(context, CaptureActivity.class);
					context.startActivity(intent);
					break;
				case 20:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(WebActivity.KEY_URL,
							"http://op.juhe.cn/ofpay/pay/recharge");
					context.startActivity(intent);
					break;
				case 21:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(
							WebActivity.KEY_URL,
							"http://wvs.m.taobao.com/game_card.htm?spm=0.0.0.0&sid=1c72ee52cc4ce9c966d24fa7c96e9841&pid=null&back_hidden_flag=null&pds=game%23h%23zhichong&type=2&unid=null");
					context.startActivity(intent);
					break;
				case 22:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(WebActivity.KEY_URL,
							"http://touch.lecai.com/?noClientdl=1&agentId=3179#path=page%2Fmain");
					context.startActivity(intent);
					break;
				case 23:
					intent = new Intent(context, WebActivity.class);
					intent.putExtra(WebActivity.KEY_URL,
							"http://kdgj.liulianginn.com/koudai/index.html");
					context.startActivity(intent);
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
				case 31:
					intent = new Intent(context, XiechengMainActivity.class);
					context.startActivity(intent);
					break;
				case 32:
					intent = new Intent(context, AboutUsActivity.class);
					context.startActivity(intent);
					break;
				case 33:
					APPWall wall = new APPWall(context, Constants.APPID, Constants.APPWallPosID);
			        wall.setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			        wall.doShowAppWall();
					break;
				default:
					break;
				}
			}
		});
	}
}
