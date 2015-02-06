package com.juhe.pockettools.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.juhe.pockettools.R;
import com.juhe.pockettools.base.BaseActivity;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

/**
 * 首页
 * 
 * @author daiye
 * 
 */
public class MainActivity extends BaseActivity {

	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);

		Parameters params = new Parameters();
		params.add("ip", "www.juhe.cn");

		/**
		 * 请求的方法 参数: 第一个参数 接口id 第二个参数 接口请求的url 第三个参数 接口请求的方式 第四个参数
		 * 接口请求的参数,键值对com.thinkland.sdk.android.Parameters类型; 第五个参数
		 * 请求的回调方法,com.thinkland.sdk.android.DataCallBack;
		 * 
		 */
		JuheData.executeWithAPI(1, "http://apis.juhe.cn/ip/ip2addr",
				JuheData.GET, params, new DataCallBack() {

					/**
					 * @param err
					 *            错误码,0为成功
					 * @param reason
					 *            原因
					 * @param result
					 *            数据
					 */
					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						// TODO Auto-generated method stub
						if (err == 0) {
							tv.setText(result);
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
