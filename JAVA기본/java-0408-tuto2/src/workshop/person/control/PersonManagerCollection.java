package workshop.person.control;

import java.util.ArrayList;
import java.util.List;

import workshop.person.entity.PersonEntity;
public class PersonManagerCollection {

		public PersonManagerCollection() {
			
		}
		public void fillPerson(List<PersonEntity> persons) {
			persons.add(new PersonEntity("�̼�ȣ","7212121028102", "��õ ��籸", "032-392-2932"));
			persons.add(new PersonEntity("���ϴ�","7302132363217", "���� ������", "02-362-1932"));
			persons.add(new PersonEntity("�ڿ���","7503111233201", "���� ���ϱ�", "02-887-1542"));
			persons.add(new PersonEntity("���μ�","7312041038988", "���� ������", "032-384-2223"));
			persons.add(new PersonEntity("ȫ����","7606221021341", "���� ��õ��", "02-158-7333"));
			persons.add(new PersonEntity("�̹̼�","7502142021321", "���� ������", "02-323-1934"));
			persons.add(new PersonEntity("�ڼ���","7402061023101", "���� ���α�", "02-308-0932"));
			persons.add(new PersonEntity("������","7103282025101", "���� ����", "02-452-0939"));
			persons.add(new PersonEntity("Ȳ����","7806231031101", "��õ �߱�", "032-327-2202"));
			persons.add(new PersonEntity("��ö��","7601211025101", "��õ ��籸", "032-122-7832"));
			
			
		}
		public void showPerson(List<PersonEntity> persons) {
			for(PersonEntity person : persons) {
				System.out.println(person.toString());
				printItemLine();
			}
		}
		public void showPerson(List<PersonEntity> persons,String name) {
			for(PersonEntity person : persons) {
				if(person.getName().equals(name)) {
					System.out.println(person.toString());
					
				}
			}
		}
		public int findByGender(List<PersonEntity> persons,char gender) {
			int counter=0;
			for(PersonEntity person : persons) {
				if(person.getGender()==gender) {
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
			PersonManagerCollection personManager = new PersonManagerCollection();
			personManager.printTitle("@@@ �ι�   ����   ��ȸ   �ý��� @@@");
			personManager.printTitleLine();

			List<PersonEntity> persons = new ArrayList<PersonEntity>();
			
			
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

	
	
