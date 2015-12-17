package com.juhe.pockettools.oil;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

import com.zoombin.koudai.R;
import com.juhe.pockettools.weather.Weather;

public class OilListViewAdapter extends BaseAdapter {
	private LayoutInflater layoutinflater;
	private Context context;
	private List<OilEntity.Data> list;

	public OilListViewAdapter(Context context) {
		this.context = context;
		layoutinflater = LayoutInflater.from(context);
	}

	// private String a(JSONObject paramJSONObject)
	// {
	// String str1 = com.fotoable.b.a.a(paramJSONObject, "discount");
	// String str2 = com.fotoable.b.a.a(paramJSONObject, "exhaust");
	// String str3 = com.fotoable.b.a.a(paramJSONObject, "brandname");
	// String str4 = com.fotoable.b.a.a(paramJSONObject, "type");
	// String str5 = com.fotoable.b.a.a(paramJSONObject, "fwlsmc");
	// if ((str3 != null) && (str3.equals("不详"))) {
	// str3 = "";
	// }
	// if ((str4 != null) && (str4.equals("其他"))) {
	// str4 = "";
	// }
	// StringBuilder localStringBuilder = new StringBuilder("标签：");
	// if ((str1 != null) && (str1.length() > 0))
	// {
	// localStringBuilder.append(str1);
	// localStringBuilder.append(", ");
	// }
	// if ((str2 != null) && (str2.length() > 0))
	// {
	// localStringBuilder.append(str2);
	// localStringBuilder.append(", ");
	// }
	// if ((str3 != null) && (str3.length() > 0))
	// {
	// localStringBuilder.append(str3);
	// localStringBuilder.append(", ");
	// }
	// if ((str4 != null) && (str4.length() > 0))
	// {
	// localStringBuilder.append(str4);
	// localStringBuilder.append(", ");
	// }
	// localStringBuilder.append(str5);
	// return localStringBuilder.toString();
	// }

	// private void a(a parama, JSONObject paramJSONObject)
	// {
	// if ((paramJSONObject == null) || (parama == null)) {
	// return;
	// }
	// parama.a.setText(com.fotoable.b.a.a(paramJSONObject, "name"));
	// parama.c.setText(com.fotoable.b.a.a(paramJSONObject, "address"));
	// Object[] arrayOfObject = new Object[1];
	// arrayOfObject[0] = Float.valueOf(com.fotoable.b.a.f(paramJSONObject,
	// "distance") / 1000.0F);
	// String str = String.format("%.1f公里", arrayOfObject);
	// parama.b.setText(str);
	// parama.d.setText(a(paramJSONObject));
	// b(parama, com.fotoable.b.a.c(paramJSONObject, "gastprice"));
	// }

	// private void b(a parama, JSONObject paramJSONObject)
	// {
	// if ((paramJSONObject == null) || (parama == null)) {}
	// ArrayList localArrayList;
	// Iterator localIterator;
	// label31:
	// do
	// {
	// do
	// {
	// return;
	// } while (paramJSONObject.length() <= 0);
	// localArrayList = new ArrayList(3);
	// localIterator = paramJSONObject.keys();
	// if (localIterator.hasNext()) {
	// break;
	// }
	// } while (localArrayList.size() <= 0);
	// int i = 0;
	// label51:
	// Object localObject3;
	// Object localObject2;
	// if (i < 3)
	// {
	// if (i != 0) {
	// break label173;
	// }
	// TextView localTextView5 = parama.e;
	// TextView localTextView6 = parama.h;
	// localObject3 = localTextView5;
	// localObject2 = localTextView6;
	// }
	// for (;;)
	// {
	// label82:
	// if (localArrayList.size() > i)
	// {
	// String str = (String)localArrayList.get(i);
	// ((TextView)localObject3).setVisibility(0);
	// ((TextView)localObject2).setVisibility(0);
	// ((TextView)localObject3).setText(str);
	// ((TextView)localObject2).setText(com.fotoable.b.a.a(paramJSONObject,
	// str));
	// }
	// for (;;)
	// {
	// i++;
	// break label51;
	// break;
	// Object localObject1 = localIterator.next();
	// if ((localObject1 == null) || (!(localObject1 instanceof String))) {
	// break label31;
	// }
	// localArrayList.add((String)localObject1);
	// break label31;
	// label173:
	// if (i == 1)
	// {
	// TextView localTextView3 = parama.f;
	// TextView localTextView4 = parama.i;
	// localObject3 = localTextView3;
	// localObject2 = localTextView4;
	// break label82;
	// }
	// if (i != 2) {
	// break label246;
	// }
	// TextView localTextView1 = parama.g;
	// TextView localTextView2 = parama.j;
	// localObject3 = localTextView1;
	// localObject2 = localTextView2;
	// break label82;
	// ((TextView)localObject3).setVisibility(4);
	// ((TextView)localObject2).setVisibility(4);
	// }
	// label246:
	// localObject2 = null;
	// localObject3 = null;
	// }
	// }

