package com.juhe.pockettools.oil;

import java.io.Serializable;
import java.util.List;

public class OilEntity implements Serializable {

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
//	{"resultcode":"200","reason":"Successed!","result":
//	{"data":[{"id":"43630","name":"金浙加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市海虞北路与外环北路交汇处的北方，纺装厂住宅区附近。","brandname":"不详","type":"其他",
//		"discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.7547,31.68258","lon":"120.76122576038","lat":"31.688492430662","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},
//		"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"","distance":2034},
//		{"id":"18527","name":"中石油胜利加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省常熟市谢桥胜利村常福公路路段。","brandname":"中石油", "type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.754734,31.696392","lon":"120.76127196778","lat":"31.702306964113","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"加油卡","distance":2619},
//			{"id":"17487","name":"中石化常熟公交加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市海虞北路58号。","brandname":"中石化"," "type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.756197,31.675782","lon":"120.76272305775","lat":"31.681665393404","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"加油卡","distance":1993},
//					{"id":"4160","name":"加油站（湘江东路）","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市湘江东路与海虞北路交汇处的东方，建设银行附近。","brandname":"中石油","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.756665,31.661053","lon":"120.76318128258","lat":"31.666924879807","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.25","0#":"5.76"},"fwlsmc":"加油卡","distance":2932},
//						{"id":"21422","name":"中石油湘江加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市湘江东路，常熟市实验中学旁。","brandname":"中石油","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.7501410372,31.6551512218","lon":"120.76504279905","lat":"31.666933487995","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","95#":"6.7"},"fwlsmc":"银联卡,信用卡支付,加油卡,便利店,发卡充值网点,银联卡充值,加油卡充值业务","distance":2823},
//						{"id":"18056","name":"中石油长江加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address" :"江苏省苏州市常熟市虞山镇长江路与泰山北路交叉路口。","brandname":"中石油","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.76704,31.67028","lon":"120.77360453575","lat":"31.675995490247","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3"},"fwlsmc":"银联卡,信用卡支付,加油卡,便利店,银联卡充值,加油卡充值业务","distance":1531},
//						{"id":"53531","name":"中石油长江加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市虞山镇长江路与泰山北路交叉路口的东北方","brandname":"中石油","type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.767046,31.670257","lon":"120.77361053409","lat":"31.675972413471","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3"},"fwlsmc":"加油卡,便利店,发卡充值网点,加油卡充值业务","distance":1533},
//						{"id":"18058","name":"中石油金隆加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市长江路,泰山路口。","brandname":"中石油","type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.77373798475,31.676033922394","lon":"120.77373798475","lat":"31.676033922394","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.6","0#":"5.81"},"fwlsmc":"加油卡","distance":1520},
//						{"id":"6354","name":"中石油开发加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市204国道黄河路口，s227与g204交叉口。","brandname":"中石油","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.779627,31.679868","lon":"120.78622188723","lat":"31.685505695009","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","95#":"6.7","0#":"5.76"},"fwlsmc":"银联卡,信用卡支付,加油卡,便利店,发卡充值网点","distance":398},
//						{"id":"7006","name":"中石化常熟第五加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市204国道常熟杨荡村。","brandname":"中石化","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.784737,31.67102","lon":"120.79132034014","lat":"31.676666436108","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","95#":"6.7","0#":"5.76"},"fwlsmc":"加油卡,便利店,加油卡充值业务","distance":1449},
//						{"id":"6855","name":"中石化常熟第六加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市长江路与204国道交叉口的东北方向，红华服装厂附近。","brandname":"中石化","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.778156065,31.6653973722","lon":"120.79132630926","lat":"31.676640457901","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","95#":"6.7","0#":"5.76"},"fwlsmc":"加油卡,便利店","distance":1452},
//						{"id":"42787","name":"加油站（昭文路）","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市昭文路与外环东路交汇处的东边，新区小学附近。","brandname":"中石化","type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.78849,31.668591","lon":"120.7950648901","lat":"31.674260999522","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"加油卡","distance":1876},
//						{"id":"93689","name":"中石油盛家宅基加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市三环东路盛家宅基路段路西","brandname":"中石油","type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.7912233223,31.6598283835","lon":"120.7977852062","lat":"31.665522998938","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"加油卡","distance":2825},
//						{"id":"4165","name":"龙腾加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市通港路与外环北路交汇处的东北方，龙潭村附近。","brandname":"中石化","type":"其他","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.798131,31.686954","lon":"120.80469323568","lat":"31.692744812935","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","0#":"5.76"},"fwlsmc":"","distance":2171},
//						{"id":"6414","name":"中石油淼泉加油站","area":"215128","areaname":"江苏省 苏州市 常熟市","address":"江苏省苏州市常熟市淼泉镇祝家桥下。","brandname":"中石油","type":"直营店","discount":"非打折加油站","exhaust":"苏Ⅴ","position":"120.807059,31.673522","lon":"120.81356713764","lat":"31.679464723221","price":{"E90":"5.77","E93":"6.12","E97":"6.47","E0":"5.76"},"gastprice":{"92#":"6.3","95#":"6.7","0#":"5.76"},"fwlsmc":"银联卡,信用卡支付,加油卡,便利店","distance":3058}],
//						"pageinfo":{"pnums":20,"current":1,"allpage":1}},"error_code":0}
	public class Result {
		private List<Data> data;

		public List<Data> getData() {
			return data;
		}

		public void setData(List<Data> data) {
			this.data = data;
		}
	}
	
	public class Data {
		private int id;
		private String name;
		private String area;
		private String areaname;
		private String address;
		private String brandname;
		private String type;
		private String discount;
		private String exhaust;
		private String position;
		private String lon;
		private String lat;
		private Price price;
//		private String gastprice;
		private String fwlsmc;
		private String distance;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getAreaname() {
			return areaname;
		}
		public void setAreaname(String areaname) {
			this.areaname = areaname;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getBrandname() {
			return brandname;
		}
		public void setBrandname(String brandname) {
			this.brandname = brandname;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getDiscount() {
			return discount;
		}
		public void setDiscount(String discount) {
			this.discount = discount;
		}
		public String getExhaust() {
			return exhaust;
		}
		public void setExhaust(String exhaust) {
			this.exhaust = exhaust;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getLon() {
			return lon;
		}
		public void setLon(String lon) {
			this.lon = lon;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public Price getPrice() {
			return price;
		}
		public void setPrice(Price price) {
			this.price = price;
		}
		public String getFwlsmc() {
			return fwlsmc;
		}
		public void setFwlsmc(String fwlsmc) {
			this.fwlsmc = fwlsmc;
		}
		public String getDistance() {
			return distance;
		}
		public void setDistance(String distance) {
			this.distance = distance;
		}
		
		
	}
	
	public class Price {
		private String E90;
		private String E93;
		private String E97;
		private String E0;
		public String getE90() {
			return E90;
		}
		public void setE90(String e90) {
			E90 = e90;
		}
		public String getE93() {
			return E93;
		}
		public void setE93(String e93) {
			E93 = e93;
		}
		public String getE97() {
			return E97;
		}
		public void setE97(String e97) {
			E97 = e97;
		}
		public String getE0() {
			return E0;
		}
		public void setE0(String e0) {
			E0 = e0;
		}
		
		
	}
}
