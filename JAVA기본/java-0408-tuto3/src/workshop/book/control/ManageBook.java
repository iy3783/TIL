package workshop.book.control;

import java.util.ArrayList;
import java.util.List;

import workshop.book.entity.Magazine;
import workshop.book.entity.Novel;
import workshop.book.entity.Publication;
import workshop.book.entity.ReferenceBook;

public class ManageBook {
	
	public ManageBook() {
		
	}
	
	public void modifyPrice(Publication publication) {
		int price = publication.getPrice();
		double rate = 0.0;
		if(publication instanceof Magazine) {
			rate = 0.6;
		}
		if(publication instanceof Novel) {
			rate = 0.8;
		}
		if(publication instanceof ReferenceBook) {
			rate = 0.9;
		}
	
		publication.setPrice((int) (price*rate));
		
	}
	public static void main(String args[]) {
		ManageBook manageBook = new ManageBook();
		
		List<Publication> publications = new ArrayList<Publication>();
		publications.add(new Magazine("����ũ�μ���Ʈ","2007-10-01",328,9900,"�ſ�"));
		publications.add(new Magazine("�濵����ǻ��","2007-10-03",316,9000,"�ſ�"));
		publications.add(new Novel("���߿�","2007-07-01",396,9800,"����������������","����Ҽ�")); 
		publications.add(new Novel("���ѻ꼺","2007-04-14",383,11000,"����","���ϼҼ�")); 
		publications.add(new ReferenceBook("�ǿ��������α׷���","2007-01-14",496,25000,"����Ʈ�������")); 
		
		
		
		
		for(Publication publication : publications) {
			System.out.println(publication.toString());	
			manageBook.modifyPrice(publication);
			System.out.println(publication.getPrice());	
		}
		
		
		
	}
	
}
