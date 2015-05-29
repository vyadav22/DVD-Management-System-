package Mainsys;

public class DVD {

	private int dvd_id;
	private String title;
	private String studio;
	private double price;
	private String rating;
	private String genre;
	private String release_date;
	
	public DVD(int dvd_id, String title, String studio, double price, String rating, String genre, String release_date)
	{
		this.dvd_id=dvd_id;
		this.title=title;
		this.studio=studio;
		this.price=price;
		this.rating=rating;
		this.genre=genre;
		this.release_date=release_date;
	}
	
	public int getId()
	{
		return dvd_id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getStudio()
	{
		return studio;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getRating()
	{
		return rating;
	}
	
	public String getGenre()
	{
		return genre;
	}
	
	public String getDate()
	{
		return release_date;
	}

	
	
}
