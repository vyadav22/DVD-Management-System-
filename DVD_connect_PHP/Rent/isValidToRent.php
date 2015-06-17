﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

$c_id=$_REQUEST['c_id'];

 $sql="select c.c_id from Customer c where c.c_id=$c_id and c.overdue=0 and 6 > (select count(*) from Rent r where r.c_id=$c_id); ";
 $result=mysql_query($sql);
 
 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<DVD>\n";
 
 while($obj = mysql_fetch_object($result))
	{
	 // printf("succes");
	   $c_id=$obj->c_id;
		echo "<br \>";
		$xmlcode .= "<c_id>$c_id</c_id>\n";
	}
 $xmlcode .="</DVD>\n";
 $filename ="xmlFromPHP/isValidToRent.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>