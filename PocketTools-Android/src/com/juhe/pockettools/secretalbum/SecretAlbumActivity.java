package com.juhe.pockettools.secretalbum;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecretAlbumActivity extends FullscreenActivity {
	public static final String a = "com.fotoable.helpr.wallpaper.IMAGES";
	public static final String b = "com.fotoable.helpr.wallpaper.IMAGE_POSITION";
	private static final String e = "SecretAlbumActivity";
	private static final int RESULT_CRMERA_IMAGE = 3023;
	private static final int RESULT_LOAD_IMAGE = 3021;
	private static final String u = "secrect_edit_mode";
	// protected com.nostra13.universalimageloader.core.d c =
	// com.nostra13.universalimageloader.core.d
	// .a();
	protected GridViewAdapter adapter = null;
	private GridView gridview;
	private ArrayList<m> list = new ArrayList<m>();
	// private com.nostra13.universalimageloader.core.c h;
	private Button btn_back;
	private FrameLayout btn_edit;
	private TextView txtEdit;
	private Button btn_gallery;
	private Button btn_camera;
	private Button btn_delete;
	private Button btn_decrypt;
	private LinearLayout addLayout;
	private LinearLayout editLayout;
	private boolean editmode = false;

	// private void a(int paramInt) {
	// Intent localIntent = new Intent(this, ImagePagerActivity.class);
	// localIntent.putExtra("com.fotoable.helpr.wallpaper.IMAGES", b());
	// localIntent.putExtra("com.fotoable.helpr.wallpaper.IMAGE_POSITION",
	// paramInt);
	// startActivity(localIntent);
	// }
	//
	// private void a(String paramString, boolean paramBoolean) {
	// if (paramString == null) {
	// }
	// do {
	// n localn;
	// do {
	// return;
	// FlurryAgent.logEvent("SecretAlbumActivity_加密图片");
	// localn = l.a().a(paramString, paramBoolean);
	// } while (localn == null);
	// m localm = new m(localn);
	// list.add(0, localm);
	// } while (adapter == null);
	// adapter.notifyDataSetChanged();
	// }
	//
	private void setButtonVisibility(boolean paramBoolean) {
		if (paramBoolean) {
			txtEdit.setText("取消");
			Animation localAnimation3 = AnimationUtils.loadAnimation(this,
					R.anim.online_push_up_out);
			addLayout.startAnimation(localAnimation3);
			addLayout.setVisibility(View.INVISIBLE);
			Animation localAnimation4 = AnimationUtils.loadAnimation(this,
					R.anim.online_push_up_in);
			editLayout.startAnimation(localAnimation4);
			editLayout.setVisibility(View.VISIBLE);
			return;
		}
		txtEdit.setText("编辑");
		Animation localAnimation1 = AnimationUtils.loadAnimation(this,
				R.anim.online_push_up_out);
		editLayout.startAnimation(localAnimation1);
		editLayout.setVisibility(View.INVISIBLE);
		Animation localAnimation2 = AnimationUtils.loadAnimation(this,
				R.anim.online_push_up_in);
		addLayout.startAnimation(localAnimation2);
		addLayout.setVisibility(View.VISIBLE);
	}

	//
	// private String[] b() {
	// String[] arrayOfString = new String[list.size()];
	// for (int i1 = 0;; i1++) {
	// if (i1 >= list.size()) {
	// return arrayOfString;
	// }
	// arrayOfString[i1] = Uri.fromFile(
	// new File(((m) list.get(i1)).b().e())).toString();
	// }
	// }
	//
	// private void d() {
	// int i1 = 0;
	// boolean bool;
	// Iterator localIterator;
	// if (editmode) {
	// bool = false;
	// editmode = bool;
	// a(editmode);
	// if (!editmode) {
	// localIterator = list.iterator();
	// label39: if (localIterator.hasNext()) {
	// break label100;
	// }
	// if (gridview != null) {
	// Log.v("SecretAlbumActivity",
	// "SecretAlbumActivitygridview childCount: "
	// + gridview.getChildCount());
	// }
	// }
	// }
	// for (;;) {
	// if (i1 > gridview.getChildCount()) {
	// return;
	// bool = true;
	// break;
	// label100: ((m) localIterator.next()).a(false);
	// break label39;
	// }
	// View localView = gridview.getChildAt(i1);
	// if (localView != null) {
	// Object localObject = localView.getTag();
	// if ((localObject != null) && ((localObject instanceof b))) {
	// ((b) localObject).c.setVisibility(4);
	// }
	// }
	// i1++;
	// }
	// }

	private void getGallery() {
		Intent localIntent = new Intent();
		localIntent.setType("image/*");
		File localFile = new File(Environment
				.getExternalStorageDirectory().getPath() + "/.tmp/");
		localFile.mkdirs();
		Uri localUri = Uri.fromFile(new File(localFile, System.currentTimeMillis() + ".jpg"));
		localIntent.putExtra("output", localUri);
		localIntent.setAction("android.intent.action.GET_CONTENT");
		try {
			startActivityForResult(localIntent, RESULT_LOAD_IMAGE);
			return;
		} catch (ActivityNotFoundException localActivityNotFoundException) {
			Toast.makeText(this, R.string.photoPickerNotFoundText1, 1).show();
		}
	}

	private void getCamera() {
		try {
			if ("mounted".equals(Environment.getExternalStorageState())) {
				File localFile = new File(Environment
						.getExternalStorageDirectory().getPath() + "/.tmp/");
				localFile.mkdirs();
				Uri localUri = Uri.fromFile(new File(localFile, System.currentTimeMillis() + ".jpg"));
				Intent localIntent = new Intent(
						"android.media.action.IMAGE_CAPTURE");
				localIntent.putExtra("output", localUri);
				startActivityForResult(localIntent, RESULT_CRMERA_IMAGE);
				return;
			}
			new AlertDialog.Builder(this)
					.setMessage(getResources().getString(R.string.no_sd_card))
					.setCancelable(true).create().show();
			return;
		} catch (ActivityNotFoundException localActivityNotFoundException) {
			Toast.makeText(this, R.string.photoPickerNotFoundText, 1).show();
		}
	}

	// private void g() {
	// ArrayList localArrayList = new ArrayList();
	// Iterator localIterator1 = list.iterator();
	// Iterator localIterator2;
	// if (!localIterator1.hasNext()) {
	// if (localArrayList.size() > 0) {
	// a("处理中");
	// localIterator2 = localArrayList.iterator();
	// }
	// }
	// for (;;) {
	// if (!localIterator2.hasNext()) {
	// adapter.notifyDataSetChanged();
	// a();
	// return;
	// m localm1 = (m) localIterator1.next();
	// if (!localm1.a()) {
	// break;
	// }
	// localArrayList.add(localm1);
	// break;
	// }
	// m localm2 = (m) localIterator2.next();
	// String str = localm2.b().e();
	// int i1 = localm2.b().a();
	// if (l.a().a(i1, str)) {
	// list.remove(localm2);
	// }
	// }
	// }

	// private void h() {
	// FlurryAgent.logEvent("SecretAlbumActivity_解密图片");
	// ArrayList localArrayList = new ArrayList();
	// Iterator localIterator1 = list.iterator();
	// Iterator localIterator2;
	// if (!localIterator1.hasNext()) {
	// if (localArrayList.size() > 0) {
	// localIterator2 = localArrayList.iterator();
	// }
	// }
	// for (;;) {
	// if (!localIterator2.hasNext()) {
	// adapter.notifyDataSetChanged();
	// a();
	// return;
	// m localm1 = (m) localIterator1.next();
	// if (!localm1.a()) {
	// break;
	// }
	// localArrayList.add(localm1);
	// break;
	// }
	// m localm2 = (m) localIterator2.next();
	// String str1 = localm2.b().e();
	// String str2 = localm2.b().d();
	// int i1 = localm2.b().a();
	// String str3 = l.a().a(i1, str1, str2);
	// if ((str3 != null) && (str3.length() > 0)) {
	// list.remove(localm2);
	// }
	// }
	// }
	//
	// protected void a() {
	// }
	//
	// protected void a(String paramString) {
	// }
	//
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);  
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {  
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };  
   
            Cursor cursor = getContentResolver().query(selectedImage,  
                    filePathColumn, null, null, null);  
            cursor.moveToFirst();  
   
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
            String picturePath = cursor.getString(columnIndex);  
            cursor.close();  
   
