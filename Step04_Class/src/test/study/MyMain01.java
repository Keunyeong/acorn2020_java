package test.study;

import test.mypac.Car;

public class MyMain01 {
	public static void main(String[] args) {
		System.out.println("main 메소드를 시작 합니다.");
		Car c1=new Car();
		Car c2=new Car();
		String carName = c1.name;
		c1.drive();
	}
}