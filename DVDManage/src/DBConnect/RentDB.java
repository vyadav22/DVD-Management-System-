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

import Mainsys.Rent;

public class RentDB {

	private final String SERVER_ADDRESS = "http://cutters.pe.hu/";

	//$title','$studio',$price,'$rating','$genre','$release_date'
	// $sql="INSERT INTO Rent VALUES (0,$c_id,$d_id,curdate(),DATE_ADD(curdate()+ interval 7 day),0);";
	public void RentDVD(int c_id, int d_id)
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/RentDVD.php?"+"c_id="+ URLEncoder.encode(""+c_id, "UTF-8")
					+"&d_id="+URLEncoder.encode(""+d_id, "UTF-8"));					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}

	public void returnDVD(int R_id)
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/returnDVD.php?"+"R_id="+ URLEncoder.encode(""+R_id, "UTF-8"));						
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}

	// $sql="select * from DVD d where d.d_id NOT IN(select r.d_id from Rent r) limit 10000;";
	public ArrayList<Rent> getAllOverdueRent(int number) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllOverdueRent.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8"));
			//  $sql="select d_id, title, studio, price, rating, genre, release_date from Rent r,DVD d where d.d_id=r.r_id limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		ArrayList<String> R_id = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "R_id");

		if(R_id.isEmpty())
		{
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "c_id");
			ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "d_id");
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "title");
			ArrayList<String> start_date = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "start_date");
			ArrayList<String> due_date = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "due_date");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getAllOverdueRent.xml", "overdue");

			int i=0;
			ArrayList<Rent> allRent=new ArrayList<Rent>();

			while(i<R_id.size())
			{
				int tempRID=Integer.parseInt(R_id.get(i));
				int tempCID=Integer.parseInt(c_id.get(i));
				int tempDID=Integer.parseInt(d_id.get(i));
				int tempdue=Integer.parseInt(overdue.get(i));


				Rent temp=new Rent(tempRID, tempCID, tempDID, title.get(i),start_date.get(i), due_date.get(i), tempdue);
				allRent.add(temp);
				i++;
			}
			return allRent;
		}		
	}
	
	public ArrayList<Rent> getRentByTitle(String keyword,int number) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getRentByTitle.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8")
					+"&title="+URLEncoder.encode(""+keyword, "UTF-8"));	
			//  $sql="select d_id, title, studio, price, rating, genre, release_date from Rent r,DVD d where d.d_id=r.r_id limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		ArrayList<String> R_id = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "R_id");

		if(R_id.isEmpty())
		{
			System.out.println("here");
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "c_id");
			ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "d_id");
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "title");
			ArrayList<String> start_date = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "start_date");
			ArrayList<String> due_date = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "due_date");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getRentByTitle.xml", "overdue");

			int i=0;
			ArrayList<Rent> allRent=new ArrayList<Rent>();

			while(i<R_id.size())
			{
				int tempRID=Integer.parseInt(R_id.get(i));
				int tempCID=Integer.parseInt(c_id.get(i));
				int tempDID=Integer.parseInt(d_id.get(i));
				int tempdue=Integer.parseInt(overdue.get(i));


				Rent temp=new Rent(tempRID, tempCID, tempDID, title.get(i),start_date.get(i), due_date.get(i), tempdue);
				allRent.add(temp);
				i++;
			}
			return allRent;
		}		
	}
	
	public ArrayList<Rent> getRentByphoneNumber(String phoneNum,int number) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getRentByPhoneNumber.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8")
					+"&phoneNum="+URLEncoder.encode(phoneNum, "UTF-8"));	
			//  $sql="select d_id, title, studio, price, rating, genre, release_date from Rent r,DVD d where d.d_id=r.r_id limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		ArrayList<String> R_id = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "R_id");

		if(R_id.isEmpty())
		{
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "c_id");
			ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "d_id");
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "title");
			ArrayList<String> start_date = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "start_date");
			ArrayList<String> due_date = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "due_date");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getRentByPhoneNumber.xml", "overdue");

			int i=0;
			ArrayList<Rent> allRent=new ArrayList<Rent>();

			while(i<R_id.size())
			{
				int tempRID=Integer.parseInt(R_id.get(i));
				int tempCID=Integer.parseInt(c_id.get(i));
				int tempDID=Integer.parseInt(d_id.get(i));
				int tempdue=Integer.parseInt(overdue.get(i));


				Rent temp=new Rent(tempRID, tempCID, tempDID, title.get(i),start_date.get(i), due_date.get(i), tempdue);
				allRent.add(temp);
				i++;
			}
			return allRent;
		}		
	}
	

	public String isValidToRent(int id) throws UnsupportedEncodingException
	{	
		
		URL url;
		try{

			url = new URL(SERVER_ADDRESS + "/isValidToRent.php?"+"c_id="+ URLEncoder.encode(""+id, "UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String c_id = null;
		c_id=getXmlData("xmlFromPHP/isValidToRent.xml", "c_id");
		//System.out.println("id : "+id+" c_id : "+c_id);

		return c_id; //if (null-> can't rent)

	}
	
	public void updateOverdueAfterReturn() throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/updateOverdueAfterReturn.php");
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

	public void updateOverdueInRent()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/updateOverdueInRent.php");
			//  $sql="select d_id, title, studio, price, rating, genre, release_date from Rent r,DVD d where d.d_id=r.r_id limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
	}
	public void updateOverdueInCustomer()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/updateOverdueInCustomer.php");
			//  $sql="select d_id, title, studio, price, rating, genre, release_date from Rent r,DVD d where d.d_id=r.r_id limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
	}
	//title

	public int countAllRent()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countAllRent.php?");
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String rentNumber = getXmlData("xmlFromPHP/countAllRent.xml","listNum");
		if(rentNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(rentNumber);
		return tempNumber;
				
	}
	
	public String isRentDVD(int d_id) throws UnsupportedEncodingException
	{
		URL url;
		try{

			url = new URL(SERVER_ADDRESS + "/isRentDVD.php?"+"d_id="+ URLEncoder.encode(""+d_id, "UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String dvdNumber = getXmlData("xmlFromPHP/isRentDVD.xml","overdue");
//		if(dvdNumber==null)
//			return 0;
//		int tempNumber=Integer.parseInt(dvdNumber);
		return dvdNumber;
				
	}
	
//	public String isRentDVD(int d_id)
//	{
//		URL url=null;
//	
//	
//					url = new URL(SERVER_ADDRESS + "/isRentDVD.php?"+"d_id="+ URLEncoder.encode(""+d_id, "UTF-8"));
//				
//					
//		String overdueNumber = getXmlData("xmlFromPHP/isRentDVD.xml","overdue");
//		System.out.println("gggg "+d_id+" vvv "+overdueNumber);
////		if(overdueNumber==null) //can't find dvd in rent
////			return 2; 
////		else{
////			int tempNumber=Integer.parseInt(overdueNumber);
////			return tempNumber;
////		}
//		return overdueNumber;
//	}
	
	public int countOverdueRent()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countOverdueRent.php?");
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String rentNumber = getXmlData("xmlFromPHP/countOverdueRent.xml","listNum");
		if(rentNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(rentNumber);
		return tempNumber;
				
	}
	
	public int countRentByTitle(String title) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countRentByTitle.php?"+"title="+ URLEncoder.encode(title, "UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String rentNumber = getXmlData("xmlFromPHP/countRentByTitle.xml","listNum");
		if(rentNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(rentNumber);
		return tempNumber;
				
	}
	
	public int countRentByphoneNumber(String phoneNum) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countRentByPhoneNum.php?"+"phoneNum="+ URLEncoder.encode(phoneNum, "UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String rentNumber = getXmlData("xmlFromPHP/countRentByPhoneNum.xml","listNum");
		if(rentNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(rentNumber);
		return tempNumber;
				
	}
	
	public ArrayList<Rent> getAllRent(int number) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllRent.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8"));

			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		ArrayList<String> R_id = getXmlDataList("xmlFromPHP/getAllRent.xml", "R_id");

		if(R_id.isEmpty())
		{
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getAllRent.xml", "c_id");
			ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getAllRent.xml", "d_id");
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getAllRent.xml", "title");
			ArrayList<String> start_date = getXmlDataList("xmlFromPHP/getAllRent.xml", "start_date");
			ArrayList<String> due_date = getXmlDataList("xmlFromPHP/getAllRent.xml", "due_date");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getAllRent.xml", "overdue");

			int i=0;
			ArrayList<Rent> allRent=new ArrayList<Rent>();

			while(i<R_id.size())
			{
				int tempRID=Integer.parseInt(R_id.get(i));
				int tempCID=Integer.parseInt(c_id.get(i));
				int tempDID=Integer.parseInt(d_id.get(i));
				int tempdue=Integer.parseInt(overdue.get(i));


				Rent temp=new Rent(tempRID, tempCID, tempDID, title.get(i),start_date.get(i), due_date.get(i), tempdue);
				allRent.add(temp);
				i++;
			}
			return allRent;
		}		
	}


	public ArrayList<Rent> getCustomerRentDVD(int id) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getCustomerRentDVD.php?"+"c_id="+ URLEncoder.encode(""+id, "UTF-8"));	

			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		ArrayList<String> R_id = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "R_id");

		if(R_id.isEmpty())
		{
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> c_id = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "c_id");
			ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "d_id");
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "title");
			ArrayList<String> start_date = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "start_date");
			ArrayList<String> due_date = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "due_date");
			ArrayList<String> overdue = getXmlDataList("xmlFromPHP/getCustomerRentDVD.xml", "overdue");

			int i=0;
			ArrayList<Rent> allRent=new ArrayList<Rent>();

			while(i<R_id.size())
			{
				int tempRID=Integer.parseInt(R_id.get(i));
				int tempCID=Integer.parseInt(c_id.get(i));
				int tempDID=Integer.parseInt(d_id.get(i));
				int tempdue=Integer.parseInt(overdue.get(i));


				Rent temp=new Rent(tempRID, tempCID, tempDID, title.get(i),start_date.get(i), due_date.get(i), tempdue);
				allRent.add(temp);
				i++;
			}
			return allRent;
		}		
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
