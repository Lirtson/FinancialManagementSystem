package 个人财务管理系统;
//每条收支信息类
public class oneAccount {
	private int No;//编号
	private int date;//日期
	private String IOtype;//收支类型
	private String type;//内容
	private double price;//金额
	public oneAccount(int no, int date, String iOtype, String type,double price) {
		super();
		No = no;
		this.date = date;
		IOtype = iOtype;
		this.type=type;
		this.price = price;
	}
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getIOtype() {
		return IOtype;
	}
	public void setIOtype(String iOtype) {
		IOtype = iOtype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
