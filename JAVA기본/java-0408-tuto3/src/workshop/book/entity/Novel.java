package workshop.book.entity;

public class Novel extends Publication {
	private String author;
	private String genre;
	public Novel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Novel(String title, String publishDate, int page, int price,String author, String genre) {
		super(title, publishDate, page, price);
		// TODO Auto-generated constructor stub
		this.author = author;
		this.genre = genre;		
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	
	

}
