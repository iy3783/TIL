package workshop.person.control;

import workshop.person.entity.PersonEntity;

public class PersonManager {

	public PersonManager() {
		
	}
	public void fillPerson(PersonEntity[] persons) {
		persons[0] = new PersonEntity("�̼�ȣ","7212121028102", "��õ ��籸", "032-392-2932");
		persons[1] = new PersonEntity("���ϴ�","7302132363217", "���� ������", "02-362-1932");
		persons[2] = new PersonEntity("�ڿ���","7503111233201", "���� ���ϱ�", "02-887-1542");
		persons[3] = new PersonEntity("���μ�","7312041038988", "���� ������", "032-384-2223");
		persons[4] = new PersonEntity("ȫ����","7606221021341", "���� ��õ��", "02-158-7333");
		persons[5] = new PersonEntity("�̹̼�","7502142021321", "���� ������", "02-323-1934");
		persons[6] = new PersonEntity("�ڼ���","7402061023101", "���� ���α�", "02-308-0932");
		persons[7] = new PersonEntity("������","7103282025101", "���� ����", "02-452-0939");
		persons[8] = new PersonEntity("Ȳ����","7806231031101", "��õ �߱�", "032-327-2202");
		persons[9] = new PersonEntity("��ö��","7601211025101", "��õ ��籸", "032-122-7832");
		
		
	}
	public void showPerson(PersonEntity[] persons) {
		for(int i=0; i < persons.length;i++) {
			System.out.println(persons[i].toString());
			printItemLine();
		}
	}
	public void showPerson(PersonEntity[] persons,String name) {
		for(int i=0; i < persons.length;i++) {
			if(persons[i].getName().equals(name)) {
				System.out.println(persons[i].toString());
				
			}
		}
	}
	public int findByGender(PersonEntity[] persons,char gender) {
		int counter=0;
		for(int i=0; i < persons.length;i++) {
			if(persons[i].getGender()==gender) {
				counter++;
			}
		}
		return counter;
	}
	public void printTitle(String title) {
		System.out.println(title);
	}
	public void printTitleLine() {
		System.out.println("====================================================");
	}
	public void printItemLine() {
		System.out.println("----------------------------------------------------");
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonManager personManager = new PersonManager();
		personManager.printTitle("@@@ �ι�   ����   ��ȸ   �ý��� @@@");
		personManager.printTitleLine();

		PersonEntity[] persons = new PersonEntity[10];
		
		personManager.fillPerson(persons);
		personManager.showPerson(persons);
		char gender='��';
		System.out.println("���� : \'"+gender+" \' (��)�� " + personManager.findByGender(persons, '��') +"�� �Դϴ�.");
		personManager.printTitleLine();
		String name ="���ϴ�";
		
		System.out.println("-- �̸� : \'"+name+"\'(��)�� ã�� ����Դϴ�. --");
		personManager.printItemLine();
		personManager.showPerson(persons,name);
		
		
		
	}

}
