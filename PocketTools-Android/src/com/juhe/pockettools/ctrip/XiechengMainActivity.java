package com.juhe.pockettools.ctrip;






import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.Config;

public class XiechengMainActivity extends Activity implements OnClickListener{

	private Context mContent;
	private ImageView iv_back;
	private TextView tv_title;
	private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
	private EditText et;
	private String string;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main_xiecheng);
		et = (EditText) findViewById(R.id.putin_city);
		mContent = this;
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		initView();
		textView1 = (TextView) findViewById(R.id.search);//搜索
		textView2 = (TextView) findViewById(R.id.animal);//动物园
		textView2.setOnClickListener(this);
		textView3 = (TextView) findViewById(R.id.spring);//春游
		textView3.setOnClickListener(this);
		textView4 = (TextView) findViewById(R.id.east);//水上乐园
		textView4.setOnClickListener(this);
		textView5 = (TextView) findViewById(R.id.nine);//九寨沟
		textView5.setOnClickListener(this);
		textView6 = (TextView) findViewById(R.id.wuzhen);//大理
		textView6.setOnClickListener(this);
		textView7 = (TextView) findViewById(R.id.zhouzhuang);//周庄
		textView7.setOnClickListener(this);
		textView8 = (TextView) findViewById(R.id.suzhou);//苏州
		textView8.setOnClickListener(this);
		
		
		textView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				string = et.getText().toString();
				if(string.equals("") || string == null){
					System.out.println("输入框为空");
				}else{
					Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
					intent.putExtra("city", string);
					startActivity(intent);
				}
				
				
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.iv_title_back){
			finish();
		}
		if(v.getId() == R.id.animal){
			String string2 = "动物园";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
		}
		if(v.getId() == R.id.spring){
			String string2 = "踏青";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
		}
		if(v.getId() == R.id.east){
			String string2 = "水上乐园";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
		}
		if(v.getId() == R.id.nine){
			String string2 = "九寨沟";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
		}
		if(v.getId() == R.id.wuzhen){
			String string2 = "大理";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
		}
		if(v.getId() == R.id.zhouzhuang){
			String string2 = "周庄";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
		}
		if(v.getId() == R.id.suzhou){
			String string2 = "苏州";
			Intent intent = new Intent(mContent,XiechengViewlistActivity.class);
			intent.putExtra("city", string2);
			startActivity(intent);
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
		tv_title.setText("携程旅游");
		

	}
	

}
