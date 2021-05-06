package test.study;

import java.io.InputStream;
import java.util.Scanner;

public class MyMain02 {
	public static void main(String[] args) {
		System.out.println("main 메소드가 시작되었습니다.");
	/*
	 *  콘솔창으로 사용자 입력을 받아서 활용하고 싶다면?
	 *  Scanner 객체를 생성해서 메소드를 이용해야 한다.
	 */
		InputStream is = System.in;
		Scanner scan = new Scanner(is);
		System.out.print("문자열 입력 : ");
		String str = scan.nextLine();
		System.out.println("str: " + str);
	}
}
