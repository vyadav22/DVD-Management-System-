﻿﻿﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

$number=$_REQUEST['number'];
$min=100*($number-1);

  $sql="select * from DVD d where d.d_id NOT IN(select r.d_id from Rent r) limit $min,100;";
 $result=mysql_query($sql);
 
 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<DVD>\n";
 
 while($obj = mysql_fetch_object($result))
	{
	 // printf("succes");
	   $d_id=$obj->d_id;
		$title=$obj->title;
		$studio=$obj->studio;
		$price=$obj->price;
		$rating=$obj->rating;
		$genre=$obj->genre;
		$release_date=$obj->release_date;
		echo "<br \>";
		$xmlcode .= "<d_id>$d_id</d_id>\n";
		$xmlcode .= "<title>$title</title>\n";
		$xmlcode .= "<studio>$studio</studio>\n";
		$xmlcode .= "<price>$price</price>\n";
		$xmlcode .= "<rating>$rating</rating>\n";
		$xmlcode .= "<genre>$genre</genre>\n";
		$xmlcode .= "<release_date>$release_date</release_date>\n";
	}
 $xmlcode .="</DVD>\n";
 $filename ="xmlFromPHP/getAllDVDInStock.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>