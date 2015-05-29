﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

 $d_id=$_REQUEST['d_id'];
 $genre=$_REQUEST['genre'];

 $sql="update DVD set genre='$genre' where d_id=$d_id;";
 $result=mysql_query($sql,$conn);


 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<RESULT>$result</RESULT>\n";
 $filename ="xmlFromPHP/modifyDVDGenre.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>