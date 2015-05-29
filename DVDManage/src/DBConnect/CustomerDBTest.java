package DBConnect;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Test;

import User.Customer;


public class CustomerDBTest {
/*
	@Test
	public void InsertTest()
	{
		CustomerDB customerDB=new CustomerDB();
		customerDB.insertCustomer("1233", "11", "gf", "gfd");
	}

	@Test
	public void getAllCustomerTest()
	{
		CustomerDB customerDB=new CustomerDB();
		ArrayList<Customer> temp=null;
		try {
			 temp=customerDB.getAllCustomer();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(temp.get(2).getName());
	}
	@Test
	public void getdeleteCustomer()
	{
		CustomerDB customerDB=new CustomerDB();
		customerDB.deleteCustomer(9);
	}
		
	@Test
	public void getAllOverdueCustomerTest()
	{
		CustomerDB customerDB=new CustomerDB();
		ArrayList<Customer> temp=null;
		try {
			 temp=customerDB.getAllOverdueCustomer();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(temp.get(2).getName());
	}
	
	@Test
	public void getCustomerSearch()
	{
		CustomerDB customerDB=new CustomerDB();
		ArrayList<Customer> temp=null;
		try {
			 temp=customerDB.getCustomerSearch(2, "f");
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
			System.out.println(temp.get(0).getAddress());
		}
	}

	
	
	
	@Test
	public void checkingRight() throws UnsupportedEncodingException
	{
		CustomerDB customerDB=new CustomerDB();
		String temp=customerDB.checkingRight("admin", "1234");
		
		if(temp==null) //
			System.out.println(" can't find anything");
		else if(temp.equals("1"))
			System.out.println("it's admin");
		else
			System.out.println("it's customer");
		System.out.println("sdd"+temp);
	}
	@Test
	public void modifyPassword() throws UnsupportedEncodingException
	{
		CustomerDB customerDB=new CustomerDB();
		customerDB.modifyCustomer(2,4, "ggggggggg");
	}
	
	@Test
	public void updateMembership() throws UnsupportedEncodingException
	{
		CustomerDB customerDB=new CustomerDB();
		customerDB.updateMembership(1,7.4);
	}
	
	@Test
	public void useMembership() throws UnsupportedEncodingException
	{
		CustomerDB customerDB=new CustomerDB();
		customerDB.useMembership(3,2.6);
	}*/
	@Test
	public void checkingRight() throws UnsupportedEncodingException
	{
		CustomerDB customerDB=new CustomerDB();
		if(customerDB.checkingRight("admin", "1234")==null) //
			System.out.println(" can't find anything");
		else if(customerDB.checkingRight("admin", "1234").equals("1"))
			System.out.println("it's admin");
		else
			System.out.println("it's customer");
	}
}
