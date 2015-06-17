package DBConnect;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class RentDBTest {
/*
	@Test
	public void RentDVD()
	{
		RentDB rent=new RentDB();
		rent.RentDVD(2, 77);
	}
	

	@Test
	public void ReturnDVD()
	{
		RentDB rent=new RentDB();
		rent.returnDVD(6);
	}
	
	@Test
	public void getAllDVDRent() throws UnsupportedEncodingException
	{
		RentDB dvd=new RentDB();
		ArrayList<DVD> temp=null;
			 temp=dvd.getAllDVDRent();

		if(temp==null)
			System.out.println("bb");
		else
			System.out.println(temp.get(1).getId());
	}
	@Test
	public void getAllDVDInStock() throws UnsupportedEncodingException
	{
		RentDB dvd=new RentDB();
		ArrayList<DVD> temp=null;
			 temp=dvd.getAllDVDInStock();

		if(temp==null)
			System.out.println("bb");
		else
			System.out.println(temp.get(3).getId());
	}
	
	@Test
	public void getAllRent() throws UnsupportedEncodingException
	{
		RentDB dvd=new RentDB();
		ArrayList<Rent> temp=null;
			 temp=dvd.getAllRent();

		if(temp==null)
			System.out.println("bb");
		else
			System.out.println(temp.get(2).getTitle());
	}
	
	@Test
	public void getAllCustomerRent() throws UnsupportedEncodingException
	{
		RentDB dvd=new RentDB();
		ArrayList<Rent> temp=null;
			 temp=dvd.getCustomerRentDVD(2);
		if(temp==null)
			System.out.println("bb");
		else
			System.out.println(temp.get(1).getTitle());
	}
	
	
	@Test
	public void UpdateOverdue()
	{
		RentDB dvd=new RentDB();
		//dvd.updateOverdueInRent();
		dvd.updateOverdueInCustomer();
	}
	*/
	@Test
	public void isValidToRent() throws UnsupportedEncodingException
	{
		RentDB dvd=new RentDB();
		String temp=null;
		temp=dvd.isValidToRent(2);
		if(temp==null)
			System.out.println("can't rent");
		else
			System.out.println("rent");
	}
}
