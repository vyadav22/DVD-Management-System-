package DBConnect;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import User.Customer;

////////////////RecoAlarmDB Class//////////////////////////////////////////////////////
public class CustomerDB{

	private final String SERVER_ADDRESS = "http://cutters.pe.hu/";


	public CustomerDB() {	//占쏙옙占쏙옙
	}

	//////////////////////////////////////recommend 占쏙옙占�///////////////////////////////////////////
	public void insertCustomer(String phoneNum, String password, String name, String address)	//占쏙옙천占싹몌옙 recommend table占쏙옙 占쌩곤옙
	{
		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/insertCustomer.php?"+"phoneNum="+ URLEncoder.encode(phoneNum, "UTF-8")
					+"&password="+ URLEncoder.encode(password, "UTF-8")
					+"&name="+ URLEncoder.encode(name, "UTF-8")
					+"&address="+ URLEncoder.encode(address, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteCustomer(int c_id)
	{
		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/deleteCustomer.php?"+"c_id="+ URLEncoder.encode(""+c_id, "UTF-8")); //�먭린 �꾩씠��
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Customer> getAllCustomer(int number) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/getAllCustomer.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getAllCustomer.xml", "c_id");

		if(c_id.isEmpty())
		{
			return null;
		}
		else {

			ArrayList<String> phoneNum = getXmlDataList("xmlFromPHP/getAllCustomer.xml", "phoneNum");
			ArrayList<String> name = getXmlDataList("xmlFromPHP/getAllCustomer.xml", "name");
			ArrayList<String> address = getXmlDataList("xmlFromPHP/getAllCustomer.xml", "address");
			ArrayList<String> mem_points = getXmlDataList("xmlFromPHP/getAllCustomer.xml", "mem_points");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getAllCustomer.xml", "overdue");

			int i=0;
			ArrayList<Customer> allCustomer=new ArrayList<Customer>();

			while(i<c_id.size())
			{
				int tempId=Integer.parseInt(c_id.get(i));
				int tempOverdue=Integer.parseInt(overdue.get(i));
				double tempMem=Double.parseDouble(mem_points.get(i));

				Customer temp=new Customer(tempId,phoneNum.get(i),name.get(i),address.get(i),tempMem,tempOverdue);
				allCustomer.add(temp);
				i++;
			}
			return allCustomer;
		}

	}

	public ArrayList<Customer> getAllOverdueCustomer(int number) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/getAllOverdueCustomer.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getAllOverdueCustomer.xml", "c_id");

		if(c_id.isEmpty())
		{
			return null;
		}
		else {

			ArrayList<String> phoneNum = getXmlDataList("xmlFromPHP/getAllOverdueCustomer.xml", "phoneNum");
			ArrayList<String> name = getXmlDataList("xmlFromPHP/getAllOverdueCustomer.xml", "name");
			ArrayList<String> address = getXmlDataList("xmlFromPHP/getAllOverdueCustomer.xml", "address");
			ArrayList<String> mem_points = getXmlDataList("xmlFromPHP/getAllOverdueCustomer.xml", "mem_points");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getAllOverdueCustomer.xml", "overdue");

			int i=0;
			ArrayList<Customer> allCustomer=new ArrayList<Customer>();

			while(i<c_id.size())
			{
				int tempId=Integer.parseInt(c_id.get(i));
				int tempOverdue=Integer.parseInt(overdue.get(i));
				double tempMem=Double.parseDouble(mem_points.get(i));

				Customer temp=new Customer(tempId,phoneNum.get(i),name.get(i),address.get(i),tempMem,tempOverdue);
				allCustomer.add(temp);
				i++;
			}
			return allCustomer;
		}

	}

	public Customer getCustomerByID(int id) throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/getCustomerById.php?"+"c_id="+ URLEncoder.encode(""+id, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String c_id = getXmlData("xmlFromPHP/getCustomerById.xml", "c_id");

		if(c_id==null)
		{
			return null;
		}
		else {

			String phoneNum = getXmlData("xmlFromPHP/getCustomerById.xml", "phoneNum");
			String name = getXmlData("xmlFromPHP/getCustomerById.xml", "name");
			String address = getXmlData("xmlFromPHP/getCustomerById.xml", "address");
			String mem_points = getXmlData("xmlFromPHP/getCustomerById.xml", "mem_points");
			String overdue = getXmlData("xmlFromPHP/getCustomerById.xml", "overdue");
			int tempId=Integer.parseInt(c_id);
			int tempOverdue=Integer.parseInt(overdue);
			double tempMem=Double.parseDouble(mem_points);
			Customer customer=new Customer(tempId,phoneNum,name,address,tempMem,tempOverdue);

			return customer; 
		}
		//System.out.println(c_id);

	}
	
	
	public ArrayList<Customer> getCustomerSearch(String type,String keyword,int number) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/getCustomerBySearch.php?"+"type="+ URLEncoder.encode(type, "UTF-8")
					+"&keyword="+URLEncoder.encode(""+keyword,"UTF-8")
					+"&number="+URLEncoder.encode(""+number,"UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getCustomerBySearch.xml", "c_id");

