package test.study;

public class MyMain01 {
	public static void main(String[] args) {
		int num1=10;
		Integer num2=10;
		
		// 숫자를 문자로 바꾸기
		String str2 = num2.toString();
		
		// 문자를 숫자로 바꾸기
		int num3 = Integer.parseInt(str2);
		
		System.out.println(num1%6);
	}
}
