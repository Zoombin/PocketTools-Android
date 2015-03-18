package com.juhe.pockettools.courier;

import java.util.List;

public class PackageEntity {
	// {"resultcode":"200","reason":"成功的返回","result":
	// {"company":"圆通","com":"yt","no":"100160372466","list":[{"datetime":"2014-12-01 18:34","remark":"浙江省杭州市文三路公司 已收件","zone":""},
	// {"datetime":"2014-12-01 20:31","remark":"浙江省杭州市文三路公司 已打包","zone":""},
	// {"datetime":"2014-12-02 07:07","remark":"江苏省苏州市常熟市公司 已收入","zone":""},
	// {"datetime":"2014-12-02 08:07","remark":"江苏省苏州市常熟市公司 派件中","zone":""},
	// {"datetime":"2014-12-02 15:55","remark":"江苏省苏州市常熟市公司 已签收","zone":""}]
	// ,"status":1},"error_code":0}
	private String resultcode;
	private String reason;
	private Result result;
	private int error_code;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public class Result {
		private String company;
		private String com;
		private String no;
		List<PEntity> list;
		private int status;

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getCom() {
			return com;
		}

		public void setCom(String com) {
			this.com = com;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public List<PEntity> getList() {
			return list;
		}

		public void setList(List<PEntity> list) {
			this.list = list;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

	public class PEntity {
		private String datetime;
		private String remark;
		private String zone;

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getZone() {
			return zone;
		}

		public void setZone(String zone) {
			this.zone = zone;
		}

	}
}
