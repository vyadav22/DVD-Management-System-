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

public class DVDDB {

	private final String SERVER_ADDRESS = "http://cutters.pe.hu/";

	
	public DVDDB() {
		// TODO Auto-generated constructor stub
	}

	//$title','$studio',$price,'$rating','$genre','$release_date'
	public void insertDVD(String title, String studio, double price, String rating, String genre, String release_date)
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/insertDVD.php?"+"title="+ URLEncoder.encode(title, "UTF-8")
					+"&studio="+URLEncoder.encode(studio, "UTF-8")
					+"&price="+URLEncoder.encode(""+price, "UTF-8")
					+"&rating="+URLEncoder.encode(rating,"UTF-8")
					+"&genre="+URLEncoder.encode(genre,"UTF-8")
					+"&release_date="+URLEncoder.encode(release_date,"UTF-8"));
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

	public void deleteDVD(int d_id)
	{
		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/deleteDVD.php?"+"d_id="+ URLEncoder.encode(""+d_id, "UTF-8")); //�먭린 �꾩씠��
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

	public ArrayList<DVD> getAllDVD(int number) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllDVD.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		ArrayList<DVD> allDVD=null;
		ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getAllDVD.xml","d_id");
		if(d_id.isEmpty())
		{
			System.out.println("v");
			return allDVD;
		}
		else{

			//String title, String studio, double price, String rating, String genre, String release_date)
			ArrayList<String> title = getXmlDataList("xmlFromPHP/getAllDVD.xml","title");
			ArrayList<String> studio = getXmlDataList("xmlFromPHP/getAllDVD.xml","studio");
			ArrayList<String> price = getXmlDataList("xmlFromPHP/getAllDVD.xml","price");
			ArrayList<String> rating = getXmlDataList("xmlFromPHP/getAllDVD.xml","rating");
			ArrayList<String> genre = getXmlDataList("xmlFromPHP/getAllDVD.xml","genre");
			ArrayList<String> release_date =getXmlDataList("xmlFromPHP/getAllDVD.xml","release_date");
			int i=0;
			allDVD=new ArrayList<DVD>();

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

	public ArrayList<DVD> getAllDVDInStock(int number) throws UnsupportedEncodingException
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllDVDInStock.php?"+"number="+ URLEncoder.encode(""+number, "UTF-8"));
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
	
	
	public int countAllDVD()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countAllDVD.php?");
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String dvdNumber = getXmlData("xmlFromPHP/countAllDVD.xml","listNum");
		if(dvdNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(dvdNumber);
		return tempNumber;
				
	}
	
	public int countDVDInStock()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/countDVDInStock.php?");
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String dvdNumber = getXmlData("xmlFromPHP/countDVDInStock.xml","listNum");
		if(dvdNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(dvdNumber);
		return tempNumber;			
	}
	
	public int countSearchDVD(String type, String keyword) throws UnsupportedEncodingException
	{
		URL url;
		try{

			url = new URL(SERVER_ADDRESS + "/countSearchDVD.php?"+"type="+ URLEncoder.encode(""+type, "UTF-8")
					+"&keyword="+URLEncoder.encode(""+keyword,"UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String dvdNumber = getXmlData("xmlFromPHP/countSearchDVD.xml","listNum");
		if(dvdNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(dvdNumber);
		return tempNumber;
				
	}
	
	public int countGenreDVD(String keyword) throws UnsupportedEncodingException
	{
		URL url;
		try{

			url = new URL(SERVER_ADDRESS + "/countGenreDVD.php?"+"genre="+ URLEncoder.encode(""+keyword, "UTF-8"));
					
			try{
				url.openStream();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		String dvdNumber = getXmlData("xmlFromPHP/countGenreDVD.xml","listNum");
		if(dvdNumber==null)
			return 0;
		int tempNumber=Integer.parseInt(dvdNumber);
		return tempNumber;
				
	}
	
	public DVD getDVDByID(int id) throws UnsupportedEncodingException
	{
		URL url;

		try {
			url = new URL(SERVER_ADDRESS + "/getDVDById.php?"+"d_id="+ URLEncoder.encode(""+id, "UTF-8"));
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

		String d_id=getXmlData("xmlFromPHP/getDVDById", "d_id");
		if(d_id==null)
			return null;
		String title=getXmlData("xmlFromPHP/getDVDById", "title"); 
		String studio=getXmlData("xmlFromPHP/getDVDById", "studio"); 
		String price=getXmlData("xmlFromPHP/getDVDById", "price"); 
		String rating=getXmlData("xmlFromPHP/getDVDById", "rating"); 
		String genre=getXmlData("xmlFromPHP/getDVDById", "genre"); 
		String release_date=getXmlData("xmlFromPHP/getDVDById", "release_date"); 
		//System.out.println(c_id);
		
	
		int tempID=Integer.parseInt(d_id);
		double tempPrice=Double.parseDouble(price);
		
		DVD newDVD = new DVD(tempID,title,studio,tempPrice,rating,genre,release_date); 
		return newDVD; 
	}
	
//	public ArrayList<DVD> getDVDSearch(int type,String keyword,int number) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
//	{
//
//		URL url;
//		String filename=null;
//		String key=null;
//		switch(type)
//		{
//		case 1:
//			filename="/getDVDByDate.php?";
//			key="release_date=";
//			break;
//		case 2:
//			//$title','$studio',$price,'$rating','$genre','$release_date'
//			filename="/getDVDByGenre.php?";
//			key="genre=";
//			break;
//		case 3:
//			filename="/getDVDByTitle.php?";
//			key="title=";
//			break;
//		case 4:
//			filename="/getDVDById.php?";
//			key="d_id=";
//			break;
//		}
//		try {
//			url = new URL(SERVER_ADDRESS + filename+key+ URLEncoder.encode(keyword, "UTF-8")
//					+"&number="+URLEncoder.encode(""+number,"UTF-8"));
//			try {
//				url.openStream();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		switch(type)
//		{
//		case 1:
//			filename="xmlFromPHP/getDVDByDate.xml";
//			break;
//		case 2:
//			filename="xmlFromPHP/getDVDByGenre.xml";
//			break;
//		case 3:
//			filename="xmlFromPHP/getDVDByTitle.xml";
//			break;
//		case 4:
//			filename="xmlFromPHP/getDVDById.xml";
//			key="d_id=";
//			break;
//		}
//		ArrayList<String> d_id = getXmlDataList(filename, "d_id");
//
//		if(d_id.isEmpty())
//		{
//			System.out.println("vx");
//			return null;
//		}
//		else {		
//
//			ArrayList<String> title = getXmlDataList(filename, "title");
//			ArrayList<String> studio = getXmlDataList(filename, "studio");
//			ArrayList<String> price = getXmlDataList(filename, "price");
//			ArrayList<String> rating = getXmlDataList(filename, "rating");
//			ArrayList<String> genre = getXmlDataList(filename, "genre");
//			ArrayList<String> release_date = getXmlDataList(filename, "release_date");
//
//
//			int i=0;
//			ArrayList<DVD> allDVD=new ArrayList<DVD>();
//
//			while(i<d_id.size())
//			{
//				int tempID=Integer.parseInt(d_id.get(i));
//				double tempPrice=Double.parseDouble(price.get(i));
//
//				DVD temp=new DVD(tempID, title.get(i), studio.get(i), tempPrice, rating.get(i), genre.get(i), release_date.get(i));
//				//System.out.println(release_date.get(i)+"bb"+temp.getDate());
//				allDVD.add(temp);
//				i++;
//			}
//			return allDVD;
//		}		
//	}	
	public ArrayList<DVD> getDVDSearch(String type,String keyword,int number) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/getDVDBySearch.php?"+"type="+ URLEncoder.encode(type, "UTF-8")
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

		ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "d_id");

		if(d_id.isEmpty())
		{
			System.out.println("vx");
			return null;
		}
		else {		

			ArrayList<String> title = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "title");
			ArrayList<String> studio = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "studio");
			ArrayList<String> price = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "price");
			ArrayList<String> rating = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "rating");
			ArrayList<String> genre = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "genre");
			ArrayList<String> release_date = getXmlDataList("xmlFromPHP/getDVDBySearch.xml", "release_date");


			int i=0;
			ArrayList<DVD> allDVD=new ArrayList<DVD>();

			while(i<d_id.size())
			{
				int tempID=Integer.parseInt(d_id.get(i));
				double tempPrice=Double.parseDouble(price.get(i));

				DVD temp=new DVD(tempID, title.get(i), studio.get(i), tempPrice, rating.get(i), genre.get(i), release_date.get(i));
				//System.out.println(release_date.get(i)+"bb"+temp.getDate());
				allDVD.add(temp);
				i++;
			}
			return allDVD;
		}		
	}	
	public ArrayList<DVD> getDVDSearchByGenre(String keyword,int number) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		try {
			url = new URL(SERVER_ADDRESS + "/getDVDByGenre.php?"+"genre="+ URLEncoder.encode(keyword, "UTF-8")
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

		ArrayList<String> d_id = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "d_id");

		if(d_id.isEmpty())
		{
			System.out.println("vx");
			return null;
		}
		else {		

			ArrayList<String> title = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "title");
			ArrayList<String> studio = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "studio");
			ArrayList<String> price = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "price");
			ArrayList<String> rating = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "rating");
			ArrayList<String> genre = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "genre");
			ArrayList<String> release_date = getXmlDataList("xmlFromPHP/getDVDByGenre.xml", "release_date");


			int i=0;
			ArrayList<DVD> allDVD=new ArrayList<DVD>();

			while(i<d_id.size())
			{
				int tempID=Integer.parseInt(d_id.get(i));
				double tempPrice=Double.parseDouble(price.get(i));

				DVD temp=new DVD(tempID, title.get(i), studio.get(i), tempPrice, rating.get(i), genre.get(i), release_date.get(i));
				//System.out.println(release_date.get(i)+"bb"+temp.getDate());
				allDVD.add(temp);
				i++;
			}
			return allDVD;
		}		
	}	

	public void modifyDVD(int d_id, int type,String keyword) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		String filename=null;
		String key=null;
		switch(type)
		{
		case 1:
			filename="/modifyDVDDate.php?";
			key="release_date=";
			break;
		case 2:
			//$title','$studio',$price,'$rating','$genre','$release_date'
			filename="/modifyDVDGenre.php?";
			key="genre=";
			break;
		case 3:
			filename="/modifyDVDTitle.php?";
			key="title=";
			break;
		case 4:
			filename="/modifyDVDPrice.php?";
			key="price=";
			break;
		case 5:
			//$title','$studio',$price,'$rating','$genre','$release_date'
			filename="/modifyDVDRating.php?";
			key="rating=";
			break;
		case 6:
			filename="/modifyDVDStudio.php?";
			key="studio=";
			break;
		}
		try {
			url = new URL(SERVER_ADDRESS + filename+key+ URLEncoder.encode(keyword, "UTF-8")
					+"&d_id="+ URLEncoder.encode(""+d_id, "UTF-8"));
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
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//xml파일경로

		NodeList list = doc.getElementsByTagName(str);

		Element el=(Element) list.item(0);
		String getElement=null;
		if(el==null)
		{
			return getElement;
		}
		else
		{
			getElement=el.getTextContent(); 
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
