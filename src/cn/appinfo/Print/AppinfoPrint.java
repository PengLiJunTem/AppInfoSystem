package cn.appinfo.Print;

public class AppinfoPrint {
	
	private int pretionCount = 1;//当前页码
	private int pretionSum;//总数量
	private int pretionCoulltion = 0;//共几页
	
	public int getPretionCount() {
		return pretionCount;
	}
	public int getPretionSum() {
		return pretionSum;
	}
	public int getPretionCoulltion() {
		return pretionCoulltion;
	}
	public void setPretionCount(int pretionCount) {
		if(pretionCount!=0)
		this.pretionCount = pretionCount;
	}
	public void setPretionSum(int pretionSum) {
		this.pretionSum = pretionSum;
		this.pretionCoulltion = this.pretionSum % 5==0?(this.pretionSum/5):(this.pretionSum/5+1);
	}
}
