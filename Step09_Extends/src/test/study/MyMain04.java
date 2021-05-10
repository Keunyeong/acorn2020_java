package test.study;

import test.mypac.Phone;

public class MyMain04 {
	public static void main(String[] args) {
		// run 했을때 실행순서 (작업단위) 가 시작되는 특별한 main 메소드
		Phone p1 = new Phone();
		p1.call();
		
		System.out.println("main 메소드를 종료 합니다.");
	}
}
