package com.juhe.pockettools.calendar.activity;

import java.io.Serializable;

public class CalendarEntity implements Serializable {
	// {"reason":"successed","result":{"id":"1841","yangli":"2015-03-06","yinli":"乙未(羊)年正月十六","wuxing":"白蜡金 满执位","chongsha":"冲猪(乙亥)煞东","baiji":"辛不合酱主人不尝 已不远行财物伏藏",
	// "jishen":"天恩 相日 驿马 天后 天巫 福德 圣心","yi":"开光 塑绘 求嗣 纳采 裁衣 合帐 冠笄 安机械 作梁 开柱眼 安门 安床 造仓 祭祀 会亲友 祈福 经络 纳财 开市 立券 交易 入学 求嗣 理发 架马","xiongshen":"五虚 土府 大煞 往亡 重日 朱雀","ji":"出行 斋醮 安葬 嫁娶"},
	// "error_code":0}
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
		private int id;
		private String yangli;
		private String yinli;
		private String wuxing;
		private String chongsha;
		private String baiji;
		private String jishen;
		private String yi;
		private String xiongshen;
		private String ji;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getYangli() {
			return yangli;
		}

		public void setYangli(String yangli) {
			this.yangli = yangli;
		}

		public String getYinli() {
			return yinli;
		}

		public void setYinli(String yinli) {
			this.yinli = yinli;
		}

		public String getWuxing() {
			return wuxing;
		}

		public void setWuxing(String wuxing) {
			this.wuxing = wuxing;
		}

		public String getChongsha() {
			return chongsha;
		}

		public void setChongsha(String chongsha) {
			this.chongsha = chongsha;
		}

		public String getBaiji() {
			return baiji;
		}

		public void setBaiji(String baiji) {
			this.baiji = baiji;
		}

		public String getJishen() {
			return jishen;
		}

		public void setJishen(String jishen) {
			this.jishen = jishen;
		}

		public String getYi() {
			return yi;
		}

		public void setYi(String yi) {
			this.yi = yi;
		}

		public String getXiongshen() {
			return xiongshen;
		}

		public void setXiongshen(String xiongshen) {
			this.xiongshen = xiongshen;
		}

		public String getJi() {
			return ji;
		}

		public void setJi(String ji) {
			this.ji = ji;
		}

	}
}
