﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

$password=sha1($_REQUEST['password']);
$phoneNum=$_REQUEST['phoneNum'];

 $sql="select c_id from Customer where phoneNum='$phoneNum' and password='$password';";

 $result=mysql_query($sql);
 
 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<DVD>\n";
 
 while($obj = mysql_fetch_object($result))
	{
	  printf("succes");
		$c_id=$obj->c_id;
		echo "<br \>";
		$xmlcode .= "<c_id>$c_id</c_id>\n";
	}
 $xmlcode .="</DVD>\n";
 //$xmlcode .="<RESULT>$result</RESULT>\n";
 //$dir = "/xmlFromPHP"; //파일이 있을 디렉토리
 $filename ="xmlFromPHP/checkingRight.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>