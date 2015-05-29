package Mainsys;
import DBConnect.CustomerDB;
import DBConnect.DVDDB;
import DBConnect.RentDB;
import User.*;


public class MainSystem {

	CustomerDB customerDB;
	DVDDB dvdDB;
	RentDB rentDB;
	AbstractUser user;
	
	public MainSystem() // after adding Ui, adding UI object to constructer
	{
		customerDB=new CustomerDB();
		dvdDB=new DVDDB();
		rentDB=new RentDB();
		user =new AbstractUser(0); //when logging, this object will be discard and Administrator or Customer will be made.
		/////////when system starts(normally every morning), update overdue. 
		rentDB.updateOverdueInCustomer();
		rentDB.updateOverdueInRent();
	}
	
	
	public static void main(final String[] args) //after adding UI, discard.
	{				
	}
	
	public void payment() // customer클래스 안에서.
	{
	
	}
	
	public boolean isValidToRent() //check customer's overdue and dvd in stock
	{
		return true;
	}
	
	public boolean isAdmin(String adminId, String password)
	{
	return true;
	}
	
	public boolean isCustomer()
	{
		return true;
	}
}
