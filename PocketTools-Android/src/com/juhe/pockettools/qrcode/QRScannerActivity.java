package com.juhe.pockettools.qrcode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zoombin.koudai.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.city.CityActivity;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.pm.PMMainActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class QRScannerActivity extends FullscreenActivity {

	private static final String TAG = "ZBarScannerActivity";

	private QRCodeGoodsInfoView view_goods_info;
	private QRCodeGoodsPriceListAdapter adapter;
	private QRCodeGoodsPriceListView listview;
	private TextView view_txt;
//	private FrameLayout qrcode_result_container;
	private TextView txtCitys;
	private ImageView goods_init_state;
	private String barcode;
	private Button btn_back;
	private FrameLayout btn_save;
	private int cityid = 1;
	QRCityEntity entity;
	
	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.activity_qr_scan);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		view_goods_info = ((QRCodeGoodsInfoView) findViewById(R.id.view_goods_info));
		view_txt = ((TextView) findViewById(R.id.view_txt));
		
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btn_save = (FrameLayout) findViewById(R.id.btn_save);
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (entity != null) {
					goCityActivity();
				} else {
					getCity();
				}
			}
		});
		
		txtCitys = (TextView) findViewById(R.id.txtCitys);
		listview = (QRCodeGoodsPriceListView) findViewById(R.id.goods_place_price_list);

