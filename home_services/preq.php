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

		$mobile=$_POST['mobile'];
		$lat=$_POST['lat'];
		$lng=$_POST['lng'];
		
		
		$q="insert into provider_location values('$mobile','$lat','$lng')";
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