package User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import DBConnect.*;
import Mainsys.DVD;
import Mainsys.Rent;

public class AbstractUser {
	
	private int id; // when not loggin, it's 0.
	private DVDDB dvdDB;
	private RentDB rentDB;
	private CustomerDB customerDB;
	
	public AbstractUser(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public ArrayList<Rent> getAllRent(RentDB rentDB) throws UnsupportedEncodingException
	{
		ArrayList<Rent> rentList=rentDB.getAllRent();
		return rentList;
	}
	
	public ArrayList<DVD> getAllDVDList()
	{
		ArrayList<DVD> DVDList=dvdDB.getAllDVD();
	
		return DVDList;
	}
	
	public void getDVDSearch(int type,String keyword,DVDDB dvdDB) throws UnsupportedEncodingException
	{
		//type : 1-release date, 2-genre, 3-title, 4-id
		dvdDB.getDVDSearch(type, keyword);
	}
	

	
	public ArrayList<DVD> getDVDListRent(RentDB rentDB) throws UnsupportedEncodingException
	{
		ArrayList<DVD> DVDList=rentDB.getAllDVDRent();
		
		return DVDList;
	}
	
	public ArrayList<DVD> getDVDListInStock(RentDB rentDB) throws UnsupportedEncodingException
	{
		ArrayList<DVD> DVDList=rentDB.getAllDVDInStock();
		
		return DVDList;
	}
	
	public boolean rentDVD()
	{
		
		return true;
	}
	
	public boolean returnDVD()
	{
		return true;
	}

	
}

