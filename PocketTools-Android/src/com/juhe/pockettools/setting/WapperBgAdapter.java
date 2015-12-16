package com.juhe.pockettools.setting;

import java.util.List;

import com.juhe.pockettools.R;

import android.R.array;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class WapperBgAdapter extends BaseAdapter {
	StatusImageView a;
	// d b = null;
	List<WapperBgEntity> list;
	// HashMap<Integer, View> d = new HashMap();
	private final Context context;
	private LayoutInflater layoutinflater;
	public int selectedindex = -1;

	// private c h;

	public WapperBgAdapter(Context context, List<WapperBgEntity> list) {
		this.context = context;

		this.list = list;
		// this.h = new c.a().a(null).b(null).c(null).b(true).d(false).a(true)
		// .e(true).a(Bitmap.Config.RGB_565).d();
		layoutinflater = ((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	public void setSelected(int selectedindex) {
		this.selectedindex = selectedindex;
		notifyDataSetChanged();
	}

	// private void a(m paramm, a parama) {
	// if ((paramm == null) || (this.b == null)) {
	// return;
	// }
	// String str1 = "";
	// if (paramm.a == o.a) {
	// str1 = "assets://" + paramm.b;
	// }
	// while ((str1 != null) && (str1.length() > 0)) {
	// parama.b.a();
	// d.a().a(str1, parama.b, this.h, null, null);
	// return;
	// if (paramm.a == o.c) {
	// String str2 = w.a().b(paramm.b);
	// str1 = "file:///" + str2;
	// }
	// }
	// parama.b.setImageBitmap(null);
	// }

	// public void clearData() {
	// this.d.clear();
	// notifyDataSetChanged();
	// }
	//
	// public void a(int paramInt) {
	// this.g = paramInt;
	// View localView = (View) this.d.get(Integer.valueOf(paramInt));
	// if (localView != null) {
	// a locala = (a) localView.getTag();
	// if (locala != null) {
	// StatusImageView localStatusImageView = locala.b;
	// if (this.a != null) {
	// this.a.setIsSelected(false);
	// }
	// localStatusImageView.setIsSelected(true);
	// this.a = localStatusImageView;
	// }
	// }
	// }
	//
	// public void setData(ArrayList<m> list) {
	// this.list = list;
	// this.d.clear();
	// notifyDataSetChanged();
	// }

	public int getCount() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	public Object getItem(int paramInt) {
		return list.get(paramInt);
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}

	public int getItemViewType(int paramInt) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final WapperBgEntity entity = list.get(position);

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutinflater.inflate(
					R.layout.view_wallpaperbg_item, parent, false);
			holder.item_icon = (StatusImageView) convertView
					.findViewById(R.id.item_icon);
			// item_icon.setTag(localm);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position == selectedindex) {
			holder.item_icon.setIsSelected(true);
		} else {
			holder.item_icon.setIsSelected(false);
		}
//		if (entity.getBg() != 0) {
//			holder.item_icon.setBackgroundColor(entity.getBg());
			holder.item_icon.setBackgroundResource(entity.getBg());
//		} else if (entity.getBg() == 0 && entity.getBgDrawable() != null) {
//			holder.item_icon.setBackgroundDrawable(entity.getBgDrawable());
//		}

		// try {
		// m localm = (m) getItem(paramInt);
		// Object localObject2;
		// View localView2;
		// if (paramView == null) {
		// paramView = layoutinflater.inflate(R.layout.view_wallpaperbg_item,
		// paramViewGroup,
		// false);
		// StatusImageView item_icon = (StatusImageView) paramView
		// .findViewById(R.id.item_icon);
		// localStatusImageView.setTag(localm);
		// localObject2 = new a(null);
		// ((a) localObject2).b = localStatusImageView;
		// paramView.setTag(localObject2);
		// localView2 = paramView;
		// }
		// try {
		// if (paramInt == this.g) {
		// ((a) localObject2).b.setIsSelected(true);
		// this.a = ((a) localObject2).b;
		// }
		// for (;;) {
		// a(localm, (a) localObject2);
		// this.d.put(Integer.valueOf(paramInt), localView2);
		// return localView2;
		// a locala = (a) paramView.getTag();
		// locala.b.setImageBitmap(null);
		// locala.b.setTag(localm);
		// localObject2 = locala;
		// localView2 = paramView;
		// break;
		// ((a) localObject2).b.setIsSelected(false);
		// }
		// ((Exception) localObject1).printStackTrace();
		// } catch (Exception localException2) {
		// localView1 = localView2;
		// localObject1 = localException2;
		// }
		// } catch (Exception localException1) {
		// for (;;) {
		// Object localObject1 = localException1;
		// View localView1 = paramView;
		// }
		// }
		return convertView;
	}

	public int getViewTypeCount() {
		return 2;
	}

	public boolean hasStableIds() {
		return true;
	}

	private class ViewHolder {
		// public Bitmap bitmap;
		public StatusImageView item_icon;
	}
}
