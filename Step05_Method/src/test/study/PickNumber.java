package test.study;

import java.util.Random;

public class PickNumber {
	public static void PickNum(int yourNum) {
		Random r = new Random();
		int rNum = r.nextInt(10)+1;
//		System.out.println(rNum);
		if (yourNum> rNum) {
			System.out.println("너무 커요!");
		} else if (yourNum<rNum) {
			System.out.println("너무 작아요!");
		} else {
			System.out.println("정답! 어떻게 맞췄지?????");
		}
	}
}
	
