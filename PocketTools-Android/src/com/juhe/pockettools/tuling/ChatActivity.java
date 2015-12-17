package com.juhe.pockettools.tuling;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zoombin.koudai.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

@SuppressLint("SimpleDateFormat")
public class ChatActivity extends Activity implements OnClickListener {

	private TopActiveBarView action_bar;
	private List<ListData> lists;
	private ListView lv;
	private EditText sendtext;
	private Button send_btn;
	private String content_str;
	private TextAdapter adapter;
	private String[] welcome_array;
	private double currentTime = 0, oldTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		action_bar = (TopActiveBarView) findViewById(R.id.action_bar);
		action_bar.setTiltleText("聊天机器人");
		action_bar.setSplitLineVisible(true);
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void cancel() {
				finish();
			}

			@Override
			public void query() {

			}
		});

		lv = (ListView) findViewById(R.id.lv);
		sendtext = (EditText) findViewById(R.id.sendText);
		send_btn = (Button) findViewById(R.id.send_btn);
		lists = new ArrayList<ListData>();
		send_btn.setOnClickListener(this);
		adapter = new TextAdapter(lists, this);
		lv.setAdapter(adapter);
		ListData listData;
		listData = new ListData(getRandomWelcomeTips(), ListData.RECEIVER,
				getTime());
		lists.add(listData);
	}

	private String getRandomWelcomeTips() {
		String welcome_tip = null;
		welcome_array = this.getResources()
				.getStringArray(R.array.welcome_tips);
		int index = (int) (Math.random() * (welcome_array.length - 1));
		welcome_tip = welcome_array[index];
		return welcome_tip;
	}

	public void parseText(String str) {
		try {
			ListData listData;
			listData = new ListData(str, ListData.RECEIVER, getTime());
			lists.add(listData);
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		getTime();
		content_str = sendtext.getText().toString();
		if (TextUtils.isEmpty(content_str)) {
			return;
		}
		sendtext.setText("");
		String dropk = content_str.replace(" ", "");
		String droph = dropk.replace("\n", "");
		ListData listData;
		listData = new ListData(content_str, ListData.SEND, getTime());
		lists.add(listData);
		if (lists.size() > 30) {
			for (int i = 0; i < lists.size(); i++) {
				lists.remove(i);
			}
		}
		adapter.notifyDataSetChanged();
		Parameters params = new Parameters();
		params.add("key", "39ded27a75232bba451702ab705faeea");
		params.add("info", droph);

		JuheData.executeWithAPI(112, "http://op.juhe.cn/robot/index",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						if (err == 0) {
							ChatEntity chatentity = new Gson().fromJson(result,
									ChatEntity.class);
							parseText(chatentity.getResult().getText());
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	private String getTime() {
		currentTime = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date curDate = new Date();
		String str = format.format(curDate);
		if (currentTime - oldTime >= 500) {
			oldTime = currentTime;
			return str;
		} else {
			return "";
		}
	}
}
