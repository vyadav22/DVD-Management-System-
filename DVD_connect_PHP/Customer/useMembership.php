﻿﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

 $c_id=$_REQUEST['c_id'];
 $mem_points=$_REQUEST['mem_points'];

$sql="update Customer set mem_points=mem_points - $mem_points where c_id=$c_id;";
 $result=mysql_query($sql,$conn);


 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<RESULT>$result</RESULT>\n";
 $filename ="xmlFromPHP/useMembership.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>