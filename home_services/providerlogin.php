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
	//	echo "connecion done...";
		$mobile = $_POST['mobile'];
		$password = $_POST['password'];
		
		$q = "select * from provider where mobile= '$mobile' and password = '$password'";
		
		$rs = $con->query($q);
		//print_r($rs);
	
		if($r = mysqli_fetch_assoc($rs))
		{
			echo "success";
		}
		else
		{
			echo "fail";
		}
	}
?>