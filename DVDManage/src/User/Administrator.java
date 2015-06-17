package User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import DBConnect.CustomerDB;
import DBConnect.DVDDB;
import DBConnect.RentDB;
import Mainsys.DVD;
import Mainsys.Rent;


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
	
	
	public void insertDVD(String title,String studio,double price,String rating,String genre,String release_date,DVDDB dvdDB)
	{
		dvdDB.insertDVD(title, studio, price, rating, genre, release_date);
	}
	
	public void modifyDVD(int d_id,int type, String keyword,DVDDB dvdDB)
	{
		//type : 1-release date, 2-genre, 3-title, 4-id
		try {
			dvdDB.modifyDVD(d_id, type, keyword);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Rent> getAllRent(RentDB rentDB,int number) throws UnsupportedEncodingException
	{
		ArrayList<Rent> rentList=rentDB.getAllRent(number);
		return rentList;
	}
	
	public ArrayList<Rent> getAllOverdueRent(RentDB rentDB, int number) throws UnsupportedEncodingException
	{
		ArrayList<Rent> DVDList=rentDB.getAllOverdueRent(number);
		
		return DVDList;
	}
	
	public ArrayList<DVD> getDVDListInStock(DVDDB dvdDB,int number) throws UnsupportedEncodingException
	{
		ArrayList<DVD> DVDList=dvdDB.getAllDVDInStock(number);
		
		return DVDList;
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
	
	public ArrayList<Customer> getAllCustomerList(CustomerDB customerDB,int number) throws UnsupportedEncodingException
	{
		ArrayList<Customer> customerList=customerDB.getAllCustomer(number);
		
		return customerList;
	}
	
	public ArrayList<Customer> getAllOverdueCustomer(CustomerDB customerDB,int number) throws UnsupportedEncodingException
	{
		ArrayList<Customer> customerList=customerDB.getAllOverdueCustomer(number);
		
		return customerList;
	}
	
	public ArrayList<Customer> getCustomerSearch(CustomerDB customerDB,String type, String keyword,int number) throws UnsupportedEncodingException
	{
		//1: c_id, 2:name, 3:phoneNumber
		ArrayList<Customer> customerList=customerDB.getCustomerSearch(type, keyword, number);
		
		return customerList; 
	}
	
	public ArrayList<Rent> getRentByTitle(RentDB rentDB, String title, int number)
	{
		ArrayList<Rent> rentList=null;
		try {
			rentList = rentDB.getRentByTitle(title, number);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rentList;
	}
	
	public ArrayList<Rent> getRentByPhoneNumber(RentDB rentDB, String phoneNum, int number)
	{
		ArrayList<Rent> rentList=null;
		try {
			rentList = rentDB.getRentByphoneNumber(phoneNum, number);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rentList;
	}
	
}
