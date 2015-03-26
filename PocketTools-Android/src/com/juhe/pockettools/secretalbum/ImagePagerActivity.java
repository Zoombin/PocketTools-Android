package com.juhe.pockettools.secretalbum;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.gallery.FilePagerAdapter;
import com.juhe.pockettools.commonview.gallery.GalleryViewPager;
import com.juhe.pockettools.home.FullscreenActivity;

public class ImagePagerActivity extends FullscreenActivity {
	public static final String EXTRA_STATE_POSITION = "STATE_POSITION";

	private GalleryViewPager pager;
	private Button cancelbutton;
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_secret_pager);

		List<SecretEntity> list = (List<SecretEntity>) getIntent().getSerializableExtra("list");
		List<String> list1 = new ArrayList<String>();
		for (SecretEntity entity : list) {
			list1.add(entity.getFilename());
		}
		int position = getIntent().getIntExtra(EXTRA_STATE_POSITION, 0);
		
		cancelbutton = (Button) findViewById(R.id.cancelbutton);
		cancelbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		pager = (GalleryViewPager) findViewById(R.id.pager);
		pager.setAdapter(new FilePagerAdapter(this, list1));
		pager.setCurrentItem(position);
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		bundle.putInt(EXTRA_STATE_POSITION, pager.getCurrentItem());
	}
}
