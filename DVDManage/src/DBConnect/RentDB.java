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

import Mainsys.DVD;
import Mainsys.Rent;

public class RentDB {
	
	private final String SERVER_ADDRESS = "http://cutters.zz.mu/";
		
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
	public ArrayList<DVD> getAllDVDRent() throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllDVDRent.php");
			//  $sql="select d_id, title, studio, price, rating, genre, release_date from Rent r,DVD d where d.d_id=r.r_id limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		
		ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "d_id");
		
		if(d_id.isEmpty())
		{
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "title");
			ArrayList<String> studio = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "studio");
			ArrayList<String> price = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "price");
			ArrayList<String> rating = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "rating");
			ArrayList<String> genre = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "genre");
			ArrayList<String> release_date = getXmlDataList("xmlFromPHP/getAllDVDRent.xml", "release_date");
			
			int i=0;
			ArrayList<DVD> allDVD=new ArrayList<DVD>();
			
			while(i<d_id.size())
			{
				int tempID=Integer.parseInt(d_id.get(i));
				double tempPrice=Double.parseDouble(price.get(i));
				
				DVD temp=new DVD(tempID, title.get(i), studio.get(i), tempPrice, rating.get(i), genre.get(i), release_date.get(i));
				allDVD.add(temp);
				i++;
			}
			return allDVD;
		}		
	}
	
	public ArrayList<DVD> getAllDVDInStock() throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllDVDInStock.php");
			//    $sql="select * from DVD d where d.d_id NOT IN(select r.d_id from Rent r) limit 10000;";
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		
		ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "d_id");
		
		if(d_id.isEmpty())
		{
			return null;
		}
		else{
			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "title");
			ArrayList<String> studio = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "studio");
			ArrayList<String> price = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "price");
			ArrayList<String> rating = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "rating");
			ArrayList<String> genre = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "genre");
			ArrayList<String> release_date = getXmlDataList("xmlFromPHP/getAllDVDInStock.xml", "release_date");
			
			int i=0;
			ArrayList<DVD> allDVD=new ArrayList<DVD>();
			
			while(i<d_id.size())
			{
				int tempID=Integer.parseInt(d_id.get(i));
				double tempPrice=Double.parseDouble(price.get(i));
				
				DVD temp=new DVD(tempID, title.get(i), studio.get(i), tempPrice, rating.get(i), genre.get(i), release_date.get(i));
				allDVD.add(temp);
				i++;
			}
			return allDVD;
		}		
	}
	
	public String isValidToRent(int id) throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/isValidToRent.php?"+"c_id="+ URLEncoder.encode(""+id, "UTF-8"));
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

		String c_id = null;
		c_id=getXmlData("xmlFromPHP/isValidToRent.xml", "c_id");
		//System.out.println(c_id);

		return c_id; //if (null-> can't rent)

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

	public ArrayList<Rent> getAllRent() throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllRent.php");
			
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
