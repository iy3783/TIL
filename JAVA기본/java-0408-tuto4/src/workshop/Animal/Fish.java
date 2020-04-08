package workshop.Animal;

public class Fish extends Animal implements Pet {
	String name;
	
	
	public Fish( String name) {
		super(0);
		this.name = name;
	}
	public Fish() {
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