//		qrcode_result_container = ((FrameLayout) findViewById(R.id.qrcode_result_container));
//		qrcode_result_container.requestLayout();
		
		adapter = new QRCodeGoodsPriceListAdapter(this);
		listview.setAdapter(adapter);
		
		barcode = getIntent().getStringExtra("barcode");
		getData();
	}
	
	private void getCity() {
		Parameters params = new Parameters();
		params.add("pkg", "com.koudai");
		JuheData.executeWithAPI(52,
				"http://api.juheapi.com/jhbar/city",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						
						if (err == 0) {
//							{"error_code":0,"reason":"请求成功！","result":[{"_id":859,"cityName":"阜宁县","lat":33.759325,"lng":119.802529,"provinceId":32},{"_id":861,"cityName":"靖江市","lat":31.982751,"lng":120.277138,"provinceId":32},{"_id":863,"cityName":"启东市","lat":31.80822,"lng":121.657428,"provinceId":32},{"_id":865,"cityName":"海安县","lat":32.533573,"lng":120.467343,"provinceId":32},{"_id":867,"cityName":"涟源市","lat":27.692542,"lng":111.664316,"provinceId":40},{"_id":869,"cityName":"冷水江市","lat":27.686252,"lng":111.434984,"provinceId":40},{"_id":871,"cityName":"泗洪县","lat":33.458914,"lng":118.216207,"provinceId":32},{"_id":873,"cityName":"桐乡市","lat":30.63007,"lng":120.565102,"provinceId":33},{"_id":875,"cityName":"沭阳县","lat":34.111022,"lng":118.804784,"provinceId":32},{"_id":877,"cityName":"灌云县","lat":34.284381,"lng":119.239381,"provinceId":33},{"_id":879,"cityName":"宝应县","lat":33.240392,"lng":119.360729,"provinceId":32},{"_id":881,"cityName":"新沂市","lat":34.369584,"lng":118.354445,"provinceId":32},{"_id":883,"cityName":"射阳县","lat":33.774806,"lng":120.258053,"provinceId":32},{"_id":885,"cityName":"滨海县","lat":33.990334,"lng":119.820831,"provinceId":32},{"_id":887,"cityName":"如皋市","lat":32.371993,"lng":120.573582,"provinceId":32},{"_id":889,"cityName":"响水县","lat":34.199481,"lng":119.578361,"provinceId":32},{"_id":891,"cityName":"睢宁县","lat":33.912786,"lng":117.941538,"provinceId":32},{"_id":893,"cityName":"兴化市","lat":32.910459,"lng":119.852541,"provinceId":32},{"_id":895,"cityName":"凤台县","lat":32.709445,"lng":116.711051,"provinceId":34},{"_id":897,"cityName":"如东县","lat":32.315794,"lng":121.187392,"provinceId":32},{"_id":899,"cityName":"当涂县","lat":31.571213,"lng":118.497972,"provinceId":34},{"_id":901,"cityName":"三河市","lat":39.982718,"lng":117.078295,"provinceId":25},{"_id":903,"cityName":"临安市","lat":30.233873,"lng":119.724733,"provinceId":33},{"_id":905,"cityName":"永康市","lat":28.888782,"lng":120.047394,"provinceId":33},{"_id":907,"cityName":"新昌县","lat":29.499832,"lng":120.903866,"provinceId":33},{"_id":909,"cityName":"姜堰市","lat":32.508753,"lng":120.127129,"provinceId":32},{"_id":911,"cityName":"吴县市","lat":31.262791,"lng":120.631978,"provinceId":32},{"_id":913,"cityName":"高邮市","lat":32.781665,"lng":119.459165,"provinceId":32},{"_id":915,"cityName":"泗阳县","lat":33.72112,"lng":118.703656,"provinceId":32},{"_id":923,"cityName":"余姚市","lat":30.037197,"lng":121.154629,"provinceId":33},{"_id":925,"cityName":"文登市","lat":37.193913,"lng":122.058128,"provinceId":37},{"_id":927,"cityName":"虎门市","lat":22.830649,"lng":113.698741,"provinceId":41},{"_id":931,"cityName":"义乌市","lat":29.306841,"lng":120.075058,"provinceId":33},{"_id":933,"cityName":"都江堰市","lat":30.988411,"lng":103.646956,"provinceId":45},{"_id":935,"cityName":"个旧市","lat":23.359121,"lng":103.160034,"provinceId":47},{"_id":939,"cityName":"楚雄市","lat":25.045532,"lng":101.528069,"provinceId":47},{"_id":941,"cityName":"彭州市","lat":30.990109,"lng":103.958014,"provinceId":45},{"_id":943,"cityName":"西昌市","lat":27.894504,"lng":102.264449,"provinceId":45},{"_id":945,"cityName":"毫州市","lat":33.844582,"lng":115.778676,"provinceId":34},{"_id":947,"cityName":"江油市","lat":31.777979,"lng":104.745731,"provinceId":45},{"_id":949,"cityName":"吴川市","lat":21.441863,"lng":110.77821,"provinceId":41},{"_id":951,"cityName":"英德市","lat":24.186062,"lng":113.415045,"provinceId":41},{"_id":953,"cityName":"丰城市","lat":28.159142,"lng":115.771094,"provinceId":36},{"_id":955,"cityName":"樟树市","lat":28.055853,"lng":115.546152,"provinceId":36},{"_id":957,"cityName":"德清市","lat":30.613328,"lng":120.291669,"provinceId":33},{"_id":959,"cityName":"海宁市","lat":30.510664,"lng":120.680748,"provinceId":33},{"_id":961,"cityName":"南安市","lat":24.960385,"lng":118.386279,"provinceId":35},{"_id":963,"cityName":"石狮市","lat":24.732246,"lng":118.64794,"provinceId":35},{"_id":965,"cityName":"长乐市","lat":25.96312,"lng":119.523382,"provinceId":35},{"_id":967,"cityName":"肇东市","lat":46.051138,"lng":125.962164,"provinceId":30},{"_id":969,"cityName":"盖州市","lat":40.40049,"lng":122.348784,"provinceId":30},{"_id":971,"cityName":"涟水县","lat":33.78096,"lng":119.260335,"provinceId":32},{"_id":973,"cityName":"灌南县","lat":34.0867,"lng":119.315685,"provinceId":32},{"_id":975,"cityName":"东港市","lat":39.863008,"lng":124.152705,"provinceId":28},{"_id":977,"cityName":"福清市","lat":25.720229,"lng":119.383735,"provinceId":35},{"_id":158,"cityName":"包头市","lat":40.657449,"lng":109.840347,"provinceId":27},{"_id":170,"cityName":"巴彦淖尔","lat":40.743213,"lng":107.387657,"provinceId":27},{"_id":178,"cityName":"新余市","lat":27.817819,"lng":114.91741,"provinceId":36},{"_id":180,"cityName":"南昌市","lat":28.68316,"lng":115.858089,"provinceId":36},{"_id":182,"cityName":"九江市","lat":29.705078,"lng":116.00193,"provinceId":36},{"_id":184,"cityName":"上饶市","lat":28.454863,"lng":117.943433,"provinceId":36},{"_id":186,"cityName":"抚州市","lat":27.949217,"lng":116.358182,"provinceId":36},{"_id":188,"cityName":"宜春市","lat":27.815619,"lng":114.416778,"provinceId":36},{"_id":190,"cityName":"吉安市","lat":27.113039,"lng":114.992912,"provinceId":36},{"_id":192,"cityName":"赣州市","lat":25.831829,"lng":114.93503,"provinceId":36},{"_id":194,"cityName":"景德镇","lat":29.268836,"lng":117.17842,"provinceId":36},{"_id":196,"cityName":"萍乡市","lat":27.622768,"lng":113.854556,"provinceId":36},{"_id":198,"cityName":"鹰潭市","lat":28.260189,"lng":117.069202,"provinceId":36},{"_id":200,"cityName":"忻州市","lat":38.416663,"lng":112.734174,"provinceId":26},{"_id":202,"cityName":"太原市","lat":37.87059,"lng":112.548879,"provinceId":26},{"_id":204,"cityName":"大同市","lat":40.076816,"lng":113.300126,"provinceId":26},{"_id":206,"cityName":"阳泉市","lat":37.856972,"lng":113.580519,"provinceId":26},{"_id":208,"cityName":"晋中市","lat":37.687024,"lng":112.752695,"provinceId":26},{"_id":210,"cityName":"长治市","lat":36.195386,"lng":113.116255,"provinceId":26},{"_id":212,"cityName":"晋城市","lat":35.490702,"lng":112.851831,"provinceId":26},{"_id":214,"cityName":"临汾市","lat":36.088067,"lng":111.51888,"provinceId":26},{"_id":216,"cityName":"吕梁","lat":37.518314,"lng":111.144319,"provinceId":26},{"_id":218,"cityName":"运城市","lat":35.026412,"lng":111.007529,"provinceId":26},{"_id":244,"cityName":"济南市","lat":36.650997,"lng":117.120497,"provinceId":37},{"_id":246,"cityName":"青岛市","lat":36.067082,"lng":120.38264,"provinceId":37},{"_id":248,"cityName":"淄博市","lat":36.813094,"lng":118.054868,"provinceId":37},{"_id":252,"cityName":"烟台市","lat":37.463819,"lng":121.447926,"provinceId":37},{"_id":254,"cityName":"潍坊市","lat":36.706774,"lng":119.161756,"provinceId":37},{"_id":256,"cityName":"济宁市","lat":35.414921,"lng":116.587099,"provinceId":37},{"_id":258,"cityName":"泰安市","lat":36.200252,"lng":117.087614,"provinceId":37},{"_id":260,"cityName":"临沂市","lat":35.104672,"lng":118.356448,"provinceId":37},{"_id":264,"cityName":"哈尔滨","lat":45.803775,"lng":126.534967,"provinceId":30},{"_id":266,"cityName":"齐齐哈尔","lat":47.354348,"lng":123.918186,"provinceId":30},{"_id":268,"cityName":"牡丹江","lat":44.551653,"lng":129.633169,"provinceId":30},{"_id":270,"cityName":"佳木斯","lat":46.799923,"lng":130.318917,"provinceId":30},{"_id":274,"cityName":"黑河市","lat":50.245297,"lng":127.52846,"provinceId":30},{"_id":280,"cityName":"大庆市","lat":46.59019,"lng":125.104637,"provinceId":30},{"_id":282,"cityName":"福州市","lat":26.074508,"lng":119.296494,"provinceId":35},{"_id":284,"cityName":"厦门市","lat":24.479834,"lng":118.089425,"provinceId":35},{"_id":286,"cityName":"宁德市","lat":26.665617,"lng":119.547933,"provinceId":35},{"_id":288,"cityName":"莆田市","lat":25.454085,"lng":119.007777,"provinceId":35},{"_id":290,"cityName":"泉州市","lat":24.874699,"lng":118.675464,"provinceId":35},{"_id":292,"cityName":"晋江市","lat":24.781681,"lng":118.552365,"provinceId":35},{"_id":294,"cityName":"漳州市","lat":24.512949,"lng":117.647481,"provinceId":35},{"_id":296,"cityName":"龙岩市","lat":25.075129,"lng":117.017705,"provinceId":35},{"_id":298,"cityName":"三明市","lat":26.263407,"lng":117.638678,"provinceId":35},{"_id":300,"cityName":"南平市","lat":26.641769,"lng":118.177708,"provinceId":35},{"_id":302,"cityName":"韶关市","lat":24.810403,"lng":113.597522,"provinceId":41},{"_id":304,"cityName":"惠州市","lat":23.111847,"lng":114.416196,"provinceId":41},{"_id":306,"cityName":"梅州市","lat":24.288615,"lng":116.122239,"provinceId":41},{"_id":308,"cityName":"汕头市","lat":23.353299,"lng":116.681838,"provinceId":41},{"_id":310,"cityName":"珠海市","lat":22.270715,"lng":113.576726,"provinceId":41},{"_id":312,"cityName":"佛山市","lat":23.021548,"lng":113.121416,"provinceId":41},{"_id":314,"cityName":"肇庆市","lat":23.047192,"lng":112.465091,"provinceId":41},{"_id":316,"cityName":"湛江市","lat":21.270702,"lng":110.359387,"provinceId":41},{"_id":318,"cityName":"中山市","lat":22.516686,"lng":113.392795,"provinceId":41},{"_id":320,"cityName":"河源市","lat":23.743538,"lng":114.700447,"provinceId":41},{"_id":322,"cityName":"清远市","lat":23.681764,"lng":113.056031,"provinceId":41},{"_id":324,"cityName":"顺德市","lat":60.1385818,"lng":24.2269874,"provinceId":41},{"_id":326,"cityName":"云浮市","lat":22.915094,"lng":112.044491,"provinceId":41},{"_id":328,"cityName":"潮州市","lat":23.65695,"lng":116.622604,"provinceId":41},{"_id":330,"cityName":"东莞市","lat":23.020536,"lng":113.751765,"provinceId":41},{"_id":332,"cityName":"汕尾市","lat":22.786211,"lng":115.375279,"provinceId":41},{"_id":334,"cityName":"潮阳市","lat":23.464556,"lng":116.43135,"provinceId":41},{"_id":336,"cityName":"阳江市","lat":21.857958,"lng":111.982232,"provinceId":41},{"_id":338,"cityName":"揭阳市","lat":23.549993,"lng":116.372831,"provinceId":41},{"_id":340,"cityName":"成都市","lat":30.658601,"lng":104.064856,"provinceId":45},{"_id":348,"cityName":"自贡市","lat":29.33903,"lng":104.778442,"provinceId":45},{"_id":352,"cityName":"绵阳市","lat":31.46745,"lng":104.679114,"provinceId":45},{"_id":354,"cityName":"南充市","lat":30.837793,"lng":106.110698,"provinceId":45},{"_id":356,"cityName":"达州市","lat":31.209572,"lng":107.468023,"provinceId":45},{"_id":360,"cityName":"遂宁市","lat":30.532847,"lng":105.592898,"provinceId":45},{"_id":366,"cityName":"泸州市","lat":28.87182,"lng":105.442347,"provinceId...
							QRCityEntity entity = new Gson().fromJson(result, QRCityEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							QRScannerActivity.this.entity = entity;
							goCityActivity();
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	private void goCityActivity() {
		Intent intent = new Intent(QRScannerActivity.this,
				CityActivity.class);
		intent.putExtra("citylist", entity);
		startActivityForResult(intent,
				CityActivity.REQUEST_CODE_CITYNAME);
	}
	private void getData() {
		barcode = "6923450605332";
		Parameters params = new Parameters();
		params.add("barcode", barcode);
		params.add("cityid", cityid);
		params.add("pkg", "com.koudai");
		JuheData.executeWithAPI(52,
				"http://api.juheapi.com/jhbar/bar",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						
						if (err == 0) {
							QRCodeEntity entity = new Gson().fromJson(result, QRCodeEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							view_goods_info.setGoodsInfo(entity.getResult().getSummary());
							adapter.setData(entity.getResult().getShop());
							adapter.notifyDataSetChanged();
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CityActivity.REQUEST_CODE_CITYNAME) {
			if (resultCode == RESULT_OK) {
				txtCitys.setText(data.getStringExtra(CityActivity.EXTRA_CITYNAME));
				cityid = data.getIntExtra(CityActivity.EXTRA_CITYID, 0);
				getData();
			}
		}
	}
}
