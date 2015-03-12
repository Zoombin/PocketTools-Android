package com.juhe.pockettools.moive;

import java.io.Serializable;
import java.util.List;

public class CinemaEntity implements Serializable {
	// {"resultcode":"200","reason":"success","result":
	// [{"rid":"1","name":"澳门风云2","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥43800","tboxoffice":"￥71521"},
	// {"rid":"2","name":"狼图腾","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥29600","tboxoffice":"￥50402"},
	// {"rid":"3","name":"天将雄师","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥28800","tboxoffice":"￥64100"},
	// {"rid":"4","name":"钟馗伏魔：雪妖魔灵","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥17000","tboxoffice":"￥37164"},
	// {"rid":"5","name":"超能陆战队","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥8800","tboxoffice":"￥8800"},
	// {"rid":"6","name":"爸爸去哪儿2","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥8600","tboxoffice":"￥21870"},
	// {"rid":"7","name":"冲上云霄","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥6500","tboxoffice":"￥14600"},
	// {"rid":"8","name":"爸爸的假期","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥5000","tboxoffice":"￥10700"},
	// {"rid":"9","name":"熊出没之雪岭熊风","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥1800","tboxoffice":"￥29364"},
	// {"rid":"10","name":"有一个地方只有我们知道","wk":"2014.2.23 – 2015.3.1（单位：万元）","wboxoffice":"￥496","tboxoffice":"￥28730"}],"error_code":0}

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private List<Result> result;

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

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public class Result implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private int rid;
		private String name;
		private String wk;
		private String wboxoffice;
		private String tboxoffice;

		public int getRid() {
			return rid;
		}

		public void setRid(int rid) {
			this.rid = rid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getWk() {
			return wk;
		}

		public void setWk(String wk) {
			this.wk = wk;
		}

		public String getWboxoffice() {
			return wboxoffice;
		}

		public void setWboxoffice(String wboxoffice) {
			this.wboxoffice = wboxoffice;
		}

		public String getTboxoffice() {
			return tboxoffice;
		}

		public void setTboxoffice(String tboxoffice) {
			this.tboxoffice = tboxoffice;
		}

	}
}
