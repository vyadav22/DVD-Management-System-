﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

$phoneNum=$_REQUEST['phoneNum'];
   
 $sql="select * from Customer where phoneNum='%$phoneNum%';";
 $result=mysql_query($sql);
 
 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<DVD>\n";
 
 while($obj = mysql_fetch_object($result))
	{
	 // printf("succes");
	   $c_id=$obj->c_id;
		$phoneNum=$obj->phoneNum;
		$name=$obj->name;
		$address=$obj->address;
		$mem_points=$obj->mem_points;
		$overdue=$obj->overdue;
		echo "<br \>";
		$xmlcode .= "<c_id>$c_id</c_id>\n";
		$xmlcode .= "<phoneNum>$phoneNum</phoneNum>\n";
		$xmlcode .= "<name>$name</name>\n";
		$xmlcode .= "<address>$address</address>\n";
		$xmlcode .= "<mem_points>$mem_points</mem_points>\n";
		$xmlcode .= "<overdue>$overdue</overdue>\n";
	}
 $xmlcode .="</DVD>\n";
 $filename ="xmlFromPHP/getCustomerByPhone.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>