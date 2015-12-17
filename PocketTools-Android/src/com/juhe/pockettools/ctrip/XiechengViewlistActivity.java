package com.juhe.pockettools.ctrip;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zoombin.koudai.R;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;


public class XiechengViewlistActivity extends Activity implements
		OnClickListener {
	private Context mContext;
	private ImageView iv_back;
	private TextView tv_title;
	private ListView lv;
	private String string;
	private ArrayList<XiechengViewlistBean> view_list;
	private final int PAGE_SIZE = 20;
	private int pageCount;
	private int currPage = 1;
	private PullToRefreshListView mPullRefreshListView;

	private RelativeLayout loadingLatyout;
	private ProgressBar progressBar;
	private TextView tvReason;
	private XiechengViewlistAdapter adapter;

	// private ArrayList<XiechengViewDetailBean> util_view_details;
	// private ArrayList<XiechengViewMediaBean>
	// util_view_media;//作为中间媒介就这个类里面用一下

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewlist);
		mContext = this;
		string = getIntent().getStringExtra("city");
		initView();
		this.getviewInfo(string);

	}

	private void getviewInfo(String string) {
		Parameters params = new Parameters();
		params.add("keyword", string);
		params.add("pageindex", currPage);
		params.add("pagesize", PAGE_SIZE);
		JuheData.executeWithAPI(127,
				"http://api2.juheapi.com/xiecheng/senicspot/ticket/search",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int code, String reason, String s) {
						// TODO Auto-generated method stub
						if (code == 0) {
							try {
								JSONObject jsonObject = new JSONObject(s);
								int error_code = jsonObject
										.getInt("error_code");
								if (error_code == 200) {
									JSONObject jObject = jsonObject
											.getJSONObject("result");
									pageCount = jObject.getInt("PagingCount");
									JSONArray jsonArray = jObject
											.getJSONArray("ScenicSpotListItemList");
									int e = jsonArray.length();
									if (e == 0) {
										progressBar.setVisibility(View.GONE);
										tvReason.setVisibility(View.VISIBLE);
										return;
									}
									ArrayList<XiechengViewlistBean> list = new ArrayList<XiechengViewlistBean>();
									for (int i = 0; i < jsonArray.length(); i++) {
										JSONObject job = (JSONObject) jsonArray
												.get(i);
										XiechengViewlistBean util_view = new XiechengViewlistBean();
										util_view.setPicture(job
												.getString("Image"));
										util_view.setMarket_price(job
												.getString("MarketPrice"));
										util_view.setPrice(job
												.getString("Price"));
										util_view.setStar(job.getString("Star"));
										util_view.setPoint(job
												.getString("CommentGrade"));
										util_view.setView_name(job
												.getString("Name"));
										util_view.setDistrictName(job
												.getString("DistrictName"));
										util_view.setId(job.getString("ID"));
										util_view.setAddress(job
												.getString("Address"));
										list.add(util_view);
									}
									view_list.addAll(list);
									adapter.notifyDataSetChanged();
									mPullRefreshListView.onRefreshComplete();
									currPage++;
									if (currPage > pageCount) {
										mPullRefreshListView
												.setMode(Mode.DISABLED);
									}

								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();

							}
						}
						loadingLatyout.setVisibility(View.GONE);

					}
				});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.iv_title_back) {
			finish();
		}
	}

	@SuppressLint("NewApi")
	private void initView() {

		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		// 返回按钮
		iv_back = (ImageView) findViewById(R.id.iv_title_back);
		iv_back.setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(this);
		// 右侧按钮

		// title
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(string);

		progressBar = (ProgressBar) findViewById(R.id.progress);
		loadingLatyout = (RelativeLayout) findViewById(R.id.loading_layout);
		tvReason = (TextView) findViewById(R.id.tv_reason);

		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.view_list);
		mPullRefreshListView.setMode(Mode.PULL_FROM_END);
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						getviewInfo(string);
					}
				});

		lv = mPullRefreshListView.getRefreshableView();
		view_list = new ArrayList<XiechengViewlistBean>();
		adapter = new XiechengViewlistAdapter(mContext, view_list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				XiechengViewlistBean util_viewlist_2 = new XiechengViewlistBean();
				util_viewlist_2 = view_list.get(position);
				Intent intent = new Intent(mContext,
						XiechengViewDetailsActivity.class);
				intent.putExtra("Base_info", util_viewlist_2);
				startActivity(intent);
			}

		});

	}

}
