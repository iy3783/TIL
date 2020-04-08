package workshop.Animal;

public class Spider extends Animal {


	public Spider() {
		super(8);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void eat() {
		System.out.println("고양이 먹는다.");
	}//Animal
	@Override
	public void walk() {
		System.out.println("고양이 걷는다.");
	}//Animal
	
}
