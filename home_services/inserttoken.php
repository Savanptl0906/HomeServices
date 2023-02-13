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
		
		
		$name=$_POST['name'];
		$mobile=$_POST['mobile'];
		$email=$_POST['email'];
		$password=$_POST['password'];
		$address=$_POST['address'];
		$pincode=$_POST['pincode'];
		
		
		$q="insert into customer values('$name','$mobile','$email','$password','$address','$pincode')";

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