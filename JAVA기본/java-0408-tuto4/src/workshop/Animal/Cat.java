package workshop.Animal;

public class Cat extends Animal implements Pet  {
	private String name;

	public Cat(String name) {
		super(4);
		this.name = name;
	}
	public Cat() {
		this("");
	}
	
	@Override
	public String getName() {
		return name;
	}//Pet
	@Override
	public void setName(String name) {
		this.name = name;
	}//Pet
	@Override
	public void play() {
		System.out.println("고양이 논다.");
	}//Pet
	@Override
	public void eat() {
		System.out.println("고양이 먹는다.");
	}//Animal
	@Override
	public void walk() {
		System.out.println("고양이 걷는다.");
	}//Animal
	
	
}
