package com.juhe.pockettools.qrcode;

import java.io.Serializable;
import java.util.List;

public class QRCodeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private Result result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {
		private Summary summary;
		private List<Shop> shop;

		public Summary getSummary() {
			return summary;
		}

		public void setSummary(Summary summary) {
			this.summary = summary;
		}

		public List<Shop> getShop() {
			return shop;
		}

		public void setShop(List<Shop> shop) {
			this.shop = shop;
		}

	}

	public class Summary {
		private String barcode;
		private String name;
		private String imgurl;
		private String shopNum;
		private String eshopNum;
		private String interval;

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImgurl() {
			return imgurl;
		}

		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}

		public String getShopNum() {
			return shopNum;
		}

		public void setShopNum(String shopNum) {
			this.shopNum = shopNum;
		}

		public String getEshopNum() {
			return eshopNum;
		}

		public void setEshopNum(String eshopNum) {
			this.eshopNum = eshopNum;
		}

		public String getInterval() {
			return interval;
		}

		public void setInterval(String interval) {
			this.interval = interval;
		}

	}

	public class Shop {
		private String price;
		private String shopname;

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getShopname() {
			return shopname;
		}

		public void setShopname(String shopname) {
			this.shopname = shopname;
		}

	}
}
