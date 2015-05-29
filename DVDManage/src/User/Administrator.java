package User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import DBConnect.CustomerDB;
import DBConnect.DVDDB;
import DBConnect.RentDB;


public class Administrator extends AbstractUser{

	public Administrator(int id)
	{
		super(id);
	}
	
	public void rentDVD(RentDB rentDB,int c_id, int d_id)
	{
		rentDB.RentDVD(c_id, d_id);
		//need customer id
		
	}
	
	public void returnDVD(RentDB rentDB,int R_id)
	{
		rentDB.returnDVD(R_id);
	}
	
	public void insertDVD(String title,String studio,double price,String rating,String genre,String release_date,DVDDB dvdDB)
	{
		dvdDB.insertDVD(title, studio, price, rating, genre, release_date);
	}
	
	public void modifyDVD(int d_id,int type, String keyword,DVDDB dvdDB) throws UnsupportedEncodingException
	{
		//type : 1-release date, 2-genre, 3-title, 4-id
		dvdDB.modifyDVD(d_id, type, keyword);
	}
	
	public void deleteDVD(int d_id,DVDDB dvdDB)
	{
		dvdDB.deleteDVD(d_id);
	}
	
	public void insertCusotmer(String phoneNum,String password, String name, String address,CustomerDB customerDB)
	{
		customerDB.insertCustomer(phoneNum, password, name, address);
	}
	
	public void modifyCustomer(CustomerDB customerDB,int c_id, int type, String keyword) throws UnsupportedEncodingException
	{
		//1:password, 2:name, 3:phoneNumber, 4:address
		customerDB.modifyCustomer(c_id, type, keyword);
	}
	
	public void deleteCustomer(int c_id,CustomerDB customerDB)
	{
		customerDB.deleteCustomer(c_id);
	}
	
	public ArrayList<Customer> getAllCustomerList(CustomerDB customerDB) throws UnsupportedEncodingException
	{
		ArrayList<Customer> customerList=customerDB.getAllCustomer();
		
		return customerList;
	}
	
	public ArrayList<Customer> getAllOverdueCustomer(CustomerDB customerDB) throws UnsupportedEncodingException
	{
		ArrayList<Customer> customerList=customerDB.getAllOverdueCustomer();
		
		return customerList;
	}
	
	public ArrayList<Customer> getCustomerSearch(CustomerDB customerDB,int type, String keyword) throws UnsupportedEncodingException
	{
		//1: c_id, 2:name, 3:phoneNumber
		ArrayList<Customer> customerList=customerDB.getCustomerSearch(type, keyword);
		
		return customerList; 
	}
	
}
