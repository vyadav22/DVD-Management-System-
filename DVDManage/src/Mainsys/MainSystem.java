package Mainsys;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import DBConnect.CustomerDB;
import DBConnect.DVDDB;
import DBConnect.RentDB;
import User.AbstractUser;
import User.Administrator;
import User.Customer;


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
		rentDB.updateOverdueInRent();
		rentDB.updateOverdueInCustomer();
		
	}
	
	public void beAdmin()
	{
		user=null;
		
		user=new Administrator(1);
	}
	
	public void beCustomer(int c_id) throws UnsupportedEncodingException
	{
		user=null;
		user=getCustomerDB().getCustomerByID(c_id);
	}
	
	public CustomerDB getCustomerDB()
	{
		return customerDB;
	}
	
	public DVDDB getDVDDB()
	{
		return dvdDB;
	}
	
	public RentDB getRentDB()
	{
		return rentDB;
	}
	
	
	public int login(String phoneNum,String password) throws NumberFormatException, UnsupportedEncodingException
	{

		return getCustomerDB().checkingRight(phoneNum, password);
	}
	
	public AbstractUser getUser()
	{
		return user;
	}
	
	public void logout()
	{
		user=null;
		user=new AbstractUser(0);
	}
	
}
