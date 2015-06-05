﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

$genre=$_REQUEST['genre'];

 $sql="select count(*) as listNum from DVD where genre like '$genre' or genre like '%/$genre' or genre like '$genre/%';";
 $result=mysql_query($sql);
 
 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<DVD>\n";
 
 while($obj = mysql_fetch_object($result))
	{
	    $listNum=$obj->listNum;
		echo "<br \>";
		$xmlcode .= "<listNum>$listNum</listNum>\n";
		}
  $xmlcode .="</DVD>\n";
 $filename ="xmlFromPHP/countGenreDVD.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>