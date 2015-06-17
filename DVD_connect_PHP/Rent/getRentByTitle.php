﻿﻿<?php
$db_host = "mysql.hostinger.kr"; 
$db_user = "u625795801_cook"; 
$db_passwd = "777777";
$db_name = "u625795801_ccs"; 
$conn = mysql_connect($db_host,$db_user,$db_passwd) or die("MySQL Server fail");
mysql_select_db($db_name,$conn);
 mysql_query("set names utf8");

 $number=$_REQUEST['number'];
$min=100*($number-1);

 $title=$_REQUEST['title'];
 
 $sql="select r.R_id, r.d_id, r.c_id, d.title, r.start_date, r.due_date, r.overdue from Rent r, DVD d where r.title='$title' and r.d_id=d.d_id limit $min,100;;";

 $result=mysql_query($sql);
 
 $xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n";
 $xmlcode .="<DVD>\n";
 
 while($obj = mysql_fetch_object($result))
	{
		 // printf("succes");
	    $R_id=$obj->R_id;
		$c_id=$obj->c_id;
		$d_id=$obj->d_id;
		$title=$obj->title;
		$start_date=$obj->start_date;
		$due_date=$obj->due_date;
		$overdue=$obj->overdue;
		echo "<br \>";
		
		$xmlcode .= "<R_id>$R_id</R_id>\n";
$xmlcode .= "<d_id>$d_id</d_id>\n";
$xmlcode .= "<c_id>$c_id</c_id>\n";
		$xmlcode .= "<title>$title</title>\n";
		$xmlcode .= "<start_date>$start_date</start_date>\n";
		$xmlcode .= "<due_date>$due_date</due_date>\n";
		$xmlcode .= "<overdue>$overdue</overdue>\n";
	}
 $xmlcode .="</DVD>\n";
 $filename ="xmlFromPHP/getRentByTitle.xml"; //파일 이름
 
 file_put_contents($filename, $xmlcode);
 mysql_close($conn);
 
 ?>