package com.juhe.pockettools.moive;

import java.io.Serializable;
import java.util.List;

public class OnlineEntity implements Serializable {
	// {"resultcode":"200","reason":"返回成功","result":
	// [{"name":"超能陆战队（数字ＩＭＡＸ３Ｄ）","totals":"5026","statistics":"2158","averaging":"84","attendance":"55%","people":"181400","fare":"49","boxoffice":"8863906"},
	// {"name":"木星上行","totals":"7443","statistics":"2696","averaging":"53","attendance":"27%","people":"143809","fare":"50","boxoffice":"7216550"},
	// {"name":"帕丁顿熊（数字）","totals":"3657","statistics":"1613","averaging":"54","attendance":"41%","people":"87426","fare":"41","boxoffice":"3582530"},
	// {"name":"狼图腾","totals":"3156","statistics":"1132","averaging":"60","attendance":"42%","people":"67477","fare":"49","boxoffice":"3302823"},
	// {"name":"澳门风云ＩＩ（数字３Ｄ）","totals":"3417","statistics":"1164","averaging":"45","attendance":"32%","people":"51798","fare":"47","boxoffice":"2438419"},
	// {"name":"天将雄师","totals":"1621","statistics":"546","averaging":"37","attendance":"29%","people":"20399","fare":"48","boxoffice":"980884"},
	// {"name":"将错，就错","totals":"1704","statistics":"570","averaging":"24","attendance":"22%","people":"13875","fare":"38","boxoffice":"531548"},
	// {"name":"北京，纽约","totals":"2036","statistics":"616","averaging":"21","attendance":"18%","people":"12891","fare":"39","boxoffice":"503020"},
	// {"name":"大喜临门","totals":"671","statistics":"212","averaging":"27","attendance":"25%","people":"5783","fare":"38","boxoffice":"219082"},
	// {"name":"钟馗伏魔：雪妖魔灵","totals":"513","statistics":"169","averaging":"27","attendance":"23%","people":"4479","fare":"47","boxoffice":"210320"},
	// {"name":"吉星高照２０１５","totals":"328","statistics":"116","averaging":"32","attendance":"29%","people":"3753","fare":"40","boxoffice":"151722"},
	// {"name":"爸爸去哪儿2","totals":"136","statistics":"87","averaging":"33","attendance":"28%","people":"2891","fare":"38","boxoffice":"111063"},
	// {"name":"熊出没之雪岭熊风（数字３Ｄ）","totals":"94","statistics":"70","averaging":"35","attendance":"32%","people":"2466","fare":"43","boxoffice":"104980"},
	// {"name":"冲上云霄","totals":"184","statistics":"70","averaging":"31","attendance":"35%","people":"2199","fare":"43","boxoffice":"94058"},
	// {"name":"美丽笨女人","totals":"121","statistics":"46","averaging":"35","attendance":"28%","people":"1617","fare":"46","boxoffice":"75132"},
	// {"name":"爸爸的假期","totals":"58","statistics":"40","averaging":"34","attendance":"32%","people":"1370","fare":"42","boxoffice":"58050"},
	// {"name":"全能囧爸","totals":"59","statistics":"23","averaging":"57","attendance":"43%","people":"1303","fare":"44","boxoffice":"57950"},
	// {"name":"有一个地方只有我们知道","totals":"47","statistics":"16","averaging":"41","attendance":"45%","people":"659","fare":"41","boxoffice":"27177"},
	// {"name":"喜羊羊与灰太狼之羊年喜羊羊","totals":"16","statistics":"10","averaging":"31","attendance":"57%","people":"313","fare":"35","boxoffice":"10813"},
	// {"name":"兔侠之青黎传说","totals":"5","statistics":"3","averaging":"72","attendance":"47%","people":"215","fare":"45","boxoffice":"9706"},
	// {"name":"有种你爱我（国语）","totals":"10","statistics":"6","averaging":"38","attendance":"66%","people":"226","fare":"39","boxoffice":"8836"}
	// ,{"name":"生命处方","totals":"9","statistics":"1","averaging":"260","attendance":"100%","people":"260","fare":"28","boxoffice":"7280"},
	// {"name":"饥饿游戏３：嘲笑鸟（上）（数字３Ｄ）","totals":"13","statistics":"6","averaging":"29","attendance":"34%","people":"174","fare":"40","boxoffice":"6877"},{"name":"摩尔庄园大电影3魔幻列车大冒险","totals":"3","statistics":"2","averaging":"12","attendance":"13%","people":"24","fare":"33","boxoffice":"783"},{"name":"安妮：纽约奇缘","totals":"1","statistics":"1","averaging":"23","attendance":"15%","people":"23","fare":"33","boxoffice":"759"},{"name":"深海挑战3D","totals":"4","statistics":"1","averaging":"6","attendance":"7%","people":"6","fare":"44","boxoffice":"264"},{"name":"新年行动","totals":"2","statistics":"1","averaging":"3","attendance":"4%","people":"3","fare":"33","boxoffice":"99"},{"name":"我只要我们在一起","totals":"2","statistics":"0","averaging":"0","attendance":"0%","people":"0","fare":"0","boxoffice":"0"},{"name":"新年来啦之大闹除夕","totals":"1","statistics":"0","averaging":"0","attendance":"0%","people":"0","fare":"0","boxoffice":"0"},{"name":"甜蜜蜜","totals":"1","statistics":"0","averaging":"0","attendance":"0%","people":"0","fare":"0","boxoffice":"0"}],"error_code":0}
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
		// {"name":"超能陆战队（数字ＩＭＡＸ３Ｄ）","totals":"5026","statistics":"2158","averaging":"84","attendance":"55%","people":"181400","fare":"49","boxoffice":"8863906"}
		private String name;
		private int totals;
		private int statistics;
		private int averaging;
		private String attendance;
		private int people;
		private int fare;
		private int boxoffice;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getTotals() {
			return totals;
		}

		public void setTotals(int totals) {
			this.totals = totals;
		}

		public int getStatistics() {
			return statistics;
		}

		public void setStatistics(int statistics) {
			this.statistics = statistics;
		}

		public int getAveraging() {
			return averaging;
		}

		public void setAveraging(int averaging) {
			this.averaging = averaging;
		}

		public String getAttendance() {
			return attendance;
		}

		public void setAttendance(String attendance) {
			this.attendance = attendance;
		}

		public int getPeople() {
			return people;
		}

		public void setPeople(int people) {
			this.people = people;
		}

		public int getFare() {
			return fare;
		}

		public void setFare(int fare) {
			this.fare = fare;
		}

		public int getBoxoffice() {
			return boxoffice;
		}

		public void setBoxoffice(int boxoffice) {
			this.boxoffice = boxoffice;
		}

	}
}
