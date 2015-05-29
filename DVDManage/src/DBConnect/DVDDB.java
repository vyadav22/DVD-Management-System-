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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import Mainsys.*;

public class DVDDB {

	private final String SERVER_ADDRESS = "http://cutters.zz.mu/";

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

	public ArrayList<DVD> getAllDVD()
	{
		URL url;
		try{
			url = new URL(SERVER_ADDRESS + "/getAllDVD.php");
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
			System.out.println("t");
			return allDVD;
		}		
	}

	public ArrayList<DVD> getDVDSearch(int type,String keyword) throws UnsupportedEncodingException		//占쏙옙천占쌨억옙占쏙옙占쏙옙 占싯띰옙.
	{

		URL url;
		String filename=null;
		String key=null;
		switch(type)
		{
		case 1:
			filename="/getDVDByDate.php?";
			key="release_date=";
			break;
		case 2:
			//$title','$studio',$price,'$rating','$genre','$release_date'
			filename="/getDVDByGenre.php?";
			key="genre=";
			break;
		case 3:
			filename="/getDVDByTitle.php?";
			key="title=";
			break;
		case 4:
			filename="/getDVDById.php?";
			key="d_id=";
			break;
		}
		try {
			url = new URL(SERVER_ADDRESS + filename+key+ URLEncoder.encode(keyword, "UTF-8"));
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

		switch(type)
		{
		case 1:
			filename="xmlFromPHP/getDVDByDate.xml";
			break;
		case 2:
			filename="xmlFromPHP/getDVDByGenre.xml";
			break;
		case 3:
			filename="xmlFromPHP/getDVDByTitle.xml";
			break;
		case 4:
			filename="xmlFromPHP/getDVDById.xml";
			key="d_id=";
			break;
		}
		ArrayList<String> d_id = getXmlDataList(filename, "d_id");

		if(d_id.isEmpty())
		{
			return null;
		}
		else {		

			ArrayList<String> title = getXmlDataList(filename, "title");
			ArrayList<String> studio = getXmlDataList(filename, "studio");
			ArrayList<String> price = getXmlDataList(filename, "price");
			ArrayList<String> rating = getXmlDataList(filename, "rating");
			ArrayList<String> genre = getXmlDataList(filename, "genre");
			ArrayList<String> release_date = getXmlDataList(filename, "release_date");


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

	/*public ArrayList<String> xmlTest(String keyword) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {



			Document document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(SERVER_ADDRESS+"/xmlFromPHP/getAllDVD.xml");


			// xpath 생성

			XPath  xpath = XPathFactory.newInstance().newXPath();



			NodeList  list = (NodeList) xpath.compile(keyword).evaluate(document, XPathConstants.NODESET);




			for( int idx=0; idx<cols.getLength(); idx++ ){



				String ssn=cols.item(idx).getAttributes().item(0).getTextContent();

				System.out.println("ssn................"+ssn);



				expression = "//*[@ssn="+ssn+"]/firstName";

				String firstName = xpath.compile(expression).evaluate(document);

				System.out.println("firstName................"+firstName);



				expression = "//*[@ssn="+ssn+"]/lastName";

				String lastName = xpath.compile(expression).evaluate(document);

				System.out.println("lastName................"+lastName);



			}



		}catch(Exception e){

			e.printStackTrace();

		}
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
	}*/

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
