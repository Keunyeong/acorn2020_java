package test.main;

import test.mypac.Aim;
import test.mypac.Gun;
import test.mypac.Weapon;

public class MainClass02 {
	public static void main(String[] args) {
		//여러분이 직접 클래스를 만들고 객체 생성을 해서 
		//아래의 useWeapon() 메소드를 호출해 보세요.

		Aim a=new Aim();
		MainClass02.useWeapon(a);

	}
	
	public static void useWeapon(Weapon a) {
		((Aim)a).name = "원숭이";
		a.prepare();
		a.attack();
	}
}




