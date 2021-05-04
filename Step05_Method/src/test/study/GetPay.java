package test.study;

public class GetPay {
	public int WorkingDay;
	
	public void AccountPay() {
		System.out.println((100000 * WorkingDay) - (100000 * WorkingDay * 0.03));
	}
}
