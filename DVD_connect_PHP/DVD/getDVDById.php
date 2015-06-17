﻿﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");


 $d_id=$_REQUEST['d_id'];

  $sql="select * from DVD where d_id=$d_id;";
////////////////////////id로 자신의 recommend 출력/////////////////////// id로 phoneNumber찾는거 이용해서 자기 전화번호
//////찾은 다음에 비교해서 배열 2개 중에 앞에 자기 번호가 있으면 자기가 보낸거, 뒤에 있으면 자기가 받은거.
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
 $filename ="xmlFromPHP/getDVDById.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>