		if(c_id.isEmpty())
		{
			return null;
		}
		else {

			ArrayList<String> phoneNum = getXmlDataList("xmlFromPHP/getCustomerBySearch.xml", "phoneNum");
			ArrayList<String> name = getXmlDataList("xmlFromPHP/getCustomerBySearch.xml", "name");
			ArrayList<String> address = getXmlDataList("xmlFromPHP/getCustomerBySearch.xml", "address");
			ArrayList<String> mem_points = getXmlDataList("xmlFromPHP/getCustomerBySearch.xml", "mem_points");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getCustomerBySearch.xml", "overdue");

			int i=0;
			ArrayList<Customer> allCustomer=new ArrayList<Customer>();

			while(i<c_id.size())
			{
				int tempId=Integer.parseInt(c_id.get(i));
				int tempOverdue=Integer.parseInt(overdue.get(i));
				double tempMem=Double.parseDouble(mem_points.get(i));

				Customer temp=new Customer(tempId,phoneNum.get(i),name.get(i),address.get(i),tempMem,tempOverdue);
				allCustomer.add(temp);
				i++;
			}
			return allCustomer;
		}

	}

	public int checkingRight(String phoneNum, String password) throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/checkingRight.php?"+"phoneNum="+ URLEncoder.encode(phoneNum, "UTF-8")
					+"&password="+ URLEncoder.encode(password, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String temp= null;
		temp=getXmlData("xmlFromPHP/checkingRight.xml", "c_id");
		//System.out.println(c_id);
		
		if(temp==null)
			return 0;
		
		int c_id=Integer.parseInt(temp);
		return c_id; //if (null-> can't find anything / 1 - admin, 2> : customer)

	}


	public void modifyCustomer(int c_id, int type,String keyword) throws UnsupportedEncodingException
	{
		URL url;
		String filename=null;
		String key=null;
		switch(type)
		{
//		case 1:
//			filename="/modifyCustomerPassword.php?";
//			key="password=";
//			break;
		case 2:
			filename="/modifyCustomerName.php?";
			key="name=";
			break;
		case 3:
			filename="/modifyCustomerPhoneNum.php?";
			key="phoneNum=";
			break;
		case 4:
			filename="/modifyCustomerAddress.php?";
			key="address=";
			break;
		}
		try {
			url = new URL(SERVER_ADDRESS + filename+key+ URLEncoder.encode(keyword, "UTF-8")
					+"&c_id="+ URLEncoder.encode(""+c_id, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateMembership(int c_id, double mem_points) throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/updateMembership.php?"+"c_id="+ URLEncoder.encode(""+c_id, "UTF-8")
					+"&mem_points="+ URLEncoder.encode(""+mem_points, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	
	public void useMembership(int c_id, double mem_points) throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/useMembership.php?"+"c_id="+ URLEncoder.encode(""+c_id, "UTF-8")
					+"&mem_points="+ URLEncoder.encode(""+mem_points, "UTF-8"));
			try {
				url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int countCustomerBySearch(String type, String keyword)
	{
		URL url=null;

			try {
				url = new URL(SERVER_ADDRESS + "/countCustomerBySearch.php?"+"type="+ URLEncoder.encode(""+type, "UTF-8")
						+"&keyword="+URLEncoder.encode(""+keyword,"UTF-8"));
			} catch (MalformedURLException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				try {
					url.openStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		

		String cusNumber = getXmlData("xmlFromPHP/countCustomerBySearch.xml","listNum");
		if(cusNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(cusNumber);
		return tempNumber;
				
	}
	
	public int countAllCustomer()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countAllCustomer.php?");
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String cusNumber = getXmlData("xmlFromPHP/countAllCustomer.xml","listNum");
		if(cusNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(cusNumber);
		return tempNumber;
				
	}
	
	public int countOverdueCustomer()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countOverdueCustomer.php?");
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String cusNumber = getXmlData("xmlFromPHP/countOverdueCustomer.xml","listNum");
		if(cusNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(cusNumber);
		return tempNumber;
				
	}

	public String getXmlData(String filename, String str) { //占승그곤옙 占싹놂옙占쏙옙 占쌨아울옙占쏙옙占쏙옙占쏙옙 String占쏙옙 占쌉쇽옙
		String rss = SERVER_ADDRESS + "/" + filename;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Document doc = null;
		try {
			doc = builder.parse(rss);
			System.out.println("1");
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//xml파일경로

		NodeList list = doc.getElementsByTagName(str);

		Element el=(Element) list.item(0);
		System.out.println("2");
		String getElement=null;
		if(el==null)
		{
			System.out.println("5");
			return getElement;
		}
		else
		{
			System.out.println("3");
			getElement=el.getTextContent(); 
			System.out.println("4");
			return getElement;
		}

	}


	public ArrayList<String> getXmlDataList(String filename, String str) { //占승그곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨아울옙占쏙옙占쏙옙占쏙옙 ArrayList<string>占쏙옙 占쏙옙占쏙옙
		String rss = SERVER_ADDRESS + "/" + filename;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Document doc = null;
		try {
			doc = builder.parse(rss);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//xml파일경로

		NodeList list = doc.getElementsByTagName(str);

		ArrayList<String> getElements = new ArrayList<String>();

		int i=0;
		Element element;
		String contents = null;

		while(list.item(i)!=null){
			element = (Element) list.item(i);
			contents = element.getTextContent();
			getElements.add(contents);
			//	System.out.println(contents);
			i++;
		}
		return getElements;
	}
}