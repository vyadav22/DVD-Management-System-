﻿﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u606571805_cook"; 
$db_passwd = "777777";
$db_name = "u606571805_cook"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

 $sql="update Customer set overdue=0 where overdue=1 and c_id not in(select r.c_id from Rent r where overdue=1);";
 $result=mysql_query($sql,$conn);


 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<RESULT>$result</RESULT>\n";
 $filename ="xmlFromPHP/updateOverdueAfterReturn.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>