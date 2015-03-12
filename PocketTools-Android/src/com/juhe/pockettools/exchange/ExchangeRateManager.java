package com.juhe.pockettools.exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExchangeRateManager {
	private Map<String, String> map = null;
	private static ExchangeRateManager instance = null;
	private ArrayList<ExChangeEntity> list = null;

	private void initMap() {
		if (map == null) {
			map = new HashMap<String, String>();
		}
		map.put("人民币", "cn,CNY,¥");
		map.put("英镑", "gb,GBP,£");
		map.put("港币", "hk,HKD,$");
		map.put("美元", "us,USD,$");
		map.put("瑞士法郎", "ch,CHF,Fr");
		map.put("新加坡元", "sg,SGD,$");
		map.put("瑞典克朗", "se,SEK,kr");
		map.put("丹麦克朗", "dk,DKK,kr");
		map.put("挪威克朗", "no,NOK,kr");
		map.put("日元", "jp,JPY,¥");
		map.put("加拿大元", "ca,CAD,$");
		map.put("澳大利亚元", "au,AUD,$");
		map.put("澳门元", "mo,MOP,P");
		map.put("菲律宾比索", "ph,PHP,₱");
		map.put("泰国铢", "th,THB,฿");
		map.put("新西兰元", "nz,NZD,$");
		map.put("韩国元", "kr,KRW,₩");
		map.put("欧元", "eu,EUR,€");
	}

	public synchronized static ExchangeRateManager getInstance() {
		if (instance == null) {
			instance = new ExchangeRateManager();
		}
		return instance;
	}

	public static void clear() {
		if (instance != null) {
			instance = null;
		}
	}

	private ExChangeEntity getEntityForName(String name) {
		for (int i = 0; i < list.size(); i++) {
			ExChangeEntity entity = (ExChangeEntity) list.get(i);
			if (entity.name.equalsIgnoreCase(name)) {
				return entity;
			}
		}
		return null;
	}

	public ArrayList<ExChangeEntity> formatList(String json) {
		initMap();
		
		if (list == null) {
			list = new ArrayList<ExChangeEntity>();
		} else {
			list.clear();
		}
		
		try {
			addRMB();
			JSONArray datalist = new JSONObject(json).getJSONArray("result");
			JSONObject data1 = datalist.getJSONObject(0);
			for (int i = 1; i <= 17; i++) {
				ExChangeEntity entity = new ExChangeEntity();
				
				JSONObject data = data1.getJSONObject("data" + i);
				entity.name = data.getString("name");
				entity.fBuyPri = Float.parseFloat(data.getString("fBuyPri"));
				entity.mBuyPri = Float.parseFloat(data.getString("mBuyPri"));
				entity.fSellPri = Float.parseFloat(data.getString("fSellPri"));
				entity.mSellPri = Float.parseFloat(data.getString("mSellPri"));
				entity.bankConversionPri = Float.parseFloat(data.getString("bankConversionPri"));
				String[] array = ((String) map.get(entity.name)).split(",");
				if (array.length >= 3) {
					entity.cn = array[0];
					entity.code = array[1];
					entity.¥ = array[2];
				}
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	private void addRMB() {
		String[] array = ((String) map.get("人民币")).split(",");
		ExChangeEntity entity = getEntityForName("人民币");
		if (entity == null) {
			entity = new ExChangeEntity();
		}
		entity.name = "人民币";
		entity.fBuyPri = 100.0F;
		entity.mBuyPri = 100.0F;
		entity.fSellPri = 100.0F;
		entity.mSellPri = 100.0F;
		entity.bankConversionPri = 100.0F;
		if (array.length >= 3) {
			entity.cn = array[0];
			entity.code = array[1];
			entity.¥ = array[2];
		}
		list.add(entity);
	}
}
