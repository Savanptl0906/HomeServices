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

		
		$shopname=$_POST['shopname'];
		$name=$_POST['name'];
		$mobile=$_POST['mobile'];
		$registration=$_POST['registration'];
		$email=$_POST['email'];
		$password=$_POST['password'];
		$address=$_POST['address'];
		$pincode=$_POST['pincode'];
		$category=$_POST['category'];
		$visitcharge=$POST['visitcharge'];
		
		
		$q="insert into provider values('$shopname','$name','$mobile','$registration','$email','$password','$address','$pincode','$category','$visitcharge')";
		$q1="insert into provider_token (mobile,token) values('$mobile','0')";
		if($con->query($q) && $con->query($q1))
				{
			echo "insertion done";
		}
		else
		{
			echo "insertion failed";
		}
	}
?>