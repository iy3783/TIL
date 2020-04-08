package workshop.Animal;

/*
 * 추상 클래스는 스스로 객체를 생성 할 수 없다.
 * Animal ani = new Animal() 불가능 x
 * Animal cat = new Cat() 가능
 */


public abstract class Animal {
	protected int legs;

	public Animal(int legs) {
		this.legs = legs;
	}
	public abstract void eat();

	public abstract void walk();
		

}
