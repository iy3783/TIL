package workshop.book.entity;

public class Magazine extends Publication {
	private String publishingPeriod;


	public Magazine() {
		super();
	}

	public Magazine(String title, String publishDate, int page, int price,String publishingPeriod) {
		super(title, publishDate, page, price);
		// TODO Auto-generated constructor stub
		this.publishingPeriod = publishingPeriod;
	}

	public String getPublishingPeriod() {
		return publishingPeriod;
	}

	public void setPublishingPeriod(String publishingPeriod) {
		this.publishingPeriod = publishingPeriod;
	}
	
	

}
