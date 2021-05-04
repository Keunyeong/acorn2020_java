package test.study;

import java.io.PrintStream;

import test.mypac.Car;

public class MyMain02 {
	public static void main(String[] args) {
		System.out.println();
		
		PrintStream a=System.out;
		a.println("해골");
		a.println("원숭이");
		
		long current=System.currentTimeMillis();
		
		System.out.println(current);
		
		
		
		String str = new String();
		str="김구라";
		
		Car c1=new Car();
		c1.name = "그랜져";
		
		System.out.println(str);
		System.out.println(c1.name);
	}
}
