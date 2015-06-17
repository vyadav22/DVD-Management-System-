﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

/*
 $phoneNum="33";
 $password=sha1("1234");
 $name="k";
 $address="c";

*/
  $phoneNum=$_REQUEST['phoneNum'];
 $password=sha1($_REQUEST['password']);
 $name=$_REQUEST['name'];
 $address=$_REQUEST['address'];
 

 $sql="INSERT INTO `Customer`(`c_id`, `phoneNum`, `password`, `name`, `address`, `mem_points`, `overdue`) VALUES
 (0,'$phoneNum','$password','$name','$address',0,0);";
 $result=mysql_query($sql,$conn);


 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<RESULT>$result</RESULT>\n";
 $filename ="xmlFromPHP/insertCustomer.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>