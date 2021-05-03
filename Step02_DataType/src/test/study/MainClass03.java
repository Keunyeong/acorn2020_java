package test.study;

public class MainClass03 {
	public static void main(String[] args) {
		System.out.println("main");
		//기본 데이터 type 지역 변수
		int a=10;
		double b=10.1;
		boolean c=true;
		
		//참조 데이터 type 지역 변수
		String d="김구라";
		int result = d.length();
		char ch1 = d.charAt(0);
		char ch2 = d.charAt(1);
		char ch3 = d.charAt(2);
		
		char[] tCA = d.toCharArray();
		char ch4 = tCA[1];
		System.out.println(result);
	}
}
