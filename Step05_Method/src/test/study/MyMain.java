package test.study;

import test.mypac.Car;
import test.mypac.MyObject;
import test.mypac.Radio;
import test.mypac.Speaker;

public class MyMain {
	public static void main(String[] args) {
//		MyObject o1 = new MyObject();
//		o1.walk();
//		int num=o1.getNumber();
//		String greet = o1.getGreeting();
//		Car car1 = o1.getCar();
		
// 동일한 패키지 혹은 java.lang 패키지에 속한 클래스가 아니면
// 반드시 import해야 사용할수 있다.
		
//		o1.setName("김구라");
//		Car car = new Car();
//		o1.useCar(car);
//		o1.useSome("김구라", car);
//		Radio r = new Radio();
//		Speaker s = new Speaker();
//		o1.doSome(r, s);
		
		PrintM p1 = new PrintM();
		p1.something = "원숭이";
		p1.pM();
		
		GetPay giveme = new GetPay();
		giveme.WorkingDay = 20;
		giveme.AccountPay();
		
		PickNumber.PickNum(3);
	}
}