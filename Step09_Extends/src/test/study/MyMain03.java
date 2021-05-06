package test.study;

import test.human.Blood;
import test.human.Person;

public class MyMain03 {
	public static void main(String[] args) {
		Blood myblood = new Blood("rh+","O");
		Person me = new Person(myblood);
		me.walk();
	}
}
