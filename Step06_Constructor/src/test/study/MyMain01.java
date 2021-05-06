package test.study;

import java.util.Random;

public class MyMain01 {
	public static void main(String[] args) {
		/*
		 *  랜덤한 무작위 숫자를 얻어내고 싶다면
		 *  Random 객체를 활용해야 한다.
		 *  따라서 new Random()해서 객체를 생성후
		 *  객체의 메소드를 이용해서 무작위 숫자를 얻어내 보자.
		 */
		Random ran = new Random();
		int ranNum = ran.nextInt(26)+75;
		boolean ranbool = ran.nextBoolean();
		
		System.out.println(ranNum);
		System.out.println(ranbool);
	}
}