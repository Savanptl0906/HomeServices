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
		$registration = $_POST['registration'];
		
		$q = "select * from registration where registrationno= '$registration'";
		
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