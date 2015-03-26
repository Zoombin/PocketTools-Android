package com.juhe.pockettools.secretalbum;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.gallery.FilePagerAdapter;
import com.juhe.pockettools.commonview.gallery.GalleryViewPager;
import com.juhe.pockettools.home.FullscreenActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ImagePagerActivity extends FullscreenActivity {
	private static final String EXTRA_STATE_POSITION = "STATE_POSITION";
//	protected DisplayImageOptions defaultOptions = com.nostra13.universalimageloader.core.d
//			.a();
//	private c d;
	private GalleryViewPager pager;
	private Button cancelbutton;

//	static {
//		if (!ImagePagerActivity.class.desiredAssertionStatus()) {
//		}
//		for (boolean bool = true;; bool = false) {
//			b = bool;
//			return;
//		}
//	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_secret_pager);
		Bundle localBundle = getIntent().getExtras();
//		if ((!b) && (localBundle == null)) {
//			throw new AssertionError();
//		}
		String[] arrayOfString = localBundle
				.getStringArray("com.fotoable.helpr.wallpaper.IMAGES");
		int i = localBundle.getInt(
				"com.fotoable.helpr.wallpaper.IMAGE_POSITION", 0);
//		if (bundle == null) {
//			for (int j = paramBundle.getInt("STATE_POSITION");; j = i) {
//				this.d = new new DisplayImageOptions.Builder().c(2130837898).d(2130837899).a(true).d(false)
//						.a(com.nostra13.universalimageloader.core.a.d.e)
//						.a(Bitmap.Config.RGB_565).e(true)
//						.a(new com.nostra13.universalimageloader.core.c.b(300)).d();
//				pager = ((GalleryViewPager) findViewById(R.id.pager));
//				ArrayList localArrayList = new ArrayList();
//				for (int k = 0;; k++) {
//					if (k >= arrayOfString.length) {
//						this.e.setAdapter(new FilePagerAdapter(this,
//								localArrayList, this.d));
//						this.e.setCurrentItem(j);
//						cancelbutton = ((Button) findViewById(R.id.cancelbutton));
//						cancelbutton.setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								finish();
//							}
//						});
//						return;
//					}
//					com.fotoable.helpr.commonview.gallery.b localb = new com.fotoable.helpr.commonview.gallery.b();
//					localb.a(arrayOfString[k]);
//					localArrayList.add(localb);
//				}
//			}
//		}
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		bundle.putInt(EXTRA_STATE_POSITION, pager.getCurrentItem());
	}
}
