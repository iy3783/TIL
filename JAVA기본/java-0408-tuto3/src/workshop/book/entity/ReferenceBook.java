package workshop.book.entity;

public class ReferenceBook extends Publication{

	private String field;



	public ReferenceBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReferenceBook(String title, String publishDate, int page, int price ,String field) {
		super(title, publishDate, page, price);
		// TODO Auto-generated constructor stub
		this.field = field;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
