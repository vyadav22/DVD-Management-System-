package Mainsys;

public class Rent {

	private int r_id;
	private int c_id;
	private int d_id;
	private String title;
	private String start_date;
	private String due_date;
	private int overdue;
	
	public Rent(int r_id, int c_id, int d_id, String title, String start_date, String due_date, int overdue)
	{
		this.r_id=r_id;
		this.c_id=c_id;
		this.d_id=d_id;
		this.title=title;
		this.start_date=start_date;
		this.due_date=due_date;
		this.overdue=overdue;
	}
	
	public int getRid()
	{
		return r_id;
	}
	
	public int getCid()
	{
		return c_id;
	}
	
	public int getDid()
	{
		return d_id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getStartDate()
	{
		return start_date;
	}
	
	public String getDueDate()
	{
		return due_date;
	}
	
	public int getOverdue()
	{
		return overdue;
	}

}
