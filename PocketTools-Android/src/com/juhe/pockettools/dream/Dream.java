package com.juhe.pockettools.dream;

import java.io.Serializable;
import java.util.List;

import com.juhe.pockettools.dream.Category.Result;

public class Dream implements Serializable {
//	{"reason":"successed","result":[{"id":"873e943d1bcb40cd4b289e0809803343","title":"黄金 金子","des":"梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。"},
//	                                {"id":"237169518a0ff81aec29b80a546aa7ac","title":"黄金","des":"梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。"},
//	                                {"id":"315f055cfbae60064e07427321e6a722","title":"捡黄金","des":"梦见捡黄金，你要发大财，但要努力争取。发财也要付出努力，不会自动掉在你的口袋里。"}],
//	                                "error_code":0}
	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private List<Result> result;

	public class Result {
		private String id;
		private String title;
		private String des;
		private String[] list;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDes() {
			return des;
		}
		public void setDes(String des) {
			this.des = des;
		}
		public String[] getList() {
			return list;
		}
		public void setList(String[] list) {
			this.list = list;
		}
	}

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
	
	
}
