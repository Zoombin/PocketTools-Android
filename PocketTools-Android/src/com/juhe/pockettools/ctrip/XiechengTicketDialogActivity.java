package com.juhe.pockettools.ctrip;

import java.util.ArrayList;



import com.juhe.pockettools.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class XiechengTicketDialogActivity extends Activity implements OnClickListener{
	private Context mContent;
	private ImageView iv_title_right;
	private TextView tv_title;
	private ListView lv;
	private String ticket_kind,time;
	private TextView ticket_kind_2,ticketlistview_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dialog_ticketinfo);
		mContent = this;
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		initView();
		//接收数据
		ticket_kind = getIntent().getStringExtra("ticket_kind");
		time = getIntent().getStringExtra("time");
		ArrayList<XiechengTicketDialogBean> dialog_list = getIntent().getParcelableArrayListExtra("dialog_list");
		
		//绑定控件
		ticket_kind_2 = (TextView) findViewById(R.id.ticket_kind_2);
		ticketlistview_time = (TextView) findViewById(R.id.ticketlistview_time);
		lv = (ListView) findViewById(R.id.ticket_details);
		//填入数据
		ticket_kind_2.setText(ticket_kind);
		ticketlistview_time.setText(time);
		XiechengTicketDialogAdapter adapter = new XiechengTicketDialogAdapter(mContent, dialog_list);
		lv.setAdapter(adapter);
		
		
	}
	private void initView() {
		// 返回按钮
		
		// 右侧按钮
		iv_title_right = (ImageView) findViewById(R.id.tv_title_right);
		iv_title_right.setVisibility(View.VISIBLE);
		iv_title_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// title
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("详细信息");

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
