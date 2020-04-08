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
		System.out.println("����� ���.");
	}//Pet
	@Override
	public void eat() {
		System.out.println("����� �Դ´�.");
	}//Animal
	@Override
	public void walk() {
		System.out.println("����� �ȴ´�.");
	}//Animal
	
	
}
