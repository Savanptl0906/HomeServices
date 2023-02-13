<?php
	
	$sname="localhost";
	$uname="root";
	$psd="";
	$dbname="home_services";
	
	$con=new mysqli($sname,$uname,$psd,$dbname);
	
	if($con->connect_error)
	{
		die("Connection error");
	}
	else
	{
//		echo "Connection done";
		
		
		$token=$_POST['token'];
		$mobile=$_POST['mobile'];
		
		$q="update customer_tokens set token =('$token') where mobile='$mobile'";
		
		if($con->query($q))
		{
			echo "done";
		}
		else
		{
			echo "failed";
		}
	}
?>