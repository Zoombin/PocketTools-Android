package com.juhe.pockettools.secretalbum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
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
import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SecretAlbumActivity extends FullscreenActivity {
	private static final String TAG = "SecretAlbumActivity";
	private static final int RESULT_CRMERA_IMAGE = 3023;
	private static final int RESULT_LOAD_IMAGE = 3021;
	protected GridViewAdapter adapter = null;
	private GridView gridview;
	private ArrayList<SecretEntity> list = new ArrayList<SecretEntity>();

	private Button btn_back;
	private FrameLayout btn_edit;
	private TextView txtEdit;
	private Button btn_gallery;
	private Button btn_camera;
	private Button btn_delete;
	private Button btn_decrypt;
	private LinearLayout addLayout;
	private LinearLayout editLayout;
	private boolean viewmode = true;

	private String filepath = Environment.getExternalStorageDirectory()
			.getPath() + "/.tmp/";

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
			viewmode = !paramBoolean;
			return;
		}
		
		getFileDir(filepath);
		txtEdit.setText("编辑");
		Animation localAnimation1 = AnimationUtils.loadAnimation(this,
				R.anim.online_push_up_out);
		editLayout.startAnimation(localAnimation1);
		editLayout.setVisibility(View.INVISIBLE);
		Animation localAnimation2 = AnimationUtils.loadAnimation(this,
				R.anim.online_push_up_in);
		addLayout.startAnimation(localAnimation2);
		addLayout.setVisibility(View.VISIBLE);
		viewmode = !paramBoolean;
	}

	private void getGallery() {
		Intent localIntent = new Intent();
		localIntent.setType("image/*");
		File localFile = new File(filepath);
		localFile.mkdirs();
		Uri localUri = Uri.fromFile(new File(localFile, System
				.currentTimeMillis() + ".jpg"));
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
				Uri localUri = Uri.fromFile(new File(localFile, System
						.currentTimeMillis() + ".jpg"));
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

	public void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}
	}

	public void getFileDir(String filePath) {
        try{
        	if (list != null) {
        		list.clear();
        	}
            File f = new File(filePath);  
            File[] files = f.listFiles();// 列出所有文件  
//            // 如果不是根目录,则列出返回根目录和上一目录选项  
//            if (!filePath.equals(rootPath)) {  
//                items.add("返回根目录");  
//                paths.add(rootPath);  
//                items.add("返回上一层目录");  
//                paths.add(f.getParent());  
//            }
            // 将所有文件存入list中  
            if(files != null){  
                int count = files.length;// 文件个数  
                for (int i = 0; i < count; i++) {  
                    File file = files[i];
                    SecretEntity entity = new SecretEntity();
                    entity.setFilename(filepath + file.getName());
                    list.add(entity);
                }
            }  
  
            if (adapter != null) {
            	adapter.notifyDataSetChanged();
            }

        }catch(Exception ex){  
            ex.printStackTrace();  
        }	
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			copyFile(picturePath, filepath + System.currentTimeMillis()
					+ ".jpg");
		}
		getFileDir(filepath);
	}

	@SuppressLint("NewApi")
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_secretalbum_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));

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
				setButtonVisibility(viewmode);
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
				for (SecretEntity entity : list) {
					if (entity.isChecked()) {
						File f = new File(entity.getFilename());
						f.delete();
					}
				}
				getFileDir(filepath);
			}
		});
//		btn_decrypt = ((Button) findViewById(R.id.btn_decrypt));
//		btn_decrypt.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//			}
//		});

		getFileDir(filepath);
	}

	protected void onStart() {
		super.onStart();
	}

	public class GridViewAdapter extends BaseAdapter {
		public GridViewAdapter() {
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			SecretEntity entity = (SecretEntity) getItem(position);
			final ViewHolder holder;
			if (convertView == null) {
				convertView = SecretAlbumActivity.this.getLayoutInflater()
						.inflate(R.layout.view_secretalbum_grid_item, parent,
								false);
				holder = new ViewHolder();

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

			holder.progress.setVisibility(View.GONE);
			ImageLoader.getInstance().displayImage("file://" + entity.getFilename(), holder.image);
			holder.checkbox.setVisibility(View.GONE);
			holder.image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (viewmode) {
						Intent intent = new Intent(SecretAlbumActivity.this, ImagePagerActivity.class);
						intent.putExtra("list", list);
						intent.putExtra(ImagePagerActivity.EXTRA_STATE_POSITION, position);
						startActivity(intent);
					} else {
						boolean ischecked = list.get(position).isChecked();
						if (!ischecked) { 
							holder.checkbox.setVisibility(View.VISIBLE);
						} else {
							holder.checkbox.setVisibility(View.GONE);
						}
						list.get(position).setChecked(!ischecked);
					}
				}
			});
			return convertView;
		}
	}

	static class ViewHolder {
		ImageView image;
		ProgressBar progress;
		Button checkbox;
	}
}
