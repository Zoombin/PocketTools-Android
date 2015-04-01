package com.juhe.pockettools.ctrip;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.map.MarkerBDMapActivity;
import com.juhe.pockettools.utils.Config;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class XiechengViewDetailsActivity extends Activity implements
		OnClickListener {
	private Context mContext;
	private ImageView iv_back;
	private TextView tv_title;
	private ImageView image;
	private TextView view_points_2, view_star_2, view_address;
	private ListView lv;
	private String view_name, view_ID;
	private ArrayList<XiechengViewDetailBean> list1;
	private ArrayList<XiechengViewMediaBean> list2;
	private ArrayList<XiechengTicketDialogBean> list3;
	private LinearLayout ll;
	private LinearLayout lin;
	private XiechengViewIntroduceBean bean6;
	private ProgressBar progressBar;
	private static final int STOP = 0x10000;
	private static final int NEXT = 0x10001;
	private int icount = 0;
	private String longitude, latitude;// 经纬度
	// private Double longfloat,latfloat;
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_view_details);
		mContext = this;
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.title);
		imageLoader = ImageLoader.getInstance();

		// ArrayList<XiechengViewDetailBean> list =
		// getIntent().getParcelableArrayListExtra("List_info");
		XiechengViewlistBean util_info = getIntent().getParcelableExtra(
				"Base_info");
		//
		view_name = util_info.getView_name().toString();
		view_ID = util_info.getId().toString();
		//
		// lv = (ListView) findViewById(R.id.ticket_listview);
		view_points_2 = (TextView) findViewById(R.id.view_points_2);
		view_star_2 = (TextView) findViewById(R.id.view_star_2);
		view_address = (TextView) findViewById(R.id.view_address_2);
		image = (ImageView) findViewById(R.id.view_image_2);
		ll = (LinearLayout) findViewById(R.id.view_query);

		progressBar = (ProgressBar) findViewById(R.id.progress);
		progressBar.setIndeterminate(false);

		imageLoader.displayImage(util_info.getPicture(), image);
		view_points_2.setText(util_info.getPoint());
		view_star_2.setText(util_info.getStar());
		view_address.setText(util_info.getAddress());
		//

		initView();
		progressBar.setVisibility(View.VISIBLE);
		progressBar.setProgress(0);
		Thread mThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 20; i++) {
					try {
						icount = (i + 1) * 5;
						Thread.sleep(1000);
						if (i == 19) {
							Message meg = new Message();
							meg.what = STOP;
							mHandler.sendMessage(meg);
						} else {
							Message meg = new Message();
							meg.what = NEXT;
							mHandler.sendMessage(meg);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		});
		mThread.start();

		getViewDetailInfo(view_ID);

	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handlerMessage(Message msg) {
			switch (msg.what) {
			case STOP:
				progressBar.setVisibility(View.GONE);
				Thread.currentThread().interrupt();
				break;
			case NEXT:
				if (!Thread.currentThread().isInterrupted()) {
					progressBar.setProgress(icount);
				}
				break;

			default:
				break;
			}
		}
	};

	@SuppressLint("NewApi")
	private void initView() {

		((ImageView) findViewById(R.id.img_bg)).setBackground(Config
				.getBgDrawable());
		// 返回按钮
		iv_back = (ImageView) findViewById(R.id.iv_title_back);
		iv_back.setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(this);
		// 右侧按钮

		// title
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(view_name);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.iv_title_back) {
			finish();
		}
	}

	public void getViewDetailInfo(String view_ID) {
		Parameters params = new Parameters();
		params.add("scenicspotid", view_ID);
		JuheData.executeWithAPI(127,
				"http://api2.juheapi.com/xiecheng/senicspot/ticket/info",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int code, String reason, String s) {
						// TODO Auto-generated method stub
						if (code == 0) {
							try {
								JSONObject jsonObject1 = new JSONObject(s);
								int status = jsonObject1.getInt("error_code");

								if (status == 200) {
									JSONObject jsonObject2 = jsonObject1
											.getJSONObject("result");
									JSONArray jsonArray1 = jsonObject2
											.getJSONArray("ScenicSpotList");
									JSONObject jsonObject3 = (JSONObject) jsonArray1
											.get(0);

									longitude = jsonObject3
											.getString("Longitude");
									latitude = jsonObject3
											.getString("Latitude");

									// longitude =
									// jsonObject3.getString("Longitude");
									// latitude =
									// jsonObject3.getString("Latitude");

									JSONArray jsonArray2 = jsonObject3
											.getJSONArray("ProductList");
									JSONObject jsonObject4 = (JSONObject) jsonArray2
											.get(0);
									// 景点详情页面票列表信息
									JSONArray jsonArray3 = jsonObject4
											.getJSONArray("ResourceList");

									list1 = new ArrayList<XiechengViewDetailBean>();
									list2 = new ArrayList<XiechengViewMediaBean>();
									for (int i = 0; i < jsonArray3.length(); i++) {
										JSONObject jsonObject5 = (JSONObject) jsonArray3
												.get(i);
										XiechengViewDetailBean bean1 = new XiechengViewDetailBean();
										bean1.setTicket_kind(jsonObject5
												.getString("Name"));
										bean1.setMarket_price(jsonObject5
												.getString("MarketPrice"));
										bean1.setPrice(jsonObject5
												.getString("Price"));
										bean1.setAdvanceBookingTime(jsonObject5
												.getString("AdvanceBookingTime"));
										list1.add(bean1);

										list3 = new ArrayList<XiechengTicketDialogBean>();
										JSONArray jsonArray4 = jsonObject5
												.getJSONArray("ResourceAddInfoList");
										for (int j = 0; j < jsonArray4.length(); j++) {
											JSONObject jsonObject6 = (JSONObject) jsonArray4
													.get(j);
											XiechengTicketDialogBean bean2 = new XiechengTicketDialogBean();
											bean2.setTitle(jsonObject6
													.getString("Title"));
											bean2.setText(jsonObject6
													.getString("Description"));
											list3.add(bean2);
										}
										XiechengViewMediaBean bean3 = new XiechengViewMediaBean();
										bean3.setList(list3);
										list2.add(bean3);
									}
									// 景点介绍详情信息获取
									JSONObject jsonObject7 = jsonObject4
											.getJSONObject("ProductDescriptionInfo");
									bean6 = new XiechengViewIntroduceBean();
									bean6.setView_introduce(jsonObject7
											.getString("Introduction"));
									JSONArray jsonArray5 = jsonObject4
											.getJSONArray("ProductAddInfoList");
									for (int t = 0; t < jsonArray5.length(); t++) {
										JSONObject jsonObject8 = (JSONObject) jsonArray5
												.get(t);
										String test = jsonObject8.getString(
												"AddInfoSubTitleName")
												.toString();
										if (test.equals("时令公告")) {
											JSONArray jsonArray6 = jsonObject8
													.getJSONArray("ProductAddInfoDetailList");
											StringBuffer sb = new StringBuffer();
											for (int k = 0; k < jsonArray6
													.length(); k++) {
												JSONObject jsonObject9 = (JSONObject) jsonArray6
														.get(k);
												sb.append(jsonObject9
														.getString("DescDetail"));
											}
											String st = sb.toString();
											bean6.setNearby_activity(st);
										}
										if (test.equals("产品经理推荐")) {
											JSONArray jsonArray6 = jsonObject8
													.getJSONArray("ProductAddInfoDetailList");
											StringBuffer sb = new StringBuffer();
											for (int k = 0; k < jsonArray6
													.length(); k++) {
												JSONObject jsonObject9 = (JSONObject) jsonArray6
														.get(k);
												sb.append(jsonObject9
														.getString("DescDetail"));
											}
											String st = sb.toString();
											bean6.setView_item(st);
										}
										if (test.equals("营业时间")) {
											JSONArray jsonArray6 = jsonObject8
													.getJSONArray("ProductAddInfoDetailList");
											StringBuffer sb = new StringBuffer();
											for (int k = 0; k < jsonArray6
													.length(); k++) {
												JSONObject jsonObject9 = (JSONObject) jsonArray6
														.get(k);
												sb.append(jsonObject9
														.getString("DescDetail"));
											}
											String st = sb.toString();
											bean6.setWork_time(st);
										}
									}

								}
								// 票价列表跳转
								lv = (ListView) findViewById(R.id.ticket_listview);
								XiechengTicketlistAdapter adapter = new XiechengTicketlistAdapter(
										mContext, list1);
								lv.setAdapter(adapter);
								progressBar.setVisibility(View.GONE);
								lv.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> parent, View view,
											int position, long id) {
										// TODO Auto-generated method stub
										XiechengViewDetailBean bean5 = new XiechengViewDetailBean();
										bean5 = list1.get(position);
										String ticket_kind = bean5
												.getTicket_kind();
										String time = bean5
												.getAdvanceBookingTime();

										XiechengViewMediaBean bean4 = new XiechengViewMediaBean();
										bean4 = list2.get(position);
										ArrayList<XiechengTicketDialogBean> list4 = new ArrayList<XiechengTicketDialogBean>();
										list4 = bean4.getList();
										Intent intent = new Intent(
												mContext,
												XiechengTicketDialogActivity.class);
										intent.putExtra("dialog_list", list4);
										intent.putExtra("ticket_kind",
												ticket_kind);
										intent.putExtra("time", time);
										startActivity(intent);
									}
								});
								// 景点介绍跳转
								ll.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												mContext,
												XiechengViewIntroduceWebviewActivity.class);
										intent.putExtra("View_introduce", bean6);
										startActivity(intent);
									}
								});
								// 地图跳转
								lin = (LinearLayout) findViewById(R.id.baidu_address);
								lin.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(mContext,
												MarkerBDMapActivity.class);
										intent.putExtra("lon", longitude);
										intent.putExtra("lat", latitude);
										startActivity(intent);
									}
								});
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});
	}
}
