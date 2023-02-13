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

		
		$provider_mobile=$_POST['pmobile'];
		$shopname=$_POST['shopname'];
		$name=$_POST['name'];
		$serviceman_mobile=$_POST['mobile'];
		$email=$_POST['email'];
		$password=$_POST['password'];
		
		
		$q="insert into serviceman values('$name','$serviceman_mobile','$provider_mobile','$shopname','$email','$password')";
		//echo $q;
		if($con->query($q))
		{
			echo "insertion done";
		}
		else
		{
			echo "insertion failed";
		}
	}
?>