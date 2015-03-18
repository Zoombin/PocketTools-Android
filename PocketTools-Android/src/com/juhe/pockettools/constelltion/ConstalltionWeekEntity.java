package com.juhe.pockettools.constelltion;

import java.util.List;

public class ConstalltionWeekEntity {
	// {"date":"2015年02月22日-2015年02月28日","health":"健康：身体小心炎症。","job":"求职：接受安排是顺势之举。 ","love":"恋情：受本能驱使，缺乏情感理智。与伴侣相处，可能有蛮横不讲理的表现。 ","money":"财运：花销随心情簸荡，财务控制力下降。 ",
	// "name":"白羊座","weekth":9,"work":"工作：知道自己想要什么，但行动无力，受制于人的可能性大。或企图走捷径，不惜放弃原则。 ","resultcode":"200","error_code":0}

	// {"all":"上旬和中旬，人际周旋多，忙碌事务往往和社团、集体有关；金星和火星皆在心灵宫，有望从小圈子或熟人暗地获得好处，但也容易和他们起冲突；受水逆影响，各种文书错误、机械故障和言辞误会频现。下旬，金星和火星相继入命宫，意图彰显个人价值，心高气盛，但太阳入心灵宫，行动力弱。",
	// "date":"2015年02月","happyMagic":"","health":"上旬水逆，出行需小心。下旬，情绪走低，火星入命宫，易有炎症或发烧。",
	// "love":"上旬和中旬，暧昧和隐晦的氛围重，喜欢异性的陪伴，但界限感不明。下旬，逐渐清晰自己的感觉，但受太阳能量影响，容易情绪化处理，难有和平收场。",
	// "money":"上旬和中旬，人际花销大，但生财机会也因此而来，偏财运旺，有贵人暗地相助，但其并不大方包容，易起冲突，导致钱财大起大落。下旬，个人价值彰显，有更多机会利用天赋或能力谋利，同时财富控制力下降，情绪性开销多。","month":2,
	// "name":"白羊座","work":"对新行业、新领域感兴趣。上旬和中旬，机会较多，但受水逆影响，易有错判。下旬，行动力下降，心有余而力不足。","resultcode":"200","error_code":0}
	private String all;
	private String date;
	private String health;
	private String job;
	private String love;
	private String money;
	private String name;
	private int weekth;
	private String work;
	private String resultcode;
	private int error_code;

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeekth() {
		return weekth;
	}

	public void setWeekth(int weekth) {
		this.weekth = weekth;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}
