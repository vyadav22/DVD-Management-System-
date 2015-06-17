package User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import DBConnect.CustomerDB;
import DBConnect.RentDB;
import Mainsys.Rent;

public class Customer extends AbstractUser{

	private String phoneNum;
	private String name;
	private String address;
	private double memPoint;
	private int overdue;
	
	public Customer(int id, String phoneNum, String name, String address, double memPoint, int overdue)
	{
		super(id);
		this.phoneNum=phoneNum;
		this.name=name;
		this.address=address;
		this.memPoint=memPoint;
		this.overdue=overdue;
	}
	
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public double getMemPoint()
	{
		return memPoint;
	}
	
	public int isOverdue()
	{
		return overdue;
	}
	
	public void rentDVD(RentDB rentDB, int d_id)
	{
		rentDB.RentDVD(getId(), d_id);
		
	}
	

	
	public ArrayList<Rent> getMyRentDVDList(RentDB rentDB) throws UnsupportedEncodingException
	{
		ArrayList<Rent> rentList=rentDB.getCustomerRentDVD(getId());
		
		return rentList;
	}
	
	public void upadateMembership(double price,CustomerDB customerDB) throws UnsupportedEncodingException
	{
		customerDB.updateMembership(getId(), price);
		System.out.println("before memPoint : "+memPoint);
		memPoint=memPoint+(price*0.1);
		System.out.println("after memPoint : "+memPoint);
	}
	
	public void useMembership(double price,CustomerDB customerDB) throws UnsupportedEncodingException
	{
		customerDB.useMembership(getId(), price);
		System.out.println("before memPoint : "+memPoint);
		memPoint=memPoint-price;
		System.out.println("after memPoint : "+memPoint);
	}
}
