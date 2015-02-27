package com.juhe.pockettools.constelltion;

public class ConstalltionEntity {
	// {"QFriend":"水瓶座","all":"60%","color":"绿色","date":20150227,"datetime":"2015年02月27日","health":"60%","love":"60%","money":"60%","name":"白羊座","number":4,
	// "summary":"今天的你有点烦躁不安，好像说什么做什么都无法让你放松下来，但又说不出为什么如此不安。此时的你需要找些事情来做，转移一下自己的注意力，不过要注意的是，本日不适合做重要的决定，否则很容易朝令夕改，徒增许多的麻烦哩！",
	// "work":"60%","resultcode":"200","error_code":0}

	// {"date":"2015年02月22日-2015年02月28日","health":"健康：身体小心炎症。","job":"求职：接受安排是顺势之举。 ","love":"恋情：受本能驱使，缺乏情感理智。与伴侣相处，可能有蛮横不讲理的表现。 ","money":"财运：花销随心情簸荡，财务控制力下降。 ",
	// "name":"白羊座","weekth":9,"work":"工作：知道自己想要什么，但行动无力，受制于人的可能性大。或企图走捷径，不惜放弃原则。 ","resultcode":"200","error_code":0}

	private String QFriend;
	private String all;
	private String color;
	private String date;
	private String datetime;
	private String health;
	private String love;
	private String job;
	private String money;
	private String name;
	private int number;
	private int weekth;
	private String summary;
	private String work;
	private String resultcode;
	private int error_code;

	public String getQFriend() {
		return QFriend;
	}

	public void setQFriend(String qFriend) {
		QFriend = qFriend;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getWeekth() {
		return weekth;
	}

	public void setWeekth(int weekth) {
		this.weekth = weekth;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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
