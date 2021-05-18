package test.main;

public class MainClass01 {
	public static void main(String[] args) {
		String name1 = "김구라";
		String name2 = "김구라";
		String name3 = new String("김구라");
		String name4 = new String("김구라");
		boolean result1 = name1 == name2 ;
		boolean result2 = name2 == name3 ;
		boolean result3 = name3 == name4 ; 
		boolean result4 = name1.equals(name2);
		boolean result5 = name2.equals(name3);
		boolean result6 = name3.equals(name4);
	}
}
