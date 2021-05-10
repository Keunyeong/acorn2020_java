package test.mypac;

public class Aim extends Weapon {
	public String name;
	@Override
	public void attack() {
		System.out.println(this.name + " 를(을) 공격해요! 탕탕탕");
	}
}