//            ImageView imageView = (ImageView) findViewById(R.id.imgView);  
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));  
   
        }
		

//		boolean bool = false;
//		Uri localUri;
//		switch (paramInt1) {
//		case 3022:
//		default:
//			localUri = null;
//		}
//		while (localUri != null) {
//			Log.v("SecretAlbumActivity",
//					"SecretAlbumActivityselected image uri:"
//							+ localUri.toString());
//			String str = l.b(localUri);
//			Log.v("SecretAlbumActivity",
//					"SecretAlbumActivityselected image filepath:" + str);
//			a(str, bool);
//			return;
//			if (paramIntent == null) {
//				Toast.makeText(this, "Load photo from gallery failed", 1)
//						.show();
//				return;
//			}
//			localUri = paramIntent.getData();
//			bool = false;
//			continue;
//			localUri = Uri.fromFile(new File(Environment
//					.getExternalStorageDirectory().getPath()
//					+ "/.tmp/capture.jpg"));
//			bool = true;
//		}
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_secretalbum_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundResource(Config
				.getBgDrawableResId());
		ArrayList localArrayList = null;//l.a().c();
//		if (localArrayList != null) {
//			list.clear();
//		}
//		for (int i1 = 0;; i1++) {
//			if (i1 >= localArrayList.size()) {
//				m localm = new m((n) localArrayList.get(i1));
//				list.add(localm);
//			}
//		}

		// this.h = new c.a().a(null).b(null).c(null).b(true).d(false)
		// .a(true).e(true).a(Bitmap.Config.RGB_565).d();
		addLayout = ((LinearLayout) findViewById(R.id.addLayout));
		editLayout = ((LinearLayout) findViewById(R.id.editLayout));
		gridview = ((GridView) findViewById(R.id.gridview));
		adapter = new GridViewAdapter();
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

			}
		});
		btn_back = ((Button) findViewById(R.id.btn_back));
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		txtEdit = ((TextView) findViewById(R.id.txtEdit));
		btn_edit = ((FrameLayout) findViewById(R.id.btn_edit));
		btn_edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btn_gallery = ((Button) findViewById(R.id.btn_gallery));
		btn_gallery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getGallery();
			}
		});
		btn_camera = ((Button) findViewById(R.id.btn_camera));
		btn_camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getCamera();
			}
		});
		btn_delete = ((Button) findViewById(R.id.btn_delete));
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btn_decrypt = ((Button) findViewById(R.id.btn_decrypt));
		btn_decrypt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		if ((bundle != null) && (bundle.containsKey("secrect_edit_mode"))) {
			editmode = bundle.getBoolean("secrect_edit_mode");
			setButtonVisibility(editmode);
		}
		HashMap<String, String> localHashMap = new HashMap<String, String>();
		localHashMap.put("itemName", "SecretAlbumItemUseTime");
		return;
	}

	// protected void onDestroy() {
	// l.b();
	// com.nostra13.universalimageloader.core.d.a().d();
	// HashMap localHashMap = new HashMap();
	// localHashMap.put("itemName", "SecretAlbumItemUseTime");
	// FlurryAgent.endTimedEvent("SecretAlbumItemUseTime", localHashMap);
	// super.onDestroy();
	// }

	@Override
	public void onSaveInstanceState(Bundle paramBundle) {
		super.onSaveInstanceState(paramBundle);
		paramBundle.putBoolean("secrect_edit_mode", editmode);
	}

	protected void onStart() {
		super.onStart();
	}

	public class GridViewAdapter extends BaseAdapter {
		public GridViewAdapter() {
		}

		public String getImageFileName(int position) {
			m entity = (m) list.get(position);
			if (entity != null) {
				return Uri.fromFile(new File(entity.b().e())).toString();
			}
			return null;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int index) {
			return list.get(index);
		}

		@Override
		public long getItemId(int index) {
			return index;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			m entity = (m) getItem(position);
			ViewHolder holder;
			if (convertView == null) {
				convertView = SecretAlbumActivity.this.getLayoutInflater()
						.inflate(R.layout.view_secretalbum_grid_item, parent,
								false);
				holder = new ViewHolder();

				// if ((!b) && (paramView == null)) {
				// throw new AssertionError();
				// }
				holder.image = ((ImageView) convertView
						.findViewById(R.id.image));
				holder.progress = ((ProgressBar) convertView
						.findViewById(R.id.progress));
				holder.checkbox = ((Button) convertView
						.findViewById(R.id.checkbox));
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// label194: for (int i = 0;; i = 4) {
			// localButton.setVisibility(i);
			// SecretAlbumActivity.this.c.a(a(paramInt), localb1.a,
			// SecretAlbumActivity.b(SecretAlbumActivity.this), new i(
			// this, localb1), new j(this, localb1));
			// return paramView;
			// localb1 = (SecretAlbumActivity.b) paramView.getTag();
			// break;
			// }
			return convertView;
		}
	}

	static class ViewHolder {
		ImageView image;
		ProgressBar progress;
		Button checkbox;
	}
}
