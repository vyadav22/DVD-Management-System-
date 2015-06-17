package User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import DBConnect.DVDDB;
import DBConnect.RentDB;
import Mainsys.DVD;

public class AbstractUser {
	
	private int id; // when not loggin, it's 0.
	
	public AbstractUser(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return id;
	}
	

	
	public ArrayList<DVD> getAllDVDList(DVDDB dvdDB,int number) throws UnsupportedEncodingException
	{
		ArrayList<DVD> DVDList = dvdDB.getAllDVD(number);
	
		return DVDList;
	}
	
	public ArrayList<DVD> getDVDSearch(String type,String keyword,DVDDB dvdDB,int number) throws UnsupportedEncodingException
	{
		//type : 1-release date, 3-title
		return dvdDB.getDVDSearch(type, keyword,number);
	}
	
	public ArrayList<DVD> getDVDByGenre(String keyword,DVDDB dvdDB,int number) throws UnsupportedEncodingException
	{
		//type : 1-release date, 2-genre, 3-title, 4-id
		return dvdDB.getDVDSearchByGenre(keyword, number);
	}
	
	public boolean rentDVD()
	{
		
		return true;
	}
	
	public void returnDVD(RentDB rentDB,int R_id)
	{
		rentDB.returnDVD(R_id);
	}

	
}