	public void setData(List<OilEntity.Data> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
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
		OilEntity.Data entity = (OilEntity.Data) list.get(position);
		ViewHolder holder; 
		
		if (convertView == null) {
			convertView = layoutinflater.inflate(R.layout.view_oil_listview_item,
					parent, false);
			holder = new ViewHolder();
			holder.nameLabel = ((TextView) convertView.findViewById(R.id.nameLabel));
			holder.distanceLabel = ((TextView) convertView.findViewById(R.id.distanceLabel));
			holder.addressLabel = ((TextView) convertView.findViewById(R.id.addressLabel));
			holder.fwlsmcLabel = ((TextView) convertView.findViewById(R.id.fwlsmcLabel));
			holder.txt_type1 = ((TextView) convertView.findViewById(R.id.txt_type1));
			holder.txt_type2 = ((TextView) convertView.findViewById(R.id.txt_type2));
			holder.txt_type3 = ((TextView) convertView.findViewById(R.id.txt_type3));
			holder.txt_price1 = ((TextView) convertView.findViewById(R.id.txt_price1));
			holder.txt_price2 = ((TextView) convertView.findViewById(R.id.txt_price2));
			holder.txt_price3 = ((TextView) convertView.findViewById(R.id.txt_price3));
			Typeface typeface = Typeface.createFromAsset(
					context.getAssets(), "fonts/HelveticaNeue-Thin.otf");
			holder.txt_type1.setTypeface(typeface);
			holder.txt_type2.setTypeface(typeface);
			holder.txt_type3.setTypeface(typeface);
			holder.txt_price1.setTypeface(typeface);
			holder.txt_price2.setTypeface(typeface);
			holder.txt_price3.setTypeface(typeface);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

//		[{"id":"43630","name":"金浙加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市海虞北路与外环北路交汇处的北方，纺装厂住宅区附近。","brandname":"不详","type":"其他",
//			"discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.7547,31.68258","lon":"120.76122576038","lat":"31.688492430662","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},
//			"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"","distance":2034},
			
		holder.nameLabel.setText(entity.getName());
		holder.distanceLabel.setText(entity.getDistance() + "米");
		holder.addressLabel.setText(entity.getAddress());
		holder.txt_type1.setText("92#");
		holder.txt_type2.setText("0#");
		holder.txt_type3.setText("95#");
		holder.txt_price1.setText(entity.getPrice().getE93());
		holder.txt_price2.setText(entity.getPrice().getE0());
		holder.txt_price3.setText(entity.getPrice().getE97());
		holder.fwlsmcLabel.setText(entity.getDiscount() + "," + entity.getExhaust() + "," + entity.getBrandname() + "," + entity.getFwlsmc());
		
		return convertView;
	}

	private class ViewHolder {
		public TextView nameLabel;
		public TextView distanceLabel;
		public TextView addressLabel;
		public TextView fwlsmcLabel;
		public TextView txt_type1;
		public TextView txt_type2;
		public TextView txt_type3;
		public TextView txt_price1;
		public TextView txt_price2;
		public TextView txt_price3;
	}
}
