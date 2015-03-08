package com.juhe.pockettools.secretalbum;

//import android.content.Intent;
//import android.graphics.Bitmap.Config;
//import android.os.Bundle;
//import android.widget.Button;
//import com.fotoable.helpr.commonview.gallery.FilePagerAdapter;
//import com.fotoable.helpr.commonview.gallery.GalleryViewPager;
//import com.fotoable.helpr.home.FullscreenActivity;
//import com.nostra13.universalimageloader.core.c;
//import com.nostra13.universalimageloader.core.c.a;
//import java.util.ArrayList;

public class ImagePagerActivity {
//extends FullscreenActivity {
//	private static final String c = "STATE_POSITION";
//	protected com.nostra13.universalimageloader.core.d a = com.nostra13.universalimageloader.core.d
//			.a();
//	private c d;
//	private GalleryViewPager e;
//	private Button f;
//
//	static {
//		if (!ImagePagerActivity.class.desiredAssertionStatus()) {
//		}
//		for (boolean bool = true;; bool = false) {
//			b = bool;
//			return;
//		}
//	}
//
//	public void onCreate(Bundle paramBundle) {
//		super.onCreate(paramBundle);
//		setContentView(2130903089);
//		Bundle localBundle = getIntent().getExtras();
//		if ((!b) && (localBundle == null)) {
//			throw new AssertionError();
//		}
//		String[] arrayOfString = localBundle
//				.getStringArray("com.fotoable.helpr.wallpaper.IMAGES");
//		int i = localBundle.getInt(
//				"com.fotoable.helpr.wallpaper.IMAGE_POSITION", 0);
//		if (paramBundle != null) {
//		}
//		for (int j = paramBundle.getInt("STATE_POSITION");; j = i) {
//			this.d = new c.a().c(2130837898).d(2130837899).a(true).d(false)
//					.a(com.nostra13.universalimageloader.core.a.d.e)
//					.a(Bitmap.Config.RGB_565).e(true)
//					.a(new com.nostra13.universalimageloader.core.c.b(300)).d();
//			this.e = ((GalleryViewPager) findViewById(2131362058));
//			ArrayList localArrayList = new ArrayList();
//			for (int k = 0;; k++) {
//				if (k >= arrayOfString.length) {
//					this.e.setAdapter(new FilePagerAdapter(this,
//							localArrayList, this.d));
//					this.e.setCurrentItem(j);
//					this.f = ((Button) findViewById(2131362059));
//					this.f.setOnClickListener(new a(this));
//					return;
//				}
//				com.fotoable.helpr.commonview.gallery.b localb = new com.fotoable.helpr.commonview.gallery.b();
//				localb.a(arrayOfString[k]);
//				localArrayList.add(localb);
//			}
//		}
//	}
//
//	public void onSaveInstanceState(Bundle bundle) {
//		bundle.putInt("STATE_POSITION", this.e.getCurrentItem());
//	}
}
