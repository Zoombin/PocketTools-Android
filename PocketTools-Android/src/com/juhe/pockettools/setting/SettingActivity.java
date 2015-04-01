package com.juhe.pockettools.setting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.HorizontalListView;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;

@SuppressLint("CutPasteId")
public class SettingActivity extends FullscreenActivity implements
		OnItemClickListener {

	ImageView img_bg;
	TopActiveBarView action_bar;
	HorizontalListView wapper_bg_listview;
	WapperBgAdapter adapter;
	Button button_select_bg;
	Drawable drawable;
	List<WapperBgEntity> list;
	int count = 0;
	private Drawable dw;
	WapperBgEntity entity4 = new WapperBgEntity();

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_setting);
		img_bg = ((ImageView) findViewById(R.id.img_bg));
		img_bg.setBackground(Config.getBgDrawable());

		button_select_bg = (Button) findViewById(R.id.select_bg);
		button_select_bg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count++;
				Intent intent2 = new Intent(Intent.ACTION_PICK, null);
				intent2.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent2, 1);
				// ContentResolver contentResolver = getContentResolver();
				// // 照片的原始资源地址
				// Uri uri = intent2.getData();
				// Bitmap photo = null;
				// try {
				// photo = MediaStore.Images.Media.getBitmap(
				// contentResolver, uri);
				//
				// // String[] proj = {MediaStore.Images.Media.DATA};
				// // Cursor cursor = managedQuery(uri, proj, null, null, null);
				// // int colun_index =
				// cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// // cursor.moveToFirst();
				// // String path = cursor.getString(colun_index);
				//
				// } catch (FileNotFoundException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// drawable = new BitmapDrawable(photo);
			}
		});

		View view = findViewById(R.id.select_bg);
		view.getBackground().setAlpha(70);

		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setTiltleText("设置背景");
		action_bar.setSplitLineVisible(false);
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void query() {

			}

			@Override
			public void cancel() {
				finish();
			}
		});
		wapper_bg_listview = ((HorizontalListView) findViewById(R.id.wapper_bg_listview));
		list = new ArrayList<WapperBgEntity>();
		WapperBgEntity entity1 = new WapperBgEntity();
		entity1.setId(0);
		entity1.setBg(R.drawable.bg1);
		list.add(entity1);

		WapperBgEntity entity2 = new WapperBgEntity();
		entity2.setId(0);
		entity2.setBg(R.drawable.bg2);
		list.add(entity2);

		WapperBgEntity entity3 = new WapperBgEntity();
		entity3.setId(0);
		entity3.setBg(R.drawable.bg3);
		list.add(entity3);
		File f = new File("/sdcard/myFolder/temp_cropped.jpg");
		if (f.exists()) {
			entity4.setId(0);
			entity4.setBgDrawable(new BitmapDrawable(BitmapFactory
					.decodeFile("/sdcard/myFolder/temp_cropped.jpg")));
			list.add(entity4);
		}

		adapter = new WapperBgAdapter(this, list);

		wapper_bg_listview.setAdapter(adapter);
		wapper_bg_listview.setOnItemClickListener(this);
		adapter.setSelected(Config.getBgIndex());
	}

	@SuppressLint("NewApi")
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Config.setBgIndex(position);
		adapter.setSelected(position);
		img_bg.setBackground(Config.getBgDrawable());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (resultCode == -1) {
				ContentResolver contentResolver = getContentResolver();
				// 照片的原始资源地址
				Uri uri = data.getData();

				try {
					// 使用ContentProvider通过URI获取原始图片
					Bitmap photo = MediaStore.Images.Media.getBitmap(
							contentResolver, uri);

					File file = new File("/sdcard/myFolder");
					if (!file.exists()) {
						file.mkdir();
						file = new File("/sdcard/temp.jpg".trim());
						String fileName = file.getName();
						String mName = fileName.substring(0,
								fileName.lastIndexOf("."));
						String sName = fileName.substring(fileName
								.lastIndexOf("."));

						String newFilePath = "/sdcard/myFolder" + "/" + mName
								+ "_cropped" + sName;
						file = new File(newFilePath);
						try {
							file.createNewFile();
							FileOutputStream fos = new FileOutputStream(file);
							photo.compress(CompressFormat.JPEG, 50, fos);
							fos.flush();
							fos.close();
						} catch (Exception e) {
							// TODO: handle exception
						}
					} else if (file.exists()) {
						file = new File("/sdcard/temp.jpg".trim());
						String fileName = file.getName();
						String mName = fileName.substring(0,
								fileName.lastIndexOf("."));
						String sName = fileName.substring(fileName
								.lastIndexOf("."));

						String newFilePath = "/sdcard/myFolder" + "/" + mName
								+ "_cropped" + sName;
						file = new File(newFilePath);
						System.out.println(newFilePath + "    文件地址");
						try {
							file.createNewFile();
							FileOutputStream fos = new FileOutputStream(file);
							photo.compress(CompressFormat.JPEG, 50, fos);
							fos.flush();
							fos.close();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}

					if (photo != null) {
						// 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
						// Bitmap smallBitmap = ImageTools.zoomBitmap(photo,
						// photo.getWidth() / SCALE, photo.getHeight()
						// / SCALE);
						// 释放原始图片占用的内存，防止out of memory异常发生
						// photo.recycle();

						// img_bg.setBackgroundDrawable(drawable);
						drawable = new BitmapDrawable(photo);

						// if(count == 1){
						//
						// entity4.setId(0);
						// entity4.setBgDrawable(drawable);
						//
						// list.add(3, entity4);
						// dw = list.get(3).getBgDrawable();
						// Config config = new Config(this, dw);
						// adapter.notifyDataSetChanged();
						// }else if(count > 1){
						entity4.setId(0);
						entity4.setBgDrawable(drawable);
						if (!list.contains(entity4)) {
							list.add(entity4);
						}
						dw = list.get(list.size() - 1).getBgDrawable();
						adapter.notifyDataSetChanged();
						// }

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ex_change_main, menu);
		return true;
	}
}
