package DBConnect;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Test;

import Mainsys.DVD;

public class DVDDBTest {
	/*
	@Test
	public void InsertTest()
	{
		DVDDB dvd=new DVDDB();
		dvd.insertDVD("a", "b", 7.7, "NR", "drama", "5-28-2015");
	}
	@Test
	public void modifyDVD() throws UnsupportedEncodingException //1-date 2-genre 3-title, 4-price, 5-rating, 6-studio
	{
		DVDDB dvd=new DVDDB();
		dvd.modifyDVD(300413, 4, "6.4");
	}
	
	@Test
	public void deleteDVD()
	{
		DVDDB dvd=new DVDDB();
		dvd.deleteDVD(300413);
	}

	

	

	@Test
	public void getDVDSearch()
	{
		DVDDB dvd=new DVDDB();
		ArrayList<DVD> temp=null;
		try {
			 temp=dvd.getDVDSearch(2, "family"); //1-date , 2-genre, 3-title, 4-d_id
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(temp==null)
		{
			System.out.println("dd");

		}
		else
		{
			System.out.println(temp.get(11).getDate());
		}
	}*/
	
	@Test
	public void getAllDVD() throws UnsupportedEncodingException
	{
		DVDDB dvd=new DVDDB();
		ArrayList<DVD> temp=null;
			 temp=dvd.getAllDVD(2);

		if(temp==null)
			System.out.println("bb");
		else
			System.out.println(temp.get(10).getDate());
	}


}
