package test.main;

import java.util.Scanner;

public class MainClass05 {
public static void main(String[] args) {
	Scanner scan=new Scanner(System.in);
	System.out.print("문자열 입력:");
	String line=scan.nextLine();
	//입력된 문자열의 양쪽 공백 없애기
	String result= line.trim();
	
	System.out.println(result);
}
}
